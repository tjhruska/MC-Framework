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


class LinkCreationRuleHelper implements Serializable {
    private static final long serialVersionUID = 1L
    private static final LinkCreationRuleHelper linkCreationRuleHelper = new LinkCreationRuleHelper()
    
    protected Map<Integer,Set<LinkCreationRule>> idToLinkCreationRule
    protected Map<String,Set<LinkCreationRule>> nameToLinkCreationRule
    protected Map<String,Set<LinkCreationRule>> descriptionToLinkCreationRule
    protected Map<Integer,Set<LinkCreationRule>> sequenceToLinkCreationRule
    protected Map<String,Set<LinkCreationRule>> databaseNoteToLinkCreationRule
    /**
     * @return helper class used for doing lookups of LinkCreationRule
     */
    protected static LinkCreationRuleHelper getLinkCreationRuleHelper(){
        return linkCreationRuleHelper
    }
    
    private LinkCreationRuleHelper(){
        idToLinkCreationRule = new HashMap<Integer,Set<LinkCreationRule>>()
        nameToLinkCreationRule = new HashMap<String,Set<LinkCreationRule>>()
        descriptionToLinkCreationRule = new HashMap<String,Set<LinkCreationRule>>()
        sequenceToLinkCreationRule = new HashMap<Integer,Set<LinkCreationRule>>()
        databaseNoteToLinkCreationRule = new HashMap<String,Set<LinkCreationRule>>()
    }
    
    protected void loadLinkCreationRule(LinkCreationRule linkCreationRule, 
        int linkCreationRuleEvId, String name, String description,
        Integer sequence, String databaseNote)
    {
        if (!idToLinkCreationRule.containsKey(linkCreationRuleEvId))
            idToLinkCreationRule.put(
                linkCreationRuleEvId, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)))
        else
            idToLinkCreationRule.get(linkCreationRuleEvId).add(linkCreationRule)
        
        if (!nameToLinkCreationRule.containsKey(name))
            nameToLinkCreationRule.put(
                name, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)))
        else
            nameToLinkCreationRule.get(name).add(linkCreationRule)
        
        if (!descriptionToLinkCreationRule.containsKey(description))
            descriptionToLinkCreationRule.put(
                description, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)))
        else
            descriptionToLinkCreationRule.get(description).add(linkCreationRule)
        
        if (!sequenceToLinkCreationRule.containsKey(sequence))
            sequenceToLinkCreationRule.put(
                sequence, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)))
        else
            sequenceToLinkCreationRule.get(sequence).add(linkCreationRule)


       if (!databaseNoteToLinkCreationRule.containsKey(databaseNote))
            databaseNoteToLinkCreationRule.put(
                databaseNote, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)))
        else
            databaseNoteToLinkCreationRule.get(databaseNote).add(linkCreationRule)
    }
}