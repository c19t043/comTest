var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:5%'>类型</th>"+
			"<th style='width:8%'>推荐用户</th>"+
			"<th style='width:10%'>被推荐用户</th>"+
			"<th style='width:10%'>推荐医生姓名</th>"+
			"<th style='width:10%'>推荐医生手机</th>"+
			"<th style='width:10%'>被推荐医生姓名</th>"+
			"<th style='width:10%'>被推荐医生手机</th>"+
			"<th style='width:7%'>奖励时间</th>"+
			"<th style='width:5%'>积分数量</th>"+
			"<th style='width:5%'>金额</th>"+
			"<th style='width:5%'>优惠卷</th>"+
			"<th style='width:5%'>是否发放</th>"+
			"<th style='width:5%'>发放时机</th>"+
			//"<th style='width:10%'>"+
				//"<div onclick=add_info() class='th_button'>添加</div>"+
			//"</th>"+
		"</tr>"
	);
	var data = {action:'getRecommentAwardRecordList'};
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
			}else if(result.recommentAwardRecordList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.recommentAwardRecordList.length;i ++){
				 var recommendUser = (result.recommentAwardRecordList[i]).recommendUser;
				 var beenRecommendUser = (result.recommentAwardRecordList[i]).beenRecommendUser;
				 var recommendDoctor = (result.recommentAwardRecordList[i]).recommendDoctor;
				 var beenRecommendDoctor = (result.recommentAwardRecordList[i]).beenRecommendDoctor;
				 var coupon = (result.recommentAwardRecordList[i]).coupon;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:5%'>"+(result.recommentAwardRecordList[i]).recommendType+"</td>"+
					"<td style='width:8%'>"+(recommendUser==null?null:recommendUser.phone)+"</td>"+
					"<td style='width:10%'>"+(beenRecommendUser==null?null:beenRecommendUser.phone)+"</td>"+
					"<td style='width:10%'>"+(recommendDoctor==null?null:recommendDoctor.doctorName)+"</td>"+
					"<td style='width:10%'>"+(recommendDoctor==null?null:recommendDoctor.doctorPhone)+"</td>"+
					"<td style='width:10%'>"+(beenRecommendDoctor==null?null:beenRecommendDoctor.doctorName)+"</td>"+
					"<td style='width:10%'>"+(beenRecommendDoctor==null?null:beenRecommendDoctor.doctorPhone)+"</td>"+
					"<td style='width:7%'>"+(result.recommentAwardRecordList[i]).awardTime+"</td>"+
					"<td style='width:5%'>"+(result.recommentAwardRecordList[i]).pointsAmount+"</td>"+
					"<td style='width:5%'>"+(result.recommentAwardRecordList[i]).amount+"</td>"+
					"<td style='width:5%'>"+(coupon==null?null:coupon.couponName)+"</td>"+
					"<td style='width:5%'>"+(result.recommentAwardRecordList[i]).isGrant+"</td>"+
					"<td style='width:5%'>"+(result.recommentAwardRecordList[i]).whenToGrant+"</td>"+
//					"<td style='width:10%'>"+
//						"<div class='td_change_button' "+
//							" keyId='"+(result.recommentAwardRecordList[i]).id+"' "+
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
	var recommendType = $("#recommendType").find("option:selected").val();
	var recommendUser = $("#recommendUser").val();
	var beenRecommendUser = $("#beenRecommendUser").val();
	var awardTime = $("#awardTime").val();
	var isGrant = $("#isGrant").find("option:selected").val();
	var recommendDoctorName = $("#recommendDoctorName").val();
	var recommendDoctorPhone = $("#recommendDoctorPhone").val();
	var beenRecommendDoctorName = $("#beenRecommendDoctorName").val();
	var beenRecommendDoctorPhone = $("#beenRecommendDoctorPhone").val();
	var data = {action:'getRecommentAwardRecordList',
				'recommentAwardRecord.recommendType':recommendType ,
				'recommentAwardRecord.awardTime':awardTime ,
				'recommentAwardRecord.isGrant':isGrant ,
				'recommentAwardRecord.recommendUser.phone':recommendUser ,
				'recommentAwardRecord.beenRecommendUser.phone':beenRecommendUser,
				'recommentAwardRecord.recommendDoctor.doctorName':recommendDoctorName,
				'recommentAwardRecord.recommendDoctor.doctorPhone':recommendDoctorPhone,
				'recommentAwardRecord.beenRecommendDoctor.doctorName':beenRecommendDoctorName,
				'recommentAwardRecord.beenRecommendDoctor.doctorPhone':beenRecommendDoctorPhone
				};
	getAllList(data);
}

