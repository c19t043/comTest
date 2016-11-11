// JavaScript Document
$(function(){
  $('#table_title').append(
    "<tr>"+
    "<th style='width:20%'>序列</th>"+
    "<th style='width:20%'>专栏标题</th>"+
    "<th style='width:20%'>状态</th>"+
    "<th style='width:20%'>评论时间</th>"+
    "<th style='width:20%'></th>"+
    "</tr>"
  );
  show_tableinfo();
  $('#button_update_click').click(function(){
  	$.ajax({
  		'url':'doctorArticleCommentHandle.action',
  		'type':'POST',
  		'data':{
  			'action':'update',
  			'articleCommetId':$('#show_status').attr('zjid'),
  			'commentsStatus':$('#show_status').val()
  		},
  		success:function(result){
  			if(result.mes == '成功'){
  				alert(result.mes);
  				$('#bottom_div').slideUp(500);
  				show_tableinfo();
  			}
  		}
  	});
  });
});

//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    $('#show_time').val($(obj).attr('data_time'));
    $('#show_title').val($(obj).attr('data_title'));
    $('#show_status').attr('zjid',$(obj).attr('data_id'));
    $('#show_status option').filter(function(){
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0).selected = true;
    $('#show_content').text($(obj).attr('data_content'));
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'doctorArticleCommentHandle.action',
		'type':'POST',
		'data':{
			'action':'getAllComment'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allArticleComment.length < 1){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allArticleComment.length;i ++){
				$('#table_content,#table_content_plus').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:20%'>"+(i+1)+"</td>"+
					"<td style='width:20%'>"+(result.allArticleComment[i])[2]+"</td>"+
					"<td style='width:20%'>"+(result.allArticleComment[i])[3]+"</td>"+
					"<td style='width:20%'>"+(result.allArticleComment[i])[4]+"</td>"+
					"<td style='width:20%'>"+
						"<div onclick='read_info(this)' class='td_change_button' "+
							" data_id='"+(result.allArticleComment[i])[0]+"' "+
							" data_title='"+(result.allArticleComment[i])[2]+"' "+
							" data_status='"+(result.allArticleComment[i])[3]+"' "+
							" data_time='"+(result.allArticleComment[i])[4]+"' "+
							" data_content='"+(result.allArticleComment[i])[5]+"' "+
						">查看</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}

function filtrate(){

	var filtrate_1 = $('#search_key').val();
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
