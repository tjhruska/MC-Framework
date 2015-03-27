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
import com.tjhruska.mc.domain.system.EnumerationLink
import com.tjhruska.mc.domain.system.EnumerationValue
import com.tjhruska.mc.enums.AutoCreationMethod
import com.tjhruska.mc.enums.ChildTableSynchMethod
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=['enumerationValues', 'linksAsA', 'linksAsB'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
public class Enumeration extends BaseDomain {

  String name
  String tableName
  String tableNameCamelCase
  String schemaName
  String postCreateSQL
  ChildTableSynchMethod childTableSynchMethod
  AutoCreationMethod autoCreationMethod
  String folderTag
  String javaPackage
  String column1Name
  String column1NameCamelCase
  Datatype column1DataType
  DbIndex column1DbIndex
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
  List<EnumerationValue> enumerationValues
  Set<EnumerationLink> linksAsA
  Set<EnumerationLink> linksAsB

}