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
		<title>选择儿童</title>

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
								<div id="checkObject" style="height: 230px;;background-color: #F6F9FF;overflow-y:visible;">
									
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
		
		var opener = environment.dialog.getOpener();
		var serviceItemIdsName = opener.$("babyName").value;
		var serviceItemIds = opener.$("babyId").value;
		if(serviceItemIds!=null&&serviceItemIds.length>0){
			$('checkObject').innerHTML="{"+opener.$("babyName").value+","+opener.$("babySex").value+","+opener.$("babyBirthday").value+"}";
			objectList.push(new object(serviceItemIds,serviceItemIdsName));
			/* var ids = serviceItemIds.split(",");
			var names = serviceItemIdsName.split(",");
			for(var i=0;i<ids.length;i++){
				if(ids[i]!=null&&ids[i].length>0){
					
				}
			} */
		}
		getAccountId();
		$('addObjectButton').addEvent("click", select);
		$('objectIframe').src = "${pageContext.request.contextPath}/publichealth/familyaccount/toChange.action?accountId="
				+opener.$("accountId").value+"&page_id="+opener.$("page_id").value;
	});
	
	function select(){
		var id,babyName,babySex,babyBirthday;
		for(var i=0;i<objectList.length;i++){
			id=objectList[i].id;
			babyName=objectList[i].babyName;
			babySex=objectList[i].babySex;
			babyBirthday=objectList[i].babyBirthday;
		}
		
		//保存分配人员
		var opener = environment.dialog.getOpener();
		
		if(id){
			opener.$("babyId").value = id;
			opener.$("babyName").value = babyName;
			opener.$("babySex").value = babySex;
			opener.$("babyBirthday").value = babyBirthday;
		}
		environment.dialog.close();
		//opener.$("childDo").click();
/* 		var toAdd = opener.$("toAdd").value;
		var doAdd = $j("#"+toAdd,opener.frames[0].document);
		alert(doAdd.html());
		doAdd.click(); */
		$j("#toAdd",opener.frames[0].document).click();
		
	
/* 		alert(opener.$("babyId").value);
		alert(opener.$("babyName").value);
		alert(opener.$("babySex").value);
		alert(opener.$("babyBirthday").value); */
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
	
	function getAccountId(){
		var accountId = environment.dialog.getOpener().$("accountId").value;
		return accountId;
	}
	
	//向div里面插入数据
	function insertObject(id,babyName,babySex,babyBirthday){
		var baby_object = {
				"id":id,
				"babyName":babyName,
				"babySex":babySex,
				"babyBirthday":babyBirthday
		};
	
		if(objectList.length>1)
			objectList = new Array();
		
		objectList.push(baby_object);
		
		var content = "{"+babyName+","+babySex+","+babyBirthday+"}";
		$j('#checkObject').html(content);
	}
</script>