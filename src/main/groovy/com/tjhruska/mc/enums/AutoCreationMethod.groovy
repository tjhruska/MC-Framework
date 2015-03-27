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
enum AutoCreationMethod {
    CREATE_ENUMERATION(19, "CREATE_ENUMERATION", "Create Enumeration", 0, "java_enumeration_generation_sql"),
    CREATE_CLASS_NO_DATABASE(20, "CREATE_CLASS_NO_DATABASE", "Create Class No Database", 1, "java_class_no_database_generation_sql"),
    CREATE_NOTHING(21, "CREATE_NOTHING", "Create Nothing", 2, null)

    final static int enumerationId = 4
    final static String idColumnName = "autoCreationMethod_ev_id"
    final static AutoCreationMethodHelper autoCreationMethodHelper = AutoCreationMethodHelper.getAutoCreationMethodHelper()
    
    final int autoCreationMethodEvId
    final String name
    final String description
    final Integer sequence
    final String sqlTag
    
    private AutoCreationMethod(
        int autoCreationMethodEvId, String name, String description, Integer sequence
        , String sqlTag)
    {
        this.autoCreationMethodEvId = autoCreationMethodEvId
        this.name = name
        this.description = description
        this.sequence = sequence
        this.sqlTag = sqlTag
        AutoCreationMethodHelper.getAutoCreationMethodHelper().loadAutoCreationMethod(
            this, autoCreationMethodEvId, name, description, sequence
            , sqlTag)
    }
    
    /**
     * @param id
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static AutoCreationMethod getAutoCreationMethodById(int id){
        Set<AutoCreationMethod> autoCreationMethods = autoCreationMethodHelper.idToAutoCreationMethod.get(id)
        if (autoCreationMethods == null) return null
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + autoCreationMethods.size() + " elements")
        return autoCreationMethods.iterator().next()
    }
    
    /**
     * @param id
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    static Set<AutoCreationMethod> getAutoCreationMethodSetById(int id){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.idToAutoCreationMethod.get(id))
    }
    
    /**
     * @param name
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static AutoCreationMethod getAutoCreationMethodByName(String name){
        Set<AutoCreationMethod> autoCreationMethods = autoCreationMethodHelper.nameToAutoCreationMethod.get(name)
        if (autoCreationMethods == null) return null
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + autoCreationMethods.size() + " elements")
        return autoCreationMethods.iterator().next()
    }
    
    /**
     * @param name
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    static Set<AutoCreationMethod> getAutoCreationMethodSetByName(String name){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.nameToAutoCreationMethod.get(name))
    }
    
    /**
     * @param description
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static AutoCreationMethod getAutoCreationMethodByDescription(String description){
        Set<AutoCreationMethod> autoCreationMethods = autoCreationMethodHelper.descriptionToAutoCreationMethod.get(description)
        if (autoCreationMethods == null) return null
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + autoCreationMethods.size() + " elements")
        return autoCreationMethods.iterator().next()
    }
    
    /**
     * @param description
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    static Set<AutoCreationMethod> getAutoCreationMethodSetByDescription(String description){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.descriptionToAutoCreationMethod.get(description))
    }
    
    /**
     * @param sequence
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static AutoCreationMethod getAutoCreationMethodBySequence(Integer sequence){
        Set<AutoCreationMethod> autoCreationMethods = autoCreationMethodHelper.sequenceToAutoCreationMethod.get(sequence)
        if (autoCreationMethods == null) return null
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + autoCreationMethods.size() + " elements")
        return autoCreationMethods.iterator().next()
    }
    
    /**
     * @param sequence
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    static Set<AutoCreationMethod> getAutoCreationMethodSetBySequence(Integer sequence){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.sequenceToAutoCreationMethod.get(sequence))
    }
    /**
     * @param sqlTag
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static AutoCreationMethod getAutoCreationMethodBySqlTag(String sqlTag){
        Set<AutoCreationMethod> autoCreationMethods = autoCreationMethodHelper.sqlTagToAutoCreationMethod.get(sqlTag)
        if (autoCreationMethods == null) return null
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("sqlTag " + sqlTag 
                + " has " + autoCreationMethods.size() + " elements")
        return autoCreationMethods.iterator().next()
    }
    
    /**
     * @param sqlTag
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    static Set<AutoCreationMethod> getAutoCreationMethodSetBySqlTag(String sqlTag){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.sqlTagToAutoCreationMethod.get(sqlTag))
    }
}