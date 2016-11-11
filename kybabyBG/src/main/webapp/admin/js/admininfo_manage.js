$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>账号</th>"+
			"<th style='width:10%'>角色名字</th>"+
			"<th style='width:10%'>状态</th>"+
			"<th style='width:10%'>姓名</th>"+
			"<th style='width:15%'>联系电话</th>"+
			"<th style='width:15%'>注册时间</th>"+
			"<th style='width:20%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	getUserInfo();
	getAdminInfo();
	getAllFunctionList();
	//更新操作
	$('#button_update_click').click(function(){
		updateAdminInfo();
	});
	$('#button_add_click').click(function(){
		addAdminInfo();
	});
});
//获取列表信息
function getAdminInfo(){
	$('#table_content').find('tr').remove();
	$.ajax({
		url:'getAdminInfo.action',
		data:{action:"all"},
		type:'post',
		success:function(result){
			if(result.adminList.length != 0){
				for(var i=0;i<result.adminList.length;i++){
					$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:10%'>"+(i+1)+"</td>"+
						"<td style='width:10%'>"+result.adminList[i].name+"</td>"+
						"<td style='width:10%'>"+result.roleNameList[i]+"</td>"+
						"<td style='width:10%'>"+result.adminList[i].status+"</td>"+
						"<td style='width:10%'>"+result.adminList[i].contactName+"</td>"+
						"<td style='width:15%'>"+result.adminList[i].contactPhone+"</td>"+
						"<td style='width:15%'>"+result.adminList[i].registTime+"</td>"+
						"<td style='width:20%'>"+
							"<div onclick='read_info(this)' data_adminid='"+result.adminList[i].id+"' data_roleid='"+result.adminList[i].roleId+"' data_pass='"+result.adminList[i].password+"' data_funlist='"+result.adminList[i].functionList+"' class='td_change_button'>修改</div>"+
						"</th>"+
					"</tr>"
					);				
				}
			}
			reset_con_page();//分页执行

		}
	});
}
//读取信息
function read_info(obj){
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	
	$('#role_username').val($(obj).parent().parent().find('td').eq(1).text());
	$('#role_username').attr('data_adminid',function(){
		return $(obj).attr('data_adminid');
	}).attr('data_roleid',function(){
		return $(obj).attr('data_roleid');
	});
	$('#role_password').val($(obj).attr('data_pass'));
	$('#role_contact_name').val($(obj).parent().parent().find('td').eq(4).text());
	$('#role_contact_phone').val($(obj).parent().parent().find('td').eq(5).text());
	$('#role_name option').filter(function(){
		return $(this).text() == $(obj).parent().parent().find('td').eq(2).text();
	}).get(0).selected = true;
	$('#role_status option').filter(function(){
		return $(this).text() == $(obj).parent().parent().find('td').eq(3).text();
	}).get(0).selected = true;
	
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
//加载权限角色
function getUserInfo(){
	$('#role_name').html('');
	$('#role_name').append("<option></option>");
	$.ajax({
		url:'getRoleInfo.action',
		async:false,
		data:{action:"all"},
		type:'post',
		success:function(result){ 
			for(var i=0;i<result.roleList.length;i++){
				$('#role_name').append("<option data-fun='"+result.roleList[i].functionList+"' data_id='"+result.roleList[i].id+"' value='"+result.roleList[i].name+"'>"+result.roleList[i].name+"</option>");
			}
				$('#role_name').change(function(){
					$("#function_ul_list input").each(function(){
						$(this).get(0).checked = false;
					});
					if($(this).val().trim() == ""){
						return false;
					}else{
						var funStr = $("#role_name option").filter(function(){
							return $(this).get(0).selected == true;
						}).data("fun");
						var arr = [];
						if(funStr.indexOf(":") == -1){
							arr.push(funStr);
						}else{
							arr = funStr.split(":");
						}


						$.each(arr,function(key,val){
							var inputObj = $("#function_ul_list input").filter(function(){
								return $(this).attr("zjid") == val;
							}).get(0);
							if(inputObj != undefined){
								inputObj.checked = true;
							}
						});
					}
				});
		}
	});
}
//更新管理员信息
function updateAdminInfo(){
	var str = '';
	for(var i=0;i < $('#function_ul_list input').length;i++){
		if($('#function_ul_list input').eq(i).get(0).checked == true){
			str = str +":"+$('#function_ul_list input').eq(i).attr('zjid');
		}
	}
	if($.trim($('#role_username').val()) == '' || $.trim($('#role_password').val()) == '' || $.trim($('#role_contact_name').val()) == '' || $.trim($('#role_contact_phone').val()) == ''){
		alert('请补全信息');
		return false;
	}else if($.trim($('#role_name').val()) == ''){
		alert('请选择角色');
		return false;
	}else if(str.length == 0){
		alert('请选择功能');
		return false;	
	}
	str = str.substring(1);
  $.ajax({
    	url:'adminManage.action',
    	type:'post',
    	data:{
    		action:"update",
    		adminId:$('#role_username').attr('data_adminid'),//管理员ID
    		adminName:$.trim($('#role_username').val()),//账号
    		password:$.trim($('#role_password').val()),//密码
    		roleId:$('#role_name option').filter(function(){
    							return $(this).text() == $('#role_name').val();
    						}).attr('data_id'),//角色ID
    		functionId:str,//1:2:2
				contactName:$.trim($('#role_contact_name').val()),//姓名
				contactPhone:$.trim($('#role_contact_phone').val()),//电话
    		status:$.trim($("#role_status").val())//状态
    	},
    	success:function(result){
         if(result.mes == '更新成功'){
       		alert('更新成功');
       		$('#bottom_div').slideUp(500);
       		getAdminInfo();
         }else if(result.mes == '该用户名已被占用'){
         	alert('该用户名已被占用');
         	$('#role_username').focus();
         }
    	}
	});
}
//添加清空弹窗
function add_info(){
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
	$('.bottom_div_con_first input,.bottom_div_con_first select').val('');
	for(var i=0;i < $('#function_ul_list input').length;i++){
		$('#function_ul_list input').eq(i).get(0).checked = false;
	}	
}
//添加管理员信息
function addAdminInfo(){
	var str = '';
	for(var i=0;i < $('#function_ul_list input').length;i++){
		if($('#function_ul_list input').eq(i).get(0).checked == true){
			str = str +":"+$('#function_ul_list input').eq(i).attr('zjid');
		}
	}
	str = str.substring(1);
	if($.trim($('#role_username').val()) == '' || $.trim($('#role_password').val()) == '' || $.trim($('#role_contact_name').val()) == '' || $.trim($('#role_contact_phone').val()) == ''){
		alert('请补全信息');
		return false;
	}else if($.trim($('#role_name').val()) == ''){
		alert('请选择角色');
		return false;
	}else if(str.length == 0){
		alert('请选择功能');
		return false;	
	}else if($('#role_status').val() == null){
		alert('请选择状态');
		return false;
	}
	$.ajax({
		url:'adminManage.action',
		type:'post',
		async:false,
		cache:false,
		type:'post',
		data:{
			action:"add",
			adminName:$.trim($("#role_username").val()),
			password:$.trim($("#role_password").val()),
			roleId:$('#role_name option:selected').attr('data_id'),
			functionId:str,
			contactName:$.trim($('#role_contact_name').val()),
			contactPhone:$.trim($('#role_contact_phone').val()),
			status:$("#role_status").val()
		},
		success:function(result){
			if(result.mes == '该用户名已被占用'){
	     	alert('该用户名已被占用');
	     	$('#role_username').focus();				
			}else if(result.mes == '用户添加成功'){
     		alert('添加成功');
     		$('#bottom_div').slideUp(500);
     		getAdminInfo();			
			}
		}
	});
}
