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
<%@ page import="com.alipayapp.alipay.util.*"%>
<%@ page import="com.alipayapp.alipay.config.*"%>
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

	
	//获取支付宝POST过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
		params.put(name, valueStr);
	}

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	
	//RSA签名解密
   	//if(AlipayConfig.sign_type.equals("0001")) {
   	//	params = AlipayNotify.decrypt(params);
	//XML解析notify_data数据
	//Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
	
	//商户订单号
	//String out_trade_no = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();
	
	//测试
	//System.out.println("out_trade_no=="+out_trade_no);
	
	//支付宝交易号
	//String trade_no = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
	
	//String subject = doc_notify_data.selectSingleNode( "//notify/subject" ).getText();
	
	//String body=doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
	
	//String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
	
	//交易状态
	//String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();
	//System.out.println("notify url 1 :"+ trade_status);
	//System.out.println("notify url 2 :"+ out_trade_no);
	//System.out.println("notify_url:bodyStr"+subject);
	
	//商户订单号	
	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//支付宝交易号	
	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//交易状态
	String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
	
	String total_fee = request.getParameter("total_fee"); 
	
	String body = "";  
    if(request.getParameter("body") != null){  
        body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "gbk");//商品描述、订单备注、描述  
    } 
    
    System.out.println("<body>"+body);
     
    String buyer_email = request.getParameter("buyer_email");   //买家支付宝账号
      
    System.out.println("<buyer_email>"+buyer_email);
	
	
	System.out.println("<trade_no>"+trade_no);
	
    System.out.println("<out_trade_no>"+out_trade_no); 
             //获取总金额  
    System.out.println("<total_fee>"+total_fee);
    
    System.out.println("<trade_status>"+trade_status);
    
	
	
	
	
	
	
	
	System.out.println("AlipayNotify.verifyNotify(params) is "+AlipayNotify.verify(params));
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

	if(AlipayNotify.verify(params)){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码
		System.out.println("test success");
		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		
		if(trade_status.equals("TRADE_FINISHED")){
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				System.out.println("HooLee1 is begining...");
				out.println("success");	//请不要修改或删除
				
			//注意：
			//该种交易状态只在两种情况下出现
			//1、开通了普通即时到账，买家付款成功后。
			//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			
		} else if (trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
			//注意：
			//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			//判断该订单是否已经处理过了
			
			String turnUrl="http://kybabydev.yishangkeji.cn/main/orderManage.action?action=paySuccess&orderNum="+out_trade_no;
			URL turnTo=new URL(turnUrl);
			URLConnection connection=turnTo.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter coon=new OutputStreamWriter(connection.getOutputStream(),"utf-8");
			coon.write(params.toString());//post的关键所在！
			coon.flush();
			coon.close();
			String temp="";
			String result="";
			InputStream outStream=connection.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(outStream));
			while((temp=reader.readLine())!=null){
				result+=temp;
			}
			JSONObject obj = JSONObject.fromObject(result);
			String mes=obj.get("mes").toString();
			System.out.println("*****************notify_url:mes= "+mes);
			if(mes.equals("操作成功")){
				//处理成功之后，开始向用户发送通知短信
				String userMessage="http://kybabydev.yishangkeji.cn/main/sendMessage.action?action=toUser&sendWords=预约&orderNum="+out_trade_no;
				URL usrmsgUrl=new URL(userMessage);
				URLConnection usrConnection=usrmsgUrl.openConnection();
				usrConnection.setDoOutput(true);
				OutputStreamWriter usrCoon=new OutputStreamWriter(usrConnection.getOutputStream(),"utf-8");
				usrCoon.write(params.toString());
				usrCoon.flush();
				usrCoon.close();
				String usrTemp="";
				String usrResult="";
				InputStream usrOutStream=usrConnection.getInputStream();
				BufferedReader usrReader=new BufferedReader(new InputStreamReader(usrOutStream));
				while((usrTemp=usrReader.readLine())!=null){
					usrResult+=usrTemp;
				}
				JSONObject usrObj=JSONObject.fromObject(usrResult);
				String usrMes=usrObj.get("mes").toString();
				System.out.println("*****************notify_url:usrMes= "+usrMes);
				
				//处理成功之后，开始向医生发送短信
				
				String docMessage="http://kybabydev.yishangkeji.cn/main/sendMessage.action?action=toDoctor&sendWords=预约&orderNum="+out_trade_no;
				URL docmsgUrl=new URL(docMessage);
				URLConnection docConnection=docmsgUrl.openConnection();
				docConnection.setDoOutput(true);
				OutputStreamWriter docCoon=new OutputStreamWriter(docConnection.getOutputStream(),"utf-8");
				docCoon.write(params.toString());
				docCoon.flush();
				docCoon.close();
				String docTemp="";
				String docResult="";
				InputStream docOutStream=docConnection.getInputStream();
				BufferedReader docReader=new BufferedReader(new InputStreamReader(docOutStream));
				while((docTemp=docReader.readLine())!=null){
					docResult+=docTemp;
				}
				JSONObject docObj=JSONObject.fromObject(docResult);
				String docMes=docObj.get("mes").toString();
				System.out.println("*****************notify_url:docMes= "+docMes);
			}
			out.println("success");	//请不要修改或删除
		}
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
		//////////////////////////////////////////////////////////////////////////////////////////
	}else{//验证失败
		out.println("fail");
	}
%>
