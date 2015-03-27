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

import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Configuration

import com.tjhruska.mc.util.tagReplacement.SQLTemplate
import com.tjhruska.mc.util.tagReplacement.Template
import com.tjhruska.mc.util.tagReplacement.TemplateImpl

@ImportResource( [
  'classpath:conf/mcFileGenerator/sql/getOrmCodeFragments.tsql.xml',
  'classpath:conf/mcFileGenerator/sql/getGroovyEnumCodeFragments.tsql.xml',
  'classpath:conf/mcFileGenerator/sql/getPogoCodeFragments.tsql.xml',
  'classpath:conf/mcFileGenerator/sql/getPogoTestCodeFragments.tsql.xml' ] )
@Configuration
class McFileGeneratorConfig {

  @Value('${mc.schemaName}')
  private String schemaName

  @Value('${mc.copyRightHeader}')
  private String copyRightHeader

  @Value('${mc.rootDirectory}')
  private String rootDirectory

  @Value('${mc.rootDirectory2}')
  private String rootDirectory2

  @Value('${mc.testDirectory}')
  private String testDirectory

  @Value('${mc.testAbstractBaseImport}')
  private String testAbstractBaseImport

  @Value('${mc.testAbstractBaseClass}')
  private String testAbstractBaseClass

  @Value('${mc.ormDirectory}')
  private String ormDirectory

  @Value('${mc.backupDirectory}')
  private String backupDirectory

  @Value('${mc.typeOfPlainOldObject}')
  private String typeOfPlainOldObject

  @Resource (name="getOrmCodeFragments")
  private SQLTemplate getOrmCodeFragments

  @Resource (name="getGroovyEnumCodeFragments")
  private SQLTemplate getGroovyEnumCodeFragments

  @Resource (name="getPogoCodeFragments")
  private SQLTemplate getPogoCodeFragments

  @Resource (name="getPogoTestCodeFragments")
  private SQLTemplate getPogoTestCodeFragments

  @Bean
  McFileGenerator mcFileGenerator() {
    McFileGenerator mcFileGenerator = new McFileGenerator()

    def installProperties = [:]
    installProperties['schema_name'] = schemaName

    Map copyRightHeaderTemplateTags = [:]
    copyRightHeaderTemplateTags['current_year'] = new DateTime().year.toString()
    copyRightHeaderTemplateTags['create_year'] = "' || date_part('year', copyright_table.add_date) || '".toString()
    Template copyRightHeaderTemplate = new TemplateImpl(copyRightHeader)
    installProperties['copyRightHeader'] = copyRightHeaderTemplate.applyTags(copyRightHeaderTemplateTags).toString()

    installProperties['rootDirectory'] = rootDirectory
    installProperties['rootDirectory2'] = rootDirectory2
    installProperties['testDirectory'] = testDirectory
    installProperties['testAbstractBaseImport'] = testAbstractBaseImport
    installProperties['testAbstractBaseClass'] = testAbstractBaseClass
    installProperties['ormDirectory'] = ormDirectory
    installProperties['backupDirectory'] = backupDirectory
    installProperties['typeOfPlainOldObject'] = typeOfPlainOldObject

    mcFileGenerator.installProperties = installProperties
    mcFileGenerator.fragmentQueries = []
    mcFileGenerator.fragmentQueries.add(getOrmCodeFragments)
    mcFileGenerator.fragmentQueries.add(getGroovyEnumCodeFragments)
    mcFileGenerator.fragmentQueries.add(getPogoCodeFragments)
    mcFileGenerator.fragmentQueries.add(getPogoTestCodeFragments)

    mcFileGenerator
  }
}
