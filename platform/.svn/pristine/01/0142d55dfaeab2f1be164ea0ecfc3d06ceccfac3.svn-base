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
		<title>编辑开通的业务</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>
		
		<!-- ecTable -->
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
		
		<!-- util 工具 js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
  		<script type="text/javascript">
  			var $j = jQuery.noConflict(); 
  			window.addEvent('domready', function(){
				new FormCheck('form_add',{
					display:{
						showErrors:1
					}
				});
				
				Util2.selectUsersByDistrictAndOrgan("amDoctorHelperUser.userName","amDoctorHelperUser.userName","amDoctorHelperUser.userId");
				Util2.selectUsersByDistrictAndOrgan("amExtensionUser.userName","amExtensionUser.userName","amExtensionUser.userId");
				Util2.selectUsersByDistrictAndOrgan("pmDoctorHelperUser.userName","pmDoctorHelperUser.userName","pmDoctorHelperUser.userId");
				Util2.selectUsersByDistrictAndOrgan("pmExtensionUser.userName","pmExtensionUser.userName","pmExtensionUser.userId");
				
				$("doctorInfo.doctorName").addEvent("click",function(){
			 		var page2page = "text=doctorInfo.doctorName&hidden=doctorInfo.id";
			 		environment.dialog.open({
						url : "<s:url namespace="/operationmanage" action="selectSingleDoctorMain" includeParams="true"/>?" + page2page,
						width : window.getCoordinates().width-200,
						height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择医生'
					});
				});
				
				//选择医疗机构
	  			$("hospitalName").addEvent("click",function() {
			 		var page2page = "text=hospitalName&hidden=hospitalId";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/familydoctor/servicepackage/hospital_select_single_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择机构'
					});
				});
				
			});
			
			// 保存
			function doSave() {
				$('save').click();
			}
			// 返回
		    function doBack(){
		    	var action = '<s:url namespace="/operationmanage" action="openClinicInfoList" includeParams="true"/>';
			 	window.location.href = action ;
			}
			
		  //上传图片
			function img_cli(obj){
				$j(obj).next().click();
			}
			//-----------------------------------
			function handleFiles(obj){ 
				var src = $j(obj).get(0).files[0];
				var imgType = src.name.split('.');
				imgType = imgType[imgType.length-1];//返回后缀名，以兼容部分移动端浏览器
				if(imgType == 'jpg'){
					imgType = 'jpeg';
				}
				if(!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')){
					ale('请选择图片文件');
					return false;
				}

			// 创建 FileReader 对象 并调用 render 函数来完成渲染.  
				var reader = new FileReader();  
			// 绑定load事件自动回调函数  
				var imgData = '';
				reader.onload = function(e){
				if(e.target.result.substring(5,10) != 'image'){
					var imgDataArr = e.target.result.split('base64');
					imgData = imgDataArr[0] + "image/"+imgType+";base64"+imgDataArr[1];
					render(obj,imgData);
				}else{
					render(obj,e.target.result); 
				}     
			};  
			  // 读取文件内容  
			reader.readAsDataURL(src);
			$j(obj).next().show();
			$j(obj).prev().hide();
			}
			//参数，最大高度  
			var MAX_HEIGHT = 70;  
			// 渲染  
			function render(obj,src){  
				var image = new Image();  
				image.onload = function(){  
				    var canvas = $j(obj).next().get(0);
				    var x = image.width;
				    var y = image.height;
				
				    if(image.height > MAX_HEIGHT) {  
					      // 宽度等比例缩放 *=  
					      image.width *= MAX_HEIGHT / image.height;  
					      image.height = MAX_HEIGHT;  
				    }
				    var ctx = canvas.getContext("2d");  // 获取 canvas的 2d 环境对象,可以理解Context是管理员，canvas是房子 
						ctx.clearRect(0, 0, canvas.width, canvas.height);// canvas清屏
				//    canvas.width = image.width;  // 重置canvas宽高
				//    canvas.height = image.height;
				    	canvas.width = 130;  // 重置canvas宽高
				    	canvas.height = 130;    
				
				    ctx.drawImage(image, 0, 0,x,y,0,0,130,130);  // 将图像绘制到canvas上
			  };  
			  image.src = src;  // 记住必须先绑定事件，才能设置src属性，否则会出同步问题。
			  setTimeout(function(){
				  var canvas = $j(obj).next();
				    // 获取Base64编码后的图像数据，格式是字符串  
				    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
				    var dataurl = canvas.get(0).toDataURL("image/png"); 
				    // 为安全 对URI进行编码  
				    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
				    var imagedata =  encodeURIComponent(dataurl);
					$j(obj).next().next().val(imagedata);
				},2000);
			}
  		</script>
  	</head>
  
  	<body>
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<s:if test="openClinicInfo.state!='已完成'">
							<span><a href="javascript:doSave();" class="pop_button_blue">更新</a></span>
						</s:if>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
  		
		<form id="form_add" class="form" action='<s:url namespace="/operationmanage" action="updateingOpenClinicInfo" includeParams="true"/>' method="post">
			<input type="hidden" name="openClinicInfo.id" id="openClinicInfo.id" value="${openClinicInfo.id}">
			<input type="hidden" name="openClinicInfo.imagePath" value="${fdServicePackage.imagePath }">
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#B7C6DD">
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开放日期<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.openClinicDate" id="openClinicInfo.openClinicDate" value="${openClinicInfo.openClinicDate}" type="text" class="input1 Wdate validate['required']" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开展业务类型<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'线上讲座':'线上讲座','线下讲座':'线下讲座','文章约稿':'文章约稿'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="openClinicInfo.businessType" value="openClinicInfo.businessType" />
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开通业务描述：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.openContent" id="openClinicInfo.openContent" type="text" class="validate['length[50]'] text3" size="40" value="${openClinicInfo.openContent}">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">医生<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="doctorInfo.id" id="doctorInfo.id" type="hidden" class="text3" size="40" value="${doctorInfo.id}">
						<input name="doctorInfo.doctorName" id="doctorInfo.doctorName" type="text" class="text3" size="40" value="${doctorInfo.doctorName}" readonly="readonly">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">开放时间段<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input type="checkbox" onclick="doCheckedAm(this);" name="openClinicInfo.timeSlot" id="openClinicInfo.timeSlotAm" value="上午">上午&nbsp;&nbsp;<input type="checkbox" onclick="doCheckedPm(this);" name="openClinicInfo.timeSlot" id="openClinicInfo.timeSlotPm" value="下午">下午
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">是否有效<span class="text4">*</span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'Y':'是','N':'否'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
						  		  name="openClinicInfo.isEffective" value="openClinicInfo.isEffective" />
					</td>
				</tr>
				
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">计划开始时间<span class="text4"></span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.planStartTime" id="openClinicInfo.planStartTime" value="${openClinicInfo.planStartTime}" type="text" class="input1 Wdate validate[]" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">计划结束时间<span class="text4"></span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.planEndTime" id="openClinicInfo.planEndTime" value="${openClinicInfo.planEndTime}" type="text" class="input1 Wdate validate[]" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
				</tr>
				
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">实际开始时间<span class="text4"></span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.actualStartTime" id="openClinicInfo.actualStartTime" value="${openClinicInfo.actualStartTime}"  type="text" class="input1 Wdate validate[]" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">实际结束时间<span class="text4"></span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.actualEndTime" id="openClinicInfo.actualEndTime" value="${openClinicInfo.actualEndTime}" type="text" class="input1 Wdate validate[]" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
					</td>
				</tr>
				
				
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">上午医生助手：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="amDoctorHelperUser.userId" id="amDoctorHelperUser.userId" type="hidden" class="text3" size="40" value="${amDoctorHelperUser.userId}">
						<input name="amDoctorHelperUser.userName" id="amDoctorHelperUser.userName" type="text" class="text3" size="40" value="${amDoctorHelperUser.userName}" readonly="readonly">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">上午推广者：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="amExtensionUser.userId" id="amExtensionUser.userId" type="hidden" class="text3" size="40" value="${amExtensionUser.userId}">
						<input name="amExtensionUser.userName" id="amExtensionUser.userName" type="text" class="text3" size="40" value="${amExtensionUser.userName}" readonly="readonly">
					</td>
				</tr>
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">下午医生助手：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="pmDoctorHelperUser.userId" id="pmDoctorHelperUser.userId" type="hidden" class="text3" size="40" value="${pmDoctorHelperUser.userId}">
						<input name="pmDoctorHelperUser.userName" id="pmDoctorHelperUser.userName" type="text" class="text3" size="40" value="${pmDoctorHelperUser.userName}" readonly="readonly">
					</td>
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">下午推广者：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="pmExtensionUser.userId" id="pmExtensionUser.userId" type="hidden" class="text3" size="40" value="${pmExtensionUser.userId}">
						<input name="pmExtensionUser.userName" id="pmExtensionUser.userName" type="text" class="text3" size="40" value="${pmExtensionUser.userName}" readonly="readonly">
					</td>
				</tr>
				
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">酬劳：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input name="openClinicInfo.money" id="openClinicInfo.money" type="text" class="validate['length[50]'] text3" size="40" value="${openClinicInfo.money}">
					</td>
					
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">归属医疗机构：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<input type="text" id="hospitalName" value="${openClinicInfo.hospitalBasicInfo.hospitalLname}" class="validate['required','length[200]'] text3" width="70%">
						<input type="hidden" id="hospitalId" name="openClinicInfo.hospitalBasicInfo.id" value="${openClinicInfo.hospitalBasicInfo.id}" >
					</td>
				</tr>
				
				
				
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">状态<span class="text4"></span>：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE">
						&nbsp;&nbsp;
						<s:select list="#{'已下发':'已下发','已完成':'已完成','已取消':'已取消','已延迟':'已延迟'}" listKey="key" listValue="value" cssStyle="width:100px;" theme="simple"
								  name="openClinicInfo.state" value="openClinicInfo.state" />
					</td>
					
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">图片上传：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
						<img  width='130px' height='130px'  onclick='img_cli(this)' src='${pageContext.request.contextPath}/${openClinicInfo.imagePath }'/>
				        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
				        <canvas style="display:none;" width="130" height="130"></canvas>
				        <input type="hidden" name="openClinicInfo.imgBase64" value=""/>
					</td>
				</tr>
				
				<tr align="center" valign="middle" class="text3">
					<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>
					<td width="35%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
						&nbsp;&nbsp;
						<textarea name="openClinicInfo.remark" class="validate['length[1024]'] Added_textarea">${openClinicInfo.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
  	</body>
</html>
<script>
	function doCheckedAm(obj) {
		 if(obj.checked) {
		 	//alert("value: " + obj.value);
			$('amDoctorHelperUser.userName').disabled="";
			$('amExtensionUser.userName').disabled="";
			$('amDoctorHelperUser.userId').disabled="";
			$('amExtensionUser.userId').disabled="";
		 }
		 else {
			$('amDoctorHelperUser.userName').disabled="disabled";
			$('amExtensionUser.userName').disabled="disabled";
			$('amDoctorHelperUser.userId').disabled="disabled";
			$('amExtensionUser.userId').disabled="disabled";
		 }
	}
	function doCheckedPm(obj) {
		if(obj.checked) {
			$('pmDoctorHelperUser.userName').disabled="";
			$('pmExtensionUser.userName').disabled="";
			$('pmDoctorHelperUser.userId').disabled="";
			$('pmExtensionUser.userId').disabled="";
		 }
		 else {
			$('pmDoctorHelperUser.userName').disabled="disabled";
			$('pmExtensionUser.userName').disabled="disabled";
			$('pmDoctorHelperUser.userId').disabled="disabled";
			$('pmExtensionUser.userId').disabled="disabled";
		 }
	}
	
	function initCheckbox() {
		$('amDoctorHelperUser.userName').disabled="disabled";
		$('amExtensionUser.userName').disabled="disabled";
		$('amDoctorHelperUser.userId').disabled="disabled";
		$('amExtensionUser.userId').disabled="disabled";
		
		$('pmDoctorHelperUser.userName').disabled="disabled";
		$('pmExtensionUser.userName').disabled="disabled";
		$('pmDoctorHelperUser.userId').disabled="disabled";
		$('pmExtensionUser.userId').disabled="disabled";
		var timeSlotStr = "${openClinicInfo.timeSlot}";
		var timeSlotArray = timeSlotStr.split(", ");
		for (var i=0; i<timeSlotArray.length; i++) {
			if ("上午"==timeSlotArray[i]) {
				$('openClinicInfo.timeSlotAm').checked="checked";
				$('amDoctorHelperUser.userName').disabled="";
				$('amExtensionUser.userName').disabled="";
				$('amDoctorHelperUser.userId').disabled="";
				$('amExtensionUser.userId').disabled="";
			}
			if ("下午"==timeSlotArray[i]) {
				$('openClinicInfo.timeSlotPm').checked="checked";
				$('pmDoctorHelperUser.userName').disabled="";
				$('pmExtensionUser.userName').disabled="";
				$('pmDoctorHelperUser.userId').disabled="";
				$('pmExtensionUser.userId').disabled="";
			}
		}
	}
	
	//checkbox初始化
	initCheckbox();
	
</script>