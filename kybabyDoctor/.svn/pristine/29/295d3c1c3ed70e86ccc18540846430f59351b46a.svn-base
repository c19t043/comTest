function takePoint(){
	if($("#pointNum").val()==""||$("#pointNum").val()==null){
		ale("请输入兑换积分数");
	}
	else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'accountManage.action',
			data : {action:"takePoint",takePoint:$("#pointNum").val()},
			success : function(result) {
				if(result.mes=="积分小于500"){
					ale(result.mes);
				}
				if(result.mes=="积分不足"){
					ale(result.mes);
				}
				if(result.mes=="不是100的倍数"){
					ale("输入积分数为100的倍数");
				}
				if(result.mes=="成功"){
					window.location.href="point.html";
				}
				if(result.mes=="请登录"){
					window.location.href="login.html";
				}
			}
		});
	}
}