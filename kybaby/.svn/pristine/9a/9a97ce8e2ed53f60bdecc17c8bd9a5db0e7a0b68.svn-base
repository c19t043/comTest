/**
 * Created by lijingwei on 2016/10/14.
 */
var gestationalWeek = '';
var gestationalDay = '';
$(function(){
    time_plug();
//       动态设置弹框高度
    if($(window).height()<450){
        $("#layer_content").height($(window).height()-45);
    }
    $("#placeholder").height($(window).height()-$("#layer_content").height());
    $("#layer_content").css("marginTop",$(window).height()-$("#layer_content").height());

//        失去焦点验证孕周
    $("#gestational_week").blur(function(){
        if($(this).val()<10 || $(this).val()>99){
            ale('孕周周数范围为10-99周');
            return;
        }
    });
    $("#gestational_day").blur(function(){
        if($(this).val()<0 || $(this).val()>6){
            ale('孕周天数范围为0-6天');
            return;
        }
    })
//        弹出弹框
    $("#birth_situation").parents(".information_div").click(function(){
        if(($("#gestational_week").val().trim()=="") || ($("#gestational_day").val().trim()=="")){
            ale("请先填写宝宝孕周");
            return;
        }
        if($("#gestational_week").val()<10 || $("#gestational_week").val()>99) {
            ale('孕周周数范围为10-99周');
            return;
        }
        if($("#gestational_day").val()<0 || $("#gestational_day").val()>6){
            ale('孕周天数范围为0-6天');
            return;
        }
        gestationalWeek = $("#gestational_week").val();
        gestationalDay = $("#gestational_day").val();
        if($("#gestational_week").val()<37){
            $("#layer_content li").eq(1).children("i").addClass("choose");
            $("#layer_content li").eq(0).children("i").removeClass("choose");
        }else{
            $("#layer_content li").eq(0).children("i").addClass("choose");
            $("#layer_content li").eq(1).children("i").removeClass("choose");
        }
        $("#layer_mark").fadeIn();
    });

    //        弹框中出生情况选择
    $("#layer_content li").click(function(){
        $(this).children(".s_icon").toggleClass("choose");
        if($("#other_abnormal").next("i").hasClass("choose")){
            $("#other_information").show();
        }else{
            $("#other_information").hide();
        }
    });

    //        点击空白处弹框隐藏
    $("#placeholder").click(function() {
        $("#layer_mark").fadeOut();
    });

    //        点击弹框中确定
    $("#layer_submit").click(function() {
        var text = '';
        for(var i=0;i<$(".choose").length;i++){
            var txt = $(".choose").eq(i).prev("p").text();
            if(txt == "其他异常情况"){
                if($("#other_information textarea").val().trim()!=""){
                    txt = "其他异常情况("+$("#other_information textarea").val()+")";
                }
            }
            text += txt+'&nbsp;&nbsp;&nbsp;';
        }
        if($("#layer_content li").eq(0).children("i").hasClass("choose") && $("#layer_content li").eq(1).children("i").hasClass("choose")){
            ale("足月和早产不能同时选择");
            return;
        }
        if($("#layer_content li").eq(2).children("i").hasClass("choose") && $("#layer_content li").eq(3).children("i").hasClass("choose")){
            ale("顺产和剖腹产不能同时选择");
            return;
        }
        if($("#layer_content li").eq(6).children("i").hasClass("choose") && $("#layer_content li").eq(7).children("i").hasClass("choose")){
            ale("双胎和多胎不能同时选择");
            return;
        }
        $("#layer_mark").fadeOut();
        $("#birth_situation").html(text);
    });

    //        下一步
    $("#next_button .login_button").click(function() {
        var url_txt = window.location.search.substring(1).split("&")[0];
        var type = '';
        if(window.location.search.substring(1).split("&")[1].split("=")[0]=="typeId"){//检查时从家庭医生进入还是订单进入
            type = "typeId="+window.location.search.substring(1).split("&")[1].split("=")[1];
        }else{
            type = "goodSn="+window.location.search.substring(1).split("&")[1].split("=")[1];
        }
        if(url_txt.split("=")[0]=="order"){
            var order_id = url_txt.split("=")[1];//订单id
        }else{
            var package_id = url_txt.split("=")[1];//包id
        }
        if($("#baby_name").val().trim()==""){
            ale("请填写宝宝姓名");
            return;
        }else{
            var babyName = $("#baby_name").val();
        }
        var baby_birthday = $("#appDate").val();
        var date_arr = baby_birthday.split('-');
        var dyear = (new Date()).getFullYear();
        var dmonth = (new Date().getMonth())+1;
        var dday = (new Date().getDate());
        var nyear = parseInt(date_arr[0]);
        var nmonth = parseInt(date_arr[1]);
        var nday = parseInt(date_arr[2]);

        if($("#appDate").val()==""){
            ale("请填写宝宝出生日期");
            return;
        }else if(nyear > dyear){
            ale('请选择正确的生日');
            return false;
        }else if(nyear == dyear && nmonth > dmonth){
            ale('请选择正确的生日');
            return false;
        }else if(nyear == dyear && nmonth == dmonth && nday>dday){
            ale('请选择正确的生日');
            return false;
        }else{
            var babyBirthDate = $("#appDate").val();
        }
        if(($("#gestational_week").val().trim()=="") || ($("#gestational_day").val().trim()=="")){
            ale("请先填写宝宝孕周");
            return;
        }
        if($("#gestational_week").val()<10 || $("#gestational_week").val()>99) {
            ale('孕周周数范围为10-99周');
            return;
        }
        if($("#gestational_day").val()<0 || $("#gestational_day").val()>6){
            ale('孕周天数范围为0-6天');
            return;
        }
        gestationalWeek = $("#gestational_week").val();
        gestationalDay = $("#gestational_day").val();
        if(($("#birth_situation").text()=="请选择")){
            ale("请填写宝宝出生情况");
            return;
        }
        else{
            var birthSituation = $("#birth_situation").text();

        }
        if($("#gestational_week").val()<37 && $("#layer_content li").eq(0).children("i").hasClass("choose")){
            ale("孕周小于37周,不能选择足月");
            return;
        }
        if($("#gestational_week").val()>37 && $("#layer_content li").eq(1).children("i").hasClass("choose")) {
            ale("孕周大于37周,不能选择早产");
            return;
        }
        var babySex = $(".c_selected .choose_item").text();
        $.ajax({
            type: 'post',
            url: testManager + 'asqUserAction.action',
            cache: false,
            async: true,
            data:  {
                action: "saveOrUpdateAsqTestUserInfo",
                "asqTestUserInfo.asqUserName":babyName,
                "asqTestUserInfo.asqUserSex":babySex,
                "asqTestUserInfo.asqUserBirthday":babyBirthDate,
                "asqTestUserInfo.gestationalWeeks":gestationalWeek,
                "asqTestUserInfo.gestationalDays":gestationalDay,
                "asqTestUserInfo.birthCondition":birthSituation,
                "b2cGoodsOrder.id" : order_id,
                "fdServicePackage.id" : package_id,
            },
            success: function (result) {
                if(result.mes=='请登录'){
                    ale('请登录', '24px');
                    link_to("login.html");
                }
                else if(result.mes=='成功'){
                    var user_id = result.asqTestUserInfo.id;//宝宝id
                    link_to("evaluate_fd_fill_questionnaire.html?"+url_txt+"&userId="+user_id+"&"+type);
                }
                else{
                    ale(result.mes);
                }
            },
            error: function () {
                layer();
            }
        });
    });

    //性别选择
    $("#baby_sex li").click(function(){
        $(this).addClass("c_selected").siblings("li").removeClass("c_selected");
    });
});

window.onresize = function(){
    if($(window).height()<450){
        $("#layer_content").height($(window).height()-45);
    }
    $("#placeholder").height($(window).height()-$("#layer_content").height());
    $("#layer_content").css("marginTop",$(window).height()-$("#layer_content").height());
}
/*****************     日期插件BEGIN     *****************/
function time_plug(){
    var currYear = (new Date()).getFullYear();
    var opt = {};
    opt.date = {preset: 'date'};
    opt.datetime = {preset: 'datetime'};
    opt.time = {preset: 'time'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
        startYear: currYear - 50, //开始年份
        endYear: currYear//结束年份
    };

    $("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
    var optDateTime = $.extend(opt['datetime'], opt['default']);
    var optTime = $.extend(opt['time'], opt['default']);
//		$("#appDate").mobiscroll(optDateTime).datetime(optDateTime);
//		 $("#appTime").mobiscroll(optTime).time(optTime);
}
/*****************     日期插件END     *****************/