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
		<title>客户列表</title>
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
		
		<script type="text/javascript">
			window.addEvent('domready', function(){
				
			});
  		</script>
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
					<s:form action="operationmanage/customerList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">来源：</td>
								<td width="12%" align="left">
									<s:select list="#{'线上活动':'线上活动','线下活动':'线下活动','社区门诊':'社区门诊','会议':'会议','讲座':'讲座','其他':'其他'}" headerKey="" headerValue="请选择" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  			  name="customer.source" value="customer.source" />		
								</td>
								<td width="8%" height="41" align="right">家长姓名：</td>
								<td width="12%" align="left">
									<input name="customer.parentName" id="customer.parentName" type="text" class="input1" value="${customer.parentName}"/>		
								</td>
								<td width="8%" height="41" align="right">宝宝姓名：</td>
								<td width="12%" align="left">
									<input name="customer.babyName" id="customer.babyName" type="text" class="input1" value="${customer.babyName}"/>		
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
							<com.java.ec:table items="list" var="o" tableId="list" border="0"
								action="${pageContext.request.contextPath}/operationmanage/customerList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_radio" cell="radio" title="选择"  width="5%" />
									<com.java.ec:column property="parentName" title="家长姓名" width="5%"/>
									<com.java.ec:column property="babyName" title="宝宝姓名" width="5%"/>
									<com.java.ec:column property="babySex" title="宝宝性别" width="5%"/>
									<com.java.ec:column property="birthday" title="宝宝生日" width="10%"/>
									<com.java.ec:column property="babyMonth" title="宝宝月龄" width="5%"/>
									<com.java.ec:column property="phone" title="手机号码" width="10%"/>
									<com.java.ec:column property="source" title="来源" width="10%"/>
									<com.java.ec:column property="null" title="所属社区" width="15%">
										${not empty o.hospitalBasicInfo ? o.hospitalBasicInfo.hospitalLname : "-"}
									</com.java.ec:column>
									<com.java.ec:column property="address" title="用户地址" width="10%"/>
									<com.java.ec:column property="null" title="行政区划" width="10%">
										${not empty o.district ? o.district.districtName : "-"}
                                    </com.java.ec:column>
                                    <com.java.ec:column property="remark" title="备注" width="10%"/>
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
<script type="text/javascript">
	// 显示详情 
	function doDetail(id) {
		var date = new Date();
		var action = '<s:url namespace="/operationmanage" action="customerDetail" includeParams="true"/>';
		environment.dialog.open({
		url : action+'?customer.id=' + id + '&_t=' + date.getTime(),
		width : window.getCoordinates().width-300,
		height : window.getCoordinates().height-20,
		icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
		title : '信息详情'
	  });
	}
	// 新增信息 
	function doAdd(action) {
		window.location.href=action;
	}
	
	// 编辑信息 
	function doEdit(action) {
		var items = EcTable.getRadioItem();
		if(items == ""){
			showMsg("请选择一项待修改的记录！");
			return;
		}
		window.location.href=action + "?customer.id="+items;
	}
</script>