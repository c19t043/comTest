//
var phone_mob = '';
var userId = '';
function isCanvasSupported(){
  var elem = document.createElement('canvas');
  return !!(elem.getContext && elem.getContext('2d'));
}
function goToPersonZone(){
	$.ajax({
		type:'post',
		url:host+'userManage.action',
		cache:false,
    async:false, 
		data:{action:"personZone"},
		success:function(result){
			if(result.userId!=0){
				if(result.userName == null){
					$('#babyName').text('请完善个人信息');
				}else{
					$('#babyName').text(result.userName);
				}
				userId = result.userId;
				
				phone_mob = result.userPhone;
				$('#babyBirthday').text(result.babyBirthDay);
				$('#babySex').text(result.babySex);
				if(result.userImage == null){
					$('#babyImg').attr('src',hostBG+'images/userFaceIcon/lt_user.png');	
				}else{
					$('#babyImg').attr('src',hostBG+'images/userFaceIcon/'+result.userImage+"?"+parseInt(Math.random()*10000000000000));	
				}			
			}else{
				ale("请登录");
        setTimeout(function(){
          window.location.href="login.html";
        },2500);				
			}
		},
		error: function () {
			layer();
		}
	});
    $.ajax({
        type: 'post',
        url: memberHost + 'memberManage.action',
        cache: false,
        async: false,
        data: {
            action: "getMemberManageList"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes == '成功'){
                var memberList=result.memberManageList;
                if(memberList!=null){
                    $('.my_member').show();
                }
            }
        },
		error: function () {
			layer();
		}
    });
}
function modify_personal_information(){
    window.location.href='modify_personal_information.html'
}
$(function(){
    $('#ml_20').html(versionCode);
	if(!isCanvasSupported()){
		ale(false);
	}
	goToPersonZone();
	$('#updateImg_hf').attr('action','userImageManage.action?action=inputIcon');
	$('#tjOther_cli').click(function(){
		window.location.href = 'recommend.html?'+$('#babyImg').attr('src')+"&&"+$('#babyName').text()+"&&"+phone_mob;
	});
	$('#about_us_cli').click(function(){
		window.location.href=hostBG+'webPage/aboutUs.html?'+'admin'
	});
});
//--------------------------------------------------

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
function userLogout(){
    var answer=confirm('确定退出当前账号吗？');
    if(answer==false){
        return false;
    }
	$.ajax({
		type:'post',
		url:host+'userManage.action',
		data:{action:"logout"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("退出成功");
        setTimeout(function(){
          window.location.href="login.html";
        },2500);				
			}
		},
		error: function () {
			layer();
		}
	});
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
    $('#babyImg').hide();
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
    	url:host+'fileRecive.action',
    	async:false,
      data :{
      	imagedata:imagedata
      },  
      type : "POST",  
 			success:function(){

 			},
 			error:function(){

 			}
    });
    hf_loading(false);
  };
