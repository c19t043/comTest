var checkWords=0;
function getCheckWords(){
	var userPhone=$.trim($("#userPhone").val());
	if(userPhone==""){
		ale("请输入手机号");
	}else if(checkMobile(userPhone)){
		$.ajax({
			type:'post',
			url:'getCheckWords.action',
			data:{action:"forgetPassword",userPhone:userPhone},
			success:function(result){
				if(result.mes=="未注册"){
					ale("您还没有注册哦！");
				}else{
					checkWords=result.checkWords;
					ale("验证码="+checkWords);
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
	var userPhone=$.trim($("#userPhone").val());
	var writeCheckwords=$.trim($("#checkWords").val());
	var userPassword=$.trim($("#newPassword").val());
	var reUserPassword=$.trim($("#reNewPassword").val());
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
			url:'userManage.action',
			data:{action:"forgetPassword",userPhone:userPhone,password:userPassword},
			success:function(result){
				if(result.mes=="错误"){
					ale("请不要更换手机号码");
				}else if(result.mes=="操作成功"){
					ale("修改成功,请重新登录吧！");
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