	$(function(){
	    getGuidePage();
	    
		var h = $(window).height();
		var w = $(window).width();
		$(".royalSlider.heroSlider, .royalSlider.heroSlider .rsOverflow").css({
			"width" : w,
			"height" : h,
		});
		$(".guide_box img").css({
//			"width" : w,
			"height" : h,
		});
	});



jQuery(document).ready(function($) {
	//window.onload=function(){
		$('#full-width-slider').royalSlider({
			arrowsNav: true,
			loop: false,
			//loopRewind: true,
			fadeinLoadedSlide: true,
			slidesSpacing: 0,
			keyboardNavEnabled: true,
			controlsInside: true,
			imageScaleMode: 'fill',
			arrowsNavAutoHide: false,
			autoScaleSlider: true,
			autoScaleSliderWidth: 800,
			autoScaleSliderHeight: 400,
			controlNavigation: 'bullets',
			thumbsFitInViewport: false,
			navigateByClick: false, //Navigates forward by clicking on slide.
			startSlideId: 0,
			autoPlay: {
				enabled: false, //autoPlay or not
				stopAtAction: false, //Stop autoPlay at first user action
				pauseOnHover: false, //Pause autoPlay on hover
				delay: 3000 //Delay between items in ms.
			},
			//transitionType:'move',
			globalCaption: true,
			deeplinking: {
				enabled: true,
				change: false
			},
			imgWidth: 800,
			imgHeight: 400,
		});
		
 		var slider = $(".royalSlider").data('royalSlider');
	
	    slider.ev.on('rsAfterSlideChange', function(event) {        
          if(slider.currSlideId==(slider.numSlides-1)){
          slider.destroy();
          window.location.href='main.html';
              //$("#btnStart").show();
          } else{
          	  //$("#btnStart").hide();
          }
          
        }); 
	});
	//}

//获取引导页图片
function getGuidePage(){
	$.ajax({
		type:'post',
		url:'getGuideInfo.action',
		data:{action:"getGuidePage"},
		async:false,
		success:function(result){
			if(result.mes=="无引导"){
				//alert("无引导页");
				window.location.href="main.html";
			}else if(result.mes=="操作成功"){
				$("#full-width-slider").html("");
				for(var i=0;i<result.guidePictureList.length;i++){
					//alert("第"+(i+1)+"个引导页的名称是："+result.guidePictureList[i].pageName);
					$("#full-width-slider").append("<div class='rsContent'><img class='rsImg' src='"+hostBG+"images/guide/"+result.guidePictureList[i].pageName+"' /></div>");
				}
				$("#full-width-slider").append("<div class='rsContent'></div>");
			}
		}
	});
}


