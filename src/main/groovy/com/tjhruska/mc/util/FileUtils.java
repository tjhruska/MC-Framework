package com.tjhruska.mc.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static final Logger log
		= LoggerFactory.getLogger(FileUtils.class);
	
	public static void writeFile(StringBuilder directoryName, 
		StringBuilder fileName, StringBuilder body) throws IOException
	{
		writeFile(directoryName.toString(), fileName.toString(), body.toString());
	}
	
	public static void writeFile(String directoryName, String fileName, String body) throws IOException{
		File file = new File(directoryName);
		//make the director(y|ies) if needed
    	if (!file.exists() && !file.mkdirs())
    		throw new RuntimeException("Could not create source directorIES: " + directoryName 
    			+ " to write out file " + fileName);
	
    	StringBuilder fqfName = new StringBuilder(directoryName).append(fileName);
		FileWriter outputFile = null;
		try{
			outputFile = new FileWriter(fqfName.toString());
			outputFile.write(body);
		}catch (Exception e){
			log.error("failed to write out file: {}", fqfName);
		}finally{
			outputFile.close();
		}
	}
}
