/*全局变量初始化*/
var view_w = $(window).width();//获取窗口宽度
var view_h = $(window).height();//获取窗口高度
var tit_name = document.title;//获取title名字

$(function(){
	//页面初始设置高度
	reset_con_height();
	//通用底部弹窗宽度设置
	$('.bottom_div').css('width',function(){
		return $('.table_h').css('width');
	});
	//通用底部弹窗取消按钮click
	$('#button_cancel_click').click(function(){
		$('#bottom_div').slideUp(500);
	});
	//加载头部文件
	$('#headcon').load('template/head.html');
	//加载左侧栏文件
	$('#leftcon').load('template/left.html',function(){
		getAllFunctionListAndParent();//回调函数获取左侧功能列表
		$('#con_tit,#con_meu .second').text(tit_name);//二级功能名字赋值
		var second_obj = $('#leftcon li').filter(function(){//被匹配到的二级功能标签jquery对象
			return $(this).text() == tit_name;
		});
		var first_name = second_obj.parent().parent().prev().find('span').eq(1).text();//该对象的一级模块名字
		second_obj.parent().parent().show();
		second_obj.css('backgroundColor','#4a8bc2');
		$('#con_meu .first').text(first_name);//一级模块名字赋值
	});
	

});
window.onresize = function(){
	//重置页面高度
	view_w = $(window).width();
	view_h = $(window).height();
	reset_con_height();
}


/*---------函数集合---------*/

//设置内容高度
function reset_con_height(){
	$('#leftcon').css('height',function(){
		return view_h - 40;
	});
	$('#rightcon').css('height',function(){
		return view_h - 40;
	});
}
//左侧栏点击函数
function left_meu_cli(obj){
	$(obj).next().slideToggle();
}
//内容点击高亮
function click_light(obj){
	$('.table_content').find('tr').css('backgroundColor','');
	$(obj).css('background','rgb(204,232,255)');
}
/*-------分页处理函数集合----------*/
//分页处理
function reset_con_page(num,x){
	if(num == 'default' || num == '' || num == undefined){
		num = $('#table_content').find('tr').length;
	}
	var page_num = Math.ceil(num / 12);//需要多少页
	$('#con_num').text(num);	//		数据个数赋值
	$('#page_num').text(Math.ceil($('.table_content').find('tr').length/12));
	
//	for(var a=0;a < $('.table_content').find('tr').length;a++){
//		if(a < 12){
//			$('.table_content').find('tr').eq(a).show();
//		}else{
//			$('.table_content').find('tr').eq(a).show();
//		}
//	}
	
	if(x == 'prev'){
		x = $('#now_page_num').text() - 1;
		if(x <= 1){
			x = 1;
		}
	}
	if(x == 'next'){
		x = parseInt($('#now_page_num').text()) + 1;
		if(x >= page_num){
			x = page_num;
		}	
	}
	

	if(x == '' || x == null || isNaN(x)){
		x = 1;
	}else if(x >= page_num){
		x = page_num;
	}
	
	for(var i=0; i<num ;i++){
		if(i >= (12*x-12) && i <= (12*x-1)){
			$('.table_content').find('tr').eq(i).show();
		}else{
			$('.table_content').find('tr').eq(i).hide();
		}
	}
}
//分页首页被点击
function page_click(num,x){
	if(x == 1){											//首页被点击
		$('#now_page_num').text('1');
		reset_con_page(num,x);
	}else if(x == 'prev'){					//上一页被点击
		x = $('#now_page_num').text() - 1;
		if(x <= 1){
			x = 1;
			$('#now_page_num').text(x);
		}else{
			x = $('#now_page_num').text() - 1;
			$('#now_page_num').text(x);
		}
		reset_con_page(num,x);
	}else if(x == 'next'){
		x = parseInt($('#now_page_num').text()) + 1;
		var page_num_all = Math.ceil($('.table_content').find('tr').length / 12);
		if(x >= page_num_all){
			x = page_num_all;
			$('#now_page_num').text(x);
		}else{
			x = parseInt($('#now_page_num').text()) + 1;
			$('#now_page_num').text(x);
		}
		reset_con_page(num,x);
	}else if(x == 'end'){
		$('#now_page_num').text(Math.ceil($('.table_content').find('tr').length / 12));
		reset_con_page(num,Math.ceil($('.table_content').find('tr').length / 12));
	}
}
//GO点击触发函数
function page_click_go(){
	var a = $.trim($('#go_page').val());
	if(isNaN(a) || parseInt(a) != a || a <=0){
		a = 1;
	}else if(a >= parseInt($('#page_num').text())){
		a = parseInt($('#page_num').text());
	}

	$('#go_page').val(a);
	$('#now_page_num').text(a);
	reset_con_page('default',a);
}


