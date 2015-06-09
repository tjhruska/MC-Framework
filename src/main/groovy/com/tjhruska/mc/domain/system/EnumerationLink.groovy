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
import com.tjhruska.mc.domain.system.Enumeration
import com.tjhruska.mc.domain.system.EnumerationLinkValue
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import com.tjhruska.mc.enums.LinkCreationRule
import java.util.ArrayList
import java.util.List

import groovy.transform.ToString
import groovy.transform.Canonical

import javax.validation.Valid
import javax.validation.constraints.NotNull


@Canonical (excludes=['linkValues'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * A link defined between to enumerations, and potential additional field definitions for that link.
 */
public class EnumerationLink extends BaseDomain {


  /**
   *Type of link to create, and which table holds the linking column.
   */
  @NotNull
  LinkCreationRule linkCreationRule

  /**
   *Table that is part of the link.
   */
  @NotNull
  Enumeration enumerationA

  /**
   *Table that is part of the link.
   */
  @NotNull
  Enumeration enumerationB

  /**
   *Index to create on the table to speed look up on this link.
   */
  DbIndex enumerationAIndex

  /**
   *Index to create on the table to speed look up on this link.
   */
  DbIndex enumerationBIndex

  /**
   *indicates if instances of this link are unique.
   */
  @NotNull
  Boolean uniqueFlag

  /**
   *Indicates if a fk whether or not it can be null.
   */
  Boolean notNullFlag

  /**
   *Name of the link.
   */
  @NotNull
  String name

  /**
   *Name of the linking table if a table is created.
   */
  @NotNull
  String tableName

  /**
   *Name of the link if created within java.
   */
  @NotNull
  String tableNameCamelCase

  /**
   *Name of schema in database to create table.
   */
  String schemaName

  /**
   *SQL to run after table creation.
   */
  String postCreateSQL

  /**
   *Rules to follow on when to synchronize to child tables.
   */
  @NotNull
  Integer childTableSynchMethod

  /**
   *Column Name on the Table.
   */
  String column1Name

  /**
   *Field name within class/enumeration.
   */
  String column1NameCamelCase

  /**
   *Data type of the column, withing the database, java, orm, and js.
   */
  Datatype column1DataType

  /**
   *Index to create on the table to speed look up on this column.
   */
  DbIndex column1DBIndex

  /**
   *Nullness rule on this column.
   */
  Boolean column1NotNullFlag

  /**
   *Default value for this column
   */
  String column1Default

  /**
   *Column Name on the Table.
   */
  String column2Name

  /**
   *Field name within class/enumeration.
   */
  String column2NameCamelCase

  /**
   *Data type of the column, withing the database, java, orm, and js.
   */
  Datatype column2DataType

  /**
   *Index to create on the table to speed look up on this column.
   */
  DbIndex column2DBIndex

  /**
   *Nullness rule on this column.
   */
  Boolean column2NotNullFlag

  /**
   *Default value for this column
   */
  String column2Default

  /**
   *Column Name on the Table.
   */
  String column3Name

  /**
   *Field name within class/enumeration.
   */
  String column3NameCamelCase

  /**
   *Data type of the column, withing the database, java, orm, and js.
   */
  Datatype column3DataType

  /**
   *Index to create on the table to speed look up on this column.
   */
  DbIndex column3DBIndex

  /**
   *Nullness rule on this column.
   */
  Boolean column3NotNullFlag

  /**
   *Default value for this column
   */
  String column3Default

  /**
   *Column Name on the Table.
   */
  String column4Name

  /**
   *Field name within class/enumeration.
   */
  String column4NameCamelCase

  /**
   *Data type of the column, withing the database, java, orm, and js.
   */
  Datatype column4DataType

  /**
   *Index to create on the table to speed look up on this column.
   */
  DbIndex column4DBIndex

  /**
   *Nullness rule on this column.
   */
  Boolean column4NotNullFlag

  /**
   *Default value for this column
   */
  String column4Default

  /**
   *Column Name on the Table.
   */
  String column5Name

  /**
   *Field name within class/enumeration.
   */
  String column5NameCamelCase

  /**
   *Data type of the column, withing the database, java, orm, and js.
   */
  Datatype column5DataType

  /**
   *Index to create on the table to speed look up on this column.
   */
  DbIndex column5DBIndex

  /**
   *Nullness rule on this column.
   */
  Boolean column5NotNullFlag

  /**
   *Default value for this column
   */
  String column5Default

  /**
   *A entry into the set of links between two enumerations as defined by the EnumerationLink.
   */
  @Valid
  List<EnumerationLinkValue> linkValues

}