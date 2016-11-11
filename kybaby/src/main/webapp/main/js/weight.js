/**
 * Created by Administrator on 2015/10/6.
 */
var curHeightValue;
var monthOld;

setInterval("get()",1);

$(function(){
    for(var i= 1;i<30;i++) {
        if(i<10){
            $(".box").append(
                    "<div class='block'><span class='kd'>"+i+"</span></div>"
            );
        }else{
            $(".box").append(
                    "<div class='block'><span class='kds'>"+i+"</span></div>"
            );
        }


    }

    for(var i=1;i<11;i++){
        if(i!=10){
            $(".block").append(
                "<div class='hm'></div>"
            );
        }else{
            $(".block").append(
                "<div class='hms'></div>"
            );
        }
    }


//    $(".block").first().css("border","0px")

//        $('.box').scrollTop( $('.box')[0].scrollHeight );
//         $(".box").click( function() {
//        ale($(".box").scrollLeft())
//    } );

    for(var i=0;i<9;i++){
        $('.block').eq(0).find('.hm').eq(i).css({opacity:0,filter:'alpha(opacity=0)'});
        $('.block:last').find('.hm').eq(i+1).css({opacity:0,filter:'alpha(opacity=0)'});
    }
    $('.block:last').find('.kds').css({opacity:0,filter:'alpha(opacity=0)'});
    $('.block:last').css({border:'none'});
    var arrBasicUrlSearch=window.location.search.substring(1).split("&");
		var proData;
		var minData;
		var maxData;
		var nowData;
    for(var i=0;i<arrBasicUrlSearch.length;i++){
    	if(arrBasicUrlSearch[i].split("=")[0]=="value"){
    		curWeightValue=arrBasicUrlSearch[i].split("=")[1];
    	}else if(arrBasicUrlSearch[i].split("=")[0]=="monthOld"){
    		monthOld=arrBasicUrlSearch[i].split("=")[1];
    	}else if(arrBasicUrlSearch[i].split("=")[0]=="range"){
        		proData=arrBasicUrlSearch[i].split("=")[1];

        		if(proData == undefined || proData == null || proData == 'undefined'){
        			
        		}else{
	         		minData=proData.split('~')[0];
	        		maxData=proData.split('~')[1];
	        		nowData=(((Math.round((curWeightValue)*100)/100-1.0)*159.96));

	        		$(".box").scrollLeft(nowData);
	        		$("#num").text(nowData);
        		}

      }
	     //new by houfei
    	else if(arrBasicUrlSearch[i].split("=")[0]=="changeId"){
    		changeId = arrBasicUrlSearch[i].split("=")[1];
    	}
    	//new by houfei
    }
			//new by houfei

			if(changeId != undefined){//说明是记录修改
				$('#saveOrChange').attr("onclick","changeRecord("+changeId+")");
			}
			//new by houfei 
    //$("#num").text(curWeightValue);
});
function get(){
    var a=(Math.round(($(".box").scrollLeft()/159.96+1.0)*10.0)/10.0).toString();
    if(a%1==0){
        a+=".0";
    }
    $("#num").text(a);
}

function addNewRecord(){
	var recordType="weight";
	var recordValue= $("#num").text();
	$.ajax({
		type:'post',
		url:host+'getUserInfo.action',
		cache:false,
        async:false, 
		data:{action:"addNewRecord",recordType:recordType,recordValue:recordValue,babyMonthYear:monthOld},
		success:function(result){
			if(result.mes="操作成功"){
				ale("记录成功");
        setTimeout(function(){
            return_before();
        },2500);				
			} else if(result.mes=="未登录"){
				ale("请先登录");
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
//new by houfei
function changeRecord(id){
	var recordType="weight";
	var recordValue= $("#num").text();
	$.ajax({
		type:'post',
		url:host+'getUserInfo.action',
		cache:false,
    async:false, 
    data:{
			action:"updateCord",
			recordType:recordType,
			recordValue:recordValue,
			recordId:id  	
    },
    success:function(result){
    	if(result.mes == '成功'){
				ale("记录成功");
        setTimeout(function(){
            return_before();
        },2500);
    	}
    },
		error: function () {
			layer();
		}
	});
}
//new by houfei