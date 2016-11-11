	var lng = '';
	var lat = '';
$(function(){
	getUserSomeInfo();

		
//var geolocation = new BMap.Geolocation();
//	geolocation.getCurrentPosition(function(r){
//		if(this.getStatus() == BMAP_STATUS_SUCCESS){
//			lng = r.point.lng;
//			lat = r.point.lat;
//			var pt = r.point;
//			var geoc = new BMap.Geocoder();
//			geoc.getLocation(pt, function(rs){
//				var addComp = rs.addressComponents;
//				$("#detailStreet").val(addComp.street + addComp.streetNumber);
//				var sheng = addComp.province.substring(0,2);
//				var shi = addComp.city.substring(0,2);
//
//				$("#city").citySelect({
//					url:"js/city.min.js",
//					prov:sheng, //省份
//					city:shi, //城市
//					dist:addComp.district, //区县
//					nodata:"none" //当子集无数据时，隐藏select
//				});
//			});  
//		}
//		else {
//			ale('failed'+this.getStatus());
//		}        
//	},{enableHighAccuracy: true})	
//-----------------------------------------------------------------

	
	
});
function getUserSomeInfo(){
	$.ajax({
		type:'post',
		url:host+'getUserInfo.action',
		cache:false,
		async:false, 
		data:{
			action:"getUser"
		},
		success:function(result){
			if(result.mes=="操作成功"){
				if(result.usr!=null){
					var usr=result.usr;					
					$('#parentName').val(usr.parentName);
					$('#parentPhone').val(usr.phone);
					$('#babyName').val(usr.babyName);
					$('#babySex option').filter(function(){
						return $(this).text() == usr.sex;
					}).get(0).checked = true;
					$('#babyBirthday').val(usr.birthday);
					

					//以下是日期初始化
			
					//以上是日期初始化
					addressInit('userProvince', 'userCity', 'userArea',usr.userProvince,usr.userCity,usr.userArea);
	        $('#detailStreet').val(usr.userStreet);
	        $('#detailAddress').val(usr.detailAddress);
	        
				}
			}else if(result.mes=="未登录"){
				ale("请先登录系统！");
			}
		},
		error: function () {
			layer();
		}
	});
}

function checkAddredd(){

}



function changeUserInfo(){
		
	var parentName=$.trim($("#parentName").val());
	var parentTel=$.trim($("#parentPhone").val());
	var babyName=$.trim($("#babyName").val());
	var babySex=$.trim($("#babySex").val());
	var babyBirthday=$.trim($("#babyBirthday").val());
	var userProvince=$.trim($("#userProvince").val());
	var userCity=$.trim($("#userCity").val());
	var userArea=$.trim($("#userArea").val());
	var userStreet=$.trim($("#detailStreet").val());
	var detailAddress=$.trim($("#detailAddress").val());
		
	var userLng=lng;
	var userLat=lat;
	
	if(parentName==""){
		ale("请输入家长姓名");
		return false;
	}else if(parentTel==""){
		ale("请填写家长手机号");
		return false;
	}else if(babyName==""){
		ale("请输入宝宝的姓名");
		return false;
	}else if(babySex==""){
		ale("请输入宝宝的性别");
		return false;
	}else if(babyBirthday==""){
		ale("请输选择宝宝的出生日期");
		return false;
	}else if(detailAddress==""){
		ale("请输入你的详细地址");
		return false;
	}
	var today = (new Date()).getFullYear()+(new Date()).getMonth()+1+(new Date()).getDate();
	var date_arr = babyBirthday.split('-');
	var date_time = parseInt(date_arr[0])+parseInt(date_arr[1])+parseInt(date_arr[2])+1;


	var dyear = (new Date()).getFullYear();
	var dmonth = (new Date().getMonth())+1;
	var dday = (new Date().getDate());
	var nyear = parseInt(date_arr[0]);
	var nmonth = parseInt(date_arr[1]);
	var nday = parseInt(date_arr[2]);
	if(nyear > dyear){
		ale('请选择正确的生日');
		return false;
	}else if(nyear == dyear && nmonth > dmonth){
		ale('请选择正确的生日');
		return false;
	}else if(nyear == dyear && nmonth == dmonth && nday>dday){
		ale('请选择正确的生日');
		return false;
	}

	
	
	
	var myGeo = new BMap.Geocoder();
	var strList = $("#userProvince").val()+"省"+$("#userCity").val()+"市"+$("#userArea").val()+$("#detailStreet").val();
	myGeo.getPoint(strList, function(point){
		if(point){
			doctorLat=point.lat;
			doctorLng=point.lng;
			$.ajax({
				type:'post',
				url:host+'userManage.action',
				cache:false,
				async:false, 
				data:{
					action:"changeInfo",
					parentName:parentName,
					userPhone:parentTel,
					babyName:babyName,
					babySex:babySex,
					babyBirthDay:babyBirthday,
					userProvince:userProvince,
					userCity:userCity,
					userArea:userArea,
					userStreet:userStreet,
					detailAddress:detailAddress,
					userLng:point.lng,
					userLat:point.lat
				},
				success:function(result){
					if(result.mes=="操作成功"){
						ale("修改信息成功");
                        return_before();
					}else if(result.mes=="未登录"){
						ale("请先登录");
					}
				},
				error: function () {
					layer();
				}
			});

		}else{
				ale("您选择地址没有解析到结果!");
			}
		}, "成都市");



}

$(function () {

	var currYear = (new Date()).getFullYear();
	var opt = {};
	opt.date = {preset: 'date'};
	opt.datetime = {preset: 'datetime'};
	opt.time = {preset: 'time'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式
		mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		startYear: currYear - 5, //开始年份
		endYear: currYear //结束年份
	};

	$("#babyBirthday").mobiscroll($.extend(opt['date'], opt['default']));
	var optDateTime = $.extend(opt['datetime'], opt['default']);
	var optTime = $.extend(opt['time'], opt['default']);
	
	
	
	
});


//        $(function () {
//			var currYear = (new Date()).getFullYear();	
//			var opt={};
//			opt.date = {preset : 'date'};
//			opt.datetime = {preset : 'datetime'};
//			opt.time = {preset : 'time'};
//			opt.default = {
//				theme: 'android-ics light', //皮肤样式
//		        display: 'modal', //显示方式 
//		        mode: 'scroller', //日期选择模式
//				dateFormat: 'yyyy-mm-dd',
//				lang: 'zh',
//				showNow: true,
//				nowText: "今天",
//		        startYear: currYear - 10, //开始年份
//		        endYear: currYear + 10 //结束年份
//			};
//
//		  	$("#babyBirthday").mobiscroll($.extend(opt['date'], opt['default']));
//		  	var optDateTime = $.extend(opt['datetime'], opt['default']);
//		  	var optTime = $.extend(opt['time'], opt['default']);
//		    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
//		    $("#appTime").mobiscroll(optTime).time(optTime);
//        });