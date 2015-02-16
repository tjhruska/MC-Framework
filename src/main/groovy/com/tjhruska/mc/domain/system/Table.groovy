
package com.tjhruska.mc.domain.system;


import com.tjhruska.mc.database.BaseDomain;
import com.tjhruska.mc.domain.system.Column;
import com.tjhruska.mc.domain.system.Index;
import com.tjhruska.mc.enums.TableCreationRule
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
public class Table extends BaseDomain {

    Boolean systemNoModifyFlag
    String name
    String description
    String nameCamelCase
    TableCreationRule tableCreationRule
    Boolean suppressTableChangesFlag
    Boolean createIUDTableFlag
    String schemaName
    String withinCreateSQL
    String postCreateSQL
    Boolean createPOJOFlag
    String srcFolderTag
    Boolean suppressPOJOChangesFlag
    String javaPackage
    String javaFullyQualifiedSuperClass
    Boolean constructorFromFieldsFlag
    String extraJavaCode
    Boolean createORMFlag
    Boolean suppressORMChangesFlag
    String extraORMCode
    Integer ormDiscriminatorColumnId
    Boolean createGUIDataServiceFlag
    Boolean suppressGUIDataServiceChangesFlag
    Boolean guiPagifyFlag
    Short guiRecordsPerPage
    Integer guiPickerDescriptionColumnId
    List<Column> columns
    Set<Index> indexes

}