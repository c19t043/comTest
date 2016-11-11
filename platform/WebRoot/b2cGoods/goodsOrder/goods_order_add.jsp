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
		<title>商品订单详情</title>
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
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rich_text.js"></script>
  		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.parse.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
  		<style type="text/css">
  				
  		</style>
  		<script type="text/javascript">
  			var $j = jQuery.noConflict(); 
  			$j(function(){
  				//描述
	  			//hf_ueditor('node_edit','noteContent');
  			});
	  		window.addEvent('domready', function(){
	  			fc = new FormCheck('form_handle',{
						display:{
						showErrors:1
					}
				});
	  			
	  			$("serviceItemNames").addEvent("click",function() {
			 		var page2page = "text=serviceItemNames&hidden=serviceItemIds";  //把这个page2page传到另外一个页面
			 		environment.dialog.open({
						url : '<s:url namespace="/familydoctor/servicePackage" action="toJumpFdServiceItemsPage" includeParams="true"/>?' + page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
						width : window.getCoordinates().width-300,
					    height : window.getCoordinates().height-50,
						icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
						title : '选择单个数据库表'
					});
				});
			});
			function doHandle() {
				/* var names = $j("#serviceItemNames").val();
				var ids = $j("#serviceItemIds").val();
				if(names != "" && "," == names.charAt(names.length-1)){
					names = names.substring(0,names.lastIndexOf(","));
					$j("#serviceItemNames").val(names);
				}
				if(ids != "" && "," == ids.charAt(ids.length-1)){
					ids = ids.substring(0,ids.lastIndexOf(","));
					$j("#serviceItemIds").val(ids);
				} */
				$('save').click();
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/b2cGoods/goodsOrderManager/getGoodsOrderList.action";
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
			
			//添加分类统计信息
		 	function newSection(tempId,targetTbody){
		 		var goodSeedBaseInfoId = $j("#goodSeedBaseInfoId").val();
		   		if(goodSeedBaseInfoId==null||goodSeedBaseInfoId==0){
			       // showMsg("请先保存基本信息!");
			   	   // return false ;
		   		}
		 		var j = parseInt(document.getElementById(targetTbody).rows.length);
	 			var kv={"id":""};
				//kv['goodSeedBaseInfoId']=goodSeedBaseInfoId;
				newTRbyCopyTemplate(tempId,targetTbody,j,kv);
				updateProductTable(targetTbody);
		 	}
		 	function newTRbyCopyTemplate(tempid,tbodyid,rowj,kv){
				var otr=document.getElementById(tempid);
				var ntr=otr.cloneNode(true);
				ntr.removeAttribute("id");
				var nntr=document.getElementById(tbodyid).appendChild(ntr);
				for(var i=0;i<nntr.cells.length;i++){
					var childs=nntr.cells[i].childNodes;
					for(var j=0;j<childs.length;j++){
						var ttt=childs[j];
						//if(!ttt.tagName=="INPUT"){continue;}
						if(ttt.name){
							if(ttt.name.indexOf("[")>=0){
								var str=ttt.name.replace('[]','['+rowj+']');
								var opName=ttt.name.substring(ttt.name.indexOf(".")+1);
							
								for(var key in kv){
									if(key==opName){
										ttt.value=kv[key];
										break;
									}
								}
								ttt.name=str;
							}
						}
						if(ttt.id){
							var str=ttt.id.replace(/Index/g,rowj);
							ttt.id=str;
						}
						if(ttt.src){
							var str=ttt.src.replace(/Index/g,rowj);
							ttt.src=str;
						}
					}
				}
				return nntr;
			}
		 	function updateProductTable(targetTbody){
				var productTypeList = document.getElementById(targetTbody);
				var size = productTypeList.rows.length;
				for(var i=0;i<=size-1;i++){
					try{
						productTypeList.rows[i].cells[0].innerText=i+1;		
						productTypeList.rows[i].cells[0].align="center";
					}
					catch(e){}
				}	
			}
			function section_del(tbodyId,sonClassName){
				var ids = "";
				var count = 0;
				$j("#"+tbodyId+" tr").each(function(index){
					var selectObj = $j(this).find("td:eq(1)").children("input[type=checkbox]");
					if(selectObj.prop("checked")){
						count++;
						var sectionId = $j(this).find("td:eq(1)").children("input:eq(0)").val();
						if(sectionId != "" && sectionId != null){
							ids += sectionId + ",";
						}
					}
		 		});
				if(count == 0 ){
		 			showMsg("请选择要删除的行!");
		 			return;
		 		}
		 		if(ids != "" ){
		 			showMsg("包含已存在数据不能删除，请重新选择!");
		 			return;
		 		}
		 		confirmMsg("确定删除选中的行？",function(tp){
		 			if(tp=='ok'){
		 				$j("#"+tbodyId+" tr").each(function(index){
							var selectObj = $j(this).find("td:eq(1)").children("input[type=checkbox]");
							if(selectObj.attr("checked")){
								$j(this).remove();
							}
				 		});
		 				updateProductTable(tbodyId);
					}
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
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/b2cGoods/goodsOrderManager" action="saveOrUpdateB2cGoodsOrder" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="b2cGoodsOrder.id" value="${b2cGoodsOrder.id}">
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
							<td class="inputLabel" style="color: red;width: 15%"  >订单状态：</td>
							<td width="15%">
								<s:select cssStyle="width:100px;" cssClass="text3" listKey="key" listValue="value" 
									list="#{'':'-请选择-','未付款':'未付款','预付款':'预付款','已付款':'已付款','用户取消':'用户取消','待发货':'待发货','已发货':'已发货','已接收':'已接收','待评价':'待评价','已评价':'已评价'}" 
									theme="simple" name="b2cGoodsOrder.orderStatus" value="b2cGoodsOrder.orderStatus"/>
							</td>
							
							<td class="inputLabel" style="width: 15%" >订单编码：</td>
							<td style="width: 15%">
								${b2cGoodsOrder.orderNum }
							</td>
							
							<td class="inputLabel" style="width: 15%" >支付方式：</td>
							<td width="15%">
								${b2cGoodsOrder.payMethod }
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel" style="width: 15%" >订单总价：</td>
							<td width="15%">
								${b2cGoodsOrder.totalPrice }
							</td>
							
							<td class="inputLabel" style="width: 15%" >实付价：</td>
							<td width="15%">
								${b2cGoodsOrder.realPrice }
							</td>
							
							<td class="inputLabel" style="width: 15%" >余额支付：</td>
							<td width="15%">
								${b2cGoodsOrder.useRemainBalance }
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel" style="width: 15%" >运费：</td>
							<td width="15%">
								${b2cGoodsOrder.postage }
							</td>
							
							<td class="inputLabel" style="width: 15%" >订单类型：</td>
							<td width="15%">
								${b2cGoodsOrder.orderType }
							</td>
							
							<td class="inputLabel" style="width: 15%" >送货方式：</td>
							<td width="15%">
								${b2cGoodsOrder.b2cGoodsDeliver.dname }
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel" style="width: 15%" >收货人：</td>
							<td width="15%">
								${b2cGoodsOrder.bconsignee }
							</td>
							
							<td class="inputLabel" style="width: 15%" >收货电话：</td>
							<td width="15%">
								${b2cGoodsOrder.bphone }
							</td>
							
							<td class="inputLabel" style="width: 15%" >收货地址：</td>
							<td width="15%">
								${b2cGoodsOrder.baddress }
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel" style="width: 15%" >下单时间：</td>
							<td width="15%">
								${b2cGoodsOrder.submitTime }
							</td>
							
							<td class="inputLabel" style="width: 15%" >更新时间：</td>
							<td width="15%">
								${b2cGoodsOrder.updateTime }
							</td>
							
							<td class="inputLabel" style="width: 15%" >是否删除：</td>
							<td width="15%">
								<c:if test="${b2cGoodsOrder.isDel=='N' }">未删除</c:if>
								<c:if test="${b2cGoodsOrder.isDel=='Y' }">已删除</c:if>
							</td>
					  	</tr>
						
					</table>
				<br>
			</div>
			
			<div id="goodsBannerDiv" >
				<table width="100%" border="0" cellspacing="1" cellpadding="1">
					<tr align="left">
						<td>
							
						</td>
					</tr>
				</table>
				<table class="add_table_bg" width="100%" border="0"
					cellspacing="1" cellpadding="1" id="productTable">
					<tr class="fb_xx_head_tr">
						<td colspan="10" align="left" class="title_text title_bg" style="text-align: left">
							&nbsp;单品信息列表
						</td>
					</tr>
					<tr class="fb_xx_head_tr">
						<th class="fb_xx_head" style="width: 4%">
							NO
						</th>
						<th class="fb_xx_head" style="width: 10%">
							商品名称
						</th>
						<th class="fb_xx_head" style="width: 10%">
							单价
						</th>
						<th class="fb_xx_head" style="width: 10%">
							数量
						</th>
						<th class="fb_xx_head" style="width: 10%">
							属性描述
						</th>
					</tr>
					<tbody id="goodsBanner_Tbody">
						<c:forEach items="${b2cGoodsOrder.b2cGoodsOrderDetailSet }" var="detail" varStatus="loopStatus">
							<tr class="fb_xx_head_tr"
								onMouseOver="this.style.backgroundColor='#F3F5A7';this.style.cursor='hand'"
								onMouseOut="this.style.backgroundColor='white'">
								<td style="width: 4%" style="text-align: center;">
									${loopStatus.index+1 }
								</td>
								<td width="10%" style="text-align: center;">
									${not empty detail.b2cGoods ? detail.b2cGoods.goodsName : "-"}
								</td>
								<td width="10%" style="text-align: center;">
									${detail.price }
								</td>
								<td width="10%" style="text-align: center;">
									${detail.goodsNum }
								</td>
								<td width="10%" style="text-align: center;">
									${detail.propVals }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div id="goodsActityDiv" >
				<table width="100%" border="0" cellspacing="1" cellpadding="1">
					<tr align="left">
						<td>
							
						</td>
					</tr>
				</table>
				<table class="add_table_bg" width="100%" border="0"
					cellspacing="1" cellpadding="1" id="productTable">
					<tr class="fb_xx_head_tr">
						<td colspan="10" align="left" class="title_text title_bg" style="text-align: left">
							&nbsp;活动信息列表
						</td>
					</tr>
					<tr class="fb_xx_head_tr">
						<th class="fb_xx_head" style="width: 4%">
							NO
						</th>
						<th class="fb_xx_head" style="width: 10%">
							活动名称
						</th>
						<th class="fb_xx_head" style="width: 10%">
							活动单价
						</th>
					</tr>
					<tbody id="goodsBanner_Tbody">
						<c:forEach items="${b2cGoodsOrder.b2cGoodsOrderPromotionSet }" var="detail" varStatus="loopStatus">
							<tr class="fb_xx_head_tr"
								onMouseOver="this.style.backgroundColor='#F3F5A7';this.style.cursor='hand'"
								onMouseOut="this.style.backgroundColor='white'">
								<td style="width: 4%" style="text-align: center;">
									${loopStatus.index+1 }
								</td>
								<td width="10%" style="text-align: center;">
									${detail.activityId}
								</td>
								<td width="10%" style="text-align: center;">
									${detail.discountMoney }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
  	</body>
</html>
