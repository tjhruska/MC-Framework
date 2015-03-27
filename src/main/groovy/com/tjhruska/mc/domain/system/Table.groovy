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
import com.tjhruska.mc.domain.system.Column
import com.tjhruska.mc.domain.system.Index
import com.tjhruska.mc.enums.TableCreationRule
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=['columns', 'indexes'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
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