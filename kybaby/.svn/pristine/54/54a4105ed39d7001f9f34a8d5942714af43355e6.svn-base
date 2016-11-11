//获取banner区域图片
var userLat='';
var userLng='';
var isFirst=sessionStorage.getItem('isFirst');
function getBannerPicture() {
    $.ajax({
        type: 'post',
        url: host + 'getBannerInfo.action',
        cache: false,
        async: false,
        data: {action: "getBannerPicture"},
        success: function (result) {
            if (result.mes == "无banner") {
                //ale("没有banner区域图片");
            } else if (result.mes == "操作成功") {
                for (var i = 0; i < result.bannerPictureList.length; i++) {
                    var banner = result.bannerPictureList[i];
                    $(".slides").append(
                        "<li onclick=\"window.location.href='"+hostBG+"webPage/"+banner.value+"'\"><img src='" + hostBG + "images/banner/" + banner.name + "?" + parseInt(Math.random() * 100000000000000000) + "' /></li>"
                    );
                    //$("#full-width-slider").append("<div class='rsContent' onclick=\"window.location.href='"+hostBG+"webPage/"+banner.value+"'\"><img width='100%'  class='rsImg' src='"+hostBG+"images/banner/"+banner.name+"?"+parseInt(Math.random()*100000000000000000)+"'/></div>");
                }
                $('.flexslider').flexslider({
                    animation: "slide",
                    controlNav: true,               // Boolean: 是否创建控制点
                    directionNav: false
                });


            }
        },
        error: function () {
            layer();
        }
    });
}

function confirmPlan(orderNumber,planId, pathId, obj) {
    if (!obj.hasClass('select')) {
        obj.addClass('select');
        $.ajax({
            type: 'post',
            url: host + 'healthPlanRemainManage.action',
            cache: false,
            async: false,
            data: {action: "confirmByOrder", planId: planId, pathId: pathId,orderNum:orderNumber},
            success: function (result) {
                //if (result.mes == "操作成功") {
                //    //ale("操作成功");
                //} else if (result.mes == "未登录") {
                //    ale("您还没有登录");
                //}
            },
            error: function(){layer();}
        });
    }
}

function openMethodPage() {
    window.location.href = hostBG + "webPage/healthManageMethod.html";
}
var isTrue;
//推荐医生
function tjdoctor(){
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"recommendedDoctorList"},
        success:function(result){

                //console.log(result);
                var list=result.doctorInfoList;
                for(var i= 0,len=list.length;i<len;i++){
                    $('#tj_doctor').append('<li onclick="window.location.href=\'pointPatient.html?'+list[i].id+'\'">' +
                    '<img src="'+hostBG+'images/doctorFaceIcon/'+list[i].doctorImage+'"/> <span class="do-name">' +
                    list[i].doctorName+'</span> ' +
                    '<span class="do-type">'+list[i].department+'</span></li>');
                }
                //if(list!=null && list.length>4){
                //    var num=-50;
                //    isTrue=true;
                //    for(var j= 0,le=list.length;j<le;j++){
                //        num=num+25;
                //        var s=num+"%";
                //        $('.tj_doctor>li').eq(j).attr('index',s).css({left:s});
                //    }
                //}

            //if(isTrue){
            //    setInterval(function () {
            //        var cln=$('.tj_doctor>li:first').clone();
            //        var list=$('.tj_doctor>li');
            //        var lastLeft=$('.tj_doctor>li:last-child').attr('index');
            //        //console.log('dasdad'+lastLeft);
            //        $('.tj_doctor').append(cln);
            //        $(list).each(function () {
            //            var left=parseInt($(this).attr('index'))-25;
            //            left=left+"%";
            //            $(this).css({left:left});
            //            $(this).attr('index',left);
            //            //console.log(left);
            //        });
            //        $('.tj_doctor>li:last').css({left:lastLeft});
            //        $('.tj_doctor>li:last').attr('index',lastLeft);
            //        $('.tj_doctor>li:first').remove();
            //    },5000);
            //}
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
}
//机构列表
function orglist(){
    var l=0;
    $.ajax({
        type:'post',
        async:false,
        url:hostOrgbusiness+'orgManage.action',
        data:{
            action:"getHospitalInfoList",
            "hospitalBasicInfo.isShowForUser":'Y',
            "listNum":3,
            "lng_current":userLng,
            "lat_current":userLat
        },
        success:function(result){
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var list=result.hospitalBasicInfoList;
                l=list.length;
                for(var i= 0,len=list.length;i<len;i++){
                    var iscanyue='';
                    if(list[i].clinicBookingDate==null){
                        iscanyue='<span class="color-gray">暂无可约时间</span>';
                    }else{
                        for(var m= 0,le=list[i].clinicBookingDate.length;m<le;m++){
                            iscanyue+='<span class="color-gray">'+list[i].clinicBookingDate[m]+'</span>'
                        }
                    }
                    $('#org_list').append('<li onclick="window.location.href=\'orgservice_page.html?'+list[i].id+'\'"><div><div>'+list[i].hospitalLname+'</div>' +
                    iscanyue +
                    '</div> <div> <div class="main_btn">'+parseFloat(list[i].toUserDistance).toFixed(2)+'km </div> </div> </li>');
                    sessionStorage.setItem('isFirst','yes');
                    sessionStorage.setItem('userLng',userLng);
                    sessionStorage.setItem('userLat',userLat);
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });
    var num=58;
    for(var i= 0;i<l;i++){
        var h=num*i+"px";
        $('#org_list>div').eq(i).css({top:h});
    }
    if(l>4){
        setInterval(function () {
            var cln=$('#org_list>div:first').clone();
            var num=58;
            for(var i= 0;i<l;i++){
                var h=num*(l-i)-116+"px";
                $('#org_list>div').eq(l-i).animate({top:h});
            }
            $('#org_list').append(cln);
            var le=(l-1)*num;
            $('#org_list>div:last').css({top:le});
            $('#org_list>div').eq(0).remove();
        },3000);
    }
}

//物理后退
document.addEventListener("deviceready", onDeviceReady, false);
function onDeviceReady() {
    document.addEventListener("backbutton", onBackKeyDown, false);
}
function onBackKeyDown() {
	alert('what?');
	navigator.app.exitApp();
}
//我的机构
function myorg(type){
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "getCurrentUserIdentity"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            } else if (result.mes == '成功') {
                var archivesInfo = result.archivesInfo;
                if(archivesInfo==null || archivesInfo.isRelation=='N'){
                    window.location.href='orgservice_associatedbaby.html';
                }else if(archivesInfo.isRelation=='Y'){
                    var orgId=archivesInfo.hospitalBasicInfo.id;
                    if(type=='babypoint'){
                        var isTrue=checkArchivesInfoServices(archivesInfo.id,'儿保预约');
                        if(isTrue==false){
                            return false;
                        }
                        window.location.href = 'orgservice_babypoint.html?' + orgId;
                    }else if(type=='vaccine'){
                        var isTrue=checkArchivesInfoServices(archivesInfo.id,'计划免疫');
                        if(isTrue==false){
                            return false;
                        }
                        if(result.inoculationed=='计免约过'){
                            window.location.href = 'orgservice_message.html?' + result.appointmentInitInfo.id;
                        }else{
                            window.location.href = 'orgservice_vaccine.html?' + orgId ;
                        }
                    }
                }
            }
        },
        error: function () {
            layer();
        }
    });
}
//用户注册机构是否对该用户开放服务
function checkArchivesInfoServices(id,type){
    var isTrue=false;
    $.ajax({
        type:'post',
        url:hostOrgbusiness+'orgManage.action',
        cache:false,
        async:false,
        data:{
            action:"checkArchivesInfoServices",
            "archivesInfo.id":id,
            businessName:type
        },
        success:function(result){
            if(result.mes == "成功"){
                isTrue=true;
            }else if(result.mes!='成功'){
                ale(result.mes);
                isTrue=false;
            }
        },
        error: function () {
            layer();
        }
    });
    return isTrue;
}
//最新消息
function getMessageList(){
    $.ajax({
        type:'post',
        url:messageHost+'messageCenterAction.action',
        cache:false,
        async:false,
        data:{action:"messageNoReadByUser"},
        success:function(result){
            if(result.mes == "成功"){
                if(result.userMessageNoReadCount>0){
                    if(result.userMessageNoReadCount>99){
                        $("#message").append("<div>…</div>");
                    }
                    else{
                        $("#message").append('<div>'+result.userMessageNoReadCount+'</div>');
                    }
                }
            }
        },
        error: function () {
            layer();
        }
    });
}

//获取手机当前位置
function getLocationFirst(){
    if(isFirst==undefined || isFirst=='undefined' ||isFirst==null){
        getLocation();
    }else if(isFirst=='yes'){
        userLat=sessionStorage.getItem('userLat');
        userLng=sessionStorage.getItem('userLng');
        orglist();
        return false;
    }
}
var timestamp = '';
var nonceStr = '';
var signature = '';
var jsapi_ticket = '';
var string_1 = '';
var search_list='';
var search_list_arr=[];
var list='';
function getLocation() {
    timestamp = getTimeStamp();
    nonceStr = getNonceStr();
    jsapi_ticket = getJsApi();
    string_1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + window.location.href;
    signature = hex_sha1(string_1);
    setTimeout(function () {
        if($('#org_list').html()!=''){
            orglist();
            return false;
        }
    },10000);
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['openLocation', 'getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        wx.checkJsApi({
            jsApiList: ['openLocation', 'getLocation'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function (res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            },
            error: function () {
                layer();
            }
        });
        //wx.openLocation({
        //    latitude: 0, // 纬度，浮点数，范围为90 ~ -90
        //    longitude: 0, // 经度，浮点数，范围为180 ~ -180。
        //    name: '', // 位置名
        //    address: '', // 地址详情说明
        //    scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
        //    infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
        //});
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                userLat = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                userLng = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                orglist();
            },
            error: function () {
                layer();
            }
        });
    });
}
function getTimeStamp() {
    var timestamp = new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    return timestampstring;
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
function getJsApi() {
    var jsApiTicket = '';
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
    return jsApiTicket;
}
//首页搜索
function searchMessage_index(){
    var val=$('#search_header input').val().trim();
    if(val=='' || val==undefined || val== null){
        return false;
    }
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getClinicDoctorInfo",
            "doctorInfo.doctorName":val,
            "doctorInfo.doctorEmployer":val
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                if(result.doctorInfoFoList==null || result.doctorInfoFoList.length==0){
                    $.ajax({
                        type: 'post',
                        async: false,
                        url: hostOrgbusiness + 'orgManage.action',
                        data: {
                            action: "getHospitalInfoList",
                            "hospitalBasicInfo.isShowForUser":'Y',
                            "hospitalBasicInfo.hospitalLname":val
                        },
                        success: function (resu) {
                            if(resu.hospitalBasicInfoList==null || resu.hospitalBasicInfoList.length==0) {
                                ale('没有相关搜索');
                            }else{
                                setSession(val);
                                window.location.href = "main_search.html?"+val;
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            layer();
                        }
                    });
                }else{
                    setSession(val);
                    window.location.href = "main_search.html?"+val;
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

}
//设置缓存
function setSession(val){
    var searchSession=localStorage.searchSession;
    if(searchSession==undefined||searchSession==''){
        localStorage.searchSession=val;
    }else{
        var arr=searchSession.split('::');
        if($.inArray(val, arr)==-1){
            searchSession=searchSession.concat('::'+val);
            localStorage.searchSession=searchSession;
        }
    }
}
//清除缓存
function clearHistoryRecord(){
    localStorage.searchSession='';
    $('#history_record').hide().html('');
    $('#history_record').next().hide();
}
//搜索
function search_layer() {
    $("#search_div").show();
    var searchSession=localStorage.searchSession;
    if(searchSession==undefined||searchSession==''){
        $('#history_record').hide();
        $('#history_record').next().hide();
    }else{
        $('#history_record').show();
        $('#history_record').next().show();
        var history_record='';
        var historyList=searchSession.split('::');
        for(var i= 0,len=historyList.length;i<len;i++){
            history_record+='<p onclick="window.location.href=\'main_search.html?'+historyList[i]+'\'">'+historyList[i]+'</p>';
        }
        $('#history_record').html('<div class="search_name" id="historical_record"><span>历史记录</span><span id="clear_historical" onclick="clearHistoryRecord()">清除历史记录</span></div>'+history_record);
    }
    $.ajax({
        type:'post',
        async:false,
        url:hostOrgbusiness+'orgManage.action',
        data:{
            action:"getHospitalInfoList",
            "hospitalBasicInfo.isShowForUser":'Y',
            "listNum":3,
            "lng_current":userLng,
            "lat_current":userLat
        },
        success:function(result){
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var list=result.hospitalBasicInfoList;
                var l=list.length;
                var html='';
                for(var i= 0,len=list.length;i<len;i++){
                    html+='<p onclick="window.location.href=\'orgservice_page.html?'+list[i].id+'\'">'+list[i].hospitalLname+'</p>';
                }
                $("#orgList").html('<div class="search_name">搜索社区</div>'+html);
                sessionStorage.setItem('isFirst','yes');
                sessionStorage.setItem('userLng',userLng);
                sessionStorage.setItem('userLat',userLat);
            }
        }
    });
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getClinicDoctorInfo"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list=result.doctorInfoFoList;
                var html='';
                var len=list.length;
                if(len>5){
                    len = 5;
                }
                var html='';
                for(var i= 0,len;i<len;i++){
                    var doctor=list[i].doctorInfo;
                    html+='<span onclick="window.location.href=\'pointPatient.html?'+doctor.id+'\'">'+doctor.doctorName+'</span>';
                }
                $("#doctorList").html('<div class="search_name">搜索医生</div>'+html);
            }
        }
    });

}

