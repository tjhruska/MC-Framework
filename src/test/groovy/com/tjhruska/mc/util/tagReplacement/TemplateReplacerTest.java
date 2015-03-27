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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TemplateReplacerTest {

	@Resource(name="replacementValues")
	Map<String, String> replacementValues;

	@Resource(name="badEvalStrings")
	List<String> badEvalStrings;

	@Resource(name="willReplaceStrings")
	Map<String, String> willReplaceStrings;
	
	@Resource(name="willNotReplaceStrings")
	Map<String, String> willNotReplaceStrings;

	@Resource(name="loopTest")
	Map<String, String> loopTest;

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
				TemplateReplacer.replaceTemplates(value.toCharArray(), replacementValues);
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
		verifyReplacements(willReplaceStrings, false, null, true);
	}
	
	@Test
	public void verifyWillNotReplaceStrings(){
		verifyReplacements(willNotReplaceStrings, true, null, true);
	}
		
	@Test
	public void verifyLoopTestLimited() {
		verifyReplacements(loopTest, false, 5, false);
		//the real key to this is that it doesn't spin off forever
	}

	@Test
	public void verifyLoopTestUnlimited() {
		verifyReplacements(loopTest, false, null, false);
		//the real key to this is that it doesn't spin off forever
	}

	@Test
	public void verifyLoopTestLimitedKeepTemplates() {
		verifyReplacements(loopTest, true, 5, false);
		//the real key to this is that it doesn't spin off forever
	}

	@Test
	public void verifyLoopTestUnlimitedKeepTemplates() {
		verifyReplacements(loopTest, true, null, false);
		//the real key to this is that it doesn't spin off forever
	}

	public void verifyReplacements(Map<String,String> testCases, Boolean leaveTags, Integer limit, Boolean runAsserts){
		for (Map.Entry<String,String> entry : testCases.entrySet()){
			System.out.println("---------------------------------");
			System.out.println("Parsing:" + entry.getKey() + " expecting eval: " + entry.getValue());
	
			StringBuilder replacement;
			if (limit == null){
				if (leaveTags)
					replacement = TemplateReplacer.replaceTemplates(
					entry.getKey().toCharArray(), replacementValues, leaveTags);
				else
					replacement = TemplateReplacer.replaceTemplates(
					entry.getKey().toCharArray(), replacementValues);
			}else{
				if (leaveTags)
					replacement = TemplateReplacer.replaceTemplates(
					entry.getKey().toCharArray(), replacementValues, leaveTags, limit);
				else
					replacement = TemplateReplacer.replaceTemplates(
					entry.getKey().toCharArray(), replacementValues, limit);
			}
			if (runAsserts)
				assertEquals(entry.getKey() + " didn't evaluate to " + entry.getValue(),
					entry.getValue(), replacement.toString());
			else
				System.out.println("outcome:" + replacement.toString());
		}
	}
}