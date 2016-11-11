var ringHost="../admin/";

$(function(){
	//加载加号选择
	$("#isAddClinic").empty();
	for(var i=0; i<=20; i++){
		$("#isAddClinic").append("<option value='"+i+"'>"+i);
	}
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>医生姓名</th>"+
			"<th style='width:10%'>门诊机构</th>"+
			"<th style='width:10%'>门诊日期</th>"+
			"<th style='width:5%'>计划开始时间</th>"+
			"<th style='width:5%'>计划结束时间</th>"+
			"<th style='width:5%'>可接待量</th>"+
			"<th style='width:5%'>可加号量</th>"+
			"<th style='width:5%'>门诊状态</th>"+
			"<th style='width:10%'>实际开始时间</th>"+
			"<th style='width:10%'>实际结束时间</th>"+
			"<th style='width:10%'>面向用户类型</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button' onclick='add_info()'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getDoctorMorePracticeList'};
	getAllList(data);

	//加载机构日期选择
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{
			action:"getCanClinicOrgAndDateList"
		},
		success:function(result){
			if(result.moreOrgClinicdateList == null){
				return false;
			}
			for(var i=0;i < result.moreOrgClinicdateList.length;i++){
				var doctorMorePracticeOrgInfo = (result.moreOrgClinicdateList[i]).doctorMorePracticeOrgInfo;
				var outOrgId = doctorMorePracticeOrgInfo==null?'':doctorMorePracticeOrgInfo.id;
				var hospitalBasicInfo = doctorMorePracticeOrgInfo==null?null:doctorMorePracticeOrgInfo.hospitalBasicInfo;
				var hospitalId = hospitalBasicInfo==null?'':hospitalBasicInfo.id; 
				$('#doctorMoreOrgClinicdate').append(
						"<option value='"+(result.moreOrgClinicdateList[i]).id+"' data_orgId='"+
						(result.moreOrgClinicdateList[i]).doctorMorePracticeOrgInfo.id+"' data_hospitalId='"+
						hospitalId+"'>"+
						(result.moreOrgClinicdateList[i]).doctorMorePracticeOrgInfo.orgName+" "+
						(result.moreOrgClinicdateList[i]).canClinicDate+
						"</option>"
				);
			}
		}
	});
}); 
//选择开放机构和日期,级联到对应的时间点
function selectOrgDate(obj){
	var orgId = $(obj).find("option:selected").attr("data_orgId");
	$('#orgClinicTimeIds').empty();
	if(orgId == undefined){
		return false;
	}
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{
			action:"getMoreOrgOpenInfo",
			"doctorMorePracticeOrgInfo.id":orgId
		},
		success:function(result){
			if(result.morePracticeOrgTimeList == null){
				return false;
			}
			for(var i=0;i < result.morePracticeOrgTimeList.length;i++){
				$('#orgClinicTimeIds').append(
						"<option value='"+(result.morePracticeOrgTimeList[i]).id+"'>"+
						(result.morePracticeOrgTimeList[i]).timeName+":"+
						(result.morePracticeOrgTimeList[i]).startTime+"-"+
						(result.morePracticeOrgTimeList[i]).endTime+
						"</option>"
				);
			}
		}
	});
}

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
			}else if(result.doctorMorePracticeList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.doctorMorePracticeList.length;i ++){
				var doctorInfo = (result.doctorMorePracticeList[i]).doctorInfo;
				var doctorMorePracticeOrgInfo = (result.doctorMorePracticeList[i]).doctorMorePracticeOrgInfo;
				var outOrgId = doctorMorePracticeOrgInfo==null?'':doctorMorePracticeOrgInfo.id;
				
				var hospitalBasicInfo = doctorMorePracticeOrgInfo==null?null:doctorMorePracticeOrgInfo.hospitalBasicInfo;
				var orgId = hospitalBasicInfo==null?'':hospitalBasicInfo.id;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+doctorInfo.doctorName+"</td>"+
					"<td style='width:10%'>"+(result.doctorMorePracticeList[i]).clinicOrg+"</td>"+
					"<td style='width:10%'>"+(result.doctorMorePracticeList[i]).clinicDate+"</td>"+
					"<td style='width:5%'>"+(result.doctorMorePracticeList[i]).clinicBeganTime+"</td>"+
					"<td style='width:5%'>"+(result.doctorMorePracticeList[i]).clinicEndTime+"</td>"+
					"<td style='width:5%'>"+(result.doctorMorePracticeList[i]).canClinicNum+"</td>"+
					"<td style='width:5%'>"+(result.doctorMorePracticeList[i]).isAddClinic+"</td>"+
					"<td style='width:5%'>"+(result.doctorMorePracticeList[i]).orgClinicStatus+"</td>"+
					"<td style='width:10%'>"+(result.doctorMorePracticeList[i]).orgClinicStartTime+"</td>"+
					"<td style='width:10%'>"+(result.doctorMorePracticeList[i]).orgClinicEndTime+"</td>"+
					"<td style='width:10%'>"+(result.doctorMorePracticeList[i]).userTypeNames+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.doctorMorePracticeList[i]).id+"' "+//该条目id(医生多点工作表id)
							" doctorId='"+doctorInfo.id+"' "+//医生id
							" doctorName='"+doctorInfo.doctorName+"' "+//医生姓名
							" outOrgId='"+outOrgId+"' "+//外出门诊id
							" orgId='"+orgId+"' "+//外出门诊机构id
							" clinicDate='"+(result.doctorMorePracticeList[i]).clinicDate+"' "+//门诊时间
							" clinicEndTime='"+(result.doctorMorePracticeList[i]).clinicEndTime+"' "+
							" clinicBeganTime='"+(result.doctorMorePracticeList[i]).clinicBeganTime+"' "+
							" canClinicNum='"+(result.doctorMorePracticeList[i]).canClinicNum+"' "+
							" clinicOrg='"+(result.doctorMorePracticeList[i]).clinicOrg+"' "+
							" clinicAddress='"+(result.doctorMorePracticeList[i]).clinicAddress+"' "+//外出门诊地址
							" isRepeat='"+(result.doctorMorePracticeList[i]).isRepeat+"' "+
							" clinicOrgType='"+(result.doctorMorePracticeList[i]).clinicOrgType+"' "+
							" isAddClinic='"+(result.doctorMorePracticeList[i]).isAddClinic+"' "+
							" operationTime='"+(result.doctorMorePracticeList[i]).operationTime+"' "+
							" orgClinicTimeIds='"+(result.doctorMorePracticeList[i]).orgClinicTimeIds+"' "+
							" orgClinicStatus='"+(result.doctorMorePracticeList[i]).orgClinicStatus+"' "+
							" orgClinicStartTime='"+(result.doctorMorePracticeList[i]).orgClinicStartTime+"' "+
							" orgClinicStartAddress='"+(result.doctorMorePracticeList[i]).orgClinicStartAddress+"' "+
							" orgClinicEndTime='"+(result.doctorMorePracticeList[i]).orgClinicEndTime+"' "+
							" orgClinicEndAddress='"+(result.doctorMorePracticeList[i]).orgClinicEndAddress+"' "+
							" userTypeIds='"+(result.doctorMorePracticeList[i]).userTypeIds+"' "+
							" userTypeNames='"+(result.doctorMorePracticeList[i]).userTypeNames+"' "+
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
    
    $("#onlyShow").show();
    
    var clinicOrgType = $(obj).attr('clinicOrgType');
    $('#keyId').val($(obj).attr('keyId'));

    $('#clinicDate').val($(obj).attr('clinicDate'));
    $('#clinicAddress').val($(obj).attr('clinicAddress'));
    //$('#clinicAddress').val();

    $('#clinicEndTime').val($(obj).attr('clinicEndTime'));
	$('#clinicBeganTime').val($(obj).attr('clinicBeganTime'));
	$('#canClinicNum').val($(obj).attr('canClinicNum'));
	$('#clinicOrg').val($(obj).attr('clinicOrg'));
	$('#isAddClinic').val($(obj).attr('isAddClinic'));
	$('#orgClinicStatus').val($(obj).attr('orgClinicStatus'));
	$('#orgClinicStartTime').val($(obj).attr('orgClinicStartTime'));
	$('#orgClinicStartAddress').val($(obj).attr('orgClinicStartAddress'));
	$('#orgClinicEndTime').val($(obj).attr('orgClinicEndTime'));
	$('#orgClinicEndAddress').val($(obj).attr('orgClinicEndAddress'));
	$('#userTypeIds').val($(obj).attr('userTypeIds'));
	$('#show_userType').text($(obj).attr('userTypeNames'));
	loadOrgOpenTime($(obj).attr('keyId'));
	
	//--------------------修改bug
	 //修改bug
/*	 $('#doctorInfo option').filter(function(){
		 return $(this).val() == $(obj).attr('doctorId');
	 }).get(0).selected = true;*/

	$("#show_doctor").text($(obj).attr('doctorName'));
	//--------------------
    //--------------添加的代码
    //设置外出门诊机构的id
    $('#orgId').val($(obj).attr('orgId'));
    //隐藏确认按钮
    $("#button_add_click").hide();

}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'doctorClinic.action?action=saveOrUpdateDoctorMorePractice');
	
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
	if($('#doctorMoreOrgClinicdate').val().trim() == ''){
		alert('请选择机构和日期！');
		return false;	
	}
	var timeIdsLen = $("#orgClinicTimeIds option:selected").length;
	if(timeIdsLen == 0){
		alert('请选择机构时间！');
		return false;	
	}
	if($('#doctorIds').val().trim() == ''){
		alert('请选择坐诊医生！');
		return false;	
	}
	//检查基础分成比例是否已经配置
	var orgId = $("#doctorMoreOrgClinicdate option:selected").attr("data_hospitalId");
	var doctorId = $("#doctorIds").val();
	var flag = findHospitalPosition(orgId,doctorId);
	if(!flag){
		alert("没有查找到该医生相关的'医院职称分成'记录,请添加记录后再操作");
		return false;	
	}
	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#show_userType').text('');
	$('#show_doctor').text('');
	$('#button_add_click').show();
	$('#onlyShow').hide();
	$('#bottom_div').slideDown(500);
}
//加载机构开放时间
function loadOrgOpenTime(fkId){
	
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:true, 
		data:{
			action:"getDoctorClinicTimeSegmentList",
			"doctorMorePractice.id":fkId
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.doctorClinicTimeSegmentList == null){
				alert('没有数据！');
				return false;			
			}
			$('#node_Tbody').empty();
			//加载表头
			$('#node_table_title').empty();
			$('#node_table_title').append(
				"<tr>"+
					"<th style='width:20%'>序列</th>"+
					"<th style='width:40%'>时间点</th>"+
					"<th style='width:40%'>是否可用</th>"+
				"</tr>"
			);
			for(var i=0;i < result.doctorClinicTimeSegmentList.length;i ++){
				$('#node_Tbody').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:20%'>"+(i+1)+"</td>"+
					"<td style='width:40%'>"+(result.doctorClinicTimeSegmentList[i]).segment+"</td>"+
					"<td style='width:40%'>"+(result.doctorClinicTimeSegmentList[i]).isCanUse+"</td>"+
				"</tr>"
				);
			}
		}
	});
	

}
//查询
function search_click(){
	var clinicOrg = $("#clinicOrg_query").val();
	var doctorName = $("#doctorName").val();
	var search_status = $("#search_status").find("option:selected").text();
	var clinicDate = $("#clinicDate_query").val();
	var data = {action:'getDoctorMorePracticeList',
				'doctorMorePractice.clinicOrg':clinicOrg ,
				'doctorMorePractice.orgClinicStatus':search_status ,
				'doctorMorePractice.clinicDate':clinicDate ,
				'doctorMorePractice.doctorInfo.doctorName':doctorName
				};
	getAllList(data);
}

//选择修改（弹出遮罩）
function chooseObj(obj){
	//遮罩
	$("#bg").css({
        display: "block", height: $(document).height()
    });
    var $box = $('.boxZhezhao');
    $box.css({
        //设置弹出层距离左边的位置
        left: ($("body").width() - $box.width()) / 2 - 20 + "px",
        //设置弹出层距离上面的位置
        top: ($(window).height() - $box.height()) / 2 + $(window).scrollTop() - 30+ "px",
        display: "block"
    }); 
    if("doctor" == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>" +
    			"<td colspan='5'>" +
	    			"医生姓名：<input type='text' id='doctorNameSelect'/>" +
	    			"  工作单位：<input type='text' id='doctorEmployerSelect'/>" +
	    			"<button onclick='search()' style='width: 80px;height: 22px;'>查找</button>" +
    			"</td</tr>"+
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_node('doctor') class='th_button'>选择</div></th>"+
				"<th style='width:30%'>工作单位</th>"+
				"<th style='width:10%'>医生姓名</th>"+
				"<th style='width:10%'>医生职称</th>"+
			"</tr>"
		);
    	//加载多点服务医生选择
    	$.ajax({
    		type:'post',
    		url:ringHost+'doctorClinic.action',
    		cache:false,
    		async:true, 
    		data:{
    			action:"getAllDoctor"
    		},
    		success:function(result){
    			//医生选项
    		    loadDoctor(result.doctorInfoList);
    		}
    	});
    }
    else if('userType' == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_userType('userType') class='th_button'>选择</div></th>"+
				"<th style='width:10%'>类型名称</th>"+
				"<th style='width:10%'>类型编码</th>"+
				"<th style='width:30%'>备注</th>"+
			"</tr>"
		);
    	//加载用户类型选择
    	$.ajax({
    		type:'post',
    		url:ringHost+'orgBusiness.action',
    		cache:false,
    		async:true, 
    		data:{
    			action:"getUserTypeList"
    		},
    		success:function(result){
    			//用户类型选项
    		    loadUserType(result.userTypeList);
    		}
    	});
    }
}
//加载开放服务选择内容
function loadDoctor(list){
	$('#selectContent').empty();
	var doctorIds = $('#doctorIds').val();
	if(doctorIds != null){
		doctorIds = doctorIds.split(",");
	}
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].id+"";
		if(doctorIds != null && $.inArray(id,doctorIds) > -1){
			selected = "checked";
		}
		$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
				"<td style='width:10%'>"+(i+1)+"</td>" +
				"<td style='width:10%'><input name='doctorName' type='radio' "+selected+" data_name='"+list[i].doctorName+"' value='"+list[i].id+"'/></td>"+
				"<td style='width:30%'>"+(list[i].doctorEmployer)+"</td>"+
				"<td style='width:10%'>"+(list[i].doctorName)+"</td>"+
				"<td style='width:10%'>"+(list[i].doctorTitle)+"</td>"+
				"</tr>"
		);
	}
}
//加载用户类型选择内容
function loadUserType(list){
	$('#selectContent').empty();
	var userTypeIds = $('#userTypeIds').val();
	if(userTypeIds != null){
		userTypeIds = userTypeIds.split(",");
	}
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].id+"";
		if(userTypeIds != null && $.inArray(id,userTypeIds) > -1){
			selected = "checked";
		}
		$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
				"<td style='width:10%'>"+(i+1)+"</td>" +
				"<td style='width:10%'><input name='userTypeName' type='checkbox' "+selected+" data_name='"+list[i].typeName+"' value='"+list[i].id+"'/></td>"+
				"<td style='width:10%'>"+(list[i].typeName)+"</td>"+
				"<td style='width:10%'>"+(list[i].typeCode)+"</td>"+
				"<td style='width:30%'>"+(list[i].remark)+"</td>"+
				"</tr>"
		);
	}
}
//隐藏遮罩层  
function hideMask(){     
	 $("#bg,.boxZhezhao").css("display", "none");   
}
//确定选中用户类型
function add_userType(obj){
	var nodeId = "";
	var nodeName = "";
	$('#selectContent input:checkbox:checked').each(function(){
		nodeId += $(this).val() + ",";
		nodeName += $(this).attr('data_name') + ",";
	});
	if(nodeId == ""){
		alert("没有选中的数据");
		return false;
	}
	nodeId = nodeId.substring(0, nodeId.lastIndexOf(","));
	nodeName = nodeName.substring(0, nodeName.lastIndexOf(","));
	$("#userTypeIds").val(nodeId);
	$("#show_userType").text(nodeName);
	//关闭遮罩
	hideMask();
}
//确定选中医生
function add_node(obj){
	var nodeId = "";
	var nodeName = "";
	$('#selectContent input:radio:checked').each(function(){
		nodeId += $(this).val() + ",";
		nodeName += $(this).attr('data_name') + ",";
	});
	if(nodeId == ""){
		alert("没有选中的数据");
		return false;
	}
	//----------------------------修改的代码
	/*
	 * 1。关闭遮罩
	 * 2.修改后台医生关联数据
	 * 3.修改页面数据
	 */
	//关闭遮罩
	hideMask();
	
	//修改后台医生关联数据
	nodeId = nodeId.substring(0,nodeId.lastIndexOf(","));
	var result = true;
	var keyid = $("#keyId").val();
	if(keyid != ""){
		updateDoctorClinicRecord(nodeId);
	}
	//修改页面医生数据
	/*
	 * 后台数据修改返回结果
	 * 1.结果是true，执行修改页面数据
	 * 2.false,不操作
	 */
	if(result){
		nodeName = nodeName.substring(0,nodeName.lastIndexOf(","));
		if("doctor" == obj){
			$("#doctorIds").val(nodeId);
			$("#show_doctor").text(nodeName);
		}
	}
	//----------------------------
}
/**
 * 更新医生门诊记录
 * @param doctorId
 */
function updateDoctorClinicRecord(doctorId){
	
	var keyid = $("#keyId").val();
	var clinicDate =  $('#clinicDate').val();
	var clinicAddress =  $('#clinicAddress').val();
	
	/*
	 * 1.同步查询医生职称分成记录
	 * 2.修改后台医生关联数据（门诊设置信息管理订单id，相关订单的医生id）
	 * 3.返回修改结果（成功/失败）
	 */
	//同步查询医生职称分成记录
	/*
	 * 1.如果没有医生职称分成记录,弹出提示，
	 * 		没有查找到该医生相关的'医院职称分成'记录,请添加记录后再操作
	 * 2.存在记录，执行后续操作
	 */
	
	/*
	 * 验证
	 */
	var orgId = $("#orgId").val();
	if(!orgId){
		alert("数据有误,请数据修正后再进行后续操作");
		return;
	}
	
	var hospitalPositionId = findHospitalPosition(orgId,doctorId);
	if(!hospitalPositionId){
		alert("没有查找到该医生相关的'医院职称分成'记录,请添加记录后再操作");
		return;
	}
	
	//修改后台医生关联数据
	var resultFlag;
	$.ajax({
		url:ringHost+"doctorClinic.action",
		type:'POST',
		async:false,
		data:{
			"action":"updateDoctorMorePracticeRecord",
			"doctorMorePractice.id":keyid,
			"doctorMorePractice.doctorInfo.id":doctorId,
			"doctorMorePractice.clinicDate":clinicDate,
			"doctorMorePractice.clinicAddress":clinicAddress
		},
		success:function(result){
			if(result.mes=="true"){
				resultFlag = true;
			}else{
				resultFlag = false;
			}
		}
	});
	return resultFlag
}
/**
 * 根据门诊,获取门诊机构id
 * @returns
 */
function getClinicOrgId(){
	$.ajax({
		url:ringHost+"orgBusiness.action",
		type:"POST",
		async:false,
		data:{
			'action':"findHospitalPosition",
			'orgId':orgId,
			'doctorId':doctorId,
			'businessType':'0'//门诊
		},
		success:function(result){
			if(result.hospitalPosition)
				hospitalPositionId = result.hospitalPosition.id;
			else 
				return false;
		}
	});
	return hospitalPositionId;
}
/**
 * 根据社区医院id,医生id,查找医生职称分成记录
 * @param orgId
 * @param doctorId
 */
function findHospitalPosition(orgId,doctorId){
	if(!orgId) return false;
	if(!doctorId) return false;
	
	var hospitalPositionId;
	
	$.ajax({
		url:ringHost+"orgBusiness.action",
		type:"POST",
		async:false,
		data:{
			'action':"findHospitalPosition",
			'orgId':orgId,
			'doctorId':doctorId,
			'businessType':'0'//门诊
		},
		success:function(result){
			if(result.hospitalPosition)
				hospitalPositionId = result.hospitalPosition.id;
			else 
				return false;
		}
	});
	return hospitalPositionId;
}
//弹出框查询
function search(){
	var name = $("#doctorNameSelect").val();
	var doctorEmployerSelect = $("#doctorEmployerSelect").val();
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{
			action:"getAllDoctor",
			"doctorInfo.doctorName":name,
			"doctorInfo.doctorEmployer":doctorEmployerSelect
		},
		success:function(result){
			//医生选项
		    loadDoctor(result.doctorInfoList);
		}
	});
}
