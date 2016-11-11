/**
 * Created by windows on 2016/2/2.
 */
//点击选择支付方式 add by sujiantang 2015-10-13
//获取数据
var orderInfoId=decodeURIComponent(window.location.search.substring(1).split('&&')[0]);
var orderDate=decodeURIComponent(window.location.search.substring(1).split('&&')[1]);
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
//function goback(){
//    var date=parseInt($('#dateTime').text().trim().split('-')[2]);
//    window.location.href='appointmentPatient.html?'+doctorId+'&&'+date;
//}
function sure2(){
    window.history.back();
    $('#cover2').hide();
}
function menu(event){
    event.stopPropagation();
    var height=parseInt($('#menu .select').height());
    if(height!=0){
        $('#menu .select').animate({height:'0px'});
    }else{
        if(orderDate!='undefined'){
            $('#modify').show();
            $('#menu .select').animate({height:'80px'});
        }else{
            $('#menu .select').animate({height:'40px'});
        }
    }
}
document.onclick= function (event) {
    $('#menu .select').animate({height:'0px'});
}
function modify(){
    window.location.href='appointmentPatient.html?'+orderInfoId+'&&orderDate='+orderDate;
}
$(function(){
    //if(orderDate!='undefined'){
    //    $('#modify').show();
    //}
    $.ajax({
        type:'post',
        url:clinicHost+'clinicOrder.action',
        cache:false,
        async:false,
        data:{action:"getClinicOrderInfo",
            "orderInfoClinic.id":orderInfoId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes == '成功'){
                var list=result.orderInfoClinic;
                isPlus=list.isPlus;
                var preferential=result.clinicDiscountInfo;
                var doctor = list.doctorInfo;
                var user = result.clinicOtherContactsInfo;
                    //console.log(result);
                totalPrice=parseFloat(list.totalPrice,2);
                balanceAll=parseFloat(list.userInfo.accountBalance,2);
                if(preferential!=null){
                    discountMoney=parseFloat(preferential.discountMoney,2);
                    useDiscountMoney=0-discountMoney;
                    $('.preferential,.preferential1').css({display:'block'});
                    $('#discountName').html(preferential.discountName);
                    $('#discountMoney').html(discountMoney);
                    cha1=totalPrice+discountMoney;
                    if(cha1<=0){
                        $('#payTotal').html(0);
                    }else{
                        $('#payTotal').html(parseFloat(cha1,2).toFixed(2));
                    }
                }else{
                    $('#payTotal').html(parseFloat(totalPrice,2).toFixed(2));
                }
                if(result.orderInfoClinic.orderStatus=="已预约"){
                    $('footer').hide();
                }
                $('.description>img').prop('src',hostBG+'images/doctorFaceIcon/'+doctor.doctorImage);
                $('#doctorMessage').html('' +
                '<span id="doctorName">' + doctor.doctorName + '</span><br/>' +
                '<span id="hospital" class=".p">'+ doctor.department+' | '+ doctor.doctorTitle+'</span>');
                $('#userName').html(user.otherName);
                $('#userPhone').html(user.otherPhone);
                $('#accountBalance').html(list.userInfo.accountBalance);
                $('#dateTime').html(list.appointmentDate);
                $('.ad').html(list.clinicAddress);
                if(result.memberManageList != null){
                    $('#historyPrice').html('<div class="float-left">预约门诊服务费</div> ' +
                    '<div class="float-right"><s style="color: #909090">&yen;'+list.historyPrice+'</s></div>');
                    $('#newPrice').html('<div class="float-left" style="color: #ff813d">会员价</div> ' +
                    '<div class="float-right"><span style="color: #ff813d">&yen;<span id="visit"></span>'+list.totalPrice+'</span></div>');
                }else{
                    $('#visit').html(list.totalPrice);
                }
                //$('#newPrice').prepend('&yen;<s>50</s>');
                //$('#newPrice').append('(<span style="color: red">会员价</span>)');
                dateTime=list.appointmentDate;
                orderId=list.id;
                orderNum=list.orderNum;
                doctorId=list.doctorInfo.id;
                timeStart=list.appointmentBeganTime;
                $('#timePeriod').html(list.appointmentBeganTime+'-'+list.appointmentEndTime);
//                    $('#dayTime').html(list.appointmentDate);
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
    $('.payWay .icon').attr('src','images/images_family_doctor/select.png');
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
//选择取消订单
function cancelOrder(){
    var answer=confirm('您是否确定取消？');
    if(answer==true){
        $.ajax({
        type:'post',
        url:clinicHost+'clinicOrder.action',
        cache:false,
        async:false,
        data:{
            action : "updateClinicOrderStatus",
            "orderInfoClinic.id":orderInfoId,
            "orderInfoClinic.orderStatus":"用户取消"
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                window.location.href = "pointPatient.html?"+doctorId;
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(XMLHttpRequest.readyState);
            //alert(textStatus);
            //alert(errorThrown);
            layer();
        }
    });
    }
}
//订单支付--------------------------------------------------------------------
var orderId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);

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
    if(xyz != 0){
        return false;
    }else{
        timeInterval();
    }
    $.ajax({
        type:'post',
        url:clinicHost+'clinicOrder.action',
        cache:false,
        async:false,
        data:{
            action : "checkClinicDoctorWorkStatue",
            "orderInfoClinic.id":orderInfoId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes != "成功") {
                alert(result.mes);
                return false;
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

    var payMethod=$('.choosePayWay>td:nth-child(2)').text();
    var payAmount=parseFloat($('#payTotal').html()).toFixed(2);
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicOrder.action',
        cache: false,
        async: false,
        data: {
            action: "checkTimeIsUsed",
            "doctorInfo.id": doctorId,//得到单个医生信息传此参数，医生列表不需要传参
            "orderInfoClinic.appointmentDate":dateTime,
            "orderInfoClinic.appointmentBeganTime":timeStart,
            "orderInfoClinic.isPlus":isPlus
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                $('#footer>p').removeProp('onclick');
                if(result.checkStatus=='已被预约'){
                    $('#cover2').show();
                }else{
                    useBalanceAll=parseFloat(useBalanceAll).toFixed(2);
                    useDiscountMoney= parseFloat(useDiscountMoney).toFixed(2);
                    if(payAmount==0){
                        $.ajax({
                            type:'post',
                            url:clinicHost+'clinicOrder.action',
                            cache:false,
                            async:false,
                            data:{
                                action:"saveOrUpdateClinicOrder",
                                "orderInfoClinic.orderStatus":"付款成功",
                                "orderInfoClinic.id":orderInfoId,
                                "doctorInfo.id": doctorId,
                                "orderInfoClinic.useRemainBalance":useBalanceAll,
                                "orderInfoClinic.discountMoney":useDiscountMoney
                    },
                            success:function(result){
                            }
                        });

                        //余额支付成功之后,根据订单编号向医生和用户发送提示信息短信，
                        //医生提示的内容包括预约的订单号、预约的时间和预约的用户和手机号
                        //用户提示的内容包括预约的订单号、预约的时间和预约的医生
                        $.ajax({
                            type:'post',
                            url:host+'sendMessageByClinic.action',
                            cache:false,
                            async:false,
                            data:{action:"toUser","orderInfoClinic.id":orderInfoId,sendWords:"预约门诊"},
                            success:function(result){
                            }
                        });
                        $.ajax({
                            type:'post',
                            url:host+'sendMessageByClinic.action',
                            cache:false,
                            async:false,
                            data:{action:"toDoctor","orderInfoClinic.id":orderInfoId,sendWords:"预约门诊"},
                            success:function(result){
                            }
                        });
                        //ale("跳转到支付成功页面");
                        window.location.href="paysuccess.html";
                    }
                    else if(payAmount>0){
                        if(payMethod=="支付宝支付"){
                            //支付宝支付相关的
                            window.location.href="turnToAlipay.html?WIDseller_email=xiongchao@qiyico.com&WIDout_trade_no="+orderId+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount+"&business_category=clinic&useBalanceAll="+useBalanceAll+"&useDiscountMoney="+useDiscountMoney+"&doctorId="+doctorId;
                        }else if(payMethod=="微信支付"){
                            //微信支付相关的
                            paycall("kybaby", orderId, orderNum, Math.round(payAmount*100));
                        }else if(payMethod=="YINLIANZHIFU"){
                            ale("正在积极的准备开发中，请耐心等候");
                        }
                    }
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });


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
//	    	   $.ajax({
//	               type:'post',
//	               url:clinicHost+'clinicOrder.action',
//	               cache:false,
//	               async:true,
//	               data:{
//	                   action:"payClinicSuccess",
//	                   "checkWords":res.err_msg
//	               },
//	               success:function(result){
//	               }
//	           });
            if(res.err_msg == "get_brand_wcpay_request:ok" || res.err_msg == "get_brand_wcpay_request：ok") {
                //ale("wei xin zhi fu 成功,开始订单处理");
                //ale("支付成功的订单编号"+orderNum);
                //支付成功，将订单号反馈，后台根据orderNum参数更新对应的状态,然后跳转到支付成功页面
                $.ajax({
                    type:'post',
                    url:clinicHost+'clinicOrder.action',
                    cache:false,
                    async:true,
                    data:{
                        action:"saveOrUpdateClinicOrder",
                        "orderInfoClinic.orderStatus":"付款成功",
                        "orderInfoClinic.id":orderInfoId,
                        "doctorInfo.id": doctorId,
                        "orderInfoClinic.useRemainBalance":useBalanceAll,
                        "orderInfoClinic.discountMoney":useDiscountMoney
                    },
                    success:function(result){
                        if(result.mes=="成功"){
                            window.location.href="paysuccess.html?"+orderAmount;
                        }else{
                            ale('出错了');
                        }
                    },
                    error: function (x) {
                        alert(x.status);
                    }
                });
                $.ajax({
                    type:'post',
                    url:'sendMessageByClinic.action',
                    cache:false,
                    async:true,
                    data:{action:"toUser","orderInfoClinic.id":orderInfoId,sendWords:"预约门诊"},
                    success:function(result){

                    }
                });
                $.ajax({
                    type:'post',
                    url:'sendMessageByClinic.action',
                    cache:false,
                    async:true,
                    data:{action:"toDoctor","orderInfoClinic.id":orderInfoId,sendWords:"预约门诊"},
                    success:function(result){

                    }
                });
            } else{
                window.location.href="payfail.html";
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