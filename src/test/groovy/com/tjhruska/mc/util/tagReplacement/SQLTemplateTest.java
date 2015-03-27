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