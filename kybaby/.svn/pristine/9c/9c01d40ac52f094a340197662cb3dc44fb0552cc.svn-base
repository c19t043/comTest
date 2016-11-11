/**
 * Created by windows on 2016/3/22.
 */

var orderId=decodeURIComponent(window.location.search.substring(1).split("&")[0]);
var orgId;
var isAppointment='';
$(function () {
    getAppointment();
    $('#footer p').click(function () {
        getAppointment();
        if(isAppointment!='已预约'){
            ale('您已预检，不能取消');
            return false;
        }
        var answer=confirm('您确定要取消吗？');
        if(answer==false){
            return false;
        }
        $.ajax({
            type: 'post',
            url: hostOrgbusiness + 'vaccineManage.action',
            cache: false,
            async: false,
            data: {
                action: "cancelAppointment",
                "userInoculationAppointmentInfo.id":orderId
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
    });
});
function getAppointment(){
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "getAppointmentById",
            "userInoculationAppointmentInfo.id":orderId
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                orderId=result.userInoculationAppointmentInfo.id;
                orgId=result.userInoculationAppointmentInfo.hospitalBasicInfo.id;
                isAppointment=result.userInoculationAppointmentInfo.status;
                $('.org_userImg>img').prop('src',hostBG+'images/userFaceIcon/'+result.userInoculationAppointmentInfo.userInfo.userImage+"?"+parseInt(Math.random()*10000000000000));
                $('.username>span').html(result.userInoculationAppointmentInfo.userInfo.babyName);
                $('.address>span').html(result.userInoculationAppointmentInfo.hospitalBasicInfo.hospitalLname+'<br/>(服务窗口:'+result.userInoculationAppointmentInfo.organServicePlaceSet.windowName+')');
                $('.yuyuema>p').html('预约码:'+result.userInoculationAppointmentInfo.appointmentCode);
                $('.time>span').html(result.userInoculationAppointmentInfo.organInoculationOpenResourcesDetail.openDate+
                    ' '+result.userInoculationAppointmentInfo.organInoculationOpenResourcesDetail.openStartTime+'~'+
                    result.userInoculationAppointmentInfo.organInoculationOpenResourcesDetail.openEndTime
                );
                if(isAppointment!='已预约'){
                    $('#footer').hide();
                }
            }
        },
        error: function () {
            layer();
        }
    });

}
