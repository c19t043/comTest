$(function () {
    $('#chooseWay>div').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
        $('#chooseWay>div img').attr('src','images/images_family_doctor/select.png');
        $(this).find('img').attr('src','images/images_family_doctor/true.png');
    });
    //getAppointment();
    //getOrgBabyPreList();
});
function getOrgBabyPreList(){//儿保
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'orgBoManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserChildcareAppointmentInfoList"
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var list=result.userChildcareAppointmentInfoList;
                if(list!=null){
                    var html='';
                    if(list.length>0){
                        for(var i= 0,len=list.length;i<len;i++){
                            var organServicePlaceSet='';
                            if(list[i].organChildcareOpenResources.isMoney=='N'){
                                organServicePlaceSet='('+list[i].organServicePlaceSet.windowName+')';
                            }
                            var totalPrice='';
                            var bianma='';
                            var payMoney;
                            if(list[i].organChildcareOpenResources.isMoney=='Y'){
                                totalPrice=list[i].totalPrice;
                            }
                            var stat='';
                            var doctor=result.userChildcareAppointmentInfoList[i].doctorInfo;
                            console.log(i);
                            console.log(doctor);
                            if(list[i].status=='已预约'){
                                stat='onclick="window.location.href=\'orgservice_babymessage.html?'+list[i].id+'\'" ';
                            }
                            var timeDivisionNeed='';
                            if(list[i].organChildcareOpenResourcesDatail!=null){
                                if(list[i].organChildcareOpenResources.timeDivisionNeed=='时间点'){
                                    timeDivisionNeed=list[i].organChildcareOpenResourcesDatail.segment;
                                }else{
                                    timeDivisionNeed=list[i].organChildcareOpenResourcesDatail.openStartTime+'~'+list[i].organChildcareOpenResourcesDatail.openEndTime;
                                }
                            }
                            html+='<div class="order_list">' +
                            '<div class="doctorInfo"> ' +
                            '<div class="img"> ' +
                            '<img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'" alt=""/> ' +
                            '</div> ' +
                            '<div class="con"> ' +
                            '<p><span>'+doctor.doctorName+'</span>'+' '+doctor.department+' '+doctor.doctorTitle+'</p> ' +
                            '<p>'+list[i].clinicAddress+'</p> ' +
                            '<p>'+list[i].orderTime+'</p> ' +
                            '</div> ' +
                            '<div class="money"> ' +
                            '&yen;<span>'+totalPrice+'</span> ' +
                            '</div> ' +
                            '</div> ' +
                            '<p class="gray_s"></p> ' +
                            '<div class="userInfo"> ' +
                            '<p>就诊人：土豆豆</p> ' +
                            '<p>预约时间：'+list[i].appointmentDate+'</p> ' +
                            '<p>建议就诊时间：'+timeDivisionNeed+'</p> ' +
                            '</div> ' +
                            '<p class="gray_s"></p> ' +
                            '<div class="payMoney"> ' +
                            payMoney+
                            '</div> ' +
                            '</div>';
                        }
                        $('#erbao').append(html);
                    }
                }
            }
        },
        error: function () {
            layer();
        }
    });
}

function getAppointment(){//儿科
    var todayTime;
    var todayDate;
    $.ajax({
        type:'post',
        url:clinicHost+'clinicOrder.action',
        cache:false,
        async:false,
        data:{action:"getClinicOrderList"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list=result.orderInfoClinicList;
                if(list!=null){
                    $.ajax({
                        type:'post',
                        url:clinicHost+'clinicBooking.action',
                        cache:false,
                        async:false,
                        data:{action:"getCurrentTime"},
                        success:function(result){
                            if (result.mes == '请登录') {
                                ale('请登录', '24px');
                                window.location.href = "login.html";
                            }
                            else if (result.mes == "成功") {
                                todayTime=result.currentTime.replace('-','').replace('-','').replace(' ','').replace(':','').replace(':','');
                                todayDate=result.currentTime.split(' ')[0].replace('-','').replace('-','');
                            }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            layer();
                        }
                    });
                    var html='';
                    for(var i= 0,len=list.length;i<len;i++){
                        var doctor=list[i].doctorInfo;
                        var lastHtml='';
                        var orderTime;
                        var orderDate;
                        var payMoney;
                        if(list[i].orderStatus=='未付款'){
                            if(list[i].isPlus=='Y'){
                                orderDate=list[i].appointmentDate.replace('-','').replace('-','');
                                if(orderDate>=todayDate){
                                    var date=list[i].appointmentDate.split('-')[2];
                                    payMoney='<p style="width: 50%"></p> ' +
                                    '<p class="pay" style="width: 35%" onclick="goPay(this,'+list[i].id+')">付款</p> ';
                                }else{
                                    payMoney='<p style="width: 50%">已过期</p> '
                                }
                            }else{
                                orderTime=list[i].appointmentDate.replace('-','').replace('-','');
                                orderTime+=list[i].appointmentBeganTime.replace(':','')+'00';
                                if(orderTime>todayTime){
                                    var date=list[i].appointmentDate.split('-')[2];
                                    payMoney='<p style="width: 50%"></p> ' +
                                    '<p class="pay" style="width: 35%" onclick="goPay(this,'+list[i].id+')">付款</p> ';
                                }else{
                                    payMoney='<p style="width: 50%">已过期</p> '
                                }
                            }
                            //lastHtml='<div class="pjdoctor1"><a href="tel://13541280713">号码</a></div>';
                        }else if(list[i].orderStatus=='已完成'){
                            payMoney='<p style="width: 50%">已完成</p>';
                        }else if(list[i].orderStatus=='已预约'){
                            payMoney='<p style="width: 50%">已付款</p> ' +
                            '<p class="pay" style="width: 35%" data-doctor-id="'+doctor.id+'" onclick="cancelPay(this,'+list[i].id+')">取消订单</p> ';
                        }else if(list[i].orderStatus=='用户取消'){
                            payMoney='<p style="width: 50%">已取消</p>';
                        }
                        html+='<div class="order_list">' +
                        '<div class="doctorInfo"> ' +
                        '<div class="img"> ' +
                        '<img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'" alt=""/> ' +
                        '</div> ' +
                        '<div class="con"> ' +
                        '<p><span>'+doctor.doctorName+'</span>'+' '+doctor.department+' '+doctor.doctorTitle+'</p> ' +
                        '<p>'+list[i].clinicAddress+'</p> ' +
                        '<p>'+list[i].orderTime+'</p> ' +
                        '</div> ' +
                        '<div class="money"> ' +
                        '&yen;<span>'+list[i].totalPrice+'</span> ' +
                        '</div> ' +
                        '</div> ' +
                        '<p class="gray_s"></p> ' +
                        '<div class="userInfo"> ' +
                        '<p>就诊人：土豆豆</p> ' +
                        '<p>预约时间：'+list[i].appointmentDate+'</p> ' +
                        '<p>建议就诊时间：'+list[i].appointmentBeganTime+'</p> ' +
                        '</div> ' +
                        '<p class="gray_s"></p> ' +
                        '<div class="payMoney"> ' +
                        payMoney+
                        '</div> ' +
                        '</div>';
//                            $('#doctor_list').append('<div class="listdetail"><div class="doctor_head"><img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'"></div> ' +
//                            '<div class="basicinfo"> <p class="detail_name">'+doctor.doctorName+'&nbsp;&nbsp;</p> ' +
//                            '<p class="detail_time">'+list[i].appointmentDate+' '+list[i].appointmentBeganTime+'-'+list[i].appointmentEndTime+'</p>' +
//                            '<p class="detail_address">'+list[i].clinicAddress+'</p><div style="overflow: hidden"><span style="color: #F86B6B;font-size: 13px">订单金额￥'+list[i].totalPrice+'</span><div class="pjdoctor" data-order="'+list[i].orderStatus+'">'+list[i].orderStatus+'</div></div>' +
//                            ' </div> '+lastHtml+' </div> ');
                    }
                    $('#orderList').html(html);
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
}
function cancelPay(div,id){//取消订单
    var answer=confirm('您是否确定取消？');
    if(answer==true){
        var doctorId=$(div).attr('data-doctor-id');
        $.ajax({
            type: 'post',
            url: clinicHost + 'clinicOrder.action',
            cache: false,
            async: false,
            data: {
                action: "saveOrUpdateClinicOrder",
                "orderInfoClinic.orderStatus": "用户取消",//这个固定的
                "orderInfoClinic.id": id,
                "doctorInfo.id": doctorId
            },
            success: function (result) {
                if(result.mes=='时间过期'){
                    ale('取消失败，您的订单已超过系统有效取消时间');
                }else{
                    window.location.reload();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer();
            }
        });
    }
}
function goPay(div,id){//去支付
    window.location.href='hospital_pay_order.html?p='+id;
    //var total=$(div).parentsUntil('#orderList').find('.money').children('span').text();
    //$('#total').html(total).attr('data-id',id);
    //$('#payMethodCover').show();
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

