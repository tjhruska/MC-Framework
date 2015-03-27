/**
Copyright 2011-2015 Timothy James Hruska (tjhruska@yahoo.com)

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

package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;

public class EndEvalNode extends RightEvalNode {

	private final EndType endType;
	
	public EndEvalNode(EndType endType){
		this.endType = endType;
	}
	
	protected Boolean eval(Map<String,String> tagMap) {
		return null;
	}

	public String toString(){
		return endType.getToken();
	}
	
	/**
	 * @return the endType
	 */
	protected EndType getEndType() {
		return endType;
	}

	protected enum EndType{
		GROUP_CLOSE(")"),
		BEGIN_TRUE_CLAUSE (":");
	
		private final String token;
		private final char[] tokenChars;
		
		EndType(String token){
			this.token = token;
			this.tokenChars = token.toCharArray();
		}
		public String getToken(){
			return this.token;
		}
		
		public char[] getTokenChars(){
			return this.tokenChars;
		}
		
		public static EndType getEndType(char c){
			if (c == ')')
				return GROUP_CLOSE;
			else if (c == ':')
				return BEGIN_TRUE_CLAUSE;
			else
				return null;
		}
	}

	protected void parse(char[] template, Solo<Integer> currentPosition) {
	}
}
