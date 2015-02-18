
package com.tjhruska.mc.domain.system


import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.domain.system.Table
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List
//import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

//@EqualsAndHashCode
@ToString
public class Index extends BaseDomain {

  Table table
  DbIndex index
  String indexName
  List<Column> columns

}