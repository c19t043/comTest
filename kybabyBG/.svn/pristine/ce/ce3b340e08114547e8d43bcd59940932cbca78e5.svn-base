package com.kybaby.kyinterface.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

/**
 * 解析请求中的乱码--》utf-8
 * 
 * @author Administrator
 * 
 */
public class MessyCodeConversion {
	
	public static String conversionString(String str) throws UnsupportedEncodingException{
		return ISO88591_2_UTF8(str);
	}

	public static String ISO88591_2_UTF8(String str)
			throws UnsupportedEncodingException {
		if (StringUtils.isBlank(str))
			return "";
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}
}
