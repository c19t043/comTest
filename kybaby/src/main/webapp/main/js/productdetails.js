/**
 * Created by vinny on 2015/10/5.
 */
var serviceTime="";
var doctorId = "";
$(function(){
    var baseUrl=decodeURIComponent(window.location.search).substring(1).split("&");
	for(var i=0;i<baseUrl.length;i++){
		if(baseUrl[i].split("=")[0]=="prodName"){
			productName=baseUrl[i].split("=")[1];
		}
		if(baseUrl[i].split("=")[0]=="doctorId"){
			doctorId = baseUrl[i].split("=")[1];
		}
	}
	if(doctorId!=''){
		$('.headerLeft').eq(0).attr('onclick',"window.location.replace('servicedoctor.html')");
	}
    productDetail(productName);
});

$(function () {

		$('#method_box').css({
			'left':function(){
				return $(window).width()/2 - 126;
			}
		});
		$('#method_box .close').click(function(){
			$('#method_box').hide();
		});
		
		if(doctorId != ''){
			$('p.button_01').hide();
		}
});




function productDetail(productName){
	$.ajax({
		type:'post',
		url:hostMain+'getProductInfo.action',
		cache:false,
        async:false, 
		data:{action:"someProductDetail",productName:productName},
		success:function(result){
			if(result.mes=="操作成功"){
				var product=result.someProduct;
				$("#full-width-slider").empty();
				if(product.bigPictureOne!=null){
					$("#full-width-slider").append("<div class='rsContent'><img class='rsImg' src='"+hostBG+"images/product/productDetail/"+product.bigPictureOne+"?"+parseInt(Math.random()*10000)+"' /></div>");
				}
				if(product.bigPictureTwo!=null){
					$("#full-width-slider").append("<div class='rsContent'><img class='rsImg' src='"+hostBG+"images/product/productDetail/"+product.bigPictureTwo+"?"+parseInt(Math.random()*10000)+"' /></div>");
				}
				if(product.bigPictureThree!=null){
					$("#full-width-slider").append("<div class='rsContent'><img class='rsImg' src='"+hostBG+"images/product/productDetail/"+product.bigPictureThree+"?"+parseInt(Math.random()*10000)+"' /></div>");
				}


                $(".product_img img").css({
                    "display": "block",
                    "margin-top": "0",
                    "margin-left": "0",
                    "height": "0"
                });
                $('#full-width-slider').royalSlider({
                    arrowsNav: true,
                    loop: true,
                    //loopRewind: true,
                    fadeinLoadedSlide: true,
                    slidesSpacing: 0,
                    keyboardNavEnabled: true,
                    controlsInside: true,
                    imageScaleMode: 'fill',
                    arrowsNavAutoHide: false,
                    autoScaleSlider: true,
                    autoScaleSliderWidth: 800,
                    autoScaleSliderHeight: 400,
                    controlNavigation: 'bullets',
                    thumbsFitInViewport: false,
                    navigateByClick: false, //Navigates forward by clicking on slide.
                    startSlideId: 0,
                    autoPlay: {
                        enabled: true, //autoPlay or not
                        stopAtAction: false, //Stop autoPlay at first user action
                        pauseOnHover: false, //Pause autoPlay on hover
                        delay: 3000 //Delay between items in ms.
                    },
                    transitionType:'move',
                    globalCaption: true,
                    deeplinking: {
                        enabled: true,
                        change: false
                    },
                    imgWidth: 800,
                    imgHeight: 400
                });
	
				$("#productDetailsTitle").text(product.name);
				//$("#productDetailPrice").text(product.totalPrice);
				$(".product_price").append("<span id='productDetailPrice'>&yen;"+product.totalPrice+"</span>");
				if(!(product.historyPrice==null || product.historyPrice=='')){
					$("#productDetailsTitle").after("<span id='his_price'>&yen;"+product.historyPrice+"</span>");
				}
				if(!(product.historyPriceRemark==null || product.historyPriceRemark=='')){
					$(".product_price").prepend("<span id='price_remark'>"+product.historyPriceRemark+"</span>");
					//$(".product_price").prepend("<span id='price_remark'>惊爆价</span>");
				}
				$("#productDetailsServiceTime").text(product.serviceTime);
				$("#productDetailsUseTime").text(product.productUseTime);
				if(result.productItemNameList!=null){
					$("#productDetailsItem").html("");
					for(var i=0;i<result.productItemNameList.length;i++){
						var showName=result.productItemShowNameList[i];
						if(showName.indexOf('DUMMYITEM')==-1){
							$("#productDetailsItem").append(
									"<input type='hidden' value='"+result.productItemNameList[i]+"' /><span style='background: #fff' class='button_11' onclick='getItemDetails(this)'>"+showName+"</span>"
								);
						}
						
					}
				}
				
				$("#productIntroduction").html(product.introduction);
				serviceTime=product.serviceTime;
				
			}else if(result.mes=="下架"){
				ale("该商品已经下架了");
			}
		},
		error: function (x) {alert(x.status);
		}
	});
}

//查看项目详情
function getItemDetails(obj){
	var itemName=$(obj).prev().val();
	$.ajax({
		type:'post',
		url:hostMain+'getProductItemInfo.action',
		cache:false,
        async:false, 
		data:{action:"itemInstance",itemName:itemName},
		success:function(result){
			if(result.mes=="操作成功"){
				$("#serviceItemName").text($(obj).text());
				$("#serviceItemContent").html(result.someProductItem.handleUrl.replace(/&lt;/g,'<').replace(/&gt;/g,'>'));
				$('#method_box').show();
				
			}
		},
		error: function () {
		}
	});
}

function orderProduct(){
    var isSession=sessionStorage.getItem('session');
    if(isSession=='yes'){
        window.location.href="yjh_orderconfirm.html"+window.location.search+"&serviceTime="+serviceTime;
    }else{
        window.location.href="orderconfirm.html"+window.location.search+"&serviceTime="+serviceTime;
    }
}


