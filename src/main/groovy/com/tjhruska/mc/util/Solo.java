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