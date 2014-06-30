package com.tjhruska.mc.util.tagReplacement;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjhruska.mc.util.Solo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ConditionalReplacerTest {

	@Resource(name="replacementValues")
	Map<String, String> replacementValues;

	@Resource(name="badEvalStrings")
	List<String> badEvalStrings;

	@Resource(name="goodEvalStrings")
	Map<String, String> goodEvalStrings;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

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
		System.out.println("Good Evaluation Strings, and expected return");
		for (Map.Entry<String, String> entry : goodEvalStrings.entrySet())
			System.out.println("  key->" + entry.getKey() + "<-  value->" + entry.getValue() + "<-");
	}
	
	@Test
	public void verifyBadEvalStrings(){
		for (String value : badEvalStrings){
			try{
				new ConditionalReplacer(value.toCharArray(), new Solo<Integer>(0));
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
	public void verifyGoodEvalStrings(){
		for (Map.Entry<String,String> entry : goodEvalStrings.entrySet()){
			System.out.println("---------------------------------");
			ConditionalReplacer cr 
				= new ConditionalReplacer(entry.getKey().toCharArray(), new Solo<Integer>(0));
			System.out.println("Parsing:" + cr + " expecting eval: " + entry.getValue());
			assertEquals(entry.getKey() + " didn't evaluate to " + entry.getValue(),
				entry.getValue(), cr.getConditionalReplacement(replacementValues).toString());
		}
	}
}
