<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>商品信息录入</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/res/css/style.css"	rel="stylesheet" type="text/css" />
		
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
  		<!-- 富文本编辑器 -->
  		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.parse.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rich_text.js"></script>
  		<style type="text/css">
  				
  		</style>
  		<script type="text/javascript">
  			var $j = jQuery.noConflict(); 
  	  		window.addEvent('domready', function(){
	  			fc = new FormCheck('form_handle',{
						display:{
						showErrors:1
					}
				});
			});
			$j(document).ready(function(){
				//选择医疗机构
	  			$("orgName").addEvent("click",function() {
			 		var page2page = "text=orgName&hidden=orgID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/familydoctor/servicepackage/hospital_select_single_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择单个数据库表'
					});
				});
			});
			function doHandle() {
				$('save').click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/orgOperatorManager/orgRoleManager.action?action=queryOrganRoleByPage";
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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/orgOperatorManager" action="orgRoleManager" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="action" value="saveOrUpdateOrganRole">
			<input type="hidden" name="organRole.id" value="${organRole.id}">
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
							<td width="8%" height="41" align="right">角色名称：</td>
							<td width="12%" align="left">
								<input name="organRole.roleName"  type="text" class="input1" value="${organRole.roleName}"/>		
							</td>
							<td width="8%" height="41" align="right">角色编码：</td>
							<td width="12%" align="left">
								<input name="organRole.roleCode"  type="text" class="input1" value="${organRole.roleCode}"/>		
							</td>
					  	</tr>
				</table>
			</div>
		</form>
  	</body>
</html>
