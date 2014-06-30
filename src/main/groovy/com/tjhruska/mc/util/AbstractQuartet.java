/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.container
 *   AbstractQuartet.java
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
 * @param <ONE> 
 * @param <TWO> 
 * @param <THREE> 
 * @param <FOUR> 
 *
 */
public class AbstractQuartet<ONE,TWO,THREE,FOUR> 
  extends AbstractTrio<ONE,TWO,THREE> 
{

	private static final long serialVersionUID = 1L;
	private FOUR four;
	
	/**
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 */
	public AbstractQuartet(ONE one, TWO two, THREE three, FOUR four) {
		super(one,two,three);
		this.four = four;
	}

	/**
	 * 
	 */
	public AbstractQuartet() {super();}

	/**
	 * @return the current value of the FOUR object
	 */
	public FOUR getFour() {
		return four;
	}

	/**
	 * @param four
	 */
	public void setFour(FOUR four) {
		this.four = four;
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@SuppressWarnings("unchecked")
//	public boolean equals(Object object) {
//		if (!(object instanceof Quartet)) return false;
//		return this.equals((Quartet)object, true);
//		FOUR objectFour = ((Quartet<ONE,TWO,THREE,FOUR>)object).getFour();
//		return (
//			(
//				(four != null && four.equals(objectFour))
//				|| 
//				(four == null && objectFour == null)
//			)
//			&&
//			super.equals(object)
//		);
//	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (four == null ? 1 : four.hashCode()) + super.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Quartet<" + getOne() + this.getSeparator() + getTwo() 
			+ this.getSeparator() + getThree() + this.getSeparator() + four + ">";
	}
	
	/**
	 * @param quartet
	 * @return the equality of the duo to this object
	 */
	protected boolean equalsHelper(AbstractQuartet<?,?,?,?> quartet){
		if (!this.getNullsAreEqual() && (quartet.getFour() == null || this.getFour() == null))
			return false;
		return (
			(
				(four == null && quartet.getFour() == null)
				||
				(four != null && four.equals(quartet.getFour()))
			)
			&&
			super.equalsHelper(quartet)
		);
	}
} 