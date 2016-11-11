function login(){
	var phone = $("#username").val();
	var password = $("#password").val();
	if(phone==null||phone==""){
		ale("请输入手机号");
	}else if(password==null||password==""){
		ale("请输入密码");
	}else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'login.action',
			data : {action:"login",phone:phone,password:password},
			success : function(result) {
				if(result.mes=="密码错误"){
					ale(result.mes);
				}
				if(result.mes=="手机号错误"){
					//ale(result.mes);
					if(result.doctorInfo==null){
						ale("您的手机号还没有注册！");
					}else if(result.doctorInfo!=null&&result.doctorInfo.doctorStatus=='N'){
						ale("您的手机号已被禁用！");
					}
				}
				if(result.mes=="成功"){
					window.location.href="consultation.html";
				} else if (result.mes=="角色选择") {
					window.location.href="roleselect.html";
				}
			}
		});
	}
}
$(function(){
	$('#username').focus(function(){
		if($(this).val() == '请输入手机号'){
			$(this).val('');
		}	
	});
	$('#username').blur(function(){
		if($(this).val() == ''){
			$(this).val('请输入手机号');
		}
	});
	$('#password').focus(function(){
		if($(this).val() == '请输入密码'){
			$(this).val('');
		}
		$(this).attr('type','password');
	});
	$('#password').blur(function(){
		if($(this).val() == ''){
			$(this).val('请输入密码');
			$(this).attr('type','text');
		}
	});
});

function wangji(){
	window.location.href="forgetpassword.html";
}