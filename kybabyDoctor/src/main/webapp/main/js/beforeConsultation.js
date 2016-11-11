var logId;
var isTag;
var audioFileName;
var doct_img_ico = '';
var user_img_ico = '';
var lastDate = '';
var packgeId;
var submitTime;
var websocketUserId=null;
$(function () {
    logId = window.location.search.substring(1).split('&')[0];
    isTag = window.location.search.substring(1).split('&')[1];
    if(isTag=='Y'){
        $('footer').hide();
    }
    $('#container').height($(window).height()-95);
    //packgeId = window.location.search.substring(1).split('&')[1];
    histConsult();
});
function saveTagIds(){
    var hasChooseTagList=$('#hasChooseTagList>span');
    var symptomTagIds='';
    $(hasChooseTagList).each(function (index) {
        symptomTagIds+='::'+$(this).attr('data-tag-id');
    });
    symptomTagIds=symptomTagIds.substring(2);
    $.ajax({
        type: 'post',
        async: false,
        url: host + 'consultManage.action',
        data: {
            action: "addSymptomTag",
            "logId":logId,
            "symptomTagIds":symptomTagIds
        },
        success: function (result) {
            if (result.mes == "成功") {
                ale('操作成功');
                setTimeout(function () {
                    window.location.href = 'consultation.html';
                }, 2500);
            }
        }
    });
}
function getAllSymptomTag(){//疾病列表
    $.ajax({
        type: 'post',
        async: true,
        url: host + 'consultManage.action',
        data: {
            action: "getAllSymptomTag"
        },
        success: function (result) {
            if (result.mes == "成功") {
                var allSymptomTagList=result.commonDiseaseList;
                if(allSymptomTagList!=null){
                    var html='';
                    for (var i= 0,len=allSymptomTagList.length;i<len;i++){
                        html+='<span data-tag-name="'+allSymptomTagList[i].allName+'" data-tag-id="'+allSymptomTagList[i].id+'">'+allSymptomTagList[i].allName+'</span>';
                    }
                    $('#canChooseTagList').html(html);
                    tagListEvent();
                }
            }
        }
    });
}
function tagListEvent(){//选择疾病
    var iic_height=$('#illness_impression_content').height();
    var constant=187;
    var remain=iic_height-constant;
    $('#canChooseTagList').height(remain-$('#hasChooseTagList').height());
    $('#canChooseTagList>span').click(function () {
        var _this=this;
        $(_this).toggleClass('selected');
        if($(_this).hasClass('selected')){
            var clone=$(_this).clone();
            $('#hasChooseTagList').append(clone);
        }else{
            $('#hasChooseTagList>span[data-tag-id='+$(_this).attr("data-tag-id")+']').remove();
        }
        $('#canChooseTagList').height(remain-$('#hasChooseTagList').height());
        hasChooseTagList(remain);
    });
    var bind_name="input";//定义所要绑定的事件名称
    if(navigator.userAgent.indexOf("MSIE")!=-1) bind_name="propertychange";//判断是否为IE内核 IE内核的事件名称要改为propertychange
    /*输入框键盘离开事件绑定*/
    $("#search input").bind(bind_name,function(){
        $('#canChooseTagList>span').hide();
        if(this.value!=null&&this.value!=""){
            $('#canChooseTagList>span[data-tag-name*='+this.value+']').show();
        }else{
            $('#canChooseTagList>span').show();
        }
    });
}
function hasChooseTagList(remain){//取消疾病
    $('#hasChooseTagList>span').click(function () {
        var _this=this;
        //console.log($(_this).attr("data-tag-id"));
        $('#canChooseTagList>span[data-tag-id='+$(_this).attr("data-tag-id")+']').removeClass('selected');
        $(_this).remove();
        $('#canChooseTagList').height(remain-$('#hasChooseTagList').height());
    });
}
function histConsult() {
    $.ajax({
        type: 'post',
        async: false,
        url: host + 'consultManage.action',
        data: {action: "detail", logId: logId,'isEnd':isTag},
        success: function (result) {
            user_img_ico = result.userImg.toString().split('::')[0];
            doct_img_ico = result.doctorImg;

            if (doct_img_ico == null || doct_img_ico == 'null' || doct_img_ico == '') {
                doct_img_ico = 'lt_doctor.png';
            }
            var userMes;
            if (result.consultBabyInfo != null) {
                userMes = result.consultBabyInfo;
                websocketUserId=userMes.userInfo.id;
                $("#baby_name").text(userMes.babyName);
                $("#baby_sex").text(userMes.sex);
                $.ajax({
                    type:'post',
                    url:urlWay.clinicHost+'doctorClinic.action',
                    cache:false,
                    async:false,
                    data:{action : "getCurrentTime"},
                    success:function(result){
                        if (result.mes == '请登录') {
                            ale('请登录', '24px');
                            window.location.href = "login.html";
                        }
                        else if (result.mes == "成功") {
                            var date=result.currentTime.split(' ')[0];
                            var year=date.split('-')[0];
                            var month=date.split('-')[1];
                            var nowYear=year-userMes.birthday.split('-')[0];
                            var nowMonth=month-userMes.birthday.split('-')[1];
                            var babyMonth=nowYear*12+nowMonth;
                            if(babyMonth<12){
                                babyMonth=babyMonth+'个月';
                            }else{
                                if(babyMonth%12==0){
                                    babyMonth=Math.floor(babyMonth/12)+'岁';
                                }else{
                                    babyMonth=Math.floor(babyMonth/12)+'岁'+babyMonth%12+'个月';
                                }
                            }
                            $("#baby_birth").text(babyMonth);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {

                    }
                });
                $("#daohanglan,title").text('与' +  result.userImg.toString().split('::')[1] + '咨询');
            }
            if(user_img_ico==null || user_img_ico==''|| user_img_ico== 'null'){
                user_img_ico = 'userFaceIcon/lt_user.png';
            }else{
                user_img_ico='userFaceIcon/'+user_img_ico;
            }
            if (result.tagNameList != null) {
                for (var i = 0; i < result.tagNameList.length; i++) {
                    $("#tagList").append("<span class='biaoqian'>" + result.tagNameList[i] + "</span>");
                }
            }
            $('#liaotian').html('');
            for (var j = 0; j < result.someConsultList.length; j++) {

                var color = "#fff";
                var background = "#4fc1e9";
                if (result.someConsultList[j].doctorReply.toString().substring(0, 10).indexOf('导诊建议') != -1) {
                    result.someConsultList[j].doctorReply = "<span class='dzjy'>导诊建议：</span>" + result.someConsultList[j].doctorReply.toString().substring(5);
                    color = "rgb(229,78,10)";
                    background = "rgb(255,195,65)";
                }
                var showTime='';
                if(j==0){
                    submitTime=result.someConsultList[j].submitTime;
                    showTime=submitTime;
                }else {
                    var nextSubmitTime=result.someConsultList[j].submitTime;
                    var compareTime=periodTime(nextSubmitTime);
                    if(compareTime!=false){
                        showTime=compareTime;
                    }
                }
                if (result.someConsultList[j].isReply == "N") {//用户
                    if (result.someConsultList[j].symptomImage != null && result.someConsultList[j].symptomImage != "") {//有图
                        if (result.someConsultList[j].msgType == 2) {//语言
                            $("#liaotian").append(
                                "<div class='consulleft'>" +
                                "<div class='consulleft_photo'><img src='" + hostBG + "images/" + user_img_ico + "' width='40'></div>" +
                                "<div class='consulleft_txt'>" +
                                "<p class='time'>" + showTime + "</p>" +
                                "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + result.someConsultList[j].doctorReply + "'></audio></p>" +
                                "<p class='zz_pic showlargeimage'><img src='" + hostBG + "images/consultPicture/" + result.someConsultList[j].symptomImage + "' /></p>" +
                                "</div>" +
                                "</div>"
                            );
                        } else {
                            var symptomDescribe='';
                            if(result.someConsultList[j].symptomDescribe!=''){
                                symptomDescribe="<p class='text'>" + result.someConsultList[j].symptomDescribe + "</p>";
                            }
                            $("#liaotian").append(
                                "<div class='consulleft'>" +
                                "<p class='time'>" + showTime + "</p>" +
                                "<div class='consulleft_photo'><img src='" + hostBG + "images/" + user_img_ico + "' width='40'></div>" +
                                "<div class='consulleft_txt'>" +
                                symptomDescribe+
                                "<p class='zz_pic showlargeimage'><img src='" + hostBG + "images/consultPicture/" + result.someConsultList[j].symptomImage + "' /></p>" +
                                "</div>" +
                                "</div>"
                            );
                        }
                    } else {
                        if (result.someConsultList[j].msgType == 2) {
                            $("#liaotian").append(
                                "<div class='consulleft'>" +
                                "<p class='time'>" + showTime + "</p>" +
                                "<div class='consulleft_photo'><img src='" + hostBG + "images/" + user_img_ico + "' width='40'></div>" +
                                "<div class='consulleft_txt'>" +
                                "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + result.someConsultList[j].doctorReply + "'></audio></p>" +
                                "</div>" +
                                "</div>"
                            );
                        } else {
                            $("#liaotian").append(
                                "<div class='consulleft'>" +
                                "<p class='time'>" +showTime+ "</p>" +
                                "<div class='consulleft_photo'><img src='" + hostBG + "images/" + user_img_ico + "' width='40'></div>" +
                                "<div class='consulleft_txt'>" +
                                "<p class='text'>" + result.someConsultList[j].symptomDescribe + "</p>" +
                                "</div>" +
                                "</div>"
                            );
                        }
                    }
                } else {
                    if (result.someConsultList[j].msgType == 2) {
                        $("#liaotian").append(
                            "<div class='consulright'>" +
                            "<p class='time'>" + showTime + "</p>" +
                            "<div class='consulright_txt'>" +
                            "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + result.someConsultList[j].doctorReply + "'></audio></p>" +
                            "</div>" +
                            "<div class='consulright_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doct_img_ico + "' width='40'></div>" +
                            "</div>"
                        );
                    } else {
                        var doctorReply='';
                        if(result.someConsultList[j].doctorReply!=''){
                            if(result.someConsultList[j].doctorReply.indexOf('family_doctor_patient.html')>-1){
                                doctorReply="<p class='text'>儿科门诊预约</p>";
                            }else if(result.someConsultList[j].doctorReply.indexOf('family_doctor_erbao.html')>-1){
                                doctorReply="<p class='text'>儿保门诊预约</p>";
                            }else if(result.someConsultList[j].doctorReply.indexOf('family_doctor_vaccine.html')>-1){
                                doctorReply="<p class='text'>计免预约</p>";
                            }else if(result.someConsultList[j].doctorReply.indexOf('family_doctor_expert_team.html')>-1){
                                doctorReply="<p class='text'>推荐团队</p>";
                            }
                            else{
                                doctorReply="<p class='text'>" + result.someConsultList[j].doctorReply + "</p>";
                            }
                        }else if(result.someConsultList[j].doctorReplyImg!=''||result.someConsultList[j].doctorReplyImg!=null){
                            doctorReply="<p class='image showlargeimage'><img src='" + hostBG + "images/consultPicture/" + result.someConsultList[j].doctorReplyImg+ "'/></p>";
                        }
                        $("#liaotian").append(
                            "<div class='consulright'>" +
                            "<p class='time'>" + showTime + "</p>" +
                            "<div class='consulright_txt'>" +
                            doctorReply+
                            "</div>" +
                            "<div class='consulright_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doct_img_ico + "' width='40'></div>" +
                            "</div>"
                        );
                    }
                }
            }
            ;
            if (result.someConsultList[result.someConsultList.length - 1].submitTime != lastDate) {

                $('#container').scrollTop(1000000);
                lastDate = result.someConsultList[result.someConsultList.length - 1].submitTime;
            }
            showLargeImage.showImage();
            //ale(result);
        }
    });
}
function tjTeam(){//推荐专家团队
    $.ajax({
        type: 'post',
        async: true,
        url: urlWay.fdmanage + 'fdUserManage.action',
        data: {action: "getDoctorAtFdTeams"},
        success: function (result) {
            if (result.mes == "操作成功") {
                var fdServiceTeamsList=result.fdServiceTeamsList;
                if(fdServiceTeamsList!=null && fdServiceTeamsList.length!=0){
                    var html='';
                    for(var i=0;i<fdServiceTeamsList.length;i++){
                        html+='<div onclick="buildBusiness(\'family_doctor_expert_team.html?'+fdServiceTeamsList[i].id+'\',\'推荐团队\')">'+fdServiceTeamsList[i].teamName+'</div>';
                    }
                    $('#tjTeam>div').html(html);
                    $('#tjTeam').show();
                }else{
                    ale('您没有参加专家团队');
                    return false;
                }
            }
        }
    });
}
function buildBusiness(type,con){//推荐业务
    var str=type;
    $.ajax({
        type: 'post',
        async: false,
        url: host + 'consultManage.action',
        data: {action: "replays", logId: logId, doctorReply: str, msgType: 1},
        success: function (result) {
            if (result.mes == "成功") {
                var showTime='';
                var nextSubmitTime=result.time;
                submitTimeHour=submitTime.split(' ')[1].split(':')[0];
                submitTimeMin=submitTime.split(' ')[1].split(':')[1];
                nextSubmitTimeHour=nextSubmitTime.split(' ')[1].split(':')[0];
                nextSubmitTimeMin=nextSubmitTime.split(' ')[1].split(':')[1];
                if((nextSubmitTimeHour-submitTimeHour)*60+nextSubmitTimeMin-submitTimeMin>5){
                    submitTime=nextSubmitTime;
                    showTime=nextSubmitTime;
                    //console.log(nextSubmitTimeHour+'=='+submitTimeHour+'=='+nextSubmitTimeMin+'=='+submitTimeMin+'=='+j);
                }
                websocketDoctorId=sessionStorage.getItem('websocketDoctorId');
                //console.log(websocketDoctorId);
                if(websocketDoctorId==null || websocketDoctorId==undefined){
                    getwebsocketDoctorId();
                }
                $('#msgText').html(con);
                var obj={"doctorId":websocketDoctorId,"submitTime":result.time,"doctorReplyImg":"","userType":2,"message":str,"msgType":1,"userId":websocketUserId,"logId":logId}
                send(obj);

            }else if(result.mes=='无数据'){
                ale('已结束');
                return_before();
            }
        }
    });
    $('#container').scrollTop(1000000);
    $(window).scrollTop($(document).height());
    $('#more').prop('class','focus');
    $('#msgText').blur();
    $('footer').css({bottom:'0'});
    $('#showBusiness,#tjTeam').hide();
}
function endOfCal() {
    var answer=confirm('确认结束本次咨询？\n结束后你将不会收到用户发送的消息');
    if(answer==false){
        return false;
    }
    var hasChooseTagList=$('#hasChooseTagList>span');
    if(hasChooseTagList.length==0){
        ale('请选择病症标签');
        return false;
    }
    if(isTag=='Y'){
        saveTagIds();
    }else{
        $.ajax({
            type: 'POST',
            url: urlWay.fdmanageHost + 'fdUserManage.action',
            async:true,
            data: {
                action: 'toEnd',
                "consult.logId": logId,
                "consult.userType": 2
            },
            success: function (result) {
                if (result.mes == '操作成功') {
                    //ale("操作成功");
                    saveTagIds();
                } else if (result.mes == '未登录') {
                    window.location.href = 'login.html';
                }
            }
        });
    }
}

function sendMsg(msgType, content) {
    if (msgType == "1" && ($("#msgText").html() == "" || $("#msgText").html() == null)) {
//		ale("请输入要发送内容");
    } else {
        if (msgType == '2') {
            $.ajax({
                type: 'post',
                async: false,
                url: host + 'consultManage.action',
                data: {action: "replays", logId: logId, doctorReply: content, msgType: msgType},
                success: function (result) {
                    if (result.mes == "成功") {
                        $("#liaotian").append(
                            "<div class='consulright'>" +
                            "<p class='time'>" + result.time + "</p>" +
                            "<div class='consulright_txt'>" +
                            "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + audioFileName + ".mp3'></audio></p>" +
                            "</div>" +
                            "<div class='consulright_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doct_img_ico + "' width='40'></div>" +
                            "</div>"
                        );
                    }else if(result.mes=='无数据'){
                        ale('已结束');
                        return_before();
                    }
                    $("#msgText").html("");
                    $('#container').scrollTop(1000000);
                    $(window).scrollTop($(document).height());
                }
            });
        } else {
            $.ajax({
                type: 'post',
                async: false,
                url: host + 'consultManage.action',
                data: {action: "replays", logId: logId, doctorReply: content, msgType: msgType},
                success: function (result) {
                    if (result.mes == "成功") {
                        websocketDoctorId=sessionStorage.getItem('websocketDoctorId');
                        //console.log(websocketDoctorId);
                        if(websocketDoctorId==null || websocketDoctorId==undefined){
                            getwebsocketDoctorId();
                        }
                        var obj={"doctorId":websocketDoctorId,"submitTime":result.time,"doctorReplyImg":"","userType":2,"message":content,"msgType":msgType,"userId":websocketUserId,"logId":logId}
                        send(obj);
                    }else if(result.mes=='无数据'){
                        ale('已结束');
                        return_before();
                    }
                }
            });
        }

    }
}


var strLocalId = "";
var strServerId = "";
var jsApiTicket = "";


$(function () {
    $('#audio').click(function () {
        if ($('#audio').attr('src') == 'images/audio_parse.png') {
            $('#audio').attr('src', 'images/zxq_15.png');
            //ale('结束录音');
            recordEnd();
        } else {
            $('#audio').attr('src', 'images/audio_parse.png');
            //ale('开始录音');
            recordStart();
        }
    });
});


function recordStart() {
    getJsApi();
    nonceStr = getNonceStr();
    timeStr = getTimeStamp();
    //ale("window.location:"+window.location.href.substring(0, window.location.href.indexOf("?")));
    var string1 = "jsapi_ticket=" + jsApiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timeStr + "&url=" + window.location.href;
    signature = hex_sha1(string1);
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端ale出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: 'wx039ad537c0a70c1b', // 必填，公众号的唯一标识
        timestamp: timeStr, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['startRecord', 'onVoiceRecordEnd', 'playVoice', 'uploadVoice', 'stopRecord', 'downloadVoice'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        wx.checkJsApi({
            jsApiList: ['stopRecord'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function (res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            }
        });
        wx.startRecord();
    });

    wx.error(function (res) {
        ale("error");
    });
}

function recordEnd() {
    wx.stopRecord({
        success: function (res) {
            strLocalId = res.localId;

            wx.uploadVoice({
                localId: strLocalId, // 需要上传的音频的本地ID，由stopRecord接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    strServerId = res.serverId; // 返回音频的服务器端ID

                    getMediaFile(strServerId);
                    //ale("fileName"+audioFileName);
                    //addNewConsultMsg(audioFileName+".mp3",'2');
                    sendMsg('2', audioFileName + ".mp3");
                }
            });
        }
    });
}

function getNonceStr() {
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    var noceStr = "";
    for (var i = 0; i < 32; i++) {
        noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return noceStr;
}

function getTimeStamp() {
    var timestamp = new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    return timestampstring;
}

function getJsApi() {
    $.ajax({
        type: 'post',
        cache: false,
        async: false,
        url: host + '../wx/getJsApi.action',
        data: {
            action: "getJsApiTicket"
        },
        success: function (result) {
            jsApiTicket = result.jsApiTicket;
        }
    });

}

//从微信服务器将语音文件上传到自己服务器
function getMediaFile(mediaId) {
    $.ajax({
        type: 'post',
        cache: false,
        async: false,
        url: host + '../wx/getMediaFile.action',
        data: {
            action: "getMediaFile",
            MEDIA_ID: mediaId,
            uniqueId: logId
        },
        success: function (result) {
            //jsApiTicket=result.jsApiTicket;
            //$("#audioPlay").attr("src","/kybabyBG/voice/"+mediaId+".mp3");
            audioFileName = result.fileName;
        }
    });

}

function playAndpaused(obj) {
    var audio = $(obj).find('audio').get(0);
    if (audio.paused) {
        audio.play();
        $(obj).attr("class", "ysk_yy_jieshu");
        setTimeout(function () {
            $(obj).attr("class", "ysk_yy");
        }, parseInt(audio.duration) * 1000);

    } else {
        audio.pause();
        $(obj).attr("class", "ysk_yy");
    }

}
function showBusiness(div){
    var cla=$(div).prop('class');
    if(cla=='focus'){
        $(div).prop('class','blur');
        $('footer').css({bottom:'75px'});
        $('#msgText').blur();
        $('#showBusiness').show();
    }else if(cla=='blur'){
        $(div).prop('class','focus');
        $('#msgText').focus();
        $('footer').css({bottom:'0'});
        $('#showBusiness').hide();
    }
}
$(function () {
    $('#msgText').click(function () {
        $('#more').prop('class','focus');
        $('footer').css({bottom:'0'});
        $('#msgText').focus();
        $('#showBusiness').hide();
    });
    //if(window.location.href.indexOf('/beforeConsultation.html') != -1){
    //	var time_set = setInterval(function(){
    //		if(window.location.href.indexOf('/beforeConsultation.html') != -1){
    //			histConsult();
    //		}
    //
    //	},10000);
    //}else{
    //	clearInterval(time_set);
    //}
});
//测试。。。。。。。。。。。。。。。。。。。。
// 参数，最大高度
var MAX_HEIGHT = 500;
// 渲染
function render(src){
    var image = new Image();
    image.onload = function(){
        var canvas = document.getElementById("myCanvas");
        var x = image.width;
        var y = image.height;

        if(image.height > MAX_HEIGHT) {
            // 宽度等比例缩放 *=
            image.width *= MAX_HEIGHT / image.height;
            image.height = MAX_HEIGHT;
        }
        var ctx = canvas.getContext("2d");  // 获取 canvas的 2d 环境对象,可以理解Context是管理员，canvas是房子
        ctx.clearRect(0, 0, canvas.width, canvas.height);// canvas清屏
//      canvas.width = image.width;  // 重置canvas宽高
//      canvas.height = image.height;
        canvas.width = image.width;  // 重置canvas宽高
        canvas.height = image.height;

        ctx.drawImage(image, 0, 0,x,y,0,0,image.width,image.height);  // 将图像绘制到canvas上
    };
    image.src = src;  // 记住必须先绑定事件，才能设置src属性，否则会出同步问题。
    setTimeout(function(){
        sendImage();
    },2000);
};
// 加载 图像文件(url路径)
function loadImage(obj){
    var src = $(obj).get(0).files[0];
    var imgType = src.name.split('.');
    imgType = imgType[imgType.length-1];//返回后缀名，以兼容部分移动端浏览器
    if(imgType == 'jpg'){
        imgType = 'jpeg';
    }
    if(!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')){
        ale('请选择图片文件');
        return false;
    }

    // 创建 FileReader 对象 并调用 render 函数来完成渲染.
    var reader = new FileReader();
    // 绑定load事件自动回调函数
    var imgData = '';
    reader.onload = function(e){
        if(e.target.result.substring(5,10) != 'image'){
            var imgDataArr = e.target.result.split('base64');
            imgData = imgDataArr[0] + "image/"+imgType+";base64"+imgDataArr[1];
            render(imgData);
        }else{
            render(e.target.result);
        }
    };
    // 读取文件内容
    reader.readAsDataURL(src);
    $('#more').prop('class','focus');
    $('#msgText').blur();
    $('footer').css({bottom:'0'});
    $('#showBusiness').hide();
};
//---------------------------------------------
// 上传图片，jQuery版
function sendImage(){
    var canvas = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。
    var dataurl = canvas.toDataURL("image/png");
    // 为安全 对URI进行编码
    // data%3Aimage%2Fpng%3Bbase64%2C 开头
    var imagedata =  encodeURIComponent(dataurl);
    $.ajax({
        type: 'post',
        async: false,
        url: host + 'consultManage.action',
        data: {action: "replays", logId: logId, doctorReply: '', msgType: 1,imagedata:imagedata},
        success: function (result) {
            if (result.mes == "成功") {
                websocketDoctorId=sessionStorage.getItem('websocketDoctorId');
                //console.log(websocketDoctorId);
                if(websocketDoctorId==null || websocketDoctorId==undefined){
                    getwebsocketDoctorId();
                }
                var obj={"doctorId":websocketDoctorId,"submitTime":result.time,"doctorReplyImg":result.doctorReplyImg,"userType":2,"message":"","userId":websocketUserId,"logId":logId}
                send(obj);
            }else if(result.mes=='无数据'){
                ale('已结束');
                return_before();
            }
        }
    });
    $('#container').scrollTop(1000000);
    $(window).scrollTop($(document).height());
};
function periodTime(nextSubmitTime){//5分钟一段时间显示
    var showTime;
    submitTimeHour=submitTime.split(' ')[1].split(':')[0];
    submitTimeMin=submitTime.split(' ')[1].split(':')[1];
    nextSubmitTimeHour=nextSubmitTime.split(' ')[1].split(':')[0];
    nextSubmitTimeMin=nextSubmitTime.split(' ')[1].split(':')[1];

    if((nextSubmitTimeHour-submitTimeHour)*60+nextSubmitTimeMin-submitTimeMin>5){
        submitTime=nextSubmitTime;
        showTime=nextSubmitTime;
        return showTime;
        //console.log(nextSubmitTimeHour+'=='+submitTimeHour+'=='+nextSubmitTimeMin+'=='+submitTimeMin+'=='+j);
    }
    return false;
}
//WebSocket即时通信
var websocket = null;
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://"+ window.location.host +"/kybaby/websocket");
}
else{
    alert('Not support websocket');
}
websocket.onerror = WSonError;
websocket.onopen = WSonOpen;
websocket.onmessage = WSonMessageNow;
websocket.onclose = WSonClose;
window.onbeforeunload = WSonBeforeUnload;
function setMessageInnerHTML(data){
    websocketDoctorId=sessionStorage.getItem('websocketDoctorId');
    if(websocketDoctorId==null || websocketDoctorId==undefined){
        getwebsocketDoctorId();
    }
    var consultationData=JSON.parse(data);
    if(consultationData.thisIsUserReply){//是用户发的信息
        if (websocketDoctorId == consultationData.doctorId && websocketUserId==consultationData.userId) {
            if(consultationData.userType=='1'){
                return false;
            }
            var showTime='';
            var nextSubmitTime=consultationData.submitTime;
            var compareTime=periodTime(nextSubmitTime);
            if(compareTime!=false){
                showTime=compareTime;
            }
            if(consultationData.symptomImage.indexOf('.jpg')>-1){//接受图片
                var symptomDescribe='';
                if(consultationData.symptomDescribe!=''){
                    symptomDescribe="<p class='text'>" + consultationData.symptomDescribe + "</p>";
                }
                $("#liaotian").append(
                    "<div class='consulleft'>" +
                    "<p class='time'>" + showTime + "</p>" +
                    "<div class='consulleft_photo'><img src='" + hostBG + "images/" + user_img_ico + "' width='40'></div>" +
                    "<div class='consulleft_txt'>" +
                    symptomDescribe+
                    "<p class='zz_pic showlargeimage'><img src='" + hostBG + "images/consultPicture/" + consultationData.symptomImage + "' /></p>" +
                    "</div>" +
                    "</div>"
                );
            }else{
                if(consultationData.msgType=="1"){//接受文本
                    $("#liaotian").append(
                        "<div class='consulleft'>" +
                        "<p class='time'>" +showTime+ "</p>" +
                        "<div class='consulleft_photo'><img src='" + hostBG + "images/" + user_img_ico + "' width='40'></div>" +
                        "<div class='consulleft_txt'>" +
                        "<p class='text'>" + consultationData.symptomDescribe + "</p>" +
                        "</div>" +
                        "</div>"
                    );
                }else if(consultationData.msgType=="2"){//接受语音

                }
            }
            clearNum();
        }
    }else{//医生发送的信息
        if (websocketDoctorId == consultationData.doctorId && websocketUserId==consultationData.userId) {
            if(consultationData.userType=='1'){
                return false;
            }
            var showTime='';
            var nextSubmitTime=consultationData.submitTime;
            var compareTime=periodTime(nextSubmitTime);
            if(compareTime!=false){
                showTime=compareTime;
            }
            if(consultationData.doctorReplyImg.indexOf('.jpg')>-1){//发送图片
                $("#liaotian").append(
                    "<div class='consulright'>" +
                    "<p class='time'>" + showTime + "</p>" +
                    "<div class='consulright_txt'>" +
                    "<p class='image showlargeimage'><img src='" + hostBG + "images/consultPicture/" + consultationData.doctorReplyImg+ "'/></p>" +
                    "</div>" +
                    "<div class='consulright_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doct_img_ico + "' width='40'/></div>" +
                    "</div>"
                );
            }else{
                if(consultationData.msgType=="1"){//发送文本
                    var color = "#fff";
                    var background = "#4fc1e9";
                    var str_con = '';
                    if ($("#msgText").html().toString().substring(0, 10).indexOf('导诊建议') != -1) {
                        str_con = "<span class='dzjy'>导诊建议：</span>" + $("#msgText").html().toString().substring(5);
                        color = "rgb(229,78,10)";
                        background = "rgb(255,195,65)";
                    } else {
                        str_con = $("#msgText").html();
                    }
                    $("#liaotian").append(
                        "<div class='consulright'>" +
                        "<p class='time'>" + showTime + "</p>" +
                        "<div class='consulright_txt'>" +
                        "<p class='text'>" + str_con + "</p>" +
                        "</div>" +
                        "<div class='consulright_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doct_img_ico + "' width='40'></div>" +
                        "</div>"
                    );
                }else if(consultationData.msgType=="2"){//接受语音

                }
            }
            $('#msgText').html('');
            $('#container').scrollTop(1000000);
            $(window).scrollTop($(document).height());
        }
    }
    $('#container').scrollTop(1000000);
    $(window).scrollTop($(document).height());
    showLargeImage.showImage();
}
function clearNum(){
    $.ajax({
        type: 'post',
        url: host + 'consultManage.action',
        cache: false,
        async: true,
        data: {
            action: "updateDoctorAlreadyRead",
            logId: logId
        },
        success: function (result) {

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
}
function WSonOpen(){
    //setMessageInnerHTML("成功进入聊天室");
}
function WSonError(){
    //setMessageInnerHTML("发生错误");
}
function WSonMessageNow(event){
    setMessageInnerHTML(event.data);
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
function send(consultMsgObj){
//  var attchJson = {"from":"user1","to":"doctor1","symptomImage":consultMsgObj.symptomImage};//自定义参数
    consultMsgObj["thisIsUserReply"]=false;
    //if(websocketDoctorId==null ||websocketDoctorId==undefined){
    //    getwebsocketDoctorId();
    //}
    //var websocketDoctorName=sessionStorage.getItem('websocketDoctorName');
    //consultMsgObj["thisIsDoctorName"]=websocketDoctorName;
    //console.log(consultMsgObj);
    websocket.send(JSON.stringify(consultMsgObj));
}