/**
 * Created by lijingwei on 2016/5/26.
 */
var move = function(e) {
    e.preventDefault && e.preventDefault();
    e.returnValue = false;
    e.stopPropagation && e.stopPropagation();
    return false;
}
var doctorType='';
var browser={
    versions:function(){
        var u = navigator.userAgent;
        return {
            weixin: u.indexOf('MicroMessenger') > -1//是否微信 （2015-01-22新增）
        };
    }(),
    language:(navigator.browserLanguage || navigator.language).toLowerCase()
}
//取消禁止滚动
function remliste() {
    window.removeEventListener('touchmove', move);
}
//禁止滚动
function addliste() {
    window.addEventListener('touchmove', move);
}
$(function() {
    if(browser.versions.weixin){
        document.documentElement.style.overflowY = 'hidden';
        document.body.style.overflowY = 'hidden';
    }
    //remliste();
    $("#mask").height($("#container").height()+47);
    $("#content").width($(window).width());
})

window.onresize=function(){
    $("#outpatient_management input").width($(window).width()-140);
    $("#leftNav").height($(window).height());
    $("#content").width($(window).width());
    $("#leftNav ul").height($(window).height()-150);
    $("#contentNew").width($(window).width());
    if($(document).height()<=$('#content').height()){
        $('#contentNew').height($(document).height());
    }else{
        $('#contentNew').height($(document).height());
    }
}

//-------------------------------------------------

/**
 * Created by windows on 2016/3/26.
 */
$(function () {
    $.ajax({
        type : 'post',
        async : false,
        url : host+'personZone.action',
        data : {action : "headMes"},
        success : function(result) {
            //ale(result.mes);
            //updated by zhong at 2015-11-14:与autoLogin()重复
            /*if(result.mes=="请登录"){
             window.location.href="login.html";
             }*/
            if(result.mes=="成功"){
                var strList = (result.headMessage.split("::"));
                var doctorInfo = result.doctorInfo;
                if(doctorInfo.doctorType!=null){
                    doctorType = doctorInfo.doctorType;
                }else{
                    doctorType = "";
                }
                if(doctorType=='医生' || doctorType=='护士'){
                    $("#doctorLevel").text(strList[2]);
                }
                //ale(strList[0]);
                $("#doctorName").text(strList[0]);
                if(strList[1] == ''){
                    $('#doctorImg>img').attr("src",hostBG+"images/doctorFaceIcon/icon_c.png");
                }else{
                    $('#doctorImg>img').attr("src",hostBG+"images/doctorFaceIcon/"+strList[1]);
                }
                $(".menuicon2").html(doctorType+'认证('+strList[4]+')');
                if(strList[4]=='已通过'){
                    $("#leftNav ul li:nth-child(5)").show();
                }else{
                    $("#leftNav ul li:nth-child(5)").hide();
                }
            }
            //if(result.mes=="成功"){
            //    //var strList = (result.headMessage.split("::"));
            //    //ale(strList[0]);
            //    console.log(result.headMessage)
            //    var doctorInfo = result.doctorInfo;
            //    $("#doctorName").text(doctorInfo.doctorName);
            //    if(doctorInfo.doctorImage == ''){
            //        $('#doctorImg>img').attr("src",hostBG+"images/doctorFaceIcon/icon_c.png");
            //    }else{
            //        $('#doctorImg>img').attr("src",hostBG+"images/doctorFaceIcon/"+doctorInfo.doctorImage);
            //    }
            //    $("#doctorLevel").text(doctorInfo.doctorTitle);
            //    $(".menuicon2").append('('+doctorInfo.doctorType+'认证)');
            //    if(doctorInfo.authentication=='已通过'){
            //        $("#leftNav ul li:nth-child(5)").show();
            //    }else{
            //        $("#leftNav ul li:nth-child(5)").hide();
            //    }
            //}
        }
    });
    $("#leftNav ul").height($(window).height()-150);
    if($(document).height()<=$('#content').height()){
        $('#contentNew').height($(document).height());
    }else{
        $('#contentNew').height($(window).height());
    }

    $("#mask").on("click", function () {
        $('#contentNew').width($(window).width());
        $('#contentNew').css({overflow:'auto'});
        $('#content').width($(window).width());
        $(this).parents("#contentNew").css({"left": "0px", "transition": ".3s"});
        $(this).css({"opacity": "0", "transition": ".5s"});
        $("#leftNav").css({"z-index":111});
        setTimeout(function () {
            $("#mask").hide();
            $("#leftNav").hide();
        }, 500);
    });
    $(".header-left").on("click", function () {
        $('#contentNew').width($('#contentNew').width()-280);
        $('#contentNew').css({overflow:'hidden'});
        //$('#content').width($('#contentNew').width()+280);
        //addliste();
        //$("body").css({overflow:"hidden"});
        $("#mask").height($("#container").height()+100);
        $(this).parents("#contentNew").css({"left": "280px", "transition": ".3s"});
        $("#leftNav").css({"z-index":2111}).show();
        $("#mask").show();
        $("#mask").css({"opacity": "1", "transition": ".5s"});
    });
    $(".speblue").click(function () {
        $("body").css({overflow:"auto"});
        $('#contentNew').css({"left": "0px", "transition": ".3s","width":$(window).width()});
        $('#content').width($(window).width());
        $("#mask").css({"opacity": "0", "transition": ".5s"});
        setTimeout(function () {
            $("#mask").hide();
            $("#leftNavl").hide();
        }, 500);
    });

});
//function serviceLoad(url) {
//    window.location.href=url;
//}
function orgOutLogin(){
    var answer=confirm('您确定要退出系统吗？');
    if(answer==false){
        return false;
    }
    $.ajax({
        type:'post',
        url:urlWay.orgBusinessHost+'orgLogin.action',
        cache:false,
        async:false,
        data:{action : "orgOutLogin"},
        success:function(result){
            if(result.mes=='成功'){
                window.location.href='login.html';
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}
//家庭医生设置初始化
function initFamilyDoctor(){
    $.ajax({
        type:'post',
        url:urlWay.familyDoctorHost+'familyDoctorServe.action',
        cache:false,
        async:false,
        data:{action : "initFamilyDoctorServe"},
        success:function(result){
            if(result.mes == "设置时间"){
                window.location.href='famillydoctortime.html';
            }else if(result.mes == "成功"){
                window.location.href='famillydoctoraddress.html';
            }
        },
        error:function(){

        }
    });
}
function tiaozhuan(obj){
    var status = $(obj).text();
    if(doctorType==""){
        window.location.href="roleselect.html";
    }else if(doctorType=="医生"){
        if(status.indexOf("已申请")>-1||status.indexOf("已通过")>-1||status.indexOf("未通过")>-1){
            window.location.href="Doctorauthentication.html?needInit";
        }else{
            window.location.href="Doctorauthentication.html";
        }
    }else if(doctorType=="技师"){
        if(status.indexOf("已申请")>-1||status.indexOf("已通过")>-1||status.indexOf("未通过")>-1){
            window.location.href="technician_authenticate.html?needInit";
        }else{
            window.location.href="technician_authenticate.html";
        }
    }else if(doctorType=="护士"){
        if(status.indexOf("已申请")>-1||status.indexOf("已通过")>-1||status.indexOf("未通过")>-1){
            window.location.href="Nurseregist.html?needInit";
        }else{
            window.location.href="Nurseregist.html";
        }
    }
}