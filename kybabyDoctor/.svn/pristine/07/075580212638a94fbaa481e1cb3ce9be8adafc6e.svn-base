phone_mob = '';
$(function(){
    $('#pl_10').text(versionCode);
	//autoLogin();
	$('#updateImg_hf').attr('action','doctorIdentify.action?action=image&type=head');
	//$('#about_us_cli').click(function(){
	//	window.location.href=hostBG+'webPage/aboutUs.html?'+'doctor';
	//});
	$('#tjOther_cli').click(function(){
		window.location.href = 'recommend.html?'+$('#img_ico_doctor').attr('src')+"&&"+$('#doctorName').text()+"&&"+phone_mob;
	});
	$.ajax({
		type : 'post',
		async : false,
		url : host+'personZone.action',
		data : {action : "headMes"},
		success : function(result) {
			//ale(result.mes);
			//updated by zhong at 2015-11-14:与autoLogin()重复
			/*if(result.mes=="请登录"){
				window.location.href="login.html";
			}*/
			if(result.mes=="成功"){
				var strList = (result.headMessage.split("::"));
				//ale(strList[0]);
				$("#doctorName").text(strList[0]);
				if(strList[1] == ''){
					$('#img_ico_doctor').attr("src",hostBG+"images/doctorFaceIcon/icon_c.png");
				}else{
					$('#img_ico_doctor').attr("src",hostBG+"images/doctorFaceIcon/"+strList[1]);
				}
				$("#doctorTitle").text(strList[2]);
				$("#doctorUnit").text(strList[3]);
				$("#rzStatus").text("（"+strList[4]+"）");
                if(strList[4]=='已通过'){
                    $('.list13').parent().show();
                }else{
                    $('.list13').parent().hide();
                }
				phone_mob = strList[5];
			}
		}
	});
})

function logout(){
	var answer=confirm('确定退出当前账号吗？');
	if(answer==false){
		return false;
	}
	$.ajax({
		type : 'post',
		async : false,
		url : host+'logout.action',
		data : {action : "logout"},
		success : function(result) {
			//if(result.mes=="成功"){
			//	window.location.href="login.html";
			//}
			//if(result.mes=="请登录"){
			//	window.location.href="login.html";
			//}
			if(result.mes=="成功"||result.mes=="请登录"){
				ale("退出成功");
				setTimeout(function(){
					window.location.href="login.html";
				},2500);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		}
	});
}
function handleFiles_(obj,img3){ 
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
	document.getElementById('updateImg_hf').submit();

}
function tiaozhuan(obj){
	var status = $(obj).find("span").text();
	if(status=="（已申请）"||status=="（已通过）"||status=="（未通过）"){
		window.location.href="Doctorauthentication.html?needInit";
	}else{
		window.location.href="Doctorauthentication.html";
	}
}

//加载。。。。。。。。。。。。。。。。。。。。。。。。。。。
function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
		if(status == false){
		document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
		return false;
	}
	$('body').append(
	"<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
		"<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
			"<img id='hf_loading_img' src='images/hf_autoplay.png' style='margin-bottom:10px;' />"+
			"<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:16px;text-align:left;color:#868686;font-weight:bold;'>正在上传中。</span>"+	
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
		if(spanobj.innerHTML == '正在上传中。'){
			spanobj.innerHTML = '正在上传中。。';
		}else if(spanobj.innerHTML == '正在上传中。。'){
			spanobj.innerHTML = '正在上传中。。。';
		}else if(spanobj.innerHTML == '正在上传中。。。'){
			spanobj.innerHTML = '正在上传中。';
		}
	}
	setInterval(function(){
		transword();
	},300);
	setInterval(function(){
		trans();
	},20);
}
//测试。。。。。。。。。。。。。。。。。。。。
  // 参数，最大高度  
  var MAX_HEIGHT = 70;  
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
      	canvas.width = 70;  // 重置canvas宽高
      	canvas.height = 70;    

      ctx.drawImage(image, 0, 0,x,y,0,0,70,70);  // 将图像绘制到canvas上
    };  
    image.src = src;  // 记住必须先绑定事件，才能设置src属性，否则会出同步问题。
		setTimeout(function(){
			sendImage();
			
		},3000);
  };  
	// 加载 图像文件(url路径)  
  function loadImage(obj){
  	var src = $(obj).get(0).files[0];
		var imgType = src.name.split('.');
		imgType = imgType[imgType.length-1];//返回后缀名，以兼容部分移动端浏览器
		if(imgType == 'jpg'){
			imgType = 'jpeg';
		}
		if(!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')){
			ale('请选择图片文件');
			return false;
		}

    // 创建 FileReader 对象 并调用 render 函数来完成渲染.  
		var reader = new FileReader();  
    // 绑定load事件自动回调函数  
		var imgData = '';
    reader.onload = function(e){
    	if(e.target.result.substring(5,10) != 'image'){
    		var imgDataArr = e.target.result.split('base64');
    		imgData = imgDataArr[0] + "image/"+imgType+";base64"+imgDataArr[1];
    		render(imgData);
    	}else{
    		render(e.target.result); 
    	}     
    };  
      // 读取文件内容  
    reader.readAsDataURL(src);
    $('#myCanvas').show();
    $('#img_ico_doctor').hide();
    hf_loading(true);
  };  
//---------------------------------------------  
  // 上传图片，jQuery版  
  function sendImage(){


    var canvas = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串  
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
    var dataurl = canvas.toDataURL("image/png"); 
    // 为安全 对URI进行编码  
    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
    var imagedata =  encodeURIComponent(dataurl);

   
    $.ajax({
    	url:host+'doctorIdentify.action',
    	async:false,
      data :{
   			action:"headImg",
    		imagedata:imagedata
      },  
      type : "POST",  
 			success:function(){
				hf_loading(false);
 			},
 			error:function(){

 			}
    });
    
  };
  //家庭医生设置初始化
  function initFamilyDoctor(){
	    $.ajax({
	    	 type:'post',
	         url:urlWay.familyDoctorHost+'familyDoctorServe.action',
	         cache:false,
	         async:false,
	         data:{action : "initFamilyDoctorServe"},
 			success:function(result){
				if(result.mes == "设置时间"){
					window.location.href='famillydoctortime.html';
				}else if(result.mes == "成功"){
					window.location.href='famillydoctoraddress.html';
				}
 			},
 			error:function(){

 			}
	    });
  }
//版本更新

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
                                var height=$('#content').height();
                                height=-height/2;
                                $('#content').css({marginTop:height});
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
                            var height=$('#content').height();
                            height=-height/2;
                            $('#content').css({marginTop:height});
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

