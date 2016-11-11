$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:25%'>序列</th>"+
			"<th style='width:25%'>擅长领域</th>"+
			"<th style='width:25%'>医生类型</th>"+
			"<th style='width:25%'>状态</th>"+
			"<th style='width:25%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	
	$('#goodField_name').val($(obj).attr('data_name'));
	$("#goodField_id").val($(obj).attr('data_id'));
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

function getGoodField()
{
	$("#table_content").empty();
	$.ajax({
		type:'post',
		url:'majorHandle.action',
		data:{action:"getAllGoodField"},
		success:function(result)
		{
			if(result.mes=="成功"&&result.doctorGoodFieldList!=null)
				{
				for(var i=0;i<result.doctorGoodFieldList.length;i++)
			      {
				$("#table_content").append(
						"<tr  onclick='click_light(this)'>"+
						"<td style='width: 25%'>"+(i+1)+"</td>"+
						"<td style='width: 25%'>"+result.doctorGoodFieldList[i].name+"</td>"+
						"<td style='width: 25%'>"+result.doctorGoodFieldList[i].doctorType+"</td>"+
						"<td style='width: 25%'>"+result.doctorGoodFieldList[i].isStart+"</td>"+
						"<td style='width: 25%'><div " +
							"data_id='"+result.doctorGoodFieldList[i].id+"'"+
							"data_name='"+result.doctorGoodFieldList[i].name+"'"+
							"doctorType='"+result.doctorGoodFieldList[i].doctorType+"'"+
							"data_status='"+result.doctorGoodFieldList[i].isStart+"'"+
							"class='td_change_button' onclick='read_info(this)'>修改</div>" +
						"</td> "+
					"</tr>"
				   );
				   }
				reset_con_page();
				}
			else{
				alert("未定义任何擅长领域");
			}
		}
		
	});
}

function saveOrUpdate()
{
	$('#data_form').attr('action','majorHandle.action?action=saveOrupdateGoodField');
	var addName=$("#goodField_name").val().trim();
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
		if(confirm("确认？")){
			$('#data_form').submit();
		}
	}	
}
