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
	<script type="text/javascript" src="js/personZoneLHH.js"></script>
    <title>个人中心</title>
</head> 
<body>
	<div>
		<h2>修改手机号</h2>
		<span>原手机号</span><input type="text" id="oldPhone" /><input type="button" value="获取验证码" onclick="checkOldPhone()" /><br/>
		<span>验证码</span><input type="text" id="inputWords" /><input type="button" value="下一步" onclick="nexteStep()" /><br/>
		<span>新手机号</span><input type="text" id="newPhone" /><input type="button" value="获取验证码" onclick="checkNewPhone()" /><br/>
		<span>验证码</span><input type="text" id="newInputWords" /><input type="button" value="确定" onclick="changePhoneNumber()" />
	</div>
	
	<div>
		<h2>推荐有奖</h2>
		<input type="button" value="推荐有奖" onclick="getUserPhone()" />
	</div>
	
	<div>
		<h2>修改密码</h2>
		<span>手机号</span><input type="text" id="changePhone" /><input type="button" value="获取验证码" onclick="checkUserPhone()" /><br/>
		<span>验证码</span><input type="text" id="changeCheckwords" /><br/>
		<span>新密码</span><input type="text" id="password" /><br/>
		<span>确认新密码</span><input type="text" id="againPassword" /><br/>
		<input type="button" value="修改密码" onclick="changePassword()" />
	</div>
	
	<div>
		<h2>意见反馈</h2>
		<span>反馈的内容</span><input type="text" id="content" /><input type="button" value="提交反馈" onclick="addFeedBack()" />
	</div>
	
	<div>
		<h2>上传头像</h2>
		<form action="userImageManage.action?action=inputIcon" type="post" onsubmit="" enctype="multipart/form-data" >
			<span>图片</span><img style="width:154px;height:86px;position:relative;" src="" alt="上传图片" onclick="productSmallFileElem.click()" id="imgup1" /><br/>
			<input type="file" id="productSmallFileElem" name="productSmallFileElem" accept="image/*" onchange="handleFiles(this,'imgup1')" style="display:none;" /><br/>
			<input type="submit" value="提交"/>
		</form>
	</div>
	
	<div>
		<h2>查看个人信息</h2>
		<input type="button" value="查看个人信息" onclick="getUserSomeInfo()" />
		<h2>修改个人信息</h2>
		<span>家长姓名</span><input type="text" id="parentName" /><br/>
		<span>家长手机</span><input type="text" id="parentTel" /><br/>
		<span>宝宝姓名</span><input type="text" id="babyName" /><br/>
		<span>宝宝性别</span><input type="text" id="babySex" /><br/>
		<span>出生日期</span><input type="text" id="babyBirthday" /><br/>
		<span>省</span><input type="text" id="userProvince" /><br/>
		<span>市</span><input type="text" id="userCity" /><br/>
		<span>区</span><input type="text" id="userArea" /><br/>
		<span>街道</span><input type="text" id="userStreet" /><br/>
		<span>详细地址</span><input type="text" id="detailAddress" /><br/>
		<span>经度</span><input type="text" id="userLng" /><br/>
		<span>纬度</span><input type="text" id="userLat" /><br/>
		<input type="button" value="修改" onclick="changeUserInfo()" />
	</div>
</body>
</html>