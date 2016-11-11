function  productAndService(){
	$.ajax({
		type:'post',
		url:'getProductInfo.action',
		cache:false,
        async:false, 
		data:{action:"getProductInfo"},
		success:function(result){
			ale("产品服务展示开始");
			if(result.userId!=null){//用户登录了
				ale("宝宝姓名为："+result.babyName);
				if(result.basicSpecialProductList!=null&&result.extraSpecialProductList!=null){
					ale("下面获取基础服务产品");
					for(var i=0;i<result.basicSpecialProductList.length;i++){
						var product=result.basicSpecialProductList[i];
						ale("第"+(i+1)+"个基础服务产品的小图是："+product.smallPicture);
						ale("第"+(i+1)+"个基础服务产品的名称是："+product.name);
						ale("第"+(i+1)+"个基础服务产品的售出次数是："+product.productUseTime);
						ale("第"+(i+1)+"个基础服务产品的价格是："+product.totalPrice);
					}
					ale("下面获取增值服务产品");
					for(var i=0;i<result.extraSpecialProductList.length;i++){
						var product=result.extraSpecialProductList[i];
						ale("第"+(i+1)+"个增值服务产品的小图是："+product.smallPicture);
						ale("第"+(i+1)+"个增值服务产品的名称是："+product.name);
						ale("第"+(i+1)+"个增值服务产品的售出次数是："+product.productUseTime);
						ale("第"+(i+1)+"个增值服务产品的价格是："+product.totalPrice);
					}
				}else if(result.basicSpecialProductList==null&&result.extraSpecialProductList==null){
					if(result.mes=="操作成功"){
						ale("下面获取特色服务产品");
						for(var i=0;i<result.characterProductList.length;i++){
							var prodcut=result.characterProductList[i];
							ale("第"+(i+1)+"个特色服务产品的小图是："+prodcut.smallPicture);
							ale("第"+(i+1)+"个特色服务产品的名称是："+prodcut.name);
							ale("第"+(i+1)+"个特色服务产品的售出次数是："+prodcut.productUseTime);
							ale("第"+(i+1)+"个特色服务产品的价格是："+prodcut.totalPrice);
						}
						ale("下面获取增值服务产品");
						for(var i=0;i<result.extraSpecialProductList.length;i++){
							var product=result.extraSpecialProductList[i];
							ale("第"+(i+1)+"个增值服务产品的小图是："+product.smallPicture);
							ale("第"+(i+1)+"个增值服务产品的名称是："+product.name);
							ale("第"+(i+1)+"个增值服务产品的售出次数是："+product.productUseTime);
							ale("第"+(i+1)+"个增值服务产品的价格是："+product.totalPrice);
						}
					} else if(result.mes=="无特色服务"){
						ale("下面获取增值服务产品");
						for(var i=0;i<result.extraSpecialProductList.length;i++){
							var product=result.extraSpecialProductList[i];
							ale("第"+(i+1)+"个增值服务产品的小图是："+product.smallPicture);
							ale("第"+(i+1)+"个增值服务产品的名称是："+product.name);
							ale("第"+(i+1)+"个增值服务产品的售出次数是："+product.productUseTime);
							ale("第"+(i+1)+"个增值服务产品的价格是："+product.totalPrice);
						}
					}else if(result.mes=="无增值服务"){
						ale("下面获取特色服务产品");
						for(var i=0;i<result.characterProductList.length;i++){
							var prodcut=result.characterProductList[i];
							ale("第"+(i+1)+"个特色服务产品的小图是："+prodcut.smallPicture);
							ale("第"+(i+1)+"个特色服务产品的名称是："+prodcut.name);
							ale("第"+(i+1)+"个特色服务产品的售出次数是："+prodcut.productUseTime);
							ale("第"+(i+1)+"个特色服务产品的价格是："+prodcut.totalPrice);
						}
					}else if(result.mes=="无服务"){
						ale("还没有添加商品哦");
					}
				}else if(result.mes=="无基础服务"){
					ale("下面获取增值服务产品");
					for(var i=0;i<result.extraSpecialProductList.length;i++){
						var product=result.extraSpecialProductList[i];
						ale("第"+(i+1)+"个增值服务产品的小图是："+product.smallPicture);
						ale("第"+(i+1)+"个增值服务产品的名称是："+product.name);
						ale("第"+(i+1)+"个增值服务产品的售出次数是："+product.productUseTime);
						ale("第"+(i+1)+"个增值服务产品的价格是："+product.totalPrice);
					}
				} else if(result.mes=="无增值服务"){
					ale("下面获取基础服务产品");
					for(var i=0;i<result.basicSpecialProductList.length;i++){
						var product=result.basicSpecialProductList[i];
						ale("第"+(i+1)+"个基础服务产品的小图是："+product.smallPicture);
						ale("第"+(i+1)+"个基础服务产品的名称是："+product.name);
						ale("第"+(i+1)+"个基础服务产品的售出次数是："+product.productUseTime);
						ale("第"+(i+1)+"个基础服务产品的价格是："+product.totalPrice);
					}
				}
			}else{//没有登录
				if(result.mes=="操作成功"){
					ale("下面获取特色服务产品");
					for(var i=0;i<result.characterProductList.length;i++){
						var prodcut=result.characterProductList[i];
						ale("第"+(i+1)+"个特色服务产品的小图是："+prodcut.smallPicture);
						ale("第"+(i+1)+"个特色服务产品的名称是："+prodcut.name);
						ale("第"+(i+1)+"个特色服务产品的售出次数是："+prodcut.productUseTime);
						ale("第"+(i+1)+"个特色服务产品的价格是："+prodcut.totalPrice);
					}
					ale("下面获取增值服务产品");
					for(var i=0;i<result.extraSpecialProductList.length;i++){
						var product=result.extraSpecialProductList[i];
						ale("第"+(i+1)+"个增值服务产品的小图是："+product.smallPicture);
						ale("第"+(i+1)+"个增值服务产品的名称是："+product.name);
						ale("第"+(i+1)+"个增值服务产品的售出次数是："+product.productUseTime);
						ale("第"+(i+1)+"个增值服务产品的价格是："+product.totalPrice);
					}
				} else if(result.mes=="无特色服务"){
					ale("下面获取增值服务产品");
					for(var i=0;i<result.extraSpecialProductList.length;i++){
						var product=result.extraSpecialProductList[i];
						ale("第"+(i+1)+"个增值服务产品的小图是："+product.smallPicture);
						ale("第"+(i+1)+"个增值服务产品的名称是："+product.name);
						ale("第"+(i+1)+"个增值服务产品的售出次数是："+product.productUseTime);
						ale("第"+(i+1)+"个增值服务产品的价格是："+product.totalPrice);
					}
				} else if(result.mes=="无增值服务"){
					ale("下面获取特色服务产品");
					for(var i=0;i<result.characterProductList.length;i++){
						var prodcut=result.characterProductList[i];
						ale("第"+(i+1)+"个特色服务产品的小图是："+prodcut.smallPicture);
						ale("第"+(i+1)+"个特色服务产品的名称是："+prodcut.name);
						ale("第"+(i+1)+"个特色服务产品的售出次数是："+prodcut.productUseTime);
						ale("第"+(i+1)+"个特色服务产品的价格是："+prodcut.totalPrice);
					}
				} else if(result.mes=="无服务"){
					ale("还没有添加商品哦");
				}
			}
		}
	});
}

function productDetail(){
	var productName=$.trim($("#productName").val());
	$.ajax({
		type:'post',
		url:'getProductInfo.action',
		cache:false,
        async:false, 
		data:{action:"someProductDetail",productName:productName},
		success:function(result){
			if(result.mes=="操作成功"){
				var product=result.someProduct;
				ale("该商品的第一张大图是："+product.bigPictureOne);
				ale("该商品的第二张大图是："+product.bigPictureTwo);
				ale("该商品的第三张大图是："+product.bigPictureThree);
				ale("该商品的名称是："+product.name);
				ale("该商品的价格是："+product.totalPrice);
				ale("该商品的服务时长是："+product.serviceTime);
				ale("该商品的服务次数是："+product.productUseTime);
				ale("该商品的产品说明是："+product.introduction);
				for(var i=0;i<result.productItemNameList.length;i++){
					ale("第"+(i+1)+"个项目的名称是："+result.productItemNameList[i]);
				}
			}else if(result.mes=="下架"){
				ale("该商品已经下架了");
			}
		},
		error: function () {
			layer();
		}
	});
}

//查看项目详情
function getItemDetails(){
	var itemName=$.trim($("#itemName").val());
	$.ajax({
		type:'post',
		url:'getProductItemInfo.action',
		cache:false,
        async:false, 
		data:{action:"itemInstance",itemName:itemName},
		success:function(result){
			if(result.mes=="操作成功"){
				ale("操作步骤是："+result.someProductItem.handleUrl);
			}
		},
		error: function () {
			layer();
		}
	});
}