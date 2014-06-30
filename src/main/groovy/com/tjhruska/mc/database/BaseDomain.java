/**
 * 
 */
package com.tjhruska.mc.database;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * The Abstract Class BaseDomain
 * 
 * Cuts down on repeated code across all domain classes using the BaseDomain class
 * 
 * @author tjhruska
 */
@SuppressWarnings("serial")
public abstract class BaseDomain implements Serializable {

	private Integer id;
	private DateTime addDate;
	
	/**
	 * 
	 */
	protected BaseDomain() {
	}

	/**
	 * @return the PK, in most cases the id, but on some objects it may be a "template" with the pk fields set
	 */
	public Serializable getPK() {
	  return id;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the addDate
	 */
	//@JsonSerialize(using DateSerializer.class)
	public DateTime getAddDate() {
		return addDate;
	}

	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(DateTime addDate) {
		this.addDate = addDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (getId() == null){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseDomain))
			return false;
		BaseDomain other = (BaseDomain) obj;
		if (id == null || other.id ==null)
			return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}

} 