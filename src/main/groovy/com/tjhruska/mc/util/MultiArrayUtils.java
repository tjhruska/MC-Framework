/**
Copyright 2010-2015 Timothy James Hruska (tjhruska@yahoo.com)

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

import java.sql.ResultSet;
import java.sql.SQLException;

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