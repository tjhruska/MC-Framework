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


class AutoCreationMethodHelper implements Serializable {
    private static final long serialVersionUID = 1L
    private static final AutoCreationMethodHelper autoCreationMethodHelper = new AutoCreationMethodHelper()
    
    protected Map<Integer,Set<AutoCreationMethod>> idToAutoCreationMethod
    protected Map<String,Set<AutoCreationMethod>> nameToAutoCreationMethod
    protected Map<String,Set<AutoCreationMethod>> descriptionToAutoCreationMethod
    protected Map<Integer,Set<AutoCreationMethod>> sequenceToAutoCreationMethod
    protected Map<String,Set<AutoCreationMethod>> sqlTagToAutoCreationMethod
    /**
     * @return helper class used for doing lookups of AutoCreationMethod
     */
    protected static AutoCreationMethodHelper getAutoCreationMethodHelper(){
        return autoCreationMethodHelper
    }
    
    private AutoCreationMethodHelper(){
        idToAutoCreationMethod = new HashMap<Integer,Set<AutoCreationMethod>>()
        nameToAutoCreationMethod = new HashMap<String,Set<AutoCreationMethod>>()
        descriptionToAutoCreationMethod = new HashMap<String,Set<AutoCreationMethod>>()
        sequenceToAutoCreationMethod = new HashMap<Integer,Set<AutoCreationMethod>>()
        sqlTagToAutoCreationMethod = new HashMap<String,Set<AutoCreationMethod>>()
    }
    
    protected void loadAutoCreationMethod(AutoCreationMethod autoCreationMethod, 
        int autoCreationMethodEvId, String name, String description,
        Integer sequence, String sqlTag)
    {
        if (!idToAutoCreationMethod.containsKey(autoCreationMethodEvId))
            idToAutoCreationMethod.put(
                autoCreationMethodEvId, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)))
        else
            idToAutoCreationMethod.get(autoCreationMethodEvId).add(autoCreationMethod)
        
        if (!nameToAutoCreationMethod.containsKey(name))
            nameToAutoCreationMethod.put(
                name, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)))
        else
            nameToAutoCreationMethod.get(name).add(autoCreationMethod)
        
        if (!descriptionToAutoCreationMethod.containsKey(description))
            descriptionToAutoCreationMethod.put(
                description, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)))
        else
            descriptionToAutoCreationMethod.get(description).add(autoCreationMethod)
        
        if (!sequenceToAutoCreationMethod.containsKey(sequence))
            sequenceToAutoCreationMethod.put(
                sequence, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)))
        else
            sequenceToAutoCreationMethod.get(sequence).add(autoCreationMethod)


       if (!sqlTagToAutoCreationMethod.containsKey(sqlTag))
            sqlTagToAutoCreationMethod.put(
                sqlTag, 
                new HashSet<AutoCreationMethod>(Arrays.asList(autoCreationMethod)))
        else
            sqlTagToAutoCreationMethod.get(sqlTag).add(autoCreationMethod)
    }
}