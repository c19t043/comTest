$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:30%'>序列</th>"+
			"<th style='width:30%'>角色名</th>"+
			"<th style='width:30%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"
	);
	//修改操作
	$('#button_update_click').click(function(){
		userInfoUpdate();
	});
	//新增操作
	$('#button_add_click').click(function(){
		userInfoAdd();
	});
	getAllFunctionList();//底部获取所有功能
	getUserInfo();//加载角色列表
	
});

//加载角色列表
function getUserInfo(){
	$('#table_content').html('');
	$.ajax({
  		url:'getRoleInfo.action',
  		async:false,
  		data:{action:"all"},
  		type:'post',
  		success:function(result){ 
  			for(var i=0;i<result.roleList.length;i++){
  				$('#table_content').append(
  				"<tr onclick='click_light(this)'>"+
  					"<td style='width:30%;'>"+(i+1)+"</td>"+
  					"<td style='width:30%;'>"+result.roleList[i].name+"</td>"+
  					"<td style='width:30%;'>"+
  						"<div data_id='"+result.roleList[i].id+"' data_name='"+result.roleList[i].name+"' data_funlist='"+result.roleList[i].functionList+"'  onclick='read_info(this)' class='td_change_button'>修改</div>"+
  					"</td>"+
  				"</tr>"
  				);
  			}
				reset_con_page();//分页执行
				
 	 	}
	});
}

//底部获取所有功能
function getAllFunctionList(){
	$.ajax({
		type:'post',
		url:'getFunctionInfo.action',
		async:false,
		data:{action:"getAllFunctionList"},
		success:function(result){
			if(result.mes=="操作成功"){
				$('#bottom_div_con').append("<ul id='function_ul_list'></ul>");
				for(var i=0;i<result.allFunctionParentList.length;i++){
					var functionList=result.allFunctionList[i];
					if(functionList!=null)
						{
					for(var j=0;j<functionList.length;j++){
						$('#function_ul_list').append(
						"<li>"+
							"<input id='fun_"+i+"_"+j+"' zjid='"+functionList[j].id+"' name='checkbox' type='checkbox' />"+
							"<label for='fun_"+i+"_"+j+"'>"+functionList[j].name+"</label>"+
						"</li>"
						);
					    }
					}
				}
			}
		}
	});
}

//读取信息
function read_info(obj){
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#role_name').val($(obj).attr('data_name'));
	$('#role_name').attr('zjid',function(){
		return $(obj).attr('data_id');
	});
	var id_arr = $(obj).attr('data_funlist').split(':');
	//for循环清空checkbox被选中状态
	for(var i=0;i < $('#function_ul_list input').length;i++){
		$('#function_ul_list input').eq(i).get(0).checked = false;
	}
	//for循环将匹配到的选中
	for(var i=0;i<id_arr.length;i++){
		$('#function_ul_list input').filter(function(){
			return $(this).attr('zjid') == id_arr[i];
		}).get(0).checked = true;
	}
}
//添加清空弹窗
function add_info(){
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
	$('#role_name').val('');
	for(var i=0;i < $('#function_ul_list input').length;i++){
		$('#function_ul_list input').eq(i).get(0).checked = false;
	}	
}
//修改角色信息
function userInfoUpdate(){
	var name = $.trim($('#role_name').val());
	var id = $('#role_name').attr('zjid');
	var str = '';
	for(var i=0;i < $('#function_ul_list input').length;i++){
		if($('#function_ul_list input').eq(i).get(0).checked == true){
			str = str +":"+$('#function_ul_list input').eq(i).attr('zjid');
		}
	}
	str = str.substring(1);
	if(name == ''){
		alert('请输入角色名！');
		$('#role_name').focus();
		return false;
	}else if(str.length == 0){
		alert('请选择功能！');
		return false;	
	}
	$.ajax({
  		url:'roleManage.action',
  		type:'post',
  		data:{
  			action:"update",
  			roleName:name, 
  			roleFunction:str,
  			roleId:id
  		},
  		success:function(result){
				if(result.mes == '更新成功'){
					alert('修改成功！');
					$('#bottom_div').slideUp(500);
					getUserInfo();
				}else if(result.mes == '该角色已经存在'){
					alert('该角色已经存在！');
					$('#role_name').val('').focus();
				}
    	}
	});
}
//添加角色
function userInfoAdd(){
	var name = $.trim($('#role_name').val());
	var str = '';
	for(var i=0;i < $('#function_ul_list input').length;i++){
		if($('#function_ul_list input').eq(i).get(0).checked == true){
			str = str +":"+$('#function_ul_list input').eq(i).attr('zjid');
		}
	}
	str = str.substring(1);
	if(name == ''){
		alert('请输入角色名！');
		$('#role_name').focus();
		return false;
	}else if(str.length == 0){
		alert('请选择功能！');
		return false;	
	}
	$.ajax({
  		url:'roleManage.action',
  		type:'post',
  		data:{
				action:"add",
				roleName:name,
				functionList:str
			},
  		success:function(result){
				if(result.mes=='角色添加成功'){
					alert('添加成功！');
					$('#bottom_div').slideUp(500);
					getUserInfo();				
				}else if(result.mes == '该角色已经存在'){
					alert('该角色已经存在！');
					$('#role_name').val('').focus();
				}
    	}
	});
}