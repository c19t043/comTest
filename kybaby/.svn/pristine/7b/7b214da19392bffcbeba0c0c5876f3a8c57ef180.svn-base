var url_str = window.location.search;

$(function(){
    //$("#login_logo").css("height" , function(){
	//return $(window).width() * 9 / 16;
	//});
});

function userLogin(){
	var username=$.trim($("#username").val());
	var password=$.trim($("#password").val());
	if(username==""){
		ale("请输入用户名");
	}else if(password==""){
		ale("请输入密码");
	}else{
		$.ajax({
			type:'post',
			url:host+'getUserInfo.action',
			data:{action:"login",username:username,userPassword:password},
			success:function(result){
				if(result.mes=="未注册"){
					ale("您还没有注册，快去注册吧！");
				}else if(result.mes=="登录错误"){
					ale("您的登录被限制了，如有疑问请联系客服！");
				}else if(result.mes=="密码错误"){
					ale("检查一下你的密码吧，已经错了啊！");
				}else if(result.mes=="操作成功"){
					//ale("登录成功");
					window.location.href="main.html";
				}else{
					ale("错误的操作！");
				}
			},
			error: function () {
			}
		});
	}
}

function goForgetPassword(){
	window.location.href=host+'forgetpassword.html';
}

function goRegist(){
    var url=decodeURIComponent(window.location.search);
	window.location.href=host+'regist.html'+url;
}
