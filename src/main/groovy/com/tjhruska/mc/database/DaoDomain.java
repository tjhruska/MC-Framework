/**
 * 
 */
package com.tjhruska.mc.database;

import java.io.Serializable;
import java.util.List;

import com.tjhruska.mc.database.restrictions.Restriction;


/**
 * @author tjhruska
 *
 */
public interface DaoDomain <T extends BaseDomain>{

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
	 * @param domainObject to delete
	 */
	public abstract void delete(T domainObject);
	
	/**
	 * @param id
	 * @return T which extends BaseDomain and has this.id = id or an composite key with this.pk(0..n) = id.pk(0..n)
	 */
	public abstract T findByPK(Serializable id);
	
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
	 * Evicts <T> from any internal caches
	 */
	public abstract void evict(T domainObject);
	
	/**
	 * Flushes any pending writes to perm store if any
	 */
	public abstract void flush();
}
