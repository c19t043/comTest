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
		<title>第一次产前随访服务记录表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		
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
                //编辑时
                //末次月经
                if("${phPrenatalFollowRecordFirst.lastMenstruation}"!=''){
                    $j('.lastMenstruation[value="时间"]').attr('checked','checked');
                }else{
                	$j('.lastMenstruation[value="不详"]').attr('checked','checked');
                }
                //妇科手术史
                if("${phPrenatalFollowRecordFirst.isGynaecologyOperationHis}"!=''){
                    $j('.isGynaecologyOperationHis[value="${phPrenatalFollowRecordFirst.isGynaecologyOperationHis}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.isGynaecologyOperationHis}"=="有"){
                        $j('.isGynaecologyOperationHis[value="有"]').next().show();
                    }
                }
                //转诊
                if("${phPrenatalFollowRecordFirst.isReferral}"!=''){
                    $j('.isReferra[value="${phPrenatalFollowRecordFirst.isReferral}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.isReferral}"=="有"){
                        $j('.isReferra[value="有"]').next().show();
                    }
                }
                //heart
                if("${phPrenatalFollowRecordFirst.heart}"!=''){
                    $j('.heart[value="${phPrenatalFollowRecordFirst.heart}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.heart}"=="异常"){
                        $j('.heart[value="异常"]').next().show();
                    }
                }
                //lungs
                if("${phPrenatalFollowRecordFirst.lungs}"!=''){
                    $j('.lungs[value="${phPrenatalFollowRecordFirst.lungs}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.lungs}"=="异常"){
                        $j('.lungs[value="异常"]').next().show();
                    }
                }
                //vulva
                if("${phPrenatalFollowRecordFirst.vulva}"!=''){
                    $j('.vulva[value="${phPrenatalFollowRecordFirst.vulva}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.vulva}"=="异常"){
                        $j('.vulva[value="异常"]').next().show();
                    }
                }
                //vagina
                if("${phPrenatalFollowRecordFirst.vagina}"!=''){
                    $j('.vagina[value="${phPrenatalFollowRecordFirst.vagina}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.vagina}"=="异常"){
                        $j('.vagina[value="异常"]').next().show();
                    }
                }
                //cervical
                if("${phPrenatalFollowRecordFirst.cervical}"!=''){
                    $j('.cervical[value="${phPrenatalFollowRecordFirst.cervical}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.cervical}"=="异常"){
                        $j('.cervical[value="异常"]').next().show();
                    }
                }
                //uterus
                if("${phPrenatalFollowRecordFirst.uterus}"!=''){
                    $j('.uterus[value="${phPrenatalFollowRecordFirst.uterus}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.uterus}"=="异常"){
                        $j('.uterus[value="异常"]').next().show();
                    }
                }
                //appendix
                if("${phPrenatalFollowRecordFirst.appendix}"!=''){
                    $j('.appendix[value="${phPrenatalFollowRecordFirst.appendix}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.appendix}"=="异常"){
                        $j('.appendix[value="异常"]').next().show();
                    }
                }
                //overallEvaluation
                if("${phPrenatalFollowRecordFirst.overallEvaluation}"!=''){
                    $j('.overallEvaluation[value="${phPrenatalFollowRecordFirst.overallEvaluation}"]').attr('checked','checked');
                    if("${phPrenatalFollowRecordFirst.overallEvaluation}"=="异常"){
                        $j('.overallEvaluation[value="异常"]').next().show();
                    }
                }
                //家族史
                if("${phPrenatalFollowRecordFirst.familyHistory}"!=''){
                    var familyHistory=$j('.familyHistory');
                    var arr="${phPrenatalFollowRecordFirst.familyHistory}".split(',');
                    $j(familyHistory).each(function(index){
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
                //个人史
                if("${phPrenatalFollowRecordFirst.personalHistory}"!=''){
                    var personalHistory=$j('.personalHistory');
                    var arr="${phPrenatalFollowRecordFirst.familyHistory}".split(',');
                    $j(personalHistory).each(function(index){
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
                //保健指导
                if("${phPrenatalFollowRecordFirst.healthCareGuidance}"!=''){
                    var healthCareGuidance=$j('.healthCareGuidance');
                    var arr="${phPrenatalFollowRecordFirst.healthCareGuidance}".split(',');
                    $j(healthCareGuidance).each(function(index){
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
                //既往史
                if("${phPrenatalFollowRecordFirst.pastHistory}"!=''){
                    var pastHistory=$j('.pastHistory');
                    $j('.pastHistory[value="无"]').removeAttr('checked');
                    var arr="${phPrenatalFollowRecordFirst.pastHistory}".split(',');
                    $j(pastHistory).each(function(index){
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
                //阴道分泌物
                if("${phPrenatalFollowRecordFirst.vaginalDischarge}"!=''){
                    var vaginalDischarge=$j('.vaginalDischarge');
                    $j('.vaginalDischarge[value="无"]').removeAttr('checked');
                    var arr="${phPrenatalFollowRecordFirst.vaginalDischarge}".split(',');
                    $j(vaginalDischarge).each(function(index){
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
				window.location.href = '${pageContext.request.contextPath}/productionvisit/preFirst/toList.action?accountId='+ $j("#accountId",parent.document).val();
			}
			function doSubmit() {
				confirmMsg("确定提交?",function(tp){
					if(tp=='ok'){
                        saveAndSubmit();
						$('form_handle').action="<s:url namespace='/productionvisit/preFirst' action='saveOrUpdatePhPrenatalFollowRecordFirst' includeParams='true'/>";
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
					url : action+'?formCode=phPrenatalFollowRecordFirst&_t=' + date.getTime(),
					width : window.getCoordinates().width-200,
					height : window.getCoordinates().height,
					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
					title : '帮助详情'
				 });
			}
    $j(function(){
    //保健指导、家族史、个人史
        $j('.healthCareGuidance,.familyHistory,.personalHistory').click(function(){
            if($j(this).val()=='其他'){
                if($j(this).attr('checked')=='checked'){
                    $j(this).next().show();
                }else{
                    $j(this).next().hide().val('');
                }
            }
        });
        //妇科手术史
        $j('.isGynaecologyOperationHis').click(function(){
            if($j(this).val()=='无'){
                $j(this).next().next().hide().val('');
            }else{$j(this).next().show();}
        });
        //转诊
        $j('.isReferra').click(function(){
            if($j(this).val()=='无'){
                $j(this).next().next().hide();
                $j(this).next().next().children().val('');
            }else{$j(this).next().show();}
        });
        //所有异常和未见异常
        $j('.heart,.lungs,.vulva,.vagina,.cervical,.uterus,.appendix,.overallEvaluation').click(function(){
            if($j(this).val()=='未见异常'){
                $j(this).next().next().hide().val('');
            }else{$j(this).next().show();}
        });
        //既往史和阴道分泌物
        $j('.vaginalDischarge,.pastHistory').click(function(){
            if($j(this).val()=='无' || $j(this).val()=='未见异常'){
                if($j(this).attr('checked')!=undefined){
                    $j(this).parent().children().removeAttr('checked');
                    $j(this).attr('checked','checked');
                    $j(this).parent().children('input[value="其他"]').next().hide().val('');
                }
            }else{
                if($j(this).attr('checked')!=undefined){
                    $j(this).parent().children('input[value="无"],input[value="未见异常"]').removeAttr('checked');
                    $j(this).attr('checked','checked');
                    if($j(this).hasClass('vaginalDischarge')||$j(this).hasClass('pastHistory')){
                        if($j(this).val()=='其他'&&$j(this).attr('checked')=='checked'){
                            $j(this).parent().children('input[value="其他"]').next().show();
                        }
                    }
                }else{
                    if($j(this).val()=='其他'){
                        $j(this).parent().children('input[value="其他"]').next().hide().val('');
                   }
                }
            }
        });


    });
        //保存和提交的方法
    function saveAndSubmit(div){
        //末次月经
        var lastMenstruation=$j('.lastMenstruation[checked="checked"]').val();
        if(lastMenstruation=='不详'){
            $j('#lastMenstruation').val('');
        }else{$j('#lastMenstruation').val($j('#lastMenstruation_time').val())}
        //保健指导
        var healthCareGuidance=$j('.healthCareGuidance');
        var healthCareGuidance_str='';
        $j(healthCareGuidance).each(function (index) {
            if($j(this).attr('checked')=='checked'){
                healthCareGuidance_str+=','+$j(this).val();
            }
        });
        $j('#healthCareGuidance').val(healthCareGuidance_str.substring(1));
        //家族史
        var familyHistory=$j('.familyHistory');
        var familyHistory_str='';
        $j(familyHistory).each(function (index) {
            if($j(this).attr('checked')=='checked'){
                familyHistory_str+=','+$j(this).val();
            }
        });
        $j('#familyHistory').val(familyHistory_str.substring(1));
        //个人史
        var personalHistory=$j('.personalHistory');
        var personalHistory_str='';
        $j(personalHistory).each(function (index) {
            if($j(this).attr('checked')=='checked'){
                personalHistory_str+=','+$j(this).val();
            }
        });
        $j('#personalHistory').val(personalHistory_str.substring(1));
        //妇科手术史
        var isGynaecologyOperationHis=$j('.isGynaecologyOperationHis');
        $j(isGynaecologyOperationHis).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#isGynaecologyOperationHis').val($j(this).val());
            }
        });
        //转诊
        var isReferra=$j('.isReferra');
        $j(isReferra).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#isReferra').val($j(this).val());
            }
        });
        //总体评估
        var overallEvaluation=$j('.overallEvaluation');
        $j(overallEvaluation).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#overallEvaluation').val($j(this).val());
            }
        });
        //heart
        var heart=$j('.heart');
        $j(heart).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#heart').val($j(this).val());
            }
        });
        //lungs
        var lungs=$j('.lungs');
        $j(lungs).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#lungs').val($j(this).val());
            }
        });
        //lungs
        var vulva=$j('.vulva');
        $j(vulva).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#vulva').val($j(this).val());
            }
        });
        //vagina
        var vagina=$j('.vagina');
        $j(vagina).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#vagina').val($j(this).val());
            }
        });
        //cervical
        var cervical=$j('.cervical');
        $j(cervical).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#cervical').val($j(this).val());
            }
        });
        //uterus
        var uterus=$j('.uterus');
        $j(uterus).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#uterus').val($j(this).val());
            }
        });
        //appendix
        var appendix=$j('.appendix');
        $j(appendix).each(function(){
            if($j(this).attr('checked')!=undefined){
                $j('#appendix').val($j(this).val());
            }
        });

        //既往史
        var pastHistory=$j('.pastHistory[checked="checked"]');
        var pastHistory_str='';
        $j(pastHistory).each(function (index) {
             pastHistory_str+=','+$j(this).val();
        });
        $j('#pastHistory').val(pastHistory_str.substring(1));
        //阴道分泌物
        var vaginalDischarge=$j('.vaginalDischarge[checked="checked"]');
        var vaginalDischarge_str='';
        $j(vaginalDischarge).each(function (index) {
            vaginalDischarge_str+=','+$j(this).val();
        });
        $j('#vaginalDischarge').val(vaginalDischarge_str.substring(1));
    }
	
        $j(function(){
        	$j("#accountId").val($j("#accountId",parent.document).val());
        })
  		</script>
        <style>
            .eXtremeTable .tableRegion2 .inputLabel{
                width:10%;
            }
            .eXtremeTable .tableRegion2 input{
                width:80%;
            }
        </style>
  	</head>
  
  	<body>
  		<s:actionmessage theme="popwind"/>
  		<div id="wz">
			<%-- <ap:step></ap:step> --%>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doHandle();" class="pop_button_blue">保存</a></span>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		
		<form id="form_handle" name="form_handle" action="<s:url namespace='/productionvisit/preFirst' action='saveOrUpdatePhPrenatalFollowRecordFirst' includeParams='true'/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="phPrenatalFollowRecordFirst.id" value="${phPrenatalFollowRecordFirst.id}">
			<input type="hidden" id="accountId" name="accountId" value="${accountId}">
			<input type="hidden"  name="phPrenatalFollowRecordFirst.familyAccountInfo.id" value="${phPrenatalFollowRecordFirst.familyAccountInfo.id}">
			<input type="hidden" name="phPrenatalFollowRecordFirst.peopleBasicInfo.id" value="${phPrenatalFollowRecordFirst.peopleBasicInfo.id}">
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
                            <td class="inputLabel">填表日期：</td>
                            <td width="14%">
                                <input type="text" name="phPrenatalFollowRecordFirst.createTime" value="<fmt:formatDate value='${phPrenatalFollowRecordFirst.createTime}' pattern='yyyy-MM-dd'/>"
                                class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
                            </td>
                            <td class="inputLabel">填表孕周：</td>
                            <td width="37%" colspan="3">
                                <input type="text" name="phPrenatalFollowRecordFirst.gestationalWeek" value="${phPrenatalFollowRecordFirst.gestationalWeek}" class="validate['length[10]'] text3" width="70%">周
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">孕妇年龄：</td>
                            <td colspan="5">
                                <input type="text" name="phPrenatalFollowRecordFirst.maternalAge" value="${phPrenatalFollowRecordFirst.maternalAge}" class="validate['number','length[10]'] text3" width="70%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">丈夫姓名：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.husbandName" value="${phPrenatalFollowRecordFirst.husbandName}" class="validate['length[10]'] text3" width="70%">
                            </td>
                            <td class="inputLabel">丈夫年龄：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.husbandAge" value="${phPrenatalFollowRecordFirst.husbandAge}" class="validate['number','length[10]'] text3" width="70%">
                            </td>
                            <td class="inputLabel">丈夫电话：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.husbandPhone" value="${phPrenatalFollowRecordFirst.husbandPhone}" class="validate['length[20]'] text3" width="70%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">孕次：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.pregnantTimes" value="${phPrenatalFollowRecordFirst.pregnantTimes}" class="validate['length[10]'] text3" width="70%">
                            </td>
                            <td class="inputLabel">产次：</td>
                            <td colspan="3">
                                阴道分娩<input type="text" name="phPrenatalFollowRecordFirst.vaginalDeliveryTimes" value="${phPrenatalFollowRecordFirst.vaginalDeliveryTimes}" class="validate['length[10]'] text3" style="width:20%">次
                                剖宫产<input type="text" name="phPrenatalFollowRecordFirst.cesareanSectionTimes" value="${phPrenatalFollowRecordFirst.cesareanSectionTimes}" class="validate['length[10]'] text3" style="width:20%">次
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">末次月经：</td>
                            <td colspan="3">
                                <input type="radio" name="lastMenstruation" value="时间" class="validate['length[20]'] text3 lastMenstruation" style="width:2%">
                                时间
                                <input type="text" name="" value="${phPrenatalFollowRecordFirst.lastMenstruation}"
                                class="validate['length[20]'] text3" id="lastMenstruation_time" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
                                <br/>
                                <input type="radio" name="lastMenstruation" value="不详" class="validate['length[20]'] text3 lastMenstruation" style="width:2%">
                                不详
                                <input type="hidden" name="phPrenatalFollowRecordFirst.lastMenstruation" value="${phPrenatalFollowRecordFirst.lastMenstruation}" class="validate['length[20]'] text3" id="lastMenstruation" style="width:2%">
                            </td>
                            <td class="inputLabel">预产期：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.expectedBirthDate" value="${phPrenatalFollowRecordFirst.expectedBirthDate}"
                                class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">既往史：</td>
                            <td colspan="5">
                                <input type="checkbox" name="pastHistory" checked value="无" class="validate['length[30]'] text3 pastHistory" style="width:2%">无
                                <input type="checkbox" name="pastHistory" value="心脏病" class="validate['length[30]'] text3 pastHistory" style="width:2%">心脏病
                                <input type="checkbox" name="pastHistory" value="肾脏疾病" class="validate['length[30]'] text3 pastHistory" style="width:2%">肾脏疾病
                                <input type="checkbox" name="pastHistory" value="肝脏疾病" class="validate['length[30]'] text3 pastHistory" style="width:2%">肝脏疾病
                                <input type="checkbox" name="pastHistory" value="高血压" class="validate['length[30]'] text3 pastHistory" style="width:2%">高血压
                                <input type="checkbox" name="pastHistory" value="贫血" class="validate['length[30]'] text3 pastHistory" style="width:2%">贫血
                                <input type="checkbox" name="pastHistory" value="糖尿病" class="validate['length[30]'] text3 pastHistory" style="width:2%">糖尿病
                                <input type="checkbox" name="pastHistory" value="其他" class="validate['length[30]'] text3 pastHistory" style="width:2%">其他
                                <input type="text" name="phPrenatalFollowRecordFirst.pastHistoryOther" value="${phPrenatalFollowRecordFirst.pastHistoryOther}" class="validate['length[100]'] text3" id="pastHistoryOther" style="width:20%;display:none">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.pastHistory" value="${phPrenatalFollowRecordFirst.pastHistory}" class="validate['length[100]'] text3" id="pastHistory" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">家族史：</td>
                            <td colspan="5">
                                <input type="checkbox" name="familyHistory" value="遗传性疾病史" class="validate['length[30]'] text3 familyHistory" style="width:2%">遗传性疾病史
                                <input type="checkbox" name="familyHistory" value="精神疾病史" class="validate['length[30]'] text3 familyHistory" style="width:2%">精神疾病史
                                <input type="checkbox" name="familyHistory" value="其他" class="validate['length[30]'] text3 familyHistory" style="width:2%">其他
                                <input type="text" name="phPrenatalFollowRecordFirst.familyHistoryOther" value="${phPrenatalFollowRecordFirst.familyHistoryOther}" class="validate['length[100]'] text3" id="familyHistoryOther" style="width:20%;display:none">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.familyHistory" value="${phPrenatalFollowRecordFirst.familyHistory}" class="validate['length[100]'] text3" id="familyHistory" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">个人史：</td>
                            <td colspan="5">
                                <input type="checkbox" name="personalHistory" value="遗传性疾病史" class="validate['length[30]'] text3 personalHistory" style="width:2%">吸烟
                                <input type="checkbox" name="personalHistory" value="遗传性疾病史" class="validate['length[30]'] text3 personalHistory" style="width:2%">饮酒
                                <input type="checkbox" name="personalHistory" value="遗传性疾病史" class="validate['length[30]'] text3 personalHistory" style="width:2%">服用药物
                                <input type="checkbox" name="personalHistory" value="遗传性疾病史" class="validate['length[30]'] text3 personalHistory" style="width:2%">接触有毒有害物质
                                <input type="checkbox" name="personalHistory" value="精神疾病史" class="validate['length[30]'] text3 personalHistory" style="width:2%">接触放射线
                                <input type="checkbox" name="personalHistory" value="其他" class="validate['length[30]'] text3 personalHistory" style="width:2%">其他
                                <input type="text" name="phPrenatalFollowRecordFirst.personalHistoryOther" value="${phPrenatalFollowRecordFirst.personalHistoryOther}" class="validate['length[100]'] text3" id="personalHistoryOther" style="width:20%;display:none">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.personalHistory" value="${phPrenatalFollowRecordFirst.personalHistory}" class="validate['length[100]'] text3" id="personalHistory" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">妇科手术史：</td>
                            <td width="80%" colspan="5">
                                <input type="radio" name="isGynaecologyOperationHis" value="无" class="validate['length[30]'] text3 isGynaecologyOperationHis" checked style="width:2%">无
                                <input type="radio" name="isGynaecologyOperationHis" value="有" class="validate['length[30]'] text3 isGynaecologyOperationHis" style="width:2%">有
                                <input type="text" name="phPrenatalFollowRecordFirst.gynaecologyOperationHis" value="${phPrenatalFollowRecordFirst.gynaecologyOperationHis}" class="validate['length[30]'] text3" id="gynaecologyOperationHis" style="width:20%;display:none">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.isGynaecologyOperationHis" value="${phPrenatalFollowRecordFirst.isGynaecologyOperationHis}" class="validate['length[30]'] text3" id="isGynaecologyOperationHis" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">孕产史：</td>
                            <td width="80%" colspan="5">
                                流产:<input type="text" name="phPrenatalFollowRecordFirst.gestationHisAbortion" value="${phPrenatalFollowRecordFirst.gestationHisAbortion}" class="validate['length[100]'] text3"><br/>
                                死胎:<input type="text" name="phPrenatalFollowRecordFirst.gestationHisDeadFetus" value="${phPrenatalFollowRecordFirst.gestationHisDeadFetus}" class="validate['length[100]'] text3"><br/>
                                死产:<input type="text" name="phPrenatalFollowRecordFirst.gestationHisDeadBirth" value="${phPrenatalFollowRecordFirst.gestationHisDeadBirth}" class="validate['length[100]'] text3"><br/>
                                新生儿死亡:<input type="text" name="phPrenatalFollowRecordFirst.gestationHisNeonatalDeath" value="${phPrenatalFollowRecordFirst.gestationHisNeonatalDeath}" class="validate['length[100]'] text3"><br/>
                                出生缺陷儿:<input type="text" name="phPrenatalFollowRecordFirst.gestationHisBirthDefects" value="${phPrenatalFollowRecordFirst.gestationHisBirthDefects}" class="validate['length[100]'] text3">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">身高：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.height" value="${phPrenatalFollowRecordFirst.height}" class="validate['number','length[100]'] text3">cm
                            </td>
                            <td class="inputLabel">体重：</td>
                            <td width="80%" colspan="3">
                                <input type="text" name="phPrenatalFollowRecordFirst.weight" value="${phPrenatalFollowRecordFirst.weight}" class="validate['number','length[10]'] text3" style="width:20%">Kg
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">体质指数：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.bodyMassIndex" value="${phPrenatalFollowRecordFirst.bodyMassIndex}" class="validate['number','length[10]'] text3">
                            </td>
                            <td class="inputLabel">血压：</td>
                            <td width="80%" colspan="3">
                                <input type="text" name="phPrenatalFollowRecordFirst.highBlood" value="${phPrenatalFollowRecordFirst.highBlood}" class="validate['number','length[10]'] text3" style="width:10%">&nbsp;/
                                <input type="text" name="phPrenatalFollowRecordFirst.lowBlood" value="${phPrenatalFollowRecordFirst.lowBlood}" class="validate['number','length[10]'] text3" style="width:10%">&nbsp;mmHg
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">听诊：</td>
                            <td colspan="5">
                                心脏:<input type="radio" checked name="heart" value="未见异常" class="validate['length[30]'] text3 heart" style="width:2%"> 未见异常
                                <input type="radio" name="heart" value="异常" class="validate['length[30]'] text3 heart" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.heartExceptions" value="${phPrenatalFollowRecordFirst.heartExceptions}" class="validate['length[100]'] text3" id="heartExceptions" style="width:20%;display:none;"><br/>
                                <input type="hidden" name="phPrenatalFollowRecordFirst.heart" value="${phPrenatalFollowRecordFirst.heart}" class="validate['length[10]'] text3" id="heart" style="width:20%">
                                肺部:<input type="radio" checked name="lungs" value="未见异常" class="validate['length[30]'] text3 lungs" style="width:2%"> 未见异常
                                <input type="radio" name="lungs" value="异常" class="validate['length[30]'] text3 lungs" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.lungsExceptions" value="${phPrenatalFollowRecordFirst.lungsExceptions}" class="validate['length[100]'] text3" id="lungsExceptions" style="width:20%;display:none;">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.lungs" value="${phPrenatalFollowRecordFirst.lungs}" class="validate['length[30]'] text3" id="lungs" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">妇科：</td>
                            <td colspan="5">
                                外阴:<input type="radio" checked name="vulva" value="未见异常" class="validate['length[30]'] text3 vulva" style="width:2%"> 未见异常
                                <input type="radio" name="vulva" value="异常" class="validate['length[30]'] text3 vulva" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.vulvaExceptions" value="${phPrenatalFollowRecordFirst.vulvaExceptions}" class="validate['length[100]'] text3" id="vulvaExceptions" style="width:20%;display:none;"><br/>
                                <input type="hidden" name="phPrenatalFollowRecordFirst.vulva" value="${phPrenatalFollowRecordFirst.vulva}" class="validate['length[30]'] text3" id="vulva" style="width:20%">

                                阴道:<input type="radio" checked name="vagina" value="未见异常" class="validate['length[30]'] text3 vagina" style="width:2%"> 未见异常
                                <input type="radio" name="vagina" value="异常" class="validate['length[30]'] text3 vagina" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.vaginaExceptions" value="${phPrenatalFollowRecordFirst.vaginaExceptions}" class="validate['length[100]'] text3" id="vaginaExceptions" style="width:20%;display:none;"><br/>
                                <input type="hidden" name="phPrenatalFollowRecordFirst.vagina" value="${phPrenatalFollowRecordFirst.vagina}" class="validate['length[30]'] text3" id="vagina" style="width:2%">

                                宫颈:<input type="radio" checked name="cervical" value="未见异常" class="validate['length[30]'] text3 cervical" style="width:2%"> 未见异常
                                <input type="radio" name="cervical" value="异常" class="validate['length[30]'] text3 cervical" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.cervicalExceptions" value="${phPrenatalFollowRecordFirst.cervicalExceptions}" class="validate['length[100]'] text3" id="cervicalExceptions" style="width:20%;display:none;"><br/>
                                <input type="hidden" name="phPrenatalFollowRecordFirst.cervical" value="${phPrenatalFollowRecordFirst.cervical}" class="validate['length[30]'] text3" id="cervical" style="width:20%">

                                子宫:<input type="radio" checked name="uterus" value="未见异常" class="validate['length[30]'] text3 uterus" style="width:2%"> 未见异常
                                <input type="radio" name="uterus" value="异常" class="validate['length[30]'] text3 uterus" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.uterusExceptions" value="${phPrenatalFollowRecordFirst.uterusExceptions}" class="validate['length[100]'] text3" id="uterusExceptions" style="width:20%;display:none;"><br/>
                                <input type="hidden" name="phPrenatalFollowRecordFirst.uterus" value="${phPrenatalFollowRecordFirst.uterus}" class="validate['length[30]'] text3" id="uterus" style="width:20%">

                                附件:<input type="radio" checked name="appendix" value="未见异常" class="validate['length[30]'] text3 appendix" style="width:2%"> 未见异常
                                <input type="radio" name="appendix" value="异常" class="validate['length[30]'] text3 appendix" style="width:2%"> 异常
                                <input type="text" name="phPrenatalFollowRecordFirst.appendixExceptions" value="${phPrenatalFollowRecordFirst.appendixExceptions}" class="validate['length[100]'] text3" id="appendixExceptions" style="width:20%;display:none;">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.appendix" value="${phPrenatalFollowRecordFirst.appendix}" class="validate['length[30]'] text3" id="appendix" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel" rowspan="12">辅助检查：</td>
                            <td class="inputLabel">血常规：</td>
                            <td colspan="4">
                                血红蛋白值:<input type="text" name="phPrenatalFollowRecordFirst.bloodHemoglobin" value="${phPrenatalFollowRecordFirst.bloodHemoglobin}" class="validate['length[10]'] text3" style="width:15%"> g/L&nbsp;&nbsp;&nbsp;&nbsp;
                                血小板计数值:<input type="text" name="phPrenatalFollowRecordFirst.bloodWhiteCellCount" value="${phPrenatalFollowRecordFirst.bloodWhiteCellCount}" class="validate['length[10]'] text3" style="width:15%"> /L<br/>
                                白细胞计数值:<input type="text" name="phPrenatalFollowRecordFirst.bloodPlateletCount" value="${phPrenatalFollowRecordFirst.bloodPlateletCount}" class="validate['length[10]'] text3" style="width:15%"> L&nbsp;&nbsp;&nbsp;&nbsp;
                                其他:<input type="text" name="phPrenatalFollowRecordFirst.bloodOther" value="${phPrenatalFollowRecordFirst.bloodOther}" class="validate['length[10]'] text3" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">尿常规：</td>
                            <td colspan="4">
                                尿蛋白:<input type="text" name="phPrenatalFollowRecordFirst.urineProtein" value="${phPrenatalFollowRecordFirst.urineProtein}" class="validate['length[10]'] text3" style="width:15%"> &nbsp;&nbsp;&nbsp;&nbsp;
                                尿糖:<input type="text" name="phPrenatalFollowRecordFirst.urineSugar" value="${phPrenatalFollowRecordFirst.urineSugar}" class="validate['length[10]'] text3" style="width:15%"> <br/>
                                尿酮体:<input type="text" name="phPrenatalFollowRecordFirst.urineKetone" value="${phPrenatalFollowRecordFirst.urineKetone}" class="validate['length[10]'] text3" style="width:15%"> &nbsp;&nbsp;&nbsp;&nbsp;
                                尿潜血:<input type="text" name="phPrenatalFollowRecordFirst.urineBlood" value="${phPrenatalFollowRecordFirst.urineBlood}" class="validate['length[10]'] text3" style="width:15%"> <br/>
                                其他:<input type="text" name="phPrenatalFollowRecordFirst.urineOther" value="${phPrenatalFollowRecordFirst.urineOther}" class="validate['length[10]'] text3" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">血型：</td>
                            <td colspan="4">
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'A型':'A型','B型':'B型','O型':'O型','AB型':'AB型','不详':'不详'}" listKey="key" listValue="value"
                                theme="simple" name="phPrenatalFollowRecordFirst.bloodType" value="phPrenatalFollowRecordFirst.bloodType"/>
                                <span>/</span>
                                RH 阴性:
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'是':'是','否':'否','不详':'不详'}" listKey="key" listValue="value"
                                theme="simple" name="phPrenatalFollowRecordFirst.bloodTypeRh" value="phPrenatalFollowRecordFirst.bloodTypeRh"/>
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">血糖：</td>
                            <td colspan="4">
                                <input type="text" name="phPrenatalFollowRecordFirst.bloodSugar" value="${phPrenatalFollowRecordFirst.bloodSugar}" class="validate['length[10]'] text3" style="width:10%">&nbsp;mmol/L
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">肝功能：</td>
                            <td colspan="4">
                                血清谷丙转氨酶:<input type="text" name="phPrenatalFollowRecordFirst.liverSgpt" value="${phPrenatalFollowRecordFirst.liverSgpt}" class="validate['length[10]'] text3" style="width:15%"> U/L&nbsp;&nbsp;&nbsp;&nbsp;
                                血清谷草转氨酶:<input type="text" name="phPrenatalFollowRecordFirst.liverSgot" value="${phPrenatalFollowRecordFirst.liverSgot}" class="validate['length[10]'] text3" style="width:15%"> U/L<br/>
                                白蛋白:<input type="text" name="phPrenatalFollowRecordFirst.liverAlbumin" value="${phPrenatalFollowRecordFirst.liverAlbumin}" class="validate['length[10]'] text3" style="width:15%"> g/L&nbsp;&nbsp;&nbsp;&nbsp;
                                总胆红素:<input type="text" name="phPrenatalFollowRecordFirst.liverBilirubin" value="${phPrenatalFollowRecordFirst.liverBilirubin}" class="validate['length[10]'] text3" style="width:15%"> μmol/L<br/>
                                结合胆红素:<input type="text" name="phPrenatalFollowRecordFirst.liverConjugatedBilirubin" value="${phPrenatalFollowRecordFirst.liverConjugatedBilirubin}" class="validate['length[10]'] text3" style="width:20%"> μmol/L
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel">肾功能：</td>
                            <td colspan="4">
                                血清肌酐:<input type="text" name="phPrenatalFollowRecordFirst.liverSerumCreatinine" value="${phPrenatalFollowRecordFirst.liverSerumCreatinine}" class="validate['length[10]'] text3" style="width:15%"> μmol/L&nbsp;&nbsp;&nbsp;&nbsp;
                                血尿素氮:<input type="text" name="phPrenatalFollowRecordFirst.liverBun" value="${phPrenatalFollowRecordFirst.liverBun}" class="validate['length[10]'] text3" style="width:15%"> μmol/L
                            </td>
					  	</tr>
						<tr>
                            <td class="inputLabel" rowspan="2">阴道分泌物：</td>
                            <td colspan="4">
                                <input type="checkbox" name="vaginalDischarge" checked value="未见异常" class="validate['length[30]'] text3 vaginal_discharge vaginalDischarge" style="width:2%">未见异常
                                <input type="checkbox" name="vaginalDischarge" value="滴虫" class="validate['length[30]'] text3 vaginal_discharge vaginalDischarge" style="width:2%">滴虫
                                <input type="checkbox" name="vaginalDischarge" value="假丝酵母菌" class="validate['length[30]'] text3 vaginal_discharge vaginalDischarge" style="width:2%">假丝酵母菌
                                <input type="checkbox" name="vaginalDischarge" value="其他" class="validate['length[30]'] text3 vaginal_discharge vaginalDischarge" style="width:2%">其他
                                <input type="text" name="phPrenatalFollowRecordFirst.vaginalDischargeExceptions" value="${phPrenatalFollowRecordFirst.vaginalDischargeExceptions}" class="validate['length[100]'] text3" id="vaginalDischargeExceptions" style="width:20%;display:none">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.vaginalDischarge" value="${phPrenatalFollowRecordFirst.vaginalDischarge}" class="validate['length[10]'] text3" id="vaginalDischarge" style="width:20%">
                            </td>
					  	</tr>
						<tr>
                            <td colspan="4">
                                阴道清洁度:<s:select cssStyle="width:100px;" cssClass="text3" list="#{'Ⅰ度':'Ⅰ度','Ⅱ度':'Ⅱ度','Ⅲ度':'Ⅲ度','Ⅳ度':'Ⅳ度'}" listKey="key" listValue="value"
                                theme="simple" name="phPrenatalFollowRecordFirst.vaginalCleanliness" value="phPrenatalFollowRecordFirst.vaginalCleanliness"/>
                            </td>
					  	</tr>
                        <tr>
                            <td class="inputLabel">乙型肝炎五项：</td>
                            <td colspan="4">
                                乙型肝炎表面抗原:<input type="text" name="phPrenatalFollowRecordFirst.hepatitisBSurfaceAntigen" value="${phPrenatalFollowRecordFirst.hepatitisBSurfaceAntigen}" class="validate['length[10]'] text3" style="width:15%"> U/L&nbsp;&nbsp;&nbsp;&nbsp;
                                乙型肝炎表面抗体:<input type="text" name="phPrenatalFollowRecordFirst.hepatitisBSurfaceAntibody" value="${phPrenatalFollowRecordFirst.hepatitisBSurfaceAntibody}" class="validate['length[10]'] text3" style="width:15%"> U/L<br/>
                                乙型肝炎e抗原:<input type="text" name="phPrenatalFollowRecordFirst.hepatitisBEAntigen" value="${phPrenatalFollowRecordFirst.hepatitisBEAntigen}" class="validate['length[10]'] text3" style="width:15%"> g/L&nbsp;&nbsp;&nbsp;&nbsp;
                                乙型肝炎e抗体:<input type="text" name="phPrenatalFollowRecordFirst.hepatitisBEAntibody" value="${phPrenatalFollowRecordFirst.hepatitisBEAntibody}" class="validate['length[10]'] text3" style="width:15%"> μmol/L<br/>
                                乙型肝炎核心抗体:<input type="text" name="phPrenatalFollowRecordFirst.hepatitisBCoreAntibody" value="${phPrenatalFollowRecordFirst.hepatitisBCoreAntibody}" class="validate['length[10]'] text3" style="width:20%"> μmol/L
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">梅毒血清学试验：</td>
                            <td colspan="4">
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'阴性':'阴性','阳性':'阳性'}" listKey="key" listValue="value"
                                theme="simple" name="phPrenatalFollowRecordFirst.serologicalTest" value="phPrenatalFollowRecordFirst.serologicalTest"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">HIV抗体检测：</td>
                            <td colspan="4">
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'阴性':'阴性','阳性':'阳性'}" listKey="key" listValue="value"
                                theme="simple" name="phPrenatalFollowRecordFirst.hivAntibodyTest" value="phPrenatalFollowRecordFirst.hivAntibodyTest"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">B超：</td>
                            <td colspan="4">
                                <input type="text" name="phPrenatalFollowRecordFirst.BUltrasonic" value="${phPrenatalFollowRecordFirst.BUltrasonic}" class="validate['length[30]'] text3" style="width:35%">
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">总体评估：</td>
                            <td colspan="5">
                                    <input type="radio" name="overallEvaluation" checked value="未见异常" class="validate['length[30]'] text3 overallEvaluation" style="width:2%"> 未见异常
                                    <input type="radio" name="overallEvaluation" value="异常" class="validate['length[30]'] text3 overallEvaluation" style="width:2%"> 异常
                                    <input type="text" name="phPrenatalFollowRecordFirst.overallEvaluationExceptions" value="${phPrenatalFollowRecordFirst.overallEvaluationExceptions}" class="validate['length[30]'] text3" id="overallEvaluationExceptions" style="width:20%;display:none;">
                                    <input type="hidden" name="phPrenatalFollowRecordFirst.overallEvaluation" value="${phPrenatalFollowRecordFirst.overallEvaluation}" class="validate['length[30]'] text3" id="overallEvaluation" style="width:20%">
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">保健指导：</td>
                            <td colspan="5">
                                <input type="checkbox" name="healthCareGuidance" value="个人卫生" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">个人卫生&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="心理" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">心理&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="营养" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">营养&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="避免致畸因素和疾病对胚胎的不良影响" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">避免致畸因素和疾病对胚胎的不良影响&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="产前筛查宣传告知" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">产前筛查宣传告知&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="HIV、梅毒、乙肝两对半免费筛查" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">HIV、梅毒、乙肝两对半免费筛查&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="领取孕产妇保健手册" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">领取孕产妇保健手册&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="农村孕产妇住院分娩补助" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">农村孕产妇住院分娩补助&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="不服甲鱼、薏仁等滑胎实物" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">不服甲鱼、薏仁等滑胎实物&nbsp;&nbsp;
                                <input type="checkbox" name="healthCareGuidance" value="其他" class="validate['length[30]'] text3 healthCareGuidance" style="width:2%">其他
                                <input type="text" name="phPrenatalFollowRecordFirst.healthCareGuidanceOther" value="${phPrenatalFollowRecordFirst.healthCareGuidanceOther}" class="validate['length[100]'] text3" id="healthCareGuidanceOther" style="width:20%;display:none">
                                <input type="hidden" name="phPrenatalFollowRecordFirst.healthCareGuidance" value="${phPrenatalFollowRecordFirst.healthCareGuidance}" class="validate['length[100]'] text3" id="healthCareGuidance" style="width:20%">
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">转诊：</td>
                            <td width="80%" colspan="5">
                                <input type="radio" name="isReferra" value="无" class="validate['length[30]'] text3 isReferra" checked style="width:2%">无
                                <input type="radio" name="isReferra" value="有" class="validate['length[30]'] text3 isReferra" style="width:2%">有
                                <span class="isReferra_have" style="display:none"><br/>
                                原因: <input type="text" name="phPrenatalFollowRecordFirst.ireferralReason" value="${phPrenatalFollowRecordFirst.ireferralReason}" class="validate['length[100]'] text3" id="ireferralReason" style="width:20%">
                                机构及科室:<input type="text" name="phPrenatalFollowRecordFirst.ireferralOrg" value="${phPrenatalFollowRecordFirst.ireferralOrg}" class="validate['length[100]'] text3" id="ireferralOrg" style="width:20%">
                                </span>
                                <input type="hidden" name="phPrenatalFollowRecordFirst.isReferral" value="${phPrenatalFollowRecordFirst.isReferral}" class="validate['length[30]'] text3" id="isReferra" style="width:20%">
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel">下次随访日期：</td>
                            <td>
                                <input type="text" name="phPrenatalFollowRecordFirst.nextFollowDate" value="<fmt:formatDate value='${phPrenatalFollowRecordFirst.nextFollowDate}' pattern='yyyy-MM-dd'/>"
                                class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
                            </td>
                            <td class="inputLabel">随访医生签名：</td>
                            <td width="80%" colspan="3">
                                <input type="text" name="" value="" class="validate['length[30]'] text3" checked style="width:20%">
                            </td>
                        </tr>

                    </table>
				<br>
			</div>
		</form>
  	</body>
</html>
