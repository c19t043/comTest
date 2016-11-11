var userLat='';
var userLng='';
var isFirst=sessionStorage.getItem('isFirst');
$(function () {
    getLocationFirst();
    getAllExpertTeams();
});
function getAllExpertTeams(){//获取专家团队包
    $.ajax({
        type:'post',
        url:familyDoctorHost+'fdServiceItemsAction.action',
        cache:false,
        async:false,
        data:{
            action:"getAllExpertTeams"
        },
        success:function(result){
            if(result.mes == '请登录'){
                ale('请登录');
                window.location.href="login.html";
            }
            else if(result.mes == '成功'){
                var fdServiceTeamsList=result.fdServiceTeamsList;
                if(fdServiceTeamsList != null && fdServiceTeamsList.length != 0){
                    var html='';
                    for(var i= 0,le=fdServiceTeamsList.length;i<le;i++){
                        var fdServicePackageSet=fdServiceTeamsList[i].fdServicePackageSet;
                        var fdServicePackageSetHTml='';
                        if(fdServicePackageSet != null && fdServicePackageSet.length != 0){
                            for(var j= 0,len=fdServicePackageSet.length;j<len;j++){
                                fdServicePackageSetHTml+='<p>'+fdServicePackageSet[j].packageShowName+'&nbsp;&nbsp;&nbsp;&nbsp;&yen;'+fdServicePackageSet[j].minPrice+'起</p> ' +
                                '<p class="gray_3"></p> ';
                            }
                        }
                        html+='<div class="expert_team_list" onclick="gotoexpertteam(\'family_doctor_expert_team.html?'+fdServiceTeamsList[i].id+'\')"> ' +
                        '<div class="expert_team"> ' +
                        '<div class="expert_header"> ' +
                        '<img src="'+photo+fdServiceTeamsList[i].teamImgPath+'"> ' +
                        '</div> ' +
                        '<div class="expert_information"> ' +
                        '<p class="expert_name">'+fdServiceTeamsList[i].teamName+'</p> ' +
                        '<p><span class="number">'+fdServiceTeamsList[i].buyCount+'</span>人购买</p> ' +
                        '</div> ' +
                        '</div> ' +
                        '<p class="gray_3"></p> ' +
                        '<div class="package"> ' +
                        fdServicePackageSetHTml+
                        '</div> ' +
                        '</div>';
                    }
                    $('#expert_team').html(html);
                }
            }
        },
        error: function () {
            layer();
        }
    });

}
function gotoexpertteam(url){//专家团队服务包
    window.location.href=url;
}
function orgList(){
    $.ajax({
        type:'post',
        url:familyDoctorHost+'familyDoctorManage.action',
        cache:false,
        async:false,
        data:{action:"getFdHospitalList",
            "hospitalBasicInfo.isShowForUser":"Y",
            lng_current:userLng,
            lat_current:userLat
        },
        success:function(result){
            if(result.mes == '请登录'){
                ale('请登录');
                window.location.href="login.html";
            }
            else if(result.mes == '成功'){
                if(result.hospitalBasicInfoList!=null){
                    var length = result.hospitalBasicInfoList.length;
                    for (var i = 0; i < length; i++) {
                        var hospitalBasicInfoList = result.hospitalBasicInfoList[i];
                        var showImgPath='';
                        if(hospitalBasicInfoList.showImgPath!=null){
                            showImgPath=hostBG+hospitalBasicInfoList.showImgPath.substring(6);
                        }
                        var doctors='';
                        if(hospitalBasicInfoList.doctors==null){
                            doctors='<span class="number">敬请期待</span>';
                        }else{
                            doctors='<span class="number">'+hospitalBasicInfoList.doctors+'</span>个医生';
                        }
                        if(hospitalBasicInfoList.hospitalType=='社区医院'){
                            $("#community_list").append('<div class="community_div" onclick="goLink('+hospitalBasicInfoList.id+',\''+hospitalBasicInfoList.isFamilydoctor+'\')"><div class="community_header"><img src="'+showImgPath+'"/></div>' +
                                '<div class="community_information"><p class="community_name">'+hospitalBasicInfoList.hospitalLname+'</p>' +
                                '<p>'+doctors+'</p><p>'+hospitalBasicInfoList.address+'</p></div>' +
                                '<div class="community_distance">'+ parseFloat(hospitalBasicInfoList.toUserDistance).toFixed(2) + 'km</div></div>'
                            );
                        }else{
                            $("#hospital_list").append('<div class="community_div" onclick="goLink('+hospitalBasicInfoList.id+',\''+hospitalBasicInfoList.isFamilydoctor+'\')"><div class="community_header"><img src="'+showImgPath+'"/></div>' +
                                '<div class="community_information"><p class="community_name">'+hospitalBasicInfoList.hospitalLname+'</p>' +
                                '<p>'+doctors+'</p><p>'+hospitalBasicInfoList.address+'</p></div>' +
                                '<div class="community_distance">'+ parseFloat(hospitalBasicInfoList.toUserDistance).toFixed(2) + 'km</div></div>'
                            );
                        }
                    }
                }
                sessionStorage.setItem('isFirst','yes');
                sessionStorage.setItem('userLng',userLng);
                sessionStorage.setItem('userLat',userLat);
                if(isFirst==undefined || isFirst=='undefined' ||isFirst==null){
                    hf_loading(false);
                }
                getFunction();
            }
        },
        error: function () {
            layer();
        }
    });
}
function getFunction(){
    $('#nav>div').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
        if($(this).attr('data-name')=='community'){
            $('#community_list').show();
            $('#hospital_list,#expert_team').hide();
        }else if($(this).attr('data-name')=='hospital'){
            $('#community_list,#expert_team').hide();
            $('#hospital_list').show();
        }else if($(this).attr('data-name')=='expert'){
            $('#community_list,#hospital_list').hide();
            $('#expert_team').show();
        }
    });
}
//获
function goLink(id,isFamilydoctor){
    if(isFamilydoctor=="Y"){
        window.location.href='family_doctor_team_list.html?'+id;
    }else{
        ale('该机构暂未开通服务');
    }

}
//获取手机当前位置
function getLocationFirst(){
    if(isFirst==undefined || isFirst=='undefined' ||isFirst==null){
        getLocation();
    }else if(isFirst=='yes'){
        userLat=sessionStorage.getItem('userLat');
        userLng=sessionStorage.getItem('userLng');
        orgList();
        return false;
    }
}
var timestamp = '';
var nonceStr = '';
var signature = '';
var jsapi_ticket = '';
var string_1 = '';
function getLocation() {
    hf_loading(true);
    timestamp = getTimeStamp();
    nonceStr = getNonceStr();
    jsapi_ticket = getJsApi();
    string_1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + window.location.href;
    signature = hex_sha1(string_1);
    setTimeout(function () {
        if($('#community_list').html()!='' &&$('#hospital_list').html()!=''){
            return false;
        }
        orgList();
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
                orgList();
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
function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
    if(status == false){
        document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
        return false;
    }
    $('body').append(
        "<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
        "<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
        "<img id='hf_loading_img' src='images/hf_autoplay.png' style='margin-bottom:10px;width:40px;height:40px;' />"+
        "<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:22px;text-align:left;color:#ff813d;font-weight:bold;'>加载中。</span>"+
        "</div>"+
        "</div>");

    var divobj = document.getElementById('hf_loading');
    var boxobj = document.getElementById('hf_loading_box');
    var imgobj = document.getElementById('hf_loading_img');
    var spanobj = document.getElementById('hf_loading_word');
    var du = 0;

    //divobj.style.width = document.documentElement.scrollWidth + "px";
    //divobj.style.height = document.documentElement.scrollHeight + "px";
    divobj.style.width = $(document).width() + "px";
    divobj.style.height = $(document).height() + "px";

    boxobj.style.top = (document.documentElement.clientHeight/2 - boxobj.offsetHeight/2) + "px";
    boxobj.style.left = (document.documentElement.clientWidth/2 - boxobj.offsetWidth/2) + "px";

    function trans(){
        if(du == 360){
            du = 0;
        }else{
            du = du + 10;
        }
        imgobj.style.transform = "rotate("+du+"deg)";
        imgobj.style.WebkitTransform = "rotate("+du+"deg)";
    }
    function transword(){
        if(spanobj.innerHTML == '加载中。'){
            spanobj.innerHTML = '加载中。。';
        }else if(spanobj.innerHTML == '加载中。。'){
            spanobj.innerHTML = '加载中。。。';
        }else if(spanobj.innerHTML == '加载中。。。'){
            spanobj.innerHTML = '加载中。';
        }
    }
    setInterval(function(){
        transword();
    },300);
    setInterval(function(){
        trans();
    },20);
}