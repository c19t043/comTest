var orderId = window.location.search.substring(1).split('&')[0];
var consultType = decodeURIComponent(window.location.search.substring(1).split('&')[1]);
$(function(){
    $('.star_1').click(function(){
        var num = $(this).attr('mark');
        $('#star_1_plus img').filter(function(){
            return $(this).attr('mark') <= num;
        }).attr('src','images/icon_star.png');
        $('#star_1_plus img').filter(function(){
            return $(this).attr('mark') > num;
        }).attr('src','images/icon_starkong.png');
    });

    $('.star_2').click(function(){
        var num = $(this).attr('mark');
        $('#star_2_plus img').filter(function(){
            return $(this).attr('mark') <= num;
        }).attr('src','images/icon_star.png');
        $('#star_2_plus img').filter(function(){
            return $(this).attr('mark') > num;
        }).attr('src','images/icon_starkong.png');
    });

    $('.star_3').click(function(){
        var num = $(this).attr('mark');
        $('#star_3_plus img').filter(function(){
            return $(this).attr('mark') <= num;
        }).attr('src','images/icon_star.png');
        $('#star_3_plus img').filter(function(){
            return $(this).attr('mark') > num;
        }).attr('src','images/icon_starkong.png');
    });
    $.ajax({
        type:'post',
        url:hostName+'consult/consultDialogManage.action',
        cache:false,
        async:true,
        data:{
            action:"getDoctorByConsult",
            "orderId":orderId
        },
        success:function(result){
            if(result.mes == '成功'){
                $('#doctor_information').html('<div id="doctor_head"><img src="'+hostBG+'images/doctorFaceIcon/'+result.doctorInfo.doctorImage+'" alt=""/></div>' +
                '<div id="doctor_data">' +
                '<p>'+result.doctorInfo.doctorName+'</p>' +
                '<p id="doctor_other">'+result.doctorInfo.department+'<span class="interval">|</span>'+result.doctorInfo.doctorTitle+'<span class="interval">|</span>'+result.doctorInfo.clinicalExperience+'年临床经验</p> ' +
                '<p id="doctor_specialty">'+result.doctorInfo.doctorEmployer+'</p>' +
                '</div>'
                );
            }else if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
        },
        error: function () {
            layer();
        }
    });

});

function submit(){
    var a = $('#star_1_plus img').filter(function(){
        return $(this).attr('src') == 'images/icon_star.png';
    });
    var b = $('#star_2_plus img').filter(function(){
        return $(this).attr('src') == 'images/icon_star.png';
    });
    var c = $('#star_3_plus img').filter(function(){
        return $(this).attr('src') == 'images/icon_star.png';
    });
    var commentContent=$('#text_evaluation>textarea').val();
    if(commentContent==''){
        ale('请输入评价内容');
        return false;
    }
    $.ajax({
        type:'post',
        url:hostName+'consult/consultDialogManage.action',
        cache:false,
        async:true,
        data:{
            action:"assess",
            "evaluate.serviceStarLevel":a.length,
            "evaluate.dutyStarLevel":b.length,
            "evaluate.onTimeStarLevel":c.length,
            "evaluate.content":commentContent,
            "evaluate.evaluateStatus":"待审核",
            "orderId":orderId,
            "evaluate.comments":consultType
        },
        success:function(result){
            if(result.mes == '成功'){
                ale('提交成功');
                return_before();
            }else{
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
}