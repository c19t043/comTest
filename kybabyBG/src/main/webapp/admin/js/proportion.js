$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:20%'>序列</th>"+
			"<th style='width:20%'>分成比例</th>"+
			"<th style='width:20%'>补贴类型</th>"+
			"<th style='width:20%'>状态</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
}
function getProperty()
{
	
	$("#table_content").empty();
   	$.ajax({
   		type:'post',
   		url:'propertiesHandle.action',
   		data:{action:"all",key:"scale"},
   		success:function(result)
   		{
   			if(result.properties!=null)
   				{
   				$("#table_content").append(
   				"<tr onclick='click_light(this)'>"+
						"<td style='width: 20%'>1</td>"+
						"<td style='width: 20%'>"+result.properties.value+"</td>"+
						"<td style='width: 20%'>"+result.properties.comments+"</td>"+
						"<td style='width: 20%'>"+result.properties.status+"</td>"+
						"<td style='width: 20%'><div data_key='"+result.properties.name+"' class='td_change_button' onclick='read_info(this)'>修改</div></td>"+
			    "</tr>"
			);
   				reset_con_page();
   				}
   		}
   	})
}
function update()
{
	var propertyValue=parseFloat($("#scale").val().trim());
	var propertyStatus=$("#select_status").val().trim();
	if(isNaN(propertyValue) || propertyValue > 1 || propertyValue < 0)
		{
		alert("请输入0-1之间的小数");
		}
	else
		{
		$.ajax({
			type:'post',
			url:'propertiesHandle.action',
			data:{action:"update",key:"scale",value:propertyValue,status:propertyStatus},
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
