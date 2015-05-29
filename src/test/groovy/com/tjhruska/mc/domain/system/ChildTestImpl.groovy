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

import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.database.DaoDomain
import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTestIface

import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTest
  

class ChildTestImpl extends GeneratedDomainAndDaoTest implements ChildTest {
  
  @Autowired
  DaoDomain<Child> childDao

  @Override
  public DaoDomain getDao() {
    childDao
  }


  @Override
  public BaseDomain getTestObject(Integer number, Integer sequence, Boolean addChildrenFlag) {
    Child child = new Child()
    
    child.setTableId(number)
    child.setParentId(number)
    child.setDescription("description${number}")
    child.setNameCamelCase("name_camel_case${number}")
    child.setOrmDiscriminatorValue("orm_discriminator_value${number}")
    child.setCreateConstraintUsingDiscriminatorFlag(number == 0 || 8%number == 0)
    child.setExtraJavaCode("extra_java_code${number}")
    child.setExtraORMCode("extra_orm_code${number}")
    
    child
  }

  @Override
  public BaseDomain updateDomainObject(Integer number, BaseDomain domain) {
    Child source = getTestObject(number, 0)
    Child target = (Child)domain
    target.setTableId(source.getTableId())
    target.setParentId(source.getParentId())
    target.setDescription(source.getDescription())
    target.setNameCamelCase(source.getNameCamelCase())
    target.setOrmDiscriminatorValue(source.getOrmDiscriminatorValue())
    target.setCreateConstraintUsingDiscriminatorFlag(source.getCreateConstraintUsingDiscriminatorFlag())
    target.setExtraJavaCode(source.getExtraJavaCode())
    target.setExtraORMCode(source.getExtraORMCode())

    return source
  }

  @Override
  public void assertDomainUpdates(BaseDomain expected, BaseDomain actual) {
    Child expectedD = (Child)expected
    Child actualD = (Child)actual
    assertEquals("table is different than expected", expectedD.getTableId(), actualD.getTableId())
    assertEquals("parent is different than expected", expectedD.getParentId(), actualD.getParentId())
    assertEquals("description is different than expected", expectedD.getDescription(), actualD.getDescription())
    assertEquals("nameCamelCase is different than expected", expectedD.getNameCamelCase(), actualD.getNameCamelCase())
    assertEquals("ormDiscriminatorValue is different than expected", expectedD.getOrmDiscriminatorValue(), actualD.getOrmDiscriminatorValue())
    assertEquals("createConstraintUsingDiscriminatorFlag is different than expected", expectedD.getCreateConstraintUsingDiscriminatorFlag(), actualD.getCreateConstraintUsingDiscriminatorFlag())
    assertEquals("extraJavaCode is different than expected", expectedD.getExtraJavaCode(), actualD.getExtraJavaCode())
    assertEquals("extraORMCode is different than expected", expectedD.getExtraORMCode(), actualD.getExtraORMCode())
  }
  
  @Override
  void deleteObject(BaseDomain domain) {
    if (domain == null) {
      return
    }
    Child target = (Child)domain

    childDao.delete(target)
    childDao.flush()
    childDao.evict(target)
  }
}