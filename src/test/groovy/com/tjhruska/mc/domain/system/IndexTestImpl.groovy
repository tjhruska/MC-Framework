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

import org.springframework.context.annotation.Lazy
import org.springframework.beans.factory.annotation.Autowired
import static org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore

import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.database.DaoDomain
import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTestIface

import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTest
import com.tjhruska.mc.domain.system.Table
import com.tjhruska.mc.domain.system.TableTest
import com.tjhruska.mc.enums.DbIndex
import java.util.ArrayList
import java.util.List
import com.tjhruska.mc.domain.system.Column
import com.tjhruska.mc.domain.system.ColumnTest
  

//    Class contains database constraints which can't be accommodated in autogenerated code.
//    To activate test: extend test class, and tweak fields with constraints to match database expectations.
//    (Extended class won't be wiped out on regeneration, and must continue to match database expectations.)
@Ignore 
class IndexTestImpl extends GeneratedDomainAndDaoTest implements IndexTest {
  
  @Autowired
  DaoDomain<Index> indexDao

  @Autowired
  @Lazy
  ColumnTest columnTest

  @Autowired
  @Lazy
  TableTest tableTest

  @Override
  public DaoDomain getDao() {
    indexDao
  }

  Table table

  @Override
  public BaseDomain getTestObject(Integer number, Integer sequence) {
    Index index = new Index()
    
    if (table == null) {
      index.setTable(tableTest.persistTestObject(number))
      index.table.indexes.add(index)
    } else {
      index.setTable(table)
    }
    index.setIndex(DbIndex.getDbIndexBySequence(1))
    index.setIndexName("index_name${number}")
    
    List<Column> columns = new ArrayList()
    if (columnTest != null) {
      columnTest.index = index
      (1..(number+2)).each { i ->
      columns.add(columnTest.getTestObject(i+(10*number), i-1))
      }
    }
    index.setColumns(columns)

    index
  }

  @Override
  public BaseDomain updateDomainObject(Integer number, BaseDomain domain) {
    Index source = getTestObject(number, 0)
    Index target = (Index)domain
    target.setTable(source.getTable())
    target.table.indexes.clear()
    target.table.indexes.add(target)
    target.setIndex(source.getIndex())
    target.setIndexName(source.getIndexName())

    target.columns.each {
      columnTest.getDao().delete(it)
    }
    target.columns.clear()
    source.columns.each {
      it.index = target
    }
    target.columns.addAll(source.columns)

    return source
  }

  @Override
  public void assertDomainUpdates(BaseDomain expected, BaseDomain actual) {
    Index expectedD = (Index)expected
    Index actualD = (Index)actual
    tableTest.assertDomainUpdates(expectedD.getTable(), actualD.getTable())
    assertEquals("index is different than expected", expectedD.getIndex(), actualD.getIndex())
    assertEquals("indexName is different than expected", expectedD.getIndexName(), actualD.getIndexName())
    assertEquals("size of columns is different than expected", expectedD.columns.size(), actualD.columns.size())
  }
  
  @Override
  void deleteObject(BaseDomain domain) {
    if (domain == null) {
      return
    }
    Index target = (Index)domain

    tableTest.deleteObject(target.table)
    target?.columns.each {
      columnTest.deleteObject(it)
    }
    target?.columns.clear()
    indexDao.flush()
    indexDao.evict(target)
  }
}