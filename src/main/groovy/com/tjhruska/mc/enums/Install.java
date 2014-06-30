/**
 *   Copyright  2007-2011 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   Install.java
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
public enum Install {
    INSTALL_01_00_ENUM_MODEL(1001, "INSTALL_01_00_ENUM_MODEL", "install_01.00_enum_model", 1),
    INSTALL_02_00_MC_MODEL(1002, "INSTALL_02_00_MC_MODEL", "install_02.00_mc_model", 2),
    INSTALL_02_01_MC_TABLE_INS(1003, "INSTALL_02_01_MC_TABLE_INS", "install_02.01_mc_table_ins", 3),
    INSTALL_02_02_MC_COL_INS(1004, "INSTALL_02_02_MC_COL_INS", "install_02.02_mc_col_ins", 4),
    INSTALL_02_03_UPDATE_ENUM_MODEL(1005, "INSTALL_02_03_UPDATE_ENUM_MODEL", "install_02.03_update_enum_model", 5);

    public final static int enumerationId = 1;
    public final static String idColumnName = "install_ev_id";
    private final static InstallHelper installHelper 
        = InstallHelper.getInstallHelper();
    
    private int installEvId;
    private String name;
    private String description;
    private Integer sequence;
    
    private Install(
        int installEvId, String name, String description, Integer sequence
        )
    {
        this.installEvId = installEvId;
        this.name = name;
        this.description = description;
        this.sequence = sequence;
        InstallHelper.getInstallHelper().loadInstall(
            this, installEvId, name, description, sequence
            );
    }
    public int getInstallEvId(){return installEvId;};
    public String getName(){return name;};
    public String getDescription(){return description;};
    public Integer getSequence(){return sequence;};
    
    /**
     * @param id
     * @return Install that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Install getInstallById(int id){
        Set<Install> installs = installHelper.idToInstall.get(id);
        if (installs == null) return null;
        if (installs.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + installs.size() + " elements");
        return installs.iterator().next();
    }
    
    /**
     * @param id
     * @return Install that is mapped to by the input parameter
     */
    public static Set<Install> getInstallSetById(int id){
        return new HashSet<Install>(installHelper.idToInstall.get(id));
    }
    
    /**
     * @param name
     * @return Install that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Install getInstallByName(String name){
        Set<Install> installs 
            = installHelper.nameToInstall.get(name);
        if (installs == null) return null;
        if (installs.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + installs.size() + " elements");
        return installs.iterator().next();
    }
    
    /**
     * @param name
     * @return Install that is mapped to by the input parameter
     */
    public static Set<Install> getInstallSetByName(String name){
        return new HashSet<Install>(installHelper.nameToInstall.get(name));
    }
    
    /**
     * @param description
     * @return Install that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Install getInstallByDescription(String description){
        Set<Install> installs 
            = installHelper.descriptionToInstall.get(description);
        if (installs == null) return null;
        if (installs.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + installs.size() + " elements");
        return installs.iterator().next();
    }
    
    /**
     * @param description
     * @return Install that is mapped to by the input parameter
     */
    public static Set<Install> getInstallSetByDescription(String description){
        return new HashSet<Install>(installHelper.descriptionToInstall.get(description));
    }
    
    /**
     * @param sequence
     * @return Install that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    public static Install getInstallBySequence(Integer sequence){
        Set<Install> installs 
            = installHelper.sequenceToInstall.get(sequence);
        if (installs == null) return null;
        if (installs.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + installs.size() + " elements");
        return installs.iterator().next();
    }
    
    /**
     * @param sequence
     * @return Install that is mapped to by the input parameter
     */
    public static Set<Install> getInstallSetBySequence(Integer sequence){
        return new HashSet<Install>(installHelper.sequenceToInstall.get(sequence));
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString(){
        String toString = "Install " + name + " (" + description + ")";
        toString += "\\n\\tinstallEvId: " + installEvId;
        toString += "\\n\\tsequence: " + sequence;
        return toString;
    }
}