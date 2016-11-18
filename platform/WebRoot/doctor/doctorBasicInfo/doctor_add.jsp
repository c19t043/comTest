<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>医生注册信息采集</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/res/css/style.css"	rel="stylesheet" type="text/css" />
		
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
  		<!-- 富文本编辑器 -->
  		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.parse.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rich_text.js"></script>
  		<style type="text/css">
  				/*富文本编辑器样式*/
			.ueditor{
				display:inline-block;
			  display: inline-block;
			  width: 70px;
			  height: 30px;
			  line-height: 30px;
			  background: #FFAF19;
			  text-align: Center;
			  position: absolute;
			  color:#fff;
			  font-weight:bold;
			  border-radius: 4px;
			  cursor:pointer;
			}
			.ueditor:hover{
				background: rgb(231, 151, 0);
			}
			.ueditor_a{
				top: 465px;
				right:20px;
			}
			.ueditor_b{
				top: 465px;
				right: 110px
			}
  			.node_edit{
			    width: 100px;
			    height: 22px;
			    line-height: 22px;
			    cursor: pointer;
			    border: 2px solid rgb(74, 139, 194);
			    border-radius: 5px;
			    display: inline-block;
			    text-align: center;
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
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		<form id="form_handle" name="form_handle" action="<s:url namespace="/doctor/doctorManager" action="doctorDataGather" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="doctorInfo.id" value="${doctorInfo.id }">
			<input type="hidden" name="action" value="saveOrUpdateDoctorBasicInfo">
			<input type="hidden" name="pageFlag" value="${pageFlag}">
			<div class="eXtremeTable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left" valign="top">
							<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
						</td>
					</tr>
				</table>
					<table width="100%" class="tableRegion2">
						<tr style="text-align: center;font-weight: bold;font-size: 24px;" class="navigation">
							<td id="baseInfo">基本信息</td>
							<td id="cardInfo">身份证明</td>
							<td id="lifeInfo">生活信息</td>
							<td id="estimateInfo">医生评价</td>
							<c:if test="${pageFlag == 'verify' }">
								<td id="flowInfo">流程审核</td>
							</c:if>
						</tr>
					</table>
					<!-- 基础信息 -->
					<table width="100%" class="tableRegion2 baseInfo showFlag">
						<tr>
							<td width="8%" height="41" align="right">姓名：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<input name="doctorInfo.doctorName"  type="text" 
								class="validate[<c:if test="${pageFlag != 'verify' }">'required',</c:if>'length[200]'] text3"  value="${doctorInfo.doctorName }"/>	
									
							</td>
							<td width="8%" height="41" align="right">性别：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<s:select list="#{'男':'男','女':'女' }"
								listKey="key" listValue="value" theme="simple"
								name="doctorInfo.doctorSex" value="doctorInfo.doctorSex" cssStyle="width:150px"
								></s:select>
							</td>
						</tr>
						<tr>
							<td width="8%" height="41" align="right">手机号：<span class="text4">*</span></td>
							<td width="12%" align="left" >
								<input name="doctorInfo.doctorPhone"  type="text" 
								class="validate[<c:if test="${pageFlag != 'verify' }">'required',</c:if>'length[200]'] text3"  value="${doctorInfo.doctorPhone }"/>		
							</td>
							<td width="8%" height="41" align="right">医生类型：<span class="text4">*</span></td>
							<td width="12%" align="left" colspan="3">
								<s:select list="#{'康优儿保医生':'康优儿保医生','康优儿科医生':'康优儿科医生' }"
								listKey="key" listValue="value" theme="simple"
								name="doctorInfo.doctorType" value="doctorInfo.doctorType" cssStyle="width:150px"
								></s:select>		
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">医院：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<select name="doctorInfo.doctorEmployer" style="width:150px;">
									<c:forEach items="${hospitalBasicInfoList }" var="v" >
										<option value="${v.hospitalLname}" <c:if test="${doctorInfo.doctorEmployer == v.hospitalLname }">selected=selected</c:if> >${v.hospitalLname}</option>
									</c:forEach>
								</select>
							</td>
							<td width="8%" height="41" align="right">科室：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<s:select 
								list="#{'儿科':'儿科','妇产科':'妇产科','儿童保健科':'儿童保健科','内科':'内科','全科':'全科','中医推拿':'中医推拿','心理科':'心理科','乳腺科':'乳腺科' }"
								listKey="key" listValue="value" theme="simple"
								name="doctorInfo.department" value="doctorInfo.department" cssStyle="width:150px"
								></s:select>	
							</td>
						</tr>
						<tr>
							<td width="8%" height="41" align="right">职称：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<select name="doctorInfo.doctorTitle" style="width:150px;">
									<c:forEach items="${positionList }" var="v" >
										<option value="${v.name}" <c:if test="${doctorInfo.doctorTitle == v.name }">selected=selected</c:if> >${v.name}</option>
									</c:forEach>
								</select>
							</td>
							<td width="8%" height="41" align="right">临床经验：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<input name="doctorInfo.clinicalExperience"  type="text" 
								class="validate[<c:if test="${pageFlag != 'verify' }">'required',</c:if>'length[200]'] text3"  value="${doctorInfo.clinicalExperience}"/>年		
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">主专业方向：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<select name="doctorInfo.major.id" id="firstMajorIds" style="width:150px;">
									<c:forEach items="${firstMajors }" var="v" >
										<option value="${v.id}" <c:if test="${doctorInfo.major.id == v.id }">selected=selected</c:if> >${v.major}</option>
									</c:forEach>
								</select>
							</td>
							<td width="8%" height="41" align="right">亚专业方向：</td>
							<td width="12%" align="left">
								<input  type="text"  id="secondMajorIds" readonly="readonly" class="text3" style="width:100%;">
								<input  type="hidden" id="secondMajorIds_ID" name="doctorInfo.secondMajorIds" 
										class="text3" value="${doctorInfo.secondMajorIds }">
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">病种：</td>
							<td width="12%" align="left" colspan="3">
								<input  type="text" id="thirdMajorIds" readonly="readonly" class="text3" style="width:100%;">
								<input  type="hidden" id="thirdMajorIds_ID" name="doctorInfo.thirdMajorIds"
										class="text3" value="${doctorInfo.thirdMajorIds }">
							</td>
					  	</tr>
					  	<tr>
					  		<td width="8%" height="41" align="right">头像：</td>
							<td width="12%" align="left">
								<img  style="height: 130px;width: 130px;"  onclick="img_cli(this)" src="${pageContext.request.contextPath}/${uploadDir}/${doctorInfo.doctorImage}"/>
						        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
						        <canvas style="display:none;" width="130" height="130"></canvas>
						        <input type="hidden" name="doctorInfo.imgBase64" id="imgBase64" value=""/>
							</td>
							<td width="8%" height="41" align="right">个人简介：</td>
							<td width="12%" align="left">
								<span id="node_edit" class="node_edit">点击编辑</span>
								<textarea rows="10" cols="50" id="doctorComment" name="doctorInfo.doctorComment">${doctorInfo.doctorComment }</textarea>
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">银行卡开户行：</td>
							<td width="12%" align="left">
								<s:select list="#{'工商银行':'工商银行','建设银行':'建设银行','中国银行':'中国银行','农业银行':'农业银行','交通银行':'交通银行','成都银行':'成都银行','招商银行':'招商银行','邮政储蓄':'邮政储蓄','广发银行':'广发银行','中信银行':'中信银行','兴业银行':'兴业银行'}"
								listKey="key" listValue="value" theme="simple"
								name="doctorInfo.bankAccountName" value="doctorInfo.bankAccountName" cssStyle="width:150px"
								></s:select>
							</td>
							<td width="8%" height="41" align="right">银行卡卡号：</td>
							<td width="12%" align="left">
								<input  type="text"  name="doctorInfo.bankCard"
									class="validate['length[200]'] text3"
								value="${doctorInfo.bankCard }">
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">开通服务：</td>
							<td width="12%" align="left" colspan="3">
								<c:if test="${pageFlag == 'verify' }">
									<c:forEach items="${doctorServiceTypes }" var="v">
										<c:if test="${fn:contains(doctorInfo.serviceTypeIds,v.id) }">
											<div style="display: inline-block;">${v.serviceTypeName },</div>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${pageFlag != 'verify' }">
									<c:forEach items="${doctorServiceTypes }" var="v">
										<div style="display: inline-block;border: 1px solid black;" 
											onclick="addServiceType(this,'${v.id}')"
										>${v.serviceTypeName }</div>
									</c:forEach>
								</c:if>
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">推荐人:</td>
							<td width="12%" align="left">
								<input  type="text"  name="doctorInfo.recommendPhone"
									class="validate['length[200]'] text3" placeholder="推荐人电话"
								value="${doctorInfo.recommendPhone }">
							</td>
					  	</tr>
					</table>
					<!-- 身份证明 -->
					<table width="100%" class="tableRegion2 cardInfo showFlag">
						<tr>
							<td width="8%" height="41" align="right">身份证明：</td>
							<td width="12%" align="left">
								<input name="doctorInfo.idCardNum"  type="text" 
								class="validate['length[200]'] text3"  value="${doctorInfo.idCardNum }"/>		
							</td>
							<td width="8%" height="41" align="right">执业证号：<span class="text4">*</span></td>
							<td width="12%" align="left">
								<input name="doctorInfo.idCard"  type="text" 
								class="validate[<c:if test="${pageFlag != 'verify' }">'required',</c:if>'length[200]'] text3"  value="${doctorInfo.idCard }"/>		
							</td>
						</tr>
						<tr>
							<td width="8%" height="41" align="right">执业证书：</td>
							<td width="12%" align="left" colspan="3">
								<c:forEach items="${doctorCardInfos }" var="v">
									<c:if test="${fn:contains(v.imgType,'执业')}">
										<img  style="height: 130px;width: 130px;"  onclick="img_cli(this)" src="${pageContext.request.contextPath}/${uploadDir}/${v.imgPath}"/>
								        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
								        <canvas style="display:none;" width="130" height="130"></canvas>
								        <input type="hidden" name="doctorInfo.imgBase64" id="imgBase64" value=""/>
									</c:if>
								</c:forEach>
								<c:if test="${pageFlag != 'verify' }">
								<img alt="增加图片" src="http://dev.qiyico.com/kybaby/main/images/menuicon/icon_add.png" onclick="addPictue(this,'执业证书')">
								</c:if>
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">资格证书：</td>
							<td width="12%" align="left" colspan="3">
								<c:forEach items="${doctorCardInfos }" var="v">
									<c:if test="${fn:contains(v.imgType,'资格')}">
										<img  style="height: 130px;width: 130px;"  onclick="img_cli(this)" src="${pageContext.request.contextPath}/${uploadDir}/${v.imgPath}"/>
								        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
								        <canvas style="display:none;" width="130" height="130"></canvas>
								        <input type="hidden" name="doctorInfo.imgBase64" id="imgBase64" value=""/>
									</c:if>
								</c:forEach>
								<c:if test="${pageFlag != 'verify' }">
								<img alt="增加图片" src="http://dev.qiyico.com/kybaby/main/images/menuicon/icon_add.png" onclick="addPictue(this,'资格证书')">
								</c:if>
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">职称证书：</td>
							<td width="12%" align="left" colspan="3">
								<c:forEach items="${doctorCardInfos }" var="v">
									<c:if test="${fn:contains(v.imgType,'职称')}">
										<img  style="height: 130px;width: 130px;"  onclick="img_cli(this)" src="${pageContext.request.contextPath}/${uploadDir}/${v.imgPath}"/>
								        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
								        <canvas style="display:none;" width="130" height="130"></canvas>
								        <input type="hidden" name="doctorInfo.imgBase64" id="imgBase64" value=""/>
									</c:if>
								</c:forEach>
								<c:if test="${pageFlag != 'verify' }">
								<img alt="增加图片" src="http://dev.qiyico.com/kybaby/main/images/menuicon/icon_add.png" onclick="addPictue(this,'职称证书')">
								</c:if>
							</td>
					  	</tr>
					 </table>
					 <!-- 生活证明 -->
					<table width="100%" class="tableRegion2 lifeInfo showFlag">
						<tr>
							<td width="8%" height="41" align="right">毕业学院：</td>
							<td width="12%" align="left">
								<input name="doctorLifeInfo.graduateSchool"  type="text" 
								class="validate['length[200]'] text3"  value="${doctorLifeInfo.graduateSchool}"/>		
							</td>
							<td width="8%" height="41" align="right">学位：</td>
							<td width="12%" align="left">
								<input name="doctorLifeInfo.degree"  type="text" 
								class="validate['length[200]'] text3"  value="${doctorLifeInfo.degree}"/>	
							</td>
						</tr>
						<tr>
							<td width="8%" height="41" align="right">院内月收入：</td>
							<td width="12%" align="left">
								<input name="doctorLifeInfo.hospitalMonthlIncome"  type="text" 
								class="validate['length[200]'] text3"  value="${doctorLifeInfo.hospitalMonthlIncome}"/>
							</td>
							<td width="8%" height="41" align="right">是否离异：</td>
							<td width="12%" align="left">
								<s:select list="#{'Y':'Y','N':'N' }"
								listKey="key" listValue="value" theme="simple"
								name="doctorLifeInfo.isDivorce" value="doctorLifeInfo.isDivorce" cssStyle="width:150px"
								></s:select>
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">特殊爱好：</td>
							<td width="12%" align="left">
								<input name="doctorLifeInfo.specialInterests"  type="text" 
								class="validate['length[200]'] text3"  value="${doctorLifeInfo.specialInterests}"/>
							</td>
					  	</tr>
					  	<tr>
							<td width="8%" height="41" align="right">其它：</td>
							<td width="12%" align="left">
								<textarea rows="10" cols="40" name="doctorLifeInfo.remark">${doctorLifeInfo.remark}</textarea>
							</td>
					  	</tr>
					 </table>
					  <!-- 医生评价 -->
					<table width="100%" class="tableRegion2 estimateInfo showFlag">
						<tr>
							<td width="8%" height="41" align="right">对医生的评价：</td>
							<td width="12%" align="left">
								<textarea rows="10" cols="40" name="doctorInfo.doctorImpression">${doctorInfo.doctorImpression}</textarea>		
							</td>
						</tr>
					 </table>
					<!-- 流程审批 -->
					<c:if test="${pageFlag == 'verify' }">
						<table width="100%" class="tableRegion2 flowInfo showFlag">
							<tr>
								<td width="8%" height="41" align="right">认证状态：<span class="text4">*</span></td>
								<td width="12%" align="left">
									<select name="doctorInfo.authentication" id="authentication" style="width:150px" >
										<option value=""></option>
										<option value="已申请"
										<c:if test="${doctorInfo.authentication == '已申请' }">
											selected="selected"
										</c:if>
										>已申请</option>
										<option value="已通过"
										<c:if test="${doctorInfo.authentication == '已通过' }">
											selected="selected"
										</c:if> 
										>通过</option>
										<option value="未通过"
											<c:if test="${doctorInfo.authentication== '未通过'}">
												selected="selected"
											</c:if> 
										>驳回</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="8%" height="41" align="right">审批状态：<span class="text4">*</span></td>
								<td width="12%" align="left">
									<select name="doctorInfo.flowStatus" id="doctorInfoflowStatus" style="width:150px" >
										<option value=""></option>
										<option value="已提交"
										<c:if test="${doctorInfo.flowStatus=='已提交'}">
											selected="selected"
										</c:if> 
										>已提交</option>
										<option value="已通过"
										<c:if test="${doctorInfo.flowStatus=='已通过'}">
											selected="selected"
										</c:if> 
										>通过</option>
										<option value="已驳回"
											<c:if test="${doctorInfo.flowStatus=='已驳回'}">
												selected="selected"
											</c:if> 
										>驳回</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="8%" height="41" align="right">审批意见：</td>
								<td width="12%" align="left">
									<textarea rows="10" cols="100" name="laterFlowRecord.remark">${laterFlowRecord.remark }</textarea>
								</td>
							</tr>
						</table>
					</c:if>
			</div>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath }/common/fileupload.js"></script>
		<script type="text/javascript">
  			var $j = jQuery.noConflict();
  			var addFlag = true;
  			$j(document).ready(function(){
  				//描述
	  			hf_ueditor('node_edit','doctorComment');
  				
  				fc = new FormCheck('form_handle',{
					display:{
					showErrors:1
					}
				});
  				<c:if test="${pageFlag != 'verify' }">
  				//选择亚专业
  				$j("#secondMajorIds").bind({
  					click:function(){
  						var firstMajorIds = $j("#firstMajorIds").val();
  						if(!firstMajorIds){
  							alert('请先选择主专业');return;
  						}
  		  		 		var page2page = "ID=secondMajorIds_ID&hidden=secondMajorIds";  //把这个page2page传到另外一个页面
  		  		 		page2page+="&doctorInfoID=${doctorInfo.id}";
  		  		 		page2page+="&majorID="+firstMajorIds;
  		  		 		environment.dialog.open({
  		  					url : '${pageContext.request.contextPath}/doctor/doctorBasicInfo/doctor_select_main.jsp?' + page2page, 
  		  					width : window.getCoordinates().width-300,
  		  				    height : window.getCoordinates().height-50,
  		  					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
  		  					title : '分配维护人'
  		  				});
  					}
  				});
  				//选择病种
  				$j("#thirdMajorIds").bind({
  					click:function(){
  						var secondMajorIds_ID = $j("#secondMajorIds_ID").val();
  						if(!secondMajorIds_ID){
  							alert('请先选择亚专业');return;
  						}
  		  		 		var page2page = "ID=thirdMajorIds_ID&hidden=thirdMajorIds";  //把这个page2page传到另外一个页面
  		  		 		page2page+="&doctorInfoID=${doctorInfo.id}";
  		  		 		page2page+="&majorID="+secondMajorIds_ID;
  		  		 		environment.dialog.open({
  		  					url : '${pageContext.request.contextPath}/doctor/doctorBasicInfo/doctor_select_main.jsp?' + page2page, 
  		  					width : window.getCoordinates().width-300,
  		  				    height : window.getCoordinates().height-50,
  		  					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
  		  					title : '分配维护人'
  		  				});
  					}
  				});
  				</c:if>
  				/*导航栏js*/
  				$j(".navigation td").bind({
  					click:function(){
  						$j(".navigation td").css("background-color","white");
						$j(".showFlag").hide();	
  						
  						$j(this).css("background-color","#055DC1");
  						var id = $j(this).attr("id");
  						$j("."+id).show();
  					}
  				});
  				/*初始化导航*/
  				<c:if test="${pageFlag == 'verify' }">
	  				$j("#flowInfo").click();
	  			</c:if>
	  			<c:if test="${pageFlag != 'verify' }">
  					$j("#baseInfo").click();
  				</c:if>
  				/*初始化亚专业*/
  				var secondMajor = "";
  				<c:forEach items="${secondMajors }" var="v" >
					<c:if test="${fn:contains(doctorInfo.secondMajorIds,v.id)}">secondMajor+="${v.major},";</c:if>
				</c:forEach>
	  			$j("#secondMajorIds").val(secondMajor);
	  			/*初始化病种*/
	  			var thirdMajorIds = "";
  				<c:forEach items="${thirdMajors }" var="v" >
					<c:if test="${fn:contains(doctorInfo.thirdMajorIds,v.id)}">thirdMajorIds+="${v.major},";</c:if>
				</c:forEach>
	  			$j("#thirdMajorIds").val(thirdMajorIds);
	  			/*返回错误信息*/
	  			<c:if test="${mes!=null && mes!='成功'}">
  					alert("${mes}");
  				</c:if>
  			});
  			/**
  			* 增加图片
  			*/
  			function addPicture(obj,name){
  				
  			}
  			/**
  			* 选择服务类型
  			*/
  			function addServiceType(obj,id){
  				
  			}
  			function validate(){
  				var optionVal = $j("#authentication").val();
				var doctorInfoflowStatus = $j("#doctorInfoflowStatus").val();
				if('已通过'==optionVal){
					if('已通过'!=doctorInfoflowStatus){
						alert('只有流程状态为通过状态,认证状态才能修改为通过');addFlag=false;
					}
				}else{
					addFlag=true;
				}
  			}
			function doHandle() {
				validate();
				if(!addFlag){return;}
				$j("#save").click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/doctor/doctorManager/getDoctorInfoList.action";
			}
  		</script>
  	</body>
</html>
