package com.tjhruska.mc.apps;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.tjhruska.mc.database.DaoDomain;
import com.tjhruska.mc.domain.system.Table;
import com.tjhruska.mc.util.BeanNameAwareRunnable;
import com.tjhruska.mc.util.spring.Context;

public class McDemo extends BeanNameAwareRunnable{
	private static final Logger log
		= LoggerFactory.getLogger(McDemo.class);
	
	@Resource
	private DaoDomain<Table> tableDao;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = Context.getClassPathXmlApplicationContext("conf/mcDemo/application_context.xml");
		((McDemo)context.getBean("mcDemo")).run();

	}

	public void run() {
		List<Table> tables = tableDao.findAll();
		log.info("There are {} tables", tables.size());

		for (Table table : tables)
			log.info("{} table with rule set to {}", table.getNameCamelCase(), table.getTableCreationRule().getName());

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Table table = tables.get(0);
			String jsonString = objectMapper.writeValueAsString(table);
			log.info("json for table:>>>\n{}", jsonString);
			log.info("table has {} columns", table.getColumns().size());
			jsonString = objectMapper.writeValueAsString(table.getColumns());
			log.info("json for column:>>>\n{}", jsonString);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		log.info("now time to do an update...");
//		log.info("{} table with rule set to {}", tables.get(0).getName(), tables.get(0).getTableCreationRule().getName());
//		tables.get(0).setName("jennifer");
//		tables.get(0).setTableCreationRule(TableCreationRule.DROP_RECREATE);
//		tableDao.saveOrUpdate(tables.get(0));
//		log.info("{} table with rule set to {}", tables.get(0).getName(), tables.get(0).getTableCreationRule().getName());
	}

}
