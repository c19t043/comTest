var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>订单编号</th>"+
			"<th style='width:10%'>下单时间</th>"+
			"<th style='width:5%'>订单金额</th>"+
			"<th style='width:10%'>门诊日期</th>"+
			"<th style='width:25%'>门诊机构</th>"+
			"<th style='width:5%'>开始时间</th>"+
			"<th style='width:5%'>结束时间</th>"+
			"<th style='width:5%'>医生姓名</th>"+
			"<th style='width:5%'>宝宝姓名</th>"+
			"<th style='width:5%'>订单状态</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button'>操作</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getOrderInfoClinicList'};
	getAllList(data);
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{
			action:"getAllDoctor"
		},
		success:function(result){
			for(var i=0;i < result.doctorInfoList.length;i++){
				$('#doctorInfo').append(
						"<option value='"+(result.doctorInfoList[i]).id+"'>"+(result.doctorInfoList[i]).doctorName+"</option>"
				);
			}
		}
	});
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.orderInfoClinicList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.orderInfoClinicList.length;i ++){
				var userInfo = (result.orderInfoClinicList[i]).userInfo;
				var babyName = userInfo==null?'':userInfo.babyName;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.orderInfoClinicList[i]).orderNum+"</td>"+
					"<td style='width:10%'>"+(result.orderInfoClinicList[i]).orderTime+"</td>"+
					"<td style='width:5%'>"+(result.orderInfoClinicList[i]).totalPrice+"</td>"+
					"<td style='width:10%'>"+(result.orderInfoClinicList[i]).appointmentDate+"</td>"+
					"<td style='width:25%'>"+(result.orderInfoClinicList[i]).clinicAddress+"</td>"+
					"<td style='width:5%'>"+(result.orderInfoClinicList[i]).appointmentBeganTime+"</td>"+
					"<td style='width:5%'>"+(result.orderInfoClinicList[i]).appointmentEndTime+"</td>"+
					"<td style='width:5%'>"+(result.orderInfoClinicList[i]).doctorInfo.doctorName+"</td>"+
					"<td style='width:5%'>"+babyName+"</td>"+
					"<td style='width:5%'>"+(result.orderInfoClinicList[i]).orderStatus+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.orderInfoClinicList[i]).id+"' "+
							" doctorId='"+(result.orderInfoClinicList[i]).doctorInfo.id+"' "+
							" orderNum='"+(result.orderInfoClinicList[i]).orderNum+"' "+
							" orderTime='"+(result.orderInfoClinicList[i]).orderTime+"' "+
							" totalPrice='"+(result.orderInfoClinicList[i]).totalPrice+"' "+
							" appointmentDate='"+(result.orderInfoClinicList[i]).appointmentDate+"' "+
							" appointmentBeganTime='"+(result.orderInfoClinicList[i]).appointmentBeganTime+"' "+
							" appointmentEndTime='"+(result.orderInfoClinicList[i]).appointmentEndTime+"' "+
							" doctorName='"+(result.orderInfoClinicList[i]).doctorInfo.doctorName+"' "+
							" babyName='"+babyName+"' "+
							" orderStatus='"+(result.orderInfoClinicList[i]).orderStatus+"' "+
							" clinicAddress='"+(result.orderInfoClinicList[i]).clinicAddress+"' "+
							" realPrice='"+(result.orderInfoClinicList[i]).realPrice+"' "+
							" payMethod='"+(result.orderInfoClinicList[i]).payMethod+"' "+
							" clinicOrgType='"+(result.orderInfoClinicList[i]).clinicOrgType+"' "+
							" isPlus='"+(result.orderInfoClinicList[i]).isPlus+"' "+
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
    
	$('#orderNum').val($(obj).attr('orderNum'));
	$('#orderTime').val($(obj).attr('orderTime'));
	$('#totalPrice').val($(obj).attr('totalPrice'));
	$('#appointmentDate').val($(obj).attr('appointmentDate'));
	$('#babyName').val($(obj).attr('babyName'));
	$('#orderStatus').val($(obj).attr('orderStatus'));
	$('#clinicAddress').val($(obj).attr('clinicAddress'));
	$('#appointmentBeganTime').val($(obj).attr('appointmentBeganTime'));
	$('#appointmentEndTime').val($(obj).attr('appointmentEndTime'));
	$('#realPrice').val($(obj).attr('realPrice'));
	$('#payMethod').val($(obj).attr('payMethod'));
	$('#clinicOrgType').val($(obj).attr('clinicOrgType')==1?"其他机构":"本单位");
	$('#isPlus').val($(obj).attr('isPlus')=="Y"?"是":"否");
	$('#doctorInfo option').filter(function(){
		return $(this).val() == $(obj).attr('doctorId');
	}).get(0).selected = true;
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'doctorClinic.action?action=saveOrUpdateOrderInfoClinic');
	
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
	var data = {action:'getOrderInfoClinicList',
				'orderInfoClinic.userInfo.babyName':baby_name ,
				'orderInfoClinic.orderStatus':search_status ,
				'orderInfoClinic.appointmentDate':clinicDate ,
				'orderInfoClinic.orderNum':orderNum ,
				'orderInfoClinic.doctorInfo.doctorName':doctor_name
				};
	getAllList(data);
}

