package com.java.util;

public class MsgUtil {
	private static ThreadLocal<Object> t = new ThreadLocal<Object>();
	public static void set(Object value){
		t.set(value);
	}
	public static Object get(){
		return t.get();
	}
}
