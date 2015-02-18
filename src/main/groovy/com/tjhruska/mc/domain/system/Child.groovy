
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
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