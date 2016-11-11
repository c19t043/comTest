var articleId="";
$(function(){
	articleId=window.location.search.substring(1);
	toSomeArticle(articleId);	
});

//点击个人专栏
function toSomeArticle(articleId){
	$.ajax({
		type:'post',
		url:host+'getDoctorArticleInfo.action',
		cache:false,
        async:false, 
		data:{action:"getArticleInstance",articleId:articleId},
		success:function(result){
			if(result.mes=="操作成功"){
				var article =result.someArticle;
				
				$("#doctorArticle").append(
						"<span class='tittle'>"+article.articleTitle+"</span>"+
					    "<p>"+article.articleContent+"</p>"					
				);
				
				if(result.articleCommentsList!=null){
					for(var i=0;i<result.articleCommentsList.length;i++){
						var comments=result.articleCommentsList[i];
						if(comments[1] == '' || comments[1] == null || comments[1] =='null' ){
							comments[1] = 'lt_user.png';
						}
						$("#userCommentsList").append(
								"<div class='touxiang'><img src='"+hostBG+"images/userFaceIcon/"+comments[1]+"'/></div>"+
						    	"<div class='box'>"+
						        	"<span>"+comments[0]+"</span><br/>"+
						        	"<span class='text'>"+comments[2]+"</span><br/>"+
						        	"<span class='text'>"+comments[3]+"</span>"+
						    	"</div>"
						);						
					}
				}else{
					//ale("该专栏还没有被评论哦！");
				}
			}
		},
		error: function () {
			layer();
		}
	});
}


//评论个人专栏
function addComment(){
	var userComments=$.trim($("#comments").val());
	if(userComments==""){
		ale("请输入内容之后再来评论吧！");
	}else{
		$.ajax({
			type:'post',
			url:host+'doctorArticleManage.action',
			cache:false,
	        async:false, 
			data:{action:"addComment",articleId:articleId,userComments:userComments},
			success:function(result){
				if(result.mes=="请登录"){
					ale("您还没有登录，请登录后再来评论吧！");
				}else if(result.mes=="操作成功"){
					ale("评论成功,等待管理员审核...");
					
				}
			},
			error: function () {
				layer();
			}
		});
	}
}