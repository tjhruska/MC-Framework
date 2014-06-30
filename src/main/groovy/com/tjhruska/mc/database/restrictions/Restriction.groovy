package com.tjhruska.mc.database.restrictions;

public interface Restriction {
	
	public RestrictionType getRestrictionType();
	
	public Restriction and(Restriction restriction);
	
	public Restriction or(Restriction restriction);
	
	public Restriction not();
} 