var randomNum = "";
//发送验证码
function httpPost(){
	var mobile = $("#original_phone").val();
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
				}
				else if(result.mes=="请输入您的手机号"){
					ale("这不是您的手机号");
				}
				else{
					randomNum = result.randomNum;
					//ale(result.randomNum);
					numReduce();
				}
			}
		});
	}
}
function newHttpPost(){
	var mobile = $("#new_phone").val();
	if(mobile==null||mobile==""){
		ale("请输入手机号");
	}
	else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'httpPost.action',
			data : {action:"regist",mobile:mobile},
			success : function(result) {
				if(result.mes=="已注册"){
					ale("手机号已被注册");
				}else{
					randomNum = result.randomNum;
					//ale(result.randomNum);
					numReduce_next();
				}
			}
		});
	}
}
function nextStep(){
	if($("#original_phone").val()==""||$("#original_phone").val()==null){
		ale("请输入手机号");
	}
	else if($("#original_code").val()==""||$("#original_code").val()==null){
		ale("请输入验证码");
	}
	else if($("#original_code").val()!= randomNum){
		ale("验证码错误");
	}
	else{

		$('.step_1').next().hide();
		$('.step_1').hide();
		$('.step_2').next().show();
		$('.step_2').show();
	}
}
function modifyPhone(){
	ale(randomNum+"验证码");
	var newPhone = $("#new_phone").val();
	if(newPhone==""||newPhone==null){
		ale("请输入手机号");
	}
	else if($("#new_code").val()==""||$("#new_code").val()==null){
		ale("请输入验证码");
	}
	else if($("#new_code").val()!= randomNum){
		ale("验证码错误");
	}
	else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'modifyPhone.action',
			data : {action:"modifyPhone",newPhone:newPhone},
			success : function(result) {
				//ale(result.mes);
				if(result.mes=="成功"){
					window.location.href="consultation.html";
				}else{
					ale("请检查您输入的手机号和验证码!");
				}
			}
		});
	}
	
}
var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_original_code').attr('onclick','httpPost()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_original_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_original_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);   
	}
}
var ttt_next=60;
function numReduce_next(){
	ttt_next--;
	if(ttt_next <= 0){
		ttt_next = 60;
		$('#get_new_code').attr('onclick','newHttpPost()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_new_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_new_code').text("("+ttt_next+"s)");
		setTimeout(numReduce_next, 1000);   
	}
}