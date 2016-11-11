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
		<title>预售模型信息</title>
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
				new FormCheck('form_add',{
					display:{
						showErrors:1
					}
				});
				//选择预售商品
	  			$j("#selectGoodsName").click(function() {
			 		var page2page = "text=selectGoodsName&hidden=selectGoodsId";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/b2cGoods/goodsPresaleModel/goods_select_single_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择单个商品'
					});
				});
			});
			
			// 保存
			function doSave() {
				$('save').click();
			}
			// 返回
		    function doBack(){
		    	var action = '<s:url namespace="/b2cGoods/goodsPresaleModelManager" action="getPresaleModelList" includeParams="true"/>';
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
  		
		<form id="form_add" class="form" action='<s:url namespace="/b2cGoods/goodsPresaleModelManager" action="saveOrUpdatePresaleModel" includeParams="true"/>' method="post">
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input id="selectGoodsId" name="b2cGoodsPresaleModel.id" type="hidden" class="text3" value="${b2cGoodsPresaleModel.id}" >
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">商品名称<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input id="selectGoodsName" name="b2cGoodsPresaleModel.b2cGoods.goodsName" type="text" class="text3" size="40" value="${b2cGoodsPresaleModel.b2cGoods.goodsName}" >
						<input id="selectGoodsId" name="b2cGoodsPresaleModel.b2cGoods.id" type="hidden" class="text3" value="${b2cGoodsPresaleModel.b2cGoods.id}" >
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">预订价格<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="b2cGoodsPresaleModel.prePrice" type="text" class="text3" size="40" value="${b2cGoodsPresaleModel.prePrice}">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">是否有效<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'Y':'Y','N':'N'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="b2cGoodsPresaleModel.isEnable" value="b2cGoodsPresaleModel.isEnable" />
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开售时间<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input type="text" name="b2cGoodsPresaleModel.startSaleDate" value="${b2cGoodsPresaleModel.startSaleDate}"
					                    class="validate['length[20]'] text3 Wdate" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
						&nbsp;&nbsp;
						<textarea name="b2cGoodsPresaleModel.remark" class="validate['length[500]'] Added_textarea">${b2cGoodsPresaleModel.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
  	</body>
</html>
