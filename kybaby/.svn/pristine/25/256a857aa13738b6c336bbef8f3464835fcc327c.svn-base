var oldPhone,oldCheckWords,newCheckWords,newPhone;
$(function(){
	$('#change_password1_').click(function(){
		if($('#phone').val() != oldPhone){
			$('#phone').val('').focus();
			return false;
		}else if($('#code').val() != oldCheckWords){
			$('#code').val('').focus();
			return false;		
		}
		$('#change_password1,#change_password1_').hide();
		$('#change_password2,#change_password2_').show();
	});

});


function checkOldPhone(){
	oldPhone=$.trim($("#phone").val());
	if(oldPhone==""){
		ale("请输入原手机号码");
		return false;
	}else if(oldPhone.length != 11 || isNaN(oldPhone) || oldPhone.substring(0,1) !=1){
		ale('手机号格式错误');
		return false;
	}
	$.ajax({
		type:'post',
		url:host+'getCheckWords.action',
		cache:false,
		async:false, 
		data:{action:"checkUserOldPhone",userPhone:oldPhone},
		success:function(result){
			if(result.mes=="未登录"){
				ale("请先登录");				
        setTimeout(function(){
          window.location.href="login.html";
        },2500);
			}else if(result.mes=="错误"){
				ale("请输入你当前的手机号");
			}else{
				//ale("验证码为："+result.checkWords);
				oldCheckWords=result.checkWords;
				oldPhone=result.userPhone;
				numReduce();
			}
		},
		error: function () {
			layer();
		}
	});
	
}
var ttt=60;
function numReduce(){
	ttt--;
	if(ttt <= 0){
		ttt = 60;
		$('#get_reg_code').attr('onclick','checkOldPhone()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#get_reg_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#get_reg_code').text("("+ttt+"s)");
		setTimeout(numReduce, 1000);   
	}
}
var ttt_next=60;
function numReduce_next(){
	ttt_next--;
	if(ttt_next <= 0){
		ttt_next = 60;
		$('#nget_reg_code').attr('onclick','checkNewPhone()').text('获取验证码').css('backgroundColor','#50c1e9');
	}else{
		$('#nget_reg_code').removeAttr('onclick').css('backgroundColor','#dadada');
		$('#nget_reg_code').text("("+ttt_next+"s)");
		setTimeout(numReduce_next, 1000);   
	}
}
function checkNewPhone(){
	newPhone=$.trim($("#nphone").val());
	if(newPhone==""){
		ale("请输入新手机号");
		return false;
	}else if(newPhone.length != 11 || isNaN(newPhone) || newPhone.substring(0,1) !=1){
		ale('请输入正确手机号');
		return false;
	}else if(oldPhone == newPhone){
		ale('不能为原手机号');
		return false;
	}

	$.ajax({
		type:'post',
		url:host+'getCheckWords.action',
		cache:false,
		async:false, 
		data:{action:"regist",userPhone:newPhone},
		success:function(result){
			if(result.mes=="已注册"){
				ale("该手机号已经注册，请更换手机号！");
			}else{
				//ale("验证码是："+result.checkWords);
				newCheckWords=result.checkWords;
				newPhone=result.userPhone;
				numReduce_next();
			}
		},
		error: function () {
			layer();
		}
	});

}
function changePhoneSubmit(){
	if(oldPhone == undefined || oldCheckWords == undefined || newCheckWords == undefined || newPhone == undefined){
		ale('输入不合法');
		return false;
	}else if($('#ncode').val().trim() != newCheckWords){
		ale('验证码错误');
		return false;
	}

	$.ajax({
		type:'post',
		url:host+'userManage.action',
		cache:false,
		async:false, 
		data:{action:"updateUserPhone",userPhone:newPhone},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("更新成功");
				
        setTimeout(function(){
          window.location.href = 'consultation.html';
        },2500);
			}else if(result.mes=="错误"){
				ale("请重新输入手机手机号，获取验证码");
			}else if(result.mes=="未登录"){
				ale("请重新登录，再进行修改！");
			}
		},
		error: function () {
			layer();
		}
	});

}