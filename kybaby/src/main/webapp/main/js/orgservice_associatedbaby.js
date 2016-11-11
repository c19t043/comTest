/**
 * Created by windows on 2016/3/28.
 */

var orgId=decodeURIComponent(window.location.search.substring(1).trim().split('&')[0]);
var birthYear;
$(function () {
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'orgManage.action',
        cache: false,
        async: false,
        data: {
            action: "getHospitalInfoList",
            "hospitalBasicInfo.isShowForUser":'Y'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }else if(result.mes=='成功'){
                var list=result.hospitalBasicInfoList;
                var html='';
                //for(var i= 0,len=list.length;i<len;i++){
                //    html+='<option value="'+list[i].id+'">'+list[i].hospitalLname+'</option>';
                ////}
                for(var i= 0,len=list.length;i<len;i++){
                    html+='<tr data-index="'+i+'" data-value="'+list[i].id+'"><td>'+list[i].hospitalLname+'</td></tr>';
                }
                $('#org').html(html);
                $('#birth').val(result.userInfo.birthday.trim());
                $('#phone').val(result.userInfo.phone);
                birthYear=result.userInfo.birthday.substring(0,4);
                if(orgId!=null && orgId!=undefined &&orgId!=""){
                    $('.personal_right>div>.menu').text($('#org tr[data-value='+orgId+']').children('td').text()).attr('data-id',orgId);
                    $('#org tr[data-value='+orgId+']').css({background:'lavender'});
                    var index=$('#org tr[data-value='+orgId+']').attr('data-index');
                }
            }
        },
        error: function () {
            layer();
        }
    });
    var winWidth;
    if (window.innerWidth) {
        winWidth = window.innerWidth;
    } else if ((document.body) && (document.body.clientWidth)) {
        winWidth = document.body.clientWidth;
    }
    if(winWidth>360){
        $('#cover>p').css({'right':'90px'});
    }else if(winWidth>400){
        $('#cover>p').css({'right':'100px'});
    }
    $('.personal_right>div').click(function () {
        $('#cover').show();
        var orgId=$('.personal_right>div>.menu').attr('data-id');
        if(!(orgId==undefined || orgId==null ||orgId == '')){
            $('#org tr').css({background:'#ffffff'});
            var index=$('#org tr[data-value='+orgId+']').attr('data-index');
            $('#cover>div>div').scrollTop(index*54);
            $('#org tr[data-value='+orgId+']').css({background:'lavender'});
        }
    });
    $('#org tr').click(function (e) {
        e.stopPropagation();
        $('.personal_right>div>.menu').text($(this).text()).attr('data-id',$(this).attr('data-value'));
        var index=$(this).attr('data-index');
        $('#cover>div>div').scrollTop(index*54);
        $('#cover').hide();
    });
    $('#cover').click(function () {
        $('#cover').hide();
    });
});
$(function () {
    var currYear = (new Date()).getFullYear();
    //$('.dw-bf>div').removeClass('dw-sel');
    //$('.dw-bf>div[data-val='+parseInt(birthYear)+']').addClass('dw-sel');

    var opt = {};
    opt.date = {preset: 'date'};
    opt.datetime = {preset: 'datetime'};
    opt.time = {preset: 'time'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
        startYear: currYear - 50, //开始年份
        endYear: currYear//结束年份
    };

    $("#birth").mobiscroll($.extend(opt['date'], opt['default']));
    var optDateTime = $.extend(opt['datetime'], opt['default']);
//		$("#appDate").mobiscroll(optDateTime).datetime(optDateTime);
//		$("#appTime").mobiscroll(optTime).time(optTime);
});

$(function () {
    $('#footer p').click(function () {
        var birth=$('#birth').val().trim();
        var phone=$('#phone').val().trim();
        var org=$('.personal_right>div>span').attr('data-id');
        if(org==undefined){
            ale('请先选择机构！');
            return false;
        }
        if(!(checkMobile(phone)&&phone.length==11)){
            ale('您填写的手机号不存在！');
            return false;
        }
        $.ajax({
            type: 'post',
            url: hostOrgbusiness + 'vaccineManage.action',
            cache: false,
            async: false,
            data: {
                action: "saveRelationArchivesInfo",
                "archivesInfo.archivesMobile":phone,
                "archivesInfo.childrenBirthday":birth,
                "archivesInfo.hospitalBasicInfo.id":org
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }else if(result.mes=='成功'){
                    ale('关联成功');
                    if(orgId==''){
                        window.location.href = "orgservice_page.html?"+org;
                    }else{
                        window.location.href = "orgservice_page.html?"+org;
                    }
                }else if(result.mes=='未关联'){
                    ale('您填写的信息与所选机构预留信息不一致或未在机构注册');
                }
            },
            error: function () {
                layer();
            }
        });

    });
});


