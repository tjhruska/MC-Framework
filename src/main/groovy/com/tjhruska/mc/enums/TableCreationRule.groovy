/**
Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)

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
enum TableCreationRule {
    ALTER(32, "ALTER", "Alter existing table", 1),
    DROP_RECREATE(33, "DROP_RECREATE", "Discard data, drop and recreate", 2),
    RENAME_CREATE_POP_DROP(34, "RENAME_CREATE_POP_DROP", "Migrate data via rename to new table", 3),
    IGNORE_ABANDON(35, "IGNORE_ABANDON", "Ignore table", 4)

    final static int enumerationId = 8
    final static String idColumnName = "tableCreationRule_ev_id"
    final static TableCreationRuleHelper tableCreationRuleHelper = TableCreationRuleHelper.getTableCreationRuleHelper()
    
    final int tableCreationRuleEvId
    final String name
    final String description
    final Integer sequence
    
    private TableCreationRule(
        int tableCreationRuleEvId, String name, String description, Integer sequence
        )
    {
        this.tableCreationRuleEvId = tableCreationRuleEvId
        this.name = name
        this.description = description
        this.sequence = sequence
        TableCreationRuleHelper.getTableCreationRuleHelper().loadTableCreationRule(
            this, tableCreationRuleEvId, name, description, sequence
            )
    }
    
    /**
     * @param id
     * @return TableCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static TableCreationRule getTableCreationRuleById(int id){
        Set<TableCreationRule> tableCreationRules = tableCreationRuleHelper.idToTableCreationRule.get(id)
        if (tableCreationRules == null) return null
        if (tableCreationRules.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + tableCreationRules.size() + " elements")
        return tableCreationRules.iterator().next()
    }
    
    /**
     * @param id
     * @return TableCreationRule that is mapped to by the input parameter
     */
    static Set<TableCreationRule> getTableCreationRuleSetById(int id){
        return new HashSet<TableCreationRule>(tableCreationRuleHelper.idToTableCreationRule.get(id))
    }
    
    /**
     * @param name
     * @return TableCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static TableCreationRule getTableCreationRuleByName(String name){
        Set<TableCreationRule> tableCreationRules = tableCreationRuleHelper.nameToTableCreationRule.get(name)
        if (tableCreationRules == null) return null
        if (tableCreationRules.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + tableCreationRules.size() + " elements")
        return tableCreationRules.iterator().next()
    }
    
    /**
     * @param name
     * @return TableCreationRule that is mapped to by the input parameter
     */
    static Set<TableCreationRule> getTableCreationRuleSetByName(String name){
        return new HashSet<TableCreationRule>(tableCreationRuleHelper.nameToTableCreationRule.get(name))
    }
    
    /**
     * @param description
     * @return TableCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static TableCreationRule getTableCreationRuleByDescription(String description){
        Set<TableCreationRule> tableCreationRules = tableCreationRuleHelper.descriptionToTableCreationRule.get(description)
        if (tableCreationRules == null) return null
        if (tableCreationRules.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + tableCreationRules.size() + " elements")
        return tableCreationRules.iterator().next()
    }
    
    /**
     * @param description
     * @return TableCreationRule that is mapped to by the input parameter
     */
    static Set<TableCreationRule> getTableCreationRuleSetByDescription(String description){
        return new HashSet<TableCreationRule>(tableCreationRuleHelper.descriptionToTableCreationRule.get(description))
    }
    
    /**
     * @param sequence
     * @return TableCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static TableCreationRule getTableCreationRuleBySequence(Integer sequence){
        Set<TableCreationRule> tableCreationRules = tableCreationRuleHelper.sequenceToTableCreationRule.get(sequence)
        if (tableCreationRules == null) return null
        if (tableCreationRules.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + tableCreationRules.size() + " elements")
        return tableCreationRules.iterator().next()
    }
    
    /**
     * @param sequence
     * @return TableCreationRule that is mapped to by the input parameter
     */
    static Set<TableCreationRule> getTableCreationRuleSetBySequence(Integer sequence){
        return new HashSet<TableCreationRule>(tableCreationRuleHelper.sequenceToTableCreationRule.get(sequence))
    }
}