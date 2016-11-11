var checkWords=0;
var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_reg_code').attr('onclick','getCheckWords()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_reg_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_reg_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);   
	}
}
function getCheckWords(){
	var userPhone=$.trim($("#phone").val());
	if(userPhone==""){
		ale("请输入手机号");
	}else if(checkMobile(userPhone)){
		$.ajax({
			type:'post',
			url:host+'getCheckWords.action',
			data:{action:"forgetPassword",userPhone:userPhone},
			success:function(result){
				if(result.mes=="未注册"){
					ale("您还没有注册哦！");
				}else{
					checkWords=result.checkWords;
					//ale("验证码="+checkWords);
					numReduce();
				}
			},
			error: function () {
				layer();
			}
		});
	}else{
		ale("请检查你输入的手机号码！");
	}
}

function forgetPassword(){
	var userPhone=$.trim($("#phone").val());
	var writeCheckwords=$.trim($("#code").val());
	var userPassword=$.trim($("#password").val());
	var reUserPassword=$.trim($("#rpassword").val());
	if(userPhone==""){
		ale("请输入你的手机号！");
	}else if(userPassword==""){
		ale("请输入新密码！");
	}else if(reUserPassword==""){
		ale("请确认新密码");
	}else if(userPassword!=reUserPassword){
		ale("两次输入的密码不一致！");
	}else if(writeCheckwords!=checkWords||checkWords==0){
		ale("请确认您的验证码！");
	}else{
		$.ajax({
			type:'post',
			url:host+'userManage.action',
			data:{action:"forgetPassword",userPhone:userPhone,password:userPassword},
			success:function(result){
				if(result.mes=="错误"){
					ale("请不要更换手机号码");
				}else if(result.mes=="操作成功"){
					ale("修改成功,请重新登录吧！");
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
}

//验证用户手机号
function checkMobile(userPhone){
	if(!(/^1[3|4|5|6|7|8][0-9]\d{4,8}$/.test(userPhone))){
		return false;
	}else{
		return true;
	}
}

