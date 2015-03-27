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