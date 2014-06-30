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
