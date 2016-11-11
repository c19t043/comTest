<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>产前第1次随访信息列表</title>
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

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			window.addEvent('domready', function(){
				
			});
			// 显示详情 
			function doDetail(id) {
				//var url="${pageContext.request.contextPath}/dataEntry/viewManagementOrg.action?managementOrg.id="+id;
				//window.showModalDialog(url,null,"dialogHeight:400px;dialogWidth:870px;status:off" );
			
// 				var date = new Date();
// 				var action = '<s:url namespace="/dataEntry" action="viewManagementOrg" includeParams="true"/>';
// 				action=action+'?managementOrg.id=' + id + '&_t=' + date.getTime();
// 				window.location.href=action;
				var action = '<s:url namespace="/productionvisit/moreFollow" action="viewPhPrenatalFollowRecordAfter" includeParams="true"/>';
				window.location.href=action + '?phPrenatalFollowRecordAfter.id='+id;
			}
			// 新增信息 
			function doAdd() {
				var action = '<s:url namespace="/productionvisit/preFirst" action="toAddPhPrenatalFollowRecordFirst" includeParams="true"/>';
				var basicId = $j("#accountId",parent.document).val();
				window.location.href=action+'?accountId='+basicId;
			}
			// 编辑信息 
			function doEdit() {
				var action = '<s:url namespace="/productionvisit/preFirst" action="toAddPhPrenatalFollowRecordFirst" includeParams="true"/>';
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					showMsg("至少选择一项！");
					return;
				}else if(items.length > 1){
					showMsg("只能选择一项进行编辑！");
					return;
				}
				var id = "";
				items.each(function(item){
					id = item.value;
				});
				window.location.href=action + '?phPrenatalFollowRecordAfter.id='+id;
			}
			function doBack(){
	            window.location.href = 
	            	"${pageContext.request.contextPath}/productionvisit/moreFollow/toPhPrenatalFollowRecordAfterList.action"
	            		+"?accountId="+$j("#accountId",parent.document).val();
	        }
			$j(function(){
				$j("#accountId").val($j("#accountId",parent.document).val());
				$j("#motherName").html($j("#motherName",parent.document).val());
				$j("#motherPhone").html($j("#motherPhone",parent.document).val());
			})
  		</script>
	</head>

	<body>
	<s:actionmessage theme="popwind"/>
<!-- 	<ap:stepAndOperation></ap:stepAndOperation> -->
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<fieldset style="border: 1px solid #B7C6DD;margin-bottom:30px;">
			<legend style="text-align:center"><span>个人基本信息</span></legend>
			<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
						<tr>
							<td width="10%" height="41" align="right">母亲姓名：</td>
							<td width="10%" align="left">
								<span id="motherName"></span>
							</td>
							<td width="10%" height="41" align="right">电话：</td>
							<td width="15%" align="left">
                                <span id="motherPhone"></span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</fieldset>
		<div id="wz">
			<%-- <ap:step></ap:step> --%>
<!-- 			<div>第2-9次产前随访信息列表</div> -->
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doAdd();" class="pop_button_blue">产前第一次随访</a></span>
					</div>
				</div>
			</div>
		</div>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr>
				<td>
					<div class="eXtremeTable">
						<s:if test="#request.list.size>0">
							<com.java.ec:table items="list" var="p" tableId="list" border="0"
								action="${pageContext.request.contextPath}/productionvisit/preFirst/toList.action?accountId=${p.familyAccountInfo.id}"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" 
								filterable="false" autoIncludeParameters="true" sortable="false" showPagination="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
									<com.java.ec:column property="null" title="随访日期" width="10%">
										<fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd"/>
									</com.java.ec:column>
									<com.java.ec:column property="gestationalWeek" title="孕周" width="10%"/>
									<%-- <com.java.ec:column property="null" title="操作" width="5%">
										<a href="javascript:doDetail('${p.id}');" title="查看"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif"  border="0"/></a>
									</com.java.ec:column> --%>
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
  	</body>
</html>
