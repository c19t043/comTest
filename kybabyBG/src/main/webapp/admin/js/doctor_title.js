$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序号</th>"+
			"<th style='width:15%'>职称名字</th>"+
			"<th style='width:15%'>职称级别</th>"+
			"<th style='width:10%'>职称状态</th>"+
			"<th style='width:10%'>分层比例</th>"+
			"<th style='width:10%'>保底薪酬</th>"+
			"<th style='width:10%'>每例提出比例</th>"+
			"<th style='width:10%'>半天保底薪酬</th>"+
			"<th style='width:15%'>"+
				"<div onclick='add_info()' class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"	
	);
});
function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#position_name').val($(obj).attr('data_name'));
	$('#position_name').attr('data_id',$(obj).attr('data_id'));
	$('#select_rank').find('option').filter(function(){
	   return $(this).text()==$(obj).attr('data_rank');			
	}).get(0).selected=true;
	$('#select_status').find('option').filter(function(){
	   return $(this).text()==$(obj).attr('data_status');			
	}).get(0).selected=true;
}

function add_info() {
	$('#position_name').val('');
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
}
function  getPosition()
{
	$("#table_content").empty();
	$.ajax({
		type:'post',
		url:'positionHandle.action',
		data:{action:"all"},
	    success:function(result)
	    {
	    	if(result.allPosition!=null)
	    		{
	    		   for(var i=0;i<result.allPosition.length;i++)
	    			   {
	    			      $("#table_content").append(
	    			    		"<tr  onclick='click_light(this)'>"+
	    							"<td style='width: 5%'>"+(i+1)+"</td>"+
	    							"<td style='width: 15%'>"+result.allPosition[i].name+"</td>"+
	    							"<td style='width: 15%'>"+result.allPosition[i].rank+"</td>"+
	    							"<td style='width: 10%'>"+result.allPosition[i].positionStatus+"</td>"+
	    							"<td style='width: 10%'>待功能</td>"+
										"<td style='width: 10%'>待功能</td>"+
										"<td style='width: 10%'>待功能</td>"+
										"<td style='width: 10%'>待功能</td>"+
	    							"<td style='width: 15%'><div " +
	    							"data_id='"+result.allPosition[i].id+"'"+
	    							"data_name='"+result.allPosition[i].name+"'"+
	    							"data_rank='"+result.allPosition[i].rank+"'"+
	    							"data_status='"+result.allPosition[i].positionStatus+"'"+
	    							"class='td_change_button' onclick='read_info(this)'>修改</div></td>"+
	    						"</tr>"	  
	    			     );
	    			   
	    			   }
	    		   reset_con_page();
	    		}
	    	else
	    		{alert("你还没有添加任何职称");}
	    }
	});
}

function add()
{
     var addName=$("#position_name").val().trim();
     var addRank=$("#select_rank").val().trim();
     var addStatus=$("#select_status").val().trim();
     if(addName==""||addRank==""||addStatus=="")
    	 {
    	 alert("请输入职称信息");
    	 }
     else
    	 {
    	 $.ajax({
    		    type:'post',
    			url:'positionHandle.action',
    			data:{action:"add",name:addName,rank:addRank,status:addStatus},
    		    success:function(result)
    		    {
    				$('#bottom_div').slideUp(500);
    				$('#button_add_click').hide();
    				alert(result.mes);
    				getPosition();
    		    }
    	 });
    	 }
}

function update()
{
      var id=$("#position_name").attr('data_id');
      var updateName=$("#position_name").val().trim();
      var updateRank=$("#select_rank").val().trim();
      var updateStatus=$("#select_status").val().trim();
      if(id==""||updateName==""||updateRank==""||updateStatus=="")
    	  {
    	  alert("请输入职称名字");
    	       if(updateRank>8||updateRank<1)
    	    	   {
    	    	    alert("职称级别应该为1-8");
    	    	   }
    	  }
      else
    	  {
    	   $.ajax({
    		type:'post',
   			url:'positionHandle.action',
   			data:{action:"update",name:updateName,rank:updateRank,status:updateStatus,updateId:id},
   		    success:function(result)
   		    {
   				$('#bottom_div').slideUp(500);
   				$('#button_update_click').hide();
   				alert(result.mes);
   				getPosition();
   		    }
    		   
    	   });
    	  }
}
