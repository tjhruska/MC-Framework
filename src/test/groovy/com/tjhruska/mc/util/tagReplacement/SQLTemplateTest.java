package com.tjhruska.mc.util.tagReplacement;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjhruska.mc.util.tagReplacement.SQLTemplateImpl;
import com.tjhruska.mc.util.tagReplacement.TemplateImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SQLTemplateTest {
	
	@Autowired
	private Map<String, SQLTemplateImpl> sqlTemplateMap;
	@Autowired
	private Map<String, TemplateImpl> allTemplateMap;

	public void setSqlTemplates(Map<String, SQLTemplateImpl> sqlTemplates) {
		this.sqlTemplateMap = sqlTemplates;
	}

	public void setAllTemplates(Map<String, TemplateImpl> allTemplates) {
		this.allTemplateMap = allTemplates;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSQLTemplate() {
		assertEquals("sqlTemplateMap size was wrong", 2, sqlTemplateMap.size());
		assertEquals("allTemplateMap size was wrong", 6, allTemplateMap.size());
		
		System.out.println("sqlTemplates:");
		for (Map.Entry<String, SQLTemplateImpl> entry : sqlTemplateMap.entrySet()){
			System.out.println("	key:" + entry.getKey() + " value:" + entry.getValue().getTemplate());
		}

		System.out.println("allTemplates:");
		for (Map.Entry<String, TemplateImpl> entry : allTemplateMap.entrySet()){
			System.out.println("	key:" + entry.getKey() + " value:" + entry.getValue().getTemplate());
		}

	}
}
