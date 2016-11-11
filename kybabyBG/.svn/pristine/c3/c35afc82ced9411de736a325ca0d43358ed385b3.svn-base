$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:20%'>规则名称</th>"+
			"<th style='width:30%'>规则说明</th>"+
			"<th style='width:10%'>是否启用</th>"+
			"<th style='width:15%'>修改时间</th>"+
			"<th style='width:15%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	$.ajax({
		'url':'sendDoctor.action',
		'type':'POST',
		'data':{
			'action':'getRuleBasicList'
		},
		success:function(result){
			show_tableinfo(result);
		}
	});
});

//修改读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
   
    $('#bottom_div_con input').val('');
    
    $("#ruleName").val($(obj).attr('ruleName'));//名称
    $('#ruleBasicId').val($(obj).attr('ruleBasicId'));//ID
    $('#ruleRemark').val($(obj).attr('ruleRemark'));//说明
   
    $('#isStart option').filter(function(){//是否启动
    	return $(this).text() == $(obj).attr('isStart');
    }).get(0).selected = true;
	$('#field_table_title').empty();
	$('#field_table_content').empty();
	$('#field_table_title').append(
			"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:30%'>字段名称</th>"+
				"<th style='width:10%'>排序号</th>"+
				"<th style='width:50%'>说明</th>"+
			"</tr>"	
		);
  //加载规则配置列表
    $.ajax({
		'url':'sendDoctor.action',
		'type':'POST',
		'data':{
			'action':'getRulesConfigureRecordList',
			'ruleBasic.ruleBasicId':$('#ruleBasicId').val()
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.ruleBasicList == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.rulesConfigureRecordList.length;i ++){
				$('#field_table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>" +
						"<input type='hidden' name='rulesConfigureRecordList["+i+"].id' value='"+(result.rulesConfigureRecordList[i]).id+"' >"+
						(i+1)+
					"</td>"+
					"<td style='width:30%'>"+(result.rulesConfigureRecordList[i]).rulesFieldBasic.fieldName+"</td>"+
					"<td style='width:10%'><input type='text' name='rulesConfigureRecordList["+i+"].sort' value='"+(result.rulesConfigureRecordList[i]).sort+"' ></td>"+
					"<td style='width:50%'>"+(result.rulesConfigureRecordList[i]).rulesFieldBasic.remark+"</td>"+
				"</tr>"
				);
			}
		}
	});

}
//加载内容
function show_tableinfo(result){
	if(result.mes != '操作成功'){
		alert('数据返回错误！');
		return false;
	}else if(result.ruleBasicList == null){
		alert('没有数据！');
		return false;			
	}
	$('#table_content').empty();
	for(var i=0;i < result.ruleBasicList.length;i ++){
		$('#table_content').append(
		"<tr onclick='click_light(this)'>"+
			"<td style='width:10%'>"+(i+1)+"</td>"+
			"<td style='width:20%'>"+(result.ruleBasicList[i]).ruleName+"</td>"+
			"<td style='width:30%'>"+(result.ruleBasicList[i]).ruleRemark+"</td>"+
			"<td style='width:10%'>"+(result.ruleBasicList[i]).isStart+"</td>"+
			"<td style='width:15%'>"+(result.ruleBasicList[i]).modifyTime+"</td>"+
			"<td style='width:15%'>"+
				"<div class='td_change_button' "+
					" ruleBasicId='"+(result.ruleBasicList[i]).ruleBasicId+"' "+
					" ruleName='"+(result.ruleBasicList[i]).ruleName+"' "+
					" ruleRemark='"+(result.ruleBasicList[i]).ruleRemark+"' "+
					" isStart='"+(result.ruleBasicList[i]).isStart+"' "+
					" modifyTime='"+(result.ruleBasicList[i]).modifyTime+"' "+
				" onclick='read_info(this)'>修改</div>"+
			"</td>"+
		"</tr>"
		);
	}
	reset_con_page();	
}

//保存或更新
function saveRuleBasic(){
	$('#ruleBasic_form').attr('action','sendDoctor.action?action=saveOrUpdateRule')
	
	function checknum_int(input){
		if(input == '' || parseInt(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	function checknum_float(input){
		if(input == '' || parseFloat(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	if($.trim($('#ruleName').val()) == ''){
		alert('请填写规则名称！');
		return false;
	}else if($('#ruleRemark').val() == ''){
		alert('请填写规则说明');
		return false;	
	}
	if(confirm("确认？")){
		$('#ruleBasic_form').submit();
	}
}
//添加
function add_info(){
  $('#bottom_div_con input').val('');
  $('#bottom_div_con textarea').val('');
  
  $('#bottom_div').slideDown(500);
  $('#button_add_click').show();
  $('#button_update_click').hide();	
  load_basicField();
}
//加载规则字段
function load_basicField(){
	$('#field_table_title').empty();
	$('#field_table_content').empty();
	$('#field_table_title').append(
			"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:30%'>字段名称</th>"+
				"<th style='width:10%'>排序号</th>"+
				"<th style='width:50%'>说明</th>"+
			"</tr>"	
		);
	$.ajax({
		'url':'sendDoctor.action',
		'type':'POST',
		'data':{
			'action':'getRulesFieldBasicList'
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.ruleBasicList == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.rulesFieldBasicList.length;i ++){
				$('#field_table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>" +
						"<input type='hidden' name='rulesConfigureRecordList["+i+"].rulesFieldBasic.rulesFieldBasicId' value='"+result.rulesFieldBasicList[i].rulesFieldBasicId+"' >"+
						(i+1)+
					"</td>"+
					"<td style='width:30%'>"+(result.rulesFieldBasicList[i]).fieldName+"</td>"+
					"<td style='width:10%'><input type='text' name='rulesConfigureRecordList["+i+"].sort' value='' placeholder='输入排序号'></td>"+
					"<td style='width:50%'>"+(result.rulesFieldBasicList[i]).remark+"</td>"+
				"</tr>"
				);
			}
		}
	});
}
//查询
function search_click(){
	var rule_name = $("#rule_name").val();
	$.ajax({
		'url':'sendDoctor.action',
		'type':'POST',
		cache: false,
		async: false,
		'data':{
			'action':'getRuleBasicList',
			'ruleBasic.ruleName':rule_name,
			'ruleBasic.ruleBasicId':0
		},
		success:function(result){
			if(result == ''){
				alert('没有数据！');
				return false;		
			}
			$('#table_content').empty();
			$('#table_content').append(result);
		}
	});
}
