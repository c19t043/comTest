/**
 * Created by lijingwei on 2016/7/12.
 */

$(function () {
    $("#container").click(function(){
        $("#top_layer").addClass("hide");
    })
    $(".select_tab").click(function(){
        $(this).addClass("emphasis");
        $(this).siblings().removeClass("emphasis");
        //$("#last_item").addClass("order_item_selected").siblings().removeClass("order_item_selected");
        //var txt = $(this).text();
        //if(txt == '全部'){
        //    $('#his_order .order_div').show();
        //}else if(txt == '已取消'){
        //    $('#his_order .order_div').hide();
        //    for(var i= 0,len=dataOrder.length;i<len;i++){
        //        $('#his_order .order_div[data-order=用户取消]').show();
        //    }
        //}else if(txt == '已预约'){
        //    $('#his_order .order_div').hide();
        //    for(var i= 0,len=dataOrder.length;i<len;i++){
        //        $('#his_order .order_div[data-order=已预约]').show();
        //        $('#his_order .order_div[data-order=已会面]').show();
        //    }
        //}else if(txt=='未付款'){
        //    $('#his_order .order_div').hide();
        //    for(var i= 0,len=dataOrder.length;i<len;i++){
        //        $('#his_order .order_div[data-order=未付款]').show();
        //    }
        //}else if(txt=='已完成'){
        //    $('#his_order .order_div').hide();
        //    for(var i= 0,len=dataOrder.length;i<len;i++){
        //        $('#his_order .order_div[data-order=已完成]').show();
        //        $('#his_order .order_div[data-order=已评价]').show();
        //    }
        //}
        var text = $(".order_item.order_item_selected").text().substring(0,4);//筛选选中值
        var title=$(this).text();//订单状态值
        var dataOrder=$('.order_div');//订单列表单个订单
        if(text=="普通用户"){//所有订单呢
            if(title == '全部'){
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title == '已取消'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=用户取消]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title == '已预约'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已预约]').show();
                    $('.order_div[data-order=已会面]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title=='未付款'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=未付款]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title=='已完成'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已完成]').show();
                    $('.order_div[data-order=已评价]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }
        }
        else if(text=="签约用户"){//所有订单呢
            if(title == '全部'){
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title == '已取消'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=用户取消]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title == '已预约'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已预约]').show();
                    $('.order_div[data-order=已会面]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title=='未付款'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=未付款]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title=='已完成'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已完成]').show();
                    $('.order_div[data-order=已评价]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }
        }
        else{
            if(title == '全部'){
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div').show();
                }
            }else if(title == '已取消'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=用户取消]').show();
                }
            }else if(title == '已预约'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已预约]').show();
                    $('.order_div[data-order=已会面]').show();
                }
            }else if(title=='未付款'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=未付款]').show();
                }
            }else if(title=='已完成'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已完成]').show();
                    $('.order_div[data-order=已评价]').show();
                }
            }
        }
    })
    //$("#pediatric_order .select_tab").click(function(){
    //    $(this).addClass("emphasis");
    //    $(this).siblings().removeClass("emphasis");
    //    //$("#last_item").addClass("order_item_selected").siblings().removeClass("order_item_selected");
    //    var txt = $(this).text();
    //    var dataOrder=$('#pediatric_order .order_div');
    //    if(txt == '全部'){
    //        $('#pediatric_order .order_div').show();
    //    }else if(txt == '已取消'){
    //        $('#pediatric_order .order_div').hide();
    //        for(var i= 0,len=dataOrder.length;i<len;i++){
    //            $('#pediatric_order .order_div[data-order=用户取消]').show();
    //        }
    //    }else if(txt == '已预约'){
    //        $('#pediatric_order .order_div').hide();
    //        for(var i= 0,len=dataOrder.length;i<len;i++){
    //            $('#pediatric_order .order_div[data-order=已预约]').show();
    //            $('#pediatric_order .order_div[data-order=已会面]').show();
    //        }
    //    }else if(txt=='未付款'){
    //        $('#pediatric_order .order_div').hide();
    //        for(var i= 0,len=dataOrder.length;i<len;i++){
    //            $('#pediatric_order .order_div[data-order=未付款]').show();
    //        }
    //    }else if(txt=='已完成'){
    //        $('#pediatric_order .order_div').hide();
    //        for(var i= 0,len=dataOrder.length;i<len;i++){
    //            $('#pediatric_order .order_div[data-order=已完成]').show();
    //            $('#pediatric_order .order_div[data-order=已评价]').show();
    //        }
    //    }
    //})
    $(".header_menu").click(function(){
        $(".right_layer").hide();
    })
    $(".select_tab").click(function(){
        $(".right_layer").hide();
    })
    $(".screening").click(function(){
        $(".right_layer").fadeToggle();
    })
    $(".right_layer").click(function(){
        //$(".right_layer").toggle();
        $(".right_layer").fadeOut();
    })
})
function his_load(){
    $.ajax({
        type: 'post',
        url: urlWay.fdmanageHost+'fdService.action',
        cache:false,
        async:false,
        data:{action : "getChildcareOrderList"},
        success:function(result){
            var list=result.userChildcareAppointmentInfoList;
            if(list!=null){
                var html='';
                for(var i= 0,len=list.length;i<len;i++){
                    var user=list[i].userInfo;
//                        doctorId=list[i].doctorInfo.id;
                    var s_text='';
                    if(list[i].payMethod=="余额支付"){
                        s_text='普通用户';
                    }else if(list[i].payMethod=="家庭医生套餐"){
                        s_text='签约用户';
                    }
                    html+='<div class="order_div" data-order="'+list[i].status+'" data-type="'+s_text+'"><div class="admin_header"><div><img src="'+hostBG+'images/userFaceIcon/'+user.userImage+'"/></div><p>'+user.babyName+'</p></div><div class="admin_information"><p class="order_time">' +
                        '<span>'+list[i].operationTime+'</span></p><p class="order_address">'+list[i].hospitalBasicInfo.address+'</p><p class="order_type">'+s_text+'</p>' +
                        '</div></div>';
                }
                $('#his_order_div').html(html);
                $('#his_order_div .order_div').hide();
                var dataOrder=$('#his_order_div .order_div');
                var txt=$(".select_tab.emphasis").text();
                if(txt=='已预约'){
                    for(var i= 0,len=dataOrder.length;i<len;i++){
                        $('#his_order_div .order_div[data-order=已预约]').show();
                        $('#his_order_div .order_div[data-order=已会面]').show();
                    }
                }
                else if(txt=="已取消"){
                    for(var i= 0,len=dataOrder.length;i<len;i++){
                        $('#his_order_div .order_div[data-order=用户取消]').show();
                    }
                }
                else if(txt=="已完成"){
                    for(var i= 0,len=dataOrder.length;i<len;i++){
                        $('#his_order_div .order_div[data-order=已完成]').show();
                        $('#his_order_div .order_div[data-order=已评价]').show();
                    }
                }else{
                    $('#his_order_div .order_div').show();
                }
            }
            setTimeout(click(),3000);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
}
function outpatient_load(){
    $.ajax({
        type:'post',
        url:urlWay.clinicHost+'doctorClinicOrder.action',
        cache:false,
        async:false,
        data:{action : "getClinicOrderList"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list=result.orderInfoClinicList;
                if(list!=null){
                    var html='';
                    for(var i= 0,len=list.length;i<len;i++){
                        var user=list[i].userInfo;
//                        doctorId=list[i].doctorInfo.id;
                        var s_text='';
                        if(list[i].payMethod=="余额支付"){
                            s_text='普通用户';
                        }else if(list[i].payMethod=="家庭医生套餐"){
                            s_text='签约用户';
                        }
                        html+='<div class="order_div" data-order="'+list[i].orderStatus+'" data-type="'+s_text+'"><div class="admin_header"><div><img src="'+hostBG+'images/userFaceIcon/'+user.userImage+'"/></div><p>'+user.parentName+'</p></div><div class="admin_information"><p class="order_time">' +
                            '<span>'+list[i].appointmentDate+'</span><span>'+list[i].appointmentBeganTime+'-'+list[i].appointmentEndTime+'</span></p><p class="order_address">'+list[i].clinicAddress+'</p><p class="order_type">'+s_text+'</p>' +
                            '</div></div>';
                    }
                    $('#pediatric_order_div').html(html);
                    $('#pediatric_order_div .order_div').hide();
                    var dataOrder=$('.order_div');
                    var txt=$(".select_tab.emphasis").text();
                    if(txt=='已预约'){
                        for(var i= 0,len=dataOrder.length;i<len;i++){
                            $('#pediatric_order_div .order_div[data-order=已预约]').show();
                            $('#pediatric_order_div .order_div[data-order=已会面]').show();
                        }
                    }
                    else if(txt=="已取消"){
                        for(var i= 0,len=dataOrder.length;i<len;i++){
                            $('#pediatric_order_div .order_div[data-order=用户取消]').show();
                        }
                    }
                    else if(txt=="已完成"){
                        for(var i= 0,len=dataOrder.length;i<len;i++){
                            $('#pediatric_order_div .order_div[data-order=已完成]').show();
                            $('#pediatric_order_div .order_div[data-order=已评价]').show();
                        }
                    }else{
                        $('#pediatric_order_div .order_div').show();
                    }
                }
            }
            setTimeout(click(),3000);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
}
function click(){
    $(".order_item").click(function(){
        $(".right_layer").fadeOut();
        $(this).addClass("order_item_selected").siblings().removeClass("order_item_selected");
        var text = $(this).text().substring(0,4);//筛选选中值
        var title=$(".select_tab.emphasis").text();//订单状态值
        var dataOrder=$('.order_div');//订单列表单个订单
        if(text=="普通用户"){//所有订单呢
            if(title == '全部'){
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title == '已取消'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=用户取消]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title == '已预约'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已预约]').show();
                    $('.order_div[data-order=已会面]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title=='未付款'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=未付款]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }else if(title=='已完成'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已完成]').show();
                    $('.order_div[data-order=已评价]').show();
                    $('.order_div[data-type=签约用户]').hide();
                }
            }
        }
        else if(text=="签约用户"){//所有订单呢
            if(title == '全部'){
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title == '已取消'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=用户取消]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title == '已预约'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已预约]').show();
                    $('.order_div[data-order=已会面]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title=='未付款'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=未付款]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }else if(title=='已完成'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已完成]').show();
                    $('.order_div[data-order=已评价]').show();
                    $('.order_div[data-type!=签约用户]').hide();
                }
            }
        }
        else{
            if(title == '全部'){
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div').show();
                }
            }else if(title == '已取消'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=用户取消]').show();
                }
            }else if(title == '已预约'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已预约]').show();
                    $('.order_div[data-order=已会面]').show();
                }
            }else if(title=='未付款'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=未付款]').show();
                }
            }else if(title=='已完成'){
                $('.order_div').hide();
                for(var i= 0,len=dataOrder.length;i<len;i++){
                    $('.order_div[data-order=已完成]').show();
                    $('.order_div[data-order=已评价]').show();
                }
            }
        }
    })
    $(".header_menu").click(function(){
        $("#top_layer").toggleClass("hide");
    })
    $(".item_div>div").click(function(){
        $("#top_layer").toggleClass("hide");
    })
}