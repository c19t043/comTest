<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
	    
	<!-- safari full screen -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta id="apple-mobile-web-app-title" name="apple-mobile-web-app-title" content="管理员">
    
    <!-- chrome full screen -->
    <meta name="mobile-web-app-capable" content="yes">
    
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<script type="text/javascript" src="../js/jquery-2.1.3.js"></script>
    <script type="text/javascript">
    function preCheck(){
	if($.trim($("#loginName").val())==""){
		alert("输入的用户名不能为空");
		return false;
	}else if($.trim($("#loginPassword").val())==""){
		alert("输入的密码不能为空");
		return false;
	}else{
		alert("正在登陆中...");
		adminLogin();
	}
}
    function adminLogin(){
	$.ajax({
		url:'<%=basePath%>admin/login.action',
		data:{action:"adminLogin",adminName:$("#loginName").val(),adminPassword:$("#loginPassword").val()},
		success:function(result){
			if(result.mes=="账号不存在"){
				alert("你输入的账号有误");
			}else if(result.mes=="密码错误") {
				alert("你输入的密码有误");
			}else{
				alert("登陆成功，正在跳转页面");
				window.location.href="main.jsp";
			}
		}
	});
}
    </script>
    
    <title>管理员登录</title>
</head> 
<body>
	<div>
		<h1>管理员登录</h1>
		<form id="adminLoginForm" name="adminLoginForm" method="post">
		    <lable>账号：</lable><input type="text" id="loginName" name="name">
			<br>
			<br>
			<lable>密码：</lable><input type="password" id="loginPassword" name="password">
			<br>
			<br>
			<input type="button" value="提交" onclick="preCheck()">
		</form>
	</div>
</body>
<html></html></i>