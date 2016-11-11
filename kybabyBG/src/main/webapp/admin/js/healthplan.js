// JavaScript Document
$(function(){
    $('#table_title').append(
            "<tr>"+
            "<th style='width:20%'>编号</th>"+
            "<th style='width:20%'>健康计划名称</th>"+
            "<th style='width:40%'>项目</th>"+
            "<th style='width:20%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
            "</tr>"
    );
    show_tableinfo();
		$.ajax({
			'url':'healthPlanHandle.action',
			'type':'POST',
			'async':false,
			'data':{
				'action':'getHealthPath'
			},
			success:function(result){
				$('#show_product').empty();
				if(result.mes != '成功'){
					alert('参数错误！');
					return false;
				}else if(result.allHealthPath == null){
					alert('没有数据！');
					return false;
				}else{
					for(var i=0;i<result.allHealthPath.length;i++){
						$('#show_product').append(
							"<div class='box'>"+
								"<input type='checkbox' id='health_recodr"+i+"' zjid='"+(result.allHealthPath[i])[0]+"' class='check'>"+
								"<label for='health_recodr"+i+"'>"+(result.allHealthPath[i])[1]+"</label>"+
							"</div>"
						);
					}
				}
			}
		});
});
function add_info(){
		$('#bottom_div_con input').val('');
		for(var i=0;i < $('#show_product input').length;i++){
			$('#show_product input').eq(i).get(0).checked = false;
		}    
    (function(){
	    if($('#show_project').attr('way') != 'add'){
		 		$.ajax({
					'url':'healthPlanHandle.action',
					'type':'POST',
					'async':false,
					'data':{
						'action':'getUnboundItem'
					},
					success:function(result){
						$('#show_project').empty();
						if(result.mes != '成功'){
							alert('参数错误！');
							return false;
						}else if(result.unboundItemOfPlan == null){
							alert('没有数据！');
							return false;
						}else{
							$('#show_project').attr('way','add');
							for(var i=0;i<result.unboundItemOfPlan.length;i++){
								$('#show_project').append(
									"<option zjid='"+(result.unboundItemOfPlan[i])[0]+"'>"+(result.unboundItemOfPlan[i])[1]+"</option>"
								);
							}
						}
					}
				});   
	    }    
    })();


    $('#bottom_div').slideDown(500);
    $('#button_add_click').show();
    $('#button_update_click').hide();
}
//读取信息
function read_info(obj){
	(function(){
		if($('#show_project').attr('way') != 'update'){
			$.ajax({
				'url':'healthPlanHandle.action',
				'type':'POST',
				'async':false,
				'data':{
					'action':'getAllItem'
				},
				success:function(result){
					$('#show_project').empty();
					if(result.mes != '成功'){
						alert('参数错误！');
						return false;
					}else if(result.allItemOfPlan == null){
						alert('没有数据！');
						return false;
					}else{
						$('#show_project').attr('way','update');
						for(var i=0;i<result.allItemOfPlan.length;i++){
							$('#show_project').append(
								"<option zjid='"+(result.allItemOfPlan[i])[0]+"'>"+(result.allItemOfPlan[i])[1]+"</option>"
							);
						}
					}
				}
			});	
		}	
	})(); 
  $('#show_name').val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));//名字和id
  var is_1 = $('#show_project option').filter(function(){//项目
  	return $(this).text() == $(obj).attr('data_project');
  }).get(0);
  if(is_1 != undefined){
  	is_1.selected = true;
  }
  $('#show_status option').filter(function(){//状态
  	return $(this).text() == $(obj).attr('data_status');
  }).get(0).selected = true;
  
  //自执行选中函数
	(function(){
		for(var i=0;i < $('#show_product input').length;i++){
			$('#show_product input').eq(i).get(0).checked = false;
		}
    var way_arr_str = $(obj).attr('data_way');
    var way_arr;
    if(way_arr_str.indexOf('::') == -1){
    	way_arr = way_arr_str;
    	for(var i=0;i < $('#show_product .box').length;i++){
    		var x = $('#show_product .box').eq(i).find('label').filter(function(){
    			return $(this).text() == way_arr;
    		}).prev().get(0);
 				if(x != undefined){
					x.checked = true;
				}   		
    	}
    }else{
    	way_arr = way_arr_str.split('::');
    	for(var i=0;i < way_arr.length;i++){
				var x = $('#show_product label').filter(function(){
					return $(this).text() == way_arr[i];
				}).prev().get(0);
				if(x != undefined){
					x.checked = true;
				}
    	}
    }		
	})();

  $('#bottom_div').slideDown(500);
  $('#button_add_click').hide();
  $('#button_update_click').show();    
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'healthPlanHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allHealthPlan == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allHealthPlan.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:20%'>"+(i+1)+"</td>"+
					"<td style='width:20%'>"+(result.allHealthPlan[i])[1]+"</td>"+
					"<td style='width:40%'>"+(result.allHealthPlan[i])[5]+"</td>"+
					"<td style='width:20%'>"+
						"<div class='td_change_button' "+
							" data_id='"+(result.allHealthPlan[i])[0]+"' "+
							" data_name='"+(result.allHealthPlan[i])[1]+"' "+
							" data_project='"+(result.allHealthPlan[i])[5]+"' "+
							" data_status='"+(result.allHealthPlan[i])[4]+"' "+
							" data_way='"+(result.allHealthPlan[i])[6]+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
function contral_info(type){

	if($('#show_project').val() == null){
		alert('请选择项目');
		return false;
	}else if($('#show_project').val().trim() == ''){
		alert('请填写健康计划名称');
		return false;
	}
	var x = $('#show_product input');
	var x_str = '';
	for(var i=0;i<x.length;i++){
		if(x.eq(i).get(0).checked){
			x_str = x_str + "::" + x.eq(i).attr('zjid');
		}
	}
	x_str = x_str.substring(2);
	if(x_str.length == 0){
		alert('请选择健康计划路径');
		return false;
	}
	var itemId = '';
	for(var i=0;i < $('#show_project option').length;i++){
		var itemId = $('#show_project option').filter(function(){
			return $(this).get(0).selected;
		}).attr('zjid');
	}
	$.ajax({
		'url':'healthPlanHandle.action',
		'type':'POST',
		'data':{
			'action':type,
			'updateId':$('#show_name').attr('zjid'),
			'itemId':itemId,
			'name':$('#show_name').val(),
			'status':$('#show_status').val(),
			'healthPathIds':x_str
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('参数错误！');
				return false;
			}else{
				if(type == "add"){
					alert('添加成功！');
				}else if(type == 'update'){
					alert('修改成功！');
				}
				$('#bottom_div').slideUp(500);
				show_tableinfo();
			}			
		}
	});
}