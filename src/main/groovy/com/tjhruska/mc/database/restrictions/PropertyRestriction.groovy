/**
 * Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tjhruska.mc.database.restrictions

import groovy.transform.Canonical
import groovy.transform.ToString


@Canonical (excludes=[])
@ToString (includeNames = true, includePackage=false)
public class PropertyRestriction implements Restriction {

  RestrictionType restrictionType
  String propertyName
  Object value1
  Object value2
  Collection<Object> collectionValues
  Object[] valueArray
  Map<String, Object> propertyNameValues

  private PropertyRestriction(RestrictionType restrictionType, String propertyName){
    this.restrictionType = restrictionType
    this.propertyName = propertyName
  }

  private PropertyRestriction(RestrictionType restrictionType, String propertyName, Object value1){
    this.restrictionType = restrictionType
    this.propertyName = propertyName
    this.value1 = value1
  }

  private PropertyRestriction(RestrictionType restrictionType, String propertyName, Object value1, Object value2){
    this.restrictionType = restrictionType
    this.propertyName = propertyName
    this.value1 = value1
    this.value2 = value2
  }

  private PropertyRestriction(RestrictionType restrictionType, String propertyName, Collection<Object> values){
    this.restrictionType = restrictionType
    this.propertyName = propertyName
    this.collectionValues = values
  }

  private PropertyRestriction(RestrictionType restrictionType, String propertyName, Object[] values){
    this.restrictionType = restrictionType
    this.propertyName = propertyName
    this.valueArray = values
  }

  private PropertyRestriction(RestrictionType restrictionType, Map<String, Object> propertyNameValues){
    this.restrictionType = restrictionType
    this.propertyNameValues = propertyNameValues
  }

  public static Restriction allEq(Map<String, Object> propertyNameValues){
    return new PropertyRestriction(RestrictionType.ALL_EQ, propertyNameValues)
  }

  public static Restriction between(String propertyName, Object lo, Object hi){
    return new PropertyRestriction(RestrictionType.BETWEEN, propertyName, lo, hi)
  }

  public static Restriction eq(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.EQUALS, propertyName, value)
  }

  public static Restriction ge(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.GREATER_THAN_OR_EQUALS, propertyName, value)
  }

  public static Restriction gt(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.GREATER_THAN, propertyName, value)
  }

  public static Restriction ilike(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.INSENSITIVE_CASE_LIKE, propertyName, value)
  }

  public static Restriction inCollection(String propertyName, Collection<Object> values){
    return new PropertyRestriction(RestrictionType.IN, propertyName, values)
  }

  public static Restriction inArray(String propertyName, Object[] values){
    return new PropertyRestriction(RestrictionType.IN, propertyName, values)
  }

  public static Restriction isNotNull(String propertyName){
    return new PropertyRestriction(RestrictionType.IS_NOT_NULL, propertyName)
  }

  public static Restriction isNull(String propertyName){
    return new PropertyRestriction(RestrictionType.IS_NULL, propertyName)
  }

  public static Restriction le(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.LESS_THAN_OR_EQUALS, propertyName, value)
  }

  public static Restriction like(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.LIKE, propertyName, value)
  }

  public static Restriction lt(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.LESS_THAN, propertyName, value)
  }

  public static Restriction ne(String propertyName, Object value){
    return new PropertyRestriction(RestrictionType.NOT_EQUALS, propertyName, value)
  }

  public Restriction and(Restriction restriction) {
    return new EvalRestriction(RestrictionType.AND, this, restriction)
  }

  public Restriction or(Restriction restriction) {
    return new EvalRestriction(RestrictionType.OR, this, restriction)
  }

  public Restriction not() {
    return new EvalRestriction(RestrictionType.NOT, this)
  }

  /**
   * @return the RestrictionType
   */
  public RestrictionType getRestrictionType() {
    return restrictionType
  }

  /**
   * @return the propertyName
   */
  public String getPropertyName() {
    return propertyName
  }

  /**
   * @return the value1
   */
  public Object getValue1() {
    return value1
  }

  /**
   * @return the value2
   */
  public Object getValue2() {
    return value2
  }

  /**
   * @return the collectionValues
   */
  public Collection<Object> getCollectionValues() {
    return collectionValues
  }

  /**
   * @return the valueArray
   */
  public Object[] getValueArray() {
    return valueArray
  }

  /**
   * @return the propertyNameValues
   */
  public Map<String, Object> getPropertyNameValues() {
    return propertyNameValues
  }
}