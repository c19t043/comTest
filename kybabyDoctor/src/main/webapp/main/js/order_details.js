
$(function(){
	//签到点击触发
	$(".qd_button").click(function(){
		$("#signInConfirmBox,#mask").show();
        $.ajax({
            type:'post',
            url:urlWay.clinicHost+'doctorClinic.action',
            cache:false,
            async:false,
            data:{action : "getCurrentTime"},
            success:function(result){
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == "成功") {
                    //alert(workStatus);
                    $('#signInTime').html(result.currentTime);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert(errorThrown);
            }
        });
	});
	//签到取消点击触发
	$("#signInCancelCli").click(function(){
		$("#signInConfirmBox,#mask").hide();
	});
	//签到确认点击触发
	$("#signInConfirmCli").click(function(){
		healthResultAjax.sign();
		$("#signInConfirmBox,#mask").hide();
		$("#video").get(0).play();
	});
	//健康档案左侧菜单点击事件
	$(".levelOne li").click(function(){
		$(this).siblings().removeClass("liActive");
		$(this).addClass("liActive");
		$(".levelTwo>div").hide();
		$(".levelTwo>div").eq($(this).data("num")).show();
	});
	//滚动条阻止冒泡
	$(".birthInfo").scroll(function(event){
		event.stopPropagation();
	});
	//编辑健康档案，弹窗显示
	$("#editHealthRecord").click(function(){
		$('#babyHealthRecord,#mask').show();
		$("body").css("overflow","hidden");
	});
	//确认、取消编辑健康档案，弹窗隐藏
	$("#updateHealthRecord,#cancelHealthRecord").click(function(){
		$('#babyHealthRecord,#mask').hide();
		$("body").css("overflow","auto");
		healthResultAjax.goResultInfo();
	});	
	//健康档案填写补充change事件
	$("#vaccineAbnormalReaction,#hospitalized,#allergies").change(function(){
		if($(this).val() == "是" || $(this).val() == "有"){
			$(this).parent().next().show().data("show","yes");
		}else{
			$(this).parent().next().hide().data("show","no");
		}
	});
	//获取当前地址
	//getLocation();
	//测试一下




});
window.onload=function(){
	$("#mask").css("height",function(){
		return $(document).height();
	});
	
}

//健康结果输入函数集合
var HealthResult = {
	mark:0,
	initialize:function(stepNum){

			stepNum = Number(stepNum) -1;//下标以0开始，所以-1
			var _thisPage = $(".resultRecordingStep").eq(stepNum);	
			_thisPage.css("display","block");
			
			
			this.buttonShow(stepNum+1);
			this.tagColor(stepNum);
			this.tagScroll();		


	},
	tagColor:function(stepNum){
		$("#resultTag ul li").each(function(i){//根据执行到第几步，给tag变色
			if(i < stepNum){
				$(this).attr('class','tagOld');
			}else if(i == stepNum){
				$(this).attr('class','tagNow');
			}else{
				$(this).attr('class','tagNew');
			}
		});	
	},
	tagScroll:function(){
		$("#resultTag ul").scrollLeft(function(){//给tag ul加滚动条距左边的距离，20表示伪元素before和after的宽度和
			var scroll_width = 0;
			$(".tagOld").each(function(i){
				if(i != ($(".tagOld").length-1)){
					scroll_width += (parseFloat($(this).css("width"))+20);			
				}
			});
			return scroll_width;
		});	
	},
	buttonShow:function(num){
		if($("#resultTag li").length > 1){
			if(num == 1){
				$("#lastStep,#otherStep").hide();
				$("#firstStep").css("display","block");//按钮(继续下一步)显示
			}else if(num == $(".resultRecordingStep").length){
				$("#lastStep").css("display","block");
				$("#firstStep,#otherStep").hide();
			}else{
				$("#otherStep").css("display","block");
				$("#firstStep,#lastStep").hide();
			}		
		}else if($("#resultTag li").length == 1){
			$("#otherStep,#lastStep").hide();
			$("#onlyStep").show();
		}

	},
	pageAnimate:function(direction){//传left或right
		var visibleObj = $(".resultRecordingStep:visible");
		if(direction == "left"){
			var nebiorObj = visibleObj.next();
		}else if(direction == "right"){
			var nebiorObj = visibleObj.prev();
		}
		visibleObj.animate({
			"left":(function(){
				if(direction == "left"){
					return -$(document).width();
				}else if(direction == "right"){
					return $(document).width();
				}	
			})(),
		},400,function(){
			$(this).hide();
		});
		nebiorObj.show().css({
			"left":function(){
				if(direction == "left"){
					return $(document).width();
				}else if(direction == "right"){
					return -$(document).width();
				}	
			}
		}).animate({
			"left":0,
		},400,function(){
			HealthResult.mark = 0;
		});	
	},
	goStep:function(direction){//传next或prev
		healthResultAjax.updateResultData();
		var nowStepNum = Number($(".resultRecordingStep:visible").data("num"));
		if(this.mark != 0){
			return false;
		}
		this.mark = 1;
		if(direction =="next"){
			this.pageAnimate("left");
			this.tagColor(nowStepNum);
			this.tagScroll();	
			this.buttonShow(Number(nowStepNum)+1);
		}else if(direction =="prev"){
			this.pageAnimate("right");	
			this.tagColor(nowStepNum-2);
			this.tagScroll();	
			this.buttonShow(Number(nowStepNum)-1);	
		}
	},
	recordHealthData:function(){
        var jsonData = {};
        var num=0;
        $(".levelTwo input[type!='checkbox'],.levelTwo select,.levelTwo textarea").each(function(){
            var key =  "babyBasicData."+$(this).attr("id");
            var val = $(this).val().trim();
            if($(this).attr("placeholder")!=undefined){
                if($(this).hasClass("mustNumber")){
                    if(val!=''){
                        if(/^[0-9]+(.[0-9]{1,3})?$/.test(val)){

                        }else{
                            alert($(this).attr("placeholder")+'这一栏请填写数字');
                            num++;
                        }
                    }
                } else{
                    if($(this).attr("id")=='origin'){
                        if(val.length>40){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过40个');
                            num++;
                        }
                    }else if($(this).attr("id")=='birthWeight' || $(this).attr("id")=='birthBodyLength'|| $(this).attr("id")=='maternalRiskFactors'|| $(this).attr("id")=='prenatalEducation'|| $(this).attr("id")=='earlyFeeding'|| $(this).attr("id")=='mainCaregivers'|| $(this).attr("id")=='geneticHeight'|| $(this).attr("id")=='degreeOfFatherEducation'|| $(this).attr("id")=='heightOfFather'|| $(this).attr("id")=='degreeOfMotherEducation'|| $(this).attr("id")=='heightOfMother' ||$(this).attr("id")=='bornWay'||$(this).attr("id")=='embryoNumber'){
                        if(val.length>10){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过10个');
                            num++;
                        }
                    }else if($(this).attr("id")=='bornHospital'||$(this).attr("id")=='twinBrotherAndSister'){
                        if(val.length>30){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过30个');
                            num++;
                        }
                    }else if($(this).attr("id")=='familyHistory'){
                        if(val.length>100){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过100个');
                            num++;
                        }
                    }else if($(this).attr("id")=='drugAllergyHistory'||$(this).attr("id")=='hospitalizedRemark'||$(this).attr("id")=='allergiesRemark'||$(this).attr("id")=='vaccineAbnormalRemark'||$(this).attr("id")=='motherBadHabits'||$(this).attr("id")=='livingEnvironment'||$(this).attr("id")=='hospitalized'||$(this).attr("id")=='vaccineAbnormalReaction'||$(this).attr("id")=='fuFamilyGeneticDisease'||$(this).attr("id")=='maFamilyGeneticDisease'||$(this).attr("id")=='fatherBadHabits'){
                        if(val.length>50){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过50个');
                            num++;
                        }
                    }else if($(this).attr("id")=='permanentAddress'){
                        if(val.length>60){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过60个');
                            num++;
                        }
                    }else if($(this).attr("id")=='motherAge'||$(this).attr("id")=='otherBabiesGrowTogether'||$(this).attr("id")=='whetherOnlyChild'||$(this).attr("id")=='dataProvider'||$(this).attr("id")=='fatherAge'||$(this).attr("id")=='earlyLearning'||$(this).attr("id")=='allergies'||$(this).attr("id")=='historyOfDisease'||$(this).attr("id")=='childProtectionAgency'||$(this).attr("id")=='regularChildProtection'||$(this).attr("id")=='vaccinationAgency'||$(this).attr("id")=='regularInjections'){
                        if(val.length>5){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过5个');
                            num++;
                        }
                    }else if($(this).attr("id")=='phoneOfMother'||$(this).attr("id")=='phoneOfFather'){
                        if(val.length>11){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过11个');
                            num++;
                        }
                    }else if($(this).attr("id")=='oftenGoHospital'){
                        if(val.length>200){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过200个');
                            num++;
                        }
                    }else{
                        if(val.length>20){
                            alert($(this).attr("placeholder")+'这一栏填写字符不超过20个');
                            num++;
                        }
                    }
                }
            }
            if(val=='请选择'){
                val='';
            }
            jsonData[key] = val;
        });
        jsonData["orderInfoClinic.id"] = orderId;
        jsonData["action"] = "saveOrUpdateBabyBasicData";
        if(num>0){
            return false;
        }
        return jsonData;
	},
	selectAll:function(obj,event){
		event.preventDefault();
		event.stopPropagation();
//		if($(obj).get(0).checked==true){
//			$(obj).get(0).checked==false;
//		}else{
//			$(obj).get(0).checked==true;
//		}
		var allnum = $("input[id^='check_num']").length;
		var selnum = $("input[id^='check_num']:checked").length;
		if(allnum == selnum){
			$("input[id^='check_num']").each(function(){
				$(this).get(0).checked=false;
			});	
			$(obj).get(0).checked=false;	
		}else{
			$("input[id^='check_num']").each(function(){
				$(this).get(0).checked=true;
			});
			$(obj).get(0).checked=true;
		}


	},

};
//获取详细地址
function getLocation(){

  if(navigator.geolocation){
    navigator.geolocation.getCurrentPosition(showPosition);
  }else{
  	ale("请打开GPS定位!");
  }
	function showPosition(position){
		var map = new BMap.Map("baiduMap");
		map.centerAndZoom(new BMap.Point(104.072, 30.663), 11);
		var myGeo = new BMap.Geocoder();
		myGeo.getLocation(new BMap.Point(position.coords.longitude,position.coords.latitude), function(result){
			if (result){
				$("#signInAddress").text(result.address);
			}
		});
	}
}

//ajax
$(function(){

	healthResultAjax.getOrderInfo();
});
var healthResultAjax = {

	searchId:location.search.substring(1),
	signBefore:function(){//签到前
		$(".contralview").hide();
		$("#order_top,#baby_basic_info,#product_info,#qd_button").show();		
	},
	signAfter:function(){//签到后
		$(".contralview").hide();
		$("#order_top,#baby_basic_info,#editHealthRecord,#product_info").show();
		$("#editHealthRecord").trigger("click");
		$("#mask").show();
	},
	goHealthRecord:function(){//填健康档案
		$(".contralview").hide();
		$("#order_top,#baby_basic_info,#editHealthRecord,#babyHealthRecord,#mask").show();
	},
	goResultInfo:function(){
		$("body").css("overflow","hidden");
		$(".contralview,#baby_basic_info,#order_top").hide();
		$("#baby_basic_info,#order_top,#product_info").prev().hide();
		$("#editHealthRecord,#healthResultRecord_box").show();
		$("#product_info").next().hide();
	},
	getOrderInfo:function(){//获取订单信息
		var _this = this;
		$.ajax({
			type:'post',
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			data:{action:"toOrder","orderInfo.id":this.searchId},		
			success:function(result){
				$("#orderinfoname").text(result.userInfo.babyName);
				$("#orderinfophone").text(result.userInfo.phone);
				$("#orderinfoaddress").text(result.userInfo.userProvince+result.userInfo.userCity+result.userInfo.userArea+result.userInfo.userStreet);
				$("#orderinfodate").text(result.orderInfo.bespokeDate+" "+result.orderInfo.bespokeTime);
				$("#orderinfonum").text(result.orderInfo.orderNum);
				$("#orderinfostatus").text(result.orderInfo.orderStatus);
				$("#productName").text(result.product.name);
				$("#product_info img").attr("src",urlWay.hostName+"/kybabyBG/admin/images/product/"+result.product.smallPicture)
				$("#babyName").text(result.userInfo.babyName);
				$("#babySex").text(result.userInfo.sex);
				$("#babyBirth").text(result.userInfo.birthday);

				if(result.babyBasicData == null){
					$(".birthInfo #id").val("");
				}else{
					$(".birthInfo #id").val(result.babyBasicData.id);
				}
				
				if(result.mes == "进签到"){
					_this.signBefore();
				}else if(result.mes == "进健康档案"){
					_this.goHealthRecord();
					healthResultAjax.getHealthRecord();
				}else if(result.mes == "进流程节点"){
					_this.goResultInfo();

				}
				if(result.orderNodeTrack == null){
					healthResultAjax.step = "first";
				}else{
					healthResultAjax.step = result.orderNodeTrack.flowNodeId;
				}
				healthResultAjax.getFlowNode();
			},
			error:function(XMLHttpRequest){
				alert(XMLHttpRequest.status);
			}
		});
	},
	sign:function(){//签到
		var _this = this;
		$.ajax({
			type:'post',
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			data:{
				action:"doctorSign",
				"orderInfo.id":this.searchId,
				"doctorSignRecord.signAddress":$("#signInAddress").text(),	
			},
			success:function(result){
				if(result.mes=="签到成功"){
					ale("签到成功");
					_this.signAfter();
					$("#orderinfostatus").text("已签到");
				}else{
					ale("签到失败了");
				}
			}
		});	
	},
	getHealthRecord:function(){//获取健康档案
		$.ajax({
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			data:{
				action:"getBabyBasicData",
				"orderInfo.id":this.searchId
			},
			success:function(result){
				if(result.babyBasicData == null){
					return false;
				}
				$.each(result.babyBasicData,function(key,val){
					var obj = $(".levelTwo input,.levelTwo select,.levelTwo textarea").filter(function(){
						return $(this).attr("id") == key;
					});
					if(obj.get(0) == undefined){
						return true;
					}else if(obj.get(0).tagName == "INPUT"){
						obj.val(val);
					}else if(obj.get(0).tagName == "TEXTAREA"){
						obj.val(val);
					}else if(obj.get(0).tagName == "SELECT"){
                        obj.find("option").each(function(){
                            $(this).removeAttr('selected');
                            if(val==''&& $(this).text()=='请选择'){
                                $(this).attr('selected','selected');
                            }else if($(this).text() == val){
                                $(this).attr('selected','selected');
                            }
                        });
						if(obj.parent().next().hasClass("forDesc")){
							if(val == "无" || val == "否"){
								obj.parent().next().hide();
							}
						}
					}else{
					}
				});
			},
			
		});
	},
	updateHealthRecord:function(){//保存健康档案
        var recordHealth=recordHealthData();
        if(recordHealth==false){
            return false;
        }
		$.ajax({
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			data:recordHealth,
			success:function(result){

			}
		});	
	},
	getHealthResultTable:function(){//获取结果数据表单
		$.ajax({
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			data:{
				action:"getItemResult",
				"orderInfo.id":this.searchId				
			},
			success:function(result){
				
			}
		});			
	},
	getFlowNode:function(){//获取流程标签
		$.ajax({
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			data:{
				action:"getFlowNode",
				"orderInfo.id":this.searchId				
			},
			success:function(result){
				
				var islastmark = false;
				//return false;
				if(result.flowNodeFoList == null){
					return false;
				}
				$("#resultTag ul,#appendResultRecordingBox").empty();
				$.each(result.flowNodeFoList,function(key,val){//先遍历flowNodeFoList
					if(val.flowNodeName.indexOf("健康指导") != -1){
						islastmark = true;
					}
					$("#resultTag ul").append(
						"<li data-id="+val.flowNodeId+">"+val.flowNodeName+"</li>"
					);
					$("#productItem ul").append(
					"<li class='label_blue'>"+val.flowNodeName+"</li>"
					);
					$("#appendResultRecordingBox").append(//生成翻页
					"<div data-num='"+(key+1)+"' class='item-"+(key+1)+" resultRecordingStep none'>"+
						"<ul>"+
						"</ul>"+
					"</div>"
					);
					var marki = 0;
					$.each(val.itemAndResultList,function(key2,val2){//再遍历每一页具体的表单
						$.each(val2,function(key3,val3){//对于null初始化为空格
							if(val2[key3] == null || val2[key3] == ""){
								val2[key3] = " ";
							}
						});



						if(val2.resultType == "输入"){
							$(".item-"+(key+1)).find("ul").append(
								"<li>"+
									"<label>"+val2.itemResultName+":</label>"+
									"<input "+
										" data-itemId='"+val2.itemId+"' "+
										" data-itemResultName='"+val2.itemResultName+"' "+
										" data-resultUnit='"+val2.resultUnit+"' "+
										" data-resultValueId='"+val2.resultValueId+"' "+
										" data-resultValue='"+val2.resultValue+"' "+
										" data-resultRemark='"+val2.resultRemark+"' "+
										" value='"+val2.resultValue+"' "+
									" type='text' />"+
									"<span class='resultUnit'>"+val2.resultUnit+"</span>"+
								"</li>"
							);			
						}else if(val2.resultType == "选择"){//选择类型的先插入列表li和select
							
							$(".item-"+(key+1)).find("ul").append(
								"<li data-type='"+val2.resultType+"' data-tag='"+val2.resultRemarkFlag+"'>"+
									"<label>"+val2.itemResultName+":</label>"+
									"<select id='spec_"+marki+"' "+
										" data-itemId='"+val2.itemId+"' "+
										" data-itemResultName='"+val2.itemResultName+"' "+
										" data-resultUnit='"+val2.resultUnit+"' "+
										" data-resultValueId='"+val2.resultValueId+"' "+
										" data-resultValue='"+val2.resultValue+"' "+
										" data-resultRemark='"+val2.resultRemark+"' "+
									"></select>"+
									"<span class='resultUnit'>"+val2.resultUnit+"</span>"+
								"</li>"
							);				
							var optionArr = val2.options.split(",");//拆分下拉框选项
							var markFlag = "style='display:none'";//初始textarea隐藏
							
							var remarkFlagArr = val2.resultRemarkFlag.toString().split(",");//拆分要显示textarea的选项
							
							if(val2.resultValue == " "){//没有值得情况
								$.each(optionArr,function(i,v){//遍历下拉选项
									if(i==0){//第一个下拉选项是要显示textarea的下拉选项之一，则显示textarea
										$.each(remarkFlagArr,function(a,b){
											if(b == v){
												markFlag = "";
											}
										});
									}
									$(".item-"+(key+1)).find("select:last").append(
										"<option>"+v+"</option>"
									);
								});							
							}else{//有值得情况
								$.each(optionArr,function(i,v){//遍历下拉选项插入option
									$(".item-"+(key+1)).find("select:last").append(
										"<option "+i+">"+v+"</option>"
									);
									
								});
								
								$(".item-"+(key+1)).find("select:last").find("option").filter(function(){//选中传递过来的值
									return $(this).text() == val2.resultValue;
								}).get(0).selected = true;		
								$.each(remarkFlagArr,function(c,d){
									if(d == val2.resultValue){
										markFlag = "";
									}
								});				
							}



							if(val2.resultRemark == " "){
								$(".item-"+(key+1)).find("ul").append(
									"<li "+markFlag+" class='desc' data-tag='"+val2.resultRemarkFlag+"'>"+
										"<label></label>"+
										"<textarea value='"+val2.resultRemark+"' placeholder='"+val2.itemResultName+"描述:'></textarea>"+
									"</li>"
								);							
							}else{
								$(".item-"+(key+1)).find("ul").append(
									"<li "+markFlag+" class='desc' data-tag='"+val2.resultRemarkFlag+"'>"+
										"<label></label>"+
										"<textarea placeholder='"+val2.itemResultName+"描述:'>"+val2.resultRemark+"</textarea>"+
									"</li>"
								);							
							}

							
						}
						marki++;
					});
				});
				$("#appendResultRecordingBox li[data-type = '选择'] select").change(function(){
					var _this = this;
					$(_this).parent().next().hide();
					if($(this).parent().data("tag").indexOf(",") != -1){

						var arr = $(this).parent().data("tag").split(",");
						$.each(arr,function(i,v){
							if(v==$(_this).val()){
								$(_this).parent().next().show();
							}
						});
					}else if($(this).val() == $(this).parent().data("tag")){
						$(this).parent().next().show();
					}else{
						$(this).parent().next().hide().find("textarea").html(" ");
					}
				});
				if(healthResultAjax.step == "first"){
					$("#resultTag ul li:first").attr("class","tagNow");
					$("#resultTag ul li:gt(0)").attr("class","tagNew");
					HealthResult.initialize(1);
				}else{
					var node = '';
					$("#resultTag ul li").each(function(key,val){
						if($(this).data("id") == healthResultAjax.step){
							node = key;
							return true;
						}					
					});
					$("#resultTag ul li:nth-child("+(node+1)+")").attr("class","tagNow");
					$("#resultTag ul li:lt("+node+")").attr("class","tagOld");
					$("#resultTag ul li:gt("+node+")").attr("class","tagNew");
					HealthResult.initialize((Number(node)+1));								
				}
				if(islastmark){
					$.ajax({
						url:urlWay.orderHost+'orderManager.action',
						cache:false,
						type:'post',
						data:{
							action:"getHealthInstruction",
							"orderInfo.id":healthResultAjax.searchId				
						},
						success:function(result){
							if(result.healthPathList == null){
								return false;
							}
							$(".resultRecordingStep:last").find("ul").append(
									"<li class='health_plan'>"+
										"<input onchange=\"HealthResult.selectAll(this,event)\" id='selectall' data-type='selectall' type='checkbox'>"+
										"<label for='selectall'>"+
									 		"<h6>全选</h6>"+
											"<p></p>"+
										"</label>"+
									"</li>"	
							);
							$.each(result.healthPathList,function(key,val){
								$(".resultRecordingStep:last").find("ul").append(
									"<li class='health_plan'>"+
										"<input id='check_num"+key+"' data-id='"+val.id+"' type='checkbox'>"+
										"<label for='check_num"+key+"'>"+
									 		"<h6>"+val.healthPathName+"</h6>"+
											"<p>"+val.comments+"</p>"+
										"</label>"+
									"</li>"			
								);
							});
			
						}
					});
				}
			}
		});

	},
	updateResultData:function(){//更新或插入结果数据
		var Data = {};
		var jsonArr = [];
		$(".resultRecordingStep:visible input,.resultRecordingStep:visible select").each(function(){
			var jsonData = {};
			jsonData.itemId = $(this).data("itemid");
			jsonData.itemResultName = $(this).data("itemresultname");
			jsonData.resultValueId = $(this).data("resultvalueid");
			jsonData.resultUnit = $(this).data("resultunit");
			jsonData.resultValue = $(this).val().trim();

			if($(this).parent().next().hasClass("desc")){
				jsonData.resultRemark = $(this).parent().next().find("textarea").val();
			}else{
				jsonData.resultRemark = " ";
			}
			$.each(jsonData,function(key,val){
				if(val == undefined || val == null || val == ""){
					jsonData[key] = " ";
				}
			});
			jsonArr.push(jsonData);
		});
		Data["orderNodeTrack.orderInfoId"] = this.searchId;
		Data["orderNodeTrack.flowNodeId"] = $("#resultTag li[class='tagNow']").data("id");
		Data["orderInfo.id"] = this.searchId;
		Data["action"] = "jumpFlowNode";
		Data["orderResultsJson"] = JSON.stringify(jsonArr);

		$.ajax({
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			async:false,
			data:Data,
			success:function(result){
				console.log(result);
			}		
		});
	},
	updateHealthDirect:function(){//更新健康指导
		var Data = {};
		var jsonArr = [];
		$(".resultRecordingStep:last").find("input:checked").filter(function(){
			return $(this).data("type") != "selectall";
		}).each(function(){
			var jsonData = {};
			jsonData["healthPathId"] = $(this).data("id");
			jsonArr.push(jsonData);
		});	
		
		Data["action"] = "saveOrUpdateHealthInstruction";
		Data["orderInfo.id"] = healthResultAjax.searchId;
		Data["healthPlanJson"] = JSON.stringify(jsonArr);
		
        //if($(".resultRecordingStep:last").find("input:checked").filter(function(){
        //        return $(this).data("type") != "selectall";
        //    }).length==0){
        //    ale('请下达健康指导');
        //    return false;
        //};
		$.ajax({
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			data:Data,
			async:false,
			success:function(result){
				if(result.mes == "成功"){
					healthResultAjax.finishOrder();
				}
			}		
		});	
	},
	finishOrder:function(){
		$.ajax({					
			url:urlWay.orderHost+'orderManager.action',
			cache:false,
			type:'post',
			data:{
				action:"finishOrder",
				"orderInfo.id":healthResultAjax.searchId				
			},
			success:function(result){
				if(result.mes == "成功"){
					
					ale("操作完成");

					setTimeout(function(){
						$(".header-left:first p").trigger("click");
					},2500);
				}
			},
		});	
	},
	nextOrder:function(){
		healthResultAjax.updateResultData();
		healthResultAjax.finishOrder();
	},
};





