/**
 * Created by vinny on 2015/10/6.
 */
var a = 2;
var doctorId;
var doctorName;
var isBefore;
var pageName;
var fdServicePackageId=null;
$(function(){
	$("#small_add").click(function(){
		if(a % 2 == 0){
			$(".sym_tag_a").css("overflow","visible");
			a++;
		}else if(a % 2 == 1){
			$(".sym_tag_a").css("overflow","hidden");
			a++;
		}
	});
	

});

$(function(){
	var arrBaseUrlSearch=decodeURIComponent(window.location.search).substring(1).split("&");
    if(arrBaseUrlSearch.length>3){
        if ("fdServicePackageId"== arrBaseUrlSearch[4].split('=')[0]) {
            fdServicePackageId = arrBaseUrlSearch[arrBaseUrlSearch.length - 1].split('=')[1];
        }
    }
    for(var i=0;i<arrBaseUrlSearch.length;i++){
		if(arrBaseUrlSearch[i].split("=")[0]=="doctorId"){
			doctorId=arrBaseUrlSearch[i].split("=")[1];
		} else if(arrBaseUrlSearch[i].split("=")[0]=="doctorName"){
			doctorName=arrBaseUrlSearch[i].split("=")[1];
		}
		
		// add by fkn
		else if(arrBaseUrlSearch[i].split("=")[0]=="Page"){
			pageName=arrBaseUrlSearch[i].split("=")[1];
		}
	}
	$("#doctorName").text(doctorName);
	
	//add by fkn
	if(pageName=="quick"){
		isBefore="Y";
	}
	if(pageName=="mydoctor"){
		isBefore="N";
	}
	fillConsultaion();
	doctor_header();
	
});

function fillConsultaion(){
	$.ajax({
		type:'post',
		url:host+'getUserConsultDoctorInfo.action',
		cache:false,
	    async:false, 
		data:{action:"getAllSymptomTagList"},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.allSymptomTagList!=null){
					$("#sym_tag_a").html("");
					for(var i =0;i<result.allSymptomTagList.length;i++){
						//ale("第"+(i+1)+"个症状标签的名称是："+resut.allSymptomTagList[i].symptomName);
						$("#sym_tag_a").append("<p class='button_11' idValue='"+result.allSymptomTagList[i].id+"'>"+result.allSymptomTagList[i].symptomName+"</p>");
					}
					
					//选择症状标签
					$(".button_11").click(function(){
						if($(this).hasClass('select')){
							$(this).removeClass('select');
						}else{
							$(this).addClass('select');
						}
						
					});
				}else{
					//ale("还没有可以咨询的标签！");
				}
			}
		},
		error: function () {
			layer();
		}
	});
}
function doctor_header(){
	$.ajax({
		type: 'post',
		url: clinicHost + 'clinicBooking.action',
		cache: false,
		async: false,
		data: {
			action: "getDoctorMorePracticeList",
			"doctorInfo.id": doctorId
		},
		success: function (result) {
			if (result.mes == '请登录') {
				ale('请登录', '24px');
				window.location.href = "login.html";
			}
			else if (result.mes == "成功") {
				var doctor = result.doctorInfo;
				$("#doctor_information").append('<div id="doctor_head"><img src="'+hostBG + 'images/doctorFaceIcon/' + doctor.doctorImage+'" alt=""/></div><p id="doctor_name">'+doctor.doctorName+'</p>');

			}
		},
		error: function () {
			//alert('you are false');
			layer();
		}
	});
}




function addNewConsult(){	
		//添加新的咨询
	
	 //症状标签Id
	 var tagLists=$(".button_11").filter(function(){
		 return $(this).hasClass('select');
	 });
	 var selectedTags="";
	 for(var i=0;i<tagLists.length;i++){
		 selectedTags+="::"+tagLists.eq(i).attr('idValue');
	 }
	 $("#tagIds").val(selectedTags.substring(2));
	 
	 $("#doctorId").val(doctorId);
	 
	 if(selectedTags==""){
		 ale("请选择症状标签");
	 } else 
	 //if(content==""){
	 if($("#symDescription").val()==""){
		 ale("请输入咨询的内容之后再咨询");
	 }else{
         $('.login_button').removeAttr('onclick');
         //$('#fillConsultationForm').attr('action', host+"userConsultDoctorManage.action?action=addNewTags&isBefore="+isBefore);
		 sendImage();
		 //$('#fillConsultationForm').submit();不再使用表单提交，改为BASE64提交
	 }
}
//测试。。。。。。。。。。。。。。。。。。。。
  // 渲染  

	// 加载 图像文件(url路径)  
  function loadImage(obj){
		var src = $(obj).get(0).files[0];  	
		var mpImg = new MegaPixImage(src);
		var resCanvas1  = document.getElementById("myCanvas");
	  var _max = 500;
	  mpImg.render(resCanvas1, {
	      maxWidth:_max
	  });
	  

	  setTimeout(function(){
		  $('#productSmallFileElem').css({
		  	'width':function(){
		  		return $('#myCanvas').css('width');
		  	},
		  	'height':function(){
		  		return $('#myCanvas').css('height');
		  	}
		  }); 
	  },2000);
	  $('#myCanvas').show().parent().css('background','transparent');  	
  };  
//---------------------------------------------  
  // 上传图片，jQuery版  
  function sendImage(){
	var not_base64 = "data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAAJYAAACWCAYAAAA8AXHiAAACoUlEQVR4Xu3SMQ0AAAzDsJU%2F6aHI5wLoEXln";

    var canvas = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串  
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
    var dataurl = canvas.toDataURL("image/png"); 
    // 为安全 对URI进行编码  
    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
    var imagedata =  encodeURIComponent(dataurl);
    if(imagedata.indexOf(not_base64)>=0){
    	imagedata = 'false';
    }
    var userType=1;
    if(fdServicePackageId==null){
        fdServicePackageId='';
        userType=0;
    }
    $.ajax({
		type:'post',
		url:host+'userConsultDoctorManage.action',
		cache:false,
        async:false, 
		data:{action:"addNewTags",tagIds:$("#tagIds").val(),description:$("#symDescription").val(),doctorId:$("#doctorId").val(),imagedata:imagedata,isBefore:isBefore,userType:userType,fdPackageId:fdServicePackageId},
		success:function(result){
			if(result.mes=="操作成功"){
                var hf='';
                if(fdServicePackageId==''){
                    hf="consultation.html?doctorId="+$("#doctorId").val()+"&LogId="+result.logId;
                }else{
                    hf="consultation.html?doctorId="+$("#doctorId").val()+"&LogId="+result.logId+"&userType=1&fdServicePackageId="+fdServicePackageId;
                }
				window.location.href=hf;
			}else if(result.mes=="参数错误"){
				ale("参数错误");
			}else if(result.mes=="未登录") {
				window.location.href="login.html";
			}else{
				ale("错误的操作");
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			sendImage();
			layer();
		}
		});
    
    
    
  };


