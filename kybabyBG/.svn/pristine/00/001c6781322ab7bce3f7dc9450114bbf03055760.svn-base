//canvas上传图片
function loadImg(obj){
	var src = obj.files[0];//获取文件
	var reader = new FileReader();
  reader.onloadend = function(e){
  	var showCanvas = obj.parentNode.querySelector('.loadimg_showcanvas');
  	var show_ctx = showCanvas.getContext('2d'); 		
		show_ctx.clearRect(0,0,80,80);//清空画布
		
		var show_img = new Image();//预览画布效果
		show_img.onload=function(){
			var x,y,px,py;
			if(show_img.width >= show_img.height){
				x = 80;
				y = show_img.height*80/show_img.width;
				px = 0;
				py = 80/2 - y/2;
			}else{
				y = 80;
				x = show_img.width*80/show_img.height;
				py = 0;
				px = 80/2 - x/2
			}
			show_ctx.drawImage(show_img,0,0,show_img.width,show_img.height,px,py,x,y);
		};
		show_img.src = e.target.result;		

  	obj.parentNode.style.background = 'rgba(255,255,255,0)';
  	obj.parentNode.querySelector("img").style.opacity=0;
  	obj.parentNode.querySelector('.loadimg_base64').value=encodeURIComponent(e.target.result).replace("jpeg","png");
	};
	reader.readAsDataURL(src);
}
function dispear(){
	$('#circleDetails').animate({
		'left':'-100%',
		'opacity':'0'
	},800,function(){
		$('#circleDetails').hide();
	});
}
function show(){
	$('#circleDetails').show().animate({
		'left':'1%',
		'opacity':'1'
	},800);
}
function changeL(obj){
	$(obj).parent().parent().find('input').removeAttr('readonly').removeAttr('disabled');
	$(obj).parent().parent().find('select').removeAttr('readonly').removeAttr('disabled');
	//--
	$(obj).parent().parent().find("select[id^='mark']").attr({"readonly":"readonly","disabled":"disabled"});
	//--
	$(obj).css('background','#FFBC00').text('保存').attr('onclick','changeCurrentL(this)');
	$(obj).parent().parent().find('input').eq(0).focus();
}
function changeR(obj){
	$(obj).parent().find('input').removeAttr('readonly').removeAttr('disabled');
	$(obj).parent().find('select').removeAttr('readonly').removeAttr('disabled');
	$(obj).css('background','#FFBC00').text('保存').attr('onclick','changeCurrentR(this)');
	$(obj).parent().find('input').eq(0).focus();
}
function changeCurrentL(obj){

	if($(obj).data("tag") == "firstLevel"){
		addOrChange("change",obj);
			$(obj).parent().parent().find('input').attr({'readonly':'readonly','disabled':'disabled'});
			$(obj).parent().parent().find('select').attr({'readonly':'readonly','disabled':'disabled'});
	}else if($(obj).data("tag") == "secondLevel"){
		secondAddOrChange("change",obj);
	}

	
}
function changeCurrentR(obj){
	$(obj).parent().find('input').attr({'readonly':'readonly','disabled':'disabled'});
	$(obj).parent().find('select').attr({'readonly':'readonly','disabled':'disabled'});
	$(obj).removeAttr('style').text('修改').attr('onclick','changeR(this)');
}
var ringHost="../ring/";
$(function(){
	getAllRingCategory();
	showFirstLevel();
});

//新增获取圈子类别
function getAllRingCategory(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
    async:true, 
		data:{action:"allCategory"},
		success:function(result){
			$('#ssfl').empty();
			if(result.mes != '操作成功' || result.allCategory == null){
				return false;
			}
			
			for(var i=0;i<result.allCategory.length;i++){
				$('#ssfl').append(
					"<option zjid='"+result.allCategory[i][0]+"'>"+result.allCategory[i][1]+"</option>"
				);		
			}
		}
	});
}
//点击获取详情
function getDetail(zjid,obj){

	//event.stopPropagation();
	
	$("#secondFontWeight").text($(obj).data("name"));
	getSecondCircleList(zjid);
	//event.preventDefault();
}
//获取圈子类别
function showFirstLevel(){
	$.ajax({
		url:ringHost+'getDoctorRringInfo.action',
		type:"POST",
		data:{
			action:"allTypeInfo"	
		},
		success:function(result){
			if(result.mes != "操作成功" || result.theTypeInfoList == null){
				return false;
			}
			$('#circle_type').empty();
			$.each(result.theTypeInfoList,function(i,content){
				$('#circle_type').append(
					"<tr >"+
						"<td class='a'>"+
							"<input class='typr_input' readonly='readonly' disabled='disabled' type='text' value='"+content.typeSort+"' /> "+
						"</td>"+
						"<td class='b'>"+
							"<input zjid='"+content.id+"' class='typr_input' readonly='readonly' disabled='disabled' type='text' value='"+content.typeName+"' />"+
						"</td>"+
						"<td class='c'>"+
							"<select id='type_"+i+"' readonly='readonly' disabled='disabled'>"+

							"</select>"+
						"</td>"+
						
						
						"<td class='d'>"+
							"<select style='background:#ccc' id='mark_"+i+"' readonly='readonly' disabled='disabled'>"+

							"</select>"+
						"</td>"+

						"<td class='e'>"+
							"<button zjid='"+content.id+"' onclick='changeL(this)' data-tag='firstLevel' class='btn btn-mini btn-success'>修改</button>"+
						"</td>"+
						"<td class='e'>"+
							"<button data-name='"+content.typeName+"' onclick=\"getDetail('"+content.id+"',this)\" class='btn btn-mini btn-warning'>详情</button>"+
						"</td>"+
					"</tr>"		
				);
				if(content.isAvailable == 'Y'){
					$("#type_"+i).append(
						"<option selected='selected'>Y</option>"+
						"<option>N</option>"			
					);				
				}else{					
					$("#type_"+i).append(
						"<option>Y</option>"+
						"<option selected='selected'>N</option>"				
					);				
				}
				if(content.isMark == 'Y'){
					$("#mark_"+i).append(
						"<option selected='selected'>Y</option>"+
						"<option>N</option>"			
					);				
				}else{					
					$("#mark_"+i).append(
						"<option>Y</option>"+
						"<option selected='selected'>N</option>"				
					);				
				}							
			});
			$("#circle_type tr:first").find("button:last").trigger("click");

		}
	});
};
//修改、添加类别
function addOrChange(typeMark,obj){

	if(typeMark == "add"){
		var Data = {
	    "theTypeInfo.isAvailable": $("#add_type_state").val(),
	    "theTypeInfo.isMark": "N",
	    "theTypeInfo.typeName": $("#add_type_name").val().trim(),
	    "theTypeInfo.typeSort": $("#add_type_numper").val().trim(),
	    "action":"saveOrUpdateTypeInfo"	
		};
		if($("#add_type_name").val().trim()=='' || $("#add_type_numper").val().trim()==""){
			return false;

		}
	}else if(typeMark == "change"){
		var Data = {
	    "theTypeInfo.id":$(obj).attr('zjid'),
	    "theTypeInfo.isAvailable": $(obj).parent().parent().find('td').eq(2).find("select").val(),
	    "theTypeInfo.isMark": $(obj).parent().parent().find('td').eq(3).find("select").val(),
	    "theTypeInfo.typeName": $(obj).parent().parent().find('td').eq(1).find("input").val(),
	    "theTypeInfo.typeSort": $(obj).parent().parent().find('td').eq(0).find("input").val(),
	    "action":"saveOrUpdateTypeInfo"
    };
		if($(obj).parent().parent().find('td').eq(1).find("input").val()=='' || $(obj).parent().parent().find('td').eq(0).find("input").val()==""){
			return false;

		}
	}else{
		return false;
	}

$(obj).removeAttr('style').text('修改').attr('onclick','changeL(this)');
	$.ajax({
		url:ringHost+'getDoctorRringInfo.action',
		type:"POST",
		data:Data,
		async:"false",
		success:function(result){
			if(result.mes == "操作成功"){
				alert("操作成功");
				showFirstLevel();
				getAllRingCategory();
				
			}else{
				alert(result.mes);
			}
		}
	});
}
//根据id获取二级圈子列表
function getSecondCircleList(searchId){

	$.ajax({
		url:ringHost+'getDoctorRringInfo.action',
		type:"POST",
		data:{
			action:"allDoctorRingType",
			"theTypeInfo.id":searchId
		},
		async:"false",
		success:function(result){
			$("#circle_lidec").empty();
			if(result.mes!="操作成功" || result.doctorRingTypeList == null){
				return false;
			}
			$.each(result.doctorRingTypeList,function(i,content){
				var mark = '';
				if(content.typeImg != undefined || content.typeImg != null){
					mark = "style='background:transparent'";
				}
				$("#circle_lidec").append(
					"<div class='list_detail'>"+
						"<div class='canvas_img'>"+
							"<div "+mark+"  class='loadimg_div'>"+
								"<input readonly='readonly' disabled='disabled' class='loadimg_input' type='file' accept='image/*' onchange='loadImg(this)'>"+
								"<canvas class='loadimg_showcanvas' width='80' height='80'></canvas>"+
								"<input id='input_64_"+i+"' class='loadimg_base64' style='display:none'>"+
								"<img id='img_"+i+"' class='loadimg_show' src='images/ring/"+content.typeImg+"'>"+
							"</div>"+						
						"</div>"+
						
						"<div class='basictextinfo'>"+
							"<input type='text' class='secondName' readonly='readonly' disabled='disabled' value='"+content.subclassName+"'>"+
							"<input  class='last secondRemark' type='text' readonly='readonly' disabled='disabled' value='"+content.remark+"'>"+
							"<p><label>浏览数：</label><span>"+content.browseNum+"</span></p>"+
							"<p><label>订阅数：</label><span>"+content.subscribeNum+"</span></p>"+
						"</div>"+
						
						"<div class='stateocontral'>"+
							"<p class='a'>"+
								"<label>是否推荐：</label>"+
								"<select class='secondSub' id='res_"+i+"' readonly='readonly' disabled='disabled'></select>"+
							"</p>"+
							"<p class='a'>"+
								"<label>是否启用：</label>"+
								"<select class='secondStart' id='restart_"+i+"' readonly='readonly' disabled='disabled'></select>"+
							"</p>"+

						"</div>"+
						"<button data-id='"+content.id+"' data-typeId='"+content.typeId+"' onclick='changeL(this)' data-tag='secondLevel' class='btn btn-success changeC'>修改</button>"+
						"<button style='display:none' onclick='show()' class='btn btn-warning changeC'>详情</button>"+
					"</div>"+
					"<hr style='border:2px dashed #ddd'>"	
				);
				if(content.isRecommend == "Y"){
					$("#res_"+i).append(
						"<option selected='selected'>Y</option><option>N</option>"
					);
				}else{
					$("#res_"+i).append(
						"<option>Y</option><option  selected='selected'>N</option>"
					);				
				}
				if(content.isStart == "Y"){
					$("#restart_"+i).append(
						"<option selected='selected'>Y</option><option>N</option>"
					);
				}else{
					$("#restart_"+i).append(
						"<option>Y</option><option  selected='selected'>N</option>"
					);				
				}

				getImgBase64($("#img_"+i).attr("src"),$("#input_64_"+i));
			});
			
		}
	});
};
//修改二级列表
function secondAddOrChange(ty,obj){
	if(ty=="change"){
		var divObj = $(obj).parent();
		var Data = {
			"action":"saveOrUpdateDoctorRingType",//action
			"doctorRingType.id":$(obj).data("id"),//id
			"doctorRingType.subclassName":$(divObj).find(".secondName").val().trim(),//名字
			"doctorRingType.remark":$(divObj).find(".secondRemark").val().trim(),//说明
			"doctorRingType.isStart":$(divObj).find(".secondStart").val(),//是否启用
			"doctorRingType.isRecommend":$(divObj).find(".secondSub").val(),//是否推荐

			"doctorRingType.typeImg":$(divObj).find(".loadimg_base64").val().trim(),//图片base64
			"doctorRingType.typeId":$(obj).data("typeid")//一级ID
		};
	}else if(ty=="add"){
		var Data = {
			"action":"saveOrUpdateDoctorRingType",//action
			"doctorRingType.subclassName":$("#addSecondName").val().trim(),//名字
			"doctorRingType.remark":$("#addSecondDesc").val().trim(),//说明
			"doctorRingType.isStart":$("#addSecondQY").val(),//是否启用
			"doctorRingType.isRecommend":$("#addSecondTJ").val(),//是否推荐

			"doctorRingType.typeImg":$("#addSecondImg").val().trim(),//图片base64
			"doctorRingType.typeId":$(":selected",$("#ssfl")).attr("zjid")//一级ID
		};	
	}
	var mark = true;
	$.each(Data,function(i,content){

		if(content == "" || content == undefined || content == null){
			alert("数据不完整");
			mark = false;
			return false;
		}
	});
	if(!mark){
		return false;
	}
	if(ty=="change"){
		$(obj).removeAttr('style').text('修改').attr('onclick','changeL(this)');
	}
	
	$.ajax({
		url:ringHost+'getDoctorRringInfo.action',
		type:"POST",
		data:Data,
		async:"true",
		success:function(result){
			if(result.mes == "操作成功"){
				alert("操作成功");
				if(ty == "change"){
					getSecondCircleList($(obj).data("typeid"));
					
				}else if(ty=="add"){
					getSecondCircleList($(":selected",$("#ssfl")).attr("zjid"));
					$("#addSecondNew input").val("");
					$("#addSecondNew img").removeAttr("src");
					$("#addSecondNew canvas").get(0).getContext('2d').clearRect(0,0,80,80);//清空画布
				}
				
			}else{
				alert(result.mes);
			}
		}
	});
}

//获取图片的base64
function getImgBase64(imgSrc,obj){
	var img = new Image();
	img.src = imgSrc;

	img.onload=function(){
		var canvas = document.createElement("canvas");
		canvas.width = img.width;
		canvas.height = img.height;
		var ctx = canvas.getContext("2d");
		ctx.drawImage(img,0,0,img.width,img.height);
		var data = canvas.toDataURL("image/png");
		obj.val(encodeURIComponent(data));
	}
}
