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

package com.tjhruska.mc.database;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjhruska.mc.database.restrictions.Restriction;

@SuppressWarnings("rawtypes")
public interface TransactionalTestSetupServiceIface {

  @Transactional
  public abstract void save(DaoDomain dao, BaseDomain domain);

  @Transactional
  public abstract void saveOrUpdate(DaoDomain dao, BaseDomain domain);

  public abstract BaseDomain findByPK(DaoDomain dao, Serializable id);

  public abstract List<BaseDomain> findByCriteria(DaoDomain dao, Restriction restriction);

  public BaseDomain findOneByCriteria(DaoDomain dao, Restriction restriction);

  @Transactional
  public abstract void delete(DaoDomain dao, BaseDomain domain);
}