var nullPic="";
var recordId = decodeURIComponent(window.location.search.substring(1));
$(function(){
	revise();
	var canvas_ = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串  
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
    var dataurl_ = canvas_.toDataURL("image/png"); 
    // 为安全 对URI进行编码  
    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
    nullPic =  encodeURIComponent(dataurl_);
});

//测试。。。。。。。。。。。。。。。。。。。。

	// 加载 图像文件(url路径)  
  function loadImage(obj){
		var src = $(obj).get(0).files[0];  	
		var mpImg = new MegaPixImage(src);
		var resCanvas1  = document.getElementById("myCanvas");
	  var _max = 230;
	  mpImg.render(resCanvas1, {
	      maxHeight: _max,
	      maxWidth:_max
	  });
	  
	  $('#myCanvas').show();
	  setTimeout(function(){
		  $('#productSmallFileElem2').css({
		  	'width':function(){
		  		return $('#myCanvas').css('width');
		  	},
		  	'height':function(){
		  		return $('#myCanvas').css('height');
		  	}
		  }); 
	  },2000);
	  $('#text_show').hide();
	  $('#imgup1').hide().parent().css('background','transparent');  	
  };  
//---------------------------------------------  
  // 上传图片，jQuery版  
  function sendImage(){


    var canvas = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串  
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
    var dataurl = canvas.toDataURL("image/png"); 
    // 为安全 对URI进行编码  
    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
    var imagedata =  encodeURIComponent(dataurl);
    
	var sleepHour=$.trim($("#sleepHour").val());
	var everyBreastfeeding=$.trim($("#everyBreastfeeding").val());
	var BreastfeedingTimes=$.trim($("#BreastfeedingTimes").val());
	var assistFoodsTimes=$.trim($("#assistFoodsTimes").val());
	var defecateTimes=$.trim($("#defecateTimes").val());
	var exerciseTimes=$.trim($("#exerciseTimes").val());
	//var nullPic="data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAASwAAACWCAYAAABkW7XSAAAEYklEQVR4Xu3UAQkAAAwCwdm%2F9HI83BLIOdw5AgQIRAQWySkmAQIEzmB5AgIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlAABg%2BUHCBDICBisTFWCEiBgsPwAAQIZAYOVqUpQAgQMlh8gQCAjYLAyVQlKgIDB8gMECGQEDFamKkEJEDBYfoAAgYyAwcpUJSgBAgbLDxAgkBEwWJmqBCVAwGD5AQIEMgIGK1OVoAQIGCw%2FQIBARsBgZaoSlACBB1YxAJfjJb2jAAAAAElFTkSuQmCC";
	
	if(sleepHour==""&&everyBreastfeeding==""&&BreastfeedingTimes==""&&assistFoodsTimes==""&&defecateTimes==""&&exerciseTimes==""){
		ale("请完善信息之后再上传");
	}else if(!(/^[0-9]+(.[0-9]{1,3})?$/.test(sleepHour))){
        ale("睡眠小时数不能乱写哦！");
    }else if(!(/^\d+$/.test(defecateTimes))){
		ale("排便次数不能乱写哦！");
	}else if(!(/^\d+$/.test(BreastfeedingTimes))){
		ale("母乳次数不能乱写哦！");
	}else if(!(/^[0-9]+(.[0-9]{1,3})?$/.test(everyBreastfeeding))){
        ale("母乳每次用量不能乱写哦！");
    }else if(!(/^[0-9]+(.[0-9]{1,3})?$/.test(exerciseTimes))){
        ale("运动次数不能乱写哦！");
    }else if(!(/^\d+$/.test(assistFoodsTimes))){
		ale("辅食次数不能乱写哦！");
	}else{
		if(imagedata==nullPic){
			imagedata="false";
		}
		 $.ajax({
				type:'post',
				url:host+'growthRecordManage.action',
				cache:false,
		        async:false, 
				data:{
					action:"add",
					sleepHour:sleepHour,
					everyBreastfeeding:everyBreastfeeding,
					BreastfeedingTimes:BreastfeedingTimes,
					assistFoodsTimes:assistFoodsTimes,
					defecateTimes:defecateTimes,
					exerciseTimes:exerciseTimes,
					imagedata:imagedata,
                    "growthRecord.id":recordId
				},
				success:function(result){
					if(result.mes=="操作成功"){
						return_before();
					}else if(result.mes=="未登录"){
						ale("请先登录哦！");
            setTimeout(function(){
              window.location.href="login.html";
            },2500);
						
					}else{
						ale("错误的操作");
					}
				},
			 error: function () {
				 layer();
			 }
			});
	}
		
//    $.ajax({
//    	url:host+'fileRecive.action',
//    	async:false,
//      data :{
//      	imagedata:imagedata
//      },  
//      type : "POST",  
// 			success:function(){
//
// 			},
// 			error:function(){
//
// 			}
//    });
  };

//修改
function revise(){
	if(recordId!=""){
		$.ajax({
			type:'post',
			url:host+'growthRecordManage.action',
			cache:false,
			async:false,
			data:{
                action:"getGrowthRecordById",
                "growthRecord.id":recordId
            },
			success:function(result){
				if(result.mes=='操作成功'){
					$(".color-basic").text("修改记录");
                    var growthRecord= result.growthRecord;
					if(growthRecord!=null){
						$("#sleepHour").val(growthRecord.sleepHour);
						$("#defecateTimes").val(growthRecord.defecateTimes);
						$("#BreastfeedingTimes").val(growthRecord.breastfeedingTimes);
						$("#everyBreastfeeding").val(growthRecord.everyBreastfeeding);
						$("#exerciseTimes").val(growthRecord.exerciseTimes);
						$("#assistFoodsTimes").val(growthRecord.assistFoodsTimes);
                        if(growthRecord.uploadImage!=""){
                            var canvas= document.getElementById("myCanvas");
                            var ctx= canvas.getContext("2d");
                            var img=new Image();
                            $('#myCanvas').css({'display':'block'});
                            img.src=hostBG+'images/growthrecord/'+growthRecord.uploadImage;
                            img.onload= function () {
                                ctx.drawImage(img,0,0,80,80);
                            }
                            $('#text_show').hide();
                        }
					}else{
						ale("暂时还没有成长记录");
					}
				}else if(result.mes=="未登录"){
					ale("请先登录之后再查看数据");
					setTimeout(function(){
						window.location.href="login.html";
					},2500);
				}
			},
			error: function () {
				layer();
			}
		});
	}
}