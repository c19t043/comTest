$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:20%'>序列</th>"+
			"<th style='width:20%'>功能</th>"+
			"<th style='width:20%'>所属功能模块</th>"+
			"<th style='width:20%'>状态</th>"+
			"<th style='width:20%'></th>"+
		"</tr>"	
	);
});


function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$("#functionName").text($(obj).attr('data_functionName'));
	$("#functionName").attr('data_id',$(obj).attr('data_id'));
	$("#functionParentName").text($(obj).attr('data_functionParent'));
	$('#select_status').find('option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
 
}

function getFunction()
{
	$("#table_content").empty();
	$.ajax({
		type:'post',
		url:'functionManage.action',
		data:{action:"show"},
	    success:function(result)
	    {
	    	if(result.mes=="成功")
	    		{
	    	for(var i=0;i<result.allSencondfunction.length;i++)
	    		{
	    	$("#table_content").append(	
	    	"<tr onclick='click_light(this)'>"+
				"<td style='width: 20%'> "+(i+1)+ "</td>"+
				"<td style='width: 20%'> "+result.allSencondfunction[i][2]+"</td>"+
				"<td style='width: 20%'> "+result.allSencondfunction[i][0]+"</td>"+
				"<td style='width: 20%'> "+result.allSencondfunction[i][3]+"</td>"+
				"<td style='width: 20%'><div data_id='"+result.allSencondfunction[i][1]+"' data_functionName='"+result.allSencondfunction[i][2]+"' data_functionParent='"+result.allSencondfunction[i][0]+"'  data_status='"+result.allSencondfunction[i][3]+"'"+
				"class='td_change_button' onclick='read_info(this)'>修改</div> </td>"+
			"</tr>");
	    		}
	    	reset_con_page();
	    		}
	    }
	});
};

function update()
{
	var id=$("#functionName").attr('data_id');

	var status=$("#select_status").val();
	
	if(id!=""&&status!="")
		{
		
		$.ajax({
			type:'post',
			url:'functionManage.action',
        	data:{action:"update",id:id,functionStatus:status},
        	success:function(result)
        	{
        		if(result.mes=="更新成功")
        			{
        			$('#bottom_div').slideUp(500);
        			$('#button_update_click').hide();
        			alert("更新成功");
        			getFunction();
        			}
        	}
			
		});
		
		}
	
}

