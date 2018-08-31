package com.wisely.util;

import java.util.Collection;
import java.util.Dictionary;

public class Toolkit {

	public static boolean isEmpty(Object value){
		if(value==null){
			return true;
		}
		if((value instanceof String ) && (((String)value).trim().length()<=0)){
			return true;
		}
		if((value instanceof Object[])&& (((Object[]) value).length<=0)){
			return true;
		}
		if((value instanceof Collection)&& ((Collection) value).size()<=0){
			return true;
		}
		if((value instanceof Dictionary)&& ((Dictionary) value).size()<=0){
			return true;
		}
		return false;
		
	}
	public static boolean notEmpty(Object value){
		return !isEmpty(value);
	}
	
}
