
$(function () {
    //开展了业务的机构
    updateVersionFirst();
    $.ajax({
        type:'post',
        url:urlWay.orgBusinessHost+'orgLogin.action',
        cache:false,
        async:false,
        data:{action : "getHospitalBasicInfoList"
        },
        success:function(result){
            if(result.mes=='成功'){
            	orgAutoLogin();
                var orglist=result.hospitalBasicInfoList;
                if(orglist!=null){
                    var html='';
                    for(var i= 0,len=orglist.length;i<len;i++){
                        html+='<option org-id="'+orglist[i].id+'" value="'+orglist[i].hospitalLname+'">'+orglist[i].hospitalLname+'</option>'
                    }
                }
                $('#org_list').html(html);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
    $('#chooseLoginType>div').click(function () {//选择登录方式
        if($(this).hasClass('org')){
            $('#org').show();
            $('title,.header-center').text('机构登录');
        }else if($(this).hasClass('doctor')){
            $('#doctor').show();
            $('title,.header-center').text('签约登录');
        }
        $('#username1,#username,#password1,#password').val('');
        $('#chooseLoginType>div').hide();
        $('#chooseLoginType>p').show();
    });
    $('#chooseLoginType>p').click(function () {//重新选择登录方式
        $('#chooseLoginType>div').show();
        $('#org,#doctor,#chooseLoginType>p').hide();
    });
});
function login(type){
    var obj={},username,password,id,val;
    if(type=='org'){
        username=$('#username').val().trim();
        password=$('#password').val().trim();
        val=$('#org_list option:selected').val();
        id=$('#org_list option:selected').attr('org-id');
        if(username==''){
            ale('请填写账号');
            return false;
        }else if(password==''){
            ale('请填写密码');
            return false;
        }
        obj={action : "orgLogin",
            "organOperator.hospitalBasicInfo.id":id,
            "organOperator.loginName":username,
            "organOperator.password":password
        }
    }else if(type=='doctor'){
        username=$('#username1').val().trim();
        password=$('#password1').val().trim();
        if(username==''){
            ale('请填写账号');
            return false;
        }else if(password==''){
            ale('请填写密码');
            return false;
        }
        obj={action : "orgLogin",
            "organOperator.loginName":username,
            "organOperator.password":password
        }
    }
    $.ajax({
        type:'post',
        url:urlWay.orgBusinessHost+'orgLogin.action',
        cache:false,
        async:false,
        data:obj,
        success:function(result){
            if(result.mes=='成功'){
                //window.location.href='org_allservise.html';
                //window.location.href='org_index.html';
                link_to(result.organOperator.homePageUrl);
            }else{
                ale(result.mes);
            };
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

//
}
function orgAutoLogin(){
    $.ajax({
        type:'post',
        url:urlWay.orgBusinessHost+'orgLogin.action',
        cache:false,
        async:false,
        data:{action : "orgAutoLogin"},
        success:function(result){
            if(result.mes=='成功'){
                //window.location.href='org_allservise.html';
                //window.location.href='org_index.html';
                link_to(result.organOperator.homePageUrl);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}
//版本更新

function updateVersionFirst() {
    var isUpdate=sessionStorage.getItem('isUpdate');
    if(isUpdate=='update'){
        orgAutoLogin();
        return false;
    }else{
        var browser={
            versions:function(){
                var u = navigator.userAgent, app = navigator.appVersion;
                return {
                    trident: u.indexOf('Trident') > -1, //IE内核
                    presto: u.indexOf('Presto') > -1, //opera内核
                    webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                    gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                    mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                    ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                    android: u.indexOf('Android') > -1 , //android终端
                    uc:  u.indexOf('Linux') > -1, //uc浏览器
                    iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                    iPad: u.indexOf('iPad') > -1, //是否iPad
                    webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                    weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                    qq: u.match(/\sQQ/i) == " qq" //是否QQ
                };
            }(),
            language:(navigator.browserLanguage || navigator.language).toLowerCase()
        }
//判断是否IE内核
//    if(browser.versions.trident){ alert("is IE"); }
//判断是否webKit内核
//    if(browser.versions.webKit){ alert("is webKit"); }
//判断是否android
//    if(browser.versions.android){ alert("is android"); }
//判断是否移动端
//    if(browser.versions.mobile||browser.versions.android||browser.versions.ios){ alert("移动端"); }
//判断是否微信
        if(browser.versions.weixin){
            orgAutoLogin();
            return false;
        }
//判断除IE外其他浏览器使用语言
//    currentLang = navigator.language;
//    if(!currentLang){//判断IE浏览器使用语言
//        currentLang = navigator.browserLanguage;
//    }
        else{
            //判断是否苹果
            if(browser.versions.ios){
                return false;
            }
            //判断是否安卓
            if(browser.versions.android){
                $.ajax({
                    type:'post',
                    url:host+'versionManage.action',
                    cache:false,
                    async:false,
                    data:{
                        action:"getNewVersionCodeByOrgan"
                    },
                    success:function(result){
                        if(result.mes=="成功"){
                            if(versionCode!=result.versionManage.versionCode){
                                $('#cover').show();
                                sessionStorage.setItem('isUpdate','update');
                                var s=result.versionManage.isForceUpdate;
                                var height=$('#covercontent').height();
                                height=-height/2;
                                $('#covercontent').css({marginTop:height});
                                $('.updateversion>p:nth-of-type(1)>span').html(result.versionManage.versionCode);
                                $('.updateversion>p:nth-of-type(2)>span').html(result.versionManage.versionSize);
                                $('.updateversion>p:nth-of-type(3)>span').html(result.versionManage.updateDate);
                                var arr=result.versionManage.updateContent.split('&&');
                                var updatecontent='';
                                for(var i=0;i<arr.length;i++){
                                    updatecontent+='<p>'+arr[i]+'</p>';
                                }
                                $('#updatecontent').html(updatecontent);
                                if(s=='Y'){
                                    $('.chooseupdate').html('<div class="rightawayspe" onclick="gotoupdate()">立即更新</div>');
                                }
                            } else{
                                orgAutoLogin();
                            }
                        }
                    }
                });
            }
        }
    }
}
function noupdate(){
    $('#cover').hide();
    orgAutoLogin();
}
function gotoupdate(){
    updateVersionOrganActivity();
}

