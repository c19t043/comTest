var checkwords = 0;
var today = new Date();
var year = today.getFullYear();
var month = today.getMonth();
var day = today.getDay();//星期
var date = today.getDate();
var days;
var dateArr = [];
var serDocId = decodeURIComponent(window.location.search.substring(1).split('&&')[0].trim());
var useChooseDate = decodeURIComponent(window.location.search.substring(1).split('&&')[1].trim());
var recentArr = [];
var address = '';
var org = '';
var isPlus='';
var orderId='';
var clinicEndTime;
var appointmentBeganTime='';
var otherPhone='';
var otherName='';
var orgId = '';
var userId;
function prev() {
    window.location.href = 'pointPatient.html?' + serDocId+'&'+orgId;
}
//修改订单进入
$(function () {
	var g=localStorage.getItem('h');

    if(g!=null){
        var s=$.parseJSON(g);
        userId=s.userInfo.id;
    }
 
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getCurrentTime","userInfo.id":userId},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                var isSession=sessionStorage.getItem('session');
                if(isSession=='yes'){
                    return false;
                }
            }
            else if (result.mes == "成功") {
                var todayTimeArr=result.currentTime.split(' ')[0].split('-');
                year=parseInt(todayTimeArr[0]);
                month=parseInt(todayTimeArr[1]-1);
                var currentWeekDate=result.currentWeekDate;
                //day=
                switch (currentWeekDate) {
                    case '周日':
                        day = 0;
                        break;
                    case '星期一':
                        day =1;
                        break;
                    case '星期二':
                        day =2 ;
                        break;
                    case '星期三':
                        day=3 ;
                        break;
                    case '星期四':
                        day =4 ;
                        break;
                    case '星期五':
                        day = 5;
                        break;
                    case '星期六':
                        day = 6;
                        break;
                }
                date=parseInt(todayTimeArr[2]);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

    orgId=decodeURIComponent(window.location.search.substring(1).split('&&')[2]);
    if(orgId == 'undefined' || orgId == undefined){
        orgId='';
    }else{
        orgId=decodeURIComponent(window.location.search.substring(1).split('&&')[2]);
    }
    if(isNaN(useChooseDate)){
        useChooseDate=useChooseDate.split('=')[1];
        $.ajax({
            type: 'post',
            url: clinicHost + 'clinicOrder.action',
            cache: false,
            async:false,
            data: {
                action: "getClinicOrder",
                "orderInfoClinic.id":serDocId,"userInfo.id":userId
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    var isSession=sessionStorage.getItem('session');
                    if(isSession=='yes'){
                        return false;
                    }
                   
                }
                else if (result.mes == "成功") {
                    serDocId=result.orderInfoClinic.doctorInfo.id;
                    orderId=result.orderInfoClinic.id;
                    appointmentBeganTime=result.orderInfoClinic.appointmentBeganTime;
                    otherName=result.clinicOtherContactsInfo.otherName;
                    otherPhone=result.clinicOtherContactsInfo.otherPhone;
                }
            },
            error: function () {
                layer();
            }
        });
    };
});
//得到今后一周的日期
$(function () {
    if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
        days = 31;
    }
    else if (month == 3 || month == 10 || month == 5 || month == 8) {
        days = 30;
    }
    else {
        if (year % 4 != 0) {
            days = 28
        }
        else {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
            } else {
                days = 29
            }
        }
    }
    var dayArr = ['今天', '明天', '后天'];
    var daySum = day + 3;
    for (var j = 0; j < 11; j++) {
        if (daySum > 6) {
            daySum = daySum - 7;
        }
        if (daySum == 0) {
            dayArr.push('周日');
        }
        else if (daySum == 1) {
            dayArr.push('周一');
        }
        else if (daySum == 2) {
            dayArr.push('周二');
        }
        else if (daySum == 3) {
            dayArr.push('周三');
        }
        else if (daySum == 4) {
            dayArr.push('周四');
        }
        else if (daySum == 5) {
            dayArr.push('周五');
        }
        else if (daySum == 6) {
            dayArr.push('周六');
        }
        daySum++;
    }
    var dateList = $('.weekdate');
    var dayList = $('.weekday');
    for (var i = 0; i < 14; i++) {
        if (date < 10) {
            if (month < 9) {
                dateArr.push(year + '-0' + (month + 1) + '-0' + date);
            } else {
                dateArr.push(year + '-' + (month + 1) + '-0' + date);
            }
        } else {
            if (month < 9) {
                dateArr.push(year + '-0' + (month + 1) + '-' + date);
            } else {
                dateArr.push(year + '-' + (month + 1) + '-' + date);
            }
        }
        $(dateList).eq(i).text(date);
        $(dayList).eq(i).text(dayArr[i]);
        date++;
        if (date > days) {
            date = date - days;
            month++;
            if (month > 11) {
                month = 0;
                year++;
            }
        }
    }
});
//得到数据和日期做比较
$(function () {
    var addressArr = [];
    var addressList;
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicBooking.action',
        cache: false,
        async:false,
        data: {
            action: "getDoctorMorePracticeList",
            "doctorInfo.id": serDocId,
            "doctorMorePractice.doctorMorePracticeOrgInfo.hospitalBasicInfo.id":orgId,"userInfo.id":userId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                var isSession=sessionStorage.getItem('session');
                if(isSession=='yes'){
                    return false;
                }
         
            }
            else if (result.mes == "成功") {
                var list = result.doctorMorePracticeFoList;
                var doctor = result.doctorInfo;
                $('tbody').append('<tr onclick="chooseContact(this)"> <td class="contactName">' + result.userInfo.babyName +
                '</td> <td class="contactPhone">' + result.userInfo.phone +
                '</td> <td class="isChecked" isCheck="y">' +
                '<img src="'+hostMain+'images/images_family_doctor/true.png" alt=""/></td> </tr> ');
                $('#doctorName').html(doctor.doctorName);
                $('#hospital').html(doctor.department+' | '+doctor.doctorTitle);
                $('.description>img').prop('src', hostBG + 'images/doctorFaceIcon/' + doctor.doctorImage);
                for (var i = 0; i < 14; i++) {
                    for (var j = 0, len = list.length; j < len; j++) {
                        if (dateArr[i] == list[j].clinicDate) {
                            recentArr.push(i);
                            addressList = list[j].doctorMorePracticeList;
                            addressArr.push(addressList);
                            for(var k= 0,le=addressList.length;k<le;k++){
                                if (addressList[k].clinicOrgType == 0) {
                                    $('#recentTime>li').eq(i).addClass('doctorAppoint');
                                } else {
                                    $('#recentTime>li').eq(i).addClass('areaAppoint');
                                }
                            }
                        }
                    }
                }
            }
            for (var ii = 0; ii < addressArr.length; ii++) {
                for (var mm = 0; mm < addressArr[ii].length; mm++) {
                    if (mm == 0) {
                        var doctorClinicTimeSegmentList=addressArr[ii][mm].doctorClinicTimeSegmentList;
                        var timeSet=doctorClinicTimeSegmentList[0].segment;
                        var isCanUse=doctorClinicTimeSegmentList[0].isCanUse;
                        for(var nn=1;nn<doctorClinicTimeSegmentList.length;nn++){
                            timeSet+=','+doctorClinicTimeSegmentList[nn].segment;
                            isCanUse+=','+doctorClinicTimeSegmentList[nn].isCanUse;
                        }
                        $('#addressContainer').append('<div data-org="' + addressArr[ii][mm].clinicOrgType + '"' +
                        'data-time-list="' + timeSet +
                        '"data-use-list="' + isCanUse +
                        '"data-isAddClinic="' + addressArr[ii][mm].isAddClinic +
                        '"data-clinicEndTime="' + addressArr[ii][mm].clinicEndTime +
                        '" index="' + recentArr[ii] + '" class="address" style="display: none"> ' +
                        '<div class="addressImg">' +
                        '<img src="'+hostMain+'images/images_family_doctor/true.png" alt=""/>' +
                        '</div> <div class="addressDes"> <div>' +
                        ' <div class="ssss">' + addressArr[ii][mm].clinicAddress +
                        ' </div> </div> </div> </div> ');
                    } else {
                        var doctorClinicTimeSegmentList=addressArr[ii][mm].doctorClinicTimeSegmentList;
                        var timeSet=doctorClinicTimeSegmentList[0].segment;
                        var isCanUse=doctorClinicTimeSegmentList[0].isCanUse;
                        for(var nn=1;nn<doctorClinicTimeSegmentList.length;nn++){
                            timeSet+=','+doctorClinicTimeSegmentList[nn].segment;
                            isCanUse+=','+doctorClinicTimeSegmentList[nn].isCanUse;
                        }
                        $('#addressContainer').append('<div data-org="' + addressArr[ii][mm].clinicOrgType + '"' +
                        'data-time-list="' + timeSet +
                        '"data-use-list="' + isCanUse +
                        '"data-isAddClinic="' + addressArr[ii][mm].isAddClinic +
                        '"data-clinicEndTime="' + addressArr[ii][mm].clinicEndTime +
                        '" index="' + recentArr[ii] + '" class="address" style="display: none"> ' +
                        '<div class="addressImg">' +
                        '<img src="'+hostMain+'images/images_family_doctor/select.png" alt=""/>' +
                        '</div> <div class="addressDes"> <div>' +
                        ' <div class="ssss">' + addressArr[ii][mm].clinicAddress +
                        ' </div> </div> </div> </div> ');
                    }
                }
            }
            loadDetail();
        },
        error: function () {
            //alert('you are false');
            layer();
        }
    });
    
    //选择时间段
});
function loadDetail(){
	for (var kk = 0; kk < 14; kk++) {
        if ($('#recentTime>li').eq(kk).find('.weekdate').text() == useChooseDate) {
            $('#recentTime>li').eq(kk).addClass('activeAppoint');
        }
    }
    $('#addressContainer>.address[index=' + $('.activeAppoint').attr('index') + ']').css({display: 'block'});
    address = $('#addressContainer>.address[index=' + $('.activeAppoint').attr('index') + ']').eq(0).find('.ssss').text(); 
    org = $('#addressContainer>.address[index=' + $('.activeAppoint').attr('index') + ']').eq(0).attr('data-org');
    var checkedTime = $('#addressContainer>.address');
    for (var qq = 0; qq < checkedTime.length; qq++) {
        if ($(checkedTime).eq(qq).attr('index') == $('.activeAppoint').attr('index')) {
            if ($(checkedTime).eq(qq).find('img').attr('src').indexOf('true.png')>-1) {
                var timeSet=$(checkedTime).eq(qq).attr('data-time-list');
                var isCanUse=$(checkedTime).eq(qq).attr('data-use-list');
                if(timeSet.trim()==''){
                    $('#chooseAppointmentTime').append('<li data-clinicEndTime="'+$(checkedTime).eq(qq).attr('data-clinicEndTime')+'">余号' + $(checkedTime).eq(qq).attr('data-isAddClinic') + '</li>');
                }else{
                    var dateTimeList = timeSet.split(',');
                    var dateUseList = isCanUse.split(',');
                    var dateTimeHtml = '';
                    var s;
                    for (var aa = 0; aa < dateTimeList.length; aa++) {
                        dateTimeHtml += '<li class="'+dateUseList[aa]+'">' + dateTimeList[aa] + '</li>';
                        if(dateUseList[aa]=='Y'){
                            s=true;
                        }
                    }
                    $('#chooseAppointmentTime').html(dateTimeHtml);
                    if(!s){
                        $('#chooseAppointmentTime').append('<li style="background:#ff813d" data-clinicEndTime="'+$(checkedTime).eq(qq).attr('data-clinicEndTime')+'">加号' + $(checkedTime).eq(qq).attr('data-isAddClinic') + '</li>');
                        $('h5').html('<input type="button" style="background: #ccc"/> 表示该时间已被预约&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="background: #ff813d"/> 表示门诊可加号数');
                    }else{
                        $('h5').html('<input type="button" style="background: #ccc"/> 表示该时间已被预约&nbsp;&nbsp;&nbsp;&nbsp;');
                    }
                }
//                    if(dateTimeList.length==0){
//                        $('#chooseAppointmentTime').html('<li>+</li>');
//                    }else{
//                        for (var aa = 0; aa < dateTimeList.length; aa++) {
//                            dateTimeHtml += '<li>' + dateTimeList[aa] + '</li>'
//                        }
//                        $('#chooseAppointmentTime').html(dateTimeHtml);
//                    }
            }
        }
    }
    //var li=$('#chooseAppointmentTime>li');
    //$(li).each(function () {
    //    if($(this).text()==appointmentBeganTime){
    //        $(this).addClass('activeStyle');
    //        $(this).append('<img src="images/images_family_doctor/true.png" alt=""/>');
    //    }
    //});
    if(otherName != '' && otherPhone!= ''){
        $('tbody').html('<tr onclick="chooseContact(this)"> <td class="contactName">' + otherName +
        '</td> <td class="contactPhone">' + otherPhone +
        '</td> <td class="isChecked" isCheck="y">' +
        '<img src="'+hostMain+'images/images_family_doctor/true.png" alt=""/></td> </tr> ');
    }
    choTime();
    $('.address').click(function () {
        $('#chooseAppointmentTime>li').removeClass('activeStyle');
        $('#chooseAppointmentTime>li>img').remove();
        thisTime='';
        var index = $(this).attr('index');
        $('.address[index=' + index + ']').find('img').prop('src',hostMain+ 'images/images_family_doctor/select.png');
        $(this).find('img').prop('src', hostMain+'images/images_family_doctor/true.png');
        var timeSet1 = $(this).attr('data-time-list');
        var isCanUse1 = $(this).attr('data-use-list');
        if(timeSet1.trim()==''){
            $('#chooseAppointmentTime').append('<li data-clinicEndTime="'+$(this).attr('data-clinicEndTime')+'">余号' + $(this).attr('data-isAddClinic') + '</li>');
        }else{
            var dateTimeList1 = timeSet1.split(',');
            var dateUseList1 = isCanUse1.split(',');
            var dateTimeHtml1 = '';
            var s;
            for (var aa = 0; aa < dateTimeList1.length; aa++) {
                dateTimeHtml1 += '<li class="'+dateUseList1[aa]+'">' + dateTimeList1[aa] + '</li>';
                if(dateUseList[aa]=='Y'){
                    s=true;
                }
            }
            $('#chooseAppointmentTime').html(dateTimeHtml1);
            if(!s){
                $('#chooseAppointmentTime').append('<li style="background:#ff813d" data-clinicEndTime="'+$(this).attr('data-clinicEndTime')+'">加号' + $(this).attr('data-isAddClinic') + '</li>');
                $('h5').html('<input type="button" style="background: #ccc"/> 表示该时间已被预约&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="background: #ffff00"/> 表示门诊可加号数');
            }else{
                $('h5').html('<input type="button" style="background: #ccc"/> 表示该时间已被预约&nbsp;&nbsp;&nbsp;&nbsp;');
            }
        }

        address = $(this).find('.ssss').text();
        org = $(this).attr('data-org');
        choTime();
    });

    //添加日期事件
    $('#recentTime>li').click(function () {
        if ($(this).prop('class')) {
            $('#chooseAppointmentTime>li').removeClass('activeStyle');
            $('#chooseAppointmentTime>li>img').remove();
            thisTime='';
            var index = $(this).attr('index');
            $('#addressContainer>.address').css({display: 'none'});
            $('#addressContainer>.address[index=' + index + ']').css({display: 'block'});
            $('#recentTime>li').removeClass('activeAppoint');
            $(this).addClass('activeAppoint');
            var checkedTime = $('#addressContainer>.address');
            for (var qq = 0; qq < checkedTime.length; qq++) {
                if ($(checkedTime).eq(qq).attr('index') == $('.activeAppoint').attr('index')) {
                	if ($(checkedTime).eq(qq).find('img').attr('src').indexOf('true.png')>-1) {
                        var timeSet=$(checkedTime).eq(qq).attr('data-time-list');
                        var isCanUse=$(checkedTime).eq(qq).attr('data-use-list');
                        if(timeSet.trim()==''){
                            $('#chooseAppointmentTime').append('<li data-clinicEndTime="'+$(checkedTime).eq(qq).attr('data-clinicEndTime')+'">加号' + $(checkedTime).eq(qq).attr('data-isAddClinic') + '</li>');
                        }else{
                            var dateTimeList = timeSet.split(',');
                            var dateUseList = isCanUse.split(',');
                            var dateTimeHtml = '';
                            var s;
                            for (var aa = 0; aa < dateTimeList.length; aa++) {
                                dateTimeHtml += '<li class="'+dateUseList[aa]+'">' + dateTimeList[aa] + '</li>';
                                if(dateUseList[aa]=='Y'){
                                    s=true;
                                }
                            }
                            $('#chooseAppointmentTime').html(dateTimeHtml);
                            if(!s){
                                $('#chooseAppointmentTime').append('<li style="background:#ff813d" data-clinicEndTime="'+$(checkedTime).eq(qq).attr('data-clinicEndTime')+'">加号' + $(checkedTime).eq(qq).attr('data-isAddClinic') + '</li>');
                                $('h5').html('<input type="button" style="background: #ccc"/> 表示该时间已被预约&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="background: #ffff00"/> 表示门诊可加号数');
                            }else{
                                $('h5').html('<input type="button" style="background: #ccc"/> 表示该时间已被预约&nbsp;&nbsp;&nbsp;&nbsp;');
                            }
                        }
                        //var dateTimeList = $(checkedTime).eq(qq).attr('data-time-list').split(',');
                        //var dateTimeHtml = '';
                        //for (var aa = 0; aa < dateTimeList.length; aa++) {
                        //    dateTimeHtml += '<li>' + dateTimeList[aa] + '</li>'
                        //}
                        //$('#chooseAppointmentTime').html(dateTimeHtml);
                    }
                }
            }
//                if(checkedTime.length==0){
//                    $('#chooseAppointmentTime').html('<li>+</li>');
//                }else{
//                    for (var qq = 0; qq < checkedTime.length; qq++) {
//                        if ($(checkedTime).eq(qq).attr('index') == $('.activeAppoint').attr('index')) {
//                            if ($(checkedTime).eq(qq).find('img').attr('src') == 'images/images_family_doctor/true.png') {
//                                var dateTimeList = $(checkedTime).eq(qq).attr('data-time-list').split(',');
//                                var dateTimeHtml = '';
//                                for (var aa = 0; aa < dateTimeList.length; aa++) {
//                                    dateTimeHtml += '<li>' + dateTimeList[aa] + '</li>'
//                                }
//                                $('#chooseAppointmentTime').html(dateTimeHtml);
//                            }
//                        }
//                    }
//                }
        }
        choTime();
        address = $('#addressContainer>.address[index=' + $('.activeAppoint').attr('index') + ']').eq(0).find('.ssss').text();
        org = $('#addressContainer>.address[index=' + $('.activeAppoint').attr('index') + ']').eq(0).attr('data-org');
    });
}
var thisTime = '';
var thisEndTime = '';
function choTime() {
    var mj = 15;
    $('#chooseAppointmentTime>li').click(function () {
        if($(this).hasClass('N')){
            ale('该时间已被预约，请重新选择');
            thisTime=0;
        }else{
            thisTime = $(this).text();
            if(thisTime.indexOf('加号')>-1){
                if((thisTime.substring(2))==0){
                    ale('该医生加号为0，请重新选择');
                    thisTime=0;
                }else{
                    isPlus='Y';
                    thisTime=$(this).attr('data-clinicEndTime');
                }
            }
            $('#chooseAppointmentTime>li').removeClass('activeStyle');
            $('#chooseAppointmentTime>li>img').remove();
            $(this).addClass('activeStyle');
            $(this).append('<img src="'+hostMain+'images/images_family_doctor/true.png" alt=""/>');
            var next = thisTime.split(':');
            var h = parseInt(next[0]);
            var m = parseInt(next[1]) + mj;
            if (m >= 60) {
                m = m - 60;
                h = h + 1;
                if (m < 10) {
                    m = '0' + m;
                }
                if (h < 10) {
                    h = '0' + h;
                }
            }
            else {
                if (m < 10) {
                    m = '0' + m;
                    if(h<10){
                        h="0"+h;
                    }
                }else{
                    if(h<10){
                        h='0'+h;
                    }
                }
            }
            thisEndTime = h + ':' + m;
        }
    });
}
//验证码
function getCheckwords() {
    var userPhone = $.trim($("#userPhone").val());
    if (userPhone == "") {
        ale("请先输入手机号！");
    } else if (checkMobile(userPhone)) {
        $.ajax({
            type: 'post',
            url: clinicHost + 'clinicOrder.action',
            data: {
                action: "getCheckWords",
                otherPhon: userPhone,"userInfo.id":userId
            },
            success: function (result) {
                if (result.mes == "已添加") {
                    ale("该手机号已经添加了");
                } else {
                    checkwords = result.checkWords;
                    numReduce();
                }
            },
            error: function () {
            }
        });
    } else {
        ale("请输入合法的手机号");
    }
}
//时间
var ttt = 60;
function numReduce() {
    ttt--;
    if (ttt <= 0) {
        ttt = 60;
        $('#verification-code').attr('onclick', 'getCheckwords()').text('获取验证码').css('backgroundColor', '#ff813d');
    } else {
        $('#verification-code').removeAttr('onclick').css('backgroundColor', '#dadada');
        $('#verification-code').text("(" + ttt + "s)");
        setTimeout(numReduce, 1000);
    }
}

function addContact() {
    $('#header,#container,footer').css({display: 'none'});
    $('#ccoverContainer').html('<p style="font-size:14px">为了方便医生联系，请填写联系方式</p> <p style="font-size:14px">(若您给宝宝预约，请填写随行家长的联系方式)</p> <p class="input"><lable for="contact" style="color:#222222">联系人：</lable><input id="contact" type="text" placeholder="请输入联系人姓名"/></p> <p class="input"><lable for="userPhone" style="color:#222222">手机号：</lable><input id="userPhone" type="number" placeholder="请输入联系人手机号"/></p> <p class="input"><lable for="verificationCode" style="color:#222222">验证码：</lable><input id="verificationCode" type="number" placeholder="请输入联系人验证码"/><span id="verification-code" onclick="getCheckwords()">发送验证码</span></p>');
    $('#addContactCover').css({display: 'block'});
}
function closeCover1() {
    $('#addContactCover').css({display: 'none'});
    $('#header,#container,footer').css({display: 'block'});
}

function closeCover() {
    if (checkwords == $('#verificationCode').val()&&checkwords!=0) {
        $('#addContactCover').css({display: 'none'});
        $('#header,#container,footer').css({display: 'block'});
        $('tbody img').prop('src', hostMain+'images/images_family_doctor/select.png');
        $('tbody .isChecked').attr('isCheck','n');
        $('tbody').append('<tr onclick="chooseContact(this)"> <td class="contactName">' + $('#contact').val() + '</td> ' +
        '<td class="contactPhone">' + $('#userPhone').val() + '</td>' +
        ' <td class="isChecked" isCheck="y"><img src="'+hostMain+'images/images_family_doctor/true.png" alt=""/></td> </tr> ');
    } else {
        ale('验证码错误，请重新输入');
    }
}
//选中联系人
function chooseContact(div) {
    $('tbody>tr img').prop('src', hostMain+'images/images_family_doctor/select.png');
    $('tbody>tr .isChecked').attr('isCheck', 'n');
    $(div).find('img').prop('src', hostMain+'images/images_family_doctor/true.png');
    $(div).find('.isChecked').attr('isCheck', 'y');
}
//下一步
function goNextStep() {
    var arr = new Array();
    var tr = $('tbody>tr');
    var isTrue = 'N';
    if (thisTime == '') {
        ale('请选择具体时间');
        return false;
    }else if(thisTime==0){
        ale('该医生加号为0，请重新选择');
        return false;
    }
    else {
        for (var i = 0, len = tr.length; i < len; i++) {
            if ($(tr).eq(i).find('.isChecked').attr('isCheck') == 'y') {
                isTrue = 'Y';
                var obj = {
                    "id": "",
                    "otherPhone": $(tr).eq(i).find('td').eq(1).text(),
                    "otherName": $(tr).eq(i).find('td').eq(0).text(),
                    "isChoose": isTrue
                }
                arr.push(obj);
            } else {
                isTrue = 'N';
            }
        }
        //alert(dateArr[parseInt($('.activeAppoint').attr('index'))]);
            //console.log(JSON.stringify(arr)+'\n'+serDocId+'\n'+dateArr[parseInt($('.activeAppoint').attr('index'))]+'\n'+thisTime+'\n'+address+'\n'+thisEndTime);
        $.ajax({
            type: 'post',
            url: clinicHost + 'clinicOrder.action',
            cache: false,
            data: {
                action: "saveOrUpdateClinicOrder",
                "othersCollectorJson": JSON.stringify(arr),
                "doctorInfo.id": serDocId,
                "orderInfoClinic.appointmentDate": dateArr[parseInt($('.activeAppoint').attr('index'))],
                "orderInfoClinic.appointmentBeganTime": thisTime,
                "orderInfoClinic.appointmentEndTime": thisEndTime,
                "orderInfoClinic.clinicAddress": address,
                "orderInfoClinic.clinicOrgType": org,
                "orderInfoClinic.isPlus": isPlus,
                "orderInfoClinic.id":orderId,"userInfo.id":userId
            },
            success: function (result) {
                if(result.mes=='成功'){
                    window.location.href = 'yjh_payAppointment.html?' + result.orderInfoClinic.id;
                }else{
                	if(result.mes=="订单已存在，请查看‘我的订单’"){
                        window.location.href = 'yjh_payAppointment.html?' + result.orderInfoClinic.id;
                        return false;
                    }
                    ale(result.mes);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }
}
