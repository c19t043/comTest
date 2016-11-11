var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>会员编号</th>"+
			"<th style='width:10%'>宝宝姓名</th>"+
			"<th style='width:10%'>用户电话</th>"+
			"<th style='width:10%'>卡片名称</th>"+
			"<th style='width:10%'>有效开始时间</th>"+
			"<th style='width:10%'>有效结束时间</th>"+
			"<th style='width:10%'>是否可用</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button'>操作</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getMemberManageList'};
	getAllList(data);
	
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'memberManage.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.memberManageList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.memberManageList.length;i ++){
				var userInfo = (result.memberManageList[i]).userInfo;
				var babyName = userInfo==null?'':userInfo.babyName;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.memberManageList[i]).memberCode+"</td>"+
					"<td style='width:10%'>"+babyName+"</td>"+
					"<td style='width:10%'>"+(result.memberManageList[i]).userPhone+"</td>"+
					"<td style='width:10%'>"+(result.memberManageList[i]).memberType.memberName+"</td>"+
					"<td style='width:10%'>"+(result.memberManageList[i]).effectStartDate+"</td>"+
					"<td style='width:10%'>"+(result.memberManageList[i]).effectEndDate+"</td>"+
					"<td style='width:10%'>"+(result.memberManageList[i]).isEnable+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.memberManageList[i]).id+"' "+
							" memberCode='"+(result.memberManageList[i]).memberCode+"' "+
							" babyName='"+babyName+"' "+
							" phone='"+(result.memberManageList[i]).userPhone+"' "+
							" memberName='"+(result.memberManageList[i]).memberType.memberName+"' "+
							" effectStartDate='"+(result.memberManageList[i]).effectStartDate+"' "+
							" effectEndDate='"+(result.memberManageList[i]).effectEndDate+"' "+
							" isEnable='"+(result.memberManageList[i]).isEnable+"' "+
							" remark='"+(result.memberManageList[i]).remark+"' "+
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
    
	$('#memberCode').val($(obj).attr('memberCode'));
	$('#babyName').val($(obj).attr('babyName'));
	$('#phone').val($(obj).attr('phone'));
	$('#memberName').val($(obj).attr('memberName'));
	$('#effectStartDate').val($(obj).attr('effectStartDate'));
	$('#effectEndDate').val($(obj).attr('effectEndDate'));
	$('#remark').val($(obj).attr('remark'));
	$('#isEnable option').filter(function(){//是否有效
	    return $(this).text() == $(obj).attr('isEnable');
	}).get(0).selected = true;
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'memberManage.action?action=saveOrUpdateMemberManage');
	
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
	if($("#babyName").val() == ""){
		alert("没有关联用户");
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
	var baby_name = $("#baby_name").val();
	var doctor_name = $("#doctor_name").val();
	var search_status = $("#search_status").find("option:selected").text();
	var clinicDate = $("#clinicDate").val();
	var orderNum = $("#order_Num").val();
	var data = {action:'getMemberManageList',
				'orderInfoClinic.userInfo.babyName':baby_name ,
				'orderInfoClinic.orderStatus':search_status ,
				'orderInfoClinic.appointmentDate':clinicDate ,
				'orderInfoClinic.orderNum':orderNum ,
				'orderInfoClinic.doctorInfo.doctorName':doctor_name
				};
	getAllList(data);
}

