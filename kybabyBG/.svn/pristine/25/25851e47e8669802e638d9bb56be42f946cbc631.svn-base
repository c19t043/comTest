$(function(){

});

function show_tableinfo(){
	if($.trim($('#ope_starttime').val()) == '' || $.trim($('#ope_endtime').val()) == ''){
		alert('请选择时间');
		return false;
	}else if(($('#ope_starttime').val()).replace('-','').replace('-','') > ($('#ope_endtime').val()).replace('-','').replace('-','')){
		alert('请选择正确时间');
		return false;	
	}else{
		$.ajax({
			'url':'operatingData.action',
			'type':'POST',
			'data':{
				'action':'getData',
				'startDate':$('#ope_starttime').val()+" 00:00:00",
				'endDate':$('#ope_endtime').val()+" 23:59:59",
			},
			success:function(result){
				$('#order_all').text(result.totalOrder);
				$('#user_reg').text(result.totalUser);
				$('#active_user').text(result.totalUser);
				$('#active_user_').text(result.activeRateOfUser);
				$('#all_money').html("&yen;"+result.totalTrade);
				$('#doctor_reg').text(result.totolDoctor);
				$('#active_doctor').text(result.totolDoctor);
				$('#active_doctor_').text(result.activeRateOfDoctor);
			}
		});
	}

}
function get_active_doctor(){
	if($.trim($('#doctor_active_time').val()) == ''){
		alert('请选择时间');
		return false;
	}
	$.ajax({
		'url':'operatingData.action',
		'type':'POST',
		'data':{
			'action':'getServiceTimeData',
			'someDay':$('#doctor_active_time').val()
		},
		success:function(result){
			if(result.numOfServiceTimeDoctor == null){
				alert('无数据');
				return false;
			}
			for(var i=0;i < result.numOfServiceTimeDoctor.length;i++){
				$('#doctor_active_num td').eq(i).text((result.numOfServiceTimeDoctor[i])[1]);
			}
			for(var i=result.numOfServiceTimeDoctor.length;i < $('#doctor_active_num td').length;i++){
				$('#doctor_active_num td').eq(i).text(0);
			}
			
		}
	});
}