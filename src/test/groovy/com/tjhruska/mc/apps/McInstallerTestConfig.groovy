package com.tjhruska.mc.apps

import javax.annotation.Resource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import com.tjhruska.mc.util.tagReplacement.SQLTemplate
import org.springframework.context.annotation.ImportResource


@ImportResource( [ 'classpath:install_0900_json_column_test.tsql.xml'] )
@Import([DataResourcesConfig])
@Configuration
public class McInstallerTestConfig extends McInstallerAbstractConfig {

  @Resource (name="install_0900_json_column_test")
  SQLTemplate install_0900_json_column_test

  @Bean
  @Override
  public McInstaller projectMcInstaller() {
    McInstaller mcInstaller = getMcInstaller()
    3.times {
      mcInstaller.installTasks.pop()
    }
    mcInstaller.installTasks.add(install_0900_json_column_test)
    mcInstaller.installTasks.add(mcTableCreator)
    mcInstaller.installTasks.add(mcEnumDbSynchronizer)
    mcInstaller.installTasks.add(mcFileGenerator)
    return mcInstaller
  }
}