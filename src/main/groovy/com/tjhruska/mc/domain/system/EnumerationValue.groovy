
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.domain.system.Enumeration

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=[])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
public class EnumerationValue extends BaseDomain {

  Enumeration enumeration
  String name
  String description
  Short sequence
  String column1Value
  String column2Value
  String column3Value
  String column4Value
  String column5Value

}