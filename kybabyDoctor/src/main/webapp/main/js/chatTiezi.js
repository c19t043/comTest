var zjid = location.search.substring(1).split("&&")[0];
function loadImage(obj){
	var objDom = $(obj).parent().get(0);//获取父级dom对象
	var src = $(obj).get(0).files[0];//获取文件	
	var mpImg = new MegaPixImage(src);//预览实例化
	var mpImg_up = new MegaPixImage(src);//上传实例化
	var resCanvas1  = objDom.querySelector('.loadimg_showcanvas');//预览
	var resCanvas2  = objDom.querySelector('.loadimg_upcanvas');//上传
	
  mpImg.render(resCanvas1, {
      maxHeight: 90,
  });
  mpImg_up.render(resCanvas2, {
      maxWidth: 250,
      maxHeight: 250
  },function(){
	  $(objDom).css({
	  	'width':function(){
	  		return $(resCanvas1).css('width');
	  	},
	  	'height':function(){
	  		return $(resCanvas1).css('height');
	  	}
	  }); 
  	var inputObj = objDom.querySelector('.loadimg_input');
	  $(inputObj).css({
	  	'width':function(){
	  		return $(resCanvas1).css('width');
	  	},
	  	'height':function(){
	  		return $(resCanvas1).css('height');
	  	}
	  });
	  $(objDom).css('background','transparent');
	  $(objDom.querySelector('.loadimg_base64')).val(encodeURIComponent(resCanvas2.toDataURL('imags/png')));
	  $('#circleContent').val($('#circleContent').val()+"[图片]");
	  cloneImg();
  }); 
}; 
function cloneImg(){
	$('#imageUpload').append(
		"<div class='loadimg_div'>"+
			"<input class='loadimg_input' type='file' accept='image/*' onchange='loadImage(this)'>"+
			"<canvas class='loadimg_showcanvas' width='90' height='90'></canvas>"+
			"<canvas class='loadimg_upcanvas' width='90' height='90' width='250' height='250' style='display:none'></canvas>"+
			"<input class='loadimg_base64' style='display:none' />"+
		"</div>"		
	);
}
function sendCon(){
	
	var tit = $('#contitle').val().trim();
	var con = $('#circleContent').val();
	if(tit == ''){
		ale('请输入标题');
		return false;
	}else if(con == ''){
		ale('请输入内容');
		return false;
	}
	var base64 = '';
	for(var i = 0;i < $('.loadimg_base64').length;i++){
		base64 = base64+"::"+$('.loadimg_base64').eq(i).val();
	}
	base64 = base64.substring(2);

	var doctorRingLabelStr = '';
	$('.belongTag').each(function(){
		doctorRingLabelStr = doctorRingLabelStr+"::"+$(this).attr('zjid');
	});
	doctorRingLabelStr = doctorRingLabelStr.substring(2);
	if(doctorRingLabelStr == ''){
		ale('请选择标签');
		return false;
	}

	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"addNewPostInfo",
			titleContent:tit,
			textContent:con,
			imgContent:base64,
			//imgContent:$('.loadimg_base64').eq(0).val(),
			categoryId:zjid,
			doctorRingLabelStr:doctorRingLabelStr
		},
		success:function(result){
			if(result.mes == "未登录"){
				ale('请先登录');
				setTimeout(function(){
					window.location.href="login.html";
				},2500);
			}
			if(result.mes == '操作成功'){
				ale('发布成功');
				setTimeout(function(){
					$('.header-left p').eq(0).trigger('click');
				},2500);
				
			}
			if(result.mes=='无权限'){
				ale('您暂无发表帖子的权限！');
				setTimeout(function(){
					$(".header-left").eq(0).find('p').trigger("click");
				},2500);
			}
		}
	});	
}
function tagShow(){
	$('#showTag').animate({
		'top':'-22'
	},function(){
		$(this).animate({
		'top':'-32'		
		},100);
	});
}
function tagHide(){
	$('#showTag').animate({
		'top':'-22'
	},100,function(){
		$(this).animate({
		'top':'-280'		
		});
	});
	var tag = $('.tagName').filter(function(){
		return $(this).hasClass('blue');
	});
	$('.tagList').empty();
	$.each(tag,function(){
		$('.tagList').append(
			"<div class='belongTag' zjid='"+$(this).attr('zjid')+"'>"+$(this).text()+"</div>"
		);
		
	});



}
function tagSelect(obj,tagName){
	if($(obj).hasClass('blue')){
		$(obj).removeClass('blue');
	}else{
		$(obj).addClass('blue');
	}
}
//获取所有医生圈标签的ID和名称
function getAllDoctorRingLabels(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
    async:true, 
		data:{action:"allDoctorRingLabelInfo",ringCategory:"Y"},
		success:function(result){
			if(result.mes != '操作成功' || result.someCategoryRingLabelObject == null){
				return false;
			}
			$('.noteTag').empty();
			$.each(result.someCategoryRingLabelObject,function(i,content){
				if(content[2] == 'Y'){
					$('.noteTag').append(
						"<span onclick=\"tagSelect(this,'"+content[1]+"')\" class='tagName' zjid='"+content[0]+"'>"+content[1]+"</span>"
					);				
				}
			});
		}
	});
}
$(function(){
	getAllDoctorRingLabels();
});