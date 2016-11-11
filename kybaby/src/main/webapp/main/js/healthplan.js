$(function(){
getPlanList();
});


function getPlanList(){
	$.ajax({
		type:'post',
		url:host+'getHealthPlanRemainInfo.action',
		data:{action:"getHealthPlanFoList"},
		success:function(result){
			if(result.healthPlanFoList == null){
				ale("暂无健康指导");
				return false;
			}
			$("#box").empty();
			$.each(result.healthPlanFoList,function(key,val){
				$("#box").append(
					"<li>"+
						"<p class='list-date'>创建日期：<span>"+val.cerateDate+"</span></p>"+
						"<p class='list-age'>宝宝月龄：<span>"+val.monthAge+"周</span></p>"+
						"<p class='list-num'>订单编号：<span>"+val.orderNumber+"</span></p>"+
						"<p class='list-more' onclick=\"getPlanDetail('"+val.orderNumber+"')\" data-num='"+val.orderNumber+"'>+more</p>"+
					"</li>"+
					"<p class='gray_2'></p>"
				);
			});
		}
	});
}
function getPlanDetail(num){
	$.ajax({
		type:'post',
		url:host+'getHealthPlanRemainInfo.action',
		data:{action:"getHealthPathList",orderNum:num},
		success:function(result){
			if(result.mes=="操作成功"){
				$("#box-detail").empty();
				$.each(result.healthPathList,function(key,val){
					$("#box-detail").append(
						"<li>"+
							"<h5><span>"+val.healthPathName+"</span></h5>"+
							"<section>"+val.comments+"</section>"+
						"</li>"+
						"<hr>"					
					);

				});
			}else if(result.mes=="未登录"){
				ale("您还没有登录");
			}
				boxshow();
		},
		error:function(){
			layer();
		}
	});
}
function boxshow(){
	$("#rightbox").show().animate({
		"left":0,
	});
}
function boxclose(){
	$("#rightbox").animate({
		"left":(function(){
			return -$(window).width()*0.75;
		})(),
	},function(){
		$(this).hide();
	});
}













