$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序号</th>"+
			"<th style='width:15%'>门诊日期</th>"+
			"<th style='width:15%'>门诊开始时间</th>"+
			"<th style='width:15%'>门诊结束时间</th>"+
			"<th style='width:15%'>可接待人数</th>"+
			"<th style='width:20%'>门诊单位</th>"+
			"<th style='width:15%'>"+
				"<div onclick='add_info()' class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"	
	);
});


//读取信息
function read_info(){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
}
//添加信息
function add_info(){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').show();
    $('#button_update_click').hide();
}