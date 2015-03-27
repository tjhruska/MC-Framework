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
enum Container {
    SOLO_ONE(5, "SOLO_ONE", "Solo<ONE>", 0, "Solo", "com.tjhruska.mc.util", null, null),
    DUO_ONE_TWO(6, "DUO_ONE_TWO", "Duo<ONE,TWO>", 1, "Duo", "com.tjhruska.mc.util", null, null),
    TRIO_ONE_TWO_THREE(7, "TRIO_ONE_TWO_THREE", "Trio<ONE,TWO,THREE>", 2, "Trio", "com.tjhruska.mc.util", null, null),
    QUARTET_ONE_TWO_THREE_FOUR(8, "QUARTET_ONE_TWO_THREE_FOUR", "Quartet<ONE,TWO,THREE,FOUR>", 3, "Quartet", "com.tjhruska.mc.util", null, null),
    SET_T(9, "SET_T", "Set<T>", 4, "Set", "java.util", null, "HashSet"),
    LIST_T(10, "LIST_T", "List<T>", 5, "List", "java.util", null, "ArrayList"),
    MAP_K_V(11, "MAP_K_V", "Map<K,V>", 6, "Map", "java.util", null, "HashMap"),
    MAP_K_SET_T(12, "MAP_K_SET_T", "Map<K,Set<T>>", 7, "Map", "java.util", null, null),
    MAP_K_LIST_T(13, "MAP_K_LIST_T", "Map<K,List<T>>", 8, "Map", "java.util", null, null),
    MAP_K_MAP_K_V(14, "MAP_K_MAP_K_V", "Map<K,Map<K,V>>", 9, "Map", "java.util", null, null)

    final static int enumerationId = 3
    final static String idColumnName = "container_ev_id"
    final static ContainerHelper containerHelper = ContainerHelper.getContainerHelper()
    
    final int containerEvId
    final String name
    final String description
    final Integer sequence
    final String javaClass
    final String javaPackage
    final String hibernate
    final String javaImplementation
    
    private Container(
        int containerEvId, String name, String description, Integer sequence
        , String javaClass, String javaPackage, String hibernate, String javaImplementation)
    {
        this.containerEvId = containerEvId
        this.name = name
        this.description = description
        this.sequence = sequence
        this.javaClass = javaClass
        this.javaPackage = javaPackage
        this.hibernate = hibernate
        this.javaImplementation = javaImplementation
        ContainerHelper.getContainerHelper().loadContainer(
            this, containerEvId, name, description, sequence
            , javaClass, javaPackage, hibernate, javaImplementation)
    }
    
    /**
     * @param id
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerById(int id){
        Set<Container> containers = containerHelper.idToContainer.get(id)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param id
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetById(int id){
        return new HashSet<Container>(containerHelper.idToContainer.get(id))
    }
    
    /**
     * @param name
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerByName(String name){
        Set<Container> containers = containerHelper.nameToContainer.get(name)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param name
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetByName(String name){
        return new HashSet<Container>(containerHelper.nameToContainer.get(name))
    }
    
    /**
     * @param description
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerByDescription(String description){
        Set<Container> containers = containerHelper.descriptionToContainer.get(description)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param description
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetByDescription(String description){
        return new HashSet<Container>(containerHelper.descriptionToContainer.get(description))
    }
    
    /**
     * @param sequence
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerBySequence(Integer sequence){
        Set<Container> containers = containerHelper.sequenceToContainer.get(sequence)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param sequence
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetBySequence(Integer sequence){
        return new HashSet<Container>(containerHelper.sequenceToContainer.get(sequence))
    }
    /**
     * @param javaClass
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerByJavaClass(String javaClass){
        Set<Container> containers = containerHelper.javaClassToContainer.get(javaClass)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("javaClass " + javaClass 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param javaClass
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetByJavaClass(String javaClass){
        return new HashSet<Container>(containerHelper.javaClassToContainer.get(javaClass))
    }
    /**
     * @param javaPackage
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerByJavaPackage(String javaPackage){
        Set<Container> containers = containerHelper.javaPackageToContainer.get(javaPackage)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("javaPackage " + javaPackage 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param javaPackage
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetByJavaPackage(String javaPackage){
        return new HashSet<Container>(containerHelper.javaPackageToContainer.get(javaPackage))
    }
    /**
     * @param hibernate
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerByHibernate(String hibernate){
        Set<Container> containers = containerHelper.hibernateToContainer.get(hibernate)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("hibernate " + hibernate 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param hibernate
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetByHibernate(String hibernate){
        return new HashSet<Container>(containerHelper.hibernateToContainer.get(hibernate))
    }
    /**
     * @param javaImplementation
     * @return Container that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Container getContainerByJavaImplementation(String javaImplementation){
        Set<Container> containers = containerHelper.javaImplementationToContainer.get(javaImplementation)
        if (containers == null) return null
        if (containers.size() > 1)
            throw new EnumerationNotUnique("javaImplementation " + javaImplementation 
                + " has " + containers.size() + " elements")
        return containers.iterator().next()
    }
    
    /**
     * @param javaImplementation
     * @return Container that is mapped to by the input parameter
     */
    static Set<Container> getContainerSetByJavaImplementation(String javaImplementation){
        return new HashSet<Container>(containerHelper.javaImplementationToContainer.get(javaImplementation))
    }
}