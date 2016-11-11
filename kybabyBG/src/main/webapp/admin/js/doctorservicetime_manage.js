$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:20%'>序列</th>"+
			"<th style='width:20%'>名字</th>"+
			"<th style='width:20%'>时间段</th>"+
			"<th style='width:20%'>状态</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	
	$('#doc_name').val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));
	$('#doc_starttime').val($(obj).attr('data_starttime')+":00");
	$('#doc_endtime').val($(obj).attr('data_endtime')+":00");
	$('#doc_status option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
	
}
function show_tableinfo(){
	$('#table_content').find('tr').remove();
	$.ajax({
		'url':'timeInitHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes == '成功'){
				for(var i=0;i < result.allTtimeInit.length;i++){
					$('#table_content').append(
						"<tr onclick='click_light(this)'>"+
							"<td style='width: 20%'>"+(i+1)+"</td>"+
							"<td style='width: 20%'>"+result.allTtimeInit[i].name+"</td>"+
							"<td style='width: 20%'>"+result.allTtimeInit[i].startTime+"~"+result.allTtimeInit[i].endTime+"</td>"+
							"<td style='width: 20%'>"+result.allTtimeInit[i].status+"</td>"+
							"<td style='width: 20%'>"+
								"<div data_id='"+result.allTtimeInit[i].id+"' data_name='"+result.allTtimeInit[i].name+"' data_starttime='"+result.allTtimeInit[i].startTime+"' data_endtime='"+result.allTtimeInit[i].endTime+"' data_status='"+result.allTtimeInit[i].status+"' class='td_change_button' onclick='read_info(this)'>修改</div>"+
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
	if($.trim($('#doc_name').val()) == ''){
		alert('请输入名字');
		return false;
	}else if($.trim($('#doc_starttime').val()) == '' || $.trim($('#doc_endtime').val()) == ''){
		alert('请选择时间段');
		return false;
	}else if(parseInt($.trim($('#doc_endtime').val()).replace(':00','')) < parseInt($.trim($('#doc_starttime').val()).replace(':00',''))){
		alert('请选择正确的时间段');
		return false;		
	}else{
		$.ajax({
			'url':'timeInitHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'updateId':$('#doc_name').attr('zjid'),
				'name':$.trim($('#doc_name').val()),
				'startTime':$('#doc_starttime').val().replace(':00',''),
				'endTime':$('#doc_endtime').val().replace(':00',''),
				'status':$('#doc_status').val()
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
