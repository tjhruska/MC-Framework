/**
Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
  
package com.tjhruska.mc.apps

import groovy.util.logging.Slf4j

import java.io.File
import java.io.IOException
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.ListIterator
import java.util.Map

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.RowMapper

import com.tjhruska.mc.util.BeanNameAwareRunnable
import com.tjhruska.mc.util.FileUtils
import com.tjhruska.mc.util.spring.Context
import com.tjhruska.mc.util.tagReplacement.SQLTemplate
import com.tjhruska.mc.util.tagReplacement.SQLTemplateImpl

@Slf4j
class McFileGenerator extends BeanNameAwareRunnable {

  List<SQLTemplateImpl> fragmentQueries
  Map<String, String> installProperties

  public void run() {

    //log the installProperties
    installProperties.each { key, value ->
      log.info("installProperty: ${key}, value: ${value}")
    }

    //if not groovy, pull the pogo command if it is present
    if (!installProperties.get('typeOfPlainOldObject').equals('PlainOldGroovyObject'))
      fragmentQueries.removeAll {
        it.beanName.equals('getPogoCodeFragments')
      }

    //if not java, pull the pojo command if it is present
    if (!installProperties.get('typeOfPlainOldObject').equals('PlainOldJavaObject'))
      fragmentQueries.removeAll {
        it.beanName.equals('getPojoCodeFragments')
      }

    //prep the backupDirectory name to include our application name, and the datetime
    StringBuilder backupDirectory = buildBackupDirectoryName(installProperties.get('backupDirectory'))

    //run the queries to get back a lists of CommandFragments
    Map<String, List<FileFragment>> fileFragments = [:]
    fragmentQueries.each { fragmentQuery ->
      String beanName = fragmentQuery.beanName
      log.debug('Found fragment query {}.', fragmentQuery.beanName)
      fileFragments.put(beanName, getFileFragments(fragmentQuery))
      log.debug('found {} fileFragments for query {}.', fileFragments.get(beanName).size(), beanName)
    }

    //assemble the fragments together
    Map<String, List<AssembledFile>> assembledFiles = [:]
    fileFragments.each { k, v ->
      assembledFiles.put(k, assembleFiles(v))
      log.debug('For {}, assembled {} fileFragments into {} assembledFiles',
          [
            k,
            Integer.toString(v.size()),
            Integer.toString(assembledFiles.get(k).size())
          ].toArray())
    }

    //backup existing files and write out new ones
    int totalBackupCount = 0
    int totalFilesWritten = 0
    assembledFiles.each { k, v ->
      int backupCount = backupOldFiles(k, v, backupDirectory)
      totalBackupCount += backupCount
      log.info('For {}, backedUp {} files to {}.', [
        k,
        backupCount,
        installProperties.get('backupDirectory')
      ].toArray())
      int filesWritten = writeOutFiles(v)
      log.info('For {} wrote out {} files to {}.', [
        k,
        Integer.toString(filesWritten),
        installProperties.get('rootDirectory')
      ].toArray())
      totalFilesWritten += filesWritten
    }
    log.info('Backedup a total of {} files.', totalBackupCount)
    log.info('Wrote out a total of {}.', totalFilesWritten)
  }

  public class FileFragment{
    public long id
    public int sequence
    public String folder_tag
    public String java_package
    public String fileName
    public String fileName2
    public String fragment
    public String fragment2
  }

  private List<FileFragment> getFileFragments(SQLTemplate sqlTemplate){
    List<FileFragment> fileFragments = null
    log.trace('about to run {} query:\n{}', sqlTemplate.beanName, sqlTemplate.applyTags(installProperties))
    try{
      fileFragments = sqlTemplate.query(installProperties,
          new RowMapper<FileFragment> (){
            public FileFragment mapRow(ResultSet rs, int rowNum) throws SQLException {
              FileFragment fileFragment = new FileFragment()
              fileFragment.id = rs.getLong('id')
              fileFragment.sequence = rs.getInt('sequence')
              fileFragment.folder_tag = rs.getString('folder_tag')
              fileFragment.java_package = rs.getString('java_package')
              fileFragment.fileName = rs.getString('filename')
              fileFragment.fileName2 = rs.getString('filename2')
              fileFragment.fragment = rs.getString('fragment')
              fileFragment.fragment2 = rs.getString('fragment2')
              return fileFragment
            }})
    } catch (Exception e){
      log.error('Failed to retrieve command fragements using {}', sqlTemplate.beanName)
      log.error('exception:', e)
      if (fileFragments == null)
        fileFragments = new ArrayList<FileFragment>()
    }
    return fileFragments
  }

  public class AssembledFile{
    public long id
    public String folder_tag
    public String java_package
    public StringBuilder filename
    public StringBuilder filename2
    public StringBuilder file
    public StringBuilder file2
  }

  public List<AssembledFile> assembleFiles(List<FileFragment> fileFragments){
    List<AssembledFile> assembledFiles = new ArrayList<AssembledFile>()

    //ListIterator<FileFragment> i = fileFragments.listIterator()
    if (fileFragments.size() < 1)
      return assembledFiles

    AssembledFile currentFile = null
    Long prevId = 0L

    fileFragments.each { fileFragment ->
      log.trace('ID-{}, sequence-{}', [
        fileFragment.id+'',
        fileFragment.sequence+''
      ].toArray())

      //store a link to the old file in-case we are changing
      AssembledFile prevFile = currentFile

      //current item not equal to prev item, ready a new currentCommand
      if (!prevId.equals(fileFragment.id)){
        log.trace('changed so creating new assembledFile')
        currentFile = new AssembledFile()
        currentFile.id = fileFragment.id
        currentFile.java_package = fileFragment.java_package
        currentFile.folder_tag = fileFragment.folder_tag
        currentFile.filename = new StringBuilder(fileFragment.fileName)
        currentFile.filename2 = (fileFragment.fileName2 == null ? null : new StringBuilder(fileFragment.fileName2))
        currentFile.file = new StringBuilder()
        currentFile.file2 = new StringBuilder()
      }

      //copy current fragment out - if on last record it is also copied into previous... :)
      if (fileFragment.fragment != null) currentFile.file.append(fileFragment.fragment)
      if (fileFragment.fragment2 != null) currentFile.file2.append(fileFragment.fragment2)

      //if we are done with command fragments, but prevFile is null, load it with the current one (special case for single file writing)
      if (prevFile == null && fileFragment.equals(fileFragments.last()))
        prevFile = currentFile

      //if we are done with command fragments, or onto a new one write out previous one
      if (prevFile != null &&
      (fileFragment.equals(fileFragments.last()) || !prevId.equals(fileFragment.id)))
      {
        log.trace('More rows={}, if false or record changed, then save off ID-{}', [
          (!fileFragment.equals(fileFragments.last())).toString(),
          prevFile.id.toString()
        ].toArray())
        assembledFiles.add(prevFile)
        if (fileFragment.equals(fileFragments.last()) && prevFile.id != currentFile.id){
          assembledFiles.add(currentFile)
          log.trace('More rows={}, if false or record changed, then save off ID-{}', [
            (!fileFragment.equals(fileFragments.last())).toString(),
            currentFile.id.toString()
          ].toArray())
        }
      }

      //set id for comparison in next iteration
      prevId = fileFragment.id

    }
    return assembledFiles
  }

  public int backupOldFiles(String beanName, List<AssembledFile> assembledFiles, StringBuilder backupDirectory){
    int backupCount = 0

    //create backup directory
    File bDFile = new File(backupDirectory.toString())
    if (!bDFile.exists() && !(new File(backupDirectory.toString()).mkdir())){
      throw new RuntimeException('Could not create backup directory: ' + backupDirectory)
    }

    assembledFiles.each { assembledFile ->

      String sourceDirectory = installProperties.get(assembledFile.folder_tag)
      //first backup the base file if it exists
      StringBuilder s = new StringBuilder(sourceDirectory).append('/')
      if (assembledFile.java_package != null)
        s.append(assembledFile.java_package).append('/')
      s.append(assembledFile.filename)
      String fqfName = s.toString()
      File file = new File(fqfName)

      if (file.exists()) {
        //create backup dir
        s = new StringBuilder(backupDirectory)
        if (assembledFile.java_package != null)
          s.append(assembledFile.java_package)

        String backupDir = s.toString()
        File backupDirFile = new File(backupDir)
        if (!backupDirFile.exists() && !backupDirFile.mkdirs())
          throw new RuntimeException('Could not create backup directorIES: ' + backupDir + ' for file: ' + fqfName)

        //backup dir exists, so rename the old file
        s.append('/').append(assembledFile.filename)
        String fqfNameBackup = s.toString()
        if (!file.renameTo(new File(fqfNameBackup)))
          throw new RuntimeException('Could not create backup: ' + fqfNameBackup + ' for file: ' + fqfName)

        backupCount++
        log.info('backed up file {} to: {}', fqfName, fqfNameBackup)

      }else{
        log.info('no backup needed, no prev ver of file exists: {}', fqfName)
      }

      if (assembledFile.filename2 != null){
        //now backup the helper file if it exists
        s = new StringBuilder(sourceDirectory).append('/')
        if (assembledFile.java_package != null)
          s.append(assembledFile.java_package).append('/')
        s.append(assembledFile.filename2)
        fqfName = s.toString()
        file = new File(fqfName)

        if (file.exists()) {
          //backup dir exists (uses same as base file...), so rename the old file
          s = new StringBuilder(backupDirectory)
          if (assembledFile.java_package != null)
            s.append(assembledFile.java_package).append('/')
          s.append(assembledFile.filename2)
          String fqfNameBackup = s.toString()
          if (!file.renameTo(new File(fqfNameBackup)))
            throw new RuntimeException('Could not create backup: ' + fqfNameBackup + ' for file: ' + fqfName)

          backupCount++
          log.info('backed up file {} to: {}', fqfName, fqfNameBackup)

        }else{
          log.info('no backup needed, no prev ver of file exists: {}', fqfName)
        }
      }

    }

    return backupCount
  }

  public int writeOutFiles(List<AssembledFile> assembledFiles){
    int writeFileCount = 0

    assembledFiles.each { assembledFile ->

      String sourceDirectory = installProperties.get(assembledFile.folder_tag)
      StringBuilder directoryName = null
      StringBuilder fileName = null
      try{
        //build the directory
        directoryName = new StringBuilder(sourceDirectory).append('/')
        if (assembledFile.java_package != null)
          directoryName.append(assembledFile.java_package).append('/')

        //first write out the base file
        fileName = assembledFile.filename
        FileUtils.writeFile(directoryName, fileName, assembledFile.file)
        writeFileCount++
        log.info('wrote file: {}{}', directoryName, fileName)

        if (assembledFile.filename2 != null){
          //then write out the helper file
          fileName = assembledFile.filename2
          FileUtils.writeFile(directoryName, fileName, assembledFile.file2)
          writeFileCount++
          log.info('wrote file: {}{}', directoryName, fileName)
        }

      }catch(IOException e){
        log.error('failed to write out file: {}/{}', directoryName, fileName)
        log.error('with exception:', e)
        throw new RuntimeException(e)
      }
    }

    return writeFileCount
  }
}