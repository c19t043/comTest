function autoLogin(){
	$.ajax({
		type:'post',
		url:host+'login.action',
		cache:false,
        async:false, 
		data:{action:"autoLogin"},
		success:function(result){
			//ale("RESULT=="+JSON.Stringify(result));
			if(result.mes=="未登录"){
				//window.location.href="login.html";
                window.location.href="login.html";
                return false;
			}
            updateVersionFirst();
		}
	});
}