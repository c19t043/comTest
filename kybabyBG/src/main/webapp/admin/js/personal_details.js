$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:15%'>序列</th>"+
			"<th style='width:15%'>注册时间</th>"+
			"<th style='width:15%'>姓名</th>"+
			"<th style='width:15%'>账号</th>"+
			"<th style='width:10%'>状态</th>"+
			"<th style='width:15%'>联系电话</th>"+
			"<th style='width:15%'></th>"+
		"</tr>"	
	);
	show();
});


//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    $('#phone').val($(obj).attr('data_phone'));
}

function show()
{
	$("#table_content").empty();
	$.ajax({
	type:'post',
	url:'selfCenter.action',
	data:{action:"myInfo"},
	success:function(result)
	{
		if(result.mes=="成功")
			{
			 var ss="有效";
			 
			 if(result.admin.status=="N")
				 { 
				   ss=="无效";
				 }
			
			 $("#table_content").append(
					  "<tr onclick='click_light(this)'>"+
			           " <td width='15%'>1</td>"+
			           " <td width='15%'>"+(result.admin.registTime).toString().replace('T',' ')+"</td>"+
			           " <td width='15%'>"+result.admin.contactName+"</td>"+
			           " <td width='15%'>"+result.admin.name+"</td>"+
			           " <td width='10%'>"+ss+"</td>"+
			           " <td width='15%'>"+result.admin.contactPhone+"</td>"+
			           " <td width='15%'><div data_phone='"+result.admin.contactPhone+"' class='td_change_button' onclick='read_info(this)'>修改</div> </td>"+
			        "</tr>"
			 );
			}
			reset_con_page();
	}
	});
}

function update()
{
     var pwd=$("#pwd").val().trim();
     var rePwd=$("#rePwd").val().trim();
     var phone=$("#phone").val().trim();
     if(pwd!=""&&pwd==rePwd&&phone!="")
    	 {
    	   $.ajax({
    		    type:'post',
    			url:'selfCenter.action',
    			data:{action:"update",pwd:pwd,contactPhone:phone},
    			success:function(result)
    			{
    				if(result.mes=="成功")
    					{
    					$('#bottom_div').slideUp(500);
    					$('#button_update_click').hide();
    					show();
    					}
    			}
    	   });
    	 }
     else
    	 {
    	 alert("两次输入密码不对或者输入为空");
    	 }
}
