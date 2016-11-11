$(function(){
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'addressManage.action',
		data : {action:"all"},
		success : function(result) {
//			ale(result.doctorAddress);
			for(var i=0;i<result.doctorAddress.length;i++){
				var city=result.doctorAddress[i].doctorCity;
				if(city.indexOf('市')<0){
					city=city+'市';
				}
				var area=result.doctorAddress[i].doctorArea;
				if(area.indexOf('区')<0){
					area=area+'区';
				}
				if(result.doctorAddress[i].addressStatus=="Y"){
					$("#container").append("<div zjid='"+result.doctorAddress[i].id+"' class='add_box'>"+
							"<div class='add_box_left'>"+
								"<p class='icon_point on'></p>"+
								"<div class='add_details'>"+
									"<p><span>"+result.doctorAddress[i].doctorProvince+"省</span>   <span>"+city+"</span>    <span>"+area+"</span></p>"+
									"<p>"+result.doctorAddress[i].doctorStreet+result.doctorAddress[i].detailAddress+"</p>"+
								"</div>"+
							"</div>"+
							"<div class='add_box_right' onclick='goto_next(this)'>&gt;</div>"+
						"</div>"+
						"<p class='gray_1'></p>");
				}
				else{
					$("#container").append("<div zjid='"+result.doctorAddress[i].id+"' class='add_box'>"+
							"<div class='add_box_left' onclick='change(this)'>"+
								"<p class='icon_point'></p>"+
								"<div class='add_details'>"+
									"<p><span>"+result.doctorAddress[i].doctorProvince+"省</span>   <span>"+city+"</span>    <span>"+area+"</span></p>"+
									"<p>"+result.doctorAddress[i].doctorStreet+result.doctorAddress[i].detailAddress+"</p>"+
								"</div>"+
							"</div>"+
							"<div class='add_box_right' onclick='goto_next(this)'>&gt;</div>"+
						"</div>"+
						"<p class='gray_1'></p>");
				}
				
			}

		}
	});
});



function goto_next(obj){
	window.location.href="modifyaddress.html?"+$(obj).parent().attr('zjid')+"";
}


function change(obj){
	$(obj).find('p:first').addClass('on');
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'addressManage.action',
		data : {action:"click",addressId:$(obj).parent().attr('zjid')},
		success : function(result) {
			if(result.mes=="成功"){
				window.location.href="consultation.html";
			}
		}
	});
}

