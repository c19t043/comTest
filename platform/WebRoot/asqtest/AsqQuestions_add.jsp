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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/AsqTest" action="saveOrUpdateAsqQuestions" includeParams="true"/>" method="post" >
			<input type="hidden" name="asqQuestions.id" value="${asqQuestions.id}">
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
							<td class="inputLabel">问卷显示名称：<span class="text4">*</span></td>
							<td width="30%">
								<input id="taotiAgeName" type="text" name="asqQuestions.asqTaotiAge.showName" value="${asqQuestions.asqTaotiAge.showName}" 
								class="validate['required','length[200]'] text3">
								<input id="taotiAgeID" type="hidden" name="asqQuestions.asqTaotiAge.id" value="${asqQuestions.asqTaotiAge.id}" 
								class="validate['required','length[200]'] text3">
							</td>
							<td class="inputLabel">题目：<span class="text4">*</span></td>
							<td width="30%" colspan="3">
								<input id="taotiName" type="text" name="asqQuestions.asqTaoti.titalName" value="${asqQuestions.asqTaoti.titalName}" 
								class="validate['required','length[200]'] text3">
								<input id="taotiID" type="hidden" name="asqQuestions.asqTaoti.id" value="${asqQuestions.asqTaoti.id}" 
								class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel">问题内容：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqQuestions.subject" value="${asqQuestions.subject}" 
								class="validate['required','length[200]'] text3">
							</td>
							
							<td class="inputLabel">问题类型：<span class="text4">*</span></td>
							<td width="30%">
								<s:select  list="#{'0':'单选 ','1':'多选'}" listKey="key" listValue="value" theme="simple"
								name="asqQuestions.subjecttype" value="asqQuestions.subjecttype" cssStyle="width:100px;"
								></s:select>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">是否删除：<span class="text4">*</span></td>
							<td width="30%" >
								<s:select  list="#{'0':'未删除','1':'已删除'}" listKey="key" listValue="value" theme="simple"
								name="asqQuestions.isdelete" value="asqQuestions.isdelete" cssStyle="width:100px;"
								></s:select>
							</td>
							
							<td class="inputLabel">排序：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqQuestions.sort" value="${asqQuestions.sort}" 
								class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel">答案：</td>
							<td width="30%">
								<input type="text" name="asqQuestions.answer" value="${asqQuestions.answer}" 
								class="validate['length[200]'] text3">
							</td>
							<td class="inputLabel">答案解释：<span class="text4">*</span></td>
							<td width="30%">
								<input type="text" name="asqQuestions.anexplain" value="${asqQuestions.anexplain}" 
								class="validate['length[200]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">图片上传：</td>
							<td width="30%" colspan="3">
								<img  style="height: 130px;width: 130px;"  onclick="img_cli(this)" src="${pageContext.request.contextPath}/${asqQuestions.picture }"/>
						        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
						        <canvas style="display:none;" width="130" height="130"></canvas>
						        <input type="hidden" name="asqQuestions.imgBase64" id="imgBase64" value=""/>
						        <input type="hidden" name="asqQuestions.picture" value="${asqQuestions.picture }"/>
							</td>
					  	</tr>
				</table>
				<table width="100%" class="tableRegion2">
					<tr>
						<td style="width: 100%;text-align: center;font-weight: bold;font-size: 30px;color: orange;margin-top: 10px;border-bottom: 0px;">
							添加问题选项
						</td>
					</tr>
					<tr>
						<td >	
							<div class="but01">
								<div class="pop_button_bar">
									<span><a href="javascript:doAdd();" class="pop_button_blue">添加问题选项</a></span>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<table id="options" width="100%" class="tableRegion2">
					<tr>
						<td>选择</td>
						<td>选项内容</td>
						<td>选项编码</td>
						<td>是否删除</td>
						<td>是否需要说明</td>
						<td>是否正确选项</td>
						<td>选项分值</td>
						<td>操作</td>
					</tr>
				</table>
			</div>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath }/common/fileupload.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict();
			var appendCount = 0;
			$j(document).ready(function(){
				
				fc = new FormCheck('form_handle',{
					display:{
					showErrors:1
					}
				});
				
	  			$("taotiAgeName").addEvent("click",function() {
			 		var page2page = "text=taotiAgeName&ID=taotiAgeID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/asqtest/AsqTaotiAge_select_main.jsp?' + page2page, 
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择测评项目-适用年龄'
					});
				});
	  			$("taotiName").addEvent("click",function() {
			 		var page2page = "text=taotiName&ID=taotiID&flag=child";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/asqtest/AsqTaoti_select_main.jsp?' + page2page, 
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择测评项目-套题'
					});
				});
	  			
	  			<c:if test="${asqBeenOptionsList != null}">
		  			<c:forEach items="${asqBeenOptionsList}" var="p">
						doAdd('${p.id}','${p.optionContent}','${p.optionCode}','${p.isNeedRemark}','${p.isRightOption}','${p.isDelete}','${p.optionScore}');
					</c:forEach>
				</c:if>
			});
			function doHandle() {
				$j("#save").click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/AsqTest/getAsqQuestionsList.action";
			}
		
			function doAdd(id,optionContent,optionCode,isNeedRemark,isRightOption,isDelete,optionScore){
				var trData = "<tr id='tr_"+appendCount+"'>"
					+"<td>"
					+"	<input type=\"radio\" value='"+(id?id:'')+"' name=\"asqBeenOptionsList["+appendCount+"].aid\"  />"
					+"  <input type=\"hidden\" value='"+(id?id:'')+"' name=\"asqBeenOptionsList["+appendCount+"].id\"  />"
					+"</td>"
					+"<td>"
					+"	<input type=\"text\"  value='"+(optionContent?optionContent:'')+"'  name=\"asqBeenOptionsList["+appendCount+"].optionContent\"  class=\"validate['required','length[200]'] text3\">"
					+"</td>"
					+"<td>"
					+"	<input type=\"text\" value='"+(optionCode?optionCode:'')+"' name=\"asqBeenOptionsList["+appendCount+"].optionCode\"  class=\"validate['required','length[200]'] text3\">"
					+"</td>"
	 				+"<td>"
					+"	 <select class='isDelete' name=\"asqBeenOptionsList["+appendCount+"].isDelete\" style=\"width:100px;\">"
					+"		<option value=\"0\" >未删除</option>"
					+"		<option value=\"1\" >已删除</option>"
					+"	</select>"
					+"</td>"
	 				+"<td>"
					+"	 <select class='isNeed' name=\"asqBeenOptionsList["+appendCount+"].isNeedRemark\" style=\"width:100px;\">"
					+"		<option value=\"N\" >不需要</option>"
					+"		<option value=\"Y\" >需要</option>"
					+"	</select>"
					+"</td>"
	 				+"<td>"
					+"	 <select class='isRight' name=\"asqBeenOptionsList["+appendCount+"].isRightOption\" style=\"width:100px;\">"
					+"		<option value=\"N\" >不是</option>"
					+"		<option value=\"Y\" >是</option>"
					+"	</select>"
					+"</td>"
					+"<td>"
					+"	<input type=\"text\" value='"+(optionScore?optionScore:'')+"'  name=\"asqBeenOptionsList["+appendCount+"].optionScore\"   class=\"validate['required','length[200]'] text3\">"
					+"</td>"
					+"<td>"	
					+"	 <a href=\"javascript:void();\" onclick=\"doDel(this)\">删除</a>"
					+"</td>"
					+"</tr>";
				$j("#options").append(trData);
				if(isNeedRemark){
					$j("#tr_"+appendCount+" .isNeed option").each(function(){
						var $option = $j(this);
						if($option.val()==isNeedRemark) $option.prop("selected",true);
					});
				}
				if(isRightOption){
					$j("#tr_"+appendCount+" .isRight option").each(function(){
						var $option = $j(this);
						if($option.val()==isRightOption) $option.prop("selected",true);
					});
				}
				if(isDelete){
					$j("#tr_"+appendCount+" .isDelete option").each(function(){
						var $option = $j(this);
						if($option.val()==isDelete) $option.prop("selected",true);
					});
				}
				$j("#tr_"+appendCount).bind("click",function(){
					$j("input:radio:checked").each(function(){
						$j(this).prop("checked",false);
					});
					$j(this).find("input:radio").prop("checked",true);
				});
				appendCount++;
			}
			
			function doDel(obj){
				if(confirm("确定要删除嘛？")){
					$j(obj).parent().parent().remove();
				}
			}
			
			function doEdit(obj){
				
			}
		</script>
  	</body>
</html>
