var doctorId = decodeURIComponent(window.location.search.substring(1).split('&&')[0]);
$(function () {
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicBooking.action',
        cache: false,
        async: false,
        data: {
            action: "getClinicDoctorInfo",
            "doctorInfo.id": doctorId//得到单个医生信息传此参数，医生列表不需要传参
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list = result.doctorInfoFoList;
//                    console.log(list);
                var doctor = list[0].doctorInfo;
                var majorNameList = list[0].majorNameList;
                var majorNameListHtml = '';
                for (var j = 0, le = majorNameList.length; j < le; j++) {
                    majorNameListHtml += ' <li>' + majorNameList[j] + '</li>'
                }
                $('tr').html('<td class="faceicon">' +
                '<img src="' + hostBG + 'images/doctorFaceIcon/' + doctor.doctorImage + '" />' +
                ' </td><td class="doctordesc"><p class="b"><span id="doctorName" class="tag">' +
                doctor.doctorName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;<small id="doctorTitle" class="tag">' +
                doctor.doctorTitle + '</small></p> <p class="a"><span id="doctorHospital">' + doctor.doctorEmployer + '</span>' +
                '<span class="iconbtn">' + list[0].hospitalLeval + '</span></p> <div class="c"><span>专业方向：</span><ul>' +
                majorNameListHtml + '</ul></div></td>');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });
    $('#good>div').click(function () {
        $('#good>div').removeClass('chooseComment');
        if ($(this).prop('class') == 'good') {
            $(this).find('img').prop('src', 'images/good.jpg');
            $('.bad').find('img').prop('src', 'images/bad1.jpg');
        } else if ($(this).prop('class') == 'bad') {
            $(this).find('img').prop('src', 'images/bad.jpg');
            $('.good').find('img').prop('src', 'images/good1.jpg');
        }
        $(this).addClass('chooseComment');
    });
});
var orderId = decodeURIComponent(window.location.search.substring(1).split('&&')[1]);

function submitComment() {
    var text = $('.chooseComment span').text();
    var val = $('textarea').val();
    var n = 0;
    if (text == '好评') {
        n = 0;
    } else {
        n = 1;
    }
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicOrder.action',
        cache: false,
        async: false,
        data: {
            action: "saveOrUpdateClinicOrder",
            "orderInfoClinic.orderStatus": "评价医生",//这个固定的
            "orderInfoClinic.id": orderId,
            "evaluateClinic.evaluateLevel": n,//评价级别（0：好；1：差）
            "evaluateClinic.evaluateContent": val,
            "doctorInfo.id": doctorId
        },
        success: function (result) {
            ale('评论成功');
            setTimeout(function(){window.location.href = 'myAppointment.html';},2500);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });
}
$(function () {
    $('.head-left p').click(function () {
        window.location.href = 'myAppointment.html';
    });
});