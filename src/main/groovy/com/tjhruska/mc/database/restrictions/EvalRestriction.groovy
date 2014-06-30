package com.tjhruska.mc.database.restrictions;

import groovy.transform.EqualsAndHashCode;

@EqualsAndHashCode
public class EvalRestriction implements Restriction {

	RestrictionType restrictionType;
	Restriction restriction1;
	Restriction restriction2;
	
	protected EvalRestriction(RestrictionType restrictionType, Restriction restriction1){
		this.restrictionType = restrictionType;
		this.restriction1 = restriction1;
	}
	
	protected EvalRestriction(RestrictionType restrictionType, Restriction restriction1, Restriction restriction2){
		this.restrictionType = restrictionType;
		this.restriction1 = restriction1;
		this.restriction2 = restriction2;
	}
	
	public static Restriction and(Restriction lhs, Restriction rhs){
		return new EvalRestriction(RestrictionType.AND, lhs, rhs);
	}

	public static Restriction not(Restriction expression){
		return new EvalRestriction(RestrictionType.NOT, expression);
	}
	
	public static Restriction or(Restriction lhs, Restriction rhs){
		return new EvalRestriction(RestrictionType.OR, lhs, rhs);
	}

	public Restriction and(Restriction restriction) {
		return new EvalRestriction(RestrictionType.AND, this, restriction);
	}

	public Restriction or(Restriction restriction) {
		return new EvalRestriction(RestrictionType.OR, this, restriction);
	}

	public Restriction not() {
		return new EvalRestriction(RestrictionType.NOT, this);
	}
	
	/**
	 * @return the RestrictionType
	 */
	public RestrictionType getRestrictionType() {
		return restrictionType;
	}

	/**
	 * @return the restriction1
	 */
	public Restriction getRestriction1() {
		return restriction1;
	}

	/**
	 * @return the restriction2
	 */
	public Restriction getRestriction2() {
		return restriction2;
	}
}
