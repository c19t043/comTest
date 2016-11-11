/**
 * 版本控制文件
 */
//版本号
var versionCode = "1.2";
//版本名称
var versionName = "版本1.1";
//版本大小
var versionSize = "3.55MB";
//更新日期
var updateDate = "2016-03-11";
//更新内容 如有多条更新内容，用&&来分割
var updateContent = "1、BUG修复及新功能发布。";
//更新日志
var updateLog = "更新成功。";

$(function () {
    $('#mask_bottom>p>span').html(versionCode);
//    $('#mask_bottom>p').click(function () {
//            var browser={
//                versions:function(){
//                    var u = navigator.userAgent, app = navigator.appVersion;
//                    return {
//                        trident: u.indexOf('Trident') > -1, //IE内核
//                        presto: u.indexOf('Presto') > -1, //opera内核
//                        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
//                        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
//                        mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
//                        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
//                        android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
//                        iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
//                        iPad: u.indexOf('iPad') > -1, //是否iPad
//                        webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
//                        weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
//                        qq: u.match(/\sQQ/i) == " qq" //是否QQ
//                    };
//                }(),
//                language:(navigator.browserLanguage || navigator.language).toLowerCase()
//            }
////判断是否IE内核
////    if(browser.versions.trident){ alert("is IE"); }
////判断是否webKit内核
////    if(browser.versions.webKit){ alert("is webKit"); }
////判断是否android
////    if(browser.versions.android){ alert("is android"); }
////判断是否移动端
////    if(browser.versions.mobile||browser.versions.android||browser.versions.ios){ alert("移动端"); }
////判断是否微信
//            if(browser.versions.weixin){
//                return false;
//            }
////判断除IE外其他浏览器使用语言
////    currentLang = navigator.language;
////    if(!currentLang){//判断IE浏览器使用语言
////        currentLang = navigator.browserLanguage;
////    }
//            else{
//                //判断是否苹果
//                if(browser.versions.ios){
//                    return false;
//                }
//                //判断是否安卓
//                if(browser.versions.android){
//                    $.ajax({
//                        type:'post',
//                        url:host+'versionManage.action',
//                        cache:false,
//                        async:false,
//                        data:{
//                            action:"getNewVersionCodeByOrgan"
//                        },
//                        success:function(result){
//                            if(result.mes=="成功"){
//                                if(versionCode!=result.versionManage.versionCode){
//                                    $('#cover').show();
//                                    sessionStorage.setItem('isUpdate','update');
//                                    var s=result.versionManage.isForceUpdate;
//                                    var height=$('#covercontent').height();
//                                    height=-height/2;
//                                    $('#covercontent').css({marginTop:height});
//                                    $('.updateversion>p:nth-of-type(1)>span').html(result.versionManage.versionCode);
//                                    $('.updateversion>p:nth-of-type(2)>span').html(result.versionManage.versionSize);
//                                    $('.updateversion>p:nth-of-type(3)>span').html(result.versionManage.updateDate);
//                                    var arr=result.versionManage.updateContent.split('&&');
//                                    var updatecontent='';
//                                    for(var i=0;i<arr.length;i++){
//                                        updatecontent+='<p>'+arr[i]+'</p>';
//                                    }
//                                    $('#updatecontent').html(updatecontent);
//                                    if(s=='Y'){
//                                        $('.chooseupdate').html('<div class="rightawayspe" onclick="gotoupdate()">立即更新</div>');
//                                    }
//                                }
//                            }
//                        }
//                    });
//                }
//            }
//
//    });
});
function noupdate(){
    $('#cover').hide();
}
function gotoupdate(){
    updateVersionOrganActivity();
}