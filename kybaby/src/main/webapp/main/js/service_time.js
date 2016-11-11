/**
 * Created by vinny on 2015/10/12.
 */
var doctorId="";
var prodId="";
var date="";
var time="";
var dateList="";
var timeList="";
var serviceTime="";
var selectDate="";
	$(function(){
		//var myDate=new Date();
		//var yue = myDate.getMonth();

		var arrUrlBase=window.location.search.substring(1).split("&");
		for(var i=0;i<arrUrlBase.length;i++){
			if(arrUrlBase[i].split("=")[0]=="doctorId"){
				doctorId=arrUrlBase[i].split("=")[1];
			} else if(arrUrlBase[i].split("=")[0]=="prodId"){
				prodId=arrUrlBase[i].split("=")[1];
			} else if(arrUrlBase[i].split("=")[0]=="time"){
				time=arrUrlBase[i].split("=")[1];
			}  else if(arrUrlBase[i].split("=")[0]=="date"){
				date=arrUrlBase[i].split("=")[1];
			}  else if(arrUrlBase[i].split("=")[0]=="serviceTime"){
				serviceTime=arrUrlBase[i].split("=")[1];
			}
		}
		/*不需要选医生来选时间，注释掉 ---侯飞
		if(doctorId==""||doctorId==null){
			getService();
		} else{
			getServiceDateList(prodId,doctorId);
		}
		*/

//	for(var i=0;i < $('#boxs td').length;i++){
//		var objtd = $('#boxs td').eq(i);
//		var mark = true;
//		var num = 0;
//		for(var j=0;j <= serviceTime;j++){
//			if($('#boxs td').eq(i+j).hasClass('blue')){
//				mark = false;
//			}
//			if($('#boxs td').eq(i+j).get(0) != undefined){
//				num++;
//			}
//		}
//		if(!mark && !objtd.hasClass('blue')){
//			objtd.css('background','#ff813d').css('color','#fff');
//			//objtd.css('background','#FFCC32').css('color','#fff');
//		}
//		if((num-1) != serviceTime){
//			//objtd.css('background','#FFCC32').css('color','#fff');
//			objtd.css('background','#ff813d').css('color','#fff');
//		}
//	}







		
		//选择时间
		$('#boxs').find('td').click(function(){
			if($(this).attr('class')=='blue'){
				ale("时间不可用");
				//ale("该段间段已被预定");
			}else{
				var temObj=$(this);
				var boxIdTemp=temObj.attr('id').split("_")[1];
				var flag=0;
				for(var i=0;i<serviceTime;i++){
					//boxIdTemp=Number(boxIdTemp)+i+1;
					//boxIdTemp=Number(boxIdTemp)+1;
					boxIdTemp=Number(boxIdTemp);
					if($("#box_"+boxIdTemp).attr('class')=='blue'||$("#box_"+boxIdTemp).get(0)==undefined){
						ale("剩余时间不足");
						flag=1;
					}
				}
				if(flag==0){
					$(this).addClass("yellow");
					var startTime=$(this).text().split("-")[0];
					//var endTime=$("#box_"+String(Number(($(this).attr('id').split("_")[1])+Number(serviceTime)+Number(1)))).text().split("-")[1];
				    var endTime=$("#box_"+boxIdTemp).text().split("-")[1];
					//window.location.href=window.location.href;
					if(window.location.search.indexOf("time=")<0){
                        var isSession=sessionStorage.getItem('session');
                        if(isSession=='yes'){
                            window.location.replace("yjh_orderconfirm.html"+window.location.search+"&time="+startTime+"-"+endTime+"&date="+selectDate);
                        }else{
                            window.location.replace("orderconfirm.html"+window.location.search+"&time="+startTime+"-"+endTime+"&date="+selectDate);
                        }
					} else {
						var arrUrlTemp=window.location.search.substring(1).split("&");
						var strUrlTemp="";
						for(var i=0;i<arrUrlTemp.length;i++){
							if(arrUrlTemp[i].split("=")[0]=="date"){
								strUrlTemp+="&date="+selectDate;
							}  else if(arrUrlTemp[i].split("=")[0]=="time"){
								strUrlTemp+="&time="+startTime+"-"+endTime;
							} else{
								strUrlTemp+="&"+arrUrlTemp[i];
							}
						}
                        var isSession=sessionStorage.getItem('session');
                        if(isSession=='yes'){
                            window.location.replace("yjh_orderconfirm.html?"+strUrlTemp.substring(1));
                        }else{
                            window.location.replace("orderconfirm.html?"+strUrlTemp.substring(1));
                        }
					}
				}
			}
			
		});
	});

function select_week(obj){
	$(".sj_box").removeClass("grey_box");
	$(obj).addClass("grey_box");
	isToday();
	selectDate=$(obj).attr('value');
	
	return false;
	
	$('#boxs').find('td').addClass('blue');
	for(var i=0;i<dateList.length;i++){
		if(timeList[i]!=null){
			if(dateList[i]==$(obj).attr('value')){
				for(var j=0;j<timeList[i].length;j++){	
					$("#box_"+timeList[i][j]).removeClass('blue');
				}
				
			}							
		}
	}
	for(var i=0;i < $('#boxs td').length;i++){
		if($('#boxs td').eq(i).hasClass('blue')){
			
			switch (serviceTime) {
				case '1':
					 $('#boxs td').eq(i-1).attr('class','blue');
				break;
				case '2':
						$('#boxs td').eq(i-1).attr('class','blue');
						$('#boxs td').eq(i-2).attr('class','blue');
				break;
				case '3':
						$('#boxs td').eq(i-1).attr('class','blue');
						$('#boxs td').eq(i-2).attr('class','blue');
						$('#boxs td').eq(i-3).attr('class','blue');
				break;
				default:
					// default statements
			}
		}
	}
	
	
}


//选中了某个商品和医生之后，获取医生的服务时间列表
function getServiceDateList(prodcutId,doctorId){
	//var prodcutId=$.trim($("#newChoseProductId").val());
	//var doctorId=$.trim($("#choseDoctorId").val());
	$.ajax({
		type:'post',
		url:hostMain+'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getSomeDoctorServiceTimeList",prodcutId:prodcutId,doctorId:doctorId},
		success:function(result){

			if(result.mes=="操作成功"){
				var firstDate=result.dateTor;
				selectDate=result.dateTor;
				for(var i = 0;i<7;i++){
					var myDate=new Date(result.dateTor);
					
					myDate.setDate(myDate.getDate()+i);
					var a =(myDate.getMonth()+1)+"/"+myDate.getDate();
					var b = myDate.getDay();
					var c='';
					//var d=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
					var myDateMonth;
					var myDateDay;
					if(myDate.getMonth() < 10){
						
						myDateMonth = "0" + myDate.getMonth();
					}else{
						
						myDateMonth = myDate.getMonth();	
					}

					if(myDate.getDate() < 10){
						myDateDay = "0" + myDate.getDate();
					}else{
						myDateDay = myDate.getDate();
					}
					var d = myDate.getFullYear()+"-"+ (myDateMonth+1) +"-"+myDateDay;
					
					switch (b) {
						case 0:
							c = '周天';
							break;
						case 1:
							c = '周一';
							break;
						case 2:
							c = '周二';
							break;
						case 3:
							c = '周三';
							break;
						case 4:
							c = '周四';
							break;
						case 5:
							c = '周五';
							break;
						case 6:
							c = '周六';
							break;
					}
					if(i==0){
						$("#time_box").append(
								"<div class='sj_box grey_box' onclick='select_week(this)' value='"+d+"'>"+
								"<span>"+c+"</span><br/><span>"+a+"</span>"+
								"</div>");
					}else{
						$("#time_box").append(
								"<div class='sj_box' onclick='select_week(this)' value='"+d+"'>"+
								"<span>"+c+"</span><br/><span>"+a+"</span>"+
								"</div>");
					}
					
				}
				
				
				dateList=result.serviceDateList;
				timeList=result.serviceTimeList;
				for(var i=0;i<result.serviceDateList.length;i++){
						if(result.serviceTimeList[i]==null){
							//ale("该天还不存在服务时间段");
						}else{
							if(result.serviceDateList[i]==firstDate){
								for(var j=0;j<result.serviceTimeList[i].length;j++){	
									$("#box_"+result.serviceTimeList[i][j]).removeClass('blue');
									//ale("该天可以用的第"+(i+1)+"个时间段是："+result.serviceTimeList[i][j]);
								}
							}							
						}
				}
				
				/*for(var i=0;i<result.serviceDateList.length;i++){
				ale("第"+(i+1)+"个服务的日期是:"+result.serviceDateList[i]);
				if(result.serviceTimeList[i]!=null){
					for(var j=0;j<result.serviceTimeList[i].length;j++){
						ale("第"+(j+1)+"个服务时间段是:"+result.serviceTimeList[i][j]);
					}
				}else{
					ale("该医生在该天没有服务时间");
				}
			}*/
				
			}else if(result.mes=="无服务"){
				ale("该医生没有服务安排");
			}
		},
		error: function () {
		}
	});
}


//获取每一天的服务时间段
function getService(){
	$.ajax({
		type:'post',
		url:hostMain+'getDoctorInfo.action',
		cache:false,
		async:false, 
		data:{action:"getService"},
		success:function(result){
			
			if(result.mes=="操作成功"){

				var firstDate=result.dateTor;
				selectDate=result.dateTor;
				for(var i = 0;i<7;i++){
					var myDate=new Date(result.dateTor);

					myDate.setDate(myDate.getDate()+i);
					var a =(myDate.getMonth()+1)+"/"+myDate.getDate();
					var b = myDate.getDay();
					var c='';
					var myDateMonth;
					var myDateDay;
					if(myDate.getMonth() < 10){					
						myDateMonth = "0" + myDate.getMonth();
					}else{						
						myDateMonth = myDate.getMonth();	
					}
					if(myDate.getDate() < 10){
						myDateDay = "0" + myDate.getDate();
					}else{
						myDateDay = myDate.getDate();
					}
					var d = myDate.getFullYear()+"-"+ (myDateMonth+1) +"-"+myDateDay;	
					switch (b) {
						case 0:
							c = '周天';
							break;
						case 1:
							c = '周一';
							break;
						case 2:
							c = '周二';
							break;
						case 3:
							c = '周三';
							break;
						case 4:
							c = '周四';
							break;
						case 5:
							c = '周五';
							break;
						case 6:
							c = '周六';
							break;
					}
					if(i==0){
						$("#time_box").append(
								"<div class='sj_box grey_box' onclick='select_week(this)' value='"+d+"'>"+
								"<span>"+c+"</span><br/><span>"+a+"</span>"+
								"</div>");
					}else{
						$("#time_box").append(
								"<div class='sj_box' onclick='select_week(this)' value='"+d+"'>"+
								"<span>"+c+"</span><br/><span>"+a+"</span>"+
								"</div>");
					}
					
				}
				
				/*if(result.serviceTimeInitList!=null){
					for(var i=0;i<result.serviceTimeInitList.length;i++){
						var serviceTime=result.serviceTimeInitList[i];
						ale("第"+(i+1)+"个服务时间区段的名称是："+serviceTime.name);
						ale("第"+(i+1)+"个服务时间区段的开始时间是："+serviceTime.startTime);
						ale("第"+(i+1)+"个服务时间区段的结束时间是："+serviceTime.endTime);
					}
				}else{
					ale("还没有服务时间呢");
				}*/
				dateList=result.serviceDateList;
				timeList=result.serviceTimeList;
				for(var i=0;i<result.serviceDateList.length;i++){
						if(result.serviceTimeList[i]==null){
							//ale("该天还不存在服务时间段");
						}else{
							if(result.serviceDateList[i]==firstDate){
								for(var j=0;j<result.serviceTimeList[i].length;j++){	
									$("#box_"+result.serviceTimeList[i][j]).removeClass('blue');
									//ale("该天可以用的第"+(i+1)+"个时间段是："+result.serviceTimeList[i][j]);
								}
							}							
						}
				}
			}
		},
		error: function () {
		}
	});
}
//侯飞 修改于 2015年12月25日10:55:28
$(function(){
	(function(){

		var date = new Date();
		var firstDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
		for(var i = 0;i<7;i++){
			var myDate=new Date(date.getFullYear(),date.getMonth(),date.getDate());
			myDate.setDate(myDate.getDate()+i);
			var a =(myDate.getMonth()+1)+"/"+myDate.getDate();
			var b = myDate.getDay();
			var c='';
			var myDateMonth;
			var myDateDay;
			if(myDate.getMonth() < 10){					
				myDateMonth = "0" + myDate.getMonth();
			}else{						
				myDateMonth = myDate.getMonth();	
			}
			if(myDate.getDate() < 10){
				myDateDay = "0" + myDate.getDate();
			}else{
				myDateDay = myDate.getDate();
			}

			var d = myDate.getFullYear()+"-"+ (Number(myDateMonth)+1) +"-"+myDateDay;	
			
			if(d.split("-")[1].length == 1){
				d = myDate.getFullYear()+"-0"+ (Number(myDateMonth)+1) +"-"+myDateDay;	
			}
			
			switch (b) {
				case 0:
					c = '周天';
					break;
				case 1:
					c = '周一';
					break;
				case 2:
					c = '周二';
					break;
				case 3:
					c = '周三';
					break;
				case 4:
					c = '周四';
					break;
				case 5:
					c = '周五';
					break;
				case 6:
					c = '周六';
					break;
			}

			if(i==0){
				$("#time_box").append(
						"<div class='sj_box grey_box' onclick='select_week(this)' value='"+d+"'>"+
						"<span>"+c+"</span><br/><span>"+a+"</span>"+
						"</div>");
				selectDate = d;
			}else{
				$("#time_box").append(
						"<div class='sj_box' onclick='select_week(this)' value='"+d+"'>"+
						"<span>"+c+"</span><br/><span>"+a+"</span>"+
						"</div>");
			}
		}	

		isToday();
		
		
	})();

});
function isToday(){
		$('#boxs td').each(function(){
			var date = new Date();
			var hours = date.getHours();
			if($('.sj_box').eq(0).hasClass('grey_box') && (Number($(this).attr('id').substring(4)) - hours < 3)){
				$(this).addClass('blue');
			}else{
				$(this).removeClass('blue');
			}
		});
}