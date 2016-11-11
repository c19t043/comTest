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
	<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="js/productAndServiceShow.js"></script>
    <title>产品服务展示</title>
</head> 
<body>
	<div>
		<h2>产品服务展示</h2>
		<div>
			<input type="button" value="产品服务展示" onclick="productAndService()" />
		</div>
	</div>
	<div>
		<h2>进入产品详情</h2>
		<div>
			<input type="text" id="productName" /><input type="button" value="产品详情" onclick="productDetail()" />
		</div>
	</div>
	<div>
		<h2>查看项目详情</h2>
		<div>
			<span>项目名称</span><input type="text" id="itemName" /><input type="button" value="查看项目详情" onclick="getItemDetails()" />
		</div>
	</div>
</body>
</html>