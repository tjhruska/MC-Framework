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

public class Duo<ONE,TWO> extends AbstractDuo<ONE,TWO> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param one
	 */
	public Duo(ONE one, TWO two) {
		super(one,two);
	}

	/**
	 * 
	 */
	public Duo() {super();}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Duo)) return false;
		return this.equalsHelper((Duo<?,?>)object);
	}
} 