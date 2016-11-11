var url_str = decodeURIComponent(window.location.search.substring(1)).split('&&');
var tjphone = url_str[2];

var tjUrl=location.href.split('?')[0].replace('recommend','regist')+"?phone="+url_str[2] ;//任意内容

if(tjUrl.substring(0, 4)=="file"){
	tjUrl=host+"regist.html?phone="+tjphone;
}

//alert("tjUrl=="+tjUrl);

$(function(){
	$('.user_photo').css('background',"url("+url_str[0]+") no-repeat center center").css('backgroundSize','50px 50px');
	$('#username').text(url_str[1]);
    var doctorId='';
    $.ajax({
        type: 'post',
        url : host+'homePageManage.action',
        cache: false,
        async: false,
        data : {action:"homePage"},
        success: function (result) {
            var doctor = result.doctorInfo;
            doctorId=doctor.id;
        },
        error: function () {
            alert('you are false');
        }
    });

    tjUrl=urlWay.hostName+'/main/regist.html?d='+doctorId;
	$("#code_ewm").qrcode({
		render: "canvas", //table方式
		width: 160, //宽度
		height:160, //高度
		text:tjUrl
	});
	$('#codetjm').text('邀请好友扫一扫分享给TA');

});




//	$.ajax({
//		type:'post',
//		url:host+'personZone.action',
//		cache:false,
//		async:false, 
//		data:{action:"recommend"},
//		success:function(result){
//
//			if(result.mes=="成功"){
//				var strList = result.headMessage;
//				url_str = strList.split("::");
//				tjphone = url_str[2];
//
//				$('.user_photo').css('background',"url("+hostBG+"images/doctorFaceIcon/"+url_str[0]+") no-repeat center center").css('backgroundSize','50px 50px');
//				$('#username').text(url_str[1]);
//
//			}else if(result.mes=="请登录"){
//				ale("请先登录！");
//				window.location.href="login.html";
//			}
//		}
//	});
//分享遮罩
$(function(){
		$('#zz').click(function(){
			$('#zz').hide();
		});	
		$('.share_cli').click(function(){

			$('#zz').show();
		});
});
//以下是分享接口
//var appId  = 'wxe143c55a723e1738';//测试服务器
//var appId  = 'wxc592f8c1fcf1ba64';//正式服务器

var timestamp = '';
var nonceStr = '';
var signature = '';
var jsapi_ticket = '';
var string_1 = '';

$(function(){
	timestamp = getTimeStamp();
	nonceStr = getNonceStr();
	jsapi_ticket = getJsApi();
	string_1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+window.location.href;
	signature = hex_sha1(string_1);
	wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: appId, // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
	 	wx.checkJsApi({
    	jsApiList: ['onMenuShareTimeline'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
    	success: function(res) {
       // 以键值对的形式返回，可用的api值true，不可用为false
       // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
   		 }
		});
			var share_title = '康优宝贝';
			var share_url = location.href.split('?')[0].replace('recommend','regist')+"?phone="+tjphone;
			var share_img = window.location.origin+"/main/images/pyq_icon.png";
			var desc = '专业医生,上门服务,给襁褓中孩子最好的礼物!';

			wx.onMenuShareTimeline({
	    	title: share_title, // 分享标题
	    	link: share_url, // 分享链接
	    	imgUrl: share_img,// 分享图标
    		success: function () { 
        	ale('分享成功！');
        	$('#zz').hide();
    		},
		    cancel: function () { 
		    	$('#zz').hide();
		    }
			});
			wx.onMenuShareAppMessage({
				title: share_title, // 分享标题
			  desc: desc, // 分享描述
			  link: share_url, // 分享链接
			 	imgUrl: share_img, // 分享图标
			  type: '', // 分享类型,music、video或link，不填默认为link
			  dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        ale('分享成功！');
		        $('#zz').hide();
		    },
		    cancel: function () { 
		    	$('#zz').hide();
		    }
			});
			wx.onMenuShareQQ({
			    title: share_title, // 分享标题
			    desc: desc, // 分享描述
			    link: share_url, // 分享链接
			    imgUrl: share_img, // 分享图标
			    success: function () { 
		        ale('分享成功！');
		        $('#zz').hide();
			    },
			    cancel: function () { 
			    	$('#zz').hide();
			    }
			});
			wx.onMenuShareQZone({
			    title: share_title, // 分享标题
			    desc: desc, // 分享描述
			    link: share_url, // 分享链接
			    imgUrl: share_img, // 分享图标
			    success: function () { 
		        ale('分享成功！');
		        $('#zz').hide();
			    },
			    cancel: function () { 
			    	$('#zz').hide();
			    }
			});
  });
});
function getTimeStamp(){
 	var timestamp=new Date().getTime();
  var timestampstring = timestamp.toString();//一定要转换字符串
  return timestampstring;
}
function getNonceStr(){
  var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  var maxPos = $chars.length;
  var noceStr = "";
  for (var i = 0; i < 32; i++) {
  	noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
  }
  return noceStr;
}
function getJsApi(){
	var jsApiTicket='';
	$.ajax({
		type : 'post',
		cache:false,
		async:false,
		url : host+'../wx/getJsApi.action',
		data : {
			action : "getJsApiTicket"
		},
		success : function(result) {
			jsApiTicket=result.jsApiTicket;
		}   		
	});
	return jsApiTicket;
}
