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

import groovy.transform.ToString
import groovy.transform.Canonical

import javax.validation.Valid
import javax.validation.constraints.NotNull


@Canonical (excludes=[])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * A entry into the set of links between two enumerations as defined by the EnumerationLink.
 */
public class EnumerationLinkValue extends BaseDomain {


  /**
   *Link back to parent enumeration link definition.
   */
  @NotNull
  EnumerationLink enumerationLink

  /**
   *Instance of an enumeration value being linked to another enumeration value.
   */
  @NotNull
  Integer enumerationValueAId

  /**
   *Instance of an enumeration value being linked to another enumeration value.
   */
  @NotNull
  Integer enumerationValueBId

  /**
   *Name giving to this instance of the linking
   */
  @NotNull
  String name

  /**
   *Description of this link
   */
  @NotNull
  String description

  /**
   *Sequence for use in collections if needed.
   */
  Short sequence

  /**
   *Value for column/field.
   */
  String column1Value

  /**
   *Value for column/field.
   */
  String column2Value

  /**
   *Value for column/field.
   */
  String column3Value

  /**
   *Value for column/field.
   */
  String column4Value

  /**
   *Value for column/field.
   */
  String column5Value

}