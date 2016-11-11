var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>社区机构</th>"+
			"<th style='width:10%'>开放日期</th>"+
			"<th style='width:10%'>开始时间</th>"+
			"<th style='width:10%'>结束时间</th>"+
			"<th style='width:5%'>休息开始时间</th>"+
			"<th style='width:5%'>休息结束时间</th>"+
			"<th style='width:5%'>时间分割值</th>"+
			"<th style='width:7.5%'>普通窗口开放资源</th>"+
			"<th style='width:7.5%'>绿色通道开放资源</th>"+
			"<th style='width:5%'>分隔对象</th>"+
			"<th style='width:5%'>分隔需求</th>"+
			"<th style='width:5%'>是否可用</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button' onclick='add_info()' id='bt_add'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getOrganChildcareOpenResourcesList'};
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
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.hospitalBasicInfoList == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.hospitalBasicInfoList.length;i++){
				$('#hospitalBasicInfo').append(
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
			}else if(result.organChildcareOpenResourcesList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.organChildcareOpenResourcesList.length;i ++){
				var hospitalBasicInfo = (result.organChildcareOpenResourcesList[i]).hospitalBasicInfo;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+hospitalBasicInfo.hospitalLname+"</td>"+
					"<td style='width:10%'>"+(result.organChildcareOpenResourcesList[i]).openDate+"</td>"+
					"<td style='width:10%'>"+(result.organChildcareOpenResourcesList[i]).openStartTime+"</td>"+
					"<td style='width:10%'>"+(result.organChildcareOpenResourcesList[i]).openEndTime+"</td>"+
					"<td style='width:5%'>"+(result.organChildcareOpenResourcesList[i]).restStartTime+"</td>"+
					"<td style='width:5%'>"+(result.organChildcareOpenResourcesList[i]).restEndTime+"</td>"+
					"<td style='width:5%'>"+(result.organChildcareOpenResourcesList[i]).timeDivisionValue+"</td>"+
					"<td style='width:7.5%'>"+(result.organChildcareOpenResourcesList[i]).generalSurplusNum+"</td>"+
					"<td style='width:7.5%'>"+(result.organChildcareOpenResourcesList[i]).greenChannelSurplusNum+"</td>"+
					"<td style='width:5%'>"+(result.organChildcareOpenResourcesList[i]).timeDivisionType+"</td>"+
					"<td style='width:5%'>"+(result.organChildcareOpenResourcesList[i]).timeDivisionNeed+"</td>"+
					"<td style='width:5%'>"+(result.organChildcareOpenResourcesList[i]).isAvailable+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.organChildcareOpenResourcesList[i]).id+"' "+
							" hospitalId='"+hospitalBasicInfo.id+"' "+
							" openStartTime='"+(result.organChildcareOpenResourcesList[i]).openStartTime+"' "+
							" openDate='"+(result.organChildcareOpenResourcesList[i]).openDate+"' "+
							" openEndTime='"+(result.organChildcareOpenResourcesList[i]).openEndTime+"' "+
							" restStartTime='"+(result.organChildcareOpenResourcesList[i]).restStartTime+"' "+
							" restEndTime='"+(result.organChildcareOpenResourcesList[i]).restEndTime+"' "+
							" timeDivisionValue='"+(result.organChildcareOpenResourcesList[i]).timeDivisionValue+"' "+
							" generalSurplusNum='"+(result.organChildcareOpenResourcesList[i]).generalSurplusNum+"' "+
							" greenChannelSurplusNum='"+(result.organChildcareOpenResourcesList[i]).greenChannelSurplusNum+"' "+
							" isAvailable='"+(result.organChildcareOpenResourcesList[i]).isAvailable+"' "+
							" timeDivisionNeed='"+(result.organChildcareOpenResourcesList[i]).timeDivisionNeed+"' "+
							" timeDivisionType='"+(result.organChildcareOpenResourcesList[i]).timeDivisionType+"' "+
							" show_doctor='"+(result.organChildcareOpenResourcesList[i]).doctorNames+"' "+
							" doctorIds='"+(result.organChildcareOpenResourcesList[i]).doctorIds+"' "+
							" isMoney='"+(result.organChildcareOpenResourcesList[i]).isMoney+"' "+
							" deadline='"+(result.organChildcareOpenResourcesList[i]).deadline+"' "+
							" isUseDeadline='"+(result.organChildcareOpenResourcesList[i]).isUseDeadline+"' "+
						" onclick='read_info(this)' id='bt_edit'>修改</div>"+
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
    
    $('#openDate').val($(obj).attr('openDate'));
	$('#openStartTime').val($(obj).attr('openStartTime'));
	$('#openEndTime').val($(obj).attr('openEndTime'));
	$('#restStartTime').val($(obj).attr('restStartTime'));
	$('#restEndTime').val($(obj).attr('restEndTime'));
	$('#timeDivisionValue').val($(obj).attr('timeDivisionValue'));
	$('#generalSurplusNum').val($(obj).attr('generalSurplusNum'));
	$('#greenChannelSurplusNum').val($(obj).attr('greenChannelSurplusNum'));
	$('#hospitalBasicInfo option').filter(function(){
		return $(this).val() == $(obj).attr('hospitalId');
	}).get(0).selected = true;
	//-------------------------------增加的代码
	//设置修改项的机构id
	$("#orgId").val($(obj).attr('hospitalId'));
	edit_bt_flag("bt_edit");
	//----------------------------------
	$('#isAvailable option').filter(function(){
		return $(this).text() == $(obj).attr('isAvailable');
	}).get(0).selected = true;
	$('#timeDivisionType option').filter(function(){
		return $(this).text() == $(obj).attr('timeDivisionType');
	}).get(0).selected = true;
	$('#timeDivisionNeed option').filter(function(){
		return $(this).text() == $(obj).attr('timeDivisionNeed');
	}).get(0).selected = true;
	$('#isMoney option').filter(function(){
		return $(this).val() == $(obj).attr('isMoney');
	}).get(0).selected = true;
	$('#isUseDeadline option').filter(function(){
		return $(this).val() == $(obj).attr('isUseDeadline');
	}).get(0).selected = true;
	$('#show_doctor').text($(obj).attr('show_doctor'));
	$('#doctorIds').val($(obj).attr('doctorIds'));
	$('#deadline').val($(obj).attr('deadline'));
	
	$('#node_Tbody').empty();
	
	var need = $(obj).attr('timeDivisionNeed');
	if("时间点" == need){
		 $("#openNum").hide();
		 $("#timeDivisionType").show();
		 $("#timeDivisionType").prev().show();
	}else if("时间段" == need){
		 $("#openNum").show();
		 $("#timeDivisionType").hide();
		 $("#timeDivisionType").prev().hide();
	}
	var isUseDeadline = $(obj).attr('isUseDeadline');
	if("Y" == isUseDeadline){
		$("#deadlineObj").show();
	}else{
		$("#deadlineObj").hide();
	}
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:true, 
		data:{
			action:"getOrganChildcareOpenResourcesDatailList",
			"organChildcareOpenResources.id":$(obj).attr('keyId')
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.organChildcareOpenResourcesDatailList == null){
				alert('没有数据！');
				return false;			
			}
			$('#node_Tbody').empty();
			if("时间段" == need){
				//加载表头
				$('#node_table_title').empty();
				$('#node_table_title').append(
					"<tr>"+
						"<th style='width:10%'>序列</th>"+
						"<th style='width:20%'>开放开始时间段</th>"+
						"<th style='width:20%'>开放结束时间段</th>"+
						"<th style='width:20%'>普通窗口剩余资源</th>"+
						"<th style='width:20%'>绿色通道剩余资源</th>"+
						"<th style='width:10%'>操作</th>"+
					"</tr>"
				);
				for(var i=0;i < result.organChildcareOpenResourcesDatailList.length;i ++){
					$('#node_Tbody').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:10%'>"+(i+1)+"</td>"+
						"<td style='width:20%'>"+(result.organChildcareOpenResourcesDatailList[i]).openStartTime+"</td>"+
						"<td style='width:20%'>"+(result.organChildcareOpenResourcesDatailList[i]).openEndTime+"</td>"+
						"<td style='width:20%'>"+(result.organChildcareOpenResourcesDatailList[i]).generalSurplusNum+"</td>"+
						"<td style='width:20%'>"+(result.organChildcareOpenResourcesDatailList[i]).greenChannelSurplusNum+"</td>"+
						"<td style='width:10%'><span onclick='update_resoure(this,"+//style='border:3px solid #4a8bc2;'
						(result.organChildcareOpenResourcesDatailList[i]).generalSurplusNum+","+
						(result.organChildcareOpenResourcesDatailList[i]).greenChannelSurplusNum
						+")' class='td_change_button' >修改</span></td>"+
						"<input type='hidden' value='"+(result.organChildcareOpenResourcesDatailList[i]).id+"'>"+
						"<input type='hidden' value='"+(result.organChildcareOpenResourcesDatailList[i]).greenChannelNum+"'>"+
						"<input type='hidden' value='"+(result.organChildcareOpenResourcesDatailList[i]).generalNum+"'>"+
					"</tr>"
					);
				}
			}
			if("时间点" == need){
				//加载表头
				$('#node_table_title').empty();
				$('#node_table_title').append(
					"<tr>"+
						"<th style='width:20%'>序列</th>"+
						"<th style='width:40%'>时间点</th>"+
						"<th style='width:40%'>是否可用</th>"+
					"</tr>"
				);
				for(var i=0;i < result.organChildcareOpenResourcesDatailList.length;i ++){
					$('#node_Tbody').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:20%'>"+(i+1)+"</td>"+
						"<td style='width:40%'>"+(result.organChildcareOpenResourcesDatailList[i]).segment+"</td>"+
						"<td style='width:40%'>"+(result.organChildcareOpenResourcesDatailList[i]).isCanUse+"</td>"+
					"</tr>"
					);
				}
			}
		}
	});
}

/**
 * 修改儿保明细资源信息
 * @returns
 */
function update_resoure(obj,generalNum,greenNum){
	/*
	 *1.获取绿色窗口资源数据信息，info
	 *2.如果 info是数字（instanceof number）则资源信息可编辑
	 *3.不是数字，则修改资源信息
	 */
	var greenChannelSurplusNum = $(obj).parent().prev()[0];//绿色通道剩余资源
	var generalSurplusNum = $(obj).parent().prev().prev()[0];//普通窗口剩余资源
	if(greenChannelSurplusNum.innerHTML.indexOf('input')==-1){
		greenChannelSurplusNum.innerHTML  = "<input type='text' onmouseout='updateResData(this)' value='"+$(greenChannelSurplusNum).text()+"'>";
		generalSurplusNum.innerHTML  = "<input type='text' onmouseout='updateResData(this)' value='"+$(generalSurplusNum).text()+"'>";
		return;
	}else{
		/*
		 * 修改资源信息
		 */
		var update_greenChannelSurplusNum = $(greenChannelSurplusNum.innerHTML).val();
		var update_generalSurplusNum = $(generalSurplusNum.innerHTML).val();
		//alert(update_greenChannelSurplusNum);return;
		
		var generalNum = $(obj).parent().next().next()[0].value;//普通窗口总资源
		var greenChannelNum = $(obj).parent().next().next().next()[0].value;//绿色窗口总资源
		
		/*
		 * 验证
		 */
		var updateFlag = true;
		if(!/^[\d]$/.test(update_greenChannelSurplusNum) || !/^[\d]$/.test(update_generalSurplusNum)){
			alert("必须输入数字");
			return;
		}
		if(update_greenChannelSurplusNum>greenChannelNum) {
			alert("修改失败,绿色通道剩余资源高于总资源'"+greenChannelNum+"'");
			updateFlag =false;
		}
		if(update_generalSurplusNum>generalNum){
			alert("修改失败,普通通道剩余资源高于总资源'"+generalNum+"'");
			updateFlag =false;
		}
		
		/*
		 * 修改页面数据
		 */
		if(updateFlag){
			greenChannelSurplusNum.innerHTML = update_greenChannelSurplusNum;
			generalSurplusNum.innerHTML = update_generalSurplusNum;
		}else{
			greenChannelSurplusNum.innerHTML = generalNum;
			generalSurplusNum.innerHTML = greenNum;
		}

		/*
		 * 修改后台数据
		 */
		if(updateFlag){
			$.ajax({
				url:ringHost+"orgBusiness.action",
				type:'POST',
				async:true,
				data:{
					"action":"updateChildCareDetailData",
					"updateChildCareDetail.id":$(obj).parent().next()[0].value,
					"updateChildCareDetail.generalSurplusNum":update_generalSurplusNum,
					"updateChildCareDetail.greenChannelSurplusNum":update_greenChannelSurplusNum
				},
				success:function(result){
					if(result.mes)
						alert("修改成功");
				}
			});
		}
	}
}

/**
 * 鼠标移出事件处理
 */
function updateResData(obj){
	$(obj).parent()[0].innerHTML = "<input type='text' onmouseout='updateResData(this)' value='"+$(obj).val()+"'>";
}


//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=saveOrUpdateOrganChildcareOpenResources');
	
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
	if($("#isMoney").val() == "Y"){//收费
		if($("#doctorIds").val() == ''){
			alert("请选择坐诊医生");
			return false;
		}
	}
	
	if($("#isUseDeadline").val() == "Y"){//要预约截止时间
		if($("#deadline").val() == ''){
			alert("请选择预约截止时间");
			return false;
		}
	}
	
	if($("#openDate").val() == ""){
		alert("请选择开放日期");
		return false;
	}

	//--------------------增加的代码
	/* 点修改，修改儿保开放信息时
	 * 更新儿保设置信息
	 * 1.如果儿保资源明细资源已有预约，则，不能修改儿保设置
	 */
	if($("bt_flag").val()=="true"){
		var existFlag;
		$.ajax({
			url:ringHost+"orgBusiness.action",
			type:"POST",
			async:false,
			data:{
				"action":'checkExistOrder',
				"organChildcareOpenResources.id":$("#keyId").val()
			},
			success:function(result){
				if(result.mes=="true")
					existFlag = true;
				else 
					existFlag = false;
			}
		});
		if(existFlag){
			alert("儿保已有预约，不能修改");
			return;
		}
	}
	//--------------------

	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
	$('#node_table_title').hide();
	$('#node_Tbody').empty();
	
	edit_bt_flag("bt_add");
}

function edit_bt_flag(id){
	/*
	 * 增加代码,修改bt_flag标识
	 * 点击添加,value=false;
	 * 点击修改，value=true;
	 */
	if(id=='bt_add')
		$("#bt_flag").val("false");
	else if(id == 'bt_edit')
		$("#bt_flag").val("true");
}

function search_click(){
	var open_Date = $("#open_Date").val();
	var hospitalLname = $("#hospitalLname").val();
	var data = {action:'getOrganChildcareOpenResourcesList',
			'organChildcareOpenResources.hospitalBasicInfo.hospitalLname':hospitalLname ,
			'organChildcareOpenResources.openDate':open_Date ,
			};
	getAllList(data);
}
//改变时间需求（时间点不需要填写号源数）
function changeNeed(obj){
	var need = $(obj).find("option:selected").text();
	if("时间点" == need){
		 $("#openNum").hide();
		 $("#timeDivisionType").show();
		 $("#timeDivisionType").prev().show();
	}else if("时间段" == need){
		 $("#openNum").show();
		 $("#timeDivisionType").hide();
		 $("#timeDivisionType").prev().hide();
		 
	}
}
//改变预约截止时间需求
function changeDeadline(obj){
	var need = $(obj).find("option:selected").text();
	if("N" == need){
		$("#deadlineObj").hide();
		$("#deadline").val("");
	}else if("Y" == need){
		$("#deadlineObj").show();
	}
}

/**
 * 选择修改（弹出遮罩）
 */
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
    		async:false, 
    		data:{
    			action:"getAllDoctor"
    		},
    		success:function(result){
    			//医生选项
    		    loadDoctor(result.doctorInfoList);
    		}
    	});
    }
}
/**
 * 加载开放服务选择内容
 * @param list
 * @returns {Boolean}
 */
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
				"<td style='width:10%'><input type='radio' name='doctorNameSelect' "+selected+" data_name='"+list[i].doctorName+"' value='"+list[i].id+"'/></td>"+
				"<td style='width:30%'>"+(list[i].doctorEmployer)+"</td>"+
				"<td style='width:10%'>"+(list[i].doctorName)+"</td>"+
				"<td style='width:10%'>"+(list[i].doctorTitle)+"</td>"+
				"</tr>"
		);
	}
}
/**
 * 隐藏遮罩层
 */  
function hideMask(){     
	 $("#bg,.boxZhezhao").css("display", "none");   
}
/**
 * 确定选中擅长领域
 */
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
	
	hideMask();
	//-----------------增加的代码
	/*
	 * 修改儿保开发资源，后台数据
	 */
	var result = updateChildCareDoctors(nodeId);
	//-----------------
	//--------------------修改的代码
	if(result){
		nodeName = nodeName.substring(0,nodeName.lastIndexOf(","));
		nodeId = nodeId.substring(0,nodeId.lastIndexOf(","));
		if("doctor" == obj){
			$("#doctorIds").val(nodeId);
			$("#show_doctor").text(nodeName);
		}
	}
	//-------------修改的代码
}
/**
 * 修改儿保开放资源,关联医生
 */
function updateChildCareDoctors(doctorId){
	var keyId = $("#keyId").val();//儿保资源id
	//var doctorId=$("#doctorIds").val();//选择关联的医生id
	//var hospitalName = $("#hospitalLname").val();//社区医院名称
	var orgId = $("#hospitalBasicInfo").val();//社区机构ID
	doctorId = doctorId.split(",")[0];
	/*
	 * 1.查看医生职称分成表是否有记录
	 * 2.修改后台,修改医生后，涉及的业务数据
	 */
	
	//查看医生职称分配表是否有记录
	/*
	 * 1.根据社区医院id,医生id,查找医生职称分成记录
	 * 2.如果不存在，提示先添加分成记录,终止后续操作
	 * 3.如果记录存在,返回分成表id
	 */
	//根据社区医院id,医生id,查找医生职称分成记录
	var hospitalPositionId = findHospitalPosition(orgId,doctorId);
	if(!hospitalPositionId) {
		alert("该医生在此社区医院中的业务中没有'医院职称分成'记录,添加记录后再操作");
		return false;
	}
	
	//修改后台,修改医生后，涉及的业务数据
	var flag;
	if($("#bt_flag").val()=="true"){
		$.ajax({
			url:ringHost+"orgBusiness.action",
			type:"POST",
			async:false,
			data:{
				"action":"updateChildCareData",
				"organChildcareOpenResources.id":keyId,
				"organChildcareOpenResources.doctorIds":doctorId,
				"organChildcareOpenResources.hospitalPositionId":hospitalPositionId
			},
			success:function(result){
				if(result.mes=="true"){
					alert("后台关联医生修改成功,请检查医生薪酬记录里,‘面向用户类型’");
					flag = true;
				}
				else{
					alert("后台关联医生修改失败");
					flag = false;
				}
			}
		});
	}else{
		flag = true;
	}
	
	return flag;
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
		type:"GET",
		async:false,
		data:{
			'action':"findHospitalPosition",
			'orgId':orgId,
			'doctorId':doctorId,
			'businessType':'1'//儿保
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
