var orderId='';
$(function () {
    getHealthMessage();
    //保存健康档案信息
    $('.header-right span').click(function () {
        var text=$(this).text();
        if(text=='修改'){
            $('#header .header-left div').prop('class','cancel');
            $(this).text('保存');
            $('#babyHealthRecord').show();
            $('#babyHealthHasRecord').hide();
            return false;
        }
        var recordHealth=recordHealthData();
        if(recordHealth==false){
            return false;
        }
        var answer=confirm('保存的那部分数据将不能修改!!');
        if(answer==true){
            $.ajax({
                type: 'post',
                url: host+'getOrderResultInfo.action',
                cache: false,
                async: false,
                data:recordHealth,
                success: function (result) {
                    ale('保存成功');
                    location.reload();
                    //getHealthMessage();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer();
                }
            });
        }
    });
    //健康档案分类
    $('.babyHealthRecord_info .levelOne li').click(function () {
        $(this).addClass('liActive').siblings().removeClass('liActive');
        var data_num=$(this).attr('data-num');
        var divList=$('.levelTwo>div');
        divList.hide();
        divList.eq(data_num).show();
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
//获取健康档案信息
function getHealthMessage(){
    $.ajax({
        type: 'post',
        url: host + 'getOrderResultInfo.action',
        cache: false,
        async: false,
        data: {
            action: "getBabyBasicData",
            "orderInfoClinic.id":orderId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "操作成功") {
                if(result.babyBasicData == null || result.babyBasicData.id==''){
                    $('.header-right span').html('保存');
                    $('#babyHealthRecord').show();
                    $('#babyHealthHasRecord').hide();
                    return false;
                }
                $('#header .header-left div').prop('class','return');
                $('.header-right span').html('修改');
                $('#babyHealthRecord').hide();
                $('#babyHealthHasRecord').show();
                $.each(result.babyBasicData,function(key,val){
                    $('#babyHealthHasRecord .'+key+'').val(val).attr("readonly","readonly");
                    if(key=='hospitalizedRemark' || key=='vaccineAbnormalRemark' || key=='allergiesRemark'){
                        $('#babyHealthHasRecord .'+key+'').html(val);
                    }
                    var obj = $(".levelTwo input,.levelTwo select,.levelTwo textarea").filter(function(){
                        return $(this).attr("id") == key;
                    });
                    if(obj.get(0) == undefined){
                        return true;
                    }else if(obj.get(0).tagName == "INPUT"){
                        if(val!=null && val!= ''){
                            $(obj).attr("readonly","readonly");
                        }
                        obj.val(val);
                    }else if(obj.get(0).tagName == "TEXTAREA"){
                        if(val!=null && val!= ''){
                            $(obj).attr("readonly","readonly");
                        }
                        obj.val(val);
                    }else if(obj.get(0).tagName == "SELECT"){
                        if(val!=null && val!= ''){
                            $(obj).attr("disabled","disabled");
                        }
                        obj.find("option").each(function(){
                            $(this).removeAttr('selected');
                            if(val==''&& $(this).text()=='请选择'){
                                $(this).attr('selected','selected');
                                console.log($(this));
                            }else if($(this).text() == val){
                                $(this).attr('selected','selected');
                                console.log($(this));
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
            layer();
        }
    });
}
//保存或返回
function cancelOrReturn(div){
    var thisClass=$(div).prop('class');
    if(thisClass=='cancel'){
        $(div).prop('class','return');
        $('.header-right span').html('修改');
        $('#babyHealthRecord').hide();
        $('#babyHealthHasRecord').show();
    }else if(thisClass=='return'){
        window.history.go(-1);
    }
}