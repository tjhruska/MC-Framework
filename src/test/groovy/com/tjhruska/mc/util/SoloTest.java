package com.tjhruska.mc.util;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class SoloTest {
	
	private Solo<Integer> soloIntegerConstructor;
	private Solo<Integer> soloIntegerNullToConstructor;
	private Solo<String> soloStringConstructor;
	private Solo<String> soloStringNullToConstructor;
	private Solo<Long> soloLongConstructor;
	private Solo<Boolean> soloBooleanEmptyConstructor;
	private Solo<Boolean> soloBooleanEmptyConstructorSet;

	@Before
	public void setup(){
		soloIntegerConstructor = new Solo<Integer>(1);
		soloIntegerNullToConstructor = new Solo<Integer>(null);
		soloStringConstructor = new Solo<String>("Hi There");
		soloStringNullToConstructor = new Solo<String>(null);
		soloLongConstructor = new Solo<Long>(1L);
		soloBooleanEmptyConstructor = new Solo<Boolean>();
		soloBooleanEmptyConstructorSet = new Solo<Boolean>();
		soloBooleanEmptyConstructorSet.setOne(true);
	}
	
	@Test
	public void testSoloONE() {
		assertEquals("Test construction", new Integer(1), soloIntegerConstructor.getOne());
		assertEquals("Test construction", null, soloIntegerNullToConstructor.getOne());
	}

	@Test
	public void testSolo() {
		assertEquals("Test empty construction", true, soloBooleanEmptyConstructorSet.getOne());
		assertEquals("Test empty construction", null, soloBooleanEmptyConstructor.getOne());
	}

	@Test
	public void testEqualsObject() {
		Solo<Integer> soloTest = new Solo<Integer>(1);
		assertTrue("test equality", soloIntegerConstructor.equals(soloTest));
		assertFalse("test not equal to null", soloIntegerConstructor.equals(soloIntegerNullToConstructor));
		
		soloTest.setOne(null);
		assertFalse("null != null", soloIntegerNullToConstructor.equals(soloTest));
		
		soloIntegerNullToConstructor.setNullsAreEqual(true);
		assertTrue("null == null", soloIntegerNullToConstructor.equals(soloTest));
		
		assertFalse("1 != 1L", soloIntegerConstructor.equals(soloLongConstructor));
		
	}

	@Test
	public void testHashCode() {
		Set<Integer> hashes = new HashSet<Integer>();
		
		assertFalse("test for unique hash", hashes.contains(soloIntegerConstructor.hashCode()));
		hashes.add(soloIntegerConstructor.hashCode());
		
		assertFalse("test for unique hash", hashes.contains(soloStringConstructor.hashCode()));
		hashes.add(soloStringConstructor.hashCode());
		
		assertTrue("test for unique hash", hashes.contains(soloStringNullToConstructor.hashCode()));
		hashes.add(soloStringNullToConstructor.hashCode());
		
		assertTrue("test for unique hash", hashes.contains(soloLongConstructor.hashCode()));
		hashes.add(soloLongConstructor.hashCode());

		assertTrue("test for unique hash", hashes.contains(soloBooleanEmptyConstructor.hashCode()));
		hashes.add(soloBooleanEmptyConstructor.hashCode());
		
		assertFalse("test for unique hash", hashes.contains(soloBooleanEmptyConstructorSet.hashCode()));
		hashes.add(soloBooleanEmptyConstructorSet.hashCode());
		
	}

	@Test
	public void testSetGetNullsAreEqual() {
		assertFalse("test initial state", soloIntegerConstructor.getNullsAreEqual());
		soloIntegerConstructor.setNullsAreEqual(true);
		assertTrue("test set and get", soloIntegerConstructor.getNullsAreEqual());
	}

	@Test
	public void testSetGetSeparator() {
		assertEquals("test initial state", ",", soloIntegerConstructor.getSeparator());
		soloIntegerConstructor.setSeparator("*");
		assertEquals("test set and get", "*", soloIntegerConstructor.getSeparator());
	}

	@Test
	public void testGetOne() {
		//already tested by constructor, and equals tests
	}

	@Test
	public void testSetOne() {
		//already tested by constructor, and equals tests
	}

	@Test
	public void testToString() {
		assertEquals("check we are just calling toString on ONE", "Solo<"+(new Integer(1)).toString()+">", soloIntegerConstructor.toString());
		assertEquals("check we are just calling toString on ONE", "Solo<"+(new Long(1L)).toString()+">", soloLongConstructor.toString());
		assertEquals("check we are just calling toString on ONE", "Solo<Hi There>", soloStringConstructor.toString());
	}
}
