//APP
//var host="http://kybabydev.yishangkeji.cn/main/";
//var hostBG="http://kybabydev.yishangkeji.cn/kybabyBG/admin/";


//WEI XIN
var host="http://dev.qiyico.com/main/";
var hostBG="/kybabyBG/admin/";
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
//咨询
var consult = "http://dev.qiyico.com/consult/";
//健康商城
var mall = "http://dev.qiyico.com/b2cManager/";
//中联接口
var zhonglian='http://dev.qiyico.com/platform/SpInterfaceService/';
var spInterfaceService='http://dev.qiyico.com/spInterfaceService/';
//测评
var testManager = 'http://dev.qiyico.com/testManager/';

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
function no_data(txt){
    if($(".prompt").length==0){
        $("body").append('<div class="prompt"><i class="iconfont"></i><p>'+txt+'~</p></div>');
    }
}
function no_data_two(div,txt){
    if($(".prompt").length==0){
        $(div).append('<div class="prompt"><i class="iconfont"></i><p>'+txt+'~</p></div>');
    }
}
function return_index(){
    window.location.href = "main.html";
}
function link_to(url){
    window.location.href=url;
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
//WebSocket
var websocket = null;
var websocketUserId=null;
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://"+ window.location.host +"/kybaby/websocket");
}
else{
    alert('Not support websocket')
}

websocket.onerror = WSonError;

websocket.onopen = WSonOpen;

websocket.onmessage = WSonMessage;

websocket.onclose = WSonClose;

window.onbeforeunload = WSonBeforeUnload;

function WSonOpen(){
    //setMessageInnerHTML("成功进入聊天室");
}
function WSonError(){
    //setMessageInnerHTML("发生错误");
}

function getwebsocketUserId(){//获取用户id
    $.ajax({
        type:'post',
        url:host+'getUserInfo.action',
        async:false,
        data:{action:"getUser"},
        success:function(result){
            if(result.mes=='操作成功'){
                websocketUserId=result.usr.id;
                sessionStorage.setItem('websocketUserId',result.usr.id);
            }
        },
        error: function () {
            layer();
        }
    });
}
function WSonMessage(event){
    getwebsocketUserId();
    websocketUserId=sessionStorage.getItem('websocketUserId');
    if(websocketUserId==null || websocketUserId==undefined){
        getwebsocketUserId();
    }
    var data=event.data;
    console.log(data);
    var url=window.location.href;
    if(url.indexOf('/consult_consultation.html')==-1 && url.indexOf('/consultation.html') == -1){
        var consultationData=JSON.parse(data);
        if(!consultationData.thisIsUserReply) {//是用户接受的信息
            if(websocketUserId==consultationData.userId){
                if(url.indexOf('/consult_myConsulting.html')>-1){
                    window.location.reload();
                    return false;
                }
                var consultationObj=$('#consultation-notice');
                if(consultationData.userType=='2'){
                    var con=consultationData.message;
                    if(consultationData.doctorReplyImg.indexOf('.jpg')>-1){
                        con='[图片]';
                    }
                    if(consultationObj.length==0){
                        $('body').append('<div id="consultation-notice">' +
                        '<div class="consultation-notice" onclick="toConsultation(\'consult_consultation.html?'+consultationData.logId+'&Y\')"><img src="img/consult_notice.png" alt=""/>'+con+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>' +
                        '</div>');
                    }else{
                        consultationObj.html('<div class="consultation-notice" onclick="toConsultation(\'consult_consultation.html?'+consultationData.logId+'&Y\')"><img src="img/consult_notice.png" alt=""/>'+con+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>');
                    }
                }else if(consultationData.userType=='1'){
                    var doctorReply=consultationData.message;
                    if(consultationData.doctorReplyImg.indexOf('.jpg')>-1){
                        doctorReply='[图片]';
                    }else{
                        if(consultationData.message.indexOf('family_doctor_patient.html')>-1){
                            doctorReply="儿科门诊预约";
                        }else if(consultationData.message.indexOf('family_doctor_erbao.html')>-1){
                            doctorReply="儿保门诊预约";
                        }else if(consultationData.message.indexOf('family_doctor_vaccine.html')>-1) {
                            doctorReply = "计免预约";
                        }
                    }
                    if(consultationObj.length==0){
                        $('body').append('<div id="consultation-notice">' +
                        '<div class="consultation-notice" onclick="toConsultation(\'consultation.html?doctorId='+consultationData.doctorId+'&LogId='+consultationData.logId+'&userType=1&fdServicePackageId=1\')"><img src="img/consult_notice.png" alt=""/>'+doctorReply+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>' +
                        '</div>');
                    }else{
                        consultationObj.html('<div class="consultation-notice" onclick="toConsultation(\'consultation.html?doctorId='+consultationData.doctorId+'&LogId='+consultationData.logId+'&userType=1&fdServicePackageId=1\')"><img src="img/consult_notice.png" alt=""/>'+doctorReply+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>');
                    }
                }
            }
        }
    }
}
function toConsultation(url){
    window.location.href=url;
}
function WSonClose(){
    //setMessageInnerHTML("退出聊天室");
}

function closeWebSocket(){
    websocket.close();
}
function WSonBeforeUnload(){
    websocket.close();
}
function closeNotice(){
    $('#consultation-notice').remove();
}
//function send(){
//    var attchJson = {"from":"user1","to":"doctor1","content":message};
////           websocket.send(NowNickName + ' 说:' +message);
//    websocket.send(JSON.stringify(attchJson));
//}
//$(document).ready(function () {   //防止在DOM元素加载完成就执行jQuery代码，从而避免产生不必要的错误
//    $("*").keydown(function (e) {//判断按键
//        e = window.event || e || e.which;
//        if (e.keyCode == 123) {//禁止F12按键
//            return false;
//        }
//    });
//    //document.onhelp = function () { return false };//
//    //window.onhelp = function () { return false };//ie下面不能屏蔽f1键的补充方法,和上面的一行的效果是一样的，选其一
//});
//$(document).ready(function(){//禁止鼠标右键
//    $(document).bind("contextmenu",function(e){
//        return false;
//    });
//});
