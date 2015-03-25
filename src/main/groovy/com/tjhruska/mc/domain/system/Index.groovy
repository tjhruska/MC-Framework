
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.domain.system.Table
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List

import groovy.transform.ToString
import groovy.transform.Canonical


@Canonical (excludes=['columns'])
@ToString (includeNames = true, includeSuper=true, includePackage=false)
public class Index extends BaseDomain {

  Table table
  DbIndex index
  String indexName
  List<Column> columns

}