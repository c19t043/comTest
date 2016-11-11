//APP
//var host="http://kybabydev.yishangkeji.cn/main/";
//var hostBG="http://kybabydev.yishangkeji.cn/kybabyBG/admin/";


//WEI XIN
var host="http://dev.qiyico.com/main/";
var hostBG="http://dev.qiyico.com/kybabyBG/admin/";
var hostName = "http://dev.qiyico.com/";
var hostMain = "http://dev.qiyico.com/main/";
var hostOrgbusiness = "http://dev.qiyico.com/orgbusiness/";
var hostOperationbussiness = "http://dev.qiyico.com/operationbussiness/";
//微信id
var appId  = 'wxe143c55a723e1738';//测试服务器
//var appId  = 'wxc592f8c1fcf1ba64';//正式服务器
//多点执业Action
var clinicHost = "http://dev.qiyico.com/clinic/";
//会员卡
var memberHost = "http://dev.qiyico.com/memberManage/";
//妈妈圈路径
var ringHost="http://dev.qiyico.com/ring/";
//家庭医生
var familyDoctorHost="http://dev.qiyico.com/familydoctor/";
var messageHost="http://dev.qiyico.com/messagecenter/";
//照片
var photo = "http://dev.qiyico.com/platform/";
//金卫控接口
var yjhmanage = "http://dev.qiyico.com/yjhmanage/";
var hostYJH="http://dev.qiyico.com/main/yjh/";
var authYJH="http://118.123.173.101:7002/api/";
function ale(str,font){
    $('body').append('<div id="alert-cover" onclick="closeAlertCover()"> <div id="alert-content"> ' +
        '<div id="alert-line"></div> ' +
        '<div id="alert-text"> ' +
        '<div id="alert-gantanhao"><img src="'+hostMain+'images/images_login_regist/alert_gantanhao.png" alt=""/></div> ' +
        '<div class="alert-text-show">'+str+'</div> ' +
        '</div> ' +
        '</div> ' +
        '</div>');
    showAlertCover();
}
function layer(){
    $("body").append('<div id="layer"><div id="layer_main"><img src="'+hostMain+'images/error_404.png"/><p id="layer_p">404...出错啦</p><p><span id="layer_return" onclick="return_before()">返回上一页</span><span id="layer_index" onclick="return_index()">返回首页</span></p></div></div>')
}
function return_index(){
    window.location.href = "main.html";
}
function return_before(){
    window.history.go(-1);
}
function showAlertCover(){
    $('#alert-cover').show();
    $('#alert-content').animate({'top':'0'},600);
}
function closeAlertCover(){
    $("#alert-cover").remove();
}