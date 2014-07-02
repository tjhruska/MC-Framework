package com.tjhruska.mc.apps

import javax.annotation.Resource

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Configuration

import com.tjhruska.mc.util.tagReplacement.SQLTemplate;


@ImportResource( [ 
  'classpath:conf/mcEnumDbSynchronizer/sql/buildEnumSynchCommands.tsql.xml', 
  'classpath:conf/mcEnumDbSynchronizer/sql/loadEnumsToColumnTable.tsql.xml' ] )
@Configuration
class McEnumDbSynchronizerConfig {
  
  @Value('${mc.schemaName}')
  private String schemaName
  
  @Value('${mc.adminUser}')
  private String adminUser
  
  @Resource (name="buildEnumSynchCommands")
  private SQLTemplate buildEnumSynchCommands;

  @Resource (name="loadEnumsToColumnTable")
  private SQLTemplate loadEnumSynchCommands;
  
  @Bean
  McEnumDbSynchronizer mcEnumDbSynchronizer() {
    getBasicEnumDbSynchronizer()
  }
    
  @Bean
  McEnumDbSynchronizer mcEnumDbSynchronizerSkipLoad() {
    McEnumDbSynchronizer mcEnumDbSynchronizer = getBasicEnumDbSynchronizer()
    mcEnumDbSynchronizer.skipLoadEnumSynchCommands = true
    
    mcEnumDbSynchronizer
  }
    
  McEnumDbSynchronizer getBasicEnumDbSynchronizer() {
    McEnumDbSynchronizer mcEnumDbSynchronizer = new McEnumDbSynchronizer()
    
    def installProperties = [:]
    installProperties['schema_name'] = schemaName
    installProperties['owner'] = adminUser
    mcEnumDbSynchronizer.installProperties = installProperties
    
    mcEnumDbSynchronizer.buildEnumSynchCommands = buildEnumSynchCommands
    mcEnumDbSynchronizer.loadEnumSynchCommands = loadEnumSynchCommands
    mcEnumDbSynchronizer.skipLoadEnumSynchCommands = false
     
    mcEnumDbSynchronizer
  }
}