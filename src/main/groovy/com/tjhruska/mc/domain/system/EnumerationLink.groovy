
package com.tjhruska.mc.domain.system;


import com.tjhruska.mc.database.BaseDomain;
import com.tjhruska.mc.domain.system.Enumeration
import com.tjhruska.mc.domain.system.EnumerationLinkValue;
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import com.tjhruska.mc.enums.LinkCreationRule
import java.util.ArrayList;
import java.util.List;
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
public class EnumerationLink extends BaseDomain {

    LinkCreationRule linkCreationRule
    Enumeration enumerationA
    Enumeration enumerationB
    DbIndex enumerationAIndex
    DbIndex enumerationBIndex
    Boolean uniqueFlag
    Boolean notNullFlag
    String name
    String tableName
    String tableNameCamelCase
    String schemaName
    String postCreateSQL
    Integer childTableSynchMethod
    String column1Name
    String column1NameCamelCase
    Datatype column1DataType
    DbIndex column1DBIndex
    Boolean column1NotNullFlag
    String column1Default
    String column2Name
    String column2NameCamelCase
    Datatype column2DataType
    DbIndex column2DBIndex
    Boolean column2NotNullFlag
    String column2Default
    String column3Name
    String column3NameCamelCase
    Datatype column3DataType
    DbIndex column3DBIndex
    Boolean column3NotNullFlag
    String column3Default
    String column4Name
    String column4NameCamelCase
    Datatype column4DataType
    DbIndex column4DBIndex
    Boolean column4NotNullFlag
    String column4Default
    String column5Name
    String column5NameCamelCase
    Datatype column5DataType
    DbIndex column5DBIndex
    Boolean column5NotNullFlag
    String column5Default
    List<EnumerationLinkValue> linkValues

}