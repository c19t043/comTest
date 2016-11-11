var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>工作单位</th>"+
			"<th style='width:5%'>本单位分成</th>"+
			"<th style='width:10%'>本单位门诊费</th>"+
			"<th style='width:10%'>职称</th>"+
			"<th style='width:10%'>其他机构名称</th>"+
			"<th style='width:5%'>保底薪酬</th>"+
			"<th style='width:10%'>半天保底薪酬</th>"+
			"<th style='width:10%'>外单位门诊费</th>"+
			"<th style='width:10%'>额外单笔分成</th>"+
			"<th style='width:5%'>业务类型</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button' onclick='add_info()'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getHospitalPositionList'};
	getAllList(data);
	addSelectList();
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
			}else if(result.hospitalPositionList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.hospitalPositionList.length;i ++){
				var hospitalBasicInfo = (result.hospitalPositionList[i]).hospitalBasicInfo;
				var hospitalLname = hospitalBasicInfo==null?'':hospitalBasicInfo.hospitalLname;
				var hospitalId = hospitalBasicInfo==null?'':hospitalBasicInfo.id;
				var position = (result.hospitalPositionList[i]).position;
				var positionName = position==null?'':position.name;
				var positionId = position==null?'':position.id;
				var doctorMorePracticeOrgInfo = (result.hospitalPositionList[i]).doctorMorePracticeOrgInfo;
				var outOrgName = doctorMorePracticeOrgInfo==null?'':doctorMorePracticeOrgInfo.orgName;
				var outOrgId = doctorMorePracticeOrgInfo==null?'':doctorMorePracticeOrgInfo.id;
				var businessType = ""
					if((result.hospitalPositionList[i]).businessType == '0'){
						businessType = "门诊";
					}else if((result.hospitalPositionList[i]).businessType == '1'){
						businessType = "儿保";
					}
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+hospitalLname+"</td>"+
					"<td style='width:5%'>"+(result.hospitalPositionList[i]).commission+"</td>"+
					"<td style='width:10%'>"+(result.hospitalPositionList[i]).clinicMoney+"</td>"+
					"<td style='width:10%'>"+positionName+"</td>"+
					"<td style='width:10%'>"+outOrgName+"</td>"+
					"<td style='width:5%'>"+(result.hospitalPositionList[i]).baseSalary+"</td>"+
					"<td style='width:10%'>"+(result.hospitalPositionList[i]).halfDayMoney+"</td>"+
					"<td style='width:10%'>"+(result.hospitalPositionList[i]).clinicMoneyOut+"</td>"+
					"<td style='width:10%'>"+(result.hospitalPositionList[i]).commissionPerCase+"</td>"+
					"<td style='width:5%'>"+businessType+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.hospitalPositionList[i]).id+"' "+
							" hospitalId='"+hospitalId+"' "+
							" commission='"+(result.hospitalPositionList[i]).commission+"' "+
							" clinicMoney='"+(result.hospitalPositionList[i]).clinicMoney+"' "+
							" positionId='"+positionId+"' "+
							" outOrgId='"+outOrgId+"' "+
							" baseSalary='"+(result.hospitalPositionList[i]).baseSalary+"' "+
							" halfDayMoney='"+(result.hospitalPositionList[i]).halfDayMoney+"' "+
							" clinicMoneyOut='"+(result.hospitalPositionList[i]).clinicMoneyOut+"' "+
							" commissionPerCase='"+(result.hospitalPositionList[i]).commissionPerCase+"' "+
							" businessType='"+(result.hospitalPositionList[i]).businessType+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
//添加下拉选项
function addSelectList(){
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{action:'getSelectList'},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}
			if(result.hospitalBasicInfoList != null){//工作单位选项
				var list = result.hospitalBasicInfoList;
				$('#hospitalName').empty();
				for(var i=0;i < list.length;i ++){
					$("#hospitalName").append(
							"<option value='"+list[i].id +"'>"+list[i].hospitalLname+"</option>"
					);
				}
				$('#hospitalName').prepend("<option value=''>=请选择=</option>");
			}
			if(result.positionList != null){//职称选项
				var list = result.positionList;
				$('#positionName').empty();
				for(var i=0;i < list.length;i ++){
					$("#positionName").append(
							"<option value='"+list[i].id +"'>"+list[i].name+"</option>"
					);
				}
				$('#positionName').prepend("<option value=''>=请选择=</option>");
			}
			if(result.morePracticeOrgList != null){//其他单位选项
				var list = result.morePracticeOrgList;
				$('#doctorMorePracticeOrgInfo').empty();
				for(var i=0;i < list.length;i ++){
					$("#doctorMorePracticeOrgInfo").append(
							"<option value='"+list[i].id +"'>"+list[i].orgName+"</option>"
					);
				}
				$('#doctorMorePracticeOrgInfo').prepend("<option value=''>=请选择=</option>");
			}
		}
	});
}
//下拉选项条件过滤
function filterWorkUnit(obj,flag){
	var condiTion = $(obj).val();
	var data;
	if("工作单位选项" == flag){
		$('#hospitalName').empty();
		data = {action:'getSelectList',
				"hospitalBasicInfo.hospitalLname":condiTion
			};
	}else if("其他单位选项" == flag){
		$('#doctorMorePracticeOrgInfo').empty();
		data = {action:'getSelectList',
				"doctorMorePracticeOrgInfo.orgName":condiTion
			};
	}
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}
			if(result.hospitalBasicInfoList != null && "工作单位选项" == flag){//工作单位选项
				var list = result.hospitalBasicInfoList;
				$('#hospitalName').empty();
				for(var i=0;i < list.length;i ++){
					$("#hospitalName").append(
							"<option value='"+list[i].id +"'>"+list[i].hospitalLname+"</option>"
					);
				}
				$('#hospitalName').prepend("<option value=''>=请选择=</option>");
			}
			if(result.morePracticeOrgList != null && "其他单位选项" == flag){//其他单位选项
				var list = result.morePracticeOrgList;
				$('#doctorMorePracticeOrgInfo').empty();
				for(var i=0;i < list.length;i ++){
					$("#doctorMorePracticeOrgInfo").append(
							"<option value='"+list[i].id +"'>"+list[i].orgName+"</option>"
					);
				}
				$('#doctorMorePracticeOrgInfo').prepend("<option value=''>=请选择=</option>");
			}
		}
	});
}
//修改读取信息
function read_info(obj){
	addSelectList();
    $('#bottom_div').slideDown(500);
    $('#bottom_div_con input').val('');
    
    $('#keyId').val($(obj).attr('keyId'));
    
    $('#hospitalName option').filter(function(){
		return $(this).val() == $(obj).attr('hospitalId');
	}).get(0).selected = true;
    $('#commission').val($(obj).attr('commission'));
    $('#clinicMoney').val($(obj).attr('clinicMoney'));
    $('#positionName option').filter(function(){
		return $(this).val() == $(obj).attr('positionId');
	}).get(0).selected = true;
	$('#doctorMorePracticeOrgInfo option').filter(function(){
		return $(this).val() == $(obj).attr('outOrgId');
	}).get(0).selected = true;
	$('#businessType option').each(function(){
		if($(this).val() == $(obj).attr('businessType')){
			$(this).attr("selected","selected");
		}
	});
	$('#baseSalary').val($(obj).attr('baseSalary'));
	$('#halfDayMoney').val($(obj).attr('halfDayMoney'));
	$('#clinicMoneyOut').val($(obj).attr('clinicMoneyOut'));
	$('#commissionPerCase').val($(obj).attr('commissionPerCase'));
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'doctorClinic.action?action=saveOrUpdateHospitalPosition');
	
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
	if($('#hospitalName').val().trim() == ''){
		alert('请选择医院！');
		return false;	
	}
	if($('#positionName').val().trim() == ''){
		alert('请选择职称！');
		return false;	
	}
	//添加机构时，检查是否添加过
	var msg="";
	if($.trim($('#doctorMorePracticeOrgInfo').val()) != ''){
		$.ajax({
			type:'post',
			url:ringHost+'doctorClinic.action',
			cache:false,
			async:false, 
			data:{
				action:"getHospitalPositionList",
				"hospitalPosition.hospitalBasicInfo.id":$('#hospitalName').val().trim(),
				"hospitalPosition.position.id":$('#positionName').val().trim(),
				"hospitalPosition.businessType":$('#businessType').val().trim(),
				"hospitalPosition.doctorMorePracticeOrgInfo.id":$('#doctorMorePracticeOrgInfo').val().trim()
			},
			success:function(result){
				if(result.mes != '操作成功'){
					alert('数据返回错误！');
					return false;
				}else if(result.hospitalPositionList != null){
					msg += $("#doctorMorePracticeOrgInfo").find("option:selected").text()+","+
							$("#hospitalName").find("option:selected").text()+","+
							$("#positionName").find("option:selected").text()+","+
							$("#businessType").find("option:selected").text()+","+
							"已设置过！";
					return false;			
				}
			}
		});
	}
	if(msg != "" && "" == $("#keyId").val()){
		alert(msg);
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
//查询
function search_click(){
	var hospitalNmae = $("#hospitalNmae").val();
	var majorName = $("#majorName").val();
	var clinicOrg = $("#clinicOrg").val();
	var data = {action:'getHospitalPositionList',
				'hospitalPosition.hospitalBasicInfo.hospitalLname':hospitalNmae ,
				'hospitalPosition.position.name':majorName ,
				'hospitalPosition.doctorMorePracticeOrgInfo.orgName':clinicOrg
				};
	getAllList(data);
}

