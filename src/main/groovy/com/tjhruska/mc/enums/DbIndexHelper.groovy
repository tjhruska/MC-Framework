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


class DbIndexHelper implements Serializable {
    private static final long serialVersionUID = 1L
    private static final DbIndexHelper dbIndexHelper = new DbIndexHelper()
    
    protected Map<Integer,Set<DbIndex>> idToDbIndex
    protected Map<String,Set<DbIndex>> nameToDbIndex
    protected Map<String,Set<DbIndex>> descriptionToDbIndex
    protected Map<Integer,Set<DbIndex>> sequenceToDbIndex
    protected Map<String,Set<DbIndex>> databaseToDbIndex
    protected Map<String,Set<DbIndex>> uniqueStringToDbIndex
    /**
     * @return helper class used for doing lookups of DbIndex
     */
    protected static DbIndexHelper getDbIndexHelper(){
        return dbIndexHelper
    }
    
    private DbIndexHelper(){
        idToDbIndex = new HashMap<Integer,Set<DbIndex>>()
        nameToDbIndex = new HashMap<String,Set<DbIndex>>()
        descriptionToDbIndex = new HashMap<String,Set<DbIndex>>()
        sequenceToDbIndex = new HashMap<Integer,Set<DbIndex>>()
        databaseToDbIndex = new HashMap<String,Set<DbIndex>>()
        uniqueStringToDbIndex = new HashMap<String,Set<DbIndex>>()
    }
    
    protected void loadDbIndex(DbIndex dbIndex, 
        int dbIndexEvId, String name, String description,
        Integer sequence, String database, String uniqueString)
    {
        if (!idToDbIndex.containsKey(dbIndexEvId))
            idToDbIndex.put(
                dbIndexEvId, 
                new HashSet<DbIndex>(Arrays.asList(dbIndex)))
        else
            idToDbIndex.get(dbIndexEvId).add(dbIndex)
        
        if (!nameToDbIndex.containsKey(name))
            nameToDbIndex.put(
                name, 
                new HashSet<DbIndex>(Arrays.asList(dbIndex)))
        else
            nameToDbIndex.get(name).add(dbIndex)
        
        if (!descriptionToDbIndex.containsKey(description))
            descriptionToDbIndex.put(
                description, 
                new HashSet<DbIndex>(Arrays.asList(dbIndex)))
        else
            descriptionToDbIndex.get(description).add(dbIndex)
        
        if (!sequenceToDbIndex.containsKey(sequence))
            sequenceToDbIndex.put(
                sequence, 
                new HashSet<DbIndex>(Arrays.asList(dbIndex)))
        else
            sequenceToDbIndex.get(sequence).add(dbIndex)


       if (!databaseToDbIndex.containsKey(database))
            databaseToDbIndex.put(
                database, 
                new HashSet<DbIndex>(Arrays.asList(dbIndex)))
        else
            databaseToDbIndex.get(database).add(dbIndex)
       if (!uniqueStringToDbIndex.containsKey(uniqueString))
            uniqueStringToDbIndex.put(
                uniqueString, 
                new HashSet<DbIndex>(Arrays.asList(dbIndex)))
        else
            uniqueStringToDbIndex.get(uniqueString).add(dbIndex)
    }
}