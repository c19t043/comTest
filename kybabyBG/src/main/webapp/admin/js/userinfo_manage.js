$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:15%'>手机号</th>"+
			"<th style='width:15%'>注册日期</th>"+
			"<th style='width:10%'>宝宝姓名</th>"+
			"<th style='width:10%'>家长姓名</th>"+
			"<th style='width:10%'>状态</th>"+
			"<th style='width:10%'>积分</th>"+
			"<th style='width:10%'>余额</th>"+
			"<th style='width:15%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
	$('#button_update_click').click(function(){
		$.ajax({
			'url':'userInfoHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'userId':$('#show_status').attr('zjid'),
				'status':$('#show_status').val()
			},
			success:function(result){
				if(result.mes == '成功'){
					alert('修改成功');
					$('#bottom_div').slideUp(500);
					show_tableinfo();
				}
			}
		})
	});
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#show_time').text($(obj).attr('data_time'));
	$('#show_address').text($(obj).attr('data_address'));
	$('#show_parentname').text($(obj).attr('data_parentname'));
	$('#show_phone').text($(obj).attr('data_phone'));
	$('#show_status option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
	$('#show_status').attr('zjid',$(obj).attr('data_id'));
	$('#show_babyname').text($(obj).attr('data_babyname'));
	$('#show_sex').text($(obj).attr('data_sex'));
	$('#show_birthday').text($(obj).attr('data_birthday'));
	$('#show_points').text($(obj).attr('data_points'));
	$('#show_money').text($(obj).attr('data_money'));
	
	
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'userInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'allUserInfo',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allUserInfo == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allUserInfo.length;i++){
				$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width: 5%'>"+(i+1)+"</td>"+
						"<td style='width: 15%'>"+(result.allUserInfo[i].phone)+"</td>"+
						"<td style='width: 15%'>"+(result.allUserInfo[i].registerTime).replace('T',' ')+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].babyName)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].parentName)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].userStatus)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].accountPoints)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].accountBalance)+"</td>"+
						"<td style='width: 15%'>"+
							"<div class='td_change_button' onclick='read_info(this)' "+
								" data_id='"+(result.allUserInfo[i].id)+"'"+
								" data_time='"+(result.allUserInfo[i].registerTime).replace('T',' ')+"'"+
								" data_address='"+result.allUserInfo[i].userProvince+result.allUserInfo[i].userCity+result.allUserInfo[i].userArea+result.allUserInfo[i].userStreet+result.allUserInfo[i].detailAddress+"'"+
								" data_parentname='"+(result.allUserInfo[i].parentName)+"'"+
								" data_phone='"+(result.allUserInfo[i].phone)+"'"+
								" data_status='"+(result.allUserInfo[i].userStatus)+"'"+
								" data_babyname='"+(result.allUserInfo[i].babyName)+"'"+
								" data_sex='"+(result.allUserInfo[i].sex)+"'"+
								" data_birthday='"+(result.allUserInfo[i].birthday)+"'"+
								" data_points='"+(result.allUserInfo[i].accountPoints)+"'"+
								" data_money='"+(result.allUserInfo[i].accountBalance)+"'"+
							"'>修改</div>"+		
						"</td>"+
					"</tr>"
				);
			}
			reset_con_page();	
		},
	});
}
function search_click(){
	$('#table_content').empty();
	var search_phone,search_parentName,search_babyName,search_startTime,search_endTime;
	$('.con_contral input:checkbox').get(0).checked ? search_phone = $.trim($('#search_phone').val()) : search_phone = '';
	$('.con_contral input:checkbox').get(1).checked ? search_parentName = $.trim($('#search_parentName').val()) : search_parentName = '';
	$('.con_contral input:checkbox').get(2).checked ? search_babyName = $.trim($('#search_babyName').val()) : search_babyName = '';
	($('.con_contral input:checkbox').get(3).checked && $.trim($('#search_startTime').val()) != '') ? search_startTime = $.trim($('#search_startTime').val())+" 00:00:00" : search_startTime = '';
	($('.con_contral input:checkbox').get(3).checked && $.trim($('#search_endTime').val()) != '') ? search_endTime = $.trim($('#search_endTime').val())+" 23:59:59" : search_endTime = '';
	$.ajax({
		'url':'userInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'search',
			'phone':search_phone,
			'parentName':search_parentName,
			'babyName':search_babyName,
			'startTime':search_startTime,
			'endTime':search_endTime
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allUserInfo == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allUserInfo.length;i++){
				$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width: 5%'>"+(i+1)+"</td>"+
						"<td style='width: 15%'>"+(result.allUserInfo[i].phone)+"</td>"+
						"<td style='width: 15%'>"+(result.allUserInfo[i].registerTime).replace('T',' ')+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].babyName)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].parentName)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].userStatus)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].accountPoints)+"</td>"+
						"<td style='width: 10%'>"+(result.allUserInfo[i].accountBalance)+"</td>"+
						"<td style='width: 15%'>"+
							"<div class='td_change_button' onclick='read_info(this)' "+
								" data_id='"+(result.allUserInfo[i].id)+"'"+
								" data_time='"+(result.allUserInfo[i].registerTime).replace('T',' ')+"'"+
								" data_address='"+result.allUserInfo[i].userProvince+result.allUserInfo[i].userCity+result.allUserInfo[i].userArea+result.allUserInfo[i].userStreet+result.allUserInfo[i].detailAddress+"'"+
								" data_parentname='"+(result.allUserInfo[i].parentName)+"'"+
								" data_phone='"+(result.allUserInfo[i].phone)+"'"+
								" data_status='"+(result.allUserInfo[i].userStatus)+"'"+
								" data_babyname='"+(result.allUserInfo[i].babyName)+"'"+
								" data_sex='"+(result.allUserInfo[i].sex)+"'"+
								" data_birthday='"+(result.allUserInfo[i].birthday)+"'"+
								" data_points='"+(result.allUserInfo[i].accountPoints)+"'"+
								" data_money='"+(result.allUserInfo[i].accountBalance)+"'"+
							"'>修改</div>"+		
						"</td>"+
					"</tr>"
				);
			}
			reset_con_page();			
		}
	});	
}