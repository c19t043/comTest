//点击预约产品
function checkSession(){
	$.ajax({
		type:'post',
		url:'getSession.action',
		cache:false,
		async:false, 
		data:{action:"checkUserSession"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("用户已经登录");
				var doctorId=$.trim($("#doctorId").val());
				var productName=$.trim($("#productName").val());
				if(doctorId==""){
					doctorId=0;
				}
				$.ajax({
					type:'post',
					url:'getOrderInfo.action',
					cache:false,
					async:false, 
					data:{action:"doctorCheck",doctorId:doctorId,productName:productName},
					success:function(result){
						if(result.mes=="操作成功"){
							ale("产品名称是："+productName);
							ale("产品小图是："+result.productSmallPic);
							if(result.someProductItemsList!=null&&result.someProductItemsList.length!=0){
								for(var i=0;i<result.someProductItemsList.length;i++){
									ale("第"+(i+1)+"各项目的名称是："+result.someProductItemsList[i]);
								}
							}
							ale("订单总金额是："+result.productPrice);
							var usr=result.someUser;
							ale("家长姓名是:"+usr.parentName);
							ale("家长电话是："+usr.phone);
							ale("用户所在的省："+usr.userProvince);
							ale("用户所在的市："+usr.userCity);
							ale("用户所在的区："+usr.userArea);
							ale("用户所在的街道："+usr.userStreet);
							if(doctorId!=0){
								var doctor=result.someDoctor;
								ale("医生的姓名是："+doctor.doctorName);
								ale("医生的职称是："+doctor.doctorTitle);
							}
							if(result.userCupnList!=null&&result.userCupnList.length!=0){
								for(var i=0;i<result.userCupnList.length;i++){
									var coupon=result.userCupnList[i];
									ale("优惠券的ID是:"+coupon.id);
									ale("优惠券的名字是:"+coupon.couponName);
									ale("优惠券的金额是:"+coupon.couponAmount);
									ale("优惠券的有效期是:"+coupon.startTime+"到"+coupon.endTime);
								}
							}
							ale("用户的积分为："+usr.accountPoints);
							ale("用户的账户余额为："+usr.accountBalance);
							ale("每100积分兑换"+result.pointsProperties.value);
						}else if(result.mes=="未登录"){
							ale("用户未登录");
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

//优先选择预约服务的时间,即在没有选择预约服务的医生的情况下，选择了预约服务的时间，进行匹配能够服务的医生列表
function getServiceDoctorList(){
	var prodcutId=$.trim($("#prodcutId").val());
	var serviceDate=$.trim($("#serviceDate").val());
	var serviceTime=$.trim($("#serviceTime").val());
	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getSomeDateDoctList",prodcutId:prodcutId,serviceDate:serviceDate,serviceTime:serviceTime},
		success:function(result){
			if(result.mes=="操作成功"){
				for(var i=0;i<result.serviceDoctorList.length;i++){
					var doctor =result.serviceDoctorList[i];
					ale("医生的头像是："+doctor.doctorImage);
					ale("医生的职称是："+doctor.doctorTitle);
					ale("医生的出诊次数："+doctor.visitedTimes);
					ale("医生的服务态度星级："+doctor.seiviceStarLevel);
					ale("医生的责任星级："+doctor.dutyStarLevel);
					ale("医生的准时星级："+doctor.onTimeStarLevel);
					if(result.doctorMajorList[i]!=null){
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							ale("第"+(j+1)+"个专业方向是："+result.doctorMajorList[i][j]);
						}
					}
					if(result.doctorDistanceList[i]!=undefined){
						ale("距离是"+result.doctorDistanceList[i]);
					}
				}
			}else if(result.mes=="无医生"){
				ale("还没有医生能够在该时间段提供服务");
			}else if(result.mes=="未登录"){
				ale("您还没有登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

//优先选择预预约服务的医生,即在没有选择预约时间的情况下，直接选择预约服务的医生
function choseSomeProdcutDoctor(){
	var prodcutId=$.trim($("#choseProductId").val());
	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getSomeProdcutServiceDoctorList",prodcutId:prodcutId},
		success:function(result){
			if(result.mes=="操作成功"){
				for(var i=0;i<result.someProdcutServiceDoctorList.length;i++){
					var doctor =result.someProdcutServiceDoctorList[i];
					ale("医生的头像是："+doctor.doctorImage);
					ale("医生的职称是："+doctor.doctorTitle);
					ale("医生的出诊次数："+doctor.visitedTimes);
					ale("医生的服务态度星级："+doctor.seiviceStarLevel);
					ale("医生的责任星级："+doctor.dutyStarLevel);
					ale("医生的准时星级："+doctor.onTimeStarLevel);
					if(result.doctorMajorList[i]!=null){
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							ale("第"+(j+1)+"个专业方向是："+result.doctorMajorList[i][j]);
						}
					}
					if(result.doctorDistanceList[i]!=undefined){
						ale("距离是"+result.doctorDistanceList[i]);
					}
				}
			}else if(result.mes=="无医生"){
				ale("还没有医生提供该产品服务");
			}else if(result.mes=="未登录"){
				ale("您还没有登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

//选中了某个商品和医生之后，获取医生的服务时间列表
function getServiceDateList(){
	var prodcutId=$.trim($("#newChoseProductId").val());
	var doctorId=$.trim($("#choseDoctorId").val());
	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getSomeDoctorServiceTimeList",prodcutId:prodcutId,doctorId:doctorId},
		success:function(result){
			if(result.mes=="操作成功"){
				for(var i=0;i<result.serviceDateList.length;i++){
					ale("第"+(i+1)+"个服务的日期是:"+result.serviceDateList[i]);
					if(result.serviceTimeList[i]!=null){
						for(var j=0;j<result.serviceTimeList[i].length;j++){
							ale("第"+(j+1)+"个服务时间段是:"+result.serviceTimeList[i][j]);
						}
					}else{
						ale("该医生在该天没有服务时间");
					}
				}
			}else if(result.mes=="无服务"){
				ale("该医生没有服务安排");
			}
		},
		error: function () {
			layer();
		}
	});
}

//获取每一天的服务时间段
function getService(){
	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getService"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("明天的日期是:"+result.dateTor);
				if(result.serviceTimeInitList!=null){
					for(var i=0;i<result.serviceTimeInitList.length;i++){
						var serviceTime=result.serviceTimeInitList[i];
						ale("第"+(i+1)+"个服务时间区段的名称是："+serviceTime.name);
						ale("第"+(i+1)+"个服务时间区段的开始时间是："+serviceTime.startTime);
						ale("第"+(i+1)+"个服务时间区段的结束时间是："+serviceTime.endTime);
					}
				}else{
					ale("还没有服务时间呢");
				}
				for(var i=0;i<result.serviceDateList.length;i++){
					if(result.serviceTimeList[i]==null){
						ale("该天还不存在服务时间段");
					}else{
						for(var j=0;j<result.serviceTimeList[i].length;j++){
							ale("该天可以用的第"+(i+1)+"个时间段是："+result.serviceTimeList[i][j]);
						}
					}
				}
			}
		},
		error: function () {
			layer();
		}
	});
}

//订单确认页面，选择优惠券
function getCanbeUserCoupon(){
	$.ajax({
		type:'post',
		url:'getUserCouponInfo.action',
		cache:false,
        async:false, 
		data:{action:"canBeUse"},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.userCupnList!=null&&result.userCupnList.length!=0){
					for(var i=0;i<result.userCupnList.length;i++){
						var coupon=result.userCupnList[i];
						ale("优惠券的ID是:"+coupon.id);
						ale("优惠券的名字是:"+coupon.couponName);
						ale("优惠券的金额是:"+coupon.couponAmount);
						ale("优惠券的有效期是:"+coupon.startTime+"到"+coupon.endTime);
					}
				}else{
					ale("您没有可以使用的优惠券");
				}
			}else if(result.mes=="未登录") {
				ale("您还没有登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

//订单支付
function payProduct(){
	var doctorId=$.trim($("#serviceDoctorId").val());
	var productId=$.trim($("#serviceProductId").val());
	var serviceDate=$.trim($("#serviceDate2").val());
	var serviceTime=$.trim($("#serviceTime2").val());
	var cuponId=$.trim($("#couponId").val());
	var points=$.trim($("#points").val());
	var amount=$.trim($("#amount").val());
	var payMethod=$.trim($("#payMethod").val());
	if(doctorId==""){
		ale("请选择服务的医生之后再提交");
	}else if(serviceDate==""){
		ale("请选择服务的日期之后再提交");
	}else if(serviceTime==""){
		ale("请选择服务的时间之后再提交");
	}else{
		/*
		 * 优惠券ID、积分数、余额数在没有使用的情况下，传递的值为0
		 * payMethod 为使用的所有支付方式组合成的字符串，各支付方式之间用双冒号隔开
		 * */
		$.ajax({
			type:'post',
			url:'getOrderInfo.action',
			cache:false,
			async:false, 
			data:{
				action:"addNewOrder",
				doctorId:doctorId,
				productId:productId,
				serviceDate:serviceDate,
				serviceTime:serviceTime,
				cuponId:cuponId,
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
						url:'sendMessage.action',
						cache:false,
						async:false, 
						data:{action:"toUser",orderNum:orderNum,sendWords:"预约"},
						success:function(result){

						}
					});
					$.ajax({
						type:'post',
						url:'sendMessage.action',
						cache:false,
						async:false, 
						data:{action:"toDoctor",orderNum:orderNum,sendWords:"预约"},
						success:function(result){

						}
					});
					ale("跳转到支付成功页面");
				}else if(result.mes=="在线支付"){
					if(payMethod=="ZHIFUBAO"){
						//支付宝支付相关的
						window.location.href="turnToAliLHH.jsp?WIDseller_email=guoyushan@yishangkeji.cn&WIDout_trade_no="+orderNum+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount;
					}else if(payMethod=="WEIXINZHIFU"){
						//微信支付相关的
						paycall("kybaby", orderNum, payAmount);
					}else if(payMethod=="YINLIANZHIFU"){
						ale("正在积极的准备开发中，请耐心等候");
					}
				}else if(result.mes=="已预约"){
					ale("该时间段已经被预约了，请返回重新修改！");
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
				if(res.err_msg == "get_brand_wcpay_request:ok" ) {          
					ale("wei xin zhi fu 成功,开始订单处理");
					ale("支付成功的订单编号"+orderNum);
					//支付成功，将订单号反馈，后台根据orderNum参数更新对应的状态,然后跳转到支付成功页面
					$.ajax({
						type:'post',
						url:'orderManage.action',
						cache:false,
				        async:false, 
						data:{action:"paySuccess",orderNum:orderNum},
						success:function(result){
							if(result.mes=="操作成功"){
								ale("订单更新成功");
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
						async:false, 
						data:{action:"toUser",orderNum:orderNum,sendWords:"预约"},
						success:function(result){

						}
					});
					$.ajax({
						type:'post',
						url:'sendMessage.action',
						cache:false,
						async:false, 
						data:{action:"toDoctor",orderNum:orderNum,sendWords:"预约"},
						success:function(result){

						}
					});
					ale("跳转到支付成功页面");
				}else{
					//window.location.href="../wx/notifyUrl.jsp?"+orderNum+"&fail";
				}   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			}
	); }

function getPrePayId(orderDesc,orderNum,orderAmount) {
	var prepayId="";
	var orderId=orderNum;
	notifyUrl=getNotifyUrl();
	var signString="appid="+ appId +"&body="+orderDesc+"&mch_id=" + mchId + "&nonce_str="+nonceStr+"&notify_url="+notifyUrl+"&openid="+userOpenId+"&out_trade_no="+orderId+"&spbill_create_ip="+ip+"&total_fee="+orderAmount+"&trade_type=JSAPI&key="+APISecret;    
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