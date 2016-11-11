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
	<script src="js/test.js" ></script>
    <title>医生圈</title>
</head>
<body>
	<div>
		<h2>获取所有医生圈顶部分类</h2>
		<input type="button" value="医生圈分类" onclick="getAllRingCategory()" />
	</div>
	<div>----------------------------------------------------------------------------</div>
	<div>
		<h2>点击某一个医生圈，获取该医生圈下面的所有子分类列表</h2>
		<span>获取分类的ID</span><input type="text" id="categoryId" /><br/>
		<input type="button" value="获取" onclick="getSomeCategory()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>订阅某一个医生圈</h2>
		<span>订阅的医生圈分类的ID</span><input type="text" id="recommendCategoryId" /><br/>
		<input type="button" value="订阅" onclick="recommendSomeCategory()" /><input type="button" value="获取详情" onclick="getSomeDoctorRingInfo()" />
		<input type="button" value="取消订阅" onclick="cancelSomeCategory()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>点击某个帖子</h2>
		<span>帖子的ID</span><input type="text" id="postInfoId" /><br/>
		<input type="button" value="获取帖子详情" onclick="somePostInfoDetail()" />
		<input type="button" value="点赞" onclick="giveStar()" />
		<input type="button" value="取消点赞" onclick="cancleStar()"/><br/>
		<span>回复的内容</span><input type="text" id="postContent" /><br/>
		<input type="button" value="回复" onclick="postReply()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>发帖</h2>
		<span>发帖的标题</span><input type="text" id="titleContent" /><br/>
		<span>文本框中内容</span><input type="text" id="textContent" /><br/>
		<span>图片BASE64码字符串</span><input type="text" id="imgContent" /><br/>
		<span>发帖所属医生圈ID</span><input type="text" id="newCategoryId" /><br/>
		<input type="button" value="发帖" onclick="addNewPostInfo()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>医生圈下面点击某一个标签进行筛选</h2>
		<span>医生圈的ID</span><input type="text" id="doctorRingId" />
		<span>选择的标签ID</span><input type="text" id="doctorRingLableId" />
		<input type="button" value="筛选" onclick="labelScreen()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>获取所有医生圈标签</h2>
		<span>获取所有医生圈标签</span><input type="button" value="获取所有医生圈标签" onclick="getAllDoctorRingLabels()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>医生端重构</h2>
		<span>测试医生端重构</span><input type="button" value="测试医生端重构" onclick="testDoctor()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>医生多点执业</h2>
		<span>测试医生多点执业</span><input type="button" value="测试医生多点执业" onclick="testClinic()" />
		<span>多点执业机构列表</span><input type="button" value="多点执业机构列表" onclick="testClinic_1()" />
		<span>我要下班</span><input type="button" value="我要下班" onclick="testClinic_3()" />
		<span>多点标签</span><input type="button" value="多点标签" onclick="testClinic_5()" />
	</div>

	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>家庭医生服务</h2>
		<span>初始化进入</span><input type="button" value="初始化" onclick="familyDoctor()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>机构业务管理</h2>
		<span>得到开展业务的机构列表</span><input type="button" value="机构列表" onclick="orgBusinessList()" />
		<span>选择机构登录</span><input type="button" value="登录" onclick="orgLogin()" />
	</div>

	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>家庭医生服务</h2>
		<span>初始化进入</span><input type="button" value="初始化" onclick="familyDoctor()" />
	</div>
	<div>-----------------------------------------------------------------------------</div>
	<div>
		<h2>机构业务管理</h2>
		<span>得到开展业务的机构列表</span><input type="button" value="机构列表" onclick="orgBusinessList()" />
		<span>选择机构登录</span><input type="button" value="登录" onclick="orgLogin()" />
		<span>儿保列表</span><input type="button" value="保列表" onclick="ochildList()" />
		<span>计免列表</span><input type="button" value="计免表" onclick="jimianList()" />
		<span>疫苗列表</span><input type="button" value="疫苗列表" onclick="yimiaoList()" />
		<span>健康档案</span><input type="button" value="健康档案" onclick="saveHealth()" />
		<span>药品</span><input type="button" value="药品" onclick="drug()" />
		<span>身份信息</span><input type="button" value="身份信息" onclick="shenfen()" />
	</div>

</body>
</html>