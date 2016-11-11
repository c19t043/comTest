/**
 * Created by windows on 2016/5/11.
 */
var orderId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);
var handleFlag=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
var orgId=decodeURIComponent(window.location.search.substring(1).split('&')[2]);
var clinicMedicalRecordsId='';
function org_outpatient(){
    window.location.href='org_outpatient.html?'+orderId+'&门诊预约';
}
$(function () {
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgClinicManager.action',
        cache: false,
        async: false,
        data: {
            action: "getOrderContactsInfoByOrderId",
            "orderInfoClinic.id":orderId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                var data=result.orderInfoClinic;
                $('.list01 input').val(data.userInfo.babyName);
                $('.list03 input').val(result.organOperator.hospitalBasicInfo.hospitalLname);
                $('.list04 input').val(data.doctorInfo.doctorName);
                $('.list02 input').val(data.appointmentDate+' '+data.appointmentBeganTime+'~'+data.appointmentEndTime);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgClinicManager.action',
        cache: false,
        async: false,
        data: {
            action: "getAllSymptomTag"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                if(result.symptomTagList!=null){
                    var symptomTagList=result.symptomTagList;
                    var html='症状记录标签：&nbsp;&nbsp;&nbsp;&nbsp;';
                    for(var i= 0,len=symptomTagList.length;i<len;i++){
                        html+='<span data-id="'+symptomTagList[i].id+'">'+symptomTagList[i].symptomName+'</span>';
                    }
                    $('.symptomsRecord').html(html);
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    if(handleFlag=='查看'){
        $('#updateHealthRecord,#updatediagnostic,#updatesymptoms').hide();
        $("input").attr("readonly","readonly");
        $("select").attr("disabled","disabled");
        $("textarea").attr("readonly","readonly");
    };
    //获取健康档案信息
    $('.babyHealth').parents(".information").click(function () {
        $('#babyHealth').show();
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost + 'orgClinicManager.action',
            cache: false,
            async: false,
            data: {
                action: "getBabyBasicData2",
                "orderInfoClinic.id":orderId
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "org_login.html";
                }
                else if (result.mes == "成功") {
                    if(result.babyBasicData == null){
                        return false;
                    }
                    $.each(result.babyBasicData,function(key,val){
                        var obj = $(".levelTwo input,.levelTwo select,.levelTwo textarea").filter(function(){
                            return $(this).attr("id") == key;
                        });
                        if(obj.get(0) == undefined){
                            return true;
                        }else if(obj.get(0).tagName == "INPUT"){
                            obj.val(val);
                        }else if(obj.get(0).tagName == "TEXTAREA"){
                            obj.val(val);
                        }else if(obj.get(0).tagName == "SELECT"){

                            obj.find("option").each(function(){
                                $(this).removeAttr('selected');
                                if(val==''&& $(this).text()=='请选择'){
                                    $(this).attr('selected','selected');
                                }else if($(this).text() == val){
                                    $(this).attr('selected','selected');
                                }
                            });
                            if(obj.parent().next().hasClass("forDesc")){
                                if(val == "无" || val == "否"){
                                    obj.parent().next().hide();
                                }
                            }
                        }else{
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });

    });
    $('#cancelHealthRecord').click(function () {
        $('#babyHealth').hide();
    });
    //诊断
    $('.diagnostic').parents(".information").click(function () {
        $('#diagnosticCover').show();
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost+'orgClinicManager.action',
            cache: false,
            async: false,
            data:{
                action: "getClinicMedicalRecords",
                "orderInfoClinic.id":orderId
            },
            success: function (result) {
                if(result.clinicMedicalRecords==null){
                    return false;
                }else{
                    clinicMedicalRecordsId=result.clinicMedicalRecords.id;
                    $('#diagnosticCovermessage').val(result.clinicMedicalRecords.diagnosticInformation);
                    var drugInfoList=result.clinicMedicalRecords.drugInfoList;
                    var html='';
                    if(drugInfoList!=null){
                        for(var i= 0,len=drugInfoList.length;i<len;i++){
                            html+='<span class="blue" data-drug-id="'+drugInfoList[i].id+'">'+drugInfoList[i].commonName+'</span>';
                        }
                    }
                    if(handleFlag=='编辑'){
                        html+='<span onclick="adddrug()">+</span>';
                    }
                    $('.diagnosticRecord>a').html(html);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    $('#canceldiagnostic').click(function () {
        $('#diagnosticCover').hide();
    });
    $('#updatediagnostic').click(function () {
        $('#diagnosticCover').hide();
        var drug_id='';
        var drug_id_list=$('.diagnosticRecord .blue');
        $(drug_id_list).each(function () {
            drug_id+=','+$(this).attr('data-drug-id');
        });
        drug_id=drug_id.substring(1);
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost+'orgClinicManager.action',
            cache: false,
            async: false,
            data:{
                action: "saveOrUpdateClinicMedicalRecords",
                "clinicMedicalRecords.id":clinicMedicalRecordsId,
                "flag":'诊断信息',
                "clinicMedicalRecords.orderInfoClinic.id":orderId,
                "clinicMedicalRecords.diagnosticInformation":$('#diagnosticCovermessage').val(),
                "clinicMedicalRecords.drugIds":drug_id
            },
            success: function (result) {

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    //症状
    $('.symptoms').parents(".information").click(function () {
        $('#symptomsCover').show();
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost+'orgClinicManager.action',
            cache: false,
            async: false,
            data:{
                action: "getClinicMedicalRecords",
                "orderInfoClinic.id":orderId
            },
            success: function (result) {
                if(result.clinicMedicalRecords==null){
                    return false;
                }else{
                    clinicMedicalRecordsId=result.clinicMedicalRecords.id;
                    $('#symptomsCovermessage').val(result.clinicMedicalRecords.symptomDescribe);
                    if(result.clinicMedicalRecords.symptomTagIds!=null && result.clinicMedicalRecords.symptomTagIds!=''){
                        var list=result.clinicMedicalRecords.symptomTagIds.split(',');
                        for(var i= 0,len=list.length;i<len;i++){
                            $('.symptomsRecord>span[data-id='+list[i]+']').addClass('blue');
                        };
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });

    });
    $('#cancelsymptoms').click(function () {
        $('#symptomsCover').hide();
    });
    $('#updatesymptoms').click(function () {
        $('#symptomsCover').hide();
        var symptomsRecordIdList='';
        var list=$('.symptomsRecord>span').filter('.blue');
        if(list.length!=0){
            $(list).each(function () {
                symptomsRecordIdList+=','+$(this).attr('data-id');
            });
            symptomsRecordIdList=symptomsRecordIdList.substring(1);
        }
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost+'orgClinicManager.action',
            cache: false,
            async: false,
            data:{
                action: "saveOrUpdateClinicMedicalRecords",
                "clinicMedicalRecords.id":clinicMedicalRecordsId,
                "flag":'症状描述',
                "clinicMedicalRecords.orderInfoClinic.id":orderId,
                "clinicMedicalRecords.symptomDescribe":$('#symptomsCovermessage').val(),
                "clinicMedicalRecords.symptomTagIds":symptomsRecordIdList
            },
            success: function (result) {

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    //症状标签
    $('.symptomsRecord>span').click(function () {
        $(this).toggleClass('blue');
    });
    //健康档案分类
    $('.babyHealthRecord_info .levelOne li').click(function () {
        $(this).addClass('liActive').siblings().removeClass('liActive');
        var data_num=$(this).attr('data-num');
        var divList=$('.levelTwo>div');
        divList.hide();
        divList.eq(data_num).show();
    });
    //保存健康档案信息
    $('#updateHealthRecord').click(function () {
        var recordHealth=recordHealthData();
        if(recordHealth==false){
            return false;
        }
        $('#babyHealth').hide();
        $.ajax({
            type: 'post',
            url: urlWay.orgBusinessHost+'orgClinicManager.action',
            cache: false,
            async: false,
            data:recordHealth,
            success: function (result) {

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    });
    //就诊者记录列表
    $('#footer>p').click(function () {
        window.location.href='org_userpatient_list.html?'+orderId;
    });
});
//健康档案数据
function recordHealthData(){
    var jsonData = {};
    var num=0;
    $(".levelTwo input[type!='checkbox'],.levelTwo select,.levelTwo textarea").each(function(){
        var key =  "babyBasicData."+$(this).attr("id");
        var val = $(this).val().trim();
        if($(this).attr("placeholder")!=undefined){
            if($(this).hasClass("mustNumber")){
                if(val!=''){
                    if(/^[0-9]+(.[0-9]{1,3})?$/.test(val)){

                    }else{
                        alert($(this).attr("placeholder")+'这一栏请填写数字');
                        num++;
                    }
                }
            } else{
                if($(this).attr("id")=='origin'){
                    if(val.length>40){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过40个');
                        num++;
                    }
                }else if($(this).attr("id")=='birthWeight' || $(this).attr("id")=='birthBodyLength'|| $(this).attr("id")=='maternalRiskFactors'|| $(this).attr("id")=='prenatalEducation'|| $(this).attr("id")=='earlyFeeding'|| $(this).attr("id")=='mainCaregivers'|| $(this).attr("id")=='geneticHeight'|| $(this).attr("id")=='degreeOfFatherEducation'|| $(this).attr("id")=='heightOfFather'|| $(this).attr("id")=='degreeOfMotherEducation'|| $(this).attr("id")=='heightOfMother' ||$(this).attr("id")=='bornWay'||$(this).attr("id")=='embryoNumber'){
                    if(val.length>10){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过10个');
                        num++;
                    }
                }else if($(this).attr("id")=='bornHospital'||$(this).attr("id")=='twinBrotherAndSister'){
                    if(val.length>30){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过30个');
                        num++;
                    }
                }else if($(this).attr("id")=='familyHistory'){
                    if(val.length>100){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过100个');
                        num++;
                    }
                }else if($(this).attr("id")=='drugAllergyHistory'||$(this).attr("id")=='hospitalizedRemark'||$(this).attr("id")=='allergiesRemark'||$(this).attr("id")=='vaccineAbnormalRemark'||$(this).attr("id")=='motherBadHabits'||$(this).attr("id")=='livingEnvironment'||$(this).attr("id")=='hospitalized'||$(this).attr("id")=='vaccineAbnormalReaction'||$(this).attr("id")=='fuFamilyGeneticDisease'||$(this).attr("id")=='maFamilyGeneticDisease'||$(this).attr("id")=='fatherBadHabits'){
                    if(val.length>50){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过50个');
                        num++;
                    }
                }else if($(this).attr("id")=='permanentAddress'){
                    if(val.length>60){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过60个');
                        num++;
                    }
                }else if($(this).attr("id")=='motherAge'||$(this).attr("id")=='otherBabiesGrowTogether'||$(this).attr("id")=='whetherOnlyChild'||$(this).attr("id")=='dataProvider'||$(this).attr("id")=='fatherAge'||$(this).attr("id")=='earlyLearning'||$(this).attr("id")=='allergies'||$(this).attr("id")=='historyOfDisease'||$(this).attr("id")=='childProtectionAgency'||$(this).attr("id")=='regularChildProtection'||$(this).attr("id")=='vaccinationAgency'||$(this).attr("id")=='regularInjections'){
                    if(val.length>5){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过5个');
                        num++;
                    }
                }else if($(this).attr("id")=='phoneOfMother'||$(this).attr("id")=='phoneOfFather'){
                    if(val.length>11){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过11个');
                        num++;
                    }
                }else if($(this).attr("id")=='oftenGoHospital'){
                    if(val.length>200){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过200个');
                        num++;
                    }
                }else{
                    if(val.length>20){
                        alert($(this).attr("placeholder")+'这一栏填写字符不超过20个');
                        num++;
                    }
                }
            }
        }
        if(val=='请选择'){
            val='';
        }
        jsonData[key] = val;
    });
    jsonData["orderInfoClinic.id"] = orderId;
    jsonData["action"] = "saveOrUpdateBabyBasicData";
    if(num>0){
        return false;
    }
    return jsonData;
}
//用药
function adddrug(){
    $('#addDrug').show();
    $('#chooseDrug').html($('.diagnosticRecord>a').html());
    $('#chooseDrug>span:last').remove();
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost+'drugInfoManage.action',
        cache: false,
        async: false,
        data:{
            action: "getDrugClassificationAndDrugList"
        },
        success: function (result) {
            var drugClassificationList=result.drugClassificationList;
            if(drugClassificationList!=null){
                var drugClassificationList_html='';
                for(var i= 0;i<drugClassificationList.length;i++){
                    drugClassificationList_html+='<div class="drug_list"> ' +
                    '<div class="personal_list_new"> ' +
                    '<div onclick="showdetail(this)"> ' +
                    '<p class="personal_left">'+drugClassificationList[i].className+'</p> ' +
                    '<p class="personal_right"><img src="images/org_zhankai.jpg" alt=""/></p> ' +
                    '</div> ' +
                    '<div class="content"> ';
                    var childClassList=drugClassificationList[i].childClassList;
                    if(childClassList!=null){
                        var childClassList_html='';
                        for(var j=0;j<childClassList.length;j++){
                            childClassList_html+='<div class="contentDetail"> ' +
                            '<p onclick="showdrugdetail(this)">'+childClassList[j].className+'</p> ' +
                            '<div class="content_detail"> ';
                            var drugInfoList=childClassList[j].drugInfoList;
                            var drugInfoList_html='';
                            if(drugInfoList!=null){
                                for(var k=0;k<drugInfoList.length;k++){
                                    drugInfoList_html+='<span data-drug-id="'+drugInfoList[k].id+'">'+drugInfoList[k].commonName+'</span>'
                                }
                            }
                            childClassList_html+=drugInfoList_html+'</div></div>';
                        }
                    }
                    drugClassificationList_html+=childClassList_html+'</div> ' +
                    '</div> ' +
                    '<p class="gray_ss"></p> ' +
                    '</div>';
                }
                $('#allDrugList').html(drugClassificationList_html);
            }
            var drug_id_list=$('.diagnosticRecord .blue');
            $(drug_id_list).each(function () {
                var index=$(this).attr("data-drug-id");
                $('.content_detail>span[data-drug-id='+index+']').addClass('blue');
            });
            drugMessage();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    var height=$('#chooseDrug').height();
    height+=45;
    $('#allDrugList').css({'marginTop':height});
}
//药品列表
function showdetail(div){
    $(div).next().slideToggle();
    $(div).find('.personal_right img').toggleClass('zhuan');
}
function showdrugdetail(div){
    $(div).next().slideToggle();
}
function drugMessage() {
    //选择药品
    $('.content_detail>span').click(function () {
        $(this).toggleClass('blue');
        if($(this).hasClass('blue')){
            $('#chooseDrug').append($(this).clone());
        }else{
            var choose_id=$(this).attr('data-drug-id');
            $('#chooseDrug>span[data-drug-id='+choose_id+']').remove();
        }
        var height=$('#chooseDrug').height();
        height+=45;
        $('#allDrugList').css({'marginTop':height});
    });
    //返回
    $('.headerLeft>p').click(function () {
        $('#addDrug').hide();
    });
    $('#addDrugHeader span').click(function () {
        $('#addDrug').hide();
        var id_list=$('#chooseDrug').html();
        $('.diagnosticRecord>a').html(id_list);
        $('.diagnosticRecord>a').append('<span onclick="adddrug()">+</span>');
    });
    //确定
};