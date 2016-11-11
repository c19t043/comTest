/**
 * Created by lijingwei on 2016/10/26.
 */
$(function(){
    var url_id = window.location.search.substring(1);
    $("#result_nav>.result_tit").click(function(){
        $(this).addClass("selected");
        $(this).siblings().removeClass("selected");
        var index = $(this).data("index");
        $("#content_div>div").eq(index).show().siblings().hide();
    })
    $.ajax({
        type: 'post',
        url: testManager + 'asqAction.action',
        cache: false,
        async: true,
        data: {
            action: "getAsqResultDetail",
            "asqResultScoreEx.id": url_id,
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                link_to("login.html");
            }
            else if (result.mes == '成功') {
//                    页面标题
                $("#questionnaire_title>h2").text(result.asqResultScoreEx.asqTaotiAge.taoti.titalName + result.asqResultScoreEx.asqTaotiAge.showName);
                $("#questionnaire_title>p").text(result.asqResultScoreEx.asqTaotiAge.applyMinMonthAge + "个月0天至" + (result.asqResultScoreEx.asqTaotiAge.applyMaxMonthAge - 1) + "个月30天宝宝");
//                    宝宝信息
                var userInfo = result.asqResultScoreEx.asqTestUserInfo;
                var setRightAge = '';
                if (userInfo.gestationalWeeks < 37) {
                    setRightAge = '<li class="flex_cont"><p>早产、矫正龄：</p><p class="flex_item">' + userInfo.setRightAge + '</p></li>'
                }
                $("#result_ul").append('<li class="flex_cont"><p>姓名：</p><p class="flex_item">' + userInfo.asqUserName + '</p></li>' +
                    '<li class="flex_cont"><p>性别：</p><p class="flex_item">' + userInfo.asqUserSex + '</p></li>' +
                    '<li class="flex_cont"><p>出生日期：</p><p class="flex_item">' + userInfo.asqUserBirthday + '</p></li>' +
                    '<li class="flex_cont"><p>提交问卷日期：</p><p class="flex_item">' + userInfo.optionTime + '</p></li>' +
                    '<li class="flex_cont"><p>宝宝生活龄：</p><p class="flex_item">' + userInfo.babyLifeAge + '</p></li>' +
                    '<li class="flex_cont"><p>宝宝孕周：</p><p class="flex_item">' + userInfo.gestationalWeeks + '周' + userInfo.gestationalDays + '天</p></li>' +
                    '<li class="flex_cont"><p>出生时情况：</p><p class="flex_item">' + userInfo.birthCondition + '</p>' +
                    '</li>' + setRightAge);

//                    发育问卷
                if (result.taotiList != null) {
                    //循环问题类型
                    for (var i = 0; i < result.taotiList.length; i++) {
                        var taotiList = result.taotiList[i];
                        $("#question_information").append('<div><p class="project_title">' + taotiList.titalName + '</p><div class="problem_item"></div></div>');
                        //信息汇总各问题得分情况循环标题
//                            $("#list_two").append('<li><span>' + taotiList.titalName + '</span></li>');
                        //循环题目
                        if (taotiList.asqQuestionsSet != null) {
                            for (var j = 0; j < taotiList.asqQuestionsSet.length; j++) {
                                var asqQuestions = taotiList.asqQuestionsSet[j];
                                var picture = '';
                                //判断是否有图片
                                if (asqQuestions.picture != null) {
                                    picture = '<div class="question_img"><img src="' + photo + asqQuestions.picture + '" alt=""></div>';
                                }
                                //信息汇总各问题得分情况循环得分情况
//                                    $("#list_two li").eq(i).append('<span>'+asqQuestions.asqQuestionRecord.score+'</span>');
                                $(".problem_item").eq(i).append('<div data-id="' + asqQuestions.id + '"><div><p class="problem_title">' + asqQuestions.sort + '.' + asqQuestions.subject + '</p></div>' + picture + '<ul class="choose_ul"></ul></div>');
                                //循环选项
                                if (asqQuestions.asqBeenOptionsSet != null) {
                                    for (var k = 0; k < asqQuestions.asqBeenOptionsSet.length; k++) {
                                        var optionsSet = asqQuestions.asqBeenOptionsSet[k];//得到当前选项
                                        var choose = 'select_option';
                                        if (asqQuestions.subjecttype == 1) {
                                            choose = "d_select_option";
                                        }
                                        $(".problem_item").eq(i).find(".choose_ul").eq(j).append('<li data-id="' + optionsSet.id + '"><span class="' + choose + '"></span><span class="choose_item">' + optionsSet.optionContent + '</span></li>');
                                    }
                                }
                                if (asqQuestions.asqQuestionRecordList != null) {
                                    for (var l = 0; l < asqQuestions.asqQuestionRecordList.length; l++) {
                                        var asqBeenOptions = asqQuestions.asqQuestionRecordList[l].asqBeenOptions;
                                        //判断是否有文字说明
                                        if(asqBeenOptions.isNeedRemark=="Y") {
                                            $(".problem_item").eq(i).find(".choose_ul").eq(j).after('<div class="problem_instructions">说明： <p>' + asqQuestions.asqQuestionRecordList[l].answerRemark + '</p></div>');
                                        }
                                        var selected_id = asqBeenOptions.id;//选中项id
                                        //可选可不选项
                                        if(asqBeenOptions.optionCode == "radio"){
                                            var code_id = asqBeenOptions.id;
                                        }
                                        var div_len = $(".problem_item").eq(i).find(".choose_ul").eq(j).find("li");
                                        for(var m=0;m<div_len.length;m++){
                                            if($(div_len).eq(m).data("id")==code_id){
                                                $(div_len).eq(m).find(".select_option").addClass("d_select_option");
                                                $(div_len).eq(m).find(".select_option").removeClass("select_option");
                                            }
                                            if($(div_len).eq(m).data("id")==selected_id){
                                                $(div_len).eq(m).addClass("c_selected");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    no_data_two("#question_information","暂无相关信息");
                }

//                    信息汇总
                if(result.asqResultScoreEx.status=="待审阅"){
                    $("#all_information .project_div").hide();
                    no_data_two("#all_information","测评结果处理中，请耐心等待");
                }else{
                    if(result.asqResultScoreExList!=null){
                        var list_one_html = '';
                        for(var i = 0;i<result.asqResultScoreExList.length;i++){
                            var asqResult = result.asqResultScoreExList[i];
                            list_one_html += '<ul class="flex_cont"><li class="flex_item">'+asqResult.sumScore+'</li><li class="flex_item">'+asqResult.resultDescription+'</li></ul>';
                        }
                        $("#list_one").html(list_one_html);
                    }else{
                        no_data_two("#all_information","暂无相关信息");
                    }
                }
                //医生解读与后续指导
                if(result.asqResultScoreEx.asqTestUserInfo.doctorReading!=null){
                    $('#doctor_reading').show();
                    $("#doctor_reading .doctor_interpretation").html(result.asqResultScoreEx.asqTestUserInfo.doctorReading);
                }
//                   亲子活动
                if (result.asqParentChildActivityList != null) {
                    var html = '';
                    for (var i = 0; i < result.asqParentChildActivityList.length; i++) {
                        var activityList = result.asqParentChildActivityList[i];
                        html += '<div class="active_div">' + activityList.activityContent + '</div>';
                    }
                    $("#active_information").html(html);
                }
                else {
                    no_data_two("#active_information","暂无相关信息");
                }
            }
            else {
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
})