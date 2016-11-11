$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:20%'>序列</th>"+
			"<th style='width:20%'>自定义标签内容</th>"+
			"<th style='width:20%'>类型</th>"+
			"<th style='width:20%'>状态</th>"+
			"<th style='width:20%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
});

function read_info(obj) {
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show();
	$('#assessmentTag_name').val($(obj).attr('data_name'));
	$('#assessmentTag_name').attr('data_id',$(obj).attr('data_id'));
	$('#select_status').find('option').filter(function(){
		return $(this).text() == $(obj).attr('data_status');
	}).get(0).selected = true;
	$('#assessmentTag_type').text($(obj).attr('data_type'));
}

function add_info() {
	$('#assessmentTag_name').val('');
	$('#bottom_div').slideDown(500);
	$('#button_add_click').show();
	$('#button_update_click').hide();
}
function getAssessmentTag()
{
	$("#table_content").empty();
	$.ajax({
		type:'post',
		url:'assessmentTagHandle.action',
		data:{action:"all"},
		success:function(result)
		{
			if(result.mes=="成功"&&result.allAssessmentTag!=null)
				{
				for(var i=0;i<result.allAssessmentTag.length;i++)
					{
					$("#table_content").append(
							"<tr  onclick='click_light(this)'>"+
							"<td style='width: 20%'>"+(i+1)+"</td>"+
							"<td style='width: 20%'>"+result.allAssessmentTag[i].tagName+"</td>"+
							"<td style='width: 20%'>"+result.allAssessmentTag[i].tagType+"</td>"+
							"<td style='width: 20%'>"+result.allAssessmentTag[i].tagStatus+"</td>"+
							"<td style='width: 20%'><div " + 
							"data_id='"+result.allAssessmentTag[i].id+"'"+
							"data_name='"+result.allAssessmentTag[i].tagName+"'"+
							"data_type='"+result.allAssessmentTag[i].tagType+"'"+
							"data_status='"+result.allAssessmentTag[i].tagStatus+"'"+
						    "class='td_change_button' onclick='read_info(this)'>修改</div></td>"+
						    "</tr>"
							
					);
					}
				reset_con_page();
				}
			else{alert("请添加标签");}
		}
	});
}
function add()
{
   var 	assessmentTagName=$("#assessmentTag_name").val().trim();
   var  assessmentTagStatus=$("#select_status").val().trim();
   if(assessmentTagName==""||assessmentTagStatus=="")
   {
	   alert("请输入标签内容");
   }
   else
   {
	   $.ajax({
		  type:'post',
		  url:'assessmentTagHandle.action',
		  data:{action:"add",addName:assessmentTagName,status:assessmentTagStatus},
		  success:function(result)
		  {
			  $('#bottom_div').slideUp(500);
			  $('#button_add_click').hide();
			  alert(result.mes);
			  getAssessmentTag();		  
		  }
		   
	   });
   }
}

function update()
{
	var id=$("#assessmentTag_name").attr('data_id');
	var updateName=$("#assessmentTag_name").val().trim();
	var updateStatus=$("#select_status").val();
    
	if(id==""||updateName==""||updateStatus=="")
		{
		alert("请输入名字");
		}
	else{	
		$.ajax({
		type:'post',
		url:'assessmentTagHandle.action',
		data:{action:"update",updateId:id,updateName:updateName,status:updateStatus},
		success:function(result)
		{
			$('#bottom_div').slideUp(500);
			$('#button_update_click').hide();
			alert(result.mes);
			getAssessmentTag();
		}
			
		});
	}
}