$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:25%'>序列</th>"+
			"<th style='width:25%'>健康计划路径</th>"+
			"<th style='width:25%'>提醒时间</th>"+
			"<th style='width:25%'></th>"+
		"</tr>"	
	);
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#everrydayRemindTime').val($(obj).attr('data_time'));
}

function getProperty()
{
	
	$("#table_content").empty();
   	$.ajax({
   		type:'post',
   		url:'propertiesHandle.action',
   		data:{action:"all",key:"everrydayRemindTime"},
   		success:function(result)
   		{
   			if(result.properties!=null)
   				{
   				$("#table_content").append(
   				"<tr  onclick='click_light(this)'>"+
						"<td style='width: 25%'>1</td>"+
						"<td style='width: 25%'>"+result.properties.comments+"</td>"+
						"<td style='width: 25%'>"+result.properties.value+"</td>"+
						"<td style='width: 25%'><div data_key='"+result.properties.name+"' data_time='"+result.properties.value+"' class='td_change_button' onclick='read_info(this)'>修改</div></td>"+
			    "</tr>"
			);
   				reset_con_page();
   				}
   		}
   	})
}
function update()
{
	var propertyValue=$("#everrydayRemindTime").val().trim();
	if($("#everrydayRemindTime").val().trim()=="")
		{
		alert("请输入修改的时间值");
		}
	else
		{
		$.ajax({
			type:'post',
			url:'propertiesHandle.action',
			data:{action:"update",key:"everrydayRemindTime",value:propertyValue,status:"Y"},
			success:function(result)
			{
				$('#bottom_div').slideUp(500);
				$('#button_update_click').hide();
				alert(result.mes);
				getProperty();
			}
		});
		}
	
}