/**
 *   Copyright  2007 Timothy James Hruska (tjhruska@yahoo.com)
 *
 *   com.tjhruska.util
 *   EnumerationNotUnique.java
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
package com.tjhruska.mc.enums;

/**
 * @author tjhruska
 *
 */
public class EnumerationNotUnique extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EnumerationNotUnique() {
		super();
	}

	/**
	 * @param arg0
	 */
	public EnumerationNotUnique(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public EnumerationNotUnique(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EnumerationNotUnique(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
} 