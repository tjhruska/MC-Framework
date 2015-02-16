/**
 *   Copyright (c) 2007-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   Container.java
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
public enum Container {
    SOLO_ONE(5, "SOLO_ONE", "Solo<ONE>", 0, "Solo", "com.tjhruska.mc.util", null, null),
    DUO_ONE_TWO(6, "DUO_ONE_TWO", "Duo<ONE,TWO>", 1, "Duo", "com.tjhruska.mc.util", null, null),
    TRIO_ONE_TWO_THREE(7, "TRIO_ONE_TWO_THREE", "Trio<ONE,TWO,THREE>", 2, "Trio", "com.tjhruska.mc.util", null, null),
    QUARTET_ONE_TWO_THREE_FOUR(8, "QUARTET_ONE_TWO_THREE_FOUR", "Quartet<ONE,TWO,THREE,FOUR>", 3, "Quartet", "com.tjhruska.mc.util", null, null),
    SET_T(9, "SET_T", "Set<T>", 4, "Set", "java.util", null, "HashSet"),
    LIST_T(10, "LIST_T", "List<T>", 5, "List", "java.util", null, "ArrayList"),
    MAP_K_V(11, "MAP_K_V", "Map<K,V>", 6, "Map", "java.util", null, "HashMap"),
    MAP_K_SET_T(12, "MAP_K_SET_T", "Map<K,Set<T>>", 7, "Map", "java.util", null, null),
    MAP_K_LIST_T(13, "MAP_K_LIST_T", "Map<K,List<T>>", 8, "Map", "java.util", null, null),
    MAP_K_MAP_K_V(14, "MAP_K_MAP_K_V", "Map<K,Map<K,V>>", 9, "Map", "java.util", null, null);

    public final static int enumerationId = 3;
    public final static String idColumnName = "container_ev_id";
    private final static ContainerHelper containerHelper 
        = ContainerHelper.getContainerHelper();
    
    private int containerEvId;
    private String name;
    private String description;
    private Integer sequence;
    private String javaClass;
    private String javaPackage;
    private String hibernate;
    private String javaImplementation;
    
    private Container(
        int containerEvId, String name, String description, Integer sequence
        , String javaClass, String javaPackage, String hibernate, String javaImplementation)
    {
        this.containerEvId = containerEvId;
        this.name = name;
        this.description = description;
        this.sequence = sequence;
        this.javaClass = javaClass;
        this.javaPackage = javaPackage;
        this.hibernate = hibernate;
        this.javaImplementation = javaImplementation;
        ContainerHelper.getContainerHelper().loadContainer(
            this, containerEvId, name, description, sequence
            , javaClass, javaPackage, hibernate, javaImplementation);
    }
    public int getContainerEvId(){return containerEvId;};
    public String getName(){return name;};
    public String getDescription(){return description;};
    public Integer getSequence(){return sequence;};
    public String getJavaClass(){return javaClass;}
    public String getJavaPackage(){return javaPackage;}
    public String getHibernate(){return hibernate;}
    public String getJavaImplementation(){return javaImplementation;}
    
    /**
     * @param id
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerById(int id){
        Set<Container> containers = containerHelper.idToContainer.get(id);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param id
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetById(int id){
        return new HashSet<Container>(containerHelper.idToContainer.get(id));
    }
    
    /**
     * @param name
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerByName(String name){
        Set<Container> containers 
            = containerHelper.nameToContainer.get(name);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param name
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetByName(String name){
        return new HashSet<Container>(containerHelper.nameToContainer.get(name));
    }
    
    /**
     * @param description
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerByDescription(String description){
        Set<Container> containers 
            = containerHelper.descriptionToContainer.get(description);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param description
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetByDescription(String description){
        return new HashSet<Container>(containerHelper.descriptionToContainer.get(description));
    }
    
    /**
     * @param sequence
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerBySequence(Integer sequence){
        Set<Container> containers 
            = containerHelper.sequenceToContainer.get(sequence);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param sequence
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetBySequence(Integer sequence){
        return new HashSet<Container>(containerHelper.sequenceToContainer.get(sequence));
    }
    /**
     * @param javaClass
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerByJavaClass(String javaClass){
        Set<Container> containers 
            = containerHelper.javaClassToContainer.get(javaClass);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("javaClass " + javaClass 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param javaClass
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetByJavaClass(String javaClass){
        return new HashSet<Container>(containerHelper.javaClassToContainer.get(javaClass));
    }
    /**
     * @param javaPackage
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerByJavaPackage(String javaPackage){
        Set<Container> containers 
            = containerHelper.javaPackageToContainer.get(javaPackage);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("javaPackage " + javaPackage 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param javaPackage
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetByJavaPackage(String javaPackage){
        return new HashSet<Container>(containerHelper.javaPackageToContainer.get(javaPackage));
    }
    /**
     * @param hibernate
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerByHibernate(String hibernate){
        Set<Container> containers 
            = containerHelper.hibernateToContainer.get(hibernate);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("hibernate " + hibernate 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param hibernate
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetByHibernate(String hibernate){
        return new HashSet<Container>(containerHelper.hibernateToContainer.get(hibernate));
    }
    /**
     * @param javaImplementation
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Container getContainerByJavaImplementation(String javaImplementation){
        Set<Container> containers 
            = containerHelper.javaImplementationToContainer.get(javaImplementation);
        if (containers == null) return null;
        if (containers.size() > 1)
            throw new EnumerationNotUnique("javaImplementation " + javaImplementation 
                + " has " + containers.size() + " elements");
        return containers.iterator().next();
    }
    
    /**
     * @param javaImplementation
     * @return Container that is mapped to by the input parameter
     */
    public static Set<Container> getContainerSetByJavaImplementation(String javaImplementation){
        return new HashSet<Container>(containerHelper.javaImplementationToContainer.get(javaImplementation));
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString(){
        String toString = "Container " + name + " (" + description + ")";
        toString += "\\n\\tcontainerEvId: " + containerEvId;
        toString += "\\n\\tsequence: " + sequence;
        toString += "\\n\\tjavaClass: " + javaClass;
        toString += "\\n\\tjavaPackage: " + javaPackage;
        toString += "\\n\\thibernate: " + hibernate;
        toString += "\\n\\tjavaImplementation: " + javaImplementation;
        return toString;
    }
}