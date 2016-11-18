<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>分配医生主界面</title>

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

		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/miftree/mif.tree-v1.1.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/miftree/mif_css/mif-tree.css" />
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
	</head>
	<base target="_self">
	<body id="tree_main">
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr	align="left" valign="top">
				<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD" style="margin-top: 5px;">
						<tr style="400px;">
						
							<td>
								<iframe name="objectIframe" id="objectIframe" scrolling="auto" src="" height="320px;" width="100%" frameborder="0"></iframe>
							</td>
						</tr>
						<tr style="height: 80px;">
							<td style="margin-top: -40px;">
								<div id="wz">
									<div class="wz_left">
										<div class="wz_ico"></div>
										<div class="wz_text">选择的内容如下：</div>
									</div>
									<div class="wz_right">	
										<div class="but01">
											<div class="pop_button_bar">
												<span><a href="javascript:void(0)" id="addObjectButton" class="pop_button_blue">选 择</a></span>
											</div>
										</div>
									</div>
								</div>
								<div id="checkedObject" style="height: 230px;;background-color: #F6F9FF;overflow-y:visible;">
									
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
			<%
				String ID = request.getParameter("ID");
			%>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			var objectList = new Array();
			var obj = function(id,name){
				this.id = id;
				this.name = name;
			}
			var tID = "<%=ID %>";
			var opener = environment.dialog.getOpener();
			
			$j(document).ready(function(){
				//查询角色分配的用户
				queryRoleOwnedModule();
				$j("#addObjectButton").bind("click",select);
				var src = "${pageContext.request.contextPath}/orgOperatorManager/orgRoleManager.action?action=toModulePage";
				$j("#objectIframe").attr("src",src);
			});
			
			/**
			 * 查询角色分配的模块
			 */
			function queryRoleOwnedModule(){
				$j.ajax({
					url:"${pageContext.request.contextPath}/orgOperatorManager/orgRoleManager.action",
					type:"POST",
					async:false,
					data:{"action":"queryRoleOwnedModule","organRole.id":tID},
					success:function(data){
						for(var i=0,len=data.length;i<len;i++){
							objectList.push(new obj(data[i].id,data[i].moduleName));
						}
						//展示信息
						showName();
					},
					error:function(){
						
					}
				});
			}
			
			/**
			* 保存角色分配的模块
			*/
			function saveRoleOwnedModule(moduleIDs){
				$j.ajax({
					url:"${pageContext.request.contextPath}/orgOperatorManager/orgRoleManager.action",
					type:"POST",
					async:false,
					data:{"action":"saveRoleOwnedModule","organRole.id":tID,"moduleIDs":moduleIDs},
					success:function(data){
					},
					error:function(){
						
					}
				});
			}
			
			function select(){
				var id="";
				var name = "";
				for(var i=0,len=objectList.length;i<len;i++){
					id+=objectList[i].id+",";
					name+=objectList[i].name+",";
				}
				if(id.length>0){
					id=id.substring(0, id.length-1);
					name=name.substring(0, name.length-1);
				}
				//保存角色分配的用户
				saveRoleOwnedModule(id);
				opener.location.reload();
				environment.dialog.close();
			}
			
			function insertObject(id,name){
				var objects = new obj(id,name);
				
				var addFlag = true;
				for(var i=0,len=objectList.length;i<len;i++){
					if(objectList[i].id==id){
						objectList.splice(i,1);addFlag=false;
					}
				}
				if(addFlag) {
					//objectList.length = 0;
					objectList.push(objects);
				}
				//展示信息
				showName();
			}
			/**
			 * 展示信息
			 */
			function showName(){
				var showName = "";
				for(var i=0,len=objectList.length;i<len;i++){
					showName+=objectList[i].name+","
				}
				$j("#checkedObject").html(showName);
			}
		</script>
	</body>
</html>
