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