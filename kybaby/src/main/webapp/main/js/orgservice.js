/**
 * Created by windows on 2016/3/21.
 */
var userLat='';
var userLng='';
var hospitalBasicInfoList=[];
var isFirst=sessionStorage.getItem('isFirst');
$(function () {
    getLocationFirst();
});
var orgService= function () {
    return {
        number:0,//加载第几个10条数据
        hospitalBasicInfoList:[],//临时存储后台数据
        orgServiceArrTemporary:[],
        scroll: function () {//向下滚动
            $(window).scroll(function () {
                if($(window).scrollTop()>=$(document).height()-$(window).height()){//滚动到底端
                    //console.log('您已经滚动到底端');
                    orgService.showTenItems(orgService.number,orgService.hospitalBasicInfoList);
                }
            });
        },
        showTenItems: function (number,hospitalBasicInfoList) {//下拉刷新显示10条数据
            var itemStart=number*10;
            var itemEnd=number*10+10;
            var length=hospitalBasicInfoList.length;
            if(itemStart>=length){
                return false;
            }
            if(itemEnd>length){
                itemEnd=length;
            }
            orgService.number++;
            var html='';
            for (var i = itemStart; i < itemEnd; i++) {
                var biaoqian = '';
                if (orgService.hospitalBasicInfoList[i].businessNameList[0] != '暂无服务') {
                    for (var j = 0, l = orgService.hospitalBasicInfoList[i].businessNameList.length; j < l; j++) {
                        biaoqian += '<span>' + orgService.hospitalBasicInfoList[i].businessNameList[j] + '</span>';
                    }
                } else {
                    biaoqian = '<span style="color: #909090;border-color:#909090">暂无服务</span>';
                }
                html+='' +
                '<div class="org" data-hospital="' + orgService.hospitalBasicInfoList[i].hospitalLname + '"><div class="orglist" onclick="window.location.href=\'orgservice_page.html?' + orgService.hospitalBasicInfoList[i].id + '\'"> ' +
                '<div class="orgimg"><img src="/kybabyBG/' + orgService.hospitalBasicInfoList[i].showImgPath + '" alt=""/></div> ' +
                '<div class="orgcon"> ' +
                '<p style="font-size: 15px;line-height: 23px">' + orgService.hospitalBasicInfoList[i].hospitalLname + '</p> ' +
                '<p class="biaoqian"> ' + biaoqian +
                '</p> ' +
                '<div class="orgaddress"> ' +
                    //'<div>地址:</div> ' +
                '<div>地址：' + orgService.hospitalBasicInfoList[i].address + '</div> ' +
                '</div> ' +
                '</div> ' +
                '<div class="orgdistance">' + parseFloat(orgService.hospitalBasicInfoList[i].toUserDistance).toFixed(2) + 'km</div> ' +
                '</div> ' +
                '<p class="gray_s"></p></div>';
            }
            $('#orglist').append(html);
            orgService.scroll();
        },
        searchMessage: function () {//搜索
            var val=$('#searchbox2 input').val();
            $('#orglist').html('');
            orgService.number=0;
            orgService.hospitalBasicInfoList=orgService.orgServiceArrTemporary;//搜索初始化
            if($.trim(val)==''){
                orgService.showTenItems(orgService.number,orgService.hospitalBasicInfoList);
                //$('.det').css({display:'block'});
            }else{
                var arr=[];
                for(var i= 0,len=orgService.hospitalBasicInfoList.length;i<len;i++){
                    var data_hospital=orgService.hospitalBasicInfoList[i].hospitalLname;
                    if(data_hospital.indexOf(val)>-1){
                        arr.push(orgService.hospitalBasicInfoList[i]);
                    }
                }
                orgService.hospitalBasicInfoList=arr;
                orgService.showTenItems(orgService.number,orgService.hospitalBasicInfoList);
                //$('.det').css({display:'none'});
                //$('.detailinfo[data-doctor*="'+val+'"]').parent().css({display:'block'});
            }
        }
    }
}();
function orgList(){
    $.ajax({
        type: 'post',
        async: true,
        url: hostOrgbusiness + 'orgManage.action',
        data: {
            action: "getHospitalInfoList",
            "hospitalBasicInfo.isShowForUser":'Y',
            "lng_current":userLng,
            "lat_current":userLat
        },
        success: function (result) {
            if (result.mes == '成功') {
                var list = result.hospitalBasicInfoList;
                orgService.hospitalBasicInfoList=list;
                orgService.orgServiceArrTemporary=orgService.hospitalBasicInfoList;
                orgService.showTenItems(orgService.number,orgService.hospitalBasicInfoList);
            }
            sessionStorage.setItem('isFirst','yes');
            sessionStorage.setItem('userLng',userLng);
            sessionStorage.setItem('userLat',userLat);
            if(isFirst==undefined || isFirst=='undefined' ||isFirst==null){
                hf_loading(false);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });
}

//function searchMessage() {
//    var val = $('#searchbox2 input').val();
//    if ($.trim(val) == '') {
//        $('.org').show();
//    } else {
//        $('.org').hide();
//        $('.org[data-hospital*="' + val + '"]').show();
//    }
//
//}
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
        if($('#orglist').html()!=''){
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
