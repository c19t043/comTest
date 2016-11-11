package com.java.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static String convertObject2String(Object obj){
		JSONObject jo = JSONObject.fromObject(obj);
		return jo.toString();
	}
	
	public static String convertObject2String(List<?> objs){
		JSONArray ja = new JSONArray();
		if(objs.isEmpty()||objs==null) return "";
		for (Object obj : objs) {
			JSONObject jo = JSONObject.fromObject(obj);
			ja.add(jo);
		}
		return ja.toString();
	}
	
	public static void writeText(HttpServletResponse response,String content){
		print(response,content,"text/plain;charset=utf-8");
	}
	
	public static void writeJson(HttpServletResponse response,String content){
		
		print(response,content,"text/json;charset=utf-8");
	}
	
	private static void print(HttpServletResponse response,String content,String ContentType){
		try {
			 response.setCharacterEncoding("UTF-8");
			 response.setContentType(ContentType);
			 PrintWriter writer = response.getWriter();
			 writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isNotEmpty(String msg){
		if(msg==null) return false;
		if(msg.trim().length()==0) return false;
		return true;
	}
}
