$(function () {
    getHealthMessage();
});

//获取健康档案信息
function getHealthMessage(){
    var userId=window.location.search.substring(1);
    $.ajax({
        type: 'post',
        url: urlWay.fdmanage + 'fdUserManage.action',
        cache: false,
        async: false,
        data: {
            action: "getBabyBasicDataAtDoctor",
            "userInfo.id":userId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                $.each(result.babyBasicData,function(key,val){
                    $('#babyHealthHasRecord .'+key+'').val(val).attr("readonly","readonly");
                    if(key=='hospitalizedRemark' || key=='vaccineAbnormalRemark' || key=='allergiesRemark'){
                        $('#babyHealthHasRecord .'+key+'').html(val);
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