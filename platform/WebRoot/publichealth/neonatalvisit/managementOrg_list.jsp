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
		<title>各级管理机构信息列表</title>
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
			
				var date = new Date();
				var action = '<s:url namespace="/dataEntry" action="viewManagementOrg" includeParams="true"/>';
				action=action+'?managementOrg.id=' + id + '&_t=' + date.getTime();
				window.location.href=action;
			}
			// 新增信息 
			function doAdd(action) {
				window.location.href=action+"?condition.form_code=ManagementOrg";
			}
			// 编辑信息 
			function doEdit(action) {
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
				window.location.href=action + "?managementOrg.id="+id + "&condition.form_code=ManagementOrg";
			}
			// 删除信息 
			function doDel(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					showMsg("至少选择一项！");
					return;
				}
				var ids = "";
				items.each(function(item){
					ids += item.value + ",";
				});
				confirmMsg("确定删除所选记录吗？",function(tp){
		 			if(tp=='ok'){
						window.location.href=action + "?managementOrg.id="+ids+ "&condition.form_code=ManagementOrg";
					}
				});
			}
			//批量审批
			function doBatch(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					showMsg("至少选择一项！");
					return;
				}
				var ids = "";
				items.each(function(item){
					ids += item.value + ",";
				});
				confirmMsg("确定批量审批吗？",function(tp){
		 			if(tp=='ok'){
						window.location.href=action + "?managementOrg.id="+ids+ "&condition.form_code=ManagementOrg&flag=ok&flowInfo.remark=同意";
					}
				});
			}
			// 单条审批
			function doOne(action,id) {
				window.location.href=action + "?managementOrg.id="+id + "&condition.form_code=ManagementOrg";
			}
			//导入
			function doImport(){
				var url="${pageContext.request.contextPath}/dataEntry/importMain.jsp?className=ManagementOrg"+"&date="+new Date().getTime();
				var obj = window.showModalDialog(url,"","dialogWidth:370px;dialogHeight:100px;scroll:no;status:off;resizable:no;");  
				
				if(obj == 1){
					window.location.href="${pageContext.request.contextPath}/dataEntry/managementOrgList.action";
				}
			}
			//导入模板下载
			function doModel(action){
				window.location.href=action +"?className=ManagementOrg";
			}
			//导出
			function doExport(action){
				var findConditions = $j('#conditionToJava').val();
				window.location.href = action+"?className=ManagementOrg"+
											"&findConditions="+findConditions
										;
			}
  		</script>
	</head>

	<body>
	<s:actionmessage theme="popwind"/>
	<ap:stepAndOperation></ap:stepAndOperation>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" height="45"  border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
			<tr align="center" valign="middle" bgcolor="#F6F9FE">
				<td height="43" align="center" class="text3">
					<s:form action="/dynamicInterface/datasource/interfaceSpecificationList.action" name="query_list_form" id="query_list_form" theme="simple">
						<table width="98%"  border="0" cellpadding="0" cellspacing="0" class="text3">
							<tr>
								<td width="10%" height="41" align="right">数据库名：</td>
								<td width="10%" align="left">
									<input type="text" name="parameter.dataBaseName" id="parameter.dataBaseName" value="${parameter.dataBaseName}" class="text3">
								</td>
								<td width="10%" height="41" align="right">表名：</td>
								<td width="15%" align="left">
									<input type="text" name="parameter.tableName" id="parameter.tableName" value="${parameter.tableName}" class="text3">
								</td>
								<td width="10%" height="41" align="right">操作类型：</td>
								<td width="15%" align="left">
									<s:select list="#{'':'-请选择-','insert':'增加','delete':'删除','update':'修改','select':'查询'}" 
										listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
						  		  		name="parameter.operationType" value="parameter.operationType" />
								</td>
								<td width="30%" align="left">
									<a href="javascript:document.getElementById('query_list_form').submit();">
										<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/serch21.gif" width="65" height="23" border="0">
									</a>
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
								action="${pageContext.request.contextPath}/dataEntry/managementOrgList.action"
								imagePath="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<com.java.ec:row>
									<com.java.ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
									<com.java.ec:column property="org_name" title="机构名称" width="15%"/>
									<com.java.ec:column property="org_level" title="机构级别" width="10%"/>
									<com.java.ec:column property="org_legal" title="机构法人" width="10%"/>
									<com.java.ec:column property="mailing_address" title="通讯地址" width="20%"/>
									<com.java.ec:column property="phone" title="联系电话" width="10%"/>
									<com.java.ec:column property="null" title="表单状态" width="10%">
										<c:if test="${p.status == 0}">草稿</c:if>
										<c:if test="${p.status == 1}">审批中</c:if>
										<c:if test="${p.status == 2}">驳回</c:if>
										<c:if test="${p.status == 3}">审批通过</c:if>
									</com.java.ec:column>
									<com.java.ec:column property="null" title="操作" width="5%">
										<a href="javascript:doDetail('${p.id}');" title="查看"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif"  border="0"/></a>
										<c:if test="${p.status == 1 || p.status == 3}">
											<ap:operationlink value="${p.id}"></ap:operationlink>
										</c:if>
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
  	</body>
</html>
