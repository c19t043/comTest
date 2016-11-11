var historyAddress = decodeURIComponent(window.location.search.substring(1).split("&&")[0]);
var dateId = decodeURIComponent(window.location.search.substring(1).split("&&")[1]);
var time;
var n = 0;
var m = 15;
var timeArr = [];
time = sessionStorage.getItem('time');
timeArr.push(time);
var add = '';
var doctorXAddress;
var doctorHospitalAddress;
//判断是添加还是重新编辑并获取缓存数据
$(function () {
    setTime();
    address();
    doctorXAddress=sessionStorage.getItem('doctorXAddress');
    doctorHospitalAddress=sessionStorage.getItem('doctorHospitalAddress');
    $('#meetHospital').html(doctorHospitalAddress+'<br/><small style="margin-left: 76px;display: none">'+doctorXAddress+'</small>');
    if (historyAddress == '编辑') {
        $.ajax({
            type: 'post',
            async: false,
            url: urlWay.clinicHost + 'doctorClinic.action',
            data: {
                action: "getDoctorMorePractice",
                "doctorMorePractice.id":dateId
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == "成功") {
                    var list = result.doctorMorePractice;
                    $('#objId').val(list.id);
                    $('#outPatientTime').text(list.clinicDate);
                    $("#startTime").val(list.clinicBeganTime);
                    $("#endTime").val(list.clinicEndTime);
                    $('#people').text(list.canClinicNum);
                    $('#meetAddress').text(list.clinicAddress);
                }
            },
            error: function () {
                alert('you are false');
            }
        });
    }
    else {
        $('#outPatientTime').text(time);
        if (historyAddress == '添加') {
            chooseTime();
            $.ajax({
                type: 'post',
                async: false,
                url: urlWay.clinicHost + 'doctorClinic.action',
                data: {
                    action: "getClinicAddressList",
                    "doctorMorePractice.clinicOrgType":0
                },
                success: function (result) {
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "login.html";
                    }
                    else if (result.mes == "成功") {
                        if(result.addressList!=null){
                            add = result.addressList[result.addressList.length - 1];
                            $('#meetAddress').text(add);
                        }
                    }
                },
                error: function () {
                    alert('you are false');
                }
            });

        } else {
            if (historyAddress == 'allNull') {
                historyAddress = sessionStorage.getItem('add');
            }
            $('#meetAddress').html(historyAddress);
            $("#startTime").val(sessionStorage.getItem('startTime1'));
            $("#endTime").val(sessionStorage.getItem('endTime1'));
            $('#people').text(sessionStorage.getItem('people1'));
            $('#outPatientTime').text(sessionStorage.getItem('outPatientTime'));
            $('#objId').val(sessionStorage.getItem('id1'));
            address();
            sessionStorage.clear();
        }
    }
});
//选择时间段
$("#startTime,#endTime").change(function () {
    chooseTime();
});
//动态生成可选时间段与时间间隔
function setTime() {
    var zd = [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19];
    var n = 0;
    var str = '';
    var x = 0;
    var selectHtml = '';
    var selectStartHtml = '<option value="08:00">08:00</option>';
    for (var i = 0; i < 100; i++) {
        n = parseInt(n) + m;
        if (n < 10) {
            n = '0' + n;
        } else if (n >= 60) {
            n = n - 60;
            if (zd.length == 1) {
                $('#startTime').html(selectStartHtml + selectHtml);
                $('#endTime').html(selectHtml);
                return false;
            } else {
                zd.splice(0, 1);
            }
            if (n < 10) {
                n = '0' + n;
            }
        }
        var s = zd[0];
        if (s < 10) {
            str = '0' + s + ':' + n;
        } else {
            str = s + ':' + n;
        }
        selectHtml += '<option value="' + str + '">' + str + '</option>';
    }
}
//选择时间段方法
var startTime;
var endTime;

function chooseTime() {
    var thisTime = $("#startTime").find("option:selected").val().split(':');
    var anotherTime = $("#endTime").find("option:selected").val().split(':');
    var timeCha = parseInt(anotherTime[0]) - parseInt(thisTime[0]);
    if (timeCha >= 0) {
        var cha = timeCha * 60 + parseInt(anotherTime[1]) - parseInt(thisTime[1]);
        if (cha > 0) {
            $('#people').text((cha / m));
        }
        else {
            $('#people').text(0);
            ale('结束时间必须大于开始时间');
        }
    } else {
        $('#people').text(0);
        ale('结束时间必须大于开始时间');
    }
}
//选择下周是否重复
$('#repeat div').click(function () {
    $('#repeat div').removeClass('activeStyle');
    $(this).addClass('activeStyle');
    $('#isRepeat').text($(this).text());
});
//门诊地址
function address() {
    $('#meetAddress1,#meetAddress').click(function () {
        sessionStorage.setItem('startTime1', $('#startTime').val());
        sessionStorage.setItem('outPatientTime', time);
        sessionStorage.setItem('endTime1', $('#endTime').val());
        sessionStorage.setItem('people1', $('#people').text());
        sessionStorage.setItem('id1', $('#objId').val());
        sessionStorage.setItem('add', $('#meetAddress').text());
        sessionStorage.setItem('doctorXAddress', doctorXAddress);
        sessionStorage.setItem('doctorHospitalAddress', doctorHospitalAddress);
        window.location.href = 'patientAddress.html?'+dateId;
    });
}
//保存与提交数据
function saveOutpatient() {
    var text = $('#meetAddress').text().substr(0, 2);
    if (text == '请输') {
        ale('请填写门诊地址');
    }
    else if ($('#people').text() <= 0) {
        ale('结束时间必须大于开始时间')
    } else {
        var objIdArr=[];
        var objId=$('#objId').val();
        objIdArr.push(objId);
        if ($('#isRepeat').text() == '下周重复') {
            n = 1;
            var editYear = parseInt(time.split('-')[0]);
            var editMonth = parseInt(time.split('-')[1]);
            var editDate = parseInt(time.split('-')[2]) + 7;
            var nextWeekTime;
            var days;
            if (editMonth == 1 || editMonth == 3 || editMonth == 5 || editMonth == 7 || editMonth == 8 || editMonth == 10 || editMonth == 12) {
                days = 31;
            }
            else if (editMonth == 4 || editMonth == 6 || editMonth == 9 || editMonth == 11) {
                days = 30;
            }
            else {
                if (editYear % 4 != 0) {
                    days = 28
                }
                else {
                    if (editYear % 100 == 0) {
                        if (editYear % 400 == 0) {
                            days = 29
                        } else {
                            days = 28;
                        }
                    } else {
                        days = 29
                    }
                }
            }
            if (editDate > days) {
                editDate = editDate - days;
                if (editMonth == 12) {
                    editYear = editYear + 1;
                    editMonth = 1;
                    nextWeekTime = editYear + '-01-0' + editDate;
                }
                else if (editMonth < 9) {
                    nextWeekTime = editYear + '-0' + (editMonth + 1) + '-0' + editDate;
                }
                else {
                    nextWeekTime = editYear + '-' + (editMonth + 1) + '-0' + editDate;
                }
            }
            else {
                if (editDate >= 10) {
                    nextWeekTime = editYear + '-' + time.split('-')[1] + '-' + editDate;
                }
                else {
                    nextWeekTime = editYear + '-' + time.split('-')[1] + '-0' + editDate;
                }
            }
            var objid='';
            $.ajax({
                type: 'post',
                async: false,
                url: urlWay.clinicHost + 'doctorClinic.action',
                data: {
                    action: "getDoctorMorePracticeList"
                },
                success: function (result) {
                        var list = result.doctorMorePracticeList;
                    $(list).each(function (index) {
                            var dataList = $(list)[index];
                            if(nextWeekTime==dataList.clinicDate){
                                objid=dataList.id;
                            }
                        });
                },
                error: function () {
                    alert('you are false');
                }
            });
            objIdArr.push(objid);
            timeArr.push(nextWeekTime);
        }
        else{
            timeArr=[time];
        }
        for (var i = 0, l = timeArr.length; i < l; i++) {
            $.ajax({
                type: 'post',
                async: false,
                url: urlWay.clinicHost + 'doctorClinic.action',
                data: {
                    action: "saveOrUpdateDoctorMorePractice",
                    "doctorMorePractice.clinicDate": timeArr[i],
                    "doctorMorePractice.clinicBeganTime": $("#startTime").find("option:selected").val(),
                    "doctorMorePractice.clinicEndTime": $("#endTime").find("option:selected").val(),
                    "doctorMorePractice.canClinicNum": $('#people').text(),
                    "doctorMorePractice.clinicAddress": $('#meetAddress').text(),
                    "doctorMorePractice.isAddClinic": '0',
                    "doctorMorePractice.clinicOrgType": '0',
                    "doctorMorePractice.isRepeat": n,
                    "doctorMorePractice.id": objIdArr[i]
                },
                success: function (result) {
                    //alert(objIdArr[i]);
                    if(result.mes=='多点机构已约'){
                        ale('医疗机构已设置过所选日期，请重新设置');
                    }else if(result.mes=='已有用户预约'){
                        ale('已有用户预约，不能更改！');
                    }
                    else{
                        ale('设置成功');
                        $('#footer>p').removeAttr('onclick');
                        window.location.href = 'chooseOutpatientTime.html';
                    }
                },
                error: function () {
                    alert('you are false');
                }
            });
        }
    }
}
