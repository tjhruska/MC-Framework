package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;

public class TemplateReplacer {

	public static StringBuilder replaceTemplates(char[] template, 
		Map<String,String> tagMap)
	{
		return replaceTemplates(template, tagMap, false, 10);
	}
	
	public static StringBuilder replaceTemplates(char[] template, 
		Map<String,String> tagMap, Integer maxPasses) 
	{
		return replaceTemplates(template, tagMap, false, maxPasses);
	}
	
	public static StringBuilder replaceTemplates(char[] template, 
		Map<String,String> tagMap, Boolean leaveFailedReplacements) 
	{
		return replaceTemplates(template, tagMap, leaveFailedReplacements, 10);
	}
	
	public static StringBuilder replaceTemplates(char[] inputTemplate, 
		Map<String,String> tagMap, Boolean leaveFailedReplacements, Integer maxPasses) 
	{
		char[] template = null;
		StringBuilder output = null;
		
		//loop over parsing X times
		for (int j = 0; j < maxPasses; j++){
		
			//prep containers for passing data to helpers
			Solo<Integer> currentPosition = new Solo<Integer>(0);
			Solo<Boolean> modified = new Solo<Boolean>(false);
			
			//prep template and output for this loop
			if (output == null){
				//first loop
				template = inputTemplate;
			}else{
				//future loop
				template = output.toString().toCharArray();
			}
			output = new StringBuilder();
		
			//ready control
			Integer maxI = template.length - 1;
			Integer i = currentPosition.getOne();

			while (i <= maxI){
				//check for open tagReplacement
				if (i+2 <= maxI && template[i] == '-' && template[i+1] == '=' 
					&& template[i+2] == '[')
				{
					currentPosition.setOne(i);
					output.append(TagReplacer.replaceTag(template, currentPosition, modified, tagMap, leaveFailedReplacements));
					i = currentPosition.getOne();	
				} else if (i+2 <= maxI 
					&& template[i] == '-' && template[i+1] == '=' 
					&& template[i+2] == '{')
				{ //check for open conditionalReplacement
					modified.setOne(true);
					currentPosition.setOne(i);
					output.append(ConditionalReplacer.replaceConditional(template, currentPosition, tagMap));
					i = currentPosition.getOne();	
				} else if (i <= maxI){
					output.append(template[i]);
					i++;
				}
			}//end while
			
			if (!modified.getOne())
				break;  //nothing changed so no need to pass again
		}//end for loop
		
		//either reached pass limit, or skipped out because we didn't find anything to modify
		return output;
	}//end static method
}
