/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.container
 *   AbstractDuo.java
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

/**
 * @author tjhruska
 *
 */
/**
 * @author dakota
 *
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