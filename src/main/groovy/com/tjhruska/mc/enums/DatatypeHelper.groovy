/**
Copyright 2007-2015 Timothy James Hruska (tjhruska@yahoo.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
  
package com.tjhruska.mc.enums

import java.io.Serializable
import java.util.Arrays
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set


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