function hf_ueditor(editorid,textareaid){/*------PC端富文本编辑器----第一个传编辑按钮id，第二个传隐藏文本标签id--*/
	if(editorid == undefined || textareaid == undefined){
		alert('富文本编辑器传参错误');
		return false;
	}
	$j('body').append(
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
	$j('#ueditor_confirm').click(function(){
		$j('#hf_edit,#hf_mask').hide();
		$j('#'+textareaid).html(ue.getContent());
		ue.setContent('');
	});
	//富文本取消点击
	$j('#ueditor_cancel').click(function(){
		$j('#hf_edit,#hf_mask').hide();
	});
	//富文本编辑点击
	$j('#'+editorid).click(function(){
		$j('#hf_edit,#hf_mask').show();

		ue.setContent($j('#'+textareaid).text());
	});
	$j('#hf_mask').css({
		'width':$j(document).width(),
		'height':$j(document).height(),
		'backgroundColor':'#fff',
		'position':'absolute',
		'top':0,
		'left':0,
		'opacity':0.7,
		'zIndex':99,
		'display':'none'
	});
	$j('#hf_edit').css({
		'width':552,
		'height':500,
		'backgroundColor':'#fff',
		'position':'fixed',
		'top':30,
		'left':$j(document).width()/2-275,
		'zIndex':100,
		'border':'1px solid rgb(74, 139, 194)',
		'display':'none'
	});
}