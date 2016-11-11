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
		<title>商品属性信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>
		
		<!-- ecTable -->
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
		
		<!-- util 工具 js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
  		
  		<script type="text/javascript">
	  		window.addEvent('domready', function(){
				new FormCheck('form_add',{
					display:{
						showErrors:1
					}
				});
				Util2.selectDistrict("b2cGoodsProperty.isEnable","b2cGoodsProperty.isMain");
			});
			
			// 保存
			function doSave() {
				$('save').click();
			}
			// 返回
		    function doBack(){
		    	var action = '<s:url namespace="/b2cGoods/goodsPropAndValManager" action="getGoodsPropList" includeParams="true"/>';
			 	window.location.href = action ;
			}
			
  		</script>
  	</head>
  
  	<body>
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doSave();" class="pop_button_blue">保存</a></span>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
  		
		<form id="form_add" class="form" action='<s:url namespace="/b2cGoods/goodsPropAndValManager" action="saveOrUpdateb2cGoodsProp" includeParams="true"/>' method="post">
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input name="b2cGoodsProperty.id" type="hidden" class="text3" size="40" value="${b2cGoodsProperty.id}">
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">属性名称<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="b2cGoodsProperty.propName" type="text" class="text3" size="40" value="${b2cGoodsProperty.propName}">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">是否有效<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'Y':'Y','N':'N'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="b2cGoodsProperty.status" value="b2cGoodsProperty.status" />
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
						&nbsp;&nbsp;
						<textarea name="b2cGoodsProperty.remark" class=" Added_textarea">${b2cGoodsProperty.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
  	</body>
</html>
