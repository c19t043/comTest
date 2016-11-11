$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:13%'>产品名称</th>"+
			"<th style='width:10%'>职称要求</th>"+
			"<th style='width:11%'>上门服务时长</th>"+
			"<th style='width:11%'>售后服务时长</th>"+
			"<th style='width:10%'>产品价格</th>"+
			"<th style='width:10%'>适用宝宝年龄</th>"+
			"<th style='width:10%'>适用宝宝性别</th>"+
			"<th style='width:15%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	hf_ueditor('child_edit','area_edit');
	show_tableinfo();
	$.ajax({
		type:'post',
		async:false,
		url:'positionHandle.action',
		data:{action:"all"},
	    success:function(result){
	    	if(result.allPosition!=null){
	    		   for(var i=0;i<result.allPosition.length;i++){
	    			      $("#show_level").append(
									"<option zjid='"+result.allPosition[i].id+"' value='"+result.allPosition[i].id+"'>"+result.allPosition[i].name+"</option>"
	    			     );
	    			   
	    			 }
	    	}

	    }
	});
	$.ajax({
		type:'post',
		async:false,
		url:'productItemHandle.action',
		data:{action:"getProductItemIdAndName"},
	    success:function(result){
	    	if(result.allProductItemIdAndName!=null){
	    		   for(var i=0;i<result.allProductItemIdAndName.length;i++){
	    			      $("#show_product").append(
									"<div class='box'><input id='product_list_"+i+"' type='checkbox' class='check'><label zjid='"+(result.allProductItemIdAndName[i])[0]+"' for='product_list_"+i+"'>"+(result.allProductItemIdAndName[i])[1]+"</label></div>"
	    			     );
	    			   
	    			 }
	    	}

	    }
	});	
	$("#memberTypeId").empty();
	//加载会员卡列表
		$.ajax({
			type:'post',
			async:false,
			url:'memberManage.action',
			data:{action:"getMemberTypeList"},
		    success:function(result){
		    	 $("#memberTypeId").append("<option value=''>请选择</option>");
		    	if(result.memberTypeList!=null){
	    		   for(var i=0;i<result.memberTypeList.length;i++){
	    			      $("#memberTypeId").append(
									"<option value='"+result.memberTypeList[i].id+"'>"+result.memberTypeList[i].memberName+"</option>"
	    			     );
	    			   
	    			 }
		    	}
		    }
		});
});

//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    
    $('#bottom_div_con input').val('');
    $('#bottom_div_con img').removeAttr('src');
    for(var i = 0;i < $('#show_product input').length;i++){
    	$('#show_product input').eq(i).get(0).checked = false;
    }
    
    
    $("#show_name").val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));//产品名称
    $('#hide_id').val($(obj).attr('data_id'));//产品ID
    $("#show_servicetime").val($(obj).attr('data_servicetime'));//服务时长
    $("#show_afterservicetime").val($(obj).attr('data_afterservicetime'));//售后服务时长
    $("#show_age").val($(obj).attr('data_age'));//宝宝年龄
    $("#show_price").val($(obj).attr('data_price'));//产品价格
    
    $('#show_isspecial option').filter(function(){//是否特色产品
    	return $(this).text() == $(obj).attr('data_isspecial');
    }).get(0).selected = true;

    $('#show_sex option').filter(function(){//性别
    	return $(this).text() == $(obj).attr('data_sex');
    }).get(0).selected = true;
 
     $('#show_level option').filter(function(){//职称级别ID
    		return $(this).text() == $(obj).attr('data_doctorname');
     }).get(0).selected = true;

    
    $('#show_productproperty option').filter(function(){//产品属性
    	return $(this).text() == $(obj).attr('data_productproperty');
    }).get(0).selected = true; 
    
    $('#show_type option').filter(function(){//产品类别
    	return $(this).text() == $(obj).attr('data_type');
    }).get(0).selected = true;
    
    $('#show_product_type option').filter(function(){//产品归属类型
    	return $(this).text() == $(obj).attr('data_product_type');
    }).get(0).selected = true;
    $('#memberTypeId option').each(function(){//会员卡选中
    	if($(this).val() == $(obj).attr('member_type_id')){
    		$(this).attr("selected","selected");
    	}
    });

    $('#show_status option').filter(function(){//产品状态
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0).selected = true;
    //产品所属流程
    loadFlowBasic();
    var flowBasicId = $('#flowBasicId option').filter(function(){
    	return $(this).val() == $(obj).attr('flowBasicId');
    }).get(0);
    if(flowBasicId != undefined){
    	flowBasicId.selected = true;
    }
    
    var isJudgeAge = $('#isJudgeAge option').filter(function(){//是否判断年龄（Y/N）
    	return $(this).val() == $(obj).attr('isJudgeAge');
    }).get(0);
    if(isJudgeAge != undefined){
    	isJudgeAge.selected = true;
    }
    
    var isJudgeSex = $('#isJudgeSex option').filter(function(){//是否判断性别（Y/N）
    	return $(this).val() == $(obj).attr('isJudgeSex');
    }).get(0);
    if(isJudgeSex != undefined){
    	isJudgeSex.selected = true;
    }
    
    $("#maxAge").val($(obj).attr('maxAge'));//最大月龄
    
    $('#area_edit').html($(obj).attr('data_info'));//产品介绍
    $('#shortIntroduction').text($(obj).attr('shortIntroduction'));//产品介绍
    $('#show_smallpic').attr('src',"images/product/"+$(obj).attr('data_smallimg')+"");//小图
    $('#show_bigpic_1').attr('src',"images/product/productDetail/"+$(obj).attr('data_bigimg_1')+"");//大图1
    $('#show_bigpic_2').attr('src',"images/product/productDetail/"+$(obj).attr('data_bigimg_2')+"");//大图2
    $('#show_bigpic_3').attr('src',"images/product/productDetail/"+$(obj).attr('data_bigimg_3')+"");//大图3
    
    
    if($(obj).attr('data_object').indexOf('::') != -1){
	    var product_arr = $(obj).attr('data_object').split('::');
	    for(var i=0;i < product_arr.length;i++){
	    	var x = $('#show_product label').filter(function(){
	    		return $(this).text() == product_arr[i];
	    	}).prev().get(0);
	    	if(x != undefined){
					x.checked = true;
	    	}
	    	
	    }
    }else{
	    	$('#show_product label').filter(function(){
	    		return $(this).text() == $(obj).attr('data_object');
	    	}).prev().get(0).checked = true; 
    }

		

}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'productHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allProduct == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allProduct.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:13%'>"+(result.allProduct[i])[1]+"</td>"+
					"<td style='width:10%'>"+(result.allProduct[i])[2]+"</td>"+
					"<td style='width:11%'>"+(result.allProduct[i])[3]+"</td>"+
					"<td style='width:11%'>"+(result.allProduct[i])[4]+"</td>"+
					"<td style='width:10%'>"+(result.allProduct[i])[5]+"</td>"+
					"<td style='width:10%'>"+(result.allProduct[i])[6]+"</td>"+
					"<td style='width:10%'>"+(result.allProduct[i])[7]+"</td>"+
					"<td style='width:15%'>"+
						"<div class='td_change_button' "+
							" data_id='"+(result.allProduct[i])[0]+"' "+
							" data_name='"+(result.allProduct[i])[1]+"' "+
							" data_doctorname='"+(result.allProduct[i])[2]+"' "+
							" data_servicetime='"+(result.allProduct[i])[3]+"' "+
							" data_afterservicetime='"+(result.allProduct[i])[4]+"' "+
							" data_price='"+(result.allProduct[i])[5]+"' "+
							" data_age='"+(result.allProduct[i])[6]+"' "+
							" data_sex='"+(result.allProduct[i])[7]+"' "+
							" data_isspecial='"+(result.allProduct[i])[8]+"' "+
							" data_productproperty='"+(result.allProduct[i])[9]+"' "+
							" data_type='"+(result.allProduct[i])[10]+"' "+
							" data_info='"+(result.allProduct[i])[11]+"' "+
							" data_smallimg='"+(result.allProduct[i])[12]+"?"+parseInt(Math.random()*1000000)+"' "+
							" data_bigimg_1='"+(result.allProduct[i])[13]+"?"+parseInt(Math.random()*1000000)+"' "+
							" data_bigimg_2='"+(result.allProduct[i])[14]+"?"+parseInt(Math.random()*1000000)+"' "+
							" data_bigimg_3='"+(result.allProduct[i])[15]+"?"+parseInt(Math.random()*1000000)+"' "+
							" data_object='"+(result.allProduct[i])[16]+"' "+
							" data_status='"+(result.allProduct[i])[17]+"' "+
							" flowBasicId='"+(result.allProduct[i])[18]+"' "+
							" isJudgeAge='"+(result.allProduct[i])[19]+"' "+
							" isJudgeSex='"+(result.allProduct[i])[20]+"' "+
							" maxAge='"+(result.allProduct[i])[21]+"' "+
							" data_product_type='"+(result.allProduct[i])[22]+"' "+
							" member_type_id='"+(result.allProduct[i])[23]+"' "+
							" shortIntroduction='"+(result.allProduct[i])[24]+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
function product_info(type){
	if(type=='update'){
		$('#product_form').attr('action','productHandle.action?action=update')
	}else if(type=='add'){
		$('#product_form').attr('action','productHandle.action?action=add')
	}
	var input_a = $.trim($('#show_servicetime').val());
	var input_b = $.trim($('#show_afterservicetime').val());
	var input_c = $.trim($('#show_age').val());
	var input_d = $.trim($('#show_price').val());
	var flowBasicId = $.trim($('#flowBasicId').val());
	var product_type = $.trim($('#show_product_type').val());
	
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
	
	if($.trim($('#show_name').val()) == ''){
		alert('请填写产品名！');
		return false;
	}
	if(product_type == '健康产品'){
		if(checknum_int(input_a) || checknum_int(input_b) || checknum_int(input_c)){
			alert('请填写正确的格式！');
			return false;
		}else if(checknum_float(input_d)){
			alert('请填写正确格式的价格');
			return false;
		}else if($('#area_edit').html() == ''){
			alert('请填写产品说明');
			return false;	
		}else if(flowBasicId == ''){
			alert('请选择所属流程');
			return false;	
		}
	}
	var arr_str='';
	for(var i=0;i < $('#show_product input').length;i++){
		if($('#show_product input').eq(i).get(0).checked){
			arr_str = arr_str +"::"+ $('#show_product input').eq(i).next().attr('zjid');
		}
	}
	arr_str=arr_str.substring(2);
	if(arr_str == ''){
		alert('请选择至少选择一个项目');
		return false;
	}
	$('#hide_projectarr').val(arr_str);
	
	$('#product_form').get(0).submit();
}
//添加
function add_info(){
  $('#bottom_div_con input').val('');
  $('#bottom_div_con img').removeAttr('src');
  for(var i = 0;i < $('#show_product input').length;i++){
  	$('#show_product input').eq(i).get(0).checked = false;
  }
  $('#bottom_div').slideDown(500);
  $('#button_add_click').show();
  $('#button_update_click').hide();	
  loadFlowBasic();
}
//-----------------------------------
function handleFiles(obj,img3){ 

	window.URL = window.URL || window.webkitURL;
	var files = obj.files;
	var img = new Image();
	if(window.URL){
		img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
	  img.width = 101;
	  img.height = 101;
	  img.onload = function(e) {
	  window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
	};
	document.getElementById(img3).src=img.src;
	}else if(window.FileReader){
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = function(e){
			img.src = this.result;
			img.width = 200;
			document.getElementById(img3).src=img.src;
		};
	}else{
		obj.select();
		obj.blur();
		var nfile = document.selection.createRange().text;
		document.selection.empty();
		img.src = nfile;
		img.width = 200;
		img.onload=function(){
	};
		document.getElementById(img3).src=img.src;
	}
	

}

//加载流程下拉选择
function loadFlowBasic(){
	$.ajax({
		'url':'operationFlow.action',
		'type':'POST',
		async:false,
		'data':{
			'action':'allOperationFlowBasicInfo',
			'operationFlowBasicInfo.isStart':'Y'
		},
		success:function(result){
			if(result.operationFlowBasicInfoList == null){
				alert('没有数据！');
				return false;			
			}
			$('#flowBasicId').empty();
			$('#flowBasicId').prepend("<option value=''>-请选择-</option>");
			for(var i=0;i < result.operationFlowBasicInfoList.length;i ++){
				$('#flowBasicId').append(
					"<option value='"+(result.operationFlowBasicInfoList[i]).flowBasicId+"'>"+(result.operationFlowBasicInfoList[i]).flowName+"</option>"	
				);
			}
		}
	});
}
//加载会员卡列表
function member(obj){
	$("#memberTypeId").empty();
	if($(obj).val() == "会员产品"){
		$.ajax({
			type:'post',
			async:false,
			url:'memberManage.action',
			data:{action:"getMemberTypeList"},
		    success:function(result){
		    	 $("#memberTypeId").append("<option value=''>请选择</option>");
		    	if(result.memberTypeList!=null){
	    		   for(var i=0;i<result.memberTypeList.length;i++){
	    			      $("#memberTypeId").append(
									"<option value='"+result.memberTypeList[i].id+"'>"+result.memberTypeList[i].memberName+"</option>"
	    			     );
	    			   
	    			 }
		    	}

		    }
		});
	}
}





