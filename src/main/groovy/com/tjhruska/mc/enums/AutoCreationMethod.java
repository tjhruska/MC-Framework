/**
 *   Copyright  2007-2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   AutoCreationMethod.java
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
public enum AutoCreationMethod {
    CREATE_ENUMERATION(19, "CREATE_ENUMERATION", "Create Enumeration", 0, "java_enumeration_generation_sql"),
    CREATE_CLASS_NO_DATABASE(20, "CREATE_CLASS_NO_DATABASE", "Create Class No Database", 1, "java_class_no_database_generation_sql"),
    CREATE_NOTHING(21, "CREATE_NOTHING", "Create Nothing", 2, null);

    public final static int enumerationId = 4;
    public final static String idColumnName = "autoCreationMethod_ev_id";
    private final static AutoCreationMethodHelper autoCreationMethodHelper 
        = AutoCreationMethodHelper.getAutoCreationMethodHelper();
    
    private int autoCreationMethodEvId;
    private String name;
    private String description;
    private Integer sequence;
    private String sqlTag;
    
    private AutoCreationMethod(
        int autoCreationMethodEvId, String name, String description, Integer sequence
        , String sqlTag)
    {
        this.autoCreationMethodEvId = autoCreationMethodEvId;
        this.name = name;
        this.description = description;
        this.sequence = sequence;
        this.sqlTag = sqlTag;
        AutoCreationMethodHelper.getAutoCreationMethodHelper().loadAutoCreationMethod(
            this, autoCreationMethodEvId, name, description, sequence
            , sqlTag);
    }
    public int getAutoCreationMethodEvId(){return autoCreationMethodEvId;};
    public String getName(){return name;};
    public String getDescription(){return description;};
    public Integer getSequence(){return sequence;};
    public String getSqlTag(){return sqlTag;}
    
    /**
     * @param id
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static AutoCreationMethod getAutoCreationMethodById(int id){
        Set<AutoCreationMethod> autoCreationMethods = autoCreationMethodHelper.idToAutoCreationMethod.get(id);
        if (autoCreationMethods == null) return null;
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + autoCreationMethods.size() + " elements");
        return autoCreationMethods.iterator().next();
    }
    
    /**
     * @param id
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    public static Set<AutoCreationMethod> getAutoCreationMethodSetById(int id){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.idToAutoCreationMethod.get(id));
    }
    
    /**
     * @param name
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static AutoCreationMethod getAutoCreationMethodByName(String name){
        Set<AutoCreationMethod> autoCreationMethods 
            = autoCreationMethodHelper.nameToAutoCreationMethod.get(name);
        if (autoCreationMethods == null) return null;
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + autoCreationMethods.size() + " elements");
        return autoCreationMethods.iterator().next();
    }
    
    /**
     * @param name
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    public static Set<AutoCreationMethod> getAutoCreationMethodSetByName(String name){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.nameToAutoCreationMethod.get(name));
    }
    
    /**
     * @param description
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static AutoCreationMethod getAutoCreationMethodByDescription(String description){
        Set<AutoCreationMethod> autoCreationMethods 
            = autoCreationMethodHelper.descriptionToAutoCreationMethod.get(description);
        if (autoCreationMethods == null) return null;
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + autoCreationMethods.size() + " elements");
        return autoCreationMethods.iterator().next();
    }
    
    /**
     * @param description
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    public static Set<AutoCreationMethod> getAutoCreationMethodSetByDescription(String description){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.descriptionToAutoCreationMethod.get(description));
    }
    
    /**
     * @param sequence
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static AutoCreationMethod getAutoCreationMethodBySequence(Integer sequence){
        Set<AutoCreationMethod> autoCreationMethods 
            = autoCreationMethodHelper.sequenceToAutoCreationMethod.get(sequence);
        if (autoCreationMethods == null) return null;
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + autoCreationMethods.size() + " elements");
        return autoCreationMethods.iterator().next();
    }
    
    /**
     * @param sequence
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    public static Set<AutoCreationMethod> getAutoCreationMethodSetBySequence(Integer sequence){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.sequenceToAutoCreationMethod.get(sequence));
    }
    /**
     * @param sqlTag
     * @return AutoCreationMethod that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static AutoCreationMethod getAutoCreationMethodBySqlTag(String sqlTag){
        Set<AutoCreationMethod> autoCreationMethods 
            = autoCreationMethodHelper.sqlTagToAutoCreationMethod.get(sqlTag);
        if (autoCreationMethods == null) return null;
        if (autoCreationMethods.size() > 1)
            throw new EnumerationNotUnique("sqlTag " + sqlTag 
                + " has " + autoCreationMethods.size() + " elements");
        return autoCreationMethods.iterator().next();
    }
    
    /**
     * @param sqlTag
     * @return AutoCreationMethod that is mapped to by the input parameter
     */
    public static Set<AutoCreationMethod> getAutoCreationMethodSetBySqlTag(String sqlTag){
        return new HashSet<AutoCreationMethod>(autoCreationMethodHelper.sqlTagToAutoCreationMethod.get(sqlTag));
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString(){
        String toString = "AutoCreationMethod " + name + " (" + description + ")";
        toString += "\\n\\tautoCreationMethodEvId: " + autoCreationMethodEvId;
        toString += "\\n\\tsequence: " + sequence;
        toString += "\\n\\tsqlTag: " + sqlTag;
        return toString;
    }
}