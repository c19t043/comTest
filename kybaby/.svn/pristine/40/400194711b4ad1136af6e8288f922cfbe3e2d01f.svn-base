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



var amountAll=0;
var orderNumAll=0;
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
                payMethod:'金控对接'
            },
            success:function(result){
                var orderNum=result.orderNum;
                orderNumAll=orderNum;
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
                    amountAll=amount;
                    document.addEventListener('deviceready', onDeviceReady, false);
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

//金卫信支付接口
function onDeviceReady(){
    var userId=0;
    var token=0;
    $.ajax({
        type:'post',
        url:host+'getUserInfo.action',
        cache:false,
        async:false,
        data:{action:"getUser"},
        success:function(result){
            if(result.mes=="操作成功"){
                if(result.usr!=null){
                    var usr=result.usr;
                    userId=usr.recommendNum;
                    token=usr.comments;
                }
            }else if(result.mes=="未登录"){
                ale("请先登录系统！");
            }
        }
    });

    YJH. H5ModuleManager.requestPayment(function(res){
            if(res.status==0){
                $.ajax({
                    type:'post',
                    url:'orderManage.action',
                    cache:false,
                    async:false,
                    data:{action:"paySuccess",orderNum:orderNumAll},
                    success:function(result){
                        if(result.mes=="操作成功"){
                            //ale("订单更新成功");
                        }
                    },
                    error: function () {
                    }
                });

                $.ajax({
                    type:'post',
                    url:'sendMessage.action',
                    cache:false,
                    async:false,
                    data:{action:"toUser",orderNum:orderNumAll,sendWords:"预约"},
                    success:function(result){

                    }
                });
                $.ajax({
                    type:'post',
                    url:'sendMessage.action',
                    cache:false,
                    async:false,
                    data:{action:"toDoctor",orderNum:orderNumAll,sendWords:"预约"},
                    success:function(result){

                    }
                });
                window.location.href="paysuccess.html?"+amountAll;
            }else{
                ale('支付失败');
            }
        },
        {'userId':userId,'cid':productId,'subject':'商城','totalFee':amountAll,'token':token ,'body':'商城商品'}
    );
}