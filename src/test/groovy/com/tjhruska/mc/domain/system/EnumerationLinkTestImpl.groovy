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
import com.tjhruska.mc.domain.system.Enumeration
import com.tjhruska.mc.domain.system.EnumerationTest
import com.tjhruska.mc.enums.Datatype
import com.tjhruska.mc.enums.DbIndex
import com.tjhruska.mc.enums.LinkCreationRule
import com.tjhruska.mc.domain.system.EnumerationLinkValue
import java.util.ArrayList
import java.util.List
import com.tjhruska.mc.domain.system.EnumerationLinkValue
import com.tjhruska.mc.domain.system.EnumerationLinkValueTest
  

//    Class contains database constraints which can't be accommodated in autogenerated code.
//    To activate test: extend test class, and tweak fields with constraints to match database expectations.
//    (Extended class won't be wiped out on regeneration, and must continue to match database expectations.)
@Ignore 
class EnumerationLinkTestImpl extends GeneratedDomainAndDaoTest implements EnumerationLinkTest {
  
  @Autowired
  DaoDomain<EnumerationLink> enumerationLinkDao

  @Autowired
  @Lazy
  EnumerationTest enumerationTest

  @Autowired
  @Lazy
  EnumerationLinkValueTest enumerationLinkValueTest

  @Override
  public DaoDomain getDao() {
    enumerationLinkDao
  }

  Enumeration enumerationA
  Enumeration enumerationB

  @Override
  public BaseDomain getTestObject(Integer number, Integer sequence) {
    EnumerationLink enumerationLink = new EnumerationLink()
    
    enumerationLink.setLinkCreationRule(LinkCreationRule.getLinkCreationRuleBySequence(1))
    if (enumerationA == null) {
      enumerationLink.setEnumerationA(enumerationTest.persistTestObject(number))
      enumerationLink.enumerationA.linksAsA.add(enumerationLink)
    } else {
      enumerationLink.setEnumerationA(enumerationA)
    }
    if (enumerationB == null) {
      enumerationLink.setEnumerationB(enumerationTest.persistTestObject(number))
      enumerationLink.enumerationB.linksAsB.add(enumerationLink)
    } else {
      enumerationLink.setEnumerationB(enumerationB)
    }
    enumerationLink.setEnumerationAIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setEnumerationBIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setUniqueFlag(number == 0 || 8%number == 0)
    enumerationLink.setNotNullFlag(number == 0 || 9%number == 0)
    enumerationLink.setName("name${number}")
    enumerationLink.setTableName("tablename${number}")
    enumerationLink.setTableNameCamelCase("tablename_camel_case${number}")
    enumerationLink.setSchemaName("schema_name${number}")
    enumerationLink.setPostCreateSQL("post_create_sql${number}")
    enumerationLink.setChildTableSynchMethod(number)
    enumerationLink.setColumn1Name("column1_name${number}")
    enumerationLink.setColumn1NameCamelCase("column1_name_camel_case${number}")
    enumerationLink.setColumn1DataType(Datatype.getDatatypeBySequence(1))
    enumerationLink.setColumn1DBIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setColumn1NotNullFlag(number == 0 || 20%number == 0)
    enumerationLink.setColumn1Default("column1_default${number}")
    enumerationLink.setColumn2Name("column2_name${number}")
    enumerationLink.setColumn2NameCamelCase("column2_name_camel_case${number}")
    enumerationLink.setColumn2DataType(Datatype.getDatatypeBySequence(1))
    enumerationLink.setColumn2DBIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setColumn2NotNullFlag(number == 0 || 26%number == 0)
    enumerationLink.setColumn2Default("column2_default${number}")
    enumerationLink.setColumn3Name("column3_name${number}")
    enumerationLink.setColumn3NameCamelCase("column3_name_camel_case${number}")
    enumerationLink.setColumn3DataType(Datatype.getDatatypeBySequence(1))
    enumerationLink.setColumn3DBIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setColumn3NotNullFlag(number == 0 || 32%number == 0)
    enumerationLink.setColumn3Default("column3_default${number}")
    enumerationLink.setColumn4Name("column4_name${number}")
    enumerationLink.setColumn4NameCamelCase("column4_name_camel_case${number}")
    enumerationLink.setColumn4DataType(Datatype.getDatatypeBySequence(1))
    enumerationLink.setColumn4DBIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setColumn4NotNullFlag(number == 0 || 38%number == 0)
    enumerationLink.setColumn4Default("column4_default${number}")
    enumerationLink.setColumn5Name("column5_name${number}")
    enumerationLink.setColumn5NameCamelCase("column5_name_camel_case${number}")
    enumerationLink.setColumn5DataType(Datatype.getDatatypeBySequence(1))
    enumerationLink.setColumn5DBIndex(DbIndex.getDbIndexBySequence(1))
    enumerationLink.setColumn5NotNullFlag(number == 0 || 44%number == 0)
    enumerationLink.setColumn5Default("column5_default${number}")
    
    List<EnumerationLinkValue> linkValues = new ArrayList()
    if (enumerationLinkValueTest != null) {
      enumerationLinkValueTest.enumerationLink = enumerationLink
      (1..(number+2)).each { i ->
      linkValues.add(enumerationLinkValueTest.getTestObject(i+(10*number), i-1))
      }
    }
    enumerationLink.setLinkValues(linkValues)

    enumerationLink
  }

  @Override
  public BaseDomain updateDomainObject(Integer number, BaseDomain domain) {
    EnumerationLink source = getTestObject(number, 0)
    EnumerationLink target = (EnumerationLink)domain
    target.setLinkCreationRule(source.getLinkCreationRule())
    target.setEnumerationA(source.getEnumerationA())
    target.enumerationA.linksAsA.clear()
    target.enumerationA.linksAsA.add(target)
    target.setEnumerationB(source.getEnumerationB())
    target.enumerationB.linksAsB.clear()
    target.enumerationB.linksAsB.add(target)
    target.setEnumerationAIndex(source.getEnumerationAIndex())
    target.setEnumerationBIndex(source.getEnumerationBIndex())
    target.setUniqueFlag(source.getUniqueFlag())
    target.setNotNullFlag(source.getNotNullFlag())
    target.setName(source.getName())
    target.setTableName(source.getTableName())
    target.setTableNameCamelCase(source.getTableNameCamelCase())
    target.setSchemaName(source.getSchemaName())
    target.setPostCreateSQL(source.getPostCreateSQL())
    target.setChildTableSynchMethod(source.getChildTableSynchMethod())
    target.setColumn1Name(source.getColumn1Name())
    target.setColumn1NameCamelCase(source.getColumn1NameCamelCase())
    target.setColumn1DataType(source.getColumn1DataType())
    target.setColumn1DBIndex(source.getColumn1DBIndex())
    target.setColumn1NotNullFlag(source.getColumn1NotNullFlag())
    target.setColumn1Default(source.getColumn1Default())
    target.setColumn2Name(source.getColumn2Name())
    target.setColumn2NameCamelCase(source.getColumn2NameCamelCase())
    target.setColumn2DataType(source.getColumn2DataType())
    target.setColumn2DBIndex(source.getColumn2DBIndex())
    target.setColumn2NotNullFlag(source.getColumn2NotNullFlag())
    target.setColumn2Default(source.getColumn2Default())
    target.setColumn3Name(source.getColumn3Name())
    target.setColumn3NameCamelCase(source.getColumn3NameCamelCase())
    target.setColumn3DataType(source.getColumn3DataType())
    target.setColumn3DBIndex(source.getColumn3DBIndex())
    target.setColumn3NotNullFlag(source.getColumn3NotNullFlag())
    target.setColumn3Default(source.getColumn3Default())
    target.setColumn4Name(source.getColumn4Name())
    target.setColumn4NameCamelCase(source.getColumn4NameCamelCase())
    target.setColumn4DataType(source.getColumn4DataType())
    target.setColumn4DBIndex(source.getColumn4DBIndex())
    target.setColumn4NotNullFlag(source.getColumn4NotNullFlag())
    target.setColumn4Default(source.getColumn4Default())
    target.setColumn5Name(source.getColumn5Name())
    target.setColumn5NameCamelCase(source.getColumn5NameCamelCase())
    target.setColumn5DataType(source.getColumn5DataType())
    target.setColumn5DBIndex(source.getColumn5DBIndex())
    target.setColumn5NotNullFlag(source.getColumn5NotNullFlag())
    target.setColumn5Default(source.getColumn5Default())

    target.linkValues.each {
      enumerationLinkValueTest.getDao().delete(it)
    }
    target.linkValues.clear()
    source.linkValues.each {
      it.enumerationLink = target
    }
    target.linkValues.addAll(source.linkValues)

    return source
  }

  @Override
  public void assertDomainUpdates(BaseDomain expected, BaseDomain actual) {
    EnumerationLink expectedD = (EnumerationLink)expected
    EnumerationLink actualD = (EnumerationLink)actual
    assertEquals("linkCreationRule is different than expected", expectedD.getLinkCreationRule(), actualD.getLinkCreationRule())
    enumerationTest.assertDomainUpdates(expectedD.getEnumerationA(), actualD.getEnumerationA())
    enumerationTest.assertDomainUpdates(expectedD.getEnumerationB(), actualD.getEnumerationB())
    assertEquals("enumerationAIndex is different than expected", expectedD.getEnumerationAIndex(), actualD.getEnumerationAIndex())
    assertEquals("enumerationBIndex is different than expected", expectedD.getEnumerationBIndex(), actualD.getEnumerationBIndex())
    assertEquals("uniqueFlag is different than expected", expectedD.getUniqueFlag(), actualD.getUniqueFlag())
    assertEquals("notNullFlag is different than expected", expectedD.getNotNullFlag(), actualD.getNotNullFlag())
    assertEquals("name is different than expected", expectedD.getName(), actualD.getName())
    assertEquals("tableName is different than expected", expectedD.getTableName(), actualD.getTableName())
    assertEquals("tableNameCamelCase is different than expected", expectedD.getTableNameCamelCase(), actualD.getTableNameCamelCase())
    assertEquals("schemaName is different than expected", expectedD.getSchemaName(), actualD.getSchemaName())
    assertEquals("postCreateSQL is different than expected", expectedD.getPostCreateSQL(), actualD.getPostCreateSQL())
    assertEquals("childTableSynchMethod is different than expected", expectedD.getChildTableSynchMethod(), actualD.getChildTableSynchMethod())
    assertEquals("column1Name is different than expected", expectedD.getColumn1Name(), actualD.getColumn1Name())
    assertEquals("column1NameCamelCase is different than expected", expectedD.getColumn1NameCamelCase(), actualD.getColumn1NameCamelCase())
    assertEquals("column1DataType is different than expected", expectedD.getColumn1DataType(), actualD.getColumn1DataType())
    assertEquals("column1DBIndex is different than expected", expectedD.getColumn1DBIndex(), actualD.getColumn1DBIndex())
    assertEquals("column1NotNullFlag is different than expected", expectedD.getColumn1NotNullFlag(), actualD.getColumn1NotNullFlag())
    assertEquals("column1Default is different than expected", expectedD.getColumn1Default(), actualD.getColumn1Default())
    assertEquals("column2Name is different than expected", expectedD.getColumn2Name(), actualD.getColumn2Name())
    assertEquals("column2NameCamelCase is different than expected", expectedD.getColumn2NameCamelCase(), actualD.getColumn2NameCamelCase())
    assertEquals("column2DataType is different than expected", expectedD.getColumn2DataType(), actualD.getColumn2DataType())
    assertEquals("column2DBIndex is different than expected", expectedD.getColumn2DBIndex(), actualD.getColumn2DBIndex())
    assertEquals("column2NotNullFlag is different than expected", expectedD.getColumn2NotNullFlag(), actualD.getColumn2NotNullFlag())
    assertEquals("column2Default is different than expected", expectedD.getColumn2Default(), actualD.getColumn2Default())
    assertEquals("column3Name is different than expected", expectedD.getColumn3Name(), actualD.getColumn3Name())
    assertEquals("column3NameCamelCase is different than expected", expectedD.getColumn3NameCamelCase(), actualD.getColumn3NameCamelCase())
    assertEquals("column3DataType is different than expected", expectedD.getColumn3DataType(), actualD.getColumn3DataType())
    assertEquals("column3DBIndex is different than expected", expectedD.getColumn3DBIndex(), actualD.getColumn3DBIndex())
    assertEquals("column3NotNullFlag is different than expected", expectedD.getColumn3NotNullFlag(), actualD.getColumn3NotNullFlag())
    assertEquals("column3Default is different than expected", expectedD.getColumn3Default(), actualD.getColumn3Default())
    assertEquals("column4Name is different than expected", expectedD.getColumn4Name(), actualD.getColumn4Name())
    assertEquals("column4NameCamelCase is different than expected", expectedD.getColumn4NameCamelCase(), actualD.getColumn4NameCamelCase())
    assertEquals("column4DataType is different than expected", expectedD.getColumn4DataType(), actualD.getColumn4DataType())
    assertEquals("column4DBIndex is different than expected", expectedD.getColumn4DBIndex(), actualD.getColumn4DBIndex())
    assertEquals("column4NotNullFlag is different than expected", expectedD.getColumn4NotNullFlag(), actualD.getColumn4NotNullFlag())
    assertEquals("column4Default is different than expected", expectedD.getColumn4Default(), actualD.getColumn4Default())
    assertEquals("column5Name is different than expected", expectedD.getColumn5Name(), actualD.getColumn5Name())
    assertEquals("column5NameCamelCase is different than expected", expectedD.getColumn5NameCamelCase(), actualD.getColumn5NameCamelCase())
    assertEquals("column5DataType is different than expected", expectedD.getColumn5DataType(), actualD.getColumn5DataType())
    assertEquals("column5DBIndex is different than expected", expectedD.getColumn5DBIndex(), actualD.getColumn5DBIndex())
    assertEquals("column5NotNullFlag is different than expected", expectedD.getColumn5NotNullFlag(), actualD.getColumn5NotNullFlag())
    assertEquals("column5Default is different than expected", expectedD.getColumn5Default(), actualD.getColumn5Default())
    assertEquals("size of linkValues is different than expected", expectedD.linkValues.size(), actualD.linkValues.size())
  }
  
  @Override
  void deleteObject(BaseDomain domain) {
    if (domain == null) {
      return
    }
    EnumerationLink target = (EnumerationLink)domain

    enumerationTest.deleteObject(target.enumerationA)
    enumerationTest.deleteObject(target.enumerationB)
    enumerationLinkDao.flush()
    enumerationLinkDao.evict(target)
  }
}