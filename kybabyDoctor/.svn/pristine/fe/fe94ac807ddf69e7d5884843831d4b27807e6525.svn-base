$(function(){
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'accountManage.action',
		data : {action:"balance"},
		success : function(result) {
			if(result.mes=="成功"){
				$("#balance").text(result.balance);
				if(result.amountDetail!=null){
					for(var i=0;i<result.amountDetail.length;i++){
						//ale(result.amountDetail[i].id);
						$("#detail").append("<div class='balance_list'>"+
                            "<p class='project'>"+result.amountDetail[i].accountDesc+"</p>"+
                            "<p class='date'>"+result.amountDetail[i].updateTime+"</p>"+
								"<p class='sum'>"+result.amountDetail[i].type+result.amountDetail[i].amount+"元</p>"+
								"</div>"
								);
					}
				}
			}
			if(result.mes=="请登录"){
				window.location.href="login.html";
			}
		}
	});
});
function tixian(){
    var time='';
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'doctorClinic.action',
        cache:false,
        async:false,
        data:{action : "getCurrentTime"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                //alert(workStatus);
                time=result.currentTime.split(' ')[0].split('-')[2];
                if(parseInt(time)<5 || parseInt(time)>10){
                    ale('请在每月5-10号申请提现');
                    return false;
                }else{
                    window.location.href='takebalance.html';
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });


}