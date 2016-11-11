/**
 * Created by Administrator on 2015/9/29.
 */

var curHeightValue;
var monthOld;
var recordData;
setInterval("get()",1);
$(function(){
    for(var i= 1;i<115;i++) {
            $(".box>div").append(
                    "<div class='block'></div><div class='blocks'></div><span class='kd' style='top: "+((i-1)*110+93)+"px'>"+(115-i+20)+"</span>"
            );
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
    for(var i=1;i<11;i++){
        if(i!=10){
            $(".blocks").append(
                "<div class='hm' style='float: right'></div>"
            );
        }else{
            $(".blocks").append(
                "<div class='hms'></div>"
            );
        }
    }
//         $(".blocks").last().css("border","0px")
//        $('.box>div').scrollTop( $('.box>div')[0].scrollHeight );

//    $(".box").click( function() {
//        ale($(".box").scrollTop())
//    } );
        for(var i=0;i<7;i++){
            $('.block').eq(0).find('.hm').eq(i).css({opacity:0,filter:'alpha(opacity=0)'});
            $('.blocks').eq(0).find('.hm').eq(i).css({opacity:0,filter:'alpha(opacity=0)'});
        }
        $('.block').eq(112).css({opacity:0,filter:'alpha(opacity=0)'});
        $('.block').eq(113).css({opacity:0,filter:'alpha(opacity=0)'});
    $('.blocks').eq(113).css({opacity:0,filter:'alpha(opacity=0)'});
    $('.blocks').eq(112).css({opacity:0,filter:'alpha(opacity=0)'});
    $('.kd').eq(113).css({opacity:0,filter:'alpha(opacity=0)'});
    $('.kd').eq(112).css({opacity:0,filter:'alpha(opacity=0)'});

    var arrBasicUrlSearch=window.location.search.substring(1).split("&");
        var proData;
        var minData;
        var maxData;
        var nowData;
        var changeId;
        for(var i=0;i<arrBasicUrlSearch.length;i++){
        	if(arrBasicUrlSearch[i].split("=")[0]=="value"){
        		curHeightValue=arrBasicUrlSearch[i].split("=")[1];
        	}else if(arrBasicUrlSearch[i].split("=")[0]=="monthOld"){
        		monthOld=arrBasicUrlSearch[i].split("=")[1];
        	}else if(arrBasicUrlSearch[i].split("=")[0]=="range"){
        		proData=arrBasicUrlSearch[i].split("=")[1];
        		if(proData == undefined || proData == null || proData == 'undefined'){

        		}else{
	         		minData=proData.split('~')[0];
	        		maxData=proData.split('~')[1];
	        		nowData=(12236 - ((Math.round(curHeightValue*100)/100-23)*110.02));
	        		//nowData=(12236 - ((Math.round(((Number(minData)+Number(maxData))/2 - 0.1)*100)/100-23)*110.02));
                    //console.log($('.box')[0].scrollHeight);
	        		$(".box").scrollTop(nowData);
                    recordData=Math.floor(nowData);
                    $("#num").text(curHeightValue);
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

});
function get(){
    var nowData=$(".box").scrollTop();
    var a=(Math.round(((12236-nowData)/110.02+23)*100.0)/100.0   ).toString();
    //if(recordData==nowData){
    //    return false;
    //}
    if(a%1==0){
        a+=".0";
    }
    $("#num").text(a);
}


function addNewRecord(){
	var recordType="height";
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
	var recordType="height";
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