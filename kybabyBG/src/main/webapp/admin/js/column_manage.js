$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:15%'>序列</th>"+
			"<th style='width:20%'>日期</th>"+
			"<th style='width:15%'>医生姓名</th>"+
			"<th style='width:15%'>专栏标题</th>"+
			"<th style='width:15%'>状态</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
	$('#button_update_click').click(function(){
		
		$.ajax({
			'url':'doctorArticleHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'doctorArticleId':$('#show_id').attr('zjid'),
				'articleStatus':$('#show_status').val()
			},
			success:function(result){
				alert(result.mes);
				if(result.mes == '成功'){
					$('#bottom_div').slideUp(500);
					show_tableinfo();
				}
			}
		});	
	});
	//alert(new Date('2015-10-07').getDay());
});

//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    $('#show_time').val($(obj).attr('data_time'));
    $('#show_name').val($(obj).attr('data_name'));
    $('#show_title').val($(obj).attr('data_title'));
    $('#show_status option').filter(function(){
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0).selected = true;
    $('#show_id').attr('zjid',$(obj).attr('data_id'));
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'doctorArticleHandle.action',
		'type':'POST',
		'data':{
			'action':'getAllArticle',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allDoctorArticle == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allDoctorArticle.length;i++){
				$('#table_content,#table_content_plus').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width: 15%'>"+(i+1)+"</td>"+
						"<td style='width: 20%'>"+(result.allDoctorArticle[i])[1]+"</td>"+
						"<td style='width: 15%'>"+(result.allDoctorArticle[i])[2]+"</td>"+
						"<td style='width: 15%'>"+(result.allDoctorArticle[i])[3]+"</td>"+
						"<td style='width: 15%'>"+(result.allDoctorArticle[i])[4]+"</td>"+
						"<td style='width: 20%'>"+
							"<div class='td_change_button' onclick='read_info(this)' "+
								"data_id='"+(result.allDoctorArticle[i])[0]+"'"+
								"data_time='"+(result.allDoctorArticle[i])[1]+"'"+
								"data_name='"+(result.allDoctorArticle[i])[2]+"'"+
								"data_title='"+(result.allDoctorArticle[i])[3]+"'"+
								"data_status='"+(result.allDoctorArticle[i])[4]+"'"+
							"'>修改</div>"+
						"</td>"+
					"</tr>"
				);
			}
			reset_con_page();	
		},
	});
}
function showbox_content(obj){
	$.ajax({
		'url':'doctorArticleHandle.action',
		'async':false,
		'data':{
			'action':'getOneArticle',
			'doctorArticleId':$(obj).attr('zjid')
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误');
				return false;
			}
			if(result.oneDoctorArticle != null){
				hf_showbox(result.oneDoctorArticle);
			}
		}
	});
}
function filtrate(){

	var filtrate_1 = $('#input_1').val();

	$('#table_content').empty();
	$('#table_content_plus tbody').clone().appendTo($('#table_content'));
	$('#table_content tr').hide();
	var marknum = 1;
	for(var i=0; i < $('#table_content tr').length;i ++){
		var a = $('#table_content tr').eq(i).filter(function(){
			return $(this).find('td').eq(2).text().indexOf(filtrate_1) != -1;
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