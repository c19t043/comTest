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
		
		<%
			String names = java.net.URLDecoder.decode(request.getParameter("text"),"UTF-8"); 
			String ids = request.getParameter("hidden"); 
			String teamId = request.getParameter("teamId");
			String roleId = request.getParameter("roleId");
		%>
		<script type="text/javascript">
		var $j = jQuery.noConflict(); 
			window.addEvent('domready', function(){
				new FormCheck('form_add',{
					display:{
						showErrors:1
					}
				});
			});
			
  		</script>
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
								<div id="cheakobject" style="height: 45px;background-color: #F6F9FF;overflow-y:visible;">
									
								</div>
								<form id="form_handle" name="form_handle" action="<s:url namespace="/familydoctor/teamRole" action="addFdServiceMemberList" includeParams="true"/>" method="post" >
									<input name="fdServiceTeams.id" type="hidden" value="<%=teamId%>">
									<input name="fdRoleInfo.id" type="hidden" value="<%=roleId%>">
									<input id="memberIds" name="fdServiceTeamRole.memberIds" type="text" value="<%=ids%>">
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
<script type="text/javascript">
	var object = function(ids,names) {
		this.id = ids;
		this.name = names;
	};

	var text = '';
	var hidden = '';
	function getTextName() { 
		return text;
	}
	function getHeddinId() {
		return hidden;
	}
	var objectList = new Array();
	window.addEvent('domready', function(){
		var flag = 0;
		var url = window.location.search;
		if(url.indexOf("?") != -1) {   
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(i=0; i<strs.length; i++) {   
				if([strs[i].split("=")[0]]=='text') {
					text = unescape(strs[i].split("=")[1]);
					flag++;
				}
				if([strs[i].split("=")[0]]=='hidden') {
					hidden = unescape(strs[i].split("=")[1]);
					flag++;
				}
				if(flag == 2) {
					break;
				}
			}   
		}
		var serviceItemIdsName = "<%=names%>";
		var serviceItemIds = hidden;
		if(serviceItemIds!=null&&serviceItemIds.length>0){
			$('cheakobject').innerHTML=serviceItemIdsName;
			var ids = serviceItemIds.split(",");
			var names = serviceItemIdsName.split(",");
			for(var i=0;i<ids.length;i++){
				if(ids[i]!=null&&ids[i].length>0){
					objectList.push(new object(ids[i],names[i]));
				}
			}
		}
		$('addObjectButton').addEvent("click", select);
		$('objectIframe').src = "${pageContext.request.contextPath}/familydoctor/teamRole/getDoctorInfoList.action";
	});
	
	function select(){
		var name="";
		var id="";
		for(var i=0;i<objectList.length;i++){
			name+=objectList[i].name+",";
			id += objectList[i].id+",";
		}
		//保存分配人员
		var opener = environment.dialog.getOpener();
		var reload_opener_page = opener.$('teamrole_reload');
		<%-- 
		$j.ajax({
				url:"${pageContext.request.contextPath}/familydoctor/teamRole/addFdServiceMemberList.action",
				type:"post",
				async:true,
				data:{
					"fdServiceTeams.id":<%=teamId%>,
					"fdRoleInfo.id":<%=roleId%>,
					"fdServiceTeamRole.memberIds":id
				},
				success:function(result){
					environment.dialog.close();
					reload_opener_page.click();
				}/* ,
				error:function(result){
					environment.dialog.close();
					reload_opener_page.click();
				} */
		}); --%>
		
		
		confirmMsg("确定吗？",function(tp){
 			if(tp=='ok'){
 				$j.ajax({
 					url:"${pageContext.request.contextPath}/familydoctor/teamRole/addFdServiceMemberList.action",
 					type:"post",
 					data:{
 						"fdServiceTeams.id":<%=teamId%>,
 						"fdRoleInfo.id":<%=roleId%>,
 						"fdServiceTeamRole.memberIds":id
 					},
 					success:function(result){
 						environment.dialog.close();
 						reload_opener_page.click();
 					}
 				});
			}
		});
	}
	
	//从div把选中的移除
	function removeObject(name,id) {
		for(var i=0;i<objectList.length;i++){
			if(objectList[i].id==id){
			    objectList.splice(i,1); 
				break;
			}
		}
		var content = $j('#cheakobject').html().replace(name+",",'');
		$j('#cheakobject').html(content);
	}
	
	//向div里面插入数据
	function insertObject(id,name){
		objects = new object(id,name);
		for(var i=0;i<objectList.length;i++){
			if(objectList[i].id==id){
				removeObject(name,id);
				return;
			}
		}
		objectList.push(objects);
		var inner = $j('#cheakobject').html();
		$j('#cheakobject').html(inner+name+",");
	}
</script>