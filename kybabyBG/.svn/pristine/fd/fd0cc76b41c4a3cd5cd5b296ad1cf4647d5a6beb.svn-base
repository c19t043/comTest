$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:15%'>序列</th>"+
			"<th style='width:15%'>奖励方式</th>"+
			"<th style='width:15%'>奖励时机</th>"+
			"<th style='width:15%'>奖励方向</th>"+
			"<th style='width:15%'>推荐类型</th>"+
			"<th style='width:25%'><div class='th_button'onclick='add_info()'>添加</div></th>"+
		"</tr>"	
	);
	show_tableinfo();
	change_way();
	$('#show_rewordWay').change(function(){
		change_way();
	});
	$.ajax({
		'url':'recommendRuleHandle.action',
		'type':'POST',
		'data':{
			'action':'getUsefulCoupon'
		},
		success:function(result){
			$('#show_yhq').empty();
			for(var i=0;i < result.allUsefulCoupon.length;i++){
				$('#show_yhq').append(
					"<option zjid='"+(result.allUsefulCoupon[i])[0]+"'>"+(result.allUsefulCoupon[i])[1]+"</option>"
				);
			}
		}
	});
});
function change_way(){
	if($('#show_rewordWay').val() == '现金'){
		$('#show_mn,#show_moneyOrNum').show();
		$('#show_yhq,#show_yhq_').hide();
		$('#show_mn').text('金额：');
	}else if($('#show_rewordWay').val() == '积分'){
		
		$('#show_mn,#show_moneyOrNum').show();
		$('#show_yhq,#show_yhq_').hide();
		$('#show_mn').text('数量：');	
	}else if($('#show_rewordWay').val() == '优惠券'){
		$('#show_mn,#show_moneyOrNum').hide();
		$('#show_yhq,#show_yhq_').show();	
	}
}
//读取信息
function read_info(obj){

    
    
    var x = $('#show_yhq option').filter(function(){
    	return $(this).attr('zjid') == $(obj).attr('data_coupon') ;
    }).get(0);
    if(x != undefined){
    	x.selected = true;
    }
    
    $('#show_rewordWay').attr('zjid',$(obj).attr('data_id'));
    $('#show_rewordWay option').filter(function(){
    	return $(this).text() == $(obj).attr('data_type');
    }).get(0).selected = true;
    $('#show_moneyOrNum').val($(obj).attr('data_moneyOrNum'));

    $('#show_rewordTime option').filter(function(){
    	return $(this).text() == $(obj).attr('data_time');
    }).get(0).selected = true;    

    $('#show_tj option').filter(function(){
    	return $(this).text() == $(obj).attr('data_tj');
    }).get(0).selected = true;  
    
    $('#show_status option').filter(function(){
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0).selected = true;  
    change_way();
	
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
}
function add_info(){
	$('#bottom_div input').val('');
  $('#bottom_div').slideDown(500);
  $('#button_add_click').show();
  $('#button_update_click').hide();
}
function contral_info(type){
	var awardValue = '';
	if($('#show_rewordWay').val() == '现金'){
		if($('#show_moneyOrNum').val().trim() == '' || $('#show_moneyOrNum').val().trim() < 0 || isNaN($('#show_moneyOrNum').val().trim())){
			alert('金额必须为非0正数');
			return false;
		}
		awardValue = $('#show_moneyOrNum').val().trim();
	}else if($('#show_rewordWay').val() == '积分'){
		if($('#show_moneyOrNum').val().trim() == '' || $('#show_moneyOrNum').val().trim() != parseInt($('#show_moneyOrNum').val().trim()) || $('#show_moneyOrNum').val().trim() < 0 || isNaN($('#show_moneyOrNum').val().trim())){
			alert('积分必须为非0整数');
			return false;
		}	
		awardValue = $('#show_moneyOrNum').val().trim();
	}else{
		awardValue = $('#show_yhq option').filter(function(){
			return $(this).get(0).selected;
		}).attr('zjid');
	}


	
	$.ajax({
		'url':'recommendRuleHandle.action',
		'type':'POST',
		'data':{
			'action':type,
			'updateId':$('#show_rewordWay').attr('zjid'),
			'awardMethod':$('#show_rewordWay').val(),
			'awardValue':awardValue,
			'whenToAward':$('#show_rewordTime').val(),
			'ruleName':$('#show_tj').val(),
			'Status':$('#show_status').val()
		},
		success:function(result){
			if(result.mes == '成功'){
				if(type=='add'){
					alert('添加成功');
				}else if(type =='update'){
					alert('修改成功');
				}
				$('#bottom_div').slideUp(500);
				show_tableinfo();
			}else{
				alert(result.mes);
			}
		}
	});
	
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'recommendRuleHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allRecommendRule == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allRecommendRule.length;i ++){	
				 var rewordWay = '';
				 var data_moneyOrNum = '';	 
				 var a = result.allRecommendRule[i].amount;
				 var b = result.allRecommendRule[i].coupon;
				 var c = result.allRecommendRule[i].points;
				 
				 if(a != null){
				 	rewordWay = '现金';
				 	data_moneyOrNum = result.allRecommendRule[i].amount;
				 }else if(b != null){
				 	rewordWay = '优惠券';
				 }else if(c != null){
				 	rewordWay = '积分';
				 	data_moneyOrNum = result.allRecommendRule[i].points;
				 }
				 			 
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:15%'>"+(i+1)+"</td>"+
					"<td style='width:15%'>"+rewordWay+"</td>"+
					"<td style='width:15%'>"+result.allRecommendRule[i].rewardTime+"</td>"+
					"<td style='width:15%'>"+result.allRecommendRule[i].recommendDirection+"</td>"+
					"<td style='width:15%'>"+result.allRecommendRule[i].ruleName+"</td>"+
					"<td style='width:25%'>"+
						"<div class='td_change_button' "+
							" data_id='"+result.allRecommendRule[i].id+"' "+
							" data_type='"+rewordWay+"' "+
							" data_moneyOrNum='"+data_moneyOrNum+"' "+
							" data_time='"+result.allRecommendRule[i].rewardTime+"' "+
							" data_tj='"+result.allRecommendRule[i].ruleName+"' "+
							" data_status='"+result.allRecommendRule[i].ruleStatus+"' "+
							" data_coupon='"+result.allRecommendRule[i].coupon+"' "+
							
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}