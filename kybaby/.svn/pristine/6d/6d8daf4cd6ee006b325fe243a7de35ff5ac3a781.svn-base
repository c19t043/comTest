$(function(){
	getConsultationDoctor();
})


function getProductByProperty(obj){
	//所有标签置灰色
	//var objUl=$(obj).parent().parent();
	//objUl.find('span').attr('class','grey');
	////选中的标签置蓝色
	//$(obj).attr('class','blue');

	//所有标签置灰色
	$("#choose_bg span").attr('class','grey');
	//选中的标签置蓝色
	$(obj).attr('class','blue');


	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
		async:false,
		data:{action:"getConsultationDoctorList",sortMethod:"welcome"},
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
		},
		error: function () {
			layer();
		}
	});


	//$("div[mark]").hide();
	//$("div[mark="+$(obj).text()+"]").show();
}


//点击快捷咨询，进入选择咨询医生列表
function getConsultationDoctor(){
	var sortMethod=$.trim($("#sortMethod").val());
	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
        async:false,
		data:{action:"getConsultationDoctorList",sortMethod:sortMethod},
		success:function(result){
			if(result.mes=="操作成功"){
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
		},
		error: function () {
			layer();
		}
	});
}

//在列表中选择一个医生进行咨询
function consulationSomeDoctor(){
	var doctorId=$.trim($("#doctorId").val());
	$.ajax({
		type:'post',
		url:'getUserConsultDoctorInfo.action',
		cache:false,
        async:false,
		data:{action:"getLastConsultation",doctorId:doctorId},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("医生的姓名："+result.doctorName);
				ale("医生的头像:"+result.doctorImage);
				ale("宝宝的姓名："+result.babyName);
				ale("宝宝的月龄："+result.babyMonthYear);
				ale("宝宝的性别："+result.babySex);
				for(var i=0;i<result.lastConsultLogList.length;i++){
					ale("第"+(i+1)+"条咨询的内容是："+result.lastConsultLogList[i].symptomDescribe);
				}
				for(var i=0;i<result.lastSymptomTagList.length;i++){
					ale("第"+(i+1)+"个症状标签的名称是："+resut.lastSymptomTagList[i].symptomName);
				}
			}else if(result.mes=="已结束"){
				ale("跳转到症状标签选择页面");
				//获取症状标签列表
				$.ajax({
					type:'post',
					url:'getUserConsultDoctorInfo.action',
					cache:false,
			        async:false, 
					data:{action:"getAllSymptomTagList"},
					success:function(result){
						if(result.mes=="操作成功"){
							if(result.allSymptomTagList!=null){
								for(var i =0;i<result.allSymptomTagList.length;i++){
									ale("第"+(i+1)+"个症状标签的名称是："+resut.allSymptomTagList[i].symptomName);
								}
							}else{
								ale("还没有可以咨询的标签！");
							}
						}
					}
				});
			}else if(result.mes=="未登录"){
				ale("您还没有登录，请您登录后再来咨询");
			}
		},
		error: function () {
			layer();
		}
	});
}

//优先判断是否选择症状标签
function checkIds(){
	var checkIds=$.trim($("#tagIds").val());
	if(checkIds==null){
		ale("请选择症状标签之后再提交");
		return false;
	}
	return true;
}

//获取历史咨询记录,儿保前还是儿保后，通过typeMethod判断
function histConsult(){
	var typeMethod=$.trim($("#typeMethod").val());
	var choseDoctorId=$.trim($("#choseDoctorId").val());
	$.ajax({
		type:'post',
		url:'consultDoctorManage.action',
		cache:false,
        async:false,
		data:{action:"getHist",typeMethod:typeMethod,doctorId:choseDoctorId},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("医生的姓名："+result.doctorName);
				ale("医生的头像:"+result.doctorImage);
				ale("宝宝的姓名："+result.babyName);
				ale("宝宝的月龄："+result.babyMonthYear);
				ale("宝宝的性别："+result.babySex);
				for(var i=0;i<result.histConsultList.length;i++){
					ale("第"+(i+1)+"条咨询的内容是："+result.histConsultList[i].symptomDescribe);
				}
				for(var i=0;i<result.lastSymptomTagList.length;i++){
					ale("第"+(i+1)+"个症状标签的名称是："+resut.lastSymptomTagList[i].symptomName);
				}
			}else if (result.mes=="已结束") {
				ale("上一次咨询已结束");
			}else if(result.mes=="未登录"){
				ale("您还没有登录，请您登录后再来咨询");
			}
		},
		error: function () {
			layer();
		}
	});
}

function addNewConsult(){
	//添加新的咨询
	 var method=$.trim($("#method").val());
	 var choseDoctorId=$.trim($("#newDoctorId").val());
	 var content=$.trim($("#content").val());
	 var msgType=$.trim($("#msgType").val());
	 var msgId=$.trim($("#msgId").val());
	 if(content==""){
		 ale("请输入咨询的内容之后再咨询");
	 }else{
		 $.ajax({
				type:'post',
				url:'consultDoctorManage.action',
				cache:false,
		        async:false, 
				data:{action:"add",typeMethod:method,doctorId:choseDoctorId,msgType:msgType,msgId:msgId},
				success:function(result){
					if(result.mes=="操作成功"){
						for(var i=0;i<result.histConsultList.length;i++){
							ale("第"+(i+1)+"条咨询的内容是："+result.histConsultList[i].symptomDescribe);
						}
					}else if(result.mes=="已结束"){
						ale("咨询已结束");
					}else if(result.mes=="未登录"){
						ale("您还没有登录呢！");
					}
				},
			 error: function () {
				 layer();
			 }
			});
	 }
}

function endOfCal(){
	var logId=$.trim($("#logId").val());
	var doctorId=$.trim($("#endDoctorId").val());
	var method=$.trim($("#endMethod").val());
	$.ajax({
		type:'post',
		url:'consultDoctorManage.action',
		cache:false,
        async:false, 
		data:{action:"end",logId:logId,doctorId:doctorId,typeMethod:method},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("退出");
			} else if(result.mes=="未登录"){
				ale("您还没有登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

function handleFiles(obj,img3){ 
	window.URL = window.URL || window.webkitURL;
	var files = obj.files;
	var img = new Image();
	if(window.URL){
		img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
	  img.width = 101;
	  img.height = 101;
	  img.onload = function(e) {
	  window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
	};
	document.getElementById(img3).src=img.src;
	}else if(window.FileReader){
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = function(e){
			img.src = this.result;
			img.width = 200;
			document.getElementById(img3).src=img.src;
		};
	}else{
		obj.select();
		obj.blur();
		var nfile = document.selection.createRange().text;
		document.selection.empty();
		img.src = nfile;
		img.width = 200;
		img.onload=function(){
	};
		document.getElementById(img3).src=img.src;
	}
}

//查看健康档案
function healthArchive(){
	//getOrderResultInfo.action
	$.ajax({
		type:'post',
		url:'getOrderResultInfo.action',
		cache:false,
        async:false, 
		data:{action:"healthArchive"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("头像是："+result.userImage);
				ale("宝宝姓名是："+result.babyName);
				ale("宝宝性别是："+result.babySex);
				ale("宝宝生日是："+result.babyBirthday);
				ale("宝宝月龄是："+result.babyMonthYear);
				var basicData=result.basicData;
				
				ale("以下是基础健康数据");
				ale("宝宝的姓名是："+basicData.babyName);
				ale("宝宝的性别是："+basicData.babySex);
				
				ale("以下是健康档案数据");
				for(var i=0;i<result.dateStrList.length;i++){
					ale("第"+(i+1)+"个日期是："+result.dateStrList[i]);
					ale("第"+(i+1)+"个产品名称是："+result.productNameList[i]);
					for(var j=0;j<result.prodcutItemNameList[i].length;j++){
						ale("第"+(j+1)+"个项目名称是："+result.prodcutItemNameList[i][j]);
						ale("第"+(j+1)+"个项目结果是："+result.productItemResultList[i][j]);
					}
					for(var j=0;j<result.healthPlanNameList[i].length;j++){
						ale("第"+(j+1)+"个健康计划名称是："+result.healthPlanNameList[i][j]);
						for(var k=0;k<result.healthPathNameList[i][j].length;k++){
							ale("第"+(k+1)+"个健康计划名称是："+result.healthPathNameList[i][j][k]);
							ale("第"+(k+1)+"个健康计划执行次数是："+result.healthPathAmountList[i][j][k]);
						}
					}
				}
			}else if(result.mes=="无执行情况"){
				ale("暂时无执行情况");
			}else if(result.mes=="未登录"){
				ale("请先登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

//获取历史成长记录
function getGrowHis(){
	$.ajax({
		type:'post',
		url:'getGrowthRecordInfo.action',
		cache:false,
        async:false, 
		data:{action:"getGrowthRecord"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("头像是："+result.userImage);
				ale("宝宝姓名是："+result.babyName);
				ale("宝宝性别是："+result.babySex);
				ale("宝宝生日是："+result.babyBirthday);
				ale("宝宝月龄是："+result.babyMonthYear);
				if(result.growthRecordList!=null){
					for(var i=0;i<result.growthRecordList.length;i++){
						var cons=result.growthRecordList[i];
						ale("记录的日期是："+cons.recordDate);
						ale("记录的图片是："+cons.uploadImage);
						ale("睡眠小时数是："+cons.sleepHour);
						ale("每次母乳进食量:"+cons.everyBreastfeeding);
						ale("母乳进食次数："+cons.BreastfeedingTimes);
						ale("辅食进食次数："+cons.assistFoodsTimes);
						ale("排便次数"+cons.defecateTimes);
						ale("运动次数"+cons.exerciseTimes);
					}
				}else{
					ale("暂时还没有成长记录");
				}
			}else if(result.mes=="未登录"){
				ale("请先登录之后再查看数据");
			}
		},
		error: function () {
			layer();
		}
	});
}

function getBabtSexAndYear(){
	$.ajax({
		type:'post',
		url:'getUserInfo.action',
		cache:false,
        async:false, 
		data:{action:"sexAndYear"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("宝宝的性别是："+result.babySex);
				ale("宝宝的月龄是："+result.babyMonthYear);
			}else if(result.mes=="未登录"){
				ale("请先登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

function addNewRecord(){
	var recordType=$.trim($("#recordType").val());
	var recordValue=$.trim($("#recordValue").val());
	$.ajax({
		type:'post',
		url:'getUserInfo.action',
		cache:false,
        async:false, 
		data:{action:"addNewRecord",recordType:recordType,recordValue:recordValue},
		success:function(result){
			if(result.mes="操作成功"){
				ale("记录成功");
			} else if(result.mes=="未登录"){
				ale("请先登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

function getHistRecord(){
	var recordType=$.trim($("#recordType").val());
	$.ajax({
		type:'post',
		url:'getUserInfo.action',
		cache:false,
        async:false, 
		data:{action:"recordHist",recordType:recordType},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.recordHis!=null){
					if(recordType=="height"){
						for(var i=0;i<result.recordHis.length;i++){
							ale("第"+(i+1)+"个身高是："+result.recordHis[i].height);
						}
					} else if(recordType=="weight"){
						for(var i=0;i<result.recordHis.length;i++){
							ale("第"+(i+1)+"个体重是："+result.recordHis[i].weight);
						}
					} else if(recordType=="head"){
						for(var i=0;i<result.recordHis.length;i++){
							ale("第"+(i+1)+"个头围是："+result.recordHis[i].headLength);
						}
					}
				}else{
					ale("暂时还没有记录");
				}
			} else if(result.mes=="未登录"){
				ale("请先登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

function getHistAche(){
	$.ajax({
		type:'post',
		url:'getAcheInfo.action',
		cache:false,
        async:false, 
		data:{action:"getHis"},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("头像是："+result.userImage);
				ale("宝宝姓名是："+result.babyName);
				ale("宝宝性别是："+result.babySex);
				ale("宝宝生日是："+result.babyBirthday);
				ale("宝宝月龄是："+result.babyMonthYear);
				for(var i=0;i<result.histCaseClipList.length;i++){
					if(result.histTagList[i]!=null){
						for(var j=0;j<result.histTagList[i].length;j++){
							ale("第"+(j+1)+"个标签是："+(result.histTagList[i][j]).symptomName);
						}
					}
					var clips=result.histCaseClipList[i];
					if(clips.symptomImage!=""){
						ale("症状图为"+clips.symptomImage);
					}
					if(clips.prescribeImage!=""){
						ale("处方图为"+clips.prescribeImage);
					}
					if(clips.drugImage!=""){
						ale("药物图为"+clips.drugImage);
					}
				}
			}else if(result.mes=="无病历夹"){
				ale("暂时还没有病历夹");
			} else if(result.mes=="未登录"){
				ale("您还没有登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

function getAllTags(){
	$.ajax({
		type:'post',
		url:'getSymptomTagInfo.action',
		cache:false,
        async:false, 
		data:{action:"allTrue"},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.allSymptomTag!=null){
					for(var i=0;i<result.allSymptomTag.length;i++){
						ale("第"+(i+1)+"个标签的名称是"+(result.allSymptomTag[i]).symptomName);
					}
				}else{
					ale("还没有添加标签哦！");
				}
			}
		},
		error: function () {
			layer();
		}
	});
}

//健康计划历史记录
function getHealthPlanHist(){
	ale("健康计划历史记录");
	$.ajax({
		type:'post',
		url:'getHealthPlanRemainInfo.action',
		cache:false,
        async:false, 
		data:{action:"getAllHealthPlan"},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.allDateList!=null){
					ale("医生的头像是："+result.doctorImage);
					ale("医生的姓名是："+result.doctorName);
					ale("医生的职称是："+result.doctorTitle);
					for(var i=0;i<result.allDateList.length;i++){
						for(var j=0;j<result.allPlanNameList[i].length;j++){
							ale("第"+(i+1)+"个日期"+result.allDateList[i]+"的第"+(j+1)+"个健康计划名称是："+(result.allPlanNameList[i][j]).healthPlan);
							for(var k=0;k<result.allPathResultList[i][j].length;k++){
								if(result.allPathResultList[i][j][k]!='X'){
									ale("第"+(k+1)+"个健康路径是："+(result.allPathNameList[i][j][k]).healthPathName);
									ale("第"+(k+1)+"个健康路径结果是："+result.allPathResultList[i][j][k]);
								}
							}
						}
					}
				}else{
					ale("您还没有健康记录");
				}
				
			} else if(result.mes=="暂无记录"){
				ale("您还没有健康记录");
			} else if(result.mes=="未登录"){
				ale("请您先登录");
			}
		},
		error: function () {
			layer();
		}
	});
}

function confirmPlan(){
	ale("执行今日健康提醒");
	var planId=$.trim($("#planId").val());
	var pathId=$.trim($("#pathId").val());
	$.ajax({
		type:'post',
		url:'healthPlanRemainManage.action',
		cache:false,
        async:false, 
		data:{action:"confirm",planId:planId,pathId:pathId},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("操作成功");
			}else if(result.mes=="未登录"){
				ale("您还没有登录");
			}
		},
		error: function () {
			layer();
		}
	});
}
