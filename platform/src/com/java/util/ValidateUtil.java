package com.java.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.java.common.lang.StringUtils;

public class ValidateUtil {
	
	/**
	 * 判断传入字段不为空
	 * @param obj 对象
	 * @param propertyNames 需要判断的字段
	 * @return
	 */
	public static Map<String,String> isNotEmptyInclude(Object obj,String[] propertyNames){
		Map<String,String> isEmptyProperty = new HashMap<String,String>();
		for (String propertyName : propertyNames) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(propertyName,obj.getClass());
				Object retVal = pd.getReadMethod().invoke(obj, null);
				String errMess = "字段值不能为空";
				if(retVal==null||StringUtils.isBlank(retVal.toString())){
					isEmptyProperty.put(propertyName, errMess);
				}
			} catch (Exception e) {
				System.out.println(e);
				isEmptyProperty.put(propertyName, "验证失败");
				return isEmptyProperty;
			}
		}
		return isEmptyProperty;
	}
	
/*	public static Map<String,String> isNotEmptyExclude(Object obj,String[] propertyNames){
		Map<String,String> isEmptyProperty = new HashMap<String,String>();
		Field[] propertys = obj.getClass().getDeclaredFields();
	}*/
}
