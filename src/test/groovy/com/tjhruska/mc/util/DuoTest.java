package com.tjhruska.mc.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DuoTest {

	private Duo<Boolean,String> duoConstructor;
	private Duo<Boolean,String> duoNullToConstructor;
	private Duo<Boolean,String> duoNullToConstructorSet;	
	
	@Before
	public void setUp() throws Exception {
		duoConstructor = new Duo<Boolean,String>(true,"Hi There");
		duoNullToConstructor = new Duo<Boolean,String>();
		duoNullToConstructorSet = new Duo<Boolean,String>();
		duoNullToConstructorSet.setOne(true);
		duoNullToConstructorSet.setTwo("Hi There");
	}

	@Test
	public void testDuoONETWO() {
		assertEquals(true, duoConstructor.getOne());
		assertEquals("Hi There", duoConstructor.getTwo());
	}

	@Test
	public void testDuo() {
		assertEquals(null, duoNullToConstructor.getOne());
		assertEquals(null, duoNullToConstructor.getTwo());
	}

	@Test
	public void testEqualsObject() {
		assertEquals(duoConstructor, duoNullToConstructorSet);
		assertNotSame(duoConstructor, duoNullToConstructor);
	}

	@Test
	public void testHashCode() {
		duoNullToConstructorSet.setOne(false);
		assertNotSame(duoConstructor.hashCode(), duoNullToConstructorSet.hashCode());
		assertNotSame(duoConstructor.hashCode(), duoNullToConstructor.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals("Duo<true,Hi There>", duoConstructor.toString());
		assertEquals("Duo<true,Hi There>", duoNullToConstructorSet.toString());
		assertEquals("Duo<null,null>", duoNullToConstructor.toString());
	}

	@Test
	public void testGetTwo() {
		//already tested in constructor test
	}

	@Test
	public void testSetTwo() {
		//already tested in constructor test
	}
} 