/**
 * Created by windows on 2016/3/28.
 */

var orderId=decodeURIComponent(window.location.search.substring(1).split("&")[0]);
var orgId;
$(function () {
    var isMoney='',isZ=decodeURIComponent(window.location.search.substring(1).split("&")[1]);
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'orgBoManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserChildcareAppointmentInfo",
            "userChildcareAppointmentInfo.id":orderId
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var list=result.userChildcareAppointmentInfo;
                orgId=list.hospitalBasicInfo.id;
                $('.org_userImg>img').prop('src',hostBG+'images/userFaceIcon/'+list.userInfo.userImage+"?"+parseInt(Math.random()*10000000000000));
                $('.username>span').html(list.userInfo.babyName);
                if(list.organChildcareOpenResources.isMoney=='Y'){
                    isMoney='Y';
                    $('.address>span').html(list.hospitalBasicInfo.hospitalLname+'('+list.hospitalBasicInfo.address+')');
                    $('.yuyuema>p').html('预约医生:'+list.doctorInfo.doctorName);
                }else{
                    $('.address>span').html(list.hospitalBasicInfo.hospitalLname+'<br/>(服务诊室:'+list.organServicePlaceSet.windowName+')');
                    $('.yuyuema>p').html('预约码:'+list.preEncoding);
                }
                if(list.organChildcareOpenResources.timeDivisionNeed=='时间段'){
                    $('.time>span').html(list.organChildcareOpenResources.openDate+
                        ' '+list.organChildcareOpenResourcesDatail.openStartTime+'~'+list.organChildcareOpenResourcesDatail.openEndTime
                    );
                }else{
                    $('.time>span').html(list.organChildcareOpenResources.openDate+
                        ' '+list.organChildcareOpenResourcesDatail.segment
                    );
                }
            }
        },
        error: function () {
            layer();
        }
    });

    $('#footer p').click(function () {
        var answer=confirm('您确定要取消吗？');
        if(answer==false){
            return false;
        }
        if(isZ=='Y'){
            $.ajax({
                type:'post',
                url:spInterfaceService + 'handleUserChildcareAppointmentInfo.action',
                cache:false,
                async:true,
                data:{
                    action : "handleUserChildcareAppointmentInfo",
                    "userChildcareAppointmentInfo.id":orderId,
                    "userChildcareAppointmentInfo.status":"用户取消"
                },
                success:function(result){
                    if (result.mess == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "login.html";
                    }else if (result.mess != "成功") {
                        ale(result.mess);
                        return false;
                    }
                    else if (result.mess == "成功") {
                        window.location.href = "myOrg.html";
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                    alert(errorThrown);
                    layer();
                }
            });
        }else{
            if(isMoney=='Y'){
                $.ajax({
                    type:'post',
                    url:hostOrgbusiness + 'childCareChargeManage.action',
                    cache:false,
                    async:false,
                    data:{
                        action : "handleUserChildcareAppointmentInfo",
                        "userChildcareAppointmentInfo.id":orderId,
                        "userChildcareAppointmentInfo.status":"用户取消"
                    },
                    success:function(result){
                        if (result.mes == '请登录') {
                            ale('请登录', '24px');
                            window.location.href = "login.html";
                        }else if (result.mes != "成功") {
                            ale(result.mes);
                            return false;
                        }
                        else if (result.mes == "成功") {
                            window.location.href = "orgservice.html";
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                        alert(errorThrown);
                        layer();
                    }
                });
            }else{
                $.ajax({
                    type: 'post',
                    url: hostOrgbusiness + 'orgBoManage.action',
                    cache: false,
                    async: false,
                    data: {
                        action: "cancelUserChildcareAppointmentInfo",
                        "userChildcareAppointmentInfo.id":orderId
                    },
                    success: function (result) {
                        if(result.mes=='请登录'){
                            ale('请登录', '24px');
                            window.location.href = "login.html";
                        }
                        else if(result.mes=='成功'){
                            ale('取消成功');
                            window.location.href='orgservice_page.html?'+orgId;
                        }
                    },
                    error: function () {
                        layer();
                    }
                });
            }
        }
    });

});

