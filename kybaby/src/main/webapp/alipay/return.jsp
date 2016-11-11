<%
/* *
 功能：支付宝页面跳转同步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 可放入HTML等美化页面的代码、商户业务逻辑程序代码
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝页面返回页面</title>
		<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
  </head>
  <body>
<% System.out.println("return url"); %>

 <script>
 	var orderNum=window.location.search.substring(1);
 	alert(orderNum+"取消 支付");
 	//alert("return.jsp");
 	//单击了反馈按钮，需要做的就是将该订单号下面的订单取消，同时将对应的商品返回到对应的商品中
 	/*
 	$.ajax({
 		type:'post',
 		url:'../main/cancleOrders.action',
 		data:{action:"cancleOrders",orderNum:orderNum},
 		success:function(result){
 			if(result.mes=="未登录"){
 				alert("请登录");
 			}else if(result.mes=="已被取消"){
 				alert("该订单已经被取消");
 			}else if(result.mes=="操作成功"){
 				alert("商品已经回库");
 			}
 		}
 	});*/
 	
 	
 	
 /*
           $.ajax({
		   		url:'http://115.28.161.197:8080/guofeiguo/alipayCookie.action',
		   		data:{action:"addCookie"},
		   		success: function(result){
                   window.history.go(-((window.history.length)-1));
                }
                });
*/
 </script>
  </body>
</html>