$(function(){
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'homePageManage.action',
		data : {action:"homePage"},
		success : function(result) {
			//ale(JSON.stringify(result));
			var someMes = result.backMes.split("::");
			$("#name").text(someMes[0]);
			if(someMes[1] == null ||someMes[1] == 'null'||someMes[1] == ''){
				someMes[1] = 'lt_doctor.png';
			}
			$('#myicon').attr('src',hostBG+"/images/doctorFaceIcon/"+someMes[1]);
			$("#title").text("（"+someMes[2]+"）");
			$("#totalOrder").text("（"+someMes[7]+"）");
			$("#employ").text("工作单位:"+someMes[3]);
			for(var i=0;i<someMes[4];i++){
				$("#serviceStar").append("<img src='images/icon_star.png'>");
			}
			for(var j=0;j<someMes[5];j++){
				$("#dutyStar").append("<img src='images/icon_star.png'>");
			}
			for(var k=0;k<someMes[6];k++){
				$("#onTimeStar").append("<img src='images/icon_star.png'>");
			}
			if(result.doctorArticleList!=null){
				for(var i=0;i<result.doctorArticleList.length;i++){
					$("#doctorArticle").append("<div class='myarticle' onclick=\"window.location.href='personalcolumn.html?"+result.doctorArticleList[i].id+"'\">"+
					"<div class='clearfix'>" +
						"<p class='article_date'>"+result.doctorArticleList[i].publishTime+"</p>"+
						"<p class='article_state'>"+result.doctorArticleList[i].articleStatus+"</p>" +
					"</div>"+
					"<p class='article_title'>"+result.doctorArticleList[i].articleTitle+"</p>"+
					"<div class='clearfix'>"+
						"<p class='comment'>评论（"+result.doctorArticleList[i].commentCount+"）</p>"+
						"<p class='view'>浏览（"+result.doctorArticleList[i].hitCount+"）</p>"+
						"<p class='right'>&gt;</p>"+
					"</div>"+
				"</div>"+

				"<p class='gray_3'></p>");

				}
			}
			if(result.tag!=null){
				for(var j=0;j<result.tag.length;j++){
					$("#someTag").append("<li>"+result.tag[j][1]+"("+result.tag[j][0]+")</li>");
				}
			}
			if(result.majorNameList!=null){
				var someMajorName = "";
				for(var k=0;k<result.majorNameList.length;k++){
					if(k == result.majorNameList.length-1){
						someMajorName+=result.majorNameList[k];
					}
					else{
						someMajorName+=result.majorNameList[k]+"、";
					}
				}
				$("#major").text("专业方向："+someMajorName);
			}
			if(result.someProductList!=null){
				for(var n=0;n<result.someProductList.length;n++){
					var someProd = result.someProductList[n].split("::");
					$("#serviceProduct").append("<div class='service_box'>"+
							"<p class='service_img'><img src='"+hostBG+"images/product/"+someProd[1]+"'></p>"+
							"<p class='service_tit'>"+someProd[0]+"</p>"+
							"<div class='clearfix'>"+
								"<p class='sale'>已售出"+someProd[2]+"次</p>"+
								"<p class='price'>&yen;"+someProd[3]+"</p>"+
							"</div>"+
						"</div>");
				}
			}
		}
	});
});