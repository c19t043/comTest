$(function(){
	healthArchive();
    $('.header-right>span').click(function () {
        $('#babyHealth').show();
    });
	//healthArchive_new();
});
//new
function healthArchive_new(){
    $.ajax({
        type:'post',
        url:host+'getOrderResultInfo.action',
        cache:false,
        async:false,
        data:{action:"healthArchive"},
        success:function(result){
            if(result.mes=="操作成功"||result.mes=="无执行情况"){
                /*ale("头像是："+result.userImage);
                 ale("宝宝姓名是："+result.babyName);
                 ale("宝宝性别是："+result.babySex);
                 ale("宝宝生日是："+result.babyBirthday);
                 ale("宝宝月龄是："+result.babyMonthYear);
                 var basicData=result.basicData;

                 ale("以下是基础健康数据");
                 ale("宝宝的姓名是："+basicData.babyName);
                 ale("宝宝的性别是："+basicData.babySex);

                 ale("以下是健康档案数据");
                 for(var i=0;i<result.dateStrList.length;i++){
                 ale("第"+(i+1)+"个日期是："+result.dateStrList[i]);
                 ale("第"+(i+1)+"个产品名称是："+result.productNameList[i]);
                 for(var j=0;j<result.prodcutItemNameList[i].length;j++){
                 ale("第"+(j+1)+"个项目名称是："+result.prodcutItemNameList[i][j]);
                 ale("第"+(j+1)+"个项目结果是："+result.productItemResultList[i][j]);
                 }
                 for(var j=0;j<result.healthPlanNameList[i].length;j++){
                 ale("第"+(j+1)+"个健康计划名称是："+result.healthPlanNameList[i][j]);
                 for(var k=0;k<result.healthPathNameList[i][j].length;k++){
                 ale("第"+(k+1)+"个健康计划名称是："+result.healthPathNameList[i][j][k]);
                 ale("第"+(k+1)+"个健康计划执行次数是："+result.healthPathAmountList[i][j][k]);
                 }
                 }
                 }*/

                var basicData=result.basicData;
                if(result.userImage == null || result.userImage == 'null' || result.userImage == ''){
                    result.userImage = 'lt_user.png'
                }
                if(basicData!=null){


                    $("#container").append(
                        "    <p class='gray_2'></p>"+
                        "    <div class='row'>"+
                        "        <div class='img_box'>"+
                        "            <img src='"+hostBG+"images/userFaceIcon/"+result.userImage+"'>"+
                        "        </div>"+
                        "        <div class='text_box'>"+
                        "            <span>宝宝姓名：</span><span>"+result.babyName+"</span><br/>"+
                        "            <span>宝宝性别：</span><span>"+result.babySex+"</span><br/>"+
                        "            <span>出生日期：</span><span>"+result.babyBirthday+"</span><br/>"+
                        "        </div>"+
                        "        <div class='nl_box'>"+
                        "            <span>"+result.babyMonthYear+"个月</span>"+
                        "        </div>"+
                        "    </div>"+
                        "    <p class='gray_1'></p>"+
                        ""+
                        "    <!--订单详情-->"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>宝宝姓名：</span>"+
                        "        <input value='"+result.babyName+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>宝宝性别：</span>"+
                        "        <input value='"+result.babySex+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>民族：</span>"+
                        "        <input value='"+basicData.nation+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>籍贯：</span>"+
                        "         <input value='"+basicData.origin+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>胎次：</span>"+
                        "        <input value='"+basicData.parity+"'/>"+
                        "        <span class='zzc_grey'>次</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>产次：</span>"+
                        "        <input value='"+basicData.para+"'/>"+
                        "        <spna class='zzc_grey'>次</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>胎龄：</span>"+
                        "        <input value='"+basicData.gestationalAge+"'/>"+
                        "        <spna class='zzc_grey'>周</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>出生体重：</span>"+
                        "        <input value='"+basicData.birthWeight+"'/>"+
                        "        <spna class='zzc_grey'>kg</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>出生身长：</span>"+
                        "        <input value='"+basicData.birthBodyLength+"'/>"+
                        "        <spna class='zzc_grey'>cm</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>1分钟的apgar：</span>"+
                        "        <input value='"+basicData.apgarOfOneMinute+"'/>"+
                        "        <spna class='zzc_grey'>分</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>5分钟的apgar：</span>"+
                        "        <input value='"+basicData.apgarOfFiveMinutes+"'/>"+
                        "        <spna class='zzc_grey'>分</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>10分钟的apgar：</span>"+
                        "        <input value='"+basicData.apgarOfTenMinutes+"'/>"+
                        "        <spna class='zzc_grey'>分</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>生产方式：</span>"+
                        "        <input value='"+basicData.bornWay+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>出生医院：</span>"+
                        "        <input value='"+basicData.bornHospital+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>家族病史：</span>"+
                        "        <input value='"+basicData.familyHistory+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>药物过敏史：</span>"+
                        "        <input value='"+basicData.drugAllergyHistory+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲姓名：</span>"+
                        "        <input value='"+basicData.motherName+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲身高：</span>"+
                        "        <input value='"+basicData.heightOfMother+"'/>"+
                        "        <span class='zzc_grey'>cm</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲文化程度：</span>"+
                        "        <input value='"+basicData.degreeOfMotherEducation+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲联系电话：</span>"+
                        "        <input value='"+basicData.phoneOfMother+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲姓名：</span>"+
                        "        <input value='"+basicData.fatherName+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲身高：</span>"+
                        "        <input value='"+basicData.heightOfFather+"'/>"+
                        "        <span class='zzc_grey'>cm</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲文化程度：</span>"+
                        "        <input value='"+basicData.degreeOfFatherEducation+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲联系电话：</span>"+
                        "        <input value='"+basicData.phoneOfFather+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>遗传身高：</span>"+
                        "        <input value='"+basicData.geneticHeight+"'/>"+
                        "        <span class='zzc_grey'>cm</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>同胞兄弟姐妹：</span>"+
                        "        <input value='"+basicData.twinBrotherAndSister+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>常住地址：</span>"+
                        "        <input value='"+basicData.permanentAddress+"'/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>主要照看人：</span>"+
                        "        <input value='"+basicData.mainCaregivers+"'/>"+
                        "    </div>"
                    );
                }
                else{
                    $("#container").append(
                        "    <p class='gray_2'></p>"+
                        "    <div class='row'>"+
                        "        <div class='img_box'>"+
                        "            <img src='"+hostBG+"images/userFaceIcon/"+result.userImage+"'>"+
                        "        </div>"+
                        "        <div class='text_box'>"+
                        "            <span>宝宝姓名：</span><span>"+result.babyName+"</span><br/>"+
                        "            <span>宝宝性别：</span><span>"+result.babySex+"</span><br/>"+
                        "            <span>出生日期：</span><span>"+result.babyBirthday+"</span><br/>"+
                        "        </div>"+
                        "        <div class='nl_box'>"+
                        "            <span>"+result.babyMonthYear+"个月</span>"+
                        "        </div>"+
                        "    </div>"+
                        "    <p class='gray_1'></p>"+
                        ""+
                        "    <!--订单详情-->"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>宝宝姓名：</span>"+
                        "        <span>"+result.babyName+"</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>宝宝性别：</span>"+
                        "        <span>"+result.babySex+"</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>民族：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>籍贯：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>胎次：</span>"+
                        "         <input value=''/>"+
                        "        <span class='zzc_grey'>次</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>产次：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>次</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>胎龄：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>周</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>出生体重：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>kg</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>出生身长：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>cm</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>1分钟的apgar：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>分</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>5分钟的apgar：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>分</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>10分钟的apgar：</span>"+
                        "         <input value=''/>"+
                        "        <spna class='zzc_grey'>分</spna>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>生产方式：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>出生医院：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>家族病史：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>药物过敏史：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲姓名：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲身高：</span>"+
                        "         <input value=''/>"+
                        "        <span class='zzc_grey'>cm</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲文化程度：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>母亲联系电话：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲姓名：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲身高：</span>"+
                        "         <input value=''/>"+
                        "        <span class='zzc_grey'>cm</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲文化程度：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>父亲联系电话：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>遗传身高：</span>"+
                        "         <input value=''/>"+
                        "        <span class='zzc_grey'>cm</span>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>同胞兄弟姐妹：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>常住地址：</span>"+
                        "         <input value=''/>"+
                        "    </div>"+
                        "    <p class='gray_2'></p>"+
                        "    <div class='zzc'>"+
                        "        <span class='zzc_t1'>主要照看人：</span>"+
                        "         <input value=''/>"+
                        "    </div>"
                    );

                }
                flag=0;
                if(result.dateStrList!=null){
                    for(var i=0;i<result.dateStrList.length;i++){
                        if(result.dateStrList[i]!=null){
                            flag=1;
                            var strResult="   <label id='ts'>"+result.dateStrList[i]+"</label>"+
                                "    <div>"+
                                "        <label>产品名称</label><span class='text left' >"+result.productNameList[i]+"</span>"+
                                "    </div>"+
                                "    <div>"+
                                "        <label>产品项目</label>"+
                                "        <div class='right'>";

                            for(var j=0;j<result.prodcutItemNameList[i].length;j++){
                                if(j == result.prodcutItemNameList[i].length-1){
                                    strResult+="<span class='text'>"+result.prodcutItemNameList[i][j].substring(10)+"</span><span class='text'>"+result.productItemResultList[i][j]+"</span><br/>";
                                }else{
                                    strResult+="<span class='text'>"+result.prodcutItemNameList[i][j]+"</span><span class='text'>"+result.productItemResultList[i][j]+"</span><br/>";
                                }
                            }

                            strResult+=	"</div></div>";

                            if(result.healthPlanNameList[i]!=null){
                                strResult+="<div>";

                                for(var j=0;j<result.healthPlanNameList[i].length;j++){
                                    if(j==0){
                                        strResult+=
                                            "        <label>健康计划</label>"+
                                            "        <div class='right1'>"+
                                            "            <span class='texts'>"+result.healthPlanNameList[i][j].substring(10)+"</span>"+
                                            "        </div>"+
                                            "        <div class='right2'>";
                                        for(var k=0;k<result.healthPathNameList[i][j].length;k++){
                                            strResult+="<span class='text1'>"+result.healthPathNameList[i][j][k]+"</span><span class='text2'>执行次数（"+result.healthPathAmountList[i][j][k]+"）</span><br/>";
                                        }
                                        strResult+=
                                            "        </div>";
                                    } else{
                                        strResult+=
                                            "        <label>&nbsp;</label>"+
                                            "        <div class='right1'>"+
                                            "            <span class='texts'>"+result.healthPlanNameList[i][j]+"</span>"+
                                            "        </div>"+
                                            "        <div class='right2'>";
                                        for(var k=0;k<result.healthPathNameList[i][j].length;k++){
                                            strResult+="<span class='text1'>"+result.healthPathNameList[i][j][k]+"</span><span class='text2'>执行次数（"+result.healthPathAmountList[i][j][k]+"）</span><br/>";
                                        }
                                        strResult+=
                                            "        </div>";
                                    }
                                }
                                strResult+="</div>";
                            }


                            $("#healthRecordList").append(strResult);
                        }
                    }

                    if(flag==0){
                        $("#healthRecordList").append("暂时无执行情况");
                    }

                }

            }else if(result.mes=="无执行情况"){
                ale("暂时无执行情况");
            }else if(result.mes=="未登录"){
                ale("请先登录");
                setTimeout(function(){
                    window.location.href="login.html";
                },2500);
            }
        },
        error:function(){
            layer();
        }
    });
}
//查看健康档案
function healthArchive(){
	$.ajax({
		type:'post',
		url:host+'getOrderResultInfo.action',
		cache:false,
        async:false, 
		data:{action:"healthArchive"},
		success:function(result){
			if(result.mes=="操作成功"||result.mes=="无执行情况"){
				/*ale("头像是："+result.userImage);
				ale("宝宝姓名是："+result.babyName);
				ale("宝宝性别是："+result.babySex);
				ale("宝宝生日是："+result.babyBirthday);
				ale("宝宝月龄是："+result.babyMonthYear);
				var basicData=result.basicData;
				
				ale("以下是基础健康数据");
				ale("宝宝的姓名是："+basicData.babyName);
				ale("宝宝的性别是："+basicData.babySex);
				
				ale("以下是健康档案数据");
				for(var i=0;i<result.dateStrList.length;i++){
					ale("第"+(i+1)+"个日期是："+result.dateStrList[i]);
					ale("第"+(i+1)+"个产品名称是："+result.productNameList[i]);
					for(var j=0;j<result.prodcutItemNameList[i].length;j++){
						ale("第"+(j+1)+"个项目名称是："+result.prodcutItemNameList[i][j]);
						ale("第"+(j+1)+"个项目结果是："+result.productItemResultList[i][j]);
					}
					for(var j=0;j<result.healthPlanNameList[i].length;j++){
						ale("第"+(j+1)+"个健康计划名称是："+result.healthPlanNameList[i][j]);
						for(var k=0;k<result.healthPathNameList[i][j].length;k++){
							ale("第"+(k+1)+"个健康计划名称是："+result.healthPathNameList[i][j][k]);
							ale("第"+(k+1)+"个健康计划执行次数是："+result.healthPathAmountList[i][j][k]);
						}
					}
				}*/
				
				var basicData=result.basicData;
				if(result.userImage == null || result.userImage == 'null' || result.userImage == ''){
					result.userImage = 'lt_user.png'
				}
				if(basicData!=null){
					
				
				$("#container").append(
				"    <p class='gray_2'></p>"+
				"    <div class='row'>"+
				"        <div class='img_box'>"+
				"            <img src='"+hostBG+"images/userFaceIcon/"+result.userImage+"'>"+
				"        </div>"+
				"        <div class='text_box'>"+
				"            <span>宝宝姓名：</span><span>"+result.babyName+"</span><br/>"+
				"            <span>宝宝性别：</span><span>"+result.babySex+"</span><br/>"+
				"            <span>出生日期：</span><span>"+result.babyBirthday+"</span><br/>"+
				"        </div>"+
				"        <div class='nl_box'>"+
				"            <span>"+result.babyMonthYear+"个月</span>"+
				"        </div>"+
				"    </div>"+
				"    <p class='gray_1'></p>"+
				""+
				"    <!--订单详情-->"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>宝宝姓名：</span>"+
				"        <span>"+result.babyName+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>宝宝性别：</span>"+
				"        <span>"+result.babySex+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>民族：</span>"+
				"         <span>"+basicData.nation+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>籍贯：</span>"+
				"         <span>"+basicData.origin+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>胎次：</span>"+
				"        <span>"+basicData.parity+"</span>"+
				"        <span class='zzc_grey'>次</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>产次：</span>"+
				"        <span>"+basicData.para+"</span>"+
				"        <spna class='zzc_grey'>次</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>胎龄：</span>"+
				"        <span>"+basicData.gestationalAge+"</span>"+
				"        <spna class='zzc_grey'>周</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>出生体重：</span>"+
				"        <span>"+basicData.birthWeight+"</span>"+
				"        <spna class='zzc_grey'>kg</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>出生身长：</span>"+
				"        <span>"+basicData.birthBodyLength+"</span>"+
				"        <spna class='zzc_grey'>cm</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>1分钟的apgar：</span>"+
				"        <span>"+basicData.apgarOfOneMinute+"</span>"+
				"        <spna class='zzc_grey'>分</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>5分钟的apgar：</span>"+
				"        <span>"+basicData.apgarOfFiveMinutes+"</span>"+
				"        <spna class='zzc_grey'>分</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>10分钟的apgar：</span>"+
				"        <span>"+basicData.apgarOfTenMinutes+"</span>"+
				"        <spna class='zzc_grey'>分</spna>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>生产方式：</span>"+
				"        <span>"+basicData.bornWay+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>出生医院：</span>"+
				"        <span>"+basicData.bornHospital+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>家族病史：</span>"+
				"        <span>"+basicData.familyHistory+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>药物过敏史：</span>"+
				"        <span>"+basicData.drugAllergyHistory+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>母亲姓名：</span>"+
				"        <span>"+basicData.motherName+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>母亲身高：</span>"+
				"        <span>"+basicData.heightOfMother+"</span>"+
				"        <span class='zzc_grey'>cm</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>母亲文化程度：</span>"+
				"        <span>"+basicData.degreeOfMotherEducation+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>母亲联系电话：</span>"+
				"        <span>"+basicData.phoneOfMother+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>父亲姓名：</span>"+
				"        <span>"+basicData.fatherName+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>父亲身高：</span>"+
				"        <span>"+basicData.heightOfFather+"</span>"+
				"        <span class='zzc_grey'>cm</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>父亲文化程度：</span>"+
				"        <span>"+basicData.degreeOfFatherEducation+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>父亲联系电话：</span>"+
				"        <span>"+basicData.phoneOfFather+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>遗传身高：</span>"+
				"        <span>"+basicData.geneticHeight+"</span>"+
				"        <span class='zzc_grey'>cm</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>同胞兄弟姐妹：</span>"+
				"        <span>"+basicData.twinBrotherAndSister+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>常住地址：</span>"+
				"        <span>"+basicData.permanentAddress+"</span>"+
				"    </div>"+
				"    <p class='gray_2'></p>"+
				"    <div class='zzc'>"+
				"        <span class='zzc_t1'>主要照看人：</span>"+
				"        <span>"+basicData.mainCaregivers+"</span>"+
				"    </div>"
				);
				}
				else{
					$("#container").append(
							"    <p class='gray_2'></p>"+
							"    <div class='row'>"+
							"        <div class='img_box'>"+
							"            <img src='"+hostBG+"images/userFaceIcon/"+result.userImage+"'>"+
							"        </div>"+
							"        <div class='text_box'>"+
							"            <span>宝宝姓名：</span><span>"+result.babyName+"</span><br/>"+
							"            <span>宝宝性别：</span><span>"+result.babySex+"</span><br/>"+
							"            <span>出生日期：</span><span>"+result.babyBirthday+"</span><br/>"+
							"        </div>"+
							"        <div class='nl_box'>"+
							"            <span>"+result.babyMonthYear+"个月</span>"+
							"        </div>"+
							"    </div>"+
							"    <p class='gray_1'></p>"+
							""+
							"    <!--订单详情-->"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>宝宝姓名：</span>"+
							"        <span>"+result.babyName+"</span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>宝宝性别：</span>"+
							"        <span>"+result.babySex+"</span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>民族：</span>"+
							"         <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>籍贯：</span>"+
							"         <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>胎次：</span>"+
							"        <span></span>"+
							"        <span class='zzc_grey'>次</span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>产次：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>次</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>胎龄：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>周</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>出生体重：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>kg</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>出生身长：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>cm</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>1分钟的apgar：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>分</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>5分钟的apgar：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>分</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>10分钟的apgar：</span>"+
							"        <span></span>"+
							"        <spna class='zzc_grey'>分</spna>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>生产方式：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>出生医院：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>家族病史：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>药物过敏史：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>母亲姓名：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>母亲身高：</span>"+
							"        <span></span>"+
							"        <span class='zzc_grey'>cm</span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>母亲文化程度：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>母亲联系电话：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>父亲姓名：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>父亲身高：</span>"+
							"        <span></span>"+
							"        <span class='zzc_grey'>cm</span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>父亲文化程度：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>父亲联系电话：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>遗传身高：</span>"+
							"        <span></span>"+
							"        <span class='zzc_grey'>cm</span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>同胞兄弟姐妹：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>常住地址：</span>"+
							"        <span></span>"+
							"    </div>"+
							"    <p class='gray_2'></p>"+
							"    <div class='zzc'>"+
							"        <span class='zzc_t1'>主要照看人：</span>"+
							"        <span></span>"+
							"    </div>"
							);
							
				}
				flag=0;
				if(result.dateStrList!=null){
					for(var i=0;i<result.dateStrList.length;i++){
						if(result.dateStrList[i]!=null){
							flag=1;
							var strResult="   <label id='ts'>"+result.dateStrList[i]+"</label>"+
							"    <div>"+
							"        <label>产品名称</label><span class='text left' >"+result.productNameList[i]+"</span>"+
							"    </div>"+
							"    <div>"+
							"        <label>产品项目</label>"+
							"        <div class='right'>";
							
							for(var j=0;j<result.prodcutItemNameList[i].length;j++){
								if(j == result.prodcutItemNameList[i].length-1){
									strResult+="<span class='text'>"+result.prodcutItemNameList[i][j].substring(10)+"</span><span class='text'>"+result.productItemResultList[i][j]+"</span><br/>";
								}else{
									strResult+="<span class='text'>"+result.prodcutItemNameList[i][j]+"</span><span class='text'>"+result.productItemResultList[i][j]+"</span><br/>";
								}
							}
							
							strResult+=	"</div></div>";
							
							if(result.healthPlanNameList[i]!=null){
								strResult+="<div>";
								
								for(var j=0;j<result.healthPlanNameList[i].length;j++){
									if(j==0){
										strResult+=
										"        <label>健康计划</label>"+
										"        <div class='right1'>"+
										"            <span class='texts'>"+result.healthPlanNameList[i][j].substring(10)+"</span>"+
										"        </div>"+
										"        <div class='right2'>";
										for(var k=0;k<result.healthPathNameList[i][j].length;k++){
											strResult+="<span class='text1'>"+result.healthPathNameList[i][j][k]+"</span><span class='text2'>执行次数（"+result.healthPathAmountList[i][j][k]+"）</span><br/>";
										}
										strResult+=
										"        </div>";
									} else{
										strResult+=
										"        <label>&nbsp;</label>"+
										"        <div class='right1'>"+
										"            <span class='texts'>"+result.healthPlanNameList[i][j]+"</span>"+
										"        </div>"+
										"        <div class='right2'>";
										for(var k=0;k<result.healthPathNameList[i][j].length;k++){
											strResult+="<span class='text1'>"+result.healthPathNameList[i][j][k]+"</span><span class='text2'>执行次数（"+result.healthPathAmountList[i][j][k]+"）</span><br/>";
										}
										strResult+=
										"        </div>";
									}
								}
								strResult+="</div>";
							}
							
							
							$("#healthRecordList").append(strResult);
						}
					}
					
					if(flag==0){
						$("#healthRecordList").append("暂时无执行情况");
					}
					
				}
				
			}else if(result.mes=="无执行情况"){
				ale("暂时无执行情况");
			}else if(result.mes=="未登录"){
				ale("请先登录");				
        setTimeout(function(){
          window.location.href="login.html";
        },2500);
			}
		},
        error:function(){
            layer();
        }
	});
}