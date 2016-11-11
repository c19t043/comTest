/**
 * Created by windows on 2016/3/28.
 */

var vaccineId = decodeURIComponent(window.location.search.substring(1).split('&')[0]);
function showdetail(div){
    $(div).next().slideToggle();
    $(div).find('.personal_right img').toggleClass('zhuan');
}
$(function () {
    //获取当前用户的身份
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "getVaccineInfoById",
            "vaccineInfo.id":vaccineId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }else if(result.mes=='成功'){
                $('.diseasePrevention').next().html(result.vaccineInfo.diseasePrevention);
                $('.diseaseHazard').next().html(result.vaccineInfo.diseaseHazard);
                $('.mattersNeedingAttentionBefore').next().html(result.vaccineInfo.mattersNeedingAttentionBefore);
                $('.mattersNeedingAttentionAfter').next().html(result.vaccineInfo.mattersNeedingAttentionAfter);
                $('.vaccinationProgram').next().html(result.vaccineInfo.vaccinationProgram);
                $('.header-left span').append(result.vaccineInfo.vaccineName);
            }
        },
        error: function () {
            layer();
        }
    });
});

