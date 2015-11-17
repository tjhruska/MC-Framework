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
enum Datatype {
    BOOLEAN(1, "BOOLEAN", "Boolean", 0, "Boolean", "boolean", "boolean", "boolean"),
    INTEGER(2, "INTEGER", "Integer", 1, "Integer", "int", "integer", "integer"),
    STRING(3, "STRING", "String", 2, "String", null, "character varying", "string"),
    LONG(4, "LONG", "Long", 3, "Long", "long", "bigint", "long"),
    SHORT(44, "SHORT", "Short", 4, "Short", "short", "smallint", "short"),
    SERIAL(45, "SERIAL", "Serial", 5, "Integer", "int", "serial", "int"),
    DATETIME(46, "DATETIME", "Date Time", 6, "org.joda.time.DateTime", null, "timestamp with time zone", "org.jadira.usertype.dateandtime.joda.PersistentDateTime"),
    TIME(50, "TIME", "Time", 7, "java.sql.Time", null, "time without time zone", "time"),
    DECIMAL(51, "DECIMAL", "Decimal", 8, "java.math.BigDecimal", null, "double precision", "big_decimal"),
    INTEGER_ARRAY(52, "INTEGER_ARRAY", "Integer[]", 9, "Integer[]", "int[]", "integer[]", "com.tjhruska.mc.database.IntegerArrayUserType"),
    SHORT_ARRAY(53, "SHORT_ARRAY", "Short[]", 10, "Short[]", "short[]", "smallint[]", "com.tjhruska.mc.database.ShortArrayUserType"),
    DECIMAL_ARRAY(54, "DECIMAL_ARRAY", "Decimal[]", 11, "java.math.BigDecimal[]", null, "numeric[]", "com.tjhruska.mc.database.BigDecimalArrayUserType"),
    DOUBLE_ARRAY(55, "DOUBLE_ARRAY", "Double[]", 12, "Double[]", "double[]", "float8[]", "com.tjhruska.mc.database.DoubleArrayUserType"),
    DOUBLE(56, "DOUBLE", "Double", 13, "Double", "double", "float8", "double"),
    DATE(57, "DATE", "Local Date", 14, "org.joda.time.LocalDate", null, "timestamp without time zone", "org.jadira.usertype.dateandtime.joda.PersistentLocalDate"),
    JSON(58, "JSON", "Javascript Object Notation", 15, "java_class_dynamic", null, "json", "hibernate_dynamic"),
    JSON_MAP(59, "JSON_MAP", "Javascript Object Notation of Map", 16, "java_class_dynamic", null, "json", "hibernate_dynamic"),
    JSON_LIST(60, "JSON_LIST", "Javascript Object Notation of List", 17, "java_class_dynamic", null, "json", "hibernate_dynamic"),
    DECIMAL_12_2(61, "DECIMAL_12_2", "Decimal", 18, "java.math.BigDecimal", null, "numeric(12,2)", "big_decimal"),
    DECIMAL_12_5(62, "DECIMAL_12_5", "Decimal", 19, "java.math.BigDecimal", null, "numeric(12,5)", "big_decimal")

    final static int enumerationId = 2
    final static String idColumnName = "datatype_ev_id"
    final static DatatypeHelper datatypeHelper = DatatypeHelper.getDatatypeHelper()
    
    final int datatypeEvId
    final String name
    final String description
    final Integer sequence
    final String javaClass
    final String javaPrimitive
    final String database
    final String hibernate
    
    private Datatype(
        int datatypeEvId, String name, String description, Integer sequence
        , String javaClass, String javaPrimitive, String database, String hibernate)
    {
        this.datatypeEvId = datatypeEvId
        this.name = name
        this.description = description
        this.sequence = sequence
        this.javaClass = javaClass
        this.javaPrimitive = javaPrimitive
        this.database = database
        this.hibernate = hibernate
        DatatypeHelper.getDatatypeHelper().loadDatatype(
            this, datatypeEvId, name, description, sequence
            , javaClass, javaPrimitive, database, hibernate)
    }
    
    /**
     * @param id
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeById(int id){
        Set<Datatype> datatypes = datatypeHelper.idToDatatype.get(id)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("id " + id 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param id
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetById(int id){
        return new HashSet<Datatype>(datatypeHelper.idToDatatype.get(id))
    }
    
    /**
     * @param name
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeByName(String name){
        Set<Datatype> datatypes = datatypeHelper.nameToDatatype.get(name)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("name " + name 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param name
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetByName(String name){
        return new HashSet<Datatype>(datatypeHelper.nameToDatatype.get(name))
    }
    
    /**
     * @param description
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeByDescription(String description){
        Set<Datatype> datatypes = datatypeHelper.descriptionToDatatype.get(description)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("description " + description 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param description
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetByDescription(String description){
        return new HashSet<Datatype>(datatypeHelper.descriptionToDatatype.get(description))
    }
    
    /**
     * @param sequence
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeBySequence(Integer sequence){
        Set<Datatype> datatypes = datatypeHelper.sequenceToDatatype.get(sequence)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("sequence " + sequence 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param sequence
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetBySequence(Integer sequence){
        return new HashSet<Datatype>(datatypeHelper.sequenceToDatatype.get(sequence))
    }
    /**
     * @param javaClass
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeByJavaClass(String javaClass){
        Set<Datatype> datatypes = datatypeHelper.javaClassToDatatype.get(javaClass)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("javaClass " + javaClass 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param javaClass
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetByJavaClass(String javaClass){
        return new HashSet<Datatype>(datatypeHelper.javaClassToDatatype.get(javaClass))
    }
    /**
     * @param javaPrimitive
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeByJavaPrimitive(String javaPrimitive){
        Set<Datatype> datatypes = datatypeHelper.javaPrimitiveToDatatype.get(javaPrimitive)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("javaPrimitive " + javaPrimitive 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param javaPrimitive
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetByJavaPrimitive(String javaPrimitive){
        return new HashSet<Datatype>(datatypeHelper.javaPrimitiveToDatatype.get(javaPrimitive))
    }
    /**
     * @param database
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeByDatabase(String database){
        Set<Datatype> datatypes = datatypeHelper.databaseToDatatype.get(database)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("database " + database 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param database
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetByDatabase(String database){
        return new HashSet<Datatype>(datatypeHelper.databaseToDatatype.get(database))
    }
    /**
     * @param hibernate
     * @return Datatype that is mapped to by the input parameter
     * @throws EnumerationNotUnique if the input parameter mapps to multiple values
     */
    static Datatype getDatatypeByHibernate(String hibernate){
        Set<Datatype> datatypes = datatypeHelper.hibernateToDatatype.get(hibernate)
        if (datatypes == null) return null
        if (datatypes.size() > 1)
            throw new EnumerationNotUnique("hibernate " + hibernate 
                + " has " + datatypes.size() + " elements")
        return datatypes.iterator().next()
    }
    
    /**
     * @param hibernate
     * @return Datatype that is mapped to by the input parameter
     */
    static Set<Datatype> getDatatypeSetByHibernate(String hibernate){
        return new HashSet<Datatype>(datatypeHelper.hibernateToDatatype.get(hibernate))
    }
}