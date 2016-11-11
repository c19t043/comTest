var productId;
var arrUrlFirst;
var theDoctorId;
var date="";
var time="";
var coupon="";
var couponId=0;
//var couponId="";
//update by HooLee
//2015年10月16日10:47:04
//优惠券如果没有使用，其值为0

var productPrice;
var accountPoints;
var pointsProperties;
var accountBalance;
var doctorId;
$(function(){
	checkSession();
	//var cousum = window.location.search.substring(1);
	$("#coupons_link").click(function(){
		window.location.href='coupons.html'+window.location.search;
	});
	$('#backoff').click(function(){
		window.location.replace("productdetails.html?prodName="+$('#productBasicInfo .tit').text());
	});
});

//点击预约产品
function checkSession(){
	//arrUrlFirst = decodeURIComponent(window.location.search).substring(1).split("::");
	var productName="";	
	var arrUrlBase=decodeURIComponent(window.location.search).substring(1).split("&");
	for(var i=0;i<arrUrlBase.length;i++){
		if(arrUrlBase[i].split("=")[0]=="prodName"){
			productName=arrUrlBase[i].split("=")[1];
		} else if(arrUrlBase[i].split("=")[0]=="doctorId"){
			doctorId=arrUrlBase[i].split("=")[1];
		} else if(arrUrlBase[i].split("=")[0]=="couponValue"){
			coupon=arrUrlBase[i].split("=")[1];
		} else if(arrUrlBase[i].split("=")[0]=="couponId"){
			couponId=arrUrlBase[i].split("=")[1];
		}		
		else if(arrUrlBase[i].split("=")[0]=="date"){
			date=arrUrlBase[i].split("=")[1];
		}else if(arrUrlBase[i].split("=")[0]=="time"){
			time=arrUrlBase[i].split("=")[1];
		}
	}
	
	$.ajax({
		type:'post',
		url:host+'getSession.action',
		cache:false,
		async:false, 
		data:{action:"checkUserSession"},
		success:function(result){
			if(result.mes=="操作成功"){
				//ale("用户已经登录");	
				
				if(doctorId==""||doctorId==null){
					doctorId=0;
				}
				theDoctorId=doctorId;
				$.ajax({
					type:'post',
					url:host+'getOrderInfo.action',
					cache:false,
					async:false, 
					data:{action:"doctorCheck",doctorId:doctorId,productName:productName},
					success:function(result){
						if(result.mes=="操作成功"){
							productId=result.productId;
							$("#productBasicInfo").html("<p class='tit'>"+productName+"</p>");
							if(date!=""){
								$("#chooseTime").html(date+" "+time);
							}
							
							
							$("#productImage").append("<img src='"+hostBG+"images/product/"+result.productSmallPic+"' />");
							
							if(result.someProductItemsList!=null&&result.someProductItemsList.length!=0){
								for(var i=0;i<result.someProductItemsList.length;i++){
									var showName=result.someProductItemShowNamesList[i];
									if(showName.indexOf('DUMMYITEM')==-1){
										$("#productBasicInfo").append("<p class='button_tab'>"+showName+"</p>");
									}
								}
							}
							
							$("#productTotalPrice").html("<span>&yen;"+result.productPrice+"</span>");

							var usr=result.someUser;
							$("#parentInfo").html(
                                "<div class='clearfix'><p class='text-left float-left'>"+usr.parentName+"</p> <p class='text-right float-right'>"+usr.phone+"</p></div>"+
                                "<p class='new_by_yjl'>"+usr.userProvince+usr.userCity+usr.userArea+"</p>"+
                                "<p class='new_by_yjl2'>"+usr.userStreet+"</p>"
                            );
							
							if(doctorId!=0){
								var doctor=result.someDoctor;
								//ale("医生的职称是："+doctor.doctorTitle);
								$("#selectDoctorName").html(doctor.doctorName+"<span>("+doctor.doctorTitle+")</span>");
							}
							/*if(result.userCupnList!=null&&result.userCupnList.length!=0){
								for(var i=0;i<result.userCupnList.length;i++){
									var coupon=result.userCupnList[i];
									ale("优惠券的ID是:"+coupon.id);
									ale("优惠券的名字是:"+coupon.couponName);
									ale("优惠券的金额是:"+coupon.couponAmount);
									ale("优惠券的有效期是:"+coupon.startTime+"到"+coupon.endTime);
								}
							}*/
							productPrice = result.productPrice;
							accountPoints = usr.accountPoints;
							pointsProperties = result.pointsProperties.value;
							accountBalance=usr.accountBalance;
							payAmountCaculation(productPrice,accountPoints,pointsProperties,accountBalance);
							
							//ale("用户的积分为："+usr.accountPoints);
							//ale("用户的账户余额为："+usr.accountBalance);
							//ale("每100积分兑换"+result.pointsProperties.value);

							
							//$("#point_sum").text(result.pointsProperties.value);
							
							/*if(parseFloat((usr.accountPoints / 100).toString().split(".")) * result.pointsProperties.value < result.productPrice){
								$("#real_sum").text(parseFloat(result.productPrice - (usr.accountPoints / 100).toString().split(".")) * result.pointsProperties.value+"");
							}*/
							//ale($("#real_sum").text() + "11");
							/*if(parseFloat($("#real_sum").text()) - parseFloat(usr.accountBalance) >= 0){
								$("#still_sum").text($("#real_sum").text() - usr.accountBalance);
							}else{
								$("#still_sum").text("0");
							}*/
						}else if(result.mes=="未登录"){
							ale("用户未登录");
              setTimeout(function(){
                window.location.href="login.html";
              },2500);							
						}
					},
					error: function () {
						layer();
					}
				});
			}else if(result.mes=="未登录"){
				ale("用户未登录");
        setTimeout(function(){
          window.location.href="login.html";
        },2500);
				
			}
		},
		error: function () {
			layer();
		}
	});
}

function payAmountCaculation(productPrice,accountPoints,pointsProperties,accountBalance){
	if(coupon!=undefined&&coupon!=null&&coupon!=""){
		$("#coupon").text("已优惠"+coupon+"元");
		if(coupon>productPrice){
			$(".ch1").animate({left: "0"});
			$("#real_sum").text("0");
			$(".ch2").animate({left: "0"});
			$(".use_sum").hide();
			$(".ch1").attr('flag','off');
			$(".ch2").attr('flag','off');
		} else{										
			if($(".ch1").attr('flag')=="on"){
				if(parseFloat((accountPoints/ 100).toString().split(".")[0]) * pointsProperties < (productPrice-coupon)){
					$("#canUsePoints").text((accountPoints/ 100).toString().split(".")[0]*100);
					$("#point_sum").text(((accountPoints/ 100).toString().split(".")[0]) * pointsProperties);
					
					//updated by zhong at 2015-10-31
					//$("#real_sum").text(parseFloat(productPrice -coupon- (accountPoints / 100).toString().split(".")) * pointsProperties+"");
					$("#real_sum").text(getSecondBits(parseFloat(getSecondBits(productPrice) -getSecondBits(coupon)- getSecondBits((accountPoints / 100).toString().split(".")[0] * pointsProperties+""))));
				}
				else{
					$("#canUsePoints").text(Math.ceil((productPrice -coupon)/ pointsProperties)*100);
					$("#point_sum").text(Math.ceil((productPrice -coupon)/ pointsProperties)*pointsProperties);
					$("#real_sum").text("0");
				}
			} else{
				$("#real_sum").text(productPrice-coupon);
				if(parseFloat((accountPoints/ 100).toString().split(".")[0]) * pointsProperties < (productPrice-coupon)){
					$("#canUsePoints").text((accountPoints/ 100).toString().split(".")[0]*100);
					$("#point_sum").text(((accountPoints/ 100).toString().split(".")[0]) * pointsProperties);
				}
				else{
					$("#canUsePoints").text(Math.ceil((productPrice -coupon)/ pointsProperties)*100);
					$("#point_sum").text(Math.ceil((productPrice -coupon)/ pointsProperties)*pointsProperties);
				}
			}
		}
	} else{
		if($(".ch1").attr('flag')=="on"){
			if(parseFloat((accountPoints/ 100).toString().split(".")[0]) * pointsProperties < productPrice){
				$("#canUsePoints").text((accountPoints/ 100).toString().split(".")[0]*100);
				$("#point_sum").text(((accountPoints/ 100).toString().split(".")[0]) * pointsProperties);
				
				//updated by zhong at 2015-10-31
				//$("#real_sum").text(parseFloat(productPrice-(accountPoints / 100).toString().split(".")) * pointsProperties+"");
				$("#real_sum").text(parseFloat(productPrice-(accountPoints / 100).toString().split(".")[0] * pointsProperties+""));
			}
			else{
				$("#canUsePoints").text(Math.ceil(productPrice/ pointsProperties)*100);
				$("#point_sum").text(Math.ceil(productPrice/ pointsProperties)*pointsProperties);
				$("#real_sum").text("0");
			}
		} else{
			$("#real_sum").text(productPrice);
			if(parseFloat((accountPoints/ 100).toString().split(".")[0]) * pointsProperties < productPrice){
				$("#canUsePoints").text((accountPoints/ 100).toString().split(".")[0]*100);
				$("#point_sum").text(((accountPoints/ 100).toString().split(".")[0]) * pointsProperties);
			}
			else{
				$("#canUsePoints").text(Math.ceil(productPrice/ pointsProperties)*100);
				$("#point_sum").text(Math.ceil(productPrice/ pointsProperties)*pointsProperties);
			}
		}
	}
	
	cashAmountCaculate(accountBalance);

}

function cashAmountCaculate(accountBalance){
	$("#canuse_sum").text(accountBalance);
	
	if($(".ch2").attr('flag')=="on"){
		if(parseFloat($("#real_sum").text()) - parseFloat(accountBalance) >= 0){
			$("#use_sum").text(accountBalance);
			$("#still_sum").text(getSecondBits(getSecondBits($("#real_sum").text()) - getSecondBits(accountBalance)));
			isCli();
		}else{
			$("#use_sum").text(parseFloat($("#real_sum").text()));
			$("#still_sum").text("0");
			isCli();
		}
	} else{
		$("#still_sum").text(getSecondBits($("#real_sum").text()));
		isCli();
	}
}

function getSecondBits(price){
	return Math.round(price*100)/100;
}

function chooseTimeClick(){
	/*if(theDoctorId==0){
		theDoctorId="";
	}
	window.location.href="service_time.html?"+productId+"&"+theDoctorId;*/
	if(window.location.search.indexOf("prodId=")<0){
		window.location.href="service_time.html"+window.location.search+"&prodId="+productId;
	} else {
		window.location.href="service_time.html"+window.location.search;
	}
	
}

function chooseDoctorClick(){
	/*window.location.href="chooseservicedoctor.html";
	var urlBase="chooseservicedoctor.html?"+productId;
	if($("#chooseTime").text()=="选择时间"){
		window.location.href=urlBase;
	} else{
		var chooseTime=$("#chooseTime").text().split(" ");
		window.location.href=urlBase+"&"+chooseTime[0]+"&"+chooseTime[1];
	}*/
	if(window.location.search.indexOf("prodId=")<0){
		window.location.href="chooseservicedoctor.html"+window.location.search+"&prodId="+productId;
	} else {
		window.location.href="chooseservicedoctor.html"+window.location.search;
	}
}

//点击选择支付方式 add by sujiantang 2015-10-13
var pay_method;
function clickPayMethod(obj){
	pay_method = $(obj).attr('payMethod');
}

//订单支付--------------------------------------------------------------------

var xyz = 0;
function timeInterval(){
	xyz++;
	if(xyz != 5){
		setTimeout(function(){
			timeInterval();
		},1000);	
	}else{
		xyz = 0;
	}
}




function payProduct(){
	
	if(document.getElementById('yl_pay').checked){
		ale('正在积极的开发中，请选择其他方式支付！');
		return false;
	}
	//var doctorId=$.trim($("#serviceDoctorId").val());
	//var productId=$.trim($("#serviceProductId").val());
	//var serviceDate=$.trim($("#serviceDate2").val());
	//var serviceTime=$.trim($("#serviceTime2").val());
	//var cuponId=$.trim($("#couponId").val());
	//var payMethod=$.trim($("#payMethod").val());
	if($('#still_sum').text() != 0){
		var x = false;
		for(var i=0;i < $('.pay_way input').length;i++){
			if($('.pay_way input').eq(i).get(0).checked){
				x = true;
			}
		}
		if(x == false){
			ale('请选择支付方式！');
			return false;
		}	
	}


	
	var points=$.trim($("#canUsePoints").text());
	
	//var amount=$.trim($("#use_sum").val());span标签的取值使用text()属性	
	var amount=$.trim($("#use_sum").text());
	
	var serviceDate=date;
	var serviceTime=time.split("-")[0].split(":")[0];
	
	if($(".ch1").attr('flag')=="off"){
		points=0;
	}
	if($(".ch2").attr('flag')=="off"){
		amount=0;
	}
	
	var payMethod="";
	if($("#coupon").text()!="选择优惠券"){
		payMethod="优惠券::";
	}//优惠券抵现
	
	if($("#canUsePoints").text!=0&&$(".ch1").attr('flag')=="on"){
		payMethod+="积分::";
	}

	if($("#canuse_sum").text()!=0&&$(".ch2").attr('flag')=="on"){
		payMethod+="余额::";
	}
	
	if(pay_method!=undefined){
		if(payMethod==""){
			payMethod=pay_method;
		}else{
			payMethod+=pay_method;
		}
	}else{
		payMethod=payMethod.substring(0, payMethod.length-2);
	}

//	if(doctorId==""){
//		ale("请选择服务的医生之后再提交");
//	}else
	if(serviceDate==""){
		ale("请选择服务的日期之后再提交");
	}else if(serviceTime==""){
		ale("请选择服务的时间之后再提交");
	}else{
		/*
		 * 优惠券ID、积分数、余额数在没有使用的情况下，传递的值为0
		 * payMethod 为使用的所有支付方式组合成的字符串，各支付方式之间用双冒号隔开
		 * */
        $('.login_button').removeAttr('onclick');

        if(xyz != 0){
			return false;
		}else{
			timeInterval();	
		}
		$.ajax({
			type:'post',
			url:host+'getOrderInfo.action',
			cache:false,
			async:false, 
			data:{
				action:"addNewOrder",
				doctorId:doctorId,
				productId:productId,
				serviceDate:serviceDate,
				serviceTime:serviceTime,
				cuponId:couponId,
				points:points,
				amount:amount,
				payMethod:payMethod
			},
			success:function(result){
				var orderNum=result.orderNum;
				var payAmount=result.payAmount;
				if(result.mes=="余额支付"){
					//余额支付成功之后,根据订单编号向医生和用户发送提示信息短信，
					//医生提示的内容包括预约的订单号、预约的时间和预约的用户和手机号
					//用户提示的内容包括预约的订单号、预约的时间和预约的医生
					$.ajax({
						type:'post',
						url:host+'sendMessage.action',
						cache:false,
						async:false, 
						data:{action:"toUser",orderNum:orderNum,sendWords:"预约"},
						success:function(result){
							
						}
					});
					$.ajax({
						type:'post',
						url:host+'sendMessage.action',
						cache:false,
						async:false, 
						data:{action:"toDoctor",orderNum:orderNum,sendWords:"预约"},
						success:function(result){
							
						}
					});
					//ale("跳转到支付成功页面");
					window.location.href="paysuccess.html?"+amount*100;
				}else if(result.mes=="在线支付"){
					if(pay_method=="支付宝支付"){
						//支付宝支付相关的
						window.location.href="turnToAlipay.html?WIDseller_email=guoyushan@yishangkeji.cn&WIDout_trade_no="+orderNum+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount;
					}else if(pay_method=="微信支付"){
						//微信支付相关的
						paycall("kybaby", orderNum, Math.round(payAmount*100));
					}else if(pay_method=="YINLIANZHIFU"){
						ale("正在积极的准备开发中，请耐心等候");
					}
				}else if(result.mes=="已预约"){
					//ale("该时间段已经被预约了，请返回重新修改！");
				}
			},
			error: function () {
				layer();
			}
		});
	}
}

/*支付功能开始*/
var ip="";
var nonceStr;
var appId;
var mchId;
var APISecret;
var userOpenId="";
var notifyUrl="";

function getUserId() {
	$.ajax({
		url:'../wx/getOpenId.action',
		cache:false,
		async:false, 
		data:{action:"openId"},
		success:function(result) {
			userOpenId=result.openId;
		},
		error: function () {
			layer();
		}
	});
	return userOpenId;
}
//获取openid结束 	2015-7-18 17:06:56-----------------------------------------------------------------------------------------------------

function getTimeStamp(){
	var timestamp=new Date().getTime();
	var timestampstring = timestamp.toString();//一定要转换字符串
	return timestampstring;
}
//获取精确到毫秒的时间字符串-----------------------------------------------------------------------------------------------------------------

function getNonceStr(){
	var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = $chars.length;
	var noceStr = "";
	for (var i = 0; i < 32; i++) {
		noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return noceStr;
}
//获取32位随机字符串-------------------------------------------------------------------------------------------------------------------------

function getRemoteIp() {
	var remoteIp="";
	$.ajax({    
		url:'../wx/configManage.action', // 跳转到 action   
		data:{action:"getRemoteIp"}, 
		cache:false,
		async:false,           
		success:function(result) {    
			remoteIp=result.ip;
			//alert(remoteIp);
		},    
		error:function(XMLHttpRequest, textStatus, errorThrown) {    
			ale(XMLHttpRequest.status);
			ale(XMLHttpRequest.readyState);
			ale(textStatus);
			layer();
		}    
	});
	return remoteIp; 
}
//获取到ip地址-----------------------------------------------------------------------------------------------------------------------------

function getAppId() {
	var appId="";
	$.ajax({    
		url:'../wx/configManage.action', // 跳转到 action   
		data:{action:"getProperty", propertyName:"corpId"}, 
		cache:false,
		async:false,           
		success:function(result) {   
			appId=result.propertyValue;
		},    
		error:function(XMLHttpRequest, textStatus, errorThrown) {    
			ale(XMLHttpRequest.status);
			ale(XMLHttpRequest.readyState);
			ale(textStatus);
			layer();
		}    
	});
	return appId; 
}
//获取到appid---------------------------------------------------------------------------------------------------------------------------------

function getMchId() {
	var mchId="";
	$.ajax({
		url:'../wx/configManage.action', // 跳转到 action   
		data:{action:"getProperty", propertyName:"mchId"}, 
		cache:false,
		async:false,           
		success:function(result) {    
			mchId=result.propertyValue;
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			ale(errorThrown);
			ale(XMLHttpRequest.status);
			ale(XMLHttpRequest.readyState);
			ale(textStatus);
			layer();
		}    
	});
	return mchId; 
}
//获取到微信支付的商户号-------------------------------------------------------------------------------------------------------------------------------

function getAPISecret() {
	var APISecret="";
	$.ajax({    
		url:'../wx/configManage.action', // 跳转到 action   
		data:{action:"getProperty", propertyName:"APISecret"}, 
		cache:false,
		async:false,           
		success:function(result) {    
			APISecret=result.propertyValue;
		},    
		error:function(XMLHttpRequest, textStatus, errorThrown) {    
			ale(XMLHttpRequest.status);
			ale(XMLHttpRequest.readyState);
			ale(textStatus);
			layer();
		}    
	});
	return APISecret; 
}
//获取到微信支付应用密匙---------------------------------------------------------------------------------------------------------------------------------

//下面开始获取notifyUrl------------------------------------------------------------------------------------------------------------------------------
function getNotifyUrl(){
	$.ajax({
		type:'post',
		url:'../wx/configManage.action',
		data:{action:"getProperty", propertyName:"notify_url"},
		cache:false,
		async:false,
		success:function(result){
			notifyUrl=result.propertyValue;
		},
		error: function () {
			layer();
		}
	});
	return notifyUrl;
}   
//调用微信支付-----------------------------------------------------------------------------------------------------------------------------------------

function paycall(orderDesc, orderNum, orderAmount) {
	userOpenId=getUserId();
	ip=getRemoteIp();
	nonceStr=getNonceStr();
	appId = getAppId();
	mchId = getMchId();
	APISecret = getAPISecret();
	if (typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		}else if (document.attachEvent){
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	}else{
		onBridgeReady(orderDesc, orderNum, orderAmount);
	} 
}

function onBridgeReady(orderDesc, orderNum, orderAmount){
	var prepId = getPrePayId(orderDesc,orderNum,orderAmount);
	var timeStr = getTimeStamp(); 
	var nonStr = getNonceStr();
	var signStringforPay="appId="+appId+"&nonceStr="+nonStr+"&package=prepay_id="+prepId+"&signType=MD5&timeStamp="+timeStr+"&key="+APISecret;
	signStringforPay=MD5(signStringforPay); 
	signStringforPay=signStringforPay.toUpperCase();
    appId=appId+"";
    timeStr=timeStr+"";
    nonStr=nonStr+"";
    signStringforPay=signStringforPay+"";
    WeixinJSBridge.invoke(
			'getBrandWCPayRequest', {
				"appId":appId,     //公众号名称，由商户传入     
				"timeStamp":timeStr,         //时间戳，自1970年以来的秒数    
				"nonceStr":nonStr, //随机串     
				"package":"prepay_id=" + prepId,   
				"signType":"MD5",         //微信签名方式:     
				"paySign":signStringforPay  //微信签名 
			},
			function(res){
                if(res.err_msg == "get_brand_wcpay_request:ok" || res.err_msg == "get_brand_wcpay_request：ok") {
					//ale("wei xin zhi fu 成功,开始订单处理");
					//ale("支付成功的订单编号"+orderNum);
					//支付成功，将订单号反馈，后台根据orderNum参数更新对应的状态,然后跳转到支付成功页面
					$.ajax({
						type:'post',
						url:'orderManage.action',
						cache:false,
				        async:true,
						data:{action:"paySuccess",orderNum:orderNum},
						success:function(result){
                            window.location.href="paysuccess.html?"+orderAmount;
                            if(result.mes=="操作成功"){
								//ale("订单更新成功");
							}
						},
						error: function () {
							layer();
						}
					});
					
					$.ajax({
						type:'post',
						url:'sendMessage.action',
						cache:false,
						async:true,
						data:{action:"toUser",orderNum:orderNum,sendWords:"预约"},
						success:function(result){

						}
					});
					$.ajax({
						type:'post',
						url:'sendMessage.action',
						cache:false,
						async:true,
						data:{action:"toDoctor",orderNum:orderNum,sendWords:"预约"},
						success:function(result){

						}
					});
				}else{
					window.location.href="payfail.html";
					//window.location.href="../wx/notifyUrl.jsp?"+orderNum+"&fail";
				}   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			}
	); }

function getPrePayId(orderDesc,orderNum,orderAmount) {
	var prepayId="";
	var orderId=orderNum;
	notifyUrl=getNotifyUrl();
	var signString="appid="+ appId +"&body="+orderDesc+"&mch_id=" + mchId + "&nonce_str="+nonceStr+"&notify_url="+notifyUrl+"&openid="+userOpenId+"&out_trade_no="+orderId+"&spbill_create_ip="+ip+"&total_fee="+orderAmount+"&trade_type=JSAPI&key="+APISecret;    
	
	//ale("signString=="+signString);//测试 
	
	signString=MD5(signString);
	signString=signString.toUpperCase();
	$.ajax({    
		url:'../wx/getPrepayId.action', // 跳转到 action 
		data:{action:"getPrepayId",mchId:mchId,tradeNo:orderNum,remoteIp:ip,nonceStr:nonceStr,signStr:signString,userOpenId:userOpenId,body:orderDesc,totalFee:orderAmount},   
		cache:false, 
		async:false,          
		success:function(result) { 
			resultXml=result.result;
			prepayId= resultXml.substring(resultXml.indexOf("<prepay_id>")+20,resultXml.indexOf("</prepay_id>")-3);
		},    
		error:function(XMLHttpRequest, textStatus, errorThrown) {    
			ale(XMLHttpRequest.status);
			ale(XMLHttpRequest.readyState);
			ale(textStatus);
			layer();
		}    
	});  
	return prepayId;         
}

//支付功能结束----------------------------------------------------------------------------
function isCli(){
	var obj = $('input').filter(function(){
		return $(this).attr('name') == 'pay';
	});
	if($('#still_sum').text() != '0'){
		obj.removeAttr('disabled');
	}else{
		obj.get(0).checked=false;
		obj.get(1).checked=false;
		obj.get(2).checked=false;
		obj.attr('disabled','disabled');

	}
}