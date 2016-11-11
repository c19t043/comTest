/**
 * Created by windows on 2016/5/11.
 */
var messageId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);
$(function () {
    $.ajax({
        type: 'post',
        url: clinicHost + 'clinicOrder.action',
        cache: false,
        async: false,
        data: {
            action: "getClinicMedicalRecords",
            "clinicMedicalRecords.id": messageId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                var data = result.clinicMedicalRecords;
                var drugInfoList_html='';
                if(data.drugInfoList!=null){
                    for(var i=0;i<data.drugInfoList.length;i++){
                        drugInfoList_html+='<span>'+data.drugInfoList[i].commonName+'</span>';
                    }
                }else{
                    drugInfoList_html='无';
                }
                $('#diagnostic').html('<div>' +
                data.diagnosticInformation+
                '</div> ');
                $('#diagnostic1').html('' +
                drugInfoList_html+
                '');
                var symptomTagList_html='';
                if(data.symptomTagList!=null){
                    for(var i=0;i<data.symptomTagList.length;i++){
                        symptomTagList_html+='<span>'+data.symptomTagList[i].symptomName+'</span>';
                    }
                }else{
                    symptomTagList_html='无';
                }
                $('#symptoms').html('<div>' +
                data.symptomDescribe+
                '</div> ' +
                '<div> ' +
                '<a>症状标签:</a> ' +
                symptomTagList_html+
                '</div>');
                var orderInfoClinic=data.orderInfoClinic;
                if(orderInfoClinic.clinicAddress.indexOf('(')>-1){
                    $('.list03 input').val(orderInfoClinic.clinicAddress.substr(0,orderInfoClinic.clinicAddress.indexOf('(')));
                }else{
                    $('.list03 input').val(orderInfoClinic.clinicAddress);
                }
                $('.list04 input').val(orderInfoClinic.doctorInfo.doctorName);
                $('.list02 input').val(orderInfoClinic.appointmentDate + ' ' + orderInfoClinic.appointmentBeganTime + '~' + orderInfoClinic.appointmentEndTime);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
    });
});
function showdetail(div){
    $(div).next().slideToggle();
    $(div).find('.personal_right img').toggleClass('zhuan');
}