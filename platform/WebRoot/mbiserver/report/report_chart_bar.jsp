<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<?xml version='1.0' encoding='UTF-8'?>
<chart palette='4' decimals='0' enableSmartLabels='1'
	   caption='${reportName}' xAxisName='${xName}' yAxisName='${yName}'
	   isSmartLineSlanted='0.1' skipOverlapLabels='1' isSmartLineSlanted='0'
	   baseFontSize='12' formatNumberScale='0' enableRotation='0' bgColor='FFFFFF,22BBFF' formatNumber='0'
	   bgAlpha='40,100' bgRatio='0,100' numberSuffix='' bgAngle='360' formatNumber='0' canvasBorderThickness='50' canvasBorderColor='FF0000'
	   showBorder='10' startingAngle='70' >
	   
	<c:forEach items="${dataList}" var="dl" varStatus="num">
			<set label='${dl[0]}' value='${dl[1]}' name='${dl[0]}' />
	</c:forEach>
	
</chart>