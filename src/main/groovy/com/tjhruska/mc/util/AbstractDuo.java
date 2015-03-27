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

/**
 * @param <ONE>
 * @param <TWO>
 */
@SuppressWarnings("serial")
public abstract class AbstractDuo<ONE,TWO> extends AbstractSolo<ONE> {

	private TWO two;
	
	/**
	 * @param one
	 * @param two
	 */
	public AbstractDuo(ONE one, TWO two) {
		super(one);
		this.two = two;
	}

	/**
	 * 
	 */
	public AbstractDuo() {super();}

	/**
	 * @return the current value of the TWO object
	 */
	public TWO getTwo() {
		return two;
	}

	/**
	 * @param two
	 */
	public void setTwo(TWO two) {
		this.two = two;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (two == null ? 1 : two.hashCode()) + super.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Duo<" + getOne() + this.getSeparator() + two + ">";
	}

	/**
	 * @param duo
	 * @return the equality of the duo to this object
	 */
	protected boolean equalsHelper(AbstractDuo<?,?> duo) {
		if (!this.getNullsAreEqual() && (duo.getTwo() == null || this.getTwo() == null))
			return false;
		return (
			(
				(this.getTwo() == null && duo.getTwo() == null)
				||
				(this.getTwo() != null && this.getTwo().equals(duo.getTwo()))
			)
			&&
			super.equalsHelper(duo)
		);
	}
} 