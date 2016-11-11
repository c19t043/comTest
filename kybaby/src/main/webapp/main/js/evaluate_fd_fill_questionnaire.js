var data_arr = [];//装答案
var left = 0;//控制导航
var user_id = '';//用户id
var type_id = '';
var good_sn = '';
var url_txt = '';
var asqTaoti_id = '';//asqTaotiAge的id
$(function(){
    user_id = window.location.search.substring(1).split("&")[1].split("=")[1];//用户id
    url_txt = window.location.search.substring(1).split("&")[0];//来源package或order
    if(window.location.search.substring(1).split("&")[2].split("=")[0]=="typeId"){
        type_id = window.location.search.substring(1).split("&")[2].split("=")[1];//题型id
    }else{
        good_sn = decodeURIComponent(window.location.search.substring(1).split("&")[2].split("=")[1]);
    }
    $.ajax({
        type: 'post',
        url: testManager + 'asqAction.action',
        cache: false,
        async: true,
        data:  {
            action: "getTaotiForAge",
            "asqTestUserInfo.id":user_id,
            "asqTaotiAge.taoti.id": type_id,
            "asqTaotiAge.taoti.titalName" : good_sn,
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                link_to("login.html");
            }
            else if(result.mes=='成功'){
                if(result.taotiList!=null){
                    $("#questionnaire_title>h2").text(result.asqTaotiAge.taoti.titalName+result.asqTaotiAge.showName);
                    $("#questionnaire_title>p").text(result.asqTaotiAge.applyMinMonthAge+"个月0天至"+(result.asqTaotiAge.applyMaxMonthAge-1)+"个月30天宝宝");
                    asqTaoti_id = result.asqTaotiAge.id;
                    //一重循环，循环各页面间的div
                    for(var i = 0;i<result.taotiList.length;i++){
                        var taotiList = result.taotiList[i];
                        $("#resultTag ul").append('<li class="tagNow tag_li">'+taotiList.titalName+'</li>');
                        $(".tag_li").eq(0).addClass("tagOld").removeClass("tagNow");
                        $("#topic_details").append('<div data-id="'+taotiList.id+'"></div>');
                        $("#topic_details>div").eq(0).addClass("according_content");
                        //二重循环，循环题目
                        for(var j = 0;j<taotiList.asqQuestionsSet.length;j++){
                            var asqQuestionsSet = taotiList.asqQuestionsSet[j];
                            var picture = '';
                            if(asqQuestionsSet.picture!=null){
                                picture = '<div class="question_img"><img src="'+photo+asqQuestionsSet.picture+'" alt=""></div>';
                            }
                            $("#topic_details>div").eq(i).append('<div class="topic_div" data-id="'+asqQuestionsSet.id+'"><p class="topic_title">'+asqQuestionsSet.sort+'.'+asqQuestionsSet.subject+'</p><p class="gray_s"></p>' +picture+
                                '<ul></ul><p class="gray_1"></p>');
                            //三重循环，循环题目选项
                            for(var k = 0;k<asqQuestionsSet.asqBeenOptionsSet.length;k++){
                                var asqBeenOptionsSet = asqQuestionsSet.asqBeenOptionsSet[k];
                                var icon = 'c_icon';//单选
                                if(asqQuestionsSet.subjecttype != 0){
                                    icon = "b_icon";//多选
                                    //$("#topic_details>div").eq(i).children(".topic_div").eq(k).addClass("multi_select");//给多选题添加类名
                                }
                                if(asqBeenOptionsSet.optionCode == 'radio'){
                                    icon = "e_icon";//多选
                                    //$("#topic_details>div").eq(i).children(".topic_div").eq(k).addClass("multi_select");//给多选题添加类名
                                }
                                var remark = '';
                                if(asqBeenOptionsSet.isNeedRemark == "Y"){
                                    remark = '<div class="other_information"><textarea placeholder="请输入内容"></textarea></div>';
                                }
                                $("#topic_details>div").eq(i).find("ul").eq(j).append('<li data-id="'+asqBeenOptionsSet.id+'"><div class="flex_cont options_li">' +
                                    '<p class="flex_item">'+asqBeenOptionsSet.optionContent+'</p><i class="s_icon '+icon+'"></i></div>'+remark+'</li>');
                            }
                        }
                    }
                    prev();
                    next();
                    if(result.taotiList.length==1){//如果只有一页测试题目
                        $("#next").text("提交");
                    }
                }else{
                    $("#foot_button").hide();
                    $("p.gray_1").hide();
                    no_data("暂无相关测试信息");
                }
                $(".topic_div li>.options_li").click(function() {
                    $(this).parents(".topic_div").css("-webkit-animation","");
                    if($(this).find("i").hasClass("c_icon")){ //    单选
                        $(this).find(".s_icon").addClass("choose");//当前选中
                        $(this).parent(".topic_div li").siblings().find("i").removeClass("choose");//取消其它选中
                        $(this).next(".other_information").show();//显示当前选项文本框
                        $(this).parent(".topic_div li").siblings().find(".options_li").next(".other_information").hide();//隐藏其它选项文本框
                    }
                    else if($(this).find("i").hasClass("b_icon")){//多选
                        $(this).find("i").toggleClass("b_selected");
                        if($(this).find("i").hasClass("b_selected")){
                            $(this).next(".other_information").show();//显示选中项文本框
                        }else{
                            $(this).next(".other_information").hide();//隐藏未选中项文本框
                        }
                    }
                    else if($(this).find("i").hasClass("e_icon")){//父母是否担心
                        $(this).find("i").toggleClass("e_selected");
                        //if($(this).find("i").hasClass("d_selected")){
                        //    $(this).next(".other_information").show();//显示选中项文本框
                        //}else{
                        //    $(this).next(".other_information").hide();//隐藏未选中项文本框
                        //}
                    }
                });
                $(".other_information textarea").focus(function(){
                    $(this).parents(".topic_div").css("-webkit-animation","");
                });
            }
            else{
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
})

//    上一步
function prev(){
    $("#prev").click(function(){
        $("#topic_details .according_content").removeClass("according_content").prev("div").addClass("according_content");
        $(".tagOld").last().addClass("tagNow").removeClass("tagOld");
        $("body").scrollTop("0");
        left = left - 100;
        $(".resultTag ul").scrollLeft(left);
        if(($(".tagOld").length)==1){
            $("#prev").hide();
        }
        if(($(".tagNow").length)>0){
            $("#next").text("下一步");
        }
    });
}

//    下一步
function next(){
    $("#next").click(function(){
        if($(this).text()=="下一步") {
            var group_arr = [];
            var h_height = 0;
            var height = $("#questionnaire_title").height()+$("#resultTag").height()+$("#header").height()+$(".gray_1").height();
            //        循环题目
            for (var m = 0; m < $(".according_content>.topic_div").length; m++) {
                var $div = $(".according_content>.topic_div").eq(m);
                if (!($div.find("i").hasClass("choose") || $div.find("i").hasClass("b_selected"))) {
                    $("body").scrollTop(parseInt(height)+parseInt(h_height));
                    ale("请完善第"+(m+1)+"道选题");
                    $($div).css({"-webkit-animation":"twinkling 1s infinite ease-in-out"});
                    return;
                }
                else if ($div.find(".other_information").length > 0 && $div.find(".other_information").css("display") == "block") {
                    if ($div.find("textarea").val().trim() == "") {
                        $("body").scrollTop(parseInt(height)+parseInt(h_height));
                        ale("请填写第"+(m+1)+"题补充说明框");
                        $($div).css({"-webkit-animation":"twinkling 1s infinite ease-in-out"});
                        return;
                    }
                }
                h_height += $(".gray_1").height() + $(".according_content>.topic_div").eq(m).height();//获取当前题目高度
                var question_id = $div.data("id");
                var openId_arr = [];
//            循环题中选项
                for (var i = 0; i < $div.find("li").length; i++) {
                    if (($div.find("li").eq(i).find("i").hasClass("choose")) || $div.find("li").eq(i).find("i").hasClass("b_selected") || $div.find("li").eq(i).find("i").hasClass("e_selected")) {
                        openId_arr.push($div.find("li").eq(i).data("id"));
                    }
                }
                var answerRemark = '';
                if ($div.find(".other_information").length > 0 && $div.find(".other_information").css("display") == "block") {
                    answerRemark = $div.find("textarea").val();
                }
                var optionId = openId_arr.join(",");
                var obj = {"questionId": question_id, "optionId": optionId, "answerRemark": answerRemark};
                group_arr.push(obj);
            }
            $.ajax({//检查约束
                type: 'post',
                url: testManager + 'asqAction.action',
                cache: false,
                async: true,
                data: {
                    action: "checkAsqAnswner",
                    "asqTaotiAge.id": asqTaoti_id,
                    "orderResultsJson": JSON.stringify(group_arr),
                },
                success: function (result) {
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        link_to("login.html");
                    }
                    else if (result.mes == '成功') {
                        //下一步操作
                        $("#topic_details .according_content").removeClass("according_content").next("div").addClass("according_content");
                        $(".tagNow").first().addClass("tagOld").removeClass("tagNow");
                        $("body").scrollTop("0");
                        left = left + 100;
                        $(".resultTag ul").scrollLeft(left);
                        if (($(".tagNow").length) == 0) {
                            $("#next").text("提交");
                        }
                        if (($(".tagOld").length) > 0) {
                            $("#prev").show();
                        }
                    } else {
                        ale(result.mes);
                    }
                },
                error: function () {
                    console.log(JSON.stringify(group_arr));
                    layer();
                }
            });
        }else {//提交
            var flag = true;//能否提交
            var h_height = 0;
            var height = $("#questionnaire_title").height()+$("#resultTag").height()+$("#header").height()+$(".gray_1").height();
            for (var m = 0; m < $(".according_content>.topic_div").length; m++) {
                var $div = $(".according_content>.topic_div").eq(m);
                if (!($div.find("i").hasClass("choose") || $div.find("i").hasClass("b_selected"))) {
                    flag = false;
                    $("body").scrollTop(parseInt(height)+parseInt(h_height));
                    ale("请完善第"+(m+1)+"道选题");
                    $($div).css({"-webkit-animation":"twinkling 1s infinite ease-in-out"});
                    return;
                }
                else if ($div.find(".other_information").length > 0 && $div.find(".other_information").css("display") == "block") {
                    if ($div.find("textarea").val().trim() == "") {
                        flag = false;
                        $("body").scrollTop(parseInt(height)+parseInt(h_height));
                        ale("请填写第"+(m+1)+"题补充说明框");
                        $($div).css({"-webkit-animation":"twinkling 1s infinite ease-in-out"});
                        return;
                    }
                }
                h_height += $(".gray_1").height() + $(".according_content>.topic_div").eq(m).height();//获取当前题目高度
            }
            if(flag==true){
                var r=confirm("确认提交问卷？");
                if (r==true){
                    data_arr = [];
                    for (var n = 0; n < $("#topic_details>div").length; n++) {
                        var $big = $("#topic_details>div").eq(n);
                        var group_arr = [];
                        var h_height = 0;
                        var height = $("#questionnaire_title").height()+$("#resultTag").height()+$("#header").height()+$(".gray_1").height();
                        //        循环题目
                        for (var m = 0; m < $big.find(".topic_div").length; m++) {
                            var $div = $big.find(".topic_div").eq(m);
                            if (!($div.find("i").hasClass("choose") || $div.find("i").hasClass("b_selected"))) {
                                $("body").scrollLeft(height+h_height)
                                alert(height+h_height);
                                ale("请完善选题");
                                return;
                            }
                            else if ($div.find(".other_information").length > 0 && $div.find(".other_information").css("display") == "block") {
                                if ($div.find("textarea").val().trim() == "") {
                                    ale("请填写补充说明框");
                                    return;
                                }
                            }
                            var question_id = $div.data("id");
                            var openId_arr = [];
//            循环题中选项
                            for (var i = 0; i < $div.find("li").length; i++) {
                                if (($div.find("li").eq(i).find("i").hasClass("choose")) || $div.find("li").eq(i).find("i").hasClass("b_selected") || $div.find("li").eq(i).find("i").hasClass("e_selected")) {
                                    openId_arr.push($div.find("li").eq(i).data("id"));
                                }
                            }
                            var answerRemark = '';
                            if ($div.find(".other_information").length > 0 && $div.find(".other_information").css("display") == "block") {
                                answerRemark = $div.find("textarea").val();
                            }
                            var optionId = openId_arr.join(",");
                            var obj = {"questionId": question_id, "optionId": optionId, "answerRemark": answerRemark};
                            data_arr.push(obj);
                        }
                    }
                    $.ajax({//提交答案
                        type: 'post',
                        url: testManager + 'asqAction.action',
                        cache: false,
                        async: true,
                        data: {
                            action: "saveResultRecode",
                            "asqTestUserInfo.id": user_id,
                            "orderResultsJson": JSON.stringify(data_arr),
                        },
                        success: function (result) {
                            if (result.mes == '请登录') {
                                ale('请登录', '24px');
                                link_to("login.html");
                            }
                            else if (result.mes == '成功') {
                                ale('提交成功', '24px');
                                setTimeout(function(){
                                    if(url_txt.split("=")[0]=="package"){
                                        link_to("evaluate_fd_evaluateResults.html?"+url_txt);
                                    }else{
                                        link_to("evaluate_evaluateResults.html?"+url_txt);
                                    }
                                },1500);
                            } else {
                                ale(result.mes);
                            }
                        },
                        error: function () {
                            console.log(JSON.stringify(data_arr));
                            layer();
                        }
                    });
                }
            }
        }
    });
}

