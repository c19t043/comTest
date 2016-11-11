function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
	$('body').append(
	"<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
		"<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
			"<img id='hf_loading_img' src='hf/icon/hf_autoplay.png' style='margin-bottom:10px;' />"+
			"<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:16px;text-align:left;color:#868686;font-weight:bold;'>努力加载中。</span>"+	
		"</div>"+
	"</div>");
	if(status == false){
		document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
		return false;
	}
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
		if(spanobj.innerHTML == '努力加载中。'){
			spanobj.innerHTML = '努力加载中。。';
		}else if(spanobj.innerHTML == '努力加载中。。'){
			spanobj.innerHTML = '努力加载中。。。';
		}else if(spanobj.innerHTML == '努力加载中。。。'){
			spanobj.innerHTML = '努力加载中。';
		}
	}
	setInterval(function(){
		transword();
	},300);
	setInterval(function(){
		trans();
	},20);
}
function hf_ueditor(editorid,textareaid){/*------PC端富文本编辑器----第一个传编辑按钮id，第二个传隐藏文本标签id--*/
	if(editorid == undefined || textareaid == undefined){
		alert('富文本编辑器传参错误');
		return false;
	}
	$('body').append(
	"<div id='hf_mask'></div>"+
	"<div id='hf_edit'>"+
		"<div class='ueditor ueditor_a' id='ueditor_cancel'>取消</div>"+
		"<div class='ueditor ueditor_b' id='ueditor_confirm'>确认</div>"+
		"<div id='hf_editor'></div>"+
	"</div>"
	);
	var ue = UE.getEditor('hf_editor',{
		zIndex:998, 
		initialFrameWidth:550,
		initialFrameHeight:400,
		maximumWords:1000,
	});
	//富文本确认点击
	$('#ueditor_confirm').click(function(){
		$('#hf_edit,#hf_mask').hide();
		$('#'+textareaid).html(ue.getContent());
		ue.setContent('');
	});
	//富文本取消点击
	$('#ueditor_cancel').click(function(){
		$('#hf_edit,#hf_mask').hide();
	});
	//富文本编辑点击
	$('#'+editorid).click(function(){
		$('#hf_edit,#hf_mask').show();

		ue.setContent($('#'+textareaid).text());
	});
	$('#hf_mask').css({
		'width':$(document).width(),
		'height':$(document).height(),
		'backgroundColor':'#fff',
		'position':'absolute',
		'top':0,
		'left':0,
		'opacity':0.7,
		'zIndex':99,
		'display':'none'
	});
	$('#hf_edit').css({
		'width':552,
		'height':500,
		'backgroundColor':'#fff',
		'position':'fixed',
		'top':30,
		'left':$(document).width()/2-275,
		'zIndex':100,
		'border':'1px solid rgb(74, 139, 194)',
		'display':'none'
	});
}

function hf_ueditor_plus(editorid,textareaid,rand){//只在后台的页面管理里面使用
	if(editorid == undefined || textareaid == undefined){
		alert('富文本编辑器传参错误');
		return false;
	}
	$('body').append(
	"<div id='hf_mask"+rand+"'></div>"+
	"<div id='hf_edit"+rand+"'>"+
		"<div class='ueditor ueditor_a' id='ueditor_cancel"+rand+"'>取消</div>"+
		"<div class='ueditor ueditor_b' id='ueditor_confirm"+rand+"'>确认</div>"+
		"<div id='hf_editor"+rand+"'></div>"+
	"</div>"
	);
	var ue = UE.getEditor("hf_editor"+rand,{
		zIndex:998,
		initialFrameWidth:550,
		initialFrameHeight:400,
		maximumWords:1000,
	});
	//富文本确认点击
	$('#ueditor_confirm'+rand).click(function(){
		$("#hf_edit"+rand+",#hf_mask"+rand+"").hide();
		$('#'+textareaid).html(ue.getContent());
		ue.setContent('');
	});
	//富文本取消点击
	$("#ueditor_cancel"+rand).click(function(){
		$("#hf_edit"+rand+",#hf_mask"+rand+"").hide();
	});
	//富文本编辑点击
	$('#'+editorid).click(function(){
		$("#hf_edit"+rand+",#hf_mask"+rand+"").show();
		ue.setContent($('#'+textareaid).val());
	});
	$("#hf_mask"+rand).css({
		'width':$(document).width(),
		'height':$(document).height(),
		'backgroundColor':'#fff',
		'position':'absolute',
		'top':0,
		'left':0,
		'opacity':0.7,
		'zIndex':99,
		'display':'none'
	});
	$("#hf_edit"+rand).css({
		'width':552,
		'height':500,
		'backgroundColor':'#fff',
		'position':'fixed',
		'top':30,
		'left':$(document).width()/2-275,
		'zIndex':100,
		'border':'1px solid rgb(74, 139, 194)',
		'display':'none'
	});
}

function hf_showbox(content){
	$('body').append(
	"<div id='hf_maskcontent'></div>"+
	"<div id='hf_showcontent'>"+
		"<span class='close'></span>"+
		"<h4>查看</h4>"+
		"<section>"+content+"</section>"+
	"</div>"
	);
	$('#hf_maskcontent').css({
		'width':$(document).width(),
		'height':$(document).height(),
		'backgroundColor':'#fff',
		'position':'absolute',
		'top':0,
		'left':0,
		'opacity':0.7,
		'zIndex':99
	});
	$('#hf_showcontent').css({
  	'width': '498px',
  	'height': '398px',
  	'position':'fixed',
  	'top': '40px',
  	'background':'#fff',
  	'zIndex':100,
  	'border':'1px solid #50c1e9',
  	'left':$(document).width()/2-250,
	});
	$('#hf_showcontent .close').css({
		'position':'absolute',
		'display':'block',
		'width':'36px',
		'height':'36px',
		'background':'rgb(94, 94, 94)',
  	'left': '499px',
  	'top': '-1px',
	  'background':'#000 url(hf/icon/hf_closecontent.png) no-repeat left',
	  'backgroundSize':'36px',
	  'cursor':'pointer'
	}).click(function(){
		$('#hf_showcontent,#hf_maskcontent').remove();
	});
	$('#hf_showcontent h4').css({
	  'height':'35px',
	  'lineHeight':'35px',
	  'textIndent':'20px',
	  'color':'#fff',
	  'fontWeight':'bold',
	  'background':'#50c1e9'
	  
	});
	$('#hf_showcontent section').css({
		'width':'458px',
  	'height':'342px',
  	'overflow':'auto',
  	'margin':'20px auto 0 auto',
  	'lineHeight':'200%'
	});
}