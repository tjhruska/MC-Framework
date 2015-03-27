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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjhruska.mc.util.Solo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TagReplacerTest {
	
	@Resource(name="replacementValues")
	Map<String, String> replacementValues;

	@Resource(name="badEvalStrings")
	List<String> badEvalStrings;

	@Resource(name="willReplaceStrings")
	Map<String, String> willReplaceStrings;
	
	@Resource(name="willNotReplaceStrings")
	Map<String, String> willNotReplaceStrings;

	@Test
	public void printDiagnostics(){
		System.out.println("--------------------------");
		System.out.println("Replacement Map");
		for (Map.Entry<String, String> entry : replacementValues.entrySet())
			System.out.println("  key->" + entry.getKey() + "<-  value->" + entry.getValue() + "<-");
		System.out.println("--------------------------");
		System.out.println("Bad Evaluation Strings");
		for (String value : badEvalStrings)
			System.out.println("  value->" + value + "<-");
		System.out.println("--------------------------");
		System.out.println("Good Evaluation Strings, will replace tags if not in map, and expected return");
		for (Map.Entry<String, String> entry : willReplaceStrings.entrySet())
			System.out.println("  key->" + entry.getKey() + "<-  value->" + entry.getValue() + "<-");
		System.out.println("--------------------------");
		System.out.println("Good Evaluation Strings, will NOT replace tags if not in map, and expected return");
		for (Map.Entry<String, String> entry : willNotReplaceStrings.entrySet())
			System.out.println("  key->" + entry.getKey() + "<-  value->" + entry.getValue() + "<-");
	}
	
	@Test
	public void verifyBadEvalStrings(){
		for (String value : badEvalStrings){
			try{
				TagReplacer.replaceTag(value.toCharArray(), new Solo<Integer>(0), new Solo<Boolean>(false), replacementValues, true);
				fail("Didn't receive an exception for bad eval value:" + value);
			}catch (RuntimeException e){
				System.out.println("---------------------------------");
				System.out.println("Received expected exception for bad eval value:" + value);
				System.out.println("Message: " + e.getMessage());
				if (e.getMessage().length() < 2){
					e.printStackTrace();
					fail("Missing a pretty parse error message, go fix it!!!" + value);
				}
			}
		}
	}
	
	@Test
	public void verifyWillReplaceStrings(){
		verifyReplacements(willReplaceStrings, false);
	}
	
	@Test
	public void verifyWillNotReplaceStrings(){
		verifyReplacements(willNotReplaceStrings, true);
	}
		
	public void verifyReplacements(Map<String,String> testCases, Boolean leaveTags){
		for (Map.Entry<String,String> entry : testCases.entrySet()){
			System.out.println("---------------------------------");
			
			StringBuilder replacement = TagReplacer.replaceTag(
				entry.getKey().toCharArray(), new Solo<Integer>(0), 
				new Solo<Boolean>(false), replacementValues, leaveTags);
			
			System.out.println("Parsing:" + entry.getKey() + " expecting eval: " + entry.getValue());
			assertEquals(entry.getKey() + " didn't evaluate to " + entry.getValue(),
				entry.getValue(), replacement.toString());
		}
	}
}