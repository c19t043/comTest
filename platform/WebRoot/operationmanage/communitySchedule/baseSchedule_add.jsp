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
		<title>基础排班信息添加</title>
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
	  		window.addEvent('domready', function(){
	  			fc = new FormCheck('form_handle',{
						display:{
						showErrors:1
					}
	  			});
	  			
	  			<c:if test="${msg !=null && msg!='' }">
	  				alert('${msg}');
	  			</c:if>
	  			
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
			function doHandle() {
				$j("#save").click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/operationmanage/toList.action";
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
		<form id="form_handle" name="form_handle" action="<s:url namespace="/operationmanage" action="saveOrUpdate" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input name="operaBaseSchedule.id"  type="hidden" value="${operaBaseSchedule.id}">
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
							<td class="inputLabel">开放时间:<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="operaBaseSchedule.openDate" 
								onFocus="WdatePicker({isShowClear:false,readOnly:true});"
								value="${operaBaseSchedule.openDate}" class="validate['length[20]','required'] text3" width="70%">
							</td> 
							
							<td class="inputLabel">社区机构：<span class="text4">*</span></td>
							<td width="30%" >
								<input type="text" id="hospitalName" name="operaBaseSchedule.orgNames"  readonly="readonly"
								value="${operaBaseSchedule.hospitalBasicInfo.hospitalLname }" class="validate['length[100]','required'] text3" width="70%">
								<input type="hidden" id="hospitalID" name="operaBaseSchedule.orgIDs" 
								value="${operaBaseSchedule.hospitalBasicInfo.id}" class="validate['length[100]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">是否可用:<span class="text4">*</span></td>
							<td width="30%">
								<select name="operaBaseSchedule.isEnable" style="width:150px;">
									<option value="Y" <c:if test="${operaBaseSchedule.isEnable == 'Y' }">selected="selected"</c:if>>Y</option>
									<option value="N" <c:if test="${operaBaseSchedule.isEnable == 'N' }">selected="selected"</c:if>>N</option>
								</select>
							</td> 
					  	</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
