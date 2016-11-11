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
  			$j(document).ready(function(){
  				fc = new FormCheck('form_handle',{
					display:{
					showErrors:1
					}
				});
  				
	  			$("taotiName").addEvent("click",function() {
			 		var page2page = "text=taotiName&ID=taotiID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/asqtest/AsqQuestions_select_main.jsp?' + page2page, 
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择测评问题'
					});
				});
			});
			function doHandle() {
				$j("#save").click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/AsqTest/getAsqBeenOptionsList.action";
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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/AsqTest" action="saveOrUpdateAsqBeenOptions" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="asqBeenOptions.id" value="${asqBeenOptions.id}">
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
							<td class="inputLabel">题目：<span class="text4">*</span></td>
							<td width="30%">
								<input id="taotiName" type="text" name="asqBeenOptions.asqQuestions.subject" value="${asqBeenOptions.asqQuestions.subject}" 
								class="validate['required','length[200]'] text3">
								<input id="taotiID" type="hidden" name="asqBeenOptions.asqQuestions.id" value="${asqBeenOptions.asqQuestions.id}" 
								class="validate['required','length[200]'] text3">
							</td>
							<td class="inputLabel">选项内容：<span class="text4">*</span></td>
							<td width="30%" colspan="3">
								<input type="text" name="asqBeenOptions.optionContent" value="${asqBeenOptions.optionContent}" 
								class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel">选项编码：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqBeenOptions.optionCode" value="${asqBeenOptions.optionCode}" 
								class="validate['required','length[200]'] text3">
							</td>
							<td class="inputLabel">是否删除：<span class="text4">*</span></td>
							<td width="30%">
								<s:select  list="#{'0':'不删除','1':'删除'}" listKey="key" listValue="value" theme="simple"
								name="asqBeenOptions.isDelete" value="asqBeenOptions.isDelete" cssStyle="width:100px;"
								></s:select>
							</td>
					  	</tr>
					  		<tr>
							<td class="inputLabel">是否需要说明：<span class="text4">*</span></td>
							<td width="30%">
								<s:select  list="#{'N':'不需要','Y':'需要'}" listKey="key" listValue="value" theme="simple"
								name="asqBeenOptions.isNeedRemark" value="asqBeenOptions.isNeedRemark" cssStyle="width:100px;"
								></s:select>
							</td>
							<td class="inputLabel">是否正确选项：<span class="text4">*</span></td>
							<td width="30%" colspan="3">
								<s:select  list="#{'N':'不是','Y':'是'}" listKey="key" listValue="value" theme="simple"
								name="asqBeenOptions.isRightOption" value="asqBeenOptions.isRightOption" cssStyle="width:100px;"
								></s:select>
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel">选项分值：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqBeenOptions.optionScore" value="${asqBeenOptions.optionScore}" 
								class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
				</table>
		</div>
  	</body>
</html>
