var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>父分类</th>"+
			"<th style='width:10%'>分类名称</th>"+
			"<th style='width:10%'>"+
				"<div onclick=add_info() class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getDrugClassificationList'};
	getAllList(data);
	//加载父类
	$.ajax({
		type:'post',
		url:ringHost+'drugInfoManage.action',
		cache:false,
		async:true, 
		data:{action:'getDrugClassificationList'},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.drugClassificationList == null){
				return false;			
			}
			$('#parentClass').empty();
			$('#parentClass').append("<option value=''>-请选择-</option>");
			for(var i=0;i < result.drugClassificationList.length;i ++){
				var parent = (result.drugClassificationList[i]).parentClass;
				if(parent == null){
					$('#parentClass').append(
							"<option value='"+(result.drugClassificationList[i]).id+"'>"+(result.drugClassificationList[i]).className+"</option>"
					);
				}
			}
		}
	});
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'drugInfoManage.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.drugClassificationList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.drugClassificationList.length;i ++){
				var parent = (result.drugClassificationList[i]).parentClass;
				var parentName = parent==null?"":parent.className;
				var parentId = parent==null?"":parent.id;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+parentName+"</td>"+
					"<td style='width:10%'>"+(result.drugClassificationList[i]).className+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.drugClassificationList[i]).id+"' "+
							" parentName='"+parentName+"' "+
							" parentId='"+parentId+"' "+
							" className='"+(result.drugClassificationList[i]).className+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
//修改读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#bottom_div_con input').val('');
    
    $('#keyId').val($(obj).attr('keyId'));
    
	$('#className').val($(obj).attr('className'));
	$('#parentClass option').each(function(){
		if($(this).val() == $(obj).attr('parentId')){
			$(this).attr("selected","selected") ;
		}
	});

}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'drugInfoManage.action?action=saveOrUpdateDrugClassification');
	
	function checknum_int(input){
		if(input == '' || parseInt(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	function checknum_float(input){
		if(input == '' || parseFloat(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}

	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
}
function search_click(){
	var baby_name = $("#baby_name").val();
	var doctor_name = $("#doctor_name").val();
	var search_status = $("#search_status").find("option:selected").text();
	var clinicDate = $("#clinicDate").val();
	var orderNum = $("#order_Num").val();
	var data = {action:'getDrugClassificationList',
				'orderInfoClinic.userInfo.babyName':baby_name ,
				'orderInfoClinic.orderStatus':search_status ,
				'orderInfoClinic.appointmentDate':clinicDate ,
				'orderInfoClinic.orderNum':orderNum ,
				'orderInfoClinic.doctorInfo.doctorName':doctor_name
				};
	getAllList(data);
}

