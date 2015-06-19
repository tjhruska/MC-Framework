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

import javax.validation.Valid
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty


@Canonical (excludes=['columns', 'indexes'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * A Table in our model, and the corresponding pojo, orm, and gui requirements.
 */
public class Table extends BaseDomain {


  /**
   * System Table - no schema changes.
   */
  Boolean systemNoModifyFlag

  /**
   * Table Name
   */
  @NotEmpty
  String name

  /**
   * Description of Table Usage
   */
  @NotEmpty
  String description

  /**
   * Name of "object" using ClassCamelCaseRules.
   */
  @NotEmpty
  String nameCamelCase

  /**
   * Rule to use on how to create tables.
   */
  @NotNull
  TableCreationRule tableCreationRule

  /**
   * If True don't alter data model automatically.  If false program may modify.
   */
  Boolean suppressTableChangesFlag

  /**
   * Generate Insert/Update/Deletion tracking table to track data changes, along with associated triggers.
   */
  Boolean createIUDTableFlag

  /**
   * Schema to use when generating sql for generation/altering the table.
   */
  String schemaName

  /**
   * When creating this table insert this sql after column list.
   */
  String withinCreateSQL

  /**
   * When creating the model, run this sql after all tables have been created.  (Allows for circular fks to be created after model is in place.)
   */
  String postCreateSQL

  /**
   * Generate Plain Old Java Object (POJO).
   */
  @NotNull
  Boolean createPOJOFlag

  /**
   * Root folder for java packages that will house generated pojos.
   */
  String srcFolderTag

  /**
   * If True don't create/recreate the POJO, if false rename old if exists, and create new.
   */
  Boolean suppressPOJOChangesFlag

  /**
   * Java package in which to place generated POJO.
   */
  String javaPackage

  /**
   * Fully Qualified java path to super class for POJO.
   */
  String javaFullyQualifiedSuperClass

  /**
   * Create a constructor utilizing columns as inputs, will be mared with a TODO.
   */
  Boolean constructorFromFieldsFlag

  /**
   * Add these java imports to POJO in import section.
   */
  String classImports

  /**
   * Add these java annotations to POJO.
   */
  String classAnnotationsCode

  /**
   * Add this java code to POJO after last method.
   */
  String extraJavaCode

  /**
   * Create Object Relational Mapping (ORM) files within same package as POJO.
   */
  Boolean createORMFlag

  /**
   * If True don't create/recreate the ORM file, if False rename old, and create new.
   */
  Boolean suppressORMChangesFlag

  /**
   * Extra ORM code to be added to the end of the mapping file.
   */
  String extraORMCode

  /**
   * If the POJO shall be subclassed via the mc_child table use this descriminator column to manage subclassing within mapping file.
   */
  Integer ormDiscriminatorColumnId

  /**
   * Create Graphical User Inteface methods to allow for the online editing of this object utilizing created POJO, ORM and gui library.
   */
  Boolean createGUIDataServiceFlag

  /**
   * If True don't create/recreate the GUI library for this object, if False rename old, and create new.
   */
  Boolean suppressGUIDataServiceChangesFlag

  /**
   * If true GUI library for this object should provide pagification widgets.
   */
  Boolean guiPagifyFlag

  /**
   * Requires gui_pagify_flag=true, show this many records per page.
   */
  Short guiRecordsPerPage

  /**
   * Field to use as a unique descripter for record within containers like comboboxes.
   */
  Integer guiPickerDescriptionColumnId

  /**
   * Columns (fields) that are properties of a Table, and their meta data that describe how to maintain them.
   */
  @Valid
  List<Column> columns

  /**
   * An multi-column Index for a Table.  (Single column indexes should be defined by Column.)
   */
  @Valid
  Set<Index> indexes

}