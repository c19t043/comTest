$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:30%'>序列</th>"+
			"<th style='width:30%'>页面模块</th>"+
			"<th style='width:40%'></th>"+
		"</tr>"	
	);
	hf_ueditor_plus('userProtocol_edit','userProtocol_textarea','userProtocol');//用户协议
	hf_ueditor_plus('healthPlan_edit','healthPlan_textarea','healthPlan');//健康计划管理方法
	hf_ueditor_plus('aboutUs_edit','aboutUs_textarea','aboutUs');//用户协议
	
	hf_ueditor_plus('banner1_edit','banner1_textarea','banner1');//用户协议
	hf_ueditor_plus('banner2_edit','banner2_textarea','banner2');//用户协议
	hf_ueditor_plus('banner3_edit','banner3_textarea','banner3');//用户协议
	hf_ueditor_plus('banner4_edit','banner4_textarea','banner4');//用户协议
	hf_ueditor_plus('doctorProtocol_edit','doctorProtocol_textarea','doctorProtocol');//用户协议
	show_tableinfo();
});

function show_tableinfo(){
	//获取用户协议
	(function(){
		$.ajax({
			'url':'bannerAndPageHandle.action',
			'type':'POST',
			'data':{
				'action':'getContent',
				'modular':'userProtocol'
			},
			success:function(result){
				if(result.mes == '成功'){
					$('#userProtocol_textarea').html(result.returnBodyConteant);
				}
			}
		});
	})();
	
	//获取健康计划管理方法
	(function(){
		$.ajax({
			'url':'bannerAndPageHandle.action',
			'type':'POST',
			'data':{
				'action':'getContent',
				'modular':'healthPlanManage'
			},
			success:function(result){
				if(result.mes == '成功'){
					$('#healthPlan_textarea').html(result.returnBodyConteant);
					$('#healthPlan_img').attr('src','images/banner/'+result.healthManageImageName);
				}
			}
		});
	})();
	
	//获取关于我们
	(function(){
		$.ajax({
		'url':'bannerAndPageHandle.action',
		'type':'POST',
		'data':{
			'action':'getContent',
			'modular':'aboutUs'
		},
		success:function(result){
			if(result.mes == '成功'){
				$('#aboutUs_textarea').html(result.returnBodyConteant);
			}
		}
		});
	})();
	
	//获取轮播
	(function(){
		$.ajax({
			'url':'bannerAndPageHandle.action',
			'type':'POST',
			'data':{
				'action':'getAllBanner'
			},
			success:function(result){
				if(result.mes != '成功'){
					return false;
				}else if(result.allBanner == null){
					return false;
				}
				for(var i=0;i < result.allBanner.length;i++){
					$('.banner_img').eq(i).attr('src','images/banner/'+result.allBanner[i].name);
					$('.banner_textarea').eq(i).html((result.bannerHtmlStr)[i]);
				}
				
			}
		});
	})();
	
	//获取医生协议
	(function(){
		$.ajax({
			'url':'bannerAndPageHandle.action',
			'type':'POST',
			'data':{
				'action':'getContent',
				'modular':'doctorProtocol'
			},
			success:function(result){
				if(result.mes == '成功'){
					$('#doctorProtocol_textarea').html(result.returnBodyConteant);
				}
			}
		});
	})();
	
	//获取引导页
	(function(){
		$.ajax({
			'url':'bannerAndPageHandle.action',
			'type':'POST',
			'data':{
				'action':'getAllGuidePage'
			},
			success:function(result){
				if(result.mes != '成功'){
					return false;
				}else if(result.allGuidepage == null){
					return false;
				}
				for(var i=0;i < result.allGuidepage.length;i++){
					$('.guide_img').eq(i).attr('src','images/guide/'+result.allGuidepage[i].pageName);
				}
				
			}
		});
	})();
}

//读取信息
function read_info(){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
}
function read_info_test(num){
	$('.hf_test').hide();
	$('.hf_test').eq(num-1).show();
	$('#bottom_div').slideDown(500);
	$('#button_add_click').hide();
	$('#button_update_click').show().attr('mark',function(){
		return $('.hf_test:visible').attr('mark');
	});

}
function contral_info(obj){
	if($(obj).attr('mark') == 1 || $(obj).attr('mark') == 3||$(obj).attr('mark')==5){
		var x,y;
		if($(obj).attr('mark') == 1 ){
			x = 'userProtocol';
			y = $('#userProtocol_textarea').html();
			y = y.replace(/&lt;/g,'<').replace(/&gt;/g,'>');
		}else if($(obj).attr('mark') == 3){
			x = 'aboutUs';
			y = $('#aboutUs_textarea').html();		
			y = y.replace(/&lt;/g,'<').replace(/&gt;/g,'>');
		}
		else if($(obj).attr('mark') == 5){
			x = 'doctorProtocol';
			y = $('#doctorProtocol_textarea').html();		
			y = y.replace(/&lt;/g,'<').replace(/&gt;/g,'>');
		}
		$.ajax({
			'url':'bannerAndPageHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'modular':x,
				'comeInBodyContent':y
			},
			success:function(result){
				if(result.mes == '成功'){
					alert('修改成功');
					$('#bottom_div').slideUp(500);
					show_tableinfo();
				}
			}
		});
	}else if($(obj).attr('mark') == 2){
		$('#health_plan_manage_method').get(0).submit();
	}else if($(obj).attr('mark') == 4){
	
		$('#banner_manage_method').get(0).submit();
	}
	else if($(obj).attr('mark') == 6){
	
		$('#guidePage_manage_method').get(0).submit();
    }
}
//-----------------------------------
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