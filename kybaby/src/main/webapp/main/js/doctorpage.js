$(function(){
	var doctorId=window.location.search.substring(1);
	toPersonalPage(doctorId);
});


//进入医生个人主页
function toPersonalPage(doctorId){
	$.ajax({
		type:'post',
		url:host+'getDoctorInfo.action',
		cache:false,
        async:false, 
		data:{action:"doctorDetail",doctorId:doctorId},
		success:function(result){
			if(result.mes=="操作成功"){
				var doctor=result.someDoctor;
				if(doctor.doctorImage == '' || doctor.doctorImage == null || doctor.doctorImage == undefined){
					doctor.doctorImage = 'lt_doctor.png';
				}
				$("#docPageImage").html("<img src='"+hostBG+"images/doctorFaceIcon/"+doctor.doctorImage+"'/>");
				$("#docPageName").text(doctor.doctorName);
				$("#docPageTitle").text(doctor.doctorTitle);
				$("#docPageEmployer").text(doctor.doctorEmployer);
				
				//专业方向
				var docMajor="";
				if(result.someDoctorMajorList!=null&&result.someDoctorMajorList.length>0){
					for(var j=0;j<result.someDoctorMajorList.length;j++){
						docMajor+=" "+result.someDoctorMajorList[j];
					}
				}else{
					
				}
				$("#docPageMajor").text(docMajor);
				
				//出诊次数
				$("#docPageUseTime").text(doctor.visitedTimes);
				
				//星级

				
							if(doctor.seiviceStarLevel == 0){
								for(var o=0;o < 5;o++){
									$("#serviceStarLevel").append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor.seiviceStarLevel;o++){
									$("#serviceStarLevel").append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor.seiviceStarLevel;o++){
									$("#serviceStarLevel").append("<img src='images/icon_starkong.png'>");
								}							
							}

							if(doctor.onTimeStarLevel == 0){
								for(var o=0;o < 5;o++){
									$("#onTimeStarLevel").append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor.onTimeStarLevel;o++){
									$("#onTimeStarLevel").append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor.onTimeStarLevel;o++){
									$("#onTimeStarLevel").append("<img src='images/icon_starkong.png'>");
								}							
							}

							if(doctor.dutyStarLevel == 0){
								for(var o=0;o < 5;o++){
									$("#dutyStarLevel").append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor.dutyStarLevel;o++){
									$("#dutyStarLevel").append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor.dutyStarLevel;o++){
									$("#dutyStarLevel").append("<img src='images/icon_starkong.png'>");
								}							
							}


							if(doctor[6] == 0){
								for(var o=0;o < 5;o++){
									$("#duty_"+doctor[0]).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[6];o++){
									$("#duty_"+doctor[0]).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[6];o++){
									$("#duty_"+doctor[0]).append("<img src='images/icon_starkong.png'>");
								}							
							}							

							if(doctor[7] == 0){
								for(var o=0;o < 5;o++){
									$("#onTime_"+doctor[0]).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[7];o++){
									$("#onTime_"+doctor[0]).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[7];o++){
									$("#onTime_"+doctor[0]).append("<img src='images/icon_starkong.png'>");
								}							
							}					
				
				
				
				
				
				
				
				
				
				
				//评价标签
				if(result.doctorAssessmentTagList!=null&&result.doctorAssessmentTagList.length>0){
					$("#docAssessTagList").html("");
					for(var i=0;i<result.doctorAssessmentTagList.length;i++){
						var tag=result.doctorAssessmentTagList[i];
						//ale("第"+(i+1)+"个评价标签是："+tag[0]+"该标签被评价的次数是："+tag[1]);
						$("#docAssessTagList").append("<li>"+tag[0]+"("+tag[1]+")</li>");
					}
				}else{
					//ale("该医生还没有被评价哦");
				}
				
				//服务产品
				$("#docServiceList").html("<p class='tit'>"+doctor.doctorName+"可服务产品</p>");
				if(result.doctorProductList!=null&&result.doctorProductList.length>0){
					for(var i=0;i<result.doctorProductList.length;i++){
						var prodcut=result.doctorProductList[i];
						$("#docServiceList").append(
								
							   "<div class='service_box' onclick=\"window.location.href='"+host+"productdetails.html?prodName="+encodeURIComponent(prodcut.name)+"&doctorId="+doctorId+"'\">"+
									"<p class='service_img'><img src='"+hostBG+"images/product/"+prodcut.smallPicture+"'/></p>"+
									"<p class='service_tit'>"+prodcut.name+"</p>"+
									//modify by xchao 2016-01-05 医生主页不需要显示该产品总体服务次数和价格
									//"<div class='clearfix'>"+
									//	"<p class='sale'>已售出"+prodcut.productUseTime+"次</p>"+
									//	"<p class='price'>&yen;"+prodcut.totalPrice+"</p>"+
									//"</div>"+
								"</div>	"
						);
						//ale("该医生的第"+(i+1)+"个服务产品的小图是："+prodcut.smallPicture+",名称是："+prodcut.name+",售出次数为："+prodcut.productUseTime+",价格是："+prodcut.totalPrice);
					}
				}else{
					//ale("该医生还没有服务产品哦！");
				}
				
				//个人专栏
				$("#doctoryPersonalTopic").html("<div class='mypage_personal_top'>"+
													"<p class='personal'>个人专栏</p>"+
													"</div>");
				if(result.doctorArticleList!=null&&result.doctorArticleList.length!=0){
					for(var i =0;i<result.doctorArticleList.length;i++){
						var article=result.doctorArticleList[i];
						$("#doctoryPersonalTopic").append(
								"<div class='myarticle' onclick=\"window.location.href='personal_column.html?"+article.id+"'\">"+
								"<p class='article_date'>"+article.publishTime+"</p>"+
								"<p class='article_title'>"+article.articleTitle+"</p>"+
								"<div class='clearfix'>"+
									"<p class='comment'>评论（"+article.commentCount+"）</p>"+
									"<p class='view'>浏览（"+article.hitCount+"）</p>"+
									"<p class='right'>&gt;</p>"+
								"</div>"+
							"</div>"						
						);
						//ale("该医生的第"+(i+1)+"篇个人专栏的发表时间是："+article.publishTime+",文章标题是："+article.articleTitle+",文章的点评次数是："+article.commentCount+",浏览次数是："+article.hitCount);
					}
				}else{
					//ale("该医生还没有个人专栏！");
				}
				
				//距离
				if(result.doctorDistance!=null){
					//ale("用于与该医生的距离为："+result.doctorDistance);
					$("#docPageDistance").text(result.doctorDistance);
				}else{
					//ale("计算不出距离");
					$("#docPageJuli")[0].style.display='none';
				}
			}
		},
		error: function () {
			layer();
		}
	});
}
