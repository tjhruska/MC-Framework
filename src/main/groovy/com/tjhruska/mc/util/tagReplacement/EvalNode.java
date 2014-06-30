/**
 * 
 */
package com.tjhruska.mc.util.tagReplacement;

import java.util.Map;

import com.tjhruska.mc.util.Solo;

/**
 * @author dakota
 *
 */
public abstract class EvalNode {
	protected abstract Boolean eval(Map<String,String> tagMap);
	protected abstract void parse(final char[] template, Solo<Integer> currentPosition);
}
