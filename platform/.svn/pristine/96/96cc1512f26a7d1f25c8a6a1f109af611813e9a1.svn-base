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
		<title>家庭医生服务订单信息录入</title>
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
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/familydoctor/fdServiceOrder/getFdServiceOrderList.action";
			}
			function doSubmit() {
				confirmMsg("确定提交?",function(tp){
					if(tp=='ok'){
						$('form_handle').action="<s:url namespace="dataEntry" action="submitfdServiceItems" includeParams="true"/>";
						$('save').click();
					}
				});
			}
			function download(exchangeId){
				window.location.href = '<s:url action="downloadAttachment" includeParams="true"/>' + '?exchange.exchangeId=' + exchangeId;
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
				    	canvas.width = 320;  // 重置canvas宽高
				    	canvas.height = 160;    
				
				    ctx.drawImage(image, 0, 0,x,y,0,0,320,160);  // 将图像绘制到canvas上
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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/familydoctor/fdServiceOrder" action="updateFdServiceOrder" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="fdServiceOrder.id" value="${fdServiceOrder.id}">
			
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
							<td class="inputLabel">订单编号：</td>
							<td width="30%">
								<input type="text" readonly="readonly" name="fdServiceOrder.orderNum" value="${fdServiceOrder.orderNum}" class="validate['required','length[20]'] text3">
								<span class="text4">*</span>
							</td>
							
							<td class="inputLabel">下单时间：</td>
							<td width="30%">
								<input type="text" readonly="readonly" name="fdServiceOrder.submitTime" value="${fdServiceOrder.submitTime}" class="validate['required','length[20]'] text3">
								<span class="text4">*</span>
							</td>
					  	</tr>
					  	
					  	<tr>
							<td class="inputLabel">下单金额：</td>
							<td width="30%" >
								<input type="text" readonly="readonly" name="fdServiceOrder.totalPrice" value="${fdServiceOrder.totalPrice}" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">更新时间：</td>
							<td width="30%" >
								<input type="text" readonly="readonly" name="fdServiceOrder.updateTime" value="${fdServiceOrder.updateTime}" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	
					  	<tr>
							<td class="inputLabel">支付方式：</td>
							<td width="30%" >
								<input type="text" readonly="readonly" name="fdServiceOrder.payMethod" value="${fdServiceOrder.payMethod}" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">使用的余额数：</td>
							<td width="30%" >
								<input type="text" readonly="readonly" name="fdServiceOrder.useRemainBalance" value="${fdServiceOrder.useRemainBalance}" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	
					  	<tr>
							<td class="inputLabel">提示信息：</td>
							<td width="30%" >
								<input type="text" readonly="readonly" name="fdServiceOrder.remindInfo" value="${fdServiceOrder.remindInfo}" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">订单状态：</td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="text3" list="#{'等待支付':'等待支付','已付款':'已付款'}" listKey="key" listValue="value" 
									theme="simple" name="fdServiceOrder.orderStatus" value="fdServiceOrder.orderStatus"/>
								<span class="text4">*</span>
							</td>
					  	</tr>
					  	
					  	<tr>
							<td class="inputLabel">使用的积分数：</td>
							<td width="30%" >
								<input type="text" readonly="readonly" name="fdServiceOrder.usePoints" value="${fdServiceOrder.usePoints}" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	
					  	<tr>
					  	
							<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>
							<td width="85%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">
								<textarea name="fdServiceOrder.comments" class="validate['length[200]'] Added_textarea">${fdServiceOrder.comments}</textarea>
							</td>
					  	</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
