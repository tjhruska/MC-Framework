/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.container
 *   Quartet.java
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
public class Quartet<ONE,TWO,THREE,FOUR> extends AbstractQuartet<ONE,TWO,THREE,FOUR> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param one
	 */
	public Quartet(ONE one, TWO two, THREE three, FOUR four) {
		super(one,two,three,four);
	}

	/**
	 * 
	 */
	public Quartet() {super();}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Quartet)) return false;
		return this.equalsHelper((Quartet<?,?,?,?>)object);
	}
} 