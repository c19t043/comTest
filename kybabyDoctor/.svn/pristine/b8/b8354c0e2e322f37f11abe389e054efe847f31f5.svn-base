$(function(){
	hf_loading(true);
});
function newAddress(){
	if($("#detailAdd").val()!=""&&$("#detailAdd").val()!=null){
		var doctorLat = "";
		var doctorLng = "";
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		var strList = $("#sheng").val()+"省"+$("#shi").val()+"市"+$("#qu").val()+$("#jiedao").val()+$("#detailAdd").val();
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
					data : {action:"add",detailAddress:$("#detailAdd").val(),doctorStreet:$("#jiedao").val(),doctorArea:$("#qu").val(),doctorCity:$("#shi").val(),doctorLat:doctorLat,doctorLng:doctorLng,doctorProvince:$("#sheng").val()},
					success : function(result) {
//						ale(result.mes);
						if(result.mes=="请登录"){
							window.location.href="login.html";
						}
						else if(result.mes=="成功"){
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


function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
	if(status == false){
		document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
		return false;
	}
	$('body').append(
		"<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
		"<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
		"<img id='hf_loading_img' src='images/hf_autoplay.png' style='margin-bottom:10px;' />"+
		"<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:16px;text-align:left;color:#868686;font-weight:bold;'>地址加载中。</span>"+
		"</div>"+
		"</div>");

	var divobj = document.getElementById('hf_loading');
	var boxobj = document.getElementById('hf_loading_box');
	var imgobj = document.getElementById('hf_loading_img');
	var spanobj = document.getElementById('hf_loading_word');
	var du = 0;

	//divobj.style.width = document.documentElement.scrollWidth + "px";
	//divobj.style.height = document.documentElement.scrollHeight + "px";
	divobj.style.width = $(document).width() + "px";
	divobj.style.height = $(document).height() + "px";

	boxobj.style.top = (document.documentElement.clientHeight/2 - boxobj.offsetHeight/2) + "px";
	boxobj.style.left = (document.documentElement.clientWidth/2 - boxobj.offsetWidth/2) + "px";

	function trans(){
		if(du == 360){
			du = 0;
		}else{
			du = du + 10;
		}
		imgobj.style.transform = "rotate("+du+"deg)";
		imgobj.style.WebkitTransform = "rotate("+du+"deg)";
	}
	function transword(){
		if(spanobj.innerHTML == '地址加载中。'){
			spanobj.innerHTML = '地址加载中。。';
		}else if(spanobj.innerHTML == '地址加载中。。'){
			spanobj.innerHTML = '地址加载中。。。';
		}else if(spanobj.innerHTML == '地址加载中。。。'){
			spanobj.innerHTML = '地址加载中。';
		}
	}
	setInterval(function(){
		transword();
	},300);
	setInterval(function(){
		trans();
	},20);
}