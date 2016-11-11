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
		<title>医生团队服务包选择页面</title>

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
		
		<script type="text/javascript">
			
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
								<div id="cheakobject" style="height: 85px;background-color: #F6F9FF;overflow-y:visible;">

								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict();
	var text = '';
	var hidden = '';
	var table = '';
	function getTextName() { 
		return text;
	}
	function getHeddinId() {
		return hidden;
	}
	function getTable() {
		return table;
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
// 				if([strs[i].split("=")[0]]=='table') {
// 					table = unescape(strs[i].split("=")[1]);
// 					flag++;
// 				}
				if(flag == 2) {
					break;
				}
			}   
		}
		
		page_reViews();
		
		$('addObjectButton').addEvent("click", select);
		$('objectIframe').src = "${pageContext.request.contextPath}/familydoctor/serviceteams/getDoctorTeamPackageList.action";
	});
	
	/**  根据父页面的值,进行回显*/
	function page_reViews(){
		var serviceItemIdsName = environment.dialog.getOpener().$(text).value;
		var hiddens = environment.dialog.getOpener().$(hidden).value;
		if(serviceItemIdsName!=null&&serviceItemIdsName.length>0){
			var ids = hiddens.split(",");
			var names = serviceItemIdsName.split(",");
			for(var i=0;i<ids.length;i++){
				if(ids[i]!=null&&ids[i].length>0){
					objectList.push(new object(ids[i],names[i]));
				}
			}
			page_show_ObjectList();
		}
	}
	
	var object = function(ids,names) {
		this.id = ids;
		this.name = names;
	};
	
	function select(){
		select_byCheckbox();
	}
	
	/**  选择事件，如果选择框是checkbox  */
	function select_byCheckbox(){
		var ids="",names="";
		for(var i=0,len=objectList.length;i<len;i++){
			ids+=objectList[i].id+",";
			names+=objectList[i].name+",";
		}
		environment.dialog.getOpener().$(getTextName()).value = names;
		environment.dialog.getOpener().$(getHeddinId()).value = ids;
		environment.dialog.getOpener().$(getHeddinId()).click();
		environment.dialog.close();
	}
	
	/**  选择事件，如果选择框是radio  */
	function select_byRadio(){
		var name="";
		var id="";
		//在对象里面取值，然后赋值给变量
		id = objects.id;
		name = objects.name;
		environment.dialog.getOpener().$(getTextName()).value = name;
		environment.dialog.getOpener().$(getHeddinId()).value = id;
		environment.dialog.close();
	}
	
	//向div里面插入数据
	function insertObject(id,name){
		insert_object_byCheckbox(id,name);
	}
	
	/**  添加数据：选择框是checkbox类型  */
	function insert_object_byCheckbox(id,name){
		var objects = new object(id,name);
		
		var isNoExistObject = true;
		for(var i=0,len=objectList.length;i<len;i++){
			if(objectList[i].id==id){
				objectList.splice(i,1);
				isNoExistObject=false;
				break;
			}
		}
		
		if(isNoExistObject) objectList.push(objects);
		
		page_show_ObjectList();
	}
	
	/**  添加数据：选择框是radio类型  */
	function insert_object_byRadio(id,name){
		objects = new object(id,name);
		$('cheakobject').innerHTML = name;
	}
	
	/**  页面显示已选择的内容  */
	function page_show_ObjectList(){
		if(objectList.length>0){
			var show_content = "";
			for(var i=0,len=objectList.length;i<len;i++){
				show_content+="{"+objectList[i].name+"}";
			}
			//if(show_content) $('cheakobject').innerHTML = show_content;
			if(show_content) $j('#cheakobject').html(show_content);
		}
	}
</script>