 package com.wx.util;
 
 import com.wx.util.mybatis.Page;

 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
 
 public class Tools
 {
   private static ObjectMapper objectMapper = new ObjectMapper();
 
   public static <T> T jsonToObj(String jsonStr, Class<T> clazz)
   {
     try
     {
       return objectMapper.readValue(jsonStr, clazz); } catch (Exception e) {
     }
     return null;
   }
 
   public static List<Object> jsonToList(String jsonStr)
   {
     JSONArray jsonArray = JSONArray.fromObject(jsonStr);
     Object[] obj = jsonArray.toArray();
     List list = Arrays.asList(obj);
     return list;
   }
 
   public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz)
   {
     JSONArray jsonArray = JSONArray.fromObject(jsonStr);
     List list = new ArrayList();
     for (int i = 0; i < jsonArray.size(); i++) {
       Object t = jsonToObj(jsonArray.get(i).toString(), clazz);
       list.add(t);
     }
     return list;
   }
 
   public static String toJson(Object obj)
   {
     try
     {
       return objectMapper.writeValueAsString(obj); } catch (Exception e) {
     }
     return null;
   }
 
   public static <T> String listPageToJson(List<T> lt, Page paper)
   {
     Map model = new HashMap();
     model.put("total", Integer.valueOf(paper.getTotalResult()));
     model.put("rows", lt);
     return toJson(model);
   }
 
   public static boolean notEmpty(String s)
   {
     return (s != null) && (!"".equals(s)) && (!"null".equals(s));
   }
 
   public static boolean isEmpty(String s)
   {
     return (s == null) || ("".equals(s)) || ("null".equals(s));
   }
 
   public static String[] str2StrArray(String str, String splitRegex)
   {
     if (isEmpty(str)) {
       return null;
     }
     return str.split(splitRegex);
   }
 
   public static String[] str2StrArray(String str)
   {
     return str2StrArray(str, ",\\s*");
   }
 
   public static Float todecimal(String str)
   {
     Float f = null;
     if (str.contains("%")) {
       str = str.replaceAll("%", "");
       f = Float.valueOf(Float.valueOf(str).floatValue() / 100.0F);
     }
     return f;
   }
 }
