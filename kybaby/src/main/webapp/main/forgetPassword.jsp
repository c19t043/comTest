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
	<script src="js/jquery-2.1.3.js"></script>
	<script src="js/forgetPasswordlhh.js" ></script>
    <title>忘记密码</title>
</head> 
<body>
	<span>手机号:</span><input type="text" id="userPhone" /><br/>
	<span>验证码：</span><input type="text" id="checkWords" /><input type="button" value="获取验证码" onclick="getCheckWords()" /><br/>
	<span>新密码：</span><input type="text" id="newPassword" /><br/>
	<span>确认新密码：</span><input type="text" id="reNewPassword" /><br/>
	<input type="button" value="确认" onclick="forgetPassword()" />
</body>
</html>