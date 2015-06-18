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
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List

import groovy.transform.ToString
import groovy.transform.Canonical

import javax.validation.Valid
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.NotEmpty


@Canonical (excludes=['columns'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * An multi-column Index for a Table.  (Single column indexes should be defined by Column.)
 */
public class Index extends BaseDomain {


  /**
   * Table/POJO on which to create the index/lookup.
   */
  @NotNull
  Table table

  /**
   * Type of Index.
   */
  @NotNull
  DbIndex index

  /**
   * Name for the index.
   */
  String indexName

  /**
   * Columns (fields) that are properties of a Table, and their meta data that describe how to maintain them.
   */
  @Valid
  List<Column> columns

}