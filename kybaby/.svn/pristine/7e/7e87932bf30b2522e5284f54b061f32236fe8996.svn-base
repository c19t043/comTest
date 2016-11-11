/**
 * Created by vinny on 2015/10/19.
 */

$(function(){
	$.ajax({
		type:'post',
		url:hostMain+'accountManage.action',
		cache:false,
		async:false,
		data:{action:"available"},
		success:function(result){
			//ale(result.toString());
			for(var i = 0; i < result.availCouponList.length; i++){
				var quan = result.availCouponList;
				var x = '';
				if(i==0){
					x = 'color01';
				}else if(i==1){
					x = 'color02';
				}else if(i==2){
					x = 'color03';
				}else if(i==3){
					x = 'color04';
				}else if(i%4==0){
					x = 'color01';
				}else if(i%4==1){
					x = 'color02';
				}else if(i%4==2){
					x = 'color03';
				}else if(i%4==3){
					x = 'color04';
				}
				$("#container").append(
					"<div class='coupons_list "+x+"'>"+
					"<div class='coupons_top'>"+
					"<p class='ky_tit'>康优宝贝</p>"+
					"<div class='coupons_left'>"+
					"<p>&yen;<span class='coupons_sum'>"+quan[i].couponAmount+"</span></p>"+
					"</div>"+
					"<div class='coupons_right'>"+
					"<p>"+quan[i].couponName+"</p>"+
					"<p class='small'>消费就送红包</p>"+
					"</div>"+
					"</div>"+
					"<div class='coupons_bottom'>"+
					"<p class='time'>使用期限：<span>"+quan[i].startTime+"—"+quan[i].endTime+"</span></p>"+
					"<p class='take' atid="+result.activityIds[i]+" zjid='"+quan[i].id+"'onclick='lqyhq(this)'>点击领取&#10148;</p>"+
					"</div>"+
					"</div>");
			}

		}
	});
});
function lqyhq(obj){
	$.ajax({
		type:'post',
		url:hostMain+'accountManage.action',
		cache:false,
		async:false,
		data:{
			action:"takeCoupon",
			couponId:$(obj).attr('zjid'),
			activityId:$(obj).attr('atid')
		},
		success:function(result){
			if(result.mes == '成功'){
				ale('领取成功');
        setTimeout(function(){
            var isSession=sessionStorage.getItem('session');
            if(isSession=='yes'){
                window.location.href="coupons.html?1";
                return false;
            }else{
                window.location.href="coupons.html?1";
            }
        },2500);
				
			}else if(result.mes == '不可用'){
				ale("您已经领取过该优惠券");
				//window.location.href='coupons.html?1';
			}

		},
		error: function () {
		}
	});
}