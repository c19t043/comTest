var randomNum = "";
//发送验证码
var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_code').attr('onclick','httpPost()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);   
	}
}
function httpPost(){
	var mobile = $("#phone").val();
	if(mobile==null||mobile==""){
		ale("请输入手机号");
	}
	else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'httpPost.action',
			data : {action:"change",mobile:mobile},
			success : function(result) {
				if(result.mes=="未注册"){
					ale("手机号不存在");
				}else if(result.mes=="请输入您的手机号"){
					ale("您输入的手机号有误！");
				}else{
					randomNum = result.randomNum;
					//ale(result.randomNum);
					numReduce();
				}
			}
		});
	}
}

function modify(){
	var password = $("#new_password").val();
	if($("#phone").val()==""||$("#phone").val()==null){
		ale("请输入手机号");
	}
	else if($("#code").val()==""||$("#code").val()==null){
		ale("请输入验证码");
	}
	else if(password==""||password==null){
		ale("请输入密码");
	}
	else if($("#confirm_password").val()==""||$("#confirm_password").val()==null){
		ale("请确认密码");
	}
	else if(password!=$("#confirm_password").val()){
		ale("两次密码不一致");
	}
	else if($("#code").val()!=randomNum){
		ale("验证码错误");
	}
	else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'modifyPassword.action',
			data : {action:"modifyPassword",password:password},
			success : function(result) {
				//ale(result.mes);
				if(result.mes=="请登录"){
					window.location.href="login.html";
				}
				if(result.mes=="成功"){
					window.location.href="consultation.html";
				}
			}
		});
	}
}