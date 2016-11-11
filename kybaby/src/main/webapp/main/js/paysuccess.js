$(function(){
	var pay_amount=decodeURIComponent(window.location.search).substring(1);
	$("#pay_amount").text(pay_amount/100);
	$("#to_order").click(function(){
		window.location.href="myAppointment.html";
	});
	$("#to_head").click(function(){
		window.location.href="main.html";
	});
    var isSession=sessionStorage.getItem('session');
    if(isSession=='yes'){
        $("#to_order,#to_head").css({display:'none'});
        $('#container').append('<div class="login_button" onclick="window.location.href=\''+hostMain+'yjh/yjh_doctor/index.html\'">返回儿科名医</div>');
    }
});