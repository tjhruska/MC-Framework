package com.tjhruska.mc.apps

import javax.annotation.Resource

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Configuration

import com.tjhruska.mc.util.tagReplacement.SQLTemplate;

@ImportResource( [
  'classpath:conf/mcFileGenerator/sql/getOrmCodeFragments.tsql.xml',
  'classpath:conf/mcFileGenerator/sql/getEnumCodeFragments.tsql.xml',
  'classpath:conf/mcFileGenerator/sql/getPogoCodeFragments.tsql.xml',
  'classpath:conf/mcFileGenerator/sql/getPogoTestCodeFragments.tsql.xml' ] )
@Configuration
class McFileGeneratorConfig {
  
  @Value('${mc.schemaName}')
  private String schemaName
  
  @Value('${mc.rootDirectory}')
  private String rootDirectory
  
  @Value('${mc.rootDirectory2}')
  private String rootDirectory2
  
  @Value('${mc.testDirectory}')
  private String testDirectory
  
  @Value('${mc.ormDirectory}')
  private String ormDirectory
  
  @Value('${mc.backupDirectory}')
  private String backupDirectory
  
  @Value('${mc.typeOfPlainOldObject}')
  private String typeOfPlainOldObject
  
  @Resource (name="getOrmCodeFragments")
  private SQLTemplate getOrmCodeFragments;
  
  @Resource (name="getEnumCodeFragments")
  private SQLTemplate getEnumCodeFragments;
  
  @Resource (name="getPogoCodeFragments")
  private SQLTemplate getPogoCodeFragments;
  
  @Resource (name="getPogoTestCodeFragments")
  private SQLTemplate getPogoTestCodeFragments;
  
  @Bean
  McFileGenerator mcFileGenerator() {
    McFileGenerator mcFileGenerator = new McFileGenerator()
    
    def installProperties = [:]
    installProperties['schema_name'] = schemaName
    installProperties['rootDirectory'] = rootDirectory
    installProperties['rootDirectory2'] = rootDirectory2
    installProperties['testDirectory'] = testDirectory
    installProperties['ormDirectory'] = ormDirectory
    installProperties['backupDirectory'] = backupDirectory
    installProperties['typeOfPlainOldObject'] = typeOfPlainOldObject
    
    mcFileGenerator.installProperties = installProperties
    mcFileGenerator.fragmentQueries = []
    mcFileGenerator.fragmentQueries.add(getOrmCodeFragments)
    mcFileGenerator.fragmentQueries.add(getEnumCodeFragments)
    mcFileGenerator.fragmentQueries.add(getPogoCodeFragments)
    mcFileGenerator.fragmentQueries.add(getPogoTestCodeFragments)
    
    mcFileGenerator
  }
}
