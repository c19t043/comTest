<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
	    
	<!-- safari full screen -->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta id="apple-mobile-web-app-title" name="apple-mobile-web-app-title" content="管理员" />
    
    <!-- chrome full screen -->
    <meta name="mobile-web-app-capable" content="yes" />
    
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<script type="text/javascript" src='../js/jquery-2.1.3.js' ></script>
	 <script type="text/javascript">
	 	var adminName="${landUser}";
	 	if(adminName==""){
	 		window.location.href="login.jsp";
	 	}
	 </script>
    <title>管理员主界面</title>
</head> 
<body>
	<div align="center" >
		<h1>管理员主界面</h1>
	</div>
	<div align="right">
		<a href="<%=basePath %>admin/logout.action?action=logout">退出登陆</a>
	</div>
	<div><h2 align="center" >This is a small demo!</h2></div>
</body>
</html>