//医生圈分享功能接口

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
			var share_title = '康优医生';
			var share_url = "http://dev.qiyico.com/kybaby/main/chatSignal.html?id="+json.id+"&str="+json.str+"&mark="+json.share;
			var share_img = window.location.origin+"/main/images/pyq_icon.png";
			var desc = '专业医生,上门服务,给襁褓中孩子最好的礼物!';
			wx.onMenuShareTimeline({
	    	title: share_title, // 分享标题
	    	link: share_url, // 分享链接
	    	imgUrl: share_img,// 分享图标
    		success: function () { 
        	ale('分享成功！');
                $('#zz').css({display:'none'});
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
		        ale('分享成功！');
                $('#zz').css({display:'none'});
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
		        ale('分享成功！');
                    $('#zz').css({display:'none'});
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
		        ale('分享成功！');
                    $('#zz').css({display:'none'});
			    },
			    cancel: function () {
			    }
			});
  });
    if(json.mark){
        var con=$('.tzContent').eq(0);
        $(con).css({padding:'20px 0px'});
        $(con).find('b').find('p').css({borderBottomColor:'#BCECFD',borderBottomWidth:'1.5px',borderBottomStyle:'solid',textAlign:'center',lineHeight:'40px'});
        $('.displayAll').css({display:"none"});
        $('#newContent').html("<img src='img/toeveryone.jpg'/>");
        $('#container').css({marginBottom:"0px",marginTop:'0px'});
    }
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
		},
		error: function () {
			layer();
		}
	});
	return jsApiTicket;
}
function menu(event){
    event.stopPropagation();//阻止事件冒泡
    var height=$('.select').height();
    if(height=='0'){
        $('.select').animate({height:'40px'});
    }
    else{
        $('.select').animate({height:'0px'});
    }
}
function showFenXiang(){
    $('#zz').css({display:'block'});
}
function closeCover(){
    $('#zz').css({display:'none'});
}
document.onclick=function(){
    $('.select').animate({height:'0px'});
}