/**
 *   Copyright (c) 2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   ContainerHelper.java
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
public class ContainerHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final ContainerHelper containerHelper = new ContainerHelper();
    
    protected Map<Integer,Set<Container>> idToContainer;
    protected Map<String,Set<Container>> nameToContainer;
    protected Map<String,Set<Container>> descriptionToContainer;
    protected Map<Integer,Set<Container>> sequenceToContainer;
    protected Map<String,Set<Container>> javaClassToContainer;
    protected Map<String,Set<Container>> javaPackageToContainer;
    protected Map<String,Set<Container>> hibernateToContainer;
    protected Map<String,Set<Container>> javaImplementationToContainer;
    /**
     * @return helper class used for doing lookups of Container
     */
    protected static ContainerHelper getContainerHelper(){
        return containerHelper;
    }
    
    private ContainerHelper(){
        idToContainer = new HashMap<Integer,Set<Container>>();
        nameToContainer = new HashMap<String,Set<Container>>();
        descriptionToContainer = new HashMap<String,Set<Container>>();
        sequenceToContainer = new HashMap<Integer,Set<Container>>();
        javaClassToContainer = new HashMap<String,Set<Container>>();
        javaPackageToContainer = new HashMap<String,Set<Container>>();
        hibernateToContainer = new HashMap<String,Set<Container>>();
        javaImplementationToContainer = new HashMap<String,Set<Container>>();
    }
    
    protected void loadContainer(Container container, 
        int containerEvId, String name, String description,
        Integer sequence, String javaClass, String javaPackage, String hibernate, String javaImplementation)
    {
        if (!idToContainer.containsKey(containerEvId))
            idToContainer.put(
                containerEvId, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            idToContainer.get(containerEvId).add(container);
        
        if (!nameToContainer.containsKey(name))
            nameToContainer.put(
                name, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            nameToContainer.get(name).add(container);
        
        if (!descriptionToContainer.containsKey(description))
            descriptionToContainer.put(
                description, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            descriptionToContainer.get(description).add(container);
        
        if (!sequenceToContainer.containsKey(sequence))
            sequenceToContainer.put(
                sequence, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            sequenceToContainer.get(sequence).add(container);

       if (!javaClassToContainer.containsKey(javaClass))
            javaClassToContainer.put(
                javaClass, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            javaClassToContainer.get(javaClass).add(container);       if (!javaPackageToContainer.containsKey(javaPackage))
            javaPackageToContainer.put(
                javaPackage, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            javaPackageToContainer.get(javaPackage).add(container);       if (!hibernateToContainer.containsKey(hibernate))
            hibernateToContainer.put(
                hibernate, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            hibernateToContainer.get(hibernate).add(container);       if (!javaImplementationToContainer.containsKey(javaImplementation))
            javaImplementationToContainer.put(
                javaImplementation, 
                new HashSet<Container>(Arrays.asList(container)));
        else
            javaImplementationToContainer.get(javaImplementation).add(container);
    }
}