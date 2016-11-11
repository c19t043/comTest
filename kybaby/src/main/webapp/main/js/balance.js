/**
 * Created by vinny on 2015/10/19.
 */

$(function(){
	$.ajax({
		type:'post',
		url:host+'accountManage.action',
		cache:false,
		async:false,
		data:{action:"balance"},
		success:function(result){
			//ale(typeof result.accountList);
			$("#balance").text(result.userBalance);
			for(var i= 0;i<result.accountList.length;i++){
				//var detail_num = result.accountList.toString();
				//ale(detail_num);
				var chuli = result.accountList[i].updateTime.replace("T"," ");
				$("#balance_detail").append(
					"<div class='balance_list'>"+
					"<p class='date'>"+chuli+"</p>"+
					"<p class='project'>"+result.accountList[i].accountDesc+"</p>"+
					"<p class='sum'>"+result.accountList[i].type+result.accountList[i].amount+"元</p></div>");
			}
		},
		error: function () {
			layer();
		}
	});
});