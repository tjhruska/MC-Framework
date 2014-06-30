/**
 *   Copyright  2010 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.container
 *   MultiArrayUtils.java
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the LGNU Lesser General Public License as 
 *   published by the Free Software Foundation, either version 3 of the 
 *   License, or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   LGNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the LGNU Lesser General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tjhruska.mc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dakota
 *
 */
public class MultiArrayUtils {

  /**
   * Converts an array[] of object Doubles to primitives[].
   * @param array
   * @return double[]
   */
  public static double[] toPrimitive(Double[] array){
    double[] newArray = new double[array.length];
    for (int i = 0; i < array.length; i++){
      newArray[i] = array[i];
    }
    return newArray;
  }
  
  /**
   * Converts an array[][] of object Doubles to primitives[][].
   * @param array
   * @return double[][]
   */
  public static double[][] toPrimitive(Double[][] array){
    double[][] newArray = new double[array.length][];
    for (int i = 0; i < array.length; i++){
      newArray[i] = new double[i];
      for (int j = 0; j < array[i].length; j++){
        newArray[i][j] = array[i][j];
      }
    }
	  
    return newArray;
  }
  
  /**
   * Extracts a Double[][] field named columnName from current resultSet record
   * @param resultSet
   * @param columnName
   * @return Double[][]
   */
  public static Double[][] readMultiDouble(
    ResultSet resultSet, String columnName)
  {
    Double[][] doubleArray = null;
    
    try {
      doubleArray = (Double[][])resultSet.getArray(columnName).getArray();
    } catch (SQLException x) {
      //just swallow it and return null
    }

    return doubleArray;
  }
  
  /**
   * Extracts a double[][] field named columnName from current resultSet record
   * @param resultSet
   * @param columnName
   * @return double[][]
   */
  public static double[][] readMultiDoublePrimitive(ResultSet resultSet, String columnName)
  {
    return MultiArrayUtils.toPrimitive(readMultiDouble(resultSet, columnName));
  }
  
  /**
   * Extracts a Double[] field named columnName from current resultSet record
   * @param resultSet
   * @param columnName
   * @return Double[]
   */
  public static Double[] readDouble(ResultSet resultSet, String columnName)
  {
    Double[] doubleArray = null;
    
    try {
      doubleArray = (Double[])resultSet.getArray(columnName).getArray();
    } catch (SQLException x) {
      //just swallow it and return null
    }
    
    return doubleArray;
  }
  
  /**
   * Extracts a double[] field named columnName from current resultSet record
   * @param resultSet
   * @param columnName
   * @return double[]
   */
  public static double[] readDoublePrimative(ResultSet resultSet, String columnName)
  {
    return MultiArrayUtils.toPrimitive(readDouble(resultSet, columnName));
  }
} 