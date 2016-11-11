
$(function(){
	getGrowHis();
});

//获取历史成长记录
function getGrowHis(){
	$.ajax({
		type:'post',
		url:host+'getGrowthRecordInfo.action',
		cache:false,
		async:false,
		data:{action:"getGrowthRecord"},
		success:function(result){
			if(result.mes=="操作成功"){
				/*ale("头像是："+result.userImage);
				 ale("宝宝姓名是："+result.babyName);
				 ale("宝宝性别是："+result.babySex);
				 ale("宝宝生日是："+result.babyBirthday);
				 ale("宝宝月龄是："+result.babyMonthYear);*/
				if(result.userImage == null || result.userImage == 'null' || result.userImage == '' || result.userImage == 'undefined'){
					result.userImage = 'lt_user.png';
				}
				$("#babyBasicInfo").append(
					"<div class='info_left'><img src='"+hostBG+"images/userFaceIcon/"+result.userImage+"'></div>"+
					"		<div class='info_center'>"+
					"			<p>"+result.babyName+"</p>"+
					"			<p>"+result.babySex+"</p>"+
					"			<p>"+result.babyBirthday+"</p>"+
					"		</div>"+
					"		<div class='info_right'><p><span>"+result.babyMonthYear+"</span>个月</p></div>"
				);
				if(result.growthRecordList!=null){
					for(var i=0;i<result.growthRecordList.length;i++){
						var cons=result.growthRecordList[i];
						/*ale("记录的日期是："+cons.recordDate);
						 ale("记录的图片是："+cons.uploadImage);
						 ale("睡眠小时数是："+cons.sleepHour);
						 ale("每次母乳进食量:"+cons.everyBreastfeeding);
						 ale("母乳进食次数："+cons.BreastfeedingTimes);
						 ale("辅食进食次数："+cons.assistFoodsTimes);
						 ale("排便次数"+cons.defecateTimes);
						 ale("运动次数"+cons.exerciseTimes);*/
						var status = "block";
						if(cons.uploadImage==null||cons.uploadImage=='null'||cons.uploadImage==''){
							status ='none';
						}
						$("#container").append(
							"	<div class='record_com clearfix' >"+
							"		<p class='record_orna'></p>"+
							"		<div class='record_sub' onclick='revise("+cons.id+")'>"+
							"			<p class='date'>"+cons.recordDate+"</p>"+
							"           <p style='display:"+status+"' class='record_img'><img src='"+hostBG+"images/growthrecord/"+cons.uploadImage+"'></p>"+
							"			<div class='times_box'>"+
							"				<p class='sleep'>睡觉<span>"+cons.sleepHour+"</span>小时</p>"+
							"				<p class='defecation'>排便<span>"+cons.defecateTimes+"</span>次</p>"+
							"				<p class='mummilk'>母乳<span>"+cons.breastfeedingTimes+"</span>次<span>"+cons.everyBreastfeeding+"</span>ml/次</p>"+
							"				<p class='sport'>运动<span>"+cons.exerciseTimes+"</span>小时</p>"+
							"				<p class='supfood'>辅食<span>"+cons.assistFoodsTimes+"</span>次</p>"+
							"			</div>"+
							"		</div>"+
							"	</div>"
						);
					}
				}else{
					//ale("暂时还没有成长记录");
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
function handleFiles_record(obj,img3){
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
		document.getElementById('record_img').style.background = img.src;
		//document.getElementById(img3).src=img.src;
	}else if(window.FileReader){
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = function(e){
			img.src = this.result;
			img.width = 200;
			document.getElementById('record_img').style.background = img.src;
			//document.getElementById(img3).src=img.src;
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
		//document.getElementById(img3).src=img.src;
		document.getElementById('record_img').style.background = img.src;
	}
}
function revise(id){
	window.location.href='growthrecordfill.html?'+id;
}