$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:20%'>序列</th>"+
			"<th style='width:20%'>健康计划路径名称</th>"+
      "<th style='width:20%'>状态</th>"+
			"<th style='width:20%'></th>"+
			"<th style='width:20%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	show_tableinfo();
});


//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    
    $('#show_name').val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));
    var data_statusObj = $('#show_status option').filter(function(){
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0);
    if(data_statusObj != undefined){
    	data_statusObj.selected = true;
    }
    
    var sex = $('#sex option').filter(function(){
    	return $(this).text() == $(obj).attr('sex');
    }).get(0);
    if(sex != undefined){
    	sex.selected = true;
    }
    
    var isJudgeAge = $('#isJudgeAge option').filter(function(){
    	return $(this).text() == $(obj).attr('isJudgeAge');
    }).get(0);
    if(isJudgeAge != undefined){
    	isJudgeAge.selected = true;
    }
    
    var isJudgeSex = $('#isJudgeSex option').filter(function(){
    	return $(this).text() == $(obj).attr('isJudgeSex');
    }).get(0);
    if(isJudgeSex != undefined){
    	isJudgeSex.selected = true;
    }
   
    $('#comments').val($(obj).attr('comments'));
    $('#minAge').val($(obj).attr('minAge'));
    $('#maxAge').val($(obj).attr('maxAge'));
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'healthPathHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allHealthPath == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allHealthPath.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:20%'>"+(i+1)+"</td>"+
					"<td style='width:20%'>"+(result.allHealthPath[i].healthPathName)+"</td>"+
					"<td style='width:20%'>"+(result.allHealthPath[i].healthPathStatus)+"</td>"+
					"<td style='width:20%'></td>"+
					"<td style='width:20%'>"+
						"<div class='td_change_button' "+
							" data_id='"+(result.allHealthPath[i].id)+"' "+
							" data_name='"+(result.allHealthPath[i].healthPathName)+"' "+
							" data_status='"+(result.allHealthPath[i].healthPathStatus)+"' "+
							" comments='"+(result.allHealthPath[i].comments)+"' "+
							" minAge='"+(result.allHealthPath[i].minAge)+"' "+
							" maxAge='"+(result.allHealthPath[i].maxAge)+"' "+
							" sex='"+(result.allHealthPath[i].sex)+"' "+
							" isJudgeAge='"+(result.allHealthPath[i].isJudgeAge)+"' "+
							" isJudgeSex='"+(result.allHealthPath[i].isJudgeSex)+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
function add_info(){
	$('#bottom_div_con input').val('');
	$('#bottom_div').slideDown(500);
    $('#button_add_click').show();
    $('#button_update_click').hide();
}
function contral_info(type){
	if($('#show_name').val().trim() == ''){
		alert('请填写健康计划路径名称');
		return false;
	}
	if($('#comments').val().trim() == ''){
		alert('请填写健康指导内容');
		return false;
	}
	$.ajax({
		'url':'healthPathHandle.action',
		'type':'POST',
		'data':{
			'action':type,
			'updateId':$('#show_name').attr('zjid'),
			'status':$('#show_status').val(),
			'comments':$('#comments').val(),
			'minAge':$('#minAge').val(),
			'maxAge':$('#maxAge').val(),
			'sex':$('#sex').val(),
			'isJudgeAge':$('#isJudgeAge').val(),
			'isJudgeSex':$('#isJudgeSex').val(),
			'name':$('#show_name').val().trim()
		},
		success:function(result){
			if(result.mes == '成功'){
				if(type == 'add'){
					alert('添加成功！');
				}else if(type == 'update'){
					alert('修改成功!');
				}
				$('#bottom_div').slideUp(500);
				show_tableinfo();
			}else{
				alert(result.mes);
			}
		}
	});
}