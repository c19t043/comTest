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
		<title>家庭医生服务包信息录入</title>
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
	  			//选择医疗机构
	  			$("memberNames").addEvent("click",function() {
			 		var page2page = "text=hospitalName&hidden=hospitalId";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/familydoctor/teamrole/doctor_select_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择医生'
					});
				});
			});
			function doHandle() {
				var names = $j("#serviceItemNames").val();
				var ids = $j("#serviceItemIds").val();
				if(names != "" && "," == names.charAt(names.length-1)){
					names = names.substring(0,names.lastIndexOf(","));
					$j("#serviceItemNames").val(names);
				}
				if(ids != "" && "," == ids.charAt(ids.length-1)){
					ids = ids.substring(0,ids.lastIndexOf(","));
					$j("#serviceItemIds").val(ids);
				}
				$('save').click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/familydoctor/servicePackage/getFdServicePackageList.action";
			}
			function download(exchangeId){
				window.location.href = '<s:url action="downloadAttachment" includeParams="true"/>' + '?exchange.exchangeId=' + exchangeId;
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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/familydoctor/servicePackage" action="saveOrUpdateFdServicePackage" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="fdServiceTeamRole.id" value="${fdServiceTeamRole.id}">
			<input type="hidden" name="fdServiceTeamRole.fdServiceTeams.id" value="${fdServiceTeamRole.fdServiceTeams.id}">
			<input type="hidden" name="fdServiceTeamRole.fdRoleInfo.id" value="${fdServiceTeamRole.fdRoleInfo.id}">
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
							<td class="inputLabel">团队名称：</td>
							<td width="30%">
								<input type="text" name="fdServiceTeamRole.fdServiceTeams.teamName" value="${fdServiceTeamRole.fdServiceTeams.teamName}" class="validate['required','length[20]'] text3">
							</td>
							<td class="inputLabel">角色名称：</td>
							<td width="30%">
								<input type="text" name="fdServiceTeamRole.fdRoleInfo.roleName" value="${fdServiceTeamRole.fdRoleInfo.roleName}" class="validate['required','length[20]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td width="15%" height="32" align="right" bgcolor="#F6F9FE">选择成员：</td>
							<td width="85%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
								<input id="memberNames" type="text" name="fdServiceTeamRole.memberNames" value="${fdServiceTeamRole.memberNames }" class="validate['required','length[20]'] text3">
								<input id="memberIds" type="text" name="fdServiceTeamRole.memberIds" value="${fdServiceTeamRole.memberIds }" >
							</td>
					  	</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
