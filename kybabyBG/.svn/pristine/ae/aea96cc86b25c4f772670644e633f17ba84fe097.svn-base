var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>医生姓名</th>"+
			"<th style='width:10%'>金额类型</th>"+
			"<th style='width:10%'>金额</th>"+
			"<th style='width:10%'>事件</th>"+
			"<th style='width:10%'>操作时间</th>"+
			//"<th style='width:10%'>"+
				//"<div onclick=add_info() class='th_button'>添加</div>"+
			//"</th>"+
		"</tr>"
	);
	var data = {action:'getAllDoctorAccountList'};
	getAllList(data);
	
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'detailRecordManager.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.doctorAccountList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.doctorAccountList.length;i ++){
				 var doctorInfo = (result.doctorAccountList[i]).doctorInfo;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(doctorInfo==null?null:doctorInfo.doctorName)+"</td>"+
					"<td style='width:10%'>"+(result.doctorAccountList[i]).type+"</td>"+
					"<td style='width:10%'>"+(result.doctorAccountList[i]).amount+"</td>"+
					"<td style='width:10%'>"+(result.doctorAccountList[i]).accountDesc+"</td>"+
					"<td style='width:10%'>"+(result.doctorAccountList[i]).updateTime+"</td>"+
//					"<td style='width:10%'>"+
//						"<div class='td_change_button' "+
//							" keyId='"+(result.doctorAccountList[i]).id+"' "+
//						" onclick='read_info(this)'>修改</div>"+
//					"</td>"+
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
	var doctorName = $("#doctorName").val();
	var accountDesc = $("#accountDesc").val();
	var type = $("#type option:selected").val();
	
	var data = {action:'getAllDoctorAccountList',
				'doctorAccount.doctorInfo.doctorName':doctorName ,
				'doctorAccount.accountDesc':accountDesc ,
				'doctorAccount.type':type
				};
	getAllList(data);
}

