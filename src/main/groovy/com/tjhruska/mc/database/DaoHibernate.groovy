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

package com.tjhruska.mc.database

import groovy.util.logging.Slf4j

import javax.persistence.EntityNotFoundException

import org.hibernate.Criteria
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.Order
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import com.tjhruska.mc.database.restrictions.Restriction
import com.tjhruska.mc.database.restrictions.RestrictionHelper

/**
 * Class DaoHibernate utilizes a loosely coupled hibernate session factory to
 * provide CRUD operations on T that extends BaseDomain (domain classes).
 *
 * @author tjhruska
 *
 */
@Slf4j
public class DaoHibernate<T extends BaseDomain> implements DaoDomain<T> {

  private Class<T> type
  private SessionFactory sessionFactory
  private Session session
  @Autowired
  private RestrictionHelper restrictionHelper

  /**
   * @param type
   */
  public DaoHibernate(Class<T> type) {
    super()
    this.type = type
  }

  /**
   * @param sessionFactory
   *          the sessionFactory to set
   */
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory
  }

  /**
   * @return the session
   */
  public Session getSession() {
    if (session != null) {
      return session
    } else {
      try {
        return sessionFactory.getCurrentSession()
      } catch (HibernateException e) {
        return sessionFactory.openSession()
      }
    }
  }

  /**
   * @param session
   *          the session to set
   */
  public void setSession(Session session) {
    this.session = session
  }

  /**
   * open session
   */
  public void openSession() {
    this.session = sessionFactory.openSession()
  }

  /**
   * close session
   */
  public void closeSession() {
    this.session.close()
  }

  /**
   * @param restrictionHelper
   *          the restrictionHelper to set
   */
  public void setRestrictionhelper(RestrictionHelper restrictionHelper) {
    this.restrictionHelper = restrictionHelper
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#getDomainClass()
   */
  @Override
  public Class<T> getDomainClass() {
    return type
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
    getSession().save(domainObject)
    return domainObject.getId()
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#saveOrUpdate(com.tjhruska.mc.database
   * .BaseDomain)
   */
  @Override
  @Transactional
  public Integer saveOrUpdate(T domainObject) {
    getSession().saveOrUpdate(domainObject)
    return domainObject.getId()
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#delete(com.tjhruska.mc.database.BaseDomain
   * )
   */
  @Override
  @Transactional
  public void delete(T domainObject) {
    getSession().delete(domainObject)
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#findByPK(java.io.Serializable)
   */
  @Override
  @Transactional
  public T findByPK(Serializable id) {
    return (T) getSession().get(type, id)
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#findAll()
   */
  @Override
  @Transactional
  public List<T> findAll() {
    // return
    // (List<T>)sessionFactory.getCurrentSession().createCriteria(type).list()
    List<T> ts = getSession().createCriteria(type).list()
    log.info("found {} objects", ts.size())
    return ts
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
    List<T> results = findByCriteria(restriction)
    if (results.size() != 1) {
      throw new EntityNotFoundException("Expected exactly one ${type.getSimpleName()}, but found " + results.size() + " based upon restriction : $restriction")
    }
    return results.iterator().next()
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction)
   */
  @Override
  @Transactional
  public List<T> findByCriteria(Restriction restriction) {
    return findByCriteria(restriction, null, null)
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction, java.lang.Integer)
   */
  @Override
  @Transactional
  public List<T> findByCriteria(Restriction restriction, Integer limit) {
    return findByCriteria(restriction, limit, null)
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction, org.hibernate.criterion.Order)
   */
  @Override
  @Transactional
  public List<T> findByCriteria(Restriction restriction, Order order) {
    return findByCriteria(restriction, null, order)
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database
   * .restrictions.Restriction, java.lang.Integer,
   * org.hibernate.criterion.Order)
   */
  @Override
  @Transactional
  public List<T> findByCriteria(Restriction restriction, Integer limit, Order order) {
    Criteria criteria = getSession().createCriteria(type)
    if (limit != null) {
      criteria.setMaxResults(limit)
    }
    restrictionHelper.buildCriterion(criteria, restriction)
    if (order != null) {
      if (order.getPropertyName().contains(".")) {
        String association = order.getPropertyName().substring(0, order.getPropertyName().indexOf("."))
        criteria.createAlias(association, association)
      }
      criteria.addOrder(order)
    }
    List<T> ts = criteria.list()
    log.debug("found {} objects", ts.size())
    return ts
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#clearCache()
   */
  @Override
  public void evict(T domainObject) {
    getSession().evict(domainObject)
  }

  /*
   * (non-Javadoc)
   *
   * @see com.tjhruska.mc.database.DaoDomain#flush()
   */
  @Override
  @Transactional
  public void flush() {
    getSession().flush()
    getSession().clear()
  }
}