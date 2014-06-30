/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.mc
 *   AbstractSolo.java
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the LGNU Lesser General Public License as 
 *   published by the Free Software Foundation, either version 3 of the 
 *   License, or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   LGNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the LGNU Lesser General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tjhruska.mc.util;

import java.io.Serializable;

/**
 * @author tjhruska
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractSolo<ONE> implements Serializable{

	private ONE one;
	private boolean nullsAreEqual;
	private String separator;
	
	/**
	 * @param one
	 */
	public AbstractSolo(ONE one) {
		this.one = one;
		this.nullsAreEqual = false;
		this.separator = ",";
	}

	/**
	 * 
	 */
	public AbstractSolo() {
		this.nullsAreEqual = false;
		this.separator = ",";
	}

	/**
	 * @param nullsAreEqual
	 */
	public void setNullsAreEqual(boolean nullsAreEqual){
		this.nullsAreEqual = nullsAreEqual;
	}
	
	/**
	 * @return nullsAreEqual
	 */
	public boolean getNullsAreEqual(){
		return this.nullsAreEqual;
	}
	
	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return this.separator;
	}

	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * @return the current value of the ONE object
	 */
	public ONE getOne() {
		return one;
	}

	/**
	 * @param one
	 */
	public void setOne(ONE one) {
		this.one = one;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (one == null ? 1 : one.hashCode());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Solo<" + one + ">";
	}

	/**
	 * @param solo
	 * @return the partial equals between the ONE objects
	 */
	protected boolean equalsHelper(AbstractSolo<?> solo) {
		if (!this.getNullsAreEqual() && (solo.getOne() == null || this.getOne() == null))
			return false;
		return (
			(
				(this.getOne() == null && solo.getOne() == null)
				||
				(this.getOne() != null && this.getOne().equals(solo.getOne()))
			)
		);
	}
} 