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
		<title>编辑客户</title>
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
				new FormCheck('form_edit',{
					display:{
						showErrors:1
					}
				});
				Util2.selectDistrict("customer.district.districtName","customer.district.districtName","customer.district.districtCode");
			});
			
			// 保存
			function doSave() {
				$('save').click();
			}
			// 返回
		    function doBack(){
		    	var action = '<s:url namespace="/operationmanage" action="customerList" includeParams="true"/>';
			 	window.location.href = action ;
			}
			
  		</script>
  	</head>
  
  	<body>
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doSave();" class="pop_button_blue">更新</a></span>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
  		
		<form id="form_edit" class="form" action='<s:url namespace="/operationmanage" action="updateingCustomer" includeParams="true"/>' method="post">
			<input name="customer.id" id="customer.id" type="hidden" value="${customer.id}">
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
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">家长姓名<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="customer.parentName" id="customer.parentName" type="text" class="validate['required','length[20]'] text3" size="40" value="${customer.parentName}">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">宝宝姓名<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="customer.babyName" id="customer.babyName" type="text" class="validate['required','length[20]'] text3" size="40" value="${customer.babyName}">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">宝宝性别<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'男':'男','女':'女'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="customer.babySex" value="customer.babySex" />
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">宝宝生日<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="customer.birthday" id="customer.birthday" type="text" value="${customer.birthday}" class="input1 Wdate validate['required']" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">手机号码<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="customer.phone" id="customer.phone" type="text" class="validate['required','length[20]'] text3" size="40" value="${customer.phone}">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">用户地址：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="customer.address" id="customer.address" type="text" class="validate['length[200]'] text3" size="40" value="${customer.address}">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">来源<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'线上活动':'线上活动','线下活动':'线下活动','社区门诊':'社区门诊','会议':'会议','讲座':'讲座','其他':'其他'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="customer.source" value="customer.source" />
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">所属社区：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="hospitalBasicInfoList" headerKey="" headerValue="--请选择--" listKey="id" listValue="hospitalLname" cssStyle="width:200px;" theme="simple"
						  		  name="customer.hospitalBasicInfo.id" value="customer.hospitalBasicInfo.id"/>
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">所属行政区划：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="customer.district.districtCode" id="customer.district.districtCode" type="hidden" class="text3" size="40" value="${customer.district.districtCode}">
						<input name="customer.district.districtName" id="customer.district.districtName" type="text" class="text3" size="40" value="${customer.district.districtName}" readonly="readonly">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<textarea name="customer.remark" class="validate['length[500]'] Added_textarea">${customer.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
  	</body>
</html>
