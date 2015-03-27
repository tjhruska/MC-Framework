/**
Copyright 2007-2015 Timothy James Hruska (tjhruska@yahoo.com)

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
