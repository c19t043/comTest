$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:7%'>序列</th>"+
			"<th style='width:7%'>医生姓名</th>"+
			"<th style='width:5%'>性别</th>"+
			"<th style='width:10%'>职称</th>"+
			"<th style='width:10%'>手机号</th>"+
			"<th style='width:15%'>工作单位</th>"+
			"<th style='width:9%'>服务范围</th>"+
			"<th style='width:10%'>认证状态</th>"+
			"<th style='width:10%'>注册时间</th>"+
			"<th style='width:16%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
	$('#button_update_click').click(function(){
		$.ajax({
			'url':'doctorHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'updateId':$('#show_status').attr('zjid'),
				'status':$('#show_status').val(),
				'authentication':$('#show_certification').val(),
				'isRecommend':$('#is_recommend').val(),
				'goodFieldIds':$('#good_at_field_id').val(),
				'serviceTypeIds':$("#openService_id").val(),
				'bankCard':$("#show_back_code").val(),
				'headImgBase64':$("#headImgBase64").val(),
				'licenseImageBase64':$("#licenseImageBase64").val(),
				'doctorTitle':$("#show_title option:selected").text(),//医生职称
				'doctorInfo.bankAccountName':$("#show_bank option:selected").text(),//银行
				'comments':$('#show_description').val(),
				'doctorInfo.doctorName':$('#show_name').val(),//医生姓名
				'doctorInfo.doctorPhone':$('#show_phone').val(),//医生手机
				'doctorInfo.doctorSex':$('#show_sex').val(),//医生性别
				'doctorInfo.doctorEmployer':$('#show_hospital').text(),//医生工作单位
				'doctorInfo.hospitalId':$('#hospital_id').val(),//医生工作单位id
				'doctorInfo.idCard':$('#show_idcard').val(),//医生资质证号
				'doctorInfo.clinicalExperience':$('#clinicalExperience').val(),//医生经验
				'doctorInfo.department':$('#department').val(),//科室
				'doctorInfo.doctorComment':$('#doctorComment').val(),//医生简介
				'doctorInfo.serviceAddType':$('#serviceAddType').val(),//服务地点类型
				'doctorInfo.doctorType':$('#doctorType').val()//医生类型
			},
			success:function(result){
				if(result.mes == '成功'){
					alert('修改成功');
					$('#bottom_div').slideUp(500);
					show_tableinfo();
				}else{
					alert('数据错误');
				}
			}
		});
	});
});     

//读取信息
function read_info(obj){
	$('#bottom_div_con input').val('');
	$('#product_tag,#ask_tag').empty();
	$('#mask').empty();
	$.ajax({
		'url':'doctorHandle.action',
		'type':'POST',
		'data':{
			'action':'getDetailDoctor',
			'doctorId':$(obj).attr('data_id')
		},
		success:function(result){
			//alert("MES=="+result.mes);
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}
			if((result.detailDoctorInfo) == null || (result.detailDoctorInfo[0])[10] == '未申请'){
				alert('该医生未提交申请！');
				return false;
			}
			
			if(result.doctorAddress != null){
				var address = '';
				for(var i=0;i<result.doctorAddress.length;i++){
					address = address + result.doctorAddress[i].doctorProvince + result.doctorAddress[i].doctorCity + result.doctorAddress[i].doctorArea + result.doctorAddress[i].doctorStreet + result.doctorAddress[i].detailAddress +"<br/>";
				}
				$('#add_showbox').attr('address',address);
				
			}else{
				$('#add_showbox').attr('address','');
			}
			if(result.allPosition != null){
				for(var i=0;i<result.allPosition.length;i++){
					$('#show_title').append("<option>"+result.allPosition[i].name+"</option>");
				}
			}
			$('#show_name').val((result.detailDoctorInfo[0])[2]);
			$('#show_sex').val((result.detailDoctorInfo[0])[3]);
			$('#show_phone').val((result.detailDoctorInfo[0])[5]);
			$('#show_area').val((result.detailDoctorInfo[0])[20]);
			$('#show_hospital').text((result.detailDoctorInfo[0])[7]);
			$('#show_servicetype').val((result.detailDoctorInfo[0])[15]);
			$('#show_serviceway').val((result.detailDoctorInfo[0])[19]);
			$('#good_at_field_name').text((result.detailDoctorInfo[0])[49]);
			$('#majorId').text((result.detailDoctorInfo[0])[48]);
			$('#advisoryLabelIds').text((result.detailDoctorInfo[0])[46]);
			//服务地点及类型
			$('#serviceAddType').text((result.detailDoctorInfo[0])[51]);
			$('#good_at_field_id').val((result.detailDoctorInfo[0])[43]);
			$('#show_status').attr('zjid',(result.detailDoctorInfo[0])[0]);
			$('#openService').html($(obj).attr('openService'));
			$('#openService_id').val((result.detailDoctorInfo[0])[45]);
			$('#clinicalExperience').val((result.detailDoctorInfo[0])[44]);
			$('#hospital_id').val((result.detailDoctorInfo[0])[41]);
			$('#doctorComment').val((result.detailDoctorInfo[0])[39]);
			if((result.detailDoctorInfo[0])[1]==null){
				$('#show_idcard').text("");
			}else{
				$('#show_idcard').val((result.detailDoctorInfo[0])[1]);
			}
			$('#show_status option').filter(function(){
				return $(this).text() == (result.detailDoctorInfo[0])[9];
			}).get(0).selected = true;
			//是否推荐
			$('#is_recommend option').each(function(){
				if($(this).text() == (result.detailDoctorInfo[0])[40]){
					$(this).attr("selected",true);
				}
			});
			//医生职称
			$('#show_title option').each(function(){
				if($(this).text() == (result.detailDoctorInfo[0])[6]){
					$(this).attr("selected",true);
				}
			});
			//医生性别
			$('#show_sex option').each(function(){
				if($(this).text() == (result.detailDoctorInfo[0])[3]){
					$(this).attr("selected",true);
				}
			});
			//科室
			$('#department option').each(function(){
				if($(this).text() == (result.detailDoctorInfo[0])[42]){
					$(this).attr("selected",true);
				}
			});
			//银行
			$('#show_bank option').each(function(){
				if($(this).text() == (result.detailDoctorInfo[0])[16]){
					$(this).attr("selected",true);
				}
			});
			//医生类型
			$('#doctorType option').each(function(){
				if($(this).val() == (result.detailDoctorInfo[0])[50]){
					$(this).attr("selected",true);
				}
			});
			

			$('#show_certification option').filter(function(){
				return $(this).text() == (result.detailDoctorInfo[0])[10];
			}).get(0).selected = true;			

			$('#show_back_code').val((result.detailDoctorInfo[0])[17]);
			$('#show_address').val('待添加');
			$('#show_head_portrait').attr('src',"images/doctorFaceIcon/"+(result.detailDoctorInfo[0])[4]);
			$('#show_doctor_zz').attr('src',"images/doctorCertifiedPicture/"+(result.detailDoctorInfo[0])[36]);
			$('#show_description').text((result.detailDoctorInfo[0])[8]);
			
			if((result.detailDoctorInfo[0])[39] == null || (result.detailDoctorInfo[0])[39] == ''){
				//return false;
			}else if((result.detailDoctorInfo[0])[39].indexOf('::') == -1){
				$('#ask_tag').append("<label>"+(result.detailDoctorInfo[0])[46]+"</label>");
			}else{
				var ask_tag_arr = (result.detailDoctorInfo[0])[46].toString().split('::');
				for(var i=0;i < ask_tag_arr.length;i ++){
					$('#ask_tag').append("<label>"+ask_tag_arr[i]+"</label>");
				}				
			}

			if((result.detailDoctorInfo[0])[47] == null || (result.detailDoctorInfo[0])[47].indexOf('::') == -1){
				$('#product_tag').append("<div class='box'><input type='checkbox' class='check'><span>"+(result.detailDoctorInfo[0])[47]+"</span></div>");
			}else{
				var ask_tag_arr = (result.detailDoctorInfo[0])[47].toString().split('::');
				for(var i=0;i < ask_tag_arr.length;i ++){
					$('#product_tag').append("<div class='box'><input checked='checked' disabled='disabled' type='checkbox' class='check'><span>"+ask_tag_arr[i]+"</span></div>");
				}				
			}
		
	    $('#bottom_div').slideDown(500);
	    $('#button_add_click').hide();
	    $('#button_update_click').show();
		}
	});
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'doctorHandle.action',
		'type':'POST',
		'data':{
			'action':'all',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allDoctor == null){
				alert('没有数据！');
				return false;			
			}
			
			for(var i=0;i < result.allDoctor.length;i++){
				$('#table_content,#table_content_plus').append(
	        "<tr onclick='click_light(this)'>"+
            "<td width='7%'>"+(i+1)+"</td>"+
            "<td width='7%'>"+(result.allDoctor[i])[2]+"</td>"+
            "<td width='5%'>"+(result.allDoctor[i])[3]+"</td>"+
            "<td width='10%'>"+(result.allDoctor[i])[6]+"</td>"+
            "<td width='10%'>"+(result.allDoctor[i])[5]+"</td>"+
            "<td width='15%'>"+(result.allDoctor[i])[7]+"</td>"+
            "<td width='9%'>"+(result.allDoctor[i])[20]+"km</td>"+
            "<td width='10%'>"+(result.allDoctor[i])[10]+"</td>"+
            "<td width='10%'>"+(result.allDoctor[i])[11]+"</td>"+
            "<td width='16%'>"+
            	"<div class='td_change_button' " +
            	"data_id='"+(result.allDoctor[i])[0]+ "' "+ 
            	"openService='"+(result.allDoctor[i])[49]+ "' "+
            	"onclick='read_info(this)'>修改</div>"+
            "</td>"+
	        "</tr>"
				);
			}
			reset_con_page();	
		},
	});
}
function filtrate(){

	var filtrate_1 = $('#input_1').val();
	var filtrate_2 = $('#input_2').val();
	var filtrate_3 = $('#input_3').val();
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	
	$('#table_content').empty();
	$('#table_content_plus tbody').clone().appendTo($('#table_content'));
	$('#table_content tr').hide();
	var marknum = 1;
	
	for(var i=0; i < $('#table_content tr').length;i ++){
		var a = $('#table_content tr').eq(i).filter(function(){
			
			if($('.check_filtrate').eq(0).get(0).checked && $('.check_filtrate').eq(1).get(0).checked){
				return $(this).find('td').eq(4).text().indexOf(filtrate_1) != -1 && $(this).find('td').eq(1).text().indexOf(filtrate_2) != -1;
			}else if($('.check_filtrate').eq(0).get(0).checked){
				return $(this).find('td').eq(4).text().indexOf(filtrate_1) != -1;
			}else if($('.check_filtrate').eq(1).get(0).checked){
				return $(this).find('td').eq(1).text().indexOf(filtrate_2) != -1;
			}else if($('.check_filtrate').eq(2).get(0).checked){//审核状态
				return $(this).find('td').eq(7).text().indexOf(filtrate_3) != -1;
			}else if($('.check_filtrate').eq(3).get(0).checked){//注册时间
				var tdVal = $(this).find('td').eq(8).text();
				tdVal = tdVal.substring(0,10).replace(/-/g, "");
				if(startTime == "" && endTime != ""){
					endTime = endTime.replace(/-/g, "");
					return tdVal <= endTime;
				}else if(startTime != "" && endTime == ""){
					startTime = startTime.replace(/-/g, "");
					return tdVal >= startTime;
				}else if(startTime != "" && endTime != ""){
					startTime = startTime.replace(/-/g, "");
					endTime = endTime.replace(/-/g, "");
					return tdVal >= startTime && tdVal <= endTime;
				}else{
					return true;
				}
			}
			else{
				return true;
			}
		});
		if(a.get(0) != undefined){
			a.find('td').eq(0).text(marknum);
			marknum++;
		}
		a.show();
	}
	$('#table_content tr:hidden').remove();
	reset_con_page($('#table_content tr:visible').length);
}
function showbox_content(obj){
	hf_showbox($(obj).attr('address'));
}
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
    if("openService" == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_node('openService') class='th_button'>选择</div></th>"+
				"<th style='width:30%'>开放服务</th>"+
				"<th style='width:50%'>所属类别</th>"+
			"</tr>"
		);
    	$.ajax({
    		'url':'doctorHandle.action',
    		'type':'POST',
    		'data':{
    			'action':'getDoctorServiceTypeList',
    		},
    		success:function(result){
    			if(result.mes != '成功'){
    				alert('数据返回错误！');
    				return false;
    			}
    			//加载开放服务选择
    			loadOpenService(result.doctorServiceTypeList);
    		}
    	});
    }else if("goodField" == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_node('goodField') class='th_button'>选择</div></th>"+
				"<th style='width:30%'>擅长领域</th>"+
				"<th style='width:50%'>说明</th>"+
			"</tr>"
		);
    	$.ajax({
    		'url':'doctorHandle.action',
    		'type':'POST',
    		'data':{
    			'action':'getDoctorGoodFieldList',
    		},
    		success:function(result){
    			if(result.mes != '成功'){
    				alert('数据返回错误！');
    				return false;
    			}
    			 //加载擅长领域选择
    		    loadGoodField(result.doctorGoodFieldList);
    		}
    	});
    }else if("show_hospital" == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_hospital() class='th_button'>选择</div></th>"+
				"<th style='width:30%'>医院名称</th>"+
				"<th style='width:50%'>医院地址</th>"+
			"</tr>"
		);
    	$.ajax({
    		'url':'../admin/doctorClinic.action',
    		'type':'POST',
    		'data':{
    			'action':'getSelectList',
    		},
    		success:function(result){
    			if(result.mes != '操作成功'){
    				alert('数据返回错误！');
    				return false;
    			}
    			//工作单位选项
    		    loadHospital(result.hospitalBasicInfoList);
    		}
    	});
    }
}
//隐藏遮罩层  
function hideMask(){     
	 $("#bg,.boxZhezhao").css("display", "none");   
}
//加载擅长领域选择内容
function loadGoodField(list){
	$('#selectContent').empty();
	var goodIds = $('#good_at_field_id').val().split("::");
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
		if(list[i].isStart == "Y"){
			$('#selectContent').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:10%'>"+(i+1)+"</td>" +
						"<td style='width:10%'><input type='checkbox' "+selected+" data_name='"+list[i].name+"' value='"+list[i].id+"'/></td>"+
						"<td style='width:30%'>"+(list[i].name)+"</td>"+
						"<td style='width:50%'>"+(list[i].remark)+"</td>"+
					"</tr>"
					);
		}
	}
}
//加载开放服务选择内容
function loadOpenService(list){
	$('#selectContent').empty();
	var goodIds = $('#openService_id').val();
	if(goodIds != null){
		goodIds = goodIds.split("::");
	}
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].id+"";
		if(goodIds != null && $.inArray(id,goodIds) > -1){
			selected = "checked";
		}
		if(list[i].parentDoctorServiceType != null && list[i].isAvailable == 'Y'){
			$('#selectContent').append(
					"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'><input type='checkbox' "+selected+" data_name='"+list[i].serviceTypeName+"' value='"+list[i].id+"'/></td>"+
					"<td style='width:30%'>"+(list[i].serviceTypeName)+"</td>"+
					"<td style='width:50%'>"+(list[i].parentDoctorServiceType.serviceTypeName)+"</td>"+
					"</tr>"
			);
		}
	}
}
//加载工作单位选择内容
function loadHospital(list){
	$('#selectContent').empty();
	var hospital_id = $('#hospital_id').val();
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].id+"";
		if(id == hospital_id){
			selected = "checked";
		}
		$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'><input name='aa' type='radio' "+selected+" data_name='"+list[i].hospitalLname+"' value='"+list[i].id+"'/></td>"+
					"<td style='width:30%'>"+(list[i].hospitalLname)+"</td>"+
					"<td style='width:50%'>"+(list[i].address)+"</td>"+
				"</tr>"
				);
	}
}
//确定选中擅长领域
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
		$("#openService_id").val(nodeId);
		$("#openService").text(nodeName);
	}else if("goodField" == obj){
		$("#good_at_field_id").val(nodeId);
		$("#good_at_field_name").text(nodeName);
	}
	hideMask();
}
//确定选中医院
function add_hospital(){
	var nodeId = "";
	var nodeName = "";
	$('#selectContent input:radio:checked').each(function(){
		nodeId += $(this).val() + "::";
		nodeName += $(this).attr('data_name') + "::";
	});
	if(nodeId == ""){
		alert("没有选中的数据");
		return false;
	}
	nodeId = nodeId.substring(0,nodeId.lastIndexOf("::"));
	nodeName = nodeName.substring(0,nodeName.lastIndexOf("::"));
	$("#hospital_id").val(nodeId);
	$("#show_hospital").text(nodeName);
	hideMask();
}


//---------------图片上传--------------------
function img_cli(obj){
	$(obj).next().click();
}
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
	    	canvas.width = 70;  // 重置canvas宽高
	    	canvas.height = 70;    
	
	    ctx.drawImage(image, 0, 0,x,y,0,0,70,70);  // 将图像绘制到canvas上
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
