//以下是分享接口
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
			var share_title = '史上最猛测试，你敢做吗？';
			var share_url = hostOperationbussiness+"investigation/index.html";
			var share_img = hostOperationbussiness+"investigation/images/xinggan.jpg";
			var desc = '当1月31“日”变成12月1“日” ——产后“合体”指数大调查';
			wx.onMenuShareTimeline({
	    	title: share_title, // 分享标题
	    	link: share_url, // 分享链接
	    	imgUrl: share_img,// 分享图标
    		success: function () { 
    		},
		    cancel: function () {
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
		    },
		    cancel: function () {
		    },error:function(){
                }
			});
			wx.onMenuShareQQ({
			    title: share_title, // 分享标题
			    desc: desc, // 分享描述
			    link: share_url, // 分享链接
			    imgUrl: share_img, // 分享图标
			    success: function () { 
			    },
			    cancel: function () {
			    }
			});
			wx.onMenuShareQZone({
			    title: share_title, // 分享标题
			    desc: desc, // 分享描述
			    link: share_url, // 分享链接
			    imgUrl: share_img, // 分享图标
			    success: function () { 
			    },
			    cancel: function () {
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
		url : '../../wx/getJsApi.action',
		data : {
			action : "getJsApiTicket"
		},
		success : function(result) {
			jsApiTicket=result.jsApiTicket;
		}   		
	});
	return jsApiTicket;
}
