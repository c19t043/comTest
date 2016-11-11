var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>分类名称</th>"+
			"<th style='width:10%'>所属机构</th>"+
			"<th style='width:10%'>是否可用</th>"+
			"<th style='width:10%'>备注</th>"+
			"<th style='width:10%'>"+
				"<div onclick='add_info()' class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getChildcareProjectTypeList'};
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
				$('#ascriptionOrgan').append(
						"<option data_address="+(result.hospitalBasicInfoList[i]).hospitalLname+
						"("+(result.hospitalBasicInfoList[i]).address+")"+
						" value='"+(result.hospitalBasicInfoList[i]).id+"'>"+
						(result.hospitalBasicInfoList[i]).hospitalLname+"</option>"
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
			}else if(result.childcareProjectTypeList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.childcareProjectTypeList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectTypeList[i]).typeName+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectTypeList[i]).ascriptionOrgan.hospitalLname+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectTypeList[i]).isEnable+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectTypeList[i]).remark+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.childcareProjectTypeList[i]).id+"' "+
							" typeName='"+(result.childcareProjectTypeList[i]).typeName+"' "+
							" hospitalLname='"+(result.childcareProjectTypeList[i]).ascriptionOrgan.hospitalLname+"' "+
							" hospitalId='"+(result.childcareProjectTypeList[i]).ascriptionOrgan.id+"' "+
							" isEnable='"+(result.childcareProjectTypeList[i]).isEnable+"' "+
							" remark='"+(result.childcareProjectTypeList[i]).remark+"' "+
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
    
	$('#typeName').val($(obj).attr('typeName'));
	$('#remark').html($(obj).attr('remark'));
	$('#ascriptionOrgan option').each(function(){
		if($(this).val() == $(obj).attr('hospitalId')){
			$(this).attr("selected","selected");
		}
	});
	$('#isEnable option').each(function(){
		if($(this).val() == $(obj).attr('isEnable')){
			$(this).attr("selected","selected");
		}
	});
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=saveOrUpdateChildcareProjectType');
	
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
	$('#bottom_div_con textarea').html('');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
}
function search_click(){
	var typeName = $("#ruleName_con").val();
	//var workDate_con = $("#workDate_con").val();
	//var search_status = $("#search_status").find("option:selected").text();
	var data = {action:'getChildcareProjectTypeList',
				'childcareProjectType.typeName':typeName
				};
	getAllList(data);
}

