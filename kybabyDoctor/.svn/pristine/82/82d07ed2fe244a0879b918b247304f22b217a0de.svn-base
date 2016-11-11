var opendoctorId=decodeURIComponent(window.location.search.substring(1));
$(function () {
    alltime();
    if(opendoctorId=='noDoctor'){
        opendoctorId='';
    }else{
        $('.header-right>span').show();
    }
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
        cache: false,
        async: false,
        data: {
            action: "getChildCareAppointmentListForReception",
            "organChildcareOpenDoctor.id":opendoctorId,
            "userChildcareAppointmentInfo.status":'已预约'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                if(result.orderCountInfo!=null){
                    $('#this_data').html('<span>总数:'+result.orderCountInfo.allSum+'</span> ' +
                    '<span>预约数: '+result.orderCountInfo.bookingSum+'</span> ' +
                    '<span>完成数: '+result.orderCountInfo.registeredSum+'</span>');
                }
                var orglist = result.userChildcareAppointmentInfoFoShowList;
                if(result.organChildcareOpenDoctor!=null){
                    if(result.organChildcareOpenDoctor.workStatus=='准备中'){
                        $('.header-right>span').html('医生上班');
                    }else if(result.organChildcareOpenDoctor.workStatus=='已上班'){
                        $('.header-right>span').html('医生下班');
                    }else if(result.organChildcareOpenDoctor.workStatus=='已下班'){
                        $('.header-right>span').html('工作结束');
                        $('.header-right>span').css({background:'#c1c1c1',color:'#909090'});
                    }
                }
                if (orglist != null) {
                    $('.content').html('');
                    var html = '';
                    for(var i= 0,len=orglist.length;i<len;i++){
                        var time=orglist[i].openStartTime;
                        if(orglist[i].openEndTime!=null){
                            time+='~'+orglist[i].openEndTime;
                        }
                        html+= '<div class="con-head">预约时间:'+time+'</div>';
                        var userInoculationAppointmentInfoList=orglist[i].userChildcareAppointmentInfoList;
                        if(userInoculationAppointmentInfoList!=null){
                            for(var j= 0,l=userInoculationAppointmentInfoList.length;j<l;j++){
                                html+='<div class="cont"><div>' +
                                '<div>'+userInoculationAppointmentInfoList[j].userInfo.babyName+'</div> ' +
                                '<div>'+userInoculationAppointmentInfoList[j].userInfo.phone+'</div> ' +
                                '<div>'+userInoculationAppointmentInfoList[j].preEncoding+'</div> ' +
                                '<div><p onclick="suremeet('+userInoculationAppointmentInfoList[j].id+')">确认<br/>会面</div> ' +
                                '</div> ' +
                                '<p class="gray_1"></p> ' +
                                '</div> ';
                            }
                        }
                    }
                    $('.content').html(html);
                }else{
                    $('.content').html('没有符合查询条件的数据');
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    $('.header-right>span').click(function () {
        var text=$(this).text();
        if(text=='医生上班'){
            $.ajax({
                type: 'post',
                url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
                cache: false,
                async: false,
                data: {
                    action: "saveOrUpdateOrganChildcareOpenDoctor",
                    "organChildcareOpenDoctor.id":opendoctorId,
                    "organChildcareOpenDoctor.workStatus":"已上班"
                },
                success: function (result) {
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "org_login.html";
                    }
                    else if (result.mes == "成功") {
                        if(result.orderCountInfo!=null){
                            $('#this_data').html('<span>总数:'+result.orderCountInfo.allSum+'</span> ' +
                            '<span>预约数: '+result.orderCountInfo.bookingSum+'</span> ' +
                            '<span>完成数: '+result.orderCountInfo.meetingSum+'</span>');
                        }
                        var dataList=result.userChildcareAppointmentInfoList;
                        var html = '';
                        if(dataList!=null){
                            for(var j= 0,l=dataList.length;j<l;j++){
                                var time=dataList[j].organChildcareOpenResources.timeDivisionNeed;
                                if(time=='时间段'){
                                    time=dataList[j].organChildcareOpenResourcesDatail.openStartTime+'~'+dataList[j].organChildcareOpenResourcesDatail.openEndTime
                                }else{
                                    time=dataList[j].organChildcareOpenResourcesDatail.segment;
                                }
                                html+='<div class="cont"><div>' +
                                '<div>'+dataList[j].userInfo.babyName+'</div> ' +
                                '<div>'+dataList[j].userInfo.phone+'</div> ' +
                                '<div>'+time+'</div> ' +
                                '<div><p onclick="suremeet('+dataList[j].id+')">确认<br/>会面</div> ' +
                                '</div> ' +
                                '<p class="gray_1"></p> ' +
                                '</div> ';
                            }
                        }
                        $('.content').html(html);
                        $('.header-right>span').html('医生下班');
                        window.location.reload();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }else if(text=='医生下班'){
            $(this).text('医生 下班');
            $.ajax({
                type: 'post',
                url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
                cache: false,
                async: false,
                data: {
                    action: "saveOrUpdateOrganChildcareOpenDoctor",
                    "organChildcareOpenDoctor.id":opendoctorId,
                    "organChildcareOpenDoctor.workStatus":"已下班"
                },
                success: function (result) {
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "org_login.html";
                    }else if(result.mes=='未完成当天已预约订单'){
                        alert(result.mes);
                        $(this).text('医生下班');
                    }
                    else if (result.mes == "成功") {
                        window.location.reload();
                        //$(this).text('工作结束');
                        $(this).css({background:'#c1c1c1',color:'#909090'});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }else{
            alert('工作已结束');
        }
    });

});
$(function () {
    $('.search').click(function () {
        var num=$('.listaddress input').val().trim();
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
            cache: false,
            async: false,
            data: {
                action: "getChildCareAppointmentListForReception",
                "userChildcareAppointmentInfo.status":'已预约',
                "organChildcareOpenDoctor.id":opendoctorId,
                "userChildcareAppointmentInfo.preEncoding":num,
                "userChildcareAppointmentInfo.organChildcareOpenResources.openDate":$(".appDate1").val()
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "org_login.html";
                }
                else if (result.mes == "成功") {
                    var orglist = result.userChildcareAppointmentInfoFoShowList;
                    if (orglist != null) {
                        $('.content').html('');
                        if(result.organChildcareOpenDoctor!=null){
                            if(result.organChildcareOpenDoctor.workStatus=='准备中'){
                                $('.header-right>span').html('医生上班');
                            }else if(result.organChildcareOpenDoctor.workStatus=='已上班'){
                                $('.header-right>span').html('医生下班');
                            }else if(result.organChildcareOpenDoctor.workStatus=='已下班'){
                                $('.header-right>span').html('工作结束');
                                $('.header-right>span').css({background:'#c1c1c1',color:'#909090'});
                            }
                        }
                        if(result.orderCountInfo!=null){
                            $('#this_data').html('<span>总数:'+result.orderCountInfo.allSum+'</span> ' +
                            '<span>预约数: '+result.orderCountInfo.bookingSum+'</span> ' +
                            '<span>完成数: '+result.orderCountInfo.meetingSum+'</span>');
                        }
                        var html = '';
                        for(var i= 0,len=orglist.length;i<len;i++){
                            var time=orglist[i].openStartTime;
                            if(orglist[i].openEndTime!=null){
                                time+='~'+orglist[i].openEndTime;
                            }
                            html+= '<div class="con-head">预约时间:'+time+'</div>';
                            var userInoculationAppointmentInfoList=orglist[i].userChildcareAppointmentInfoList;
                            if(userInoculationAppointmentInfoList!=null){
                                for(var j= 0,l=userInoculationAppointmentInfoList.length;j<l;j++){
                                    html+='<div class="cont"><div>' +
                                    '<div>'+userInoculationAppointmentInfoList[j].userInfo.babyName+'</div> ' +
                                    '<div>'+userInoculationAppointmentInfoList[j].userInfo.phone+'</div> ' +
                                    '<div>'+userInoculationAppointmentInfoList[j].preEncoding+'</div> ' +
                                    '<div><p onclick="suremeet('+userInoculationAppointmentInfoList[j].id+')">确认<br/>会面</div> ' +
                                    '</div> ' +
                                    '<p class="gray_1"></p> ' +
                                    '</div> ';
                                }
                            }
                        }
                        $('.content').html(html);
                    }else{
                        $('.content').html('没有符合查询条件的数据');
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    $('.reset').click(function () {
        window.location.reload();
    });
    $('.print').click(function () {
        window.location.href='org_babyprint.html';
    });
});
function suremeet(id){
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
        cache: false,
        async: false,
        data: {
            action: "saveOrUpdateUserChildcareAppointmentInfo",
            "organChildcareOpenDoctor.id":opendoctorId,
            "userChildcareAppointmentInfo.id":id,
            "userChildcareAppointmentInfo.status":'已会面'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                window.location.reload();
            }else if (result.mes == "重复操作") {
                ale('数据已被更新过');
                setTimeout(function () {
                    window.location.reload();
                },2000);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}
function alltime() {
    var currYear = (new Date()).getFullYear();
    var currTime='';
    $.ajax({
        type:'post',
        url:urlWay.orgBusinessHost+'orgClinicManager.action',
        cache:false,
        async:false,
        data:{action : "getCurrentTime"},
        success:function(result){
            currTime=result.currentTime.split(' ')[0];
            currYear=parseInt(result.currentTime.split(' ')[0].split('-')[0]);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });

    var opt = {};
    opt.date = {preset: 'date'};
    opt.datetime = {preset: 'datetime'};
    opt.time = {preset: 'time'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
        startYear: 2016, //开始年份
        endYear: currYear+1//结束年份
    };
    $(".appDate1").mobiscroll($.extend(opt['date'], opt['default']));
    $(".appDate1").val(currTime);
};


