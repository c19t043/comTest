/**
 * Created by vinny on 2015/10/19.
 */

$(function(){
	$.ajax({
		type:'post',
		url:host+'accountManage.action',
		cache:false,
		async:false,
		data:{action:"point"},
		success:function(result){
			//ale(result.toString());
			$("#point").text(result.userPoint);
			for(var i = 0; i<result.pointList.length; i++){
				var chuli = result.pointList[i].updateTime.replace("T"," ");
				$("#point_detail").append(
					"<div class='balance_list'>"+
					"<p class='date'>"+chuli+"</p>"+
					"<p class='project'>"+result.pointList[i].pointsDes+"</p>"+
					"<p class='sum'>"+result.pointList[i].type+result.pointList[i].points+"</p>" +
					"</div>");
			}
		}
	});
});
