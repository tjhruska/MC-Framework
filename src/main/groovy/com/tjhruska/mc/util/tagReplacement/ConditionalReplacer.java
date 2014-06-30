package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;

public class ConditionalReplacer extends EvalNode{
	BranchingEvalNode conditional;
	StringBuilder expressionIfTrue;
	StringBuilder expressionIfFalse;
	
	public static StringBuilder replaceConditional(char[] template, 
		Solo<Integer> currentPosition, Map<String,String> tagMap)
	{
		ConditionalReplacer cr = new ConditionalReplacer(template, currentPosition);
		return cr.getConditionalReplacement(tagMap);
	}
	
	public ConditionalReplacer(char[] template, Solo<Integer> currentPosition){
		expressionIfTrue = new StringBuilder();
		expressionIfFalse = new StringBuilder();
		parse(template, currentPosition);
	}
	
	protected StringBuilder getConditionalReplacement(Map<String,String> tagMap) {
		if (eval(tagMap)){
			return expressionIfTrue;
		}else{
			return expressionIfFalse;
		}
	}

	public String toString(){
		StringBuilder string = new StringBuilder();
		string.append("-={");
		string.append(conditional.toString());
		string.append(expressionIfTrue);
		string.append(":?:");
		string.append(expressionIfFalse);
		string.append("}=-");
		return string.toString();
	}
	
	protected void parse(char[] template, Solo<Integer> currentPosition) {
		Integer maxI = template.length - 1;
		Integer i = currentPosition.getOne();

		if (i + 2 > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template not finding -={");
		
		if(!(template[i] == '-' && template[i+1] == '=' && template[i+2] == '{'))
			throw new RuntimeException("Parse error at char " + i 
				+ ", expected -={ in order to begin conditionalReplacement.");

		//eat conditional open tag
		i = i + 3;
		
		currentPosition.setOne(i);
		
		//parse conditional
		conditional = new BranchingEvalNode(0, template, currentPosition);
		//conditional already ate the : as a part of its parsing

		//parse expressionIfTrue
		parseTrueExpression(template, currentPosition);
		//current position is either :?: or }=-

		i = currentPosition.getOne();
		
		//eat the :?: tag if it is there
		if(template[i] == ':' && template[i+1] == '?' && template[i+2] == ':')
			i = i + 3;
	
		currentPosition.setOne(i);
		
		//parse expressionIfFalse
		parseFalseExpression(template, currentPosition);
		//current position is }=-
		
		i = currentPosition.getOne();
		i = i + 3;
		currentPosition.setOne(i);
	}

	protected Boolean eval(Map<String, String> tagMap) {
		return conditional.eval(tagMap);
	}
	
	protected void parseTrueExpression(char[] template, Solo<Integer> currentPosition) {
		Integer maxI = template.length - 1;
		Integer i = currentPosition.getOne();

		//the true expression is the literal that user wishes replaced
		//  so no eating of the whitespace (mmm... white space is tasty)
	
		if (i + 2 > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding }=- to terminate conditional replacement clause.");
		
		//scan for else tag :?:, or the end conditional tag }=-
		while(
			!(template[i] == ':' && template[i+1] == '?' && template[i+2] == ':') &&
			!(template[i] == '}' && template[i+1] == '=' && template[i+2] == '-'))
		{
			expressionIfTrue.append(template[i]);
			i++;
			if (i + 2 > maxI) 
				throw new RuntimeException("Parse error at char " + i 
					+ ", reached end of template before finding }=- to terminate conditional replacement clause.");
		}
		currentPosition.setOne(i);
	}
	
	protected void parseFalseExpression(char[] template, Solo<Integer> currentPosition) {
		Integer maxI = template.length - 1;
		Integer i = currentPosition.getOne();

		//the false expression is the literal that user wishes replaced
		//  so no eating of the whitespace (mmm... false white space is even more tasty)
	
		if (i + 2 > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding }=- to terminate conditional replacement clause.");
		
		//scan for the end conditional tag }=-
		while(!(template[i] == '}' && template[i+1] == '=' && template[i+2] == '-'))
		{
			expressionIfFalse.append(template[i]);
			i++;
			if (i + 2 > maxI) 
				throw new RuntimeException("Parse error at char " + i 
					+ ", reached end of template before finding }=- to terminate conditional replacement clause.");
		}
		currentPosition.setOne(i);
	}
}
