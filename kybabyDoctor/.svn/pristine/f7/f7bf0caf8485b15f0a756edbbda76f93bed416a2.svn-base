var urlphone;
var url_str = window.location.search;
if(url_str.indexOf('?') != -1){
	urlphone = url_str.substring(1,18)
	urlphone = urlphone.split('=')[1];
}
$(function(){
	if(urlphone != undefined){
		if(urlphone.length == 11){
		$('#yaoqing_code').val(urlphone);
		}
	}
});
function regist(){
	var phone = $("#regist_phone").val();
	var password = $("#regist_password").val();
	var referPhone=$.trim($("#yaoqing_code").val());
	if(phone==null||phone==""){
		ale("请输入手机号");
	}
	else if($("#regist_code").val()==null||$("#regist_code").val()==""){
		ale("请输入验证码");
	}
	else if(password==null||password==""){
		ale("请输入密码");
	}
	else if($("#regist_rpassword").val()==null||$("#regist_rpassword").val()==""){
		ale("请确认密码");
	}
	else if($("#regist_password").val()!=$("#regist_rpassword").val()){
		ale("两次密码不匹配");
	}
	else if($("#regist_code").val()!= randomNum){
		ale("验证码错误");
	}
	else if(!$("#cbUserAgreement").is(":checked")){
		ale("请先阅读医生协议");
	}
	else if(phone==referPhone){
		ale("不能推荐自己哦！");
	}else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'regist.action',
			data : {action:"regist",phone:phone,password:password,referPhone:referPhone},
			success : function(result) {
				if(result.mes=="已注册"){
					ale("用户已存在");
				}
				else if(result.mes=="成功"){
					//window.location.href="consultation.html";
					window.location.href="roleselect.html";
				}
			}
		});
	}
}
var randomNum = "";
//发送验证码
var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_reg_code').attr('onclick','httpPost()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_reg_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_reg_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);   
	}
}
function httpPost(){
	var mobile = $("#regist_phone").val();
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
					ale("手机号已注册");
				}else{
					randomNum = result.randomNum;
					//ale(result.randomNu m);
					numReduce(); 
				}
			}
		});
	}
}

$(function(){
	$("#doctor_xieyi").click(function(){
		$(window).scrollTop(0);
		$('#doctor_xieyi_box').load(hostBG + 'webPage/doctorProtocol.html').show();
		$('#doctor_xieyi_tit').show();
	});
});

function hide_doctor_xieyi(){
	$('#doctor_xieyi_box').empty().hide();
	$('#doctor_xieyi_tit').hide();
}