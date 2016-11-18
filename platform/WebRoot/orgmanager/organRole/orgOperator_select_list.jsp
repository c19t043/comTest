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
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="/orgOperatorManager/orgRoleManager.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">显示名称：</td>
								<td width="12%" align="left">
									<input name="organOperator.showName"  type="text" class="input1" value="${organOperator.showName}"/>		
								</td>
								<td width="8%" height="41" align="right">登陆名：</td>
								<td width="12%" align="left">
									<input name="organOperator.loginName"  type="text" class="input1" value="${organOperator.loginName}"/>		
								</td>
								<td width="8%" height="41" align="right">机构名称：</td>
								<td width="12%" align="left">
									<input name="organOperator.hospitalBasicInfo.hospitalLname"  type="text" class="input1" value="${organOperator.hospitalBasicInfo.hospitalLname}"/>
								</td>
								<td width="8%" height="41" align="right">是否可用：</td>
								<td width="12%" align="left">
									<s:select list="#{'Y':'Y','N':'N' }"
									headerKey="" headerValue=""
									listKey="key" listValue="value" theme="simple"
									name="organOperator.isEnable" value="organOperator.isEnable" cssStyle="width:150px"
									></s:select>
								</td>
								<td width="19%" align="left">
									<input name="action"  type="hidden" class="input1" value="toOperatorPage"/>
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
								action="${pageContext.request.contextPath}/orgOperatorManager/orgRoleManager.action?action=toOperatorPage"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row onclick="parent.insertObject('${p.id}','${p.showName}')">
									<com.java.ec:column property="id" alias="id_radio" cell="checkbox" title="选择"  width="5%" />
									<com.java.ec:column property="showName" title="显示名称" width="10%"/>
									<com.java.ec:column property="loginName" title="登陆名" width="10%"/>
									<com.java.ec:column property="null" title="机构名称" width="10%">
										${p.hospitalBasicInfo.hospitalLname}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="拥有角色" width="10%">
										<c:forEach items="${p.organRoles}" var="v">
											${v.roleName},
										</c:forEach>
									</com.java.ec:column>
									<com.java.ec:column property="homePageUrl" title="机构登陆首页地址" width="10%"/>
									<com.java.ec:column property="isEnable" title="是否可用" width="10%"/>
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
			var $now_checkedArr = $j("input:checkbox");
			var parent_checkedArr = parent.objectList;
			for(var i=0,len=parent_checkedArr?parent_checkedArr.length:0;i<len;i++){
				$now_checkedArr.each(function(){
					if($j(this).val()==parent_checkedArr[i].id){
						$j(this).prop("checked",true);return false;
					}
				});
			}
		</script>
  	</body>
</html>
