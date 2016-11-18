<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>报表显示主界面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/pop_button.css" rel="stylesheet" type="text/css">
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

		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/miftree/mif.tree-v1.1.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/miftree/mif_css/mif-tree.css" />
		
	</head>
	<s:actionmessage theme="popwind"/>
	<body id="bodyId" style="height: 100%;overflow: auto;">
		<div id="main">
			<table width="100%" style="height: 100%" id="tableId" border="0" cellpadding="0" cellspacing="0">
				<tr id="trId" style="height: 100%" align="left" valign="top">
					<td width="20%" height="100%" align="left">
						<div class="Searchlist_Content">
							<div class="Added_ContentBoder">
								<div class="Searchlist_Part">
									<div class="Organization_Part">
										<div class="Searchlist_Part_1" style="width: 95%;">
											<div class="Searchlist_Part_1_ico"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/Searchlist_Part_ico.gif" height="13" width="16"></div>
											<div class="Searchlist_Part_1_text">报表分类树1</div>
										</div>
									</div>
								</div>
								<div class="eXtremeTable">
									<div style="background-color: #EFF6FE;width: 100%;">
										<input type="text" id=query_name style="width: 90%;"/><img id="btnFind" style="cursor: pointer;" src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/sousuo01.gif"/>
									</div>
									<div id="object_tree" style="width: 100%; overflow: auto;margin-top:0px;"></div>
								</div>
							</div>
						</div>
					</td>
					<td width="80%" height="100%" align="left" class="tree_right" >
 						<iframe name="objectIframe" id="objectIframe" scrolling="auto" src="" height="100%" width="100%" frameborder="0"></iframe>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>

<script type="text/javascript">
	window.addEvent('domready', function(){
		//document.getElementById('objectIframe').src = "${pageContext.request.contextPath}/mbi/report/reportShowListInit.action";
	});


	tree = new Mif.Tree({
		container: $('object_tree'),
		forest: true,
		initialize: function(){
			//this.initCheckbox('deps');
			new Mif.Tree.KeyNav(this);
		},
		types: {
			folder:{
				openIcon: 'mif-tree-open-icon',
				closeIcon: 'mif-tree-close-icon'
			},
			loader:{
				openIcon: 'mif-tree-loader-open-icon',
				closeIcon: 'mif-tree-loader-close-icon'
			},
			disabled:{
				openIcon: 'mif-tree-open-icon',
				closeIcon: 'mif-tree-close-icon',
				dragDisabled: true,
				cls: 'disabled'
			},
			book:{
				openIcon: 'mif-tree-book-icon',
				closeIcon: 'mif-tree-book-icon',
				loadable: true
			},
			bin:{
				openIcon: 'mif-tree-bin-open-icon',
				closeIcon: 'mif-tree-bin-close-icon'
			}
		},
		dfltType:'folder'
	});
	
	tree.addEvent('load',function(){
		$('btnFind').addEvent('click',function(){
			var dep = $('query_name').value;
			tree.root.recursive(function(){
				if(this.name.indexOf(dep)!=-1){
					tree.scrollTo(this);
					var highlight = function(){
						if(this.getDOM('node') != null){
							this.getDOM('node').highlight();
						}
					};
					highlight.delay(1000,this);
					highlight.delay(500,this);
					return true;
				}
			});
		});
	});
	
	tree.load({
		url: '${pageContext.request.contextPath}/mbi/report/tree.action?times='+new Date().getTime()
	});
	
	tree.addEvent('select',function(node){
		document.getElementById('objectIframe').src = "${pageContext.request.contextPath}/mbi/report/reportShowList.action?reportType.reportTypeId="+node.data.id;
	});
	var height = window.getCoordinates().height;
	$("object_tree").setStyle("height",height - 85);
</script>

