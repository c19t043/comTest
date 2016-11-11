var orderId=decodeURIComponent(window.location.search.substring(1).split("&")[0]);
var orderStatus=decodeURIComponent(window.location.search.substring(1).split("&")[1]);
$(function () {
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'doctorClinicOrder.action',
        cache:false,
        async:false,
        data:{
            action : "getClinicOrderDetail",
            "orderInfoClinic.id":orderId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var data=result.orderInfoClinic;
                $('.parentImg').prop('src',hostBG+'images/userFaceIcon/'+data.userInfo.userImage);
                $('#user_info>div').html(data.userInfo.parentName+'<br/>联系人：'+data.userInfo.parentName);
                $('.address>span').html(data.clinicAddress);
                $('.time>span').html(data.appointmentDate+' '+data.appointmentBeganTime+'-'+data.appointmentEndTime);
                if(orderStatus=='已预约'){
                    $('#start>span').html(data.orderTime);
                    $('.confirm').html('确认会面');
                }else if(orderStatus=='已会面'){
                    $('#start>span').html(data.orderTime);
                    $('.confirm').html('结束会面');
                    $('#ongoing').removeClass('filter');
                    $('#ongoing>span').html(result.orderInfoClinic.meetTime);
                }else{
                    $('#start>span').html(data.orderTime);
                    $('#status').css({display:'none'});
                    $('#ongoing').removeClass('filter');
                    $('#end').removeClass('filter');
                    $('#ongoing>span').html(result.orderInfoClinic.meetTime);
                    $('#end>span').html(result.orderInfoClinic.endTime);
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });
});
function sureMeet(div){
    var div=$(div).text();
    if(div=='确认会面'){
        $('.cover1').html('');
        $('#cover1').css({display:'block'});
        $('.cover1').qrcode({
            render: "canvas", //table方式
            width: 200, //宽度
            height:200, //高度
            text:urlWay.hostName+'/kybaby/main/confirmMeeting.html?'+orderId
        });

        //$('.cover1').html('<img src="http://www.2d-code.cn/2dcode/api.php?key=c_8dc66jJNwJwBxcKy' +
        //'AsHxWKnIBjQ2z9dR9Yed7goxlHE&text=2dcode二维码生成&bgcolor=ffffff&color=000000&cl=H&si' +
        //'ze=300&border=1&url='+urlWay.hostName+'/kybaby/main/confirmMeeting.html?'+orderId+'"/>');
    }else{
        $('#cover').css({display:'block'});
    }
}
$('.cancel').click(function () {
    $('#cover').css({display:'none'});
});
$('.cancel2').click(function () {
    $('#cover2').css({display:'none'});
});
$('.sure').click(function () {
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'clinicOrderStatus.action',
        cache:false,
        async:false,
        data:{action : "updateClinicOrderStatus",
            "orderInfoClinic.id":orderId,
            "orderInfoClinic.orderStatus":"已完成"
        },
        success:function(result){
            $('#cover').css({display:'none'});
            $('#end').removeClass('filter');
            $('#status').css({display:'none'});
            $.ajax({
                type:'post',
                url:urlWay.clinicHost+'doctorClinicOrder.action',
                cache:false,
                async:false,
                data:{
                    action : "getClinicOrderDetail",
                    "orderInfoClinic.id":orderId
                },
                success:function(result){
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "login.html";
                    }
                    else if (result.mes == "成功") {
                        var data=result.orderInfoClinic;
                        $('#end>span').html(data.endTime);
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                    alert(errorThrown);
                }
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });
});
$('.sure2').click(function () {
    $('#cover1').css({display:'none'});
    $('#cover2').css({display:'none'});
});
$('.cancel_sure').click(function(){
    var ns='';
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'doctorClinicOrder.action',
        cache:false,
        async:false,
        data:{
            action : "getClinicOrderDetail",
            "orderInfoClinic.id":orderId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                ns=result.orderInfoClinic.orderStatus;
                if(ns=='已会面'){
                    $('#cover1').css({display:'none'});
                    $('.confirm').html('结束会面');
                    $('#ongoing').removeClass('filter');
                    $('#ongoing>span').html(result.orderInfoClinic.meetTime);
                }else{
                    $('#cover2').css({display:'block'});
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });
});
