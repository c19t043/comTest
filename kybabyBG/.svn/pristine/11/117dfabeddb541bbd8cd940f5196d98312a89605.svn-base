$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>金额</th>"+
			"<th style='width:20%'>补贴类型</th>"+
			"<th style='width:30%'>起止日期</th>"+
			"<th style='width:10%'>累计单数</th>"+
			"<th style='width:10%'>状态</th>"+
			"<th style='width:10%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	show_tableinfo();
	sub_type_change();
});

function add_information(){
	if($.trim($('#sub_money').val()) == '' || isNaN($.trim($('#sub_money').val()))){
		alert('请输入正确格式的金额');
		return false;
	}else if($.trim($('#sub_starttime').val()) == '' || $.trim($('#sub_endtime').val()) == ''){
		alert('请选择起止时间');
		return false;
	}else if($.trim($('#sub_endtime').val()).replace('-','').replace('-','') < $.trim($('#sub_starttime').val()).replace('-','').replace('-','')){
		alert('请选择正确的起止时间');
		return false;		
	}else if($('#sub_type').val() == '活跃度补贴'){
		if($.trim($('#sub_num').val()) == '' || isNaN($.trim($('#sub_num').val()))){
			alert('请输入正确格式的单数');
			return false;	
		}else{
			$.ajax({
				'url':'subsidyHandle.action',
				'type':'POST',
				'data':{
					'action':'add',
					'updateId':$('#sub_money').attr('zjid'),
					'method':$('#sub_type').val(),
					'startDate':$('#sub_starttime').val(),
					'endDate':$('#sub_endtime').val(),
					'amount':$('#sub_money').val(),
					'accumulativeNum':$('#sub_num').val(),
					'status':$('#sub_status').val()
				},
				success:function(result){
					if(result.mes == '成功'){
						alert(result.mes);
						show_tableinfo();
						$('#bottom_div').slideUp(500);
					}else{
						alert(result.mes);
					}
				}
			});		
		}
	}else{
		$.ajax({
			'url':'subsidyHandle.action',
			'type':'POST',
			'data':{
				'action':'add',
				'updateId':$('#sub_money').attr('zjid'),
				'method':$('#sub_type').val(),
				'startDate':$('#sub_starttime').val(),
				'endDate':$('#sub_endtime').val(),
				'amount':$('#sub_money').val(),
				'accumulativeNum':$('#sub_num').val(),
				'status':$('#sub_status').val()
			},
			success:function(result){
				if(result.mes == '成功'){
					alert(result.mes);
					show_tableinfo();
					$('#bottom_div').slideUp(500);
				}else{
					alert(result.mes);
				}
			}
		});
	}	
}

function sub_type_change(){
	if($(sub_type).val() == '交通补贴'){
		$('#sub_num').hide().prev().hide();
	}else if($(sub_type).val() == '活跃度补贴'){
		$('#sub_num').show().prev().show();
	}
}

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	
	$('#sub_money').val($(obj).attr('data_money')).attr('zjid',$(obj).attr('data_id'));
	$('#sub_starttime').val($(obj).attr('data_starttime'));
	$('#sub_endtime').val($(obj).attr('data_endtime'));
	$('#sub_type option').filter(function(){
		return $(this).text() == $(obj).attr('data_type');
	}).get(0).selected = true;
	if($(sub_type).val() == '交通补贴'){
		$('#sub_num').hide().prev().hide();
	}else if($(sub_type).val() == '活跃度补贴'){
		$('#sub_num').show().prev().show();
	}
	$('#sub_type').val($(obj).attr('data_type'));
	$('#sub_num').val($(obj).attr('data_num'));
	$('#sub_status option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
}

function add_info() {
	$('#bottom_div_con').find('input').val('');
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
	
}

function show_tableinfo(){
	$('#table_content').find('tr').remove();
	$.ajax({
		'url':'subsidyHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes == '成功'){
				for(var i=0;i < result.allSubsidy.length;i++){
					$('#table_content').append(
						"<tr onclick='click_light(this)'>"+
							"<td style='width: 10%'>"+(i+1)+"</td>"+
							"<td style='width: 10%'>"+result.allSubsidy[i].amount+"元</td>"+
							"<td style='width: 20%'>"+result.allSubsidy[i].subsidyMethod+"</td>"+
							"<td style='width: 30%'>"+result.allSubsidy[i].startDate+"~"+result.allSubsidy[i].endDate+"</td>"+
							"<td style='width: 10%'>"+result.allSubsidy[i].accumulativeNum+"</td>"+
							"<td style='width: 10%'>"+result.allSubsidy[i].subsidyStatus+"</td>"+
							"<td style='width: 10%'>"+
								"<div data_id='"+result.allSubsidy[i].id+"' data_money='"+result.allSubsidy[i].amount+"' data_starttime='"+result.allSubsidy[i].startDate+"' data_endtime='"+result.allSubsidy[i].endDate+"' data_type='"+result.allSubsidy[i].subsidyMethod+"' data_num='"+result.allSubsidy[i].accumulativeNum+"' data_status='"+result.allSubsidy[i].subsidyStatus+"' class='td_change_button' onclick='read_info(this)'>修改</div>"+
							"</td>"+
						"</tr>"
					);			
				}	
				reset_con_page();		
			}
		}
	});
}

function update_information(){
	if($.trim($('#sub_money').val()) == '' || isNaN($.trim($('#sub_money').val()))){
		alert('请输入正确格式的金额');
		return false;
	}else if($.trim($('#sub_starttime').val()) == '' || $.trim($('#sub_endtime').val()) == ''){
		alert('请选择起止时间');
		return false;
	}else if($.trim($('#sub_endtime').val()).replace('-','').replace('-','') < $.trim($('#sub_starttime').val()).replace('-','').replace('-','')){
		alert('请选择正确的起止时间');
		return false;		
	}else if($('#sub_type').val() == '活跃度补贴'){
		if($.trim($('#sub_num').val()) == '' || isNaN($.trim($('#sub_num').val()))){
			alert('请输入正确格式的单数');
			return false;	
		}else{
			$.ajax({
				'url':'subsidyHandle.action',
				'type':'POST',
				'data':{
					'action':'update',
					'updateId':$('#sub_money').attr('zjid'),
					'method':$('#sub_type').val(),
					'startDate':$('#sub_starttime').val(),
					'endDate':$('#sub_endtime').val(),
					'amount':$('#sub_money').val(),
					'accumulativeNum':$('#sub_num').val(),
					'status':$('#sub_status').val()
				},
				success:function(result){
					if(result.mes == '成功'){
						alert(result.mes);
						show_tableinfo();
						$('#bottom_div').slideUp(500);
					}else{
						alert(result.mes);
					}
				}
			});		
		}
	}else{
		$.ajax({
			'url':'subsidyHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'updateId':$('#sub_money').attr('zjid'),
				'method':$('#sub_type').val(),
				'startDate':$('#sub_starttime').val(),
				'endDate':$('#sub_endtime').val(),
				'amount':$('#sub_money').val(),
				'accumulativeNum':$('#sub_num').val(),
				'status':$('#sub_status').val()
			},
			success:function(result){
				if(result.mes == '成功'){
					alert(result.mes);
					show_tableinfo();
					$('#bottom_div').slideUp(500);
				}else{
					alert(result.mes);
				}
			}
		});
	}	
}
