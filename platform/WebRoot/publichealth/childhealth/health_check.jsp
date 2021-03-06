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
    <title>1岁以内儿童健康检查记录表</title>
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
        #fontanelle_div,#advice_div{
            display:none;
            float:left;
            margin-left:15px;
            line-height:38px;
        }
        .label{
            text-align:right;
            background:#F6FAFE;
        }
        #ill_situation input[type="text"]{
            width:50px;
            margin:0 3px;
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
            if("${childHealthExaminationRecord.advice}"!=''){
                var advice = $j('input[name="advice"]');
                advice.removeAttr('checked');
                var arr="${childHealthExaminationRecord.advice}".split(',');
                $j(advice).each(function(index){
                    for(var i=0,l=arr.length;i<l;i++){
                        if($j(this).val()==arr[i]){
                            $j(this).attr('checked','checked');
                        }
                    }
                });
            }
            //转诊
            if("${childHealthExaminationRecord.isReferral}"!=''){
                $j('.isReferra[value="${childHealthExaminationRecord.isReferral}"]').attr('checked','checked');
                    if("${childHealthExaminationRecord.isReferral}"=="有"){
                $j('.isReferra[value="有"]').next().show();
                }
            }
            //两次随访间患病情况
            if("${childHealthExaminationRecord.twoCasesFollow}"!='无'&&"${childHealthExaminationRecord.twoCasesFollow}"!=''){
                var twoCasesFollow=$j("input:checkbox[name='ill']");
               $j('.ill_situation_none').removeAttr('checked');
                var arr="${childHealthExaminationRecord.twoCasesFollow}".split(',');
                    $j(twoCasesFollow).each(function(index){
                        for(var i=0,l=arr.length;i<l;i++){
                        if($j(this).val()==arr[i]){
                            $j(this).attr('checked','checked');
                            $j(this).next().show();
                        }
                    }
                });
            }
            //前路
            if("${childHealthExaminationRecord.brine}"!=''){
                if("${childHealthExaminationRecord.brine}"=="未闭合"){
                    $j('#fontanelle_div').show();
                    $j("#fontanelle").css("marginTop","9px");
                }
            }
        });
        $j(function(){
            //转诊
            $j('.isReferra').click(function(){
                if($j(this).val()=='无'){
                $j(this).next().next().hide();
                    $j(this).next().next().children().val('');
                }else{$j(this).next().show();}
            });
            //两次随访间患病情况
            $j('.ill_situation_none').click(function(){
                $j("input:checkbox[name='ill']").removeAttr('checked');
                if($j(this).val()=='无'){
                    if($j(this).attr('checked')=='checked')
                    $j("input:checkbox[name='ill']").next().hide();
                    $j("input:checkbox[name='ill']").next().children('input').val('');
                }
            });
            //两次随访间患病情况
            $j("input:checkbox[name='ill']").click(function(){
                 $j('.ill_situation_none').removeAttr('checked');
                 if($j(this).attr('checked')=='checked'){
                    $j(this).next().show();
                 }else{
                    $j(this).next().hide();
                    $j(this).next().children('input').val('');
                }
            });
        });
        function doHandle() {
            saveAndSubmit();
// 			alert("隐藏域的值>>>>>>"+t1);
            $('save').click();
        }
        function doBack(){
            window.location.href = "${pageContext.request.contextPath}/publichealth/childhealth/toPhChildHealthExaminationRecordList.action"
            	+"?accountId="+$j("#accountId",parent.document).val()
            			+"&babyId="+$j("#babyId",parent.document).val();
        	/* var id = $j("#basicId").val();
            window.location.href = '<s:url namespace="/publichealth/childhealth" action="toPhChildHealthExaminationRecordList" includeParams="true"/>'  +'?phPeopleBasicInfo.id='+id; */
        }
        function doSubmit() {
            confirmMsg("确定提交?",function(tp){
                if(tp=='ok'){
                    saveAndSubmit();
                    $('form_handle').action="<s:url namespace="/publichealth/childhealth" action="submitChildHealth" includeParams="true"/>";
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
                url : action+'?formCode=ManagementOrg&_t=' + date.getTime(),
                width : window.getCoordinates().width-200,
                height : window.getCoordinates().height,
                icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
                title : '帮助详情'
            });
        }
    //保存和提交的方法
    function saveAndSubmit(div){
        //classify
        var classify=$j('.classify');
        //两次随访间患病情况
        var ill_situation_none = $j("input:checkbox[name='ill']:checked").map(function(index, elem) {
        return $j(elem).val();
        }).get().join(',');
        if(ill_situation_none.length!='' || ill_situation_none.length!=undefined){
            $j('#twoCasesFollow').val(ill_situation_none);
        }else{ $j('#twoCasesFollow').val('无');}
        //转诊
        var isReferra=$j('.isReferra');
        $j(isReferra).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#isReferra').val($j(this).val());
            }
        });
        var text = $j("input:checkbox[name='advice']:checked").map(function(index, elem) {
        return $j(elem).val();
        }).get().join(',');
        // 			alert("选中的checkbox的值为：" + text);
        $j("#childHealthExaminationRecord_advice").val(text);

    }
	$j(function(){
		$j("#accountId").val($j("#accountId",parent.document).val());
		$j("#babyId").val($j("#babyId",parent.document).val());
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
                <span><a href="javascript:doSubmit();" class="pop_button_red">提交</a></span>
                <span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
            </div>
        </div>
    </div>
</div>

<form id="form_handle" name="form_handle" action="<s:url namespace="/publichealth/childhealth" action="saveOrUpdateChildHealth" includeParams="true"/>" method="post" >
<input name="save" id="save" type="submit" value="save" style="display: none;">
<input type="hidden" id="accountId" name="childHealthExaminationRecord.familyAccountInfo.id" value="${childHealthExaminationRecord.familyAccountInfo.id }">
<input type="hidden" id="babyId" name="childHealthExaminationRecord.consultBabyInfo.id" value="${childHealthExaminationRecord.consultBabyInfo.id }">
<input type="hidden" name="childHealthExaminationRecord.id" value="${childHealthExaminationRecord.id}">
<input type="hidden" name="childHealthExaminationRecord.monthAge" value="${childHealthExaminationRecord.monthAge}">
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
            <td colspan="2" class="label">月龄</td>
            <td style="text-align:left;background:#F6FAFE">${childHealthExaminationRecord.monthAge }月龄</td>
        </tr>
        <tr>
            <td colspan="2" class="label">随访日期：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.createTime" value="<fmt:formatDate value='${childHealthExaminationRecord.createTime}' pattern='yyyy-MM-dd'/>"
                       class="validate['length[20]'] text3 Wdate" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">体重(kg)：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.weight" value="${childHealthExaminationRecord.weight}" class="validate['length[20]','number'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">身长(cm)：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.height" value="${childHealthExaminationRecord.height}" class="validate['length[20]','number'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">头围(cm)：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.headSize" value="${childHealthExaminationRecord.headSize}" class="validate['length[20]','number'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">体格发育评价：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'正常':'正常','低体重':'低体重','消瘦':'消瘦','发育迟缓':'发育迟缓','超重':'超重'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.physicalDevelopEvaluation" value="childHealthExaminationRecord.physicalDevelopEvaluation"/>
            </td>
        </tr>
        <tr>
            <c:if test="${childHealthExaminationRecord.monthAge < 36}">
                <td rowspan="20" style="width: 12px;padding: 15px;">体格检查</td>
            </c:if>
            <c:if test="${childHealthExaminationRecord.monthAge >= 36}">
                <td rowspan="18" style="width: 12px;padding: 15px;">体格检查</td>
            </c:if>
            <td class="label" width="18%">面色：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'红润':'红润','黄染':'黄染','其他':'其他'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.face" value="childHealthExaminationRecord.face"/>
            </td>
        </tr>
        <tr>
            <td class="label">皮肤:</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常','其他':'其他'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.skin" value="childHealthExaminationRecord.skin"/>
            </td>
        </tr>
        <tr>
            <td class="label">前囟:</td>
            <td>
                <div id="fontanelle" style="float:left">
                    <s:select cssStyle="width:120px;" cssClass="text3" list="#{'闭合':'闭合','未闭合':'未闭合'}" listKey="key" listValue="value"
                        theme="simple" name="childHealthExaminationRecord.brine" value="childHealthExaminationRecord.brine" onchange="fontanelle(this)" />
                </div>
                
                <div id="fontanelle_div">
                    <input type="text" name="childHealthExaminationRecord.bregmaMultiplier" value="${childHealthExaminationRecord.bregmaMultiplier}" class="validate['fax','length[20]'] text3">
                    <span>cm×</span>
                    <input type="text" name="childHealthExaminationRecord.bregmaMultiplicand" value="${childHealthExaminationRecord.bregmaMultiplicand}" class="validate['fax','length[20]'] text3">
                    <span>cm</span>
                </div>
            </td>
        </tr>
        <tr>
            <td class="label">颈部包块:</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.neckMass" value="childHealthExaminationRecord.neckMass"/>
            </td>
        </tr>
        <tr>
            <td class="label">眼外观:</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.eyeAppearance" value="childHealthExaminationRecord.eyeAppearance"/>
            </td>
        </tr>
        <tr>
            <td class="label">耳外观：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.earAppearance" value="childHealthExaminationRecord.earAppearance"/>
            </td>
        </tr>
        <tr>
            <td class="label">听力：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'通过':'通过','未通过':'未通过'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.hearing" value="childHealthExaminationRecord.hearing"/>
            </td>
        </tr>
        <tr>
            <td class="label">视觉：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.vision" value="${childHealthExaminationRecord.vision}" class="validate['length[20]'] text3">
            </td>
        </tr>

        <tr>
            <td class="label">牙数(颗)/龋齿数：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.teethNumber" value="${childHealthExaminationRecord.teethNumber}" class="validate['length[20]'] text3">/
                <input type="text" name="childHealthExaminationRecord.cariesNumber" value="${childHealthExaminationRecord.cariesNumber}" class="validate['length[20]'] text3">
            </td>
        </tr>
        <tr>
            <td class="label">口腔：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.oralCavity" value="childHealthExaminationRecord.oralCavity"/>
            </td>
        </tr>
        <tr>
            <td class="label">心肺：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.heartLung" value="childHealthExaminationRecord.heartLung"/>
            </td>
        </tr>
        <tr>
            <td class="label">腹部：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.abdomen" value="childHealthExaminationRecord.abdomen"/>
            </td>
        </tr>
        <tr>
            <td class="label">脐部：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未脱':'未脱','脱落':'脱落','脐部有渗出':'脐部有渗出','其他':'其他'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.region" value="childHealthExaminationRecord.region"/>
            </td>
        </tr>
        <tr>
            <td class="label">四肢：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.armsAndLegs" value="childHealthExaminationRecord.armsAndLegs"/>
            </td>
        </tr>
        <tr>
            <td class="label">步态：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.gait" value="childHealthExaminationRecord.gait"/>
            </td>
        </tr>
        <c:if test="${childHealthExaminationRecord.monthAge <= 12}">
	        <tr>
	            <td class="label">可疑佝偻病症状：</td>
	            <td>
	                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'无':'无','夜惊':'夜惊','多汗':'多汗','烦躁':'烦躁'}" listKey="key" listValue="value"
	                    theme="simple" name="childHealthExaminationRecord.signsSymptom" value="childHealthExaminationRecord.signsSymptom"/>
	            </td>
	        </tr>
        </c:if>
        <c:if test="${childHealthExaminationRecord.monthAge <= 5}">
	        <tr>
	            <td class="label">可疑佝偻病体征：</td>
	            <td>
	                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'无':'无','颅骨软化':'颅骨软化','方颅':'方颅','枕秃':'枕秃'}" listKey="key" listValue="value"
	                    theme="simple" name="childHealthExaminationRecord.signsRickets" value="childHealthExaminationRecord.signsRickets"/>
	            </td>
	        </tr>
        </c:if>
        <c:if test="${childHealthExaminationRecord.monthAge >= 6 && childHealthExaminationRecord.monthAge <= 8}">
	        <tr>
	            <td class="label">可疑佝偻病体征：</td>
	            <td>
	                <s:select cssStyle="width:120px;" cssClass="text3" 
	                	list="#{'无':'无','肋串珠':'肋串珠','肋外翻':'肋外翻','肋软骨沟':'肋软骨沟','鸡胸':'鸡胸','手镯征':'手镯征'}" 
	                	listKey="key" listValue="value" theme="simple" 
	                	name="childHealthExaminationRecord.signsRickets" value="childHealthExaminationRecord.signsRickets"/>
	            </td>
	        </tr>
        </c:if>
        <c:if test="${childHealthExaminationRecord.monthAge >= 12 && childHealthExaminationRecord.monthAge <= 24}">
	        <tr>
	            <td class="label">可疑佝偻病体征(1-2岁)：</td>
	            <td>
	                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'“O型腿”':'O型腿','“X型腿”':'X型腿'}" listKey="key" listValue="value"
	                    theme="simple" name="childHealthExaminationRecord.signsRickets" value="childHealthExaminationRecord.signsRickets"/>
	            </td>
	        </tr>
        </c:if>
        <tr>
            <td class="label">肛门/外生殖：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未见异常':'未见异常','异常':'异常'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.anusExternalGenital" value="childHealthExaminationRecord.anusExternalGenital"/>
            </td>
        </tr>
        <tr>
            <td class="label">血红蛋白值：</td>
            <td>
                <input style="margin-left:3px" type="text" name="childHealthExaminationRecord.hemoglobinValue" value="${childHealthExaminationRecord.hemoglobinValue}" class="validate['length[20]'] text3">
                <span>g/L</span>
            </td>
        </tr>
        <tr>
            <td class="label">其他：</td>
            <td>
            <input type="text" name="childHealthExaminationRecord.others" value="${childHealthExaminationRecord.others}" class="validate['length[20]'] text3">
        </td>
    </tr>
        <tr>
            <td colspan="2" class="label">户外活动：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.outdoorActivities" value="${childHealthExaminationRecord.outdoorActivities}" class="validate['length[20]'] text3">
                <span>小时/日</span>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">服用维生素D：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.takeVitaminD" value="${childHealthExaminationRecord.takeVitaminD}" class="validate['length[20]'] text3">
                <span>IU/日</span>
        </tr>
        <tr>
            <td colspan="2" class="label">发育评估：</td>
            <td>
                <s:select cssStyle="width:120px;" cssClass="text3" list="#{'通过':'通过','未过':'未过'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.developmentalAssessment" value="childHealthExaminationRecord.developmentalAssessment"/>
            </td>
        </tr>
        <c:if test="${childHealthExaminationRecord.monthAge <36 }">
            <tr>
                <td colspan="2" class="label">两次随访间患病情况：</td>
                <td>
                    <s:select cssStyle="width:120px;" cssClass="text3" list="#{'未患病':'未患病','患病':'患病'}" listKey="key" listValue="value"
                    theme="simple" name="childHealthExaminationRecord.twoCasesFollow" value="childHealthExaminationRecord.twoCasesFollow"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${childHealthExaminationRecord.monthAge >=36 }">
	        <tr>
	            <td colspan="2" class="label">两次随访间患病情况(3-6岁)：</td>
	            <td id="ill_situation">
	                <input name="" type="checkbox" value="无" checked class="ill_situation_none" />无
	                <input name="childHealthExaminationRecord.twoCasesFollow" type="hidden" value="${childHealthExaminationRecord.twoCasesFollow }" checked id="twoCasesFollow" />
	                <input name="ill" type="checkbox" value="肺炎" />肺炎<span style="display:none"><input type="text" name="childHealthExaminationRecord.twoVisitIllLung" value="${childHealthExaminationRecord.twoVisitIllLung}" class="validate['length[20]','number'] text3"><span>次</span></span>
	                <input name="ill" type="checkbox" value="腹泻" />腹泻<span style="display:none"><input type="text" name="childHealthExaminationRecord.twoVisitIllDiarrhea" value="${childHealthExaminationRecord.twoVisitIllDiarrhea}" class="validate['length[20]','number'] text3"><span>次</span></span>
	                <input name="ill" type="checkbox" value="外伤" />外伤<span style="display:none"><input type="text" name="childHealthExaminationRecord.twoVisitIllTrauma" value="${childHealthExaminationRecord.twoVisitIllTrauma}" class="validate['length[20]','number'] text3"><span>次</span></span>
	                <input name="ill" type="checkbox" value="其他" />其他<span style="display:none"><input type="text" name="childHealthExaminationRecord.twoVisitIllOther" value="${childHealthExaminationRecord.twoVisitIllOther}" class="validate['length[20]'] text3"></span>
	            </td>
	        </tr>
        </c:if>
        <tr>
            <td colspan="2" class="label">转诊建议：</td>
            <td>
                <input type="radio" name="isReferra" value="无" class="validate['length[30]'] text3 isReferra" checked style="width:1%">无
                <input type="radio" name="isReferra" value="有" class="validate['length[30]'] text3 isReferra" style="width:1%">有
                <span class="isReferra_have" style="display:none"><br/>
                原因: <input type="text" name="childHealthExaminationRecord.ireferralReason" value="${childHealthExaminationRecord.ireferralReason}" class="validate['length[100]'] text3" id="ireferralReason" style="width:20%">
                机构及科室:<input type="text" name="childHealthExaminationRecord.ireferralOrg" value="${childHealthExaminationRecord.ireferralOrg}" class="validate['length[100]'] text3" id="ireferralOrg" style="width:20%">
                </span>
                <input type="hidden" name="childHealthExaminationRecord.isReferral" value="${childHealthExaminationRecord.isReferral}" class="validate['length[30]'] text3" id="isReferra" style="width:20%">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">指导：</td>
            <td>
                <input name="advice" type="checkbox" value="喂养指导" />喂养指导
                <input name="advice" type="checkbox" value="发育指导" />发育指导
                <input name="advice" type="checkbox" value="防病指导" />防病指导
                <input name="advice" type="checkbox" value="预防伤害指导" />预防伤害指导
                <input name="advice" type="checkbox" value="口腔保健指导" />口腔保健指导
                <input type="hidden" id="childHealthExaminationRecord_advice" name="childHealthExaminationRecord.advice" value="${childHealthExaminationRecord.advice}" class="validate['length[100]'] text3">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">下次访视日期：</td>
            <td>
                <input type="text" name="childHealthExaminationRecord.nextFollowDate" value="<fmt:formatDate value='${childHealthExaminationRecord.nextFollowDate}' pattern='yyyy-MM-dd'/>"
                    class="validate['length[20]'] text3 Wdate" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="label">随访医生签名：</td>
            <td>
                <input type="text" value="${childHealthExaminationRecord.createPerson.userName}" class="validate['length[20]'] text3">
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>--%>
            <%--<td width="85%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">--%>
            <%--<textarea name="managementOrg.remark" class="validate['length[2000]'] Added_textarea">${managementOrg.remark}</textarea>--%>
        <%--</td>--%>
    </tr>
    </table>
    <br>
</div>
</form>
    <script>
        function fontanelle(param){
            if($j(param).find("option:selected").val()=="未闭合"){
                $j("#fontanelle").css("marginTop","9px");
                $j("#fontanelle_div").show();
                $j("#fontanelle_div input").attr("required","required");

            }
            else{
                $j("#fontanelle").css("marginTop","0");
                $j("#fontanelle_div").hide();
                $j("#fontanelle_div").children('input').val('');
                $j("#fontanelle_div input").attr("required","");
            }
        }

    </script>  
</body>
</html>