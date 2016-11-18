<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展现报表</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/selectDate.js"></script>
<script src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery-1.3.2.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/flexigrid.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/contextmenu.css" type="text/css" rel="stylesheet"  />
<script src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.flexigrid.js" type="text/javascript" ></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/linkbutton.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/shadow.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/dialog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.draggable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.resizable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.linkbutton.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.shadow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.dialog.js"></script>
<!-- 右键快显菜单 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jquery.contextmenu.packed.js"></script>
<!-- 调试插件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mbiserver/components/flexigrid/css/blackbird.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/blackbird.js"></script>
<!-- 下拉列表框 -->
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jQuery.FillOptions.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/flexigrid/js/jQuery.CascadingSelect.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/fusioncharts/js/FusionCharts.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/mootools/mootools-1.2-core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/mootools/mootools-1.2-more.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/fw-moo12-adapter.js"></script>
<!-- environment弹出窗口样式 -->
<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>	

<STYLE type="text/css">
.btn_sure{
	display:block;
	font-size:12px;
	color: #15428B;
	font-weight: bold;
	}

</STYLE>


<script type="text/javascript">
var $j = jQuery.noConflict(); 

$j(document).ready(function(){
	var s  =  "网页可见区域宽："+  document.body.clientWidth; 
		s  +=  "<br />网页可见区域高："+  document.body.clientHeight; 
		s  +=  "<br />网页可见区域高："+  document.body.offsetWeight  +"  (包括边线的宽)"; 
		s  +=  "<br />网页可见区域高："+  document.body.offsetHeight  +"  (包括边线的宽)"; 
		s  +=  "<br />网页正文全文宽："+  document.body.scrollWidth; 
		s  +=  "<br />网页正文全文高："+  document.body.scrollHeight; 
		s  +=  "<br />网页被卷去的高："+  document.body.scrollTop; 
		s  +=  "<br />网页被卷去的左："+  document.body.scrollLeft; 
		s  +=  "<br />网页正文部分上："+  window.screenTop; 
		s  +=  "<br />网页正文部分左："+  window.screenLeft; 
		s  +=  "<br />屏幕分辨率的高："+  window.screen.height; 
		s  +=  "<br />屏幕分辨率的宽："+  window.screen.width; 
		s  +=  "<br />屏幕可用工作区高度："+  window.screen.availHeight; 
		s  +=  "<br />屏幕可用工作区宽度："+  window.screen.availWidth; 
		s  +=  "<br />页面高度："+document.documentElement.clientHeight;
		log.info(s);

	var maiheight = document.documentElement.clientHeight;
	
	var w = $j("#ptable").width() - 3;
	var gapTop = 30;
	var otherpm = 250; //GridHead，toolbar，footer,gridmargin
	var h = maiheight - gapTop - otherpm;

	var grid=$j("#flexTable").flexigrid({
		width: 'auto',
		height: h,
		minwidth: 30, //列的最小宽度
		url: '${pageContext.request.contextPath}/mbi/report/showReportByFlexiGrid.action?report.reportId=${report.reportId}',
			 /*  + '&packageContent.column='+encodeURI('${packageContent.column}') + '&packageContent.value='+encodeURI('${packageContent.value}')
		      + '&packageContent.dimStructId=${packageContent.dimStructId}&packageContent.other='+encodeURI('${packageContent.other}')
		      + '&packageContent.isToTake=${packageContent.isToTake}&packageContent.tableName='+encodeURI('${packageContent.tableName}')
		      + '&flag=${flag}' ,*/
		dataType: 'json',
		striped: true,
		novstripe: false,
		colModel : [
				<c:forEach items="${columnList}" var="cl" varStatus="status">
				<c:choose>
					<c:when test="${status.last}">
						{display: '${cl}', name : '${cl}', width : 120, sortable : false, align: 'center'}
					</c:when>
					<c:otherwise>
						{display: '${cl}', name : '${cl}', width : 120, sortable : false, align: 'center'},
					</c:otherwise>
				</c:choose>
				</c:forEach>
			],
		errormsg: '发生异常',
		sortname: "id",
		sortorder: "desc",
		//qop: "LIKE",//搜索的操作符
		usepager: false,
		title: '${report.reportName}&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/mbi/report/reportShowList.action?reportType.reportTypeId=${report.reportType.reportTypeId}"><img height="20px" width="20px" border="0" style="margin-bottom: -5px" alt="返回" src="${pageContext.request.contextPath}/mbiserver/res/images/cancel1.png"></a>',
		pagestat: '显示记录从{from}到{to}，总数 {total} 条',
		useRp: true,
		rp: 10,
		rpOptions: [10, 15, 20, 30, 40, 100], //可选择设定的每页结果数
		nomsg: '没有符合条件的记录存在',
		minColToggle: 1, //允许显示的最小列数
		showTableToggleBtn: true,
		autoload: true, //自动加载，即第一次发起ajax请求
		resizable: true, //table是否可伸缩
		procmsg: '加载中, 请稍等 ...',
		hideOnSubmit: true, //是否在回调时显示遮盖
		blockOpacity: 0.5,//透明度设置
		showcheckbox: false,//是否显示第一列的checkbox（用于全选）
		gridClass: "bbit-grid",//样式
        //rowhandler: contextmenu,  是否启用行的扩展事情功能,在生成行时绑定事件，如双击，右键菜单等
		rowbinddata: true,//配合上一个操作，如在双击事件中获取该行的数据
		//onrowchecked: callme//在每一行的的checkbox选中状态发生变化时触发某个事件
		//onrowchecked: false//在每一行的的checkbox选中状态发生变化时触发某个事件
	});
});
	
	function doQuery(showType){
		if (showType == 2) {
			if(document.getElementById("selectKpiValue.pieKpiValue").value == ''){
				alert("请选择饼状图显示的指标！");
				return false;
			} 
		}else if (showType == 0) {
			if(document.getElementById("selectKpiValue.barKpiValue").value == ''){
				alert("请选择柱状图显示的指标！");
				return false;
			}
		}else if (showType == 1) {
			if(document.getElementById("selectKpiValue.curveKpiValue").value == ''){
				alert("请选择曲线图显示的指标！");
				return false;
			}
		}
		
		var action = '${pageContext.request.contextPath}/mbi/report/doChart.action?report.reportId=${report.reportId}&report.showType='+showType
		                                           + '&selectKpiValue.currentDimStruct=' + encodeURI(document.getElementById("selectKpiValue.currentDimStruct").value);
		if(showType == 0){//柱状图
			document.getElementById("showType").value = '0';
			document.getElementById("kpiValueName").value = document.getElementById("selectKpiValue.barKpiValue").value;
			action += '&selectKpiValue.barKpiValue=' + encodeURI(document.getElementById("selectKpiValue.barKpiValue").value);
		}else if(showType == 1){//曲线图
			document.getElementById("showType").value = '1';
			document.getElementById("kpiValueName").value = document.getElementById("selectKpiValue.curveKpiValue").value;
			action += '&selectKpiValue.curveKpiValue=' + encodeURI(document.getElementById("selectKpiValue.curveKpiValue").value);
		}else if(showType == 2){//饼图
			document.getElementById("showType").value = '2';
			document.getElementById("kpiValueName").value = document.getElementById("selectKpiValue.pieKpiValue").value;
			action += '&selectKpiValue.pieKpiValue=' + encodeURI(document.getElementById("selectKpiValue.pieKpiValue").value);
		}
		document.getElementById('chartIframe').src = action;
	}
</script>

</head>
<body>
<div id="ptable">
	<table id="flexTable" style="display:none"></table>
</div>
<table style="font-size: 12px;" id="chart_table_id">
<tr>
	<td>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
		<input type="hidden" name="packageContent.dimStructId" id="packageContent.dimStructId" value="${packageContent.dimStructId}">
		<input type="hidden" name="selectKpiValue.currentDimStruct" id="selectKpiValue.currentDimStruct" value="${selectKpiValue.currentDimStruct}">
		<input type="hidden" id="showType" name="report.showType" value="${report.showType }">
		<input type="hidden" id="kpiValueName" name="report.kpiValueName" value="${report.kpiValueName }">
			<tr align="center" valign="middle" class="text3">
				<td width="33%" height="32" align="left" bgcolor="#F6F9FE"/>
					<label>&nbsp;柱状图显示指标：</label>
					<s:select list="kpiValueMap" listKey="key" listValue="value" cssStyle="width:170px;" theme="simple"
		          			  headerKey="111" headerValue="--请选择--" 
		          			  id="selectKpiValue.barKpiValue" name="selectKpiValue.barKpiValue" value="selectKpiValue.barKpiValue" />
		          	<a href="javascript:doQuery(0);"><img title="柱状图展示" style="border:0;padding:0;" width="17px" height="17px" src="${pageContext.request.contextPath}/mbiserver/res/images/bar.png"></a>
				</td>
				<td width="33%" height="32" align="left" bgcolor="#F6F9FE"/>
					<label>&nbsp;曲线图显示指标：</label>
					<s:select list="kpiValueMap" listKey="key" listValue="value" cssStyle="width:170px;" theme="simple"
					 headerKey="111" headerValue="--请选择--" 
              			      id="selectKpiValue.curveKpiValue" name="selectKpiValue.curveKpiValue" value="selectKpiValue.curveKpiValue" />
					<a href="javascript:doQuery(1);"><img title="曲线图展示" style="border:0;padding:0;" width="17px" height="17px" src="${pageContext.request.contextPath}/mbiserver/res/images/curve.png"></a>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td>
		<iframe name="chartIframe" id="chartIframe" scrolling="auto" src="" height="500px" width="100%" frameborder="0"></iframe>	
	</td>
</tr>
</table>
</body>
</html>
<script>
	$j("#chart_table_id").width(document.body.scrollWidth);
	document.all("chartIframe").style.width=document.body.scrollWidth-10;
	document.all("chartIframe").style.height=document.documentElement.clientHeight+90;
</script>