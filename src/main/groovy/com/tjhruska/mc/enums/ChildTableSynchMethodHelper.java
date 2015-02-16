/**
 *   Copyright (c) 2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   ChildTableSynchMethodHelper.java
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

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tjhruska
 *
 */
public class ChildTableSynchMethodHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final ChildTableSynchMethodHelper childTableSynchMethodHelper = new ChildTableSynchMethodHelper();
    
    protected Map<Integer,Set<ChildTableSynchMethod>> idToChildTableSynchMethod;
    protected Map<String,Set<ChildTableSynchMethod>> nameToChildTableSynchMethod;
    protected Map<String,Set<ChildTableSynchMethod>> descriptionToChildTableSynchMethod;
    protected Map<Integer,Set<ChildTableSynchMethod>> sequenceToChildTableSynchMethod;
    /**
     * @return helper class used for doing lookups of ChildTableSynchMethod
     */
    protected static ChildTableSynchMethodHelper getChildTableSynchMethodHelper(){
        return childTableSynchMethodHelper;
    }
    
    private ChildTableSynchMethodHelper(){
        idToChildTableSynchMethod = new HashMap<Integer,Set<ChildTableSynchMethod>>();
        nameToChildTableSynchMethod = new HashMap<String,Set<ChildTableSynchMethod>>();
        descriptionToChildTableSynchMethod = new HashMap<String,Set<ChildTableSynchMethod>>();
        sequenceToChildTableSynchMethod = new HashMap<Integer,Set<ChildTableSynchMethod>>();
    }
    
    protected void loadChildTableSynchMethod(ChildTableSynchMethod childTableSynchMethod, 
        int childTableSynchMethodEvId, String name, String description,
        Integer sequence)
    {
        if (!idToChildTableSynchMethod.containsKey(childTableSynchMethodEvId))
            idToChildTableSynchMethod.put(
                childTableSynchMethodEvId, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)));
        else
            idToChildTableSynchMethod.get(childTableSynchMethodEvId).add(childTableSynchMethod);
        
        if (!nameToChildTableSynchMethod.containsKey(name))
            nameToChildTableSynchMethod.put(
                name, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)));
        else
            nameToChildTableSynchMethod.get(name).add(childTableSynchMethod);
        
        if (!descriptionToChildTableSynchMethod.containsKey(description))
            descriptionToChildTableSynchMethod.put(
                description, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)));
        else
            descriptionToChildTableSynchMethod.get(description).add(childTableSynchMethod);
        
        if (!sequenceToChildTableSynchMethod.containsKey(sequence))
            sequenceToChildTableSynchMethod.put(
                sequence, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)));
        else
            sequenceToChildTableSynchMethod.get(sequence).add(childTableSynchMethod);


    }
}