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
		<title>测评套题-年龄</title>
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
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doAdd();" class="pop_button_blue">添加</a></span>
						<span><a href="javascript:doEdit();" class="pop_button_green">编辑</a></span>
					</div>
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
					<s:form action="/AsqTest/getAsqQuestionsList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">问卷显示名称：</td>
								<td width="12%" align="left">
									<input name="asqQuestions.asqTaotiAge.showName"  type="text" class="input1" 
									value="${asqQuestions.asqTaotiAge.showName}"/>		
								</td>
								
								<td width="8%" height="41" align="right">套题名称：</td>
								<td width="12%" align="left">
									<input name="asqQuestions.asqTaoti.parent.titalName"  type="text" class="input1" 
									value="${asqQuestions.asqTaoti.parent.titalName}"/>		
								</td>
								
								<td width="8%" height="41" align="right">题目：</td>
								<td width="12%" align="left">
									<input name="asqQuestions.asqTaoti.titalName"  type="text" class="input1" 
									value="${asqQuestions.asqTaoti.titalName}"/>		
								</td>
								
								<td width="8%" height="41" align="right">问题内容：</td>
								<td width="12%" align="left">
									<input name="asqQuestions.subject"  type="text" class="input1" 
									value="${asqQuestions.subject}"/>		
								</td>
								
								<td width="19%" align="left">
									<a href="javascript:document.getElementById('query_list_form').submit();"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/serch21.gif" width="65" height="23" border="0"></a>
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
								action="${pageContext.request.contextPath}/AsqTest/getAsqQuestionsList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row >
									<com.java.ec:column property="id" alias="id_radio" cell="radio" title="选择"  width="5%" />
									<com.java.ec:column property="null" title="问卷显示名称" width="10%">
										${p.asqTaotiAge.showName }
									</com.java.ec:column>
									<com.java.ec:column property="null" title="套题名称" width="10%">
										${p.asqTaoti.parent.titalName }
									</com.java.ec:column>
									<com.java.ec:column property="showName" title="题目" width="10%">
										${p.asqTaoti.titalName }
									</com.java.ec:column>
									<com.java.ec:column property="subject" title="问题内容" width="10%"/>
									<com.java.ec:column property="null" title="问题类型" width="10%">
										<c:if test="${p.subjecttype == '0' }">单选</c:if>
										<c:if test="${p.subjecttype == '1' }">多选</c:if>
									</com.java.ec:column>
									<com.java.ec:column property="sort" title="排序" width="10%"/>
									<com.java.ec:column property="null" title="选项内容" width="10%">
										${p.optionContents }
									</com.java.ec:column>
									<com.java.ec:column property="null" title="是否删除" width="10%">
										<c:if test="${p.isdelete == '0' }">未删除</c:if>
										<c:if test="${p.isdelete == '1' }">已删除</c:if>
									</com.java.ec:column>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			$j = jQuery.noConflict();
			// 新增信息 
			function doAdd(action) {
				action="${pageContext.request.contextPath}/AsqTest/toAddOfAsqQuestions.action";
				window.location.href=action;
			}
			// 编辑信息 
			function doEdit(action) {
				action="${pageContext.request.contextPath}/AsqTest/toAddOfAsqQuestions.action";
				var $checked = $j("input:checked");
				var items = EcTable.getRadioItem();
				if(items == ""){
					showMsg("请选择一项待修改的记录！");
					return;
				}
				window.location.href=action + "?asqQuestions.id="+$checked.val();
			}
		</script>
  	</body>
</html>
