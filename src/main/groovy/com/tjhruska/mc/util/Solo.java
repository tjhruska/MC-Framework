/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util.container
 *   Solo.java
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
@SuppressWarnings("serial")
public class Solo<ONE> extends AbstractSolo<ONE> {

	/**
	 * @param one
	 */
	public Solo(ONE one) {
		super(one);
	}

	/**
	 * 
	 */
	public Solo() {}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("all")
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Solo)) return false;
		return this.equalsHelper((Solo)object);
	}
} 