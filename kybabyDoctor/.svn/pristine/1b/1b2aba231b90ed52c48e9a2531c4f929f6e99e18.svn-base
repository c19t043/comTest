sessionStorage.clear();
var startArr = [];
var endArr = [];
var address = decodeURIComponent(window.location.search.substring(1).split('&')[0]);
var dayTime = decodeURIComponent(window.location.search.substring(1).split('&')[1]);
var orgId = decodeURIComponent(window.location.search.substring(1).split('&')[2]);
var doctorId = decodeURIComponent(window.location.search.substring(1).split('&')[3]);
var orgAddress = decodeURIComponent(window.location.search.substring(1).split('&')[4]);
$(function () {
    $('.orgAddress').text(address);
    $('.address span.canChoose').text(orgAddress);
    $('.time span.canChoose').text(dayTime);
    $.ajax({
        type: 'post',
        url: urlWay.clinicHost + 'doctorClinic.action',
        cache: false,
        async: false,
        data: {
            action: "getMoreOrgTimeList",
            "doctorMorePracticeOrgInfo.id": orgId,
            "doctorMorePracticeOrgInfo.canClinicDate": dayTime
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list = result.morePracticeOrgTimeList;
                if (list == null) {
                    $('tbody').html('<tr><td style="width: 100%;">该机构暂无可约时间</td></tr> ');
                    $('h4,select').css({display: 'none'});
                } else {
                    for (var i = 0, len = list.length; i < len; i++) {
                        $('tbody').append('<tr onclick="chooseAllTime(this)" index="' + list[i].id + '"><td>' + list[i].timeName + '</td><td>' + list[i].startTime + '-' + list[i].endTime + '</td> <td><img src="../images/chooseAddress1.jpg" alt=""/></td> </tr>');
                        startArr.push(list[i].startTime);
                        endArr.push(list[i].endTime);
                    }
                    $('tbody tr:nth-child(1)').prop('class', 'chooseThisTime');
                    $('tbody tr:nth-child(1) img').prop('src', 'images/chooseAddress.jpg');
                    $('tbody tr:nth-child(2) img').prop('src', 'images/chooseAddress1.jpg');
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
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'doctorClinicOrder.action',
        cache:false,
        async:false,
        data:{
            action : "orgSetShow",
            "orgSetShow.doctorInfo.id":doctorId,
            "orgSetShow.doctorMorePracticeOrgInfo.id":orgId
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var data=result.orgSetShow;
                $('.baseSalary').html(data.baseSalary);
                $('.am').html(data.amCount);
                $('.pm').html(data.pmCount);
                $('.all').html(parseFloat(data.pmCount)+parseFloat(data.amCount));
                $('.halfDayMoney').html(data.halfDayMoney);
                $('.commissionPerCase').html(parseFloat(data.commissionPerCase)*100);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });


});
function makeAppointment() {
    if ($('#chooseClear span').prop('class') == 'clear') {
        ale('请选择我已清楚说明！')
    } else if ($('tbody td').html() == '该机构暂无可约时间') {
        ale('该机构暂无可约时间!');
    }
    else {
        var s = $('.chooseThisTime');
        var arr = [];
        var timeArr = [];
        var start_time;
        var end_time;
        if(s.length==0){
            ale('请选择坐诊时间');
        }else{
        if(s.length==1){
                if($('.chooseThisTime>td:nth-child(1)').html()=='上午'){
                    start_time=startArr[0];
                    end_time=endArr[0];
                }else{
                    if(startArr.length==1){
                        start_time=startArr[0];
                        end_time=endArr[0];
                    }else{
                        start_time=startArr[1];
                        end_time=endArr[1];
                    }
                }
            }else if(s.length==2){
                start_time=startArr[0];
                end_time=endArr[1];
            }
            for (var j = 0, le = s.length; j < le; j++) {
                var obj = {'id': $(s).eq(j).attr('index')}
                var t = $('tr').eq(j).find('td:nth-child(2)').text();
                arr.push(obj);
                timeArr.push(t);
            }
            //console.log(start_time);
            //console.log(end_time);
            $.ajax({
                type: 'post',
                async: false,
                url: urlWay.clinicHost + 'doctorClinic.action',
                data: {
                    action: "saveOrUpdateDoctorMorePractice",
                    "doctorMorePractice.clinicDate": $('.time span').text(),
                    "doctorMorePractice.clinicBeganTime":start_time,
                    "doctorMorePractice.clinicEndTime": end_time,
                    "doctorMorePractice.clinicAddress": $('.address span').text(),
                    "doctorMorePractice.isAddClinic": $('select').val(),
                    "doctorMorePractice.clinicOrgType": '1',
                    "allDayMoreOrgTimeSetIdJson": JSON.stringify(arr),
                    "doctorMorePractice.clinicOrg":$('.orgAddress').text(),
                    "doctorMorePractice.doctorMorePracticeOrgInfo.id":orgId
//                    "doctorMorePractice.orgClinicTime":timeHtml
                },
                success: function (result) {
                    if(result.mes=='工作单位已约'){
                        ale('工作单位已设置过所选日期，请重新设置');
                        setTimeout(function () {
                            window.location.href = 'institution.html';
                        },2500);
                    }
                    else if(result.mes=='多点机构已约'){
                        ale('其它机构已设置过所选日期，请重新设置');
                        setTimeout(function () {
                            window.location.href = 'institution.html';
                        },2500);
                    }else{
                        ale('设置成功');
                        $('#footer>p').removeAttr('onclick');
                        setTimeout(function () {
                            window.location.href = 'institution.html';
                        },2500);

                    }
                },
                error: function () {
                    alert('you are false');
                }
            });
        }

        //var timeHtml='';
            //if(timeArr.length==2){
            //    timeHtml=timeArr[0]+' '+' '+timeArr[1];
            //}else{
            //    timeHtml=timeArr[0];
            //}
    }
}
function clearInstruction(div) {
    if ($(div).prop('class') == 'isClass') {
        $(div).removeClass('isClass');
        $('#clear').addClass('clear');
        $(div).find('img').prop('src', 'images/chooseAddress1.jpg');
        $('.chooseTime').css({display: 'none'});
    } else {
        $('#clear').removeClass('clear');
        $(div).addClass('isClass');
        $(div).find('img').prop('src', 'images/chooseAddress.jpg');
        $('.chooseTime').css({display: 'block'});
    }
}
function chooseAllTime(td) {
    if ($(td).prop('class') == 'chooseThisTime') {
        $(td).removeClass('chooseThisTime');
        $(td).find('img').prop('src', 'images/chooseAddress1.jpg');
    } else if ($(td).prop('class') == '') {
        $(td).addClass('chooseThisTime');
        $(td).find('img').prop('src', 'images/chooseAddress.jpg');
    }
};
