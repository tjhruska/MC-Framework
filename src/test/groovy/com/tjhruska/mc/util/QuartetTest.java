package com.tjhruska.mc.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuartetTest {
	
	private Quartet<Integer,Long,Boolean,String> quartetConstructor;
	private Quartet<Integer,Long,Boolean,String> quartetNullToConstructor;
	private Quartet<Integer,Long,Boolean,String> quartetNullToConstructorSet;
	
	@Before
	public void setUp() throws Exception {
		quartetConstructor = new Quartet<Integer,Long,Boolean,String>(1, 1L, true, "Hi There");
		quartetNullToConstructor = new Quartet<Integer,Long,Boolean,String>();
		quartetNullToConstructorSet = new Quartet<Integer,Long,Boolean,String>();
		quartetNullToConstructorSet.setOne(1);
		quartetNullToConstructorSet.setTwo(1L);
		quartetNullToConstructorSet.setThree(true);
		quartetNullToConstructorSet.setFour("Hi There");
	}

	@Test
	public void testQuartetONETWOTHREEFOUR() {
		assertEquals("test one", new Integer(1), quartetConstructor.getOne());
		assertEquals("test two", new Long(1), quartetConstructor.getTwo());
		assertEquals("test three", true, quartetConstructor.getThree());
		assertEquals("test four", "Hi There", quartetConstructor.getFour());
	}

	@Test
	public void testQuartet() {
		assertEquals("test one", null, quartetNullToConstructor.getOne());
		assertEquals("test two", null, quartetNullToConstructor.getTwo());
		assertEquals("test three", null, quartetNullToConstructor.getThree());
		assertEquals("test four", null, quartetNullToConstructor.getFour());
	}

	@Test
	public void testEqualsObject() {
		assertEquals("is equal", quartetConstructor, quartetNullToConstructorSet);
		assertNotSame("is not equal", quartetConstructor, quartetNullToConstructor);
	}

	@Test
	public void testHashCode() {
		quartetNullToConstructorSet.setOne(2);
		assertNotSame(quartetConstructor.hashCode(), quartetNullToConstructorSet.hashCode());
		assertNotSame(quartetConstructor.hashCode(), quartetNullToConstructor.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals("Quartet<1,1,true,Hi There>", quartetConstructor.toString());
		assertEquals("Quartet<null,null,null,null>", quartetNullToConstructor.toString());
	}

	@Test
	public void testGetFour() {
		//already tested in constructor
	}

	@Test
	public void testSetFour() {
		//already tested in constructor
	}
}
