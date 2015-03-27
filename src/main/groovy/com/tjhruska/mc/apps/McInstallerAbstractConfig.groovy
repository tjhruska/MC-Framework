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

import javax.annotation.Resource

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Configuration


import com.tjhruska.mc.util.tagReplacement.SQLTemplate;

import org.springframework.context.annotation.Configuration


@ImportResource( [
  'classpath:conf/mcInstaller/sql/initInstalled.tsql.xml',
  'classpath:conf/mcInstaller/sql/getInstalled.tsql.xml',
  'classpath:conf/mcInstaller/sql/addInstalled.tsql.xml',
  'classpath:conf/mcInstaller/sql/install_0000_enum_model.tsql.xml',
  'classpath:conf/mcInstaller/sql/install_0001_mc_model.tsql.xml',
  'classpath:conf/mcInstaller/sql/install_0002_mc_table_ins.tsql.xml',
  'classpath:conf/mcInstaller/sql/install_0003_mc_col_ins.tsql.xml',
  'classpath:conf/mcInstaller/sql/install_0004_update_enum_model.tsql.xml'
] )
@Configuration
@Import([McEnumDbSynchronizerConfig, McFileGeneratorConfig, McTableCreatorConfig])
abstract class McInstallerAbstractConfig {

  @Value('${mc.schemaName}')
  private String schemaName

  @Value('${mc.adminUser}')
  private String adminUser

  @Value('${mc.packageBase}')
  private String packageBase

  @Resource (name='initInstalled')
  SQLTemplate initInstalled

  @Resource (name='getInstalled')
  SQLTemplate getInstalled

  @Resource (name='addInstalled')
  SQLTemplate addInstalled

  @Resource (name='mcEnumDbSynchronizer')
  McEnumDbSynchronizer mcEnumDbSynchronizer

  @Resource (name='mcEnumDbSynchronizerSkipLoad')
  McEnumDbSynchronizer mcEnumDbSynchronizerSkipLoad

  @Resource (name='mcTableCreator')
  McTableCreator mcTableCreator

  @Resource (name='mcFileGenerator')
  McFileGenerator mcFileGenerator

  @Resource (name="install_0000_enum_model")
  SQLTemplate install_0000_enum_model

  @Resource (name="install_0001_mc_model")
  SQLTemplate install_0001_mc_model

  @Resource (name="install_0002_mc_table_ins")
  SQLTemplate install_0002_mc_table_ins

  @Resource (name="install_0003_mc_col_ins")
  SQLTemplate install_0003_mc_col_ins

  @Resource (name="install_0004_update_enum_model")
  SQLTemplate install_0004_update_enum_model

  abstract McInstaller projectMcInstaller()

  protected McInstaller getMcInstaller() {
    McInstaller mcInstaller = new McInstaller()

    mcInstaller.initInstalled = initInstalled
    mcInstaller.getInstalled = getInstalled
    mcInstaller.addInstalled = addInstalled

    def installProperties = [:]
    installProperties['schema_name'] = schemaName
    installProperties['owner'] = adminUser
    installProperties['packageBase'] = packageBase
    mcInstaller.installProperties = installProperties

    def installTasks = []
    installTasks.add(install_0000_enum_model)
    installTasks.add(mcEnumDbSynchronizerSkipLoad)
    installTasks.add(install_0001_mc_model)
    installTasks.add(install_0002_mc_table_ins)
    installTasks.add(install_0003_mc_col_ins)
    installTasks.add(install_0004_update_enum_model)
    installTasks.add(mcTableCreator)
    installTasks.add(mcEnumDbSynchronizer)
    installTasks.add(mcFileGenerator)
    mcInstaller.installTasks = installTasks

    mcInstaller
  }
}