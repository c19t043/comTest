<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<html>
	<head>
		<title>ETL管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/pop_button.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>

		<!-- environment弹出窗口样式 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>

		<!-- miftree -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/miftree/mif_css/mif-tree.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/miftree/mif.tree-v1.1.js"></script>

		<!-- ecTable列表 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">

		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>

		<!-- notimoo消息提示  -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>
	</head>
	<body id="bodyId" style="height: 100%">
		<s:actionmessage theme="popwind"/>	
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
											<div class="Searchlist_Part_1_text">专业树</div>
										</div>
									</div>
								</div>
								<div class="eXtremeTable">
									<div id="oragn_tree" style="width: 100%; overflow: auto;margin-top:0px;"></div>
								</div>
							</div>
						</div>
					</td>
					<td width="80%" height="100%" align="left" class="tree_right" >
						<iframe name="organIframe" id="organIframe" scrolling="auto" 
						src="${pageContext.request.contextPath}/doctorManager/major/getMajors.action" 
						height="100%" width="100%" frameborder="0"></iframe>	
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict();
	window.addEvent('domready',function(){
		tree = new Mif.Tree({
			container: $('oragn_tree'),
			forest: false,
			//hiddenRoot:true,
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
		tree.load({url: '${pageContext.request.contextPath}/doctorManager/jsonOp/json_queryMajorsTrees.action?times='+new Date().getTime()});
		tree.addEvent('select',function(node){
		   var action="${pageContext.request.contextPath}/doctorManager/major/getMajors.action";
		   action+="?major.parent.id="+node.data.id;
		   document.getElementById('organIframe').src = action;
		});
		//$("oragn_tree").setStyle("height",window.getSize().y - $("navigation").getSize().y-42);
		var height = window.getCoordinates().height;
		$("oragn_tree").setStyle("height",height - 68);
	});
</script>