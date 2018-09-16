package com.wisely.util;

import java.io.File;

import com.wisely.Constants;

public class FileUtil {
	
	public static final String BASE = "photo_dir";
	
	public static String getBaseDir(){
		String dir = System.getProperty("user.dir");
		String baseDir = dir+File.separator	+ BASE ; 
		File file = new File(baseDir);
		if(!file.exists()){
			file.mkdirs();
		}
		return baseDir ; 
	}
	
	public static String getSecondDir(int type){
		String base = getBaseDir();
		String typeDir = base+File.separator+Constants.MODEL_TYPES[type];
		File file = new File(typeDir);
		if(!file.exists()){
			file.mkdirs();
		}
		return typeDir;
	}
	
}
