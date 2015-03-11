package com.tjhruska.mc.apps

import groovy.transform.Synchronized
import groovy.util.logging.Slf4j

import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.List
import java.util.Map

import javax.annotation.Resource

import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.RowMapper

import com.tjhruska.mc.util.BeanNameAwareRunnable
import com.tjhruska.mc.util.Duo
import com.tjhruska.mc.util.spring.Context
import com.tjhruska.mc.util.tagReplacement.SQLTemplate

@Slf4j
class McInstaller extends BeanNameAwareRunnable {

  SQLTemplate initInstalled
  SQLTemplate getInstalled
  SQLTemplate addInstalled
  Map<String, String> installProperties
  List installTasks

  @Synchronized
  public void run() {
    installProperties.each { key, value ->
      log.info("installProperty: ${key}, value: ${value}")
    }

    List<String> alreadyCompletedTasks = getAlreadyCompletedTasks()

    installTasks.eachWithIndex { task, i ->
      def taskName = task.getBeanName()
      def completedTask = (alreadyCompletedTasks.size > 0 ? alreadyCompletedTasks.remove(0): null)

      if (completedTask != null && completedTask.equals(taskName)) {
        log.info(">>>>>>>> task {} previously run, skipping.", taskName)
        return
      } else if (completedTask != null) {
        log.error("installTasks list has changed in configuration from what has already been run in db."
            + "  Not allowed. Previously run {}, asked to run {} at index {}",
            completedTask, taskName, i)
        throw new RuntimeException("installTasks tasks that have already been run should not be changed in configuration.")
      }

      log.info(">>>>>>>> about to run install task {}.", taskName)
      if (task instanceof SQLTemplate) {
        task.jdbcQuery(installProperties)
      } else {
        task.run()
      }
      log.info(">>>>>>>> finished run install task {}.", taskName)
      installProperties.put('install_version', taskName)
      addInstalled.update(installProperties)
      installProperties.remove('install_version')
    }
  }

  private ArrayList getAlreadyCompletedTasks() {
    initInstalled.jdbcQuery(installProperties)
    def alreadyCompletedInstalls
    try{
      alreadyCompletedInstalls = getInstalled.query( installProperties,
          new RowMapper<String> (){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
              return rs.getString('task')
            }
          })
      log.info('found {} previous installation records.', alreadyCompletedInstalls.size())
    }catch (Exception e){
      log.info('failed to get previous installation list, assuming no installs and continuing.', e)
      alreadyCompletedInstalls = new ArrayList<String>()
    }
    return alreadyCompletedInstalls
  }
}