/**
 * Created by Administrator on 2015/9/25.
 */
var productIds;
var majorIds='';
//var symptomIds;
var serviceAddType='';
var goodAtField='';
var roleName = "";
var optionArr = new Array();

$(function(){
	gettittle();
	//加载医生原有信息
	var flag=decodeURIComponent(window.location.search).substring(1);
	if(flag == "needInit"){
		$(".mustFill").text("");
		initDoctorInfo();
	}else{
		roleName = decodeURIComponent(window.location.search).substring(1).split("&")[0];
	}
})
function sever_type(obj){
	$(obj).toggleClass("blue");
	$(obj).toggleClass("grey");
	var arr_string = '';
	var mark = $('#service li span.blue');
	for (var i = 0; i < mark.length; i++) {
		arr_string = arr_string + "::" + mark.eq(i).text();
	}
	serviceAddType = arr_string.substring(2);
}
function switch_sex(obj){
	$(obj).addClass("selected").siblings().removeClass("selected");
}
//加载擅长方向和业务方向
function gettittle(){
	$.ajax({
		url:host+'doctorIdentify.action',
		async:false,
		cache:false,
		data:{action:"getSomething"},
		success:function(result){
			for(var j=0;j<result.majorList.length;j++){
				if(result.majorList[j].doctorType=="技师"){
					$("#majorList").append("<li><span class='grey' onclick='changemajor(this)' majorId='"+result.majorList[j].id+"'>"+result.majorList[j].major+"</span></li>");
				}
			}
			for(var k=0;k<result.goodFieldList.length;k++){
				if(result.goodFieldList[k].doctorType=="技师") {
					$("#goodFieldList").append("<li><span class='grey' onclick='changesymptom(this)' goodFieldId='" + result.goodFieldList[k].id + "'>" + result.goodFieldList[k].name + "</span></li>");
				}
			}
			//if(result.positionList!=null){
			//	for(i=0;i<result.positionList.length;i++){
			//		//option的value值为小区ID, 显示的是小区的名字
			//		$("#doctorTittle").append(
			//			"<option value='"+result.positionList[i].rank +"'>"+result.positionList[i].name+"</option>"
			//		);
			//	}
			//	getService();
			//}
			//if(result.hospitalBasicInfoList!=null){
			//	for(i=0;i<result.hospitalBasicInfoList.length;i++){
			//		//option的value值为医院ID, 显示的是医院的名字
			//		$("#hospital").append(
			//			"<option value='"+result.hospitalBasicInfoList[i].id +"'>"+result.hospitalBasicInfoList[i].hospitalLname+"</option>"
			//		);
			//		//医院查询
			//		optionArr.push("<option value='"+result.hospitalBasicInfoList[i].id +"'>"+result.hospitalBasicInfoList[i].hospitalLname+"</option>");
			//	}
			//}
			////服务项目渲染
			//if(result.doctorServiceTypeFoList!=null){
			//	var divHtml = "";
			//	for(i=0;i<result.doctorServiceTypeFoList.length;i++){
			//		var parent = result.doctorServiceTypeFoList[i].doctorServiceType;
			//		var childList = result.doctorServiceTypeFoList[i].childList;
            //
			//		divHtml += "<p class='gray_1'></p>" +
			//			"<div class='row'>" +
			//			"<span class='tittle' style='font-size:14px'> 开放"+parent.serviceTypeName+"</span><br/>" +
			//			"<ul>";
			//		for(j=0;j<childList.length;j++){
			//			divHtml +="<li><span class='grey' onclick='changeServiceType(this)' serviceTypeId='"+childList[j].id+"'>"+childList[j].serviceTypeName+"</span></li>";
			//		}
			//		divHtml +=  "</ul></div>";
			//	}
			//	$("#serviceType").html(divHtml);
			//}
		}
	});
}

//function getService(){
//	$.ajax({
//		url:host+'doctorIdentify.action',
//		async:false,
//		cache:false,
//		data:{action:"getProduct",rank:  $("#doctorTittle").val()},
//		success:function(result){
//			if(result.mes=="已申请"){
//				ale("请不要重复提交");
//			}
//			if(result.productNameList!=null){
//				$("#serviceProducts").empty();
//				for(var i=0;i<result.productNameList.length;i++){
//					//option的value值为小区ID, 显示的是小区的名字
//					var str = result.productNameList[i].toString();
//					var strList = str.split(",");
//					$("#serviceProducts").append(
//						"<li><span class='grey' onclick='changecolor(this)' data_id='"+strList[0]+"'>"+strList[1]+"</span></li>"
//					);
//
//				}
//
//			}
//		}
//	});
//
//}

function submited(){
//	ale($("#renzheng06").val());
	var idCard=$.trim($("#idCard").val());
	var doctorHeadFileElem = $("#updateDoctorHeadFileElem").val();
	var MyHeadFileElem = $("#updateMyHeadFileElem").val();
//	if(location.search.substring(1) != 'needInit' && MyHeadFileElem == ""){
//		ale("请上传您的执照图片");
//	}
	if(location.search.substring(1) == 'needInit'){			//修改时标签字符串重组
		var arr_string = '';
		var mark = $('#majorList li span').filter(function(){
			return $(this).hasClass('blue');
		});
		for(var i=0;i < mark.length;i++){
			arr_string = arr_string + "::" + mark.eq(i).attr('majorId');
		}
		majorIds = arr_string.substring(2);
	}

	if(location.search.substring(1) == 'needInit'){			//修改时标签字符串重组
		var arr_string='';
		var mark = $('#goodFieldList li span').filter(function(){
			return $(this).hasClass('blue');
		});
		for(var i=0;i < mark.length;i++){
			arr_string = arr_string + "::" + mark.eq(i).attr('goodfieldid');
		}
        goodAtField = arr_string.substring(2);
	}

	if(location.search.substring(1) == 'needInit'){			//修改时标签字符串重组---服务方式
		var arr_string='';
		var mark = $('#service li span').filter(function(){
			return $(this).hasClass('blue');
		});
		for (var i = 0; i < mark.length; i++) {
			arr_string = arr_string + "::" + mark.eq(i).text();
		}
		serviceAddType = arr_string.substring(2);
	}
	if($("#renzheng01").val()==""){
		ale('请填写姓名');
		$("#renzheng01").focus();
		return false;
	}else if($("#renzheng03").val()==""){
		$("#renzheng03").focus();
		ale('请填写服务地点');
		return false;
	}else if($("#renzheng08").val()==""){
		$("#renzheng08").focus();
		ale('请填写银卡卡号');
		return false;
	}else if($("#renzheng09").val().trim()==""){
		$("#renzheng09").focus();
		ale('请填写从业时间');
		return false;
	}else if(isNaN($("#renzheng09").val().trim())){
		$("#renzheng09").focus();
		ale('从业时间请填写有效数字');
		return false;
	}else if($("#renzheng09").val().length > 2){
		ale('从业时间请填写有效数');
		$("#renzheng09").focus();
		return false;
	}else if(majorIds == ''){
		ale('请选择业务方向');
		return false;
	}else if(goodAtField == ''){
		ale('请选择擅长方向');
		return false;
	}else if(serviceAddType==''){
        ale('服务地点及服务类型方式');
        $("#renzheng09").focus();
        return false;
    }
//	else if(productIds == null){
//		ale('请选择服务产品');
//		return false;				
//	}
	else if(location.search.substring(1) != 'needInit' && doctorHeadFileElem == ""){
		ale("请上传您的头像")
		return false;
	}



	if($("#renzheng01").val()==""||$("#renzheng03").val()==""
		||$("#renzheng08").val()==""||majorIds==null||majorIds==""||$("#renzheng09").val()==""){
		ale("请完善认证信息");
	}
	else if(location.search.substring(1) != 'needInit' && doctorHeadFileElem == ""){
		ale("请上传您的头像");
	}
	else{
		if($("#renzheng08").val().trim().length>19){
			ale("请检查您的银行卡号!");
			return false;
		}
		if($("#renzheng01").val().trim().length>20){
			ale("请不要输入超过20个字符的名字!");
			return false;
		}
		if(idCard.length>30){
			ale("请检查您的资格证号!");
		}else
		{
			if(location.search.substring(1) == 'needInit'){
				if(!confirm('确定要修改吗？审核期间将无法提供服务！')){
					return false;
				}
			}
			hf_loading(true);
			$.ajax({
				type:'post',
				url:host+'doctorIdentify.action',
				async:false,
				data:{action:"submited",
					doctorName:$("#renzheng01").val(),
					doctorSex:$(".sex_box.selected").attr('data-sex'),
					//doctorTitle:$("#doctorTittle").val(),
					//doctorTitle:"技师",
					//doctorEmployer:$("#renzheng03").val(),
					//serviceMode:$("#renzheng04").val(),
					//serviceMode:"上门服务",
					bankAccountName:$("#renzheng07").val(),
					bankCard:$("#renzheng08").val(),
					//comeMethod:$("#renzheng05").val(),
					comeMethod:"平台接送",
					//serviceArea:$("#renzheng06").val(),
					serviceArea:"20",
					majorId:majorIds,
					//productIds:productIds,
					productIds:"7",
					//goodAtField:"1",
					//advisoryLabelIds:symptomIds,
					//idCard:idCard,
					//hospitalBasicInfoId:$("#renzheng03Id").val(),//医院id
					//department:$("#renzheng10").val(),//所在科室
					clinicalExperience:$("#renzheng09").val(),//临床经验
					//serviceTypeIds:serviceTypeIds,//服务项目ids
					roleName:roleName,
					serviceAddType:serviceAddType,//服务方式
					goodAtField:goodAtField//擅长领域
				},
				success:function(result){
					if(result.mes=="成功"){
					}
				},
				error:function(){
					alert("错误！");
				}
			});
			//上传资质证书图片
			sendImage("myCanvasUp");
			//上传头像图片
			sendImage("doctorCanvasUp");
		}
	}
}

//点击切换颜色并获取value值
//function changecolor(obj){
//	var arr_string='';
//	$(obj).toggleClass("blue");
//	$(obj).toggleClass("grey");
//	var mark = $('#serviceProducts li span').filter(function(){
//		return $(this).hasClass('blue');
//	});
//	for(var i=0;i < mark.length;i++){
//		arr_string = arr_string + "::" + mark.eq(i).attr('data_id');
//	}
//	productIds = arr_string.substring(2);
//}
//点击切换服务类型颜色并获取value值
//function changeServiceType(obj){
//	var arr_string='';
//	$(obj).toggleClass("blue");
//	$(obj).toggleClass("grey");
//	var mark = $('#serviceType ul li span').filter(function(){
//		return $(this).hasClass('blue');
//	});
//	for(var i=0;i < mark.length;i++){
//		arr_string = arr_string + "::" + mark.eq(i).attr('serviceTypeId');
//	}
//	serviceTypeIds = arr_string.substring(2);
//}
//点击切换颜色并获取value值
function changemajor(obj){
	var arr_string='';
	$(obj).toggleClass("blue");
	$(obj).toggleClass("grey");
	var mark = $('#majorList li span').filter(function(){
		return $(this).hasClass('blue');
	});
	for(var i=0;i < mark.length;i++){
		arr_string = arr_string + "::" + mark.eq(i).attr('majorId');
	}
	majorIds = arr_string.substring(2);
}

//点击切换颜色并获取value值
function changesymptom(obj){
	var arr_string='';
	$(obj).toggleClass("blue");
	$(obj).toggleClass("grey");
	var mark = $('#goodFieldList li span').filter(function(){
		return $(this).hasClass('blue');
	});
	for(var i=0;i < mark.length;i++){
		arr_string = arr_string + "::" + mark.eq(i).attr('goodFieldId');
	}
	goodAtField = arr_string.substring(2);
}



//测试。。。。。。。。。。。。。。。。。。。。
function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
	if(status == false){
		document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
		return false;
	}
	$('body').append(
		"<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
		"<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
		"<img id='hf_loading_img' src='images/hf_autoplay.png' style='margin-bottom:10px;' />"+
		"<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:16px;text-align:left;color:#868686;font-weight:bold;'>正在提交中。</span>"+
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
		if(spanobj.innerHTML == '正在提交中。'){
			spanobj.innerHTML = '正在提交中。。';
		}else if(spanobj.innerHTML == '正在提交中。。'){
			spanobj.innerHTML = '正在提交中。。。';
		}else if(spanobj.innerHTML == '正在提交中。。。'){
			spanobj.innerHTML = '正在提交中。';
		}
	}
	setInterval(function(){
		transword();
	},300);
	setInterval(function(){
		trans();
	},20);
}
// 加载 图像文件(url路径)  
function loadImage_touxiang(obj){
	var src = $(obj).get(0).files[0];
	var mpImg = new MegaPixImage(src);
	var mpImg_up = new MegaPixImage(src);
	var resCanvas1  = document.getElementById("doctorCanvas");//预览
	var resCanvas2  = document.getElementById("doctorCanvasUp");//上传

	mpImg.render(resCanvas1, {
		maxHeight: 40
	});
	mpImg_up.render(resCanvas2, {
		maxWidth: 400,
		maxHeight: 400
	});

	setTimeout(function(){
		$('#updateDoctorHeadFileElem').css({
			'width':function(){
				return $('#doctorCanvas').css('width');
			},
			'height':function(){
				return $('#doctorCanvas').css('height');
			}
		});
	},2000);
	$('#doctorCanvas').show().prev().remove();
};
function loadImage_zhizhao(obj){
	var src = $(obj).get(0).files[0];
	var mpImg = new MegaPixImage(src);
	var mpImg_up = new MegaPixImage(src);
	var resCanvas1  = document.getElementById("myCanvas");//预览
	var resCanvas2  = document.getElementById("myCanvasUp");//上传
	mpImg.render(resCanvas1, {
		maxHeight: 40
	});
	mpImg_up.render(resCanvas2, {
		maxWidth: 800,
		maxHeight: 800
	});

	setTimeout(function(){
		$('#updateMyHeadFileElem').css({
			'width':function(){
				return $('#myCanvas').css('width');
			},
			'height':function(){
				return $('#myCanvas').css('height');
			}
		});
	},2000);
	$('#myCanvas').show().prev().remove();
};
//---------------------------------------------  
// 上传图片，jQuery版  
//2015-12-02李昊修改，增加一个上传Canvas的id参数，用于区分不同的图片
function sendImage(canvasUpId){


	var canvas = document.getElementById(canvasUpId);
	// 获取Base64编码后的图像数据，格式是字符串  
	// "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
	var dataurl = canvas.toDataURL("image/png");
	// 为安全 对URI进行编码  
	// data%3Aimage%2Fpng%3Bbase64%2C 开头  
	var imagedata =  encodeURIComponent(dataurl);

	var actionName = "image";
	if(canvasUpId == "doctorCanvasUp"){
		actionName = "headImg";
	}

	$.ajax({
		url:host+'doctorIdentify.action',
		async:false,
		data :{
			action:actionName,
			imagedata:imagedata
		},
		type : "POST",
		success:function(){
			hf_loading(false);
			window.location.href='consultation.html';
		},
		error:function(){

		}
	});

};

//初始化加载医生信息
function initDoctorInfo(){
	//将提交改为修改 
	$("input").attr("readonly","readonly").attr('disabled','disabled');
	$("select").attr("disabled","disabled").attr('disabled','disabled');
	$('#majorList span').removeAttr('onclick');
	//$('#serviceType ul li span').removeAttr('onclick');
	$('#sex .sex_box').removeAttr('onclick');
	$('#service li>span').removeAttr('onclick');
	$('#goodFieldList li>span').removeAttr('onclick');
	$("#updateDiv").show();
	$("#submitDiv").hide();
	$(".mustFill").hide();
	$.ajax({
		url:host+'doctorIdentify.action',
		cache:false,
		async:true,
		data :{
			action:"initDoctorInfo"
		},
		type : "POST",
		success:function(result){
			var doctor = result.doctorInfo;
			$("#renzheng01").val(doctor.doctorName);
			if(doctor.doctorImage == ''){
				$('#showDoctor_img').attr("src",hostBG+"images/doctorFaceIcon/icon_c.png");
			}else{
				$('#showDoctor_img').attr("src",hostBG+"images/doctorFaceIcon/"+doctor.doctorImage);
			}
			$("#touxiang").val(doctor.doctorName);
            $('.sex_box').removeClass('selected');
            $('.sex_box[data-sex='+doctor.doctorSex+']').addClass('selected');
			$('#show_img').attr("src",hostBG+"images/doctorCertifiedPicture/"+doctor.licenseImage);
			//$("#doctorTittle").val(doctor.doctorTitle);//职称
			//$("#renzheng04").val(doctor.serviceMode);
			//$("#renzheng05").val(doctor.comeMethod);
			//$("#renzheng06").val(doctor.serviceArea);
			$("#renzheng07").val(doctor.bankAccountName);//开户行
			$("#renzheng08").val(doctor.bankCard);//卡号
			$("#renzheng09").val(doctor.clinicalExperience);//经验
			//所属科室
			//$("#renzheng10 option").each(function(){
			//	if($(this).html() == doctor.department){
			//		$(this).attr("selected","selected");
			//	}
			//});
			//医生职称doctorTitle
			//$("#doctorTittle option").each(function(){
			//	if($(this).html() == doctor.doctorTitle){
			//		$(this).attr("selected","selected");
			//	}
			//});
			//医生医院hospital
			//$("#hospital option").each(function(){
			//	if($(this).html() == doctor.doctorEmployer){
			//		$(this).attr("selected","selected");
			//	}
			//});
			//业务方向
			if(doctor.majorId != ""){
				var majorList = doctor.majorId.split("::");
				$("#majorList span").each(function(){
					if($.inArray($(this).attr("majorId"), majorList) > -1){
						$(this).attr("class","blue");
					}
				});
			}
			//擅长方向
			if(doctor.goodAtField != ""){
				var productIdList = doctor.goodAtField.split("::");
				$("#goodFieldList span").each(function(){
					if($.inArray($(this).attr("goodfieldid"), productIdList) > -1){
						$(this).attr("class","blue");
					}
				});
			}
			//服务方式
			if(doctor.serviceAddType != ""){
				var serviceTypeIdList = doctor.serviceAddType.split("::");
				$("#service li span").each(function(){
					if($.inArray($(this).text(), serviceTypeIdList) > -1){
						$(this).attr("class","blue");
					}
				});
			}
		},
		error:function(){

		}
	});
}
//修改编辑资料
function updateInfo(){
	if(!confirm("确认需要修改资料吗？")){
		return false;
	}
	$("#promptDiv").show();
	$("input").removeAttr("readonly").removeAttr("disabled");
	$("select").removeAttr("disabled").removeAttr("readonly");
	$('#majorList span').attr('onclick','changemajor(this)');
	//$('#serviceProducts span').attr('onclick','changecolor(this)');
	$('#sex .sex_box').attr('onclick','switch_sex(this)');
	$('#service li>span').attr('onclick','sever_type(this)');
	$('#goodFieldList li>span').attr('onclick','changesymptom(this)');
	//$('#serviceType ul li span').attr('onclick','changeServiceType(this)');
	$("#updateDiv").hide();
	$("#submitDiv").show();
	$(".mustFill").text("*");
	$(".mustFill").show();

	var head_ico = document.querySelector('#showDoctor_img');
	var head_canvas = document.querySelector('#doctorCanvasUp');
	var head_cdx = head_canvas.getContext('2d');
	var head_width = '';
	var head_height = '';
	$("<img/>").attr('src',$(head_ico).attr('src')).load(function(){
		head_width = this.width;//图片真实宽度
		head_height = this.height;//图片真实高度

		head_canvas.width = this.width;//重置画布宽度
		head_canvas.height = this.height;//重置画布高度

		head_cdx.clearRect(0,0,head_canvas.width,head_canvas.height);//清空画布
		head_cdx.drawImage(head_ico,0,0,head_width,head_height,0,0,head_canvas.width,head_canvas.height);
	});

	var zhizhao_ico = document.querySelector('#show_img');
	var zhizhao_canvas = document.querySelector('#myCanvasUp');
	var zhizhao_cdx = zhizhao_canvas.getContext('2d');
	var zhizhao_width = '';
	var zhizhao_height = '';
	$("<img/>").attr('src',$(zhizhao_ico).attr('src')).load(function(){
		zhizhao_width = this.width;
		zhizhao_height = this.height;

		zhizhao_canvas.width = this.width;//重置画布宽度
		zhizhao_canvas.height = this.height;//重置画布高度		

		zhizhao_cdx.clearRect(0,0,zhizhao_canvas.width,zhizhao_canvas.height);
		zhizhao_cdx.drawImage(zhizhao_ico,0,0,zhizhao_width,zhizhao_height,0,0,zhizhao_canvas.width,zhizhao_canvas.height);
	});

}
//选择地址相关
var chooseHos = {
	close:function(){
		event.preventDefault();
		$("#addressCover").fadeOut(200);
	},
	show:function(event){
		event.preventDefault();
		event.stopPropagation();
		$("#addressCover").show();
		$("#renzheng03").blur();
	},
	ok:function(){
		event.preventDefault();
		$("#addressCover").fadeOut(200);
		$("#renzheng03").val($("#hospital").find("option:selected").text());
		$("#renzheng03Id").val($("#hospital").val());
	}
}
//医院查询

function search(){
//	optionArr.length = 0; 
	var inputVal = $.trim($("#inputVal").val());
	if(optionArr.length > 0){
		$("#hospital").empty();
		for(i=0;i<optionArr.length;i++){
			if(optionArr[i].indexOf(inputVal) >= 0){
				$("#hospital").append(optionArr[i]);
			}
		}
	}
}

