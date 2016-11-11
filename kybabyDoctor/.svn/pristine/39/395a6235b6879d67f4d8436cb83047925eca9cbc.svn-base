$(function(){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			//ale('您的位置：'+r.point.lng+','+r.point.lat);
			var pt = r.point;
			var geoc = new BMap.Geocoder();
			geoc.getLocation(pt, function(rs){
				var addComp = rs.addressComponents;
				//ale(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
				$("#detailAdd").val(addComp.streetNumber);
				$("#jiedao").val(addComp.street);
				var sheng = addComp.province.substring(0,2);
				var shi = addComp.city.substring(0,2);
				addressInit('sheng', 'shi', 'qu', sheng, shi, addComp.district);
				hf_loading(false);
				//ale(sheng);
			});  
		}
		else {
			ale('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
});