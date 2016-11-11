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
		<title>社区排班模板列表</title>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			window.addEvent('domready', function(){
				
			});
			function doSelectTemplate(){
				var $checked =  $j("input:radio:checked");
				if($checked.length==0){
					alert('请选择一个模板');
					return;
				}
				var id = $checked.val();
				var operaDoctorSchedule_id = $j("#operaDoctorSchedule_id").val();
				var operaBaseSchedule_id = $j("#operaBaseSchedule_id").val();
				window.location.href = "${pageContext.request.contextPath}/operationModelManager/selectTemplate.action"
					+"?operaDoctorSchedule.id="+id
					+"&operaBaseSchedule.id="+operaBaseSchedule_id
  					+"&preOperaDoctorSchedule.id="+operaDoctorSchedule_id;
			}
			function doback(){
				var operaDoctorSchedule_id = $j("#operaDoctorSchedule_id").val();
				var operaBaseSchedule_id = $j("#operaBaseSchedule_id").val();
				window.location.href = "${pageContext.request.contextPath}/operationModelManager/toBack.action"
					+"?operaBaseSchedule.id="+operaBaseSchedule_id
  					+"&preOperaDoctorSchedule.id="+operaDoctorSchedule_id;		
			}
  		</script>
	</head>

	<body>
	<s:actionmessage theme="popwind"/>
	<c:if test="${templateFlag == null || templateFlag != 'select' }">
		<ap:stepAndOperation></ap:stepAndOperation>
	</c:if>
	<c:if test="${templateFlag != null && templateFlag == 'select' }">
		 <div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doSelectTemplate();" class="pop_button_blue">选择</a>
						</span>
						<span><a href="javascript:doback();" class="pop_button_green">返回</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</c:if>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="operationModelManager/toModelList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">模板名称：</td>
								<td width="12%" align="left">
									<input name="operaDoctorSchedule.remarks" id="operaDoctorSchedule.remarks" type="text" class="input1" value="${operaDoctorSchedule.remarks}"/>		
								</td>
								
								<td width="8%" height="41" align="right">社区机构：</td>
								<td width="12%" align="left">
									<input name="operaDoctorSchedule.hospitalBasicInfo.hospitalLname" 
									 type="text" class="input1" value="${operaDoctorSchedule.hospitalBasicInfo.hospitalLname}"/>		
								</td>
								<td width="19%" align="left">
									<input type="hidden"   name="queryFLag" value="query" />
									<input type="hidden"   name="templateFlag" value="${templateFlag}" />
									<input type="hidden"  
									name="preOperaDoctorSchedule.id"
									value="${preOperaDoctorSchedule.id}" id="operaDoctorSchedule_id" />
									<input type="hidden" 
									name="operaBaseSchedule.id"
									 value="${operaBaseSchedule.id}" id="operaBaseSchedule_id" />
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
								action="${pageContext.request.contextPath}/operationModelManager/toModelList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_radio" cell="radio" title="选择"  width="5%" />
									<com.java.ec:column property="remarks" title="模板名称" width="10%"/>
									<com.java.ec:column property="null" title="医生名称" width="10%">
										${o.doctorInfo.doctorName }
									</com.java.ec:column>
									<com.java.ec:column property="null" title="社区机构" width="10%">
										${o.hospitalBasicInfo.hospitalLname }
									</com.java.ec:column>
									<com.java.ec:column property="scheduleType" title="类型" width="5%"/>
									<com.java.ec:column property="operaBusinessType.name" title="业务类型" width="5%"/>
									<com.java.ec:column property="userTypes" title="面向用户类型" width="10%"/>
									<com.java.ec:column property="null" title="工作起止时间" width="10%">
										${o.workBeginTime }至${o.workEndTime }
									</com.java.ec:column>
									<com.java.ec:column property="null" title="休息起止时间" width="10%">
										${o.restBeginTime }至${o.restEndTime }
									</com.java.ec:column>
									<com.java.ec:column property="isFamilyDoctor" title="是否家庭医生排班" width="5%"/>
									<com.java.ec:column property="timeSegment" title="时间分割值" width="5%"/>
									<com.java.ec:column property="additiveSource" title="可加号源数(儿科)" width="5%"/>
									<com.java.ec:column property="segmentationSourse" title="时间段内可约号源(儿保)" width="5%"/>
									<com.java.ec:column property="isDeadLine" title="是否需要截止时间(儿保)" width="5%"/>
									<com.java.ec:column property="deadLine" title="截止预约时间(儿保)" width="5%"/>
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
		window.location.href=action + "?operaDoctorSchedule.id="+items;
	}
</script>