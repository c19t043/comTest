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
		<title>社区排班明细信息</title>
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
		
		<style type="text/css">
			#schedule{
				border:1px solid #055DC1;width:100%;
			}
			#schedule td{
				width: 50px;height:50px;border:1px solid #8DB2E3;text-align: center;
			}
		</style>
	</head>

	<body>
  		<s:actionmessage theme="popwind"/>
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<input type="hidden" id="btn_refresh" onclick="refresh()">
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		<div class="eXtremeTable">
			<table width="100%" class="tableRegion2">
				<tr>
					<td class="inputLabel">开放时间:</td>
					<td width="30%">${operaBaseSchedule.openDate }</td> 
					
					<td class="inputLabel">社区机构：</td>
					<td width="30%" >${operaBaseSchedule.hospitalBasicInfo.hospitalLname }</td>
			  	</tr>
			</table>
		<!-- </div> -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<div id="wz">
						<div class="wz_right">
							<div class="but01">
								<div class="pop_button_bar">
									<a href="javascript:toAddDoctorSchedule();" class="pop_button_blue">添加医生排班</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<table width="100%" class="tableRegion2">
			<tr>
				<td>工作时间</td>
				<td>休息时间</td>
				<td>医生</td>
				<td>业务类型</td>
				<td>家庭医生</td>
				<td>面向用户类型</td>
				<td>备注</td>
				<td>是否可用</td>
				<td>操作人</td>
				<td>发布状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${operaDoctorSchedule_list}" var="v">
				<tr>
					<td>${v.workBeginTime }-${v.workEndTime }</td>
					<td>${v.restBeginTime }-${v.restEndTime }</td>
					<td>${v.doctorInfo.doctorName }</td>
					<td>${v.operaBusinessType.name }</td>
					<td>${v.isFamilyDoctor }</td>
					<td>${v.userTypes }</td>
					<td>${v.remarks }</td>
					<td>${v.isEnable }</td>
					<td>
						<c:if test="${v.modifyPerson != null }">${v.modifyPerson}</c:if>
						<c:if test="${v.modifyPerson == null }">${v.createPerson}</c:if>
					</td>
					<td class="publishStatus"
							<c:if test="${v.publishStatus == '有改动'}">style="color: red;"</c:if>
							<c:if test="${v.publishStatus == '已发布' }">style="color: green;"</c:if>
					>${v.publishStatus }</td>
					<td>
						<a href="javascript:void(0);" onclick="publishSchedule(this,'${v.id }')">发布</a>
						<a href="javascript:toAddDoctorSchedule('${v.id }','edit')">编辑</a>
						<a href="javascript:toAddDoctorSchedule('${v.id }','detail')">详情</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<div id="wz">
						<div class="wz_right">
							<div class="but01">
								<div class="pop_button_bar">
									<a href="javascript:toAddWorkerSchedule();" class="pop_button_blue">添加工作人员排班</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<table width="100%" class="tableRegion2">
			<tr>
				<td>工作时间</td>
				<td>休息时间</td>
				<td>医生助理</td>
				<td>业务类型</td>
				<td>备注</td>
				<td>是否可用</td>
				<td>操作人</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${operaWorkerSchedule_list}" var="v">
				<tr>
					<td>${v.workBeginTime }-${v.workEndTime }</td>
					<td>${v.restBeginTime }-${v.restEndTime }</td>
					<td>${v.user.userInfo.name }</td>
					<td>${v.operaBusinessType.name }</td>
					<td>${v.remarks }</td>
					<td>${v.isEnable }</td>
					<td>
						<c:if test="${v.modifyPerson != null }">${v.modifyPerson}</c:if>
						<c:if test="${v.modifyPerson == null }">${v.createPerson}</c:if>
					</td>
					<td>
						<a href="javascript:toAddWorkerSchedule('${v.id }','edit')">编辑</a>
						<a href="javascript:toAddWorkerSchedule('${v.id }','detail')">详情</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			
			function publishSchedule(obj,id){
				if(!confirm("确认发布吗？")) return;
				var publishStatus = $j(obj).parent().parent().children(".publishStatus").text();
				if('已发布'==publishStatus) {
					alert('该医生排班已发布,请误重复发布');return;
				}
				$j.ajax({
					url:"${pageContext.request.contextPath}/operationmanage/json_publishSchedule.action",
					type:"POST",
					cache:false,
					async:true,
					data:{
						"operaDoctorSchedule.id":id
					},
					success:function(result){
						alert(result);
						refresh();
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
/* 			            alert(XMLHttpRequest.status);
			            alert(XMLHttpRequest.readyState);
			            alert(textStatus);
 */						alert("系统异常,请联系管理员!"+errorThrown);
						refresh();
					}
				});
			}
			/**
			 * 返回排班基础信息列表页面
			 */
			function doBack(){
				window.location.href="${pageContext.request.contextPath}/operationmanage/toList.action";
			}
			/**
			 * 跳转到医生排班添加or编辑页面
			 */
			function toAddDoctorSchedule(doctorScheduleID,statusFlag){
		 		var page2page = "operaBaseSchedule.id=${operaBaseSchedule.id}"
		 			+"&statusFlag="+(statusFlag?statusFlag:"")
		 			+"&operaDoctorSchedule.id="+(doctorScheduleID?doctorScheduleID:"");  
		 		environment.dialog.open({
					url : '${pageContext.request.contextPath}/operationmanage/toDoctorSchedulePage.action?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
					width : window.getCoordinates().width-300,
				    height : window.getCoordinates().height-50,
					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
					title : '医生排班页面'
				});
			}
			/**
			 * 跳转到工作人员排班添加or编辑页面
			 */
			function toAddWorkerSchedule(workerScheduleID,statusFlag){
				var page2page = "operaBaseSchedule.id=${operaBaseSchedule.id}"
					+"&statusFlag="+(statusFlag?statusFlag:"")
		 			+"&operaWorkerSchedule.id="+(workerScheduleID?workerScheduleID:""); 
		 		environment.dialog.open({
					url : '${pageContext.request.contextPath}/operationmanage/toWorkerSchedulePage.action?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
					width : window.getCoordinates().width-300,
				    height : window.getCoordinates().height-50,
					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
					title : '工作人员排班页面'
				});
			}
			/**
			 * 重新刷新页面
			 */
			function refresh(){
				window.location.href="${pageContext.request.contextPath}/operationmanage/toCBMDetailPage.action?operaBaseSchedule.id=${operaBaseSchedule.id}";
			}
  		</script>
  	</body>
</html>
