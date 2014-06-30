package com.tjhruska.mc.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.BeanNameAware;

public abstract class BeanNameAwareRunnable implements Runnable, BeanNameAware {

	private String beanName;
	public void setBeanName(String name) {
		this.beanName = name;
	}
	
	public String getBeanName(){
		return beanName;
	}
	
	public StringBuilder buildBackupDirectoryName(String baseDirectory){
		//prep the backupDirectory name to include our application name, and the datetime
		DateTime dt = new DateTime();
		DateTimeFormatter fmt = ISODateTimeFormat.basicDateTime();
		StringBuilder newDirectory = new StringBuilder(baseDirectory);
		newDirectory.append('/').append(getBeanName()).append('-').append(fmt.print(dt)).append('/');
		return newDirectory;
	}
}
