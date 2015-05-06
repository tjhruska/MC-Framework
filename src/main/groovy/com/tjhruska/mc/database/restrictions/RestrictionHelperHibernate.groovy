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

import java.util.Collection
import java.util.Map

import org.hibernate.Criteria
import org.hibernate.criterion.Criterion
import org.hibernate.criterion.Restrictions

public class RestrictionHelperHibernate implements RestrictionHelper{

  public void buildCriterion(Object criteriaObject, Restriction restriction){
    Criteria criteria = (Criteria)criteriaObject
    criteria.add((Criterion)buildCriterionPrivate(restriction))
  }

  private Criterion buildCriterionPrivate(Restriction restriction) {

    if (restriction instanceof PropertyRestriction)
      return buildPropertyRestrictionCriterion((PropertyRestriction)restriction)
    else
      return buildEvalRestrictionCriterion((EvalRestriction)restriction)
  }

  private Criterion buildPropertyRestrictionCriterion(PropertyRestriction propertyRestriction){
    String propertyName = propertyRestriction.getPropertyName()
    Object value1 = propertyRestriction.getValue1()
    Object value2 = propertyRestriction.getValue2()
    Collection<Object> collectionValues = propertyRestriction.getCollectionValues()
    Object[] valueArray = propertyRestriction.getValueArray()
    Map<String, Object> propertyNameValues = propertyRestriction.getPropertyNameValues()

    switch(propertyRestriction.getRestrictionType()){
      case ALL_EQ:
        return Restrictions.allEq(propertyNameValues)
      case BETWEEN:
        return Restrictions.between(propertyName, value1, value2)
      case EQUALS:
        return Restrictions.eq(propertyName, value1)
      case GREATER_THAN:
        return Restrictions.gt(propertyName, value1)
      case GREATER_THAN_OR_EQUALS:
        return Restrictions.ge(propertyName, value1)
      case IN:
        if (collectionValues != null)
          return Restrictions.in(propertyName, collectionValues)
        else
          return Restrictions.in(propertyName, valueArray)
      case INSENSITIVE_CASE_LIKE:
        return Restrictions.ilike(propertyName, value1)
      case IS_NOT_NULL:
        return Restrictions.isNotNull(propertyName)
      case IS_NULL:
        return Restrictions.isNull(propertyName)
      case LESS_THAN:
        return Restrictions.lt(propertyName, value1)
      case LESS_THAN_OR_EQUALS:
        return Restrictions.le(propertyName, value1)
      case LIKE:
        return Restrictions.like(propertyName, value1)
      case NOT_EQUALS:
        return Restrictions.ne(propertyName, value1)
    }
    return null
  }

  private Criterion buildEvalRestrictionCriterion(EvalRestriction evalRestriction){
    Restriction restriction1 = evalRestriction.getRestriction1()
    Restriction restriction2 = evalRestriction.getRestriction2()

    switch(evalRestriction.getRestrictionType()){
      case AND:
        return Restrictions.and(buildCriterionPrivate(restriction1), buildCriterionPrivate(restriction2))
      case NOT:
        return Restrictions.not(buildCriterionPrivate(restriction1))
      case OR:
        return Restrictions.or(buildCriterionPrivate(restriction1), buildCriterionPrivate(restriction2))
    }
    return null
  }
}
