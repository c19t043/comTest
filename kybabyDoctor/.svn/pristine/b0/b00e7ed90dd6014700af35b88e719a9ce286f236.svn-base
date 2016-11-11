/**
 * Created by windows on 2016/3/28.
 */

$(function (){
    alltime('cur');
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserInoculationAppointmentInfoListForReception",
            "userInoculationAppointmentInfo.status":'已预检,已登记'
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
                    for(var i= 0,len=orglist.length;i<len;i++){
                        html+= '<div class="con-head">接种时间:'+orglist[i].openStartTime+'~'+orglist[i].openEndTime+'</div>';
                        var userInoculationAppointmentInfoList=orglist[i].userInoculationAppointmentInfoList;
                        if(userInoculationAppointmentInfoList!=null){
                            for(var j= 0,l=userInoculationAppointmentInfoList.length;j<l;j++){
                                html+='<div class="cont"><div>' +
                                '<div><span>预约编号</span><br/>'+userInoculationAppointmentInfoList[j].appointmentCode+'</div> ' +
                                '<div><span>宝宝姓名</span><br/>'+userInoculationAppointmentInfoList[j].userInfo.babyName+'</div> ' +
                                '<div class="vaccine-time"> ' +
                                //'<span>本次接种疫苗:</span> ' +
                                '<select name="" placeholder="本次接种疫苗" id="id'+j+'" disabled></select> ' +
                                '<p class="hr"></p>' +
                                //' <span>下次接种时间:</span> ' +
                                '<input readonly name="time" disabled class="appDate1" type="text" placeholder="下次接种时间"/> </div>' +
                                '<div><p class="startvaccine" onclick="beginwork(this)" data-status="'+userInoculationAppointmentInfoList[j].status+'" data-id="'+userInoculationAppointmentInfoList[j].id+'">开始<br/>接种</p></div> ' +
                                '</div> ' +
                                '<p class="gray_1"></p> ' +
                                '</div> ';
                            }
                        }
                    }
                    $('.content').html(html);
                    getDengji();
                    alltime();
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
                "userInoculationAppointmentInfo.status":'已预检,已登记',
                "userInoculationAppointmentInfo.appointmentCode":num,
                "userInoculationAppointmentInfo.organInoculationOpenResources.openDate":$(".appDate2").val()
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
                                    '<div class="vaccine-time"> ' +
                                    //'<span>本次接种疫苗:</span> ' +
                                    '<select placeholder="本次接种疫苗" name="" id="id'+j+'" disabled></select> ' +
                                    '<p class="hr"></p> ' +
                                    //'<span>下次接种时间:</span> ' +
                                    '<input readonly name="time" disabled class="appDate1" placeholder="下次接种时间" type="text"/> </div>' +
                                    '<div><p class="startvaccine" onclick="beginwork(this)" data-status="'+userInoculationAppointmentInfoList[j].status+'" data-id="'+userInoculationAppointmentInfoList[j].id+'">开始<br/>接种</p></div> ' +
                                    '</div> ' +
                                    '<p class="gray_1"></p> ' +
                                    '</div> ';
                                }
                            }
                        }
                        $('.content').html(html);
                        getDengji();
                        alltime();
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
    //
});
//开始或完成接种
function beginwork(div){
    var text=$(div).text().substr(0,2);
    //开始接种
    var id=$(div).attr('data-id');
    if(text=='开始'){
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
            cache: false,
            async: false,
            data: {
                action: "saveOrUpdateUserInoculationAppointmentInfo",
                "userInoculationAppointmentInfo.id":id,
                "userInoculationAppointmentInfo.status":'已登记'
            },
            success: function (result) {
                if (result.mes == "重复操作") {
                    ale('数据已被更新过');
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
        $(div).html('完成<br/>接种');
        $(div).parent().prev().find('input').attr('disabled',false);
        var html='';
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
            cache: false,
            async: false,
            data: {
                action: "getVaccineInfoList"
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "org_login.html";
                }
                else if (result.mes == "成功") {
                    var orglist = result.vaccineInfoList;
                    if (orglist != null) {
                        for(var i= 0,len=orglist.length;i<len;i++){
                            var inoculumNumber=orglist[i].inoculumNumber;
                            if(inoculumNumber==null || inoculumNumber==''){
                                inoculumNumber='';
                            }else{
                                inoculumNumber='('+inoculumNumber+')';
                            }
                            html+='<option data-vaccineid="'+orglist[i].id+'" value="'+orglist[i].vaccineName+'">'+orglist[i].vaccineName+inoculumNumber+'</option>';
                        }
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
        $(div).parent().prev().find('select').attr('disabled',false).html(html);
    }else{
        //完成接种
        var value=$(div).parent().prev().find('input').val();
        if(value==''){
            ale('请选择下次接种时间');
            return false;
        }
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
        var mydatestr=myyear+''+mymonth+''+mydat;
        var datestr= value.replace('-','').replace('-','');
        if(datestr<=mydatestr){
            ale('请选择明天及以后的时间');
            return false;
        }
        var vaccineid=$(div).parent().prev().find('select option:selected').attr('data-vaccineid');
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
            cache: false,
            async: false,
            data: {
                action: "saveOrUpdateUserInoculationAppointmentInfo",
                "userInoculationAppointmentInfo.id":id,
                "userInoculationAppointmentInfo.vaccineInfo.id":vaccineid,
                "userInoculationAppointmentInfo.nextVaccinationDate":value,
                "userInoculationAppointmentInfo.status":'已完成'
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
}
function getDengji(){
    var staList=$('p[data-status=已登记]');
    if(staList != null && staList != undefined){
        $(staList).each(function () {
            $(this).html('完成<br/>接种');
            $(this).parent().prev().find('input').attr('disabled',false);
            var html='';
            $.ajax({
                type: 'post',
                url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
                cache: false,
                async: false,
                data: {
                    action: "getVaccineInfoList"
                },
                success: function (result) {
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "org_login.html";
                    }
                    else if (result.mes == "成功") {
                        var orglist = result.vaccineInfoList;
                        if (orglist != null) {
                            for(var i= 0,len=orglist.length;i<len;i++){
                                var inoculumNumber=orglist[i].inoculumNumber;
                                if(inoculumNumber==null || inoculumNumber==''){
                                    inoculumNumber='';
                                }else{
                                    inoculumNumber='('+inoculumNumber+')';
                                }
                                html+='<option data-vaccineid="'+orglist[i].id+'" value="'+orglist[i].vaccineName+'">'+orglist[i].vaccineName+inoculumNumber+'</option>';
                            }
                        }
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                }
            });
            $(this).parent().prev().find('select').attr('disabled',false).html(html);
        });
    }

}

function alltime(type) {
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
    $(".appDate2").mobiscroll($.extend(opt['date'], opt['default']));
    if(type=='cur'){
        $(".appDate2").val(currTime);
    }
};

