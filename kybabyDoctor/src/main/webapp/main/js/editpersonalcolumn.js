function submite(){
	if($("#biaoti").val()==""||$("#biaoti").val()==null){
		ale("请输入标题");
	}
	if($("#zhengwen").val()==""||$("#zhengwen").val()==null){
		ale("请输入文章内容");
	}
	else{
		$.ajax({
			type : 'post',
			async: false ,
			url : host+'articleManage.action',
			data : {action:"submite",title:$("#biaoti").val(),text:$("#zhengwen").val()},
			success : function(result) {
				if(result.mes=="成功"){
					ale("文章提交成功，正在审核！");
          setTimeout(function(){
            window.location.href="mypage.html";
          },2500);
					
				}
				else if(result.mes=="请登录"){
					window.location.href="login.html";
				}
				else{
					ale("请补全文章");
				}
			}
		});
	}
}