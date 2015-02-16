/**
 *   Copyright (c) 2007-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   LinkCreationRule.java
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

import java.util.HashSet;
import java.util.Set;

import com.tjhruska.mc.enums.EnumerationNotUnique;


/**
 * @author tjhruska
 *
 */
public enum LinkCreationRule {
    N_TO_N(29, "N_TO_N", "N to N", 1, "Will Create Linking Table"),
    N_TO_ONE(30, "N_TO_ONE", "N to 1", 2, "Table A has FK Column to B"),
    ONE_TO_N(31, "ONE_TO_N", "1 to N", 3, "Table B has FK Column to A");

    public final static int enumerationId = 7;
    public final static String idColumnName = "linkCreationRule_ev_id";
    private final static LinkCreationRuleHelper linkCreationRuleHelper 
        = LinkCreationRuleHelper.getLinkCreationRuleHelper();
    
    private int linkCreationRuleEvId;
    private String name;
    private String description;
    private Integer sequence;
    private String databaseNote;
    
    private LinkCreationRule(
        int linkCreationRuleEvId, String name, String description, Integer sequence
        , String databaseNote)
    {
        this.linkCreationRuleEvId = linkCreationRuleEvId;
        this.name = name;
        this.description = description;
        this.sequence = sequence;
        this.databaseNote = databaseNote;
        LinkCreationRuleHelper.getLinkCreationRuleHelper().loadLinkCreationRule(
            this, linkCreationRuleEvId, name, description, sequence
            , databaseNote);
    }
    public int getLinkCreationRuleEvId(){return linkCreationRuleEvId;};
    public String getName(){return name;};
    public String getDescription(){return description;};
    public Integer getSequence(){return sequence;};
    public String getDatabaseNote(){return databaseNote;}
    
    /**
     * @param id
     * @return LinkCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static LinkCreationRule getLinkCreationRuleById(int id){
        Set<LinkCreationRule> linkCreationRules = linkCreationRuleHelper.idToLinkCreationRule.get(id);
        if (linkCreationRules == null) return null;
        if (linkCreationRules.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + linkCreationRules.size() + " elements");
        return linkCreationRules.iterator().next();
    }
    
    /**
     * @param id
     * @return LinkCreationRule that is mapped to by the input parameter
     */
    public static Set<LinkCreationRule> getLinkCreationRuleSetById(int id){
        return new HashSet<LinkCreationRule>(linkCreationRuleHelper.idToLinkCreationRule.get(id));
    }
    
    /**
     * @param name
     * @return LinkCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static LinkCreationRule getLinkCreationRuleByName(String name){
        Set<LinkCreationRule> linkCreationRules 
            = linkCreationRuleHelper.nameToLinkCreationRule.get(name);
        if (linkCreationRules == null) return null;
        if (linkCreationRules.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + linkCreationRules.size() + " elements");
        return linkCreationRules.iterator().next();
    }
    
    /**
     * @param name
     * @return LinkCreationRule that is mapped to by the input parameter
     */
    public static Set<LinkCreationRule> getLinkCreationRuleSetByName(String name){
        return new HashSet<LinkCreationRule>(linkCreationRuleHelper.nameToLinkCreationRule.get(name));
    }
    
    /**
     * @param description
     * @return LinkCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static LinkCreationRule getLinkCreationRuleByDescription(String description){
        Set<LinkCreationRule> linkCreationRules 
            = linkCreationRuleHelper.descriptionToLinkCreationRule.get(description);
        if (linkCreationRules == null) return null;
        if (linkCreationRules.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + linkCreationRules.size() + " elements");
        return linkCreationRules.iterator().next();
    }
    
    /**
     * @param description
     * @return LinkCreationRule that is mapped to by the input parameter
     */
    public static Set<LinkCreationRule> getLinkCreationRuleSetByDescription(String description){
        return new HashSet<LinkCreationRule>(linkCreationRuleHelper.descriptionToLinkCreationRule.get(description));
    }
    
    /**
     * @param sequence
     * @return LinkCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static LinkCreationRule getLinkCreationRuleBySequence(Integer sequence){
        Set<LinkCreationRule> linkCreationRules 
            = linkCreationRuleHelper.sequenceToLinkCreationRule.get(sequence);
        if (linkCreationRules == null) return null;
        if (linkCreationRules.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + linkCreationRules.size() + " elements");
        return linkCreationRules.iterator().next();
    }
    
    /**
     * @param sequence
     * @return LinkCreationRule that is mapped to by the input parameter
     */
    public static Set<LinkCreationRule> getLinkCreationRuleSetBySequence(Integer sequence){
        return new HashSet<LinkCreationRule>(linkCreationRuleHelper.sequenceToLinkCreationRule.get(sequence));
    }
    /**
     * @param databaseNote
     * @return LinkCreationRule that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static LinkCreationRule getLinkCreationRuleByDatabaseNote(String databaseNote){
        Set<LinkCreationRule> linkCreationRules 
            = linkCreationRuleHelper.databaseNoteToLinkCreationRule.get(databaseNote);
        if (linkCreationRules == null) return null;
        if (linkCreationRules.size() > 1)
            throw new EnumerationNotUnique("databaseNote " + databaseNote 
                + " has " + linkCreationRules.size() + " elements");
        return linkCreationRules.iterator().next();
    }
    
    /**
     * @param databaseNote
     * @return LinkCreationRule that is mapped to by the input parameter
     */
    public static Set<LinkCreationRule> getLinkCreationRuleSetByDatabaseNote(String databaseNote){
        return new HashSet<LinkCreationRule>(linkCreationRuleHelper.databaseNoteToLinkCreationRule.get(databaseNote));
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString(){
        String toString = "LinkCreationRule " + name + " (" + description + ")";
        toString += "\\n\\tlinkCreationRuleEvId: " + linkCreationRuleEvId;
        toString += "\\n\\tsequence: " + sequence;
        toString += "\\n\\tdatabaseNote: " + databaseNote;
        return toString;
    }
}