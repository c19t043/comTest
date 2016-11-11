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
  		<style type="text/css">
  			td,input{
  				height:30px;
  				border:1
  			}
  		</style>
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
								<iframe name="objectIframe" id="objectIframe" scrolling="auto" src="" height="270px;" width="100%" frameborder="0"></iframe>
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
								<div id="cheakObject" style="height: 85px;background-color: #F6F9FF;overflow-y:visible;">
								
								</div>
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
	var set_pro_Object_list = new Array();
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
		
		var serviceItemIdsName = "{"+environment.dialog.getOpener().$("relMealNames").value+","+
			environment.dialog.getOpener().$("relOrgNames").value+"}";
		var serviceItemIds = environment.dialog.getOpener().$("middleID").value;
		var relOrgIDs = environment.dialog.getOpener().$("relOrgIDs").value ;
		var relOrgNames = environment.dialog.getOpener().$("relOrgNames").value ;
		var relMealIDs = environment.dialog.getOpener().$("relMealIDs").value ;
		var relMealNames = environment.dialog.getOpener().$("relMealNames").value ;
		insertObject(serviceItemIds,relMealIDs,relMealNames,relOrgIDs,relOrgNames);
		if(serviceItemIds!=null&&serviceItemIds.length>0){
			$('cheakObject').innerHTML=serviceItemIdsName;
			var ids = serviceItemIds.split(",");
 			var names = serviceItemIdsName.split(":-:");
			for(var i=0;i<ids.length;i++){
				if(ids[i]!=null&&ids[i].length>0){
					objectList.push(new object(ids[i],names[i]));
				}
			}
		}
		$('addObjectButton').addEvent("click", select);
		$('objectIframe').src = "${pageContext.request.contextPath}/medicalorgandbusiness/setpro/getMealList.action";
	});
	
	function select(){
		
		var middleID="",relOrgIDs="",relOrgNames="",relMealIDs="",relMealNames="";
		for(var i=0,len=set_pro_Object_list.length;i<len;i++){
			middleID+=set_pro_Object_list[i].middleID+",";
			relOrgIDs+=set_pro_Object_list[i].orgid+",";
			relOrgNames+=set_pro_Object_list[i].orgname+",";
			relMealIDs+=set_pro_Object_list[i].mealid+",";
			relMealNames+=set_pro_Object_list[i].mealname+",";
		}
		
		middleID=middleID.substring(0, middleID.length-1);
		relOrgIDs=relOrgIDs.substring(0, relOrgIDs.length-1);
		relOrgNames=relOrgNames.substring(0, relOrgNames.length-1);
		relMealIDs=relMealIDs.substring(0, relMealIDs.length-1);
		relMealNames=relMealNames.substring(0, relMealNames.length-1);
		
		/* if(relMealIDs.indexOf(",")!=-1){
			alert("只能选择一个套餐");
			return;
		} */
		
		var mealName =environment.dialog.getOpener().$("relOrgNames");
		
		environment.dialog.getOpener().$("middleID").value = middleID;
		environment.dialog.getOpener().$("relOrgIDs").value = relOrgIDs;
 		environment.dialog.getOpener().$("relOrgNames").value = relOrgNames;
		environment.dialog.getOpener().$("relMealIDs").value = relMealIDs;
 		environment.dialog.getOpener().$("relMealNames").value = relMealNames;
 		
 		$j(mealName).click();
 		
		environment.dialog.close();
	}
	
	//从div把选中的移除
	function removeObject(name,id) {
		for(var i=0;i<objectList.length;i++){
			if(objectList[i].id==id){
			    objectList.splice(i,1); 
				break;
			}
		}
		var content = $j('#cheakObject').html().replace(name+",",'');
		$j('#cheakObject').html(content);
	}
	
	
	//向div里面插入数据
	function insertObject(middleID,mealid,mealname,orgid,orgname){
		set_pro_Object_list = [];
		var set_pro_Object = {
				middleID:middleID,
				mealid : mealid,
				mealname : mealname,
				orgid : orgid,
				orgname : orgname
			};
		
		var addFlag = true;
// 		for(var i=0,len=set_pro_Object_list.length;i<len;i++){
// 			if(set_pro_Object_list[i].middleID == middleID){
// 				set_pro_Object_list.splice(i, 1);
// 				addFlag=false;
// 				break;
// 			}
// 		}
		
		if(addFlag) set_pro_Object_list.push(set_pro_Object);
		
		var content = "";
		for(var i=0,len=set_pro_Object_list.length;i<len;i++){
			content+="{"+set_pro_Object_list[i].mealname+","+set_pro_Object_list[i].orgname+"}";
		}
		$j('#cheakObject').html(content)
	}
</script>