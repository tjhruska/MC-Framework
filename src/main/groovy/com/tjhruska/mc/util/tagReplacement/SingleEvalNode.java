package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;
import com.tjhruska.mc.util.tagReplacement.BranchingEvalNode.WhiteSpaceToken;

public class SingleEvalNode extends LeftEvalNode {

	private StringBuilder tag;
	private Operation operation;
	private StringBuilder expectation;
	private char quoteCharacter;
	
	public SingleEvalNode(boolean isNegated, char[] template, Solo<Integer> currentPosition){
		if (isNegated)
			this.operation = Operation.NOT;
		else
			this.operation = Operation.EXISTS;
		parse(template, currentPosition);
	}

	public String toString(){
		return ((operation.equals(Operation.NOT)? "!" : "")
			+ ((operation.equals(Operation.EQUALS) || operation.equals(Operation.NOT_EQUALS))? "(":"")
			+ tag.toString()
			+ (operation.equals(Operation.EQUALS)? " == " : "")
			+ (operation.equals(Operation.NOT_EQUALS)? " != " : "")
			+ (expectation != null ? quoteCharacter + expectation.toString() + quoteCharacter : "")
			+ ((operation.equals(Operation.EQUALS) || operation.equals(Operation.NOT_EQUALS))? ")":""));
	}
	
	protected void parse(char[] template, Solo<Integer> currentPosition) {
		//ASSERT:  first character is not '!' or '(' or '|' or '&' or '=' or WS
		Integer maxI = template.length - 1;
		Integer i = currentPosition.getOne();
			
		tag = new StringBuilder();

		if ((template[i] == '&' || template[i] == '|' || template[i] == '=' || template[i] == '!') && i + 1 > maxI)
			throw new RuntimeException("Parse error at char " + i  
				+ ", reached end of template before finding : to terminate if clause.");
		
		//first item will be the tag, 
		//eat until :, ), whitespace, &&, ||, !=, ==
		while(
			!(template[i] == ':') &&
			!(template[i] == ')') &&
			!WhiteSpaceToken.isWhiteSpace(template[i]) &&
			!(template[i] == '&' && template[i+1] == '&') &&
			!(template[i] == '|' && template[i+1] == '|') &&
			!(template[i] == '!' && template[i+1] == '=') &&
			!(template[i] == '=' && template[i+1] == '='))
		{
			//if (template[i] == '&' || template[i] == '|' || template[i] == '=' || template[i] == '!')
			//	throw new RuntimeException("Parse error at char " + i  
			//		+ ", encountered special character " + template[i] + " which may not begin a tag.");
		
			tag.append(template[i]);
			i++;
			if (i > maxI) 
				throw new RuntimeException("Parse error at char " + i 
					+ ", reached end of template before finding : to terminate if clause.");
			if ((template[i] == '&' || template[i] == '|' || template[i] == '=' || template[i] == '!') && i + 1 > maxI)
				throw new RuntimeException("Parse error at char " + i  
					+ ", reached end of template before finding : to terminate if clause.");
		}

		//eat whitespace
		while (WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;
	
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");
	
		if ((template[i] == '&' || template[i] == '|' || template[i] == '=' || template[i] == '!') && i + 1 > maxI)
			throw new RuntimeException("Parse error at char " + i  
				+ ", reached end of template before finding : to terminate if clause.");
		
		//if clause complete, or is an exists operation we are done
		if ((template[i] == ':') ||
			(template[i] == ')') ||
			(template[i] == '&' && template[i+1] == '&')||
			(template[i] == '|' && template[i+1] == '|'))
		{//all done parsing
			if (tag.length() == 0)
				throw new RuntimeException("Parse error at char " + i  
					+ ", reached: to terminate if clause without finding a required tag.");
			currentPosition.setOne(i);
			return;
		}
	
		//if we are a not exists clause then we should be done check to be sure
		if (operation.equals(Operation.NOT)){
			throw new RuntimeException("Parse error at char " + i +" found '" 
				+ template[i] + "', !TAG must be followed by either :, &&, ||, or )");
		}

		//figure out which operation to which we are comparing the TAG
		if (template[i] == '=' && template[i+1] == '='){
			operation = Operation.EQUALS;
			i = i + 2;
		} else if (template[i] == '!' && template[i+1] == '='){
			operation = Operation.NOT_EQUALS;
			i = i + 2;
		} else {
			throw new RuntimeException("Parse error at char " + i +" found '" 
				+ template[i] + "', TAG must be followed by either :, &&, ||, ), ==, or !=");
		}

		//eat whitespace after the operation
		while (WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;

		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");
	
		//check for " or '
		if (template[i] != '"' && template[i] !='\''){
			throw new RuntimeException("Parse error at char " + i +" found '" 
				+ template[i] + "', TAG " + tag.toString() + " "
				+ ((operation.equals(Operation.EQUALS))? "==" : "!=")
				+ " must be followed by single or double quote.");
		}

		quoteCharacter = template[i];
		i++; //eat the "

		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");
	
		expectation = new StringBuilder();
		//read in the expectation
		while(template[i] != quoteCharacter){
			expectation.append(template[i]);
			i++;
			if (i > maxI) 
				throw new RuntimeException("Parse error at char " + i 
					+ ", reached end of template before finding : to terminate if clause.");
		}	
	
		i++; //eat the "
	
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");
	
		//eat whitespace after the expectation
		while (WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;
	
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");
	
		if ((template[i] == '&' || template[i] == '|') && i + 1 > maxI)
			throw new RuntimeException("Parse error at char " + i  
				+ ", reached end of template before finding : to terminate if clause.");
		
		//clause complete
		if ((template[i] == ':') ||
			(template[i] == ')') ||
			(template[i] == '&' && template[i+1] == '&')||
			(template[i] == '|' && template[i+1] == '|'))
		{//all done parsing
			currentPosition.setOne(i);
			return;
		} else {
			//didn't find one of the correct closing characters
			throw new RuntimeException("Parse error at char " + i +" found '" 
				+ template[i] + "', TAG "
				+ ((operation.equals(Operation.EQUALS))? "==" : "!=")
				+ " \"EXPECTATION\" (" + tag.toString() + " "
				+ ((operation.equals(Operation.EQUALS))? "==" : "!=")
				+ " \"" + expectation.toString() 
				+ "\" must be followed by either :, &&, ||, or )");
		}
		
	}
	
	/**
	 * @return the operation
	 */
	protected Operation getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	protected void setOperation(Operation operation) {
		this.operation = operation;
	}

	/**
	 * @return the expectation
	 */
	protected StringBuilder getExpectation() {
		return expectation;
	}

	/**
	 * @param expectation the expectation to set
	 */
	protected void setExpectation(StringBuilder expectation) {
		this.expectation = expectation;
	}

	/**
	 * @return the tag
	 */
	protected StringBuilder getTag() {
		return tag;
	}

	protected Boolean eval(Map<String,String> tagMap) {
		switch(operation){
		case EXISTS:
			return tagMap.containsKey(tag.toString());
		case NOT:
			return !(tagMap.containsKey(tag.toString()));
		case EQUALS:
			return (tagMap.containsKey(tag.toString()) 
				&& expectation.toString().equals(tagMap.get(tag.toString())));
		case NOT_EQUALS:
			return !(tagMap.containsKey(tag.toString()) 
				&& expectation.toString().equals(tagMap.get(tag.toString())));
		}
		return false;
	}

	protected enum Operation{
		EXISTS(""),
		NOT ("!"),
		EQUALS ("=="),
		NOT_EQUALS ("!=");
	
		private final String token;
		private final char[] tokenChars;
		
		Operation(String token){
			this.token = token;
			this.tokenChars = token.toCharArray();
		}
		public String getToken(){
			return this.token;
		}
		
		public char[] getTokenChars(){
			return this.tokenChars;
		}
	}

}
