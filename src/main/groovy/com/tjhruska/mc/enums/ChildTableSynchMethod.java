/**
 *   Copyright  2007-2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   ChildTableSynchMethod.java
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
package com.tjhruska.mc.enums;

import java.util.HashSet;
import java.util.Set;

import com.tjhruska.mc.enums.EnumerationNotUnique;


/**
 * @author tjhruska
 *
 */
public enum ChildTableSynchMethod {
    SYNCH_CHILD_TABLES(22, "SYNCH_CHILD_TABLES", "Synchronize Child Tables", 0),
    NO_CHILD_TABLES(23, "NO_CHILD_TABLES", "No Child Tables", 1),
    ABANDON_CHILDREN(24, "ABANDON_CHILDREN", "Abandon Any Existing Tables", 2);

    public final static int enumerationId = 5;
    public final static String idColumnName = "childTableSynchMethod_ev_id";
    private final static ChildTableSynchMethodHelper childTableSynchMethodHelper 
        = ChildTableSynchMethodHelper.getChildTableSynchMethodHelper();
    
    private int childTableSynchMethodEvId;
    private String name;
    private String description;
    private Integer sequence;
    
    private ChildTableSynchMethod(
        int childTableSynchMethodEvId, String name, String description, Integer sequence
        )
    {
        this.childTableSynchMethodEvId = childTableSynchMethodEvId;
        this.name = name;
        this.description = description;
        this.sequence = sequence;
        ChildTableSynchMethodHelper.getChildTableSynchMethodHelper().loadChildTableSynchMethod(
            this, childTableSynchMethodEvId, name, description, sequence
            );
    }
    public int getChildTableSynchMethodEvId(){return childTableSynchMethodEvId;};
    public String getName(){return name;};
    public String getDescription(){return description;};
    public Integer getSequence(){return sequence;};
    
    /**
     * @param id
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static ChildTableSynchMethod getChildTableSynchMethodById(int id){
        Set<ChildTableSynchMethod> childTableSynchMethods = childTableSynchMethodHelper.idToChildTableSynchMethod.get(id);
        if (childTableSynchMethods == null) return null;
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + childTableSynchMethods.size() + " elements");
        return childTableSynchMethods.iterator().next();
    }
    
    /**
     * @param id
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    public static Set<ChildTableSynchMethod> getChildTableSynchMethodSetById(int id){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.idToChildTableSynchMethod.get(id));
    }
    
    /**
     * @param name
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static ChildTableSynchMethod getChildTableSynchMethodByName(String name){
        Set<ChildTableSynchMethod> childTableSynchMethods 
            = childTableSynchMethodHelper.nameToChildTableSynchMethod.get(name);
        if (childTableSynchMethods == null) return null;
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + childTableSynchMethods.size() + " elements");
        return childTableSynchMethods.iterator().next();
    }
    
    /**
     * @param name
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    public static Set<ChildTableSynchMethod> getChildTableSynchMethodSetByName(String name){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.nameToChildTableSynchMethod.get(name));
    }
    
    /**
     * @param description
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static ChildTableSynchMethod getChildTableSynchMethodByDescription(String description){
        Set<ChildTableSynchMethod> childTableSynchMethods 
            = childTableSynchMethodHelper.descriptionToChildTableSynchMethod.get(description);
        if (childTableSynchMethods == null) return null;
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + childTableSynchMethods.size() + " elements");
        return childTableSynchMethods.iterator().next();
    }
    
    /**
     * @param description
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    public static Set<ChildTableSynchMethod> getChildTableSynchMethodSetByDescription(String description){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.descriptionToChildTableSynchMethod.get(description));
    }
    
    /**
     * @param sequence
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static ChildTableSynchMethod getChildTableSynchMethodBySequence(Integer sequence){
        Set<ChildTableSynchMethod> childTableSynchMethods 
            = childTableSynchMethodHelper.sequenceToChildTableSynchMethod.get(sequence);
        if (childTableSynchMethods == null) return null;
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + childTableSynchMethods.size() + " elements");
        return childTableSynchMethods.iterator().next();
    }
    
    /**
     * @param sequence
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    public static Set<ChildTableSynchMethod> getChildTableSynchMethodSetBySequence(Integer sequence){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.sequenceToChildTableSynchMethod.get(sequence));
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString(){
        String toString = "ChildTableSynchMethod " + name + " (" + description + ")";
        toString += "\\n\\tchildTableSynchMethodEvId: " + childTableSynchMethodEvId;
        toString += "\\n\\tsequence: " + sequence;
        return toString;
    }
}