/**
* Created by vinny on 2015/10/9.
*/

var babySex;
var babyMonthYearOld=0;
var babyHeight=0;
var babyWeight=0;
var babyHead=0;
//身高体重头围切换
var keyWord2=sessionStorage.getItem('key');
sessionStorage.removeItem('key');
//

$(function () {
    getBabtSexAndYear();
    if(keyWord2=='体重'){
        changeType('体重','m',true);
    }
    else if(keyWord2=='头围'){
        changeType('头围','n',true);
    }
    else{
        changeType('身高','hh',true);
    }
});
function changeType(name,div,isStack) {
    $('.top_box li').removeClass('select');
    $(div).addClass('select');
    if(name=='体重'){
        $('.top_box li').eq(1).addClass('select');
        $(".height_box, .head_box").hide();
        $(".weight_box").show();
        showWeightPage();
    }else if(name=='头围'){
        $('.top_box li').eq(2).addClass('select');
        $(".weight_box, .height_box").hide();
        $(".head_box").show();
        showHeadPage();
    }else{
        $('.top_box li').eq(0).addClass('select');
        $(".weight_box, .head_box").hide();
        $(".height_box").show();
        showHeightPage();
    }
    //var arrHistoryList = [];
    //$("body").load(name,function(){
    //    if(isStack){
    //        arrHistoryList.push(window.location.href);
    //    }
    //    window.history.pushState({},"","heightweightheadrecord.html?"+name);
    //    var scategoryType=decodeURIComponent(window.location.search.substring(1).split('&&')[0].trim());
    //    arrHistoryList.pop();
    //});
}
//趋势图和历史纪录切换
$(function(){
    $(".qushitu").addClass("select");
    $(".qushi_box").show();
    $(".qushitu").click(function(){
        $(".qushitu").addClass("select");
        $(".lishijilu").removeClass("select");
        $(".qushi_box").show();
        $(".lishi_box").hide();
    });
    $(".lishijilu").click(function(){
        $(".qushitu").removeClass("select");
        $(".lishijilu").addClass("select");
        $(".qushi_box").hide();
        $(".lishi_box").show();
    });
});

//点 身高
//	0~11个月 男
//$(function(){
function height11nan(age,hei){
    //var age = babyMonthYearOld;  //年龄/月
    //var hei = babyHeight;  //身高/cm

    if(hei-40<0 || hei>80){
        $(".height_point_011").css({
            "left" :  25,
            "bottom" : 34
        });
    }else{
        $(".height_point_011").css({
            "left" :  25+age*24.125,
            "bottom" : 34+(hei-40)*3.66
        });
    }
}
//});
//	0~11个月 女
//$(function(){
//var age = 8;  //年龄/月
//var hei = 70;  //身高/cm
function height11nv(age,hei){
    if(hei-40<0 || hei>80){
        $(".height_point_011nv").css({
            "left" :  25,
            "bottom" : 34
        });
    }else{
        $(".height_point_011nv").css({
            "left" :  25+age*24.125,
            "bottom" : 34+(hei-40)*3.66
        });
    }
}
//});
//	12~23个月 男
//$(function(){
//var age = 20;  //年龄/月
//var hei = 85;  //身高/cm
function height23nan(age,hei){
    if(age-12<0 || hei-60<0){
        $(".height_point_1223").css({
            "left" :  25,
            "bottom" : 34
        });
    }else{
        $(".height_point_1223").css({
            "left" :  25+(age-12)*24.125,
            "bottom" : 34+(hei-60)*4.167
        });

    }
}
//});
//	12~23个月 女
//$(function(){
//var age = 20;  //年龄/月
//var hei = 85;  //身高/cm
function height23nv(age,hei){
    if(age-12<0 || hei-60<0){
        $(".height_point_1223nv").css({
            "left" :  25,
            "bottom" : 34
        });
    }else{
        $(".height_point_1223nv").css({
            "left" :  25+(age-12)*24.125,
            "bottom" : 34+(hei-60)*4.167
        });

    }
}
//});
//	24~35个月 男
//$(function(){
//var age = 30;  //年龄/月
//var hei = 90;  //身高/cm
function height35nan(age,hei){
    if(age-24<0 || hei-60<0){
        $(".height_point_2435").css({
            "left" :  29,
            "bottom" : 34
        });
    }else{
        $(".height_point_2435").css({
            "left" :  29+(age-24)*23.727,
            "bottom" : 34+(hei-60)*3.2444
        });

    }
}
//});
//	24~35个月 女
//$(function(){
//var age = 30;  //年龄/月
//var hei = 90;  //身高/cm
function height35nv(age,hei){
    if(age-24<0 || hei-60<0){
        $(".height_point_2435nv").css({
            "left" :  29,
            "bottom" : 34
        });
    }else{
        $(".height_point_2435nv").css({
            "left" :  29+(age-24)*23.727,
            "bottom" : 34+(hei-60)*3.2444
        });

    }
}
//});

//点 体重
//	0~11个月 男
//$(function(){
//var age = 1;  //年龄/月
//var wei = 2;  //体重/kg
function weight11nan(age,wei){
    $(".weight_point_011").css({
        "left" :  25+age*24.125,
        "bottom" : 34+wei*12.15
    });
}
//});
//	0~11个月 女
//$(function(){
//var age = 1;  //年龄/月
//var wei = 2;  //体重/kg
function weight11nv(age,wei){
    $(".weight_point_011nv").css({
        "left" :  25+age*24.125,
        "bottom" : 34+wei*12.15
    });
}
//});

//12~23个月 男
//$(function(){
//var age = 22;  //年龄/月
//var wei = 14;  //体重/kg
function weight23nan(age,wei){
    $(".weight_point_1223").css({
        "left" :  25+(age-12)*24.125,
        "bottom" : 34+wei*9.15
    });
}
//});
//12~23个月 女
//$(function(){
//var age = 22;  //年龄/月
//var wei = 14;  //体重/kg
function weight23nv(age,wei){
    $(".weight_point_1223nv").css({
        "left" :  25+(age-12)*24.125,
        "bottom" : 34+wei*9.15
    });
}
//});

//24~35个月 男
//$(function(){
//var age = 26;  //年龄/月
//var wei = 10;  //体重/kg
function weight35nan(age,wei){
    $(".weight_point_2435").css({
        "left" :  25+(age-24)*24.125,
        "bottom" : 34+wei*8.125
    });
}
//});
//24~35个月 女
//$(function(){
//var age = 26;  //年龄/月
//var wei = 10;  //体重/kg
function weight35nv(age,wei){
    $(".weight_point_2435nv").css({
        "left" :  25+(age-24)*24.125,
        "bottom" : 34+wei*8.125
    });
}
//});



//点 头围
//	0~11个月 男
//$(function(){
//var age = 6;  //年龄/月
//var hea = 42;  //头围/cm
function head11nan(age,hea){
    $(".head_point_011").css({
        "left" :  25+age*24.125,
        "bottom" : 34+(hea-30)*8.09
    });
}
//});
//	0~11个月 女
//$(function(){
//var age = 6;  //年龄/月
//var hea = 42;  //头围/cm
function head11nv(age,hea){
    $(".head_point_011nv").css({
        "left" :  25+age*24.125,
        "bottom" : 34+(hea-30)*8.09
    });
}
//});

//12~23个月 男
//$(function(){
//var age = 19;  //年龄/月
//var hea = 48;  //头围/cm
function head23nan(age,hea){
    $(".head_point_1223").css({
        "left" :  25+(age-12)*24.125,
        "bottom" : 34+(hea-40)*14.6
    });
}
//});
//12~23个月 女
//$(function(){
//var age = 19;  //年龄/月
//var hea = 48;  //头围/cm
function head23nv(age,hea){
    $(".head_point_1223nv").css({
        "left" :  25+(age-12)*24.125,
        "bottom" : 34+(hea-40)*14.6
    });
}
//});

//24~35个月 男
//$(function(){
//var age = 30;  //年龄/月
//var hea = 44;  //头围/cm
function head35nan(age,hea){
    $(".head_point_2435").css({
        "left" :  25+(age-24)*24.125,
        "bottom" : 34+(hea-40)*12.2
    });
}
//});
//24~35个月 女
//$(function(){
//var age = 30;  //年龄/月
//var hea = 44;  //头围/cm
function head35nv(age,hea){
    $(".head_point_2435nv").css({
        "left" :  25+(age-24)*24.125,
        "bottom" : 34+(hea-40)*12.2
    });
}
//});





function showHeightPage(){
    getHistRecord("height");
    $("#babyLatestHeight").text(babyHeight);
    if(babySex=="男"){
        if(babyMonthYearOld<=11){
            $(".height_011").show();
            height11nan(babyMonthYearOld,babyHeight);
        }else if(babyMonthYearOld<=23){
            $(".height_1223").show();
            height23nan(babyMonthYearOld,babyHeight);
        }else if(babyMonthYearOld<=35){
            $(".height_2435").show();
            height35nan(babyMonthYearOld,babyHeight);
        }
    } else{
        if(babyMonthYearOld<=11){
            $(".height_011nv").show();
            height11nv(babyMonthYearOld,babyHeight);
        }else if(babyMonthYearOld<=23){
            $(".height_1223nv").show();
            height23nv(babyMonthYearOld,babyHeight);
        }else if(babyMonthYearOld<=35){
            $(".height_2435nv").show();
            height35nv(babyMonthYearOld,babyHeight);
        }
    }
    var height_point = $('.height_po');
    for(var i=0;i<height_point.length;i++){

        var left_val = parseInt(height_point.eq(i).css('left'));
        var bottom_val = parseInt(height_point.eq(i).css('bottom'));
        if(left_val < 26){
            height_point.eq(i).css('left','26px');
        }else if(left_val > 290){
            height_point.eq(i).css('left','290px');
        }
        if(bottom_val < 34){
            height_point.eq(i).css('bottom','34px');
        }else if(bottom_val > 180){
            height_point.eq(i).css('bottom','180px');
        }
    }
}

function showWeightPage(){
    getHistRecord("weight");
    $("#babyLatestWeight").text(babyWeight);
    if(babySex=="男"){
        if(babyMonthYearOld<=11){
            $(".weight_011").show();
            weight11nan(babyMonthYearOld,babyWeight);
        }else if(babyMonthYearOld<=23){
            $(".weight_1223").show();
            weight23nan(babyMonthYearOld,babyWeight);
        }else if(babyMonthYearOld<=35){
            $(".weight_2435").show();
            weight35nan(babyMonthYearOld,babyWeight);
        }
    } else{
        if(babyMonthYearOld<=11){
            $(".weight_011nv").show();
            weight11nv(babyMonthYearOld,babyWeight);
        }else if(babyMonthYearOld<=23){
            $(".weight_1223nv").show();
            weight23nv(babyMonthYearOld,babyWeight);
        }else if(babyMonthYearOld<=35){
            $(".weight_2435nv").show();
            weight35nv(babyMonthYearOld,babyWeight);
        }
    }
    var weight_point = $('.weight_po');
    for(var i=0;i<weight_point.length;i++){
        var left_val = parseInt(weight_point.eq(i).css('left'));
        var bottom_val = parseInt(weight_point.eq(i).css('bottom'));
        if(left_val < 26){
            weight_point.eq(i).css('left','26px');
        }else if(left_val > 290){
            weight_point.eq(i).css('left','290px');
        }
        if(bottom_val < 34){
            weight_point.eq(i).css('bottom','34px');
        }else if(bottom_val > 180){
            weight_point.eq(i).css('bottom','180px');
        }
    }
}

function showHeadPage(){
    getHistRecord("head");
    $("#babyLatestHead").text(babyHead);
    if(babySex=="男"){
        if(babyMonthYearOld<=11){
            $(".head_011").show();
            head11nan(babyMonthYearOld,babyHead);
        }else if(babyMonthYearOld<=23){
            $(".head_1223").show();
            head23nan(babyMonthYearOld,babyHead);
        }else if(babyMonthYearOld<=35){
            $(".head_2435").show();
            head35nan(babyMonthYearOld,babyHead);
        }
    } else{
        if(babyMonthYearOld<=11){
            $(".head_011nv").show();
            head11nv(babyMonthYearOld,babyHead);
        }else if(babyMonthYearOld<=23){
            $(".head_1223nv").show();
            head23nv(babyMonthYearOld,babyHead);
        }else if(babyMonthYearOld<=35){
            $(".head_2435nv").show();
            head35nv(babyMonthYearOld,babyHead);
        }
    }
    var head_point = $('.head_po');
    for(var i=0;i<head_point.length;i++){
        var left_val = parseInt(head_point.eq(i).css('left'));
        var bottom_val = parseInt(head_point.eq(i).css('bottom'));
        if(left_val < 26){
            head_point.eq(i).css('left','26px');
        }else if(left_val > 290){
            head_point.eq(i).css('left','290px');
        }
        if(bottom_val < 34){
            head_point.eq(i).css('bottom','34px');
        }else if(bottom_val > 180){
            head_point.eq(i).css('bottom','180px');
        }
    }
}


function getBabtSexAndYear(){
    $.ajax({
        type:'post',
        url:host+'getUserInfo.action',
        cache:false,
        async:false,
        data:{action:"sexAndYear"},
        success:function(result){
            if(result.mes=="操作成功"){
                babySex=result.babySex;
                babyMonthYearOld=result.babyMonthYear;
            }else if(result.mes=="未登录"){
                ale("请先登录");

                setTimeout(function(){
                    window.location.href="login.html";
                },2500);
            }
        }
    });
}


function getHistRecord(recordType){

    $.ajax({
        type:'post',
        url:host+'getUserInfo.action',
        cache:false,
        async:false,
        data:{action:"recordHist",recordType:recordType},
        success:function(result){
            if(result.mes=="操作成功"){
                if(result.recordHis!=null){

                    if(recordType=="height"){
                        $("#lishi_box_height").html("");
                        for(var i=0;i<result.recordHis.length;i++){
                            var color_status = 'num_normal';
                            if(result.recordHis[i].recordStatus == '偏高' || result.recordHis[i].recordStatus == '偏重' || result.recordHis[i].recordStatus == '偏大'){
                                color_status = "num_high";
                            }else if(result.recordHis[i].recordStatus == '正常'){
                                color_status = "num_normal";
                            }else if(result.recordHis[i].recordStatus == '偏低' || result.recordHis[i].recordStatus == '偏轻' || result.recordHis[i].recordStatus == '偏小'){
                                color_status = "num_low";
                            }
                            $("#lishi_box_height").append(
                                "<div class='lishi_list'>"+
                                "<p>"+result.recordHis[i].recordTime+"</p>"+
                                "<p>"+result.recordHis[i].babyMonthYear+"个月</p>"+
                                "<p>"+result.recordHis[i].height+"cm</p>"+
                                    //<!--new by houfei-->
                                "<p><span "+
                                " onclick=\"changeRecord('height',"+result.recordHis[i].height+",$('#height_normal_range').attr('data_range'),"+result.recordHis[i].id+")\" "+
                                " class='hwh_icon "+color_status+"'>"+result.recordHis[i].recordStatus+"</span></p>"+
                                    //<!--new by houfei-->
                                "</div>"
                            );
                        }
                        babyHeight=result.recordHis[i-1].height;
                        var year=result.recordHis[i-1].babyMonthYear;

                        getLatestRecordValue(year,babySex,recordType,babyHeight);

                        //需要根据当前宝宝的性别和月龄以及数据所属类型，返回当前对应的正常范围和对应的提示信息
                    } else if(recordType=="weight"){
                        $("#lishi_box_weight").html("");
                        for(var i=0;i<result.recordHis.length;i++){
                            var color_status = 'num_normal';
                            if(result.recordHis[i].recordStatus == '偏高' || result.recordHis[i].recordStatus == '偏重' || result.recordHis[i].recordStatus == '偏大'){
                                color_status = "num_high";
                            }else if(result.recordHis[i].recordStatus == '正常'){
                                color_status = "num_normal";
                            }else if(result.recordHis[i].recordStatus == '偏低' || result.recordHis[i].recordStatus == '偏轻' || result.recordHis[i].recordStatus == '偏小'){
                                color_status = "num_low";
                            }
                            $("#lishi_box_weight").append(
                                "<div class='lishi_list'>"+
                                "<p>"+result.recordHis[i].recordTime+"</p>"+
                                "<p>"+result.recordHis[i].babyMonthYear+"个月</p>"+
                                "<p>"+result.recordHis[i].weight+"kg</p>"+
                                    //<!--new by houfei-->
                                "<p><span "+
                                " onclick=\"changeRecord('weight',"+result.recordHis[i].weight+",$('#weight_normal_range').attr('data_range'),"+result.recordHis[i].id+")\" "+
                                " class='hwh_icon "+color_status+"'>"+result.recordHis[i].recordStatus+"</span></p>"+
                                    //<!--new by houfei-->
                                "</div>"
                            );
                        }
                        babyWeight=result.recordHis[i-1].weight;
                        var year=result.recordHis[i-1].babyMonthYear;
                        getLatestRecordValue(year,babySex,recordType,babyWeight);
                        //需要根据当前宝宝的性别和月龄以及数据所属类型，返回当前对应的正常范围和对应的提示信息
                    } else if(recordType=="head"){
                        $("#lishi_box_head").html("");
                        for(var i=0;i<result.recordHis.length;i++){
                            var color_status = 'num_normal';
                            if(result.recordHis[i].recordStatus == '偏高' || result.recordHis[i].recordStatus == '偏重' || result.recordHis[i].recordStatus == '偏大'){
                                color_status = "num_high";
                            }else if(result.recordHis[i].recordStatus == '正常'){
                                color_status = "num_normal";
                            }else if(result.recordHis[i].recordStatus == '偏低' || result.recordHis[i].recordStatus == '偏轻' || result.recordHis[i].recordStatus == '偏小'){
                                color_status = "num_low";
                            }
                            $("#lishi_box_head").append(
                                "<div class='lishi_list'>"+
                                "<p>"+result.recordHis[i].recordTime+"</p>"+
                                "<p>"+result.recordHis[i].babyMonthYear+"个月</p>"+
                                "<p>"+result.recordHis[i].headLength+"cm</p>"+
                                    //<!--new by houfei-->
                                "<p><span "+
                                " onclick=\"changeRecord('head',"+result.recordHis[i].headLength+",$('#head_normal_range').attr('data_range'),"+result.recordHis[i].id+")\" "+
                                " class='hwh_icon "+color_status+"'>"+result.recordHis[i].recordStatus+"</span></p>"+
                                    //<!--new by houfei-->
                                "</div>"
                            );
                        }
                        babyHead=result.recordHis[i-1].headLength;
                        var year=result.recordHis[i-1].babyMonthYear;
                        getLatestRecordValue(year,babySex,recordType,babyHead);
                        //需要根据当前宝宝的性别和月龄以及数据所属类型，返回当前对应的正常范围和对应的提示信息
                    }
                }else{
                    $('#weight_record_note,#height_record_note,#head_record_note').hide();
                }
            } else if(result.mes=="未登录"){
                ale("请先登录");
                setTimeout(function(){
                    window.location.href="login.html";
                },2500);
            }
        }
    });
}



function newRecord(key,value,range,keyWord){
    sessionStorage.setItem('key',keyWord);
    window.location.href=key+".html?value="+value+"&monthOld="+babyMonthYearOld+"&range="+range;
}
//<!--new by houfei-->
function changeRecord(key,value,range,id){
    window.location.href=key+".html?value="+value+"&monthOld="+babyMonthYearOld+"&range="+range+"&changeId="+id;
}
//<!--new by houfei-->

function getLatestRecordValue(monthYear,sex,recordType,value){
    $.ajax({
        type:'post',
        url:host+'getUserInfo.action',
        cache:false,
        async:false,
        data:{action:"latest",babyMonthYear:monthYear,babySex:sex,recordType:recordType,recordValue:value},
        success:function(result){
            if(result.mes=="操作成功"){
                var tips=result.tips;
                var range=result.ranges;
                if(recordType=="height"){//身高

                    $("#height_normal_range").text("正常身高范围是:"+range+"cm").attr('data_range',range);
                    $("#height_record_note").text(tips);
                } else if(recordType=="weight"){//体重

                    $("#weight_normal_range").text("正常体重范围是:"+range+"kg").attr('data_range',range);
                    $("#weight_record_note").text(tips);
                } else if(recordType=="head"){//头围

                    $("#head_normal_range").text("正常头围范围是:"+range+"cm").attr('data_range',range);
                    $("#head_record_note").text(tips);
                }
            }else{
                //ale("暂无数据");
            }
        }
    });
}

