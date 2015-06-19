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

import javax.validation.Valid
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty


@Canonical (excludes=['guardedColumns'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * Columns (fields) that are properties of a Table, and their meta data that describe how to maintain them.
 */
public class Column extends BaseDomain {


  /**
   * Table to which this Column entry belongs.
   */
  @NotNull
  Table table

  /**
   * Order of fields on Table.
   */
  @NotNull
  Short sequence

  /**
   * Column Name on the Table.
   */
  @NotEmpty
  String name

  /**
   * Description of the Column for documentation purposes.
   */
  @NotEmpty
  String description

  /**
   * Name of how the column would appear in a POJO using fieldCamelCaseSyntax.
   */
  @NotEmpty
  String nameCamelCase

  /**
   * Data type of the column, within the database, java, orm, and js.
   */
  @NotNull
  Datatype datatype

  /**
   * Extra meta data to help with code creation.
   */
  String datatypeRef1

  /**
   * Extra meta data to help with code creation.
   */
  String datatypeRef2

  /**
   * Extra meta data to help with code creation.
   */
  String datatypeRef3

  /**
   * Database index to create on this column.
   */
  DbIndex dbIndex

  /**
   * This column contributes to the unique PK index for this table.
   */
  @NotNull
  Boolean primaryKeyFlag

  /**
   * This column is a foreign key to another table.
   */
  @NotNull
  Boolean foreignKeyFlag

  /**
   * Include this property in toString, hashCode, and equals methods.
   */
  @NotNull
  Boolean foreignIncludeInCanonicalFlag

  /**
   * Table to which we are linking.
   */
  Integer foreignTableId

  /**
   * Type of collection on foreign table
   */
  Container foreignPogoCollectionDatatype

  /**
   * Name of collection on foreign table
   */
  String foreignPojoCollectionNameCamelCase

  /**
   * If map/list the column on current table is the key/index
   */
  Integer foreignPojoCollectionLocalKeyColumnId

  /**
   * cascade style to use from parent side of collection
   */
  String foreignPojoCollectionORMCascade

  /**
   * Should collection on foreign table be lazy loaded
   */
  Boolean foreignOrmLazyFlag

  /**
   * Is current table linking table only? if so point to referenced table
   */
  Integer foreignKeyLinkThruToTableId

  /**
   * True on side of link that is responsible for persistance; true = local, false = foreign
   */
  Boolean foreignKeyOrmInverseFlag

  /**
   * Foreign column to use for URL filter to limit drop down list results in GUI.
   */
  String fkGuiColumnFilterForeign

  /**
   * Local Id or filter test to use for URL filter to limit drop down list results in GUI.
   */
  String fkGuiColumnFilterLocal

  /**
   * Whether nulls will be allowed for this Table.Column field.
   */
  @NotNull
  Boolean notNullFlag

  /**
   * Default value for this column
   */
  String defaultValue

  /**
   * This constraint code will be within the create table syntax during table creation.
   */
  String constraintSQL

  /**
   * Put this field into the POJO constructor list if indicated on by Table.ConstructorFromFieldFlag.
   */
  Boolean pojoConstructorFieldFlag

  /**
   * Java imports needed to support the annotations on this field.
   */
  String pojoFieldAnnotationImport

  /**
   * Java annotations for this field
   */
  String pojoFieldAnnotation

  /**
   * Name of this field that should appear in a GUI editor as either a Column Header, or Field Name.
   */
  String guiHeader

  /**
   * Helpful data that shall appear within a tooltip upon mouse hover/click for this field.
   */
  String guiToolTip

  /**
   * Whether this field can be used to sort the table in a grid view.
   */
  Boolean guiSortableFlag

  /**
   * Name of the field group that this column belongs to in editor, can be null if not part of a group.
   */
  String guiFieldGroupName

  /**
   * Sequence in which this field should appear withing group.
   */
  Short guiFieldGroupSequence

  /**
   * Column which activates/deactivates this group.  (I know this is currently not 3rd Normal Form…)
   */
  Column guiFieldGroupGuardColumn

  /**
   * If this field is Not Null when the group is active.
   */
  Boolean guiFieldGroupFieldRequiredFlag

  /**
   * Columns (fields) that are properties of a Table, and their meta data that describe how to maintain them.
   */
  @Valid
  List<Column> guardedColumns

}