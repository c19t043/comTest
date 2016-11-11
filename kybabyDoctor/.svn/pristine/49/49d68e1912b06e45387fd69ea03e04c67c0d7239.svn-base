/**
 * Created by lijingwei on 2016/11/9.
 */
//    装主专业id
var select_id = '';
//    装病种id
var professionalId_arr = [],professionalOld_arr=[];
var doctorLifeInfoId = '';//生活信息id
$(function(){
    var url_id = window.location.search.substring(1);
    if(url_id!=''){
        doctorDataMan.doctorInfoId = url_id;
    }
    $.ajax({
        type: 'post',
        url: host+'doctorIdentify.action',
        cache: false,
        async: true,
        data: {
            action: "getSomething"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                link_to("login.html");
            }
            else if (result.mes == '成功') {
                //就职医院
                var hospitalName = '';
                if(result.hospitalBasicInfoList!=null){
                    for(var i = 0;i<result.hospitalBasicInfoList.length;i++){
                        hospitalName += '<li data-id="'+result.hospitalBasicInfoList[i].id+'">'+result.hospitalBasicInfoList[i].hospitalLname+'</li>';
                    }
                }
                $("#layer_list_hospital .layer_list>ul").html(hospitalName);
                //医生职称
                var title = '';
                if(result.positionList!=null){
                    for(var i = 0;i<result.positionList.length;i++){
                        title += '<li data-id="'+result.positionList[i].id+'">'+result.positionList[i].name+'</li>';
                    }
                }
                $("#layer_list_title .layer_list>ul").html(title);
                $(".basic_information li").click(function(){
                    layer_list_li(this);
                })
            } else {
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
    $.ajax({
        type: 'post',
        url: urlWay.doctorDataGather+'doctorDataGather.action',
        cache: false,
        async: true,
        data: {
            action: "initDoctorInfoPage",
            professionFlag: "医生",
            "doctorInfo.id": url_id
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                link_to("login.html");
            }
            else if (result.mes == "成功") {
                var tag = "both";
                if(result.doctorLifeInfo!=null){
                    doctorLifeInfoId = result.doctorLifeInfo.id;
                }
                //医生维护
                if(result.doctorInfo.id!=null){
                    var info = result.doctorInfo;
                    //图片
                    if(info.doctorImage!=null)
                        doctorDataMan.doctorImg = '';
                    $("#doc_name").val(info.doctorName);
                    //性别
                    var doctorSex = info.doctorSex;
                    if(doctorSex == "女"){
                        $("#woman").addClass("c_selected").prev("li").removeClass("c_selected");
                    }
                    $("#doc_tel").val(info.doctorPhone);
                    //头像
                    var img_load = urlWay.hostName+result.uploadDir.substring(2)+'/'+info.doctorImage;
                    $("#doc_portrait img.h_img").prop("src",img_load);
                    $("#doc_hospital").text(info.doctorEmployer);
                    $("#doc_department").text(info.department);
                    $("#doc_title").text(info.doctorTitle);
                    $("#doc_experience input").val(info.clinicalExperience);
                    //主专业方向
                    if(info.major != null){
                        var firstmajor = info.major.id;//主专业方向
                        //var current_id='';
                        for(var i=0;i<result.firstMajors.length;i++){
                            var m_num = result.firstMajors[i].id.toString();
                            if(firstmajor == m_num){
                                $("#doc_direction").text(result.firstMajors[i].major);
                            }
                        }
                    }
                    //亚专业
                    if(info.secondMajorIds != null){
                        var secondMajor_arr = info.secondMajorIds.split("::");//亚专业数组
                        var secondMajor='';//亚专业字符串
                        for(var i=0;i<result.secondMajors.length;i++){
                            var m_num = result.secondMajors[i].id.toString();
                            if($.inArray(m_num, secondMajor_arr)!=-1){
                                secondMajor += result.secondMajors[i].major+" ";
                            }
                        }
                        $("#doc_professional").text(secondMajor);
                    }
                    //病种
                    if(info.thirdMajorIds != null){
                        var thirdMajorId_arr = info.thirdMajorIds.split("::");//病种数组
                        var thirdMajorId='';//病种字符串
                        for(var i=0;i<result.thirdMajors.length;i++){
                            var m_num = result.thirdMajors[i].id.toString();
                            if($.inArray(m_num, thirdMajorId_arr)!=-1){
                                thirdMajorId += result.thirdMajors[i].major+" ";
                            }
                        }
                        $("#doc_diseases").text(thirdMajorId);
                    }
                    //个人擅长
                    if(info.doctorComment != ""&&info.doctorComment != null){
                        $("#good_at").text(info.doctorComment);
                    }
                    //开户行
                    $("#doc_bank").text(info.bankAccountName);
                    //银行卡号
                    $("#doc_bankCard").val(info.bankCard);
                    //开通服务
                    if(info.serviceTypeIds != null){
                        var serviceType_arr = info.serviceTypeIds.split("::");
                        for(var i=0;i<result.doctorServiceTypes.length;i++){
                            var m_num = result.doctorServiceTypes[i].id.toString();
                            if($.inArray(m_num, serviceType_arr)!=-1){
                                $("#doc_service div").append('<span data-id="'+result.doctorServiceTypes[i].id+'" class="selected">'+result.doctorServiceTypes[i].parentDoctorServiceType.serviceTypeName+'</span>')
                            }
                        }
                    }else{
                        $("#doc_service").parent("div").hide();
                    }
                    //推荐人
                    if(info.recommendPhone != null){
                        $("#referees_tel").val(info.recommendPhone);
                    }else{
                        $("#referees_div").hide();
                    }
                    //评价
                    if(result.doctorInfo.doctorImpression != null){
                        $('#evaluation').text(result.doctorInfo.doctorImpression);
                    }
                    //身份信息
                    $('#ID_card').val(result.doctorInfo.idCardNum);
                    $('#practice_num').val(result.doctorInfo.idCard);
                    var doctorCardInfos= result.doctorCardInfos;
                    var uploadDir=hostBG+result.uploadDir.substring(18);
                    if(doctorCardInfos != null && doctorCardInfos.length != 0){
                        for(var i= 0,len=doctorCardInfos.length;i<len;i++){
                            var imgType=doctorCardInfos[i].imgType,
                                ele;
                            switch (imgType){
                                case "执业证书":
                                    ele=$('#practice_certificate');
                                    break;
                                case "职称证书":
                                    ele=$('#certificate_title');
                                    break;
                                case "资格证书":
                                    ele=$('#qualification_certificate');
                                    break;
                            }
                            $(ele).prepend('<div class="updateImg"><form class="file" action="" method="post" enctype="multipart/form-data"> ' +
                                '<img src="'+uploadDir+'/'+doctorCardInfos[i].imgPath+'" class="h_img"/> ' +
//                            '<canvas width="40" height="40" class="canvas"></canvas> ' +
//                            '<input type="file" class="input" accept="images/*" onchange="loadImage(this,'+imgType+')"/> ' +
//                            '<canvas class="canvas1"></canvas> ' +
                                '</form> ' +
                                '<span class="removeImg" onclick="doctorDataMan.removeDoctorImageInfo('+doctorCardInfos[i].id+',this)">×</span>' +
                                '</div>');
                        }
                        if($('#practice_certificate>.updateImg').length>6){
                            $('#practice_certificate>.updateImg:last-child').addClass('hidden');
                        }
                        if($('#certificate_title>.updateImg').length>6){
                            $('#certificate_title>.updateImg:last-child').addClass('hidden');
                        }
                        if($('#qualification_certificate>.updateImg').length>6){
                            $('#qualification_certificate>.updateImg:last-child').addClass('hidden');
                        }
                    }
                    //生活信息
                    var doctorLifeInfo=result.doctorLifeInfo;
                    if(doctorLifeInfo != null){
                        $('#school').val(doctorLifeInfo.graduateSchool);
                        $('#salary>input').val(doctorLifeInfo.hospitalMonthlIncome);
                        if(doctorLifeInfo.degree == ''){
                            $('#degree').text('请选择');
                        }else{
                            $('#degree').text(doctorLifeInfo.degree);
                        }
                        if(doctorLifeInfo.isDivorce=='Y'){
                            $('#divorced>li').removeClass('c_selected');
                            $('#divorced>li[data-sex="Y"]').addClass('c_selected');
                        }
                        $('#school').val(doctorLifeInfo.graduateSchool);
                        if(doctorLifeInfo.specialInterests != null){
                            $('#hobby').text(doctorLifeInfo.specialInterests);
                        }
                        if(doctorLifeInfo.remark != null){
                            $('#other').text(doctorLifeInfo.remark);
                        }
                    }
                    //驳回
                    if(result.laterFlowRecord!=null){
                        if(result.laterFlowRecord.flowStatus=="已驳回"){
                            $(".evaluate").append("<div id='textinstructions'>审批说明："+result.laterFlowRecord.remark+"</div>");
                        }
                    }
                }
                if(result.doctorInfo.flowStatus=="已提交"||result.doctorInfo.flowStatus=="已通过"){
                    tag = "pass";
                    $("#good_at").attr("contenteditable",false);
                    $(".basic input").each(function(){
                        $(this).attr("readonly","readonly");
                    })
                    $("input#doc_bankCard").removeAttr("readonly");
                    $("#layer_list_bank li").click(function(){
                        layer_list_li(this);
                    });
                    $("#layer_list_degree li").click(function(){
                        layer_list_li(this);
                    });
                    $("#doc_portrait input").remove();
                    $("#doc_tel").removeAttr("onblur");
                    layer_choose(tag);
                }
                else{//新建医生
                    //主专业
                    var firstMajors = '';
                    for(var i=0;i<result.firstMajors.length;i++){
                        var firstMajor = result.firstMajors[i];
                        firstMajors += '<li data-id="'+firstMajor.id+'">'+firstMajor.major+'</li>'
                    }
                    $("#layer_list_direction .layer_list>ul").html(firstMajors);
                    $(".direction_information li").click(function(){
                        layer_list_li(this);
                    })
                    //亚专业
                    var secondMajors = '';
                    for(var j=0;j<result.secondMajors.length;j++){
                        var secondMajor = result.secondMajors[j]
                        secondMajors += '<li data-parentId="'+secondMajor.parent.id+'" data-id="'+secondMajor.id+'">'+secondMajor.major+'</li>'
                    }
                    $("#layer_list_professional .layer_list_two>ul").html(secondMajors);
                    //病种
                    var thirdMajors = '';
                    for(var k=0;k<result.thirdMajors.length;k++){
                        var thirdMajor = result.thirdMajors[k];
                        thirdMajors += '<li data-parentId="'+thirdMajor.parent.id+'" data-parentsId="'+thirdMajor.parent.parent.id+'" data-id="'+thirdMajor.id+'">'+thirdMajor.major+'</li>'
                    }
                    $("#layer_list_diseases .layer_list_two>ul").html(thirdMajors);
                    layer_list_two_li();
                    if(result.doctorInfo.authentication!=null){
                        //按结果设置默认选中项
                        $("#layer_list_hospital li").each(function(){
                            if($(this).text()==$("#doc_hospital").text()){
                                $(this).addClass("green");
                            }
                        })
                        $("#layer_list_department li").each(function(){
                            if($(this).text()==$("#doc_department").text()){
                                $(this).addClass("green");
                            }
                        })
                        $("#layer_list_title li").each(function(){
                            if($(this).text()==$("#doc_title").text()){
                                $(this).addClass("green");
                            }
                        })
                        $("#layer_list_direction li").each(function(){
                            if($(this).text()==$("#doc_direction").text()){
                                $(this).addClass("green");
                            }
                        })
                        $("#layer_list_bank li").each(function(){
                            if($(this).text()==$("#doc_bank").text()){
                                $(this).addClass("green");
                            }
                        })
                        $("#layer_list_degree li").each(function(){
                            if($(this).text()==$("#degree").text()){
                                $(this).addClass("green");
                            }
                        })
                        var secondMajors_arr = info.secondMajorIds.split("::");
                        if(result.doctorInfo.major!=null){
                            $("#layer_list_professional .layer_list_two li").each(function(){
                                if($(this).attr("data-parentid")!=result.doctorInfo.major.id){
                                    $(this).hide();
                                }
                                if($.inArray($(this).data("id").toString(), secondMajors_arr)>-1){
                                    $(this).addClass("green");
                                }
                            })
                        }
                        var thirdMajorIds_arr = info.thirdMajorIds.split("::");
                        $("#layer_list_diseases li").each(function(){
                            if($.inArray($(this).attr("data-parentid").toString(), secondMajors_arr)==-1){
                                $(this).hide();
                            }
                            if($.inArray($(this).data("id").toString(), thirdMajorIds_arr)>-1){
                                $(this).addClass("green");
                            }
                        })
                    }
//                    开通服务
                    $("#doc_service div").html("");
                    if(result.doctorInfo.authentication==null){//新添加
                        var doctorServiceTypes = '';
                        for(var m=0;m<result.doctorServiceTypes.length;m++){
                            doctorServiceTypes += '<span data-id="'+result.doctorServiceTypes[m].id+'">'+result.doctorServiceTypes[m].parentDoctorServiceType.serviceTypeName+'</span>';
                        }
                        $("#doc_service div").html(doctorServiceTypes);
                    }else{
                        var serviceType_arr = result.doctorInfo.serviceTypeIds.split("::");
                        for(var i=0;i<result.doctorServiceTypes.length;i++){
                            var m_num = result.doctorServiceTypes[i].id.toString();
                            if($.inArray(m_num, serviceType_arr)!=-1){
                                $("#doc_service div").append('<span data-id="'+result.doctorServiceTypes[i].id+'" class="selected">'+result.doctorServiceTypes[i].parentDoctorServiceType.serviceTypeName+'</span>')
                            }else{
                                $("#doc_service div").append('<span data-id="'+result.doctorServiceTypes[i].id+'">'+result.doctorServiceTypes[i].parentDoctorServiceType.serviceTypeName+'</span>');
                            }
                        }
                    }
                    //推荐人
                    $("#referees_div").show();
                    $("#doc_service span").click(function(){
                        $(this).toggleClass("selected");
                    });
                    //    性别选择
                    $("#doc_sex li").click(function(){
                        $(this).addClass("c_selected").siblings("li").removeClass("c_selected");
                    });
                    layer_choose(tag);
                }
            } else {
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
    bottom_button();
    //阻止冒泡
    $("#layer_div>div").click(function(event){
        event.stopPropagation();
    })
})
//弹出弹框
function layer_choose(obj){
    $(".layer_choose").click(function(event){
        if(obj=="pass"){
            if($(this).children("label").text()!="开户行："&&$(this).children("label").text()!="学位："){
                return;
            }
        }
        event.stopPropagation();
        $("body").css("overflow","hidden");
        var index = $(this).data("id");
        //选中项为主专业方向或亚专业或病种
        if($(this).data("name")=="professional"){
            $("#layer_box>div>div").hide();
            if($(this).find("label").text()=="主专业方向："){
                $("#layer_box").show();
                $("#layer_list_direction").show();
            }else if($(this).find("label").text()=="亚专业："){
                if($("#doc_direction").text()=="请选择"){
                    ale("请先选择主专业方向");
                }else{
                    $("#layer_box").show();
                    $("#layer_list_professional").show();
                }
            }else if($(this).find("label").text()=="病种："){
                if($("#doc_direction").text()=="请选择") {
                    ale("请先选择主专业方向");
                }else if($("#doc_professional").text()=="请选择"){
                    ale("请先选择亚专业");
                }else{
                    $("#layer_box").show();
                    $("#layer_list_diseases").show();
                }
            }
        }else{
            $("#layer_box").show();
            $("#layer_div>div").eq(index).show();
            $("#layer_div>div").eq(index).siblings().hide();
        }
        var height = 45;
        if($("#layer_div>div").eq(index).find(".search_div").length!=0){
            height += 68;
            if($("#layer_div>div").eq(index).find(".bottom_button").length!=0){
                height += 57;
            }
        }
        $("#layer_div ul").height($("#layer_div").height()-height);
    });
}
window.document.onclick=function(){
    $("body").css("overflow","auto");
    $('#layer_box').hide();
}
//    选中弹框内容
function layer_list_li(org){
    var index = $(org).parents(".layer_list").data("id");
    //弹框为主专业方向
    if(index==3){
        if(!$(org).hasClass("green")){
            //若亚专业和病种已被选择则会被清空
            if($("#doc_professional").text()!="请选择"||$("#doc_diseases").text()!="请选择"){
                var r=confirm("切换主专业方向后，亚专业和病种均需重新选择");
                if (r==true){
                    professionalOld_arr = [];
                }else{
                    return;
                }
            }
            $("#doc_professional").text("请选择");
            //清空亚专业选中项
            $("#layer_list_professional .layer_list_two li").each(function(){
                $(this).removeClass('green');
                $(this).show();
            });
            $("#doc_diseases").text("请选择");
            //清空病种选中项
            $("#layer_list_diseases .layer_list_two li").each(function(){
                $(this).removeClass('green');
                $(this).show();
            });
            $(org).addClass("green").siblings("li").removeClass("green");
            $(".layer_choose").eq(index).find(".text-right").text($(org).text());
            select_id = $("#layer_list_direction .layer_list li.green").attr("data-id");
            for(var i=0;i<$("#layer_list_professional .layer_list_two li").length;i++){
                if($("#layer_list_professional .layer_list_two li").eq(i).attr("data-parentId")!=select_id){
                    $("#layer_list_professional .layer_list_two li").eq(i).hide();
                }
            }
        }
        $("#layer_list_direction").hide();
        $("#layer_list_professional").show();
//            控制ul高度
        var height = 45;
        if($("#layer_div>div").eq(index+1).find(".search_div").length!=0){
            height += 68;
            if($("#layer_div>div").eq(index+1).find(".bottom_button").length!=0){
                height += 57;
            }
        }
        $("#layer_div ul").height($("#layer_div").height()-height);
    }else{
        $(org).addClass("green").siblings("li").removeClass("green");
        $(".layer_choose").eq(index).find(".text-right").text($(org).text());
        $("body").css("overflow","auto");
        $('#layer_box').hide();
    }
}
function layer_list_two_li(){
    //弹框为亚专业
    $("#layer_list_professional .layer_list_two li").click(function(){
        $(this).toggleClass("green");
    })
    //弹框为病种
    $("#layer_list_diseases .layer_list_two li").click(function(){
        $(this).toggleClass("green");
    })
}

//    弹框中按钮
function bottom_button(){
    $(".bottom_button").click(function(){
        var index = $(this).prev(".layer_list_two").data("id");
        var li_text = '';
        for(var i=0;i<$(this).prev(".layer_list_two").find(".green").length;i++){
            li_text += $(this).prev(".layer_list_two").find(".green").eq(i).text()+' ';
        }
        //亚专业
        if($(this).text()=="下一步"){
            if(li_text==''||li_text==null){
                ale("请选择亚专业");
                $("#doc_professional").text("请选择");
                return;
            }else{
                $("#layer_list_diseases .layer_list_two li").each(function(){
                    $(this).removeClass('green');
                    $(this).show();
                });
//                    得到亚专业选中项id
                professionalId_arr = [];
                for(var j=0;j<$("#layer_list_professional .layer_list_two li.green").length;j++){
                    professionalId_arr.push(parseInt($("#layer_list_professional .layer_list_two li.green").eq(j).data("id")));
                }
                if(professionalOld_arr.length!=0){
                    if(professionalId_arr.sort().toString()!=professionalOld_arr.sort().toString()){
                        var r=confirm("切换亚专业后，病种均需重新选择");
                        if (r==true){
                            professionalOld_arr = professionalId_arr;
                            $("#layer_list_diseases .layer_list_two li").each(function(){
                                $(this).show();
                            });
                        }else{
                            return;
                        }
                    }
                }else{
                    professionalOld_arr = professionalId_arr;
                }
                $(".layer_choose").eq(index).find(".text-right").text(li_text);
                for(var i=0;i<$("#layer_list_diseases .layer_list_two li").length;i++){
                    var diseases_id=parseInt($("#layer_list_diseases .layer_list_two li").eq(i).attr("data-parentid"));
                    if($.inArray(diseases_id, professionalId_arr)==-1){
                        $("#layer_list_diseases .layer_list_two li").eq(i).hide();
                        $("#layer_list_diseases .layer_list_two li").eq(i).removeClass("green");
                    }
                }
                $("#layer_list_professional").hide();
                $("#layer_list_diseases").show();
            }
        }else{
            if(li_text==''||li_text==null){
                ale("请选择病种");
                $("#doc_diseases").text("请选择");
                return;
            }else{
                $("#layer_list_diseases").hide();
                $(".layer_choose").eq(index).find(".text-right").text(li_text);
                $("body").css("overflow","auto");
                $('#layer_box').hide();
            }
        }
        var height = 45;
        if($("#layer_div>div").eq(index).find(".search_div").length!=0){
            height += 68;
            if($("#layer_div>div").eq(index).find(".bottom_button").length!=0){
                height += 57;
            }
        }
        $("#layer_div ul").height($("#layer_div").height()-height);
    })
}

//保存医生信息
function save_button(org){
    if($("#doc_name").val().trim()==""){
        ale("请填写姓名");
        return;
    }
    if($("#doc_tel").val().trim()==''){
        ale("请填写手机号码");
        return;
    }
    //头像
    if(doctorDataMan.doctorImg==null){
        ale("请上传头像");
        return;
    }
    if($('#doc_hospital').text()=='请选择'){
        ale("请选择就职医院");
        return;
    }
    if($('#doc_department').text()=='请选择'){
        ale("请选择就职科室");
        return;
    }
    if($('#doc_title').text()=='请选择'){
        ale("请选择职称");
        return;
    }
    if($("#doc_experience input").val().trim()==''){
        ale("请填写临床经验");
        return;
    }else if($("#doc_experience input").val()<0||$("#doc_experience input").val()>100){
        ale("请填写正确的临床经验");
        return;
    }
    if($('#doc_direction').text()=='请选择'){
        ale("请选择主专业方向");
        return;
    }
    if($('#doc_professional').text()=='请选择'){
        ale("请选择亚专业方向");
        return;
    }
    if($('#doc_diseases').text()=='请选择'){
        ale("请选择病种");
        return;
    }
    if($("#good_at").text().trim()==''){
        ale("请填写个人擅长");
        return;
    }
    if($('#doc_service span.selected').length==0){
        ale("请选择开通服务");
        return;
    }
    var name = $("#doc_name").val();
    var sex = $("#doc_sex li.c_selected .choose_item").text();
    var phone = $("#doc_tel").val();
    //头像
    var hospital = $('#doc_hospital').text();
    var department = $('#doc_department').text();
    var title = $('#doc_title').text();
    var experience = $('#doc_experience input').val();
    var firstMajorId = $("#layer_list_direction li.green").data("id");
    var secondMajorId_arr = [];
    $("#layer_list_professional .layer_list_two li.green").each(function(){
        secondMajorId_arr.push($(this).data("id"));
    });
    var secondMajorIds = secondMajorId_arr.join("::");
    //病种
    var thirdMajorId_arr = [];
    $("#layer_list_diseases .layer_list_two li.green").each(function(){
        thirdMajorId_arr.push($(this).data("id"));
    });
    var thirdMajorIds = thirdMajorId_arr.join("::");
    //var good_arr = [];
    //$("#good_at span.selected").each(function(){
    //    good_arr.push($(this).data("id"));
    //});
    var goods = $("#good_at").text();
    var bank = '';
    if($('#doc_bank').text()!="请选择"){
        bank = $('#doc_bank').text();
    }
    var bankCard = '';
    if($('#doc_bankCard').val().trim()!=''){
        bankCard = $('#doc_bankCard').val();
    }
    var service_arr = [];
    $("#doc_service span.selected").each(function(){
        service_arr.push($(this).data("id"));
    });
    var service = service_arr.join("::");
    var referees_phone = $('#referees_tel').val();
    //身份提交
    var graduateSchool=$('#school').val();
    var degree=$('#degree').text();
    if(degree=='请选择'){
        degree='';
    }
    var hospitalMonthlIncome=$('#salary>input').val();
    //if(hospitalMonthlIncome != ''){
    //    hospitalMonthlIncome+='K';
    //}
    var isDivorce=$('#divorced>.c_selected').attr('data-sex');
    if(isDivorce == undefined){
        isDivorce='';
    }
    var specialInterests=$('#hobby').text();
    var remark=$('#other').text();
    //生活信息
    var idCardNum=$('#ID_card').val();
    var idCard=$('#practice_num').val();
    //评价
    var evaluation=$('#evaluation').text();
    $.ajax({
        type: 'post',
        url: urlWay.doctorDataGather+'doctorDataGather.action',
        cache: false,
        async: true,
        data:{
            action:"saveOrUpdateDoctorBasicInfo",
            "doctorInfo.id":doctorDataMan.doctorInfoId,
            "doctorInfo.doctorName":name,
            "doctorInfo.doctorSex":sex,
            "doctorInfo.doctorPhone":phone,
            "doctorInfo.doctorTitle":title,
            "doctorInfo.doctorEmployer":hospital,
            "doctorInfo.department":department,
            "doctorInfo.clinicalExperience":experience,
            "doctorInfo.major.id":firstMajorId,
            "doctorInfo.secondMajorIds":secondMajorIds,
            "doctorInfo.thirdMajorIds":thirdMajorIds,
            "doctorInfo.serviceTypeIds":service,
            "doctorInfo.imgBase64":doctorDataMan.doctorImg,
            "doctorInfo.doctorComment":goods,
            "doctorInfo.bankAccountName":bank,
            "doctorInfo.bankCard":bankCard,
            "doctorInfo.recommendPhone":referees_phone,
            //
            "doctorLifeInfo.id":doctorLifeInfoId,
            "doctorLifeInfo.graduateSchool":graduateSchool,
            "doctorLifeInfo.degree":degree,
            "doctorLifeInfo.hospitalMonthlIncome":hospitalMonthlIncome,
            "doctorLifeInfo.isDivorce":isDivorce,
            "doctorLifeInfo.specialInterests":specialInterests,
            "doctorLifeInfo.remark":remark,
            //
            "doctorInfo.idCardNum":idCardNum,
            "doctorInfo.idCard":idCard,
            //
            "doctorInfo.doctorImpression":evaluation
        },
        success:function(result){
            if(result.mes=='成功'){
                doctorDataMan.doctorInfoId=result.doctorInfo.id;
                if($(org).text()=="提交"){
                    $.ajax({
                        type: 'post',
                        url: urlWay.doctorDataGather+'doctorDataGather.action',
                        cache: false,
                        async: true,
                        data: {
                            action: "commitApprove",
                            'doctorInfo.id': doctorDataMan.doctorInfoId
                        },
                        success: function (result) {
                            if (result.mes == '请登录') {
                                ale('请登录', '24px');
                                link_to("login.html");
                            }
                            else if (result.mes == "成功") {
                                ale("提交成功");
                                setTimeout(function(){return_before();},2000);
                            } else {
                                ale(result.mes);
                            }
                        },
                        error: function () {
                            layer();
                        }
                    });
                }else{
                    ale("保存成功");
                }
            }
        },
        error:function(){
            console.log(name);
            console.log(sex);
            console.log(phone);
            console.log(title);
            console.log(hospital);
            console.log(department);
            console.log(experience);
            console.log(firstMajorId);
            console.log(secondMajorIds);
            console.log(thirdMajorIds);
            console.log(doctorDataMan.doctorImg);
            console.log(goods);
            console.log(service);
            console.log(bank);
            console.log(bankCard);
            console.log(referees_phone);
            console.log(graduateSchool);
            console.log(degree);
            console.log(hospitalMonthlIncome);
            console.log(isDivorce);
            console.log(specialInterests);
            console.log(remark);
            console.log(idCardNum);
            console.log(idCard);
            console.log(evaluation);
        }
    });
}
/*
 *     下面是yjl添加的js
 * */
$(function () {
    doctorDataMan.divorced();
    doctorDataMan.changeNav();
});
var doctorDataMan={
    doctorInfoId:null,
    doctorImg:null,
    checkMobile: function () {
        //检查手机号
        var doc_tel=$('#doc_tel').val().trim();
        if((/^1[3|4|5|6|7|8][0-9]\d{4,8}$/.test(doc_tel)) && doc_tel.length==11){
            $.ajax({
                type:"post",
                url:urlWay.doctorDataGather+"doctorDataGather.action",
                async:true,
                cache:false,
                data:{
                    action:"checkDoctorPhone",
                    "doctorInfo.doctorPhone":doc_tel
                },
                success: function (result) {
                    if(result.mes=="成功"){

                    }else{
                        ale(result.mes);
                    }
                },
                error: function () {

                }
            });
            return false;
        }else{
            ale('请正确填写手机号');
        }
    },
    changeNav: function () {
        //    是否离异
        $(".header_nav .flex_item").click(function(){
            var type=$(this).attr('data-type');
            if(type != 'basic' && doctorDataMan.doctorInfoId==null){
                ale('请先完善基本信息');
                return;
            }
            //switch (type){
            //    case 'basic':
            //        $('.header-right').html('<span class="right_topButton" onclick="save_button()">保存</span>');
            //        break;
            //    case 'identify':
            //        $('.header-right').html('<span class="right_topButton" onclick="doctorDataMan.saveOrUpdateDoctorCardInfo();">保存</span>');
            //        break;
            //    case 'life':
            //        $('.header-right').html('<span class="right_topButton" onclick="doctorDataMan.saveOrUpdateDoctorLifInfo();">保存</span>');
            //        break;
            //    case 'evaluate':
            //        $('.header-right').html('<span class="right_topButton" onclick="doctorDataMan.saveOrUpdateDoctorAppraise();">保存</span>');
            //        break;
            //}
            $(this).addClass("header_nav_select").siblings("li").removeClass("header_nav_select");
            $('.doctor_information').hide();
            $('.doctor_information[data-type='+type+']').show();
        });
    },
    divorced: function () {
        //    是否离异
        $("#divorced li").click(function(){
            $(this).addClass("c_selected").siblings("li").removeClass("c_selected");
        });
    },
    saveOrUpdateDoctorLifInfo: function () {
        //var graduateSchool=$('#school').val();
        //var degree=$('#degree').text();
        //if(degree=='请选择'){
        //    degree='';
        //}
        //var hospitalMonthlIncome=$('#salary>input').val();
        ////if(hospitalMonthlIncome != ''){
        ////    hospitalMonthlIncome+='K';
        ////}
        //var isDivorce=$('#divorced>.c_selected').attr('data-sex');
        //if(isDivorce == undefined){
        //    isDivorce='';
        //}
        //var specialInterests=$('#hobby').text();
        //var remark=$('#other').text();
        //保存生活信息
        //$.ajax({
        //    type:"post",
        //    url:urlWay.doctorDataGather+"doctorDataGather.action",
        //    async:true,
        //    cache:false,
        //    data:{
        //        action:"saveOrUpdateDoctorLifInfo",
        //        "doctorInfo.id":doctorDataMan.doctorInfoId,
        //        "doctorLifeInfo.id":doctorLifeInfoId,
        //        "doctorLifeInfo.graduateSchool":graduateSchool,
        //        "doctorLifeInfo.degree":degree,
        //        "doctorLifeInfo.hospitalMonthlIncome":hospitalMonthlIncome,
        //        "doctorLifeInfo.isDivorce":isDivorce,
        //        "doctorLifeInfo.specialInterests":specialInterests,
        //        "doctorLifeInfo.remark":remark
        //    },
        //    success: function (result) {
        //        if(result.mes=="成功"){
        //            ale('生活信息保存成功');
        //        }
        //    },
        //    error: function () {
        //
        //    }
        //});
    },
    uploadDoctorImageInfo: function (imagedata,type,obj) {
        //上传医生资格证书图片
        $.ajax({
            type:"post",
            url:urlWay.doctorDataGather+"doctorDataGather.action",
            async:true,
            cache:false,
            data:{
                action:"uploadDoctorImageInfo",
                "doctorCardInfo.doctorInfo.id":doctorDataMan.doctorInfoId,
                "doctorCardInfo.imgBase64":imagedata,
                "doctorCardInfo.imgType":type
            },
            success: function (result) {
                if(result.mes=="成功"){
                    $(obj).parent().parent().append('<span class="removeImg" onclick="doctorDataMan.removeDoctorImageInfo('+result.doctorCardInfo.id+',this)">×</span>');
                    var len=$(obj).parent().parent().parent().find('.updateImg').length;
                    var hidden='';
                    if(len>=6){
                        hidden='hidden';//最多六张图片，然后隐藏选择图片按钮
                    }
                    $(obj).parent().parent().parent().append('<div class="updateImg '+hidden+'">' +
                        '<form class="file" action="" method="post" enctype="multipart/form-data"> ' +
                        '<img src="images/menuicon/icon_add.png" class="h_img"/> ' +
                        '<canvas width="40" height="40" class="canvas"></canvas> ' +
                        '<input type="file" class="input" accept="images/*" onchange="loadImage(this,\''+type+'\')"/> ' +
                        '<canvas class="canvas1"></canvas> ' +
                        '</form> </div>');
                }
            },
            error: function () {

            }
        });
    },
    removeDoctorImageInfo: function (id,obj) {
        $(obj).parent().parent().parent().find('.updateImg').removeClass('hidden');
        //删除医生资格证书图片
        $.ajax({
            type:"post",
            url:urlWay.doctorDataGather+"doctorDataGather.action",
            async:true,
            cache:false,
            data:{
                action:"removeDoctorImageInfo",
                "doctorCardInfo.id":id
            },
            success: function (result) {
                if(result.mes=='成功'){
                    $(obj).parent().remove();
                }
            },
            error: function () {

            }
        });
    },
    saveOrUpdateDoctorCardInfo: function () {
        //var idCardNum=$('#ID_card').val();
        //var idCard=$('#practice_num').val();
        //身份提交保存
        //$.ajax({
        //    type:"post",
        //    url:urlWay.doctorDataGather+"doctorDataGather.action",
        //    async:true,
        //    cache:false,
        //    data:{
        //        action:"saveOrUpdateDoctorCardInfo",
        //        "doctorInfo.id":doctorDataMan.doctorInfoId,
        //        "doctorInfo.idCardNum":idCardNum,
        //        "doctorInfo.idCard":idCard
        //    },
        //    success: function (result) {
        //        if(result.mes=="成功"){
        //            ale('身份提交保存成功');
        //        }
        //    },
        //    error: function () {
        //
        //    }
        //});
    },
    saveOrUpdateDoctorAppraise: function () {
        //var evaluation=$('#evaluation').text();
        //if(evaluation.trim() == ''){
        //    return;
        //}
        ////保存评价
        //$.ajax({
        //    type:"post",
        //    url:urlWay.doctorDataGather+"doctorDataGather.action",
        //    async:true,
        //    cache:false,
        //    data:{
        //        action:"saveOrUpdateDoctorAppraise",
        //        "doctorInfo.id":doctorDataMan.doctorInfoId,
        //        "doctorInfo.doctorImpression":evaluation
        //    },
        //    success: function (result) {
        //        if(result.mes=="成功"){
        //            ale('评价信息保存成功');
        //        }
        //    },
        //    error: function () {
        //
        //    }
        //});
    }
};
function loadImage(obj,type) {
    var src = $(obj).get(0).files[0];
    var imgType = src.name.split('.');
    imgType = imgType[imgType.length - 1];//返回后缀名，以兼容部分移动端浏览器
    if (imgType == 'jpg') {
        imgType = 'jpeg';
    }
    if (!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')) {
        ale('请选择图片文件');
        return false;
    }

    // 创建 FileReader 对象 并调用 render 函数来完成渲染.
    var reader = new FileReader();
    // 绑定load事件自动回调函数
    var imgData = '';
    reader.onload = function (e) {
        if (e.target.result.substring(5, 10) != 'image') {
            var imgDataArr = e.target.result.split('base64');
            imgData = imgDataArr[0] + "image/" + imgType + ";base64" + imgDataArr[1];
            render(imgData, obj,type);
        } else {
            render(e.target.result, obj,type);
        }
    };
    // 读取文件内容
    reader.readAsDataURL(src);
}
var MAX_HEIGHT = 500;
// 渲染
function render(src, obj,type) {
    var image = new Image();
    image.onload = function () {
        var canvas = $(obj).prev(".canvas").get(0);
        var canvas1 = $(obj).next(".canvas1").get(0);
        var x = image.width;
        var y = image.height;

        if (image.height > MAX_HEIGHT) {
            // 宽度等比例缩放 *=
            image.width *= MAX_HEIGHT / image.height;
            image.height = MAX_HEIGHT;
        }
        var ctx = canvas.getContext("2d");  // 获取 canvas的 2d 环境对象,可以理解Context是管理员，canvas是房子
        var ctx1 = canvas1.getContext("2d");  // 获取 canvas的 2d 环境对象,可以理解Context是管理员，canvas是房子
        ctx.clearRect(0, 0, canvas.width, canvas.height);// canvas清屏
        ctx1.clearRect(0, 0, canvas1.width, canvas1.height);// canvas清屏
        canvas1.width = image.width;  // 重置canvas宽高
        canvas1.height = image.height;
        ctx.drawImage(image, 0, 0, x, y, 0, 0, 40, 40);  // 将图像绘制到canvas上
        ctx1.drawImage(image, 0, 0, x, y, 0, 0, image.width, image.height);  // 将图像绘制到canvas上
    };
    image.src = src;  // 记住必须先绑定事件，才能设置src属性，否则会出同步问题。
    setTimeout(function () {
        sendImage(obj,type);
    }, 2000);
    $(obj).prev(".canvas").show();
}
function sendImage(obj,type) {
    var canvas1 = $(obj).next(".canvas1").get(0);
    // 获取Base64编码后的图像数据，格式是字符串
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。
    var dataurl = canvas1.toDataURL("image/png");
    // 为安全 对URI进行编码
    // data%3Aimage%2Fpng%3Bbase64%2C 开头
    var imagedata = encodeURIComponent(dataurl);
    if(type=='头像'){
        doctorDataMan.doctorImg=imagedata;
        $(obj).css({'zIndex':22});
        return;
    }
    doctorDataMan.uploadDoctorImageInfo(imagedata,type,obj);
}

