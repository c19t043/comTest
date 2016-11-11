function autoLogin(){
	$.ajax({
		type:'post',
		url:host+'userManage.action',
		cache:false,
        async:false, 
		data:{action:"autoLogin"},
		success:function(result){
			if(result.mes=="未登录"){
				//ale("用户还没有登录");
			}
		}
	});
}