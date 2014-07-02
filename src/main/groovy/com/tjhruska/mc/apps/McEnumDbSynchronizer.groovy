package com.tjhruska.mc.apps

import com.tjhruska.mc.apps.McTableCreator.CommandFragment

import groovy.util.logging.Slf4j

import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.List
import java.util.ListIterator
import java.util.Map

import javax.annotation.Resource


import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.RowMapper

import com.tjhruska.mc.util.BeanNameAwareRunnable
import com.tjhruska.mc.util.Duo
import com.tjhruska.mc.util.spring.Context
import com.tjhruska.mc.util.tagReplacement.SQLTemplate


@Slf4j
public class McEnumDbSynchronizer extends BeanNameAwareRunnable {

	SQLTemplate buildEnumSynchCommands
	SQLTemplate loadEnumSynchCommands
  boolean skipLoadEnumSynchCommands
	Map<String, String> installProperties
	
	public void run() {
    installProperties.each { key, value ->
      log.info("installProperty: ${key}, value: ${value}")
    }
		log.info('Found {} query.', buildEnumSynchCommands.getBeanName())
		
		//run the query to get back a list of CommandFragments
		
		List<CommandFragment> commandFragments = null
		try{
			commandFragments = buildEnumSynchCommands.query(installProperties, 
				new RowMapper<CommandFragment> (){
					public CommandFragment mapRow(ResultSet rs, int rowNum) throws SQLException {
						CommandFragment commandFragment = new CommandFragment()
						commandFragment.section = rs.getString('section')
						commandFragment.id = rs.getLong('id')
						commandFragment.sequence = rs.getInt('sequence')
						commandFragment.sequence2 = rs.getInt('sequence2')
						commandFragment.new_sql = rs.getString('new_sql')
						return commandFragment
					}})
		} catch (Exception e){
			log.error('Failed to retrieve command fragements using {}', buildEnumSynchCommands.getBeanName())
			log.error('exception:', e)
			return
		}

		if (commandFragments == null || commandFragments.size() == 0){
			log.info('No changes in setup tables to propagate during DBSynchronizer')
		}else{
			log.info('Retrieved {} command fragments, converting them into commands.', commandFragments.size())
	
			List<SQLTemplate> sqlTemplates = CommandBuilder.buildCommands(commandFragments, buildEnumSynchCommands)
		
			log.info('Built {} commands from {} command fragments', sqlTemplates.size(), commandFragments.size())
	
			runCommands(sqlTemplates)
		}
    if (skipLoadEnumSynchCommands) {
      log.info("skipping loadEnumSynchCommands.")
    } else {
      loadEnumSynchCommands.update(installProperties)
    }
		
	}

	public void runCommands(List<SQLTemplate> sqlTemplates){
		int successCount = 0
		int failCount = 0
		for (SQLTemplate sqlTemplate : sqlTemplates){
			log.info('applying command {}', sqlTemplate.getBeanName())
			log.trace('\n{}', sqlTemplate.applyTags(installProperties))
			try {
				sqlTemplate.jdbcQuery(installProperties)
				successCount++
			} catch (SQLException e) {
				log.error('failed to run command for {}, with error {}.', sqlTemplate.getBeanName(), e.getMessage())
				log.error(sqlTemplate.applyTags(installProperties).toString())
				failCount++
			}
		}
		log.info('McEnumDbSynchronizer - {} total commands, {} failed, {} succeeded', 
			sqlTemplates.size() + '', failCount + '', successCount + '')
		
	}
	
  @Slf4j
	public class CommandFragment{
		//String section, int id, int sequence, int sequence2, String new_sql
		public String section
		public long id
		public int sequence
		public int sequence2
		public String new_sql
	}
	
  @Slf4j
	public static class CommandBuilder{
		
		public static List<SQLTemplate> buildCommands(
			List<CommandFragment> commandFragments, 
			SQLTemplate buildEnumSynchCommands)
		{
			List<SQLTemplate> sqlTemplates = new ArrayList<SQLTemplate>()
      if (commandFragments.size() == 0)
        return sqlTemplates
       
			StringBuilder currentCommand = null
			Duo<String,Long> prevItem = new Duo<String,Long>(new String(''), 0L)
      
      commandFragments.each { commandFragment -> 
      
				log.debug('{}-{}', commandFragment.section, commandFragment.id)
				
				//store a link to the old command in-case we are changing
				StringBuilder prevCommand = currentCommand
				
				//current item not equal to prev item, ready a new currentCommand
				if (commandFragment != commandFragments.last() &&
					(!prevItem.getOne().equals(commandFragment.section)
					|| !prevItem.getTwo().equals(commandFragment.id)))
				{
					log.debug('changed so creating new stringBuilder')
					currentCommand = new StringBuilder()
				}
			
				//copy current fragment out - if on last record it is also copied into previous... :)
				currentCommand.append(commandFragment.new_sql)
			
				//if we are done with command fragments, or onto a new one write out previous one
				if (prevCommand != null &&
					(commandFragment == commandFragments.last()
					|| !prevItem.getOne().equals(commandFragment.section)
					|| !prevItem.getTwo().equals(commandFragment.id)))
				{
					log.debug('More rows={}, if false or record changed, then save off {}-{}', [commandFragment != commandFragments.last(), prevItem.getOne(), prevItem.getTwo().toString()])
					//finished with sqlTemplate, add it to list
					SQLTemplate sqlTemplate = new SQLTemplate(prevCommand.toString())
					sqlTemplate.setBeanName(prevItem.getOne() + '-' + prevItem.getTwo())
					sqlTemplate.setDataSource(buildEnumSynchCommands.getDataSource())
					sqlTemplate.setJdbcTemplate(buildEnumSynchCommands.getJdbcTemplate())
					sqlTemplates.add(sqlTemplate)
				}
				
				//set section and id for comparison in next iteration
				prevItem.setOne(commandFragment.section)
				prevItem.setTwo(commandFragment.id)
				
			}
			return sqlTemplates
		}
	}
}
