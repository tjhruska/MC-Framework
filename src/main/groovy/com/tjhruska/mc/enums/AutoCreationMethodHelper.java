/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   AutoCreationMethodHelper.java
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
public class AutoCreationMethodHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final AutoCreationMethodHelper autoCreationMethodHelper = new AutoCreationMethodHelper();
    
    protected Map<Integer,Set<AutoCreationMethod>> idToAutoCreationMethod;
    protected Map<String,Set<AutoCreationMethod>> nameToAutoCreationMethod;
    protected Map<String,Set<AutoCreationMethod>> descriptionToAutoCreationMethod;
    protected Map<Integer,Set<AutoCreationMethod>> sequenceToAutoCreationMethod;
    protected Map<String,Set<AutoCreationMethod>> sqlTagToAutoCreationMethod;
    /**
     * @return helper class used for doing lookups of AutoCreationMethod
     */
    protected static AutoCreationMethodHelper getAutoCreationMethodHelper(){
        return autoCreationMethodHelper;
    }
    
    private AutoCreationMethodHelper(){
        idToAutoCreationMethod = new HashMap<Integer,Set<AutoCreationMethod>>();
        nameToAutoCreationMethod = new HashMap<String,Set<AutoCreationMethod>>();
        descriptionToAutoCreationMethod = new HashMap<String,Set<AutoCreationMethod>>();
        sequenceToAutoCreationMethod = new HashMap<Integer,Set<AutoCreationMethod>>();
        sqlTagToAutoCreationMethod = new HashMap<String,Set<AutoCreationMethod>>();
    }
    
    protected void loadAutoCreationMethod(AutoCreationMethod autoCreationMethod, 
        int autoCreationMethodEvId, String name, String description,
        Integer sequence, String sqlTag)
    {
        if (!idToAutoCreationMethod.containsKey(autoCreationMethodEvId))
            idToAutoCreationMethod.put(
                autoCreationMethodEvId, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)));
        else
            idToAutoCreationMethod.get(autoCreationMethodEvId).add(autoCreationMethod);
        
        if (!nameToAutoCreationMethod.containsKey(name))
            nameToAutoCreationMethod.put(
                name, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)));
        else
            nameToAutoCreationMethod.get(name).add(autoCreationMethod);
        
        if (!descriptionToAutoCreationMethod.containsKey(description))
            descriptionToAutoCreationMethod.put(
                description, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)));
        else
            descriptionToAutoCreationMethod.get(description).add(autoCreationMethod);
        
        if (!sequenceToAutoCreationMethod.containsKey(sequence))
            sequenceToAutoCreationMethod.put(
                sequence, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)));
        else
            sequenceToAutoCreationMethod.get(sequence).add(autoCreationMethod);

       if (!sqlTagToAutoCreationMethod.containsKey(sqlTag))
            sqlTagToAutoCreationMethod.put(
                sqlTag, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)));
        else
            sqlTagToAutoCreationMethod.get(sqlTag).add(autoCreationMethod);
    }
}