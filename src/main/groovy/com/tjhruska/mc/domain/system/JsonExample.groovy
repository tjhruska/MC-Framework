/**
Copyright 2015-2015 Timothy James Hruska (tjhruska@yahoo.com)

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
import java.util.List
import java.util.Map

import groovy.transform.ToString
import groovy.transform.Canonical

import javax.validation.Valid
import javax.validation.constraints.NotNull


@Canonical (excludes=[])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
/**
 * Test table for json columns
 */
public class JsonExample extends BaseDomain {


  /**
   *Json Field 1
   */
  @Valid
  List jsonFoo

  /**
   *Json Field 2
   */
  @NotNull
  @Valid
  Map<String,Integer> jsonBar

}