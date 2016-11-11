
//获取分类
function getAllRingCategory(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
    async:false, 
		data:{action:"allCategory"},
		success:function(result){
			if(result.mes=="操作成功"){
				$('#circle_type').empty();
				for(var i=0;i<result.allRingCategory.length;i++){
					var cla="greyli";
					if(i == 0){
						cla = 'actli';
					}
					$('#circle_type').append(
						"<li data_state='"+result.allRingCategory[i][2]+"' onclick=\"getSomeCategory('"+result.allRingCategory[i][0]+"',this)\" class='"+cla+"' zjid='"+result.allRingCategory[i][0]+"'><span>"+result.allRingCategory[i][1]+"</span></li>"
					);
				}
				var tag = decodeURIComponent(location.search).substring(1);
				if(tag == ''){
					$('#circle_type li').eq(0).trigger('click');
				}else{
					$('#circle_type li').filter(function(){
						return $(this).text() == tag;
					}).trigger('click');
				}
				
			}else if(result.mes=="无分类"){
				ale("还没有分类哦！");
			}
		}
	});
}
//某个分类的列表
function getSomeCategory(zjid,obj){

	$(obj).siblings().attr('class','greyli');
	$(obj).attr('class','actli');
	
	var categoryId=zjid;
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
    async:false, 
		data:{action:"someCategory",categoryId:categoryId},
		success:function(result){

			$('#article_list').empty();
			if(result.someRingCategory != null){
				for(var i=0;i < result.someRingCategory.length;i++){
					$('#article_list').append(
						"<div class='article_con'>"+
			 				"<div onclick=\"getSomeDoctorRingInfo('"+result.someRingCategory[i][0]+"','"+result.someRingCategory[i][2]+"')\" class='con_row1'>"+
			 					"<img src='"+hostBG+"images/ring/"+result.someRingCategory[i][1]+"' width='40px' height='40px'>"+
			 				"</div>"+
			 				"<div onclick=\"getSomeDoctorRingInfo('"+result.someRingCategory[i][0]+"','"+result.someRingCategory[i][2]+"')\" class='con_row2'>"+
			 					"<h3>"+result.someRingCategory[i][2]+"</h3>"+
			 					"<h4>"+result.someRingCategory[i][3]+"</h4>"+
			 					"<div class='con_row2_num'>"+
			 						"<span>发帖数：<span>"+result.someRingCategory[i][4]+"</span></span>"+
			 						"<span style='margin-left:20px'>浏览次数：<span>"+result.someRingCategory[i][5]+"</span></span>"+
			 					"</div>"+
			 				"</div>"+
			 				"<div id='con_row3_"+i+"' zjid='"+result.someRingCategory[i][0]+"' class='con_row3'>"+
			 				"</div>"+				
			 			"</div>"+
			 			"<p class='gray_3'></p>"
					);
					if(result.someRingCategory[i][6] == "N"){
						$("#con_row3_"+i).attr("onclick","focu('gz','"+result.someRingCategory[i][0]+"')").append(
			 				"<p class='a'>+</p>"+
			 				"<p class='b'>关注</p>"
						);
					}else if(result.someRingCategory[i][6] == "Y"){
						$("#con_row3_"+i).attr("onclick","focu('qx','"+result.someRingCategory[i][0]+"')").append(
			 				"<p class='a color_blue'>&radic;</p>"+
			 				"<p class='b color_blue'>取消</p>"
						);				
					}
					
	
	 			
	 			
				}
			}

			if(result.recommendRingCategory != null && $(obj).attr('data_state') == 'Y'){
				$('#article_list').append("<p class='rq_tj'>热圈推荐</p>");
				
				for(var i=0;i < result.recommendRingCategory.length;i++){
					$('#article_list').append(
						"<div class='article_con'>"+
			 				"<div onclick=\"getSomeDoctorRingInfo('"+result.recommendRingCategory[i][0]+"','"+result.recommendRingCategory[i][2]+"')\" class='con_row1'>"+
			 					"<img src='"+hostBG+"images/ring/"+result.recommendRingCategory[i][1]+"' width='40px' height='40px'>"+
			 				"</div>"+
			 				"<div onclick=\"getSomeDoctorRingInfo('"+result.recommendRingCategory[i][0]+"','"+result.recommendRingCategory[i][2]+"')\" class='con_row2'>"+
			 					"<h3>"+result.recommendRingCategory[i][2]+"</h3>"+
			 					"<h4>"+result.recommendRingCategory[i][3]+"</h4>"+
			 					"<div class='con_row2_num'>"+
			 						"<span>发帖数：<span>"+result.recommendRingCategory[i][4]+"</span></span>"+
			 						"<span  style='margin-left:20px'>参与人数：<span>"+result.recommendRingCategory[i][5]+"</span></span>"+
			 					"</div>"+
			 				"</div>"+
			 				"<div id='con_row3_n"+i+"' zjid='"+result.recommendRingCategory[i][0]+"' class='con_row3'>"+
			 				"</div>"+				
			 			"</div>"+
			 			"<p class='gray_3'></p>"
					);
					if(result.recommendRingCategory[i][6] == "N"){
						$("#con_row3_n"+i).attr("onclick","focu('gz','"+result.recommendRingCategory[i][0]+"')").append(
			 				"<p class='a'>+</p>"+
			 				"<p class='b'>关注</p>"
						);
					}else if(result.recommendRingCategory[i][6] == "Y"){
						$("#con_row3_n"+i).attr("onclick","focu('qx','"+result.recommendRingCategory[i][0]+"')").append(
			 				"<p class='a color_blue'>&radic;</p>"+
			 				"<p class='b color_blue'>取消</p>"
						);				
					}
					
	
	 			
	 			
				}
			}

		}
	});
}
//订阅某一个医生圈
function recommendSomeCategory(zjid){
	var categoryId=zjid;
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"recommend",ringTypeId:categoryId},
		success:function(result){
			if(result.mes == "未登录"){
				ale('请先登录');
				setTimeout(function(){
					window.location.href="login.html";
				},2500);
			}
			$(event.target).parent().empty().append(
				"<p class='a color_blue'>&radic;</p>"+
				"<p class='b color_blue'>取消</p>"		
			).attr('onclick',function(){
				return $(this).attr('onclick').replace('gz','qx');
			});
		}
	});
}
//取消订阅的方法
function cancelSomeCategory(zjid){
	var categoryId=zjid;
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
    async:false, 
		data:{action:"cancle",ringTypeId:categoryId},
		success:function(result){
			if(result.mes == "未登录"){
				ale('请先登录');
				setTimeout(function(){
					window.location.href="login.html";
				},2500);
			}
			$(event.target).parent().empty().append(
				"<p class='a'>+</p><p class='b'>关注</p>"		
			).attr('onclick',function(){
				return $(this).attr('onclick').replace('qx','gz');
			});
		}
	});
}
//订阅或取消
function focu(type,zjid){
	if(type == 'gz'){
		recommendSomeCategory(zjid);
	}else if(type == 'qx'){
		cancelSomeCategory(zjid);
	}
}
//点击某一个医生圈，进入到医生圈内获取到医生圈内帖子的详细列表
function getSomeDoctorRingInfo(zjid,typename){
	var returnMark = encodeURIComponent($("#circle_type li").filter(function(){
		return $(this).hasClass('actli');
	}).text());
	window.location.href = "chatAll.html?"+zjid+"&&"+encodeURIComponent(typename)+"&&"+returnMark+"&&"+encodeURIComponent('全部');
}
$(function(){
	getAllRingCategory();
});

