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
    <title>第2~9次产前随访服务记录表</title>
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
            //classify
            if("${phPrenatalFollowRecordAfter.classify}"!=''){
                $j('.classify[value="${phPrenatalFollowRecordAfter.classify}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordAfter.classify}"=="异常"){
                $j('.classify[value="异常"]').next().show();
                }
            }
            //转诊
            if("${phPrenatalFollowRecordAfter.isReferral}"!=''){
                $j('.isReferra[value="${phPrenatalFollowRecordAfter.isReferral}"]').attr('checked','checked');
                if("${phPrenatalFollowRecordAfter.isReferral}"=="有"){
                    $j('.isReferra[value="有"]').next().show();
                }
            }
    	});
        function doHandle() {
            saveAndSubmit();
            $('save').click();
        }
        function doBack(){
            window.location.href = 
            	"${pageContext.request.contextPath}/productionvisit/moreFollow/toPhPrenatalFollowRecordAfterList.action"
            		+"?accountId="+$j("#accountId",parent.document).val();
        }
        function doSubmit() {
            confirmMsg("确定提交?",function(tp){
                if(tp=='ok'){
                    saveAndSubmit();
                    $('form_handle').action="<s:url namespace="/productionvisit/moreFollow" action="submitPhPrenatalFollowRecordAfter" includeParams="true"/>";
                    $('save').click();
                }
            });
        }

        //检测附件是否是合法的后缀  inputStr：输入文件值    description：描述信息
        function checkAttachment(inputStr,description) {
            var photostr = ".doc;.docx;.xls;.xlsx;.pdf;.gif;.jpg;.jpeg;.bmp;.txt";
            var isok = true;
            if(inputStr.lastIndexOf(".")<0) {
                isok = false;
            }
            iPos = inputStr.lastIndexOf(".");
            var fileext = inputStr.substring(iPos,inputStr.length).toLowerCase();
            if(photostr.lastIndexOf(fileext)<0) {
                isok=false;
            }
            if ( (description!=null) && (description.length>0) && isok==false ) {
                //alert(description+"不是合法的文件,必须为"+photostr+"格式的!");
                showMsg(description+"不是合法的文件,必须为"+photostr+"格式的!");
            }
            return isok;
        }
        //帮助
        function help(){
            var date = new Date();
            var action = '<s:url namespace="/dataEntry" action="help" includeParams="true"/>';
            environment.dialog.open({
                url : action+'?formCode=phPrenatalFollowRecordAfter&_t=' + date.getTime(),
                width : window.getCoordinates().width-200,
                height : window.getCoordinates().height,
                icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
                title : '帮助详情'
            });
        }
    $j(function(){
    //转诊
        $j('.isReferra').click(function(){
            if($j(this).val()=='无'){
                $j(this).next().next().hide();
                $j(this).next().next().children().val('');
            }else{$j(this).next().show();}
        });
        //所有异常和未见异常
        $j('.classify').click(function(){
            if($j(this).val()=='未见异常'){
                $j(this).next().next().hide().val('');
            }else{$j(this).next().show();}
        });
    });
    //保存和提交的方法
    function saveAndSubmit(div){
        //classify
        var classify=$j('.classify');
        $j(classify).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#classify').val($j(this).val());
            }
        });
    //转诊
        var isReferra=$j('.isReferra');
            $j(isReferra).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#isReferra').val($j(this).val());
            }
        });
    }
	$j(function(){
		$j("#accountId").val($j("#accountId",parent.document).val());
	})
    </script>
</head>

<body>
<s:actionmessage theme="popwind"/>
<div id="wz">
    <ap:step></ap:step>
    <div class="wz_right">
        <div class="but01">
            <div class="pop_button_bar">
                <span><a href="javascript:doHandle();" class="pop_button_blue">保存</a></span>
                <span><a href="javascript:doSubmit();" class="pop_button_red">提交</a></span>
                <span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
<!--                 <span><a href="javascript:help();" title="帮助"><font style="font-size: 12px;color:#50657F ">帮助(?)</font></a></span> -->
            </div>
        </div>
    </div>
</div>

<form id="form_handle" name="form_handle" action="<s:url namespace="/productionvisit/moreFollow" action="saveOrUpdatePhPrenatalFollowRecordAfter" includeParams="true"/>" method="post" >
<input name="save" id="save" type="submit" value="save" style="display: none;">
<input type="hidden" name="phPrenatalFollowRecordAfter.id" value="${phPrenatalFollowRecordAfter.id}">
<%-- <input type="hidden" id="accountId" name="accountId" value="${accountId}"> --%>
<input type="hidden" id="accountId" name="phPrenatalFollowRecordAfter.familyAccountInfo.id" value="${phPrenatalFollowRecordAfter.familyAccountInfo.id}">
<div class="eXtremeTable">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="left" valign="top">
                <img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
            </td>
        </tr>
    </table>
    <table width="100%" class="tableRegion2">
        <%--<tr>--%>
            <%--<td colspan="2" class="label">项目</td>--%>
            <%--<td style="text-align:center;background:#F6FAFE">第2次</td>--%>
        <%--</tr>--%>
        <tr>
            <td colspan="2" class="label">随访日期</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.createTime" value="<fmt:formatDate value='${phPrenatalFollowRecordAfter.createTime}' pattern='yyyy-MM-dd'/>"
                       class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">孕周(周)</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.gestationalWeek" value="${phPrenatalFollowRecordAfter.gestationalWeek}" class="validate['length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">主诉</td>
            <td>
                <textarea name="phPrenatalFollowRecordAfter.mainSuit" class="validate['length[100]'] Added_textarea">${phPrenatalFollowRecordAfter.mainSuit}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">体重(kg)</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.weight" value="${phPrenatalFollowRecordAfter.weight}" class="validate['number','length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td rowspan="4" style="width: 12px;padding: 15px;">产前检查</td>
            <td class="label" width="18%">宫底高度(cm)</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.fundusHeight" value="${phPrenatalFollowRecordAfter.fundusHeight}" class="validate['number','length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td class="label">腹围(cm)</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.widerBellies" value="${phPrenatalFollowRecordAfter.widerBellies}" class="validate['number','length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td class="label">胎位</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.fetal" value="${phPrenatalFollowRecordAfter.fetal}" class="validate['length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td class="label">胎心率(次/分钟)</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.fetalHeartRate" value="${phPrenatalFollowRecordAfter.fetalHeartRate}" class="validate['number','length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">血压(mmHg)</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.bloodPressureHigh" value="${phPrenatalFollowRecordAfter.bloodPressureHigh}" class="validate['number','length[10]'] text3">&nbsp;/
                <input type="text" name="phPrenatalFollowRecordAfter.bloodPressureLow" value="${phPrenatalFollowRecordAfter.bloodPressureLow}" class="validate['number','length[10]'] text3">&nbsp;
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">血红蛋白(g/L)</td>
            <td>
                <input style="margin-left:3px" type="text" name="phPrenatalFollowRecordAfter.hemoglobin" value="${phPrenatalFollowRecordAfter.hemoglobin}" class="validate['number','length[10]'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">尿蛋白</td>
            <td>
            <input type="text" name="phPrenatalFollowRecordAfter.urineProtein" value="${phPrenatalFollowRecordAfter.urineProtein}" class="validate['length[10]'] text3">
        </td>
        </tr>
        <tr>
            <td colspan="2" class="label">其他辅助检查</td>
            <td>
                <textarea name="phPrenatalFollowRecordAfter.otherAuxiliaryExaminations" class="validate['length[2000]'] Added_textarea">${phPrenatalFollowRecordAfter.otherAuxiliaryExaminations}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">分类</td>
            <td>
                <input type="radio" checked name="classify" value="未见异常" class="validate['length[30]'] text3 classify" style="width:2%"> 未见异常
                <input type="radio" name="classify" value="异常" class="validate['length[30]'] text3 classify" style="width:2%"> 异常
                <input type="text" name="phPrenatalFollowRecordAfter.classifyOther" value="${phPrenatalFollowRecordAfter.classifyOther}" class="validate['length[100]'] text3" id="classifyOther" style="width:20%;display:none;">
                <input type="hidden" name="phPrenatalFollowRecordAfter.classify" value="${phPrenatalFollowRecordAfter.classify}" class="validate['length[30]'] text3" id="classify" style="width:20%">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">指导</td>
            <td>
                <textarea name="phPrenatalFollowRecordAfter.advice" class="validate['length[2000]'] Added_textarea">${phPrenatalFollowRecordAfter.advice}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">转诊</td>
            <td>
                <input type="radio" name="isReferra" value="无" class="validate['length[30]'] text3 isReferra" checked style="width:2%">无
                <input type="radio" name="isReferra" value="有" class="validate['length[30]'] text3 isReferra" style="width:2%">有
                <span class="isReferra_have" style="display:none"><br/>
                原因: <input type="text" name="phPrenatalFollowRecordAfter.ireferralReason" value="${phPrenatalFollowRecordAfter.ireferralReason}" class="validate['length[100]'] text3" id="ireferralReason" style="width:20%">
                机构及科室:<input type="text" name="phPrenatalFollowRecordAfter.ireferralOrg" value="${phPrenatalFollowRecordAfter.ireferralOrg}" class="validate['length[100]'] text3" id="ireferralOrg" style="width:20%">
                </span>
                <input type="hidden" name="phPrenatalFollowRecordAfter.isReferral" value="${phPrenatalFollowRecordAfter.isReferral}" class="validate['length[30]'] text3" id="isReferra" style="width:20%">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">下次随访日期</td>
            <td>
                <input type="text" name="phPrenatalFollowRecordAfter.nextFollowDate" value="<fmt:formatDate value='${phPrenatalFollowRecordAfter.nextFollowDate}' pattern='yyyy-MM-dd'/>"
                    class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
            </td>
        </tr>
<!--         <tr> -->
<!--             <td colspan="2" class="label">随访医生签名</td> -->
<!--             <td> -->
<!--                 <input type="text" name="" value="" class="validate['length[20]'] text3"> -->
<!--             </td> -->
<!--         </tr> -->
        <%--<tr>--%>
            <%--<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>--%>
            <%--<td width="85%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">--%>
            <%--<textarea name="phPrenatalFollowRecordAfter.remark" class="validate['length[2000]'] Added_textarea">${phPrenatalFollowRecordAfter.remark}</textarea>--%>
        <%--</td>--%>
    </tr>
    </table>
    <br>
</div>
</form>
</body>
</html>