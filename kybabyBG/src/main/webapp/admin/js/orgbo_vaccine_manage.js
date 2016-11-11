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
			"<th style='width:10%'>普通窗口开放资源</th>"+
			"<th style='width:10%'>绿色通道开放资源</th>"+
			"<th style='width:10%'>是否可用</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button' onclick='add_info()'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getOrganInoculationOpenResourcesList'};
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
			}else if(result.organInoculationOpenResourcesList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.organInoculationOpenResourcesList.length;i ++){
				var hospitalBasicInfo = (result.organInoculationOpenResourcesList[i]).hospitalBasicInfo;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+hospitalBasicInfo.hospitalLname+"</td>"+
					"<td style='width:10%'>"+(result.organInoculationOpenResourcesList[i]).openDate+"</td>"+
					"<td style='width:10%'>"+(result.organInoculationOpenResourcesList[i]).openStartTime+"</td>"+
					"<td style='width:10%'>"+(result.organInoculationOpenResourcesList[i]).openEndTime+"</td>"+
					"<td style='width:5%'>"+(result.organInoculationOpenResourcesList[i]).restStartTime+"</td>"+
					"<td style='width:5%'>"+(result.organInoculationOpenResourcesList[i]).restEndTime+"</td>"+
					"<td style='width:5%'>"+(result.organInoculationOpenResourcesList[i]).timeDivisionValue+"</td>"+
					"<td style='width:10%'>"+(result.organInoculationOpenResourcesList[i]).generalSurplusNum+"</td>"+
					"<td style='width:10%'>"+(result.organInoculationOpenResourcesList[i]).greenChannelSurplusNum+"</td>"+
					"<td style='width:10%'>"+(result.organInoculationOpenResourcesList[i]).isAvailable+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.organInoculationOpenResourcesList[i]).id+"' "+
							" hospitalId='"+hospitalBasicInfo.id+"' "+
							" openStartTime='"+(result.organInoculationOpenResourcesList[i]).openStartTime+"' "+
							" openDate='"+(result.organInoculationOpenResourcesList[i]).openDate+"' "+
							" openEndTime='"+(result.organInoculationOpenResourcesList[i]).openEndTime+"' "+
							" restStartTime='"+(result.organInoculationOpenResourcesList[i]).restStartTime+"' "+
							" restEndTime='"+(result.organInoculationOpenResourcesList[i]).restEndTime+"' "+
							" timeDivisionValue='"+(result.organInoculationOpenResourcesList[i]).timeDivisionValue+"' "+
							" generalSurplusNum='"+(result.organInoculationOpenResourcesList[i]).generalSurplusNum+"' "+
							" greenChannelSurplusNum='"+(result.organInoculationOpenResourcesList[i]).greenChannelSurplusNum+"' "+
							" generalNum='"+(result.organInoculationOpenResourcesList[i]).generalNum+"' "+
							" greenChannelNum='"+(result.organInoculationOpenResourcesList[i]).greenChannelNum+"' "+
							" isAvailable='"+(result.organInoculationOpenResourcesList[i]).isAvailable+"' "+
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
    
    $('#openDate').val($(obj).attr('openDate'));
	$('#openStartTime').val($(obj).attr('openStartTime'));
	$('#openEndTime').val($(obj).attr('openEndTime'));
	$('#restStartTime').val($(obj).attr('restStartTime'));
	$('#restEndTime').val($(obj).attr('restEndTime'));
	$('#timeDivisionValue').val($(obj).attr('timeDivisionValue'));
	$('#generalNum').val($(obj).attr('generalNum'));
	$('#greenChannelNum').val($(obj).attr('greenChannelNum'));
	$('#hospitalBasicInfo option').filter(function(){
		return $(this).val() == $(obj).attr('hospitalId');
	}).get(0).selected = true;
	$('#isAvailable option').filter(function(){
		return $(this).text() == $(obj).attr('isAvailable');
	}).get(0).selected = true;
	
	$('#node_Tbody').empty();
	$('#node_table_title').show();
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:true, 
		data:{
			action:"getOrganInoculationOpenResourcesDetailList",
			"organInoculationOpenResources.id":$(obj).attr('keyId')
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.organInoculationOpenResourcesDetailList == null){
				alert('没有数据！');
				return false;			
			}
			$('#node_Tbody').empty();
			for(var i=0;i < result.organInoculationOpenResourcesDetailList.length;i ++){
				var hospitalBasicInfo = (result.organInoculationOpenResourcesDetailList[i]).hospitalBasicInfo;
				$('#node_Tbody').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:20%'>"+(i+1)+"</td>"+
					"<td style='width:20%'>"+(result.organInoculationOpenResourcesDetailList[i]).openStartTime+"</td>"+
					"<td style='width:20%'>"+(result.organInoculationOpenResourcesDetailList[i]).openEndTime+"</td>"+
					"<td style='width:20%'>"+(result.organInoculationOpenResourcesDetailList[i]).generalSurplusNum+"</td>"+
					"<td style='width:20%'>"+(result.organInoculationOpenResourcesDetailList[i]).greenChannelSurplusNum+"</td>"+
				"</tr>"
				);
			}
		}
	});
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=saveOrUpdateOrganInoculationOpenResources');
	
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
	$('#node_table_title').hide();
	$('#node_Tbody').empty();
}
function search_click(){
	var open_Date = $("#open_Date").val();
	var hospitalLname = $("#hospitalLname").val();
	var data = {action:'getOrganInoculationOpenResourcesList',
				'organInoculationOpenResources.hospitalBasicInfo.hospitalLname':hospitalLname ,
				'organInoculationOpenResources.openDate':open_Date ,
				};
	getAllList(data);
}

