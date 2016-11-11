$(function(){
	getOrderList();
	$('#select_cli li').click(function(){
		var txt = $(this).text();
		$('#select_cli li').removeClass('blue');
		$(this).addClass('blue');
		if(txt == '全部'){
			$('.order_divbox').show();
		}else if(txt == '已取消'){
			$('.order_divbox').hide();
			$('.top_zt').filter(function(){
				var a =	$(this).text().indexOf('取消');

				if(a == -1){
					return false;
				}else{
					return true;
				}
			}).parent().show();				
		}else if(txt == '已完成'){
            $('.order_divbox').hide();
            $('.top_zt').filter(function(){
                var a =	$(this).text().indexOf('已完成');
                var b=	$(this).text().indexOf('已评价');

                if(a == -1&&b==-1){
                    return false;
                }else{
                    return true;
                }
            }).parent().show();
        }else{
			$('.order_divbox').hide();
			$('.top_zt').filter(function(){
				return $(this).text() == txt;
			}).parent().show();		
		}

	});
});
//获取所有订单列表
function getOrderList(){
	$('#info_box_all').empty();
	$.ajax({
		type:'post',
		url:host+'getOrderInfo.action',
		cache:false,
    async:false, 
		data:{action:"orderList"},
		success:function(result){
			if(result.someOrderList == null){
				return false;
			}
			for(var i=0;i<result.someOrderList.length;i++){
				var cancel;
				var str;
				var status;
				var moneyStatus = 'block';
				var cancelShow = 'none';
				var detail_status = 'none';	//是否显示结果
				if(result.someOrderList[i][2] == '已接单'){
					cancel = '取消';
					str = '取消';
					status = 'visible';
				}else if(result.someOrderList[i][2] == '未付款'){
					cancel = '支付';
					str = '立即支付';
					status = 'visible';		
					cancelShow = 'block';		
				}else if(result.someOrderList[i][2] == '已完成'){
					cancel = '确认';
					str = '确认';
					status = 'visible';
					detail_status = 'block';				
				}else if(result.someOrderList[i][2].indexOf('取消') != -1){
					cancel = 'N';
					str = 'N';
					status = 'hidden';	
					moneyStatus = 'none';					
				}else if(result.someOrderList[i][2] == '已确认'){
					cancel = '评价';
					str = '评价';
					status = 'visible';	
					detail_status = 'block';			
				}else if(result.someOrderList[i][2] == '已评价'){
					cancel = 'N';
					str = 'N';
					status = 'hidden';
					detail_status = 'block';					
				}
				else if(result.someOrderList[i][2] == '已签到'){
					cancel = '确认';
					str = '确认';
					status = 'hidden';
				}
				var orderAmount=Math.round((result.someOrderList[i][6] - result.someOrderList[i][3] -result.someOrderList[i][4]-result.someOrderList[i][12])*100)/100;
				if(orderAmount<0){
					orderAmount=0;
				}
				var isShowcan = 'visible';
				var colorObj = 'black';
				if(result.someOrderList[i][8]==null){
					isShowcan = 'hidden';
					colorObj = 'red';
				}
				$('#info_box_all').append(
					"<div class='order_divbox' onclick=\"if('"+isShowcan+"' == 'visible'){window.location.href='order_details.html?"+result.someOrderList[i][0]+"'} \">"+
		        "<span class='top_sj'>"+(result.someOrderList[i][1])+"</span>"+
		        "<span class='top_zt'>"+(result.someOrderList[i][2])+"</span>"+
		        "<p class='gray_2'></p>"+
		        "<div class='info'>"+
		            "<div class='img'>"+
									"<img src='"+hostBG+"/images/product/"+(result.someOrderList[i][7])+"' />"+
		            "</div>"+
		            "<div class='text_box'>"+
		                "<span>服务产品</span>&nbsp;<span  class='font1'>"+(result.someOrderList[i][5])+"</span><br/>"+
		                "<span style='color:"+colorObj+"'  class='font1'>"+(result.someOrderList[i][8] == null?"正在匹配合适的医生...":result.someOrderList[i][8])+"</span><br/>"+
		                "<span style='visibility:"+isShowcan+"' class='font1'>职称：</span><span  class='font1'>"+(result.someOrderList[i][9]==null?"":result.someOrderList[i][9])+"</span><br/>"+
		            "</div>"+
		        "</div>"+
		        "<p class='gray_2'></p>"+
		        "<div class='top_je ' style='display:"+moneyStatus+"'>"+
		            "<span>实付金额：</span>"+
		            "<span class='font2'>&yen;"+Math.round((orderAmount)*100)/100+"</span>"+
		           // "<span data_ordernum='"+result.someOrderList[i][result.someOrderList[i].length-1]+"' data_name='"+(result.someOrderList[i][5])+"' data_id='"+(result.someOrderList[i][0])+"' data_money='"+(result.someOrderList[i][6])+"' class='button' style='visibility:"+status+"' onclick=\"javascript:cancel("+result.someOrderList[i][0]+",'"+cancel+"',event,this)\">"+str+"</span>"+
		            "<span data_ordernum='"+result.someOrderList[i][result.someOrderList[i].length-3]+"' data_name='"+(result.someOrderList[i][5])+"' data_id='"+(result.someOrderList[i][0])+"' data_money='"+orderAmount+"' class='button' style='visibility:"+status+"' onclick=\"javascript:cancel("+result.someOrderList[i][0]+",'"+cancel+"',event,this)\">"+str+"</span>"+
		            "<span data_ordernum='"+result.someOrderList[i][result.someOrderList[i].length-3]+"' data_name='"+(result.someOrderList[i][5])+"' data_id='"+(result.someOrderList[i][0])+"' data_money='"+orderAmount+"' class='button' style='visibility:"+status+";display:"+cancelShow+"' onclick=\"javascript:cancel("+result.someOrderList[i][0]+",'取消',event,this)\">取消</span>"+
		            //添加一个按钮，用于用户查询该订单项目的结果
		            "<span class='button' style='display:"+detail_status+"' onclick='go_result_info("+result.someOrderList[i][result.someOrderList[i].length-3]+",event)' >查询结果</span>"+
		            
		            //"<input type='button' onclick=getOrderResult("+result.someOrderList[i][result.someOrderList[i].length-3]+") value='查询结果' />"+
		        "</div>"+
		        "<p class='gray_1'></p>"+
	        "</div>"
				);
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

	if(status=='取消'){
		var r=confirm("确定要取消订单吗？");
		if (r==false){
		  return false;
		}
	}
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
					getOrderList();
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
			'data_money':$(obj).attr('data_money'),
			'data_ordernum':$(obj).attr('data_ordernum')
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
	
	/*ale('支付方式：'+pay_way);
	ale('支付id：'+$(obj).attr('data_id'));
	ale('支付钱：'+$(obj).attr('data_money'));
	ale('支付的订单编号是：'+$(obj).attr('data_ordernum'));*/
	
	var payMethod=pay_way;
	var payAmount=$(obj).attr('data_money');
	var orderNum=$(obj).attr('data_ordernum');
	//以上为订单支付必须包括的信息
	if(payMethod=="微信"){
		//ale("您选择了微信支付");
		paycall("kybaby", orderNum, Math.round(payAmount*100));
	}else if(payMethod=="支付宝"){
		//ale("您选择了支付宝支付");
		window.location.href="turnToAlipay.html?WIDseller_email=guoyushan@yishangkeji.cn&WIDout_trade_no="+orderNum+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount;
	}else if(payMethod=="银联"){
		ale("正在积极的开发中，请耐心等待");
	}
	
}
function go_result_info(str,event){
	event.stopPropagation();
	window.location.replace("result_info.html?"+str);
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
	
	//ale("signStringforPay=="+signStringforPay);
	
	signStringforPay=MD5(signStringforPay);
	
	//ale("signStringforPay=="+signStringforPay);
	
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
	
	//ale("signString=="+signString);//测试 
	
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


