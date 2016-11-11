var addressId;
var comeMethod;
var serviceArea;
var doctorId;
$(function(){
    $.ajax({
        type:'post',
        url:urlWay.familyDoctorHost+'familyDoctorServe.action',
        cache:false,
        async:false,
        data:{action : "getFamilyDoctorServe"},
        success:function(result){

            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list=result.doctorServiceTypeList;
                var address=result.doctorAddress;
                serviceArea=result.doctorInfo.serviceArea;
                comeMethod=result.doctorInfo.comeMethod;
                doctorId=result.doctorInfo.id;
                if(serviceArea<=10){
                    $('.distance>div>div:nth-child(1)').addClass('chooseActive');
                    $('.distance>div>div:nth-child(1)>img').prop('src','images/chooseAddress.jpg');
                }else if(serviceArea<=20&&serviceArea>10){
                    $('.distance>div>div:nth-child(2)').addClass('chooseActive');
                    $('.distance>div>div:nth-child(2)>img').prop('src','images/chooseAddress.jpg');
                }else{
                    $('.distance>div>div:nth-child(3)').addClass('chooseActive');
                    $('.distance>div>div:nth-child(3)>img').prop('src','images/chooseAddress.jpg');
                }

                if(comeMethod=='自驾'){
                    $('.go_way span:nth-child(1)').addClass('spe');
                }else if(comeMethod=='公共交通'){
                    $('.go_way span:nth-child(2)').addClass('spe');
                }else{
                    $('.go_way span:nth-child(3)').addClass('spe');
                }

                if(address==null){
                    addressId='';
                }else{
                    addressId=address.id;
                }
                if(list==null||list.length==0){
                    $('.service_open').hide();
                }else if(list.length>0){
                    for(var i= 0,len=list.length;i<len;i++){
                        $('.service_open>div').append('<span>'+list[i].serviceTypeName+'</span>');
                    }
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
    });
    hf_loading(true);
});
function newAddress(){
    if($("#detailAdd").val().trim()==''){
        ale("请填写详细地址");
        return false;
    }
    if($("#jiedao").val().trim()==''){
        ale("请填写街道");
        return false;
    }
    if($("#detailAdd").val()!=""&&$("#detailAdd").val()!=null){
        var doctorLat = "";
        var doctorLng = "";
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        var strList = $("#sheng").val()+"省"+$("#shi").val()+"市"+$("#qu").val()+$("#jiedao").val()+$("#detailAdd").val();
        //ale(strList+"地址字符串");
        myGeo.getPoint(strList, function(point){
            if (point) {
                doctorLat=point.lat;
                doctorLng=point.lng;
                comeMethod=$('.spe').html();
                serviceArea=parseInt($('.chooseActive').html());
                if(isNaN(serviceArea)){
                    serviceArea=9999;
                }
                //ale(doctorLat+"纬度");
                //ale(doctorLng+"经度");
                $.ajax({
                    type : 'post',
                    async: false ,
                    url:urlWay.familyDoctorHost+'familyDoctorServe.action',
                    data : {
                        action:"saveFamilyDoctorServe",
                        "doctorAddress.detailAddress":$("#detailAdd").val(),
                        "doctorAddress.doctorStreet":$("#jiedao").val(),
                        "doctorAddress.doctorArea":$("#qu").val(),
                        "doctorAddress.doctorCity":$("#shi").val(),
                        "doctorAddress.doctorLat":doctorLat,
                        "doctorAddress.doctorProvince":$("#sheng").val(),
                        "doctorAddress.doctorLng":doctorLng,
                        "doctorAddress.id":addressId,
                        "doctorAddress.addressStatus":"Y",
                        "doctorInfo.comeMethod":comeMethod,
                        "doctorInfo.serviceArea":serviceArea,
                        "doctorInfo.id":doctorId
                    },
                    success : function(result) {
//						ale(result.mes);
                        if(result.mes=="请登录"){
                            window.location.href="login.html";
                        }
                        else if(result.mes=="成功"){
                            window.location.href="famillydoctortime.html";
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                    }
                });
            }else{
                ale("您选择地址没有解析到结果!");
            }
        }, "成都市");
        return false;
    }
}


function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
    if(status == false){
        document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
        return false;
    }
    $('body').append(
        "<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
        "<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
        "<img id='hf_loading_img' src='images/hf_autoplay.png' style='margin-bottom:10px;' />"+
        "<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:16px;text-align:left;color:#868686;font-weight:bold;'>地址加载中。</span>"+
        "</div>"+
        "</div>");

    var divobj = document.getElementById('hf_loading');
    var boxobj = document.getElementById('hf_loading_box');
    var imgobj = document.getElementById('hf_loading_img');
    var spanobj = document.getElementById('hf_loading_word');
    var du = 0;

    //divobj.style.width = document.documentElement.scrollWidth + "px";
    //divobj.style.height = document.documentElement.scrollHeight + "px";
    divobj.style.width = $(document).width() + "px";
    divobj.style.height = $(document).height() + "px";

    boxobj.style.top = (document.documentElement.clientHeight/2 - boxobj.offsetHeight/2) + "px";
    boxobj.style.left = (document.documentElement.clientWidth/2 - boxobj.offsetWidth/2) + "px";

    function trans(){
        if(du == 360){
            du = 0;
        }else{
            du = du + 10;
        }
        imgobj.style.transform = "rotate("+du+"deg)";
        imgobj.style.WebkitTransform = "rotate("+du+"deg)";
    }
    function transword(){
        if(spanobj.innerHTML == '地址加载中。'){
            spanobj.innerHTML = '地址加载中。。';
        }else if(spanobj.innerHTML == '地址加载中。。'){
            spanobj.innerHTML = '地址加载中。。。';
        }else if(spanobj.innerHTML == '地址加载中。。。'){
            spanobj.innerHTML = '地址加载中。';
        }
    }
    setInterval(function(){
        transword();
    },300);
    setInterval(function(){
        trans();
    },20);
}
$(function () {
    $('.distance>div>div').click(function(){
        $('.distance>div>div').removeClass('chooseActive');
        $('.distance>div>div>img').prop('src','images/chooseAddress1.jpg');
        $(this).addClass('chooseActive');
        $(this).find('img').prop('src','images/chooseAddress.jpg');
        serviceArea=parseFloat($(this).html());
        if(serviceArea==NaN){
            serviceArea=999;
        }
    });
    $('.go_way>div>span').click(function(){
        $('.go_way>div>span').removeClass('spe');
        $(this).addClass('spe');
        comeMethod=$(this).text();
    });
});
