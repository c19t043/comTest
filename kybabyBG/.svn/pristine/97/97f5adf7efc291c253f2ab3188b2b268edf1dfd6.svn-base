$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:25%'>序列</th>"+
			"<th style='width:25%'>专业方向</th>"+
			"<th style='width:25%'>医生类型</th>"+
			"<th style='width:25%'>状态</th>"+
			"<th style='width:25%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#major_name').val($(obj).attr('data_name'));
	$("#major_name").attr('data_id',$(obj).attr('data_id'));
	$('#select_status').find('option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
	$('#doctorType').find('option').filter(function(){
		return $(this).text() == $(obj).attr('doctorType');
	}).get(0).selected = true;
	
}

function add_info() {
	$('#major_name').val('');
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
}

function getMajor()
{
	$("#table_content").empty();
	$.ajax({
		type:'post',
		url:'majorHandle.action',
		data:{action:"all"},
		success:function(result)
		{
			if(result.mes=="成功"&&result.allMajor!=null)
				{
				for(var i=0;i<result.allMajor.length;i++)
			      {
				$("#table_content").append(
						"<tr  onclick='click_light(this)'>"+
						"<td style='width: 25%'>"+(i+1)+"</td>"+
						"<td style='width: 25%'>"+result.allMajor[i].major+"</td>"+
						"<td style='width: 25%'>"+result.allMajor[i].doctorType+"</td>"+
						"<td style='width: 25%'>"+result.allMajor[i].majorStatus+"</td>"+
						"<td style='width: 25%'><div " +
						"data_id='"+result.allMajor[i].id+"'"+
						"data_name='"+result.allMajor[i].major+"'"+
						"doctorType='"+result.allMajor[i].doctorType+"'"+
						"data_status='"+result.allMajor[i].majorStatus+"'"+
						"class='td_change_button' onclick='read_info(this)'>修改</div></td> "+
					"</tr>"
				   );
				   }
				reset_con_page();
				}
			else
				{
				alert("未定义任何专业方向");
				}
		}
		
	});
}

function add()
{
	var addName=$("#major_name").val().trim();
	var addStatus=$("#select_status").val().trim();
	var doctorType = $("#doctorType").val().trim();
	if(addName==""||addStatus==""){
		alert("输入不能为空");
		return false;
	}else if(doctorType == ""){
		alert("请选择医生类型");
		return false;
	}
	else{
		$.ajax({
		type:'post',
		url:'majorHandle.action',
		data:{action:"add",name:addName,status:addStatus,doctorType:doctorType},
		success:function(result)
		{
			$('#bottom_div').slideUp(500);
			$('#button_add_click').hide();
			alert(result.mes);
			getMajor()
		}
			
		});
		
	}	
}
function update()
{
	var id=$("#major_name").attr('data_id');
	var updateName=$("#major_name").val().trim();
	var updateStatus=$("#select_status").val();
	var doctorType = $("#doctorType").val().trim();
	if(id==""||updateName==""||updateStatus==""){
		alert("请输入名字");
		return false;
	}else if(doctorType == ""){
		alert("请选择医生类型");
		return false;
	}
	else{	
		$.ajax({
		type:'post',
		url:'majorHandle.action',
		data:{action:"update",updateId:id,name:updateName,status:updateStatus,doctorType:doctorType},
		success:function(result)
		{
			$('#bottom_div').slideUp(500);
			$('#button_update_click').hide();
			alert(result.mes);
			getMajor()
		}
			
		});
	}
}
