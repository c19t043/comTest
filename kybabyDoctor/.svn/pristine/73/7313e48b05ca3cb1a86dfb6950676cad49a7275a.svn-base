
$(function () {
    alltime();
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserInoculationAppointmentInfoListForReception",
            "userInoculationAppointmentInfo.status":'已预约'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                var orglist = result.userInoculationAppointmentInfoShowList;
                if(result.orderCountInfo!=null){
                    $('#this_data').html('<span>总数:'+result.orderCountInfo.allSum+'</span> ' +
                    '<span>预约数: '+result.orderCountInfo.bookingSum+'</span> ' +
                    '<span>预检数: '+result.orderCountInfo.finishedSum+'</span> ' +
                    '<span>登记数: '+result.orderCountInfo.havePreSum+'</span> ' +
                    '<span>完成数: '+result.orderCountInfo.registeredSum+'</span>');
                }
                if (orglist != null) {
                    var html='';
                    for(var i= 0,len=orglist.length;i<len;i++){
                        html+= '<div class="con-head">接种时间:'+orglist[i].openStartTime+'~'+orglist[i].openEndTime+'</div>';
                        var userInoculationAppointmentInfoList=orglist[i].userInoculationAppointmentInfoList;
                        if(userInoculationAppointmentInfoList!=null){
                            for(var j= 0,l=userInoculationAppointmentInfoList.length;j<l;j++){
                                html+='<div class="cont"><div>' +
                                '<div><span>预约编号</span><br/>'+userInoculationAppointmentInfoList[j].appointmentCode+'</div> ' +
                                '<div><span>宝宝姓名</span><br/>'+userInoculationAppointmentInfoList[j].userInfo.babyName+'</div> ' +
                                '<div><span>测试温度</span><br/><input class="id'+userInoculationAppointmentInfoList[j].id+'" type="text"/>℃</div> ' +
                                '<div><p onclick="startRecord('+userInoculationAppointmentInfoList[j].id+')">开始<br/>登记</p></div> ' +
                                '</div> ' +
                                '<p class="gray_1"></p> ' +
                                '</div> ';
                            }
                        }
                    }
                    $('.content').html(html);
                }else{
                    $('#today').html('机构暂时没有预约');
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    //查询
    $('.search').click(function () {
        var num=$('.listaddress input').val().trim();
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
            cache: false,
            async: false,
            data: {
                action: "getUserInoculationAppointmentInfoListForReception",
                "userInoculationAppointmentInfo.status":'已预约',
                "userInoculationAppointmentInfo.appointmentCode":num,
                "userInoculationAppointmentInfo.organInoculationOpenResources.openDate":$(".appDate1").val()
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
                        '<span>预检数: '+result.orderCountInfo.finishedSum+'</span> ' +
                        '<span>登记数: '+result.orderCountInfo.havePreSum+'</span> ' +
                        '<span>完成数: '+result.orderCountInfo.registeredSum+'</span>');
                    }
                    var orglist = result.userInoculationAppointmentInfoShowList;
                    if (orglist != null) {
                        var html='';
                        $('.content').html('');
                        for(var i= 0,len=orglist.length;i<len;i++){
                            html+= '<div class="con-head">接种时间:'+orglist[i].openStartTime+'~'+orglist[i].openEndTime+'</div>';
                            var userInoculationAppointmentInfoList=orglist[i].userInoculationAppointmentInfoList;
                            if(userInoculationAppointmentInfoList!=null){
                                for(var j= 0,l=userInoculationAppointmentInfoList.length;j<l;j++){
                                    html+='<div class="cont"><div>' +
                                    '<div><span>预约编号</span><br/>'+userInoculationAppointmentInfoList[j].appointmentCode+'</div> ' +
                                    '<div><span>宝宝姓名</span><br/>'+userInoculationAppointmentInfoList[j].userInfo.babyName+'</div> ' +
                                    '<div><span>测试温度</span><br/><input class="id'+userInoculationAppointmentInfoList[j].id+'" type="text"/>℃</div> ' +
                                    '<div><p onclick="startRecord('+userInoculationAppointmentInfoList[j].id+')">开始<br/>登记</p></div> ' +
                                    '</div> ' +
                                    '<p class="gray_1"></p> ' +
                                    '</div> ';
                                }
                            }
                        }
                        $('.content').html(html);
                    }
                    else{
                        ale('没有符合查询条件的数据');
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });

    });
});
//开始预检
function startRecord(id){
    var temperature=$(".id"+id).val();
    if(/\d$/.test(temperature) && temperature > 35 && temperature < 45){
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
            cache: false,
            async: false,
            data: {
                action: "saveOrUpdateUserInoculationAppointmentInfo",
                "userInoculationAppointmentInfo.id":id,
                "userInoculationAppointmentInfo.measuringTemperature":temperature,
                "userInoculationAppointmentInfo.status":'已预检'
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "org_login.html";
                }
                else if (result.mes == "成功") {
                    ale('操作成功');
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                } else if (result.mes == "重复操作") {
                    ale('数据已被更新过');
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }else{
        ale('请填写宝宝体温');
    }
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

