var changePassword_code,changePassword_phone;
var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_reg_code').attr('onclick','checkUserPhone()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_reg_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_reg_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);   
	}
}
function checkUserPhone(){
	var phone = $('#phone').val().trim();
	if(phone == '' || phone.length != 11 || isNaN(phone) || phone.substring(0,1) != 1){
        ale('请输入当前手机号');
		return false;
	}
	
	$.ajax({
		type:'post',
		url:host+'getCheckWords.action',
		cache:false,
		async:false, 
		data:{action:"checkUserOldPhone",userPhone:phone},
		success:function(result){
			if(result.mes=="未登录"){
				ale("请先登录");
        setTimeout(function(){
          window.location.href='login.html';
        },2500);
			}else if(result.mes=="错误"){
				ale("请输入您当前的手机号");
				$('#phone').focus().val('');
			}else{
				//ale("验证码为："+result.checkWords);
				changePassword_code=result.checkWords;
				changePassword_phone=result.userPhone;
				numReduce();
			}
		},
		error: function () {
			layer();
		}
	});

}
function changePassword(){
	var changePhone=$.trim($("#phone").val());
	var code=$.trim($("#code").val());
	var password=$.trim($("#password").val());
	var againPassword=$.trim($("#rpassword").val());
	if(changePhone==""){
		ale('请输入手机号');
		$("#phone").focus();
	}else if(code==""){
		ale('请输入验证码');
		$("#changeCheckwords").focus();
	}else if(password==""){
		ale('请输入密码');
		$("#password").focus();
	}else if(againPassword==""){
		ale('请再次输入密码');
		$("#rpassword").focus();
	}else if(changePhone!=changePassword_phone){
		ale("手机号填写错误");
		$("#phone").focus().val('');
	}else if(code!=changePassword_code){
		ale("验证码错误，请重新输入验证码");
		$("#changeCheckwords").focus().val('');
	}else if(password!=againPassword){
		ale("两次输入的密码不一致");
		$("#rpassword").focus().val('');
	}else{
		$.ajax({
			type:'post',
			url:host+'userManage.action',
			cache:false,
			async:false, 
			data:{action:"updatePassword",userPhone:changePassword_phone,password:password},
			success:function(result){
				if(result.mes=="操作成功"){
					ale("修改成功");
          setTimeout(function(){
            window.location.href='consultation.html';
          },2500);
					
				}else if(result.mes=="错误"){
					ale("请重新输入手机手机号，获取验证码");
				}else if(result.mes=="未登录"){
					ale("请重新登录后再修改");
          setTimeout(function(){
            window.location.href='login.html';
          },2500);
					
				}
			},
			error: function () {
				layer();
			}
		});
	}
}



