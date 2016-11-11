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
	<script type="text/javascript" src="js/helperLHH.js"></script>
    <title>助手</title>
</head> 
<body>
	<div>
		<h2>快捷咨询</h2>
		<span>排序方式</span><input type="text" id="sortMethod" />(sortMethod=”welcome”(最受欢迎)、 expert（专家级别）、isOnline（是否在线）)<br/>
		<input type="button" value="获取咨询医生列表" onclick="getConsultationDoctor()" />
	</div>
	
	<div>
		<h2>点击某个医生进行咨询</h2>
		<span>待咨询医生的ID</span><input type="text" id="doctorId" /><br/>
		<input type="button" value="咨询医生" onclick="consulationSomeDoctor()"/>
	</div>
	
	<div>
		<h2>选择症状标签、输入症状描述、选择上传图片</h2>
		<form action="userConsultDoctorManage.action?action=addNewTags" type="post" onsubmit="checkIds()" enctype="multipart/form-data" >
			<span>选择的症状标签ID集合，按照::进行隔开的</span><input type="text"  name="tagIds" /><br/>
			<span>症状的描述</span><input type="text" name="description" /><br/>
			<span>医生的ID</span><input type="text" name="doctorId" />
			<span>图片</span><img style="width:154px;height:86px;position:relative;" src="" alt="上传图片" onclick="productSmallFileElem.click()" id="imgup1" /><br/>
			<input type="file" id="productSmallFileElem" name="productSmallFileElem" accept="image/*" onchange="handleFiles(this,'imgup1')" style="display:none;" /><br/>
			<input type="submit" value="提交"/>
		</form>
	</div>
	
	<div>
		<h2>历史咨询记录列表</h2>
		<span>医生的ID</span><input type="text" id="choseDoctorId" /><br/>
		<span>儿保前还是儿保后的咨询</span><input type="text" id="typeMethod" /><br/>
		<p>儿保前咨询为before,儿保后的咨询为after</p>
		<input type="button" value="历史咨询记录" onclick="histConsult()" />
	</div>
	
	<div>
		<h2>向医生进行咨询</h2>
		<span>儿保前还是儿保后的咨询</span><input type="text" id="method" /><br/>
		<span>咨询医生的Id</span><input type="text" id="newDoctorId" /><br/>
		<span>输入的咨询内容</span><input type="text" id="content" /><br/>
		<span>消息的类型</span><input type="text" id="msgType" /><br/>
		<span>最后一条咨询信息的ID</span><input type="text" id="msgId" />
		<input type="button" value="咨询" onclick="addNewConsult()"/>
	</div>
	
	<div>
		<h2>结束咨询</h2>
		<span>会话的ID</span><input type="text" id="logId" ><br/>
		<span>医生的ID</span><input type="text" id="endDoctorId" /><br/>
		<span>儿保前还是儿保后咨询</span><input type="text" id="endMethod" /><br/>
		<input type="button" value="结束" onclick="endOfCal()" />
	</div>
	
	<div>
		<h2>健康档案</h2>
		<input type="button" value="获取健康档案" onclick="healthArchive()" />
	</div>
	
	<div>
		<h2>成长记录</h2>
		<div>
			<h3>获取成长记录列表</h3>
			<input type="button" value="成长记录" onclick="getGrowHis()" />
		</div>
		<div>
		<h3>添加新的成长记录</h3>
		<form action="growthRecordManage.action?action=add" type="post" onsubmit="" enctype="multipart/form-data" >
			<span>睡眠</span><input type="text"  name="sleepHour" /><br/>
			<span>排便</span><input type="text" name="defecateTimes" /><br/>
			<span>母乳</span><input type="text" name="BreastfeedingTimes" /><input type="text" name="everyBreastfeeding" /><br/>
			<span>辅食</span><input type="text" name="assistFoodsTimes" /><br/>
			<span>运动</span><input type="text" name="assistFoodsTimes" /><br/>
			<span>图片</span><img style="width:154px;height:86px;position:relative;" src="" alt="上传图片" onclick="productSmallFileElem2.click()" id="imgup1" /><br/>
			<input type="file" id="productSmallFileElem2" name="productSmallFileElem2" accept="image/*" onchange="handleFiles(this,'imgup1')" style="display:none;" /><br/>
			<input type="submit" value="提交"/>
		</form>
	</div>
	</div>
	
	<div>
		<h2>身高、体重、头围记录</h2>
		<input type="button" value="进入项目" onclick="getBabtSexAndYear()" /><br/>
		<span>身高(height)、头重(weight)、头围(head)</span><input type="text" id="recordType" /><br/>
		<span>数值：</span><input type="text" id="recordValue" /><br/>
		<input type="button" value="记录" onclick="addNewRecord()" /><input type="button" value="历史数据" oncanplay="getHistRecord()"/>
	</div>
	
	<div>
		<h2>病历夹</h2>
		<input type="button" value="病历夹历史记录" onclick="getHistAche()" /><br/>
		<input type="button" value="获取症状标签" onclick="getAllTags()" /><br/>
	</div>
	
	<div>
		<form action="caseClipManage.action?action=add" type="post" onsubmit="" enctype="multipart/form-data" >
			<span>症状标签ID组成的字符串（::隔开）</span><input type="text" name="tagIdsStr" /><br/>
			<span>上传图片的类型</span><input type="text" name="picType" /><br/>
			<span>图片</span><img style="width:154px;height:86px;position:relative;" src="" alt="上传图片" onclick="productSmallFileElem3.click()" id="imgup1" /><br/>
			<input type="file" id="productSmallFileElem3" name="productSmallFileElem3" accept="image/*" onchange="handleFiles(this,'imgup1')" style="display:none;" /><br/>
			<input type="submit" value="提交"/>
		</form>
	</div>
	
	<div>
		<h2>健康计划</h2>
		<input type="button" value="历史健康计划" onclick="getHealthPlanHist()" />
		<h2>执行今日健康计划</h2>
		<span>执行健康计划的ID：</span><input type="text" id="planId" /><br/>
		<span>执行健康计划路径的ID：</span><input type="text" id="pathId" /><br/>
		<input type="button" value="执行" onclick="confirmPlan()" />
	</div>
	
</body>
</html>