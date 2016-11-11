/**
 * Created by vinny on 2015/10/19.
 */

function addFeedBack(){
	//给平台反馈
	var backContent=$.trim($("#content").val());
	if(backContent==""){
		ale("请输入反馈意见再提交");
	}else{
		$.ajax({
			type:'post',
			url:host+'feedBackManage.action',
			cache:false,
			async:false,
			data:{action:"add",backContent:backContent},
			success:function(result){
				if(result.mes=="操作成功"){
					ale("提交成功");
          setTimeout(function(){
            window.location.href='personalcenter.html';
          },2500);
					
				}else if(result.mes=="未登录"){
					ale("您还没有登录");
          setTimeout(function(){
            window.location.href='login.html';
          },2500);
					
				}
			},
			error: function () {
				layer();
			}
		});
	}
}
