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
    <title>产后42天访视记录</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">

    <!-- css/js -->
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
    <style>
        input[type="radio"],input[type="checkbox"]{
            margin:0 3px 0 12px;
            vertical-align:-3px;
        }
        input[type="text"]{
            width:130px;
        }
        #referral_div,#advice_div{
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
            if("${phPostpartumFollowRecord.classify}"!=''){
                $j('.classify[value="${phPostpartumFollowRecord.classify}"]').attr('checked','checked');
                if("${phPostpartumFollowRecord.classify}"=="未恢复"){
                    $j('.classify[value="未恢复"]').next().show();
                }
            }
            //breast
            if("${phPostpartumFollowRecord.breast}"!=''){
                $j('.breast[value="${phPostpartumFollowRecord.breast}"]').attr('checked','checked');
                if("${phPostpartumFollowRecord.breast}"=="异常"){
                    $j('.breast[value="异常"]').next().show();
                }
            }
            //lochia
            if("${phPostpartumFollowRecord.lochia}"!=''){
                $j('.lochia[value="${phPostpartumFollowRecord.lochia}"]').attr('checked','checked');
                if("${phPostpartumFollowRecord.lochia}"=="异常"){
                    $j('.lochia[value="异常"]').next().show();
                }
            }
            //uterus
            if("${phPostpartumFollowRecord.uterus}"!=''){
                $j('.uterus[value="${phPostpartumFollowRecord.uterus}"]').attr('checked','checked');
                if("${phPostpartumFollowRecord.uterus}"=="异常"){
                    $j('.uterus[value="异常"]').next().show();
                }
            }
            //wound
            if("${phPostpartumFollowRecord.wound}"!=''){
                $j('.wound[value="${phPostpartumFollowRecord.wound}"]').attr('checked','checked');
                if("${phPostpartumFollowRecord.wound}"=="异常"){
                    $j('.wound[value="异常"]').next().show();
                }
            }
            <%--//转诊--%>
            <%--if("${phPostpartumFollowRecord.isReferral}"!=''){--%>
                <%--$j('.isReferra[value="${phPostpartumFollowRecord.isReferral}"]').attr('checked','checked');--%>
                <%--if("${phPostpartumFollowRecord.isReferral}"=="有"){--%>
                    <%--$j('.isReferra[value="有"]').next().show();--%>
                <%--}--%>
            <%--}--%>
            //处理
            if("${phPostpartumFollowRecord.handle}"!=''){
                $j('.handle[value="${phPostpartumFollowRecord.handle}"]').attr('checked','checked');
                if("${phPostpartumFollowRecord.handle}"=="转诊"){
                    $j('.handle[value="转诊"]').next().show();
                }
            }
            //指导
            if("${phPostpartumFollowRecord.advice}"!=''){
                var advice=$j('.advice');
                var arr="${phPostpartumFollowRecord.advice}".split(',');
                $j(advice).each(function(index){
                    for(var i=0,l=arr.length;i<l;i++){
                        if($j(this).val()==arr[i]){
                            $j(this).attr('checked','checked');
                            if($j(this).val()=='其他'){
                                $j(this).next().show();
                            }
                        }
                    }
                });
            }
    });
        function doHandle() {
            saveAndSubmit();
            $('save').click();
        }
        function doBack(){
        	window.location.href = "${pageContext.request.contextPath}/productionvisit/postpartumVisit/toList.action"
    			+"?accountId="+$j("#accountId",parent.document).val()
    			+ "&phPostpartumFollowRecord.followUpOpportunity=1";
        }
        function doSubmit() {
            confirmMsg("确定提交?",function(tp){
                if(tp=='ok'){
                    saveAndSubmit();
                    $('form_handle').action="<s:url namespace="/productionvisit/postpartumVisit" action="submitPhPostpartumFollowRecord" includeParams="true"/>";
                    $('save').click();
                }
            });
        }
        function download(exchangeId){
            window.location.href = '<s:url action="downloadAttachment" includeParams="true"/>' + '?exchange.exchangeId=' + exchangeId;
        }
        function delAttachment() {
            confirmMsg("确定删除！！",function(tp){
                if(tp=='ok'){
                    document.getElementById('fileAttachmentId').style.display='none';
                    document.getElementById('exchange.exchangePname').value='';
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
                url : action+'?formCode=phPostpartumFollowRecord&_t=' + date.getTime(),
                width : window.getCoordinates().width-200,
                height : window.getCoordinates().height,
                icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
                title : '帮助详情'
            });
        }
    $j(function(){
        <%--//转诊--%>
        <%--$j('.isReferra').click(function(){--%>
            <%--if($j(this).val()=='无'){--%>
                <%--$j(this).next().next().hide();--%>
                <%--$j(this).next().next().children().val('');--%>
            <%--}else{$j(this).next().show();}--%>
        <%--});--%>
        //所有异常和未见异常
        $j('.breast,.lochia,.uterus,.wound').click(function(){
            if($j(this).val()=='未见异常'){
                $j(this).next().next().hide().val('');
            }else{$j(this).next().show();}
        });
        //分类1
        $j('.classify').click(function(){
            if($j(this).val()=='已恢复'){
                $j(this).next().next().hide().val('');
            }else{$j(this).next().show();}
        });
        //处理1
        $j('.handle').click(function(){
            if($j(this).val()=='结案'){
                $j(this).next().next().hide();
                $j(this).next().next().children().val('');
            }else{$j(this).next().show();}
        });
        //指导
        $j('.advice').click(function(){
            if($j(this).val()=='其他'){
                if($j(this).attr('checked')=='checked'){
                    $j(this).next().show();
                }else{
                    $j(this).next().hide().val('');
                }
            }
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
        //breast
        var breast=$j('.breast');
            $j(breast).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#breast').val($j(this).val());
            }
        });
        //lochia
        var lochia=$j('.lochia');
            $j(lochia).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#lochia').val($j(this).val());
            }
        });
        //uterus
        var uterus=$j('.uterus');
            $j(uterus).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#uterus').val($j(this).val());
            }
        });
        //wound
        var wound=$j('.wound');
            $j(wound).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#wound').val($j(this).val());
            }
        });
        //处理1
        var handle=$j('.handle');
            $j(handle).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#handle').val($j(this).val());
            }
        });
        <%--//转诊--%>
        <%--var isReferra=$j('.isReferra');--%>
            <%--$j(isReferra).each(function(){--%>
            <%--if($j(this).attr('checked')!=undefined){--%>
                <%--$j('#isReferra').val($j(this).val());--%>
            <%--}--%>
        <%--});--%>
        //指导
        var advice=$j('.advice');
        var advice_str='';
        $j(advice).each(function (index) {
            if($j(this).attr('checked')=='checked'){
                advice_str+=','+$j(this).val();
            }
        });
        $j('#advice').val(advice_str.substring(1));
    }
	$j(function(){
		$j("#accountId").val($j("#accountId",parent.document).val());
	})
    </script>
</head>

<body>
<s:actionmessage theme="popwind"/>
<div id="wz">
    <%-- <ap:step></ap:step> --%>
    <div class="wz_right">
        <div class="but01">
            <div class="pop_button_bar">
                <span><a href="javascript:doHandle();" class="pop_button_blue">保存</a></span>
                <%-- <span><a href="javascript:doSubmit();" class="pop_button_red">提交</a></span> --%>
                <span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
<!--                 <span><a href="javascript:help();" title="帮助"><font style="font-size: 12px;color:#50657F ">帮助(?)</font></a></span> -->
            </div>
        </div>
    </div>
</div>

<form id="form_handle" name="form_handle" action="<s:url namespace="/productionvisit/postpartumVisit" action="saveOrUpdatePhPostpartumFollowRecord"  includeParams="true"/>" method="post" >
<input name="save" id="save" type="submit" value="save" style="display: none;">
<input type="hidden" name="phPostpartumFollowRecord.id" value="${phPostpartumFollowRecord.id}">
<input type="hidden" id="accountId" name="accountId" >
<input type="hidden" name="phPostpartumFollowRecord.followUpOpportunity" value="${phPostpartumFollowRecord.followUpOpportunity}">
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
            <td class="label" width="20%">随访日期</td>
            <td>
                <input type="text" name="phPostpartumFollowRecord.createTime" value="<fmt:formatDate value='${phPostpartumFollowRecord.createTime}' pattern='yyyy-MM-dd'/>"
                       class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td class="label">随访方式</td>--%>
            <%--<td>--%>
                <%--<s:select cssStyle="width:120px;" cssClass="text3" list="#{'门诊':'门诊','家庭':'家庭','电话':'电话'}" listKey="key" listValue="value"--%>
                    <%--theme="simple" name="phPostpartumFollowRecord.followType" value="phPostpartumFollowRecord.followType"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="label">体温</td>--%>
            <%--<td>--%>
                <%--<input type="text" name="phPostpartumFollowRecord.temperature" value="${phPostpartumFollowRecord.temperature}" class="validate['length[5]'] text3"><span>℃</span>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="label">一般健康情况</td>
            <td>
                <textarea name="phPostpartumFollowRecord.generalHealthStatus" class="validate['length[2000]'] Added_textarea">${phPostpartumFollowRecord.generalHealthStatus}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">一般心理状况</td>
            <td>
                <textarea name="phPostpartumFollowRecord.generalMentalState" class="validate['length[500]'] Added_textarea">${phPostpartumFollowRecord.generalMentalState}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">血压</td>
            <td>
                <input type="text" name="phPostpartumFollowRecord.bloodPressureHigh" value="${phPostpartumFollowRecord.bloodPressureHigh}" class="validate['number','length[10]'] text3" style="width:10%">&nbsp;/
                <input type="text" name="phPostpartumFollowRecord.bloodPressureLow" value="${phPostpartumFollowRecord.bloodPressureLow}" class="validate['number','length[10]'] text3" style="width:10%">&nbsp;mmHg
            </td>
        </tr>
        <tr>
            <td class="label">乳房</td>
            <td>
                <input type="radio" name="breast" checked value="未见异常" class="validate['length[30]'] text3 breast" style="width:1%"> 未见异常
                <input type="radio" name="breast" value="异常" class="validate['length[30]'] text3 breast" style="width:1%"> 异常
                <input type="text" name="phPostpartumFollowRecord.breastException" value="${phPostpartumFollowRecord.breastException}" class="validate['length[100]'] text3" id="breastException" style="width:20%;display:none;">
                <input type="hidden" name="phPostpartumFollowRecord.breast" value="${phPostpartumFollowRecord.breast}" class="validate['length[30]'] text3" id="breast" style="width:20%">
            </td>
        </tr>
        <tr>
            <td class="label">恶露</td>
            <td>
                <input type="radio" name="lochia" checked value="未见异常" class="validate['length[30]'] text3 lochia" style="width:1%"> 未见异常
                <input type="radio" name="lochia" value="异常" class="validate['length[30]'] text3 lochia" style="width:1%"> 异常
                <input type="text" name="phPostpartumFollowRecord.lochiaException" value="${phPostpartumFollowRecord.lochiaException}" class="validate['length[100]'] text3" id="lochiaException" style="width:20%;display:none;">
                <input type="hidden" name="phPostpartumFollowRecord.lochia" value="${phPostpartumFollowRecord.lochia}" class="validate['length[30]'] text3" id="lochia" style="width:20%">
            </td>
        </tr>
        <tr>
            <td class="label">子宫</td>
            <td>
                <input type="radio" name="uterus" checked value="未见异常" class="validate['length[30]'] text3 uterus" style="width:1%"> 未见异常
                <input type="radio" name="uterus" value="异常" class="validate['length[30]'] text3 uterus" style="width:1%"> 异常
                <input type="text" name="phPostpartumFollowRecord.uterusException" value="${phPostpartumFollowRecord.uterusException}" class="validate['length[100]'] text3" id="uterusException" style="width:20%;display:none;">
                <input type="hidden" name="phPostpartumFollowRecord.uterus" value="${phPostpartumFollowRecord.uterus}" class="validate['length[30]'] text3" id="uterus" style="width:20%">
            </td>
        </tr>
        <tr>
            <td class="label">伤口</td>
            <td>
                <input type="radio" name="wound" checked value="未见异常" class="validate['length[30]'] text3 wound" style="width:1%"> 未见异常
                <input type="radio" name="wound" value="异常" class="validate['length[30]'] text3 wound" style="width:1%"> 异常
                <input type="text" name="phPostpartumFollowRecord.woundException" value="${phPostpartumFollowRecord.woundException}" class="validate['length[100]'] text3" id="woundException" style="width:20%;display:none;">
                <input type="hidden" name="phPostpartumFollowRecord.wound" value="${phPostpartumFollowRecord.wound}" class="validate['length[30]'] text3" id="wound" style="width:20%">
            </td>
        </tr>
        <tr>
            <td class="label">其他</td>
            <td>
                <textarea name="phPostpartumFollowRecord.others" class="validate['length[500]'] Added_textarea">${phPostpartumFollowRecord.others}</textarea>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td class="label">分类</td>--%>
            <%--<td>--%>
                <%--<input type="radio" name="classify" checked value="未见异常" class="validate['length[30]'] text3 classify" style="width:1%"> 未见异常--%>
                <%--<input type="radio" name="classify" value="异常" class="validate['length[30]'] text3 classify" style="width:1%"> 异常--%>
                <%--<input type="text" name="phPostpartumFollowRecord.classifyException" value="${phPostpartumFollowRecord.classifyException}" class="validate['length[100]'] text3" id="classifyException" style="width:20%;display:none;">--%>
                <%--<input type="hidden" name="phPostpartumFollowRecord.classify" value="${phPostpartumFollowRecord.classify}" class="validate['length[30]'] text3" id="classify" style="width:20%">--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="label">分类</td>
            <td>
                <input type="radio" name="classify" checked value="已恢复" class="validate['length[30]'] text3 classify" style="width:1%"> 已恢复
                <input type="radio" name="classify" value="未恢复" class="validate['length[30]'] text3 classify" style="width:1%"> 未恢复
                <input type="text" name="phPostpartumFollowRecord.classifyException" value="${phPostpartumFollowRecord.classifyException}" class="validate['length[100]'] text3" id="classifyException" style="width:20%;display:none;">
                <input type="hidden" name="phPostpartumFollowRecord.classify" value="${phPostpartumFollowRecord.classify}" class="validate['length[30]'] text3" id="classify" style="width:20%">
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td class="label">指导</td>--%>
            <%--<td>--%>
                <%--<input type="checkbox" name="advice" value="个人卫生" class="advice"/>个人卫生--%>
                <%--<input type="checkbox" name="advice" value="心理" class="advice"/>心理--%>
                <%--<input type="checkbox" name="advice" value="营养" class="advice"/>营养--%>
                <%--<input type="checkbox" name="advice" value="母乳喂养" class="advice"/>母乳喂养--%>
                <%--<input type="checkbox" name="advice" value="新生儿护理与喂养" class="advice"/>新生儿护理与喂养--%>
                <%--<input type="checkbox" name="advice" value="其他" class="advice" />其他--%>
                <%--<input style="margin-left:3px;display:none" type="text" name="phPostpartumFollowRecord.adviceOther" value="${phPostpartumFollowRecord.adviceOther}" class="validate['length[100]'] text3">--%>
                <%--<input type="hidden" name="phPostpartumFollowRecord.advice" value="${phPostpartumFollowRecord.advice}" class="validate['length[100]'] text3" id="advice">--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="label">指导</td>
            <td>
                <input type="checkbox" name="advice" class="advice" value="性保健"/>性保健
                <input type="checkbox" name="advice" class="advice" value="避孕"/>避孕
                <input type="checkbox" name="advice" class="advice" value="婴儿喂养及营养"/>婴儿喂养及营养
                <input type="checkbox" name="advice" class="advice" value="其他" />其他
                <input style="margin-left:3px;display:none" type="text" name="phPostpartumFollowRecord.adviceOther" value="${phPostpartumFollowRecord.adviceOther}" class="validate['length[100]'] text3">
                <input type="hidden" name="phPostpartumFollowRecord.advice" value="${phPostpartumFollowRecord.advice}" class="validate['length[100]'] text3"  id="advice">
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td class="label">转诊</td>--%>
            <%--<td>--%>
                <%--<input type="radio" name="isReferra" value="无" class="validate['length[30]'] text3 isReferra" checked style="width:1%">无--%>
                <%--<input type="radio" name="isReferra" value="有" class="validate['length[30]'] text3 isReferra" style="width:1%">有--%>
                <%--<span class="isReferra_have" style="display:none"><br/>--%>
                <%--原因: <input type="text" name="phPostpartumFollowRecord.ireferralReason" value="${phPostpartumFollowRecord.ireferralReason}" class="validate['length[100]'] text3" id="ireferralReason" style="width:20%">--%>
                <%--机构及科室:<input type="text" name="phPostpartumFollowRecord.ireferralOrg" value="${phPostpartumFollowRecord.ireferralOrg}" class="validate['length[100]'] text3" id="ireferralOrg" style="width:20%">--%>
                <%--</span>--%>
                <%--<input type="hidden" name="phPostpartumFollowRecord.isReferral" value="${phPostpartumFollowRecord.isReferral}" class="validate['length[30]'] text3" id="isReferra" style="width:20%">--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="label">处理</td>
            <td>
                <input type="radio" name="handle" value="结案" class="validate['length[30]'] text3 handle" checked style="width:1%">结案
                <input type="radio" name="handle" value="转诊" class="validate['length[30]'] text3 handle" style="width:1%">转诊
                <span class="isReferra_have" style="display:none"><br/>
                原因: <input type="text" name="phPostpartumFollowRecord.handleIreferralReason" value="${phPostpartumFollowRecord.handleIreferralReason}" class="validate['length[100]'] text3" id="handleIreferralReason" style="width:20%">
                机构及科室:<input type="text" name="phPostpartumFollowRecord.handleIreferralOrg" value="${phPostpartumFollowRecord.handleIreferralOrg}" class="validate['length[100]'] text3" id="handleIreferralOrg" style="width:20%">
                </span>
                <input type="hidden" name="phPostpartumFollowRecord.handle" value="${phPostpartumFollowRecord.handle}" class="validate['length[30]'] text3" id="handle" style="width:20%">
            </td>
        </tr>
        <tr>
            <td class="label">下次随访日期</td>
            <td>
                <input type="text" name="phPostpartumFollowRecord.nextFollowDate" value="<fmt:formatDate value='${phPostpartumFollowRecord.nextFollowDate}' pattern='yyyy-MM-dd'/>"
                    class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
            </td>
        </tr>
<!--         <tr> -->
<!--             <td class="label">随访医生签名</td> -->
<!--             <td> -->
<!--                 <input type="text" name="phPostpartumFollowRecor" value="${phPostpartumFollowRecord}" class="validate['length[20]'] text3"> -->
<!--             </td> -->
<!--         </tr> -->
    </tr>
    </table>
    <br>
</div>
</form>
</body>
</html>