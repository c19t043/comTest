var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>服务名称</th>"+
			"<th style='width:10%'>排序号</th>"+
			"<th style='width:10%'>"+
				"<div onclick=add_info() class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getOrgOpenBusinessList'};
	getAllList(data);
	
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.orgOpenBusinessList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.orgOpenBusinessList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.orgOpenBusinessList[i]).businessName+"</td>"+
					"<td style='width:10%'>"+(result.orgOpenBusinessList[i]).sort+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.orgOpenBusinessList[i]).id+"' "+
							" businessName='"+(result.orgOpenBusinessList[i]).businessName+"' "+
							" sort='"+(result.orgOpenBusinessList[i]).sort+"' "+
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
    
	$('#businessName').val($(obj).attr('businessName'));
	$('#sort').val($(obj).attr('sort'));

}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=saveOrupdateOrgOpenBusiness');
	
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
	var data = {action:'getorgOpenBusinessList',
				'orderInfoClinic.userInfo.babyName':baby_name ,
				'orderInfoClinic.orderStatus':search_status ,
				'orderInfoClinic.appointmentDate':clinicDate ,
				'orderInfoClinic.orderNum':orderNum ,
				'orderInfoClinic.doctorInfo.doctorName':doctor_name
				};
	getAllList(data);
}

