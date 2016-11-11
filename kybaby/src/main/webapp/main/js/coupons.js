/**
 * Created by vinny on 2015/10/12.
 */
var arrUrl;
$(function(){
	$(".header-right").hide();
	arrUrl=decodeURIComponent(window.location.search).substring(1);
	if(arrUrl == 1){
		$(".header-right").show();
	}
})

function getCouponSum(youhui,obj){
	var cousum;
	var couponId = youhui;
	cousum = obj;
	if(arrUrl == 1){
	}else{
        var isSession=sessionStorage.getItem('session');
        if(isSession=='yes'){
            window.location.href="yjh_orderconfirm.html?"+arrUrl+"&couponValue="+cousum+"&couponId="+couponId+"";
        }else{
            window.location.href="orderconfirm.html?"+arrUrl+"&couponValue="+cousum+"&couponId="+couponId+"";
        }
	}

}

$(function(){
	$.ajax({
		type:'post',
		url:hostMain+'accountManage.action',
		cache:false,
		async:false,
		data:{action:"coupon"},
		success:function(result){
			//ale(result.toString());
			//ale(chuli);
			for(var i = 0; i < result.couponList.length; i++){
				var chuli = result.couponList[i].toString().split(",");
				var endtime = chuli[4].replace("-","").replace("-","");
				var today = result.time.replace("-","").replace("-","");
				//ale(endtime);
				if(chuli[0]=='N' && parseInt(endtime)>=parseInt(today)){
					$("#container").append(
						"<div class='coupons_list' onclick='getCouponSum("+chuli[5]+","+chuli[2]+")'>"+
						"<div class='coupons_top'>"+
						"<p class='ky_tit'>康优宝贝</p>"+
						"<div class='coupons_left'>"+
						"<p>&yen;<span class='coupons_sum' id='coupons_sum'>"+chuli[2]+"</span></p>"+
						"</div>"+
						"<div class='coupons_right'>"+
						"<p>"+chuli[1]+"</p>"+
						"<p class='small'>您身边的儿童健康管理专家</p>"+
						"</div>"+
						"</div>"+
						"<div class='coupons_bottom'>"+
						"<p class='time'>使用期限：<span>"+chuli[3]+"—"+chuli[4]+"</span></p>"+
						"<p class='use_state'>未使用</p>"+
						"</div>"+
						"</div>")
				}else if(chuli[0]=='Y'){
					$("#container").append(
						//"<div class='coupons_list used' onclick='getCouponSum("+chuli[5]+","+chuli[2]+")'>"+
						"<div class='coupons_list used'  onclick=\"ale('已使用')\">"+
						"<div class='coupons_top'>"+
						"<p class='ky_tit'>康优宝贝</p>"+
						"<div class='coupons_left'>"+
						"<p>&yen;<span class='coupons_sum' id='coupons_sum'>"+chuli[2]+"</span></p>"+
						"</div>"+
						"<div class='coupons_right'>"+
						"<p>"+chuli[1]+"</p>"+
						"<p class='small'>您身边的儿童健康管理专家</p>"+
						"</div>"+
						"</div>"+
						"<div class='coupons_bottom'>"+
						"<p class='time'>使用期限：<span>"+chuli[3]+"—"+chuli[4]+"</span></p>"+
						"<p class='use_state'>已使用</p>"+
						"</div>"+
						"</div>")
				}else if(parseInt(endtime)<parseInt(today)){
					//ale("123");
					$("#container").append(
						//"<div class='coupons_list passed' onclick='getCouponSum("+chuli[5]+","+chuli[2]+")'>"+
						"<div class='coupons_list passed' onclick=\"ale('已过期')\">"+
						"<div class='coupons_top'>"+
						"<p class='ky_tit'>康优宝贝</p>"+
						"<div class='coupons_left'>"+
						"<p>&yen;<span class='coupons_sum' id='coupons_sum'>"+chuli[2]+"</span></p>"+
						"</div>"+
						"<div class='coupons_right'>"+
						"<p>"+chuli[1]+"</p>"+
						"<p class='small'>您身边的儿童健康管理专家</p>"+
						"</div>"+
						"</div>"+
						"<div class='coupons_bottom'>"+
						"<p class='time'>使用期限：<span>"+chuli[3]+"—"+chuli[4]+"</span></p>"+
						"<p class='use_state'>已过期</p>"+
						"</div>"+
						"</div>")
				}

			}
		},
		error: function () {
		}
	});
})