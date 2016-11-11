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
	  			//既往史手术
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryOperationSet}" var="sd" varStatus="loopIndex">
                $j('#surgery_allergy_history').hide();
                if("${sd.operationTime}"!=''){
                    var s="${sd.operationTime}".split(' ')[0];
                }else{s=''}
                $j('.surgery_allergy_history').append('<span>名称：${sd.operationName}</span>&nbsp;&nbsp;<span>时间：'+s+'<br/></span>');
				</c:forEach>
	  			//既往史外伤
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryTraumaSet}" var="sd" varStatus="loopIndex">
                    $j('#trauma_allergy_history').hide();
                    $j('#surgery_allergy_history').hide();
                    if("${sd.traumaTime}"!=''){
                    var s="${sd.traumaTime}".split(' ')[0];
                    }else{s=''}
                    $j('.trauma_allergy_history').append('<span>名称：${sd.traumaName}</span>&nbsp;&nbsp;<span>时间：'+s+'<br/><span>');
				</c:forEach>
	  			//既往史输血
	  			<c:forEach items="${phPeopleBasicInfo.phPastHistoryBloodTransfusionSet}" var="sd" varStatus="loopIndex">
                    $j('#blood_allergy_history').hide();
                    $j('#surgery_allergy_history').hide();
                    if("${sd.bloodTime}"!=''){
                    var s="${sd.bloodTime}".split(' ')[0];
                    }else{s=''}
                    $j('.blood_allergy_history').append('<span>名称：${sd.bloodReason}</span>&nbsp;&nbsp;<span>时间：'+s+'<br/><span>');
				</c:forEach>


			});
			function doHandle() {
                $('save').click();
			}
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}"
					+"/residentsFile/child/phPeopleBasicInfoList.action"
					+"?accountId="+$j("#accountId",parent.document).val();
			}
			function doSubmit() {
				confirmMsg("确定提交?",function(tp){
					if(tp=='ok'){
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
<%-- 						<span><a href="javascript:doHandle();" class="pop_button_blue">保存</a></span>
						<span><a href="javascript:doSubmit();" class="pop_button_red">提交</a></span> --%>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		
		<form id="form_handle" name="form_handle" action="<s:url namespace='residentsFile' action='saveOrUpdatePhPeopleBasicInfo' includeParams='true'/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="phPeopleBasicInfo.id" value="${phPeopleBasicInfo.id}">
			<input type="hidden" name="phPeopleBasicInfo.infoOwner" value="child">
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
								<span>${phPeopleBasicInfo.name}</span>
							</td>
							<td class="inputLabel">性别：</td>
							<td width="23%">
                                <span>${phPeopleBasicInfo.sex}</span>
							</td>
                            <td class="inputLabel">出生日期：</td>
                            <td width="23%">
                                <span>${phPeopleBasicInfo.birthday}</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">身份证号：</td>
							<td width="23%">
								<span>${phPeopleBasicInfo.idCardNum}</span>
							</td>
							<td class="inputLabel">工作单位：</td>
							<td width="46%" colspan="3">
                                <span>${phPeopleBasicInfo.workUnit}</span>
							</td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">家庭电话：</td>
							<td width="23%">
								<span>${phPeopleBasicInfo.familyTel}</span>
							</td>
							<td class="inputLabel">联系人姓名：</td>
							<td width="23%">
								<span>${phPeopleBasicInfo.contactName}</span>
							</td>
							<td class="inputLabel">联系人电话：</td>
							<td width="23%">
                                <span>${phPeopleBasicInfo.contactPhone}</span>
							</td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">常住类型：</td>
							<td width="23%">
                                <span>${phPeopleBasicInfo.permanentType}</span>
							</td>
							<td class="inputLabel">民族：</td>
							<td width="46%" colspan="3">
								<span>${phPeopleBasicInfo.nation}</span>
							</td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">血型：</td>
							<td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.bloodType}</span>
                                <span>/</span>
                                RH 阴性:
                                <span>${phPeopleBasicInfo.bloodRH}</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">文化程度：</td>
							<td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.degreeEducation}</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">职业：</td>
							<td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.profession}</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">婚姻状况：</td>
							<td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.maritalStatus}</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">医疗费用支付方式：</td>
							<td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.paymentMethod}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.paymentMethodOther}</span>
                            </td>
					  	</tr>
                        <tr>
							<td class="inputLabel" colspan="2">药物过敏史：</td>
							<td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.historyDrugAllergy}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.historyDrugAllergyOther}</span>
                            </td>
					  	</tr>
                        <tr>
							<td rowspan="4">既往史：</td>
							<td>疾病：</td>
                            <td colspan="5">
                                <c:forEach items="${phPeopleBasicInfo.phPastHistoryIllnessSet}" var="sd" varStatus="loopIndex">
                                    <span>${sd.illnessName}</span>&nbsp;&nbsp;<span>${sd.remark}</span>&nbsp;&nbsp;<span><fmt:formatDate value='${sd.sureTime}' pattern='yyyy-MM-dd'/></span><br/>
                                </c:forEach>
                            </td>
					  	</tr>
                        <tr>
							<td>手术：</td>
                            <td colspan="5">
                                <span id="surgery_allergy_history" style="display:block">无</span>
                                <span class="surgery_allergy_history"></span>
                            </td>
					  	</tr>
                        <tr>
							<td>外伤：</td>
                            <td colspan="5">
                                <span id="trauma_allergy_history" style="display:block">无</span>
                                <span class="trauma_allergy_history"></span>
                            </td>
					  	</tr>
                        <tr>
							<td>输血：</td>
                            <td colspan="5">
                                <span id="blood_allergy_history" style="display:block">无</span>
                                <span class="blood_allergy_history"></span>
                            </td>
					  	</tr>
                        <tr>
							<td rowspan="4">家族史：</td>
							<td style="width:100px">父亲：</td>
                            <td colspan="5">
                                <span>${phPeopleBasicInfo.familyHistoryFather}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.familyHistoryFatherOther}</span>
                            </td>
					  	</tr>
                        <tr>
							<td>母亲：</td>
                            <td colspan="5">
                                <span>${phPeopleBasicInfo.familyHistoryMum}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.familyHistoryMumOther}</span>
                            </td>
					  	</tr>
                        <tr>
							<td>兄弟姐妹：</td>
                            <td colspan="5">
                                <span>${phPeopleBasicInfo.familyHistoryBrothers}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.familyHistoryBrothersOther}</span>
                            </td>
					  	</tr>
                        <tr>
							<td>子女：</td>
                            <td colspan="5">
                                <span>${phPeopleBasicInfo.familyHistoryChildren}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.familyHistoryChildrenOther}</span>
                            </td>
					  	</tr>
                        <tr>
                        <td class="inputLabel" colspan="2">遗传病史：</td>
                            <td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.isGeneticHistory}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.geneticHistoryNames}</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputLabel" colspan="2">残疾情况：</td>
                            <td width="80%" colspan="5">
                                <span>${phPeopleBasicInfo.disabilityStatus}</span>&nbsp;&nbsp;<span>${phPeopleBasicInfo.disabilityStatusOther}</span>
                            </td>
                        </tr>
                    </table>
				<br>
			</div>
		</form>
  	</body>
</html>
