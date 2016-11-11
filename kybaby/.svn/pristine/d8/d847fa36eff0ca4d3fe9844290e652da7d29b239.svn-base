/**
 * Created by windows on 2016/3/21.
 */
var orgId;
var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var monthBiao = month;
var day = today.getDay();//星期
var date = today.getDate();
var dateBiao = date;
var tod = '';
var prevDay;
var getDateArr = [];
var iscurrentUserIdentity='';
var isYueGuo='';
//    var freeday=['2016-02-29','2016-03-09','2016-03-10','2016-03-13','2016-03-19','2016-04-06','2016-04-16','2016-03-11','2016-03-12',
//        '2016-03-01','2016-03-02','2016-03-03','2016-03-04','2016-03-05','2016-03-06','2016-03-07','2016-03-08','2016-03-14','2016-03-15',
//        '2016-03-16','2016-03-17','2016-03-18','2016-03-20','2016-03-21','2016-03-22','2016-03-23','2016-03-24','2016-03-25','2016-03-26'];

$(function () {
    var packageId=window.location.search.substring(1).split('&')[0];
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getCurrentTime"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
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
                monthBiao = month;
                dateBiao = date;
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserInitInoculationInfo",
            "fdServicePackage.id":packageId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }else if(result.mes=='成功'){
                var list=result.appointmentInitInfo;
                isYueGuo=list.flagStatus;
                var data=list.vaccineInfo;
                var data_id=0;
                if(list.hospitalBasicInfo!=null){
                    orgId=list.hospitalBasicInfo.id;
                    $('.listaddress span').html(list.hospitalBasicInfo.hospitalLname);
                }
                if(list.organInoculationOpenResources != null && list.organInoculationOpenResourcesDetail!=null){
                    if(list.organInoculationOpenResources.id==null){
                        data_id=-1;
                    }else{
                        data_id=list.organInoculationOpenResources.id;
                    }
                    $('.listdate span').html(list.organInoculationOpenResources.openDate).attr('date-id',data_id);
                    $('.listtime span').html(list.organInoculationOpenResourcesDetail.openStartTime+'~'+list.organInoculationOpenResourcesDetail.openEndTime).attr('time-id',list.organInoculationOpenResourcesDetail.id);
                }
                if(data!=null){
                    $('#next').html('<div class="personal_list" style="padding: 5px 0px"> <div class="list" onclick="window.location.href=\'orgservice_vaccinedetail.html?'+data.id+'\'"> <p class="personal_left1">' +
                    data.vaccineName+'&nbsp;&nbsp;&nbsp;&nbsp;'+data.inoculumNumber+'&nbsp;&nbsp;&nbsp;&nbsp;<span ' +
                    'class="spe">'+data.vaccineType+'</span><br/> <img style="margin: -2px 3px 0 0;width: 17px;display: inline-block;" ' +
                    'src="images/images_family_doctor/arrow_left.png" alt=""/>'+data.diseasePrevention+' </p> ' +
                    '<p class="personal_right" style="height: 25px"></p> ' +
                    '</div> </div> <p class="gray_s"></p>');
                }
            }
        },
        error: function () {
            layer();
        }
    });

    //取消
    $('#cancel_button .login_button').click(function () {
        $('#coverall').hide();
    });
    $('.cancal').click(function () {
        $('#covertime').hide();
    });
    //是否阅读用户协议
    $('#user_xieyi').click(function () {
        $(".instructions").show();
    });
    $('.instructions>#layer_closed').click(function () {
        $(".instructions").hide();
    });
    //$("#cbUserAgreement").click(function () {
    //    $(this).toggleClass('cbUserAgreement');
    //    if($(this).hasClass('cbUserAgreement')){
    //        $(this).prop('src','images/org_notchecked.png');
    //    }else{
    //        $(this).prop('src','images/org_checked.png');
    //    };
    //});
    $("#cbUserAgreement").click(function () {
        $(this).parent("#yuyuexuzhi").toggleClass('cbUserAgreement');
    });
//  立即预约
    $("#footer p").click(function () {
        var time_id=$('.listtime span').attr('time-id');
        var date_id=$('.listdate span').attr('date-id');
//            var order_id='';
        if(isYueGuo=='未完成预约'){
            ale('您已约过，请到订单处取消后再约');
            return false;
        }
        if($("#yuyuexuzhi").hasClass('cbUserAgreement')){
            ale('请先阅读预约说明');
            return false;
        }
        if(date_id==-1){
            ale('该日期没有开放，请选择其他日期');
            return false;
        }
        var d=$('.listdate span').html();
        var t=$('.listtime span').html();
        var mydate=new Date();
        var myyear=mydate.getFullYear();
        var mymonth=mydate.getMonth()+1;
        var mydat=mydate.getDate();
        //var myhours=mydate.getHours();
        //var myminutes=mydate.getMinutes();
        if(mymonth<10){
            mymonth='0'+mymonth;
        }
        if(mydat<10){
            mydat='0'+mydat;
        }
        //if(myhours<10){
        //    myhours='0'+myhours;
        //}
        //if(myminutes<10){
        //    myminutes='0'+myminutes;
        //}
        var mydatestr=myyear+''+mymonth+''+mydat;
        var datestr= d.replace('-','').replace('-','');
        if(datestr<=mydatestr){
            ale('请选择明天及以后的时间');
            return false;
        }
//            console.log(mydatestr);
//            console.log(datestr);
        $.ajax({
            type: 'post',
            url: hostOrgbusiness + 'vaccineManage.action',
            cache: false,
            async: false,
            data: {
                action: "saveAppointment",
                "userInoculationAppointmentInfo.organInoculationOpenResources.id":date_id,
                "userInoculationAppointmentInfo.organInoculationOpenResourcesDetail.id":time_id,
                "fdServicePackage.id":packageId
            },
            success: function (result) {
                if(result.mes=='成功'){
                    var order_id=result.userInoculationAppointmentInfo.id;
                    window.location.href = 'orgservice_message.html?'+order_id;
                } else if(result.mes!='成功'){
                    ale(result.mes);
                    return false;
                }
            },
            error: function () {
                layer();
            }
        });
    });
});
//显示接种时间
function showTime(){
    var id=$('.listdate span').attr('date-id');
    if(id==undefined){
        ale('请先选择日期');
        return false;
    }
    if(id==-1){
        ale('该日期没有开放，请选择其他日期');
        return false;
    }
    timeperiod(id);
    $('#covertime').show();
}
//选择时间
function choosehaoyuan(li) {
    var num=parseInt($(li).find('p').text().substring(1));
    if(num==0){
        ale('该时间段已无号源，请选择其他时间段');
        return false;
    }
    $('.listtime span').html($(li).find('div').text()).attr('time-id',$(li).attr('time-id'));
    $('#covertime').hide();
}
//显示日期
function showdate() {
    $('#coverall').show();
    changeDay();
    spe();
    addActive();
    meterfreeday();
}
//改变日期
function changeDay() {
    var newDate = new Date(year, month, 1);
    var firstDay = newDate.getDay();
    tod = firstDay;
    $('#showDate').html(year + '年' + (month + 1) + '月');
    var kong = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14];
    var prevDay1 = [25, 26, 27, 28, 29, 30, 31];
    var prevDay2 = [24, 25, 26, 27, 28, 29, 30];
    var prevDay3 = [23, 24, 25, 26, 27, 28, 29];
    var prevDay4 = [22, 23, 24, 25, 26, 27, 28];
    var pre = [];
    var days;
    var prevYear;
    var nextYear;
    var monthStr;
    var prevMonth;
    var prevMonth1;
    var nextMonth;
    if (month == 0) {
        prevMonth = 11;
        prevMonth1 = 12;
        prevYear = year - 1;
        nextYear = year;
        nextMonth = '02';
        monthStr = '01';
    } else if (month == 11) {
        prevMonth = 10;
        prevMonth1 = 11;
        nextYear = year + 1;
        prevYear = year;
        nextMonth = '01';
        monthStr = 12;
    } else {
        prevMonth = month - 1;
        prevMonth1 = month;
        prevYear = year;
        nextYear = year;
        nextMonth = month + 2;
        if (month > 8) {
            monthStr = month + 1;
        } else {
            monthStr = '0' + (month + 1);
        }
        if (prevMonth1 < 10) {
            prevMonth1 = '0' + prevMonth1;
        }
        if (nextMonth < 10) {
            nextMonth = '0' + nextMonth;
        }
    }
    if (prevMonth == 0 || prevMonth == 2 || prevMonth == 4 || prevMonth == 6 || prevMonth == 7 || prevMonth == 9 || prevMonth == 11) {
        prevDay = 31;
        pre = prevDay1;
    } else if (prevMonth == 3 || prevMonth == 10 || prevMonth == 5 || prevMonth == 8) {
        prevDay = 30;
        pre = prevDay2;
    } else {
        if (year % 4 != 0) {
            prevDay = 28;
            pre = prevDay4;
        }
        else {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    prevDay = 29;
                    pre = prevDay3;
                } else {
                    prevDay = 28;
                    pre = prevDay4;
                }
            } else {
                prevDay = 29;
                pre = prevDay3;
            }
        }
    }
    var html = '';

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
                    days = 29
                } else {
                    days = 28;
                }
            } else {
                days = 29
            }
        }
    }
    var ls=firstDay;
    if(firstDay==0){
        ls=7;
    }
    var _pre = pre.slice(8 - ls, 7);
    for (var j = 0, l = _pre.length; j < l; j++) {
        html += '<div class="gray" data-time="' + prevYear + '-' + prevMonth1 + '-' + _pre[j] + '">' + _pre[j] + '</div>';
    }
    for (var i = 0; i < days; i++) {
        if (i < 9) {
            html += '<div data-time="' + year + '-' + monthStr + '-0' + (i + 1) + '">' + (i + 1) + '</div>';
        }
        else {
            html += '<div data-time="' + year + '-' + monthStr + '-' + (i + 1) + '">' + (i + 1) + '</div>';
        }
    }
    var cha = 42 - _pre.length - days;
    for (var k = 0; k < cha; k++) {
        if (k < 9) {
            html += '<div class="gray" data-time="' + nextYear + '-' + nextMonth + '-0' + (k + 1) + '">' + kong[k] + '</div>';
        } else {
            html += '<div class="gray" data-time="' + nextYear + '-' + nextMonth + '-' + (k + 1) + '">' + kong[k] + '</div>';
        }
    }
    $('#content').html(html);
}
//选择日期
function changeDate(m) {
    var monthcha = monthBiao - month;
    if (monthcha == 0 || monthcha == -1) {
        if (m == 'prevMonth') {
            if (month == 0) {
                month = 11;
                year--;
            } else {
                month--;
            }
            $('#prevMonth').removeAttr('onclick');
            $('#nextMonth').attr('onclick', "changeDate('nextMonth')");
        } else {
            if (month == 11) {
                month = 0;
                year++;
            } else {
                month++;
            }
            $('#nextMonth').removeAttr('onclick');
            $('#prevMonth').attr('onclick', "changeDate('prevMonth')");
        }
        changeDay();
        spe();
        addActive();
        meterfreeday()
    }

}
//展示当天日期
function spe() {
//        var list = $('#content div');
//        $(list).each(function () {
//            var dayStr = $(this).attr('data-time');
//        });
//        $('#content div').eq(tod + date - 2).addClass('activespe');
}
//选中日期的时间段
function timeperiod(id){
    console.log(timeperiod);
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "openTimeDetail",
            "organInoculationOpenResourcesDetail.organInoculationOpenResources.id":id
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }else if(result.mes=='成功'){
                var list=result.organInoculationOpenResourcesDetailList;
                var html='';
                if(list!=null){
                    for(var i= 0,len=list.length;i<len;i++){
                        var num=list[i].generalSurplusNum;
                        if(iscurrentUserIdentity=='金卡'||iscurrentUserIdentity=='普卡'){
                            num=list[i].greenChannelSurplusNum;
                        }
                        html+='<li time-id="'+list[i].id+'" onclick="choosehaoyuan(this)">' +
                        '<p>剩'+num+'位</p><div>' +list[i].openStartTime
                        +'~'+list[i].openEndTime+'</div></li>';
                    }
                    $('#cover1 ul').html(html);
                }
            }
        },
        error: function () {
            layer();
        }
    });
    $('#covertime').show();
}
//选中日期
function addActive() {
    $('#content div').click(function () {
        if ($(this).hasClass('active')) {
            var data_time = $(this).attr('data-time');
            var data_id = $(this).attr('data-id');
            $('.listdate span').html(data_time);
            $('#coverall').hide();
            timeperiod(data_id);
            $('.listdate span').attr('date-id',data_id);
            return false;
//                $(this).addClass('activespe').siblings().removeClass('activespe');
        }
        var monthcha = monthBiao - month;
        if (monthcha == 0) {
            if ($(this).hasClass('gray')) {
                var val = parseInt($(this).text());
                if (val < 15) {
                    changeDate('nextMonth');
                }
            }
        } else if (monthcha == -1 || monthcha == 11) {
            if ($(this).hasClass('gray')) {
                var val = parseInt($(this).text());
                if (val > 20) {
                    changeDate('prevMonth');
                }
            }
        }
    });
}
function meterfreeday() {
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "openTime",
            "organInoculationOpenResources.hospitalBasicInfo.id": orgId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            } else if (result.mes == '成功') {
                var divlist = $('#content div');
                var list = result.organInoculationOpenResourcesList;
                if (list != null) {
                    for (var i = 0, len = list.length; i < len; i++) {
                        $(divlist).each(function () {
                            if ($(this).attr('data-time') == list[i].openDate) {
                                $(this).addClass('active').attr('data-id', list[i].id);
                                var txt=parseInt($(this).text());
                                if(iscurrentUserIdentity=='金卡'||iscurrentUserIdentity=='普卡'){
                                    $(this).html(txt+'<div>剩' + list[i].greenChannelSurplusNum+'号</div>');
                                }else{
                                    $(this).html(txt+'<div>剩' + list[i].generalSurplusNum+'号</div>');
                                }
                            }
                        });
                    }
                }
            }
        },
        error: function () {
            layer();
        }
    });
}

