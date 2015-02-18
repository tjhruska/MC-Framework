
package com.tjhruska.mc.domain.system

import org.springframework.beans.factory.annotation.Autowired
import static org.junit.Assert.*
import org.junit.Before

import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.database.DaoDomain
import com.tjhruska.mc.database.test.GeneratedDomainAndDaoTest
  
class ChildTest extends GeneratedDomainAndDaoTest {
  
  @Autowired
  DaoDomain<Child> childDao

  @Override
  public DaoDomain getDao() {
    childDao
  }


  @Override
  public BaseDomain getTestObject(Integer number, Integer sequence) {
    Child child = new Child()
    
    child.setTableId(number)
    child.setParentId(number)
    child.setDescription("description${number}")
    child.setNameCamelCase("name_camel_case${number}")
    child.setOrmDiscriminatorValue("orm_discriminator_value${number}")
    child.setCreateConstraintUsingDiscriminatorFlag(8%number == 0)
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
  public void deleteChildrenIfNeeded(BaseDomain domain) {
    Child target = (Child)domain
  }
}