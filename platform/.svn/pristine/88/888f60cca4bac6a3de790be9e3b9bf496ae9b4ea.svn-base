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
		<title>测评套题添加</title>
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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/AsqTest" action="saveOrUpdateAsqTaoti" includeParams="true"/>" method="post" >
			<input type="hidden" name="asqTaoti.id" value="${asqTaoti.id}">
			<input name="save" id="save" type="submit" value="save" style="display: none;">
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
							<td class="inputLabel">标题：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqTaoti.titalName" value="${asqTaoti.titalName}" id="aaa" class="validate['required','length[200]'] text3">
							</td>
							<c:if test="${asqTaoti == null || asqTaoti.parent.titalName != null}">
								<td class="inputLabel">父标题：</td>
								<td width="30%" colspan="3">
									<input id="taotiName" type="text" name="asqTaoti.parent.titalName" value="${asqTaoti.parent.titalName}" 
									class="validate['length[200]'] text3">
									<input id="taotiID" type="hidden" name="asqTaoti.parent.id" value="${asqTaoti.parent.id}" 
									class="validate['length[200]'] text3">
								</td>
							</c:if>
					  	</tr>
						<tr>
							<td class="inputLabel">是否删除：<span class="text4">*</span></td>
							<td width="30%">
								<s:select  list="#{'0':'未删除','1':'已删除'}" listKey="key" listValue="value" theme="simple"
								name="asqTaoti.isdelete" value="asqTaoti.isdelete" cssStyle="width:100px;"
								></s:select>
							</td>
							<td class="inputLabel">排序编码：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqTaoti.sort" value="${asqTaoti.sort}" 
								class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">图片上传：</td>
							<td width="30%" >
								<img  style="height: 130px;width: 130px;"  onclick="img_cli(this)" src="${pageContext.request.contextPath}/${asqTaoti.imgPath }"/>
						        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
						        <canvas style="display:none;" width="130" height="130"></canvas>
						        <input type="hidden" name="asqTaoti.imgBase64" id="imgBase64" value=""/>
						        <input type="hidden" name="asqTaoti.imgPath" value="${asqTaoti.imgPath }"/>
							</td>
							<td class="inputLabel">功能连接地址：</td>
							<td width="30%" >
								<input type="text" style="width: 100%;" name="asqTaoti.url" value="${asqTaoti.url}" 
								class="validate['length[200]'] text3">
							</td>
					  	</tr>
				</table>
		</div>
		  <script type="text/javascript" src="${pageContext.request.contextPath }/common/fileupload.js"></script>
		  <script type="text/javascript">
  			var $j = jQuery.noConflict(); 
			$j(document).ready(function(){
				fc = new FormCheck('form_handle',{
					display:{
					showErrors:1
					}
				});
				//选择用户类型
				$j("#taotiName").bind("click",function(){
					var page2page = "text=taotiName&ID=taotiID&flag=parent";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/asqtest/AsqTaoti_select_main.jsp?' + page2page, 
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择测评套题'
					});
				});
			});
			function doHandle() {
				$('save').click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/AsqTest/getAsqTaotisList.action";
			}
  		</script>
  	</body>
</html>
