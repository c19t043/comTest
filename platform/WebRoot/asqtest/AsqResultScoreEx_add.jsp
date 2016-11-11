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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/AsqTest" action="saveOrUpdateAsqResultScoreEx" includeParams="true"/>" method="post" >
			<input type="hidden" name="asqResultScoreExUser.id" value="${asqResultScoreExUser.id}">
			<input type="hidden" name="asqTestUserInfo.id" value="${asqTestUserInfo.id}">
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<div class="eXtremeTable">
				<table  width="100%" class="tableRegion2">
					<!-- 
						套题信息
					 -->
					<tr>
						<td class="inputLabel">套题名字：</td>
						<td width="30%">
							${asqTaotiAge.taoti.titalName }
						</td>
						<td class="inputLabel">适用月龄：</td>
						<td width="30%">
							${asqTaotiAge.applyMinMonthAge }月-${asqTaotiAge.applyMaxMonthAge }月
						</td>
					</tr>
					<!-- 
						宝宝基础信息
					-->
					<tr><td style="font-weight: 24px;color: orange;text-align: center;" colspan="4">宝宝基础信息</td></tr>
					<tr>
						<td class="inputLabel">姓名：</td>
						<td width="30%" >
							${AsqTestUserInfo.asqUserName }
						</td>
						<td class="inputLabel">性别：</td>
						<td width="30%" >
							${AsqTestUserInfo.asqUserSex }
						</td>
					</tr>
					<tr>
						<td class="inputLabel">出生日期：</td>
						<td width="30%">
							${AsqTestUserInfo.asqUserBirthday }
						</td>
						<td class="inputLabel">出生时情况：</td>
						<td width="30%">
							${AsqTestUserInfo.birthCondition }
						</td>
					</tr>
					<tr>
						<td class="inputLabel">孕周-周：</td>
						<td width="30%">
							${AsqTestUserInfo.gestationalWeeks }
						</td>
						<td class="inputLabel">孕周-天：</td>
						<td width="30%" >
							${AsqTestUserInfo.gestationalDays }
						</td>
					</tr>
					<tr>
						<td class="inputLabel">宝宝生活年龄：</td>
						<td width="30%"  >
							${AsqTestUserInfo.babyLifeAge }
						</td>
						<td class="inputLabel">早产矫正龄：</td>
						<td width="30%"  >
							${AsqTestUserInfo.setRightAge }
						</td>
					</tr>
					<tr>
						<td class="inputLabel">当前月龄：</td>
						<td width="30%" colspan="3" >
							${AsqTestUserInfo.currentMonthAge }
						</td>
					</tr>
				</table>
				<!-- 
					答题
				 -->
				<table width="100%" class="tableRegion2">
					<tr><td style="font-weight: 30px;color: orange;text-align: center;" colspan="4">答题信息</td></tr>
					<!-- 遍历所有子标题 -->
					<c:forEach items="${asqTaotislist }" var="asqTaoti">
						<tr>
							<td style="font-weight: 20px;color: green;">${asqTaoti.titalName }</td>
							<td colspan="2"></td>
						</tr>
							<!-- 遍历所有问题 -->
						<c:forEach items="${asqTaoti.asqQuestionslist }" var="asqQuestions" varStatus="status">
							<tr>
								<td>${asqQuestions.sort }</td>
								<td>${asqQuestions.subject }
									<div style="float: right;">
										<!-- 遍历对应的问题选项 -->
										<c:forEach items="${asqQuestions.asqBeenOptionsList }" var="asqBeenOptions">
											<div	style="display: inline-block;width:200px;">
												<div
													<c:if test="${asqBeenOptions.isOptionRecord == 'Y' }" >
														style="display: inline-block;border:1px solid red;"
													</c:if>
													<c:if test="${asqBeenOptions.isOptionRecord != 'Y' }" >
														style="display: inline-block;"
													</c:if>
													>
													${asqBeenOptions.optionContent }
												</div>
											</div>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:forEach>
			  </table>
			   <!-- 
			  			评价
			  -->
			  <table width="100%" class="tableRegion2">
			 		 <tr>
			 			<td style="font-weight: 30px;color: orange;text-align: center;" colspan="3">总评价</td>
			 		 </tr>
			  		<c:forEach items="${asqResultScoreExlist}" var="asqResultScoreEx" varStatus="status">
							<tr>
								<td style="font-weight: 20px;color: green;">${asqResultScoreEx.asqTaoti.titalName }
									<input type="hidden" name="asqResultScoreExlist[${status.index}].id" value="${asqResultScoreEx.id }">
								</td>
								<td  colspan="3">
							</tr>
							<tr>
								<td class="inputLabel">得分：<span class="text4">*</span></td>
								<td colspan="3">
									<input type="text" 
									name="asqResultScoreExlist[${status.index}].sumScore" value="${asqResultScoreEx.sumScore }"
									class="validate['length[20]','required'] text3">
								</td>
							</tr>
							<tr>	
								<td class="inputLabel">得分描述：<span class="text4">*</span></td>
								<td  colspan="3">
									<textarea rows="5" cols="100" 
									name="asqResultScoreExlist[${status.index}].resultDescription"
									class="validate['length[500]','required'] text3">${asqResultScoreEx.resultDescription }</textarea>
								</td>
							</tr>
					</c:forEach>
					
					<tr>
						<td style="font-weight: 20px;color: red;">医生解读测评结果：<span class="text4">*</span></td>
						<td  colspan="3">
							<textarea rows="5" cols="100" 
								name="asqTestUserInfo.doctorReading" 
								class="validate['length[500]','required'] text3">${asqTestUserInfo.doctorReading }</textarea>
						</td>
					</tr>
			  </table>
			</div>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath }/common/fileupload.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict();
			$j(document).ready(function(){
				fc = new FormCheck('form_handle',{
					display:{
					showErrors:1
					}
				});
			});
			var appendCount = 0;
			function doHandle() {
				$('save').click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/AsqTest/getAsqResultScoreExList.action";
			}
		</script>
  	</body>
</html>
