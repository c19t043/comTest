<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>社区排班列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>

		<!-- environment弹出窗口样式 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>

		<!-- ecTable列表 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>

		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
	
		<style>
			.docTb{
				margin-left: -10px;
			}
		</style>
	</head>

	<body>
  		<s:actionmessage theme="popwind"/>
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doAdd();" class="pop_button_blue">新增</a></span>
						<span><a href="javascript:doEdit();" class="pop_button_green">编辑</a></span>
						<span><a href="javascript:doPublish();" class="pop_button_green">发布</a></span>
						<span><a href="javascript:exportExcel();" class="pop_button_green">导出排班</a></span>
					</div>
				</div>
			</div>
		</div>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="/operationmanage/toList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="8%" height="41" align="right">机构名字：</td>
								<td width="8%" align="left">
									<input type="text" name="operaBaseSchedule.hospitalBasicInfo.hospitalLname" 
									value="${operaBaseSchedule.hospitalBasicInfo.hospitalLname }" class="text3" style="width: 220px;">
								</td>
								
								<td width="8%" height="41" align="right">医生姓名：</td>
								<td width="8%" align="left">
									<input type="text" name="operaBaseSchedule.doctorName" 
									value="${operaBaseSchedule.doctorName }" class="text3">
								</td>
								
								<td width="8%" height="41" align="right">业务类型：</td>
								<td width="8%" align="left">
									<s:select list="#businessTypeList"
									headerKey="" headerValue=""
									listKey="name" listValue="name" 
									cssStyle="width:100px;" 
									name="operaBaseSchedule.businessType" value="operaBaseSchedule.businessType"></s:select>
								</td>
								
								<td width="8%" height="41" align="right">是否是家庭医生：</td>
								<td width="8%" align="left">
									<s:select list="#{'Y':'是','N':'不是'}"
									headerKey="" headerValue=""
									listKey="key" listValue="value" 
									cssStyle="width:100px;" 
									name="operaBaseSchedule.isFamilyDoctor" value="operaBaseSchedule.isFamilyDoctor"></s:select>
								</td>
								
								
								<td width="30%" align="left">
									<a href="javascript:document.getElementById('query_list_form').submit();">
										<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/serch21.gif" width="65" height="23" border="0">
									</a>
								</td>
							</tr>	
							<tr>
								<td width="8%" height="41" align="right">开放时间：</td>
								<td width="20%" align="left">
									<input type="text" name="operaBaseSchedule.openDate" 
									onFocus="WdatePicker({isShowClear:true,readOnly:true});" 
									value="${operaBaseSchedule.openDate }" class="text3" style="width:100px;">
									-
									<input type="text" name="operaBaseSchedule.endDate" 
									onFocus="WdatePicker({isShowClear:true,readOnly:true});"
									value="${operaBaseSchedule.endDate }" class="text3" style="width:100px;">
								</td>
								
								<td width="8%" height="41" align="right">总发布状态：</td>
								<td width="8%" align="left">
									<select name="operaBaseSchedule.publishStatus" style="width:150px;">
										<option></option>
										<option value="未发布" <c:if test="${operaBaseSchedule.publishStatus == '未发布'}">selected="selected"</c:if>>未发布</option>
										<option value="部分发布" <c:if test="${operaBaseSchedule.publishStatus == '部分发布'}">selected="selected"</c:if>>部分发布</option>
										<option value="已发布"<c:if test="${operaBaseSchedule.publishStatus == '已发布'}">selected="selected"</c:if>>已发布</option>
									</select>
								</td>
								
								<td width="8%" height="41" align="right">是否可用：</td>
								<td width="8%" align="left">
									<s:select list="#{'Y':'可用','N':'不可用'}" 
									headerKey="" headerValue="" name="operaBaseSchedule.isEnable" value="operaBaseSchedule.isEnable"
									listKey="key" listValue="value" cssStyle="width:100px;"></s:select>
								</td>
								
							</tr>
							
						</table>
					</s:form>
				</td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr>
				<td>
					<div class="eXtremeTable">
						<s:if test="#request.list.size>0">
							<com.java.ec:table items="list" var="p" tableId="list" border="0"
								action="${pageContext.request.contextPath}/operationmanage/toList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
									<com.java.ec:column property="openDate" title="开放时间" width="5%"/>
									<com.java.ec:column property="null" title="机构名字" width="10%">
										${p.hospitalBasicInfo.hospitalLname}
									</com.java.ec:column>
									<com.java.ec:column property="null" title="工作时间-医生-业务类型-是否是家庭医生-子发布状态" width="15%">
										<table class="docTb">
											<c:forEach items="${p.operaDoctorSchedules }" var="v" varStatus="status">
												<c:if test="${v.isEnable == 'Y' }">
													<tr
														<c:if test="${v.publishStatus == '有改动'}">style="color: red;"</c:if>
														<c:if test="${v.publishStatus == '已发布' }">style="color: green;"</c:if>
													>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.workBeginTime }-${v.workEndTime }</td>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.doctorInfo.doctorName }</td>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.operaBusinessType.name }</td>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>
															<c:if test="${v.isFamilyDoctor == 'Y' }">是</c:if>
															<c:if test="${v.isFamilyDoctor == 'N'  }">不是</c:if>
														</td>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.publishStatus }</td>
													</tr>
												</c:if>
											</c:forEach>
										</table>
									</com.java.ec:column>
									<com.java.ec:column property="null" title="工作时间-医助-业务类型" width="15%">
										<table class="docTb">
											<c:forEach items="${p.operaWorkerSchedules }" var="v" varStatus="status">
												<c:if test="${v.isEnable == 'Y' }">
													<tr>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.workBeginTime }-${v.workEndTime }</td>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.user.userInfo.name }</td>
														<td <c:if test="${status.last}">style="border-bottom: 0px;"</c:if>>${v.operaBusinessType.name }</td>
													</tr>
												</c:if>
											</c:forEach>
										</table>
									</com.java.ec:column>
									<com.java.ec:column property="isEnable" title="是否可用" width="3%">
											<c:if test="${p.isEnable == 'Y' }">可用</c:if>
											<c:if test="${p.isEnable == 'N'  }">不可用</c:if>
									</com.java.ec:column>
									<com.java.ec:column property="publishStatus" title="总发布状态" width="3%"/>
									<com.java.ec:column property="null" title="操作" width="3%">
										<a href="javascript:void();" onclick="toDetail(${p.id})">详细信息</a>
									</com.java.ec:column>
								</com.java.ec:row>
							</com.java.ec:table>
						</s:if>
						<s:else>
							<styles:nolist/>
						</s:else>
					</div>
				</td>
			</tr>
		</table>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			//发布
			function doPublish(){
				var $checkeds = $j("input:checked");
				var $checkeds = $j("input:checked");
				if($checkeds.length==0){
					showMsg("至少选择一项！");
					return;
				}
				var ids = '';
				$checkeds.each(function(){
					var val =  $j(this).val();
					if(!/^[0-9]*$/.test(val)) return true;
					ids+=val+",";
				});
				//alert(ids);
				$j.ajax({
					url:"${pageContext.request.contextPath}/operationmanage/json_batchPublishSchedule.action",
					type:"Post",
					cache:false,
					async:true,
					data:{
						"operaBaseSchedule.publishIDs":ids
					},
					success:function(result){
						alert(result);
						refresh();
					},
					error:function(result){
						alert("失败");
						refresh();
					}
				});
				
			}
			function refresh(){
				window.location.href="${pageContext.request.contextPath}/operationmanage/toList.action";
			}
		
			// 新增信息 
			function doAdd(action) {
				action = "${pageContext.request.contextPath}/operationmanage/toAdd.action";
				window.location.href=action;
			}
			// 编辑信息 
			function doEdit(action) {
				action = "${pageContext.request.contextPath}/operationmanage/toAdd.action";
				var $checkeds = $j("input:checked");
				if($checkeds.length==0){
					showMsg("至少选择一项！");
					return;
				}else if($checkeds.length > 1){
					showMsg("只能选择一项进行编辑！");
					return;
				}
				var id = $checkeds.val();
				window.location.href=action + "?operaBaseSchedule.id="+id;
			}
			//跳转到人员配置页面
			function toDetail(id){
				window.location.href="${pageContext.request.contextPath}/operationmanage/toCBMDetailPage.action?operaBaseSchedule.id="+id;
			}
			function exportExcel(){
				if(!confirm("确认要导出吗？")) return;
				var preAction =  $j("#query_list_form").attr("action");
				var exportAction = "${pageContext.request.contextPath}/operationmanage/exportExcel.action";
				$j("#query_list_form").attr("action",exportAction);
				$j("#query_list_form").submit();
				$j("#query_list_form").attr("action",preAction);
			}
  		</script>
  	</body>
</html>
