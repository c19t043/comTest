var doctorAddress = decodeURIComponent(window.location.search.substring(1).split('&')[0]);
var doctorId;
$(function () {
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
                var data = result.doctorServiceTypeList;
                if (data.length == 0) {
                    $('#date,#container,#footer').hide();
                    $('#description').html('<p style="margin-top: 50px;font-size: 20px;color: #505050">您暂时未开通医疗机构门诊服务<br/>,如需开通，请在"医生认证"<br/>功能中重新提交资料</p>')
                } else {
                    var isTrue = false;
                    for (var i = 0, len = data.length; i < len; i++) {
                        if (data[i].serviceTypeName == '医疗机构') {
                            isTrue = true;
                        }
                    }
                    if (!isTrue) {
                        $('#date,#container,#footer').hide();
                        $('#description').html('<p style="margin-top: 50px;font-size: 20px;color: #505050">您暂时未开通医疗机构门诊服务<br/>,如需开通，请在"医生认证"<br/>功能中重新提交资料</p>')
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
        url: urlWay.clinicHost + 'doctorClinic.action',
        cache: false,
        async: false,
        data: {action: "getMoreOrgList"},
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list = result.morePracticeOrgList;
                doctorId = result.doctorInfo.id;
                for (var i = 0, len = list.length; i < len; i++) {
                    var istrue;
                    if (list[i].isCanClinic == false) {
                        istrue = '';
                    } else {
                        istrue = 'canChoose'; //2016-8-8屏蔽
                    }
                    $('tbody').append('<tr><td>' + list[i].orgName + '</td><td>' + list[i].canClinicDate + '</td> <td><span class="' + istrue + '" onclick="goToSetVisit(\'' + list[i].orgName + '\',\'' + list[i].canClinicDate + '\',' + list[i].id + ',\'' + list[i].orgAddress + '\')">我要坐诊</span></td> </tr>');
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });
});
function goToSetVisit(address, time, id, orgAddress) {
    window.location.href = 'setVisit.html?' + address + '&' + time + '&' + id + '&' + doctorId + '&' + orgAddress;//2016-8-8屏蔽
}
