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
var userInfoArr=[];
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
                var userInfo=result.orderInfoClinic.userInfo;
                userInfoArr=[userInfo.recommendNum,userInfo.comments,orderInfoId,result.orderInfoClinic.clinicAddress];
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
                    $('.preferential').css({display:'block'});
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
                $('#footer').html('<p class="login_button" onclick="payProduct()">去支付</p>');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
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

function payProduct() {
    if (xyz != 0) {
        return false;
    } else {
        timeInterval();
    }
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicOrder.action',
        cache: false,
        async: false,
        data: {
            action: "checkClinicDoctorWorkStatue",
            "orderInfoClinic.id": orderInfoId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes != "成功") {
                alert(result.mes);
                return false;
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });

    var payMethod = $('.choosePayWay>td:nth-child(2)').text();
    var payAmount = parseFloat($('#payTotal').html()).toFixed(2);
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicOrder.action',
        cache: false,
        async: false,
        data: {
            action: "checkTimeIsUsed",
            "doctorInfo.id": doctorId,//得到单个医生信息传此参数，医生列表不需要传参
            "orderInfoClinic.appointmentDate": dateTime,
            "orderInfoClinic.appointmentBeganTime": timeStart,
            "orderInfoClinic.isPlus": isPlus
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                $('#footer>p').removeProp('onclick');
                if (result.checkStatus == '已被预约') {
                    $('#cover2').show();
                } else {
                    useBalanceAll = parseFloat(useBalanceAll).toFixed(2);
                    useDiscountMoney = parseFloat(useDiscountMoney).toFixed(2);
                    if (payAmount == 0) {
                        paySuccess(payAmount);
                    }
                    else if (payAmount > 0) {
                        document.addEventListener('deviceready', onDeviceReady, false);
                    }
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            //layer();
        }
    });
}
//金卫信支付接口
function onDeviceReady(){
    var payAmount = parseFloat($('#payTotal').html()).toFixed(2);
    YJH. H5ModuleManager.requestPayment(function(res){
            if(res.status==0){
                useBalanceAll = parseFloat(useBalanceAll).toFixed(2);
                useDiscountMoney = parseFloat(useDiscountMoney).toFixed(2);
                paySuccess(payAmount);
            }else{
                ale('支付失败');
            }
    },
    {'userId':userInfoArr[0],'cid':userInfoArr[2],'subject':'门诊预约','totalFee':payAmount,'token':userInfoArr[1] ,'body':userInfoArr[3]}
    );
}
//支付成功后
function paySuccess(payAmount){
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicOrder.action',
        cache: false,
        async: false,
        data: {
            action: "saveOrUpdateClinicOrder",
            "orderInfoClinic.orderStatus": "付款成功",
            "orderInfoClinic.id": orderInfoId,
            "doctorInfo.id": doctorId,
            "orderInfoClinic.useRemainBalance": useBalanceAll,
            "orderInfoClinic.payMethod": '金控对接',
            "orderInfoClinic.discountMoney": useDiscountMoney
        },
        success: function (result) {
        }
    });

    //余额支付成功之后,根据订单编号向医生和用户发送提示信息短信，
    //医生提示的内容包括预约的订单号、预约的时间和预约的用户和手机号
    //用户提示的内容包括预约的订单号、预约的时间和预约的医生
    $.ajax({
        type: 'post',
        url: host + 'sendMessageByClinic.action',
        cache: false,
        async: false,
        data: {action: "toUser", "orderInfoClinic.id": orderInfoId, sendWords: "预约门诊"},
        success: function (result) {
        }
    });
    $.ajax({
        type: 'post',
        url: host + 'sendMessageByClinic.action',
        cache: false,
        async: false,
        data: {action: "toDoctor", "orderInfoClinic.id": orderInfoId, sendWords: "预约门诊"},
        success: function (result) {
        }
    });
    //ale("跳转到支付成功页面");
    window.location.href = "paysuccess.html?"+Math.round(payAmount*100);
}