$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:25%'>序列</th>"+
			"<th style='width:25%'>自定义标签内容</th>"+
			"<th style='width:25%'>状态</th>"+
			"<th style='width:25%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
});


function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#symptom_tag_name').val($(obj).attr('data_name'));
	$("#symptom_tag_name").attr('data_id',$(obj).attr('data_id'));
	$('#select_status').find('option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
	
}

function add_info() {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
}

function getSymptomTag(){
	$("#table_content").empty();
	$.ajax({
		type:'post',
		url:'symptomTagHandle.action',
		data:{action:"all"},
		success:function(result)
		{
			if(result.mes=="成功"&&result.allSymptomTag!=null)
				{
				for(var i=0;i<result.allSymptomTag.length;i++)
			      {
				$("#table_content").append(
						"<tr onclick='click_light(this)'>"+
						"<td style='width: 25%'>"+(i+1)+"</td>"+
						"<td style='width: 25%'>"+result.allSymptomTag[i].symptomName+"</td>"+
						"<td style='width: 25%'>"+result.allSymptomTag[i].symptomStatus+"</td>"+
						"<td style='width: 25%'><div " +
						"data_id='"+result.allSymptomTag[i].id+"'"+
						"data_name='"+result.allSymptomTag[i].symptomName+"'"+
						"data_status='"+result.allSymptomTag[i].symptomStatus+"'"+
						"class='td_change_button' onclick='read_info(this)'>修改</div></td> "+
					"</tr>"
				   );
				   }
				reset_con_page();
				}
			else
				{
				alert("未定义任何症状标签");
				}
		}
		
	});
}

function add()
{
	var symptomTagName=$("#symptom_tag_name").val().trim();
	var symptomTagStatus=$("#select_status").val().trim();
	if(symptomTagName==""||symptomTagStatus=="")
		{
		alert("输入不能为空");
		}
	else{
		$.ajax({
		type:'post',
		url:'symptomTagHandle.action',
		data:{action:"add",addName:symptomTagName,status:symptomTagStatus},
		success:function(result)
		{
			$('#bottom_div').slideUp(500);
			$('#button_add_click').hide();
			alert(result.mes);
			getSymptomTag();
		}
			
		});
		
	}	
}
function update()
{
	var id=$("#symptom_tag_name").attr('data_id');
	var updateName=$("#symptom_tag_name").val().trim();
	var updateStatus=$("#select_status").val();
	if(id==""||updateName==""||updateStatus=="")
		{
		alert("请输入名字");
		}
	else{	
		$.ajax({
		type:'post',
		url:'symptomTagHandle.action',
		data:{action:"update",updateId:id,updateName:updateName,status:updateStatus},
		success:function(result)
		{
			$('#bottom_div').slideUp(500);
			$('#button_update_click').hide();
			alert(result.mes);
			getSymptomTag();
		}
			
		});
	}
}
