var message_id = '';
var num = '',t_tal='';
$(function () {
    $('#chooseWay>div').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
        $('#chooseWay>div img').attr('src','images/images_family_doctor/select.png');
        $(this).find('img').attr('src','images/images_family_doctor/true.png');
    });
});
function payInfo(org){//明细
    //var id = $(org).parents(".pay_cost_list").attr("data-id");
    //window.location.href='hospital_pay_detail.html?'+id;
}
function goPay(number,tal,m_id){//去支付
    //tal=$(div).prev('.payMoney').children('span').text();
    t_tal = tal;
    $('#total').html(total).attr('data-id',m_id);
    $('#payMethodCover').show();
    message_id = m_id;
    num = number;
}
function surePay(){//去支付
    var payMethod=$('#chooseWay>.selected>p').html();
    var id=$('#total').attr('data-id');
    if(payMethod=='微信支付'){
        paycall("kybaby", num, '', Math.round(t_tal*100));
    }
}
function whetherToPay(res){
    if(res.err_msg == "get_brand_wcpay_request:ok" || res.err_msg == "get_brand_wcpay_request：ok") {
        //ale("wei xin zhi fu 成功,开始订单处理");
        //ale("支付成功的订单编号"+orderNum);
        //支付成功，将订单号反馈，后台根据orderNum参数更新对应的状态,然后跳转到支付成功页面
        payMoney('微信支付');
    }else{
        ale('支付失败');
        //window.location.href="../wx/notifyUrl.jsp?"+orderNum+"&fail";
    }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
}
//付款
function payMoney(method){
    $.ajax({
        type: 'post',
        async: false,
        cache: true,
        url: spInterfaceService + 'executeClinicCharge.action',
        data: {
            spQueryMessageID:message_id,
            "organSetMeatOrder.id":num,
            //"organSetMeatOrder.payMethod":method,
            //"organSetMeatOrder.useRemainBalance":useBalanceAll,
            //"organSetMeatOrder.orderStatus":'付款成功'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录');
                window.location.href="login.html";
            }
            else if (result.mes == '成功') {
                $.ajax({
                    type:'post',
                    url:spInterfaceService+'executeClinicCharge.action',
                    cache:false,
                    async:true,
                    data:{
                        spQueryMessageID:message_id,
                    },
                    success:function(result){
                        hf_loading(false);
                        if(result.mess=='收费成功'){
                            ale('支付成功');
                        }else{
                            ale("支付失败");
                        }
                    }
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
}


