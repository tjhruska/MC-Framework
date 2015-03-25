
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=[])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
public class Child extends BaseDomain {

  Integer tableId
  Integer parentId
  String description
  String nameCamelCase
  String ormDiscriminatorValue
  Boolean createConstraintUsingDiscriminatorFlag
  String extraJavaCode
  String extraORMCode

}