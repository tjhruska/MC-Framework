package com.tjhruska.mc.util.tagReplacement;

import static org.junit.Assert.*;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjhruska.mc.util.tagReplacement.TemplateImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TemplateTest {
	
	@Resource(name="replacementValues")
	Map<String, String> replacementValues;
	
	@Resource(name="replacementValuesFinalizer")
	Map<String, String> replacementValuesFinalizer;
	
	@Resource(name="templateConstructorInjected1")
	private Template templateConstructorInjected1;

	@Resource(name="templateConstructorInjected2")
	private Template templateConstructorInjected2;

	@Resource(name="templatePropertyInjected1")
	private Template templatePropertyInjected1;

	@Resource(name="templatePropertyInjected2")
	private Template templatePropertyInjected2;

	@Test
	public void testSimpleConstructor() {
		assertNotNull("TemplateImpl not injected", templateConstructorInjected1);
		
		//happy path
		assertEquals("Incorrect string returned from template",
			"Hello there Mr. Lee.  Pleasure to meet you.",
			templateConstructorInjected1.applyTags(replacementValues).toString());

		//missing entry, but slim constructor says discard
		assertEquals("Incorrect string returned from template",
			"Hello there Mr. .  Pleasure to meet you.",
			templateConstructorInjected1.applyTags(replacementValuesFinalizer).toString());
		
		//missing entry, but now set to keep
		templateConstructorInjected1.setKeepUnusedTags(true);
		assertEquals("Incorrect string returned from template",
			"Hello there Mr. -=[LNAME]=-.  Pleasure to meet you.",
			templateConstructorInjected1.applyTags(replacementValuesFinalizer).toString());
	}

	@Test
	public void testFullConstructor() {
		assertNotNull("TemplateImpl not injected", templateConstructorInjected2);
	
		//happy path
		assertEquals("Incorrect string returned from template",
			"**************************************************-=[YYY]=-",
			templateConstructorInjected2.applyTags(replacementValues).toString());

		//test when replacement isn't found, but constructor said to keep templates
		assertEquals("Incorrect string returned from template", "-=[YYY]=-",
			templateConstructorInjected2.applyTags(replacementValuesFinalizer).toString());

		//happy path fewer iterations
		templateConstructorInjected2.setMaxIterations(5);
		assertEquals("Incorrect string returned from template", "*****-=[YYY]=-",
			templateConstructorInjected2.applyTags(replacementValues).toString());
		
		//test when replacement isn't found, and we set the keep = false
		templateConstructorInjected2.setKeepUnusedTags(false);
		assertEquals("Incorrect string returned from template", "",
			templateConstructorInjected2.applyTags(replacementValuesFinalizer).toString());
		
		//swap templates, NOPE
		try {
			templateConstructorInjected1.setTemplate(templateConstructorInjected2.getTemplate());
			fail("Can't change the template once it is set!");
		}catch(RuntimeException e){}
		
		//New template expecting default iterations
		Template t = new TemplateImpl(templateConstructorInjected2.getTemplate());
		assertEquals("Incorrect string returned from template",
			"**********-=[YYY]=-",
			t.applyTags(replacementValues).toString());
	}

	@Test
	public void testConditional(){
		assertNotNull("TemplateImpl not injected", templatePropertyInjected1);
	
		//Suffix found
		assertEquals("Incorrect string returned from template",
			" Lee Jr., Robert (Lee) ",
			templatePropertyInjected1.applyTags(replacementValues).toString());

		//Else
		replacementValues.remove("SUFFIX");
		assertEquals("Incorrect string returned from template",
			" Robert (Lee) ",
			templatePropertyInjected1.applyTags(replacementValues).toString());
	
	}
	
	@Test
	public void testCascade(){
		assertNotNull("TemplateImpl not injected", templatePropertyInjected2);
	
		//Suffix found
		assertEquals("Incorrect string returned from template",
			"Goodbye Robert, thanks for playing.  Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye, Goodbye!",
			TemplateReplacer.replaceTemplates(
				templatePropertyInjected2.applyTags(replacementValues).toString().toCharArray(),
				replacementValuesFinalizer).toString());
	}
}
