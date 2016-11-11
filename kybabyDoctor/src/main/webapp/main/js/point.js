$(function(){
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'accountManage.action',
		data : {action:"point"},
		success : function(result) {
			if(result.mes=="成功"){
				$("#point").text(result.point);
				if(result.pointDetail!=null){
					for(var i=0;i<result.pointDetail.length;i++){
						$("#detail").append("<div class='balance_list'>"+
								"<p class='date'>"+result.pointDetail[i].updateTime+"</p>"+
								"<p class='project'>"+result.pointDetail[i].pointsDes+"</p>"+
								"<p class='sum'>"+result.pointDetail[i].type+result.pointDetail[i].points+"</p>"+
								"</div>"
								);
					}
				}
			}
			if(result.mes=="请登录"){
				window.location.href="login.html";
			}
		}
	});
})