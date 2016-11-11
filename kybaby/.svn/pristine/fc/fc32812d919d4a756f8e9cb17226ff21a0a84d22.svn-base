/**
 * Created by windows on 2016/2/2.
 */
//点击选择支付方式 add by sujiantang 2015-10-13
var dServiceOrderId;
var totalPrice=-10;
var balanceAll=0;
var useBalanceAll=0;
var orderNum=0;
var hospitalArr=[];
$(function () {
    dServiceOrderId=decodeURIComponent(window.location.search.substring(1));
    getFdServiceOrder();
    $("#pay_type .options").click(function(){
        $(this).parent("div").addClass("selected");
        $(this).parent("div").siblings("div").removeClass("selected");
        if($(this).prev().text()=='线下支付'){
            $(".accountBalance>.accountBalance-right>p").removeClass("choose");
            useBalanceAll=0;
            $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
            $('#pay_subtotal>span').html(parseFloat(totalPrice).toFixed(2));
        }
    });
    $(".accountBalance>.accountBalance-right>p").click(function(){
        if($('#pay_type .selected .payMethod').text()=='线下支付'){
            ale('线下支付不能用余额支付');
            return false;
        }
        $(this).toggleClass("choose");
        if($(this).hasClass("choose")){
            if(totalPrice<=balanceAll){
                useBalanceAll=totalPrice;
                $('#pay_subtotal>span').html(0);
                $('#accountBalance').html(parseFloat(balanceAll-totalPrice).toFixed(2));
            }else{
                useBalanceAll=balanceAll;
                $('#accountBalance').html(0);
                $('#pay_subtotal>span').html(parseFloat(totalPrice-balanceAll).toFixed(2));
            }
        }else{
            useBalanceAll=0;
            $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
            $('#pay_subtotal>span').html(parseFloat(totalPrice).toFixed(2));
        }
    });
});
function getFdServiceOrder(){//生成订单
    $.ajax({
        type: 'post',
        async: false,
        cache: false,
        url: familyDoctorHost + 'fdServiceItemsAction.action',
        data: {
            action: "getFdServiceOrder",
            "fdServiceOrder.id":dServiceOrderId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录');
                window.location.href="login.html";
            }else if (result.mes == '成功') {
                $('#accountBalance').html(parseFloat(result.fdServiceOrder.userInfo.accountBalance,2).toFixed(2));
                $('#service_name').html(result.fdServiceOrder.fdServicePackage.packageShowName);
                if(result.fdServiceOrder.fdServicePackage.isOfflinePay!="Y"){
                    $('#offline_payment').parent().css({display:'none'});
                    $('#offline_payment').parent().prev().css({display:'none'});
                }
                if(result.fdServiceOrder.orderStatus=='已付款'){
                    $('footer').hide();
                }
                $('#money').html('&yen;'+parseFloat(result.fdServiceOrder.totalPrice,2).toFixed(2));
                $('#pay_subtotal>span').html(parseFloat(result.fdServiceOrder.totalPrice,2).toFixed(2));
                $('#service_time>p>.right').eq(0).html(result.fdServiceOrder.fdServiceTimes.effectiveTimeShow);
                $('#service_time>p>.right').eq(1).html(result.fdServiceOrder.serviceEndTime);
                balanceAll=parseFloat(result.fdServiceOrder.userInfo.accountBalance,2);
                totalPrice=parseFloat(result.fdServiceOrder.totalPrice);
                orderNum=parseFloat(result.fdServiceOrder.orderNum);
                if(result.fdServiceOrder.fdServicePackage.hospitalBasicInfo!=null){
                    hospitalArr=[result.fdServiceOrder.fdServicePackage.hospitalBasicInfo.hospitalLname,result.fdServiceOrder.fdServicePackage.hospitalBasicInfo.address]
                }
                $('#bottom_button>div').attr('onclick','goPay()');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
}
function goPay(){
    $('#bottom_button>div').removeAttr('onclick');
    var payMethod=$('#pay_type>.selected>.payMethod').text();
    if(payMethod=='线下支付'){
        useBalanceAll=0;
        payMoney('线下支付');
    }else{
        var total=$('#pay_subtotal>span').html();
        if(total==0){
            payMoney('余额支付');
        }else{
            if(payMethod=='微信支付'){
                paycall("kybaby", dServiceOrderId, orderNum, Math.round(total*100));
            }
        }
    }
}
//付款
function payMoney(method){
    $.ajax({
        type: 'post',
        async: false,
        cache: true,
        url: familyDoctorHost + 'fdServiceItemsAction.action',
        data: {
            action: "handleFdServiceOrder",
            "fdServiceOrder.id":dServiceOrderId,
            "fdServiceOrder.payMethod":method,
            "fdServiceOrder.useRemainBalance":useBalanceAll,
            "fdServiceOrder.orderStatus":'付款成功'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录');
                window.location.href="login.html";
            }
            else if (result.mes == '成功') {
                if(method=='线下支付'){
                    window.location.href="family_doctor_pay.html?"+result.fdServiceOrder.id+'&'+$('#service_name').text()+'&'+hospitalArr[0]+'&'+hospitalArr[1];
                }else{
                    window.location.href="family_doctor_pay2.html?"+result.fdServiceOrder.id+'&'+totalPrice+'&'+orderNum+'&'+method;
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
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
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
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
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
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
            //ale(errorThrown);
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
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
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
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

function paycall(orderDesc, orderId, orderNum, orderAmount) {
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
        onBridgeReady(orderDesc, orderId, orderNum, orderAmount);
    }
}

function onBridgeReady(orderDesc, orderId, orderNum, orderAmount){
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
                payMoney('微信支付');
                //window.location.href="family_doctor_pay2.html?"+dServiceOrderId+'&'+totalPrice+'&'+orderNum+'&微信支付';
            }else{
                ale('支付失败');
                window.history.go(-1);
                //window.location.href="../wx/notifyUrl.jsp?"+orderNum+"&fail";
            }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    ); }

function getPrePayId(orderDesc,orderNum,orderAmount) {
    var prepayId="";
    var orderNumCopy=orderNum;
    notifyUrl=getNotifyUrl();
    var signString="appid="+ appId +"&body="+orderDesc+"&mch_id=" + mchId + "&nonce_str="+nonceStr+"&notify_url="+notifyUrl+"&openid="+userOpenId+"&out_trade_no="+orderNumCopy+"&spbill_create_ip="+ip+"&total_fee="+orderAmount+"&trade_type=JSAPI&key="+APISecret;
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
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
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