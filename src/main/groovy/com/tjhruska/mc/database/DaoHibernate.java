/**
 * 
 */
package com.tjhruska.mc.database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tjhruska.mc.database.restrictions.Restriction;
import com.tjhruska.mc.database.restrictions.RestrictionHelper;

/**
 * Class DaoHibernate utilizes a loosely coupled hibernate session factory
 * to provide CRUD operations on T that extends BaseDomain (domain classes).
 * @author tjhruska
 *
 */
public class DaoHibernate <T extends BaseDomain> implements DaoDomain<T> {
	private static final Logger log
		= LoggerFactory.getLogger(DaoHibernate.class);

	private Class<T> type;
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private RestrictionHelper restrictionHelper;
	
	/**
	 * @param type
	 */
	public DaoHibernate(Class<T> type){
		super();
		this.type = type;
		//System.out.println("DaoHibernate constructer was passed a Class<T>=" + type);
	}
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
   * @return the session
   */
  public Session getSession() {
    if (session != null) {
      return session;
    } else {
      return sessionFactory.getCurrentSession();
    }
  }
  
  /**
   * @param session the session to set
   */
  public void setSession(Session session) {
    this.session = session;
  }

  /**
   * open session
   */
  public void openSession() {
    this.session = sessionFactory.openSession();
  }

  /**
   * close session
   */
  public void closeSession() {
    this.session.close();
  }
  
  /**
	 * @param restrictionHelper the restrictionHelper to set
	 */
	public void setRestrictionhelper(RestrictionHelper restrictionHelper) {
		this.restrictionHelper = restrictionHelper;
	}

	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#getDomainClass()
	 */
	public Class<T> getDomainClass() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#save(com.tjhruska.mc.database.BaseDomain)
	 */
	public Integer save(T domainObject) {
	  getSession().save(domainObject);
	  return domainObject.getId();
	}
	
	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#saveOrUpdate(com.tjhruska.mc.database.BaseDomain)
	 */
	@Transactional
	public Integer saveOrUpdate(T domainObject) {
		getSession().saveOrUpdate(domainObject);
		return domainObject.getId();
	}

	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#delete(com.tjhruska.mc.database.BaseDomain)
	 */
	@Transactional
	public void delete(T domainObject) {
		getSession().delete(domainObject);
	}

	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#findByPK(java.io.Serializable)
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public T findByPK(Serializable id) {
		return (T)getSession().get(type, id);
	}

	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findAll() {
		//return (List<T>)sessionFactory.getCurrentSession().createCriteria(type).list();
		List<T> ts = (List<T>)getSession().createCriteria(type).list();
		log.info("found {} objects", ts.size());
		return ts;
	}
	
	/* (non-Javadoc)
	 * @see com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database.restrictions.Restriction)
	 */
	@Transactional
	public List<T> findByCriteria(Restriction restriction) {
		return findByCriteria(restriction, null);
	}

  /* (non-Javadoc)
   * @see com.tjhruska.mc.database.DaoDomain#findByCriteria(com.tjhruska.mc.database.restrictions.Restriction, java.lang.Integer)
   */
	@Transactional
	@SuppressWarnings("unchecked")
  public List<T> findByCriteria(Restriction restriction, Integer limit) {
		Criteria criteria = getSession().createCriteria(type);
		if (limit != null) {
		  criteria.setMaxResults(limit);
		}
		restrictionHelper.buildCriterion(criteria, restriction);
		List<T> ts = (List<T>)criteria.list();
		log.info("found {} objects", ts.size());
		return ts;
  }

  /* (non-Javadoc)
   * @see com.tjhruska.mc.database.DaoDomain#clearCache()
   */
  public void evict(T domainObject) {
    getSession().evict(domainObject);
  }

  /* (non-Javadoc)
   * @see com.tjhruska.mc.database.DaoDomain#flush()
   */
	@Transactional
  public void flush() {
    getSession().flush();
  }
} 