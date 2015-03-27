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