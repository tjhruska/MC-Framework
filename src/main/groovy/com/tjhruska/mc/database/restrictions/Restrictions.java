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

package com.tjhruska.mc.database.restrictions;

import java.util.Collection;
import java.util.Map;

public class Restrictions {

  public static Restriction allEq(Map<String, Object> propertyNameValues) {
    return PropertyRestriction.allEq(propertyNameValues);
  }

  public static Restriction and(Restriction lhs, Restriction rhs) {
    return EvalRestriction.and(lhs, rhs);
  }

  public static Restriction between(String propertyName, Object lo, Object hi) {
    return PropertyRestriction.between(propertyName, lo, hi);
  }

  public static Restriction eq(String propertyName, Object value) {
    return PropertyRestriction.eq(propertyName, value);
  }

  public static Restriction ge(String propertyName, Object value) {
    return PropertyRestriction.ge(propertyName, value);
  }

  public static Restriction gt(String propertyName, Object value) {
    return PropertyRestriction.gt(propertyName, value);
  }

  public static Restriction ilike(String propertyName, Object value) {
    return PropertyRestriction.ilike(propertyName, value);
  }

  public static Restriction in(String propertyName, Collection<Object> values) {
    return PropertyRestriction.inCollection(propertyName, values);
  }

  public static Restriction in(String propertyName, Object[] values) {
    return PropertyRestriction.inArray(propertyName, values);
  }

  public static Restriction isNotNull(String propertyName) {
    return PropertyRestriction.isNotNull(propertyName);
  }

  public static Restriction isNull(String propertyName) {
    return PropertyRestriction.isNull(propertyName);
  }

  public static Restriction le(String propertyName, Object value) {
    return PropertyRestriction.le(propertyName, value);
  }

  public static Restriction like(String propertyName, Object value) {
    return PropertyRestriction.like(propertyName, value);
  }

  public static Restriction lt(String propertyName, Object value) {
    return PropertyRestriction.lt(propertyName, value);
  }

  public static Restriction ne(String propertyName, Object value) {
    return PropertyRestriction.ne(propertyName, value);
  }

  public static Restriction not(Restriction expression) {
    return EvalRestriction.not(expression);
  }

  public static Restriction or(Restriction lhs, Restriction rhs) {
    return EvalRestriction.or(lhs, rhs);
  }
}
