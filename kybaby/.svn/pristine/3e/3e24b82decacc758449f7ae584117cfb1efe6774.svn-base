function userLogout(){
	$.ajax({
		type:'post',
		url:'userManage.action',
		data:{action:"logout"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("退出登录");				
        setTimeout(function(){
          window.location.href="userLogin.html";
        },2500);
			}
		},
		error: function () {
			layer();
		}
	});
}