var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:30%'>所属机构</th>"+
			"<th style='width:10%'>场所类型</th>"+
			"<th style='width:10%'>场所名称</th>"+
			"<th style='width:10%'>适用用户</th>"+
			"<th style='width:20%'>场所说明</th>"+
			"<th style='width:10%'>"+
				"<div onclick=add_info() class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getOrganServicePlaceSetList'};
	getAllList(data);
	
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:false, 
		data:{
			action:"getHospitalBasicInfoList"
		},
		success:function(result){
			for(var i=0;i < result.hospitalBasicInfoList.length;i++){
				$('#hospitalId').append(
						"<option value='"+(result.hospitalBasicInfoList[i]).id+"'>"+(result.hospitalBasicInfoList[i]).hospitalLname+"</option>"
				);
			}
		}
	});
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
			}else if(result.organServicePlaceSetList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.organServicePlaceSetList.length;i ++){
				var hospitalBasicInfo = (result.organServicePlaceSetList[i]).hospitalBasicInfo;
				var hospitalLname = hospitalBasicInfo==null?'':hospitalBasicInfo.hospitalLname;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:30%'>"+hospitalLname+"</td>"+
					"<td style='width:10%'>"+(result.organServicePlaceSetList[i]).serviceType+"</td>"+
					"<td style='width:10%'>"+(result.organServicePlaceSetList[i]).windowName+"</td>"+
					"<td style='width:10%'>"+(result.organServicePlaceSetList[i]).ascriptionUserUser+"</td>"+
					"<td style='width:20%'>"+(result.organServicePlaceSetList[i]).windowType+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.organServicePlaceSetList[i]).id+"' "+
							" hospitalId='"+(result.organServicePlaceSetList[i]).hospitalBasicInfo.id+"' "+
							" serviceType='"+(result.organServicePlaceSetList[i]).serviceType+"' "+
							" windowName='"+(result.organServicePlaceSetList[i]).windowName+"' "+
							" ascriptionUserUser='"+(result.organServicePlaceSetList[i]).ascriptionUserUser+"' "+
							" windowType='"+(result.organServicePlaceSetList[i]).windowType+"' "+
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
    
	$('#windowName').val($(obj).attr('windowName'));
	$('#windowType').val($(obj).attr('windowType'));

	$('#ascriptionUserUser option').filter(function(){
		return $(this).val() == $(obj).attr('ascriptionUserUser');
	}).get(0).selected = true;
	$('#serviceType option').filter(function(){
		return $(this).val() == $(obj).attr('serviceType');
	}).get(0).selected = true;
	$('#hospitalId option').filter(function(){
		return $(this).val() == $(obj).attr('hospitalId');
	}).get(0).selected = true;
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=saveOrupdateOrganServicePlaceSet');
	
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
	var data = {action:'getorganServicePlaceSetList',
				'orderInfoClinic.userInfo.babyName':baby_name ,
				'orderInfoClinic.orderStatus':search_status ,
				'orderInfoClinic.appointmentDate':clinicDate ,
				'orderInfoClinic.orderNum':orderNum ,
				'orderInfoClinic.doctorInfo.doctorName':doctor_name
				};
	getAllList(data);
}

