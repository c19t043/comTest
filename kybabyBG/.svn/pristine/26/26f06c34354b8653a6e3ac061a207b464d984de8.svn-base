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
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script src="js/test.js" ></script>
    <title>医生圈</title>
</head>
<body>
	<div>
		<h2>所有圈的分类</h2>
		<span>获取所有圈的分类的ID和名称</span><input type="button" value="所有分类ID和名称" onclick="getAllRingCategory()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>某个分类下的医生圈列表</h2>
		<span>分类的ID</span><input type="text" id="someCategory" /><br/>
		<span>获取某个分类下的医生圈的ID和名称</span><input type="button" value="分类下医生圈的ID和名称" onclick="getSomeCategory()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>发帖</h2>
		<span>帖子的标题</span><input type="text" id="postInfoTitle" /><br/>
		<span>帖子的内容</span><input type="text" id="postInfoContent" /><br/>
		<span>帖子所属医生圈ID</span><input type="text" id="ringTypeId" /><br/>
		<span>是否允许回复</span><input type="text" id="isAllowReply" /><br/>
		<span>是否置顶</span><input type="text" id="isTop" /><br/>
		<span>帖子状态</span><input type="text" id="postInfoStatus" /><br/>
		<input type="button" value="发表" onclick="addNewPostInfo()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>获取所有的帖子</h2>
		<span>获取所有帖子</span><input type="button" value="获取所有帖子" onclick="getAllPostInfo()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>修改帖子</h2>
		<span>帖子的ID</span><input type="text" id="postInfoId" /><br/>
		<span>帖子的标题</span><input type="text" id="new_postInfoTitle" /><br/>
		<span>帖子的内容</span><input type="text" id="new_postInfoContent" /><br/>
		<span>帖子所属医生圈ID</span><input type="text" id="new_ringTypeId" /><br/>
		<span>是否允许回复</span><input type="text" id="new_isAllowReply" /><br/>
		<span>是否置顶</span><input type="text" id="new_isTop" /><br/>
		<span>帖子状态</span><input type="text" id="new_postInfoStatus" /><br/>
		<input type="button" value="修改" onclick="changePostInfoInstance()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>所有医生圈分类的ID、名称、状态</h2>
		<span>获取所有医生圈分类的ID、名称、状态</span><input type="button" value="所有医生圈分类的ID、名称、状态" onclick="getAllTypeInfo()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>获取所有标签列表</h2>
		<span>获取所有标签</span><input type="button" value="获取所有标签" onclick="getAllDoctorRingLabel()"/>
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>增加新的标签</h2>
		<span>标签名称</span><input type="text" id="ringLabelName" /><br/>
		<span>标签状态</span><input type="text" id="ringLabelStatus" /><br/>
		<input type="button" value="添加" onclick="addNewLabel()" />
	</div>
	<p>------------------------------------------------------------------------------------------</p>
	<div>
		<h2>修改标签</h2>
		<span>标签的ID</span><input type="text" id="labelId" /><br/>
		<span>标签名称</span><input type="text" id="labelName" /><br/>
		<span>标签状态</span><input type="text" id="labelStatus" /><br/>
		<input type="button" value="修改" id="updateSomeLabel()" />
	</div>
</body>
</html>