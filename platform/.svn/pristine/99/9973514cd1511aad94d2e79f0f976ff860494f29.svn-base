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
		
		
		<!-- util 工具 js -->
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			window.addEvent('domready', function(){
				
			});
			// 显示详情 
/* 			function doDetail(id) {
				//var url="${pageContext.request.contextPath}/dataEntry/viewManagementOrg.action?managementOrg.id="+id;
				//window.showModalDialog(url,null,"dialogHeight:400px;dialogWidth:870px;status:off" );
			
// 				var date = new Date();
// 				var action = '<s:url namespace="/dataEntry" action="viewManagementOrg" includeParams="true"/>';
// 				action=action+'?managementOrg.id=' + id + '&_t=' + date.getTime();
// 				window.location.href=action;
				var action = '<s:url namespace="/familydoctor/fdRoleInfo" action="getFdRoleInfList" includeParams="true"/>';
				window.location.href=action + "?archivesInfo.id="+id;
			} */
			function doDetail(id) {
				var page2page = "userChildcareAppointmentInfo.id="+id;
		 		environment.dialog.open({
					url : '${pageContext.request.contextPath}/medicalorgandbusiness/toDetail.action?'
							+ page2page,
					width : window.getCoordinates().width-300,
				    height : window.getCoordinates().height-10,
					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
					title : '儿保订单详情'
				});
			}
			// 新增信息 
			function doAdd(action) {
				window.location.href=action;
			}
			function doBatchEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					showMsg("至少选择一项！");
					return;
				}
				var id = "";
				items.each(function(item){
					id += item.value+",";
				});
				id = id.substring(0,id.lastIndexOf(","));
				window.location.href=action+"?ids="+id;
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
				
				var parent_ = $j(items).parent().parent().find("option:selected");
				
				if(!parent_.val()){
					alert("请选择状态");
					return;
				}
				var	parent =parent_.text();
				window.location.href=action + "?userChildcareAppointmentInfo.id="+id+"&userChildcareAppointmentInfo.status="+parent;
			}
			
			$j(function(){
				$j("#orderType option").each(function(){
					 if($j(this).val()=='${userChildcareAppointmentInfo.orderType }')
						 $j(this).attr("selected","selected");
				});
				$j("#status option").each(function(){
					 if($j(this).val()=='${userChildcareAppointmentInfo.status }')
						 $j(this).attr("selected","selected");
				});
			})		
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
					<s:form action="/medicalorgandbusiness/toList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="10%" height="41" align="right">门诊时间：</td>
								<td width="10%" align="left">
									<input type="text" name="userChildcareAppointmentInfo.organChildcareOpenResources.openDate" 
									value="${userChildcareAppointmentInfo.organChildcareOpenResources.openDate }" class="text3"
									onfocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"
									>
								</td>
								
								<td width="10%" height="41" align="right">预约医生：</td>
								<td width="10%" align="left">
									<input type="text" name="userChildcareAppointmentInfo.doctorInfo.doctorName"  
									value="${userChildcareAppointmentInfo.doctorInfo.doctorName}" class="text3">
								</td>
								
								<td width="10%" height="41" align="right">订单类型：</td>
								<td width="10%" align="left">
									<select id="orderType" name="userChildcareAppointmentInfo.orderType" style="width:100px">
											<option value=""></option>
											<option value="机构套餐">机构套餐</option>
											<option value="家庭医生套餐">家庭医生套餐</option>
											<option value="普通订单">普通订单</option>
									</select>
									
<%-- 									<input type="text" name="userChildcareAppointmentInfo.orderType"  
									value="${userChildcareAppointmentInfo.orderType }" class="text3"> --%>
								</td>
								
								<td width="10%" height="41" align="right">订单状态：</td>
								<td width="10%" align="left">
								
									<select id="status" name="userChildcareAppointmentInfo.status" style="width:100px">
											<option value=""></option>
											<option value="已会面">已会面</option>
											<option value="已完成">已完成</option>
											<option value="未完成">未完成</option>
											<option value="已预约">已预约</option>
									</select>
<%-- 									<input type="text" name="userChildcareAppointmentInfo.status"  
									value="${userChildcareAppointmentInfo.status }" class="text3"> --%>
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
								action="${pageContext.request.contextPath}/medicalorgandbusiness/toList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
									<com.java.ec:column property="orderNum" title="订单编号" width="10%"/>
									<com.java.ec:column property="null" title="宝宝姓名" width="5%">
										${p.userInfo.babyName}
									</com.java.ec:column>
									<com.java.ec:column property="operationTime" title="下单时间" width="10%"/>
									<com.java.ec:column property="null" title="门诊时间" width="10%">
										${p.organChildcareOpenResources.openDate}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="预约医生" width="5%">
										${p.doctorInfo.doctorName}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="套餐类型" width="5%">
										${p.archivesInfo.userType}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="本次所做项目" width="10%">
										${p.organSetChildCareRecode.childcareProject.projectTitle}
									</com.java.ec:column>
									<com.java.ec:column property="totalPrice" title="金额" width="5%"/>
									<com.java.ec:column property="orderType" title="订单类型" width="10%"/>
									<com.java.ec:column property="status" title="状态" width="10%"/>
									<com.java.ec:column property="null" title="操作" width="10%">
										<select name="orderStatus" style="width:70px;float:left">
										  	<option selected="selected" value="">请选择</option>
											<option value="已完成">已完成</option>
											<option value="未完成">未完成</option>
										</select>
										<c:if test="${p.orderType == '机构套餐'}">
											<a href="javascript:doDetail(${p.id});" style="width:50px;color:blue;" title="详情">详情</a>
										</c:if>
										<%-- <s:if test="%{orderType=='机构套餐'}">
											<a href="javascript:void();" style="width:50px;" title="详情">详情</a>
										</s:if> --%>
										<%-- <a href="javascript:doDetail();" title="分配医生">
										<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif"  border="0"/></a> --%>
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
  	</body>
</html>
