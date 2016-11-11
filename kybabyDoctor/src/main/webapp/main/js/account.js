$(function(){
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'accountManage.action',
		data : {action:"all"},
		success : function(result) {
			if(result.mes=="成功"){
				$("#balance").text(result.balance);
				$("#point").text(result.point);
			}
			if(result.mes=="请登录"){
				window.location.href="login.html";
			}
		}
	});
});