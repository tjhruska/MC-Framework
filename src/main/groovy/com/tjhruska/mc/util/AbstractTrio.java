/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.container
 *   AbstractTrio.java
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
public abstract class AbstractTrio<ONE,TWO,THREE> extends AbstractDuo<ONE,TWO> {

	private static final long serialVersionUID = 1L;
	private THREE three;
	
	/**
	 * @param one
	 */
	public AbstractTrio(ONE one, TWO two, THREE three) {
		super(one,two);
		this.three = three;
	}

	/**
	 * 
	 */
	public AbstractTrio() {super();}

	/**
	 * @return the current value of the THREE object
	 */
	public THREE getThree() {
		return three;
	}

	/**
	 * @param three
	 */
	public void setThree(THREE three) {
		this.three = three;
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@SuppressWarnings("unchecked")
//	public boolean equals(Object object) {
//		if (!(object instanceof Trio)) return false;
//		return this.equals((Trio)object, true);
//		THREE objectThree = ((Trio<ONE,TWO,THREE>)object).getThree();
//		return (
//			(
//				(three != null && three.equals(objectThree))
//				|| 
//				(three == null && objectThree == null)
//			)
//			&&
//			super.equals(object)
//		);
//	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (three == null ? 1 : three.hashCode()) + super.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Trio<" + getOne() + this.getSeparator() 
			+ getTwo() + this.getSeparator() + three + ">";
	}

	/**
	 * @param trio
	 * @return the partial equals of the THREE, TWO, ONE objects
	 */
	protected boolean equalsHelper(AbstractTrio<?,?,?> trio) {
		if (!this.getNullsAreEqual() && (trio.getThree() == null || this.getThree() == null))
			return false;
		return (
			(
				(three == null && trio.getThree() == null)
				||
				(three != null && three.equals(trio.getThree()))
			)
			&&
			super.equalsHelper(trio)
		);
	}
} 