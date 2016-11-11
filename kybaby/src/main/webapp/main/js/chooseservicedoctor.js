var strUrlSearch="";

var productName="";
var doctorId="";
var coupon="";
var prodId="";
var date="";
var time="";
$(function(){
	/*var strUrl=window.location.search.substring(1).split("&");
	if(strUrl.length>1){
		getServiceDoctorList(strUrl[0],strUrl[1],strUrl[2]);
	} else{
		choseSomeProdcutDoctor(strUrl[0]);
	}*/
	
	
	
	var arrUrlBase=decodeURIComponent(window.location.search).substring(1).split("&");
	for(var i=0;i<arrUrlBase.length;i++){
		/*if(arrUrlBase[i].split("=")[0]=="prodName"){
			productName=arrUrlBase[i].split("=")[1];
		} else */if(arrUrlBase[i].split("=")[0]=="doctorId"){
			doctorId=arrUrlBase[i].split("=")[1];
		} /*else if(arrUrlBase[i].split("=")[0]=="couponValue"){
			coupon=arrUrlBase[i].split("=")[1];
		}*/ else if(arrUrlBase[i].split("=")[0]=="prodId"){
			prodId=arrUrlBase[i].split("=")[1];
		} else if(arrUrlBase[i].split("=")[0]=="time"){
			time=arrUrlBase[i].split("=")[1];
		}  else if(arrUrlBase[i].split("=")[0]=="date"){
			date=arrUrlBase[i].split("=")[1];
		}
	}
	if(date!=""){
		getServiceDoctorList(prodId,date,time.split("-")[0].split(":")[0]);
	}else{
		choseSomeProdcutDoctor(prodId);
	}
});

//优先选择预预约服务的医生,即在没有选择预约时间的情况下，直接选择预约服务的医生
function choseSomeProdcutDoctor(prodcutId){
	//var prodcutId=$.trim($("#choseProductId").val());
	$.ajax({
		type:'post',
		url:host+'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getSomeProdcutServiceDoctorList",prodcutId:prodcutId},
		success:function(result){
			if(result.mes=="操作成功"){
				$("#container").html("<p class='gray_s'></p>");
				for(var i=0;i<result.someProdcutServiceDoctorList.length;i++){
					var doctor =result.someProdcutServiceDoctorList[i];
					
					var docMajor="";
					if(result.doctorMajorList[i]!=null){
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							docMajor+=" "+result.doctorMajorList[i][j];
						}
					}
					if(doctor.doctorImage == null || doctor.doctorImage == '' || doctor.doctorImage == 'null'){
						doctor.doctorImage = 'lt_doctor.png';
					}
					$("#container").append(
							/*"<div class='doctor_box' id='doctor_"+doctor.id+"' onclick=\"window.location.href='doctorpage.html?"+doctor.id+"'\">"+*/
							"<div class='doctor_box' id='doctor_"+doctor.id+"'>"+
							"<div class='doctor_left'>"+
								"<div class='doctor_photo'><img src='"+hostBG+"images/doctorFaceIcon/"+doctor.doctorImage+"'/></div>"+
							"</div>"+
							"<div class='doctor_center'>"+
								"<p class='doctor_name'>"+doctor.doctorName+"</p>"+
								"<p>医师认证("+doctor.doctorTitle+")</p>"+
								"<p>专业方向："+docMajor.substring(1)+"</p>"+
								"<div class='mymark'>"+
									"<div class='mark_list'>"+
										"<div class='mark_list_left'>服务态度</div>"+
										"<div class='mark_list_right' id='service_"+doctor.id+"'></div>"+
									"</div>"+
									"<div class='mark_list'>"+
										"<div class='mark_list_left'>责任感</div>"+
										"<div class='mark_list_right' id='duty_"+doctor.id+"'></div>"+
									"</div>"+
									"<div class='mark_list'>"+
										"<div class='mark_list_left'>准时度</div>"+
										"<div class='mark_list_right' id='onTime_"+doctor.id+"'></div>"+
									"</div>"+
								"</div>"+
							"</div>"+
							"<div class='doctor_right'>"+
								"<p class='juli' id='juli_"+doctor.id+"' style='display:none'></p>"+
								"<p>出诊<span>"+doctor.visitedTimes+"</span>次</p>"+
							"</div>"+
							/*"<div class='choose_state' onclick='chooseDoctor("+doctor.id+",this)'></div>"+*/
						"</div>"
									
					);
					/*ale("医生的头像是："+doctor.doctorImage);
					ale("医生的职称是："+doctor.doctorTitle);
					ale("医生的出诊次数："+doctor.visitedTimes);
					ale("医生的服务态度星级："+doctor.seiviceStarLevel);
					ale("医生的责任星级："+doctor.dutyStarLevel);
					ale("医生的准时星级："+doctor.onTimeStarLevel);*/
					if(doctor.id==doctorId){
						$("#doctor_"+doctor.id).append("<div class='choose_state select' onclick='chooseDoctor("+doctor.id+",this)'></div>");
					} else {
						$("#doctor_"+doctor.id).append("<div class='choose_state' onclick='chooseDoctor("+doctor.id+",this)'></div>");
					}
					
							if(doctor.seiviceStarLevel == 0){
								for(var o=0;o < 5;o++){
									$("#service_"+doctor.id).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor.seiviceStarLevel;o++){
									$("#service_"+doctor.id).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor.seiviceStarLevel;o++){
									$("#service_"+doctor.id).append("<img src='images/icon_starkong.png'>");
								}							
							}

							if(doctor.dutyStarLevel == 0){
								for(var o=0;o < 5;o++){
									$("#duty_"+doctor.id).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor.dutyStarLevel;o++){
									$("#duty_"+doctor.id).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor.dutyStarLevel;o++){
									$("#duty_"+doctor.id).append("<img src='images/icon_starkong.png'>");
								}							
							}							

							if(doctor.onTimeStarLevel == 0){
								for(var o=0;o < 5;o++){
									$("#onTime_"+doctor.id).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor.onTimeStarLevel;o++){
									$("#onTime_"+doctor.id).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor.onTimeStarLevel;o++){
									$("#onTime_"+doctor.id).append("<img src='images/icon_starkong.png'>");
								}							
							}						
					

					
					
					
					if(result.doctorDistanceList[i]!=undefined){
						//ale("距离是"+result.doctorDistanceList[i]);
						$("#juli_"+doctor.id).show();
						$("#juli_"+doctor.id).append("<span>"+result.doctorDistanceList[i]+"</span>公里");
					}
				}
			}else if(result.mes=="无医生"){
				ale("还没有医生提供该产品服务");
        setTimeout(function(){
          window.history.back();
        },2500);
				
			}else if(result.mes=="未登录"){
				ale("您还没有登录");
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


//优先选择预约服务的时间,即在没有选择预约服务的医生的情况下，选择了预约服务的时间，进行匹配能够服务的医生列表
function getServiceDoctorList(prodcutId,serviceDate,serviceTime){
	//var prodcutId=$.trim($("#prodcutId").val());
	//var serviceDate=$.trim($("#serviceDate").val());
	//var serviceTime=$.trim($("#serviceTime").val());
	$.ajax({
		type:'post',
		url:host+'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getSomeDateDoctList",prodcutId:prodcutId,serviceDate:serviceDate,serviceTime:serviceTime},
		success:function(result){
			if(result.mes=="操作成功"&&result.serviceDoctorList!=""){
				/*for(var i=0;i<result.serviceDoctorList.length;i++){
					var doctor =result.serviceDoctorList[i];
					ale("医生的头像是："+doctor.doctorImage);
					ale("医生的职称是："+doctor.doctorTitle);
					ale("医生的出诊次数："+doctor.visitedTimes);
					ale("医生的服务态度星级："+doctor.seiviceStarLevel);
					ale("医生的责任星级："+doctor.dutyStarLevel);
					ale("医生的准时星级："+doctor.onTimeStarLevel);
					if(result.doctorMajorList[i]!=null){
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							ale("第"+(j+1)+"个专业方向是："+result.doctorMajorList[i][j]);
						}
					}
					if(result.doctorDistanceList[i]!=undefined){
						ale("距离是"+result.doctorDistanceList[i]);
					}
				}*/
				
				$("#container").html("<p class='gray_2'></p>");
				for(var i=0;i<result.serviceDoctorList.length;i++){
					var doctor =result.serviceDoctorList[i];
					
					var docMajor="";
					if(result.doctorMajorList[i]!=null){
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							docMajor+=" "+result.doctorMajorList[i][j];
						}
					}
					$("#container").append(
							/*"<div class='doctor_box' id='doctor_"+doctor.id+"' onclick=\"window.location.href='doctorpage.html?"+doctor.id+"'\">"+*/
							"<div class='doctor_box' id='doctor_"+doctor.id+"'>"+
							"<div class='doctor_left'>"+
								"<div class='doctor_photo'><img src='"+hostBG+"images/doctorFaceIcon/"+doctor.doctorImage+"'/></div>"+
							"</div>"+
							"<div class='doctor_center'>"+
								"<p class='doctor_name'>"+doctor.doctorName+"</p>"+
								"<p>医师认证("+doctor.doctorTitle+")</p>"+
								"<p>专业方向："+docMajor.substring(1)+"</p>"+
								"<div class='mymark'>"+
									"<div class='mark_list'>"+
										"<div class='mark_list_left'>服务态度</div>"+
										"<div class='mark_list_right' id='service_"+doctor.id+"'></div>"+
									"</div>"+
									"<div class='mark_list'>"+
										"<div class='mark_list_left'>责任感</div>"+
										"<div class='mark_list_right' id='duty_"+doctor.id+"'></div>"+
									"</div>"+
									"<div class='mark_list'>"+
										"<div class='mark_list_left'>准时度</div>"+
										"<div class='mark_list_right' id='onTime_"+doctor.id+"'></div>"+
									"</div>"+
								"</div>"+
							"</div>"+
							"<div class='doctor_right'>"+
								"<p class='juli' id='juli_"+doctor.id+"' style='display:none'></p>"+
								"<p>出诊<span>"+doctor.visitedTimes+"</span>次</p>"+
							"</div>"+
							/*"<div class='choose_state' onclick='chooseDoctor("+doctor.id+",this)'></div>"+*/
						"</div>"
									
					);
					if(doctor.id==doctorId){
						$("#doctor_"+doctor.id).append("<div class='choose_state select' onclick='chooseDoctor("+doctor.id+",this)'></div>");
					} else {
						$("#doctor_"+doctor.id).append("<div class='choose_state' onclick='chooseDoctor("+doctor.id+",this)'></div>");
					}
					for(var o=0;o<doctor.seiviceStarLevel;o++){
						$("#service_"+doctor.id).append("<img src='images/icon_star.png'>");
					}
					for(var p=0;p<doctor.dutyStarLevel;p++){
						$("#duty_"+doctor.id).append("<img src='images/icon_star.png'>");
					}
					for(var q=0;q<doctor.onTimeStarLevel;q++){
						$("#onTime_"+doctor.id).append("<img src='images/icon_star.png'>");
					}
					
					
					
					if(result.doctorDistanceList[i]!=undefined){
						//ale("距离是"+result.doctorDistanceList[i]);
						$("#juli_"+doctor.id).show();
						$("#juli_"+doctor.id).append("<span>"+result.doctorDistanceList[i]+"</span>公里");
					}
				}
			}else if(result.mes=="无医生"||result.serviceDoctorList==""){
				ale("还没有医生能够在该时间段提供服务");
				//ale("yes or no");
				window.history.back();
				//$('.header-left p').trigger('click');
			}else if(result.mes=="未登录"){
				ale("您还没有登录");
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


function chooseDoctor(doctorId,obj){
	var urlSearch=window.location.search;
	if(urlSearch.indexOf("doctorId=")>0){
		if(urlSearch.indexOf("&",urlSearch.indexOf("doctorId="))>0){
			urlSearch=urlSearch.substring(0,urlSearch.indexOf("doctorId=")-1)+urlSearch.substring(urlSearch.indexOf("&",urlSearch.indexOf("doctorId=")+9))+"&doctorId="+doctorId;
		} else{
			urlSearch=urlSearch.substring(0,urlSearch.indexOf("doctorId="))+"doctorId="+doctorId;
		}
		
	} else{
		urlSearch+="&doctorId="+doctorId;
	}
	window.location.replace("orderconfirm.html"+urlSearch);
}