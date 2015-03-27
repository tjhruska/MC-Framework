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