$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序号</th>"+
			"<th style='width:10%'>省份</th>"+
			"<th style='width:10%'>市/州</th>"+
			"<th style='width:10%'>区/县</th>"+
			"<th style='width:20%'>名称</th>"+
			"<th style='width:20%'>地址</th>"+
			"<th style='width:10%'>级别</th>"+
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