/**
 * Created by windows on 2016/5/5.
 */
var opendoctorId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);
$(function(){
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'childCareChargeManage.action',
        cache: false,
        async: false,
        data: {
            action: "getOrganChildcareOpenDoctor",
            "organChildcareOpenDoctor.id":opendoctorId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                var data = result.organChildcareOpenDoctor;
                if (data != null) {
                    $('.header-center').html(data.doctorInfo.doctorName);
                    if(data.workStatus=='准备中'){
                        ale('请点击医生上班，确认上班');
                    }else if(data.workStatus=='已上班'){
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
                    }else if(data.workStatus=='已下班'){
                        $('.header-right>span').html('工作结束');
                        $('.header-right>span').css({background:'#c1c1c1',color:'#909090'});
                    }
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
                url: urlWay.orgBusinessHost + 'childCareChargeManage.action',
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
                url: urlWay.orgBusinessHost + 'childCareChargeManage.action',
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
            window.location.reload();
        }
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
