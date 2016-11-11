<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<html>
	<head>
		<title>医生排班模板页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		
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
		
		<!-- attachment组件  -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/css/attachment.css" rel="stylesheet"/>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/js/attachment.js"></script>
		
		<!-- util 工具 js -->
		<!--<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils2.js"></script>-->
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
  		
  		<script type="text/javascript">
  			var $j = jQuery.noConflict(); 
  			$j(document).ready(function(){
  				fc = new FormCheck('form_handle',{
					display:{
					showErrors:1
					}
  				});
  				
				$j(".peadiatrics,.childcare").hide();
				
				$j("#scheduleType").change(function(){
					$j(".peadiatrics,.childcare").hide();
					var val = $j(this).val();
					if('儿科'==val){
						$j(".peadiatrics").show();
						$j(".deadLineId").val("");
					}else if('儿保'==val){
						$j(".childcare").show();
						if($j("#isDeadLine").val()!='Y'){
							$j(".deadLine").hide();
							$j(".deadLineId").val("");
						}
					}
				});
				
				$j("#isDeadLine").click(function(){
					if($j(this).val()=='Y'){
						$j(".deadLine").show();
					}else{
						$j(".deadLine").hide();
						$j(".deadLineId").val("");
					}
				});
				
				$j("#scheduleType").trigger("change");
				
  				$j("#doctorName").click(function(){
  					var page2page = "text=doctorName&hidden=doctorID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/operationmanage/communitySchedule/doctor_select_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择医生'
					});
  				});
  			//选择医疗机构
	  			$("hospitalName").addEvent("click",function() {
			 		var page2page = "text=hospitalName&hidden=hospitalID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/operationmanage/communitySchedule/hospital_select_single_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择单个数据库表'
					});
				});
  				
  			});
  			
  			function doHandle(){
  				$j("#save").click();
  			}
  			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/operationModelManager/toModelList.action";
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
						<span><a href="javascript:doHandle();" class="pop_button_blue">保存</a></span>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		<form id="form_handle" name="form_handle" action="<s:url namespace="/operationModelManager" action="saveOrUpdateDoctorSchedule" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input name="operaDoctorSchedule.id"  type="hidden" value="${operaDoctorSchedule.id }">
			<input name="operaDoctorSchedule.isEnable"  type="hidden" value="Y">
			<input name="operaDoctorSchedule.isStatistics"  type="hidden" value="Y">
			<div class="eXtremeTable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left" valign="top">
							<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
						</td>
					</tr>
				</table>
					<table width="100%" class="tableRegion2">
					  	<tr>
					  		<td class="inputLabel">模板名称:</td>
							<td width="30%" colspan="3">
								<input name="operaDoctorSchedule.remarks" class="validate['length[200]'] text3" 
								value="${operaDoctorSchedule.remarks }">
							</td>  
					  	</tr>
					  	<tr>
					  		<td class="inputLabel">医生:<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="operaDoctorSchedule.doctorInfo.doctorName" id="doctorName"
								value="${operaDoctorSchedule.doctorInfo.doctorName }" 
								class="validate['length[20]','required'] text3" width="70%">
								<input type="hidden" name="operaDoctorSchedule.doctorInfo.id" id="doctorID"
								value="${operaDoctorSchedule.doctorInfo.id }"
								 class="validate['length[20]'] text3" width="70%">
							</td>
							<td class="inputLabel">排班类型:<span class="text4">*</span></td>
							<td width="30%">
								<s:select id="scheduleType" cssStyle="width:100px;" cssClass="text3" list="#{'儿科':'儿科','儿保':'儿保'}" listKey="key" listValue="value" 
									theme="simple" name="operaDoctorSchedule.scheduleType" value="operaDoctorSchedule.scheduleType"/>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">业务类型:<span class="text4">*</span></td>
							<td colspan="3">
								<c:forEach items="${operaBusinessType_list }" var="v">
									<div style="display: inline-block;width:80px;">
										<input type="radio" name="operaDoctorSchedule.operaBusinessType.id" 
										<c:if test='${operaDoctorSchedule.operaBusinessType.id == v.id }'>checked="checked"</c:if>
										value="${v.id }">${v.name }
									</div>
								</c:forEach>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">面向用户类型:<span class="text4">*</span></td>
							<td colspan="3">
								<c:forEach items="${userTypes_list }" var="v">
									<div style="display: inline-block;width:80px;">
										<input type="checkbox" name="operaDoctorSchedule.userTypeArr" 
										<c:if test='${fn:contains(operaDoctorSchedule.userType,v.id)}'>checked="checked"</c:if>
										value="${v.id }">${v.typeName }
									</div>
								</c:forEach>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">工作时间:<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="operaDoctorSchedule.workBeginTime" 
								value="${operaDoctorSchedule.workBeginTime }"
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								 class="validate['length[20]','required'] text3" width="30%">
								-
								<input type="text" name="operaDoctorSchedule.workEndTime" 
								value="${operaDoctorSchedule.workEndTime }"
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								 class="validate['length[20]','required'] text3" width="30%">
							</td> 
							
							<td class="inputLabel">休息时间:<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="operaDoctorSchedule.restBeginTime" 
								value="${operaDoctorSchedule.restBeginTime }"
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								 class="validate['required','length[20]'] text3" width="30%">
								-
								<input type="text" name="operaDoctorSchedule.restEndTime" 
								value="${operaDoctorSchedule.restEndTime }"
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								 class="validate['length[20]','required'] text3" width="30%">
							</td> 
					  	</tr>
					  	<tr>
							<td class="inputLabel">是否家庭医生排班:<span class="text4">*</span></td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="text3" list="#{'Y':'是','N':'否'}" listKey="key" listValue="value" 
									theme="simple" name="operaDoctorSchedule.isFamilyDoctor" value="operaDoctorSchedule.isFamilyDoctor"/>
							</td>
							
							<td class="inputLabel">社区机构：<span class="text4">*</span></td>
							<td width="30%" >
								<input type="text" id="hospitalName" name="operaBaseSchedule.orgNames"  readonly="readonly"
								value="${operaDoctorSchedule.hospitalBasicInfo.hospitalLname }" class="validate['length[100]','required'] text3" width="70%">
								<input type="hidden" id="hospitalID" name="operaBaseSchedule.orgIDs" 
								value="${operaDoctorSchedule.hospitalBasicInfo.id}" class="validate['length[100]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">时间分割值:<span class="text4">*</span></td>
							<td width="30%">
								<input type="text"  name="operaDoctorSchedule.timeSegment"  
								value="${operaDoctorSchedule.timeSegment }"
								class="validate['length[20]','required'] text3"  width="30%"/>
							</td>
							
							<td class="inputLabel peadiatrics" >可加号源:</td>
							<td width="30%" class="peadiatrics">
								<input type="text" name="operaDoctorSchedule.additiveSource" 
								value="${operaDoctorSchedule.additiveSource }" 
								 class="validate['length[20]'] text3"  width="30%">
							</td>
							
							<td class="inputLabel childcare">时间段内可约号源:<span class="text4">*</span></td>
							<td width="30%" class="childcare">
								<input type="text" name="operaDoctorSchedule.segmentationSourse" 
								value="${operaDoctorSchedule.segmentationSourse }" 
								 class="validate['length[20]'] text3"  width="30%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel childcare">是否需要截止时间:<span class="text4">*</span></td>
							<td width="30%" class="childcare">
								<select id="isDeadLine" name="operaDoctorSchedule.isDeadLine" style="width:150px;">
									<option value="N" 
									<c:if test="${operaDoctorSchedule.isDeadLine == 'N' }">selected="selected"</c:if>
									>N</option>
									<option value="Y" 
									<c:if test="${operaDoctorSchedule.isDeadLine == 'Y' }">selected="selected"</c:if>
									>Y</option>
								</select>
							</td>
							
							<td class="inputLabel childcare deadLine">截止时间:</td>
							<td width="30%" class="childcare deadLine">
								<input type="text" name="operaDoctorSchedule.deadLine" id="deadLineId"
								value="${operaDoctorSchedule.deadLine }" 
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								class="validate['length[20]'] text3"  width="30%">
							</td>
					  	</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
