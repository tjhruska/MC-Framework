package com.tjhruska.mc.apps;

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
	
	@Resource (name="getInstalled")
	SQLTemplate getInstalled;
	
	@Resource (name="addInstalled")
	SQLTemplate addInstalled;
	
	
	List<Duo<SQLTemplate,BeanNameAwareRunnable>> installs;
  //void setInstalls(List<Duo<SQLTemplate,BeanNameAwareRunnable>> installs){
  //  this.installs = installs
  //}

	Map<String, String> installProperties;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
    McInstallerForProjectsHelper.configure("application").run()
		//ApplicationContext context = Context.getClassPathXmlApplicationContext("conf/mcInstaller/application_context.xml");
		//((McInstaller)context.getBean("mcInstaller")).run();
	}

	public void run() {
		for (Map.Entry<String, String> entry : installProperties.entrySet()){
			log.info("installProperty: {}, value: {}", entry.getKey(), entry.getValue());
		}

		List<String> alreadyCompletedInstalls;
		try{
			alreadyCompletedInstalls = getInstalled.query( installProperties, 
				new RowMapper<String> (){
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("description");
					}});
			log.info("found {} previous installation records.", alreadyCompletedInstalls.size());
		}catch (Exception e){
			log.info("failed to get previous installation list, assuming no installs and continuing.");
			alreadyCompletedInstalls = new ArrayList<String>();
		}
		
		try {
			runQueries(alreadyCompletedInstalls);
		} catch (SQLException e) {
			//already reported, and hopefully rolled back.
		}
	}

	public void runQueries(List<String> alreadyCompletedInstalls) throws SQLException{
		int installCount = 0;
		boolean firstSynchronize = true;
		for (Duo<SQLTemplate,BeanNameAwareRunnable> commandPair: installs){
			SQLTemplate sqlTemplate = commandPair.getOne();
			BeanNameAwareRunnable runnable = commandPair.getTwo();
			if (runnable instanceof McEnumDbSynchronizer && firstSynchronize){
				firstSynchronize = false;
				((McEnumDbSynchronizer)runnable).setSkipEnumToColumnTableNextRun();
			}
			if (sqlTemplate != null){
				if (alreadyCompletedInstalls.contains(sqlTemplate.getBeanName())){
					log.info("Skipping query {}, already installed.", sqlTemplate.getBeanName());
					continue;
				}
				//log.info("About to run install query {}", sqlTemplate.getBeanName());
				try {
					sqlTemplate.jdbcQuery(installProperties);
					log.info("finished running query {}", sqlTemplate.getBeanName());
					
					installProperties.put("install_version", sqlTemplate.getBeanName());
					addInstalled.update(installProperties);
					installProperties.remove("install_version");
					//log.info("Recorded installation of {} in database.", sqlTemplate.getBeanName());
			
					installCount++;
				} catch (SQLException e) {
					log.error("received an exception when processing {}, aborting installation.", sqlTemplate.getBeanName());
					log.error("query:\n{}", sqlTemplate.applyTags(installProperties));
					throw e;
				}
			}
			if (runnable != null){
				log.info("executing job {}, configured to run after install query {}.", runnable.getBeanName(), (sqlTemplate == null ? "none" : sqlTemplate.getBeanName()));
				runnable.run();
			}
		}
		log.info("Completed {} install queries.", installCount);
	}
}
