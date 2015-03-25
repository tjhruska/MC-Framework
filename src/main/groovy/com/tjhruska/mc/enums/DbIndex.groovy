/**
 *   Copyright (c) 2007-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.mc.enums
 *   DbIndex.groovy
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

import groovy.transform.ToString

import java.util.HashSet
import java.util.Set

import com.tjhruska.mc.enums.EnumerationNotUnique


/**
 * @author tjhruska
 *
 */
 
@ToString (includeNames = true, includePackage=false, includeFields=true)
enum DbIndex {
    NO_INDEX(25, "NO_INDEX", "No Index", 1, null, null),
    HASH(26, "HASH", "Hash", 2, "hash", null),
    BTREE_UNIQUE(27, "BTREE_UNIQUE", "B-Tree Unique", 3, "btree", "unique"),
    BTREE_NON_UNIQUE(28, "BTREE_NON_UNIQUE", "B-Tree Non Unique", 4, "btree", null)

    final static int enumerationId = 6
    final static String idColumnName = "dbIndex_ev_id"
    final static DbIndexHelper dbIndexHelper = DbIndexHelper.getDbIndexHelper()
    
    final int dbIndexEvId
    final String name
    final String description
    final Integer sequence
    final String database
    final String uniqueString
    
    private DbIndex(
        int dbIndexEvId, String name, String description, Integer sequence
        , String database, String uniqueString)
    {
        this.dbIndexEvId = dbIndexEvId
        this.name = name
        this.description = description
        this.sequence = sequence
        this.database = database
        this.uniqueString = uniqueString
        DbIndexHelper.getDbIndexHelper().loadDbIndex(
            this, dbIndexEvId, name, description, sequence
            , database, uniqueString)
    }
    
    /**
     * @param id
     * @return DbIndex that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static DbIndex getDbIndexById(int id){
        Set<DbIndex> dbIndexs = dbIndexHelper.idToDbIndex.get(id)
        if (dbIndexs == null) return null
        if (dbIndexs.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + dbIndexs.size() + " elements")
        return dbIndexs.iterator().next()
    }
    
    /**
     * @param id
     * @return DbIndex that is mapped to by the input parameter
     */
    static Set<DbIndex> getDbIndexSetById(int id){
        return new HashSet<DbIndex>(dbIndexHelper.idToDbIndex.get(id))
    }
    
    /**
     * @param name
     * @return DbIndex that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static DbIndex getDbIndexByName(String name){
        Set<DbIndex> dbIndexs = dbIndexHelper.nameToDbIndex.get(name)
        if (dbIndexs == null) return null
        if (dbIndexs.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + dbIndexs.size() + " elements")
        return dbIndexs.iterator().next()
    }
    
    /**
     * @param name
     * @return DbIndex that is mapped to by the input parameter
     */
    static Set<DbIndex> getDbIndexSetByName(String name){
        return new HashSet<DbIndex>(dbIndexHelper.nameToDbIndex.get(name))
    }
    
    /**
     * @param description
     * @return DbIndex that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static DbIndex getDbIndexByDescription(String description){
        Set<DbIndex> dbIndexs = dbIndexHelper.descriptionToDbIndex.get(description)
        if (dbIndexs == null) return null
        if (dbIndexs.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + dbIndexs.size() + " elements")
        return dbIndexs.iterator().next()
    }
    
    /**
     * @param description
     * @return DbIndex that is mapped to by the input parameter
     */
    static Set<DbIndex> getDbIndexSetByDescription(String description){
        return new HashSet<DbIndex>(dbIndexHelper.descriptionToDbIndex.get(description))
    }
    
    /**
     * @param sequence
     * @return DbIndex that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static DbIndex getDbIndexBySequence(Integer sequence){
        Set<DbIndex> dbIndexs = dbIndexHelper.sequenceToDbIndex.get(sequence)
        if (dbIndexs == null) return null
        if (dbIndexs.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + dbIndexs.size() + " elements")
        return dbIndexs.iterator().next()
    }
    
    /**
     * @param sequence
     * @return DbIndex that is mapped to by the input parameter
     */
    static Set<DbIndex> getDbIndexSetBySequence(Integer sequence){
        return new HashSet<DbIndex>(dbIndexHelper.sequenceToDbIndex.get(sequence))
    }
    /**
     * @param database
     * @return DbIndex that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static DbIndex getDbIndexByDatabase(String database){
        Set<DbIndex> dbIndexs = dbIndexHelper.databaseToDbIndex.get(database)
        if (dbIndexs == null) return null
        if (dbIndexs.size() > 1)
            throw new EnumerationNotUnique("database " + database 
                + " has " + dbIndexs.size() + " elements")
        return dbIndexs.iterator().next()
    }
    
    /**
     * @param database
     * @return DbIndex that is mapped to by the input parameter
     */
    static Set<DbIndex> getDbIndexSetByDatabase(String database){
        return new HashSet<DbIndex>(dbIndexHelper.databaseToDbIndex.get(database))
    }
    /**
     * @param uniqueString
     * @return DbIndex that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static DbIndex getDbIndexByUniqueString(String uniqueString){
        Set<DbIndex> dbIndexs = dbIndexHelper.uniqueStringToDbIndex.get(uniqueString)
        if (dbIndexs == null) return null
        if (dbIndexs.size() > 1)
            throw new EnumerationNotUnique("uniqueString " + uniqueString 
                + " has " + dbIndexs.size() + " elements")
        return dbIndexs.iterator().next()
    }
    
    /**
     * @param uniqueString
     * @return DbIndex that is mapped to by the input parameter
     */
    static Set<DbIndex> getDbIndexSetByUniqueString(String uniqueString){
        return new HashSet<DbIndex>(dbIndexHelper.uniqueStringToDbIndex.get(uniqueString))
    }
}