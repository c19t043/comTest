/**
 * Created by vinny on 2015/9/25.
 */
$(function(){
	$(".bank_list").hide();
	$(".addcard_box").find(".addbank_name").click(function(){
		$(".bank_list").toggle();
	});
	$(".bank_list li").click(function(){
		$(".addbank_name").empty();
		var i = $(this.children);
		i.clone().appendTo($(".addbank_name"));
		$(".bank_list").hide();
	})
})