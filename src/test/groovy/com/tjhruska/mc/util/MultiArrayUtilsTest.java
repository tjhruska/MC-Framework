package com.tjhruska.mc.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MultiArrayUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToPrimitiveSingle() {
	  Double[] oArray = new Double[3];
		oArray[0] = new Double(10);
		oArray[1] = new Double(20);
		oArray[2] = new Double(30);
		
		double[] pArray = new double[3];
		pArray[0] = new Double(10);
		pArray[1] = new Double(20);
		pArray[2] = new Double(30);
		
		double[] rArray = MultiArrayUtils.toPrimitive(oArray);
		
		for (int i = 0; i < oArray.length; i++){
		  assertEquals(new Double(pArray[i]), new Double(rArray[i]));
		}
		
	}
	
	@Test
	public void testToPrimitiveMulti() {
		Double[][] oArray = new Double[3][];
		oArray[0] = new Double[0];
		oArray[1] = new Double[1];
		oArray[1][0] = new Double(10);
		oArray[2] = new Double[2];
		oArray[2][0] = new Double(20);
		oArray[2][1] = new Double(21);
		
		double[][] pArray = new double[3][];
		pArray[0] = new double[0];
		pArray[1] = new double[1];
		pArray[1][0] = new Double(10);
		pArray[2] = new double[2];
		pArray[2][0] = new Double(20);
		pArray[2][1] = new Double(21);
		
		double[][] rArray = MultiArrayUtils.toPrimitive(oArray);
		
		for (int i = 0; i < oArray.length; i++){
			for (int j = 0; j < oArray[i].length; j++){
				assertEquals(new Double(pArray[i][j]), new Double(rArray[i][j]));
			}
		}
	}

}
