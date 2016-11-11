/**
 * Created by vinny on 2015/10/19.
 */

$(function(){
	$.ajax({
		type:'post',
		url:host+'accountManage.action',
		cache:false,
		async:false,
		data:{action:"total"},
		success:function(result){
			//ale("余额："+result.userBalance);
			//ale("积分："+result.userPoint);
			$("#balance").text(result.userBalance);
			$("#point").text(result.userPoint);
		},
		error: function () {
			layer();
		}
	});
})