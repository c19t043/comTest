//获取产品列表
function getProductList() {
    var text = $('.searchf>input').val().trim();
    $.ajax({
        type: 'post',
        url: host + 'getProductInfo.action',
        cache: false,
        async: false,
        data: {
            action: "getProductInfo",
            "productName": text
        },
        success: function (result) {
            //ale(result.userId);
            if (result.userId == 0) {//表示用户还没有登录
                $("#productListContent2").show();
                if (result.mes == "无服务") {
                    ale("无服务");
                }
                //特色服务产品
                if (result.characterProductList != null) {
                    $('#tesefuwuliebiao').html('');
                    for (var i = 0; i < result.characterProductList.length; i++) {
                        var characterProduct = result.characterProductList[i];
                        $("#tesefuwuliebiao").append(
                            "<div class='box_main_1' mark='" + characterProduct.productProperty + "' onclick=\"window.location.href='" + host + "productdetails.html?prodName=" + encodeURIComponent(characterProduct.name) + "'\">" +
                            "<img src='" + hostBG + "images/product/" + characterProduct.smallPicture + "'>" +
                            "<span class='product-name'>" + characterProduct.name + "</span><span class='history_price'>&yen;" + characterProduct.historyPrice + "</span></br>" +
                            "<p>已售出<span>" + characterProduct.productUseTime + "</span>次</p>" +
                            "<span class='total_price'>&yen;<span style='color: #FE4444'>" + characterProduct.totalPrice + "</span></span>" +
                            "</div>	"
                        );

                    }
                } else {
                    $("#tesefuwuliebiaoBox").hide();
                }

                //增值服务产品
                if (result.extraSpecialProductList != null) {
                    $("#zengzhifuwuliebiao2").html('');
                    for (var i = 0; i < result.extraSpecialProductList.length; i++) {
                        var extraSpecialProduct = result.extraSpecialProductList[i];
                        $("#zengzhifuwuliebiao2").append(
                            "<div class='box_main_1' mark='" + extraSpecialProduct.productProperty + "' onclick=\"window.location.href='" + host + "productdetails.html?prodName=" + encodeURIComponent(extraSpecialProduct.name) + "'\">" +
                            "<img src='" + hostBG + "images/product/" + extraSpecialProduct.smallPicture + "'>" +
                            "<span class='product-name'>" + extraSpecialProduct.name + "</span><span class='history_price'>&yen;" + extraSpecialProduct.historyPrice + "</span></br>" +
                            "<p>已售出<span>" + extraSpecialProduct.productUseTime + "</span>次</p>" +
                            "<span class='total_price'>&yen;<span style='color: #FE4444'>" + extraSpecialProduct.totalPrice + "</span></span>" +
                            "</div>	"
                        );
                    }
                } else {
                    $("#zengzhifuwuliebiao2Box").hide();
                }

            }
            //用户已经登录的情况
            else {
                $("#txtBabyName").text(result.babyName);
                if (result.mes == "无服务") {
                    ale("没有相关查询");
                    $("#productListContent1").hide();
                    return false;
                }
                $("#productListContent1").show();
                //基础服务产品列表
                if (result.basicSpecialProductList != null) {
                    $("#jichufuwuliebiao").html('');
                    $("#jichufuwuliebiaoBox").show();
                    for (var i = 0; i < result.basicSpecialProductList.length; i++) {
                        var basicSpecialProduct = result.basicSpecialProductList[i];
                        var historyPrice='';
                        if(!(basicSpecialProduct.historyPrice==null || basicSpecialProduct.historyPrice=='')){
                            historyPrice="<span class='history_price'>&yen;" + basicSpecialProduct.historyPrice + "</span>";
                        }
                        $("#jichufuwuliebiao").append(
                            "<div class='box_main_1' mark='" + basicSpecialProduct.productProperty + "' onclick=\"window.location.href='" + host + "productdetails.html?prodName=" + encodeURIComponent(basicSpecialProduct.name) + "'\">" +
                            "<img src='" + hostBG + "images/product/" + basicSpecialProduct.smallPicture + "'>" +
                            "<span class='product-name'>" + basicSpecialProduct.name + "</span>"+historyPrice+"</br>" +
                            "<p>已售出<span style='color: #FE4444;font-size: 17px;margin: 0 3px'>" + basicSpecialProduct.productUseTime + "</span>次</p>" +
                            "<span class='total_price'>&yen;<span style='color: #FE4444;font-size: 17px;margin-left: 2px'>" + basicSpecialProduct.totalPrice + "</span></span>" +
                            "</div>	"
                        );
                    }
                } else {
                    $("#jichufuwuliebiaoBox").hide();
                }

                //针对宝宝增值服务产品
                if (result.extraSpecialProductList != null) {
                    $("#zengzhifuwuliebiao").html('');
                    $("#zengzhifuwuliebiaoBox").show();
                    for (var i = 0; i < result.extraSpecialProductList.length; i++) {
                        var extraSpecialProduct = result.extraSpecialProductList[i];
                        var historyPrice='';
                        if(!(extraSpecialProduct.historyPrice==null || extraSpecialProduct.historyPrice=='')){
                            historyPrice="<span class='history_price'>&yen;" + extraSpecialProduct.historyPrice + "</span>";
                        }
                        $("#zengzhifuwuliebiao").append(
                            "<div class='box_main_1' mark='" + extraSpecialProduct.productProperty + "' onclick=\"window.location.href='" + host + "productdetails.html?prodName=" + encodeURIComponent(extraSpecialProduct.name) + "'\">" +
                            "<img src='" + hostBG + "images/product/" + extraSpecialProduct.smallPicture + "'>" +
                            "<span class='product-name'>" + extraSpecialProduct.name + "</span>"+historyPrice+"</br>" +
                            "<p>已售出<span style='color: #FE4444;font-size: 17px;margin: 0 3px'>" + extraSpecialProduct.productUseTime + "</span>次</p>" +
                            "<span class='total_price'>&yen;<span style='color: #FE4444;font-size: 17px;margin-left: 2px'>" + extraSpecialProduct.totalPrice + "</span></span>" +
                            "</div>	"
                        );
                    }
                } else {
                    $("#zengzhifuwuliebiaoBox").hide();
                }

                //如果针对宝宝的基础服务产品和增值服务产品都为空,则查询特色服务产品和增值服务产品
                if (result.basicSpecialProductList == null && result.extraSpecialProductList == null) {

                    $("#productListContent1").hide();
                    $("#productListContent2").show();

                    //特色服务产品
                    if (result.characterProductList != null) {
                        $("#tesefuwuliebiao").html('');
                        for (var i = 0; i < result.characterProductList.length; i++) {
                            var characterProduct = result.characterProductList[i];
                            $("#tesefuwuliebiao").append(
                                "<div class='box_main_1' mark='" + characterProduct.productProperty + "' onclick=\"window.location.href='" + host + "productdetails.html?prodName=" + encodeURIComponent(characterProduct.name) + "'\">" +
                                "<img src='" + hostBG + "images/product/" + characterProduct.smallPicture + "'>" +
                                "<span class='product-name'>" + characterProduct.name + "</span><span class='history_price'>&yen;" + characterProduct.historyPrice + "</span></br>" +
                                "<p>已售出<span>" + characterProduct.productUseTime + "</span>次</p>" +
                                "<span class='total_price'>&yen;" + characterProduct.totalPrice + "</span>" +
                                "</div>	"
                            );
                        }
                    } else {
                        $("#tesefuwuliebiaoBox").hide();
                    }

                    //不针对宝宝的增值服务产品
                    if (result.extraProductList != null) {
                        $("#zengzhifuwuliebiao2").html('');
                        for (var i = 0; i < result.extraProductList.length; i++) {
                            var extraProduct = result.extraProductList[i];
                            $("#zengzhifuwuliebiao2").append(
                                "<div class='box_main_1' mark='" + extraProduct.productProperty + "' onclick=\"window.location.href='" + host + "productdetails.html?prodName=" + encodeURIComponent(extraProduct.name) + "'\">" +
                                "<img src='" + hostBG + "images/product/" + extraProduct.smallPicture + "'>" +
                                "<span class='product-name'>" + extraProduct.name + "</span><span class='history_price'>&yen;" + extraSpecialProduct.historyPrice + "</span></br>" +
                                "<p>已售出<span>" + extraProduct.productUseTime + "</span>次</p>" +
                                "<span class='total_price'>&yen;" + extraProduct.totalPrice + "</span>" +
                                "</div>	"
                            );
                        }
                    } else {
                        $("#zengzhifuwuliebiao2Box").hide();
                    }
                }

            }
        },
        error: function () {
            layer();
        }
    });
}
function getExclusiveProductList() {
    var text = $('.searchf>input').val().trim();
    $.ajax({
        type: 'post',
        url: host + 'getProductInfo.action',
        cache: false,
        async: false,
        data: {
            action: "getProductInfo",
            "productName": text
        },
        success: function (result) {
            //ale(result.userId);
            $('#exclusiveProduct').empty();
            $('#memberProduct').empty();
            //会员产品
            if (result.memberProductList != null) {
                for (var i = 0; i < result.memberProductList.length; i++) {
                    var memberProductList = result.memberProductList[i];
                    var l=memberProductList.shortIntroduction;
                    if(memberProductList.shortIntroduction!=null &&memberProductList.shortIntroduction.length>13){
                        l=memberProductList.shortIntroduction.substr(0,13)+'...';
                    }
                    $('#memberProduct').append('<li class="products_item" onclick="window.location.href=\'health_products.html?'+memberProductList.id+'\'"> ' +
                    '<div class="products_img"> ' +
                    '<img src="'+hostBG+"images/product/"+memberProductList.smallPicture+'" alt=""/> ' +
                    '</div> ' +
                    '<div class="products_content" style="padding-top:24px;"> ' +
                    '<h3>'+memberProductList.name+'</h3> ' +
                    '<p>'+l+'</p>' +
                    '<span>&yen;'+ memberProductList.totalPrice+'</span> ' +
                    '</div> ' +
                    '</li>'
                );
                }
            }

            if (result.basicSpecialProductList != null) {
                    for (var i = 0; i < result.basicSpecialProductList.length; i++) {
                        var basicSpecialProduct = result.basicSpecialProductList[i];
                        var l=basicSpecialProduct.shortIntroduction;
                        if(basicSpecialProduct.shortIntroduction!=null && basicSpecialProduct.shortIntroduction.length>13){
                            l=basicSpecialProduct.shortIntroduction.substr(0,13)+'...';
                        }
                        $('#exclusiveProduct').append(
                            '<li mark="' + basicSpecialProduct.productProperty + '" class="products_item" onclick="window.location.href=\'' + host + 'productdetails.html?prodName=' + encodeURIComponent(basicSpecialProduct.name) + '\'">'+
                        '<a> ' +
                        '<div class="products_img"> ' +
                        '<img src="'+ hostBG + "images/product/" + basicSpecialProduct.smallPicture+'" alt=""/>' +
                        '</div>' +
                        '<div class="products_content" style="padding-top:24px;"> ' +
                        '<h3>'+ basicSpecialProduct.name +'</h3> ' +
                        '<p>'+l+'</p> ' +
                        '<span>&yen;'+ basicSpecialProduct.totalPrice+'</span>' +
                        '</div> ' +
                        '</a>' +
                        '</li>'
                        );
                    }
                }

                //针对宝宝增值服务产品
                if (result.extraSpecialProductList != null) {
                    for (var i = 0; i < result.extraSpecialProductList.length; i++) {
                        var extraSpecialProduct = result.extraSpecialProductList[i];
                        var l=extraSpecialProduct.shortIntroduction;
                        if(extraSpecialProduct.shortIntroduction!=null && extraSpecialProduct.shortIntroduction.length>13){
                            l=extraSpecialProduct.shortIntroduction.substr(0,13)+'...';
                        }
                        $('#exclusiveProduct').append(
                            '<li mark="' + extraSpecialProduct.productProperty + '" class="products_item" onclick="window.location.href=\'' + host + 'productdetails.html?prodName=' + encodeURIComponent(extraSpecialProduct.name) + '\'">'+
                            '<a> ' +
                            '<div class="products_img"> ' +
                            '<img src="'+ hostBG + "images/product/" + extraSpecialProduct.smallPicture+'" alt=""/>' +
                            '</div>' +
                            '<div class="products_content" style="padding-top:24px;"> ' +
                            '<h3>'+ extraSpecialProduct.name +'</h3> ' +
                            '<p>'+l+'</p> ' +
                            '<span>&yen;'+ extraSpecialProduct.totalPrice+'</span>' +
                            '</div> ' +
                            '</a>' +
                            '</li>'
                        );
                    }
                }
            },
        error: function () {
            layer();
        }
    });

}

//获取特定产品属性(新生儿,早产儿,一岁以下,一岁到三岁)
function getProductByProperty(obj) {
    //所有标签置灰色
    var objUl = $(obj).parent().parent();
    objUl.find('span').attr('class', 'grey');

    //选中的标签置蓝色
    $(obj).attr('class', 'blue');

    $("div[mark]").hide();
    $("div[mark=" + $(obj).text() + "]").show();
}
function searchMessage() {
    getProductList();
}