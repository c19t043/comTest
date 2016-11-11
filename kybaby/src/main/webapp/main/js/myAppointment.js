$(function(){
    $(".selbtn").click(function(){
        $(".selbtn").removeClass("select");
        $(this).addClass("select");
        sessionStorage.setItem('myAppointment',$(this).children("span").text());
        if($(this).data("name") == "我的预约门诊"){
            $("#info_boxdoc").show();
            $("#select_cli1").show();
            $("#info_box_all").hide();
            $("#select_cli").hide();
        }else{
            $("#info_boxdoc").hide();
            $("#select_cli1").hide();
            $("#info_box_all").show();
            $("#select_cli").show();
        }
    });
    getOrderList();
    $('#select_cli li').click(function(){
        var txt = $(this).children("span").text();
        $('#select_cli li').removeClass('blue');
        $(this).addClass('blue');
        sessionStorage.setItem('myAppointment2',txt);
        if(txt == '全部'){
            $('.order_divbox').show();
        }else if(txt == '已取消'){
            $('.order_divbox').hide();
            $('.top_zt').filter(function(){
                var a =	$(this).text().indexOf('取消');

                if(a == -1){
                    return false;
                }else{
                    return true;
                }
            }).parent().show();
        }else if(txt == '已接单'){
            $('.order_divbox').hide();
            $('.top_zt').filter(function(){
                var a =	$(this).text().indexOf('已接单');

                if(a == -1){
                    return false;
                }else{
                    return true;
                }
            }).parent().show();
        }else if(txt == '未付款'){
            $('.order_divbox').hide();
            $('.top_zt').filter(function(){
                var a =	$(this).text().indexOf('未付款');

                if(a == -1){
                    return false;
                }else{
                    return true;
                }
            }).parent().show();
        }else if(txt == '已完成'){
            $('.order_divbox').hide();
            $('.top_zt').filter(function(){
                var a =	$(this).text().indexOf('已完成');
                var b=	$(this).text().indexOf('已评价');

                if(a == -1&&b==-1){
                    return false;
                }else{
                    return true;
                }
            }).parent().show();
        }else{
            $('.order_divbox').hide();
            $('.top_zt').filter(function(){
                return $(this).text() == txt;
            }).parent().show();
        }

    });
    getAppointment();
    $('#select_cli1>li').click(function () {
        var arr=[];
        var txt = $(this).children("span").text();
        sessionStorage.setItem('myAppointment1',txt);
        $('#select_cli1>li').removeClass('blue');
        $(this).addClass('blue');
        var dataOrder=$('.pjdoctor');
        if(txt == '全部'){
            $('.listdetail').show();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=用户取消]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='已预约'||$('.pjdoctor').eq(i).html()=='用户取消'||$('.pjdoctor').eq(i).html()=='未付款'||$('.pjdoctor').eq(i).html()=='已完成'||$('.pjdoctor').eq(i).html()=='已评价'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt == '已取消'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=用户取消]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='用户取消'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt == '已预约'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=已预约]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='已预约'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt=='未付款'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=未付款]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='未付款'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }else if(txt=='已完成'){
            $('.listdetail').hide();
            for(var i= 0,len=dataOrder.length;i<len;i++){
                $('.pjdoctor[data-order=已完成]').parentsUntil('#doctor_list').show();
                $('.pjdoctor[data-order=已评价]').parentsUntil('#doctor_list').show();
                if($('.pjdoctor').eq(i).html()=='已完成'||$('.pjdoctor').eq(i).html()=='已评价'){
                    arr.push($('.pjdoctor').html());
                }
            }
        }
        if(arr.length==0){
            $("#info_boxdoc>h6").hide();
        }else{
            $("#info_boxdoc>h6").show();
        }
    });
    var session=sessionStorage.getItem('myAppointment');
    if(session!=''||session!=null||session!=undefined){
        for(var i=0;i<$(".selbtn").length;i++){
            if($(".selbtn").eq(i).children("span").text()==session){
                $(".selbtn").eq(i).trigger("click");
            }
        }
        var session1=sessionStorage.getItem('myAppointment1');
        if(session1!=''||session1!=null||session1!=undefined){
            for(var i=0;i<$("#select_cli1 li").length;i++){
                if($("#select_cli1 li").eq(i).children("span").text()==session1){
                    $("#select_cli1 li").eq(i).trigger("click");
                }
            }
        }
        var session2=sessionStorage.getItem('myAppointment2');
        if(session2!=''||session2!=null||session2!=undefined){
            for(var i=0;i<$("#select_cli li").length;i++){
                if($("#select_cli li").eq(i).children("span").text()==session2){
                    $("#select_cli li").eq(i).trigger("click");
                }
            }
        }
    }
});
//得到门诊预约订单

function getAppointment(){
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
                if(list==null){
                    $("#info_boxdoc>h6").hide();
                }else{
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

                    for(var i= 0,len=list.length;i<len;i++){
                        var doctor=list[i].doctorInfo;
                        var lastHtml='';
                        var orderTime;
                        var orderDate;
                        if(list[i].orderStatus=='未付款'){
                            if(list[i].isPlus=='Y'){
                                orderDate=list[i].appointmentDate.replace('-','').replace('-','');
                                if(orderDate>=todayDate){
                                    var date=list[i].appointmentDate.split('-')[2];
                                    lastHtml='<div class="pjdoctor1" onclick="mspay('+list[i].id+','+date+',\''+list[i].is_zhonglian+'\')">立即支付</div>';
                                }else{
                                    lastHtml='<div style="position: absolute;top:35px;right: 10px" class="mark_icon"><img src="images/passed.png"/></div>'
                                }
                            }else{
                                orderTime=list[i].appointmentDate.replace('-','').replace('-','');
                                orderTime+=list[i].appointmentBeganTime.replace(':','')+'00';
                                if(orderTime>todayTime){
                                    var date=list[i].appointmentDate.split('-')[2];
                                    lastHtml='<div class="pjdoctor1" onclick="mspay('+list[i].id+','+date+',\''+list[i].is_zhonglian+'\')">立即支付</div>';
                                }else{
                                    lastHtml='<div style="position: absolute;top:35px;right: 10px" class="mark_icon"><img src="images/passed.png"/></div>'
                                }
                            }
                            //lastHtml='<div class="pjdoctor1"><a href="tel://13541280713">号码</a></div>';
                        }else if(list[i].orderStatus=='已完成'){
                            lastHtml='<div class="pjdoctor1" onclick="commentDoctor('+doctor.id+','+list[i].id+')">评价医生</div>';
                        }else if(list[i].orderStatus=='已预约'){
                            lastHtml='<div class="pjdoctor1" onclick="cancellation('+doctor.id+','+list[i].id+',\''+list[i].is_zhonglian+'\')">取消预约</div>';
                        }else if(list[i].orderStatus=='用户取消'||list[i].orderStatus=='已评价'){
                            lastHtml='';
                        }
                        $('#doctor_list').append('<div class="listdetail"><div class="doctor_head"><img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'"></div> ' +
                        '<div class="basicinfo"> <p class="detail_name">'+doctor.doctorName+'&nbsp;&nbsp;</p> ' +
                        '<p class="detail_time">'+list[i].appointmentDate+' '+list[i].appointmentBeganTime+'-'+list[i].appointmentEndTime+'</p>' +
                        '<p class="detail_address">'+list[i].clinicAddress+'</p><div style="overflow: hidden"><span style="color: #F86B6B;font-size: 13px">订单金额￥'+list[i].totalPrice+'</span><div class="pjdoctor" data-order="'+list[i].orderStatus+'">'+list[i].orderStatus+'</div></div>' +
                        ' </div> '+lastHtml+' </div> ');
                    }
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
}
function mspay(id,date,isZ){
    if(isZ=='Y'){
        window.location.href='hospital_pay_order.html?p='+id;
    }else{
        window.location.href='payAppointment.html?'+id+'&&'+date;
    }
}
function commentDoctor(doctorid,orderId){
    window.location.href='commentDoctor.html?'+doctorid+'&&'+orderId;
}
function cancellation(doctorId,orderId,isZ) {
    var answer=confirm('您是否确定取消？');
    if(answer==true){
        if(isZ=='Y'){
            $.ajax({
                type: 'post',
                url: spInterfaceService + 'saveOrUpdatePidiatricsClinicOrder.action',
                cache: false,
                async: true,
                data: {
                    action: "saveOrUpdateClinicOrder",
                    "orderInfoClinic.orderStatus": "用户取消",//这个固定的
                    "orderInfoClinic.id": orderId,
                    "doctorInfo.id": doctorId
                },
                success: function (result) {
                    if(result.mess=='时间过期'){
                        ale('取消失败，您的订单已超过系统有效取消时间');
                    }else if(result.mess!='成功'){
                        ale(result.mess);
                        setTimeout(function () {
                            window.location.href = 'myAppointment.html';
                        },1000);
                    }else{
                        window.location.href = 'myAppointment.html';
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //alert(XMLHttpRequest.status);
                    //alert(errorThrown);
                    layer();
                }
            });
        }else{
            $.ajax({
                type: 'post',
                url: clinicHost + 'clinicOrder.action',
                cache: false,
                async: false,
                data: {
                    action: "saveOrUpdateClinicOrder",
                    "orderInfoClinic.orderStatus": "用户取消",//这个固定的
                    "orderInfoClinic.id": orderId,
                    "doctorInfo.id": doctorId
                },
                success: function (result) {
                    if(result.mes=='时间过期'){
                        ale('取消失败，您的订单已超过系统有效取消时间');
                    }else if(result.mes!='成功'){
                        ale(result.mes);
                        setTimeout(function () {
                            window.location.href = 'myAppointment.html';
                        },1000);
                    }else{
                        window.location.href = 'myAppointment.html';
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //alert(XMLHttpRequest.status);
                    //alert(errorThrown);
                    layer();
                }
            });
        }
    }
}

//获取所有订单列表
function getOrderList(){
    $('#info_box_all').empty();
    var todayTime;
    $.ajax({
        type:'post',
        url:host+'getOrderInfo.action',
        cache:false,
        async:false,
        data:{action:"orderList"},
        success:function(result){
            if(result.someOrderList == null){
                return false;
            }
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
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    layer();
                }
            });
            for(var i=0;i<result.someOrderList.length;i++){
                var cancel;
                var str;
                var status;
                var moneyStatus = 'block';
                var cancelShow = 'none';
                var detail_status = 'none';	//是否显示结果
                var orderTime;
                var isShow="";
                if(result.someOrderList[i][2] == '已接单'){
                    cancel = '取消';
                    str = '取消';
                    status = 'visible';
                }else if(result.someOrderList[i][2] == '未付款'){
                    orderTime=result.someOrderList[i][13].replace('-','').replace('-','');
                    var orderTime1=result.someOrderList[i][14].split('-')[0].split(':')[0];
                    var orderTime2=result.someOrderList[i][14].split('-')[0].split(':')[1];
                    if(orderTime1<10){
                        orderTime1='0'+orderTime1;
                    }
                    orderTime=orderTime+""+orderTime1+""+orderTime2+"00";
                    if(todayTime<=orderTime){
                        cancel = '支付';
                        str = '立即支付';
                        status = 'visible';
                        cancelShow = 'block';
                    }else{
                        cancel = '';
                        str = '';
                        status = 'hidden';
                        cancelShow = 'none';
                        isShow='<div style="position: absolute;top:35px;right: 10px" class="mark_icon"><img src="images/passed.png"/></div>'
                        //isShow="<div><img style=\"width:64px;height:64px;position: absolute;top:20px;right: 10px\" src=\"images/passed.png\"/></div>"
                    }
                }else if(result.someOrderList[i][2] == '已完成'){
                    cancel = '确认';
                    str = '确认';
                    status = 'visible';
                    detail_status = 'block';
                }else if(result.someOrderList[i][2].indexOf('取消') != -1){
                    cancel = 'N';
                    str = 'N';
                    status = 'hidden';
                    moneyStatus = 'none';
                }else if(result.someOrderList[i][2] == '已确认'){
                    cancel = '评价';
                    str = '评价';
                    status = 'visible';
                    detail_status = 'block';
                }else if(result.someOrderList[i][2] == '已评价'){
                    cancel = 'N';
                    str = 'N';
                    status = 'hidden';
                    detail_status = 'block';
                }
                else if(result.someOrderList[i][2] == '已签到'){
                    cancel = 'N';
                    str = 'N';
                    status = 'hidden';
                }
                else if(result.someOrderList[i][2] == '操作中'){
                    cancel = 'N';
                    str = 'N';
                    status = 'hidden';
                }
                var orderAmount=Math.round((result.someOrderList[i][6] - result.someOrderList[i][3] -result.someOrderList[i][4]-result.someOrderList[i][12])*100)/100;
                if(orderAmount<0){
                    orderAmount=0;
                }
                var isShowcan = 'visible';
                var colorObj = 'black';
                if(result.someOrderList[i][8]==null){
                    isShowcan = 'hidden';
                    colorObj = 'red';
                }
                $('#info_box_all').append(
                    "<div class='order_divbox' onclick=\"if('"+isShowcan+"' == 'visible'){window.location.href='order_details.html?"+result.someOrderList[i][0]+"'}else if('"+result.someOrderList[i][2]+"'=='未付款'){window.location.href='productdetails.html?prodName="+result.someOrderList[i][5]+"'} \">"+
                    "<span class='top_sj'>"+(result.someOrderList[i][1])+"</span>"+
                    "<span class='top_zt'>"+(result.someOrderList[i][2])+"</span>"+
                    //"<p class='gray_2'></p>"+
                    "<div class='info' style='position: relative'>"+
                    "<div class='img'>"+
                    "<img src='"+hostBG+"/images/product/"+(result.someOrderList[i][7])+"' />"+
                    "</div>"+
                    "<div class='text_box'>"+
                    "<div class='service_title'><span>服务产品</span>&nbsp;<span  class='font1'>"+(result.someOrderList[i][5])+"</span></div>"+
                    "<span style='color:"+colorObj+"'  class='font1 red'>"+(result.someOrderList[i][8] == null?"正在匹配合适的医生...":result.someOrderList[i][8])+"</span><br/>"+
                    "<span style='visibility:"+isShowcan+"' class='font1'>职称：</span><span  class='font1'>"+(result.someOrderList[i][9]==null?"":result.someOrderList[i][9])+"</span><br/>"+
                    "</div>"+ isShow +
                    "</div>"+
                    //"<p class='gray_2'></p>"+
                    "<div class='top_je ' style='display:"+moneyStatus+"'>"+
                    "<span>实付金额：</span>"+
                    "<span class='font2'>&yen;"+Math.round((orderAmount)*100)/100+"</span>"+
                        // "<span data_ordernum='"+result.someOrderList[i][result.someOrderList[i].length-1]+"' data_name='"+(result.someOrderList[i][5])+"' data_id='"+(result.someOrderList[i][0])+"' data_money='"+(result.someOrderList[i][6])+"' class='button' style='visibility:"+status+"' onclick=\"javascript:cancel("+result.someOrderList[i][0]+",'"+cancel+"',event,this)\">"+str+"</span>"+
                    "<span data_ordernum='"+result.someOrderList[i][10]+"' data_name='"+(result.someOrderList[i][5])+"' data_id='"+(result.someOrderList[i][0])+"' data_money='"+orderAmount+"' class='button' style='visibility:"+status+"' onclick=\"javascript:cancel("+result.someOrderList[i][0]+",'"+cancel+"',event,this)\">"+str+"</span>"+
                    "<span data_ordernum='"+result.someOrderList[i][10]+"' data_name='"+(result.someOrderList[i][5])+"' data_id='"+(result.someOrderList[i][0])+"' data_money='"+orderAmount+"' class='button' style='visibility:"+status+";display:"+cancelShow+"' onclick=\"javascript:cancel("+result.someOrderList[i][0]+",'取消',event,this)\">取消</span>"+
                        //添加一个按钮，用于用户查询该订单项目的结果
                    "<span class='button' style='display:"+detail_status+"' onclick='go_result_info("+result.someOrderList[i][10]+",event)' >查询结果</span>"+

                        //"<input type='button' onclick=getOrderResult("+result.someOrderList[i][result.someOrderList[i].length-3]+") value='查询结果' />"+
                    "</div>"+
                    "<p class='gray_s'></p>"+
                    "</div>"
                );
                $(".top_zt").each(function(){
                    if($(this).text()=="用户取消"||$(this).text()=="未付款"){
                        //$(this).parent(".order_divbox").find(".red").css("color","#909090");
                        $(this).parent(".order_divbox").find(".red").remove();
                        if($(".font1").attr("visibility","hidden")){
                            $(this).parent(".order_divbox").find(".service_title").css("marginTop","30px");
                        }
                    }
                })
            }
        },
        error: function () {
            layer();
        }
    });
}

function cancel(id,status,event,obj){
    event.stopPropagation();

    if(status == 'N'){
        return false;
    }else if(status == '取消' || status == '确认'){

        if(status=='取消'){
            var r=confirm("确定要取消订单吗？");
            if (r==false){
                return false;
            }
        }
        $.ajax({
            type:'post',
            url:host+'orderManage.action',
            cache:false,
            async:false,
            data:{
                action:"change",
                orderStatus:status,
                orderId:id
            },
            beforeSend:function(){
                $(obj).removeAttr('onclick');
            },
            success:function(result){
                if(result.mes == '成功'){
                    ale('操作成功！');
                    getOrderList();
                }
            },
            error: function () {
                layer();
            }
        });
    }else if(status == '支付'){
        $('#zPayMethod').show();
        $('#btnOrderPay').attr({
            'data_id':$(obj).attr('data_id'),
            'data_money':$(obj).attr('data_money'),
            'data_ordernum':$(obj).attr('data_ordernum')
        });
    }else if(status == '评价'){
        window.location.href = "evaluation.html?"+$(obj).attr('data_id');
    }

}

function quxiao(){
    $('#zPayMethod').hide();
}

function goPay(obj){
    var pay_way = '';
    for(var i=0;i < $('.checkbo').length;i++){
        if($('.checkbo').eq(i).get(0).checked == true){
            pay_way = $('.checkbo').eq(i).attr('mark');
        }
    }
    if(pay_way == ''){
        ale('请选择支付方式');
        return false;
    }

    /*ale('支付方式：'+pay_way);
     ale('支付id：'+$(obj).attr('data_id'));
     ale('支付钱：'+$(obj).attr('data_money'));
     ale('支付的订单编号是：'+$(obj).attr('data_ordernum'));*/

    var payMethod=pay_way;
    var payAmount=$(obj).attr('data_money');
    var orderNum=$(obj).attr('data_ordernum');
    //以上为订单支付必须包括的信息
    if(payMethod=="微信"){
        //ale("您选择了微信支付");
        paycall("kybaby", orderNum, Math.round(payAmount*100));
    }else if(payMethod=="支付宝"){
        //ale("您选择了支付宝支付");
        window.location.href="turnToAlipay.html?WIDseller_email=guoyushan@yishangkeji.cn&WIDout_trade_no="+orderNum+"&WIDsubject=kybaby"+"&WIDtotal_fee="+payAmount;
    }else if(payMethod=="银联"){
        ale("正在积极的开发中，请耐心等待");
    }

}
function go_result_info(str,event){
    event.stopPropagation();
    window.location.replace("result_info.html?"+str);
}


/*支付功能开始*/
var ip="";
var nonceStr;
var appId;
var mchId;
var APISecret;
var userOpenId="";
var notifyUrl="";

function getUserId() {
    $.ajax({
        url:'../wx/getOpenId.action',
        cache:false,
        async:false,
        data:{action:"openId"},
        success:function(result) {
            userOpenId=result.openId;
        },
        error: function () {
            layer();
        }
    });
    return userOpenId;
}
//获取openid结束 	2015-7-18 17:06:56-----------------------------------------------------------------------------------------------------

function getTimeStamp(){
    var timestamp=new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    return timestampstring;
}
//获取精确到毫秒的时间字符串-----------------------------------------------------------------------------------------------------------------

function getNonceStr(){
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    var noceStr = "";
    for (var i = 0; i < 32; i++) {
        noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return noceStr;
}
//获取32位随机字符串-------------------------------------------------------------------------------------------------------------------------

function getRemoteIp() {
    var remoteIp="";
    $.ajax({
        url:'../wx/configManage.action', // 跳转到 action
        data:{action:"getRemoteIp"},
        cache:false,
        async:false,
        success:function(result) {
            remoteIp=result.ip;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
            layer();
        }
    });
    return remoteIp;
}
//获取到ip地址-----------------------------------------------------------------------------------------------------------------------------

function getAppId() {
    var appId="";
    $.ajax({
        url:'../wx/configManage.action', // 跳转到 action
        data:{action:"getProperty", propertyName:"corpId"},
        cache:false,
        async:false,
        success:function(result) {
            appId=result.propertyValue;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
            layer();
        }
    });
    return appId;
}
//获取到appid---------------------------------------------------------------------------------------------------------------------------------

function getMchId() {
    var mchId="";
    $.ajax({
        url:'../wx/configManage.action', // 跳转到 action
        data:{action:"getProperty", propertyName:"mchId"},
        cache:false,
        async:false,
        success:function(result) {
            mchId=result.propertyValue;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            //ale(errorThrown);
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
            layer();
        }
    });
    return mchId;
}
//获取到微信支付的商户号-------------------------------------------------------------------------------------------------------------------------------

function getAPISecret() {
    var APISecret="";
    $.ajax({
        url:'../wx/configManage.action', // 跳转到 action
        data:{action:"getProperty", propertyName:"APISecret"},
        cache:false,
        async:false,
        success:function(result) {
            APISecret=result.propertyValue;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
            layer();
        }
    });
    return APISecret;
}
//获取到微信支付应用密匙---------------------------------------------------------------------------------------------------------------------------------

//下面开始获取notifyUrl------------------------------------------------------------------------------------------------------------------------------
function getNotifyUrl(){
    $.ajax({
        type:'post',
        url:'../wx/configManage.action',
        data:{action:"getProperty", propertyName:"notify_url"},
        cache:false,
        async:false,
        success:function(result){
            notifyUrl=result.propertyValue;
        },
        error: function () {
            layer();
        }
    });
    return notifyUrl;
}
//调用微信支付-----------------------------------------------------------------------------------------------------------------------------------------

function paycall(orderDesc, orderNum, orderAmount) {
    userOpenId=getUserId();
    ip=getRemoteIp();
    nonceStr=getNonceStr();
    appId = getAppId();
    mchId = getMchId();
    APISecret = getAPISecret();
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady(orderDesc, orderNum, orderAmount);
    }
}

function onBridgeReady(orderDesc, orderNum, orderAmount){
    var prepId = getPrePayId(orderDesc,orderNum,orderAmount);
    var timeStr = getTimeStamp();
    var nonStr = getNonceStr();
    var signStringforPay="appId="+appId+"&nonceStr="+nonStr+"&package=prepay_id="+prepId+"&signType=MD5&timeStamp="+timeStr+"&key="+APISecret;

    //ale("signStringforPay=="+signStringforPay);

    signStringforPay=MD5(signStringforPay);

    //ale("signStringforPay=="+signStringforPay);

    signStringforPay=signStringforPay.toUpperCase();
    appId=appId+"";
    timeStr=timeStr+"";
    nonStr=nonStr+"";
    signStringforPay=signStringforPay+"";
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appId,     //公众号名称，由商户传入
            "timeStamp":timeStr,         //时间戳，自1970年以来的秒数
            "nonceStr":nonStr, //随机串
            "package":"prepay_id=" + prepId,
            "signType":"MD5",         //微信签名方式:
            "paySign":signStringforPay  //微信签名
        },
        function(res){
            if(res.err_msg == "get_brand_wcpay_request:ok" || res.err_msg == "get_brand_wcpay_request：ok") {
                //ale("wei xin zhi fu 成功,开始订单处理");
                //ale("支付成功的订单编号"+orderNum);
                //支付成功，将订单号反馈，后台根据orderNum参数更新对应的状态,然后跳转到支付成功页面
                $.ajax({
                    type:'post',
                    url:'orderManage.action',
                    cache:false,
                    async:true,
                    data:{action:"paySuccess",orderNum:orderNum},
                    success:function(result){
                        window.location.href="paysuccess.html?"+orderAmount;
                        if(result.mes=="操作成功"){
                            ale("订单更新成功");
                        }
                    },
                    error: function () {
                        layer();
                    }
                });

                $.ajax({
                    type:'post',
                    url:'sendMessage.action',
                    cache:false,
                    async:true,
                    data:{action:"toUser",orderNum:orderNum,sendWords:"预约"},
                    success:function(result){

                    }
                });
                $.ajax({
                    type:'post',
                    url:'sendMessage.action',
                    cache:false,
                    async:true,
                    data:{action:"toDoctor",orderNum:orderNum,sendWords:"预约"},
                    success:function(result){

                    }
                });
            }else{
                window.location.href="payfail.html";
                //window.location.href="../wx/notifyUrl.jsp?"+orderNum+"&fail";
            }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    ); }

function getPrePayId(orderDesc,orderNum,orderAmount) {
    var prepayId="";
    var orderId=orderNum;
    notifyUrl=getNotifyUrl();
    var signString="appid="+ appId +"&body="+orderDesc+"&mch_id=" + mchId + "&nonce_str="+nonceStr+"&notify_url="+notifyUrl+"&openid="+userOpenId+"&out_trade_no="+orderId+"&spbill_create_ip="+ip+"&total_fee="+orderAmount+"&trade_type=JSAPI&key="+APISecret;

    //ale("signString=="+signString);//测试

    signString=MD5(signString);

    //ale("signString=="+signString);//测试

    signString=signString.toUpperCase();
    $.ajax({
        url:'../wx/getPrepayId.action', // 跳转到 action
        data:{action:"getPrepayId",mchId:mchId,tradeNo:orderNum,remoteIp:ip,nonceStr:nonceStr,signStr:signString,userOpenId:userOpenId,body:orderDesc,totalFee:orderAmount},
        cache:false,
        async:false,
        success:function(result) {
            resultXml=result.result;
            prepayId= resultXml.substring(resultXml.indexOf("<prepay_id>")+20,resultXml.indexOf("</prepay_id>")-3);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
            layer();
        }
    });
    return prepayId;
}

//支付功能结束----------------------------------------------------------------------------
