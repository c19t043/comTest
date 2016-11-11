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
var orgId = decodeURIComponent(window.location.search.substring(1).split('&')[0]);
var packageId = decodeURIComponent(window.location.search.substring(1).split('&')[1]);
var isYuyue='';
//得到今后一周的日期
$(function () {
    if(packageId==undefined||packageId=="undefined"){
        packageId='';
    }
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
        type: 'post',
        url: hostOrgbusiness + 'orgBoManage.action',
        cache: false,
        async: false,
        data: {
            action: "initOrganChildcare",
            "hospitalBasicInfo.id":orgId,
            "organSetMealOrderId":packageId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
                return false;
            }
            var project = result.childcareProject;
            if(project!=null){
                $("#message").after("<div class='do_project' data-id='"+project.childcareProjectType.id+"' index='"+project.id+"'><div class='project_p'><p class='project_title'>本次应做项目</p><p class='times'>"
                    +project.projectTitle+"</p><p>"+project.projectContent+"</p></div><div class='arrow'><img src='images/prev_01.png'/></div></div>");
            }
            $('.org_image>img').prop('src','/kybabyBG/'+result.hospitalBasicInfo.showImgPath);
            $('.address').html(result.hospitalBasicInfo.hospitalLname);
            $('.org_address>div>span').html(result.hospitalBasicInfo.address);
            isYuyue=result.mes;
            var organChildcareOpenResourcesFoList=result.organChildcareOpenResourcesFoList;
            if(organChildcareOpenResourcesFoList!=null && organChildcareOpenResourcesFoList.length!=0){
                for(var a= 0,le=organChildcareOpenResourcesFoList.length;a<le;a++){
                    for(var j= 0;j<14;j++){
                        if(organChildcareOpenResourcesFoList[a].openDate==dateArr[j]){
                            $('#recentTime>li').eq(j).addClass('isCanUse').attr('data-index',j);
                            canArr.push(j);
                            doctorArr.push(organChildcareOpenResourcesFoList[a].organChildcareOpenResourcesList);
                        }
                    }
                }
                erbao.chooseDate($('.isCanUse').eq(0));
            } else{
                $('#footer p').html('暂无服务').addClass('dark_button').removeAttr('onclick');
            }
        },
        error: function () {
            layer();
        }
    });
    $(".do_project").on("click",function(){
        window.location.href="plan_project.html?"+$(this).attr("index")+"&"+$(this).attr("data-id");
    });
    $('.isCanUse').click(function () {//选择日期
        erbao.chooseDate($(this));
    });
    $('.doctor_list').click(function () {//选择医生
        $('.doctor_appointment>p').removeClass('btn_light');
        $(this).find('.doctor_appointment>p').addClass('btn_light');
        erbao.chooseDoctor($(this).attr('data-organchildcareopenresources-id'));
    });
});
var erbao={
    saveFamDocOrder: function () {//确定
        var timeText=$('.time_choose').attr('data-time-id');
        var doctorId=$('.btn_light').parent().parent().attr('data-doctorInfo-id');
        var organChildcareOpenResourcesId=$('.btn_light').parent().parent().attr('data-organChildcareOpenResources-id');
        if(timeText=='' ||timeText==null ||timeText==undefined){
            ale('请先选择一个时间');
            return false;
        }else{
            var answer=confirm('确定预约吗？');
            if(answer==true){
                //return false;
                //console.log(isPlus);
                //return false;
                var orderType='普通订单';
                if(packageId==undefined||packageId=="undefined"||packageId==""){
                    orderType='普通订单';
                }else{
                    orderType='机构套餐';
                }
                $.ajax({
                    type: 'post',
                    url: hostOrgbusiness + 'childCareChargeManage.action',
                    cache: false,
                    async: false,
                    data: {
                        action: "handleUserChildcareAppointmentInfo",
                        "userChildcareAppointmentInfo.hospitalBasicInfo.id":orgId,
                        "userChildcareAppointmentInfo.organChildcareOpenResources.id":organChildcareOpenResourcesId,
                        "userChildcareAppointmentInfo.organChildcareOpenResourcesDatail.id":timeText,
                        "userChildcareAppointmentInfo.status":'下一步',
                        "doctorInfo.id":doctorId,
                        "organSetMeatOrderId":packageId,
                        "userChildcareAppointmentInfo.orderType":orderType
                    },
                    success: function (result) {
                        if (result.mes == '请登录') {
                            ale('请登录', '24px');
                            window.location.href = "login.html";
                        }else if(result.mes!='成功'){
                            ale(result.mes);
                            return false;
                        }
                        else if(result.mes=='成功'){
                            window.location.href = "orgservice_pay_babypoint.html?"+result.userChildcareAppointmentInfo.id;
                        }
                    },
                    error: function () {
                        layer();
                    }
                });
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
                    if(doctorArr[i][j].doctorInfo==null) continue;
                    html+='<div class="doctor_list" data-organChildcareOpenResources-id="'+doctorArr[i][j].id+'" data-doctorInfo-id="'+doctorArr[i][j].doctorInfo.id+'"> ' +
                    '<div class="doctor_name"> ' +
                    '<p>'+doctorArr[i][j].doctorInfo.doctorName+'</p> ' +
                    '</div> ' +
                    '<div class="doctor_message"> ' +
                    '<p>'+doctorArr[i][j].doctorInfo.doctorType+'</p> ' +
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
            erbao.chooseDoctor($(this).attr('data-organchildcareopenresources-id'));
        });
        erbao.chooseDoctor($('.doctor_list').eq(0).attr('data-organchildcareopenresources-id'));
    },
    chooseDoctor: function (id) {//选择医生
        $.ajax({
            type:'post',
            url:familyDoctorHost+'fdChildCareManage.action',
            cache:false,
            async:false,
            data:{
                action:"getFdChildCareOpenDatail",
                "organChildcareOpenResources.id":id
            },
            success:function(result){
                if (result.mes == '请登录') {
                    ale('请登录');
                    window.location.href="login.html";
                }
                else if (result.mes == '成功') {
                    var html='';
                    var organChildcareOpenResourcesDatailList=result.organChildcareOpenResourcesDatailList;
                    if(organChildcareOpenResourcesDatailList!=null){
                        for(var i= 0,len=organChildcareOpenResourcesDatailList.length;i<len;i++){
                            if(organChildcareOpenResourcesDatailList[i].organChildcareOpenResources.timeDivisionNeed=='时间段'){
                                if(organChildcareOpenResourcesDatailList[i].isDel != 'Y'){
                                    html+='<li data-time-id="'+organChildcareOpenResourcesDatailList[i].id+'" class="'+organChildcareOpenResourcesDatailList[i].isCanUse+'"><p>'+organChildcareOpenResourcesDatailList[i].openStartTime+'-'+organChildcareOpenResourcesDatailList[i].openEndTime+'</p>' +
                                    '<div>剩余<span class="color-basic"> '+organChildcareOpenResourcesDatailList[i].generalSurplusNum+' </span>号</div> ' +
                                    '</li>';
                                }
                            }else{
                                if(organChildcareOpenResourcesDatailList[i].isDel != 'Y'){
                                    html+='<li data-time-id="'+organChildcareOpenResourcesDatailList[i].id+'" class="'+organChildcareOpenResourcesDatailList[i].isCanUse+'">' +
                                    '<p>'+organChildcareOpenResourcesDatailList[i].segment+'</p></li>';
                                }
                            }
                        }
                    }
                    $('#time_list').html(html);
                    erbao.chooseTime();
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



