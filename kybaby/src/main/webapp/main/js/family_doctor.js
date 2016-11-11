/**
 * Created by lijingwei on 2016/6/27.
 */
var tag = 0;
var arr=[];
$(function () {
    var length_next;
    $.ajax({
        type:'post',
        url:familyDoctorHost+'fdServiceItemsAction.action',
        cache:false,
        async:false,
        data:{action:"getAllFdServiceItems"},
        success:function(result){
            //登录前
            if (result.fdServiceItemsList != null && result.fdServiceItemsList.length!=0) {
                $(".products_img").remove();
                $("#bottom_button").remove();
                $("#bottom_before").show();
                var length = result.fdServiceItemsList.length;
                for (var i = 0; i < length; i++) {
                    var fdServiceItemsList = result.fdServiceItemsList[i];
                    $('#before_login .family_doctor_list').append('<div><div class="myDiv" style="display: none">'+fdServiceItemsList.itemDescription+'</div><img src="'+photo+fdServiceItemsList.imagePath+'"/>' +
                        '<p>'+fdServiceItemsList.itemShowName+'</p></div>'
                    );
                }
                if(length%2==1){
                    $('.family_doctor_list').append('<div></div>');
                }
                tag = 1;
            }
            //登录后
            if(result.fdUserBuyRecordList != null && result.fdUserBuyRecordList.length!=0){
                $("#before_login").remove();
                $("#bottom_before").remove();
                var length = result.fdUserBuyRecordList.length;
                length_next=length;
                for (var i = 0; i < length; i++) {
                    var teamImgPath,teamName,fdUserBuyRecordList = result.fdUserBuyRecordList[i];
                    var teamId='';
                    if(fdUserBuyRecordList.servicePackage.fdServiceTeamsSet[0]!=null){
                        teamImgPath = fdUserBuyRecordList.servicePackage.fdServiceTeamsSet[0].teamImgPath;
                        teamName = fdUserBuyRecordList.servicePackage.fdServiceTeamsSet[0].teamName;
                    }
                    var hospitalLname='<p style="visibility: hidden">d</p>';
                    if(fdUserBuyRecordList.servicePackage.hospitalBasicInfo!=null){
                        hospitalLname=fdUserBuyRecordList.servicePackage.hospitalBasicInfo.hospitalLname;
                    }else{
                        teamImgPath = fdUserBuyRecordList.fdServiceOrder.fdServiceTeams.teamImgPath;
                        teamName = fdUserBuyRecordList.fdServiceOrder.fdServiceTeams.teamName;
                        teamId = fdUserBuyRecordList.fdServiceOrder.fdServiceTeams.id;
                    }
                    $("#full-width-slider").append('<div class="rsContent"><div class="doctor_information"><div id="doctor_header"><img src="'+photo+teamImgPath+'"/></div>' +
                        '<div class="doctor_introduction"><p>'+teamName+'</p>'+'<p>'+hospitalLname+'</p>' +
                        '<p>服务时间：<span>'+fdUserBuyRecordList.serviceStartTime+'</span>至<span>'+fdUserBuyRecordList.serviceEndTime+'</span></p></div></div>' +
                        '<p class="gray_s"></p><div class="family_doctor_list"></div></div>');
                    var length2 = fdUserBuyRecordList.servicePackage.fdServiceItemsList.length;
                    for (var j = 0; j < length2; j++) {
                        var fdServiceItemsList = fdUserBuyRecordList.servicePackage.fdServiceItemsList[j];
                        $('.family_doctor_list').eq(i).append('<div onclick="goTo(\''+fdServiceItemsList.url+'?'+fdUserBuyRecordList.servicePackage.id+'&'+teamId+'\')"><img src="'+photo+fdServiceItemsList.imagePath+'"/><p>'
                            +fdServiceItemsList.itemShowName+'</p></div>'
                        );
                    }
                    if(length2%2==1){
                        $('.family_doctor_list').eq(i).append('<div></div>');
                    }
                    teamImgPath="";
                    teamName="";
                }
            }
        },
        error: function () {
            layer();
        }
    });

    //登录前
    if(tag == 1){
        $('.family_doctor_list div').on('click', function(){
            if($(this).html()!=''){
                var text = $(this).children("p").text();
                layer.open({
                    type: 1,
                    area: ['80%', '360px'],
                    title : text,
                    btn: '关闭',
                    closeBtn: 0,
                    shadeClose: true, //点击遮罩关闭
                    content: '\<\div style="padding:20px;">'+$(this).find(".myDiv").html()+'\<\/div>'
                });
            }
        });
    }
    //登录后轮滑
    else{
        $(".rsContent").css({
            "display": "block",
            "margin-top": "0",
            "margin-left": "0",
            "height": "0"
        });
        $('#full-width-slider').royalSlider({
            arrowsNav: false,
            loop: true,
            transitionSpeed:1000,//切换毫秒速度
            fadeinLoadedSlide: true,//渐显加载加载幻灯片
            slidesSpacing: 0,//幻灯片间距
            keyboardNavEnabled: true,//启用键盘导航
            controlsInside: false,//内部控制
            imageScaleMode: 'fill',
            autoScaleSlider: true,//是否基于基础宽度自动更新滑块高度
            autoScaleSliderWidth: 800,//幻灯片基本宽度
            autoScaleSliderHeight: 800,
            controlNavigation: 'bullets',//导航类型
            thumbsFitInViewport: true,
            navigateByClick: false, //是否允许在幻灯片上点击鼠标导航
            startSlideId: 0,//从第几张幻灯片开始播放
            transitionType:'move',
            globalCaption: true,
            imgWidth: 60,//所有图片的基本宽度
            imgHeight: 60
        });
        for(var i=0;i<$(".products_img .rsSlide").length;i++){
            var head_height = $(".rsSlide").eq(i).find(".doctor_information").height();
            var list_height = $(".rsSlide").eq(i).find(".family_doctor_list").height();
            $(".products_img .rsContent").eq(i).height(head_height+list_height+21);//1为“gray_p的高度”；
            $(".products_img .rsSlide").eq(i).height($(".products_img .rsContent").eq(i).height());
            arr.push($(".products_img .rsSlide").eq(i).height());
        }
        var max_height = Math.max.apply(null,arr);
        $(".products_img .rsContainer").height(max_height);
        var div_height = $(".products_img .rsContainer").height()+"px";
        $(".products_img .royalSlider.heroSlider .rsOverflow").css("cssText","height:"+div_height+" !important");
    }
    if(length_next<=1){
        $("#container").css("marginBottom","53px");
        $(".rsMinW .rsBullets").css({display:'none'});
    }
});
window.onresize=function(){
    for(var i=0;i<$(".products_img .rsSlide").length;i++){
        var head_height = $(".rsSlide").eq(i).find(".doctor_information").height();
        var list_height = $(".rsSlide").eq(i).find(".family_doctor_list").height();
        $(".products_img .rsContent").eq(i).height(head_height+list_height+21);//1为“gray_p的高度”；
        $(".products_img .rsSlide").eq(i).height($(".products_img .rsContent").eq(i).height());
        arr.push($(".products_img .rsSlide").eq(i).height());
    }
    var max_height = Math.max.apply(null,arr);
    $(".products_img .rsContainer").height(max_height);
    var div_height = $(".products_img .rsContainer").height()+"px";
    $(".products_img .royalSlider.heroSlider .rsOverflow").css("cssText","height:"+div_height+" !important");
}
$("#cbUserAgreement").click(function () {
    $(this).parent("p").toggleClass('cbUserAgreement');
});
function isReadXieYi(){
    var text=$('.cbUserAgreement').text();
    if(text==''){
        window.location.href='family_doctor_hospital_list.html'
    }else{
        ale('请先阅读家庭医生签约协议');
        return false;
    }
}
function goTo(url){
    //console.log(url)
    //console.log(urls)
    var teamId=url.split('&')[1];
    if(teamId==''){
        window.location.href=url;
    }else{
        if(url.indexOf('family_doctor_patient')>-1){
            window.location.href='family_doctor_team_doctor.html?'+teamId;
        }else{
            window.location.href=url;
        }
    }
}
