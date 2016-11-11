var userId;
$(function(){
    var g=localStorage.getItem('h');
    if(g!=null){
        var s=$.parseJSON(g);
        userId=s.userInfo.id;
    }
    $(".selbtn").click(function(){
        $(".selbtn").removeClass("select");
        $(this).addClass("select");
        if($(this).data("name") == "我的预约门诊"){
            $("#info_boxdoc").show();
            $("#select_cli1").show();
            $("#info_box_all").hide();
            $("#select_cli").hide();
        }else{
            $("#info_boxdoc").hide();
            $("#select_cli1").hide();
            $("#info_box_all").show();
            $("#select_cli").show();
        }
    });
});
//得到门诊预约订单

function getAppointment(){
    var todayTime;
    var todayDate;
    $.ajax({
        type:'post',
        url:clinicHost+'clinicOrder.action',
        cache:false,
        async:true,
        data:{action:"getClinicOrderList","userInfo.id":userId},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
            }
            else if (result.mes == "成功") {
                var list=result.orderInfoClinicList;
                if(list==null){
                    $("#info_boxdoc>h6").hide();
                }else{
                    $.ajax({
                        type:'post',
                        url:clinicHost+'clinicBooking.action',
                        cache:false,
                        async:true,
                        data:{action:"getCurrentTime","userInfo.id":userId},
                        success:function(result){
                            if (result.mes == '请登录') {
                                ale('请登录', '24px');
                            }
                            else if (result.mes == "成功") {
                                todayTime=result.currentTime.replace('-','').replace('-','').replace(' ','').replace(':','').replace(':','');
                                todayDate=result.currentTime.split(' ')[0].replace('-','').replace('-','');
                                for(var i= 0,len=list.length;i<len;i++){
                                    var doctor=list[i].doctorInfo;
                                    var lastHtml='';
                                    var orderTime;
                                    var orderDate;
                                    if(list[i].orderStatus=='未付款'){
                                        if(list[i].isPlus=='Y'){
                                            orderDate=list[i].appointmentDate.replace('-','').replace('-','');
                                            if(orderDate>=todayDate){
                                                var date=list[i].appointmentDate.split('-')[2];
                                                lastHtml='<div class="pjdoctor1" onclick="mspay('+list[i].id+','+date+')">立即支付</div>';
                                            }else{
                                                lastHtml='<div style="position: absolute;top:35px;right: 10px" class="mark_icon"><img src="'+hostMain+'images/passed.png"/></div>'
                                            }
                                        }else{
                                            orderTime=list[i].appointmentDate.replace('-','').replace('-','');
                                            orderTime+=list[i].appointmentBeganTime.replace(':','')+'00';
                                            if(orderTime>todayTime){
                                                var date=list[i].appointmentDate.split('-')[2];
                                                lastHtml='<div class="pjdoctor1" onclick="mspay('+list[i].id+','+date+')">立即支付</div>';
                                            }else{
                                                lastHtml='<div style="position: absolute;top:35px;right: 10px" class="mark_icon"><img src="'+hostMain+'images/passed.png"/></div>'
                                            }
                                        }
                                        //lastHtml='<div class="pjdoctor1"><a href="tel://13541280713">号码</a></div>';
                                    }else if(list[i].orderStatus=='已完成'){
                                        //lastHtml='<div class="pjdoctor1" onclick="commentDoctor('+doctor.id+','+list[i].id+')">评价医生</div>';
                                    }else if(list[i].orderStatus=='已预约'){
                                        //lastHtml='<div class="pjdoctor1" onclick="cancellation('+doctor.id+','+list[i].id+')">取消预约</div>';
                                    }else if(list[i].orderStatus=='用户取消'||list[i].orderStatus=='已评价'){
                                        lastHtml='';
                                    }
                                    $('#doctor_list').append('<div class="listdetail"><div class="doctor_head"><img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'"></div> ' +
                                    '<div class="basicinfo"> <p class="detail_name">'+doctor.doctorName+'&nbsp;&nbsp;</p> ' +
                                    '<p class="detail_time">'+list[i].appointmentDate+' '+list[i].appointmentBeganTime+'-'+list[i].appointmentEndTime+'</p>' +
                                    '<p class="detail_address">'+list[i].clinicAddress+'</p><div style="overflow: hidden"><span style="color: #F86B6B;font-size: 13px">订单金额￥'+list[i].totalPrice+'</span><div class="pjdoctor" data-order="'+list[i].orderStatus+'">'+list[i].orderStatus+'</div></div>' +
                                    ' </div> '+lastHtml+' </div> ');
                                }
                            }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                           // layer();
                        }
                    });
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
}
function mspay(id,date){
//    window.location.href='payAppointment.html?'+id+'&&'+date;
	 window.location.href = 'yjh_payAppointment.html?' + id + '&&' + date;
}
function commentDoctor(doctorid,orderId){
    window.location.href='commentDoctor.html?'+doctorid+'&&'+orderId;
}
$(function () {
    getAppointment();
    $('#select_cli1>li').click(function () {
        var arr=[];
        var txt = $(this).text();
        $('#select_cli1>li').removeClass('blue');
        $(this).addClass('blue');
        var dataOrder=$('.pjdoctor');
        if(txt == '全部'){
            $('.listdetail').show();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=用户取消]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='已预约'||$('.pjdoctor').eq(i).html()=='用户取消'||$('.pjdoctor').eq(i).html()=='未付款'||$('.pjdoctor').eq(i).html()=='已完成'||$('.pjdoctor').eq(i).html()=='已评价'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt == '已取消'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=用户取消]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='用户取消'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt == '已预约'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=已预约]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='已预约'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt=='未付款'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=未付款]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='未付款'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt=='已完成'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=已完成]').parentsUntil('#doctor_list').show();
                $('.pjdoctor[data-order=已评价]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='已完成'||$('.pjdoctor').eq(i).html()=='已评价'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }
        if(arr.length==0){
            $("#info_boxdoc>h6").hide();
        }else{
            $("#info_boxdoc>h6").show();
        }
    });
});
function cancellation(doctorId,orderId) {
    var answer=confirm('您是否确定取消？');
    if(answer==true){
        $.ajax({
            type: 'post',
            url: clinicHost + 'clinicOrder.action',
            cache: false,
            async: false,
            data: {
                action: "saveOrUpdateClinicOrder",
                "orderInfoClinic.orderStatus": "用户取消",//这个固定的
                "orderInfoClinic.id": orderId,
                "doctorInfo.id": doctorId
            },
            success: function (result) {
                if(result.mes=='时间过期'){
                    ale('取消失败，您的订单已超过系统有效取消时间');
                }else{
                    window.location.href = 'myAppointment.html';
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //alert(XMLHttpRequest.status);
                //alert(errorThrown);
                layer();
            }
        });
    }
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
                //ale("wei xin zhi fu 成功,开始订单处理");
                //ale("支付成功的订单编号"+orderNum);
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
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
            layer();
        }
    });
    return prepayId;
}

//支付功能结束----------------------------------------------------------------------------
