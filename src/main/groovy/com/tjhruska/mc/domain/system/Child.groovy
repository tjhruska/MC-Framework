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

import groovy.transform.ToString
import groovy.transform.Canonical

import javax.validation.Valid
import javax.validation.constraints.NotNull


@Canonical (excludes=[])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * Child of a Table, and the discriminators and subclass definitions.
 */
public class Child extends BaseDomain {


  /**
   * Original ancestor in which this child is inherited from.  (Always filled.)
   */
  @NotNull
  Integer tableId

  /**
   * Immediate parent, if not the original ancestor.  (Don't fill this in on first gen children.)
   */
  Integer parentId

  /**
   * Child table/POJO which can add columns/indexes.
   */
  @NotNull
  String description

  /**
   * Name of "object" using ClassCamelCaseRules.
   */
  @NotNull
  String nameCamelCase

  /**
   * Used to map records to subclasses within mapping file.
   */
  @NotNull
  String ormDiscriminatorValue

  /**
   * Create constraint on table to limit fields to match introduction by subclass.
   */
  @NotNull
  Boolean createConstraintUsingDiscriminatorFlag

  /**
   * Add this java code to POJO after last method.
   */
  String extraJavaCode

  /**
   * Extra ORM code to be added to the end of the mapping file.
   */
  String extraORMCode

}