
package com.tjhruska.mc.domain.system

import org.springframework.beans.factory.annotation.Autowired
import static org.junit.Assert.*
import org.junit.Before

import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.database.DaoDomain
import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTest
  
class JsonExampleTest extends GeneratedDomainAndDaoTest {
  
  @Autowired
  DaoDomain<JsonExample> jsonExampleDao

  @Override
  public DaoDomain getDao() {
    jsonExampleDao
  }


  @Override
  public BaseDomain getTestObject(Integer number, Integer sequence) {
    JsonExample jsonExample = new JsonExample()
    
    jsonExample.setJsonFoo(new java.util.ArrayList())
    jsonExample.setJsonBar(new java.util.HashMap())
    
    jsonExample
  }

  @Override
  public BaseDomain updateDomainObject(Integer number, BaseDomain domain) {
    JsonExample source = getTestObject(number, 0)
    JsonExample target = (JsonExample)domain
    target.setJsonFoo(source.getJsonFoo())
    target.setJsonBar(source.getJsonBar())

    return source
  }

  @Override
  public void assertDomainUpdates(BaseDomain expected, BaseDomain actual) {
    JsonExample expectedD = (JsonExample)expected
    JsonExample actualD = (JsonExample)actual
    assertEquals("jsonFoo is different than expected", expectedD.getJsonFoo(), actualD.getJsonFoo())
    assertEquals("jsonBar is different than expected", expectedD.getJsonBar(), actualD.getJsonBar())
  }

  @Override
  public void deleteChildrenIfNeeded(BaseDomain domain) {
    JsonExample target = (JsonExample)domain
  }
}