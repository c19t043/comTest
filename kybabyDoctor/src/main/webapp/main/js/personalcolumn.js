$(function(){
	var articleId = window.location.search.substring(1);
	//ale(articleId);
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'articleManage.action',
		data : {action:"get",articleId:articleId},
		success : function(result) {
			//ale(result.detailList);
//			ale(result.mes);
			var strOne = result.detailList.split("::");
//			ale(typeof(strOne[0]));
			var strOneOne = strOne[0].split("!:");
			$("#textArea").append("标题："+strOneOne[0]+"<br/>正文："+strOneOne[1]+"");
			//ale(strOne[1]);
			var strOneTow = strOne[1].split(":!!:");
			//ale(strOneTow.length);
			for(var i=0;i<strOneTow.length-1;i++){
				var strTow = strOneTow[i].split(":!:");
				$("#userComment").append("<div class='article_comment_list'>"+
						"<div class='article_comment_left'><img src='"+hostBG+"images/userFaceIcon/"+strTow[3]+"'></div>"+
						"<div class='article_comment_right'>"+
				"<p class='comment_username'>"+strTow[2]+"</p>"+
				"<p class='comment_date'>"+strTow[1]+"</p>"+
				"<p class='comment_detail'>"+strTow[0]+"</p>"+
			"</div>"+
		"</div>");
			}
		}
	});
}) 