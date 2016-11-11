var address_all_list=[];
var tag = "Y";//设为默认地址
var address_id = '',inventory=0;
var is_del = 'N';
var url_id = window.location.search.substring(1);
var orderNum=0;
//    var flag = 0;
var name='',phone='',s_street='',detailed_address='',zip_code='',combination='',s_province = '',s_city = '',s_area = '';
$(function(){
    address();
    //余额支付
    //$("#balance").click(function(){
    //
    //})
    //选择支付方式
    $(".pay_type").click(function(){
        $(this).addClass("selected").siblings(".pay_type").removeClass("selected");
    })
    //设为默认地址
    $("#switch>div").click(function(){
        if(tag == "Y"){
            tag = "N";
        }else{
            tag = "Y";
        }
    })
    //遮照
    $(".header-left").click(function(){
        if($(this).find("span").text()=="添加收货地址"){
            $(this).find("span").text("选择收货地址");
            $("#save_address").hide();
        }else if($(this).find("span").text()=="修改收货地址"){
            $(this).find("span").text("选择收货地址");
            $("#save_address").hide();
            $("input#name").val("");
            $("input#phone").val("");
            $("#expressArea dd").text("省 > 市 > 区/县");
            $("input#street").val("");
            $("input#zip_code").val("");
            $("#detailed_address").val("");
        }else if($(this).find("span").text()=="选择收货地址"){
            $(this).find("span").text("订单确认");
            $("#select_address").hide();
        }else if($(this).find("span").text()=="订单确认"){
            return_before();
        }
    })
    $("#address").click(function(){
        $(".header-left span").text("选择收货地址");
        $("#select_address").show();
    });
    url_id = window.location.search.substring(1);
    if(url_id.indexOf('&')>-1){
        get_order_info();
    }else{
        get_order_information();
    }
    //新增收货地址
    $("#add_button").click(function(){
        address_id = '';
        $(".header-left span").text("添加收货地址");
        $("#save_address").show();
    });
    //保存地址
    $("#save_button").click(function(){
        name = $("input#name").val();
        phone = $("input#phone").val();
        s_street = $("input#street").val();
        detailed_address = $("input#detailed_address").val();
        zip_code = $("input#zip_code").val();
        combination = $("#expressArea dd").text().split(" > ");
        if(combination.length<3){
            s_province = combination[0];
            s_city = combination[0];
            s_area = combination[1];
        }else{
            s_province = combination[0];
            s_city = combination[1];
            s_area = combination[2];
        }
        if(name=='') {
            ale("请填写收货人姓名");
        }
        else if(phone==''){
            ale("请填写手机号");
        }else if(phone.length<11){
            ale("请填写11位数的手机号");
        }else if($("#expressArea dd").text().indexOf('区/县')>-1){
            ale("请选择市区");
        } else if(s_street==''){
            ale("请填写收货街道");
        }else if(detailed_address == ''){
            ale("请填写详细的收货地址");
        }
        else{
            save_ajax();
        }
    })
})
//地址列表
function save_ajax(){
    $.ajax({
        type: 'post',
        url: mall + 'goodsOrderManage.action',
        cache: false,
        async: true,
        data:  {action: "saveOrUpdateB2cAddress",
            "b2cAddress.province":s_province,
            "b2cAddress.city":s_city,
            "b2cAddress.area":s_area,
            "b2cAddress.street":s_street,
            "b2cAddress.address":detailed_address,
            "b2cAddress.isMain":tag,
            "b2cAddress.phone":phone,
            "b2cAddress.receiver":name,
            "b2cAddress.postCode":zip_code,
            "b2cAddress.id":address_id,
            "b2cAddress.isDel":is_del
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                ale("地址保存成功");
                $(".header-left").find("span").text("选择收货地址");
                $("#save_address").hide();
                $("input#name").val("");
                $("input#phone").val("");
                $("#expressArea dd").text("省 > 市 > 区/县");
                $("input#street").val("");
                $("input#zip_code").val("");
                $("#detailed_address").val("");
                address();
                is_del="N";
            }
        },
        error: function () {
            layer();
        }
    });
}
function address(){
    $.ajax({
        type: 'post',
        url: mall + 'goodsOrderManage.action',
        cache: false,
        async: true,
        data:  {action: "getB2cAddressList"},
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                address_all_list = result.b2cAddressList;
                if(address_all_list!=null){
//                        $("#address #no_address").hide();
//                        $("#admin_address").show();
                    var html ='',i=0;
                    for(;i<address_all_list.length;i++){
                        var address_list = address_all_list[i];
                        var is_main = '';
                        if(address_list.isMain=='Y'){
                            is_main = '<small>[默认]</small>';
                        }
                        html += '<li data-id="'+address_list.id+'"><div class="address_information"><div class="admin_name"><p>'+address_list.receiver+is_main+'</p><p class="admin_phone">'+address_list.phone+'</p></div>' +
                            '<p class="admin_address">'+address_list.province+address_list.city+address_list.area+address_list.street+address_list.address+'' +
                            '</p></div><p class="gray_s"></p><div class="address_button"><p>' +
                            '<button class="color_button" data-main="'+address_list.isMain+'" onclick="modify(this)">修改</button><button class="del_button">删除</button></p></div></li>';
                    }

                    $("#select_address ul").html(html);
                    $("#no_address").hide();
                    $("#admin_address").show();
                    $("#admin_address").html('<div><p id="admin_name">收货人：<span id="receive_name">'+address_all_list[0].receiver+'</span><span id="admin_tel">'+address_all_list[0].phone+'</span></p><p id="address_information">收货地址：<span>'+address_all_list[0].province+address_all_list[0].city+
                        address_all_list[0].area+address_all_list[0].street+address_all_list[0].address+'</span></p></div>');
                    //删除收货地址
                    $(".del_button").click(function(){
                        var r=confirm("是否删除该条信息？");
                        if (r==true)
                        {
                            address_id = $(this).parents("li").data("id");
                            is_del="Y";
                            save_ajax();
                            $(this).parents("li").remove();
                        }
                    })
                    //选择收货地址
                    $(".address_information").click(function(){
                        var id = $(this).parents("li").index();
                        $(".header-left span").text("订单确认");
                        $("#no_address").hide();
                        $("#admin_address").show();
                        $("#select_address").hide();
                        $("#admin_address").html('<div><p id="admin_name">收货人：'+address_all_list[id].receiver+'<span id="admin_tel">'+address_all_list[id].phone+'</span></p><p id="address_information"><span>收货地址：'+address_all_list[id].province+address_all_list[id].city+
                            address_all_list[id].area+address_all_list[id].street+address_all_list[id].address+'</span></p></div>');
                    })
                }
            }
        },
        error: function () {
            layer();
        }
    });
}
//进入修改收货地址页面
function modify(org){
    var id = $(org).parents("li").index();
    $(".header-left span").text("修改收货地址");
    $("#save_address").show();
    if($(org).data('main')=='N'){
        if(tag=='Y'){
            $("#switch>div").click();
        }
    }
    $("input#name").val(address_all_list[id].receiver);
    $("input#phone").val(address_all_list[id].phone);
    if(address_all_list[id].province == address_all_list[id].city){
        $("#expressArea dd").text(address_all_list[id].province+" > "+address_all_list[id].area);
    }else{
        $("#expressArea dd").text(address_all_list[id].province+" > "+address_all_list[id].city+" > "+address_all_list[id].area);
    }
    $("input#street").val(address_all_list[id].street);
    $("input#zip_code").val(address_all_list[id].postCode);
    $("#detailed_address").val(address_all_list[id].address);
    address_id = address_all_list[id].id;
}

//没有产生订单的未付款信息
function get_order_info(){
    var id=url_id.split('&')[0];
    $.ajax({
        type: 'post',
        url: mall + 'goodsManage.action',
        cache: false,
        async: true,
        data:  {action: "getGoodsSomeThing",
            "b2cGoods.id":id
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var orderdetail = result.b2cGoods,orderType='预售订单';
                var propVals = decodeURIComponent(url_id).split('&')[2];
                $("#order_content").html('<div id="product_img"><img src="'+photo+orderdetail.goodsImg+'" alt=""/></div><div class="product_content" data-id="'+id+'">' +
                '<p class="product_name">'+orderdetail.goodsName+'</p>' +
                '<p class="product_specifications">'+propVals+'</p><p class="product_price">￥<span>'+parseFloat(url_id.split('&')[1]).toFixed(2)+'</span></p></div>');
                inventory = orderdetail.inventory;//库存量
                if(orderdetail.b2cGoodsPresaleModel==null){
                    orderType='单品订单';
                }
                $('#order_information').attr('data-status','未付款').attr('data-type',orderType);
                if(orderType=="预售订单"){
                    $('#yushou>span').html(parseFloat(orderdetail.b2cGoodsPresaleModel.prePrice).toFixed(2));
                    $('#yushou').parent().show();
                    $('#freight,#amount').addClass('gray');
                }
                $('#amount>span').html(parseFloat(url_id.split('&')[1]).toFixed(2));
                orderNum = result.orderNum;
                var postWay=result.b2cGoodsDeliverList;
                if(postWay!=null && postWay.length!=0){
                    for(var j= 0,len=postWay.length;j<len;j++){
                        if(postWay[j].isMain=='Y'){
                            $("#freight>span").text(parseFloat(postWay[j].dmoney).toFixed(2));
                            $('#send_way').append('<option selected data-dmoney="'+postWay[j].dmoney+'" value="'+postWay[j].id+'">'+postWay[j].dname+'</option>');
                        }else{
                            $('#send_way').append('<option data-dmoney="'+postWay[j].dmoney+'" value="'+postWay[j].id+'">'+postWay[j].dname+'</option>');
                        }
                    }
                }
                var propertyList_normal=result.propertyList_normal;
                if(propertyList_normal!=null && propertyList_normal.length!=0){
                    var specificationHtml='<li>'+propVals+'</li>';
                    for(var m= 0,len=propertyList_normal.length;m<len;m++){
                        specificationHtml+='<li>'+propertyList_normal[m].propName+'：'+propertyList_normal[m].b2cGoodsPropertyValueSet[0].valName+'</li>'
                    }
                    $('#specification').html(specificationHtml);
                    $('.specification').show();
                }
                $("#balance>#span").text((result.userInfo.accountBalance).toFixed(2));
                price_calculation(inventory,1);
                $('#submit').attr('onclick','goPay()');
                //setTimeout(function () {
               //    payMoney('微信支付','');
               //},10000)
            }
        },
        error: function () {
            layer();
        }
    });
}
//得到订单信息
function get_order_information(){
    $.ajax({
        type: 'post',
        url: mall + 'goodsOrderManage.action',
        cache: false,
        async: true,
        data:  {action: "getB2cGoodsOrder",
            "b2cGoodsOrder.id":url_id.split('&')[0]
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                if(result.b2cGoodsOrder!=null){
                    var orderdetail = result.b2cGoodsOrder.b2cGoodsOrderDetailSet[0];
                    var propVals='';
                    orderNum = result.b2cGoodsOrder.orderNum;
                    if(orderdetail.propVals!=null){
                        propVals = orderdetail.propVals;
                    }
                    var goodsorder = result.b2cGoodsOrder;
                    $("#order_information #order_content").html('<div id="product_img"><img src="'+photo+orderdetail.b2cGoods.goodsImg+'" alt=""/></div><div class="product_content" data-id="'+orderdetail.b2cGoods.id+'">' +
                        '<p class="product_name">'+orderdetail.b2cGoods.goodsName+'</p>' +
                        '<p class="product_specifications">'+propVals+'</p><p class="product_price">￥<span>'+parseFloat(orderdetail.price).toFixed(2)+'</span></p></div>');
                    inventory = orderdetail.b2cGoods.inventory;//库存量
                    $('#order_information').attr('data-status',result.b2cGoodsOrder.orderStatus).attr('data-type',result.b2cGoodsOrder.orderType);
                    var postWay=result.b2cGoodsOrder.b2cGoodsDeliver;
                    if(postWay==null){
                        for(var j= 0,len=result.b2cGoodsDeliverList.length;j<len;j++){
                            if(result.b2cGoodsDeliverList[j].isMain=='Y'){
                                $('#send_way').append('<option selected data-dmoney="'+result.b2cGoodsDeliverList[j].dmoney+'" value="'+result.b2cGoodsDeliverList[j].id+'">'+result.b2cGoodsDeliverList[j].dname+'</option>');
                            }else{
                                $('#send_way').append('<option data-dmoney="'+result.b2cGoodsDeliverList[j].dmoney+'" value="'+result.b2cGoodsDeliverList[j].id+'">'+result.b2cGoodsDeliverList[j].dname+'</option>');
                            }
                        }
                    }else{
                        for(var j= 0,len=result.b2cGoodsDeliverList.length;j<len;j++){
                            if(result.b2cGoodsDeliverList[j].id==result.b2cGoodsOrder.b2cGoodsDeliver.id){
                                $('#send_way').append('<option selected data-dmoney="'+result.b2cGoodsDeliverList[j].dmoney+'" value="'+result.b2cGoodsDeliverList[j].id+'">'+result.b2cGoodsDeliverList[j].dname+'</option>');
                            }else{
                                $('#send_way').append('<option data-dmoney="'+result.b2cGoodsDeliverList[j].dmoney+'" value="'+result.b2cGoodsDeliverList[j].id+'">'+result.b2cGoodsDeliverList[j].dname+'</option>');
                            }
                        }
                    }


                    if(result.b2cGoodsOrder.orderType=="预售订单"){
                        if(result.b2cGoodsOrder.orderStatus=='未付款'){
                            $('#yushou>span').html(parseFloat(result.b2cGoodsOrder.b2cGoodsOrderPromotionSet[0].discountMoney).toFixed(2));
                            $('#yushou').parent().show();
                            $('#freight,#amount').addClass('gray');
                        }else if(result.b2cGoodsOrder.orderStatus=='预付款'){
                            var to=parseFloat(result.b2cGoodsOrder.b2cGoodsOrderPromotionSet[0].discountMoney)*parseFloat(orderdetail.goodsNum);
                            $('#yushou>span').html(parseFloat(to).toFixed(2));
                            $('#number').val(orderdetail.goodsNum);
                            $('#yushou').prepend('-').parent().show();
                            $('#yushou').addClass('gray');
                            $('#yushou').prev().html('已付订金');
                        }
                    }else if(result.b2cGoodsOrder.orderType=="单品订单"){
                        if(result.b2cGoodsOrder.orderStatus=='未付款'){
                            $('#number').val(orderdetail.goodsNum);
                        }
                    }
                    var propValFoList=result.b2cGoodsOrder.propValFoList;
                    if(propValFoList!=null && propValFoList.length!=0){
                        var specificationHtml='';
                        for(var m= 0,len=propValFoList.length;m<len;m++){
                            specificationHtml+='<li>'+propValFoList[m].name_prop_val+'</li>'
                        }
                        $('#specification').html(specificationHtml);
                        $('.specification').show();
                    }
                    $('#amount>span').html(parseFloat(orderdetail.goodsNum*orderdetail.price).toFixed(2));
                    if(goodsorder.postage==null ||goodsorder.postage=='免运费'){
                        $("#freight>span").text(parseFloat(0).toFixed(2));
                    }else {
                        $("#freight>span").text(parseFloat(goodsorder.postage).toFixed(2));
                    }
                    $("#balance>#span").text((result.b2cGoodsOrder.userInfo.accountBalance).toFixed(2));
                    price_calculation(inventory,orderdetail.goodsNum);
                    $('#submit').attr('onclick','goPay()');
                }
            }
        },
        error: function () {
            layer();
        }
    });
}

//价格计算
function price_calculation(inventory,num){
    var number = 0;//数量
    //var new_amount = 0;//总价
    var my_price=0;//单价
    var my_yushou=0;//单个预售价
    var freight = 0;//运费
    var balance = 0;//可用余额
    var new_amount = 0;//总价
    var accountBalance = 0;
    var userBalance = 0;//使用余额
    var yushou = 0;//预售
    $('#send_way').attr('data-id',$('#send_way option:selected').val());
    freight = parseFloat($("#freight>span").text());//运费
    balance = parseFloat($("#balance>#span").text());//可用余额
    my_price = parseFloat($("p.product_price>span").text());//单价
    my_yushou = parseFloat($("#yushou>span").text())/num;//单个预售价
    var orderType=$('#order_information').data('type');
    var orderStatus=$('#order_information').data('status');
    if(orderType=='预售订单'){
        if(orderStatus=='未付款'){
            $("#cont>span").text(parseFloat(my_yushou).toFixed(2));
        }else if(orderStatus=='预付款'){
            var to=parseFloat(my_yushou)*parseFloat(num);
            $('#cont>span').html(parseFloat(my_price*num-to+freight).toFixed(2));
            yushou=to;
        }
    }else if(orderType=="单品订单"){
        if(orderStatus=='未付款'){
            $("#cont>span").text(parseFloat(my_price*num+freight).toFixed(2));
        }
    }
    $("#num_add").click(function(){
        number = parseInt($("#number").val());//input框内数量
        accountBalance = parseFloat($("#balance>span").text());//及时余额
        if($("#number").val()==inventory){
            ale("库存已达上限");
        }else{
            $("#number").val(++number);
            $("#amount>span").text((my_price*number).toFixed(2));
            if($("#balance").hasClass("selected")){
                if(orderType=='预售订单'){
                    if(orderStatus=='未付款'){
                        if(balance<number*my_yushou){
                            $("#balance>#span").text((0).toFixed(2));
                            $("#cont>span").text((number*my_yushou-balance).toFixed(2)).attr('usebalance',balance);
                        }else{
                            $("#balance>#span").text((balance-number*my_yushou).toFixed(2));
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',number*my_yushou);
                        }
                        $("#yushou>span").text((my_yushou*number).toFixed(2));
                    }else if(orderStatus=='预付款'){
                        $("#yushou>span").text((yushou).toFixed(2));
                        if(number*my_price-yushou+freight<=0){
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',0);
                            return false;
                        }
                        if(balance<(number*my_price-yushou+freight)){
                            $("#balance>#span").text((0).toFixed(2));
                            $("#cont>span").text((number*my_price-yushou-balance+freight).toFixed(2)).attr('usebalance',balance);
                        }else{
                            $("#balance>#span").text((balance-(number*my_price-yushou+freight)).toFixed(2));
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',(number*my_price-yushou+freight));
                        }
                    }
                }else if(orderType=="单品订单"){
                    if(orderStatus=='未付款'){
                        if(balance<(number*my_price+freight)){
                            $("#balance>#span").text((0).toFixed(2));
                            $("#cont>span").text((number*my_price+freight-balance).toFixed(2)).attr('usebalance',balance);
                        }else{
                            $("#balance>#span").text((balance-(number*my_price+freight)).toFixed(2));
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',(number*my_price+freight));
                        }
                    }
                }
            }else{
                $("#balance>#span").text((balance).toFixed(2));
                if(orderType=='预售订单'){
                    if(orderStatus=='未付款'){
                        $("#yushou>span").text((my_yushou*number).toFixed(2));
                        $("#cont>span").text((my_yushou*number).toFixed(2)).attr('usebalance',0);
                    }else if(orderStatus=='预付款'){
                        $("#yushou>span").text((my_yushou*num).toFixed(2));
                        if(number*my_price-yushou+freight<=0){
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',0);
                            return false;
                        }
                        $("#cont>span").text((number*my_price-yushou+freight).toFixed(2)).attr('usebalance',0);
                    }
                }else if(orderType=="单品订单"){
                    if(orderStatus=='未付款'){
                        $("#cont>span").text((number*my_price+freight).toFixed(2)).attr('usebalance',0);
                    }
                }

            }
        }
    });
    $("#num_reduce").click(function(){
        number = parseInt($("#number").val());//input框内数量
        accountBalance = parseFloat($("#balance>span").text());//及时余额
        if($("#number").val()==1) {
            ale("宝贝不能再减少了哦");
        }else{
            $("#number").val(--number);
            if(orderStatus=='预付款'&&orderType=='预售订单'&&number<num){
                $("#number").val(num);
                ale('已预订状态不能减少商品预订数量');
                return false;
            }
            $("#amount>span").text((my_price*number).toFixed(2));
            if($("#balance").hasClass("selected")){
                if(orderType=='预售订单'){
                    if(orderStatus=='未付款'){
                        $("#yushou>span").text((my_yushou*number).toFixed(2));
                        if(balance<number*my_yushou){
                            $("#balance>#span").text((0).toFixed(2));
                            $("#cont>span").text((number*my_yushou-balance).toFixed(2)).attr('usebalance',balance);
                        }else{
                            $("#balance>#span").text((balance-number*my_yushou).toFixed(2));
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',number*my_yushou);
                        }
                    }else if(orderStatus=='预付款'){
                        $("#yushou>span").text((my_yushou*num).toFixed(2));
                        if(number*my_price-yushou+freight<=0){
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',0);
                            return false;
                        }
                        if(balance<(number*my_price-yushou+freight)){
                            $("#balance>#span").text((0).toFixed(2));
                            $("#cont>span").text((number*my_price-yushou-balance+freight).toFixed(2)).attr('usebalance',balance);
                        }else{
                            $("#balance>#span").text((balance-(number*my_price-yushou+freight)).toFixed(2));
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',(number*my_price-yushou+freight));
                        }
                    }
                }else if(orderType=="单品订单"){
                    if(orderStatus=='未付款'){
                        if(balance<(number*my_price+freight)){
                            $("#balance>#span").text((0).toFixed(2));
                            $("#cont>span").text((number*my_price+freight-balance).toFixed(2)).attr('usebalance',balance);
                        }else{
                            $("#balance>#span").text((balance-(number*my_price+freight)).toFixed(2));
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',(number*my_price+freight));
                        }
                    }
                }
            }else{
                $("#balance>#span").text((balance).toFixed(2));
                if(orderType=='预售订单'){
                    $("#yushou>span").text((my_yushou*number).toFixed(2));
                    if(orderStatus=='未付款'){
                        $("#cont>span").text((my_yushou*number).toFixed(2)).attr('usebalance',0);
                    }else if(orderStatus=='预付款'){
                        $("#yushou>span").text((my_yushou*num).toFixed(2));
                        if(number*my_price-yushou+freight<=0){
                            $("#cont>span").text((0).toFixed(2)).attr('usebalance',0);
                            return false;
                        }
                        $("#cont>span").text((number*my_price-yushou+freight).toFixed(2)).attr('usebalance',0);
                    }
                }else if(orderType=="单品订单"){
                    if(orderStatus=='未付款'){
                        $("#cont>span").text((number*my_price+freight).toFixed(2)).attr('usebalance',0);
                    }
                }

            }
        }
    })
    $("#balance").click(function(){
        $(this).toggleClass("selected");
        number = parseInt($("#number").val());//input框内数量
        new_amount = parseFloat($("#amount>span").text());//总价
        accountBalance = parseFloat($("#balance>span").text());//及时余额
        if($("#balance").hasClass("selected")){
            if(orderType=='预售订单'){
                if(orderStatus=='未付款'){
                    if(balance<number*my_yushou){
                        $("#balance>#span").text((0).toFixed(2));
                        $("#cont>span").text((number*my_yushou-balance).toFixed(2)).attr('usebalance',balance);
                    }else{
                        $("#balance>#span").text((balance-number*my_yushou).toFixed(2));
                        $("#cont>span").text((0).toFixed(2)).attr('usebalance',number*my_yushou);
                    }
                }else if(orderStatus=='预付款'){
                    if(number*my_price-yushou+freight<=0){
                        $("#cont>span").text((0).toFixed(2)).attr('usebalance',0);
                        return false;
                    }
                    if(balance<(number*my_price-yushou+freight)){
                        $("#balance>#span").text((0).toFixed(2));
                        $("#cont>span").text((number*my_price-yushou-balance+freight).toFixed(2)).attr('usebalance',balance);
                    }else{
                        $("#balance>#span").text((balance-(number*my_price-yushou+freight)).toFixed(2));
                        $("#cont>span").text((0).toFixed(2)).attr('usebalance',(number*my_price-yushou+freight));
                    }
                }
            }else if(orderType=="单品订单"){
                if(orderStatus=='未付款'){
                    if(balance<(number*my_price+freight)){
                        $("#balance>#span").text((0).toFixed(2));
                        $("#cont>span").text((number*my_price+freight-balance).toFixed(2)).attr('usebalance',balance);
                    }else{
                        $("#balance>#span").text((balance-(number*my_price+freight)).toFixed(2));
                        $("#cont>span").text((0).toFixed(2)).attr('usebalance',(number*my_price+freight));
                    }
                }
            }
        }else{
            $("#balance>#span").text((balance).toFixed(2));
            if(orderType=='预售订单'){
                if(orderStatus=='未付款'){
                    $("#cont>span").text((my_yushou*number).toFixed(2)).attr('usebalance',0);
                }else if(orderStatus=='预付款'){
                    if(number*my_price-yushou+freight<=0){
                        $("#cont>span").text((0).toFixed(2)).attr('usebalance',0);
                        return false;
                    }
                    $("#cont>span").text((number*my_price-yushou+freight).toFixed(2)).attr('usebalance',0);
                }
            }else if(orderType=="单品订单"){
                if(orderStatus=='未付款'){
                    $("#cont>span").text((number*my_price+freight).toFixed(2)).attr('usebalance',0);
                }
            }

        }
    });
    $('#send_way').change(function () {//取货方式
        var obj=$('#send_way option:selected');
        $(this).attr('data-id',$(obj).val());
        freight=$(this).find('option:selected').data('dmoney');
        $("#freight>span").text((freight).toFixed(2));
        $("#balance").click();
        $("#balance").click();
    });
}


function goPay(){
    if($('#admin_address').html()==''){
        ale('请添加收货人地址');
        return false;
    }
    var total=$("#cont>span").html();
    var payMethod='';
    if($("#cont>span").text()>0){
        payMethod = '微信支付';
    }else{
        payMethod = '余额支付';
    }
    $('#submit').removeAttr('onclick');
    if(total==0){
        if(url_id.indexOf('&')>-1){
            parFirstMoney('余额支付');
        }else{
            payMoney('余额支付','paySuccess');
        }
    }else{
        if(payMethod=='微信支付'){
            paycall("kybaby", url_id.split('&')[0], orderNum, Math.round(total*100));
        }
    }
}
//第一次直接付钱方法
function parFirstMoney(payMethod){
    var new_amount = parseFloat($("#amount>span").text());
    var number = parseInt($("#number").val());//input框内数量
    var new_cont = parseFloat($("#cont>span").text());//总价
    var accountBalance = parseFloat($("#balance>span").text());
    var freight = parseFloat($("#freight>span").text());
    var b2cGoods_id = $(".product_content").attr("data-id");
    var propVals = $(".product_specifications").text();
    var use_balance = $("#cont>span").attr('usebalance');
    var sendWay=$('#send_way').attr('data-id');
    if($("#cont>span").text()>0){
        payMethod = '微信支付';
    }else{
        payMethod = '余额支付';
    }
    $.ajax({
        type: 'post',
        url: mall + 'goodsOrderManage.action',
        cache: false,
        async: true,
        data:  {action: "paySuccessOrder",
            "b2cGoodsOrder.totalPrice":(new_amount+freight),
            "b2cGoodsOrder.realPrice":new_cont,
            "b2cGoodsOrder.useRemainBalance":use_balance,
            "b2cGoodsOrder.payMethod":payMethod,
            "b2cGoodsOrder.baddress":$("#address_information>span").text(),
            "b2cGoodsOrder.bconsignee":$('#receive_name').text(),
            "b2cGoodsOrder.bphone":$("#admin_tel").text(),
            "b2cGoodsOrder.postage":freight,
            "b2cGoodsOrder.id":'',
            "b2cGoodsOrder.orderStatus":"paySuccess",
            "b2cGoodsOrderDetail.b2cGoods.id":b2cGoods_id,
            "b2cGoodsOrderDetail.goodsNum":number,
            "b2cGoodsOrder.b2cGoodsDeliver.id":sendWay,//配送方式
            "b2cGoodsOrderDetail.propVals":propVals,
            "b2cGoodsOrder.orderNum":orderNum,
            "b2cGoodsOrderDetail.price":url_id.split('&')[1]
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                window.location.href='mall_pay_success.html?'+result.b2cGoodsOrder.id;
            }
        },
        error: function () {
            layer();
        }
    });
}
//付款
function payMoney(payMethod,sta){
    var new_amount = parseFloat($("#amount>span").text());
    var number = parseInt($("#number").val());//input框内数量
    var new_cont = parseFloat($("#cont>span").text());//总价
    var accountBalance = parseFloat($("#balance>span").text());
    var freight = parseFloat($("#freight>span").text());
    var b2cGoods_id = $(".product_content").attr("data-id");
    var propVals = $(".product_specifications").text();
    var use_balance = $("#cont>span").attr('usebalance');
    var sendWay=$('#send_way').attr('data-id');
    var id='';
    if($("#cont>span").text()>0){
        payMethod = '微信支付';
    }else{
        payMethod = '余额支付';
    }
    if(url_id.indexOf('&')>-1){
        id='';
        $.ajax({
            type: 'post',
            url: mall + 'goodsOrderManage.action',
            cache: false,
            async: true,
            data:  {action: "handleB2cGoodsOrder",
                "b2cGoodsOrder.totalPrice":(new_amount+freight),
                "b2cGoodsOrder.realPrice":0,
                "b2cGoodsOrder.useRemainBalance":0,
                "b2cGoodsOrder.payMethod":payMethod,
                "b2cGoodsOrder.baddress":$("#address_information>span").text(),
                "b2cGoodsOrder.bconsignee":$('#receive_name').text(),
                "b2cGoodsOrder.bphone":$("#admin_tel").text(),
                "b2cGoodsOrder.postage":freight,
                "b2cGoodsOrder.id":id,
                "b2cGoodsOrder.orderStatus":sta,
                "b2cGoodsOrderDetail.b2cGoods.id":b2cGoods_id,
                "b2cGoodsOrderDetail.goodsNum":number,
                "b2cGoodsOrder.b2cGoodsDeliver.id":sendWay,//配送方式
                "b2cGoodsOrderDetail.propVals":propVals,
                "b2cGoodsOrderDetail.price":url_id.split('&')[1]
            },
            success: function (result) {
                if(result.mes=='请登录'){
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if(result.mes=='成功'){
                    if(url_id.indexOf('&')>-1){
                        ale('支付失败');
                        setTimeout(function () {
                            window.history.go(-1);
                        },1000);
                    }else{
                        window.location.href='mall_pay_success.html?'+result.b2cGoodsOrder.id;
                    }
                }
            },
            error: function () {
                layer();
            }
        });
    }else{
        id=url_id.split('&')[0];
        $.ajax({
            type: 'post',
            url: mall + 'goodsOrderManage.action',
            cache: false,
            async: true,
            data:  {action: "handleB2cGoodsOrder",
                "b2cGoodsOrder.totalPrice":(new_amount+freight),
                "b2cGoodsOrder.realPrice":new_cont,
                "b2cGoodsOrder.useRemainBalance":use_balance,
                "b2cGoodsOrder.payMethod":payMethod,
                "b2cGoodsOrder.baddress":$("#address_information>span").text(),
                "b2cGoodsOrder.bconsignee":$('#receive_name').text(),
                "b2cGoodsOrder.bphone":$("#admin_tel").text(),
                "b2cGoodsOrder.postage":freight,
                "b2cGoodsOrder.id":id,
                "b2cGoodsOrder.orderStatus":sta,
                "b2cGoodsOrderDetail.b2cGoods.id":b2cGoods_id,
                "b2cGoodsOrderDetail.goodsNum":number,
                "b2cGoodsOrder.b2cGoodsDeliver.id":sendWay,//配送方式
                "b2cGoodsOrderDetail.propVals":propVals
            },
            success: function (result) {
                if(result.mes=='请登录'){
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if(result.mes=='成功'){
                    if(url_id.indexOf('&')>-1){
                        ale('支付失败');
                        setTimeout(function () {
                            window.history.go(-1);
                        },1000);
                    }else{
                        window.location.href='mall_pay_success.html?'+result.b2cGoodsOrder.id;
                    }
                }
            },
            error: function () {
                layer();
            }
        });
    }
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

function paycall(orderDesc, orderId, orderNum, orderAmount) {
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
        onBridgeReady(orderDesc, orderId, orderNum, orderAmount);
    }
}

function onBridgeReady(orderDesc, orderId, orderNum, orderAmount){
    var prepId = getPrePayId(orderDesc,orderNum,orderAmount);
    var timeStr = getTimeStamp();
    var nonStr = getNonceStr();
    var signStringforPay="appId="+appId+"&nonceStr="+nonStr+"&package=prepay_id="+prepId+"&signType=MD5&timeStamp="+timeStr+"&key="+APISecret;
    signStringforPay=MD5(signStringforPay);
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
                if(url_id.indexOf('&')>-1){
                    parFirstMoney('微信支付');
                }else{
                    payMoney('微信支付','paySuccess');
                }
                //window.location.href="family_doctor_pay2.html?"+dServiceOrderId+'&'+totalPrice+'&'+orderNum+'&微信支付';
            }else{
                if(url_id.indexOf('&')>-1){
                    payMoney('微信支付','');
                }else{
                    ale('支付失败');
                    setTimeout(function () {
                        window.history.go(-1);
                    },1000);
                }
                //window.location.href="../wx/notifyUrl.jsp?"+orderNum+"&fail";
            }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }
    ); }

function getPrePayId(orderDesc,orderNum,orderAmount) {
    var prepayId="";
    var orderNumCopy=orderNum;
    notifyUrl=getNotifyUrl();
    var signString="appid="+ appId +"&body="+orderDesc+"&mch_id=" + mchId + "&nonce_str="+nonceStr+"&notify_url="+notifyUrl+"&openid="+userOpenId+"&out_trade_no="+orderNumCopy+"&spbill_create_ip="+ip+"&total_fee="+orderAmount+"&trade_type=JSAPI&key="+APISecret;
    //ale("signString=="+signString);//测试
    signString=MD5(signString);
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
function isCli(){
    var obj = $('input').filter(function(){
        return $(this).attr('name') == 'pay';
    });
    if($('#still_sum').text() != '0'){
        obj.removeAttr('disabled');
    }else{
        obj.get(0).checked=false;
        obj.get(1).checked=false;
        obj.get(2).checked=false;
        obj.attr('disabled','disabled');

    }
}