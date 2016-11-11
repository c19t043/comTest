var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var monthBiao = month;
var day = today.getDay();//星期
var date = today.getDate();
var dateBiao=date;
var tod = '';
var prevDay;
var getDateArr = [];
var startArr = [];
var endArr = [];
var peopleArr = [];
var addressArr = [];
var idArr = [];
var doctorXAddress;
var doctorHospitalAddress;
//获取数据
$(function () {
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'doctorClinic.action',
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
        }
    });

    sessionStorage.clear();
    sessionStorage.removeItem('id');
    getDateArr = [];
    $.ajax({
        type: 'post',
        async: false,
        url: urlWay.clinicHost + 'doctorClinic.action',
        data: {action: "getMorePractice"},
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var data=result.doctorServiceTypeList;
                if(data.length==0||data==null){
                    $('#date,#container,#footer').hide();
                    $('#description').html('<p style="margin-top: 50px;font-size: 20px;color: #505050">您暂时未开通工作单位门诊服务<br/>,如需开通，请在"医生认证"<br/>功能中重新提交资料</p>')
                }else{
                    var isTrue=false;
                    for(var i= 0,len=data.length;i<len;i++){
                        if(data[i].serviceTypeName=='工作单位'){
                            isTrue=true;
                        }
                    }
                    if(!isTrue){
                        $('#date,#container,#footer').hide();
                        $('#description').html('<p style="margin-top: 50px;font-size: 20px;color: #505050">您暂时未开通工作单位门诊服务<br/>,如需开通，请在"医生认证"<br/>功能中重新提交资料</p>')
                    }
                }
            }
        },
        error: function () {
            alert('you are false');
        }
    });

    $.ajax({
        type: 'post',
        async: false,
        url: urlWay.clinicHost + 'doctorClinic.action',
        data: {
            action: "getDoctorMorePracticeList",
            "doctorMorePractice.clinicOrgType":0
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list = result.doctorMorePracticeList;
                doctorXAddress = result.hospitalBasicInfo.address;
                doctorHospitalAddress=result.hospitalBasicInfo.hospitalLname;
//                $('#workAddress span').text(result.doctorInfo.doctorEmployer);
                $(list).each(function (index) {
                    var dataList = $(list)[index];
                    idArr.push(dataList.id);
                    getDateArr.push(dataList.clinicDate);
                    startArr.push(dataList.clinicBeganTime);
                    endArr.push(dataList.clinicEndTime);
                    peopleArr.push(dataList.canClinicNum);
                    addressArr.push(dataList.clinicAddress);
                });
            }
        },
        error: function () {
            alert('you are false');
        }
    });
});
function goToOrg() {
    window.location.href = 'institution.html?' + doctorXAddress
}
//进入日期
$(function () {
    var getStorage = sessionStorage.getItem('time');
    if (getStorage) {
        year = parseInt(getStorage.split('-')[0]);
        month = parseInt(getStorage.split('-')[1]) - 1;
        date = parseInt(getStorage.split('-')[2]);
        sessionStorage.clear();
    }
    changeDay();
    spe();
    addActive();
    isPoint();
});
//生成日历的方法并显示当前所在月份的日历
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
        prevMonth = month-1;
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
    var monthcha=monthBiao-month;
    if(monthcha==0||monthcha==-1){
        date = parseInt($('.active').html());
        if (m == 'prevMonth') {
            if (month == 0) {
                month = 11;
                year--;
            } else {
                month--;
            }
            $('#prevDate').removeAttr('onclick');
            $('#nextDate').attr('onclick',"changeDate('nextDate')");
        } else {
            if (month == 11) {
                month = 0;
                year++;
            } else {
                month++;
            }
            $('#nextDate').removeAttr('onclick');
            $('#prevDate').attr('onclick',"changeDate('prevMonth')");
        }
        changeDay();
        spe();
        addActive();
        isPoint();
    }

}
//给医生可约时间日期上存放数据
function spe() {
    var list = $('#content div');
    $(list).each(function () {
        var dayStr = $(this).attr('data-time');
        for (var i = 0, l = getDateArr.length; i < l; i++) {
            if (dayStr == getDateArr[i]) {
                $(this).attr('data-id',idArr[i]);
                $(this).append('<br>约').css({lineHeight: '20px', fontSize: '14px'});
                $(this).data({
                    'time': getDateArr[i],
                    'startTime': startArr[i],
                    'endTime': endArr[i],
                    'people': peopleArr[i],
                    'address': addressArr[i],
                    'id': idArr[i],
                    'doctorXAddress':doctorXAddress,
                    'doctorHospitalAddress':doctorHospitalAddress
                });
                return true;
            }
        }
    });
    $('#content div').eq(tod + date - 2).addClass('active');
}
//通过判定是编辑还是添加来设置缓存数据
function isPoint() {
    var con = $('.active').text();
    if (isNaN(con)) {
        $('#footer>p').text('编辑');
        sessionStorage.clear();
        sessionStorage.setItem('time', $('.active').data().time);
        sessionStorage.setItem('startTime', $('.active').data().startTime);
        sessionStorage.setItem('endTime', $('.active').data().endTime);
        sessionStorage.setItem('people', $('.active').data().people);
        sessionStorage.setItem('address', $('.active').data().address);
        sessionStorage.setItem('id', $('.active').data().id);
        sessionStorage.setItem('doctorXAddress', doctorXAddress);
        sessionStorage.setItem('doctorHospitalAddress',doctorHospitalAddress);
    } else {
        $('#footer>p').text('添加');
        sessionStorage.clear();
        sessionStorage.setItem('time', $('.active').data().time);
        sessionStorage.setItem('startTime', $('.active').data().startTime);
        sessionStorage.setItem('endTime', $('.active').data().endTime);
        sessionStorage.setItem('people', $('.active').data().people);
        sessionStorage.setItem('address', $('.active').data().address);
        sessionStorage.setItem('id', $('.active').data().id);
        sessionStorage.setItem('doctorXAddress', doctorXAddress);
        sessionStorage.setItem('doctorHospitalAddress',doctorHospitalAddress);
    }
}
//选择时间并判定医生时候可约
function addActive() {
    $('#content div').click(function () {
        var monthcha=monthBiao-month;
        $('#content div').removeClass('active');
        $(this).addClass('active');
        if(monthcha==0){
            if ($(this).hasClass('gray')) {
                var val = parseInt($(this).text());
                if (val <15) {
                    changeDate('nextMonth');
                }
            }
        }else if(monthcha==-1||monthcha==11){
            if ($(this).hasClass('gray')) {
                var val = parseInt($(this).text());
                if (val > 20) {
                    changeDate('prevMonth');
                }
            }
        }
        if (isNaN($(this).text())) {
            $('#footer>p').text('编辑');
            sessionStorage.clear();
            sessionStorage.setItem('time', $(this).data().time);
            sessionStorage.setItem('startTime', $(this).data().startTime);
            sessionStorage.setItem('endTime', $(this).data().endTime);
            sessionStorage.setItem('people', $(this).data().people);
            sessionStorage.setItem('address', $(this).data().address);
            sessionStorage.setItem('doctorXAddress', doctorXAddress);
            sessionStorage.setItem('doctorHospitalAddress',doctorHospitalAddress);
            sessionStorage.setItem('id', $(this).data().id);
        } else {
            sessionStorage.clear();
            sessionStorage.setItem('time', $(this).data().time);
            sessionStorage.setItem('startTime', $(this).data().startTime);
            sessionStorage.setItem('endTime', $(this).data().endTime);
            sessionStorage.setItem('people', $(this).data().people);
            sessionStorage.setItem('address', $(this).data().address);
            sessionStorage.setItem('doctorXAddress', doctorXAddress);
            sessionStorage.setItem('doctorHospitalAddress',doctorHospitalAddress);
            sessionStorage.setItem('id', $(this).data().id);
            $('#footer>p').text('添加');
        }
    });
}
//
function editOutpatientTime() {
    var month1=parseInt($('.active').attr('data-time').split('-')[1]);
    var monthcha1=monthBiao+1-month1;
    if(parseInt($('.active').text())<dateBiao){
        if(monthcha1==0||monthcha1==1||monthcha1==-11){
            ale('不能设置过期时间');
            return false;
        }
    }else {
        if(monthcha1==1||monthcha1==-11){
            ale('不能设置过期时间');
            return false;
        }
    }
    var txt=$('.active').attr('data-id');
    window.location.href = 'editOutpatientTime.html?' + $('#footer>p').text()+'&&'+txt;
}
