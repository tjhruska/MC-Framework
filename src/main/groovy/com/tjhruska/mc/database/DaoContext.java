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

import org.joda.time.DateTime;

import com.tjhruska.mc.database.restrictions.Restriction;

/**
 * Class DaoContext utilizes a loosely coupled list backed storage mechanism to
 * provide CRUD operations on T that extends BaseDomain (domain classes). It
 * does not currently propagate saves. (This could be implemented with
 * reflection, or loading maps of local containers to doa for that container,
 * and implementing some type of dirty checking.) It does not handle composite
 * pks.
 *
 * @author tjhruska
 *
 */
public class DaoContext<T extends BaseDomain> implements DaoDomain<T> {

  private Class<T> type;
  private List<T> storageContainer;

  /**
   * @param type
   * @param storageContainer
   */
  public DaoContext(Class<T> type, List<T> storageContainer) {
    super();
    this.type = type;
    this.storageContainer = storageContainer;
    System.out.println("type:" + type + " size:" + storageContainer.size());
    int id = 1;
    for (T item : storageContainer) {
      item.setId(id);
      item.setAddDate(new DateTime());
      id++;
    }
  }

  private Integer markPersisted(T domainObject) {
    // for now we will assume anything with an id is persisted
    Integer id = domainObject.getId();
    if (id == null) {
      id = getMaxId();
      id++;
      domainObject.setId(id);
      domainObject.setAddDate(new DateTime());
    }
    return id;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#getDomainClass()
   */
  @Override
  public Class<T> getDomainClass() {
    return type;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#save(com.tjhruska.mc.database.BaseDomain
   * )
   */
  @Override
  public Integer save(T domainObject) {
    Integer id = domainObject.getId();
    if (id == null) {
      id = markPersisted(domainObject);
    }
    boolean isInStorageContainer = false;
    for (T item : storageContainer) {
      if (item.getId().equals(domainObject.getId()))
        isInStorageContainer = true;
    }
    if (!isInStorageContainer)
      storageContainer.add(domainObject);
    else
      throw new RuntimeException("id: " + id + " is already in the store, call saveOrUpdate instead.");
    return id;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DomainDao#saveOrUpdate(com.tjhruska.mc.database
   * .BaseDomain)
   */
  @Override
  public Integer saveOrUpdate(T domainObject) {
    Integer id = markPersisted(domainObject);
    boolean isInStorageContainer = false;
    for (T item : storageContainer) {
      if (item.equals(domainObject))
        isInStorageContainer = true;
    }
    if (!isInStorageContainer)
      storageContainer.add(domainObject);
    return id;
  }

  /**
   * @return the max id currently in use in storageContainer
   */
  private int getMaxId() {
    int currentId = 0;
    for (T current : storageContainer) {
      if (current.getId() > currentId)
        currentId = current.getId();
    }
    return currentId;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DomainDao#delete(com.tjhruska.mc.database.BaseDomain
   * )
   */
  @Override
  public void delete(T domainObject) {
    storageContainer.remove(domainObject);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DomainDao#findByPK(java.io.Serializable)
   */
  @Override
  public T findByPK(Serializable id) {
    if (id == null)
      return null;
    for (T element : storageContainer) {
      if (element.getId().equals(id))
        return element;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DomainDao#findAll()
   */
  @Override
  public List<T> findAll() {
    return storageContainer;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction)
   */
  @Override
  public List<T> findByCriteria(Restriction restriction) {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction, Integer)
   */
  @Override
  public List<T> findByCriteria(Restriction restriction, Integer limit) {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#clearCache()
   */
  @Override
  public void evict(T domainObject) {
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#flush()
   */
  @Override
  public void flush() {
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findOneByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction)
   */
  @Override
  public T findOneByCriteria(Restriction restriction) {
    throw new UnsupportedOperationException();
  }
}