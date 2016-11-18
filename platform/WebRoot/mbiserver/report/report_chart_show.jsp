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
<title>展现报表的图形</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/mbiserver/components/fusioncharts/js/FusionCharts.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/mootools/mootools-1.2-core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/mootools/mootools-1.2-more.js"></script>
	
</head>
<div>
	<c:if test="${report.showType == '2'}">
		<div id="chartdiv1" align="center" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;float:left;width:100%;">饼图</div>
		<script>
			var widthValue = window.getCoordinates().width - 30;
			var chart = new FusionCharts("${pageContext.request.contextPath}/mbiserver/components/fusioncharts/charts/Pie2D.swf", "ChartId", widthValue, "300", "0", "0");
			var action='${pageContext.request.contextPath}/mbi/report/doChartByPie.action';
			var params='report.reportId=${report.reportId}&selectKpiValue.pieKpiValue=' + encodeURI('${selectKpiValue.pieKpiValue}')
			          + '&selectKpiValue.currentDimStruct=' + encodeURI('${selectKpiValue.currentDimStruct}');
			chart.setDataURL(
				encodeURIComponent(action+'?'+params)
			);
			chart.render("chartdiv1");  
		</script>
	</c:if>
	<c:if test="${report.showType == '0'}">
		<div id="chartdiv2" align="center" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;float:right;width:100%">柱状图</div>
		<script>
			var widthValue = window.getCoordinates().width - 30;
			var chart = new FusionCharts("${pageContext.request.contextPath}/mbiserver/components/fusioncharts/charts/Column3D.swf", "ChartId", widthValue, "300", "0", "0");
			var action='${pageContext.request.contextPath}/mbi/report/doChartByBar.action';
			var params='report.reportId=${report.reportId}&selectKpiValue.barKpiValue=' + encodeURI('${selectKpiValue.barKpiValue}')
			          + '&selectKpiValue.currentDimStruct=' + encodeURI('${selectKpiValue.currentDimStruct}');
			chart.setDataURL(
				encodeURIComponent(action+'?'+params)
			);		   
			chart.render("chartdiv2");  
		</script>
	</c:if>
	<c:if test="${report.showType == '1'}">
		<div id="chartdiv3" align="center" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;float:left;width:100%">线图</div>
		<script>
			var widthValue = window.getCoordinates().width - 30;
			var chart = new FusionCharts("${pageContext.request.contextPath}/mbiserver/components/fusioncharts/charts/MSLine.swf", "ChartId", widthValue, "300", "0", "0");
			var action='${pageContext.request.contextPath}/mbi/report/doChartByCurve.action';
			var params='report.reportId=${report.reportId}&selectKpiValue.curveKpiValue=' + encodeURI('${selectKpiValue.curveKpiValue}')
			          + '&selectKpiValue.currentDimStruct=' + encodeURI('${selectKpiValue.currentDimStruct}');
			chart.setDataURL(
				encodeURIComponent(action+'?'+params)
			);				   
			chart.render("chartdiv3");  
		</script>
	</c:if>
</div>
