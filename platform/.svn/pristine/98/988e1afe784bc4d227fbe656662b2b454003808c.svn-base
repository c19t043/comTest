<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>选择医生列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/TabPanel.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>
		
		<!-- ecTable列表 -->
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
		
		<script type="text/javascript">
			environment.loader.loadJavaScript("${pageContext.request.contextPath}/environment/themes/default/resource/js/SimpleUI/SimpleUI.js");
			environment.loader.loadStyleSheet("${pageContext.request.contextPath}/environment/themes/default/resource/skin/default/SimpleUI/css/SimpleUI.css");
			
			window.addEvent('domready', function(){
				var allItems = EcTable.getAllRadioItems();
				var cheak = parent.datas.id;
				if(cheak!=null&&cheak.length>0){
					for(var i=0;i<allItems.length;i++){
						if(allItems[i].value==cheak){
							allItems[i].checked=true;
							break;
						}
					}
				}
			});
			
			// window模态框选择
			function select(id,name){
				var arg = window.dialogArguments;
				var dc = arg.window.document;
				dc.getElementById(arg.text).value = name;
				dc.getElementById(arg.hidden).value = id;
				window.close();
			}
			
			// environment新型弹出框
			function select2(id,name){
				var text = window.parent.getTextName();
				var hidden = window.parent.getHeddinId();
				environment.dialog.getOpener().$(text).value = name;
				environment.dialog.getOpener().$(hidden).value = id;
				environment.dialog.close();
			}
			
		</script>
	</head>
	<base target="_self">
	<body>
		<s:actionmessage theme="popwind"/>
		<div id="wz">
			<div class="wz_left">
				<div class="wz_ico">
					<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/wz_ico.gif" height="12" width="13">
				</div>
				<div class="wz_text">
				您所在的位置：<b>选择医生</b>
				</div>
			</div>
		</div>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="operationmanage/selectSingleDoctorList.action" name="select_form" id="select_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">医生姓名：</td>
								<td width="12%" align="left">
									<input type="text" name="doctorInfo.doctorName" id="doctorInfo.doctorName" value="${doctorInfo.doctorName}">
								</td>
								<td width="19%" align="left">
									<a href="javascript:document.getElementById('select_form').submit();"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/serch21.gif" width="65" height="23" border="0"></a>
								</td>
							</tr>
						</table>
					</s:form>
				</td>
			</tr>
		</table>
		<div class="eXtremeTable">
			<c:if test="${list!=null && list!='[]'}">
				<com.java.ec:table items="list" var="d" tableId="list" border="0" 
					action="${pageContext.request.contextPath}/operationmanage/selectSingleDoctorList.action"
					imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
					width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit" 
					filterable="false" autoIncludeParameters="true" sortable="false">
					<com.java.ec:row onclick="parent.insertData('${d.id}','${d.doctorName}');">	
						<com.java.ec:column property="id" alias="id_radio" title="选择" cell="radio" width="10%"/>
						<com.java.ec:column property="doctorName" title="医生姓名" />
						<com.java.ec:column property="null" title="选 择" sortable="false">
							<button class="btn_2k3" onclick="select2('${d.id}','${d.doctorName}');">选 择</button>
						</com.java.ec:column>
					</com.java.ec:row>
				</com.java.ec:table>
			</c:if>
			<c:if test="${list=='[]'}">
				<styles:nolist/>
			</c:if>
		</div>
	</body>
</html>