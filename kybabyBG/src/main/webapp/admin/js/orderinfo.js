$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>订单编号</th>"+
			"<th style='width:15%'>下单时间</th>"+
			"<th style='width:10%'>订单金额</th>"+
			"<th style='width:10%'>儿保产品</th>"+
			"<th style='width:10%'>医生姓名</th>"+
			"<th style='width:10%'>宝宝姓名</th>"+
			"<th style='width:10%'>订单状态</th>"+
			"<th style='width:10%'>手机号</th>"+
			"<th style='width:15%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
	$.ajax({
		'url':'orderInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'getProductName'
		},
		success:function(result){

			for(var i=0;i < result.prodcutNameList.length;i++){
				$('#select_product').append(
					"<option>"+(result.prodcutNameList[i])+"</option>"
				);
			}
		}
	});
});



//读取信息
function read_info(obj){
	     var show_doctorName = $(obj).attr('data_doctorName');//获取医生姓名
	     var data_status = $(obj).attr('data_status');//获取订单状态
		if(data_status == '医生取消' || show_doctorName == ''){
			var actionName = 'getDoctor';
			if(show_doctorName == ''){//订单里没有医生信息时加载所有可用医生
				actionName = 'getAllDoctor';//得到所有可用医生，供运营指派
			}
			$.ajax({//遍历医生姓名
				'url':'orderInfoHandle.action',
				'async':false,
				'data':{
					'action':actionName,
					'updateId':$(obj).attr('data_id')
				},
				success:function(result){
					$('#show_doctorName_se').empty();//医生姓名选择
					if(result.mes == '成功' && result.doctorList != null && result.doctorList.length > 0){
						for(var i=0;i < result.doctorList.length;i++){
							$('#show_doctorName_se').append(
								"<option zjid='"+(result.doctorList[i])[0]+"'>"+(result.doctorList[i])[1]+"</option>"
							);
						}
					}else{
						alert('没有可以接单的医生！');
					}
				}
			});
			//医生姓名列表可选
			$('#show_doctorName_se').show();
			$('#show_doctorName').hide();
			//填充订单状态
			$('#show_status').removeAttr('disabled').removeAttr('readonly');
			$('#show_status').empty();
      $('#show_status').append(
      	"<option>医生取消</option>"+
	    	"<option>已接单</option>"+
	    	"<option>已退款</option>"
     	);
		}else{
			$('#show_doctorName_se').hide();
			$('#show_doctorName').show();
			$('#show_status').attr('disabled','disabled').attr('readonly','readonly');
			$('#show_status').empty();
      $('#show_status').append(
	    	"<option>已接单</option>"+
	    	"<option>医生取消</option>"+
	    	"<option>用户取消</option>"+
	    	"<option>已退款</option>"+
	    	"<option>已签到</option>"+
	    	"<option>已完成</option>"+
	    	"<option>已确认</option>"+
	    	"<option>未付款</option>" 
     	);
		}
		

   
    $('#show_orderNum').val($(obj).attr('data_num')).attr('zjid',$(obj).attr('data_id')).
    attr('statu',$(obj).attr('data_status')).attr('doctorName',$(obj).attr('data_doctorName'));
    $('#show_doctorName').val($(obj).attr('data_doctorName'));
    $('#show_time').val($(obj).attr('data_time'));
    $('#show_babyName').val($(obj).attr('data_babyName'));
    $('#show_money').val($(obj).attr('data_money'));
    $('#show_phone').val($(obj).attr('data_phone'));
    (function(){
	    var x = $('#show_status option').filter(function(){
	    	return $(this).text() == $(obj).attr('data_status');
	    }).get(0);
	    if(x != undefined){
	    	x.selected = true;
	    }    
    })();
    $('#show_parentName').val($(obj).attr('data_parentName'));
    $('#show_adress').val($(obj).attr('data_address'));
    (function(){
    	$('#show_object').empty();
	    var x = $(obj).attr('data_serviceObject');
	    if(x.indexOf('::') == -1){
	   		if(x != null){
		     	$('#show_object').append(
		    		"<label>"+x+"</label>"
		    	);  		
	   		}
	    }else{
	    	x = x.split('::');
	    	for(var i=0;i < x.length;x++){
	 	     	$('#show_object').append(
		    		"<label>"+x[i]+"</label>"
		    	);    	
	    	}
	    }    
    })();
    (function(){
    	$('#show_tag').empty();
	    var x = $(obj).attr('data_tag');
	    if(x == 'null'){
	    	return false;
	    }
	    if(x.indexOf('::') == -1){
	   		if(x != null){
		     	$('#show_tag').append(
		    		"<label>"+x+"</label>"
		    	);  		
	   		}
	    }else{
	    	x = x.split('::');
	    	for(var i=0;i < x.length;i++){
	 	     	$('#show_tag').append(
		    		"<label>"+x[i]+"</label>"
		    	);    	
	    	}
	    }    
    })();

    $('#show_serviceStar').text($(obj).attr('data_starService')+"星");  
    $('#show_dutyStar').text($(obj).attr('data_starZeren')+"星");
    $('#show_timeStar').text($(obj).attr('data_starTime')+"星");    
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();

    $.ajax({//判断订单是否来之巴蜀快医
		'url':'../kyinterface/kyInterfaceAction.action',
		'async':false,
		'data':{
			'action':'sckyOrder',
			'orderNum':$(obj).attr('data_num')
		},
		success:function(result){
			if(result){
				$("#show_kyinterface").prev().show();
				$("#show_kyinterface").show();
			}else{
				$("#show_kyinterface").prev().hide();
				$("#show_kyinterface").hide();
			}
		}
	});
}
function contral_info(){
	if($('#show_orderNum').attr('statu') != '医生取消' && $('#show_orderNum').attr('doctorName') != ''){
		alert('只有状态为‘医生取消’的订单才可以修改！');
		return false;
	}
	
	var doctorId = $('#show_doctorName_se option').filter(function(){
		return $(this).get(0).selected;
	}).attr('zjid');
	
	$.ajax({
		'url':'orderInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'update',
			'updateId':$('#show_orderNum').attr('zjid'),
			'status':$('#show_status').val(),
			'doctorId':doctorId
		},
		success:function(result){
			if(result.mes.toString().trim() == ''){
				return false;
			}
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allOrderInfo == null){
				alert('没有数据！');
				return false;			
			}else if(result.mes == '成功'){
				/*edit_BSKY_orderinfo(doctorId);*/
				alert('修改成功！');
				$('#bottom_div').slideUp(500);
				show_tableinfo();	
			}
		}
	});	
	

}

/*function edit_BSKY_orderinfo(doctorId){
	var checkedFlag = $("#show_kyinterface:checked").length
	if(checkedFlag) {
		$.ajax({
			'url':'../kyinterface/kyInterfaceAction.action',
			'data':{
				'action':'sckyOrderEdit',
				'orderNum':$('#show_orderNum').val(),
				'doctorId':doctorId
			},
			success:function(result){
				if(result.indexOf('成功')!=-1){
					alert('巴蜀快医订单数据修改成功');
				}else{
					alert('巴蜀快医订单数据修改失败');
				}
			}
		})
	}
}*/

function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'orderInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'getAllOrderInfo'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allOrderInfo == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allOrderInfo.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(result.allOrderInfo[i])[1]+"</td>"+//订单号
					"<td style='width:15%'>"+(result.allOrderInfo[i])[4]+"</td>"+//下单时间
					"<td style='width:10%'>"+(result.allOrderInfo[i])[8]+"</td>"+//金额
					"<td style='width:10%'>"+(result.allOrderInfo[i])[29]+"</td>"+//二宝产品
					"<td style='width:10%'>"+(result.allOrderInfo[i])[28]+"</td>"+//医生姓名
					"<td style='width:10%'>"+(result.allOrderInfo[i])[21]+"</td>"+//宝宝姓名
					"<td style='width:10%'>"+(result.allOrderInfo[i])[10]+"</td>"+//订单状态
					"<td style='width:10%'>"+(result.allOrderInfo[i])[27]+"</td>"+//手机号
					"<td style='width:15%'>"+
						"<div class='td_change_button' "+
							" data_id='"+(result.allOrderInfo[i])[0]+"' "+//id
							" data_num='"+(result.allOrderInfo[i])[1]+"' "+//订单号
							" data_doctorName='"+(result.allOrderInfo[i])[28]+"' "+//医生姓名
							" data_time='"+(result.allOrderInfo[i])[4]+"' "+//下单时间
                " data_pesPokeTime='"+(result.allOrderInfo[i])[5]+" "+(result.allOrderInfo[i])[6]+"' "+//下单时间
                " data_babyName='"+(result.allOrderInfo[i])[21]+"' "+//宝宝名字
							" data_money='"+(result.allOrderInfo[i])[8]+"' "+//订单金额
							" data_phone='"+(result.allOrderInfo[i])[27]+"' "+//手机号
							" data_status='"+(result.allOrderInfo[i])[10]+"' "+//订单状态
							" data_parentName='"+(result.allOrderInfo[i])[20]+"' "+//父母名字
							" data_address='"+(result.allOrderInfo[i])[22]+(result.allOrderInfo[i])[23]+(result.allOrderInfo[i])[24]+(result.allOrderInfo[i])[25]+(result.allOrderInfo[i])[26]+"' "+//地址
							" data_serviceObject='"+(result.allOrderInfo[i])[30]+"' "+//服务项目
							" data_tag='"+(result.allOrderInfo[i])[42]+"' "+//标签
							" data_starService='"+(result.allOrderInfo[i])[33]+"' "+//服务星级
							" data_starZeren='"+(result.allOrderInfo[i])[40]+"' "+//责任星级
							" data_starTime='"+(result.allOrderInfo[i])[41]+"' "+//准时星级
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
function search_click(){
	var baby_name,doctor_name,select_product,search_status,start_time,end_time;
	
	($('.con_contral input:checkbox').get(0).checked && $.trim($('#baby_name').val()) != '') ? baby_name = $.trim($('#baby_name').val()) : baby_name = '';
	($('.con_contral input:checkbox').get(1).checked && $.trim($('#doctor_name').val()) != '') ? doctor_name = $.trim($('#doctor_name').val()) : doctor_name = '';
	($('.con_contral input:checkbox').get(2).checked && $.trim($('#select_product').val()) != '') ? select_product = $.trim($('#select_product').val()) : select_product = '';
	($('.con_contral input:checkbox').get(3).checked && $.trim($('#search_status').val()) != '') ? search_status = $.trim($('#search_status').val()) : search_status = '';
	($('.con_contral input:checkbox').get(4).checked && $.trim($('#start_time').val()) != '') ? start_time = $.trim($('#start_time').val())+" 00:00:00" : start_time = '';
	($('.con_contral input:checkbox').get(4).checked && $.trim($('#end_time').val()) != '') ? end_time = $.trim($('#end_time').val())+" 23:59:59" : end_time = '';

//	alert(baby_name);
//	alert(doctor_name);
//	alert(select_product);
//	alert(search_status);
//	alert(start_time);
//	alert(end_time);
	



	$.ajax({
		'url':'orderInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'search',
			'babyName':baby_name,
			'doctorName':doctor_name,
			'orderStatus':search_status,
			'productName':select_product,
			'startTime':start_time,
			'endTime':end_time
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allOrderInfo == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.allOrderInfo.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(result.allOrderInfo[i])[1]+"</td>"+//订单号
					"<td style='width:15%'>"+(result.allOrderInfo[i])[4]+"</td>"+//下单时间
					"<td style='width:10%'>"+(result.allOrderInfo[i])[8]+"</td>"+//金额
					"<td style='width:10%'>"+(result.allOrderInfo[i])[29]+"</td>"+//二宝产品
					"<td style='width:10%'>"+(result.allOrderInfo[i])[28]+"</td>"+//医生姓名
					"<td style='width:10%'>"+(result.allOrderInfo[i])[21]+"</td>"+//宝宝姓名
					"<td style='width:10%'>"+(result.allOrderInfo[i])[10]+"</td>"+//订单状态
					"<td style='width:10%'>"+(result.allOrderInfo[i])[27]+"</td>"+//手机号
					"<td style='width:15%'>"+
						"<div class='td_change_button' "+
							" data_id='"+(result.allOrderInfo[i])[0]+"' "+//id
							" data_num='"+(result.allOrderInfo[i])[1]+"' "+//订单号
							" data_doctorName='"+(result.allOrderInfo[i])[28]+"' "+//医生姓名
							" data_time='"+(result.allOrderInfo[i])[4]+"' "+//下单时间
                " data_pesPokeTime='"+(result.allOrderInfo[i])[5]+" "+(result.allOrderInfo[i])[6]+"' "+//下单时间
                " data_babyName='"+(result.allOrderInfo[i])[21]+"' "+//宝宝名字
							" data_money='"+(result.allOrderInfo[i])[8]+"' "+//订单金额
							" data_phone='"+(result.allOrderInfo[i])[27]+"' "+//手机号
							" data_status='"+(result.allOrderInfo[i])[10]+"' "+//订单状态
							" data_parentName='"+(result.allOrderInfo[i])[20]+"' "+//父母名字
							" data_address='"+(result.allOrderInfo[i])[22]+(result.allOrderInfo[i])[23]+(result.allOrderInfo[i])[24]+(result.allOrderInfo[i])[25]+(result.allOrderInfo[i])[26]+"' "+//地址
							" data_serviceObject='"+(result.allOrderInfo[i])[30]+"' "+//服务项目
							" data_tag='"+(result.allOrderInfo[i])[42]+"' "+//标签
							" data_starService='"+(result.allOrderInfo[i])[33]+"' "+//服务星级
							" data_starZeren='"+(result.allOrderInfo[i])[40]+"' "+//责任星级
							" data_starTime='"+(result.allOrderInfo[i])[41]+"' "+//准时星级
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();			
		}
	});	
}