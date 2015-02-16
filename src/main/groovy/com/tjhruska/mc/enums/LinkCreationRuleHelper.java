/**
 *   Copyright (c) 2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   LinkCreationRuleHelper.java
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
package com.tjhruska.mc.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tjhruska
 *
 */
public class LinkCreationRuleHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final LinkCreationRuleHelper linkCreationRuleHelper = new LinkCreationRuleHelper();
    
    protected Map<Integer,Set<LinkCreationRule>> idToLinkCreationRule;
    protected Map<String,Set<LinkCreationRule>> nameToLinkCreationRule;
    protected Map<String,Set<LinkCreationRule>> descriptionToLinkCreationRule;
    protected Map<Integer,Set<LinkCreationRule>> sequenceToLinkCreationRule;
    protected Map<String,Set<LinkCreationRule>> databaseNoteToLinkCreationRule;
    /**
     * @return helper class used for doing lookups of LinkCreationRule
     */
    protected static LinkCreationRuleHelper getLinkCreationRuleHelper(){
        return linkCreationRuleHelper;
    }
    
    private LinkCreationRuleHelper(){
        idToLinkCreationRule = new HashMap<Integer,Set<LinkCreationRule>>();
        nameToLinkCreationRule = new HashMap<String,Set<LinkCreationRule>>();
        descriptionToLinkCreationRule = new HashMap<String,Set<LinkCreationRule>>();
        sequenceToLinkCreationRule = new HashMap<Integer,Set<LinkCreationRule>>();
        databaseNoteToLinkCreationRule = new HashMap<String,Set<LinkCreationRule>>();
    }
    
    protected void loadLinkCreationRule(LinkCreationRule linkCreationRule, 
        int linkCreationRuleEvId, String name, String description,
        Integer sequence, String databaseNote)
    {
        if (!idToLinkCreationRule.containsKey(linkCreationRuleEvId))
            idToLinkCreationRule.put(
                linkCreationRuleEvId, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)));
        else
            idToLinkCreationRule.get(linkCreationRuleEvId).add(linkCreationRule);
        
        if (!nameToLinkCreationRule.containsKey(name))
            nameToLinkCreationRule.put(
                name, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)));
        else
            nameToLinkCreationRule.get(name).add(linkCreationRule);
        
        if (!descriptionToLinkCreationRule.containsKey(description))
            descriptionToLinkCreationRule.put(
                description, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)));
        else
            descriptionToLinkCreationRule.get(description).add(linkCreationRule);
        
        if (!sequenceToLinkCreationRule.containsKey(sequence))
            sequenceToLinkCreationRule.put(
                sequence, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)));
        else
            sequenceToLinkCreationRule.get(sequence).add(linkCreationRule);

       if (!databaseNoteToLinkCreationRule.containsKey(databaseNote))
            databaseNoteToLinkCreationRule.put(
                databaseNote, 
                new HashSet<LinkCreationRule>(Arrays.asList(linkCreationRule)));
        else
            databaseNoteToLinkCreationRule.get(databaseNote).add(linkCreationRule);
    }
}