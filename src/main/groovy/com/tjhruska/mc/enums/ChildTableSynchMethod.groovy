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

import groovy.transform.ToString

import java.util.HashSet
import java.util.Set

import com.tjhruska.mc.enums.EnumerationNotUnique


@ToString (includeNames = true, includePackage=false, includeFields=true)
enum ChildTableSynchMethod {
    SYNCH_CHILD_TABLES(22, "SYNCH_CHILD_TABLES", "Synchronize Child Tables", 0),
    NO_CHILD_TABLES(23, "NO_CHILD_TABLES", "No Child Tables", 1),
    ABANDON_CHILDREN(24, "ABANDON_CHILDREN", "Abandon Any Existing Tables", 2)

    final static int enumerationId = 5
    final static String idColumnName = "childTableSynchMethod_ev_id"
    final static ChildTableSynchMethodHelper childTableSynchMethodHelper = ChildTableSynchMethodHelper.getChildTableSynchMethodHelper()
    
    final int childTableSynchMethodEvId
    final String name
    final String description
    final Integer sequence
    
    private ChildTableSynchMethod(
        int childTableSynchMethodEvId, String name, String description, Integer sequence
        )
    {
        this.childTableSynchMethodEvId = childTableSynchMethodEvId
        this.name = name
        this.description = description
        this.sequence = sequence
        ChildTableSynchMethodHelper.getChildTableSynchMethodHelper().loadChildTableSynchMethod(
            this, childTableSynchMethodEvId, name, description, sequence
            )
    }
    
    /**
     * @param id
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static ChildTableSynchMethod getChildTableSynchMethodById(int id){
        Set<ChildTableSynchMethod> childTableSynchMethods = childTableSynchMethodHelper.idToChildTableSynchMethod.get(id)
        if (childTableSynchMethods == null) return null
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + childTableSynchMethods.size() + " elements")
        return childTableSynchMethods.iterator().next()
    }
    
    /**
     * @param id
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    static Set<ChildTableSynchMethod> getChildTableSynchMethodSetById(int id){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.idToChildTableSynchMethod.get(id))
    }
    
    /**
     * @param name
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static ChildTableSynchMethod getChildTableSynchMethodByName(String name){
        Set<ChildTableSynchMethod> childTableSynchMethods = childTableSynchMethodHelper.nameToChildTableSynchMethod.get(name)
        if (childTableSynchMethods == null) return null
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + childTableSynchMethods.size() + " elements")
        return childTableSynchMethods.iterator().next()
    }
    
    /**
     * @param name
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    static Set<ChildTableSynchMethod> getChildTableSynchMethodSetByName(String name){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.nameToChildTableSynchMethod.get(name))
    }
    
    /**
     * @param description
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static ChildTableSynchMethod getChildTableSynchMethodByDescription(String description){
        Set<ChildTableSynchMethod> childTableSynchMethods = childTableSynchMethodHelper.descriptionToChildTableSynchMethod.get(description)
        if (childTableSynchMethods == null) return null
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + childTableSynchMethods.size() + " elements")
        return childTableSynchMethods.iterator().next()
    }
    
    /**
     * @param description
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    static Set<ChildTableSynchMethod> getChildTableSynchMethodSetByDescription(String description){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.descriptionToChildTableSynchMethod.get(description))
    }
    
    /**
     * @param sequence
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static ChildTableSynchMethod getChildTableSynchMethodBySequence(Integer sequence){
        Set<ChildTableSynchMethod> childTableSynchMethods = childTableSynchMethodHelper.sequenceToChildTableSynchMethod.get(sequence)
        if (childTableSynchMethods == null) return null
        if (childTableSynchMethods.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + childTableSynchMethods.size() + " elements")
        return childTableSynchMethods.iterator().next()
    }
    
    /**
     * @param sequence
     * @return ChildTableSynchMethod that is mapped to by the input parameter
     */
    static Set<ChildTableSynchMethod> getChildTableSynchMethodSetBySequence(Integer sequence){
        return new HashSet<ChildTableSynchMethod>(childTableSynchMethodHelper.sequenceToChildTableSynchMethod.get(sequence))
    }
}