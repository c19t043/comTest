package com.kybaby.util;

import org.apache.log4j.Logger;

public class LogUtil {

	private static Logger log = Logger.getLogger(LogUtil.class);
	
	private static boolean isEnable = true;
	
	public static void debug(String msg){
		if(isEnable) log.debug(msg);
	}
	public static void debug(String msg,Throwable e){
		if(isEnable) log.debug(msg, e);
	}
	public static void info(String msg){
		if(isEnable) log.info(msg);
	}
	public static void error(String msg){
		if(isEnable) log.error(msg);
	}
	public static void error(String msg,Throwable e){
		if(isEnable) log.error(msg,e);
	}
	public static void warn(String msg){
		if(isEnable) log.warn(msg);
	}
	public static void warn(String msg,Throwable e){
		if(isEnable) log.warn(msg, e);
	}
	public static void fatal(String msg){
		if(isEnable) log.fatal(msg);
	}
	public static void fatal(String msg,Throwable e){
		if(isEnable) log.fatal(msg, e);
	}
}
