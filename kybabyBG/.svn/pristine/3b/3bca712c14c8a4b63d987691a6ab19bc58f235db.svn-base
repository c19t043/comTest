$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>宝宝姓名</th>"+
			"<th style='width:10%'>宝宝性别</th>"+
			"<th style='width:15%'>宝宝生日</th>"+
			"<th style='width:20%'>住址</th>"+
			"<th style='width:10%'>家长姓名</th>"+
			"<th style='width:15%'>手机号</th>"+
			"<th style='width:10%'></th>"+
		"</tr>"	
	);
	show_tableinfo();
});
function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'userInfoHandle.action',
		'type':'POST',
		'data':{
			'action':'getUserBabyInfo'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.userBabyInfo.length < 1){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.userBabyInfo.length;i ++){
				$('#table_content,#table_content_plus').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.userBabyInfo[i])[1]+"</td>"+//名字
					"<td style='width:10%'>"+(result.userBabyInfo[i])[2]+"</td>"+//性别
					"<td style='width:15%'>"+(result.userBabyInfo[i])[3]+"</td>"+//生日
					"<td style='width:20%'>"+(result.userBabyInfo[i])[4]+(result.userBabyInfo[i])[5]+(result.userBabyInfo[i])[6]+(result.userBabyInfo[i])[7]+(result.userBabyInfo[i])[8]+"</td>"+
					"<td style='width:10%'>"+(result.userBabyInfo[i])[9]+"</td>"+//家长姓名
					"<td style='width:15%'>"+(result.userBabyInfo[i])[10]+"</td>"+//电话
					"<td style='width:10%'>"+
						"<div class='td_change_button data_id="+(result.userBabyInfo[i])[0]+"' onclick=\"window.open('baby_details.html?getBasicInfo&&"+(result.userBabyInfo[i])[0]+"')\">查看</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
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
				return $(this).find('td').eq(1).text().indexOf(filtrate_1) != -1 && $(this).find('td').eq(6).text().indexOf(filtrate_2) != -1;
			}else if($('.check_filtrate').eq(0).get(0).checked){
				return $(this).find('td').eq(1).text().indexOf(filtrate_1) != -1;
			}else if($('.check_filtrate').eq(1).get(0).checked){
				return $(this).find('td').eq(6).text().indexOf(filtrate_2) != -1;
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