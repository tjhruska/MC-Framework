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

import java.io.Serializable
import java.util.Arrays
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set


class TableCreationRuleHelper implements Serializable {
    private static final long serialVersionUID = 1L
    private static final TableCreationRuleHelper tableCreationRuleHelper = new TableCreationRuleHelper()
    
    protected Map<Integer,Set<TableCreationRule>> idToTableCreationRule
    protected Map<String,Set<TableCreationRule>> nameToTableCreationRule
    protected Map<String,Set<TableCreationRule>> descriptionToTableCreationRule
    protected Map<Integer,Set<TableCreationRule>> sequenceToTableCreationRule
    /**
     * @return helper class used for doing lookups of TableCreationRule
     */
    protected static TableCreationRuleHelper getTableCreationRuleHelper(){
        return tableCreationRuleHelper
    }
    
    private TableCreationRuleHelper(){
        idToTableCreationRule = new HashMap<Integer,Set<TableCreationRule>>()
        nameToTableCreationRule = new HashMap<String,Set<TableCreationRule>>()
        descriptionToTableCreationRule = new HashMap<String,Set<TableCreationRule>>()
        sequenceToTableCreationRule = new HashMap<Integer,Set<TableCreationRule>>()
    }
    
    protected void loadTableCreationRule(TableCreationRule tableCreationRule, 
        int tableCreationRuleEvId, String name, String description,
        Integer sequence)
    {
        if (!idToTableCreationRule.containsKey(tableCreationRuleEvId))
            idToTableCreationRule.put(
                tableCreationRuleEvId, 
                new HashSet<TableCreationRule>(Arrays.asList(tableCreationRule)))
        else
            idToTableCreationRule.get(tableCreationRuleEvId).add(tableCreationRule)
        
        if (!nameToTableCreationRule.containsKey(name))
            nameToTableCreationRule.put(
                name, 
                new HashSet<TableCreationRule>(Arrays.asList(tableCreationRule)))
        else
            nameToTableCreationRule.get(name).add(tableCreationRule)
        
        if (!descriptionToTableCreationRule.containsKey(description))
            descriptionToTableCreationRule.put(
                description, 
                new HashSet<TableCreationRule>(Arrays.asList(tableCreationRule)))
        else
            descriptionToTableCreationRule.get(description).add(tableCreationRule)
        
        if (!sequenceToTableCreationRule.containsKey(sequence))
            sequenceToTableCreationRule.put(
                sequence, 
                new HashSet<TableCreationRule>(Arrays.asList(tableCreationRule)))
        else
            sequenceToTableCreationRule.get(sequence).add(tableCreationRule)


    }
}