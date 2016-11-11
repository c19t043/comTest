<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<html>
<head>
    <title>第2~9次产前随访服务查看</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">

    <!-- css/js -->
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
    <style>
        input[type="checkbox"]{
            margin:0 3px 0 12px;
            vertical-align:-3px;
        }
        input[type="text"]{
            width:130px;
        }
        #advice_div{
            display:none;
            float:left;
            margin-left:15px;
            line-height:38px;
        }
        .label{
            text-align:right;
            background:#F6FAFE;
        }
        textarea{
            resize:none;
            overflow:hidden;
        }
    </style>

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

    <!-- attachment组件  -->
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/css/attachment.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/js/attachment.js"></script>

    <!-- util 工具 js -->
    <!--<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils2.js"></script>-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>

    <script type="text/javascript">
        var $j = jQuery.noConflict();
        window.addEvent('domready', function(){
            fc = new FormCheck('form_handle',{
                display:{
                    showErrors:1
                }
            });
    	});
        function doBack(){
            window.location.href = 
            	"${pageContext.request.contextPath}/productionvisit/moreFollow/toPhPrenatalFollowRecordAfterList.action"
            		+"?accountId="+$j("#accountId",parent.document).val();
        }
    </script>
</head>

<body>
<s:actionmessage theme="popwind"/>
<div id="wz">
    <ap:step></ap:step>
    <div class="wz_right">
        <div class="but01">
            <div class="pop_button_bar">
                <span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
            </div>
        </div>
    </div>
</div>

<form id="form_handle" name="form_handle" action="<s:url namespace="/productionvisit/moreFollow" action="saveOrUpdatePhPrenatalFollowRecordAfter" includeParams="true"/>" method="post" >
<input name="save" id="save" type="submit" value="save" style="display: none;">
<input type="hidden" name="phPrenatalFollowRecordAfter.id" value="${phPrenatalFollowRecordAfter.id}">
<input type="hidden" id="basicId" name="phPrenatalFollowRecordAfter.peopleBasicInfo.id" value="${phPrenatalFollowRecordAfter.peopleBasicInfo.id}">
<div class="eXtremeTable">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="left" valign="top">
                <img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
            </td>
        </tr>
    </table>
    <table width="100%" class="tableRegion2">
        <tr>
            <td colspan="2" class="label">随访日期</td>
            <td>
                <fmt:formatDate value='${phPrenatalFollowRecordAfter.createTime}' pattern='yyyy-MM-dd'/>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">孕周(周)</td>
            <td>
                ${phPrenatalFollowRecordAfter.gestationalWeek}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">主诉</td>
            <td>
                ${phPrenatalFollowRecordAfter.mainSuit}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">体重(kg)</td>
            <td>
                ${phPrenatalFollowRecordAfter.weight}
            </td>
        </tr>
        <tr>
            <td rowspan="4" style="width: 12px;padding: 15px;">产前检查</td>
            <td class="label" width="18%">宫底高度(cm)</td>
            <td>
                ${phPrenatalFollowRecordAfter.fundusHeight}
            </td>
        </tr>
        <tr>
            <td class="label">腹围(cm)</td>
            <td>
                ${phPrenatalFollowRecordAfter.widerBellies}
            </td>
        </tr>
        <tr>
            <td class="label">胎位</td>
            <td>
                ${phPrenatalFollowRecordAfter.fetal}
            </td>
        </tr>
        <tr>
            <td class="label">胎心率(次/分钟)</td>
            <td>
               ${phPrenatalFollowRecordAfter.fetalHeartRate}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">血压(mmHg)</td>
            <td>
                ${phPrenatalFollowRecordAfter.bloodPressureHigh} &nbsp;/
               ${phPrenatalFollowRecordAfter.bloodPressureLow}&nbsp;
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">血红蛋白(g/L)</td>
            <td>
                ${phPrenatalFollowRecordAfter.hemoglobin}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">尿蛋白</td>
            <td>
	            ${phPrenatalFollowRecordAfter.urineProtein}
	        </td>
        </tr>
        <tr>
            <td colspan="2" class="label">其他辅助检查</td>
            <td>
                ${phPrenatalFollowRecordAfter.otherAuxiliaryExaminations}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">分类</td>
            <td>
                ${phPrenatalFollowRecordAfter.classify}&nbsp;&nbsp;${phPrenatalFollowRecordAfter.classifyOther}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">指导</td>
            <td>
                ${phPrenatalFollowRecordAfter.advice}
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">转诊</td>
            <td>
            	${phPrenatalFollowRecordAfter.isReferral}
            	<c:if test="${phPrenatalFollowRecordAfter.isReferral == '有'}">
            		  原因: ${phPrenatalFollowRecordAfter.ireferralReason}
                	机构及科室:${phPrenatalFollowRecordAfter.ireferralOrg}
            	</c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">下次随访日期</td>
            <td>
               <fmt:formatDate value='${phPrenatalFollowRecordAfter.nextFollowDate}' pattern='yyyy-MM-dd'/>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">随访医生签名</td>
            <td>
                ${phPrenatalFollowRecordAfter.createPerson.userName}
            </td>
        </tr>
    </tr>
    </table>
    <br>
</div>
</form>
</body>
</html>