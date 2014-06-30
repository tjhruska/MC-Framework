package com.tjhruska.mc.database.restrictions;

import java.util.Collection;
import java.util.Map;

public class Restrictions {
	
	public static Restriction allEq(Map<String,Object> propertyNameValues){
		return PropertyRestriction.allEq(propertyNameValues);
	}
	public static Restriction and(Restriction lhs, Restriction rhs){
		return EvalRestriction.and(lhs, rhs);
	}
	public static Restriction between(String propertyName, Object lo, Object hi){
		return PropertyRestriction.between(propertyName, lo, hi);
	}
	public static Restriction eq(String propertyName, Object value){
		return PropertyRestriction.eq(propertyName, value);
	}
	public static Restriction ge(String propertyName, Object value){
		return PropertyRestriction.ge(propertyName, value);
	}
	public static Restriction gt(String propertyName, Object value){
		return PropertyRestriction.gt(propertyName, value);
	}
	public static Restriction ilike(String propertyName, Object value){
		return PropertyRestriction.ilike(propertyName, value);
	}
	public static Restriction in(String propertyName, Collection<Object> values){
		return PropertyRestriction.inCollection(propertyName, values);
	}
	public static Restriction in(String propertyName, Object[] values){
		return PropertyRestriction.inArray(propertyName, values);
	}
	public static Restriction isNotNull(String propertyName){
		return PropertyRestriction.isNotNull(propertyName);
	}
	public static Restriction isNull(String propertyName){
		return PropertyRestriction.isNull(propertyName);
	}
	public static Restriction le(String propertyName, Object value){
		return PropertyRestriction.le(propertyName, value);
	}
	public static Restriction like(String propertyName, Object value){
		return PropertyRestriction.like(propertyName, value);
	}
	public static Restriction lt(String propertyName, Object value){
		return PropertyRestriction.lt(propertyName, value);
	}
	public static Restriction ne(String propertyName, Object value){
		return PropertyRestriction.ne(propertyName, value);
	}
	public static Restriction not(Restriction expression){
		return EvalRestriction.not(expression);
	}
	public static Restriction or(Restriction lhs, Restriction rhs){
		return EvalRestriction.or(lhs, rhs);
	}
}
