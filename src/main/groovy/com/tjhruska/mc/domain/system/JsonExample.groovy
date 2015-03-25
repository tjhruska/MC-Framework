
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import java.util.List
import java.util.Map

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=[])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
public class JsonExample extends BaseDomain {

  List jsonFoo
  Map jsonBar

}