var url_str = window.location.search.substring(1);
$(function(){
	$('.star_1').click(function(){
		var num = $(this).attr('mark');
		$('#star_1_plus img').filter(function(){
			return $(this).attr('mark') <= num;
		}).attr('src','images/icon_star.png');
		$('#star_1_plus img').filter(function(){
			return $(this).attr('mark') > num;
		}).attr('src','images/icon_starkong.png');
	});

	$('.star_2').click(function(){
		var num = $(this).attr('mark');
		$('#star_2_plus img').filter(function(){
			return $(this).attr('mark') <= num;
		}).attr('src','images/icon_star.png');
		$('#star_2_plus img').filter(function(){
			return $(this).attr('mark') > num;
		}).attr('src','images/icon_starkong.png');
	});

	$('.star_3').click(function(){
		var num = $(this).attr('mark');
		$('#star_3_plus img').filter(function(){
			return $(this).attr('mark') <= num;
		}).attr('src','images/icon_star.png');
		$('#star_3_plus img').filter(function(){
			return $(this).attr('mark') > num;
		}).attr('src','images/icon_starkong.png');
	});
	$.ajax({
		type:'post',
		url:host+'orderManage.action',
		cache:false,
		async:false, 
		data:{action:"assess",active:"tag",orderId:url_str},
		success:function(result){
			for(var i=3;i < result.assessmentTagList.length;i++){
				$('#tag').append(
				"<li><span zjid='"+result.assessmentTagList[i].id+"' onclick='addClass(this)'>"+result.assessmentTagList[i].tagName+"</span></li>"
				);
				
			}
		},
		error: function () {
			layer();
		}
	});
});

function submit(){
	var a = $('#star_1_plus img').filter(function(){
		return $(this).attr('src') == 'images/icon_star.png';
	});
	var b = $('#star_2_plus img').filter(function(){
		return $(this).attr('src') == 'images/icon_star.png';
	});
	var c = $('#star_3_plus img').filter(function(){
		return $(this).attr('src') == 'images/icon_star.png';
	});
	var str_star = a.length + "&" + b.length + "&" + c.length;
	var str_tag = '';
	
	for(var i=0;i < $('#tag span').length;i++){
		var a =	$('#tag span').eq(i).filter(function(){
			return $(this).hasClass('on');
		});
		if(a.get(0) != undefined){
			str_tag = str_tag + "@" + a.attr('zjid');
		}
	}
	var all_str = str_star+"::"+str_tag.substring(1);
	$.ajax({
		type:'post',
		url:host+'orderManage.action',
		cache:false,
		async:false, 
		data:{action:"assess",active:"submite",orderId:url_str,someIds:all_str},
		success:function(result){
			if(result.mes == '成功'){
				ale('提交成功');
        setTimeout(function(){
          window.location.href='myAppointment.html';
        },2500);
				
			}else{
				ale(result.mes);
			}
		},
		error: function () {
			layer();
		}
	});
}
function addClass(obj){
	if($(obj).hasClass('on')){
		$(obj).removeClass('on');
	}else{
		$(obj).addClass('on');
	}
}