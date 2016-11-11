var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:5%'>业务</th>"+
			"<th style='width:10%'>医生姓名</th>"+
			"<th style='width:15%'>多点执业地点</th>"+
			"<th style='width:10%'>工作日期</th>"+
			"<th style='width:10%'>计划开始时间</th>"+
			"<th style='width:10%'>计划结束时间</th>"+
			"<th style='width:10%'>医生薪酬</th>"+
			"<th style='width:10%'>每例费用</th>"+
			"<th style='width:5%'>用户类型</th>"+
			"<th style='width:10%'>"+
				"<div  class='th_button'>操作</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getDoctorMoneyRecordList'};
	getAllList(data);
	//加载用户类型选择
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:false, 
		data:{
			action:"getUserTypeList"
		},
		success:function(result){
			$('#userType').empty();
			$('#userType').append("<option value=''>-请选择-</option>");
			if(result.userTypeList == null){
				return false;
			}
			for(var i=0;i < result.userTypeList.length;i++){
				$('#userType').append(
						"<option value='"+(result.userTypeList[i]).id+"'>"+(result.userTypeList[i]).typeName+"</option>"
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
			}else if(result.doctorMoneyRecordList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.doctorMoneyRecordList.length;i ++){
				var businessType = (result.doctorMoneyRecordList[i]).hospitalPosition.businessType;
				var workPlace = (result.doctorMoneyRecordList[i]).hospitalPosition.doctorMorePracticeOrgInfo.orgName;
				var userType = (result.doctorMoneyRecordList[i]).userType;
				if(businessType == "0"){
					businessType = "门诊"
				}else if(businessType == "1"){
					businessType = "儿保"
				}
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:5%'>"+businessType+"</td>"+
					"<td style='width:10%'>"+(result.doctorMoneyRecordList[i]).doctorInfo.doctorName+"</td>"+
					"<td style='width:15%'>"+workPlace+"</td>"+
					"<td style='width:10%'>"+(result.doctorMoneyRecordList[i]).workDate+"</td>"+
					"<td style='width:10%'>"+(result.doctorMoneyRecordList[i]).workStartTime+"</td>"+
					"<td style='width:10%'>"+(result.doctorMoneyRecordList[i]).workEndTime+"</td>"+
					"<td style='width:10%'>"+(result.doctorMoneyRecordList[i]).money+"</td>"+
					"<td style='width:10%'>"+(result.doctorMoneyRecordList[i]).moneyPer+"</td>"+
					"<td style='width:5%'>"+(userType==null?'':userType.typeName)+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.doctorMoneyRecordList[i]).id+"' "+
							" businessType='"+businessType+"' "+
							" workPlace='"+workPlace+"' "+
							" userTypeId='"+(userType==null?'':userType.id)+"' "+
							" doctorName='"+(result.doctorMoneyRecordList[i]).doctorInfo.doctorName+"' "+
							" workDate='"+(result.doctorMoneyRecordList[i]).workDate+"' "+
							" workStartTime='"+(result.doctorMoneyRecordList[i]).workStartTime+"' "+
							" workEndTime='"+(result.doctorMoneyRecordList[i]).workEndTime+"' "+
							" money='"+(result.doctorMoneyRecordList[i]).money+"' "+
							" moneyPer='"+(result.doctorMoneyRecordList[i]).moneyPer+"' "+
							" remark='"+(result.doctorMoneyRecordList[i]).remark+"' "+
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
    
	$('#businessType').val($(obj).attr('businessType'));
	$('#workPlace').val($(obj).attr('workPlace'));
	$('#doctorName').val($(obj).attr('doctorName'));
	$('#workDate').val($(obj).attr('workDate'));
	$('#workStartTime').val($(obj).attr('workStartTime'));
	$('#workEndTime').val($(obj).attr('workEndTime'));
	$('#money').val($(obj).attr('money'));
	$('#moneyPer').val($(obj).attr('moneyPer'));
	$('#remark').val($(obj).attr('remark'));
	$('#userType option').each(function(){
		if($(this).val() == $(obj).attr('userTypeId')){
			$(this).attr("selected","selected")
		}
	});
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=updateDoctorMoneyRecord');
	
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
	if($("#userType").val()==""){
		alert("请选择面对用户类型");
		$("#userType").focus();
		return false;
	}
	if($("#money").val()==""){
		alert("请填写医生当天薪酬");
		$("#money").focus();
		return false;
	}
	if($("#moneyPer").val()==""){
		alert("请填写单例费用");
		$("#moneyPer").focus();
		return false;
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
	var doctorName_con = $("#doctorName_con").val();
	var workDate_con = $("#workDate_con").val();
	//var search_status = $("#search_status").find("option:selected").text();
	var data = {action:'getDoctorMoneyRecordList',
				'doctorMoneyRecord.workDate':workDate_con ,
				'doctorMoneyRecord.doctorInfo.doctorName':doctorName_con
				};
	getAllList(data);
}

