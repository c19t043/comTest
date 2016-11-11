/**
 * Created by vinny on 2015/9/24.
 */
$(function(){
	$('.time_table').empty();
	$(".add_box .add_box_left p.icon_point").addClass("on");
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'setServiceTime.action',
		data : {action:"getAll"},
		success : function(result) {
			//result.doctorAddress;result.timeInit;result.dateNowStr
			if(result.doctorAddress==null){
				$('#header').hide();
				$('#container').hide();
				ale("请到个人地址管理设置服务地址！");
        setTimeout(function(){
          window.location.href="consultation.html";
        },2500);
				
			}
			
			for(var i=0; i < 14 ; i++){
				var a = (result.dateNowStr[i]).toString();
				var b = new Date(a).getDay();
				var c;
				switch (b) {
				case 0:
					c = '星期日'
					break;
				case 1:
					c = '星期一'
					break;
				case 2:
					c = '星期二'
					break;
				case 3:
					c = '星期三'
					break;
				case 4:
					c = '星期四'
					break;
				case 5:
					c = '星期五'
					break;
				case 6:
					c = '星期六'
					break;
				}
				if(i == 0){
					c = '今天';
				}else if(i == 1){
					c = '明天';
				}else if(i == 2){
					c = '后天';
				}
				$('.time_table').append(
				  "<tr id='show_tr_"+i+"'>"+
				    "<td><p>"+c+"</p><p class='p_date'>"+a.substring(5).replace('-','/')+"</p></td>"+
				  "</tr>"
				);
				for(var j=0;j <result.timeInit.length;j++){
					if(i == 0){
						$("#show_tr_"+i).append(
						 "<td zjtime='"+a.substring(5)+"' class='td_select_light_' zjid='"+result.timeInit[j].id+"'><p>"+result.timeInit[j].name+"</p><p>"+result.timeInit[j].startTime+":00-"+result.timeInit[j].endTime+":00</p></td>"
						);
						//$("#show_tr_"+i).hide();
					}else{
						$("#show_tr_"+i).append(
								"<td zjtime='"+a.substring(5)+"' class='td_select_light_' zjid='"+result.timeInit[j].id+"'><p>"+result.timeInit[j].name+"</p><p>"+result.timeInit[j].startTime+":00-"+result.timeInit[j].endTime+":00</p></td>"						
								// "<td class='td_select_light_' zjid='"+a.substring(5)+"&&"+result.timeInit[j].id+"'><p>"+result.timeInit[j].name+"</p><p>"+result.timeInit[j].startTime+":00-"+result.timeInit[j].endTime+":00</p></td>"						
						);						
					}

				}
			}

				for(var k=0;k<result.doctorAddress.length;k++){
					var city=result.doctorAddress[k].doctorCity;
					if(city.indexOf('市')<0){
						city=city+'市';
					}
					var area=result.doctorAddress[k].doctorArea;
					if(area.indexOf('区')<0){
						area=area+'区';
					}
					$("#addressList").append("<div class='add_box'>"+
							"<div id='address_selected_"+k+"' onclick='address_select(this)' zjid='"+result.doctorAddress[k].id+"' class='add_box_left'>"+
							"<p class='icon_point'></p>"+
							"<div class='add_details'>"+
						"<p><span>"+result.doctorAddress[k].doctorProvince+"省</span><span>"+city+"</span><span>"+area+"</span></p>"+
						"<p>"+result.doctorAddress[k].doctorStreet+result.doctorAddress[k].detailAddress+"</p>"+
					"</div>"+
				"</div>"+
				"<div class='add_box_right'>&gt;</div>"+
			"</div>"+
			"<p class='gray_1'></p>");
					if(result.doctorAddress[k].addressStatus == 'Y'){
						$("#address_selected_"+k).trigger('click');
					}		
				}
		}
	});

});
function address_select(obj){
	$('.time_table td').removeClass('select');
	$.ajax({
		type : 'post',
		async: false ,
		url : host+'setServiceTime.action',
		
		//updated by zhong at 2015-11-01.别的地址选中的时间,在当前地址中显示为黄色
		//data : {action:"selected",'addressId':$(obj).attr('zjid'),},
		data : {action:"selectedTime"},
		success:function(result){
			$('.icon_point').css('background','').attr('mark_s','N');
			$(obj).find('p:first').css('background','#4fc1e9').attr('mark_s','Y');;
			if(result.doctorProductList == null){
				return false;
			}

			for(var i=0;i<result.doctorProductList.length;i++){
				var tr_light = $('.p_date').filter(function(){
					return $(this).text() == result.doctorProductList[i].serviceDate.toString().substring(5).replace('-','/');
				}).parent().parent();
				
				
				var td_light = tr_light.find('td').filter(function(){
					return $(this).attr('zjid')== result.doctorProductList[i].timeInitId;
				});
				
				
				//updated by zhong at 2015-11-01.别的地址选中的时间,在当前地址中显示为黄色
				/*if(result.doctorProductList[i].isProvide=="Y"){
					td_light.addClass('select_yellow');
				}else{
					td_light.addClass('select');
				}*/
				
				if(result.doctorProductList[i].isProvide=="Y"){
					td_light.addClass('select_yellow');
				} else if(result.doctorProductList[i].isProvide=="N"&&result.doctorProductList[i].addressId!=$(obj).attr('zjid')){
					td_light.addClass('select_yellow');
				}
				else{
					td_light.addClass('select');
				}
				
			}
		}
	});
}


var someTimeStr;//设置服务时间拼接字符串


$(function(){
	$('.td_select_light_').click(function(){
		if($(this).hasClass('select_yellow')){
			return false;
		}
		if($(this).hasClass('select')){
			$(this).removeClass('select');
		}else{
			$(this).addClass('select');
		}		
		var arr_string='';
		var address_id = $('.add_box_left p').filter(function(){
			return $(this).attr('mark_s') == 'Y';
		}).parent().attr('zjid');

	    var mark = $('.td_select_light_').filter(function(){
	    	return $(this).hasClass('select');
	    });
	    for(var i=0;i < mark.length;i++){
	    	arr_string = arr_string + "::" + mark.eq(i).attr('zjid')+"&&"+mark.eq(i).attr('zjtime');
	    }		
		
	    someTimeStr = address_id+"@@"+arr_string.substring(2);
//	    ale(someTimeStr);
	});
});

function saveSomeTime(){
	
	if(someTimeStr!=undefined){
		var str_check = someTimeStr.split("@@");
		if(str_check[0]== "undefined"){
			ale("请选择地址");
		}else{
			$.ajax({
				type : 'post',
				async: false ,
				url : host+'setServiceTime.action',
				data : {
					action:"setSome",
					'someTimeStr':someTimeStr,
					},
				success:function(result){
					if(result.mes=="成功"){
						ale("设置成功");
					}
				}
			});
		}
	}else{
		ale("您还没有做更改!");
	}
}