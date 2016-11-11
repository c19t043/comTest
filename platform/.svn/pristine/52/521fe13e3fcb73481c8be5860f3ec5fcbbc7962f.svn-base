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
		<title>开通的业务</title>
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
					<s:form action="operationmanage/openClinicInfoList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">开放日期：</td>
								<td width="12%" align="left">
									<input name="openClinicInfo.openClinicDate" id="openClinicInfo.openClinicDate" type="text" class="input1 Wdate" value="<s:date format="yyyy-MM-dd" name="openClinicInfo.openClinicDate"/>" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>		
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
								action="${pageContext.request.contextPath}/operationmanage/openClinicInfoList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_radio" cell="radio" title="选择"  width="5%" />
									<com.java.ec:column property="openClinicDate" title="开放日期" width="10%"/>
									<com.java.ec:column property="timeSlot" title="开放时间段" width="10%"/>
									<com.java.ec:column property="businessType" title="开通业务类型" width="10%"/>
									<com.java.ec:column property="planStartTime" title="计划开始时间" width="10%"/>
									<com.java.ec:column property="planEndTime" title="计划结束时间" width="10%"/>
									<com.java.ec:column property="actualStartTime" title="实际开始时间" width="10%"/>
									<com.java.ec:column property="actualEndTime" title="实际结束时间" width="10%"/>
									<com.java.ec:column property="money" title="酬劳" width="10%"/>
									<com.java.ec:column property="state" title="状态" width="10%"/>
									<com.java.ec:column property="hospitalBasicInfo.hospitalLname" title="医疗机构" width="10%"/>
									<com.java.ec:column property="doctorSet" title="医生姓名" width="10%">
										${not empty o.doctorSet ? o.doctorSet : "-"}
                                    </com.java.ec:column>
									<com.java.ec:column property="amDoctorHelperSet" title="上午医生助理" width="10%">
										${not empty o.amDoctorHelperSet ? o.amDoctorHelperSet : "-"}
                                    </com.java.ec:column>
                                    <com.java.ec:column property="amExtensionSet" title="上午推广" width="10%">
										${not empty o.amExtensionSet ? o.amExtensionSet : "-"}
                                    </com.java.ec:column>
									<com.java.ec:column property="pmDoctorHelperSet" title="下午医生助理" width="10%">
										${not empty o.pmDoctorHelperSet ? o.pmDoctorHelperSet : "-"}
                                    </com.java.ec:column>
									<com.java.ec:column property="pmExtensionSet" title="下午推广" width="10%">
										${not empty o.pmExtensionSet ? o.pmExtensionSet : "-"}
                                    </com.java.ec:column>
									<com.java.ec:column property="isAvailable" title="是否有效 " width="10%">
                                    	${o.isEffective == 'Y' ? '<span style="color:#3F6">是</span>' : '<span style="color:red">否</span>'}
                                    </com.java.ec:column>
									<com.java.ec:column property="null" title="详细信息" width="5%">
										<a href="javascript:doDetail('${o.id}');"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif"  border="0"/></a>
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
<script type="text/javascript">
	// 显示详情 
	function doDetail(id) {
		var date = new Date();
		var action = '<s:url namespace="/operationmanage" action="openClinicInfoDetail" includeParams="true"/>';
		environment.dialog.open({
		url : action+'?openClinicInfo.id=' + id + '&_t=' + date.getTime(),
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
		window.location.href=action + "?openClinicInfo.id="+items;
	}
</script>