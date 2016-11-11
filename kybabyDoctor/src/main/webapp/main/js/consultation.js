/**
 * Created by vinny on 2015/9/22.
 */

$(function(){
    autoLogin();
	$('.tab_item').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
        if($(this).hasClass('signing')){
            $('#signing').show();
            getConsultation(1);//家庭医生咨询
            $('#normal,#receiveOrder').hide();
            sessionStorage.setItem('consultationUserType',1);
        }else{
            $('#normal,#receiveOrder').show();
            $('#signing').hide();
            getConsultation(2);//普通咨询
            sessionStorage.setItem('consultationUserType',2);
        }
    });
    var session=sessionStorage.getItem('consultationUserType');
    if(session==1){
        $('.normal').removeClass('selected');
        $('.signing').addClass('selected');
        $('#signing').show();
        getConsultation(1);//家庭医生咨询
        $('#normal,#receiveOrder').hide();
    }else{
        getConsultation(2);
    }
});
function receiveOrder(id,isOnline){
    $('.wd>span').addClass('btnhor');
    setTimeout(function () {
        $('.wd>span').removeClass('btnhor');
    },2000);
    var te='停止接单';
    if(isOnline=='Y'){
        te='开始接单';
    }
    var answer=confirm('你确定要'+te+'吗？');
    if(answer==false){
        return false;
    }
    $.ajax({
        type : 'post',
        async: true ,
        url : host+'consultManage.action',
        data : {
            action:"saveOrUpdateConsultDoctorInfo",
            "consultDoctorInfo.id":id,
            "consultDoctorInfo.isOnline":isOnline
        },
        success : function(result) {
            if(result.mes=='成功'){
                ale('设置成功');
                window.location.reload();
            }
        }
    });
}
function getConsultation(type){
    $.ajax({
        type : 'post',
        async: true ,
        url : host+'consultManage.action',
        data : {action:"getAll",'isEnd':'',userType:type},
        success : function(result) {
            if(result.consultDoctorInfo!=null && type==2){
                $('#receiveOrder').show();
                if(result.consultDoctorInfo.isOnline=='Y'){
                    $('#receiveOrder>.con').html('已开始接单');
                    $('#receiveOrder>.wd>span').html('停止接单').attr('onclick','receiveOrder('+result.consultDoctorInfo.id+',\'N\')');
                }else{
                    $('#receiveOrder>.con').html('已停止接单');
                    $('#receiveOrder>.wd>span').html('开始接单').attr('onclick','receiveOrder('+result.consultDoctorInfo.id+',\'Y\')');
                }
            }
            if(result.mes=="无数据"){
//				$("#").append("暂无咨询记录");
            }
            if(result.mes=="成功"){
                $("#normal,#signing").html('');
                for(var i=0;i<result.userInfoStrList.length;i++){

                    var userStr = result.userInfoStrList[i].split("::");
                    var img_ico = userStr[1];
                    if(userStr[1] == 'null'){
                        img_ico = 'lt_user.png';
                    }
                    var consultStr = result.someCousultStrList[i].split("::");
                    var isadd='',isTag='N';
                    if(consultStr[7]=='不显示'){
                        continue;
                    }
                    if(consultStr[6]=='待添加病情'){
                        isadd='<div class="text-right">待添加病情</div>';
                        isTag='Y';
                    }
                    if(consultStr[3]==2){
                        if(consultStr[1]==""||consultStr[1]==null){
                            if(result.newMesNumList[i]==0){
                                var ssss='[图片]';
                                if(consultStr[4]!=null &&consultStr[4]!=''){
                                    //ssss="<img src='" + hostBG + "images/consultPicture/" + consultStr[4] + "' width='40'>";
                                };
                                $("#normal").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('beforeConsultation.html?"+consultStr[0]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+ssss+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }
                            else{
                                var ssss='[图片]';
                                if(consultStr[4]!=null &&consultStr[4]!=''){
                                    //ssss="<img src='" + hostBG + "images/consultPicture/" + consultStr[4] + "' width='40'>";
                                };
                                $("#normal").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('beforeConsultation.html?"+consultStr[0]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "<div class='number'>"+result.newMesNumList[i]+"</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+ssss+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }

                        }
                        else{
                            if(result.newMesNumList[i]==0){
                                var doctorReply;
                                if(consultStr[1].indexOf('family_doctor_patient.html')>-1){
                                    doctorReply="儿科门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_erbao.html')>-1){
                                    doctorReply="儿保门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_vaccine.html')>-1){
                                    doctorReply="计免预约";
                                }else if(consultStr[1].indexOf('family_doctor_expert_team.html')>-1){
                                    doctorReply="推荐团队";
                                } else{
                                    doctorReply=consultStr[1];
                                }
                                $("#normal").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('beforeConsultation.html?"+consultStr[0]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+doctorReply+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }
                            else{
                                var doctorReply;
                                if(consultStr[1].indexOf('family_doctor_patient.html')>-1){
                                    doctorReply="儿科门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_erbao.html')>-1){
                                    doctorReply="儿保门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_vaccine.html')>-1){
                                    doctorReply="计免预约";
                                } else{
                                    doctorReply=consultStr[1];
                                }
                                $("#normal").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('beforeConsultation.html?"+consultStr[0]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "<div class='number'>"+result.newMesNumList[i]+"</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+doctorReply+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }

                        }
                    }else{
                        if(consultStr[1]==""||consultStr[1]==null){
                            if(result.newMesNumList[i]==0){
                                var ssss='[图片]';
                                if(consultStr[4]!=null &&consultStr[4]!=''){
                                    //ssss="<img src='" + hostBG + "images/consultPicture/" + consultStr[4] + "' width='40'>";
                                };
                                $("#signing").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('afterConsultation.html?"+consultStr[0]+"&"+consultStr[5]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+

                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+ssss+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }
                            else{
                                var ssss='[图片]';
                                if(consultStr[4]!=null &&consultStr[4]!=''){
                                    //ssss="<img src='" + hostBG + "images/consultPicture/" + consultStr[4] + "' width='40'>";
                                };
                                $("#signing").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('afterConsultation.html?"+consultStr[0]+"&"+consultStr[5]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "<div class='number'>"+result.newMesNumList[i]+"</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+ssss+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }
                        }
                        else{
                            if(result.newMesNumList[i]==0){
                                var doctorReply;
                                if(consultStr[1].indexOf('family_doctor_patient.html')>-1){
                                    doctorReply="儿科门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_erbao.html')>-1){
                                    doctorReply="儿保门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_vaccine.html')>-1){
                                    doctorReply="计免预约";
                                } else{
                                    doctorReply=consultStr[1];
                                }
                                $("#signing").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('afterConsultation.html?"+consultStr[0]+"&"+consultStr[5]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+doctorReply+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }
                            else{
                                var doctorReply;
                                if(consultStr[1].indexOf('family_doctor_patient.html')>-1){
                                    doctorReply="儿科门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_erbao.html')>-1){
                                    doctorReply="儿保门诊预约";
                                }else if(consultStr[1].indexOf('family_doctor_vaccine.html')>-1){
                                    doctorReply="计免预约";
                                } else{
                                    doctorReply=consultStr[1];
                                }
                                $("#signing").append("<div class='consultation_box' onclick=\"sessionStorage.setItem('consultationUserType',"+type+");link_to('afterConsultation.html?"+consultStr[0]+"&"+consultStr[5]+"&"+isTag+"')\">"+
                                "<div class='consultation_left'>"+
                                "<div class='consultation_photo'>"+
                                "<img width='60' height='60' src='"+hostBG+"images/userFaceIcon/"+img_ico+"' />"+
                                "</div>"+
                                "<div class='number'>"+result.newMesNumList[i]+"</div>"+
                                "</div>"+
                                "<div class='consultation_right'>"+
                                "<p class='consultation_name'>"+userStr[0]+"</p>"+
                                "<p class='consultation_time'>"+consultStr[2]+"</p>"+
                                "<p class='consultation_note'>"+doctorReply+"</p>"+

                                "</div>"+isadd+
                                "</div>"+
                                "<p class='gray_3'></p>");
                            }

                        }
                    }
                }
                //var objList=$('.number');
                //if(objList.length>0){
                //    $('.selected .new_message_red2').show();
                //}else{
                //    $('.selected .new_message_red2').hide();
                //}
            }

        }
    });
}
function updateVersionFirst() {
    var isUpdate=sessionStorage.getItem('isUpdate');
    if(isUpdate=='update'){
        return false;
    }else{
        var browser={
            versions:function(){
                var u = navigator.userAgent, app = navigator.appVersion;
                return {
                    trident: u.indexOf('Trident') > -1, //IE内核
                    presto: u.indexOf('Presto') > -1, //opera内核
                    webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                    gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                    mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                    ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                    android: u.indexOf('Android') > -1 , //android终端
                    uc:  u.indexOf('Linux') > -1, //uc浏览器
                    iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                    iPad: u.indexOf('iPad') > -1, //是否iPad
                    webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                    weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                    qq: u.match(/\sQQ/i) == " qq" //是否QQ
                };
            }(),
            language:(navigator.browserLanguage || navigator.language).toLowerCase()
        }
//判断是否IE内核
//    if(browser.versions.trident){ alert("is IE"); }
//判断是否webKit内核
//    if(browser.versions.webKit){ alert("is webKit"); }
//判断是否android
//    if(browser.versions.android){ alert("is android"); }
//判断是否移动端
//    if(browser.versions.mobile||browser.versions.android||browser.versions.ios){ alert("移动端"); }
//判断是否微信
        if(browser.versions.weixin){
            return false;
        }
//判断除IE外其他浏览器使用语言
//    currentLang = navigator.language;
//    if(!currentLang){//判断IE浏览器使用语言
//        currentLang = navigator.browserLanguage;
//    }
        else{
            //判断是否苹果
            if(browser.versions.ios){
                return false;
            }
            //判断是否安卓
            if(browser.versions.android){
                $.ajax({
                    type:'post',
                    url:host+'versionManage.action',
                    cache:false,
                    async:false,
                    data:{
                        action:"getNewVersionCode"
                    },
                    success:function(result){
                        if(result.mes=="成功"){
                            if(versionCode!=result.versionManage.versionCode){
                                $('#cover').show();
                                sessionStorage.setItem('isUpdate','update');
                                var s=result.versionManage.isForceUpdate;
                                var height=$('#covercontent').height();
                                height=-height/2;
                                $('#covercontent').css({marginTop:height});
                                $('.updateversion>p:nth-of-type(1)>span').html(result.versionManage.versionCode);
                                $('.updateversion>p:nth-of-type(2)>span').html(result.versionManage.versionSize);
                                $('.updateversion>p:nth-of-type(3)>span').html(result.versionManage.updateDate);
                                var arr=result.versionManage.updateContent.split('&&');
                                var updatecontent='';
                                for(var i=0;i<arr.length;i++){
                                    updatecontent+='<p>'+arr[i]+'</p>';
                                }
                                $('#updatecontent').html(updatecontent);
                                if(s=='Y'){
                                    $('.chooseupdate').html('<div class="rightawayspe" onclick="gotoupdate()">立即更新</div>');
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}

function updateVersion() {
    var browser={
        versions:function(){
            var u = navigator.userAgent, app = navigator.appVersion;
            return {
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 , //android终端
                uc:  u.indexOf('Linux') > -1, //uc浏览器
                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                qq: u.match(/\sQQ/i) == " qq" //是否QQ
            };
        }(),
        language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }
//判断是否IE内核
//    if(browser.versions.trident){ alert("is IE"); }
//判断是否webKit内核
//    if(browser.versions.webKit){ alert("is webKit"); }
//判断是否android
//    if(browser.versions.android){ alert("is android"); }
//判断是否移动端
//    if(browser.versions.mobile||browser.versions.android||browser.versions.ios){ alert("移动端"); }
//判断是否微信
    if(browser.versions.weixin){
        return false;
    }
//判断除IE外其他浏览器使用语言
//    currentLang = navigator.language;
//    if(!currentLang){//判断IE浏览器使用语言
//        currentLang = navigator.browserLanguage;
//    }
    else{
        //判断是否苹果
        if(browser.versions.ios){
            return false;
        }
        //判断是否安卓
        if(browser.versions.android){
            $.ajax({
                type:'post',
                url:host+'versionManage.action',
                cache:false,
                async:false,
                data:{
                    action:"getNewVersionCode"
                },
                success:function(result){
                    if(result.mes=="成功"){
                        if(versionCode!=result.versionManage.versionCode){
                            $('#cover').show();
                            var s=result.versionManage.isForceUpdate;
                            var height=$('#covercontent').height();
                            height=-height/2;
                            $('#covercontent').css({marginTop:height});
                            $('.updateversion>p:nth-of-type(1)>span').html(result.versionManage.versionCode);
                            $('.updateversion>p:nth-of-type(2)>span').html(result.versionManage.versionSize);
                            $('.updateversion>p:nth-of-type(3)>span').html(result.versionManage.updateDate);
                            var arr=result.versionManage.updateContent.split('&&');
                            var updatecontent='';
                            for(var i=0;i<arr.length;i++){
                                updatecontent+='<p>'+arr[i]+'</p>';
                            }
                            $('#updatecontent').html(updatecontent);
                            if(s=='Y'){
                                $('.chooseupdate').html('<div class="rightawayspe" onclick="gotoupdate()">立即更新</div>');
                            }
                        }
                        else{
                            ale('当前已是最新版本');
                        }
                    }
                }
            });
        }
    }
}

function noupdate(){
    $('#cover').hide();
}
function gotoupdate(){
    updateVersionActivity();
}