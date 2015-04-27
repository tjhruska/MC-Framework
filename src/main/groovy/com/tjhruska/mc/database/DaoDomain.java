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

import org.hibernate.criterion.Order;

import com.tjhruska.mc.database.restrictions.Restriction;

/**
 * @author tjhruska
 *
 */
public interface DaoDomain<T extends BaseDomain> {

  /**
   * @return class of T
   */
  public abstract Class<T> getDomainClass();

  /**
   * @param domainObject
   * @return saves new T which extends BaseDomain
   */
  public abstract Integer save(T domainObject);

  /**
   * @param domainObject
   * @return saves or updates new T which extends BaseDomain
   */
  public abstract Integer saveOrUpdate(T domainObject);

  /**
   * @param domainObject
   *          to delete
   */
  public abstract void delete(T domainObject);

  /**
   * @param id
   * @return T which extends BaseDomain and has this.id = id or an composite key
   *         with this.pk(0..n) = id.pk(0..n)
   */
  public abstract T findByPK(Serializable id);

  /**
   * @return T which extends BaseDomain filtered by criteria
   */
  public T findOneByCriteria(Restriction restriction);

  /**
   * @return list of T which extends BaseDomain
   */
  public abstract List<T> findAll();

  /**
   * @return list of T which extends BaseDomain filtered by criteria
   */
  public List<T> findByCriteria(Restriction restriction);

  /**
   * @param limit
   * @return list of T which extends BaseDomain filtered by criteria
   */
  public List<T> findByCriteria(Restriction restriction, Integer limit);

  /**
   * @param order
   * @return list of T which extends BaseDomain filtered by criteria
   */
  public List<T> findByCriteria(Restriction restriction, Order order);

  /**
   * @param limit
   * @param order
   * @return list of T which extends BaseDomain filtered by criteria
   */
  public List<T> findByCriteria(Restriction restriction, Integer limit, Order order);

  /**
   * Evicts <T> from any internal caches
   */
  public abstract void evict(T domainObject);

  /**
   * Flushes any pending writes to perm store if any
   */
  public abstract void flush();
}
