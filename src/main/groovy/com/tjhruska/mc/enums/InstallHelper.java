/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   InstallHelper.java
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
public class InstallHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final InstallHelper installHelper = new InstallHelper();
    
    protected Map<Integer,Set<Install>> idToInstall;
    protected Map<String,Set<Install>> nameToInstall;
    protected Map<String,Set<Install>> descriptionToInstall;
    protected Map<Integer,Set<Install>> sequenceToInstall;
    /**
     * @return helper class used for doing lookups of Install
     */
    protected static InstallHelper getInstallHelper(){
        return installHelper;
    }
    
    private InstallHelper(){
        idToInstall = new HashMap<Integer,Set<Install>>();
        nameToInstall = new HashMap<String,Set<Install>>();
        descriptionToInstall = new HashMap<String,Set<Install>>();
        sequenceToInstall = new HashMap<Integer,Set<Install>>();
    }
    
    protected void loadInstall(Install install, 
        int installEvId, String name, String description,
        Integer sequence)
    {
        if (!idToInstall.containsKey(installEvId))
            idToInstall.put(
                installEvId, 
                new HashSet<Install>(Arrays.asList(install)));
        else
            idToInstall.get(installEvId).add(install);
        
        if (!nameToInstall.containsKey(name))
            nameToInstall.put(
                name, 
                new HashSet<Install>(Arrays.asList(install)));
        else
            nameToInstall.get(name).add(install);
        
        if (!descriptionToInstall.containsKey(description))
            descriptionToInstall.put(
                description, 
                new HashSet<Install>(Arrays.asList(install)));
        else
            descriptionToInstall.get(description).add(install);
        
        if (!sequenceToInstall.containsKey(sequence))
            sequenceToInstall.put(
                sequence, 
                new HashSet<Install>(Arrays.asList(install)));
        else
            sequenceToInstall.get(sequence).add(install);


    }
}