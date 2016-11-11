$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:15%'>提交日期</th>"+
			"<th style='width:10%'>宝宝姓名</th>"+
			"<th style='width:10%'>症状标签</th>"+
			"<th style='width:10%'>是否结束</th>"+
			"<th style='width:10%'>医生姓名</th>"+
			"<th style='width:15%'>更新日期</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
	$.ajax({
		'url':'symptomTagHandle.action',
		'type':'POST',
		'data':{
			'action':'getSymptomTagIdAndName',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.symptomTagIdAndName == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.symptomTagIdAndName.length;i++){
				$('#select_tag').append(
					"<option>"+(result.symptomTagIdAndName[i])[1]+"</option>"
				);
			}
		},
	});
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'consultDetail.action',
		'type':'POST',
		'data':{
			'action':'getAllConsult',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allConsult == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allConsult.length;i++){
				$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width: 10%'>"+(i+1)+"</td>"+
						"<td style='width: 15%'>"+(result.allConsult[i])[6]+"</td>"+
						"<td style='width: 10%'>"+(result.allConsult[i])[14]+"</td>"+
						"<td style='width: 10%'>"+(result.allConsult[i])[16].toString().replace(/::/g,',')+"</td>"+
						"<td style='width: 10%'>"+(result.allConsult[i])[11]+"</td>"+
						"<td style='width: 10%'>"+(result.allConsult[i])[15]+"</td>"+
						"<td style='width: 15%'>"+(result.allConsult[i])[18]+"</td>"+
						"<td style='width: 20%'><div class='td_change_button'  onclick=\"window.open('chat_records.html?getDetailConsultion&&"+(result.allConsult[i])[17]+"')\">查看</div></td>"+
					"</tr>"
				);
			}
			reset_con_page();	
		},
	});
}
function search_click(){
	$('#table_content').empty();
	var start_time,end_time,select_tag,search_doctorName,search_babyName;
	($('.con_contral input:checkbox').get(0).checked && $.trim($('#start_time').val()) != '') ? start_time = $.trim($('#start_time').val())+" 00:00:00" : start_time = '';
	($('.con_contral input:checkbox').get(0).checked && $.trim($('#end_time').val()) != '') ? end_time = $.trim($('#end_time').val())+" 23:59:59" : end_time = '';
	$('.con_contral input:checkbox').get(1).checked ? select_tag = $.trim($('#select_tag').val()) : select_tag = '';
	$('.con_contral input:checkbox').get(2).checked ? search_doctorName = $.trim($('#search_doctorName').val()) : search_doctorName = '';
	$('.con_contral input:checkbox').get(3).checked ? search_babyName = $.trim($('#search_babyName').val()) : search_babyName = '';
	
	$.ajax({
		'url':'consultDetail.action',
		'type':'POST',
		'data':{
			'action':'search',
			'startTime':start_time,
			'endTime':end_time,
			'tagName':select_tag,
			'babyName':search_babyName,
			'doctorName':search_doctorName
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.searchList == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.searchList.length;i++){
				$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width: 10%'>"+(i+1)+"</td>"+
						"<td style='width: 15%'>"+(result.searchList[i])[6]+"</td>"+
						"<td style='width: 10%'>"+(result.searchList[i])[14]+"</td>"+
						"<td style='width: 10%'>"+((result.searchList[i])[16]).toString().replace(/::/g,',')+"</td>"+
						"<td style='width: 10%'>"+(result.searchList[i])[11]+"</td>"+
						"<td style='width: 10%'>"+(result.searchList[i])[15]+"</td>"+
						"<td style='width: 15%'>"+(result.searchList[i])[18]+"</td>"+
						"<td style='width: 20%'><div class='td_change_button'  onclick=\"window.open('chat_records.html?getDetailConsultion&&"+(result.searchList[i])[17]+"')\">查看</div></td>"+
					"</tr>"
				);
			}
			reset_con_page();			
		}
	});	
}
