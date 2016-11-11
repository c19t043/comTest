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
		<title>咨询订单列表</title>
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

		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			// 新增信息 
			function doAdd(action) {
				window.location.href=action;
			}
			// 编辑信息 
			function doEdit(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					showMsg("至少选择一项！");
					return;
				}else if(items.length > 1){
					showMsg("只能选择一项进行编辑！");
					return;
				}
				var id = "";
				items.each(function(item){
					id = item.value;
				});
				window.location.href=action + "?consultDoctorInfo.id="+id;
			}
  		</script>
	</head>

	<body>
	<s:actionmessage theme="popwind"/>
	<ap:step></ap:step>
<%-- 	<div id="wz">
		<ap:step></ap:step>
		<div class="wz_right">
			<div class="but01">
				<span id="familyInfo"><span>
				<div class="pop_button_bar">
					<span><a href="javascript:doBack();" class="pop_button_green bt_re">返回</a></span>
				</div>
			</div>
		</div>
	</div> --%>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="/consultmanager/consultdoctormanager/toListPage.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="10%" height="41" align="right">用户电话：</td>
								<td width="10%" align="left">
									<input type="text" name="consultOrderInfo.userInfo.phone" 
									value="${consultOrderInfo.userInfo.phone }" class="text3">
								</td>
							
								<td width="10%" height="41" align="right">用户姓名：</td>
								<td width="10%" align="left">
									<input type="text" name="consultOrderInfo.userInfo.parentName" 
									value="${consultOrderInfo.userInfo.parentName }" class="text3">
								</td>
								
								<td width="10%" height="41" align="right">医生姓名：</td>
								<td width="10%" align="left">
									<input type="text" name="consultOrderInfo.consultDoctorInfo.doctorInfo.doctorName"  
									value="${consultOrderInfo.consultDoctorInfo.doctorInfo.doctorName }" class="text3">
								</td>
								
								<%--<td width="10%" height="41" align="right">下单时间：</td>
								<td width="10%" align="left">
									<input type="text" name="consultOrderInfo.orderTime"
									onfocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});" 
									value="${consultOrderInfo.orderTime }" class="text3">
								</td>
								
								 <td width="10%" height="41" align="right">订单状态：</td>
								<td width="10%" align="left">
									<select name="consultOrderInfo.orderStatus" style="width:170px;">
											<option></option>
											<option value="已付款" <c:if test="${consultOrderInfo.orderStatus == '已付款' }">selected="selected"</c:if>>已付款</option>
											<option value="待评价" <c:if test="${consultOrderInfo.orderStatus == '待评价' }">selected="selected"</c:if>>待评价</option>
											<option value="已评价" <c:if test="${consultOrderInfo.orderStatus == '已评价' }">selected="selected"</c:if>>已评价</option>
									</select>
								</td> --%>
								
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
								action="${pageContext.request.contextPath}/consultmanager/consultdoctormanager/toListPage.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
									<com.java.ec:column property="null" title="用户电话" width="10%">
										${p.userInfo.phone}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="用户姓名" width="10%">
										${p.userInfo.parentName}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="医生姓名" width="10%">
										${p.consultDoctorInfo.doctorInfo.doctorName}
									</com.java.ec:column>
									<%--<com.java.ec:column property="totalPrice" title="服务总价" width="10%"/>
									<com.java.ec:column property="useRemainBalance" title="使用余额" width="10%"/>
									<com.java.ec:column property="payMethod" title="支付方式" width="10%"/> --%>
									<com.java.ec:column property="orderTime" title="下单时间" width="10%"/>
									<com.java.ec:column property="effectiveStartTime" title="有效开始时间" width="10%"/>
									<com.java.ec:column property="effectiveEndTime" title="有效结束时间(购买后24小时)" width="10%"/>
									<%-- <com.java.ec:column property="orderStatus" title="订单状态" width="10%"/> --%>
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
