<%
/* *
 功能：支付宝服务器异步通知页面
 版本：3.3
 日期：2012-08-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
 该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.dom4j.Document"%>
<%@ page import="org.dom4j.DocumentHelper"%>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.OutputStreamWriter" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONException" %>
<%@ page import="net.sf.json.JSONObject" %>

<%
System.out.println("**************************");
	//获取支付宝POST过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	System.out.println("**************************111"+request.getMethod());
	System.out.println("**************************222"+request.getServletPath());
	System.out.println("**************************333"+request.getPathInfo());
	Map<String,String[]> requestParams = request.getParameterMap();
	System.out.println("**************************11");
	
	for (String key : requestParams.keySet()) {
	System.out.println("********************************************************************************************");
   System.out.println("key= "+ key + " and value= " + requestParams.get(key));
  }
	
	
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
	System.out.println("**************************22");
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		
		System.out.println("**************************"+name);
		System.out.println("**************************"+valueStr);
		
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
		params.put(name, valueStr);
	}


%>
