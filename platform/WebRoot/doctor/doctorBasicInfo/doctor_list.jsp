<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>医生列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>

		<!-- environment弹出窗口样式 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>

		<!-- ecTable -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>

		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>

		<!-- util 工具 js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>	
	</head>

	<body>
	<s:actionmessage theme="popwind"/>
	<ap:stepAndOperation></ap:stepAndOperation>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="doctor/doctorManager/getDoctorInfoList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">医生姓名：</td>
								<td width="12%" align="left">
									<input name="doctorInfo.doctorName" id="doctorName" type="text" class="input1" value="${doctorInfo.doctorName}"/>		
								</td>
								
								<td width="8%" height="41" align="right">医生电话：</td>
								<td width="12%" align="left">
									<input name="doctorInfo.doctorPhone" type="text" class="input1" value="${doctorInfo.doctorPhone}"/>		
								</td>
								
								<td width="8%" height="41" align="right">认证状态：</td>
								<td width="12%" align="left">
									<s:select list="#{'已申请':'已申请','已通过':'已通过','未通过':'未通过'}"
									headerKey="" headerValue=""
									listKey="key" listValue="value"
									theme="simple"
									name="doctorInfo.authentication" value="doctorInfo.authentication"
									></s:select>	
								</td>
								
								<td width="8%" height="41" align="right">流程状态：</td>
								<td width="12%" align="left">
									<s:select list="#{'已提交':'已提交','已通过':'已通过','已驳回':'已驳回'}"
									headerKey="" headerValue=""
									listKey="key" listValue="value"
									theme="simple"
									name="doctorInfo.flowStatus" value="doctorInfo.flowStatus"
									></s:select>	
								</td>
								
								<td width="19%" align="left">
									<a href="javascript:document.getElementById('query_list_form').submit();"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/serch21.gif" width="65" height="23" border="0"></a>
									<a href="javascript:void();" onclick="orderGather()">开单汇总</a>
								</td>
							</tr>
						</table>
					</s:form>
				</td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr>
				<td>
					<div class="eXtremeTable">
						<s:if test="#request.list.size>0">
							<com.java.ec:table items="list" var="o" tableId="list" border="0"
								action="${pageContext.request.contextPath}/doctor/doctorManager/getDoctorInfoList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_radio" cell="radio" title="选择"  width="5%" />
									<com.java.ec:column property="doctorName" title="医生姓名" width="5%"/>
									<com.java.ec:column property="doctorSex" title="性别" width="5%"/>
									<com.java.ec:column property="doctorTitle" title="职称" width="5%"/>
									<com.java.ec:column property="doctorPhone" title="手机号" width="5%"/>
									<com.java.ec:column property="doctorEmployer" title="工作单位" width="10%"/>
									<com.java.ec:column property="authentication" title="认证状态" width="5%"/>
									<com.java.ec:column property="registerTime" title="注册时间" width="10%"/>
									<com.java.ec:column property="flowStatus" title="流程状态" width="10%"/>
									<com.java.ec:column property="doctorRegisterMaintenance" title="维护人" width="10%"/>
									<%-- <com.java.ec:column property="null" title="操作" width="10%">
										<a href="javascript:void();" onclick="doDetail('${o.id}')">分配维护人</a>
									</com.java.ec:column> --%>
								</com.java.ec:row>
							</com.java.ec:table>
						</s:if>
						<s:else>
							<styles:nolist/>
						</s:else>
					</div>
				</td>
			</tr>
		</table>
  	</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict();
	function orderGather(){
		$j.ajax({
			url:"${pageContext.request.contextPath}/doctorManager/jsonOp/doctorJson_doctorOrderGather.action",
			type:"post",
			success:function(data){
				alert(data);
			},
			error:function(){
				alert('调用失败');
			}
		});
	}
	// 分配维护人
	function doAllot(action) {
		//获取要选择的医生记录
		var $checked = $j("input:checked");
		var items = EcTable.getRadioItem();
		if(items == ""){
			showMsg("请选择一项待修改的记录！");
			return;
		}
		//分配维护人
 		var page2page = "ID="+$checked.val();  //把这个page2page传到另外一个页面
 		environment.dialog.open({
			url : '${pageContext.request.contextPath}/doctor/doctorBasicInfo/doctor_select_main.jsp?' + page2page, 
			width : window.getCoordinates().width-300,
		    height : window.getCoordinates().height-50,
			icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
			title : '分配维护人'
		});
	}
	
	// 新增信息 
	function doAdd(action) {
		window.location.href=action+"?action=initDoctorInfoPage&pageFlag=save";
	}
	//医生数据审核
	function doctorDataVerify(action){
		var $checked = $j("input:checked");
		var items = EcTable.getRadioItem();
		if(items == ""){
			showMsg("请选择一项待修改的记录！");
			return;
		}
		window.location.href=action+"?action=initDoctorInfoPage&pageFlag=verify&doctorInfo.id="+$checked.val();
	}
	// 编辑信息 
	function doEdit(action) {
		var $checked = $j("input:checked");
		var items = EcTable.getRadioItem();
		if(items == ""){
			showMsg("请选择一项待修改的记录！");
			return;
		}
		window.location.href=action+"?action=initDoctorInfoPage&pageFlag=update&doctorInfo.id="+$checked.val();
	}
</script>