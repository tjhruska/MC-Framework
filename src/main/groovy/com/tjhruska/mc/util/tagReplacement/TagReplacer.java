package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;

public final class TagReplacer {
	
	public static StringBuilder replaceTag(char[] template, 
		Solo<Integer> currentPosition, Solo<Boolean> modified,
		Map<String,String> tagMap, Boolean leaveFailedReplacements) 
	{
		StringBuilder replacement = new StringBuilder();

		//parse
		StringBuilder tag = TagReplacer.parse(template, currentPosition);
		
		//replace
		String value = tagMap.get(tag.toString());
		if (value != null){
			replacement.append(value);
			//only set modified if we complete a replacement
			modified.setOne(true);
		}else if (leaveFailedReplacements){
			replacement.append("-=[");
			replacement.append(tag);
			replacement.append("]=-");
			//we didn't modify, so don't set modified 
		}
		return replacement;
	}

	private static StringBuilder parse(char[] template, Solo<Integer> currentPosition) {
		Integer maxI = template.length - 1;
		Integer i = currentPosition.getOne();
		StringBuilder tag = new StringBuilder();

		if (i + 2 > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template not finding -=[");
		
		if(!(template[i] == '-' && template[i+1] == '=' && template[i+2] == '['))
			throw new RuntimeException("Parse error at char " + i 
				+ ", expected -=[ in order to begin tagReplacement.");

		//eat open tag
		i = i + 3;
		
		//the tag is a literal that user wishes replaced
		//  so no eating of the whitespace
		
		if (i + 2 > maxI) 
			throw new RuntimeException("Parse error at char " + i 
				+ ", reached end of template before finding ]=- to terminate tag replacement clause.");
		
		//scan for the end conditional tag }=-
		while(!(template[i] == ']' && template[i+1] == '=' && template[i+2] == '-'))
		{
			tag.append(template[i]);
			i++;
			if (i + 2 > maxI) 
				throw new RuntimeException("Parse error at char " + i 
					+ ", reached end of template before finding ]=- to terminate tag replacement clause.");
		}
	
		//eat close tag
		i = i + 3;
		currentPosition.setOne(i);
		return tag;
	}
}
