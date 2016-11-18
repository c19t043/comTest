<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>报表展现列表</title>
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
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>


		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/TabPanel.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/js/JTabPanel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/js/Fader.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/js/Math.uuid.js"></script>

		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			window.addEvent('domready', function(){
				
			});
			$j(function(){
				var reportTypeId = '${reportType.reportTypeId}';
				
				if(reportTypeId == '' || '${isShowBtn}' == 'false'){
					$j('.pop_button_blue').hide();
				}
			});
			function doSort(){
				var url="${pageContext.request.contextPath}/mbiserver/report/reportListSortIframe.jsp?reportType.reportTypeId=${reportType.reportTypeId}";
	 			var objVal = window.showModalDialog(url,null,"dialogHeight:300px;dialogWidth:850px;status:off" );
	 			if(objVal != null && objVal[0] == 1){
	 				window.location.href="${pageContext.request.contextPath}/mbi/report/reportShowList.action?reportType.reportTypeId=${reportType.reportTypeId}";;
	 			}
			}
  		</script>
	</head>

	<body>
	<s:actionmessage theme="popwind"/>
	<ap:stepAndOperation></ap:stepAndOperation>
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
		<s:if test="#request.list.size>0">
			<tr align="center" valign="middle" class="text3">
			<c:forEach items="${list}" var="o" varStatus="num">
				<td width="50%" height="32" align="center" bgcolor="#F6F9FE">
					<b>
						<img border="0" src="${pageContext.request.contextPath}/mbiserver/res/images/type.gif" width="12" height="12">&nbsp;
						<a href="javascript:doShow('${o.reportId}')">${o.reportName}</a>
					</b>
				</td>
			<c:choose>
				<c:when test="${num.count%2==0}">
					</tr>
					<c:if test="${!num.last}"><tr align="center" valign="middle" class="text3"></c:if>
				</c:when>
				<c:when test="${num.last && num.count%2!=0}">
					<td width="50%" height="32" align="center" bgcolor="#F6F9FE">&nbsp;</td>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>	
			</c:forEach>
			</tr>
		</s:if>
		<s:else>
			<styles:nolist text="没有可查看的报表!"/>
		</s:else>
		</table>
  	</body>
</html>
<script type="text/javascript">
	function doShow(id) {
		window.location.href='${pageContext.request.contextPath}/mbi/report/showReport.action?report.reportId=' + id + "&flag=1";
	}
</script>
