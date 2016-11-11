/**
 * Created by windows on 2016/3/21.
 */

var orgId = decodeURIComponent(window.location.search.substring(1).trim().split('&')[0]);
var orgName = '';
$(function () {
    $.ajax({
        type: 'post',
        async: false,
        url: hostOrgbusiness + 'orgManage.action',
        data: {
            action: "getHospitalInfo",
            "hospitalBasicInfo.id": orgId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == '成功') {
                if(result.userFollowHospital==null){
                    $('#guanzhujigou').html('关注机构').attr('data-isFollow','Y').attr('data-id','');
                }else if(result.userFollowHospital.isFollow=='N'){
                    $('#guanzhujigou').html('关注机构').attr('data-isFollow','Y').attr('data-id',result.userFollowHospital.id);
                }else if(result.userFollowHospital.isFollow=='Y'){
                    $('#guanzhujigou').html('取消关注').attr('data-isFollow','N').attr('data-id',result.userFollowHospital.id);
                }
                //if(result.archivesInfo!=null){
                //    if(result.archivesInfo.isRelation=='Y' && result.archivesInfo.hospitalBasicInfo != null){
                //    	if(result.archivesInfo.hospitalBasicInfo.id == orgId){
                //    		$('.orgImage').hide();
                //            $('.cancelbangding').attr('archivesInfo-id',result.archivesInfo.id).show();
                //    	}
                //    }
                //}
                var list = result.hospitalBannerList;
                var data = result.hospitalBasicInfo;
                if (list != null) {
                    for (var i = 0, len = list.length; i < len; i++) {
                        $('#banner ul').append('<li><img src="/kybabyBG/' + list[i].imgPath + '" alt=""/></li>');
                    }
                }
                orgName = data.hospitalLname;
                $('.con>p').html(data.hospitalLname);
                $('.fans>span').html(data.userFollows);
                $('.clitime>span').html(data.clinics);
                $('.docnum>span').html(data.doctors);
                $('#description').html(data.introduction);
                $('#dynamic').html(data.orgDynamic);
                var nature_html='';
                var level_html='';
                if(data.hospitalNature!=""){
                    nature_html='<span>'+data.hospitalNature+'</span>';
                }
                if(data.hospitalLevel!=""){
                    level_html='<span style="margin-left: 4px">'+data.hospitalLevel+'</span>';
                }
                if(data.hospitalType=='专科医院'){
                    $('.orgImage').html('<img src="images/zhuan_main_img.jpg" alt=""/><p>'+data.hospitalType+'</p>'+nature_html+level_html);
                }else if(data.hospitalType=='综合医院'){
                    $('.orgImage').html('<img src="images/all_main_img.jpg" alt=""/><p>'+data.hospitalType+'</p>'+nature_html+level_html);
                }else if(data.hospitalType=='社区医院'){
                    $('.orgImage').html('<img src="images/org_main_img.jpg" alt=""/><p>'+data.hospitalType+'</p>'+nature_html+level_html);
                }
                //$('.orgImage span').html(data.hospitalType);
                $('#address').html('地址:' + data.address);
                $('#tel').html('电话:<a href="tel:' + data.tel + '">' + data.tel + '</a>');
                var le = result.orgOpenBusinessList.length;
                if (le == 0) {
                    $('#footer ul').html('<li class="spe" style="width: 100%">暂时没有开通服务</li>');
                } else if (le == 1) {
                    $('#footer ul').html('<li style="width: 100%" onclick="gotodetail(this)">' + result.orgOpenBusinessList[0].businessName + '</li>');
                } else if (le == 2) {
                    $('#footer ul').html('<li style="width: 50%;border-right: 1px solid #FF813D" onclick="gotodetail(this)">' + result.orgOpenBusinessList[0].businessName + '</li>' +
                    '<li style="width: 50%" onclick="gotodetail(this)">' + result.orgOpenBusinessList[1].businessName + '</li>');
                } else {
                    $('#footer ul').html('<li style="width: 33%;border-right: 1px solid #FF813D" onclick="gotodetail(this)">' + result.orgOpenBusinessList[0].businessName + '</li>' +
                    '<li style="width: 34%;border-right: 1px solid #FF813D" onclick="gotodetail(this)">' + result.orgOpenBusinessList[1].businessName + '</li>' +
                    '<li style="width: 33%" onclick="gotodetail(this)">' + result.orgOpenBusinessList[2].businessName + '</li>');
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });
    //banner
    $('.flexslider').flexslider({
        animation: "slide",
        controlNav: true,               // Boolean: 是否创建控制点
        directionNav: false
    });
    //关注
    $('#guanzhujigou').click(function () {
        var txt=$(this).attr('data-isFollow');
        var id=$(this).attr('data-id');
        $.ajax({
            type: 'post',
            async: false,
            url: hostOrgbusiness + 'orgManage.action',
            data: {
                action: "saveOrUpdateUserFollowHospital",
                "hospitalBasicInfo.id": orgId,
                "userFollowHospital.isFollow":txt,
                "userFollowHospital.id":id
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == '成功') {
                    if(result.userFollowHospital==null){
                        $('#guanzhujigou').html('关注机构').attr('data-isFollow','Y').attr('data-id','');
                    }else if(result.userFollowHospital.isFollow=='N'){
                        $('#guanzhujigou').html('关注机构').attr('data-isFollow','Y').attr('data-id',result.userFollowHospital.id);
                    }else if(result.userFollowHospital.isFollow=='Y'){
                        $('#guanzhujigou').html('取消关注').attr('data-isFollow','N').attr('data-id',result.userFollowHospital.id);
                    }
                    $('.fans>span').html(result.follows);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //alert(XMLHttpRequest.status);
                //alert(errorThrown);
                layer();
            }
        });

    });
    //返回首页
    $('#black').click(function () {
        window.location.href='orgservice.html';
    });
    $.ajax({
        type: 'post',
        async: false,
        url: hostOrgbusiness + 'orgSetMealManager.action',
        data: {
            action: "getOrganSetMealList",
            "hospitalBasicInfo.id": orgId
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }else if (result.mes == '成功') {
                var organSetMealList=result.organSetMealList;
                if(organSetMealList!=null && organSetMealList.length!=0){
                    var html='';
                    for(var i= 0,len=organSetMealList.length;i<len;i++){
                        html+='<li onclick="packageHospitalList('+orgId+','+organSetMealList[i].id+')"> ' +
                        '<p><img src="'+photo+'admin/images/familydoctor/'+organSetMealList[i].packageImg+'" alt=""/></p>' +
                        organSetMealList[i].packageName+'</li>';
                    }
                    $('#product').html(html);
                }else{
                    $('#product').parent().hide();
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });

    //取消绑定
    $('.cancelbangding').click(function () {
        var answer=confirm('您确定要取消吗？');
        var text=$(this).attr('archivesInfo-id');
        if(answer==false){
            return false;
        }
        $.ajax({
            type: 'post',
            async: false,
            url: hostOrgbusiness + 'orgManage.action',
            data: {
                action: "relieveArchivesInfo",
                "archivesInfo.id":text
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == '成功') {
                    ale('解绑成功');
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //alert(XMLHttpRequest.status);
                //alert(errorThrown);
                layer();
            }
        });
    });
});
function packageHospitalList(orgId,id){//购买社区服务
    window.location.href='package_confirm.html?'+orgId+'&'+id;
}
//预约
function gotodetail(div) {
    var text = $(div).text().trim();
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "getCurrentUserIdentity"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html?"+orgId;
            } else if (result.mes == '成功') {
                var archivesInfo = result.archivesInfo;
                if (text == '门诊预约') {
                    window.location.href = 'orgservice_doctorlist.html?' + orgId;
                } else if (text == '儿保预约') {
                    if (archivesInfo == null) {
                        window.location.href = 'orgservice_associatedbaby.html?' + orgId;
                        return false;
                    }
                    if (archivesInfo.isRelation == 'Y') {
                        if (archivesInfo.hospitalBasicInfo.id != orgId) {
                            ale('您是在'+archivesInfo.hospitalBasicInfo.hospitalLname+'注册');
                            return false;
                        }
//                        if (result.currentUserIdentity == '金卡' || result.currentUserIdentity == '普卡' || result.currentUserIdentity == '散户') {
                            window.location.href = 'orgservice_babypoint.html?' + orgId;
//                        } else {
//                            ale('您还不是VIP用户，请联系该机构工作人员办理');
//                        }
                    } else {
                        window.location.href = 'orgservice_associatedbaby.html?' + orgId;
                    }
                }
                else if (text == '计划免疫') {
                    if (archivesInfo == null) {
                        window.location.href = 'orgservice_associatedbaby.html?' + orgId;
                        return false;
                    }
                    if (archivesInfo.isRelation == 'Y') {
                        if (archivesInfo.hospitalBasicInfo.id != orgId) {
                            ale('您是在'+archivesInfo.hospitalBasicInfo.hospitalLname+'注册');
                            return false;
                        }
                        if(result.inoculationed=='计免约过'){
                            window.location.href = 'orgservice_message.html?' + result.appointmentInitInfo.id;
                        }else{
                            window.location.href = 'orgservice_vaccine.html?' + orgId + '&' + orgName;
                        }
                    } else {
                        window.location.href = 'orgservice_associatedbaby.html?' + orgId;
                    }
                }
            }
        },
        error: function () {
            layer();
        }
    });
}
//医生简介多余字数处理
$(function () {
    var wordStr = $("#description").html();
    $("#description").html(" ");
    $("#description").append("<span id='doctorMessageContent'></span><a href='#' id='openDoctorMessage'></a>");
    $('#doctorMessageContent').html(wordStr.substring(0, 70));
    $('#openDoctorMessage').html(wordStr.length > 70 ? "...展开" : "");
    $('#openDoctorMessage').click(function () {
        if ($('#openDoctorMessage').html() == "...展开") {
            $('#openDoctorMessage').html("收起");
            $('#doctorMessageContent').html(wordStr);
        } else {
            $('#openDoctorMessage').html("...展开");
            $('#doctorMessageContent').html(wordStr.substring(0, 70));
        }
    });
    var wordStr1 = $("#dynamic").html();
    $("#dynamic").html(" ");
    $("#dynamic").append("<span id='doctorMessageContent1'></span><a href='#' id='openDoctorMessage1'></a>");
    $('#doctorMessageContent1').html(wordStr1.substring(0, 70));
    $('#openDoctorMessage1').html(wordStr1.length > 70 ? "...展开" : "");
    $('#openDoctorMessage1').click(function () {
        if ($('#openDoctorMessage1').html() == "...展开") {
            $('#openDoctorMessage1').html("收起");
            $('#doctorMessageContent1').html(wordStr1);
        } else {
            $('#openDoctorMessage1').html("...展开");
            $('#doctorMessageContent1').html(wordStr.substring(0, 70));
        }
    });
});

