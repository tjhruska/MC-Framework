
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.domain.system.Table
import com.tjhruska.mc.enums.Container
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
public class Column extends BaseDomain {

  Table table
  Short sequence
  String name
  String description
  String nameCamelCase
  Datatype datatype
  String datatypeRef1
  String datatypeRef2
  String datatypeRef3
  DbIndex dbIndex
  Boolean primaryKeyFlag
  Boolean foreignKeyFlag
  Integer foreignTableId
  Container foreignPogoCollectionDatatype
  String foreignPojoCollectionNameCamelCase
  Integer foreignPojoCollectionLocalKeyColumnId
  Boolean foreignOrmLazyFlag
  Integer foreignKeyLinkThruToTableId
  Boolean foreignKeyOrmInverseFlag
  String fkGuiColumnFilterForeign
  String fkGuiColumnFilterLocal
  Boolean notNullFlag
  String defaultValue
  String constraintSQL
  Boolean pojoConstructorFieldFlag
  String guiHeader
  String guiToolTip
  Boolean guiSortableFlag
  String guiFieldGroupName
  Short guiFieldGroupSequence
  Column guiFieldGroupGuardColumn
  Boolean guiFieldGroupFieldRequiredFlag
  List<Column> guardedColumns

}