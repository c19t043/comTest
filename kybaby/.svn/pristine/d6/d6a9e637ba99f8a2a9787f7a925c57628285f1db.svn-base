var checkWords=0;
var oldPhoneCheck=0;
function checkOldPhone(){
	var oldPhone=$.trim($("#oldPhone").val());
	if(oldPhone==""){
		ale("请输入原手机号码");
	}else{
		$.ajax({
			type:'post',
			url:'getCheckWords.action',
			cache:false,
			async:false, 
			data:{action:"checkUserOldPhone",userPhone:oldPhone},
			success:function(result){
				if(result.mes=="未登录"){
					ale("请先登录");
				}else if(result.mes=="错误"){
					ale("请输入你当前的手机号");
				}else{
					ale("验证码为："+result.checkWords);
					checkWords=result.checkWords;
					oldPhoneCheck=result.userPhone;
				}
			},
			error: function () {
				layer();
			}
		});
	}
}

function nexteStep(){
	var oldPhone=$.trim($("#oldPhone").val());
	var inputWords=$.trim($("#inputWords").val());
	if(oldPhone==""){
		ale("请重新输入原手机号重新获取验证码");
	}else if(inputWords==""){
		ale("请输入验证码");
	}else if(oldPhoneCheck!=oldPhone){
		ale("请重新输入原手机号重新获取验证码");
	}else if(inputWords!=checkWords){
		ale("验证码错误");
	}else{
		ale("跳转到下一页");
	}
}

function checkNewPhone(){
	ale("检查新的手机号");
	var newPhone=$.trim($("#newPhone").val());
	if(newPhone==""){
		ale("请输入手机号之后获取验证码");
	}else{
		$.ajax({
			type:'post',
			url:'getCheckWords.action',
			cache:false,
			async:false, 
			data:{action:"regist",userPhone:newPhone},
			success:function(result){
				if(result.mes=="已注册"){
					ale("该手机号已经注册，请更换手机号！");
				}else{
					ale("验证码是："+result.checkWords);
					checkWords=result.checkWords;
					oldPhoneCheck=result.userPhone;
				}
			},
			error: function () {
				layer();
			}
		});
	}
}

function changePhoneNumber(){
	ale("确认修改手机号");
	var newPhone=$.trim($("#newPhone").val());
	var newInputWords=$.trim($("#newInputWords").val());
	if(newPhone==""){
		ale("请重新输入手机号获取验证码");
	}else if(newInputWords==""){
		ale("请输入验证码！");
	}else if(newPhone!=oldPhoneCheck){
		ale("请重新输入手机好获取验证码");
	}else if(newInputWords!=checkWords){
		ale("请输入正确的验证码");
	}else{
		$.ajax({
			type:'post',
			url:'userManage.action',
			cache:false,
			async:false, 
			data:{action:"updateUserPhone",userPhone:newPhone},
			success:function(result){
				if(result.mes=="操作成功"){
					ale("更新成功");
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
}

function getUserPhone(){
	$.ajax({
		type:'post',
		url:'getUserInfo.action',
		cache:false,
		async:false, 
		data:{action:"getUserPhone"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("当前用户的手机号是："+result.userPhone);
			}else if(result.mes=="未登录"){
				ale("请先登录！");
			}
		},
		error: function () {
			layer();
		}
	});
}

function checkUserPhone(){
	var oldPhone=$.trim($("#changePhone").val());
	if(oldPhone==""){
		ale("请输入手机号码");
	}else{
		$.ajax({
			type:'post',
			url:'getCheckWords.action',
			cache:false,
			async:false, 
			data:{action:"checkUserOldPhone",userPhone:oldPhone},
			success:function(result){
				if(result.mes=="未登录"){
					ale("请先登录");
				}else if(result.mes=="错误"){
					ale("请输入你当前的手机号");
				}else{
					ale("验证码为："+result.checkWords);
					checkWords=result.checkWords;
					oldPhoneCheck=result.userPhone;
				}
			},
			error: function () {
				layer();
			}
		});
	}
}

function changePassword(){
	var changePhone=$.trim($("#changePhone").val());
	var changeCheckwords=$.trim($("#changeCheckwords").val());
	var password=$.trim($("#password").val());
	var againPassword=$.trim($("#againPassword").val());
	if(changePhone==""){
		ale("请输入手机号");
	}else if(changeCheckwords==""){
		ale("请输入验证码");
	}else if(password==""){
		ale("请输入密码");
	}else if(againPassword==""){
		ale("请输入确认密码");
	}else if(changePhone!=oldPhoneCheck){
		ale("请重新获取验证码");
	}else if(changeCheckwords!=checkWords){
		ale("验证码错误，请重新输入验证码");
	}else if(password!=againPassword){
		ale("两次输入的密码不一致");
	}else{
		$.ajax({
			type:'post',
			url:'userManage.action',
			cache:false,
			async:false, 
			data:{action:"updatePassword",userPhone:changePhone,password:password},
			success:function(result){
				if(result.mes=="操作成功"){
					ale("修改成功");
				}else if(result.mes=="错误"){
					ale("请重新输入手机手机号，获取验证码");
				}else if(result.mes=="未登录"){
					ale("请重新登录后再修改");
				}
			},
			error: function () {
				layer();
			}
		});
	}
}

function addFeedBack(){
	//给平台反馈
	var backContent=$.trim($("#content").val());
	if(backContent==""){
		ale("请输入输入反馈意见再提交");
	}else{
		$.ajax({
			type:'post',
			url:'feedBackManage.action',
			cache:false,
			async:false, 
			data:{action:"add",backContent:backContent},
			success:function(result){
				if(result.mes=="操作成功"){
					ale("反馈成功");
				}else if(result.mes=="未登录"){
					ale("您还没有登录");
				}
			},
			error: function () {
				layer();
			}
		});
	}
}

//查看用户的基本信息
function getUserSomeInfo(){
	$.ajax({
		type:'post',
		url:'getUserInfo.action',
		cache:false,
		async:false, 
		data:{action:"getUser"},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.usr!=null){
					var usr=result.usr;
					ale("家长姓名是："+usr.parentName);
					ale("宝宝姓名是："+usr.babyName);
					ale("宝宝性别是："+usr.sex);
					ale("出生日期是："+usr.birthday);
					ale("省："+usr.userProvince);
					ale("市："+usr.userCity);
					ale("区："+usr.userArea);
					ale("街道："+usr.userStreet);
					ale("详细地址："+usr.detailAddress);
				}
			}else if(result.mes=="未登录"){
				ale("请先登录系统！");
			}
		},
		error: function () {
			layer();
		}
	});
}

//修改用户的部分信息
function changeUserInfo(){
	var parentName=$.trim($("#parentName").val());
	var parentTel=$.trim($("#parentTel").val());
	var babyName=$.trim($("#babyName").val());
	var babySex=$.trim($("#babySex").val());
	var babyBirthday=$.trim($("#babyBirthday").val());
	var userProvince=$.trim($("#userProvince").val());
	var userCity=$.trim($("#userCity").val());
	var userArea=$.trim($("#userArea").val());
	var userStreet=$.trim($("#userStreet").val());
	var detailAddress=$.trim($("#detailAddress").val());
	var userLng=$.trim($("#userLng").val());
	var userLat=$.trim($("#userLat").val());
	if(parentName==""){
		ale("请输入家长姓名");
	}else if(parentTel=="请填写家长姓名"){
		ale("请填写家长手机号");
	}else if(babyName==""){
		ale("请输入宝宝的姓名");
	}else if(babySex==""){
		ale("请输入宝宝的性别");
	}else if(babyBirthday==""){
		ale("请输选择宝宝的出生日期");
	}else if(detailAddress==""){
		ale("请输入你的详细地址");
	}else{
		$.ajax({
			type:'post',
			url:'userManage.action',
			cache:false,
			async:false, 
			data:{
				action:"changeInfo",
				parentName:parentName,
				userPhone:parentTel,
				babyName:babyName,
				babySex:babySex,
				babyBirthDay:babyBirthday,
				userProvince:userProvince,
				userCity:userCity,
				userArea:userArea,
				userStreet:userStreet,
				detailAddress:detailAddress,
				userLng:userLng,
				userLat:userLat
			},
			success:function(result){
				if(result.mes=="操作成功"){
					ale("修改信息成功");
				}else if(result.mes=="未登录"){
					ale("请先登录");
				}
			},
			error: function () {
				layer();
			}
		});
	}
}
