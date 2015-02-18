
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import java.util.List
import java.util.Map
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
public class JsonExample extends BaseDomain {

  List jsonFoo
  Map jsonBar

}