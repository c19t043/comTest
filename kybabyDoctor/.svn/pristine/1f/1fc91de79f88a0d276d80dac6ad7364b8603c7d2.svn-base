$(function () {
    $.ajax({
        type : 'post',
        async: false ,
        url : host+'accountManage.action',
        data : {action:"getCashMoney"},
        success : function(result) {
            $('#balanceNum').attr('placeholder','可提现金额：'+parseFloat(result.cashMoney).toFixed(2));
        }
    });

});
function takeBalance(){
    var val=$("#balanceNum").val();
	if($("#balanceNum").val()==""||$("#balanceNum").val()==null){
		ale("请输入提现金额");
        return false;
	}
	else{
        if(/^[0-9]+(.[0-9]{1,3})?$/.test(val)){
            if(val<100){
                ale('最小提现金额为100元');
                return false;
            }
            $.ajax({
                type : 'post',
                async: false ,
                url : host+'accountManage.action',
                data : {action:"takeBalance",takeBalance:$("#balanceNum").val()},
                success : function(result) {
                    //if(result.mes=="余额小于50"){
                    //	ale(result.mes);
                    //}
                    //if(result.mes=="余额不足"){
                    //	ale(result.mes);
                    //}
                    if(result.mes=="请登录"){
                        window.location.href="login.html";
                    }
                    else if(result.mes=="成功"){
                        window.location.href="balance.html";
                    } else if(result.mes!='成功'){
                        ale(result.mes);
                        return false;
                    }
                }
            });

            return false;
        }else{
            ale('请输入非负数字');
        }
	}
}