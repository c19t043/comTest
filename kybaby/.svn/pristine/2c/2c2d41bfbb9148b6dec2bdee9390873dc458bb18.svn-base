/**
 * Created by windows on 2016/2/2.
 */
//点击选择支付方式 add by sujiantang 2015-10-13
//获取数据
var productId=decodeURIComponent(window.location.search.substring(1).split('&&')[0]);
var cha1=0;
var balanceAll=0;
var orderId;
var orderNum;
var doctorId;
var dateTime;
var timeStart;
var discountMoney=0;
var totalPrice=0;
var useBalanceAll=0;
var useDiscountMoney=0;
var isPlus='';
var userId='';
//function goback(){
//    var date=parseInt($('#dateTime').text().trim().split('-')[2]);
//    window.location.href='appointmentPatient.html?'+doctorId+'&&'+date;
//}

$(function(){
    //if(orderDate!='undefined'){
    //    $('#modify').show();
    //}
    $.ajax({
        type:'post',
        url:memberHost+'memberManage.action',
        cache:false,
        async:false,
        data:{action:"getProduct",
            "product.id":productId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes == '成功'){
                var list=result.product;
                totalPrice=parseFloat(list.totalPrice,2);
                balanceAll=parseFloat(result.userInfo.accountBalance,2);
                $('#payTotal').html(parseFloat(totalPrice,2).toFixed(2));
                $('.description>img').prop('src',hostBG+"images/product/"+result.product.smallPicture);
                $('#accountBalance').html(result.userInfo.accountBalance);
                $('#visit').html(list.totalPrice);
                $('#doctorMessage').html(list.name);
                orderId='';
                orderNum=result.orderNum;
//                    $('#dayTime').html(list.appointmentDate);
                $('#footer>p').attr('onclick','payProduct()');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });
});
//选择支付方式
$('.payWay tr').click(function(){
    $('.payWay tr').removeClass('choosePayWay');
    $('.icon').attr('src','images/images_family_doctor/select.png');
    $(this).addClass('choosePayWay');
    $(this).find('.icon').attr('src','images/images_family_doctor/true.png');
});
//是否选择福利
$('.preferential').click(function () {
    $('#discountMoney').toggleClass('preferential_payment');
    if($('#discountMoney').prop('class')=='preferential_payment'){
        $(this).find('img').prop('src','images/images_family_doctor/true.png');
        if($('#accountBalance').prop('class')=='balance_payment'){
            var cha=totalPrice-balanceAll+discountMoney;
            if(cha<=0){
                $('#payTotal').html(0);
                if(discountMoney+totalPrice>=0){
                    $('#accountBalance').html(parseFloat(0-cha).toFixed(2));
                    useBalanceAll=discountMoney+totalPrice;
                }else{
                    $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
                    useBalanceAll=0;
                }
                useDiscountMoney=0-discountMoney;
            }else{
                $('#payTotal').html(parseFloat(cha).toFixed(2));
                $('#accountBalance').html(0);
                useBalanceAll=balanceAll;
                useDiscountMoney=0-discountMoney;
            }
        }else{
            var cha1=totalPrice+discountMoney;
            if(cha1<=0){
                $('#payTotal').html(0);
                $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
                useBalanceAll=0;
                useDiscountMoney=0-discountMoney;
            }else{
                $('#payTotal').html(parseFloat(cha1).toFixed(2));
                $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
                useBalanceAll=0;
                useDiscountMoney=0-discountMoney;
            }
        }
    }
    else{
        if($('#accountBalance').prop('class')=='balance_payment'){
            var cha2=totalPrice-balanceAll;
            if(cha2<=0){
                $('#payTotal').html(0);
                $('#accountBalance').html(parseFloat(0-cha2).toFixed(2));
                useBalanceAll=totalPrice;
                useDiscountMoney=0;
            }else{
                $('#payTotal').html(parseFloat(cha2).toFixed(2));
                $('#accountBalance').html(0);
                useBalanceAll=balanceAll;
                useDiscountMoney=0;
            }
        }else{
            $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
            useBalanceAll=0;
            useDiscountMoney=discountMoney;
            $('#payTotal').html(parseFloat(totalPrice).toFixed(2));
        }
        $(this).find('img').prop('src','images/images_family_doctor/select.png');
    }
    //console.log(useBalanceAll);
    //console.log(useDiscountMoney);
});
//是否选择余额
$('.accountBalance').click(function () {
    $('#accountBalance').toggleClass('balance_payment');
    if($('#accountBalance').prop('class')=='balance_payment'){
        $(this).find('img').prop('src','images/images_family_doctor/true.png');
        if($('#discountMoney').prop('class')=='preferential_payment'){
            var cha=totalPrice-balanceAll+discountMoney;
            if(cha<=0){
                $('#payTotal').html(0);
                if(totalPrice+discountMoney<=0){
                    useBalanceAll=0;
                    $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
                }else{
                    useBalanceAll=totalPrice+discountMoney;
                    $('#accountBalance').html(parseFloat(0-cha).toFixed(2));
                }
                useDiscountMoney=0-discountMoney;
            }else{
                $('#payTotal').html(parseFloat(cha).toFixed(2));
                $('#accountBalance').html(0);
                useBalanceAll=balanceAll;
                useDiscountMoney=0-discountMoney;
            }
        }else{
            var cha1=totalPrice-balanceAll;
            if(cha1<=0){
                $('#payTotal').html(0);
                $('#accountBalance').html(parseFloat(0-cha1).toFixed(2));
                useBalanceAll=totalPrice;
                useDiscountMoney=0;
            }else{
                $('#payTotal').html(parseFloat(cha1).toFixed(2));
                $('#accountBalance').html(0);
                useBalanceAll=balanceAll;
                useDiscountMoney=0;
            }
        }
    }
    else{
        if($('#discountMoney').prop('class')=='preferential_payment'){
            var cha2=totalPrice+discountMoney;
            if(cha2<=0){
                $('#payTotal').html(0);
                $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
                useBalanceAll=0;
                useDiscountMoney=0-discountMoney;
            }else{
                $('#payTotal').html(parseFloat(cha2).toFixed(2));
                $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
                useBalanceAll=0;
                useDiscountMoney=0-discountMoney;
            }
        }else{
            $('#payTotal').html(parseFloat(totalPrice).toFixed(2));
            $('#accountBalance').html(parseFloat(balanceAll).toFixed(2));
            useBalanceAll=0;
            useDiscountMoney=0;
        }
        $(this).find('img').prop('src','images/images_family_doctor/select.png');
    }
});
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
    $('#footer>p').removeAttr('onclick');
    orderId='';
    if(xyz != 0){
        return false;
    }else{
        timeInterval();
    }
    var payMethod=$('.choosePayWay>td:nth-child(2)').text();
    var payAmount=parseFloat($('#payTotal').html()).toFixed(2);
    useBalanceAll=parseFloat(useBalanceAll).toFixed(2);
    useDiscountMoney= parseFloat(useDiscountMoney).toFixed(2);
    if(payAmount==0){
        $.ajax({
            type:'post',
            url: memberHost + 'memberManage.action',
            cache:false,
            async:false,
            data:{
                action:"saveOrUpdateMemberBuyInfo",
                "product.id":productId,
                "memberBuyInfo.useRemainBalance":useBalanceAll,
                "memberBuyInfo.discountMoney":useDiscountMoney,
                "memberBuyInfo.orderNum":orderNum
            },
            success:function(result){
                window.location.href="user_member.html?"+orderNum;
            },
            error:function(){
                layer();
            }
        });

        //余额支付成功之后,根据订单编号向医生和用户发送提示信息短信，
        //医生提示的内容包括预约的订单号、预约的时间和预约的用户和手机号
        //用户提示的内容包括预约的订单号、预约的时间和预约的医生

        //ale("跳转到支付成功页面");
    }
    else if(payAmount>0){
        if(payMethod=="支付宝支付"){
            //支付宝支付相关的
            window.location.href="turnToAlipay.html?WIDseller_email=xiongchao@qiyico.com&WIDout_trade_no="+orderId+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount+"&business_category=clinic&useBalanceAll="+useBalanceAll+"&useDiscountMoney="+useDiscountMoney+"&userId="+userId;
        }else if(payMethod=="微信支付"){
            //微信支付相关的
            paycall("kybaby", orderId, orderNum, Math.round(payAmount*100));
        }else if(payMethod=="YINLIANZHIFU"){
            ale("正在积极的准备开发中，请耐心等候");
        }
}



//if(payMethod=="支付宝支付"){
//        //支付宝支付相关的
//        window.location.href="turnToAlipay.html?WIDseller_email=guoyushan@yishangkeji.cn&WIDout_trade_no="+orderId+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount;
//    }else if(payMethod=="微信支付"){
//        //微信支付相关的
//        paycall("kybaby", orderId, Math.round(payAmount*100));
//    }else if(payMethod=="YINLIANZHIFU"){
//        ale("正在积极的准备开发中，请耐心等候");
//    }

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
        error:function(){
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
                $.ajax({
                    type:'post',
                    url: memberHost + 'memberManage.action',
                    cache:false,
                    async:true,
                    data:{
                        action:"saveOrUpdateMemberBuyInfo",
                        "product.id":productId,
                        "memberBuyInfo.useRemainBalance":useBalanceAll,
                        "memberBuyInfo.discountMoney":useDiscountMoney,
                        "memberBuyInfo.orderNum":orderNum
                    },
                    success:function(result){
                        if(result.mes=="成功"){
                            //ale("订单更新成功");
                            window.location.href="user_member.html?"+orderNum;
                        }
                    },
                    error:function(){
                        layer();
                    }
                });
            }else{
                window.location.href="user_member_product.html";
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