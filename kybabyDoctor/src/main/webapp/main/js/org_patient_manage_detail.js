/**
 * Created by windows on 2016/9/26.
 */
$(function () {
    orgPatientManageDetail.orgId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);
    orgPatientManageDetail.organOperatorId=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
    orgPatientManageDetail.allTime();
});
var orgPatientManageDetail={
    orgId:null,
    organOperatorId:null,
    keyDownOnLoad: function () {
        var bind_name='input';
        if(navigator.userAgent.indexOf('MSIE')>-1) bind_name="propertychange";
        $('#search input').bind(bind_name, function () {
            if(this.value!=null && this.value!=""){
                $('#org_list>li').hide();
                $('#org_list>li[data-name*='+this.value+']').show();
            }else{
                $('#org_list>li').show();
            }
        });
    },
    getHospital: function (id,name) {//单项选择
        $.ajax({
            type:'post',
            url:urlWay.orgBusinessHost+'orgLogin.action',
            cache:false,
            async:true,
            data:{
                action : "getHospitalBasicInfoList"
            },
            success:function(result){
                if(result.mes=='成功'){
                    var orglist=result.hospitalBasicInfoList,
                        hospitalName=name;
                    if(orglist!=null){
                        var html='';
                        for(var i= 0,len=orglist.length;i<len;i++){
                            if(name=='N'){
                                if(orglist[i].id==id){
                                    hospitalName=orglist[i].hospitalLname;
                                }
                            }
                            html+='<li data-name="'+orglist[i].hospitalLname+'" value="'+orglist[i].id+'">'+orglist[i].hospitalLname+'</li>';
                            //html+='<option value="'+orglist[i].id+'">'+orglist[i].hospitalLname+'</option>';
                        }
                    }
                    $('#org_list').html(html).height($('#cover').height()*0.7-100);
                    //$('#orgList').html(html);
                    $('.choose_org').attr('data-id',id).text(hospitalName);
                    //$('#orgList').val(orgPatientManageDetail.orgId);
                    orgPatientManageDetail.loadEvent();
                    orgPatientManageDetail.keyDownOnLoad();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    },
    select: function (ele,val) {//单项选择
        var obj=$('#'+ele+'>span');
        obj.removeClass('green');
        $(obj).each(function () {
            if($(this).text()==val){
                $(this).addClass('green');
            }
        });
    },
    getOperaMedicalRecordsById: function (opt) {
        var od=this.organOperatorId;
        if(od != undefined && od != "undefined"){//如果是编辑就调用此方法
            $('title,.header-center').html('修改就诊用户信息');
            $('#submit>span').html('修改');
            $.ajax({
                type:'post',
                url:urlWay.orgBusinessHost+'operaMedicalRecordsAction.action',
                cache:false,
                async:true,
                data:{
                    action : "getOperaMedicalRecordsById",
                    "operaMedicalRecords.id":od
                },
                success:function(result){
                    if(result.mes=='请登录'){
                        ale('请登录');
                        link_to('org_login.html');
                    }else if(result.mes=='成功'){
                        if(result.operaMedicalRecords!=null){
                            var operaMedicalRecords=result.operaMedicalRecords;
                            $("#appDate").val(operaMedicalRecords.clinicTime);
                            $("#appDate1").val(operaMedicalRecords.patientBirthday);
                            $("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
                            $("#appDate1").mobiscroll($.extend(opt['date'], opt['default']));
                            //$('#boType .green').text();
                            orgPatientManageDetail.select('boType',operaMedicalRecords.boType);
                            $('#patientName').val(operaMedicalRecords.patientName);
                            //$('#patientSex .green').text();
                            orgPatientManageDetail.select('patientSex',operaMedicalRecords.patientSex);
                            $('#patientPhone').val(operaMedicalRecords.patientPhone);
                            $('#patientParentName').val(operaMedicalRecords.patientParentName);
                            $('#patientAddress').val(operaMedicalRecords.patientAddress);
                            //$('#isGuahao .green').text();
                            orgPatientManageDetail.select('isGuahao',operaMedicalRecords.isGuahao);
                            //$('#isRegister .green').text();
                            orgPatientManageDetail.select('isRegister',operaMedicalRecords.isRegister);
                            //$('#isPreAppointment .green').text();
                            orgPatientManageDetail.select('isPreAppointment',operaMedicalRecords.isPreAppointment);
                            //$('#isPay .green').text();
                            orgPatientManageDetail.select('isPay',operaMedicalRecords.isPay);
                            //$('#isPrescription .green').text();
                            orgPatientManageDetail.select('isPrescription',operaMedicalRecords.isPrescription);
                            //$('#isCheck .green').text();
                            orgPatientManageDetail.select('isCheck',operaMedicalRecords.isCheck);
                            //$('#isTest .green').text();
                            orgPatientManageDetail.select('isTest',operaMedicalRecords.isTest);
                            //$('#isCreateFile .green').text();
                            orgPatientManageDetail.select('isCreateFile',operaMedicalRecords.isCreateFile);
                            orgPatientManageDetail.select('isNowAppointment',operaMedicalRecords.isNowAppointment);
                            $('#remark').text(operaMedicalRecords.remark);
                            orgPatientManageDetail.getHospital(operaMedicalRecords.hospitalBasicInfo.id,operaMedicalRecords.hospitalBasicInfo.hospitalLname);
                        }
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                }
            });
        }else{
            this.organOperatorId='';
            this.getHospital(this.orgId,'N');
        }
    },
    saveOrUpdateOperaMedicalRecords: function () {//保存与更新
        var patientPhone=$('#patientPhone').val().trim();
        if($('#patientName').val().trim()==''){
            ale('请填写宝宝姓名');
            return;
        }else if($('#appDate1').val()==''){
            ale('请选择宝宝生日');
            return;
        }else if(patientPhone=='' || patientPhone.length != 11 || !checkMobile(patientPhone)){
            ale('请输入正确的手机号');
            return;
        }
        $.ajax({
            type:'post',
            url:urlWay.orgBusinessHost+'operaMedicalRecordsAction.action',
            cache:false,
            async:true,
            data:{
                action : "saveOrUpdateOperaMedicalRecords",
                "operaMedicalRecords.id":this.organOperatorId,
                "operaMedicalRecords.clinicTime":$('#appDate').val(),
                "operaMedicalRecords.hospitalBasicInfo.id":$('.choose_org').attr('data-id'),
                "operaMedicalRecords.boType":$('#boType .green').text(),
                "operaMedicalRecords.patientName":$('#patientName').val(),
                "operaMedicalRecords.patientSex":$('#patientSex .green').text(),
                "operaMedicalRecords.patientBirthday":$('#appDate1').val(),
                "operaMedicalRecords.patientPhone":$('#patientPhone').val(),
                "operaMedicalRecords.patientParentName":$('#patientParentName').val(),
                "operaMedicalRecords.patientAddress":$('#patientAddress').val(),
                "operaMedicalRecords.isGuahao":$('#isGuahao .green').text(),
                "operaMedicalRecords.isRegister":$('#isRegister .green').text(),
                "operaMedicalRecords.isPreAppointment":$('#isPreAppointment .green').text(),
                "operaMedicalRecords.isPay":$('#isPay .green').text(),
                "operaMedicalRecords.isPrescription":$('#isPrescription .green').text(),
                "operaMedicalRecords.isCheck":$('#isCheck .green').text(),
                "operaMedicalRecords.isTest":$('#isTest .green').text(),
                "operaMedicalRecords.isCreateFile":$('#isCreateFile .green').text(),
                "operaMedicalRecords.isNowAppointment":$('#isNowAppointment .green').text(),
                "operaMedicalRecords.remark":$('#remark').text()
            },
            success:function(result){
                if(result.mes=='成功'){
                    ale('保存成功');
                    setTimeout(function () {
                        return_before();
                    },2000);
                }else{
                    ale('保存失败');
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    },
    allTime: function () {//日期插件
        var currYear = (new Date()).getFullYear();
        var currTime='';
        $.ajax({
            type:'post',
            url:urlWay.orgBusinessHost+'orgClinicManager.action',
            cache:false,
            async:true,
            data:{action : "getCurrentTime"},
            success:function(result){
                currTime=result.currentTime.split(' ')[0];
                currYear=parseInt(result.currentTime.split(' ')[0].split('-')[0]);
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
                    startYear: 2000, //开始年份
                    endYear: currYear+1 //结束年份
                };
                $("#appDate").val(currTime);
                $("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
                $("#appDate1").mobiscroll($.extend(opt['date'], opt['default']));
                orgPatientManageDetail.getOperaMedicalRecordsById(opt);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    },
    loadEvent: function (){
        $('.item_con>span').click(function () {//选择项目
            $(this).addClass('green').siblings().removeClass('green');
        });
        $('#org_list>li').click(function () {//选择社区
            $('#cover').hide();
            $('.choose_org').text($(this).text()).attr('data-id',$(this).attr('value'));
        });
        $('.close>p').click(function () {//关闭选择机构遮罩
            $('#cover').hide();
        });
        $('.choose_org').click(function () {//打开选择机构遮罩
            $('#cover').show();
        });
        //$('#orgList').change(function () {//选择社区
        //    $('.choose_org').text($(this).find('option:selected').text()).attr('data-id',$(this).val());
        //});
    }
}