/**
 * Created by vinny on 2015/10/9.
 */

var babySex;
var babyMonthYearOld=0;
var babyHeight=0;
var babyWeight=0;
var babyHead=0;
var babyBirthDay='2015-01-01';
var canvasHeight=document.getElementById('canvasHeight');
var ctxHeight=canvasHeight.getContext('2d');
var str_height=[];
var canvasWeight=document.getElementById('canvasWeight');
var ctxWeight=canvasWeight.getContext('2d');
var str_weight=[];
var canvasHead=document.getElementById('canvasHead');
var ctxHead=canvasHead.getContext('2d');
var str_head=[];
var babyNowMonth=8;
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
    $('.tu_tab>div');
    $(".qushitu").addClass("select");
    $(".qushi_box").show();
    $(".qushitu").click(function(){
        $(".qushitu").addClass("select");
        $(".lishijilu").removeClass("select");
        $(".qushi_box").show();
        $(".lishi_box").hide();
        var _this=$(this).parent().parent().parent();
        if($(_this).hasClass('weight_box')){
            getHistRecord("weight");
        }else if($(_this).hasClass('height_box')){
            getHistRecord("height");
        }else if($(_this).hasClass('height_box')){
            getHistRecord("head");
        }
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
        },
        error: function () {
            layer();
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
                babyBirthDay=result.usr.birthday;
                if(result.recordHis!=null){

                    if(recordType=="height"){
                        $("#lishi_box_height").html("");
                        for(var i=0;i<result.recordHis.length;i++){
                            var color_status = 'num_normal';
                            if(result.recordHis[i].recordStatus == '偏高' || result.recordHis[i].recordStatus == '偏重' || result.recordHis[i].recordStatus == '偏大'){
                                color_status = "num_high";
                            }else if(result.recordHis[i].recordStatus == '正常'){
                                color_status = "num_normal";
                            }else if(result.recordHis[i].recordStatus == '偏低' ||result.recordHis[i].recordStatus == '偏矮' || result.recordHis[i].recordStatus == '偏轻' || result.recordHis[i].recordStatus == '偏小'){
                                color_status = "num_low";
                            }
                            var hisMonth;
                            var recordHis=result.recordHis[i];
                            var recordTime_day=recordHis.recordTime.split(' ')[0].split('-')[2];
                            var currentYear=parseInt(recordHis.recordTime.split(' ')[0].split('-')[0]);
                            var currentMonth=parseInt(recordHis.recordTime.split(' ')[0].split('-')[1]);
                            var babyBirth_year=parseInt(babyBirthDay.split('-')[0]);
                            var babyBirth_month=parseInt(babyBirthDay.split('-')[1]);
                            var babyBirth_day=babyBirthDay.split('-')[2];
                            if(currentYear==babyBirth_year){
                                hisMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);
                            }else if(currentYear>babyBirth_year){
                                if(currentMonth>=babyBirth_month){
                                    hisMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);

                                }else{
                                    hisMonth=(currentYear-babyBirth_year-1)*12+(currentMonth+12-babyBirth_month);
                                }
                            }
                            var record_days='';
                            if(recordTime_day>=babyBirth_day){
                                record_days=recordTime_day-babyBirth_day;
                            }else{
                                record_days=recordTime_day-babyBirth_day+30;
                                hisMonth=hisMonth-1;
                            }
                            var arr=[];
                            arr.push(parseInt(hisMonth));//月份
                            arr.push(400+2*40-Math.floor(recordHis.height/10)*40-(recordHis.height-Math.floor(recordHis.height/10)*10)*4);//身高
                            arr.push(record_days);//天数
                            str_height.push(arr);


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
                        drawCosHeight();
                        //currentTimeHeight();
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
                            }else if(result.recordHis[i].recordStatus == '偏低' ||result.recordHis[i].recordStatus == '偏矮' || result.recordHis[i].recordStatus == '偏轻' || result.recordHis[i].recordStatus == '偏小'){
                                color_status = "num_low";
                            }
                            var hisMonth;
                            var recordHis=result.recordHis[i];
                            var recordTime_day=recordHis.recordTime.split(' ')[0].split('-')[2];
                            var currentYear=parseInt(recordHis.recordTime.split(' ')[0].split('-')[0]);
                            var currentMonth=parseInt(recordHis.recordTime.split(' ')[0].split('-')[1]);
                            var babyBirth_year=parseInt(babyBirthDay.split('-')[0]);
                            var babyBirth_month=parseInt(babyBirthDay.split('-')[1]);
                            var babyBirth_day=babyBirthDay.split('-')[2];
                            if(currentYear==babyBirth_year){
                                hisMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);
                            }else if(currentYear>babyBirth_year){
                                if(currentMonth>=babyBirth_month){
                                    hisMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);

                                }else{
                                    hisMonth=(currentYear-babyBirth_year-1)*12+(currentMonth+12-babyBirth_month);
                                }
                            }
                            var record_days='';
                            if(recordTime_day>=babyBirth_day){
                                record_days=recordTime_day-babyBirth_day;
                            }else{
                                record_days=recordTime_day-babyBirth_day+30;
                                hisMonth=hisMonth-1;
                            }
                            var arr=[];
                            arr.push(parseInt(hisMonth));//月份
                            arr.push(400-Math.floor(recordHis.weight/2)*40-(recordHis.weight-Math.floor(recordHis.weight/2)*2)*40/2);//体重
                            arr.push(record_days);//天数
                            str_weight.push(arr);

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
                        drawCosWeight();
                        //currentTimeWeight();
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
                            }else if(result.recordHis[i].recordStatus == '偏低' ||result.recordHis[i].recordStatus == '偏矮' || result.recordHis[i].recordStatus == '偏轻' || result.recordHis[i].recordStatus == '偏小'){
                                color_status = "num_low";
                            }
                            var hisMonth;
                            var recordHis=result.recordHis[i];
                            var recordTime_day=recordHis.recordTime.split(' ')[0].split('-')[2];
                            var currentYear=parseInt(recordHis.recordTime.split(' ')[0].split('-')[0]);
                            var currentMonth=parseInt(recordHis.recordTime.split(' ')[0].split('-')[1]);
                            var babyBirth_year=parseInt(babyBirthDay.split('-')[0]);
                            var babyBirth_month=parseInt(babyBirthDay.split('-')[1]);
                            var babyBirth_day=babyBirthDay.split('-')[2];
                            if(currentYear==babyBirth_year){
                                hisMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);
                            }else if(currentYear>babyBirth_year){
                                if(currentMonth>=babyBirth_month){
                                    hisMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);

                                }else{
                                    hisMonth=(currentYear-babyBirth_year-1)*12+(currentMonth+12-babyBirth_month);
                                }
                            }
                            var record_days='';
                            if(recordTime_day>=babyBirth_day){
                                record_days=recordTime_day-babyBirth_day;
                            }else{
                                record_days=recordTime_day-babyBirth_day+30;
                                hisMonth=hisMonth-1;
                            }
                            var arr=[];
                            arr.push(parseInt(hisMonth));//月份
                            arr.push(400+40*9-Math.floor(recordHis.headLength/3)*40-(recordHis.headLength-Math.floor(recordHis.headLength/3)*3)*40/3);//头围
                            arr.push(record_days);//天数
                            str_head.push(arr);
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
                        drawCosHead();
                        //currentTimeHead();
                        babyHead=result.recordHis[i-1].headLength;
                        var year=result.recordHis[i-1].babyMonthYear;
                        getLatestRecordValue(year,babySex,recordType,babyHead);
                        //需要根据当前宝宝的性别和月龄以及数据所属类型，返回当前对应的正常范围和对应的提示信息
                    }
                }else{
                    drawCosHeight();
                    drawCosWeight();
                    drawCosHead();
                    $('#weight_record_note,#height_record_note,#head_record_note').hide();
                }
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



function newRecord(key,value,range,keyWord){
    if(babyNowMonth>=36){
        ale('只能记录0~36个月');
        return false;
    }
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
        },
        error: function () {
            layer();
        }
    });
}

function drawAxis(){
    ctxHeight.translate(100,100);
    //x 轴
    ctxHeight.beginPath();
    ctxHeight.moveTo(0,340);
    ctxHeight.lineTo(600,340);
    ctxHeight.lineTo(592,343);
    ctxHeight.lineTo(592,337);
    ctxHeight.lineTo(600,340);
    ctxHeight.stroke();   //描边
    //y 轴
    ctxHeight.moveTo(0,340);
    ctxHeight.lineTo(0,0);
    ctxHeight.lineTo(3,8);
    ctxHeight.lineTo(-3,8);
    ctxHeight.lineTo(0,0);
    ctxHeight.stroke();   //描边
    //画坐标
    ctxHeight.save();
    ctxHeight.fillStyle='rgba(0,0,0,1)';
    ctxHeight.fillText("0",0,350);
    ctxHeight.fillText("500",500,350);
    ctxHeight.fillText("300",10,40);
    ctxHeight.restore();

}
function drawCosHeight(){
    ctxHeight.clearRect(0,0,canvasHeight.width,canvasHeight.height);
    for(var i= 0;i<=180;i++){
        ctxHeight.beginPath();
        if(i%5==0){
            ctxHeight.lineWidth=1;
            ctxHeight.strokeStyle = "#858525";
        }else{
            ctxHeight.lineWidth=1;
            ctxHeight.strokeStyle = "#a5a5a5";
        }
        ctxHeight.lineTo(i*8,0);
        ctxHeight.lineTo(i*8,400);
        ctxHeight.stroke();   //描边
        ctxHeight.closePath();
    }
    for(var i= 0;i<=50;i++){
        ctxHeight.beginPath();
        if(i%5==0){
            ctxHeight.lineWidth=1;
            ctxHeight.strokeStyle = "#858525";
        }else{
            ctxHeight.lineWidth=1;
            ctxHeight.strokeStyle = "#a5a5a5";
        }
        ctxHeight.lineTo(0,i*8);
        ctxHeight.lineTo(1440,i*8);
        ctxHeight.stroke();   //描边
        ctxHeight.closePath();
    }

    ctxHeight.beginPath();
    ctxHeight.strokeStyle = "#ff813d";
    for(var i= 0,l=str_height.length;i<l;i++){
        ctxHeight.lineTo(str_height[i][0]*40+str_height[i][2]*4/3,str_height[i][1]);
    }
    ctxHeight.stroke();   //描边
    ctxHeight.closePath();
    for(var i= 0,l=str_height.length;i<l;i++){
        ctxHeight.beginPath();
        var circle = {
            x : str_height[i][0]*40+str_height[i][2]*4/3,    //圆心的x轴坐标值
            y : str_height[i][1],    //圆心的y轴坐标值
            r : 4     //圆的半径
        };
        //沿着坐标点(100,100)为圆心、半径为50px的圆的顺时针方向绘制弧线
        ctxHeight.arc(circle.x, circle.y, circle.r, 0, Math.PI * 2, true);
        //按照指定的路径绘制弧线

        ctxHeight.stroke();   //描边
        ctxHeight.fillStyle= "#ff813d";
        ctxHeight.fill();
        ctxHeight.closePath();
    }
    drawheight();
    currentTimeHeight();
}
function drawheight(){
    var img=new Image();
    if(babySex=='男'){
        img.src='images/height_weight_head/normal_height_nan.png';
    }else{
        img.src='images/height_weight_head/normal_height_nv.png';
    }
    img.onload= function () {
        ctxHeight.drawImage(img,0,0,1440,400);
        ctxHeight.save();
        ctxHeight.shadowColor='rgba(0,0,0,0.8)';
        ctxHeight.shadowOffsetX=0;
        ctxHeight.shadowOffsetY=0;
        ctxHeight.shadowBlur=0;
        //drawAxis();
        //drawCosHeight();
        ctxHeight.restore();
    }
}
function drawCosWeight(){
    ctxWeight.clearRect(0,0,canvasWeight.width,canvasWeight.height);
    for(var i= 0;i<=180;i++){
        ctxWeight.beginPath();
        if(i%5==0){
            ctxWeight.lineWidth=1;
            ctxWeight.strokeStyle = "#858525";
        }else{
            ctxWeight.lineWidth=1;
            ctxWeight.strokeStyle = "#a5a5a5";
        }
        ctxWeight.lineTo(i*8,0);
        ctxWeight.lineTo(i*8,400);
        ctxWeight.stroke();   //描边
        ctxWeight.closePath();
    }
    for(var i= 0;i<=50;i++){
        ctxWeight.beginPath();
        if(i%5==0){
            ctxWeight.lineWidth=1;
            ctxWeight.strokeStyle = "#858525";
        }else{
            ctxWeight.lineWidth=1;
            ctxWeight.strokeStyle = "#a5a5a5";
        }
        ctxWeight.lineTo(0,i*8);
        ctxWeight.lineTo(1440,i*8);
        ctxWeight.stroke();   //描边
        ctxWeight.closePath();
    }

    ctxWeight.beginPath();
    ctxWeight.strokeStyle = "#ff813d";
    for(var i= 0,l=str_weight.length;i<l;i++){
        ctxWeight.lineTo(str_weight[i][0]*40+str_weight[i][2]*4/3,str_weight[i][1]);
    }
    ctxWeight.stroke();   //描边
    ctxWeight.closePath();
    for(var i= 0,l=str_weight.length;i<l;i++){
        ctxWeight.beginPath();
        var circle = {
            x : str_weight[i][0]*40+str_weight[i][2]*4/3,    //圆心的x轴坐标值
            y : str_weight[i][1],    //圆心的y轴坐标值
            r : 4     //圆的半径
        };
        //沿着坐标点(100,100)为圆心、半径为50px的圆的顺时针方向绘制弧线
        ctxWeight.arc(circle.x, circle.y, circle.r, 0, Math.PI * 2, true);
        //按照指定的路径绘制弧线

        ctxWeight.stroke();   //描边
        ctxWeight.fillStyle= "#ff813d";
        ctxWeight.fill();
        ctxWeight.closePath();
    }
    drawweight();
    currentTimeWeight();
}
function drawweight(){
    var img=new Image();
    if(babySex=='男'){
        img.src='images/height_weight_head/normal_weight_nan.png';
    }else{
        img.src='images/height_weight_head/normal_weight_nv.png';
    }
    img.onload= function () {
        ctxWeight.drawImage(img,0,0,1440,400);
        ctxWeight.save();
        ctxWeight.shadowColor='rgba(0,0,0,0.8)';
        ctxWeight.shadowOffsetX=0;
        ctxWeight.shadowOffsetY=0;
        ctxWeight.shadowBlur=0;
        //drawAxis();
        //drawCosWeight();
        ctxWeight.restore();
    }
}
function drawCosHead(){
    ctxHead.clearRect(0,0,canvasHead.width,canvasHead.height);
    for(var i= 0;i<=180;i++){
        ctxHead.beginPath();
        if(i%5==0){
            ctxHead.lineWidth=0.6;
            ctxHead.strokeStyle = "#858525";
        }else{
            ctxHead.lineWidth=0.6;
            ctxHead.strokeStyle = "#a5a5a5";
        }
        ctxHead.lineTo(i*8,0);
        ctxHead.lineTo(i*8,400);
        ctxHead.stroke();   //描边
        ctxHead.closePath();
    }
    for(var i= 0;i<=50;i++){
        ctxHead.beginPath();
        if(i%5==0){
            ctxHead.lineWidth=0.6;
            ctxHead.strokeStyle = "#858525";
        }else{
            ctxHead.lineWidth=0.6;
            ctxHead.strokeStyle = "#a5a5a5";
        }
        ctxHead.lineTo(0,i*8);
        ctxHead.lineTo(1440,i*8);
        ctxHead.stroke();   //描边
        ctxHead.closePath();
    }

    ctxHead.beginPath();
    ctxHead.strokeStyle = "#ff813d";
    for(var i= 0,l=str_head.length;i<l;i++){
        ctxHead.lineTo(str_head[i][0]*40+str_head[i][2]*4/3,str_head[i][1]);
    }
    ctxHead.stroke();   //描边
    ctxHead.closePath();
    for(var i= 0,l=str_head.length;i<l;i++){
        ctxHead.beginPath();
        var circle = {
            x : str_head[i][0]*40+str_head[i][2]*4/3,    //圆心的x轴坐标值
            y : str_head[i][1],    //圆心的y轴坐标值
            r : 4     //圆的半径
        };
        //沿着坐标点(100,100)为圆心、半径为50px的圆的顺时针方向绘制弧线
        ctxHead.arc(circle.x, circle.y, circle.r, 0, Math.PI * 2, true);
        //按照指定的路径绘制弧线

        ctxHead.stroke();   //描边
        ctxHead.fillStyle= "#ff813d";
        ctxHead.fill();
        ctxHead.closePath();
    }
    drawhead();
    currentTimeHead();
}
function drawhead(){
    var img=new Image();
    if(babySex=='男'){
        img.src='images/height_weight_head/normal_head_nan.png';
    }else{
        img.src='images/height_weight_head/normal_head_nv.png';
    }
    img.onload= function () {
        ctxHead.drawImage(img,0,0,1440,400);
        ctxHead.save();
        ctxHead.shadowColor='rgba(0,0,0,0.8)';
        ctxHead.shadowOffsetX=0;
        ctxHead.shadowOffsetY=0;
        ctxHead.shadowBlur=0;
        //drawAxis();
        //drawCosHead();
        ctxHead.restore();
    }
}
var firstCurrentTimeHeight;
function currentTimeHeight(){
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getCurrentTime"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href="login.html";
            }
            else if (result.mes == "成功") {
                var currentTime=result.currentTime.split(' ')[0].split('-')[2];
                var currentYear=parseInt(result.currentTime.split(' ')[0].split('-')[0]);
                var currentMonth=parseInt(result.currentTime.split(' ')[0].split('-')[1]);
                var babyBirth_year=parseInt(babyBirthDay.split('-')[0]);
                var babyBirth_month=parseInt(babyBirthDay.split('-')[1]);
                var babyBirth_day=babyBirthDay.split('-')[2];
                var record_days='';
                if(currentYear==babyBirth_year){
                    babyNowMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);
                }else if(currentYear>babyBirth_year){
                    if(currentMonth>=babyBirth_month){
                        babyNowMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);

                    }else{
                        babyNowMonth=(currentYear-babyBirth_year-1)*12+(currentMonth+12-babyBirth_month);
                    }
                }
                if(currentTime>=babyBirth_day){
                    record_days=currentTime-babyBirth_day;
                }else{
                    record_days=currentTime-babyBirth_day+30;
                    babyNowMonth=babyNowMonth-1;
                }
                ctxHeight.beginPath();
                ctxHeight.strokeStyle = "#ff813d";
                ctxHeight.lineTo(babyNowMonth*40+record_days*4/3,0);
                ctxHeight.lineTo(babyNowMonth*40+record_days*4/3,400);
                ctxHeight.stroke();   //描边
                ctxHeight.font = "10px Microsoft YaHei";
                ctxHeight.lineWidth=0.6;
                ctxHeight.strokeText("今天", babyNowMonth*40+record_days*4/3-14, 14);
                if(babyNowMonth*40+record_days*4/3>140){
                    firstCurrentTimeHeight=babyNowMonth*40+record_days*4/3-140;
                    $(".qushitu_all").eq(0).scrollLeft(babyNowMonth*40+record_days*4/3-140);
                }
                ctxHeight.stroke();
                ctxHeight.closePath();
                normalAll('height');

            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

}
function currentTimeWeight(){
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getCurrentTime"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href="login.html";
            }
            else if (result.mes == "成功") {
                var currentTime=result.currentTime.split(' ')[0].split('-')[2];
                var currentYear=parseInt(result.currentTime.split(' ')[0].split('-')[0]);
                var currentMonth=parseInt(result.currentTime.split(' ')[0].split('-')[1]);
                var babyBirth_year=parseInt(babyBirthDay.split('-')[0]);
                var babyBirth_month=parseInt(babyBirthDay.split('-')[1]);
                var babyBirth_day=babyBirthDay.split('-')[2];
                var record_days='';
                if(currentYear==babyBirth_year){
                    babyNowMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);
                }else if(currentYear>babyBirth_year){
                    if(currentMonth>=babyBirth_month){
                        babyNowMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);

                    }else{
                        babyNowMonth=(currentYear-babyBirth_year-1)*12+(currentMonth+12-babyBirth_month);
                    }
                }
                if(currentTime>=babyBirth_day){
                    record_days=currentTime-babyBirth_day;
                }else{
                    record_days=currentTime-babyBirth_day+30;
                    babyNowMonth=babyNowMonth-1;
                }
                ctxWeight.beginPath();
                ctxWeight.strokeStyle = "#ff813d";
                ctxWeight.lineTo(babyNowMonth*40+record_days*4/3,0);
                ctxWeight.lineTo(babyNowMonth*40+record_days*4/3,400);
                ctxWeight.stroke();   //描边
                ctxWeight.font = "10px Microsoft YaHei";
                ctxWeight.lineWidth=0.6;
                ctxWeight.strokeText("今天", babyNowMonth*40+record_days*4/3-14, 14);
                if(babyNowMonth*40+record_days*4/3>140){
                    $(".qushitu_all").eq(1).scrollLeft(babyNowMonth*40+record_days*4/3-140);
                }
                ctxWeight.stroke();
                ctxWeight.closePath();
                normalAll('weight');

            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

}
function currentTimeHead(){
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getCurrentTime"},
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href="login.html";
            }
            else if (result.mes == "成功") {
                var currentTime=result.currentTime.split(' ')[0].split('-')[2];
                var currentYear=parseInt(result.currentTime.split(' ')[0].split('-')[0]);
                var currentMonth=parseInt(result.currentTime.split(' ')[0].split('-')[1]);
                var babyBirth_year=parseInt(babyBirthDay.split('-')[0]);
                var babyBirth_month=parseInt(babyBirthDay.split('-')[1]);
                var babyBirth_day=babyBirthDay.split('-')[2];
                var record_days='';
                if(currentYear==babyBirth_year){
                    babyNowMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);
                }else if(currentYear>babyBirth_year){
                    if(currentMonth>=babyBirth_month){
                        babyNowMonth=(currentYear-babyBirth_year)*12+(currentMonth-babyBirth_month);

                    }else{
                        babyNowMonth=(currentYear-babyBirth_year-1)*12+(currentMonth+12-babyBirth_month);
                    }
                }
                if(currentTime>=babyBirth_day){
                    record_days=currentTime-babyBirth_day;
                }else{
                    record_days=currentTime-babyBirth_day+30;
                    babyNowMonth=babyNowMonth-1;
                }
                ctxHead.beginPath();
                ctxHead.strokeStyle = "#ff813d";
                ctxHead.lineTo(babyNowMonth*40+record_days*4/3,0);
                ctxHead.lineTo(babyNowMonth*40+record_days*4/3,400);
                ctxHead.stroke();   //描边
                ctxHead.font = "10px Microsoft YaHei";
                ctxHead.lineWidth=0.6;
                ctxHead.strokeText("今天", babyNowMonth*40+record_days*4/3-14, 14);
                if(8*40+record_days*4/3>140){
                    $(".qushitu_all").eq(2).scrollLeft(babyNowMonth*40+record_days*4/3-140);
                }
                ctxHead.stroke();
                ctxHead.closePath();
                normalAll('head');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });

}
$(function () {
    $(".qushitu_all").eq(0).scrollLeft(firstCurrentTimeHeight);
});
$(function () {
    normalAll('height');
    $('.qushitu_all').eq(0).scroll(function () {
        normalAll('height');
    });
    $('.qushitu_all').eq(1).scroll(function () {
        normalAll('weight');
    });
    $('.qushitu_all').eq(2).scroll(function () {
        normalAll('head');
    });
});
//正常范围
function normalAll(type){
    var boy_height_normal_arr=[49.9,54.7,58.4,61.4,63.9,65.9,67.6,69.2,70.6,72.0,73.3,74.5,75.7,76.9,78.0,79.1,80.2,81.2,82.3,83.2,84.2,85.1,86.0,86.9,87.4,88.0,88.8,89.6,90.4,91.2,91.9,92.7,93.4,94.1,94.8,95.4];
    var girl_height_normal_arr=[49.1,53.7,57.1,59.8,62.1,64.0,65.7,67.3,68.7,70.1,71.5,72.8,74.0,75.2,76.4,77.5,78.6,79.7,80.7,81.7,82.7,83.7,84.6,85.5,86.0,86.6,87.4,88.3,89.1,89.9,90.7,91.4,92.2,92.9,93.6,94.4];
    var boy_weight_normal_arr=[3.3,4.5,5.6,6.4,7.0,7.5,7.9,8.3,8.6,8.9,9.2,9.4,9.6,9.9,10.1,10.3,10.5,10.7,10.9,11.1,11.3,11.5,11.8,12.0,12.2,12.4,12.5,12.7,12.9,13.1,13.3,13.5,13.7,13.8,14.0,14.2];
    var girl_weight_normal_arr=[3.2,4.2,5.1,5.8,6.4,6.9,7.3,7.6,7.9,8.2,8.5,8.7,8.9,9.2,9.4,9.6,9.8,10.0,10.2,10.4,10.6,10.9,11.1,11.3,11.5,11.7,11.9,12.1,12.3,12.5,12.7,12.9,13.1,13.3,13.5,13.7];
    var boy_head_normal_arr=[34.5,37.3,39.1,40.5,41.6,42.6,43.3,44.0,44.5,45.0,45.4,45.8,46.1,46.3,46.6,46.8,47.0,47.2,47.4,47.5,47.7,47.8,48.0,48.1,48.3,48.4,48.5,48.6,48.7,48.8,48.9,49.0,49.1,49.2,49.3,49.4];
    var girl_head_normal_arr=[33.9,36.5,38.3,39.5,40.6,41.5,42.2,42.8,43.4,43.8,44.2,44.6,44.9,45.2,45.4,45.7,45.9,46.1,46.2,46.4,46.6,46.7,46.9,47.0,47.2,47.3,47.5,47.6,47.7,47.8,47.9,48.0,48.1,48.2,48.3,48.4];
    if(type=='height'){
        var arr=[];
        if(babySex=='男'){
            arr=boy_height_normal_arr;
        }else{
            arr=girl_height_normal_arr;
        }
        var width=$('.qushitu_all').eq(0).width();
        //console.log(($('.qushitu_all').eq(0).scrollLeft()+width)/40);
        var month_normal=Math.ceil(($('.qushitu_all').eq(0).scrollLeft()+width)/40);
        var height=arr[month_normal];
        if(height==undefined){
            height=arr[35];
        }
        var rightBiaoTop=400+2*40-Math.floor(height/10)*40-(height-Math.floor(height/10)*10)*4-30;
        $('#canvasContentHeight>div.rightBiao').css({'top':rightBiaoTop});
    }else if(type=='weight'){
        var arr=[];
        if(babySex=='男'){
            arr=boy_weight_normal_arr;
        }else{
            arr=girl_weight_normal_arr;
        }
        var width=$('.qushitu_all').eq(1).width();
        //console.log(($('.qushitu_all').eq(0).scrollLeft()+width)/40);
        var month_normal=Math.ceil(($('.qushitu_all').eq(1).scrollLeft()+width)/40);
        var weight=arr[month_normal];
        if(weight==undefined){
            weight=arr[35];
        }
        var rightBiaoTop=400-Math.floor(weight/2)*40-(weight-Math.floor(weight/2)*2)*40/2-30;
        $('#canvasContentWeight>div.rightBiao').css({'top':rightBiaoTop});
    } else if(type=='head'){
        var arr=[];
        if(babySex=='男'){
            arr=boy_head_normal_arr;
        }else{
            arr=girl_head_normal_arr;
        }
        var width=$('.qushitu_all').eq(2).width();
        var month_normal=Math.ceil(($('.qushitu_all').eq(2).scrollLeft()+width)/40);
        var head=arr[month_normal];
        if(head==undefined){
            head=arr[35];
        }
        var rightBiaoTop=400+40*9-Math.floor(head/3)*40-(head-Math.floor(head/3)*3)*40/3-30;
        //console.log(rightBiaoTop);
        $('#canvasContentHead>div.rightBiao').css({'top':rightBiaoTop});
    }
}

//$(function (){
//    $.ajax({
//        type:'post',
//        url:clinicHost+'clinicBooking.action',
//        cache:false,
//        async:false,
//        data:{action:"getCurrentTime"},
//        success:function(result){
//            if (result.mes == '请登录') {
//                ale('请登录', '24px');
//                window.location.href="login.html";
//            }
//            else if (result.mes == "成功") {
//                var currentTime=result.currentTime.split(' ')[0].split('-')[2];
//                var babyBirth_day=babyBirthDay.split('-')[2];
//                var record_days='';
//                if(currentTime>=babyBirth_day){
//                    record_days=currentTime-babyBirth_day;
//                }else{
//                    record_days=currentTime-babyBirth_day+30;
//                }
//                ctxHeight.beginPath();
//                ctxHeight.strokeStyle = "#ff813d";
//                ctxHeight.lineTo(babyNowMonth*40+record_days*4/3,0);
//                ctxHeight.lineTo(babyNowMonth*40+record_days*4/3,400);
//                ctxHeight.stroke();   //描边
//                ctxHeight.font = "14px";
//                ctxHeight.lineWidth=1;
//                ctxHeight.strokeText("今天", babyNowMonth*40+record_days*4/3-14, 14);
//                if(babyNowMonth*40+record_days*4/3>140){
//                    $(".qushitu_all").eq(0).scrollLeft(babyNowMonth*40+record_days*4/3-140);
//                }
//                ctxHeight.stroke();
//                ctxHeight.closePath();
//
//                ctxWeight.beginPath();
//                ctxWeight.strokeStyle = "#ff813d";
//                ctxWeight.lineTo(babyNowMonth*40+record_days*4/3,0);
//                ctxWeight.lineTo(babyNowMonth*40+record_days*4/3,400);
//                ctxWeight.stroke();   //描边
//                ctxWeight.font = "14px";
//                ctxWeight.lineWidth=1;
//                ctxWeight.strokeText("今天", babyNowMonth*40+record_days*4/3-14, 14);
//                if(babyNowMonth*40+record_days*4/3>140){
//                    $(".qushitu_all").eq(1).scrollLeft(babyNowMonth*40+record_days*4/3-140);
//                }
//                ctxWeight.stroke();
//                ctxWeight.closePath();
//
//                ctxHead.beginPath();
//                ctxHead.strokeStyle = "#ff813d";
//                ctxHead.lineTo(babyNowMonth*40+record_days*4/3,0);
//                ctxHead.lineTo(babyNowMonth*40+record_days*4/3,400);
//                ctxHead.stroke();   //描边
//                ctxHead.font = "14px";
//                ctxHead.lineWidth=0.6;
//                ctxHead.strokeText("今天", babyNowMonth*40+record_days*4/3-14, 14);
//                if(8*40+record_days*4/3>140){
//                    $(".qushitu_all").eq(2).scrollLeft(babyNowMonth*40+record_days*4/3-140);
//                }
//                ctxHead.stroke();
//                ctxHead.closePath();
//            }
//        },
//        error: function(XMLHttpRequest, textStatus, errorThrown) {
//        }
//    });
//
//});

