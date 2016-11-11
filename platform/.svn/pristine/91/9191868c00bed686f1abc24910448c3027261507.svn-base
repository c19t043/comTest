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
  		
  		  		<!-- 富文本编辑器 -->
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rich_text.js"></script>
  		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.parse.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
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
				window.location.href = "${pageContext.request.contextPath}/medicalorgandbusiness/setpro/toList.action";
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
			/**
			*分配套餐
			*/
			function doRelMeal(){
				var page2page = "text=relName&hidden=mealID";  //把这个page2page传到另外一个页面
		 		environment.dialog.open({
					url : '${pageContext.request.contextPath}/medicalorgandbusiness/SetPro_select_main.jsp?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
					width : window.getCoordinates().width-300,
				    height : window.getCoordinates().height-50,
					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
					title : '选择套餐'
				});
			};
			$j(function(){
		  		//选择用户类型
	  			$("userTypeName").addEvent("click",function() {
			 		var page2page = "text=userTypeName&hidden=userTypeID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '<s:url namespace="/familydoctor/archivesInfo" action="toJumpUserType" includeParams="true"/>?' + page2page, 
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择单个数据库表'
					});
				});
				
		  		//选择上级产品
	  			$("parentName").addEvent("click",function() {
			 		var page2page = "text=userTypeName&hidden=userTypeID";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '${pageContext.request.contextPath}/medicalorgandbusiness/SetPro_pro_select_main.jsp?' + page2page, 
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择单个数据库表'
					});
				});
		  		
		  		
		  		$j("#parentID").bind({
		  			click:function(){
		  				var parentID = $j("#parentID").val();
		  				if(parentID==""){
		  					//清空父id，关联套餐和机构清空
		  					$j("#distributeMeal").show();
		  					
		  					$j("#relOrgIDs").val("");
		  					$j("#relOrgNames").val("");
		  					$j("#relMealIDs").val("");
		  					$j("#relMealNames").val("");
		  				}else{
		  					$j.ajax({
		  						url:"${pageContext.request.contextPath}/medicalorgandbusiness/setpro/getRelMealAndOrg.action",
		  						type:"POST",
		  						async:false,
		  						data:{
		  							"organSetPro.id":parentID
		  						},
		  						success:function(result){
		  							if(result!=null){
		  								var editFlag = false;
		  								
		  								if(result.hospitalBasicInfo!=null&&result.organSetMeal!=null){
		  									editFlag = true;
		  								}
		  								
		  								//设置父产品,关联父产品的套餐和机构
		  								if(editFlag){
		  									$j("#distributeMeal").hide();
		  									$j("#relOrgIDs").val(result.hospitalBasicInfo.id);
			  			  					$j("#relOrgNames").val(result.hospitalBasicInfo.hospitalLname);
		  									$j("#relMealIDs").val(result.organSetMeal.id);
			  			  					$j("#relMealNames").val(result.organSetMeal.packageName);
		  								}
		  							}
		  						}
		  					});
		  				}
		  			}
		  		});
		  		
	  			//描述
	  			hf_ueditor('node_edit','noteContent');
		  		
	  			<c:if test="${organSetPro.parentOrganSetPro.id!=null}">
	  				$j("#distributeMeal").hide();
	  			</c:if>
			});
			
			
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
		<form id="form_handle" name="form_handle" action="<s:url namespace="/medicalorgandbusiness/setpro" action="saveOrUpdate" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
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
							<td class="inputLabel">上级产品:</td>
							<td width="30%" colspan="1">
								<input type="hidden" id="parentID" name="organSetPro.parentOrganSetPro.id" value="${organSetPro.parentOrganSetPro.id }" class="text3">
								<input type="text" id="parentName" readonly="readonly" value="${organSetPro.parentOrganSetPro.proName}" 
								class="text3">
							</td> 
							<td class="inputLabel">价格类型:<span class="text4">*<span></td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="text3" list="#{'':'-请选择-','0':'实时计算','1':'统一定价'}" listKey="key" listValue="value" 
									theme="simple" name="organSetPro.priceType" value="organSetPro.priceType" class="validate['required']"/>
							</td> 
					  	</tr>
						<tr>
							<td class="inputLabel">产品名称:<span class="text4">*<span></td>
							<td width="30%">
								<input type="hidden" name="organSetPro.id" value="${organSetPro.id}">
								<input type="text" id="packageName" name="organSetPro.proName" value="${organSetPro.proName }"
								class="validate['required','length[200]'] text3" width="70%">
							</td> 
							
							<td class="inputLabel">产品价格：<span class="text4">*<span></td>
							<td width="30%" >
								<input type="text" value="${organSetPro.proPrice }" name="organSetPro.proPrice" 
									class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">服务时长:</td>
							<td width="30%">
								<input type="text" value="${organSetPro.serviceTimeLength }" name="organSetPro.serviceTimeLength" class="text3">
								<select id="serviceTimeUnit" class="text3" style="width:70px;" name="organSetPro.serviceTimeUnit">
									<option value="天" <c:if test="${organSetPro.serviceTimeUnit=='天' }">selected="selected"</c:if>>天</option>
									<option value="月" <c:if test="${organSetPro.serviceTimeUnit=='月' }">selected="selected"</c:if>>月</option>
								</select>
							</td> 
							
							<td class="inputLabel">服务次数：</td>
							<td width="30%" >
								<input type="text" value="${organSetPro.serviceTimes }" name="organSetPro.serviceTimes" class="text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">最小月龄：</td>
							<td width="30%" >
								<input type="text" value="${organSetPro.minMonthAge }" name="organSetPro.minMonthAge" class="text3">
							</td>
							
							<td class="inputLabel">最大月龄:</td><span class="text4">*<span>
							<td width="30%">
								<input type="text" value="${organSetPro.maxMonthAge }" name="organSetPro.maxMonthAge" class="validate['required','length[5]'] text3">
							</td> 
					  	</tr> 
					  	<tr>
							<td class="inputLabel">是否可用:<span class="text4">*<span></td>
							<td width="30%">
								<select id="isEnable" class="text3" style="width:70px;" name="organSetPro.isEnable">
									<option value="Y" <c:if test="${organSetPro.isEnable=='Y' }">selected="selected"</c:if>>Y</option>
									<option value="N" <c:if test="${organSetPro.isEnable=='N' }">selected="selected"</c:if>>N</option>
								</select>
							</td> 
							
							<td class="inputLabel">是否可线下支付：</td>
							<td width="30%" >
								<select  class="text3" style="width:70px;" name="organSetPro.isOfflinePay">
									<option value="Y" <c:if test="${organSetPro.isOfflinePay=='Y' }">selected="selected"</c:if>>Y</option>
									<option value="N" <c:if test="${organSetPro.isOfflinePay=='N' }">selected="selected"</c:if>>N</option>
								</select>
							</td>
					  	</tr> 
					  	<tr>
					  	
							<td class="inputLabel">下架截止时间:</td>
							<td width="30%">
								<input type="text" value="${organSetPro.offLineTime }" 
								onfocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});"
								name="organSetPro.offLineTime" class="text3">
							</td> 

							<td class="inputLabel">用户类型：<span class="text4">*<span></td>
							<td width="30%" >
								<input type="hidden" id="userTypeID" value="${organSetPro.userType.id }" name="organSetPro.userType.id" class="text3">
								<input type="text" readonly="readonly" id="userTypeName"  value="${organSetPro.userType.typeName }" 
									class="validate['required','length[200]'] text3">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">关联套餐/关联机构：<span class="text4">*<span></td>
							<td width="30%" >
								<input type="hidden" id="middleID" value="${organSetPro.middleID}">
								<input type="hidden" id="relMealIDs" value="${organSetPro.organSetMeal.id}" name="organSetPro.organSetMeal.id">
								<input type="text" readonly="readonly" id="relMealNames" value="${organSetPro.organSetMeal.packageName }"
								class="validate['required','length[200]'] text3">
								<input type="hidden" id="relOrgIDs" value="${organSetPro.hospitalBasicInfo.id}" name="organSetPro.hospitalBasicInfo.id">
								<input type="text" readonly="readonly" id="relOrgNames" value="${organSetPro.hospitalBasicInfo.hospitalLname}">
								<a href="javascript:void();" id="distributeMeal" onclick="doRelMeal()">分配套餐</a>
							</td>
							
							<td class="inputLabel">产品类型：<span class="text4">*<span></td>
							<td width="30%" >
								<select id="proType" name="organSetPro.proType" class="text3" style="width:70px;">
									<option value="0" <c:if test="${organSetPro.proType=='0' }">selected="selected"</c:if>>儿保套餐</option>
									<option value="1" <c:if test="${organSetPro.proType=='1' }">selected="selected"</c:if>>其他</option>
								</select>								
							</td>
					  	</tr>
						 <tr>
					  		<input type="hidden" name="organSetPro.proImg" value="${organSetPro.proImg}">
					  		<td class="inputLabel">图片上传：</td>
							<td width="30%" >
								<img  width='130px' height='130px'  onclick='img_cli(this)' src='${pageContext.request.contextPath}/${organSetPro.proImg}'/>
						        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
						        <canvas style="display:none;" width="130" height="130"></canvas>
						        <input type="hidden" name="organSetPro.imgBase64" />
							</td>
							
							<td class="inputLabel">描述：</td>
							<td width="30%" >
								<span id="node_edit" class="node_edit">点击编辑</span>
								<textarea id="noteContent" readonly="readonly" rows="10" cols="50" name="organSetPro.description" class="text3">${organSetPro.description }</textarea>
								<%-- <input type="text" value="${organSetPro.description }" name="organSetPro.description" class="text3"> --%>
							</td>
						</tr>
					</table>
				<br>
			</div>
		</form>
  	</body>
</html>
