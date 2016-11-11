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
		<title>工作人员排班页面</title>
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
  				
  				Util2.selectUsers("workerName","workerName","workerID");
  				
  				<c:if test="${statusFlag == 'reload'}">
	  				var opener = environment.dialog.getOpener();
	  				var parentDocument = opener.document;
	  				environment.dialog.close();
	  				
	  				$j("#btn_refresh",parentDocument).click();
				</c:if>
  			});
  			
  			function doSave(){
  				$j("#save").click();
  			}
  			
  			
  		</script>
  	</head>
  
  	<body>
  		<s:actionmessage theme="popwind"/>
  		<div id="wz">
			<ap:step></ap:step>
			<c:if test="${statusFlag != 'detail' }">
				<div class="wz_right">
					<div class="but01">
						<div class="pop_button_bar">
							<span><a href="javascript:doSave();" class="pop_button_blue">保存</a></span>
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<form id="form_handle" name="form_handle" action="<s:url namespace="/operationmanage" action="saveOrUpdateWorkerSchedule" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input name="operaBaseSchedule.id"  type="hidden" value="${operaBaseSchedule.id }">
			<input name="operaWorkerSchedule.id"  type="hidden" value="${operaWorkerSchedule.id }">
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
							<td class="inputLabel">开放时间:</td>
							<td width="30%">${operaBaseSchedule.openDate }</td> 
							
							<td class="inputLabel">社区机构：</td>
							<td width="30%" >${operaBaseSchedule.hospitalBasicInfo.hospitalLname }</td>
					  	</tr>
						<tr>
							<td colspan="4" style="text-align: center;font-size: 16px;font-weight: bold;">工作人员排班:</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">工作人员:<span class="text4">*</span></td>
							<td colspan="3" >
								<input type="text" name="operaWorkerSchedule.user.userInfo.name"  id="workerName" 
								value="${operaWorkerSchedule.user.userInfo.name }" class="validate['length[20]','required'] text3" width="70%">
								<input type="hidden" name="operaWorkerSchedule.user.userId" id="workerID"
								value="${operaWorkerSchedule.user.userId }" class="validate['length[200]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">业务类型:<span class="text4">*</span></td>
							<td colspan="3">
								<c:forEach items="${operaBusinessType_list }" var="v">
									<div style="display: inline-block;width:80px;">
										<input type="radio" name="operaWorkerSchedule.operaBusinessType.id" 
										<c:if test='${operaWorkerSchedule.operaBusinessType.id == v.id }'>checked="checked"</c:if>
										value="${v.id }">${v.name }
									</div>
								</c:forEach>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">工作时间:</td>
							<td width="30%">
								<input type="text" name="operaWorkerSchedule.workBeginTime" 
								value="${operaWorkerSchedule.workBeginTime }" 
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								class="validate['length[20]'] text3" width="30%">
								-
								<input type="text" name="operaWorkerSchedule.workEndTime" 
								value="${operaWorkerSchedule.workEndTime }" 
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								class="validate['length[20]'] text3" width="30%">
							</td> 
							
							<td class="inputLabel">休息时间:</td>
							<td width="30%">
								<input type="text" name="operaWorkerSchedule.restBeginTime" 
								value="${operaWorkerSchedule.restBeginTime }" 
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								class="validate['length[20]'] text3" width="30%">
								-
								<input type="text" name="operaWorkerSchedule.restEndTime" 
								value="${operaWorkerSchedule.restEndTime}"
								onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'HH:mm'});"
								class="validate['length[20]'] text3" width="30%">
							</td> 
					  	</tr>
					  	<tr>
							<td class="inputLabel">是否可用:<span class="text4">*</span></td>
							<td width="30%" >
								<select name="operaWorkerSchedule.isEnable" style="width:150px;">
									<option value="Y" <c:if test="${operaWorkerSchedule.isEnable == 'Y' }">selected="selected"</c:if>>Y</option>
									<option value="N" <c:if test="${operaWorkerSchedule.isEnable == 'N' }">selected="selected"</c:if>>N</option>
								</select>
							</td>
							
							<td class="inputLabel">备注:</td>
							<td width="30%" >
								<textarea rows="3" cols="40" name="operaWorkerSchedule.remarks">${operaWorkerSchedule.remarks }</textarea>
							</td>  
					  	</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
