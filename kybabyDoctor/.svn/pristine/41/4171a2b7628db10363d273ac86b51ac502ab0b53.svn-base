var doctorId;
var doctorImg;
$(function () {
    $.ajax({
        type: 'post',
        url : host+'homePageManage.action',
        cache: false,
        async: false,
        data : {action:"homePage"},
        success: function (result) {
            var doctor = result.doctorInfo;
            doctorId=doctor.id;
            doctorImg=doctor.doctorImage;
            $('#doctorMessage').html('' +
            '<span id="doctorName">' + doctor.doctorName + '</span><br/>' +
            '<span id="hospital">' + doctor.doctorEmployer + '</span>');
            $('.description>.float-left>img').prop('src', hostBG + 'images/doctorFaceIcon/' + doctor.doctorImage);
            $('#description').html(doctor.doctorComment);
            var professional=doctor.department.split(',');
            for(var i= 0,len=professional.length;i<len;i++){
                $('#professional').append('<span>'+professional[i]+'</span>');
                $('#goodat').append('<span>'+professional[i]+'</span>');
            }
            $('.doctorLevel').html('<li>' + doctor.department + '</li><li>' + doctor.doctorTitle + '</li><li>' + doctor.clinicalExperience + '年以上</li>');
        },
        error: function () {
            alert('you are false');
        }
    });
    $('#erweima').qrcode({
        render: "canvas", //table方式
        width: 120, //宽度
        height:120, //高度
        text:urlWay.hostName+'/main/sharePointPatient.html?'+doctorId
    });
    $('#erweima').append('<img style="width:40px;height:40px;position:absolute;left:50%;margin-left:-20px;top:40px;" src="'+hostBG+ 'images/doctorFaceIcon/'+doctorImg+'">');
});
