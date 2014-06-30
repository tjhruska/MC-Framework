package com.tjhruska.mc.database.restrictions;

import java.util.HashMap;
import java.util.Map;

public class RestrictionTypeHelper {
	
	private static final RestrictionTypeHelper restrictionTypeHelper = new RestrictionTypeHelper();
	
	protected Map<String,RestrictionType> lookupMap;
	
	private RestrictionTypeHelper(){
		lookupMap = new HashMap<String, RestrictionType>();
	}
	
	protected static RestrictionTypeHelper getRestrictionTypeHelper() {
		return restrictionTypeHelper;
	}
} 