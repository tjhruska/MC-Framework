/**
 *   Copyright (c) 2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   DatatypeHelper.groovy
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
package com.tjhruska.mc.enums

import java.io.Serializable
import java.util.Arrays
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set

/**
 * @author tjhruska
 *
 */
class DatatypeHelper implements Serializable {
    private static final long serialVersionUID = 1L
    private static final DatatypeHelper datatypeHelper = new DatatypeHelper()
    
    protected Map<Integer,Set<Datatype>> idToDatatype
    protected Map<String,Set<Datatype>> nameToDatatype
    protected Map<String,Set<Datatype>> descriptionToDatatype
    protected Map<Integer,Set<Datatype>> sequenceToDatatype
    protected Map<String,Set<Datatype>> javaClassToDatatype
    protected Map<String,Set<Datatype>> javaPrimitiveToDatatype
    protected Map<String,Set<Datatype>> databaseToDatatype
    protected Map<String,Set<Datatype>> hibernateToDatatype
    /**
     * @return helper class used for doing lookups of Datatype
     */
    protected static DatatypeHelper getDatatypeHelper(){
        return datatypeHelper
    }
    
    private DatatypeHelper(){
        idToDatatype = new HashMap<Integer,Set<Datatype>>()
        nameToDatatype = new HashMap<String,Set<Datatype>>()
        descriptionToDatatype = new HashMap<String,Set<Datatype>>()
        sequenceToDatatype = new HashMap<Integer,Set<Datatype>>()
        javaClassToDatatype = new HashMap<String,Set<Datatype>>()
        javaPrimitiveToDatatype = new HashMap<String,Set<Datatype>>()
        databaseToDatatype = new HashMap<String,Set<Datatype>>()
        hibernateToDatatype = new HashMap<String,Set<Datatype>>()
    }
    
    protected void loadDatatype(Datatype datatype, 
        int datatypeEvId, String name, String description,
        Integer sequence, String javaClass, String javaPrimitive, String database, String hibernate)
    {
        if (!idToDatatype.containsKey(datatypeEvId))
            idToDatatype.put(
                datatypeEvId, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            idToDatatype.get(datatypeEvId).add(datatype)
        
        if (!nameToDatatype.containsKey(name))
            nameToDatatype.put(
                name, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            nameToDatatype.get(name).add(datatype)
        
        if (!descriptionToDatatype.containsKey(description))
            descriptionToDatatype.put(
                description, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            descriptionToDatatype.get(description).add(datatype)
        
        if (!sequenceToDatatype.containsKey(sequence))
            sequenceToDatatype.put(
                sequence, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            sequenceToDatatype.get(sequence).add(datatype)


       if (!javaClassToDatatype.containsKey(javaClass))
            javaClassToDatatype.put(
                javaClass, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            javaClassToDatatype.get(javaClass).add(datatype)
       if (!javaPrimitiveToDatatype.containsKey(javaPrimitive))
            javaPrimitiveToDatatype.put(
                javaPrimitive, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            javaPrimitiveToDatatype.get(javaPrimitive).add(datatype)
       if (!databaseToDatatype.containsKey(database))
            databaseToDatatype.put(
                database, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            databaseToDatatype.get(database).add(datatype)
       if (!hibernateToDatatype.containsKey(hibernate))
            hibernateToDatatype.put(
                hibernate, 
                new HashSet<Datatype>(Arrays.asList(datatype)))
        else
            hibernateToDatatype.get(hibernate).add(datatype)
    }
}