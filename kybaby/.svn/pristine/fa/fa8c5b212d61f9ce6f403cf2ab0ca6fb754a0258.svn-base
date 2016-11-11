var logId;
var isCurrentDialog;
var userImage;
var audioFileName;
var doctorIma;
$(function () {
    logId = decodeURIComponent(window.location.search).substring(1).split('&')[0];
    isCurrentDialog = decodeURIComponent(window.location.search).substring(1).split('&')[1];//判断是否结束
    if(isCurrentDialog=='N'){
        $('#myFooter').hide();
        $('.header-right').removeAttr('onclick');
        $('.header-right>span').hide();
        $('#container').height($(window).height()-95);
    }else{
        $('#container').height($(window).height()-95);
    }
    histConsult();
    //var time_set;
    //if(window.location.href.indexOf('/consult_consultation.html') != -1){
    //    time_set = setInterval(function(){
    //        if(window.location.href.indexOf('/consult_consultation.html') != -1){
    //            histConsult();
    //        }
    //    },10000);
    //}else{
    //    clearInterval(time_set);
    //}
});
//获取历史咨询记录,儿保前还是儿保后，通过typeMethod判断
var doctorId=null;
function histConsult() {
    $.ajax({
        type: 'post',
        url: hostName + 'consult/consultDialogManage.action',
        cache: false,
        async: true,
        data: {action: "getHist", userType: 2, orderId: logId},
        success: function (result) {
            if (result.mes == "成功") {
                var doctor = result.doctorInfo;
                doctorId=doctor.id;
                $("#doctor_information").html('<div id="doctor_head"><img src="' + hostBG + 'images/doctorFaceIcon/' + doctor.doctorImage + '" alt=""/></div><div id="doctor_data"><p>' + doctor.doctorName+'<span class="interval">|</span><small>'+doctor.doctorTitle + '</small></p>' +
                '<p id="doctor_other">'+doctor.doctorEmployer+'<span class="interval">|</span>'+doctor.department+'</p></div>');
                $("#text_box").html("");
                $("#doctorName").text(result.doctorName);
                if (result.histConsultList != null) {
                    logId = result.histConsultList[0].logId;
                    if (result.userImage == null || result.userImage == 'null' || result.userImage == '') {
                        userImage = 'lt_user.png';
                    } else {
                        userImage = result.userImage;
                    }
                    var status='block';
                    if(result.histConsultList[0].symptomImage==''||result.histConsultList[0].symptomImage==null){
                        status='none';
                    }
                    $("#text_box").append(
                        "<div class='consultop'>" +
                        "<div class='consultop_time'>" + result.histConsultList[0].submitTime + "</div>" +
                        "<div id='tag_zz' class='consultop_tab'></div>" +
                        "<div style='display:" + status + "' class='consultop_img showlargeimage'><img src='" + hostBG + "images/consultPicture/" + result.histConsultList[0].symptomImage + "' /></div>" +
                        "<div class='user_info'>" +
                        "<div class='user_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                        "<div style='color:" + color + ";background:" + background + "' class='user_txt'>" + result.histConsultList[0].symptomDescribe + "</div>" +
                        "</div>" +
                        "</div>"
                    );
                    for (var i = 1; i < result.histConsultList.length; i++) {
                        var color = "#fff";
                        var background = "#ff813d";
                        var color2 = "#000";
                        var background2 = "#f7f7f7";
                        var doctorImage = result.doctorInfo.doctorImage;
                        doctorIma = result.doctorInfo.doctorImage;
                        if (doctorImage == null || doctorImage == 'null' || doctorImage == '') {
                            doctorImage = 'lt_doctor.png';
                            doctorIma=doctorImage;
                        }
                        if (result.histConsultList[i].isReply=="Y") {
                            //语音内容
                            if (result.histConsultList[i].msgType == "2") {
                                $("#text_box").append(
                                    "<div class='consulleft'>" +
                                    "<div class='consulleft_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doctorImage + "' width='40'></div>" +
                                    "<div class='consulleft_txt'>" +
                                    "<p class='time'>" + result.histConsultList[i].submitTime + "</p>" +
                                    "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + result.histConsultList[i].doctorReply + "'></audio></p>" +
                                    "</div>" +
                                    "</div>"
                                );
                            }
                            //文字
                            else if (result.histConsultList[i].msgType == "1") {
                                var doctorReply='';
                                if(result.histConsultList[i].doctorReply!=''){
                                    if(result.histConsultList[i].doctorReply.indexOf('family_doctor_patient.html')>-1){
                                        doctorReply="<p class='text' onclick='window.location.href=\""+result.histConsultList[i].doctorReply+"\"'><img src='images/images_icon/appointment.png'/>儿科门诊预约</p>";
                                    }else if(result.histConsultList[i].doctorReply.indexOf('family_doctor_erbao.html')>-1){
                                        doctorReply="<p class='text' onclick='window.location.href=\""+result.histConsultList[i].doctorReply+"\"'><img src='images/images_icon/appointment.png'/>儿保门诊预约</p>";
                                    }else if(result.histConsultList[i].doctorReply.indexOf('family_doctor_vaccine.html')>-1){
                                        doctorReply="<p class='text' onclick='window.location.href=\""+result.histConsultList[i].doctorReply+"\"'><img src='images/images_icon/appointment.png'/>计免预约</p>";
                                    }else if(result.histConsultList[i].doctorReply.indexOf('family_doctor_expert_team.html')>-1){
                                        doctorReply="<p class='text' onclick='window.location.href=\""+result.histConsultList[i].doctorReply+"\"'><img class='smallImg' src='images/images_icon/tjteam.png'/>推荐团队</p>";
                                    }else{
                                        doctorReply="<p style='color:" + color2 + "' class='text'>" + result.histConsultList[i].doctorReply + "</p>";
                                    }
                                }else{
                                    doctorReply="<p class='image showlargeimage'><img src='" + hostBG + "images/consultPicture/" + result.histConsultList[i].doctorReplyImg + "'/></p>";
                                }
                                $("#text_box").append(
                                    "<div class='consulleft'>" +
                                    "<div class='consulleft_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doctorImage + "' width='40'></div>" +
                                    "<div class='consulleft_txt'>" +
                                    "<p class='time'>" + result.histConsultList[i].submitTime + "</p>" +
                                    doctorReply +
                                    "</div>" +
                                    "</div>"
                                );
                            }

                        }//用户的信息
                        else {
                            if (result.histConsultList[i].msgType == "2") {
                                $("#text_box").append(
                                    "<div class='consulright'>" +
                                    "<div class='consulright_txt'>" +
                                    "<p class='time'>" + result.histConsultList[i].submitTime + "</p>" +
                                    "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + result.histConsultList[i].symptomDescribe + "'></audio></p>" +
                                    "</div>" +
                                    "<div class='consulright_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                                    "</div>"
                                );
                            }
                            else if (result.histConsultList[i].msgType == "1") {
                                var doctorReply='';
                                if(result.histConsultList[i].symptomImage!=''){
                                    doctorReply="<p class='image showlargeimage'><img src='" + hostBG + "images/consultPicture/" + result.histConsultList[i].symptomImage + "'/></p>";
                                }else{
                                    doctorReply="<p style='color:" + color2 + "' class='text'>" + result.histConsultList[i].symptomDescribe + "</p>";
                                }
                                $("#text_box").append(
                                    "<div class='consulright'>" +
                                    "<div class='consulright_txt'>" +
                                    "<p class='time'>" + result.histConsultList[i].submitTime + "</p>" +
                                    doctorReply +
                                    "</div>" +
                                    "<div class='consulright_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                                    "</div>"
                                );
                            }
                        }

                    }
                    if(result.commonDiseaseList!=null&& result.commonDiseaseList.length!=0){
                        var commonDiseaseListHtml='<div class="illness_impress">';
                        for (var j= 0,len=result.commonDiseaseList.length;j<len;j++){
                            commonDiseaseListHtml+='<span>'+result.commonDiseaseList[j].allName+'</span>';
                        }
                        commonDiseaseListHtml+='</div>';
                        $("footer").html('<p class="illness_impression">疑似问题：</p>'+commonDiseaseListHtml).css({height:"auto"}).show();
                    }
                }
                showLargeImage.showImage();

            } else if (result.mes == "已结束") {
                ale("上一次咨询已结束");

            } else if (result.mes == "未登录") {
                ale("您还没有登录，请您登录后再来咨询");
                setTimeout(function () {
                    window.location.href = "login.html";
                }, 2500);
            }
            $('#container').scrollTop(1000000);
            $(window).scrollTop($(document).height());
        },
        error: function () {
            layer();
        }
    });
}
function addNewConsultMsg(content, msgType) {
    if (content == "") {
        return false;
    } else {
        $.ajax({
            type: 'post',
            url: hostName + 'consult/consultDialogManage.action',
            cache: false,
            async: true,
            data: {
                action: "add",
                orderId: logId,
                description: content,
                imagedata:'',
                userType:2
            },
            success: function (result) {
                if (result.mes == "成功") {
                    var consultMsgObj = result.histConsultList[0];
                    send(consultMsgObj);
                } else if (result.mes == "已结束") {
                    ale("咨询已结束");
                } else if (result.mes == "未登录") {
                    ale("您还没有登录呢！");
                    setTimeout(function () {
                        window.location.href = "login.html";
                    }, 2500);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer();
            }
        });
    }
}

function endOfCal() {
    var answer=confirm('是否结束此次购买的咨询服务');
    if(answer==false){
        return false;
    }
    $.ajax({
        type: 'post',
        url: hostName + 'consult/consultDialogManage.action',
        cache: false,
        async: true,
        data: {action: "end", orderId: logId},
        success: function (result) {
            if (result.mes == "成功") {
                ale("成功");
                window.location.href='consult_myConsulting.html';
            } else if (result.mes == "未登录") {
                ale("您还没有登录");
                setTimeout(function () {
                    window.location.href = "login.html";
                }, 2500);
            }
        },
        error: function () {
            layer();
        }
    });
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
            },
            error: function () {
                layer();
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
                    addNewConsultMsg(audioFileName + ".mp3", '2');
                    //$("#text_box").append("<audio src='"+hostBG+"voice/"+audioFileName+".mp3'></audio>");
                },
                error: function () {
                    layer();
                }
            });
        },
        error: function () {
            layer();
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
        },
        error: function () {
            layer();
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
            uniqueId: doctorId
        },
        success: function (result) {
            //jsApiTicket=result.jsApiTicket;
            //$("#audioPlay").attr("src","/kybabyBG/voice/"+mediaId+".mp3");
            audioFileName = result.fileName;
        },
        error: function () {
            layer();
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
function showBusiness(div) {
    var cla = $(div).prop('class');
    if (cla == 'focus') {
        $(div).prop('class', 'blur');
        $('footer').css({bottom: '75px'});
        $('#newMsgContent').blur();
        $('#showBusiness').show();
    } else if (cla == 'blur') {
        $(div).prop('class', 'focus');
        $('#newMsgContent').focus();
        $('footer').css({bottom: '0'});
        $('#showBusiness').hide();
    }
}
$(function () {
    $('#newMsgContent').click(function () {
        $('#more').prop('class', 'focus');
        $('footer').css({bottom: '0'});
        $('#newMsgContent').focus();
        $('#showBusiness').hide();
    });
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
function loadImage(obj) {
    var src = $(obj).get(0).files[0];
    var imgType = src.name.split('.');
    imgType = imgType[imgType.length - 1];//返回后缀名，以兼容部分移动端浏览器
    if (imgType == 'jpg') {
        imgType = 'jpeg';
    }
    if (!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')) {
        ale('请选择图片文件');
        return false;
    }

    // 创建 FileReader 对象 并调用 render 函数来完成渲染.
    var reader = new FileReader();
    // 绑定load事件自动回调函数
    var imgData = '';
    reader.onload = function (e) {
        if (e.target.result.substring(5, 10) != 'image') {
            var imgDataArr = e.target.result.split('base64');
            imgData = imgDataArr[0] + "image/" + imgType + ";base64" + imgDataArr[1];
            render(imgData);
        } else {
            render(e.target.result);
        }
    };
    // 读取文件内容
    reader.readAsDataURL(src);
    $('#more').prop('class', 'focus');
    $('#msgText').blur();
    $('footer').css({bottom: '0'});
    $('#showBusiness').hide();
};
//---------------------------------------------
// 上传图片，jQuery版
function sendImage() {


    var canvas = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。
    var dataurl = canvas.toDataURL("image/png");
    // 为安全 对URI进行编码
    // data%3Aimage%2Fpng%3Bbase64%2C 开头
    var imagedata = encodeURIComponent(dataurl);
    $.ajax({
        type: 'post',
        url: hostName + 'consult/consultDialogManage.action',
        cache: false,
        async: true,
        data: {
            action: "add",
            orderId: logId,
            description: '',
            imagedata:imagedata,
            userType:2
        },
        success: function (result) {
            if (result.mes == "成功") {
                /*for(var i=0;i<result.histConsultList.length;i++){
                 ale("第"+(i+1)+"条咨询的内容是："+result.histConsultList[i].symptomDescribe);
                 }*/
                var consultMsgObj = result.histConsultList[0];
                send(consultMsgObj);
                //$("#text_box").append(
                //    "<div class='consulright'>" +
                //    "<div class='consulright_txt'>" +
                //    "<p class='time'>" + consultMsgObj.submitTime + "</p>" +
                //    "<p class='image'><img src='" + hostBG + "images/consultPicture/" + result.symptomImage + "'/></p>" +
                //    "</div>" +
                //    "<div class='consulright_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                //    "</div>"
                //);
            } else if (result.mes == "已结束") {
                ale("咨询已结束");
            } else if (result.mes == "未登录") {
                ale("您还没有登录呢！");

                setTimeout(function () {
                    window.location.href = "login.html";
                }, 2500);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
};
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
    websocketUserId=sessionStorage.getItem('websocketUserId');
    if(websocketUserId==null || websocketUserId==undefined){
        getwebsocketUserId();
    }
    var consultationData=JSON.parse(data);
    if(consultationData.thisIsUserReply){//是用户发的信息
        if(websocketUserId==consultationData.userId && consultationData.doctorId==doctorId) {
            if(consultationData.userType=='1'){
                return false;
            }
            if(consultationData.symptomImage.indexOf('.jpg')>-1){//发送图片
                $("#text_box").append(
                    "<div class='consulright'>" +
                    "<div class='consulright_txt'>" +
                    "<p class='time'>" + consultationData.submitTime + "</p>" +
                    "<p class='image showlargeimage'><img src='" + hostBG + "images/consultPicture/" + consultationData.symptomImage + "'/></p>" +
                    "</div>" +
                    "<div class='consulright_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                    "</div>"
                );
            }else{
                if(consultationData.msgType=="1"){//发送文本
                    $("#text_box").append(
                        "<div class='consulright'>" +
                        "<div class='consulright_txt'>" +
                        "<p class='time'>" + consultationData.submitTime + "</p>" +
                        "<p class='text'>" + consultationData.symptomDescribe + "</p>" +
                        "</div>" +
                        "<div class='consulright_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                        "</div>"
                    );
                }else if(consultationData.msgType=="2"){//发送语音
                    $("#text_box").append(
                        "<div class='consulright'>" +
                        "<div class='consulright_txt'>" +
                        "<p class='time'>" + consultationData.submitTime + "</p>" +
                        "<p class='ysk_yy' onclick='playAndpaused(this)'><audio src='" + hostBG + "voice/" + audioFileName + ".mp3'></audio></p>" +
                        "</div>" +
                        "<div class='consulright_photo'><img src='" + hostBG + "images/userFaceIcon/" + userImage + "' width='40'></div>" +
                        "</div>"
                    );
                }
            }
            $('#newMsgContent').html('');
            $('#container').scrollTop(1000000);
            $(window).scrollTop($(document).height());
        }
    }else{//接受的医生的消息
        if(websocketUserId==consultationData.userId && consultationData.doctorId==doctorId) {
            if (consultationData.userType == '1') {
                return false;
            }
            var doctorReply='';
            if(consultationData.doctorReplyImg.indexOf('.jpg')==-1){
                if(consultationData.message.indexOf('family_doctor_patient.html')>-1){
                    doctorReply="<p class='text'><img class='smallImg' src='images/images_icon/appointment.png'/>儿科门诊预约</p>";
                }else if(consultationData.message.indexOf('family_doctor_erbao.html')>-1){
                    doctorReply="<p class='text'><img class='smallImg' src='images/images_icon/appointment.png'/>儿保门诊预约</p>";
                }else if(consultationData.message.indexOf('family_doctor_vaccine.html')>-1){
                    doctorReply="<p class='text'><img class='smallImg' src='images/images_icon/appointment.png'/>计免预约</p>";
                }else if(consultationData.message.indexOf('family_doctor_expert_team.html')>-1){
                    doctorReply="<p class='text' onclick='window.location.href=\""+consultationData.message+"\"'><img class='smallImg' src='images/images_icon/tjteam.png'/>推荐团队</p>";
                }else{
                    doctorReply="<p style='color: #000000' class='text'>" + consultationData.message + "</p>";
                }
            }else{
                doctorReply="<p class='image showlargeimage'><img src='" + hostBG + "images/consultPicture/" + consultationData.doctorReplyImg + "'/></p>";
            }
            $("#text_box").append(
                "<div class='consulleft'>" +
                "<div class='consulleft_photo'><img src='" + hostBG + "images/doctorFaceIcon/" + doctorIma + "' width='40'></div>" +
                "<div class='consulleft_txt'>" +
                "<p class='time'>" + consultationData.submitTime + "</p>" +
                doctorReply +
                "</div>" +
                "</div>"
            );
            $('#newMsgContent').html('');
            $('#container').scrollTop(1000000);
            $(window).scrollTop($(document).height());
            clearNum();
        }
    }
    showLargeImage.showImage();
}
function clearNum(){
    $.ajax({
        type: 'post',
        url: consult + 'consultDialogManage.action',
        cache: false,
        async: true,
        data: {
            action: "updateUserAlreadyRead",
            orderId: logId,
            fdPackageId: ''
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
    consultMsgObj["thisIsUserReply"]=true;
    websocket.send(JSON.stringify(consultMsgObj));
}