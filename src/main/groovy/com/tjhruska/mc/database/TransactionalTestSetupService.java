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

package com.tjhruska.mc.database;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjhruska.mc.database.restrictions.Restriction;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TransactionalTestSetupService implements TransactionalTestSetupServiceIface {

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#save(com.tjhruska
   * .mc.database.DaoDomain, com.tjhruska.mc.database.BaseDomain)
   */
  @Override
  @Transactional
  public void save(DaoDomain dao, BaseDomain domain) {
    dao.save(domain);
    dao.flush();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#saveOrUpdate
   * (com.tjhruska.mc.database.DaoDomain, com.tjhruska.mc.database.BaseDomain)
   */
  @Override
  @Transactional
  public void saveOrUpdate(DaoDomain dao, BaseDomain domain) {
    dao.saveOrUpdate(domain);
    dao.flush();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#findByPK(com
   * .tjhruska.mc.database.DaoDomain, java.io.Serializable)
   */
  @Override
  @Transactional
  public BaseDomain findByPK(DaoDomain dao, Serializable id) {
    return dao.findByPK(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#findByCriteria
   * (com.tjhruska.mc.database.DaoDomain,
   * com.tjhruska.mc.database.restrictions.Restriction)
   */
  @Override
  public List<BaseDomain> findByCriteria(DaoDomain dao, Restriction restriction) {
    return dao.findByCriteria(restriction);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#findOneByCriteria
   * (com.tjhruska.mc.database.DaoDomain,
   * com.tjhruska.mc.database.restrictions.Restriction)
   */
  @Override
  @Transactional
  public BaseDomain findOneByCriteria(DaoDomain dao, Restriction restriction) {
    return dao.findOneByCriteria(restriction);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.tjhruska.mc.database.TransactionalTestSetupServiceIface#delete(com.
   * tjhruska.mc.database.DaoDomain, com.tjhruska.mc.database.BaseDomain)
   */
  @Override
  @Transactional
  public void delete(DaoDomain dao, BaseDomain domain) {
    dao.delete(domain);
  }
}
