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
		<title>儿童健康检查信息录入</title>
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
			});
			function doHandle() {
				$('save').click();
			}
			function doBack(){
				window.location.href = '<s:url action="managementOrgList" includeParams="true"/>'  +'?condition.form_code=ManagementOrg';
			}
			function doSubmit() {
				confirmMsg("确定提交?",function(tp){
					if(tp=='ok'){
						$('form_handle').action="<s:url namespace="dataEntry" action="submitManagementOrg" includeParams="true"/>";
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
						<c:if test="${managementOrg.status != 3}">
							<span><a href="javascript:doSubmit();" class="pop_button_red">提交</a></span>
						</c:if>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
						<span><a href="javascript:help();" title="帮助"><font style="font-size: 12px;color:#50657F ">帮助(?)</font></a></span>
					</div>
				</div>
			</div>
		</div>
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="dataEntry" action="addingManagementOrg" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="managementOrg.id" value="${managementOrg.id}">
			<input type="hidden" id="status" name="managementOrg.status" value="${managementOrg.status}">
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
							<td class="inputLabel">机构名称：</td>
							<td width="30%">
								<input type="text" name="managementOrg.org_name" value="${managementOrg.org_name}" class="validate['required','length[30]'] text3" width="70%">
								<span class="text4">*</span>
							</td>
							<td class="inputLabel">机构级别：</td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="validate['required'] text3" list="#{'国家级':'国家级','省级':'省级','市级':'市级','县级':'县级'}" listKey="key" listValue="value" 
									headerKey="" headerValue="--请选择--" theme="simple" name="managementOrg.org_level" value="managementOrg.org_level"/>
								<span class="text4">*</span>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">机构法人：</td>
							<td width="30%">
								<input type="text" name="managementOrg.org_legal" value="${managementOrg.org_legal}" class="validate['required','length[20]'] text3">
								<span class="text4">*</span>
							</td>
							<td class="inputLabel">主管部门：</td>
							<td width="30%">
								<input type="text" name="managementOrg.department" value="${managementOrg.department}" class="validate['length[30]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">联系电话：</td>
							<td width="30%">
								<input type="text" name="managementOrg.phone" value="${managementOrg.phone}" class="validate['required','phone','length[20]'] text3">
								<span class="text4">*</span>
							</td>
							<td class="inputLabel">传真：</td>
							<td width="30%">
								<input type="text" name="managementOrg.fax" value="${managementOrg.fax}" class="validate['fax','length[20]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">电子邮箱：</td>
							<td width="30%">
								<input type="text" name="managementOrg.email" value="${managementOrg.email}" class="validate['email','length[30]'] text3">
							</td>
							<td class="inputLabel">邮政编码：</td>
							<td width="30%">
								<input type="text" name="managementOrg.postcode" value="${managementOrg.postcode}" class="validate['number','length[10]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">编制：</td>
							<td width="30%">
								<input type="text" name="managementOrg.prepared_by" value="${managementOrg.prepared_by}" class="validate['length[20]'] text3" width="70%">
							</td>
							<td class="inputLabel">在岗人数：</td>
							<td width="30%">
								<input type="text" name="managementOrg.in_post_numbers" value="${managementOrg.in_post_numbers}" class="validate['digit','length[5]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">通讯地址：</td>
							<td width="30%" colspan="3">
								<input type="text" name="managementOrg.mailing_address" value="${managementOrg.mailing_address}" size="60" class="validate['required','length[50]'] text3" width="70%">
								<span class="text4">*</span>
							</td>
					  	</tr>
					  	<tr>
							<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>
							<td width="85%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
								<textarea name="managementOrg.remark" class="validate['length[2000]'] Added_textarea">${managementOrg.remark}</textarea>
							</td>
					  	</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
