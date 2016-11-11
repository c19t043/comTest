var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>省</th>"+
			"<th style='width:10%'>市</th>"+
			"<th style='width:20%'>医院名称</th>"+
			"<th style='width:20%'>医院地址</th>"+
			"<th style='width:10%'>医院级别</th>"+
			"<th style='width:10%'>医院类别</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button' onclick='add_info()'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getHospitalList'};
	getAllList(data);
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.hospitalBasicInfoList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.hospitalBasicInfoList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.hospitalBasicInfoList[i]).province+"</td>"+
					"<td style='width:10%'>"+(result.hospitalBasicInfoList[i]).city+"</td>"+
					"<td style='width:20%'>"+(result.hospitalBasicInfoList[i]).hospitalLname+"</td>"+
					"<td style='width:20%'>"+(result.hospitalBasicInfoList[i]).address+"</td>"+
					"<td style='width:10%'>"+(result.hospitalBasicInfoList[i]).hospitalLevel+"</td>"+
					"<td style='width:10%'>"+(result.hospitalBasicInfoList[i]).hospitalType+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.hospitalBasicInfoList[i]).id+"' "+
							" province='"+(result.hospitalBasicInfoList[i]).province+"' "+
							" city='"+(result.hospitalBasicInfoList[i]).city+"' "+
							" hospitalLname='"+(result.hospitalBasicInfoList[i]).hospitalLname+"' "+
							" address='"+(result.hospitalBasicInfoList[i]).address+"' "+
							" hospitalLevel='"+(result.hospitalBasicInfoList[i]).hospitalLevel+"' "+
							" hospitalType='"+(result.hospitalBasicInfoList[i]).hospitalType+"' "+
							" introduction='"+(result.hospitalBasicInfoList[i]).introduction+"' "+
							" orgDynamic='"+(result.hospitalBasicInfoList[i]).orgDynamic+"' "+
							" tel='"+(result.hospitalBasicInfoList[i]).tel+"' "+
							" hospitalLng='"+(result.hospitalBasicInfoList[i]).hospitalLng+"' "+
							" hospitalLat='"+(result.hospitalBasicInfoList[i]).hospitalLat+"' "+
							" open_business_id='"+(result.hospitalBasicInfoList[i]).open_business_id+"' "+
							" open_business_name='"+(result.hospitalBasicInfoList[i]).open_business_name+"' "+
							" hospitalNature='"+(result.hospitalBasicInfoList[i]).hospitalNature+"' "+
							" isShowForUser='"+(result.hospitalBasicInfoList[i]).isShowForUser+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
//修改读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#bottom_div_con input').val('');
    
    $('#keyId').val($(obj).attr('keyId'));
    
	$('#province option').filter(function(){
		return $(this).text() == $(obj).attr('province');
	}).get(0).selected = true;
	$('#city option').filter(function(){
		return $(this).text() == $(obj).attr('city');
	}).get(0).selected = true;
	$('#hospitalLname').val($(obj).attr('hospitalLname'));
	$('#address').val($(obj).attr('address'));
	$('#hospitalLevel option').filter(function(){
		return $(this).val() == $(obj).attr('hospitalLevel');
	}).get(0).selected = true;
	$('#hospitalType option').filter(function(){
		return $(this).text() == $(obj).attr('hospitalType');
	}).get(0).selected = true;
	$('#hospitalNature option').filter(function(){
		return $(this).text() == $(obj).attr('hospitalNature');
	}).get(0).selected = true;
	$('#isShowForUser option').filter(function(){
		return $(this).text() == $(obj).attr('isShowForUser');
	}).get(0).selected = true;
	 $('#tel').val($(obj).attr('tel'));
	 $('#introduction').val($(obj).attr('introduction'));
	 $('#orgDynamic').val($(obj).attr('orgDynamic'));
	 $('#hospitalLng').val($(obj).attr('hospitalLng'));
	 $('#hospitalLat').val($(obj).attr('hospitalLat'));
	 $('#open_business_name').text($(obj).attr('open_business_name'));
	 $('#open_business_id').val($(obj).attr('open_business_id'));
	//加载banner
	$('#node_Tbody').empty();
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{
			action:"getHospitalBannerList",
			"hospitalBasicInfo.id":$(obj).attr('keyId')
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.hospitalBannerList == null){
				//alert('没有数据！');
				return false;			
			}
			$('#node_Tbody').empty();
			for(var i=0;i < result.hospitalBannerList.length;i ++){
				$('#node_Tbody').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'>" +
						"<input type='hidden' name='hospitalBannerList["+i+"].id' value='"+result.hospitalBannerList[i].id+"' >"+
						"<input type='hidden' name='hospitalBannerList["+i+"].imgPath' value='"+result.hospitalBannerList[i].imgPath+"' >"+
					"<input type='checkbox' /></td>"+
					"<td style='width:10%'>" +
						"<img  width='60px' height='30px'  onclick='img_cli(this)' src='../"+result.hospitalBannerList[i].imgPath+"'/>" +
						"<input type='file' accept='image/*' onchange='handleFiles(this)' style='display:none;'>" +
						"<canvas style='display:none;' width='60' height='30'></canvas>" +
						"<input type='hidden' name='hospitalBannerList["+i+"].imgBase64' value=''>" +
					"</td>"+
					"<td style='width:10%'>" +
					"<input type='hidden' value='"+result.hospitalBannerList[i].isMainImg+"' >" +
					"<select name='hospitalBannerList["+i+"].isMainImg'><option value='Y'>Y</option><option value='N'>N</option></select></td>"+
					"<td style='width:10%'><input class='query' type='text' name='hospitalBannerList["+i+"].imgRemark' value='"+result.hospitalBannerList[i].imgRemark+"' placeholder='描述'></td>"+
				"</tr>"
				);
			}
		}
	});
	//设置select默认选中
	$('#node_Tbody tr').each(function(){
		var oldIsMain = $(this).find('td:eq(3)').children('input').val();
		$(this).find('td:eq(3)').children('select').find("option").each(function(){
			if(oldIsMain == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	});
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'doctorClinic.action?action=saveOrUpdateHospitalBasicInfo');
	
	function checknum_int(input){
		if(input == '' || parseInt(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	function checknum_float(input){
		if(input == '' || parseFloat(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	if($('#hospitalLname').val().trim() == ''){
		alert('请填写医院名称！');
		return false;	
	}
	if($('#address').val().trim() == ''){
		alert('请填写医院地址！');
		return false;	
	}
	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#bottom_div_con img').removeAttr('src');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
}


//function handleFiles(file){
//	var image = '';
//	if(!file.files || !file.files[0]){
//		return;
//	}
//	var reader = new FileReader();
//	reader.onload = function(evt){
////		document.getElementById('show_smallpic').src = evt.target.result;
//		image = evt.target.result;
//	}
//	$(file).next().val(image);
//	reader.readAsDataURL(file.files[0]);
//}


function img_cli(obj){
	$(obj).next().click();
}

//-----------------------------------
function handleFiles(obj){ 
	var src = $(obj).get(0).files[0];
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
$(obj).next().show();
$(obj).prev().hide();
}
//参数，最大高度  
var MAX_HEIGHT = 70;  
// 渲染  
function render(obj,src){  
	var image = new Image();  
	image.onload = function(){  
	    var canvas = $(obj).next().get(0);
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
	  var canvas = $(obj).next();
	    // 获取Base64编码后的图像数据，格式是字符串  
	    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
	    var dataurl = canvas.get(0).toDataURL("image/png"); 
	    // 为安全 对URI进行编码  
	    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
	    var imagedata =  encodeURIComponent(dataurl);
		$(obj).next().next().val(imagedata);
	},2000);
}  

//添加节点信息
function add_node_click(tempId,targetTbody){
	var j = parseInt(document.getElementById(targetTbody).rows.length);
	var kv={"id":""};//默认值设置
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
//移除选中行
function del_node_click(tbodyId){
	var ids = 0;
	$("#"+tbodyId+" tr").each(function(index){
		var selectObj = $(this).find("td:eq(1)").children("input[type=checkbox]");
		if(selectObj.prop("checked")){
			var sectionId = $(this).find("td:eq(1)").children("input:eq(0)").val();
			if(sectionId == ""){
				ids++;
			}else{
				alert("已有数据不能删除!");
				selectObj.attr("checked",false);
				return true;
			}
		}
	});
		if(ids == 0 ){
			alert("请选择要删除的行!");
			return false;
		}
		if(confirm("确定删除选中的行？")){
			$("#"+tbodyId+" tr").each(function(index){
				var selectObj = $(this).find("td:eq(1)").children("input[type=checkbox]");
				var sectionId = $(this).find("td:eq(1)").children("input:eq(0)").val();
				if(sectionId == "" && selectObj.prop("checked")){
					$(this).remove();
				}
	 		});
		}
}
//---------------弹出遮罩-------------------
//选择修改（弹出遮罩）
function chooseObj(obj){
	//遮罩
	$("#bg").css({
        display: "block", height: $(document).height()
    });
    var $box = $('.boxZhezhao');
    $box.css({
        //设置弹出层距离左边的位置
        left: ($("body").width() - $box.width()) / 2 - 20 + "px",
        //设置弹出层距离上面的位置
        top: ($(window).height() - $box.height()) / 2 + $(window).scrollTop() - 30+ "px",
        display: "block"
    }); 
    if("openBusiness" == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_node('openService') class='th_button'>选择</div></th>"+
				"<th style='width:30%'>开放服务</th>"+
			"</tr>"
		);
    	$.ajax({
    		'url':'orgBusiness.action',
    		'type':'POST',
    		'data':{
    			'action':'getOrgOpenBusinessList',
    		},
    		success:function(result){
    			if(result.mes != '操作成功'){
    				alert('数据返回错误！');
    				return false;
    			}
    			//加载开放服务选择
    			loadOpenService(result.orgOpenBusinessList);
    		}
    	});
    }
}
//加载开放服务
function loadOpenService(list){
	$('#selectContent').empty();
	var goodIds = $('#open_business_id').val().split("::");
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].id+"";
		if($.inArray(id,goodIds) > -1){
			selected = "checked";
		}
		$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'><input type='checkbox' "+selected+" data_name='"+list[i].businessName+"' value='"+list[i].id+"'/></td>"+
					"<td style='width:30%'>"+(list[i].businessName)+"</td>"+
				"</tr>"
				);
	}
}
//隐藏遮罩层  
function hideMask(){     
	 $("#bg,.boxZhezhao").css("display", "none");   
}
//确定选中开展业务
function add_node(obj){
	var nodeId = "";
	var nodeName = "";
	$('#selectContent input:checkbox:checked').each(function(){
		nodeId += $(this).val() + "::";
		nodeName += $(this).attr('data_name') + "::";
	});
	if(nodeId == ""){
		alert("没有选中的数据");
		return false;
	}
	nodeId = nodeId.substring(0,nodeId.lastIndexOf("::"));
	nodeName = nodeName.substring(0,nodeName.lastIndexOf("::"));
	if("openService" == obj){
		$("#open_business_id").val(nodeId);
		$("#open_business_name").text(nodeName);
	}
	hideMask();
}
//查询
function search_click(){
	var hospitalName = $("#hospitalName").val();
	var data = {action:'getHospitalList',
				'hospitalBasicInfo.hospitalLname':hospitalName
				};
	getAllList(data);
}

