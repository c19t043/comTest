/**
 * Created by windows on 2016/9/26.
 */
$(function () {
    orgPatientManage.orgId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);
    $('#search input').val('');
    orgPatientManage.getPatientList();
    orgPatientManage.searchOnLoad();
});
var orgPatientManage={
    orgId:null,
    getPatientList: function () {//获取当日新增就诊列表
        $.ajax({
            type:'post',
            url:urlWay.orgBusinessHost+'operaMedicalRecordsAction.action',
            async:true,
            cache:false,
            data:{
                action:"getOperaMedicalRecordsList"
            },
            success: function (result) {
                if(result.mes=='请登录'){
                    ale('请登录');
                    link_to('org_login.html');
                }else if(result.mes=='成功'){
                    var operaMedicalRecordsList=result.operaMedicalRecordsList;
                    if(operaMedicalRecordsList != null && operaMedicalRecordsList.length != 0){
                        var html='';
                        for(var i= 0,len=operaMedicalRecordsList.length;i<len;i++){
                            html+='<li class="patient_list" data-phone="'+operaMedicalRecordsList[i].patientPhone+'" data-name="'+operaMedicalRecordsList[i].patientName+'" onclick="link_to(\'org_patient_manage_detail.html?'+orgPatientManage.orgId+'&'+operaMedicalRecordsList[i].id+'\')"> ' +
                            '<div class="phone">'+operaMedicalRecordsList[i].patientPhone+'</div> ' +
                            '<div class="name">'+operaMedicalRecordsList[i].patientName+'</div> ' +
                            '<div class="sex">'+operaMedicalRecordsList[i].patientSex+'</div> ' +
                            '<div class="date">'+operaMedicalRecordsList[i].patientBirthday+'</div> ' +
                            '</li>';
                        }
                        $('#patient_list').append(html);
                    }
                }
            },
            error: function (status) {
                layer();
            }
        });
    },
    searchOnLoad: function () {//键盘及时输入事件
        var bind_name='input';
        if(navigator.userAgent.indexOf('MSIE')>-1) bind_name="propertychange";
        $('#search input').bind(bind_name, function () {
            if(this.value!=null && this.value!=""){
                $('.patient_list').hide();
                $('.patient_list[data-phone*='+this.value+']').show();
                $('.patient_list[data-name*='+this.value+']').show();
            }else{
                $('.patient_list').show();
            }
        });
    }
}