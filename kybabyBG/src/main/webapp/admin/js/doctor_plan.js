$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:15%'>序列</th>"+
			"<th style='width:15%'>医生姓名</th>"+
			"<th style='width:15%'>性别</th>"+
			"<th style='width:15%'>手机号</th>"+
			"<th style='width:20%'>地址</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
});


//读取信息
function read_info(obj){
	$('#service_time tr').not(':first').remove();
  $('#show_name').val($(obj).attr('data_name'));
  $('#show_phone').val($(obj).attr('data_phone'));
  $('#show_address').val($(obj).attr('data_address'));
	if($('#start_time').val().trim() == '' || $('#end_time').val().trim() == ''){
		alert("请先选择起止日期");
	}else{
		$.ajax({
			'url':'doctorTimePlan.action',
			'type':'POST',
			'async':false,
			'data':{
				'action':'getDetailPlan',
				'doctorId':$(obj).attr('data_id'),
				'startDate':$('#start_time').val().trim(),
				'endDate':$('#end_time').val().trim()
			},
			success:function(result){
				if(result.doctorServiceTime == null){
					return false;
				}
				for(var i=0;i < result.doctorServiceTime.length;i++){
					if((result.doctorServiceTime[i])[3].indexOf('::') == -1){
						$('#service_time').append(
							"<tr id='tr_time_"+i+"'>"+
								"<td width='4%'>"+(result.doctorServiceTime[i])[2].toString().substring(5).replace('-','/')+"</td>"+
			         "</tr>"
						);
						for(var j=0;j < 12;j++){
							if(((result.doctorServiceTime[i])[3] - 9) == j){
								$("#tr_time_"+i).append("<td class='red' width='8%'>Y</td>");
							}else{
								$("#tr_time_"+i).append("<td width='8%'></td>");
							}
						}					
					}else{
						$('#service_time').append(
							"<tr>"+
								"<td width='4%'>"+(result.doctorServiceTime[i])[2].toString().substring(5).replace('-','/')+"</td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
								"<td class='arr_time_"+i+"' width='8%'></td>"+
			         "</tr>"
						);					
						var arr = (result.doctorServiceTime[i])[3].toString().split('::');
						for(var m=0;m < arr.length;m++){
							$(".arr_time_"+i).eq(arr[m]-9).text('Y').addClass('red');
						}				
					}
			
				}
		
			}
		});	
	}


  $('#bottom_div').slideDown(500);
  $('#button_add_click').hide();
  $('#button_update_click').hide();
}

                    
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'doctorTimePlan.action',
		'type':'POST',
		'data':{
			'action':'getBriefDoctor',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.briefInfoOfDoctorPlan == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.briefInfoOfDoctorPlan.length;i++){
				$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width: 15%'>"+(i+1)+"</td>"+
						"<td style='width: 15%'>"+(result.briefInfoOfDoctorPlan[i])[1]+"</td>"+
						"<td style='width: 15%'>"+(result.briefInfoOfDoctorPlan[i])[2]+"</td>"+
						"<td style='width: 15%'>"+(result.briefInfoOfDoctorPlan[i])[4]+"</td>"+
						"<td style='width: 20%'>"+(result.briefInfoOfDoctorPlan[i])[5]+(result.briefInfoOfDoctorPlan[i])[6]+(result.briefInfoOfDoctorPlan[i])[7]+(result.briefInfoOfDoctorPlan[i])[8]+(result.briefInfoOfDoctorPlan[i])[9]+"</td>"+
						"<td style='width: 20%'>"+
							"<div class='td_change_button' onclick='read_info(this)' "+
								"data_id='"+(result.briefInfoOfDoctorPlan[i])[0]+"'"+
								"data_name='"+(result.briefInfoOfDoctorPlan[i])[1]+"'"+
								"data_phone='"+(result.briefInfoOfDoctorPlan[i])[4]+"'"+
								"data_address='"+(result.briefInfoOfDoctorPlan[i])[5]+(result.briefInfoOfDoctorPlan[i])[6]+(result.briefInfoOfDoctorPlan[i])[7]+(result.briefInfoOfDoctorPlan[i])[8]+(result.briefInfoOfDoctorPlan[i])[9]+"'"+
							"'>查看</div>"+
						"</td>"+
					"</tr>"
				);
			}
			reset_con_page();	
		},
	});
}
