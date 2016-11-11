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
	<script type="text/javascript" src="js/doctorShow.js" ></script>
    <title>医生展示</title>
</head> 
<body>
	<div>
		<h2>展示医生列表</h2>
		<div>
			<input type="button" value="医生展示" onclick="doctorShow()"/>
		</div>
	</div>
	<div>
		<h2>医生主页</h2>
		<div>
			<span>医生的ID:</span><input type="text" id="doctorId" /><input type="button" value="医生主页" onclick="toPersonalPage()" />
		</div>
	</div>
	<div>
		<h2>点击个人专刊</h2>
		<span>个人专栏ID:</span><input type="text" id="articleId" /><input type="button" value="点击个人专栏" onclick="toSomeArticle()" />
	</div>
	<div>
		<h2>评论个人专栏</h2>
		<span>个人专栏ID：</span><input type="text" id="newArticleId" /><br/>
		<span>评论内容：</span><input type="text" id="comments" /><br/>
		<input type="button" value="评论" onclick="addComment()" />
	</div>
</body>
</html>