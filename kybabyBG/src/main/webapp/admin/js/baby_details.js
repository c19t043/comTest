$(function(){
	var search_key = window.location.search.substring(1);
	search_key = search_key.split('&&');
	//基本信息加载
	$.ajax({
		'url':'healthRecordData.action',
		'type':'POST',
		'async':false,
		'data':{
			'action':search_key[0],
			'userId':search_key[1]
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('返回错误');
				return false;
			}
			if(result.basicInfo == null){
				alert('没有数据');
				return false;
			}
			$('#baby_name').text(result.basicInfo[0][1]);
			$('#baby_sex').text(result.basicInfo[0][2]);
			$('#baby_birthday').text(result.basicInfo[0][3]);
			$('#baby_national').text(result.basicInfo[0][4].nation);
			$('#baby_origin').text(result.basicInfo[0][4].origin);
			$('#baby_parity').text(result.basicInfo[0][4].parity);
			$('#baby_para').text(result.basicInfo[0][4].para);
			$('#baby_gestationalAge').text(result.basicInfo[0][4].gestationalAge+"周");
			$('#baby_weight').text(result.basicInfo[0][4].birthWeight+"kg");
			$('#baby_height').text(result.basicInfo[0][4].birthBodyLength+"cm");
			$('#apgar_1').text(result.basicInfo[0][4].apgarOfOneMinute+"次");
			$('#apgar_5').text(result.basicInfo[0][4].apgarOfFiveMinutes+"次");
			$('#apgar_10').text(result.basicInfo[0][4].apgarOfTenMinutes+"次");
			$('#baby_bornway').text(result.basicInfo[0][4].bornWay);
			$('#embryoNum').text(result.basicInfo[0][4].embryoNumber);
			$('#baby_hospital').text(result.basicInfo[0][4].bornHospital);
			$('#baby_familyHistory').text(result.basicInfo[0][4].familyHistory);
			$('#drugAllergyHistory').text(result.basicInfo[0][4].drugAllergyHistory);
			$('#mother_name').text(result.basicInfo[0][4].motherName);
			$('#mother_height').text(result.basicInfo[0][4].heightOfMother+"cm");
			$('#mother_education').text(result.basicInfo[0][4].degreeOfMotherEducation);
			$('#mother_phone').text(result.basicInfo[0][4].phoneOfMother);
			$('#father_name').text(result.basicInfo[0][4].fatherName);
			$('#father_height').text(result.basicInfo[0][4].heightOfFather+"cm");
			$('#father_education').text(result.basicInfo[0][4].degreeOfFatherEducation);
			$('#father_phone').text(result.basicInfo[0][4].phoneOfFather);
			$('#genticHeight').text(result.basicInfo[0][4].geneticHeight+"cm");
			$('#twinBrotherAndSister').text(result.basicInfo[0][4].twinBrotherAndSister);
			$('#father_phone').text(result.basicInfo[0][4].phoneOfFather);
			$('#mainCaregivers').text(result.basicInfo[0][4].mainCaregivers);
			$('#detail_address').text(result.basicInfo[0][4].permanentAddress);
		}
	});
	//健康成长数据加载
	$.ajax({
		'url':'healthRecordData.action',
		'type':'POST',
		'async':false,
		'data':{
			'action':'getGrowthInfo',
			'userId':search_key[1]
		},
		success:function(result){
			if(result.growthInfo == null){
				alert('没有数据');
				return false;
			}
			for(var i=0;i < result.growthInfo.length;i ++){
				$('#data_a').append(
					"<h3>"+result.growthInfo[i].recordDate+"</h3>"+
					"<ul class='data_a_ul'>"+
						"<li><span>睡眠：</span><span>"+result.growthInfo[i].sleepHour+"小时</span></li>"+
						"<li><span>母乳：</span><span>"+result.growthInfo[i].breastfeedingTimes+"次&nbsp;"+result.growthInfo[i].everyBreastfeeding+"ml/次</span></li>"+
						"<li><span>辅食：</span><span>"+result.growthInfo[i].assistFoodsTimes+"次</span></li>"+
						"<li><span>排便次数：</span><span>"+result.growthInfo[i].defecateTimes+"次</span></li>"+
						"<li><span>运动小时：</span><span>"+result.growthInfo[i].exerciseTimes+"小时</span></li>"+
					"</ul>"			
				);			
			}
		}
	});
	//健康管理数据加载
	$.ajax({
		'url':'healthRecordData.action',
		'type':'POST',
		'async':false,
		'data':{
			'action':'geBabytProductResult',
			'userId':search_key[1]
		},
		success:function(result){
			if(result.babyProductResult == null){
				alert('没有数据');
				return false;
			}
			for(var i = 0;i < result.babyProductResult.length;i++){
				$('#data_b').append(
				"<h3>"+result.babyProductResult[i][0][1]+"</h3>"+
				"<p class='name'>儿保产品：<span>"+result.babyProductResult[i][0][2]+"</span></p>"+
				"<table id='tab_"+i+"'>"+
					"<tr>"+
						"<th>儿保产品项目</th>"+
						"<th>儿保项目结果</th>"+
						"<th>儿保计划</th>"+
						"<th>儿保健康计划路径</th>"+
						"<th>执行统计</th>"+
					"</tr>"+
				"</table>"			
				);
				for(var j=0;j < result.babyProductResult[i].length;j++){
					if((result.babyProductResult[i])[j][9].toString().indexOf('::') == -1){
						$("#tab_"+i).append(
							"<tr>"+
							"<td><span>"+(result.babyProductResult[i])[j][4]+"</span></td>"+
							"<td><span>"+(result.babyProductResult[i])[j][7]+(result.babyProductResult[i])[j][8]+"</span></td>"+
							"<td><span>"+(result.babyProductResult[i])[j][5]+"</span></td>"+
							"<td><span>"+(result.babyProductResult[i])[j][9]+"</span></td>"+
							"<td><span>"+(result.babyProductResult[i])[j][10]+"次</span></td>"+
						"</tr>"
						);					
					}else{
						var arr_way = (result.babyProductResult[i])[j][9].toString().split('::');
						var arr_num =	(result.babyProductResult[i])[j][10].toString().split('::');
						$("#tab_"+i).append(
							"<tr>"+
							"<td><span>"+(result.babyProductResult[i])[j][4]+"</span></td>"+
							"<td><span>"+(result.babyProductResult[i])[j][7]+(result.babyProductResult[i])[j][8]+"</span></td>"+
							"<td><span>"+(result.babyProductResult[i])[j][5]+"</span></td>"+
							"<td id='td_way_"+j+"'></td>"+
							"<td id='td_num_"+j+"'></td>"+
						"</tr>"
						);	
						for(var m=0;m < arr_way.length; m++){
							$("#td_way_"+j).append(
								"<span>"+arr_way[m]+"</span>"
							);
							$("#td_num_"+j).append(
								"<span>"+arr_num[m]+"</span>"
							);						
						}					
					}


				}
			}
		}
	});
	//病历夹
	$.ajax({
		'url':'healthRecordData.action',
		'type':'POST',
		'async':false,
		'data':{
			'action':'getBabyCaseClip',
			'userId':search_key[1]
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('错误');
				return false;
			}else if(result.babyCaseClip.length <= 0){
				alert('没有数据');
				return false;
			}
			for(var i = 0;i < result.babyCaseClip.length;i++){
				var status = 'block';
				if((result.babyCaseClip[i])[4] == null || (result.babyCaseClip[i])[4].trim() == ''){
					status = 'none';
				}
				if((result.babyCaseClip[i])[8].toString().indexOf('::') == -1){
					$('#data_c').append(
						"<h3>"+(result.babyCaseClip[i])[2]+"</h3>"+
						"<div style='display:"+status+"' class='img'>"+
//							"<img width='500px' height='400px' src='images/caseclipPicture/'"+(result.babyCaseClip[i])[4]+" />"+
							//update by fkn
							"<img width='500px' height='400px' src='images/caseclipPicture/"+(result.babyCaseClip[i])[4]+"' />"+
						"</div>"+
						"<div class='mark'>"+
							"<span>"+(result.babyCaseClip[i])[8]+"</span>"+
						"</div>"	
					);				
				}else{
					$('#data_c').append(
						"<h3>"+(result.babyCaseClip[i])[2]+"</h3>"+
						"<div style='display:"+status+"' class='img'>"+
							"<img width='500px' height='400px' src='images/caseclipPicture/"+(result.babyCaseClip[i])[4]+"' />"+
						"</div>"+
						"<div id='mark_tag_"+i+"' class='mark'>"+
						"</div>"	
					);
					var arr = (result.babyCaseClip[i])[8].toString().split('::');
					for(var j=0;j < arr.length;j++){
						$("#mark_tag_"+i).append(
						"<span>"+arr[j]+"</span>"
						);
					}				
				}

				
				
			}
		}
	});
});