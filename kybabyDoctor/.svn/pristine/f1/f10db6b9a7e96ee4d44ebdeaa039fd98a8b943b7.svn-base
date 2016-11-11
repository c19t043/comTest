var addressId;
$(function(){
	addressId = window.location.search.substring(1);
	//ale(addressId);
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'addressManage.action',
		data : {action:"one",addressId:addressId},
		success : function(result) {
//			ale(result.doctAddress);
//			result.doctAddress;
			addressInit('prov', 'address_city', 'dist', result.doctAddress.doctorProvince, result.doctAddress.doctorCity, result.doctAddress.doctorArea);
//			$("#prov option").filter(function(){
//				return $(this).text() == result.doctAddress.doctorProvince;
//			}).get(0).selected = true;
			
//			$("#dist option").filter(function(){
//				return $(this).text() == result.doctAddress.doctorArea;
//			}).get(0).selected = true;
			
//			$("#address_city option").filter(function(){
//				return $(this).text() == result.doctAddress.doctorCity;
//			}).get(0).selected = true;
			$("#address_jiedao").val(result.doctAddress.doctorStreet);
			$("#address_detial").val(result.doctAddress.detailAddress);
		}
	});
});

function modify(){
	if($("#address_detial").val()!=""&&$("#address_detial").val()!=null){
		var doctorLat = "";
		var doctorLng = "";
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		var strList = $("#prov").val()+"省"+$("#address_city").val()+"市"+$("#dist").val()+$("#address_jiedao").val()+$("#address_detial").val();
		//ale(strList+"地址字符串");
		myGeo.getPoint(strList, function(point){
			if (point) {
				doctorLat=point.lat;
				doctorLng=point.lng;
				//ale(doctorLat+"纬度");
				//ale(doctorLng+"经度");
				$.ajax({
					type : 'post',
					async: false ,
					url : host+'addressManage.action',
					data : {action:"modify",addressId:addressId,doctorStreet:$("#address_jiedao").val(),detailAddress:$("#address_detial").val(),doctorArea:$("#dist").val(),doctorCity:$("#address_city").val(),doctorProvince:$("#prov").val(),doctorLat:doctorLat,doctorLng:doctorLng},
					success : function(result) {
						if(result.mes=="成功"){
							window.location.href="addressmanage.html";
						}
					}
				});
			}else{
				ale("您选择地址没有解析到结果!");
			}
		}, "成都市");
	}
	else{
		ale("请填写详细地址");
	}
}

function delet(){
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'addressManage.action',
		data : {action:"delete",addressId:addressId},
		success : function(result) {
			if(result.mes=="成功"){
				window.location.href="addressmanage.html";
			}else if(result.mes=="地址不存在"){
				ale("当前地址有异常！");
			}else if(result.mes=="修改"){
				ale("请修改当前地址！");
			}
		}
	});
}