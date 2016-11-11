/**
 * Created by lijingwei on 2016/5/26.
 */
var move = function(e) {
    e.preventDefault && e.preventDefault();
    e.returnValue = false;
    e.stopPropagation && e.stopPropagation();
    return false;
}
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
    var url = decodeURIComponent(window.location.search);
    $(".header-center").text(url.split("&")[1]);
    $("#content").width($(window).width());
})

window.onresize=function(){
    $("#outpatient_management input").width($(window).width()-140);
    $("#leftNav").height($(window).height());
    $("#content").width($(window).width());
    $("#leftNav ul").height($(window).height()-213);
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
    var ser=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgLogin.action',
        cache: false,
        async: false,
        data: {
            action: "getModuleList"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                var organModuleInfoList = result.organModuleInfoList;
                $("#community_name").text(result.organOperator.hospitalBasicInfo.hospitalLname);
                if (organModuleInfoList != null &&organModuleInfoList.length !=0) {
                    var html = '';
                    var loginName=result.organOperator.loginName;
                    $('#loginName').html(loginName+"<span style='margin-left: 15px'>欢迎登录！</span>");
                    for (var i = 0, len = organModuleInfoList.length; i < len; i++) {
                        var serviceurl=organModuleInfoList[i].pagePath+'?'+ result.organOperator.hospitalBasicInfo.id+'&'+organModuleInfoList[i].moduleName;
                        html += '<li data-ser='+organModuleInfoList[i].moduleName+' " onclick="serviceLoad(\'' + serviceurl + '\')">' + organModuleInfoList[i].moduleName + '</li>'
                    }

                    $('#leftNav ul').html(html);
                    $('#leftNav ul>li[data-ser='+ser+']').addClass('speblue').removeAttr('onclick');
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    var oldHeight = $("#leftNav ul").height();
    $("#leftNav ul").height($(window).height()-213);
    var newHeight = $("#leftNav ul").height();
    //$("#mask").on("click", function () {
    //    remliste();
    //    $("body").css({overflow:"auto"});
    //    $(this).parents("#content").css({"left": "0px", "transition": ".3s"});
    //    $(this).css({"opacity": "0", "transition": ".5s"});
    //    setTimeout(function () {
    //        $("#mask").hide();
    //    }, 500);
    //});
    //$(".header-left").on("click", function () {
    //    if(browser.versions.weixin){
    //        if(oldHeight>newHeight){
    //            ale("设备高度过低，请使用高度适宜的设备");
    //        }
    //    }
    //        addliste();
    //        $("body").css({overflow:"hidden"});
    //        $("#mask").height($("#container").height()+100);
    //        $(this).parents("#content").css({"left": "240px", "transition": ".3s"});
    //        $("#mask").show();
    //        $("#mask").css({"opacity": "1", "transition": ".5s"});
    //});
    //$('#content').height();
    //console.log($('#content').height());
    //if($(document).height()<=$('#content').height()){
    //    $('#contentNew').height($(document).height());
    //}else{
    //    $('#contentNew').height($(document).height());
    //}

    $("#mask").on("click", function () {
        $('#contentNew').width($(window).width());
        $('#content').width($(window).width());
        $(this).parents("#contentNew").css({"left": "0px", "transition": ".3s"});
        $(this).css({"opacity": "0", "transition": ".5s"});
        setTimeout(function () {
            $("#mask").hide();
        }, 500);
    });
    $(".header-left").on("click", function () {
        $('#contentNew').width($('#contentNew').width()-240);
        $('#content').width($('#contentNew').width()+240);
        //addliste();
        //$("body").css({overflow:"hidden"});
        $("#mask").height($("#container").height()+100);
        $(this).parents("#contentNew").css({"left": "240px", "transition": ".3s"});
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
        }, 500);
    });

});
function serviceLoad(url) {
    window.location.href=url;
}
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
                window.location.href='org_login.html';
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}
