$(function () {
    $('#chooseWay>div').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
        $('#chooseWay>div img').attr('src','images/images_family_doctor/select.png');
        $(this).find('img').attr('src','images/images_family_doctor/true.png');
    });
});
function payInfo(id){//明细
    window.location.href='hospital_pay_detail.html';
}
function goPay(div){//去支付
    var total=$(div).prev('.payMoney').children('span').text();
    $('#total').html(total).attr('data-id',3);
    $('#payMethodCover').show();
}
function surePay(){//去支付
    var payMethod=$('#chooseWay>.selected>p').html();
    var total=$('#total').html();
    var id=$('#total').attr('data-id');
    if(payMethod=='微信支付'){
        paycall("kybaby", organSetMeatOrderId, orderNum, Math.round(total*100));
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
        url: hostOrgbusiness + 'orgSetMealManager.action',
        data: {
            action: "handleOrganSetMealOrder",
            "organSetMeatOrder.id":organSetMeatOrderId,
            "organSetMeatOrder.payMethod":method,
            "organSetMeatOrder.useRemainBalance":useBalanceAll,
            "organSetMeatOrder.orderStatus":'付款成功'
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录');
                window.location.href="login.html";
            }
            else if (result.mes == '成功') {
                ale('支付成功');
                setTimeout(function () {
                    window.location.reload();
                },1000);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
}

