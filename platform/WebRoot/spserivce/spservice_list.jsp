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
		<title>苏坡社区挂号排班列表</title>
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

		<!-- ecTable列表 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>

		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			function syncRegister(){
				$j.ajax({
					url:"${pageContext.request.contextPath}/SpInterfaceService/querySpAppointmentSchedules.action",
					type:"POST",
					async:false,
					success:function(result){
						if(result.msg){
							alert(result.msg);
							window.location.reload();
						}
					}
				});
			}
			function syncDoctorInfos(){
				$j.ajax({
					url:"${pageContext.request.contextPath}/SpInterfaceService/queryDoctorInfos.action",
					type:"POST",
					async:false,
					success:function(result){
						if(result.msg){
							alert(result.msg);
						}
					}
				});
			}
  		</script>
	</head>

	<body>
	 <s:actionmessage theme="popwind"/>
 	<div id="wz">
		<ap:step></ap:step>
		<div class="wz_right">
			<div class="but01">
				<div class="pop_button_bar">
					<span><a href="javascript:syncRegister();" class="pop_button_blue bt_op">同步苏坡挂号排班信息</a></span>
					<%-- <span><a href="javascript:syncDoctorInfos();" class="pop_button_red bt_op">同步医生信息</a></span> --%>
				</div>
			</div>
		</div>
	</div>
<%-- 	<s:actionmessage theme="popwind"/>
	<ap:stepAndOperation></ap:stepAndOperation> --%>

		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="/SpInterfaceService/queryRegisterSchedule.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
							
								<td width="10%" height="41" align="right">机构名称：</td>
								<td width="10%" align="left">
									<select name="spAppointmentSchedule.orgID" style="width:200px">
										<option ></option>
										<option value="3d715fb3-5fd4-4f36-9be9-7cca29de01ca"<c:if test="${spAppointmentSchedule.orgID == '3d715fb3-5fd4-4f36-9be9-7cca29de01ca' }">selected="selected"</c:if>>苏坡社区卫生服务中心</option>
										<option value="1cc69208-1d1d-43f8-925b-39fc437e320d"<c:if test="${spAppointmentSchedule.orgID == '1cc69208-1d1d-43f8-925b-39fc437e320d' }">selected="selected"</c:if>>成都市青羊区第九人民医院</option>
									</select>
								</td>
								
								<td width="10%" height="41" align="right">科室名字：</td>
								<td width="10%" align="left">
									<select name="spAppointmentSchedule.depName" style="width:150px">
										<option ></option>
										<option value="儿科"<c:if test="${spAppointmentSchedule.depName == '儿科' }">selected="selected"</c:if>>儿科</option>
										<option value="儿保室"<c:if test="${spAppointmentSchedule.depName == '儿保室' }">selected="selected"</c:if>>儿保室</option>
										<%-- <option value="培风新区全科"<c:if test="${spAppointmentSchedule.depName == '培风新区全科' }">selected="selected"</c:if>>培风新区全科</option> --%>
									</select>
								</td>
								<td width="10%" height="41" align="right">医生名称：</td>
								<td width="10%" align="left">
									<select name="spAppointmentSchedule.doctor" style="width:200px">
										<option ></option>
										<c:forEach items="${doctorNames }" var="v">
											<option value="${v }"<c:if test="${spAppointmentSchedule.doctor == v }">selected="selected"</c:if>>${v }</option>
										</c:forEach>
									</select>
								</td>
								
								<td width="30%" align="left">
									<a href="javascript:document.getElementById('query_list_form').submit();">
										<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/serch21.gif" width="65" height="23" border="0">
									</a>
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
							<com.java.ec:table items="list" var="p" tableId="list" border="0"
								action="${pageContext.request.contextPath}/SpInterfaceService/queryRegisterSchedule.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
									<com.java.ec:column property="orgName" title="机构名称" width="10%"/>
									<com.java.ec:column property="registerNO" title="安排号" width="10%"/>
									<com.java.ec:column property="depName" title="科室名称" width="10%"/>
									<com.java.ec:column property="doctor" title="医生名称" width="10%"/>
									<com.java.ec:column property="exTime" title="出诊时间" width="10%"/>
									<com.java.ec:column property="money" title="金额" width="10%"/>
									<com.java.ec:column property="optime" title="操作时间" width="10%"/>
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
