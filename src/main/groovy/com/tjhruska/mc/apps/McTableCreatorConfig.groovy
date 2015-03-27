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
import org.springframework.context.annotation.ImportResource

import com.tjhruska.mc.util.tagReplacement.SQLTemplate


@ImportResource( [
  'classpath:conf/mcTableCreator/sql/buildTableCreatorCommands.tsql.xml',
  'classpath:conf/mcTableCreator/sql/getExistingTablesAndViews.tsql.xml' ] )
@Configuration
public class McTableCreatorConfig {

  @Value('${mc.schemaName}')
  private String schemaName

  @Value('${mc.adminUser}')
  private String adminUser

  @Value('${mc.backupDirectory}')
  private String backupDirectory

  @Resource (name='buildTableCreatorCommands')
  private SQLTemplate buildTableCreatorCommands

  @Resource (name='getExistingTablesAndViews')
  private SQLTemplate getExistingTablesAndViews

  @Bean
  McTableCreator mcTableCreator() {
    McTableCreator mcTableCreator = new McTableCreator()

    def installProperties = [:]
    installProperties['schema_name'] = schemaName
    installProperties['owner'] = adminUser
    installProperties['backupDirectory'] = backupDirectory
    mcTableCreator.installProperties = installProperties

    mcTableCreator.buildTableCreatorCommands = buildTableCreatorCommands
    mcTableCreator.getExistingTablesAndViews = getExistingTablesAndViews

    mcTableCreator
  }
}