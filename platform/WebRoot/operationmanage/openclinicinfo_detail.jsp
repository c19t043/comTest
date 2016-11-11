<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<html>
	<head>
		<title>开通的业务详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>
		
		<!-- ecTable -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>
		
		<!-- environment弹出窗口样式 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>
		
		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>
		
		<!-- util 工具 js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
  		
  		<script type="text/javascript">
	  		window.addEvent('domready', function(){
				new FormCheck('form_add',{
					display:{
						showErrors:1
					}
				});
				
			});
			
  		</script>
  	</head>
  
  	<body>
  		<div id="wz">
			<ap:step></ap:step>
		</div>
  		
		<form id="form_add" class="form" action='' method="post">
			<input type="hidden" name="openClinicInfo.id" id="openClinicInfo.id" value="${openClinicInfo.id}">
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开放日期<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.openClinicDate" id="openClinicInfo.openClinicDate" value="${openClinicInfo.openClinicDate}" type="text" class="input1 Wdate validate['required']" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开展业务类型<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'门诊':'门诊','儿保':'儿保','计免':'计免','上门':'上门','培训':'培训','讲座':'讲座','约稿':'约稿','咨询':'咨询'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="openClinicInfo.businessType" value="openClinicInfo.businessType" />
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开通业务描述：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.openContent" id="openClinicInfo.openContent" type="text" class="validate['length[50]'] text3" size="40" value="${openClinicInfo.openContent}">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">医生<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="doctorInfo.id" id="doctorInfo.id" type="hidden" class="text3" size="40" value="${doctorInfo.id}">
						<input name="doctorInfo.doctorName" id="doctorInfo.doctorName" type="text" class="text3" size="40" value="${doctorInfo.doctorName}" readonly="readonly">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开放时间段<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input type="checkbox" onclick="doCheckedAm(this);" name="openClinicInfo.timeSlot" id="openClinicInfo.timeSlotAm" value="上午">上午&nbsp;&nbsp;<input type="checkbox" onclick="doCheckedPm(this);" name="openClinicInfo.timeSlot" id="openClinicInfo.timeSlotPm" value="下午">下午
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">是否有效<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'Y':'是','N':'否'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
						  		  name="openClinicInfo.isEffective" value="openClinicInfo.isEffective" />
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">上午医生助手：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="amDoctorHelperUser.userId" id="amDoctorHelperUser.userId" type="hidden" class="text3" size="40" value="${amDoctorHelperUser.userId}">
						<input name="amDoctorHelperUser.userName" id="amDoctorHelperUser.userName" type="text" class="text3" size="40" value="${amDoctorHelperUser.userName}" readonly="readonly">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">上午推广者：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="amExtensionUser.userId" id="amExtensionUser.userId" type="hidden" class="text3" size="40" value="${amExtensionUser.userId}">
						<input name="amExtensionUser.userName" id="amExtensionUser.userName" type="text" class="text3" size="40" value="${amExtensionUser.userName}" readonly="readonly">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">下午医生助手：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="pmDoctorHelperUser.userId" id="pmDoctorHelperUser.userId" type="hidden" class="text3" size="40" value="${pmDoctorHelperUser.userId}">
						<input name="pmDoctorHelperUser.userName" id="pmDoctorHelperUser.userName" type="text" class="text3" size="40" value="${pmDoctorHelperUser.userName}" readonly="readonly">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">下午推广者：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="pmExtensionUser.userId" id="pmExtensionUser.userId" type="hidden" class="text3" size="40" value="${pmExtensionUser.userId}">
						<input name="pmExtensionUser.userName" id="pmExtensionUser.userName" type="text" class="text3" size="40" value="${pmExtensionUser.userName}" readonly="readonly">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
						&nbsp;&nbsp;
						<textarea name="openClinicInfo.remark" class="validate['length[1024]'] Added_textarea">${openClinicInfo.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
  	</body>
</html>
<script>
	function doCheckedAm(obj) {
		 if(obj.checked) {
		 	//alert("value: " + obj.value);
			$('amDoctorHelperUser.userName').disabled="";
			$('amExtensionUser.userName').disabled="";
			$('amDoctorHelperUser.userId').disabled="";
			$('amExtensionUser.userId').disabled="";
		 }
		 else {
			$('amDoctorHelperUser.userName').disabled="disabled";
			$('amExtensionUser.userName').disabled="disabled";
			$('amDoctorHelperUser.userId').disabled="disabled";
			$('amExtensionUser.userId').disabled="disabled";
		 }
	}
	function doCheckedPm(obj) {
		if(obj.checked) {
			$('pmDoctorHelperUser.userName').disabled="";
			$('pmExtensionUser.userName').disabled="";
			$('pmDoctorHelperUser.userId').disabled="";
			$('pmExtensionUser.userId').disabled="";
		 }
		 else {
			$('pmDoctorHelperUser.userName').disabled="disabled";
			$('pmExtensionUser.userName').disabled="disabled";
			$('pmDoctorHelperUser.userId').disabled="disabled";
			$('pmExtensionUser.userId').disabled="disabled";
		 }
	}
	
	function initCheckbox() {
		$('amDoctorHelperUser.userName').disabled="disabled";
		$('amExtensionUser.userName').disabled="disabled";
		$('amDoctorHelperUser.userId').disabled="disabled";
		$('amExtensionUser.userId').disabled="disabled";
		
		$('pmDoctorHelperUser.userName').disabled="disabled";
		$('pmExtensionUser.userName').disabled="disabled";
		$('pmDoctorHelperUser.userId').disabled="disabled";
		$('pmExtensionUser.userId').disabled="disabled";
		var timeSlotStr = "${openClinicInfo.timeSlot}";
		var timeSlotArray = timeSlotStr.split(", ");
		for (var i=0; i<timeSlotArray.length; i++) {
			if ("上午"==timeSlotArray[i]) {
				$('openClinicInfo.timeSlotAm').checked="checked";
				$('amDoctorHelperUser.userName').disabled="";
				$('amExtensionUser.userName').disabled="";
				$('amDoctorHelperUser.userId').disabled="";
				$('amExtensionUser.userId').disabled="";
			}
			if ("下午"==timeSlotArray[i]) {
				$('openClinicInfo.timeSlotPm').checked="checked";
				$('pmDoctorHelperUser.userName').disabled="";
				$('pmExtensionUser.userName').disabled="";
				$('pmDoctorHelperUser.userId').disabled="";
				$('pmExtensionUser.userId').disabled="";
			}
		}
	}
	
	//checkbox初始化
	initCheckbox();
	
</script>