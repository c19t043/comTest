$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:15%'>结果名称</th>"+
			"<th style='width:15%'>对应项目</th>"+
			"<th style='width:15%'>结果类型</th>"+
			"<th style='width:10%'>结果单位</th>"+
			"<th style='width:15%'>结果选项</th>"+
			"<th style='width:20%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	show_tableinfo();
	$('#show_isNeedRemind,#show_type').change(function(){
		show_hide();
	});
});

function add_info(){
	show_hide();
	$('#show_object').empty();
	$.ajax({
		'url':'itemResultHandle.action',
		'type':'POST',
		'async':false,
		'data':{
			'action':'getUnuseItem'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('参数错误！');
				return false;
			}else if(result.unUseItem == null){
				alert('没有数据！');
				return false;
			}else{
				for(var i=0;i<result.unUseItem.length;i++){
					$('#show_object').append(
						"<option zjid='"+(result.unUseItem[i])[0]+"'>"+(result.unUseItem[i])[1]+"</option>"
					);
				}
			}
		}
	});
	$('#bottom_div_con input').val('');
    $('#bottom_div').slideDown(500);
    $('#button_add_click').show();
    $('#button_update_click').hide();
}


function contral_info(type){
	var itemId = $('#show_object option').filter(function(){
		return $(this).get(0).selected;
	}).attr('zjid');
	var MaxRemind;
	var MinRemind;
	MaxRemind=$('#show_maxRemind').val().trim();
	MinRemind=$('#show_minRemind').val().trim();
	if(""==$('#show_maxRemind').val().trim())
		{
		  MaxRemind=$('#show_yes_remind').val().trim();
		}
	if(""==$('#show_minRemind').val().trim())
		{
		  MinRemind=$('#show_no_remind').val().trim();
		}
	$.ajax({
		'url':'itemResultHandle.action',
		'type':'POST',
		'data':{
			'action':type,
			'updateId':$('#show_name').attr('zjid'),
			'itemId':itemId,
			'name':$('#show_name').val().trim(),
			'resultUnit':$('#show_unit').val().trim(),
			'resultType':$('#show_type').val(),
			'resultRemarkFlag':$('#resultRemarkFlag').val(),
			'options':$('#show_result').val().trim(),
			'isNeedRemind':$('#show_isNeedRemind').val(),
			'maxValue':$('#show_max').val().trim(),
			'maxValueRemind':MaxRemind,
			'minValue':$('#show_min').val().trim(),
			'minValueRemind':MinRemind
		},
		success:function(result){
			if(result.mes == '成功'){
				if(type == 'add'){
					alert('添加成功！');
				}else if(type == 'update'){
					alert('修改成功！');
				}
				
				$('#bottom_div').slideUp(500);
				show_tableinfo();
			}else{
				alert(result.mes);
			}
		}
	});

}


function show_hide(){
	$('#bottom_div_con input').show().prev().show();
	$('#bottom_div_con select').show().prev().show();
	if($('#show_type').val() == '选择' && $('#show_isNeedRemind').val() == 'Y'){
		$('#resultRemarkFlag,#show_result,#show_max,#show_min,#show_maxRemind,#show_minRemind').prev().hide();
		$('#resultRemarkFlag,#show_result,#show_max,#show_min,#show_maxRemind,#show_minRemind').hide();
	}else if($('#show_type').val() == '选择' && $('#show_isNeedRemind').val() == 'N'){
		$('#show_max,#show_min,#show_maxRemind,#show_minRemind,#show_yes_remind,#show_no_remind').prev().hide();
		$('#show_max,#show_min,#show_maxRemind,#show_minRemind,#show_yes_remind,#show_no_remind').hide();
	}else if($('#show_type').val() == '输入' && $('#show_isNeedRemind').val() == 'Y'){
		$('#resultRemarkFlag,#show_result,#show_yes_remind,#show_no_remind').prev().hide();
		$('#resultRemarkFlag,#show_result,#show_yes_remind,#show_no_remind').hide();
	}else if($('#show_type').val() == '输入' && $('#show_isNeedRemind').val() == 'N'){
		$('#resultRemarkFlag,#show_result,#show_max,#show_min,#show_maxRemind,#show_minRemind,#show_yes_remind,#show_no_remind').prev().hide();
		$('#resultRemarkFlag,#show_result,#show_max,#show_min,#show_maxRemind,#show_minRemind,#show_yes_remind,#show_no_remind').hide();
	}else{
	}
}

//读取信息
function read_info(obj){
	$('#show_object').empty();
	$.ajax({
		'url':'itemResultHandle.action',
		'type':'POST',
		'async':false,
		'data':{
			'action':'getAllUsedItem'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('参数错误！');
				return false;
			}else if(result.allUsedItem == null){
				alert('没有数据！');
				return false;
			}else{
				for(var i=0;i<result.allUsedItem.length;i++){
					$('#show_object').append(
						"<option zjid='"+(result.allUsedItem[i])[0]+"'>"+(result.allUsedItem[i])[1]+"</option>"
					);
				}
			}
		}
	});


		$('#show_name').val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));//名字和id
		var a = $('#show_object option').filter(function(){//对应项目
			return $(this).text() == $(obj).attr('data_object');
		}).get(0);
		if(a != undefined){
			a.selected = true;
		}
		var b = $('#show_type option').filter(function(){//结果类型
			return $(this).text() == $(obj).attr('data_type');
		}).get(0);
		if(b != undefined){
			b.selected = true;
		}
		$('#show_unit').val($(obj).attr('data_unit'));//单位
		var c = $('#show_isNeedRemind option').filter(function(){//是否需要提示
			return $(this).text() == $(obj).attr('data_isNeedRemind');
		}).get(0);
		if(c != undefined){
			c.selected = true;
		}		
		$('#show_result').val($(obj).attr('data_option'));//结果选项
		$('#resultRemarkFlag').val($(obj).attr('resultRemarkFlag'));//结果输入备注标示
		$('#show_max').val($(obj).attr('data_max'));
		$('#show_min').val($(obj).attr('data_min'));
		if($(obj).attr('data_isNeedRemind')=='Y'&&$(obj).attr('data_type')=='选择')
		{
			$('#show_yes_remind').val($(obj).attr('data_maxRemind'));
			$('#show_no_remind').val($(obj).attr('data_minRemind'));	
		}
		else
			{
		$('#show_maxRemind').val($(obj).attr('data_maxRemind'));
		$('#show_minRemind').val($(obj).attr('data_minRemind'));
			}
		
		show_hide();
	
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'itemResultHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allItemResult == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allItemResult.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:15%'>"+(result.allItemResult[i])[2]+"</td>"+
					"<td style='width:15%'>"+(result.allItemResult[i])[13]+"</td>"+
					"<td style='width:15%'>"+(result.allItemResult[i])[4]+"</td>"+
					"<td style='width:10%'>"+(result.allItemResult[i])[3]+"</td>"+
					"<td style='width:15%'>"+(result.allItemResult[i])[5]+"</td>"+
					"<td style='width:20%'>"+
						"<div class='td_change_button' "+
							" data_id='"+(result.allItemResult[i])[0]+"' "+//
							" data_name='"+(result.allItemResult[i])[2]+"' "+//
							" data_unit='"+(result.allItemResult[i])[3]+"' "+//
							" data_type='"+(result.allItemResult[i])[4]+"' "+//
							" data_option='"+(result.allItemResult[i])[5]+"' "+
							" data_isNeedRemind='"+(result.allItemResult[i])[7]+"' "+
							" data_max='"+(result.allItemResult[i])[8]+"' "+
							" data_maxRemind='"+(result.allItemResult[i])[9]+"' "+
							" data_min='"+(result.allItemResult[i])[10]+"' "+
							" data_minRemind='"+(result.allItemResult[i])[11]+"' "+
							" resultRemarkFlag='"+(result.allItemResult[i])[12]+"' "+
							" data_object='"+(result.allItemResult[i])[13]+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}