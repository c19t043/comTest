//created by lihuohao


function doctorShow(){
	$.ajax({
		type:'post',
		url:host+'getDoctorInfo.action',
		cache:false,
        async:false, 
		data:{action:"getDoctorList"},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.doctorSomeInfoList!=null&&result.doctorSomeInfoList.length!=0){
					for(var i=0;i<result.doctorSomeInfoList.length;i++){
						var doctor=result.doctorSomeInfoList[i];
						ale("第"+(i+1)+"个医生的ID是："+doctor[0]);
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							ale("第"+(i+1)+"个专长方向是："+result.doctorMajorList[i][j]);
						}
						ale("第"+(i+1)+"个医生的头像是："+doctor[2]);
						ale("第"+(i+1)+"个医生的姓名是："+doctor[3]);
						ale("第"+(i+1)+"个医生的职称是："+doctor[4]);
						ale("第"+(i+1)+"个医生的服务态度星级是："+doctor[5]);
						ale("第"+(i+1)+"个医生的责任感星级是："+doctor[6]);
						ale("第"+(i+1)+"个医生的准时星级是："+doctor[7]);
						ale("第"+(i+1)+"个医生的出诊次数是："+doctor[8]);
						if(result.doctorDistanceList[i]!=undefined){
							ale("第"+(i+1)+"个医生的距离是："+result.doctorDistanceList[i]);
						}
					}
				}else{
					ale("暂无医生");
				}
			} else if(result.mes=="无医生"){
				ale("暂无医生");
			}
		},
		error: function () {
			layer();
		}
	});
}

//进入医生个人主页
function toPersonalPage(){
	var doctorId=$.trim($("#doctorId").val());
	$.ajax({
		type:'post',
		url:host+'getDoctorInfo.action',
		cache:false,
        async:false, 
		data:{action:"doctorDetail",doctorId:doctorId},
		success:function(result){
			if(result.mes=="操作成功"){
				var doctor=result.someDoctor;
				ale("医生的头像是："+doctor.doctorImage);
				ale("医生的姓名是："+doctor.doctorName);
				ale("医生的职称是："+doctor.doctorTitle);
				ale("医生的出诊次数是："+doctor.visitedTimes);
				ale("医生的工作单位是："+doctor.doctorEmployer);
				if(result.someDoctorMajorList!=null&&result.someDoctorMajorList.length>0){
					for(var i=0;i<result.someDoctorMajorList.length;i++){
						ale("第"+(i+1)+"个专业方向是："+result.someDoctorMajorList[i]);
					}
				}else{
					ale("该医生还没有填写专业方向！");
				}
				ale("医生的服务态度星级是："+doctor.seiviceStarLevel);
				ale("医生的准时度星级是："+doctor.onTimeStarLevel);
				ale("医生的责任感星级是："+doctor.dutyStarLevel);
				if(result.doctorAssessmentTagList!=null&&result.doctorAssessmentTagList.length>0){
					for(var i=0;i<result.doctorAssessmentTagList.length;i++){
						var tag=result.doctorAssessmentTagList[i];
						ale("第"+(i+1)+"个评价标签是："+tag[0]+"该标签被评价的次数是："+tag[1]);
						
					}
				}else{
					ale("该医生还没有被评价哦");
				}
				if(result.doctorProductList!=null&&result.doctorProductList.length>0){
					for(var i=0;i<result.doctorProductList.length;i++){
						var prodcut=result.doctorProductList[i];
						ale("该医生的第"+(i+1)+"个服务产品的小图是："+prodcut.smallPicture+",名称是："+prodcut.name+",售出次数为："+prodcut.productUseTime+",价格是："+prodcut.totalPrice);
					}
				}else{
					ale("该医生还没有服务产品哦！");
				}
				if(result.doctorArticleList!=null&&result.doctorArticleList.length!=0){
					for(var i =0;i<result.doctorArticleList.length;i++){
						var article=result.doctorArticleList[i];
						ale("该医生的第"+(i+1)+"篇个人专栏的发表时间是："+article.publishTime+",文章标题是："+article.articleTitle+",文章的点评次数是："+article.commentCount+",浏览次数是："+article.hitCount);
					}
				}else{
					ale("该医生还没有个人专栏！");
				}
				if(result.doctorDistance!=null){
					ale("用于与该医生的距离为："+result.doctorDistance);
				}else{
					ale("计算不出距离");
				}
			}
		},
		error: function () {
			layer();
		}
	});
}

//点击个人专栏

function toSomeArticle(){
	var articleId=$.trim($("#articleId").val());
	$.ajax({
		type:'post',
		url:host+'getDoctorArticleInfo.action',
		cache:false,
        async:false, 
		data:{action:"getArticleInstance",articleId:articleId},
		success:function(result){
			if(result.mes=="操作成功"){
				var article =result.someArticle;
				ale("个人专栏的标题是："+article.articleTitle);
				ale("个人专栏的内容是："+article.articleContent);
				if(result.articleCommentsList!=null){
					for(var i=0;i<result.articleCommentsList.length;i++){
						var comments=result.articleCommentsList[i];
						ale("第"+(i+1)+"条评论的评论人是："+comments[0]);
						ale("第"+(i+1)+"条评论的评论人头像是："+comments[1]);
						ale("第"+(i+1)+"条评论的评论时间是："+comments[2]);
						ale("第"+(i+1)+"条评论的评论是："+comments[3]);
					}
				}else{
					ale("该专栏还没有被评论哦！");
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
	var articleId=$.trim($("#newArticleId").val());
	var userComments=$.trim($("#comments").val());
	if(userComments==""){
		ale("请输入内容之后再来评论吧！");
	}else{
		$.ajax({
			type:'post',
			url:'doctorArticleManage.action',
			cache:false,
	        async:false, 
			data:{action:"addComment",articleId:articleId,userComments:userComments},
			success:function(result){
				if(result.mes=="请登录"){
					ale("您还没有登录，请登录后再来评论吧！");
				}else if(result.mes=="操作成功"){
					ale("评论成功");
				}
			},
			error: function () {
				layer();
			}
		});
	}
}
