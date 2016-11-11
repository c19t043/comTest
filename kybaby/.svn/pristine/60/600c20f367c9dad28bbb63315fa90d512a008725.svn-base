var checkwords=0;
var urlphone;
var userLng=104.0722270000, userLat=30.6634560000, userProvince='', userCity='', userArea='', userStreet='', detailAddress='';
var linshiPhone=0;
//var url_str = window.location.search;
//if(url_str.indexOf('?') != -1){
//	urlphone = url_str.substring(1,18)
//	urlphone = urlphone.split('=')[1];
//}
//$(function(){
//    if(urlphone != undefined){
//		$('#regist_codes').val(urlphone);
//    }
//});

$(function () {
    var url_str=window.location.search;
    if(url_str.indexOf('?') != -1){
        urlphone = url_str.substring(1);
        $.ajax({
            type:'post',
            async:false,
            url:host+'userManage.action',
            data:{
                action:"getPhone",
                "refereeUserPhone":urlphone
            },
            success:function(result){
                urlphone=result.refereeUserPhone;
                $('#regist_codes').val(urlphone);
                $("#regist_codes,#regist_codes1").hide();
            },
            error: function () {
                //layer();
            }
        });
    }
});

var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_reg_code').attr('onclick','getCheckwords()').text('获取验证码').css('backgroundColor','#ffffff');
	}else{
		$('#get_reg_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_reg_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);
	}
}
var checkWordTime=0;
function getCheckwords(){
    linshiPhone=$.trim($("#regist_phone").val());
    var userPhone=$.trim($("#regist_phone").val());
	if(userPhone==""){
		ale("请先输入手机号！");
	}else if(checkMobile(userPhone)){
		$.ajax({
			type:'post',
			url:host+'getCheckWords.action',
			data:{
				action:"regist",
				userPhone:userPhone
				},
			success:function(result){
				if(result.mes=="已注册"){
					ale("该手机号已经注册了，快点去登录吧");
				}else{
					checkwords=result.checkWords;
                    checkWordTime++;
                    if(checkWordTime>=3){//没收到短信第三次开始自动填入验证码
                        $('#regist_code').val(checkwords);
                        return false;
                    }
					numReduce();
				}
			},
            error: function () {
                //layer();
            }
		});
	}else{
		ale("请输入合法的手机号");
	}
}

function nextStep(){
	var userPhone=$.trim($("#regist_phone").val());
	var password=$.trim($("#regist_password").val());
	var rpassword=$.trim($("#regist_rpassword").val());
	var writeCheckWods=$.trim($("#regist_code").val());
    if(linshiPhone != userPhone){
        alert('您注册的手机号不一致，请重新获取验证码');
        $('#regist_code').val('');
        linshiPhone=0;
        return false;
    }
	if(userPhone==""){
		ale("请重新输入手机号，获取验证码吧！");
		return false;
	}
	if(writeCheckWods==""||checkwords==0){
		ale("请输入你收到的验证码");
		return false;
	}
	if(checkwords!=writeCheckWods){
		ale("您输入的验证码不对！");
		return false;
	}
	if(password==""){
		ale("你还是设置一个密码吧！");
		return false;
	}
	if(rpassword==""){
		ale("请重新输入密码吧！");
		return false;
	}
	if(password!=rpassword){
		ale("两次输入的密码不一致，请确认！");
		return false;
	}
	if(password==rpassword){
		//ale("进入下一页");
		$("#registFirstStep").hide();
		$("#btnNextStep").hide();
		$("#registNextStep").show();
		$("#btnRegist").show();
		$("#userAgreement").show();
        getLocation();
	}
}

function regist(){
//	var userProvince=$.trim($("#addressProvince").val());
//	var userCity=$.trim($("#addressCity").val());
//	var userArea=$.trim($("#addressArea").val());
//	var userStreet=$.trim($("#street_address").val());
//	var detailAddress=$.trim($("#xxaddress").val());

	//var userPhone=$.trim($("#regist_phone").val());//用户注册输入的手机号
	//var password=$.trim($("#regist_password").val());//用户输入的密码
	//var babyName=$.trim($("#regist_baby_name").val());//用户输入的宝贝名字
	//var babySex="男";//暂时写死,性别
	//var babySex=$('input[name="sex"]:checked').val();
	//var babyBirthDay=$.trim($("#appDate").val());//生日
	//var babyBirthDay="2008-08-08";
    var userPhone=$.trim($("#regist_phone").val());//用户注册输入的手机号
    var password=$.trim($("#regist_password").val());//用户输入的密码
    var babyName=$.trim($("#regist_baby_name").val());//用户输入的宝贝名字
    //var babySex="男";//暂时写死,性别
    var babySex=$('input[name="sex"]:checked').val();
    var babyBirthDay=$.trim($("#appDate").val());//生日
    var refereeUserPhone=$.trim($("#regist_codes").val());//推荐人手机号

    var today = (new Date()).getFullYear()+(new Date()).getMonth()+1+(new Date()).getDate();
	var date_arr = babyBirthDay.split('-');
	var date_time = parseInt(date_arr[0])+parseInt(date_arr[1])+parseInt(date_arr[2])+1;

	var dyear = (new Date()).getFullYear();
	var dmonth = (new Date().getMonth())+1;
	var dday = (new Date().getDate());
	var nyear = parseInt(date_arr[0]);
	var nmonth = parseInt(date_arr[1]);
	var nday = parseInt(date_arr[2]);
    if(babyName==""){
        ale('请填写宝宝姓名');
        return false;
    }
    if(nyear > dyear){
		ale('请选择正确的生日');
		return false;
	}else if(nyear == dyear && nmonth > dmonth){
		ale('请选择正确的生日');
		return false;
	}else if(nyear == dyear && nmonth == dmonth && nday>dday){
		ale('请选择正确的生日');
		return false;
	}

	var refereeUserPhone=$.trim($("#regist_codes").val());//推荐人手机号
	if(babySex==""){
		ale("请选择宝宝的性别哦！");
	}else if(babyBirthDay==""){
		ale("请输入宝宝的生日哦！");
	}
//	else if(detailAddress==""){
//		//ale("请输入您详细的地址信息！");
//	}
	//added by zhong at 2015-10-06
	else if(!$("#cbUserAgreement").is(":checked")){
		ale("请先阅读用户协议");
	}else if(userPhone==refereeUserPhone){
		ale("请输入正确的推荐人手机号！");
	}
	else{

        //ale("开始注册");
/*
        if(userStreet!=""){
        	// 创建地址解析器实例
        	var myGeo = new BMap.Geocoder();
        	// 将地址解析结果显示在地图上,并调整地图视野
            myGeo.getPoint(userStreet, function(point){
        	if (point) {
        		userLng=point.lng;//经度
        		userLat=point.lat;//纬度
        	}else{
        		//ale("您选择地址没有解析到结果!");
        	}
    		$.ajax({
    			type:'post',
    			url:host+'userManage.action',
    			data:{
    				action:"regist",
    				userPhone:userPhone,
    				password:password,
    				babyName:babyName,
    				babySex:babySex,
    				babyBirthDay:babyBirthDay,
    				userLng:userLng,
    				userLat:userLat,
    				userProvince:userProvince,
    				userCity:userCity,
    				userArea:userArea,
    				userStreet:userStreet,
    				detailAddress:detailAddress,
    				refereeUserPhone:refereeUserPhone
    			},
    			success:function(result){
    				if(result.mes=="手机号错误"){
    					ale("请填写正确的注册手机号");
    				}else if(result.mes=="操作成功"){
    					ale("注册成功");
    					if(urlphone != undefined){
    						ale('恭喜注册成功，现在请关注微信公众号“康优宝贝”，即可体验！');
    						return false;
    					}
    					window.location.href="main.html";
    				}
    			}

    		});
            }, userCity);
        }
 */
        $.ajax({
            type:'post',
            async:false,
            url:host+'userManage.action',
            data:{
                action:"regist",
                userPhone:userPhone,
                password:password,
                babyName:babyName,
                babySex:babySex,
                babyBirthDay:babyBirthDay,
                userLng:userLng,
                userLat:userLat,
                userProvince:userProvince,
                userCity:userCity,
                userArea:userArea,
                userStreet:userStreet,
                detailAddress:detailAddress,
                refereeUserPhone:refereeUserPhone
            },
            success:function(result){
                if(result.mes=="手机号已注册"){
                    ale("手机号已注册过!");
                }else if(result.mes=="操作成功"){
                    ale("注册成功");
                    if(urlphone != undefined){
//                        ale('恭喜注册成功，现在请关注微信公众号“康优宝贝”，即可体验！');
//                        return false;
                    }
                    window.location.href="main.html";
                }
            },
            error: function () {
                //layer();
            }

        });

    }
}

$(function(){
	$("#user_xieyi").click(function(){
		$(window).scrollTop(0);
		$('#user_xieyi_box').load(hostBG + 'webPage/userProtocol.html').show();
		$('#user_xieyi_tit').show();
	});
});

function hide_user_xieyi(){
	$('#user_xieyi_box').empty().hide();
	$('#user_xieyi_tit').hide();
}
//获取手机当前位置
//获取手机当前位置
var timestamp = '';
var nonceStr = '';
var signature = '';
var jsapi_ticket = '';
var string_1 = '';
function getLocation() {
    timestamp = getTimeStamp();
    nonceStr = getNonceStr();
    jsapi_ticket = getJsApi();
    string_1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + window.location.href;
    signature = hex_sha1(string_1);
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
                var userLat = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var userLng = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var speed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度
                getAddress(userLat,userLng);
            },
            error: function () {
                //layer();
            }
        });
    });
}
//通过经纬度获取地址
function getAddress(latitude, longitude, callback) {
    $.ajax({
        url: 'http://api.map.baidu.com/geocoder/v2/?ak=btsVVWf0TM1zUBEbzFz6QqWF&callback=renderReverse&location=' + latitude + ',' + longitude + '&output=json&pois=1',
        type: "get",
        dataType: "jsonp",
        jsonp: "callback",
        success: function (data) {
            userProvince = data.result.addressComponent.province;
            var cityname = (data.result.addressComponent.city);
            userCity=cityname;
            userArea = data.result.addressComponent.district;
            userStreet = data.result.addressComponent.street;
            detailAddress = data.result.addressComponent.street_number;
            var formatted_address = data.result.formatted_address;
            //domTempe(cityname,latitude,longitude);
            var data = {
                latitude: latitude,
                longitude: longitude,
                cityname: cityname
            };
            if (typeof callback == "function") {
                callback(data);
            }
        },
        error: function () {
            //layer();
        }
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
            //layer();
        }
    });
    return jsApiTicket;
}

//function getLocation(){
//    var options={
//        enableHighAccuracy:true,
//        maximumAge:1000
//    }
//    if(navigator.geolocation){
//        //浏览器支持geolocation
//        navigator.geolocation.getCurrentPosition(onSuccess,onError,options);
//    }else{
//        //浏览器不支持geolocation
//        alert('您的浏览器不支持地理位置定位');
//    }
//}
////成功时
//
//function onSuccess(position){
//    //返回用户位置
//    //经度
//    var longitude =position.coords.longitude;
//    //纬度
//    var latitude = position.coords.latitude;
//    //alert('经度'+longitude+'，纬度'+latitude);
//
//    //根据经纬度获取地理位置，不太准确，获取城市区域还是可以的
//    var map = new BMap.Map("allmap");
//    var point = new BMap.Point(longitude,latitude);
//    var gc = new BMap.Geocoder();
//    gc.getLocation(point, function(rs){
//        //alert(workStatus);
//        var addComp = rs.addressComponents;
//        //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
//
//        userProvince = addComp.province;
//        userCity = addComp.city;
//        userArea = addComp.district;
//        userStreet = addComp.street;
//        detailAddress = addComp.streetNumber;
//        userLng=longitude;
//        userLat=latitude;
//    });
//}
////失败时
//function onError(error){
//    switch(error.code){
//        case 1:
//            //alert("位置服务被拒绝");
//            break;
//        case 2:
//            //alert("暂时获取不到位置信息");
//            break;
//        case 3:
//            //alert("获取信息超时");
//            break;
//        case 4:
//            //alert("未知错误");
//            break;
//    }
//}