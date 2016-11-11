var url_str = window.location.search.substring(1);
$(function(){
	getOrderDetail();
});
function getOrderDetail(){
	$.ajax({
		type:'post',
		url:host+'getOrderInfo.action',
		cache:false,
    async:false, 
		data:{
			action:"orderDetail",
			orderId:url_str
		},
		success:function(result){

			for(var i=0;i < result.someProductItemShowNamesList.length;i++){
				var showName=result.someProductItemShowNamesList[i];
				if(showName.indexOf('DUMMYITEM')==-1){
					$('#order_object').append(
					"<span class='bq'>"+showName+"</span>"
					);	
				}			
			}
				var cancel;
				var str;
				var status;
				var moneyStatus = 'block';
				if(result.orderDetail[0][2] == '已接单'){
					cancel = '取消';
					str = '取消'
					status = 'visible';
				}else if(result.orderDetail[0][2] == '未付款'){
					cancel = '支付';
					str = '立即支付'
					status = 'visible';				
				}else if(result.orderDetail[0][2] == '已完成'){
					cancel = '确认';
					str = '确认'
					status = 'visible';						
				}else if(result.orderDetail[0][2].indexOf('取消') != -1){
					cancel = 'N';
					str = 'N'
					status = 'hidden';	
					moneyStatus = 'none';					
				}else if(result.orderDetail[0][2] == '已确认'){
					cancel = '评价';
					str = '评价'
					status = 'visible';						
				}else if(result.orderDetail[0][2] == '已评价'){
					cancel = 'N';
					str = 'N'
					status = 'hidden';
				}else if(result.orderDetail[0][2] == '已签到'){
					cancel = 'N';
					str = 'N'
					status = 'hidden';
				}
			$('#order_status_plus').text(str).attr({
				'data_contral':cancel,
				'data_id':url_str,
				'data_money':result.orderDetail[0][3],
				'onclick':"javascript:cancel("+url_str+",'"+cancel+"',event,this)"
			}).css('visibility',status);
			$('#order_time').text(result.orderDetail[0][0]);
			$('#order_num').text(result.orderDetail[0][1]);
			$('#order_status').text(result.orderDetail[0][2]);
			$('#order_userName').text(result.orderDetail[0][7]);
			$('#order_phone').text(result.orderDetail[0][8]);
			$('#order_address_1').text(result.orderDetail[0][9]+" "+result.orderDetail[0][10]+" "+result.orderDetail[0][11]);
			$('#order_address_2').text(result.orderDetail[0][12]);
			$('#order_img').attr("src",hostBG+"/images/product/"+result.orderDetail[0][15]);
			$('#order_productName').text(result.orderDetail[0][14]);
			$('#order_doctor').text(result.orderDetail[0][16]);
			$('#order_level').text("职称："+result.orderDetail[0][17]);
			$('#order_moneyAll').html("&yen;"+result.orderDetail[0][3]);
			if(result.orderDetail[0][18] == 0 || result.orderDetail[0][18] == undefined){
				$('#order_yhq').parent().hide();
			}else{
				$('#order_yhq').html("&yen;-"+result.orderDetail[0][18]);
			}
			if(result.orderDetail[0][4] == 0){
				$('#order_jf').parent().hide();
			}else{
				$('#order_jf').html("&yen;-"+result.orderDetail[0][4]);
			}
			if(result.orderDetail[0][5] == 0){
				$('#order_ye').parent().hide();
			}else{
				$('#order_ye').html("&yen;-"+result.orderDetail[0][5]);
			}
			if(result.orderDetail[0][6].toString().indexOf('支付宝支付') != -1){
				$('#order_payWay').text("支付宝支付");
				if(result.orderDetail[0].length == 18){
					var x = Math.round((result.orderDetail[0][3]-result.orderDetail[0][4]-result.orderDetail[0][5])*100)/100;
					$('#order_otherWayMoney').html("&yen;-"+x);
					$('#order_lastMoney').html("&yen;"+x);
				}else if(result.orderDetail[0].length == 19){
					var x = Math.round((result.orderDetail[0][3]-result.orderDetail[0][18]-result.orderDetail[0][4]-result.orderDetail[0][5])*100)/100;
					$('#order_otherWayMoney').html("&yen;-"+x);
					$('#order_lastMoney').html("&yen;"+x);
				}

			}else if(result.orderDetail[0][6].toString().indexOf('微信支付') != -1){
				$('#order_payWay').text("微信支付");
				if(result.orderDetail[0].length == 18){
					var x = Math.round((result.orderDetail[0][3]-result.orderDetail[0][4]-result.orderDetail[0][5])*100)/100;
					$('#order_otherWayMoney').html("&yen;-"+x);
					$('#order_lastMoney').html("&yen;"+x);
				}else if(result.orderDetail[0].length == 19){
					var x = Math.round((result.orderDetail[0][3]-result.orderDetail[0][18]-result.orderDetail[0][4]-result.orderDetail[0][5])*100)/100;
					$('#order_otherWayMoney').html("&yen;-"+x);
					$('#order_lastMoney').html("&yen;"+x);
				}

			}else{
				$('#order_lastMoney').html("&yen;0");
			}			
		},
		error: function () {
			layer();
		}
	});

}
function cancel(id,status,event,obj){
	event.stopPropagation(); 
	if(status == 'N'){
		return false;
	}else if(status == '取消' || status == '确认'){
		$.ajax({
			type:'post',
			url:host+'orderManage.action',
			cache:false,
		  async:false, 
			data:{
				action:"change",
				orderStatus:status,
				orderId:id
			},
			beforeSend:function(){
				$(obj).removeAttr('onclick');
			},
			success:function(result){
				if(result.mes == '成功'){
					ale('操作成功！');
          setTimeout(function(){
            window.location.href='myAppointment.html';
          },2500);					
				}
			},
			error: function () {
				layer();
			}
		});	
	}else if(status == '支付'){
		$('#zPayMethod').show();
		$('#btnOrderPay').attr({
			'data_id':$(obj).attr('data_id'),
			'data_money':$(obj).attr('data_money')
		});
	}else if(status == '评价'){
				window.location.href = "evaluation.html?"+$(obj).attr('data_id');
	}

}
function quxiao(){
	$('#zPayMethod').hide();
}

function goPay(obj){
	var pay_way = '';
	for(var i=0;i < $('.checkbo').length;i++){
		if($('.checkbo').eq(i).get(0).checked == true){
			pay_way = $('.checkbo').eq(i).attr('mark');
		}
	}
	if(pay_way == ''){
		ale('请选择支付方式');
		return false;
	}
	
	//updated by zhong at 2015-10-31
	//var payAmount=$(obj).attr('data_money');
	var payAmount=$('#order_lastMoney').text().substring(1);
	
	var orderNum=$('#order_num').text();
		
	
	if(pay_way=='微信'){
		paycall("kybaby", orderNum, Math.round(payAmount*100));
	}else if(pay_way=='支付宝'){
		window.location.href="turnToAlipay.html?WIDseller_email=guoyushan@yishangkeji.cn&WIDout_trade_no="+orderNum+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount;
	}else if(pay_way=='银联'){
		ale('正在积极的开发中，请耐心等待');
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
					window.location.href="paysuccess.html?"+orderAmount;
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


