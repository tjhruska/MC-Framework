package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;
import com.tjhruska.mc.util.tagReplacement.EndEvalNode.EndType;

public class BranchingEvalNode extends RightEvalNode {

	private int groupDepth;
	private LeftEvalNode leftEvalNode;
	private Operation operation;
	private RightEvalNode rightEvalNode;
	
	public BranchingEvalNode(int groupDepth, final char[] template, Solo<Integer> currentPosition){
		this.groupDepth = groupDepth;
		this.operation = Operation.NO_OP;
		this.parse(template, currentPosition);
	}

	public String toString(){
		return leftEvalNode.toString()
		+ (!operation.equals(Operation.NO_OP)? (" " + operation.getToken() + " "): "") 
		+ rightEvalNode.toString();
	}
	
	protected void parse(final char[] template, Solo<Integer> currentPosition){

		Integer maxI = template.length - 1;
		
		//////////////////
		//Parse out the left node which must be either a grouping node or a single node
		//  figure out which by parsing the leading characters, 
		//  and pulling off the token if it is a single node
		Solo<Boolean> isNegated = new Solo<Boolean>();
		Solo<Boolean> isGroup = new Solo<Boolean>();
		prepLeftNode(template, currentPosition, isNegated, isGroup);

		if (isGroup.getOne())
			leftEvalNode = new GroupingEvalNode(isNegated.getOne(), groupDepth, 
				template, currentPosition);
		else
			leftEvalNode = new SingleEvalNode(isNegated.getOne(), template, currentPosition);
		
		Integer i = currentPosition.getOne();
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//eat whitespace
		while (WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//////////////////////////
		//done with the left check for end characters
		EndType endType = EndType.getEndType(template[i]);
		if (endType != null){
			if (groupDepth > 0 && endType.equals(EndType.BEGIN_TRUE_CLAUSE)){
				throw new RuntimeException("Parse error at char " + i 
					+ " found '" + template[i] + "', attempt to close if "
					+ "clause before closing groups, expect " 
		           + groupDepth + " more ')'s before end of if clause.");
			}
			if (groupDepth == 0 && endType.equals(EndType.GROUP_CLOSE)){
				throw new RuntimeException("Parse error at char " + i 
					+ " found '" + template[i] + "', attempt to close group "
					+ "clause but we are not in a group.");
			}
			rightEvalNode = new EndEvalNode(endType);
			currentPosition.setOne(i + 1);
			return;
		}
	
		//eat whitespace
		while (WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		if ((template[i] == '&' || template[i] == '|') && i + 1 > maxI)
			throw new RuntimeException("Parse error at char " + i  
				+ ", reached end of template before finding : to terminate if clause.");	

		//////////////////////////
		//check for operation, it is required at this point
		if (template[i] == '&' && template[i+1] == '&'){
			this.operation = Operation.AND;
			i = i + 2;
		} else if (template[i] == '|' && template[i+1] == '|'){
			this.operation = Operation.OR;
			i = i + 2;
		} else {
			throw new RuntimeException("Parse error at char " + i +" found '" 
				+ template[i] + "', expected either && or ||");
		}
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//eat whitespace
		while (WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		currentPosition.setOne(i);
		
		//send the rest of the template to the right branching node
		rightEvalNode = new BranchingEvalNode(groupDepth, template, currentPosition);
	}
	
	private void prepLeftNode(final char[] template, 
		Solo<Integer> currentPosition, Solo<Boolean> isNegated, 
		Solo<Boolean> isGroup)
	{
		Integer maxI = template.length - 1;
		Integer i = currentPosition.getOne();
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//eat initial white space
		while(WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;

		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//check for negation
		if (template[i] == '!'){
			isNegated.setOne(true);
			i++;
		}else
			isNegated.setOne(false);
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//eat white space
		while(WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;

		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//check for negation
		if (template[i] == '('){
			isGroup.setOne(true);
			i++;
		}else
			isGroup.setOne(false);
		
		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		//eat white space
		while(WhiteSpaceToken.isWhiteSpace(template[i]))
			i++;

		if (i > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding : to terminate if clause.");

		currentPosition.setOne(i);
	}
	
	protected Boolean eval(Map<String,String> tagMap) {
		Boolean leftResult = leftEvalNode.eval(tagMap);
		switch(operation){
		case NO_OP:
			return leftResult;
		case AND:
			return leftResult && rightEvalNode.eval(tagMap);
		case OR:
			return leftResult || rightEvalNode.eval(tagMap);
		}
		return true;
	}

	protected enum Operation{
		NO_OP(""),
		AND ("&&"),
		OR ("||");
	
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
	
	protected enum WhiteSpaceToken{
		SPACE(" "),
		TAB ("\t"),
		NEWLINE ("\n"),
		CARIAGE_RETURN ("\r");
	
		private final String token;
		private final char[] tokenChars;
		
		WhiteSpaceToken(String token){
			this.token = token;
			this.tokenChars = token.toCharArray();
		}
		public String getToken(){
			return this.token;
		}
		
		public char[] getTokenChars(){
			return this.tokenChars;
		}
		
		public char getTokenChar(int position){
			return this.tokenChars[position];
		}
		
		public static boolean isWhiteSpace(char c){
			for (WhiteSpaceToken whiteSpaceToken : WhiteSpaceToken.values())
				if (whiteSpaceToken.getTokenChar(0) == c)
					return true;
			return false;
		}
	}
}
