package com.tjhruska.mc.util;

import static org.junit.Assert.*;

import org.junit.Test;
public class SimpleTests {

	@Test
	public void checkReturnInteger(){
		Integer fred = 5;
		callGeorge(fred);
		assertEquals("fred is still set to 5", new Integer(5), fred);
	}
	
	public void callGeorge(Integer i){
		i = 10;
	}
}
