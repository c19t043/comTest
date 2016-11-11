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
		<title>个人基本信息</title>
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
	  			//编辑时将数据加载进来
	  			//既往史疾病
                var domList=$j('.disease_allergy_history');
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryIllnessSet}" var="sd" varStatus="loopIndex">
                $j('.disease_allergy_history[value="无"]').removeAttr('checked');
                    $j(domList).each(function () {
                        if('${sd.illnessName}'==$j(this).val()){
                            $j(this).attr('checked','checked');
                            if('${sd.illnessName}'=='无'){

                            }else if('${sd.illnessName}'=='恶性肿瘤'){
                                $j(this).next().next().show();
                                $j(this).next().val('${sd.remark}');
                                $j(this).next().next().children('input').val("<fmt:formatDate value='${sd.sureTime}' pattern='yyyy-MM-dd'/>");
                            }else if('${sd.illnessName}'=='其他法定传染项目'){
                                $j(this).next().val('${sd.remark}');
                            }else{
                                $j(this).next().show();
                                $j(this).next().children('input').val("<fmt:formatDate value='${sd.sureTime}' pattern='yyyy-MM-dd'/>");
                            }
                        }
                    });
				</c:forEach>
	  			//既往史手术
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryOperationSet}" var="sd" varStatus="loopIndex">
                    $j('#surgery_allergy_history>option[value="有"]').attr('selected','selected');
                    $j('.surgery_allergy_history_add').show();
                    $j('.surgery_allergy_history_have').show().append('<span><br/><input type="hidden" name="phPastHistoryOperationList["${loopIndex.index}"].id" value="" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryOperationList["${loopIndex.index}"].operationName" value="${sd.operationName}" class="validate[\'length[30]\'] text3 surgery_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryOperationList["${loopIndex.index}"].operationTime" value="<fmt:formatDate value='${sd.operationTime}' pattern='yyyy-MM-dd'/>" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="surgery_allergy_history_del">-</span></span>');
				</c:forEach>
	  			//既往史外伤
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryTraumaSet}" var="sd" varStatus="loopIndex">
                    $j('#trauma_allergy_history>option[value="有"]').attr('selected','selected');
                    $j('.trauma_allergy_history_add').show();
                    $j('.trauma_allergy_history_have').show().append('<span><br/><input type="hidden" name="phPastHistoryTraumaList["${loopIndex.index}"].id" value="" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryTraumaList["${loopIndex.index}"].traumaName" value="${sd.traumaName}" class="validate[\'length[30]\'] text3 surgery_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryTraumaList["${loopIndex.index}"].traumaTime" value="<fmt:formatDate value='${sd.traumaTime}' pattern='yyyy-MM-dd'/>" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="trauma_allergy_history_del">-</span></span>');
				</c:forEach>
	  			//既往史输血
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryBloodTransfusionSet}" var="sd" varStatus="loopIndex">
                    $j('#blood_allergy_history>option[value="有"]').attr('selected','selected');
                    $j('.blood_allergy_history_add').show();
                    $j('.blood_allergy_history_have').show().append('<span><br/><input type="hidden" name="phPastHistoryBloodTransfusionList["${loopIndex.index}"].id" value="" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryBloodTransfusionList["${loopIndex.index}"].bloodReason" value="${sd.bloodReason}" class="validate[\'length[30]\'] text3 surgery_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryBloodTransfusionList["${loopIndex.index}"].bloodTime" value="<fmt:formatDate value='${sd.bloodTime}' pattern='yyyy-MM-dd'/>" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="blood_allergy_history_del">-</span></span>');
				</c:forEach>
	  			//药物过敏史
                if("${phPeopleBasicInfo.historyDrugAllergy}"!=''){
                    $j('input[name="drug_allergy_history"]').removeAttr('checked');
                    $j('input[name="drug_allergy_history"][value="有"]').attr('checked','checked');
                    var arr="${phPeopleBasicInfo.historyDrugAllergy}".split(',');
                    var drug_allergy_history_have=$j('input[name="drug_allergy_history_have"]');
                    $j('.drug_allergy_history_have').show();
                    $j(drug_allergy_history_have).each(function(index){
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
	  			//遗传病史
                if("${phPeopleBasicInfo.isGeneticHistory}"=='有'){
                    $j('.genetic_disease_allergy_history').removeAttr('checked');
                    $j('.genetic_disease_allergy_history[value="有"]').attr('checked','checked');
                    $j('.genetic_disease_allergy_history_have').show();
                }
	  			//医疗支付paymentMethod
                if("${phPeopleBasicInfo.paymentMethod}"!=''){
                    var paymentMethod=$j('.paymentMethod');
                    var arr="${phPeopleBasicInfo.paymentMethod}".split(',');
                    $j(paymentMethod).each(function(index){
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
	  			//父亲
                if("${phPeopleBasicInfo.familyHistoryFather}"!=''){
                    var family_history_father=$j('.family_history_father');
                    $j('.family_history_father[value="无"]').removeAttr('checked');
                    var arr="${phPeopleBasicInfo.familyHistoryFather}".split(',');
                    $j(family_history_father).each(function(index){
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
	  			//母亲
                if("${phPeopleBasicInfo.familyHistoryMum}"!=''){
                    var family_history_mather=$j('.family_history_mather');
                    family_history_mather.removeAttr('checked');
                    var arr="${phPeopleBasicInfo.familyHistoryMum}".split(',');
                    $j(family_history_mather).each(function(index){
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
	  			//兄弟姐妹
                if("${phPeopleBasicInfo.familyHistoryBrothers}"!=''){
                    var family_history_bor=$j('.family_history_bor');
                    family_history_bor.removeAttr('checked');
                    var arr="${phPeopleBasicInfo.familyHistoryBrothers}".split(',');
                    $j(family_history_bor).each(function(index){
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
	  			//子女
                if("${phPeopleBasicInfo.familyHistoryChildren}"!=''){
                    var family_history_children=$j('.family_history_children');
                    family_history_children.removeAttr('checked');;
                    var arr="${phPeopleBasicInfo.familyHistoryChildren}".split(',');
                    $j(family_history_children).each(function(index){
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
	  			//残疾
                if("${phPeopleBasicInfo.disabilityStatus}"!=''){
                    var disability=$j('.disability');
                    disability.removeAttr('checked');
                    var arr="${phPeopleBasicInfo.disabilityStatus}".split(',');
                    $j(disability).each(function(index){
                        for(var i=0,l=arr.length;i<l;i++){
                            if($j(this).val()==arr[i]){
                                $j(this).attr('checked','checked');
                                if($j(this).val()=='其他残疾'){
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
				window.location.href = "${pageContext.request.contextPath}"
					+"/residentsFile/child/phMumPeopleBasicInfoList.action"
					+"?accountId="+$j("#accountId",parent.document).val();
			}
			function doSubmit() {
				confirmMsg("确定提交?",function(tp){
					if(tp=='ok'){
                        saveAndSubmit();
                        $('form_handle').action="<s:url namespace='/residentsFile' action='submitPhPeopleBasicInfo' includeParams='true'/>";
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
            $j(function(){
   
    //药物过敏史
                $j('input[name="drug_allergy_history"]').click(function(){
                    if($j(this).val()=='无'){
                        $j('input[name="drug_allergy_history_have"]').removeAttr('checked');
                        $j('#drug_allergy_history_other').val('');
                        $j('.drug_allergy_history_have').hide();
                    }else {
                        $j('.drug_allergy_history_have').show();
                    }
                });
                $j('input[name="drug_allergy_history_have"]').click(function(){
                    if($j(this).val()=='其他'){
                        if($j(this).attr('checked')==undefined){
                            $j('#drug_allergy_history_other').val('').hide();
                        }else{$j('#drug_allergy_history_other').show();}
                    }
                });
    //遗传病史
                $j('.genetic_disease_allergy_history').click(function(){
                    if($j(this).val()=='无'){
                            $j('.genetic_disease_allergy_history_have').hide();
                            $j('#genetic_disease_allergy_history').prev().val('').hide();
                    }else{$j('#genetic_disease_allergy_history').prev().show();$j('.genetic_disease_allergy_history_have').show();}
                });
    //医疗方式
                $j('.paymentMethod').click(function(){
                    if($j(this).val()=='其他'){
                        if($j(this).attr('checked')=='checked'){
                            $j(this).next().show();
                        }else{
                            $j(this).next().hide().val('');
                        }
                    }
                });
    //既往史>疾病
                $j('.disease_allergy_history').click(function(){
                    if($j(this).val()=='无'){
                        if($j(this).attr('checked')!=undefined){
                            $j('.disease_allergy_history').removeAttr('checked');
                            $j(this).attr('checked','checked');
                            $j(this).parent().find('span').hide();
                        }
                    }else if($j(this).val()=='恶性肿瘤'){
                        if($j(this).attr('checked')!=undefined){
                            $j('.disease_allergy_history[value="无"]').removeAttr('checked');
                            $j(this).attr('checked','checked');
                            $j(this).next().next().show();
                        }else{$j(this).next().next().hide();}
                    }else if($j(this).val()=='其他法定传染项目'){
                        if($j(this).attr('checked')!=undefined){
                            $j('.disease_allergy_history[value="无"]').removeAttr('checked');
                            $j(this).attr('checked','checked');
                        }
                    }else{
                        if($j(this).attr('checked')!=undefined){
                            $j('.disease_allergy_history[value="无"]').removeAttr('checked');
                            $j(this).attr('checked','checked');
                            $j(this).next().show();
                        }else{$j(this).next().hide();}
                    }
                });
    //家族史和残疾（ disability）
                $j('.family_history_father,.family_history_children,.family_history_mather,.family_history_bor,.disability').click(function(){
                    if($j(this).val()=='无' || $j(this).val()=='无残疾'){
                        if($j(this).attr('checked')!=undefined){
                            $j(this).parent().children().removeAttr('checked');
                            $j(this).attr('checked','checked');
                            if($j(this).hasClass('family_history_father')||$j(this).hasClass('family_history_children')||$j(this).hasClass('family_history_mather')||$j(this).hasClass('family_history_bor')){
                                $j(this).parent().children('input[value="其他"]').next().hide().val('');
                            }else if($j(this).hasClass('disability')){
                                $j(this).parent().children('input[value="其他残疾"]').next().hide().val('');
                            }
                        }
                    }else{
                        if($j(this).attr('checked')!=undefined){
                            $j(this).parent().children('input[value="无"]').removeAttr('checked');
                            $j(this).parent().children('input[value="无残疾"]').removeAttr('checked');
                            $j(this).attr('checked','checked');
                            if($j(this).hasClass('family_history_father')||$j(this).hasClass('family_history_children')||$j(this).hasClass('family_history_mather')||$j(this).hasClass('family_history_bor')){
                                if($j(this).val()=='其他'&&$j(this).attr('checked')=='checked'){
                                    $j(this).parent().children('input[value="其他"]').next().show();
                                }
                            }else if($j(this).hasClass('disability')){
                                if($j(this).val()=='其他残疾'&&$j(this).attr('checked')=='checked'){
                                    $j(this).parent().children('input[value="其他残疾"]').next().show();
                                }
                            }
                        }else{
                            if($j(this).val()=='其他'){
                                $j(this).parent().children('input[value="其他"]').next().hide();
                            }else{$j(this).next().val('')}
                            if($j(this).val()=='其他残疾'){
                                $j(this).parent().children('input[value="其他残疾"]').next().hide();
                            }else{$j(this).next().val('')}
                        }
                    }
                });
            });
            //保存和提交的方法
            function saveAndSubmit(div){
               
                //医疗支付
                var paymentMethod=$j('.paymentMethod');
                var paymentMethod_str='';
                $j(paymentMethod).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        paymentMethod_str+=','+$j(this).val();
                    }
                });
                $j('#paymentMethod').val(paymentMethod_str.substring(1));
                //药物过敏史
                var drug_allergy_history_have=$j('input[name="drug_allergy_history_have"]');
                var drug_allergy_history_have_str='';
                $j(drug_allergy_history_have).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        drug_allergy_history_have_str+=','+$j(this).val();
                    }
                });
                $j('#drug_allergy_history_have').val(drug_allergy_history_have_str.substring(1));
                //$j('#drug_allergy_history_have').val($j('input[name="drug_allergy_history"][checked="checked"]').val());
                //遗传病史
                var genetic_disease_allergy_history=$j('.genetic_disease_allergy_history');
                $j(genetic_disease_allergy_history).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        $j('#genetic_disease_allergy_history').val($j(this).val());
                    }
                });

                //孩子病史
                var family_history_children=$j('.family_history_children');
                var family_history_children_str='';
                $j(family_history_children).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        family_history_children_str+=','+$j(this).val();
                    }
                });
                $j('#family_history_children').val(family_history_children_str.substring(1));
                //父亲病史
                var family_history_father=$j('.family_history_father');
                var family_history_father_str='';
                $j(family_history_father).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        family_history_father_str+=','+$j(this).val();
                    }
                });
                $j('#family_history_father').val(family_history_father_str.substring(1));
                //母亲病史
                var family_history_mather=$j('.family_history_mather');
                var family_history_mather_str='';
                $j(family_history_mather).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        family_history_mather_str+=','+$j(this).val();
                    }
                });
                $j('#family_history_mather').val(family_history_mather_str.substring(1));
                //兄弟姐妹病史
                var family_history_bor=$j('.family_history_bor');
                var family_history_bor_str='';
                $j(family_history_bor).each(function (index) {
                if($j(this).attr('checked')=='checked'){
                        family_history_bor_str+=','+$j(this).val();
                    }
                });
                $j('#family_history_bor').val(family_history_bor_str.substring(1));
                //残疾
                var disability=$j('.disability');
                var disability_str='';
                $j(disability).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        disability_str+=','+$j(this).val();
                    }
                });
                $j('#disability').val(disability_str.substring(1));
                //疾病
                var domList=$j('.disease_allergy_history[checked="checked"]');
                $j(domList).each(function (index) {
                    if($j(this).attr('checked')=='checked'){
                        if($j(this).val()=='无'){
                            $j(this).prev().attr('name','phPastHistoryIllnessList[0].id');
                            $j(this).attr('name','phPastHistoryIllnessList[0].illnessName');
                        }else if($j(this).val()=='恶性肿瘤'){
                            $j(this).prev().attr('name','phPastHistoryIllnessList['+index+'].id');
                            $j(this).attr('name','phPastHistoryIllnessList['+index+'].illnessName');
                            $j(this).next().attr('name','phPastHistoryIllnessList['+index+'].remark');
                            $j(this).next().next().children('input').attr('name','phPastHistoryIllnessList['+index+'].sureTime');
                        }else if($j(this).val()=='其他法定传染项目'){
                            $j(this).prev().attr('name','phPastHistoryIllnessList['+index+'].id');
                            $j(this).attr('name','phPastHistoryIllnessList['+index+'].illnessName');
                            $j(this).next().attr('name','phPastHistoryIllnessList['+index+'].remark');
                        }else{
                            $j(this).prev().attr('name','phPastHistoryIllnessList['+index+'].id');
                            $j(this).attr('name','phPastHistoryIllnessList['+index+'].illnessName');
                            $j(this).next().children('input').attr('name','phPastHistoryIllnessList['+index+'].sureTime');
                        }
                    }
                });
                //手术
                var surgery_allergy_history_have=$j('.surgery_allergy_history_have>span');
                $j(surgery_allergy_history_have).each(function (index) {
                     $j(this).children('input').eq(0).attr('name','phPastHistoryOperationList['+index+'].id');
                     $j(this).children('input').eq(1).attr('name','phPastHistoryOperationList['+index+'].operationName');
                     $j(this).children('input').eq(2).attr('name','phPastHistoryOperationList['+index+'].operationTime');
                });
                //外伤
                var trauma_allergy_history_have=$j('.trauma_allergy_history_have>span');
                $j(trauma_allergy_history_have).each(function (index) {
                     $j(this).children('input').eq(0).attr('name','phPastHistoryTraumaList['+index+'].id');
                     $j(this).children('input').eq(1).attr('name','phPastHistoryTraumaList['+index+'].traumaName');
                     $j(this).children('input').eq(2).attr('name','phPastHistoryTraumaList['+index+'].traumaTime');
                });
                //输血
                var blood_allergy_history_have=$j('.blood_allergy_history_have>span');
                $j(blood_allergy_history_have).each(function (index) {
                     $j(this).children('input').eq(0).attr('name','phPastHistoryBloodTransfusionList['+index+'].id');
                     $j(this).children('input').eq(1).attr('name','phPastHistoryBloodTransfusionList['+index+'].bloodReason');
                     $j(this).children('input').eq(2).attr('name','phPastHistoryBloodTransfusionList['+index+'].bloodTime');
                });
            }
            function allSelect(div){
                var selectClass=$j(div).prop('class');
                if(selectClass.indexOf('surgery_allergy_history')>-1){//既往史>手术
                    var selectVal=$j(div).find('option:selected').val();
                    if(selectVal=='无'){
                        $j('.surgery_allergy_history_have').hide().empty();
                        $j('.surgery_allergy_history_add').hide();
                    }else {
                        $j('.surgery_allergy_history_have').show().append('<span><br/><input type="hidden" name="phPastHistoryOperationList[0].id" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryOperationList[0].operationName" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3 surgery_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryOperationList[0].operationTime" value="" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="surgery_allergy_history_del">-</span></span>');
                        $j('.surgery_allergy_history_add').show();
                    }
                }
                else if(selectClass.indexOf('trauma_allergy_history')>-1){//既往史>外伤
                    var selectVal=$j(div).find('option:selected').val();
                    if(selectVal=='无'){
                        $j('.trauma_allergy_history_have').hide().empty();
                        $j('.trauma_allergy_history_add').hide();
                    }else {
                        $j('.trauma_allergy_history_have').show().append('<span><br/><input type="hidden" name="phPastHistoryTraumaList[0].id" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryTraumaList[0].traumaName" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3 trauma_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryTraumaList[0].traumaTime" value="" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="trauma_allergy_history_del">-</span></span>');;
                        $j('.trauma_allergy_history_add').show();
                    }
                }
                else if(selectClass.indexOf('blood_allergy_history')>-1){//既往史>输血
                    var selectVal=$j(div).find('option:selected').val();
                    if(selectVal=='无'){
                        $j('.blood_allergy_history_have').hide().empty();
                        $j('.blood_allergy_history_add').hide();
                    }else {
                        $j('.blood_allergy_history_have').show().append('<span><br/><input type="hidden" name="phPastHistoryBloodTransfusionList[0].id" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryBloodTransfusionList[0].bloodReason" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3 blood_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryBloodTransfusionList[0].bloodTime" value="" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="blood_allergy_history_del">-</span></span>');;
                        $j('.blood_allergy_history_add').show();
                    }
                }
            }
            function surgery_allergy_history_add(div){//既往史
                var arr=$j(div).next().children('span');
                arr=arr.length;
                var selectClass=$j(div).prop('class');
                if(selectClass.indexOf('surgery_allergy_history_add')>-1){//既往史>手术>add
                    $j('.surgery_allergy_history_have').append('<span><br/><input type="hidden" name="phPastHistoryOperationList['+arr+'].id" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryOperationList['+arr+'].operationName" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3 surgery_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryOperationList['+arr+'].operationTime" value="" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="surgery_allergy_history_del">-</span></span>');
                }
                else if(selectClass.indexOf('trauma_allergy_history_add')>-1){//既往史>外伤>add
                    $j('.trauma_allergy_history_have').append('<span><br/><input type="hidden" name="phPastHistoryTraumaList['+arr+'].id" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryTraumaList['+arr+'].traumaName" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3 trauma_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryTraumaList['+arr+'].traumaTime" value="" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="trauma_allergy_history_del">-</span></span>');
                }
                else if(selectClass.indexOf('blood_allergy_history_add')>-1){//既往史>输血>add
                    $j('.blood_allergy_history_have').append('<span><br/><input type="hidden" name="phPastHistoryBloodTransfusionList['+arr+'].id" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3">名称:<input type="text" name="phPastHistoryBloodTransfusionList['+arr+'].bloodReason" value="${familyHistoryBrothers}" class="validate[\'length[30]\'] text3 blood_allergy_history_have_list" style="width:120px"> 时间:<input type="text" name="phPastHistoryBloodTransfusionList['+arr+'].bloodTime" value="" class="validate[\'length[20]\'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:\'yyyy-MM-dd\',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="blood_allergy_history_del">-</span></span>');
                }
            }
            function surgery_allergy_history_del(div){//既往史
                $j(div).parent().remove();
            }
            $j(function(){
            	$j("#accountId").val($j("#accountId",parent.document).val());
            })
  		</script>
        <style type="text/css">
            .eXtremeTable .tableRegion2 .inputLabel{
                width:10%;
            }
            .eXtremeTable .tableRegion2 input{
                width:80%;
            }
            .surgery_allergy_history_add,.surgery_allergy_history_del,.trauma_allergy_history_add,.trauma_allergy_history_del,.blood_allergy_history_add,.blood_allergy_history_del{
                padding:2px 5px;
                border-radius:3px;
                border:1px solid #c1c1c1;
            }
        </style>
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
					</div>
				</div>
			</div>
		</div>
		
		<form id="form_handle" name="form_handle" action="<s:url namespace='/residentsFile' action='saveOrUpdatePhPeopleBasicInfo' includeParams='true'/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="phPeopleBasicInfo.id" value="${phPeopleBasicInfo.id}">
			<input type="hidden" name="phPeopleBasicInfo.infoOwner" value="mum">
			<input type="hidden" id="accountId" name="accountId">
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
							<td class="inputLabel" colspan="2">姓名：</td>
							<td width="23%">
								<input type="text" name="phPeopleBasicInfo.name" value="${phPeopleBasicInfo.name}" class="validate['required','length[10]'] text3" width="70%">
								<span class="text4">*</span>
							</td>
							<td class="inputLabel">性别：</td>
							<td width="23%">
                                <%--<input type="radio" name="familyHistoryBrothers" value="${familyHistoryBrothers}" class="validate['length[30]'] text3" style="width:10%">男--%>
                                <%--<input type="radio" name="familyHistoryBrothers" value="${familyHistoryBrothers}" class="validate['length[30]'] text3" style="width:10%">女--%>
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'男':'男','女':'女'}" listKey="key" listValue="value"
                                 theme="simple" name="phPeopleBasicInfo.sex" value="phPeopleBasicInfo.sex"/>
                                <span class="text4">*</span>
							</td>
                            <td class="inputLabel">出生日期：</td>
                            <td width="23%">
                                <input type="text" name="phPeopleBasicInfo.birthday" value="<fmt:formatDate value='${phPeopleBasicInfo.birthday}' pattern='yyyy-MM-dd'/>"
                                class="validate['required','length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
                                <span class="text4">*</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">身份证号：</td>
							<td width="23%">
								<input type="text" name="phPeopleBasicInfo.idCardNum" value="${phPeopleBasicInfo.idCardNum}" class="validate['required','IDCard','length[20]'] text3" width="70%">
								<span class="text4">*</span>
							</td>
							<td class="inputLabel">工作单位：</td>
							<td width="46%" colspan="3">
                                <input type="text" name="phPeopleBasicInfo.workUnit" value="${phPeopleBasicInfo.workUnit}" class="validate['length[100]'] text3" width="70%">
							</td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">家庭电话：</td>
							<td width="23%">
								<input type="text" name="phPeopleBasicInfo.familyTel" value="${phPeopleBasicInfo.familyTel}" class="validate['length[20]'] text3" width="70%">
							</td>
							<td class="inputLabel">联系人姓名：</td>
							<td width="23%">
								<input type="text" name="phPeopleBasicInfo.contactName" value="${phPeopleBasicInfo.contactName}" class="validate['length[10]'] text3" width="70%">
							</td>
							<td class="inputLabel">联系人电话：</td>
							<td width="23%">
                                <input type="text" name="phPeopleBasicInfo.contactPhone" value="${phPeopleBasicInfo.contactPhone}" class="validate['phone','length[20]'] text3" width="70%">
							</td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">常住类型：</td>
							<td width="23%">
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'户籍':'户籍','非户籍':'非户籍'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.permanentType" value="phPeopleBasicInfo.permanentType"/>
							</td>
							<td class="inputLabel">民族：</td>
							<td width="46%" colspan="3">
                               <s:select cssStyle="width:100px;" cssClass="text3" 
									list="#{'汉族':'汉族','蒙古族':'蒙古族','彝族':'彝族','侗族':'侗族','哈萨克族':'哈萨克族','畲族':'畲族',
										'纳西族':'纳西族','仫佬族':'仫佬族','仡佬族':'仡佬族','怒族':'怒族','保安族':'保安族','鄂伦春族':'鄂伦春族',
										'回族':'回族','壮族':'壮族','瑶族':'瑶族','傣族':'傣族','高山族':'高山族','景颇族':'景颇族','羌族':'羌族',
										'锡伯族':'锡伯族','乌孜别克族':'乌孜别克族','裕固族':'裕固族','赫哲族':'赫哲族','藏族':'藏族','布依族':'布依族',
										'白族':'白族','黎族':'黎族','拉祜族':'拉祜族','柯尔克孜族':'柯尔克孜族','布朗族':'布朗族','阿昌族':'阿昌族',
										'俄罗斯族':'俄罗斯族','京族':'京族','门巴族':'门巴族','维吾尔族':'维吾尔族','朝鲜族':'朝鲜族','土家族':'土家族',
										'傈僳族':'傈僳族','水族':'水族','土族':'土族','撒拉族':'撒拉族','普米族':'普米族','鄂温克族':'鄂温克族','塔塔尔族':'塔塔尔族',
										'珞巴族':'珞巴族','苗族':'苗族','满族':'满族','哈尼族':'哈尼族','佤族':'佤族','东乡族':'东乡族','达斡尔族':'达斡尔族',
										'毛南族':'毛南族','塔吉克族':'塔吉克族','德昂族':'德昂族','独龙族':'独龙族','基诺族':'基诺族'}" 
										listKey="key" listValue="value"
                                 		theme="simple" name="phPeopleBasicInfo.nation" value="phPeopleBasicInfo.nation"/>
							</td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">血型：</td>
							<td width="80%" colspan="5">
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'A型':'A型','B型':'B型','O型':'O型','AB型':'AB型','不详':'不详'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.bloodType" value="phPeopleBasicInfo.bloodType"/>
                                <span>/</span>
                                RH 阴性:
                                <s:select cssStyle="width:100px;" cssClass="text3" list="#{'否':'否','是':'是','不详':'不详'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.bloodRH" value="phPeopleBasicInfo.bloodRH"/>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">文化程度：</td>
							<td width="80%" colspan="5">
                                <s:select cssStyle="width:200px;" cssClass="text3" list="#{'文盲及半文盲':'文盲及半文盲','小学':'小学','初中':'初中','高中/技校':'高中/技校','大学专科及以上':'大学专科及以上','不详':'不详'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.degreeEducation" value="phPeopleBasicInfo.degreeEducation"/>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">职业：</td>
							<td width="80%" colspan="5">
                                <s:select cssStyle="width:300px;" cssClass="text3" list="#{'国家机关、党群组织、企业、事业单位负责人':'国家机关、党群组织、企业、事业单位负责人','专业技术人员':'专业技术人员','办事人员和有关人员':'办事人员和有关人员'
                                ,'商业、服务业人员':'商业、服务业人员','农、林、牧、渔、水利业生产人员':'农、林、牧、渔、水利业生产人员','生产、运输设备操作人员及有关人员':'生产、运输设备操作人员及有关人员','军人':'军人'
                                ,'不便分类的其他从业人员':'不便分类的其他从业人员'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.profession" value="phPeopleBasicInfo.profession"/>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">婚姻状况：</td>
							<td width="80%" colspan="5">
                                <s:select cssStyle="width:200px;" cssClass="text3" list="#{'未婚':'未婚','已婚':'已婚','离婚':'离婚','丧偶':'丧偶','未说明的婚姻情况':'未说明的婚姻情况'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.maritalStatus" value="phPeopleBasicInfo.maritalStatus"/>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">医疗费用支付方式：</td>
							<td width="80%" colspan="5">
                                <input type="checkbox" name="paymentMethod" value="城镇职工基本医疗保险" class="validate['length[30]'] text3 paymentMethod" style="width:2%">城镇职工基本医疗保险
                                <input type="checkbox" name="paymentMethod" value="城乡居民基本医疗保险" class="validate['length[30]'] text3 paymentMethod" style="width:2%">城乡居民基本医疗保险
                                <input type="checkbox" name="paymentMethod" value="大病补充医疗" class="validate['length[30]'] text3 paymentMethod" style="width:2%">大病补充医疗
                                <input type="checkbox" name="paymentMethod" value="贫困救助" class="validate['length[30]'] text3 paymentMethod" style="width:2%">贫困救助
                                <input type="checkbox" name="paymentMethod" value="商业医疗保险" class="validate['length[30]'] text3 paymentMethod" style="width:2%">商业医疗保险
                                <input type="checkbox" name="paymentMethod" value="全公费" class="validate['length[30]'] text3 paymentMethod" style="width:2%">全公费
                                <input type="checkbox" name="paymentMethod" value="全自费" class="validate['length[30]'] text3 paymentMethod" style="width:2%">全自费
                                <input type="checkbox" name="paymentMethod" value="其他" class="validate['length[30]'] text3 paymentMethod" style="width:2%">其他
                                <input type="text" name="phPeopleBasicInfo.paymentMethodOther" value="${phPeopleBasicInfo.paymentMethodOther }" class="validate['length[100]'] text3" style="width:20%;display:none;">
                                <input type="hidden" name="phPeopleBasicInfo.paymentMethod" value="${phPeopleBasicInfo.paymentMethod }" class="validate['length[100]'] text3" id="paymentMethod" style="width:20%">
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">药物过敏史：</td>
							<td width="80%" colspan="5">
                                <input type="radio" name="drug_allergy_history" value="无" class="validate['length[30]'] text3" checked style="width:2%">无
                                <input type="radio" name="drug_allergy_history" value="有" class="validate['length[30]'] text3" style="width:2%">有
                                <span class="drug_allergy_history_have" style="display:none">
                                    <input type="checkbox" name="drug_allergy_history_have" value="青霉素" class="validate['length[30]'] text3" style="width:2%">青霉素
                                    <input type="checkbox" name="drug_allergy_history_have" value="磺胺" class="validate['length[30]'] text3" style="width:2%">磺胺
                                    <input type="checkbox" name="drug_allergy_history_have" value="链霉素" class="validate['length[30]'] text3" style="width:2%">链霉素
                                    <input type="checkbox" name="drug_allergy_history_have" value="其他" class="validate['length[30]'] text3" style="width:2%">其他
                                    <input type="text" name="phPeopleBasicInfo.historyDrugAllergyOther" value="${phPeopleBasicInfo.historyDrugAllergyOther}" class="validate['length[100]'] text3" id="drug_allergy_history_other" style="width:20%;display:none;">
                                    <input type="hidden" name="phPeopleBasicInfo.historyDrugAllergy" value="${phPeopleBasicInfo.historyDrugAllergy}" class="validate['length[100]'] text3" id="drug_allergy_history_have" style="width:20%">
                                </span>
                            </td>
					  	</tr>
                        <tr>
							<td rowspan="4">既往史：</td>
							<td>疾病：</td>
                            <td colspan="5">
                            	<input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" checked name="phPastHistoryIllnessList[].illnessName" value="无" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">无<br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="高血压" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">高血压
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="糖尿病" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">糖尿病
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="冠心病" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">冠心病
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="COPD" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">COPD
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="恶性肿瘤" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">恶性肿瘤
                                <input type="text" name="phPastHistoryIllnessList[].remark" class="validate['length[30]'] text3" style="width:120px">
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="脑卒中" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">脑卒中
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="重性精神疾病" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">重性精神疾病
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="结核病" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">结核病
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="肝炎" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">肝炎
                                <span style="display:none">&nbsp&nbsp;确诊时间:<input style="width:100px" type="text" name="phPastHistoryIllnessList[].sureTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"
                                class="['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"></span><br/>

                                <input type="hidden" name="phPastHistoryIllnessList[].id" value="">
                                <input type="checkbox" name="phPastHistoryIllnessList[].illnessName" value="其他法定传染项目" class="validate['length[30]'] text3 disease_allergy_history" style="width:2%">其他法定传染项目
                                <input type="text" name="familyHistoryBrothers" value="${familyHistoryBrothers}" class="validate['length[30]'] text3" style="width:20%">
                            </td>
					  	</tr>
                        <tr>
							<td>手术：</td>
                            <td colspan="5">
                                <s:select cssStyle="width:100px;" cssClass="text3 surgery_allergy_history" list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo" id="surgery_allergy_history" value=""  onchange="allSelect(this)"/>&nbsp;<span onclick="surgery_allergy_history_add(this)" class="surgery_allergy_history_add" style="display:none">+</span>
                                <span class="surgery_allergy_history_have" style="display:none">
                                    <%--<span>--%>
                                        <%--<input type="hidden" name="phPastHistoryOperationList[].id" value="${familyHistoryBrothers}" class="validate['length[30]'] text3">--%>
                                        <%--名称:<input type="text" name="phPastHistoryOperationList[].operationName" value="${familyHistoryBrothers}" class="validate['length[30]'] text3 surgery_allergy_history_have_list" style="width:120px">--%>
                                        <%--时间:<input type="text" name="phPastHistoryOperationList[].operationTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"--%>
                                        <%--class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="surgery_allergy_history_del">-</span>--%>
                                    <%--</span>--%>
                                </span>

                            </td>
					  	</tr>
                        <tr>
							<td>外伤：</td>
                            <td colspan="5">
                                <s:select cssStyle="width:100px;" cssClass="text3 trauma_allergy_history" list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.org_level" id="trauma_allergy_history" value="phPeopleBasicInfo.org_level"  onchange="allSelect(this)"/>&nbsp;<span onclick="surgery_allergy_history_add(this)" class="trauma_allergy_history_add" style="display:none">+</span>
                                <span class="trauma_allergy_history_have" style="display:none">
                                    <%--<span>--%>
                                        <%--<input type="hidden" name="phPastHistoryTraumaList[].id" value="${familyHistoryBrothers}" class="validate['length[30]'] text3">--%>
                                        <%--名称:<input type="text" name="phPastHistoryTraumaList[].traumaName" value="${familyHistoryBrothers}" class="validate['length[30]'] text3 trauma_allergy_history_have_list" style="width:120px">--%>
                                        <%--时间:<input type="text" name="phPastHistoryTraumaList[].traumaTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"--%>
                                        <%--class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="trauma_allergy_history_del">-</span>--%>
                                    <%--</span>--%>
                                </span>
                            </td>
					  	</tr>
                        <tr>
							<td>输血：</td>
                            <td colspan="5">
                                <s:select cssStyle="width:100px;" cssClass="text3 blood_allergy_history" list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
                                theme="simple" name="phPeopleBasicInfo.org_level" id="blood_allergy_history" value="phPeopleBasicInfo.org_level"  onchange="allSelect(this)"/>&nbsp;<span onclick="surgery_allergy_history_add(this)" class="blood_allergy_history_add" style="display:none">+</span>
                                <span class="blood_allergy_history_have" style="display:none">
                                    <%--<span>--%>
                                        <%--<input type="hidden" name="phPastHistoryBloodTransfusionList[].id" value="${familyHistoryBrothers}" class="validate['length[30]'] text3">--%>
                                        <%--名称:<input type="text" name="phPastHistoryBloodTransfusionList[].bloodReason" value="${familyHistoryBrothers}" class="validate['length[30]'] text3 blood_allergy_history_have_list" style="width:120px">--%>
                                        <%--时间:<input type="text" name="phPastHistoryBloodTransfusionList[].bloodTime" value="<fmt:formatDate value='${goodSeedBaseInfo.first_create_time}' pattern='yyyy-MM-dd'/>"--%>
                                        <%--class="validate['length[20]'] text3" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});" style="width:120px">&nbsp;<span onclick="surgery_allergy_history_del(this)" class="blood_allergy_history_del">-</span>--%>
                                    <%--</span>--%>
                                </span>
                            </td>
					  	</tr>
                        <tr>
							<td rowspan="4">家族史：</td>
							<td style="width:100px">父亲：</td>
                            <td colspan="5">
                                <input type="checkbox" checked name="familyHistoryFather" value="无" class="validate['length[30]'] text3 family_history_father" style="width:2%">无
                                <input type="checkbox" name="familyHistoryFather" value="高血压" class="validate['length[30]'] text3 family_history_father" style="width:2%">高血压
                                <input type="checkbox" name="familyHistoryFather" value="糖尿病" class="validate['length[30]'] text3 family_history_father" style="width:2%">糖尿病
                                <input type="checkbox" name="familyHistoryFather" value="冠心病" class="validate['length[30]'] text3 family_history_father" style="width:2%">冠心病
                                <input type="checkbox" name="familyHistoryFather" value="慢性阻塞性肺疾病" class="validate['length[30]'] text3 family_history_father" style="width:2%">慢性阻塞性肺疾病
                                <input type="checkbox" name="familyHistoryFather" value="恶性肿瘤" class="validate['length[30]'] text3 family_history_father" style="width:2%">恶性肿瘤
                                <input type="checkbox" name="familyHistoryFather" value="脑卒中" class="validate['length[30]'] text3 family_history_father" style="width:2%">脑卒中
                                <input type="checkbox" name="familyHistoryFather" value="重性精神疾病" class="validate['length[30]'] text3 family_history_father" style="width:2%">重性精神疾病
                                <input type="checkbox" name="familyHistoryFather" value="结核病" class="validate['length[30]'] text3 family_history_father" style="width:2%">结核病
                                <input type="checkbox" name="familyHistoryFather" value="肝炎" class="validate['length[30]'] text3 family_history_father" style="width:2%">肝炎
                                <input type="checkbox" name="familyHistoryFather" value="先天畸形" class="validate['length[30]'] text3 family_history_father" style="width:2%">先天畸形
                                <input type="checkbox" name="familyHistoryFather" value="其他" class="validate['length[30]'] text3 family_history_father" style="width:2%">其他
                                <input type="text" name="phPeopleBasicInfo.familyHistoryFatherOther" value="${phPeopleBasicInfo.familyHistoryFatherOther}" class="validate['length[50]'] text3" style="width:20%;display:none;">
                                <input type="hidden" name="phPeopleBasicInfo.familyHistoryFather" value="${phPeopleBasicInfo.familyHistoryFather}" class="validate['length[50]'] text3" id="family_history_father" style="width:20%">
                            </td>
					  	</tr>
                        <tr>
							<td>母亲：</td>
                            <td colspan="5">
                                <input type="checkbox" checked name="familyHistoryMum" value="无" class="validate['length[30]'] text3 family_history_mather" style="width:2%">无
                                <input type="checkbox" name="familyHistoryMum" value="高血压" class="validate['length[30]'] text3 family_history_mather" style="width:2%">高血压
                                <input type="checkbox" name="familyHistoryMum" value="糖尿病" class="validate['length[30]'] text3 family_history_mather" style="width:2%">糖尿病
                                <input type="checkbox" name="familyHistoryMum" value="冠心病" class="validate['length[30]'] text3 family_history_mather" style="width:2%">冠心病
                                <input type="checkbox" name="familyHistoryMum" value="慢性阻塞性肺疾病" class="validate['length[30]'] text3 family_history_mather" style="width:2%">慢性阻塞性肺疾病
                                <input type="checkbox" name="familyHistoryMum" value="恶性肿瘤" class="validate['length[30]'] text3 family_history_mather" style="width:2%">恶性肿瘤
                                <input type="checkbox" name="familyHistoryMum" value="脑卒中" class="validate['length[30]'] text3 family_history_mather" style="width:2%">脑卒中
                                <input type="checkbox" name="familyHistoryMum" value="重性精神疾病" class="validate['length[30]'] text3 family_history_mather" style="width:2%">重性精神疾病
                                <input type="checkbox" name="familyHistoryMum" value="结核病" class="validate['length[30]'] text3 family_history_mather" style="width:2%">结核病
                                <input type="checkbox" name="familyHistoryMum" value="肝炎" class="validate['length[30]'] text3 family_history_mather" style="width:2%">肝炎
                                <input type="checkbox" name="familyHistoryMum" value="先天畸形" class="validate['length[30]'] text3 family_history_mather" style="width:2%">先天畸形
                                <input type="checkbox" name="familyHistoryMum" value="其他" class="validate['length[30]'] text3 family_history_mather" style="width:2%">其他
                                <input type="text" name="phPeopleBasicInfo.familyHistoryMumOther" value="${phPeopleBasicInfo.familyHistoryMumOther}" class="validate['length[50]'] text3" style="width:20%;display:none;">
                                <input type="hidden" name="phPeopleBasicInfo.familyHistoryMum" value="${phPeopleBasicInfo.familyHistoryMum}" class="validate['length[200]'] text3" id="family_history_mather" style="width:20%">
                            </td>
					  	</tr>
                        <tr>
							<td>兄弟姐妹：</td>
                            <td colspan="5">
                                <input type="checkbox" checked name="familyHistoryBrothers" value="无" class="validate['length[30]'] text3 family_history_bor" style="width:2%">无
                                <input type="checkbox" name="familyHistoryBrothers" value="高血压" class="validate['length[30]'] text3 family_history_bor" style="width:2%">高血压
                                <input type="checkbox" name="familyHistoryBrothers" value="糖尿病" class="validate['length[30]'] text3 family_history_bor" style="width:2%">糖尿病
                                <input type="checkbox" name="familyHistoryBrothers" value="冠心病" class="validate['length[30]'] text3 family_history_bor" style="width:2%">冠心病
                                <input type="checkbox" name="familyHistoryBrothers" value="慢性阻塞性肺疾病" class="validate['length[30]'] text3 family_history_bor" style="width:2%">慢性阻塞性肺疾病
                                <input type="checkbox" name="familyHistoryBrothers" value="恶性肿瘤" class="validate['length[30]'] text3 family_history_bor" style="width:2%">恶性肿瘤
                                <input type="checkbox" name="familyHistoryBrothers" value="脑卒中" class="validate['length[30]'] text3 family_history_bor" style="width:2%">脑卒中
                                <input type="checkbox" name="familyHistoryBrothers" value="重性精神疾病" class="validate['length[30]'] text3 family_history_bor" style="width:2%">重性精神疾病
                                <input type="checkbox" name="familyHistoryBrothers" value="结核病" class="validate['length[30]'] text3 family_history_bor" style="width:2%">结核病
                                <input type="checkbox" name="familyHistoryBrothers" value="肝炎" class="validate['length[30]'] text3 family_history_bor" style="width:2%">肝炎
                                <input type="checkbox" name="familyHistoryBrothers" value="先天畸形" class="validate['length[30]'] text3 family_history_bor" style="width:2%">先天畸形
                                <input type="checkbox" name="familyHistoryBrothers" value="其他" class="validate['length[30]'] text3 family_history_bor" style="width:2%">其他
                                <input type="text" name="phPeopleBasicInfo.familyHistoryBrothersOther" value="${phPeopleBasicInfo.familyHistoryBrothersOther}" class="validate['length[50]'] text3" style="width:20%;display:none;">
                                <input type="hidden" name="phPeopleBasicInfo.familyHistoryBrothers" value="${phPeopleBasicInfo.familyHistoryBrothers}" class="validate['length[200]'] text3" id="family_history_bor" style="width:20%">
                            </td>
					  	</tr>
                        <tr>
							<td>子女：</td>
                            <td colspan="5">
                                <input type="checkbox" checked name="familyHistoryChildren" value="无" class="validate['length[30]'] text3 family_history_children" style="width:2%">无
                                <input type="checkbox" name="familyHistoryChildren" value="高血压" class="validate['length[30]'] text3 family_history_children" style="width:2%">高血压
                                <input type="checkbox" name="familyHistoryChildren" value="糖尿病" class="validate['length[30]'] text3 family_history_children" style="width:2%">糖尿病
                                <input type="checkbox" name="familyHistoryChildren" value="冠心病" class="validate['length[30]'] text3 family_history_children" style="width:2%">冠心病
                                <input type="checkbox" name="familyHistoryChildren" value="慢性阻塞性肺疾病" class="validate['length[30]'] text3 family_history_children" style="width:2%">慢性阻塞性肺疾病
                                <input type="checkbox" name="familyHistoryChildren" value="恶性肿瘤" class="validate['length[30]'] text3 family_history_children" style="width:2%">恶性肿瘤
                                <input type="checkbox" name="familyHistoryChildren" value="脑卒中" class="validate['length[30]'] text3 family_history_children" style="width:2%">脑卒中
                                <input type="checkbox" name="familyHistoryChildren" value="重性精神疾病" class="validate['length[30]'] text3 family_history_children" style="width:2%">重性精神疾病
                                <input type="checkbox" name="familyHistoryChildren" value="结核病" class="validate['length[30]'] text3 family_history_children" style="width:2%">结核病
                                <input type="checkbox" name="familyHistoryChildren" value="肝炎" class="validate['length[30]'] text3 family_history_children" style="width:2%">肝炎
                                <input type="checkbox" name="familyHistoryChildren" value="先天畸形" class="validate['length[30]'] text3 family_history_children" style="width:2%">先天畸形
                                <input type="checkbox" name="familyHistoryChildren" value="其他" class="validate['length[30]'] text3 family_history_children" style="width:2%">其他
                                <input type="text" name="phPeopleBasicInfo.familyHistoryChildrenOther" value="${phPeopleBasicInfo.familyHistoryChildrenOther}" class="validate['length[30]'] text3" style="width:20%;display:none;">
                                <input type="hidden" name="phPeopleBasicInfo.familyHistoryChildren" value="${phPeopleBasicInfo.familyHistoryChildren}" class="validate['length[200]'] text3" id="family_history_children" style="width:20%">
                            </td>
					  	</tr>
                        <tr>
                        <td class="inputLabel" colspan="2">遗传病史：</td>
                            <td width="80%" colspan="5">
                                <input type="radio" name="genetic_disease_allergy_history" value="无" class="validate['length[30]'] text3 genetic_disease_allergy_history" checked style="width:2%">无
                                <input type="radio" name="genetic_disease_allergy_history" value="有" class="validate['length[30]'] text3 genetic_disease_allergy_history" style="width:2%">有
                                <span class="genetic_disease_allergy_history_have" style="display:none">
                                    疾病名称
                                    <input type="text" name="phPeopleBasicInfo.geneticHistoryNames" value="${phPeopleBasicInfo.geneticHistoryNames}" class="validate['length[50]'] text3" style="width:30%">
                                    <input type="hidden" name="phPeopleBasicInfo.isGeneticHistory" value="${phPeopleBasicInfo.isGeneticHistory}" class="validate['length[50]'] text3" id="genetic_disease_allergy_history" style="width:30%">
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel" colspan="2">残疾情况：</td>
                            <td width="80%" colspan="5">
                                <input type="checkbox" checked name="disabilityStatus" value="无残疾" class="validate['length[30]'] text3 disability" style="width:2%">无残疾
                                <input type="checkbox" name="disabilityStatus" value="视力残疾" class="validate['length[30]'] text3 disability" style="width:2%">视力残疾
                                <input type="checkbox" name="disabilityStatus" value="听力残疾" class="validate['length[30]'] text3 disability" style="width:2%">听力残疾
                                <input type="checkbox" name="disabilityStatus" value="语言残疾" class="validate['length[30]'] text3 disability" style="width:2%">语言残疾
                                <input type="checkbox" name="disabilityStatus" value="肢体残疾" class="validate['length[30]'] text3 disability" style="width:2%">肢体残疾
                                <input type="checkbox" name="disabilityStatus" value="智力残疾" class="validate['length[30]'] text3 disability" style="width:2%">智力残疾
                                <input type="checkbox" name="disabilityStatus" value="精神残疾" class="validate['length[30]'] text3 disability" style="width:2%">精神残疾
                                <input type="checkbox" name="disabilityStatus" value="其他残疾" class="validate['length[30]'] text3 disability" style="width:2%">其他残疾
                                <input type="text" name="phPeopleBasicInfo.disabilityStatusOther" value="${phPeopleBasicInfo.disabilityStatusOther}" class="validate['length[50]'] text3" style="width:20%;display:none;">
                                <input type="hidden" name="phPeopleBasicInfo.disabilityStatus" value="${phPeopleBasicInfo.disabilityStatus}" class="validate['length[50]'] text3" id="disability" style="width:20%">
                            </td>
                        </tr>
                    </table>
				<br>
			</div>
		</form>
  	</body>
</html>
