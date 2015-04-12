/**
Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)

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
  
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.domain.system.Table
import com.tjhruska.mc.enums.Container
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=['guardedColumns'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
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
  Boolean foreignIncludeInCanonicalFlag
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