//APP
//var host="http://kybabydev.yishangkeji.cn/kybaby/main/";
//var hostBG="http://kybabydev.yishangkeji.cn/kybabyBG/admin/";
var host="http://localhost:8080/kybabyDoctor/main/";
var hostBG="http://localhost:8080/kybabyBG/admin/";
//医生圈路径
var ringHost="http://localhost:8080/kybabyDoctor/ring/";
//WEI XIN
//var host="";
//var hostBG="/kybabyBG/admin/";
//医生圈路径
//var ringHost="../ring/";
//dev测试
var urlWay = {
	"host":"http://localhost:8080/",
	"hostName":"http://localhost:8080",
	"orderHost":"http://localhost:8080/kybabyDoctor/doctorOrder/",
    "clinicHost":"http://localhost:8080/kybabyDoctor/doctorClinic/",
    "familyDoctorHost":"http://localhost:8080/kybabyDoctor/familyDoctor/",
    "orgBusinessHost":"http://localhost:8080/kybabyDoctor/orgBusiness/",//机构
    "fdmanage":"http://localhost:8080/kybabyDoctor/fdmanage/",//用户
    "fdmanageHost":"http://localhost:8080/kybabyDoctor/fdmanage/",
    "doctorDataGather":"http://localhost:8080/kybabyDoctor/DoctorDataGather/"
};
var appId  = 'wxe143c55a723e1738';//测试服务器
//var appId  = 'wxc592f8c1fcf1ba64';//正式服务器

function ale(str,font){
    $('body').append('<div id="alert-cover" onclick="closeAlertCover()"> <div id="alert-content"> ' +
    '<div id="alert-line"></div> ' +
    '<div id="alert-text"> ' +
    '<div id="alert-gantanhao"><img src="images/images_login_regist/alert_gantanhao.png" alt=""/></div> ' +
    '<div class="alert-text-show">'+str+'</div> ' +
    '</div> ' +
    '</div> ' +
    '</div>');
    showAlertCover();
}
function showAlertCover(){
    $('#alert-cover').show();
    $('#alert-content').animate({'top':'0'},600);
}
function closeAlertCover(){
    $("#alert-cover").remove();
}
function layer(){
    $("body").append('<div id="layer"><div id="layer_main"><img src="images/error_404.png"/><p id="layer_p">404...出错啦</p><p><span id="layer_return" onclick="return_before()">返回上一页</span><span id="layer_index" onclick="return_index()">返回首页</span></p></div></div>')
}
function return_index(){
    window.location.href = "consultation.html";
}
function link_to(url){
    window.location.href = url;
}
function return_before(){
    window.history.back();
}
//WebSocket
var websocket = null;
var websocketDoctorId=null;
var websocketDoctorName='';
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://"+ urlWay.hostName.substring(7)+"/kybaby/websocket");
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
    //alert("ws://"+ urlWay.hostName.substring(7)+"/kybaby/websocket");
    //setMessageInnerHTML("成功进入聊天室");
}
function WSonError(){
    //setMessageInnerHTML("发生错误");
}

function getwebsocketDoctorId(){//获取医生id
    $.ajax({
        type:'post',
        url:host+'personZone.action',
        async:false,
        data:{action:"recommend"},
        success:function(result){
            if(result.mes=='成功'){
                websocketDoctorId=result.doctorInfo.id;
                //websocketDoctorName=result.doctorInfo.doctorName;
                sessionStorage.setItem('websocketDoctorId',result.doctorInfo.id);
                //sessionStorage.setItem('websocketDoctorName',result.doctorInfo.doctorName);
            }
        },
        error: function () {
            layer();
        }
    });
}
function WSonMessage(event){
    websocketDoctorId=sessionStorage.getItem('websocketDoctorId');
    //console.log(websocketDoctorId);
    if(websocketDoctorId==null || websocketDoctorId==undefined){
        getwebsocketDoctorId();
    }
    var data=event.data;
    var url=window.location.href;
    //console.log(JSON.parse(data));
    if(url.indexOf('/beforeConsultation.html')==-1 && url.indexOf('/afterConsultation.html') == -1){
        var consultationData=JSON.parse(data);
        if(consultationData.thisIsUserReply) {//是接受用户的信息
            if(websocketDoctorId==consultationData.doctorId){
                if(url.indexOf('/consultation.html')>-1){
                    window.location.reload();
                    return false;
                }
                var consultationObj=$('#consultation-notice');
                if(consultationData.userType=='2'){
                    var con=consultationData.symptomDescribe;
                    if(consultationData.symptomImage.indexOf('.jpg')>-1){
                        con='[图片]';
                    }
                    if(consultationObj.length==0){
                        $('body').append('<div id="consultation-notice">' +
                        '<div class="consultation-notice" onclick="toConsultation(\'beforeConsultation.html?'+consultationData.logId+'&N\')"><img src="img/consult_notice.png" alt=""/>'+con+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>' +
                        '</div>');
                    }else{
                        consultationObj.html('<div class="consultation-notice" onclick="toConsultation(\'beforeConsultation.html?'+consultationData.logId+'&N\')"><img src="img/consult_notice.png" alt=""/>'+con+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>');
                    }
                }else if(consultationData.userType=='1'){
                    var userReply=consultationData.symptomDescribe;
                    if(consultationData.symptomImage.indexOf('.jpg')>-1){
                        userReply='[图片]';
                    }
                    if(consultationObj.length==0){
                        $('body').append('<div id="consultation-notice">' +
                        '<div class="consultation-notice" onclick="toConsultation(\'afterConsultation.html?'+consultationData.logId+'&'+consultationData.fdPackageId+'&N\')"><img src="img/consult_notice.png" alt=""/>'+userReply+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>' +
                        '</div>');
                    }else{
                        consultationObj.html('<div class="consultation-notice" onclick="toConsultation(\'afterConsultation.html?'+consultationData.logId+'&'+consultationData.fdPackageId+'&N\')"><img src="img/consult_notice.png" alt=""/>'+userReply+'</div><img onclick="closeNotice()" src="img/close_notice.png" alt=""/>');
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