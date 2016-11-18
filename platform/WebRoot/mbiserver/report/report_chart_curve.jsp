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

<chart caption='${reportName}' 
	numdivlines='9' lineThickness='2' showValues='0' numVDivLines='22'
	formatNumberScale='0' labelDisplay='ROTATE' slantLabels='1'
	anchorRadius='2' anchorBgAlpha='50' showAlternateVGridColor='1'
	anchorAlpha='100' animation='1' limitsDecimalPrecision='0'
	divLineDecimalPrecision='1' formatNumber='0' baseFont='宋体' baseFontSize='12'>
	<categories>
	<c:forEach items="${dataList}" var="dl" varStatus="num">
		<category label='${dl[0]}' />	
	</c:forEach>
	</categories>
	
	<dataset seriesName='${yName}' color='FF0080' anchorBorderColor='FF0080'>
		<c:forEach items="${dataList}" var="dl" varStatus="num">
			<set value='${dl[1]}' />
		</c:forEach>
	</dataset>
</chart>