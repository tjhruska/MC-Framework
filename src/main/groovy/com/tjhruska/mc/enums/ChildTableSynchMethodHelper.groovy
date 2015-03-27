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


class ChildTableSynchMethodHelper implements Serializable {
    private static final long serialVersionUID = 1L
    private static final ChildTableSynchMethodHelper childTableSynchMethodHelper = new ChildTableSynchMethodHelper()
    
    protected Map<Integer,Set<ChildTableSynchMethod>> idToChildTableSynchMethod
    protected Map<String,Set<ChildTableSynchMethod>> nameToChildTableSynchMethod
    protected Map<String,Set<ChildTableSynchMethod>> descriptionToChildTableSynchMethod
    protected Map<Integer,Set<ChildTableSynchMethod>> sequenceToChildTableSynchMethod
    /**
     * @return helper class used for doing lookups of ChildTableSynchMethod
     */
    protected static ChildTableSynchMethodHelper getChildTableSynchMethodHelper(){
        return childTableSynchMethodHelper
    }
    
    private ChildTableSynchMethodHelper(){
        idToChildTableSynchMethod = new HashMap<Integer,Set<ChildTableSynchMethod>>()
        nameToChildTableSynchMethod = new HashMap<String,Set<ChildTableSynchMethod>>()
        descriptionToChildTableSynchMethod = new HashMap<String,Set<ChildTableSynchMethod>>()
        sequenceToChildTableSynchMethod = new HashMap<Integer,Set<ChildTableSynchMethod>>()
    }
    
    protected void loadChildTableSynchMethod(ChildTableSynchMethod childTableSynchMethod, 
        int childTableSynchMethodEvId, String name, String description,
        Integer sequence)
    {
        if (!idToChildTableSynchMethod.containsKey(childTableSynchMethodEvId))
            idToChildTableSynchMethod.put(
                childTableSynchMethodEvId, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)))
        else
            idToChildTableSynchMethod.get(childTableSynchMethodEvId).add(childTableSynchMethod)
        
        if (!nameToChildTableSynchMethod.containsKey(name))
            nameToChildTableSynchMethod.put(
                name, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)))
        else
            nameToChildTableSynchMethod.get(name).add(childTableSynchMethod)
        
        if (!descriptionToChildTableSynchMethod.containsKey(description))
            descriptionToChildTableSynchMethod.put(
                description, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)))
        else
            descriptionToChildTableSynchMethod.get(description).add(childTableSynchMethod)
        
        if (!sequenceToChildTableSynchMethod.containsKey(sequence))
            sequenceToChildTableSynchMethod.put(
                sequence, 
                new HashSet<ChildTableSynchMethod>(Arrays.asList(childTableSynchMethod)))
        else
            sequenceToChildTableSynchMethod.get(sequence).add(childTableSynchMethod)


    }
}