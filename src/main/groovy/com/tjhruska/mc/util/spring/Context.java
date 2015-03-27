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

package com.tjhruska.mc.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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