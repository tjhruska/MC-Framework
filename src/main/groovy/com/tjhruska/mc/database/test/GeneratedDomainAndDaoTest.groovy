/**
 * Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tjhruska.mc.database.test

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

import com.tjhruska.mc.database.BaseDomain
import com.tjhruska.mc.database.DaoDomain


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
abstract class GeneratedDomainAndDaoTest {

  abstract DaoDomain getDao()

  abstract BaseDomain getTestObject(Integer number, Integer sequence)

  abstract BaseDomain updateDomainObject(Integer number, BaseDomain domain)

  abstract void assertDomainUpdates(BaseDomain expected, BaseDomain actual)

  abstract void deleteObject(BaseDomain domain)

  BaseDomain persistTestObject(Integer number) {
    BaseDomain domain1 = getTestObject(number, 0)
    //assertNull(domain1.getId()) -- the class knows if this should be null or not, either way it will fail to persist if it is wrong, testing here doesn't work
    assertNull(domain1.getAddDate())

    getDao().saveOrUpdate(domain1)
    getDao().flush()
    getDao().evict(domain1)
    return domain1
  }

  @Test
  void createTest(){
    BaseDomain domain1 = persistTestObject(1)
    assertNotNull(domain1.getId())
  }

  @Test
  void readTest(){
    BaseDomain domain1 = persistTestObject(1)

    BaseDomain domain1Found = dao.findByPK(domain1.getPK())

    assertDomainUpdates(domain1, domain1Found)
  }

  @Test
  void updateTest(){
    BaseDomain domain1 = persistTestObject(1)

    BaseDomain expected = updateDomainObject(3, domain1)
    dao.saveOrUpdate(domain1)
    dao.flush()
    dao.evict(domain1)

    BaseDomain domain1Found = dao.findByPK(domain1.getPK())

    assertDomainUpdates(expected, domain1Found)
  }

  @Test
  void deleteTest(){
    BaseDomain domain1 = persistTestObject(1)

    deleteObject(domain1)

    BaseDomain domain1Deleted = dao.findByPK(domain1.getPK())
    assertNull(domain1Deleted)
  }
}