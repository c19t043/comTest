/**
 * Created by vinny on 2015/10/9.
 */

$(function(){
	//$("#login_logo").css("height" , function(){
	//return $(window).width() * 9 / 16;
	//})
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