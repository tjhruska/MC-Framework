/**
Copyright 2007-2015 Timothy James Hruska (tjhruska@yahoo.com)

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

package com.tjhruska.mc.util;

import java.io.Serializable;


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