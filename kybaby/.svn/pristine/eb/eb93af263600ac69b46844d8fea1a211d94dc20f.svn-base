//    var index=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
//    var listArr=sessionStorage.getItem('list'+index);
//    console.log(listArr);
var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var day = today.getDay();//星期
var date = today.getDate();
var days;
var dateArr = [];
var serDocId = decodeURIComponent(window.location.search.substring(1).split('&')[0].trim());
var orgId = '';
var recentArr = [];
var userId;
//得到今后一周的日期
$(function () {
	var g=localStorage.getItem('h');

    if(g!=null){
        var s=$.parseJSON(g);
        userId=s.userInfo.id;
    }

    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        
        data:{action:"getCurrentTime","userInfo.id":userId},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                var isSession=sessionStorage.getItem('session');
                if(isSession=='yes'){
                    return false;
                }
     
            }
            else if (result.mes == "成功") {
                var todayTimeArr=result.currentTime.split(' ')[0].split('-');
                year=parseInt(todayTimeArr[0]);
                month=parseInt(todayTimeArr[1]-1);
                var currentWeekDate=result.currentWeekDate;
                //day=
                switch (currentWeekDate) {
                    case '周日':
                        day = 0;
                        break;
                    case '星期一':
                        day =1;
                        break;
                    case '星期二':
                        day =2 ;
                        break;
                    case '星期三':
                        day=3 ;
                        break;
                    case '星期四':
                        day =4 ;
                        break;
                    case '星期五':
                        day = 5;
                        break;
                    case '星期六':
                        day = 6;
                        break;
                }
                date=parseInt(todayTimeArr[2]);
                //console.log(year);
                //console.log(month);
                //console.log(date);
                //console.log(day);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

    orgId=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
    if(orgId == 'undefined' || orgId == undefined){
        orgId='';
    }else{
        orgId=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
    }
    if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
        days = 31;
    }
    else if (month == 3 || month == 10 || month == 5 || month == 8) {
        days = 30;
    }
    else {
        if (year % 4 != 0) {
            days = 28
        }
        else {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
            } else {
                days = 29
            }
        }
    }
    var dayArr = ['今天', '明天', '后天'];
    var daySum = day + 3;
    for (var j = 0; j < 11; j++) {
        if (daySum > 6) {
            daySum = daySum - 7;
        }
        if (daySum == 0) {
            dayArr.push('周日');
        }
        else if (daySum == 1) {
            dayArr.push('周一');
        }
        else if (daySum == 2) {
            dayArr.push('周二');
        }
        else if (daySum == 3) {
            dayArr.push('周三');
        }
        else if (daySum == 4) {
            dayArr.push('周四');
        }
        else if (daySum == 5) {
            dayArr.push('周五');
        }
        else if (daySum == 6) {
            dayArr.push('周六');
        }
        daySum++;
    }
    var dateList = $('.weekdate');
    var dayList = $('.weekday');
    for (var i = 0; i < 14; i++) {
        if (date < 10) {
            if (month < 9) {
                dateArr.push(year + '-0' + (month + 1) + '-0' + date);
            } else {
                dateArr.push(year + '-' + (month + 1) + '-0' + date);
            }
        } else {
            if (month < 9) {
                dateArr.push(year + '-0' + (month + 1) + '-' + date);
            } else {
                dateArr.push(year + '-' + (month + 1) + '-' + date);
            }
        }
        $(dateList).eq(i).text(date);
        $(dayList).eq(i).text(dayArr[i]);
        date++;
        if (date > days) {
            date = date - days;
            month++;
            if (month > 11) {
                month = 0;
                year++;
            }
        }
    }
});
//得到数据和日期做比较
$(function () {
    var listArr = [];
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicBooking.action',
        cache: false,
        data: {
            action: "getClinicDoctorInfo","userInfo.id":userId
            //"doctorInfo.id":38//得到单个医生信息传此参数，医生列表不需要传参
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                var isSession=sessionStorage.getItem('session');
                if(isSession=='yes'){
                    return false;
                }
       
            }
            else if (result.mes == "成功") {
                var list = result.doctorInfoFoList;
                for (var i = 0, len = list.length; i < len; i++) {
                    var doctor = list[i].doctorInfo;
                    if (doctor.id == serDocId) {
                        listArr = list[i].majorNameList;
                        var preHtml = '';
                        for (var x = 0, le = listArr.length; x < le; x++) {
                            preHtml += '<span>' + listArr[x] + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
                        }
                        $('.preDirection').html(preHtml);
                        return false;
                    }
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);

        }
    });
    var addressArr = [];
    var addressList;
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicBooking.action',
        cache: false,
        data: {
            action: "getDoctorMorePracticeList",
            "doctorInfo.id": serDocId,
            "doctorMorePractice.doctorMorePracticeOrgInfo.hospitalBasicInfo.id":orgId,"userInfo.id":userId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                var isSession=sessionStorage.getItem('session');
                if(isSession=='yes'){
                    return false;
                }
     
            }
            else if (result.mes == "成功") {
                var list = result.doctorMorePracticeFoList;
                var doctor = result.doctorInfo;
                $('#doctorMessage').html('' +
                '<span id="doctorName">' + doctor.doctorName + '</span>'
                //'<br/><span id="hospital">' + doctor.doctorType + '</span>'
                );
                $('.description>img').prop('src', hostBG + 'images/doctorFaceIcon/' + doctor.doctorImage);
                $('#description').html(doctor.doctorComment);
                $('.doctorLevel').html('<li>' + doctor.department + '</li><li>' + doctor.doctorTitle + '</li><li>' + doctor.clinicalExperience + '年以上</li>');
                for (var i = 0; i < 14; i++) {
                    for (var j = 0, len = list.length; j < len; j++) {
                        if (dateArr[i] == list[j].clinicDate) {
                            recentArr.push(i);
                            addressList = list[j].doctorMorePracticeList;
                            addressArr.push(addressList);
                            for(var k= 0,le=addressList.length;k<le;k++){
                                if (addressList[k].clinicOrgType == 0) {
                                    $('#recentTime>li').eq(i).addClass('doctorAppoint');
                                } else {
                                    $('#recentTime>li').eq(i).addClass('areaAppoint');
                                }
                            }
                        }
                    }
                }
            }
            $('#recentTime>li').eq(recentArr[0]).addClass('activeAppoint');
            for (var ii = 0; ii < addressArr.length; ii++) {
                if (ii == 0) {
                    for (var mm = 0; mm < addressArr[ii].length; mm++) {
                        $('#addressContainer').append('<div index="' + recentArr[ii] + '" class="address"> ' +
                        '<div class="addressImg">' +
                        '<img style="width: 20px" src="'+hostMain+'images/images_icon/address.png" alt=""/>' +
                        '</div> <div class="addressDes"> <div>' +
                        ' <div class="ssss">' + addressArr[ii][mm].clinicAddress +
                        ' </div> </div> </div> </div>');
                    }
                } else {
                    for (var jj = 0; jj < addressArr[ii].length; jj++) {
                        $('#addressContainer').append('<div index="' + recentArr[ii] + '" class="address" style="display: none"' +
                        '><div class="addressImg">' +
                        '<img src="'+hostMain+'images/images_icon/address.png" alt=""/>' +
                        '</div> <div class="addressDes"> <div>' +
                        '<div class="ssss">' + addressArr[ii][jj].clinicAddress +
                        '</div></div></div></div>');
                    }
                }
            }
            if (list.length == 0) {
                $('#footer p').html('暂无服务').addClass('dark_button').removeAttr('onclick');
            }
        },
        error: function () {
            //alert('you are false');
        }
    });
    $('#recentTime>li').click(function () {
        if ($(this).prop('class')) {
            var index = $(this).attr('index');
            $('#addressContainer>.address').css({display: 'none'});
            $('#addressContainer>.address[index=' + index + ']').css({display: 'block'});
            $('#recentTime>li').removeClass('activeAppoint');
            $(this).addClass('activeAppoint');
        }
    });

});
//医生简介多余字数处理
$(function () {
    var wordStr = $("#description").html();
    $("#description").html(" ");
    $("#description").append("<span id='doctorMessageContent'></span><a href='#' id='openDoctorMessage'></a>");
    $('#doctorMessageContent').html(wordStr.substring(0, 100));
    $('#openDoctorMessage').html(wordStr.length > 100 ? "...展开" : "");
    $('#openDoctorMessage').click(function () {
        if ($('#openDoctorMessage').html() == "...展开") {
            $('#openDoctorMessage').html("收起");
            $('#doctorMessageContent').html(wordStr);
        } else {
            $('#openDoctorMessage').html("...展开");
            $('#doctorMessageContent').html(wordStr.substring(0, 100));
        }
    });
});
//添加日期事件
//约Ta门诊跳转页面
function goTo() {
    var isSession=sessionStorage.getItem('session');
    if(isSession=='yes'){
        window.location.href='yjh_appointmentPatient.html?' + serDocId + '&&' + $('.activeAppoint>.weekdate').text()+'&&'+orgId;
    }else{
        window.location.href = 'appointmentPatient.html?' + serDocId + '&&' + $('.activeAppoint>.weekdate').text()+'&&'+orgId;
    }
};
//分享医生
function share(e){
    e.stopPropagation();
    $('#share').show();
}
window.document.onclick=function(){
    $('#share').hide();
}
//分享接口
//以下是分享接口
var timestamp = '';
var nonceStr = '';
var signature = '';
var jsapi_ticket = '';
var string_1 = '';

$(function(){
    timestamp = getTimeStamp();
    nonceStr = getNonceStr();
    jsapi_ticket = getJsApi();
    string_1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+window.location.href;
    signature = hex_sha1(string_1);
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function(){
        wx.checkJsApi({
            jsApiList: ['onMenuShareTimeline'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function(res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            }
        });
        var share_title = '康优医生'+$('#doctorName').html();;
        var share_url = hostMain+"sharePointPatient.html?"+serDocId;
        var share_img = window.location.origin+"/main/images/kybaby.png";
        var desc = '专业医生,上门与门诊服务,给襁褓中孩子最好的礼物!';
        wx.onMenuShareTimeline({
            title: share_title, // 分享标题
            link: share_url, // 分享链接
            imgUrl: share_img,// 分享图标
            success: function () {
                ale('分享成功！');
                $('#share').hide();
            },
            cancel: function () {
            },error:function(result){
                alert(result);
            }
        });
        wx.onMenuShareAppMessage({
            title: share_title, // 分享标题
            desc: desc, // 分享描述
            link: share_url, // 分享链接
            imgUrl: share_img, // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                ale('分享成功！');
                $('#share').hide();
            },
            cancel: function () {
            },error:function(){
            }
        });
        wx.onMenuShareQQ({
            title: share_title, // 分享标题
            desc: desc, // 分享描述
            link: share_url, // 分享链接
            imgUrl: share_img, // 分享图标
            success: function () {
                ale('分享成功！');
                $('#share').hide();
            },
            cancel: function () {
            }
        });
        wx.onMenuShareQZone({
            title: share_title, // 分享标题
            desc: desc, // 分享描述
            link: share_url, // 分享链接
            imgUrl: share_img, // 分享图标
            success: function () {
                ale('分享成功！');
                $('#share').hide();
            },
            cancel: function () {
            }
        });
    });
});
function getTimeStamp(){
    var timestamp=new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    return timestampstring;
}
function getNonceStr(){
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    var noceStr = "";
    for (var i = 0; i < 32; i++) {
        noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return noceStr;
}
function getJsApi(){
    var jsApiTicket='';
    $.ajax({
        type : 'post',
        cache:false,
        
        url : host+'../wx/getJsApi.action',
        data : {
            action : "getJsApiTicket"
        },
        success : function(result) {
            jsApiTicket=result.jsApiTicket;
        },
        error: function () {
          
        }
    });
    return jsApiTicket;
}

