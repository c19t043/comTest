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
		<title>商品信息录入</title>
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
  				
  		</style>
  		<script type="text/javascript">
  			var $j = jQuery.noConflict(); 
  			$j(function(){
  				//描述
	  			hf_ueditor('node_edit','noteContent');
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
				window.location.href = "${pageContext.request.contextPath}/b2cGoods/goodsManager/getGoodsInfoList.action";
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
		
		<form id="form_handle" name="form_handle" action="<s:url namespace="/b2cGoods/goodsManager" action="saveOrUpdateB2cGoods" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="b2cGoods.id" value="${b2cGoods.id}">
			<input type="hidden" name="b2cGoods.goodsImg" value="${b2cGoods.goodsImg }">
			<input type="hidden" name="b2cGoods.b2cGoodsType.id" value="${b2cGoods.b2cGoodsType.id }">
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
							<td class="inputLabel">商品名称：</td>
							<td width="30%">
								<input type="text" name="b2cGoods.goodsName" value="${b2cGoods.goodsName}" class="validate['required','length[200]'] text3">
								<span class="text4">*</span>
							</td>
							<td class="inputLabel">是否上架：</td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="text3" list="#{'Y':'上架','N':'下架'}" listKey="key" listValue="value" 
									theme="simple" name="b2cGoods.isUp" value="b2cGoods.isUp"/>
							</td>
					  	</tr>
						<tr>
							<td class="inputLabel">是否免邮：</td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="text3" list="#{'Y':'免邮','N':'收费'}" listKey="key" listValue="value" 
									theme="simple" name="b2cGoods.isFreight" value="b2cGoods.isFreight"/>
							</td>
							
							<td class="inputLabel">发布日期</td>
							<td width="30%">
								<input type="text" name="b2cGoods.createDate" value="${b2cGoods.createDate}"
                       class="validate['required','length[20]'] text3 Wdate" onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});">
								<span class="text4">*</span>
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">商品编码：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.goodsSn" value="${b2cGoods.goodsSn}" class="validate['length[20]'] text3" width="70%">
							</td>
							<td height="32" align="right" bgcolor="#F6F9FE">最少库存量：</td>
							<td width="30%" height="32" align="left" bgcolor="#F6F9FE">
								<input type="text" name="b2cGoods.wearingInventory" value="${b2cGoods.wearingInventory}" class="validate['length[10]','number'] text3" width="70%">
							</td>
					  	</tr>
					  	
					  	<tr>
					  		<td class="inputLabel">商品售价：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.priceSale" value="${b2cGoods.priceSale}" class="validate['length[10]','number'] text3" width="70%">
							</td>
							
							<td class="inputLabel">库存量：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.inventory" value="${b2cGoods.inventory}" class="validate['length[10]','number'] text3" width="70%">
							</td>
					  	</tr>
					  	
					  	<tr>
					  		<td class="inputLabel">商品市场价：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.priceMarket" value="${b2cGoods.priceMarket}" class="validate['length[10]','number'] text3" width="70%">
							</td>
							
							<td class="inputLabel">商品成本价：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.priceCost" value="${b2cGoods.priceCost}" class="validate['length[10]','number'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
					  		<td class="inputLabel">计量单位：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.unit" value="${b2cGoods.unit}" class="validate['length[10]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">重量：</td>
							<td width="30%" >
								<input type="text" name="b2cGoods.weight" value="${b2cGoods.weight}" class="validate['length[10]','number'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">图片上传：</td>
							<td width="30%" >
								<img  width='130px' height='130px'  onclick='img_cli(this)' src='${pageContext.request.contextPath}/${b2cGoods.goodsImg }'/>
						        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
						        <canvas style="display:none;" width="130" height="130"></canvas>
						        <input type="hidden" name="b2cGoods.imgBase64" value=""/>
							</td>
							
							<td height="32" align="right" bgcolor="#F6F9FE">商品描述：</td>
							<td width="30%" height="32" align="left" bgcolor="#F6F9FE">
								<span id="node_edit" class="node_edit">点击编辑</span>
								<textarea id="noteContent" name="b2cGoods.content" class=" Added_textarea">${b2cGoods.content}</textarea>
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
							&nbsp;商品banner信息列表
							<input type="button" class="add_button_hand" value="添加"
								title="添加" id="insertButton" onclick="newSection('goodsBanner_template','goodsBanner_Tbody')"/>
							<input type="button" class="add_button_A_Deny" value="删除"
								title="删除" id="removeButton" onclick="section_del('goodsBanner_Tbody','goodsBanner')"/>
						</td>
					</tr>
					<tr class="fb_xx_head_tr">
						<th class="fb_xx_head" style="width: 4%">
							NO
						</th>
						<th class="fb_xx_head" style="width: 4%">
							选择
						</th>
						<th class="fb_xx_head" style="width: 10%">
							图片上传
						</th>
						<th class="fb_xx_head" style="width: 10%">
							图片描述
						</th>
						<th class="fb_xx_head" style="width: 4%">
							是否可用
						</th>
					</tr>
					<tbody id="goodsBanner_Tbody">
					<c:forEach items="${b2cGoodsBannerList }" var="goodsBanner" varStatus="loopStatus">
						<tr class="fb_xx_head_tr"
							onMouseOver="this.style.backgroundColor='#F3F5A7';this.style.cursor='hand'"
							onMouseOut="this.style.backgroundColor='white'">
							<td style="width: 4%" style="text-align: center;">
								${loopStatus.index+1 }
							</td>
							<td name="goodsBannerId" width="4%" style="text-align: center;">
								<input type="hidden" name="b2cGoodsBannerList[${loopStatus.index }].id" value="${goodsBanner.id }"/>
								<input type="checkbox" value="${goodsBanner.id  }">
							</td>
							<td width="10%" style="text-align: center;">
								<input type="hidden" name="b2cGoodsBannerList[${loopStatus.index }].imgPath" value="${goodsBanner.imgPath }" class="query "/>
								<img  width='130px' height='65px'  onclick='img_cli(this)' src='${pageContext.request.contextPath}/${goodsBanner.imgPath }'/>
						        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
						        <canvas style="display:none;" width="130" height="65"></canvas>
						        <input type="hidden" name="b2cGoodsBannerList[${loopStatus.index }].imgBase64" value=""/>
							</td>
							<td width="10%" style="text-align: center;">
								<input type="text" name="b2cGoodsBannerList[${loopStatus.index }].imgDiscrete" value="${goodsBanner.imgDiscrete }" class="query "/>
							</td>
							<td width="4%" style="text-align: center;">
								<input type="text" name="b2cGoodsBannerList[${loopStatus.index }].isEnable" value="${goodsBanner.isEnable }" class="query "/>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
		<div style="display: none; width: 0px; height: 0px;">
			<table>
				<tr id="goodsBanner_template" class="fb_xx_head_tr" onMouseOver="this.style.backgroundColor='#F3F5A7';this.style.cursor='hand'"
								onMouseOut="this.style.backgroundColor='white'">
					<td width="4%">
						
					</td>
					<td name="sectionId" width="4%">
						<input type="hidden" name="b2cGoodsBannerList[].id" />
						<input type="checkbox" />
					</td>
					<td width="10%" style="text-align: center;">
						<input type="hidden" name="b2cGoodsBannerList[].imgPath" value="${goodsBanner.imgPath }" class="query "/>
				        <img  width='130px' height='65px'  onclick='img_cli(this)' src=''/>
				        <input type="file" accept="image/*" onchange="handleFiles(this)" style="display:none;"/>
				        <canvas style="display:none;" width="130" height="65"></canvas>
				        <input type="hidden" name="b2cGoodsBannerList[].imgBase64" value=""/>
					</td>
					<td width="10%" style="text-align: center;">
						<input type="text" name="b2cGoodsBannerList[].imgDiscrete" class="query "/>
					</td>
					<td width="4%" style="text-align: center;">
						<select name="b2cGoodsBannerList[].isEnable">
							<option value="Y">Y</option>
							<option value="N">N</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
  	</body>
</html>
