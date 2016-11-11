/**
 * Created by windows on 2016/6/28.
 */
/**
 * Created by windows on 2016/3/22.
 */

var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var day = today.getDay();//星期
var date = today.getDate();
var days;
var dateArr = [];
var canArr = [];
var doctorArr = [];
var fdServicePackageId = decodeURIComponent(window.location.search.substring(1).split('&')[0]);
//得到今后一周的日期
$(function () {
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
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

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
    var dayArr = [ '今天','明天', '后天'];
    var daySum = day + 3;
    for (var j = 0; j < 11; j++) {
        if (daySum > 6) {
            daySum = daySum - 7;
        }
        if (daySum == 0) {
            dayArr.push('日');
        }
        else if (daySum == 1) {
            dayArr.push('一');
        }
        else if (daySum == 2) {
            dayArr.push('二');
        }
        else if (daySum == 3) {
            dayArr.push('三');
        }
        else if (daySum == 4) {
            dayArr.push('四');
        }
        else if (daySum == 5) {
            dayArr.push('五');
        }
        else if (daySum == 6) {
            dayArr.push('六');
        }
        daySum++;
    }
    var dateList = $('.weekdate');
    var dayList = $('.weekday');
    for (var i = 0; i < 14; i++) {
        if (date< 10) {
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
$(function () {
    $.ajax({
        type:'post',
        url:familyDoctorHost+'familyDoctorManage.action',
        cache:false,
        async:false,
        data:{
            action:"fdClinicDate",
            "fdServicePackage.id":fdServicePackageId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录');
                window.location.href="login.html";
            }
            else if (result.mes == '成功') {
                var doctorMorePracticeFoList=result.doctorMorePracticeFoList;
                if(doctorMorePracticeFoList!=null && doctorMorePracticeFoList.length!=0){
                    familyDoctorPatient.invokeAddress=result.hospitalBasicInfo.invokeAddress;
                    familyDoctorPatient.elseOrgID=result.hospitalBasicInfo.elseOrgID;
                    for(var a= 0,le=doctorMorePracticeFoList.length;a<le;a++){
                        for(var j= 0;j<14;j++){
                            if(doctorMorePracticeFoList[a].clinicDate==dateArr[j]){
                                $('#recentTime>li').eq(j).addClass('isCanUse').attr('data-index',j);
                                canArr.push(j);
                                doctorArr.push(doctorMorePracticeFoList[a].doctorMorePracticeList);
                            }
                        }
                    }
                        //$('#chooseAppointmentTime').prepend('<p>就诊地址<br/></p><p style="color: #505050"><img style="width: 16px;margin-right:10px" src="images/images_icon/address.png" alt=""/>苏坡社区卫生服务中心（四川省成都市青羊区中鹏西路2号-老院区）</p>');
                    familyDoctorPatient.chooseDate($('.isCanUse').eq(0));
                } else{
                    $('#footer p').html('暂无服务').addClass('dark_button').removeAttr('onclick');
                }
            }
        },
        error: function () {
            layer();
        }
    });
    $('.isCanUse').click(function () {//选择日期
        familyDoctorPatient.chooseDate($(this));
    });
    //$('.doctor_list').click(function () {//选择医生
    //    $('.doctor_appointment>p').removeClass('btn_light');
    //    $(this).find('.doctor_appointment>p').addClass('btn_light');
    //    familyDoctorPatient.chooseDoctor($(this).attr('data-doctorMorePracticeList-id'));
    //});
});
var familyDoctorPatient={
    invokeAddress:null,
    //elseOrgID:null,
    saveFamDocOrder: function () {//确定
        var timeText=$('.time_choose>p').text();
        var doctorId=$('.btn_light').parent().parent().attr('data-doctor-id');
        var address=$('.btn_light').parent().parent().attr('data-doctor-clinicAddress');
        var clinicDate=$('.btn_light').parent().parent().attr('data-doctor-clinicDate');
        if(timeText=='' ||timeText==null ||timeText==undefined){
            ale('请先选择一个时间');
            return false;
        }else{
            var answer=confirm('确定预约吗？');
            if(answer==true){
                var mj=15;
                var isPlus='N';
                if(timeText.indexOf('加号')>-1){
                    timeText=$('.time_choose').attr('data-time');
                    isPlus='Y';
                }
                var next = timeText.split(':');
                var h = parseInt(next[0]);
                var m = parseInt(next[1]) + mj;
                if (m >= 60) {
                    m = m - 60;
                    h = h + 1;
                    if (m < 10) {
                        m = '0' + m;
                    }
                    if (h < 10) {
                        h = '0' + h;
                    }
                }
                else {
                    if (m < 10) {
                        m = '0' + m;
                        if(h<10){
                            h="0"+h;
                        }
                    }else{
                        if(h<10){
                            h='0'+h;
                        }
                    }
                }
                var textEndTime = h + ':' + m;
                //console.log(isPlus);
                //return false;
                if(familyDoctorPatient.invokeAddress=='中联'){
                    $.ajax({
                        type: 'post',
                        url: spInterfaceService + 'saveOrUpdatePidiatricsClinicOrder.action',
                        cache: false,
                        async: false,
                        data: {
                            "doctorInfo.id": doctorId,
                            "orderInfoClinic.appointmentDate": clinicDate,
                            "orderInfoClinic.appointmentBeganTime": timeText,
                            "orderInfoClinic.appointmentEndTime": textEndTime,
                            "orderInfoClinic.clinicAddress": address,
                            "orderInfoClinic.clinicOrgType": 1,
                            "orderInfoClinic.isPlus": isPlus,
                            "orderInfoClinic.id":''
                        },
                        success: function (result) {
                            if(result.mess=='成功'){
                                window.location.href="hospital_pay_order.html?p="+result.orderInfoClinic.id;
                            }else{
                                ale(result.mess);
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                        }
                    });

                }else{
                    $.ajax({
                        type:'post',
                        url:familyDoctorHost+'familyDoctorManage.action',
                        cache:false,
                        async:false,
                        data:{
                            action:"saveOrUpdateClinicOrder",
                            "doctorInfo.id": doctorId,
                            "orderInfoClinic.appointmentDate": clinicDate,
                            "orderInfoClinic.appointmentBeganTime": timeText,
                            "orderInfoClinic.appointmentEndTime": textEndTime,
                            "orderInfoClinic.clinicAddress": address,
                            "orderInfoClinic.clinicOrgType": 1,
                            "orderInfoClinic.isPlus": isPlus
                        },
                        success:function(result){
                            if (result.mes == '请登录') {
                                ale('请登录');
                                window.location.href="login.html";
                            }else if (result.mes != '成功') {
                                ale(result.mes);
                            }else if (result.mes == '成功') {
                                window.location.href="myAppointment.html";

                            }
                        },
                        error: function () {
                            layer();
                        }
                    });
                }
            }
        }
    },
    chooseDate: function (ele) {//选择日期
        var index=$(ele).attr('data-index');
        $(ele).addClass('activeAppoint').siblings().removeClass('activeAppoint');
        $('#recentTime').scrollLeft((index-3)*(0.13)*$('#recentTime').width());
        for(var i= 0,len=canArr.length;i<len;i++){
            if(index==canArr[i]){
                var html='';
                for(var j=0;j<doctorArr[i].length;j++){
                    html+='<div class="doctor_list" data-doctor-clinicAddress="'+doctorArr[i][j].clinicAddress+'" data-doctor-clinicDate="'+doctorArr[i][j].clinicDate+'" data-doctor-id="'+doctorArr[i][j].doctorInfo.id+'" data-doctorMorePracticeList-id="'+doctorArr[i][j].id+'"> ' +
                    '<div class="doctor_name"> ' +
                    '<img src="'+hostBG+'images/doctorFaceIcon/'+doctorArr[i][j].doctorInfo.doctorImage+'" alt=""/> ' +
                    '<p>'+doctorArr[i][j].doctorInfo.doctorName+'</p> ' +
                    '</div> ' +
                    '<div class="doctor_message"> ' +
                    '<p>'+doctorArr[i][j].doctorInfo.doctorEmployer+'</p> ' +
                    '<p>'+doctorArr[i][j].doctorInfo.department+' | '+doctorArr[i][j].doctorInfo.doctorTitle+' | '+doctorArr[i][j].doctorInfo.clinicalExperience+'年临床经验</p>' +
                    '<p class="color-gray">'+doctorArr[i][j].clinicBeganTime+' - '+doctorArr[i][j].clinicEndTime+'</p> ' +
                    '</div> ' +
                    '<div class="doctor_appointment"> ' +
                    '<p></p> ' +
                    '</div> ' +
                    '</div>';
                }
                $('#familyDoctorList').html(html);
            }
        }
        $('.doctor_appointment>p').eq(0).addClass('btn_light');
        $('.doctor_list').click(function () {//选择医生
            $('.doctor_appointment>p').removeClass('btn_light');
            $(this).find('.doctor_appointment>p').addClass('btn_light');
            familyDoctorPatient.chooseDoctor($(this).attr('data-doctorMorePracticeList-id'));
            familyDoctorPatient.addAddress($(this).attr('data-doctor-clinicAddress'));
        });
        familyDoctorPatient.addAddress($('.doctor_list').eq(0).attr('data-doctor-clinicAddress'));
        familyDoctorPatient.chooseDoctor($('.doctor_list').eq(0).attr('data-doctorMorePracticeList-id'));
    },
    addAddress: function (address) {
        $('#addressContainer').html('<div class="address"> ' +
        '<div class="addressImg">' +
        '<img src="'+hostMain+'images/images_icon/address.png" alt=""/>' +
        '</div> <div class="addressDes"> <div>' +
        ' <div class="ssss">' + address+
        ' </div> </div> </div> </div>');
    },
    chooseDoctor: function (id) {//选择医生
        $.ajax({
            type:'post',
            url:familyDoctorHost+'familyDoctorManage.action',
            cache:false,
            async:false,
            data:{
                action:"getFdClinicTimeSegmentList",
                "doctorMorePractice.id":id
            },
            success:function(result){
                if (result.mes == '请登录') {
                    ale('请登录');
                    window.location.href="login.html";
                }
                else if (result.mes == '成功') {
                    var html='';
                    var segment_html='';
                    var doctorClinicTimeSegmentList=result.doctorClinicTimeSegmentList;
                    if(doctorClinicTimeSegmentList!=null){
                        for(var i= 0,len=doctorClinicTimeSegmentList.length;i<len;i++){
                            segment_html+=doctorClinicTimeSegmentList[i].isCanUse;
                            if(doctorClinicTimeSegmentList[i].isDel != 'Y'){
                                html+='<li data-time-id="'+doctorClinicTimeSegmentList[i].id+'" class="'+doctorClinicTimeSegmentList[i].isCanUse+'">' +
                                '<p>'+doctorClinicTimeSegmentList[i].segment+'</p></li>';
                            }
                        };
                    }
                    $('#time_list').html(html);
                    if(segment_html.indexOf("Y")==-1){
                        if(result.doctorMorePractice.isAddClinic>0){
                            $('#time_list').append('<li data-time-id="'+doctorClinicTimeSegmentList[0].id+'" data-time="'+doctorClinicTimeSegmentList[doctorClinicTimeSegmentList.length-1].segment+'" class="Y">' +
                            '<p>加号'+result.doctorMorePractice.isAddClinic+'</p></li>');
                        }else{
                            $('#time_list').append('<li data-time-id="'+doctorClinicTimeSegmentList[0].id+'" class="N">' +
                            '<p>加号0</p></li>');
                        }
                    }
                    familyDoctorPatient.chooseTime();
                }
            },
            error: function () {

            }
        });
    },
    chooseTime: function () {//选择时间
        $('#time_list>li').click(function () {//选择时间
            if($(this).hasClass('Y')){
                $(this).addClass('time_choose').siblings().removeClass('time_choose');
            }else{
                ale('该时间不可用，请选择其他时间');
            }
        });
    }
}
