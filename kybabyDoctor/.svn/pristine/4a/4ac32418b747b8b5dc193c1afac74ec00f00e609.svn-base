var orgClinicStartAddress='四川省成都市武侯区';
$(function(){
    //$("#ul_cli").show();
    $("#content").show();
    getMyDoctorOrder();
    var session=sessionStorage.getItem('door');
    for(var i=0;i<$(".select_tab").length;i++){
        if($(".select_tab").eq(i).text()==session){
            $(".select_tab").eq(i).trigger("click");
            sessionStorage.setItem('door',$(".select_tab").eq(i).text());
        }
    }
});
//详情
var doctorMorePractice;

//获取我的家庭医生订单
function getMyDoctorOrder(){
    $.ajax({
        type : 'post',
        async: false ,
        url : host+'orderManage.action',
        data : {action:"all"},
        success : function(result) {
            if(result.mes=="请登录"){
                window.location.href="login.html";
            }
            if(result.mes=="成功"){
                if(result.someOrderList != null){
                    $("#content").html('');
                    for(var i=0;i<result.someOrderList.length;i++){
//					ale(result.someOrderList.toString());
                        var str = result.someOrderList[i].toString();
                        var strList = str.split(",");
                        var total = parseInt(strList[1]);
                        var rate = parseFloat(strList[2]);
                        var butie = parseInt(strList[3]);
                        var mine = total*rate+butie;
                        if(strList[4]=="已接单"){
                            var linshiTime = result.time.substring(0,10);

                            if(linshiTime<strList[15]){
                                $("#content").append(
                                    "<!--内容-->"+
                                    "<div class='info_box' onclick='showDetail("+strList[14]+")'>"+
                                    "<span class='top_sj'>"+strList[0]+"</span>"+
                                    "<div class='info clearfix'>"+
                                    "<div class='img'>"+
                                    "<img src='"+hostBG+"/images/product/"+result.someOrderList[i][13]+"' width='80px' height='80px'>"+
                                    "</div>"+
                                    "<div class='text_box'>"+
                                    "<span>"+result.someOrderList[i][12]+"</span>&nbsp;<span  class='font1'></span><br/>"+
                                    "<span  class='font1'>家长姓名：</span><span  class='font1'>"+strList[5]+"</span><br/>"+
                                    "<span  class='font1'>手机号：</span><span  class='font1'>"+strList[6]+"</span><br/>"+
                                    "<span  class='font1'>地址：</span><span  class='font1 SL'>"+strList[7]+strList[8]+strList[9]+strList[10]+strList[11]+"</span>"+
                                    "</div>"+
                                    "</div>"+
                                    "<div class='top_je ' id='inButtom'>"+
                                    "<span>收入金额：</span>"+
                                    "<span class='font2'>&yen;"+mine+"</span>"+
                                    "<span class='button' data_type='"+strList[4]+"' onclick='cancle("+strList[14]+",event)'>取消订单</span>"+
                                    "<p class='gray_3'></p>"+
                                    "</div>"+
                                    "</div>");
                            }
                            else{
                                $("#content").append(
                                    "<!--内容-->"+
                                    "<div class='info_box' onclick='showDetail("+strList[14]+")'>"+
                                    "<span class='top_sj'>"+strList[0]+"</span>"+
                                    "<div class='info clearfix'>"+
                                    "<div class='img'>"+
                                    "<img src='"+hostBG+"/images/product/"+result.someOrderList[i][13]+"' width='80px' height='80px'>"+
                                    "</div>"+
                                    "<div class='text_box'>"+
                                    "<span>"+result.someOrderList[i][12]+"</span>&nbsp;<span  class='font1'></span><br/>"+
                                    "<span  class='font1'>家长姓名：</span><span  class='font1'>"+strList[5]+"</span><br/>"+
                                    "<span  class='font1'>手机号：</span><span  class='font1'>"+strList[6]+"</span><br/>"+
                                    "<span  class='font1'>地址：</span><span  class='font1 SL'>"+strList[7]+strList[8]+strList[9]+strList[10]+strList[11]+"</span>"+
                                    "</div>"+
                                    "</div>"+
                                    "<div class='top_je ' id='inButtom'>"+
                                    "<span>收入金额：</span>"+
                                    "<span class='font2'>&yen;"+mine+"</span>"+
                                    "<span data_type='"+strList[4]+"' class='button'>"+strList[4]+"</span>"+
                                    "<p class='gray_3'></p>"+
                                    "</div>"+
                                    "</div>");
                            }

                        }
                        else{
                            $("#content").append(
                                "<!--内容-->"+
                                "<div class='info_box' onclick='showDetail("+strList[14]+")'>"+
                                "<span class='top_sj'>"+strList[0]+"</span>"+
                                "<div class='info clearfix'>"+
                                "<div class='img'>"+
                                "<img src='"+hostBG+"images/product/"+result.someOrderList[i][13]+"' width='80px' height='80px'>"+
                                "</div>"+
                                "<div class='text_box'>"+
                                "<span>"+result.someOrderList[i][12]+"</span>&nbsp;<span  class='font1' id='productName'></span><br/>"+
                                "<span  class='font1'>家长姓名：</span><span  class='font1' id='parentName'>"+strList[5]+"</span><br/>"+
                                "<span  class='font1'>手机号：</span><span  class='font1' id='phoneNum'>"+strList[6]+"</span><br/>"+
                                "<span  class='font1'>地址：</span><span  class='font1 SL' id='address'>"+strList[7]+strList[8]+strList[9]+strList[10]+strList[11]+"</span>"+
                                "</div>"+
                                "</div>"+
                                "<div class='top_je ' id='inButtom'>"+
                                "<span>收入金额：</span>"+
                                "<span class='font2' id='myMoney'>&yen;"+mine+"</span>"+
                                "<span data_type='"+strList[4]+"' class='button' id='status'>"+strList[4]+"</span>"+
                                "<p class='gray_3'></p>"+
                                "</div>"+
                                "</div>");
                        }
                    }
                    $('span[data_type=已评价],span[data_type=已完成],span[data_type=已确认],span[data_type=用户取消],span[data_type=医生取消],span[data_type=已退款]').parent().parent().removeAttr('onclick');

                }
                $('.select_tab').click(function(){
                    var txt = $(this).text();
                    $(this).addClass("emphasis");
                    $(this).siblings().removeClass("emphasis");
                    sessionStorage.setItem('door',txt);
                    if(txt == '全部'){
                        $('.info_box').show();
                        $('.info_box').next().show();
                    }else if(txt == '已取消'){
                        $('.info_box').hide();
                        $('.info_box').next().hide();
                        var a = $('.button').filter(function(){
                            return $(this).attr('data_type') == '医生取消';
                        }).parent().parent();
                        a.show();
                        a.next().show();
                    }else if(txt == '已完成'||txt == '已评价'){
                        $('.info_box').hide();
                        $('.info_box').next().hide();
                        var a = $('.button').filter(function(){
                            return $(this).attr('data_type') == '已完成';
                        }).parent().parent();
                        a.show();
                        a.next().show();
                        var b = $('.button').filter(function(){
                            return $(this).attr('data_type') == '已确认';
                        }).parent().parent();
                        b.show();
                        b.next().show();
                        var c = $('.button').filter(function(){
                            return $(this).attr('data_type') == '已评价';
                        }).parent().parent();
                        c.show();
                        c.next().show();
                    }else{
                        $('.info_box').hide();
                        $('.info_box').next().hide();
                        var a = $('.button').filter(function(){
                            return $(this).attr('data_type') == txt;
                        }).parent().parent();
                        a.show();
                        a.next().show();
                    }
                });
            }
        }
    });

}

function cancle(orderId,event){
    event.stopPropagation();
    var r=confirm("确定要取消订单吗？");
    if (r==false){
        return false;
    }
    $.ajax({
        type : 'post',
        async: false ,
        url : host+'orderManage.action',
        data : {action:"cancel",orderId:orderId},
        success : function(result) {
            if(result.mes=="成功"){
                window.location.href="myorder.html";
            }
        }
    });
}

function showDetail(id){
    window.location.href="order_details.html?"+id;//传订单ID
    var text = $(".emphasis").text();
    if(text!="全部"){
        sessionStorage.setItem('door',text);
    }
}

//获取详细地址
//function showLocation(position) {
//    var latitude = position.coords.latitude;
//    var longitude = position.coords.longitude;
//    var accuracy = position.coords.accuracy;
//    alert("Latitude : " + latitude + " Longitude: " + longitude+ " accuracy: " + accuracy);
//}
//
//function errorHandler(err) {
//    if(err.code == 1) {
//        alert("Error: Access is denied!");
//    }
//    else if( err.code == 2) {
//        alert("Error: Position is unavailable!");
//    }
//}
//
//function getLocation(){
//
//    if(navigator.geolocation){
//        // timeout at 60000 milliseconds (60 seconds)
//        var options = {timeout:60000};
//        navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);
//    }
//    else{
//        alert("Sorry, browser does not support geolocation!");
//    }
//}
var workStatus;
function getLocation(){
    var browser={
        versions:function(){
            var u = navigator.userAgent, app = navigator.appVersion;
            return {
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                qq: u.match(/\sQQ/i) == " qq" //是否QQ
            };
        }(),
        language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }
//判断是否IE内核
//    if(browser.versions.trident){ alert("is IE"); }
//判断是否webKit内核
//    if(browser.versions.webKit){ alert("is webKit"); }
//判断是否android
//    if(browser.versions.android){ alert("is android"); }
//判断是否移动端
//    if(browser.versions.mobile||browser.versions.android||browser.versions.ios){ alert("移动端"); }
//判断是否微信
    if(browser.versions.weixin){
        return false;
    }

}
//获取手机当前位置
var timestamp = '';
var nonceStr = '';
var signature = '';
var jsapi_ticket = '';
var string_1 = '';
function getLocation() {
    timestamp = getTimeStamp();
    nonceStr = getNonceStr();
    jsapi_ticket = getJsApi();
    string_1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + window.location.href;
    signature = hex_sha1(string_1);
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['openLocation', 'getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        wx.checkJsApi({
            jsApiList: ['openLocation', 'getLocation'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function (res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            }
        });
        //wx.openLocation({
        //    latitude: 0, // 纬度，浮点数，范围为90 ~ -90
        //    longitude: 0, // 经度，浮点数，范围为180 ~ -180。
        //    name: '', // 位置名
        //    address: '', // 地址详情说明
        //    scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
        //    infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
        //});
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                var userLat = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var userLng = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var speed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度
                getAddress(userLat,userLng);
            }
        });
    });
}
//通过经纬度获取地址
function getAddress(latitude, longitude, callback) {
    $.ajax({
        url: 'http://api.map.baidu.com/geocoder/v2/?ak=btsVVWf0TM1zUBEbzFz6QqWF&callback=renderReverse&location=' + latitude + ',' + longitude + '&output=json&pois=1',
        type: "get",
        dataType: "jsonp",
        jsonp: "callback",
        success: function (data) {
            var cityname = (data.result.addressComponent.city);
            orgClinicStartAddress = data.result.formatted_address;
            //domTempe(cityname,latitude,longitude);
            var data = {
                latitude: latitude,
                longitude: longitude,
                cityname: cityname
            };
            if (typeof callback == "function") {
                callback(data);
            }
        }
    });
}

function getTimeStamp() {
    var timestamp = new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    return timestampstring;
}
function getNonceStr() {
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    var noceStr = "";
    for (var i = 0; i < 32; i++) {
        noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return noceStr;
}
function getJsApi() {
    var jsApiTicket = '';
    $.ajax({
        type: 'post',
        cache: false,
        async: false,
        url: host + '../wx/getJsApi.action',
        data: {
            action: "getJsApiTicket"
        },
        success: function (result) {
            jsApiTicket = result.jsApiTicket;
        }
    });
    return jsApiTicket;
}

//遮罩
function cancel2() {
    $('#cover2').css({display:'none'});
};
function sure2() {
    $('#cover2').css({display:'none'});
    var txt=$('.isSure2').attr('data-stu');
    workStatus=txt;
    if(workStatus=='我要上班'){
        $.ajax({
            type:'post',
            url:urlWay.clinicHost+'doctorClinic.action',
            cache:false,
            async:false,
            data:{action : "saveWorkRecord",
                "doctorMorePractice.orgClinicStartAddress":orgClinicStartAddress,
                "doctorMorePractice.startEndFlag":workStatus,
                "doctorMorePractice.id":doctorMorePractice
            },
            success:function(result){
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == "成功") {
                    //alert(workStatus);
                    $('#ul_cli2,#content2').css({display:'block'});
                    $('#cover').css({display:'none'});
                    getMyOrg();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert(errorThrown);
            }
        });
    }
    else{
        $.ajax({
            type:'post',
            url:urlWay.clinicHost+'doctorClinic.action',
            cache:false,
            async:false,
            data:{action : "saveWorkRecord",
                "doctorMorePractice.orgClinicEndAddress":orgClinicStartAddress,
                "doctorMorePractice.startEndFlag":workStatus,
                "doctorMorePractice.id":doctorMorePractice
            },
            success:function(result){
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == "成功") {
                    //alert(workStatus);
                    $('#ul_cli2,#content2').css({display:'block'});
                    $('#cover').css({display:'none'});
                    getMyOrg();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert(errorThrown);
            }
        });
    }

};



