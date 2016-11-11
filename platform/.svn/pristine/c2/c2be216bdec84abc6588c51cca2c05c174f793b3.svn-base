<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <title>选择医生主页</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
				<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/TabPanel.css" rel="stylesheet" type="text/css">
		
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
		<script type="text/javascript">
	  		var text = '';
			var hidden = '';
			
	  		var data = function(id,name){
				this.id = id;
				this.name = name
			}
			var datas = new data("", "");
			window.addEvent('domready', function(){
				/*var arg = window.dialogArguments;
				var dc = arg.window.document;
				var name = dc.getElementById(arg.text).value;
				var id = dc.getElementById(arg.hidden).value;
				if(id!=null&&id.length>0){
					datas = new data(id,name);
				}*/
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
				
				//加载选择的内容和值
				var names = environment.dialog.getOpener().$(text).value;
				var ids = environment.dialog.getOpener().$(hidden).value;
				if($chk(ids)) {
					insertData(ids,names);
				}
				
				document.getElementById('selectOne').src="${pageContext.request.contextPath}/operationmanage/selectSingleDoctorList.action?text=" + text + "&hidden=" + hidden;
			});
	
			// 好像没用
			function select(){
				var arg = window.dialogArguments;
				var dc = arg.window.document;
				var name=datas.name;
				var id=datas.id;
				dc.getElementById(arg.text).value = name;
				dc.getElementById(arg.hidden).value = id;
				window.close();
			}
			
			function insertData(id,name){
				datas = new data(id,name);
			}
			
			function getTextName() {
				return text;
			}
			
			function getHeddinId() {
				return hidden;
			}
  		</script>
  	</head>
  
	<body>
	  <iframe id="selectOne" src="${pageContext.request.contextPath}/operationmanage/selectSingleDoctorList.action?text=doctorInfo.doctorName&hidden=doctorInfo.id" width="100%" height="100%" frameborder="0"></iframe>
	</body>
</html>
