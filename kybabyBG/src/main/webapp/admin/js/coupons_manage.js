var ringHost="../admin/";
$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>优惠劵名称</th>"+
			"<th style='width:5%'>卡卷类型</th>"+
			"<th style='width:8%'>有效日期</th>"+
			"<th style='width:10%'>抵扣金额</th>"+
			"<th style='width:10%'>最低消费金额</th>"+
			"<th style='width:10%'>折扣比例</th>"+
			"<th style='width:10%'>最高折扣金额</th>"+
			"<th style='width:10%'>获取方式</th>"+
			"<th style='width:12%'>说明</th>"+
			"<th style='width:10%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	var data = {action:'getAllCouponList'};
	getAllList(data);
});

function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'operationStrategy.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.couponList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.couponList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.couponList[i]).couponName+"</td>"+
					"<td style='width:5%'>"+(result.couponList[i]).couponType+"</td>"+
					"<td style='width:8%'>"+(result.couponList[i]).startTime+"到"+(result.couponList[i]).endTime+"</td>"+
					"<td style='width:10%'>"+(result.couponList[i]).couponAmount+"</td>"+
					"<td style='width:10%'>"+(result.couponList[i]).minConsumption+"</td>"+
					"<td style='width:10%'>"+(result.couponList[i]).discountRate+"</td>"+
					"<td style='width:10%'>"+(result.couponList[i]).maxAmount+"</td>"+
					"<td style='width:10%'>"+(result.couponList[i]).getWay+"</td>"+
					"<td style='width:12%'>"+(result.couponList[i]).remark+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.couponList[i]).id+"' "+
							" couponName='"+(result.couponList[i]).couponName+"' "+
							" couponType='"+(result.couponList[i]).couponType+"' "+
							" startTime='"+(result.couponList[i]).startTime+"' "+
							" endTime='"+(result.couponList[i]).endTime+"' "+
							" couponAmount='"+(result.couponList[i]).couponAmount+"' "+
							" minConsumption='"+(result.couponList[i]).minConsumption+"' "+
							" discountRate='"+(result.couponList[i]).discountRate+"' "+
							" maxAmount='"+(result.couponList[i]).maxAmount+"' "+
							" getWay='"+(result.couponList[i]).getWay+"' "+
							" remark='"+(result.couponList[i]).remark+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}
function add_info(){
	$('#bottom_div_con input').val('');
  $('#bottom_div').slideDown(500);
  $('#button_add_click').show();
  $('#button_update_click').hide();
  $('#show_event').removeAttr('disabled').removeAttr('readonly');
}

//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    
    $('#keyId').val($(obj).attr('keyId'));
    $('#couponName').val($(obj).attr('couponName'));
    $('#couponType option').filter(function(){
    	return $(this).text() == $(obj).attr('couponType');
    }).get(0).selected = true;
    $('#startTime').val($(obj).attr('startTime'));
    $('#endTime').val($(obj).attr('endTime'));
    $('#couponAmount').val($(obj).attr('couponAmount'));
    $('#discountRate').val($(obj).attr('discountRate'));
    
    $('#getWay option').filter(function(){
    	return $(this).text() == $(obj).attr('getWay');
    }).get(0).selected = true;
    
    $('#maxAmount').val($(obj).attr('maxAmount'));
    $('#minConsumption').val($(obj).attr('minConsumption'));
    $('#remark').val($(obj).attr('remark'));
}
//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'operationStrategy.action?action=saveOrUpdateCoupon');
	
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
	var input_h = $('#show_endTime').val();
	var input_i = $('#show_yhqstartTime').val();
	if(parseInt(input_h.replace(/-/g,'')) > parseInt(input_i.replace(/-/g,''))){
		alert('活动日期输入错误');
		return false;
	}else if(parseInt(input_j.replace(/-/g,'')) > parseInt(input_k.replace(/-/g,''))){
		alert('优惠券日期输入错误');
		return false;	
	}
	if(confirm("确认？")){
		$('#data_form').submit();
	}
}
