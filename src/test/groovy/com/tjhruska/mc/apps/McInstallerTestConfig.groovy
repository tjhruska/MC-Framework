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