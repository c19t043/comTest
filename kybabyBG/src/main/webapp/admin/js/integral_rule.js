$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:20%'>序列</th>"+
			"<th style='width:20%'>奖励对象</th>"+
			"<th style='width:20%'>奖励类型</th>"+
			"<th style='width:20%'>奖励规则</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
});


//读取信息
function read_info(obj){

    if($(obj).attr('data_name') == '消费返积分'){
    	$('#show1').show();
    	$('#show2').hide();
    	$('#show3').hide();
    	$('#show_1_').val($(obj).attr('data_value')).attr('zjid',$(obj).attr('data_id')).attr('data_name',$(obj).attr('data_name'));
    	$('#button_update_click').attr('mark','消费返积分');
    }else if($(obj).attr('data_name') == '积分抵现'){
    	$('#show1').hide();
    	$('#show2').show();
    	$('#show3').hide(); 
    	$('#show_2_').val($(obj).attr('data_value')).attr('zjid',$(obj).attr('data_id')).attr('data_name',$(obj).attr('data_name'));
    	$('#button_update_click').attr('mark','积分抵现');
    }else if($(obj).attr('data_name') == '积分换现金'){
     	$('#show1').hide();
    	$('#show2').hide();
    	$('#show3').show(); 
    	$('#show_3_').val($(obj).attr('data_value')).attr('zjid',$(obj).attr('data_id')).attr('data_name',$(obj).attr('data_name'));
    	$('#button_update_click').attr('mark','积分换现金');  
    }
		
		$('#show_status option').filter(function(){
			return $(this).text() == $(obj).attr('data_status');
		}).get(0).selected = true;
 
   
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();   
}
function contral_info(){
	var x;
	var y;
	if($('#button_update_click').attr('mark') == '消费返积分'){
		x = $('#show_1_').val();
		y = $('#show_1_').attr('data_name');
	}else if($('#button_update_click').attr('mark') == '积分抵现'){
		x = $('#show_2_').val();
		y = $('#show_2_').attr('data_name');
	}else if($('#button_update_click').attr('mark') == '积分换现金'){
		x = $('#show_3_').val();
		y = $('#show_3_').attr('data_name');
	}
	if(isNaN(x) || x < 0){
		alert('请填写正数金额');
		return false;
	}

	
	$.ajax({
		'url':'propertiesHandle.action',
		'type':'POST',
		'data':{
			'action':'update',
			'key':y,
			'value':x,
			'status':$('#show_status').val()
		},
		success:function(result){
			if(result.mes == '成功'){
				alert('修改成功');
				$('#bottom_div').slideUp(500);
				show_tableinfo();				
			}else{
				alert(result.mes);
			}
		}
	});
	
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'propertiesHandle.action',
		'type':'POST',
		'data':{
			'action':'all',
			'key':'pointsRule'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.propertiesList == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.propertiesList.length;i ++){	
				var user = '用户';
		 		if(result.propertiesList[i].name == '积分换现金'){
		 			user = '医生';
		 		}
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:20%'>"+(i+1)+"</td>"+
					"<td style='width:20%'>"+user+"</td>"+
					"<td style='width:20%'>"+result.propertiesList[i].name+"</td>"+
					"<td style='width:20%'>"+result.propertiesList[i].comments+"</td>"+
					"<td style='width:20%'>"+
						"<div class='td_change_button' "+
							" data_id='"+result.propertiesList[i].id+"' "+
							" data_name='"+result.propertiesList[i].name+"' "+
							" data_value='"+result.propertiesList[i].value+"' "+
							" data_status='"+result.propertiesList[i].status+"' "+					
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}