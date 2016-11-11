$(function(){
	getHistAche();
});

function getHistAche(){
	$.ajax({
		type:'post',
		url:host+'getAcheInfo.action',
		cache:false,
        async:false, 
		data:{action:"getHis"},
		success:function(result){
			if(result.mes=="操作成功"){
				/*ale("头像是："+result.userImage);
				ale("宝宝姓名是："+result.babyName);
				ale("宝宝性别是："+result.babySex);
				ale("宝宝生日是："+result.babyBirthday);
				ale("宝宝月龄是："+result.babyMonthYear);*/
				
				if(result.userImage == ''||result.userImage == null || result.userImage == undefined){
					result.userImage = 'lt_user.png';
				}
				
				$("#babyBasicInfo").html(
						"<div class='info_left'><img src='"+hostBG+"images/userFaceIcon/"+result.userImage+"'></div>"+
						"		<div class='info_center'>"+
						"			<p>宝宝姓名："+result.babyName+"</p>"+
						"			<p>宝宝性别："+result.babySex+"</p>"+
						"			<p>出生日期："+result.babyBirthday+"</p>"+
						"		</div>"+
						"		<div class='info_right'><p><span>"+result.babyMonthYear+"</span>个月</p></div>"	
				);
				for(var i=0;i<result.histCaseClipList.length;i++){
					
					var tagLists="";
					var zhengzhuangName="";
					var strImage="";
					if(result.histTagList[i]!=null){
						for(var j=0;j<result.histTagList[i].length;j++){
							//ale("第"+(j+1)+"个标签是："+(result.histTagList[i][j]).symptomName);
							tagLists+="<p class='button_11'>"+(result.histTagList[i][j]).symptomName+"</p>";
						}
					}
					var clips=result.histCaseClipList[i];
					if(clips.symptomImage!=null&&clips.symptomImage!="null"&&clips.symptomImage!=""){
						//ale("症状图为"+clips.symptomImage);
						zhengzhuangName="症状图";
						strImage=clips.symptomImage;
					}
					if(clips.prescribeImage!=null&&clips.prescribeImage!="null"&&clips.prescribeImage!=""){
						//ale("处方图为"+clips.prescribeImage);
						zhengzhuangName="处方图";
						strImage=clips.prescribeImage;
					}
					if(clips.drugImage!=null&&clips.drugImage!="null"&&clips.drugImage!=""){
						//ale("药物图为"+clips.drugImage);
						zhengzhuangName="药物图";
						strImage=clips.drugImage;
					}
					
					if(strImage==""){
						$("#caseHistData").append(
								"	<div class='case_com clearfix'>"+
								"		<p class='case_orna'></p>"+
								"		<div class='case_sub'>"+
								"			<p class='date'>"+clips.submitTime+"</p>"+
								tagLists+
								"		</div>"+
								"	</div>"	
						);
					} else {
						$("#caseHistData").append(
								"<div class='case_com clearfix'> "+
								"		<p class='case_orna'></p>"+
								"		<div class='case_sub'>"+
                                "			<p class='date'>"+clips.submitTime+"</p>"+
                                tagLists+
								"		</div>"+
								//"	</div>"+
								//"	<div class='case_com clearfix'>"+
								"		<p class='case_orna'></p>"+
								"		<div class='case_sub'>"+
								//"			<p class='date'>"+clips.submitTime+"</p>"+
                                "			<p class='case_img showlargeimage'><img src='"+hostBG+"images/caseclipPicture/"+strImage+"'/></p>"+
                                "			<p>"+zhengzhuangName+"</p>"+
                                "		</div>"+
								"	</div>"	
						);
					}
					
					
				}
			}else if(result.mes=="无病历夹"){
				//ale("暂时还没有病历夹");
			} else if(result.mes=="未登录"){
				ale("您还没有登录");
                setTimeout(function(){
          window.location.href="login.html";
        },2500);
				
			}
		},
		error: function () {
			layer();
		}
	});
}