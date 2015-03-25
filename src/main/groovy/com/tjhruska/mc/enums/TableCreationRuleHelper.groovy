/**
 *   Copyright (c) 2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   TableCreationRuleHelper.groovy
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