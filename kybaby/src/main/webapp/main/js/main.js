function wlCommonInit(){

	/*
	 * Application is started in offline mode as defined by a connectOnStartup property in initOptions.js file.
	 * In order to begin communicating with Worklight Server you need to either:
	 * 
	 * 1. Change connectOnStartup property in initOptions.js to true. 
	 *    This will make Worklight framework automatically attempt to connect to Worklight Server as a part of application start-up.
	 *    Keep in mind - this may increase application start-up time.
	 *    
	 * 2. Use WL.Client.connect() API once connectivity to a Worklight Server is required. 
	 *    This API needs to be called only once, before any other WL.Client methods that communicate with the Worklight Server.
	 *    Don't forget to specify and implement onSuccess and onFailure callback functions for WL.Client.connect(), e.g:
	 *    
	 *    WL.Client.connect({
	 *    		onSuccess: onConnectSuccess,
	 *    		onFailure: onConnectFailure
	 *    });
	 *     
	 */
	
	// Common initialization code goes here

}


$(function(){
	//后退事件绑定
	$('.header-left p').click(function(){

			window.history.go(-1);
	});
});


//验证用户手机号
function checkMobile(userPhone){
	if(!(/^1[3|4|5|6|7|8][0-9]\d{4,8}$/.test(userPhone))){
		return false;
	}else{
		return true;
	}
}



function handleFiles(obj,img3){ 
	window.URL = window.URL || window.webkitURL;
	var files = obj.files;
	var img = new Image();
	if(window.URL){
		img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
		img.width = 101;
		img.height = 101;
		img.onload = function(e) {
			window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
		};
		document.getElementById(img3).src=img.src;
	}else if(window.FileReader){
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = function(e){
			img.src = this.result;
			img.width = 200;
			document.getElementById(img3).src=img.src;
		};
	}else{
		obj.select();
		obj.blur();
		var nfile = document.selection.createRange().text;
		document.selection.empty();
		img.src = nfile;
		img.width = 200;
		img.onload=function(){
		};
		document.getElementById(img3).src=img.src;
	}
}


//判断操作系统
$(function(){
	//ale("1");
	var u = navigator.userAgent;
	//ale(u.toString());
	if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
		//ale("安卓手机");
// window.location.href = "mobile/index.html";
	} else if (u.indexOf('iPhone') > -1) {//苹果手机
// window.location.href = "mobile/index.html";
//		ale("苹果手机");


		//$("#header").css({
		//	"padding-top" : "20px"
		//});
		//$("#container").css({
		//	"margin" : "65px 0 70px"
		//});

	} else if (u.indexOf('Windows Phone') > -1) {//winphone手机
		//ale("winphone手机");
// window.location.href = "mobile/index.html";
	}
});


