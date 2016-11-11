$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:15%'>序列</th>"+
			"<th style='width:20%'>日期</th>"+
			"<th style='width:15%'>医生姓名</th>"+
			"<th style='width:15%'>结算金额</th>"+
			"<th style='width:15%'>结算状态</th>"+
			"<th style='width:20%'>"+
				"<div onclick='window_show()' class='th_button' style='display:inline-block;'>查看</div>&nbsp;"+
				"<div onclick='add_info()' class='th_button' style='display:inline-block;'>打印结算单</div>"+
			"</th>"+
		"</tr>"	
	);
	show_tableinfo();
	$('#button_update_click').click(function(){
		$.ajax({
			'url':'withdrawalsManage.action',
			'type':'POST',
			'data':{
				'action':'update',
				'doctorWithdrawalsId':$('#show_status').attr('zjid'),
				'applyStatus':$('#show_status').val(),
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
	$('.show_window_mask').css({
		'width':$(document).width(),
		'height':$(document).height(),
		'backgroundColor':'#fff',
		'position':'absolute',
		'top':0,
		'left':0,
		'opacity':0.7,
		'zIndex':1
	});
	$('.show_window').css({
  	'left':$(document).width()/2-500
	});
	$('#window_close_cli').click(function(){
		$('.show_window_mask,.show_window').hide();
	});
});


//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    $('#show_time').val($(obj).attr('data_time'));
    $('#show_money').val($(obj).attr('data_money')+"元");
    $('#show_name').val($(obj).attr('data_name'));
    $('#show_status').attr('zjid',$(obj).attr('data_id'));
    $('#show_status option').filter(function(){
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0).selected = true;
    
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'withdrawalsManage.action',
		'type':'POST',
		'data':{
			'action':'getAllWithdrawals',
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allDoctorWithdrawals == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allDoctorWithdrawals.length;i++){
				$('#table_content,#table_content_plus').append(
	        "<tr onclick='click_light(this)'>"+
            "<td width='15%'>"+(i+1)+"</td>"+
            "<td width='20%'>"+(result.allDoctorWithdrawals[i])[1]+"</td>"+
            "<td width='15%'>"+(result.allDoctorWithdrawals[i])[2]+"</td>"+
            "<td width='15%'>"+(result.allDoctorWithdrawals[i])[4]+"</td>"+
            "<td width='15%'>"+(result.allDoctorWithdrawals[i])[5]+"</td>"+
            "<td width='20%'>"+
            	"<div class='td_change_button' "+
            		" data_id='"+(result.allDoctorWithdrawals[i])[0]+"' "+
            		" data_time='"+(result.allDoctorWithdrawals[i])[1]+"' "+
            		" data_name='"+(result.allDoctorWithdrawals[i])[2]+"' "+
            		" data_money='"+(result.allDoctorWithdrawals[i])[4]+"' "+
            		" data_status='"+(result.allDoctorWithdrawals[i])[5]+"' "+
            	" onclick='read_info(this)'>修改</div>"+
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
	
	$('#table_content').empty();
	$('#table_content_plus tbody').clone().appendTo($('#table_content'));
	$('#table_content tr').hide();
	var marknum = 1;
	for(var i=0; i < $('#table_content tr').length;i ++){
		var a = $('#table_content tr').eq(i).filter(function(){
			if($('.check_filtrate').eq(0).get(0).checked && $('.check_filtrate').eq(1).get(0).checked){
				return $(this).find('td').eq(4).text().indexOf(filtrate_1) != -1 && $(this).find('td').eq(2).text().indexOf(filtrate_2) != -1;
			}else if($('.check_filtrate').eq(0).get(0).checked){
				return $(this).find('td').eq(4).text().indexOf(filtrate_1) != -1;
			}else if($('.check_filtrate').eq(1).get(0).checked){
				return $(this).find('td').eq(2).text().indexOf(filtrate_2) != -1;
			}else{
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
	reset_con_page($('#table_content tr:visible').length)
}
function add_info(){
	$.ajax({
		'url':'withdrawalsManage.action',
		'async':false,
		'data':{
			'action':'generalBalance'
		},
		success:function(result){
			if(result.mes == '成功'){
				alert('成功');
				window.open('pdfFile/'+result.fileName);
			}else{
				alert(result.mes);
			}
		}
	});
}
function window_show(){
	$.ajax({
		'url':'withdrawalsManage.action',
		'type':'POST',
		'data':{
			'action':'getAllBalance'
		},
		success:function(result){
			if(result.allBalance == null){
				alert('没有数据');
				return false;
			}else{
				$('#table_content_w').empty();
				for(var i=0;i < result.allBalance.length;i++){
					$('#table_content_w').append(
						"<tr onclick='click_light(this)'>"+
							"<td width='10%'>"+(i+1)+"</td>"+
							"<td width='30%'>"+result.allBalance[i].time+"</td>"+
							"<td width='30%'>"+result.allBalance[i].fileName+"</td>"+
							"<td width='30%'>"+
								"<div onclick=\"window.open('pdfFile/"+result.allBalance[i].fileName+"')\" class='td_change_button'>查看</div>"+
							"</td>"+
						"</tr>"		
					);
				}
				reset_con_page_w();
				$('.show_window_mask,.show_window').show();
			}
		}
	});
	
}


//额外分页函数
//分页处理
function reset_con_page_w(num,x){
	if(num == 'default' || num == '' || num == undefined){
		num = $('#table_content_w').find('tr').length;
	}
	var page_num = Math.ceil(num / 12);//需要多少页
	$('#con_num_w').text(num);	//		数据个数赋值
	$('#page_num_w').text(Math.ceil($('#table_content_w').find('tr').length/12));
	
	
	if(x == 'prev'){
		x = $('#now_page_num_w').text() - 1;
		if(x <= 1){
			x = 1;
		}
	}
	if(x == 'next'){
		x = parseInt($('#now_page_num_w').text()) + 1;
		if(x >= page_num){
			x = page_num;
		}	
	}
	

	if(x == '' || x == null || isNaN(x)){
		x = 1;
	}else if(x >= page_num){
		x = page_num;
	}
	
	for(var i=0; i<num ;i++){
		if(i >= (12*x-12) && i <= (12*x-1)){
			$('#table_content_w').find('tr').eq(i).show();
		}else{
			$('#table_content_w').find('tr').eq(i).hide();
		}
	}
}
//分页首页被点击
function page_click_w(num,x){
	if(x == 1){											//首页被点击
		$('#now_page_num_w').text('1');
		reset_con_page_w(num,x);
	}else if(x == 'prev'){					//上一页被点击
		x = $('#now_page_num_w').text() - 1;
		if(x <= 1){
			x = 1;
			$('#now_page_num_w').text(x);
		}else{
			x = $('#now_page_num_w').text() - 1;
			$('#now_page_num_w').text(x);
		}
		reset_con_page_w(num,x);
	}else if(x == 'next'){
		x = parseInt($('#now_page_num_w').text()) + 1;
		var page_num_all = Math.ceil($('#table_content_w').find('tr').length / 12);
		if(x >= page_num_all){
			x = page_num_all;
			$('#now_page_num_w').text(x);
		}else{
			x = parseInt($('#now_page_num_w').text()) + 1;
			$('#now_page_num_w').text(x);
		}
		reset_con_page_w(num,x);
	}else if(x == 'end'){
		$('#now_page_num_w').text(Math.ceil($('#table_content_w').find('tr').length / 12));
		reset_con_page_w(num,Math.ceil($('#table_content_w').find('tr').length / 12));
	}
}
//GO点击触发函数
function page_click_go_w(){
	var a = $.trim($('#go_page_w').val());
	if(isNaN(a) || parseInt(a) != a || a <=0){
		a = 1;
	}else if(a >= parseInt($('#page_num_w').text())){
		a = parseInt($('#page_num_w').text());
	}

	$('#go_page_w').val(a);
	$('#now_page_num_w').text(a);
	reset_con_page_w('default',a);
}