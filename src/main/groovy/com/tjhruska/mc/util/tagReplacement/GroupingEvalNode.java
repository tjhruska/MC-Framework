package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;

public class GroupingEvalNode extends LeftEvalNode {

	//this was parsed out of the branching node, and we "own" this token
	private char assumedStoredChar = '(';

	private BranchingEvalNode branchingEvalNode;
	private boolean isNegated;
	
	public GroupingEvalNode(boolean isNegated, int groupDepth, 
		final char[] template, Solo<Integer> currentPosition)
	{
		branchingEvalNode = new BranchingEvalNode(groupDepth + 1, template, currentPosition);
		this.isNegated = isNegated;
		parse(template, currentPosition);
	}
	
	public String toString(){
		return  (isNegated ? "!":"") + assumedStoredChar 
			+ branchingEvalNode.toString();
	}
	
	protected void parse(final char[] template, Solo<Integer> currentPosition){}
	
	protected Boolean eval(Map<String,String> tagMap) {
		if (isNegated)
			return !branchingEvalNode.eval(tagMap);
		else
			return branchingEvalNode.eval(tagMap);
	}

}
