$(function(){
    getOrgBabyPreList();
    $(".selbtn").click(function(){
        $(".selbtn").removeClass("select");
        $(this).addClass("select");
        sessionStorage.setItem('myOrg',$(this).children("span").text());
        if($(this).text()=='我的儿保预约'){
            getOrgBabyPreList();
            $('#org_erbao').show();
            $('#select_cli').show();
            $('#org_jimian').hide();
            $('#select_cli1').hide();
        }else{
            getOrgVanList();
            $('#org_erbao').hide();
            $('#org_jimian').show();
            $('#select_cli').hide();
            $('#select_cli1').show();
        }
    });
    var session=sessionStorage.getItem('myOrg');
    if(session!=''||session!=null||session!=undefined){
        for(var i=0;i<$(".selbtn").length;i++){
            if($(".selbtn").eq(i).children("span").text()==session){
                $(".selbtn").eq(i).trigger("click");
            }
        }
        var session1=sessionStorage.getItem('myOrg1');
        if(session1!=''||session1!=null||session1!=undefined){
            for(var i=0;i<$("#select_cli li").length;i++){
                if($("#select_cli li").eq(i).text()==session1){
                    $("#select_cli li").eq(i).trigger("click");
                }
            }
        }
        var session2=sessionStorage.getItem('myOrg2');
        if(session2!=''||session2!=null||session2!=undefined){
            for(var i=0;i<$("#select_cli1 li").length;i++){
                if($("#select_cli1 li").eq(i).text()==session2){
                    $("#select_cli1 li").eq(i).trigger("click");
                }
            }
        }
    }
});
function getOrgBabyPreList(){
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'orgBoManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserChildcareAppointmentInfoList"
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var list=result.userChildcareAppointmentInfoList;
                if(list!=null){
                    var html='';
                    if(list.length>0){
                        for(var i= 0,len=list.length;i<len;i++){
                            var organServicePlaceSet='';
                            if(list[i].organChildcareOpenResources.isMoney=='N'){
                                organServicePlaceSet='('+list[i].organServicePlaceSet.windowName+')';
                            }
                            var isCanClick='';
                            var bianma='';
                            if(list[i].organChildcareOpenResources.isMoney=='Y'){
                                bianma='<p class="bianma">订单金额:￥'+list[i].totalPrice+'</p>';
                                if(list[i].status=='未付款'){
                                    if(list[i].is_zhonglian=='Y'){
                                        isCanClick='<span style="padding: 2px 3px;margin-left:8px;background-color: #ff813d;color: white;border-radius: 4px;" onclick="window.location.href=\'hospital_pay_order.html?e='+list[i].id+'\'">立即支付</span>';
                                    }else{
                                        isCanClick='<span style="padding: 2px 3px;margin-left:8px;background-color: #ff813d;color: white;border-radius: 4px;" onclick="window.location.href=\'orgservice_pay_babypoint.html?'+list[i].id+'\'">立即支付</span>';
                                    }
                                }
                            }else{
                                bianma='<p class="bianma">预约编码:'+list[i].preEncoding+' '+organServicePlaceSet+'</p>';
                            }
                            var stat='';
                            if(list[i].status=='已预约'){
                                stat='onclick="window.location.href=\'orgservice_babymessage.html?'+list[i].id+'&'+list[i].is_zhonglian+'\'" ';
                            }
                            var timeDivisionNeed='';
                            if(list[i].organChildcareOpenResourcesDatail!=null){
                                if(list[i].organChildcareOpenResources.timeDivisionNeed=='时间点'){
                                    timeDivisionNeed=list[i].organChildcareOpenResourcesDatail.segment;
                                }else{
                                    timeDivisionNeed=list[i].organChildcareOpenResourcesDatail.openStartTime+'~'+list[i].organChildcareOpenResourcesDatail.openEndTime;
                                }
                            }
                            html+='<div data-status="'+list[i].status+'" class="org_erbaolist"'+stat+
                                '><div class="head"> ' +
                                '<p class="left">预约时间:'+list[i].operationTime+'</p><p class="right">'+list[i].status+isCanClick+'</p> ' +
                                '</div> ' +
                                '<div class="content"> ' +
                                '<div class="con_left"><img src="/kybabyBG/'+list[i].hospitalBasicInfo.showImgPath+'" alt=""/></div> ' +
                                '<div class="con_right"> ' +
                                '<p class="orgname">'+list[i].hospitalBasicInfo.hospitalLname+'</p> ' +
                                '<p class="address">'+list[i].hospitalBasicInfo.address+'</p> ' +
                                '<p class="time">儿保时间:'+list[i].organChildcareOpenResources.openDate+' '+timeDivisionNeed+'</p> ' + bianma + '</div> ' +
                                '</div> ' +
                                '</div>';
                        }
                        $('#org_erbao').html(html);
                        $('#org_erbao>div').hide();
                        var show=$('#select_cli>li.blue').text();
                        if(show=='已预约'){
                            $('#org_erbao>div[data-status=已预约]').show();
                        }else if(show=='已取消'){
                            $('#org_erbao>div[data-status=用户取消]').show();
                        }else if(show=='已完成'){
                            $('#org_erbao>div[data-status=已完成]').show();
                        }else if(show=='全部'){
                            $('#org_erbao>div').show();
                        }
                    }
                    //else{
                    //    $(".org_erbao").html("<div class='prompt'><i class='iconfont'>&#xe631;</i><p>暂无相关数据</p></div>");
                    //}
                }
            }
        },
        error: function () {
            layer();
        }
    });
    $('#select_cli>li').click(function () {
        $(this).addClass('blue').siblings().removeClass('blue');
        $('#org_erbao>div').hide();
        var text=$(this).text();
        sessionStorage.setItem('myOrg1',text);
        if(text=='已预约'){
            $('#org_erbao>div[data-status=已预约]').show();
        }else if(text=='已取消'){
            $('#org_erbao>div[data-status=用户取消]').show();
        }else if(text=='已完成'){
            $('#org_erbao>div[data-status=已完成]').show();
        }else if(text=='全部'){
            $('#org_erbao>div').show();
        }
    });
}

function getOrgVanList(){
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'vaccineManage.action',
        cache: false,
        async: false,
        data: {
            action: "getUserInoculationAppointmentInfoList"
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if(result.mes=='成功'){
                var list=result.userInoculationAppointmentInfoList;
                if(list!=null){
                    var html='';
                    if(list.length>0){
                        for(var i= 0,len=list.length;i<len;i++){
                            var stat='';
                            if(list[i].status=='已预约'){
                                stat='onclick="window.location.href=\'orgservice_message.html?'+list[i].id+'\'" ';
                            }
                            html+='<div data-status="'+list[i].status+'" class="org_jimianlist"'+stat+'>'+
                                '<div class="head"> ' +
                                '<p class="left">预约时间:'+list[i].optTime+'</p><p class="right">'+list[i].status+'</p> ' +
                                '</div> ' +
                                '<div class="content"> ' +
                                '<div class="con_left"><img src="/kybabyBG/'+list[i].hospitalBasicInfo.showImgPath+'" alt=""/></div> ' +
                                '<div class="con_right"> ' +
                                '<p class="orgname">'+list[i].hospitalBasicInfo.hospitalLname+'</p> ' +
                                '<p class="address">'+list[i].hospitalBasicInfo.address+'</p> ' +
                                '<p class="time">计免时间:'+list[i].organInoculationOpenResources.openDate+' '+list[i].organInoculationOpenResourcesDetail.openStartTime+'~'+list[i].organInoculationOpenResourcesDetail.openEndTime+'</p> ' +
                                '<p class="bianma">预约编码:'+list[i].appointmentCode+' ('+list[i].organServicePlaceSet.windowName+')</p> ' +
                                '</div> ' +
                                '</div> </div>';
                        }
                        $('#org_jimian').html(html);
                        $('#org_jimian>div').hide();
                        var show=$('#select_cli1>li.blue').text();
                        if(show=='已预约'){
                            $('#org_jimian>div[data-status=已预约]').show();
                        }else if(show=='已取消'){
                            $('#org_jimian>div[data-status=已取消]').show();
                        }else if(show=='已完成'){
                            $('#org_jimian>div[data-status=已完成]').show();
                        }else if(show=='全部'){
                            $('#org_jimian>div').show();
                        }
                    }
                    //else{
                    //    $(".org_jimian").html("<div class='prompt'><i class='iconfont'>&#xe631;</i><p>暂无相关数据</p></div>");
                    //}
                }
            }
        },
        error: function () {
            layer();
        }
    });
    $('#select_cli1>li').click(function () {
        $(this).addClass('blue').siblings().removeClass('blue');
        $('#org_jimian>div').hide();
        var text=$(this).text();
        sessionStorage.setItem('myOrg2',text);
        if(text=='已预约'){
            $('#org_jimian>div[data-status=已预约]').show();
        }else if(text=='已取消'){
            $('#org_jimian>div[data-status=已取消]').show();
        }else if(text=='已完成'){
            $('#org_jimian>div[data-status=已完成]').show();
        }else if(text=='全部'){
            $('#org_jimian>div').show();
        }
    });
}