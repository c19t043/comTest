$(function(){
	getConsultationDoctor("welcome");
});


function getProductByProperty(obj,obj2){
	//所有标签置灰色
	$("#choose_bg span").attr('class','grey');
	//选中的标签置蓝色
	$(obj).attr('class','blue');
	
	getConsultationDoctor(obj2);

/*	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
		async:false,
		data:{action:"getConsultationDoctorList",sortMethod:obj2},
		success:function(result){
			if(result.mes=="操作成功"){
				$("#container_son").empty();
				for(var i=0;i<result.consultationDoctorList.length;i++){
					var doctor=result.consultationDoctorList[i];
					if(doctor[5] == "Y"){
						$("#container_son").append("<div class='doctor_box'>"+
							"<div class='doctor_left'>"+
							"<div class='doctor_photo'></div>"+
							"</div>"+
							"<div class='doctor_center'>"+
							"<p class='doctor_name'>"+doctor[7]+"</p>"+
							"<p>医师认证("+doctor[2]+"）</p>"+
							"<p id='major_fangxiang'>专业方向：</p>"+
							"<div class='mymark'>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>服务态度</div>"+
							"<div class='mark_list_right' id='fuwutaidu_"+i+"'></div>"+
							"</div>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>责任感</div>"+
							"<div class='mark_list_right' id='zerengan_"+i+"'></div>"+
							"</div>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>准时度</div>"+
							"<div class='mark_list_right' id='zhunshidu_"+i+"'></div>"+
							"</div>"+
							"</div>"+
							"</div>"+
							"<div class='consultation_icon on'></div>"+
							"</div>");
						for(var k=0;k<doctor[8];k++){
							$("#fuwutaidu_"+i+"").append("<img src='images/icon_star.png'>");
						}
						for(var m=0;m<doctor[9];m++){
							$("#zerengan_"+i+"").append("<img src='images/icon_star.png'>");
						}
						for(var n=0;n<doctor[10];n++){
							$("#zhunshidu_"+i+"").append("<img src='images/icon_star.png'>");
						}
						ale("第"+(i+1)+"个医生头像是："+doctor[6]);
						ale("第"+(i+1)+"个医生姓名是："+doctor[7]);
						ale("第"+(i+1)+"个医生职称是："+doctor[2]);
						ale("第"+(i+1)+"个医生服务态度星级是："+doctor[8]);
						ale("第"+(i+1)+"个医生责任感星级是："+doctor[9]);
						ale("第"+(i+1)+"个医生准时星级是："+doctor[10]);
						if(result.doctorMajorList[i]!=null){
							for(var j=0;j<result.doctorMajorList[i].length;j++){
								ale("第"+(i+1)+"个医生的第"+(j+1)+"个专业方向是:"+result.doctorMajorList[i][j]);
								if(j == result.doctorMajorList[i].length-1){
									$("#major_fangxiang").append(result.doctorMajorList[i][j]);
								}else{
									$("#major_fangxiang").append(result.doctorMajorList[i][j]+",");
								}
							}
						}
					}else{
						$("#container").append("<div class='doctor_box'>"+
							"<div class='doctor_left'>"+
							"<div class='doctor_photo'></div>"+
							"</div>"+
							"<div class='doctor_center'>"+
							"<p class='doctor_name'>"+doctor[7]+"</p>"+
							"<p>医师认证("+doctor[2]+"）</p>"+
							"<p id='major_fangxiang'>专业方向：</p>"+
							"<div class='mymark'>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>服务态度</div>"+
							"<div class='mark_list_right' id='fuwutaidu_"+i+"'></div>"+
							"</div>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>责任感</div>"+
							"<div class='mark_list_right' id='zerengan_"+i+"'></div>"+
							"</div>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>准时度</div>"+
							"<div class='mark_list_right' id='zhunshidu_"+i+"'></div>"+
							"</div>"+
							"</div>"+
							"</div>"+
							"<div class='consultation_icon'></div>"+
							"</div>");
						for(var k=0;k<doctor[8];k++){
							$("#fuwutaidu_"+i+"").append("<img src='images/icon_star.png'>");
						}
						for(var m=0;m<doctor[9];m++){
							$("#zerengan_"+i+"").append("<img src='images/icon_star.png'>");
						}
						for(var n=0;n<doctor[10];n++){
							$("#zhunshidu_"+i+"").append("<img src='images/icon_star.png'>");
						}
						ale("第"+(i+1)+"个医生头像是："+doctor[6]);
						ale("第"+(i+1)+"个医生姓名是："+doctor[7]);
						ale("第"+(i+1)+"个医生职称是："+doctor[2]);
						ale("第"+(i+1)+"个医生服务态度星级是："+doctor[8]);
						ale("第"+(i+1)+"个医生责任感星级是："+doctor[9]);
						ale("第"+(i+1)+"个医生准时星级是："+doctor[10]);
						if(result.doctorMajorList[i]!=null){
							for(var j=0;j<result.doctorMajorList[i].length;j++){
								ale("第"+(i+1)+"个医生的第"+(j+1)+"个专业方向是:"+result.doctorMajorList[i][j]);
								if(j == result.doctorMajorList[i].length-1){
									$("#major_fangxiang").append(result.doctorMajorList[i][j]);
								}else{
									$("#major_fangxiang").append(result.doctorMajorList[i][j]+",");
								}
							}
						}

					}

				}
			}else if(result.mes=="无咨询医生"){
				ale("没有医生能够提供咨询服务");
			}
		}
	});*/


	//$("div[mark]").hide();
	//$("div[mark="+$(obj).text()+"]").show();
}


//点击快捷咨询，进入选择咨询医生列表
function getConsultationDoctor(sortMethod){
	//var sortMethod=$.trim($("#sortMethod").val());
	$.ajax({
		type:'post',
		url:host+'getDoctorInfo.action',
		cache:false,
        async:false,
		data:{action:"getConsultationDoctorList",sortMethod:sortMethod},
		success:function(result){
			if(result.mes=="操作成功"){
				$("#container_son").html("");
				for(var i=0;i<result.consultationDoctorList.length;i++){					
					var doctor=result.consultationDoctorList[i];
					var doctorMajor="";
					if(result.doctorMajorList[i]!=null){
						for(var j=0;j<result.doctorMajorList[i].length;j++){
							//ale("第"+(i+1)+"个医生的第"+(j+1)+"个专业方向是:"+result.doctorMajorList[i][j]);
							if(j == result.doctorMajorList[i].length-1){
								doctorMajor+=result.doctorMajorList[i][j];
							}else{
								doctorMajor+=result.doctorMajorList[i][j]+" ";
							}
						}
					}
					if(doctor[6] == null || doctor[6] == 'null' || doctor[6] == ''){
						doctor[6] = 'lt_doctor.png';
					}
					if(doctor[5] == "Y"){
						$("#container_son").append("<div class='doctor_box'>"+
						"<div class='doctor_left'>"+
						"<div class='doctor_photo'><img src='"+hostBG+"images/doctorFaceIcon/"+doctor[6]+"'/></div>"+
						"</div>"+
						"<div class='doctor_center'>"+
						"<p style='color: #444' class='doctor_name'>"+doctor[7]+"</p>"+
						"<p>"+doctor[2]+"</p>"+
						"<p style='color:#7c7c7c;'>专业方向："+doctorMajor+"</p>"+
						"<div class='mymark'>"+
						"<div class='mark_list'>"+
						"<div class='mark_list_left'>服务态度</div>"+
						"<div class='mark_list_right' id='fuwutaidu_"+i+"'></div>"+
						"</div>"+
						"<div class='mark_list'>"+
						"<div class='mark_list_left'>责任感</div>"+
						"<div class='mark_list_right' id='zerengan_"+i+"'></div>"+
						"</div>"+
						"<div class='mark_list'>"+
						"<div class='mark_list_left'>准时度</div>"+
						"<div class='mark_list_right' id='zhunshidu_"+i+"'></div>"+
						"</div>"+
						"</div>"+
						"</div>"+
						"<div class='consultation_icon on' onclick=\"consulationSomeDoctor("+doctor[0]+",'"+doctor[7]+"')\">立即咨询</div>"+
						"</div>");
							if(doctor[8] == 0){
								for(var o=0;o < 5;o++){
									$("#fuwutaidu_"+i).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[8];o++){
									$("#fuwutaidu_"+i).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[8];o++){
									$("#fuwutaidu_"+i).append("<img src='images/icon_starkong.png'>");
								}							
							}

							if(doctor[9] == 0){
								for(var o=0;o < 5;o++){
									$("#zerengan_"+i).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[9];o++){
									$("#zerengan_"+i).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[9];o++){
									$("#zerengan_"+i).append("<img src='images/icon_starkong.png'>");
								}							
							}							

							if(doctor[10] == 0){
								for(var o=0;o < 5;o++){
									$("#zhunshidu_"+i).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[10];o++){
									$("#zhunshidu_"+i).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[10];o++){
									$("#zhunshidu_"+i).append("<img src='images/icon_starkong.png'>");
								}							
							}							
						
						

						
					}else{
						$("#container_son").append("<div class='doctor_box'>"+
							"<div class='doctor_left'>"+
							"<div class='doctor_photo'><img src='"+hostBG+"images/doctorFaceIcon/"+doctor[6]+"'/></div>"+
							"</div>"+
							"<div class='doctor_center'>"+
							"<p class='doctor_name'>"+doctor[7]+"</p>"+
							"<p style='color: #444'>"+doctor[2]+"</p>"+
							"<p style='color:#7c7c7c;'>专业方向："+doctorMajor+"</p>"+
							"<div class='mymark'>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>服务态度</div>"+
							"<div class='mark_list_right' id='fuwutaidu_"+i+"'></div>"+
							"</div>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>责任感</div>"+
							"<div class='mark_list_right' id='zerengan_"+i+"'></div>"+
							"</div>"+
							"<div class='mark_list'>"+
							"<div class='mark_list_left'>准时度</div>"+
							"<div class='mark_list_right' id='zhunshidu_"+i+"'></div>"+
							"</div>"+
							"</div>"+
							"</div>"+

							"<div class='consultation_icon' onclick=\"consulationSomeDoctor("+doctor[0]+",'"+doctor[7]+"')\"></div>"+
							"</div>");
							if(doctor[8] == 0){
								for(var o=0;o < 5;o++){
									$("#fuwutaidu_"+i).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[8];o++){
									$("#fuwutaidu_"+i).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[8];o++){
									$("#fuwutaidu_"+i).append("<img src='images/icon_starkong.png'>");
								}							
							}

							if(doctor[9] == 0){
								for(var o=0;o < 5;o++){
									$("#zerengan_"+i).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[9];o++){
									$("#zerengan_"+i).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[9];o++){
									$("#zerengan_"+i).append("<img src='images/icon_starkong.png'>");
								}							
							}							

							if(doctor[10] == 0){
								for(var o=0;o < 5;o++){
									$("#zhunshidu_"+i).append("<img src='images/icon_star_gray.png'>");
								}
							}else{
								for(var o=0;o<doctor[10];o++){
									$("#zhunshidu_"+i).append("<img src='images/icon_star.png'>");
								}
								for(var o=0;o< 5-doctor[10];o++){
									$("#zhunshidu_"+i).append("<img src='images/icon_starkong.png'>");
								}							
							}	
					}

				}
			}else if(result.mes=="无咨询医生"){
				ale("没有医生能够提供咨询服务");
			}
		},
		error: function () {
			layer();
		}
	});
}



//在列表中选择一个医生进行咨询
function consulationSomeDoctor(doctorId,doctorName){
	$.ajax({
		type:'post',
		url:host+'getUserConsultDoctorInfo.action',
		cache:false,
		async:false,
		data:{action:"getLastConsultation",doctorId:doctorId},
		success:function(result){
//			alert("MES"+result.mes);
			if(result.mes=="操作成功"){
//				alert("1");
				/*ale("医生的姓名："+result.doctorName);
				ale("医生的头像:"+result.doctorImage);
				ale("宝宝的姓名："+result.babyName);
				ale("宝宝的月龄："+result.babyMonthYear);
				ale("宝宝的性别："+result.babySex);
				for(var i=0;i<result.lastConsultLogList.length;i++){
					ale("第"+(i+1)+"条咨询的内容是："+result.lastConsultLogList[i].symptomDescribe);
				}
				for(var i=0;i<result.lastSymptomTagList.length;i++){
					ale("第"+(i+1)+"个症状标签的名称是："+resut.lastSymptomTagList[i].symptomName);
				}*/
				//update by fkn
				window.location.href="consultation1.html?doctorId="+doctorId+"&doctorName="+encodeURIComponent(doctorName)+"&Page=quick";
			}else if(result.mes=="已结束"){
//				alert("2");
				window.location.href='fillconsultation.html?doctorId='+doctorId+"&doctorName="+encodeURIComponent(doctorName)+"&Page=quick";
			}else if(result.mes=="未登录"){
				ale("您还没有登录，请您登录后再来咨询");
				
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