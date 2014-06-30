/**
 * 
 */
package com.tjhruska.mc.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tjhruska
 *
 */
public class Context {
	private static ApplicationContext context = null;
	private static String contextFileName = null;
	
	/**
	 * Loads the applicationContext if needed, and returns it.  If called a second time with a different file name it will throw a RuntimeException.
	 * @param fileName file containing the ApplicationContext to be loaded
	 * @return ApplicationContext loaded from the fileName
	 */
	public static ApplicationContext getClassPathXmlApplicationContext(String fileName){
		if (contextFileName != null && !contextFileName.equals(fileName))
			throw new RuntimeException("Context fileName requested doesn't match context already loaded.");
		if (context != null) return context;
		context = new ClassPathXmlApplicationContext(fileName);
		contextFileName = fileName;
		return context;
	}
	
	/**
	 * Returns previously loaded ApplicationContext, or null if not loaded.
	 * Going with the spirit of spring runtime exceptions this class will throw a RuntimeException if called before loading of context.
	 * @return previously loaded ApplicationContext, or null
	 */
	public static ApplicationContext getContext(){
		if (context != null) return context;
		throw new RuntimeException("Context not loaded.");
	}
} 