var organSetMealId='',orgId='',parentId='';
$(function () {
    orgId=decodeURIComponent(window.location.search.substring(1).split('&')[0]);
    organSetMealId=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
    time_plug();
    packageConfirm.getPackageInfo();
    //packageConfirm.getBabyRecord();
});
var packageConfirm={
    getPackageInfo: function () {//获取套餐详情
        $.ajax({
            type: 'post',
            async: true,
            url: hostOrgbusiness + 'orgSetMealManager.action',
            data: {
                action: "getOrganSetMealDetail",
                "organSetMeal.id":organSetMealId,
                "hospitalBasicInfo.id":orgId
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == '成功') {
                    $('.packageName').html(result.organSetMeal.packageName);
                    var monthAge=result.consultBabyInfo.monthAge;
                    var babyAge='';
                    if(monthAge/12>=1){
                        if(monthAge%12==0){
                            babyAge=monthAge/12+'年';
                        }else{
                            babyAge=Math.floor(monthAge/12)+'年'+monthAge%12+'个月';
                        }
                    }else{
                        babyAge=monthAge+'个月';
                    }
                    $('#babyInfo').html(result.consultBabyInfo.babyName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+babyAge).attr('data-baby-id',result.consultBabyInfo.id);
                    var babyId=result.consultBabyInfo.id;
                    $('.packageImg>img').prop('src',photo+'admin/images/familydoctor/'+result.organSetMeal.packageImg);
                    var parentOrganSetProList=result.parentOrganSetProList;
                    if(parentOrganSetProList!=null && parentOrganSetProList.length!=0){
                        var html='';
                        for(var i= 0,len=parentOrganSetProList.length;i<len;i++){
                            html+='<li data-parentOrganSetProList-id="'+parentOrganSetProList[i].id+'" onclick="packageConfirm.getPackageInfoTime('+parentOrganSetProList[i].id+','+babyId+',this)">'+parentOrganSetProList[i].proName+'<div style="display: none">' +
                                parentOrganSetProList[i].description
                                +'</div></li>';
                        }
                        $('#packageType').html(html);
                        //$('#packageType>li:nth-child(1)').addClass('active').append('<img src="images/images_family_doctor/true.png" alt="">');
                        //packageConfirm.packageType();
                        packageConfirm.getPackageInfoTime(parentOrganSetProList[0].id,babyId,$('#packageType>li:nth-child(1)'));
                    }else{
                        $('.packageType,.chooseTime').hide();
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer();
            }
        });
    },
    getPackageInfoTime: function (organSetProId,babyId,_this) {//获取套餐类型下的服务时间
        var babId=$('#babyInfo').attr('data-baby-id');
        $('#packageType>li>img').remove();
        $('#packageInstruction').html($(_this).find('div').html());
        $(_this).addClass('active').append('<img src="images/images_family_doctor/true.png" alt="">').siblings().removeClass('active');
        $.ajax({
            type: 'post',
            async: true,
            url: hostOrgbusiness + 'orgSetMealManager.action',
            data: {
                action: "getChildOrganSetProList",
                "organSetPro.id":organSetProId,
                "consultBabyInfo.id":babId,
                "hospitalBasicInfo.id":orgId
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == '成功') {
                    var childOrganSetProList=result.childOrganSetProList;
                    if(childOrganSetProList!=null && childOrganSetProList.length!=0){
                        var html='';
                        for(var i= 0,len=childOrganSetProList.length;i<len;i++){
                            html+='<li data-serviceTimes="'+childOrganSetProList[i].serviceTimes+'" data-proPrice="'+childOrganSetProList[i].proPrice+'" data-isCanChoose="'+childOrganSetProList[i].isCanChoose+'" data-childOrganSetProList-id="'+childOrganSetProList[i].id+'">'+childOrganSetProList[i].proName+'</li>';
                        }
                        $('#chooseTime').html(html);
                        $('#chooseTime>li[data-isCanChoose=Y]').eq(0).addClass('active').append('<img src="images/images_family_doctor/true.png" alt="">');
                        if(!isNaN(parseFloat($('#chooseTime .active').attr('data-proPrice')).toFixed(2))){
                            $('.packageCon>.money').html('&yen;'+parseFloat($('#chooseTime .active').attr('data-proPrice')).toFixed(2));
                        }
                        packageConfirm.chooseTime();
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer();
            }
        });
    },
    packageType: function () {
        $('#packageType>li').click(function () {//选择套餐类型
            $('#packageType>li>img').remove();
            $(this).addClass('active').append('<img src="images/images_family_doctor/true.png" alt="">').siblings().removeClass('active');
        });
    },
    chooseTime: function () {
        $('#chooseTime>li').click(function () {//选择服务时间
            var _this=this;
            if($(_this).attr('data-isCanChoose')=='N'){
                ale('该产品的使用限制不适合您的宝宝，请重新选择吧。');
                return false;
            }
            $('#chooseTime>li>img').remove();
            $(_this).addClass('active').append('<img src="images/images_family_doctor/true.png" alt="">').siblings().removeClass('active');
            $('.packageCon>.money').html('&yen;'+parseFloat($(_this).attr('data-proPrice')).toFixed(2));
        });
    },
    buyRightAway:function (){//立即购买
        var organSetProId=$('#chooseTime .active').attr('data-childOrganSetProList-id');
        var serviceTimes=$('#chooseTime .active').attr('data-servicetimes');
        var proPrice=$('#chooseTime .active').attr('data-proPrice');
        var babyId=$('#babyInfo').attr('data-baby-id');
        if(organSetProId==undefined||organSetProId==''||organSetProId==null){
            ale('请先选择服务时间');
            return false;
        }
        $.ajax({
            type: 'post',
            async: true,
            url: hostOrgbusiness + 'orgSetMealManager.action',
            data: {
                action: "handleOrganSetMealOrder",
                "organSetMeatOrder.id":'',
                "organSetMeatOrder.organSetMeal.id":organSetMealId,
                "organSetMeatOrder.babyInfo.id":babyId,
                "organSetMeatOrder.serviceTimes":serviceTimes,
                "organSetMeatOrder.serviceSurplusTimes":serviceTimes,
                "organSetMeatOrder.organSetPro.id":organSetProId,
                "organSetMeatOrder.totalPrice":proPrice
            },
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                }
                else if (result.mes == '成功') {
                    window.location.href='package_pay.html?'+result.organSetMeatOrder.id;
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer();
            }
        });
    },
    chooseBaby: function () {
        $('.baby_information').click(function () {//为谁购买选择该宝宝
            $(this).addClass('select').siblings().removeClass('select');
        });
        $("#baby_sex li").click(function(){//性别选择
            $(this).addClass("selected").siblings("li").removeClass("selected");
        })
    },
    showChooseBaby: function () {
        $('#chooseBabyCover').show();
        packageConfirm.getBabyRecord();
    },
    getBabyRecord: function () {//获取宝宝记录
        $.ajax({
            type: 'post',
            url: consult + 'consultBabyManager.action',
            cache: false,
            async: true,
            data: {action: "getBabys"},
            success: function (result) {
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    window.location.href = "login.html";
                } else if (result.message == "成功") {
                    var babyInfoList=result.babyInfoList;
                    parentId=result.parentId;
                    var html='';
                    if(babyInfoList!=null){
                        for(var i= 0,len=babyInfoList.length;i<len;i++){
                            var imgsrc=hostBG+'images/userFaceIcon/lt_user.png';
                            if(babyInfoList[i].headImg!=null){
                                imgsrc=hostBG + 'images/consultPicture/'+babyInfoList[i].headImg;
                            }
                            html+='<li class="baby_information" data-birth="'+babyInfoList[i].birthday+'" data-baby-id="'+babyInfoList[i].id+'">' +
                                '<img src="'+imgsrc+'" alt="图片"/>' +
                                '<p>'+babyInfoList[i].babyName+'</p>' +
                                '</li>';
                        }
                        html+='<li id="add_baby" onclick="packageConfirm.addBaby()">' +
                            '<img src="images/images_main/add.png" alt="图片"/>' +
                            '<p>添加宝宝</p></li>';
                        $('#baby_list').html(html);
                        var babyId=$('#babyInfo').attr('data-baby-id');
                        $('.baby_information[data-baby-id='+babyId+']').addClass('select');
                        packageConfirm.chooseBaby();
                    }
                }
            },
            error: function () {

            }
        });
    },
    addBaby: function () {
        var obj=$('#baby_list>li');
        if(obj.length>3){
            ale('最多只能添加3个宝宝');
            return false;
        }
        $('#addBabyCover').show();
    },
    confirmBaby: function () {
        var babyId=$('#baby_list>.select').attr('data-baby-id');
        var babyBirth=$('#baby_list>.select').attr('data-birth');
        $.ajax({
            type:'post',
            url:clinicHost+'clinicBooking.action',
            cache:false,
            async:true,
            data:{action:"getCurrentTime"},
            success:function(result){
                if (result.mes == '请登录') {
                    ale('请登录', '24px');
                    var isSession=sessionStorage.getItem('session');
                    if(isSession=='yes'){
                        return false;
                    }
                    window.location.href = "login.html";
                }
                else if (result.mes == "成功") {
                    var todayTimeArr=result.currentTime.split(' ')[0].split('-');
                    var year=parseInt(todayTimeArr[0]);
                    var month=parseInt(todayTimeArr[1]);
                    var nowyear=parseInt(babyBirth.split('-')[0]);
                    var nowmonth=parseInt(babyBirth.split('-')[1]);
                    var babyNowMonth=(year-nowyear)*12+(month-nowmonth);
                    var babyAge='';
                    if(babyNowMonth/12>=1){
                        if(babyNowMonth%12==0){
                            babyAge=babyNowMonth/12+'年';
                        }else{
                            babyAge=Math.floor(babyNowMonth/12)+'年'+babyNowMonth%12+'个月';
                        }
                    }else{
                        babyAge=babyNowMonth+'个月';
                    }
                    $('#babyInfo').html($('#baby_list>.select>p').text()+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+babyAge).attr('data-baby-id',babyId);
                    packageConfirm.getPackageInfoTime($('#packageType>li.active').attr('data-parentOrganSetProList-id'), babyId, $('#packageType>li.active'));
                    $('#chooseBabyCover').hide();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    },
    addBabyConfirm: function () {
        var obj=$('#baby_list>li');
        if(obj.length>3){
            ale('最多只能添加3个宝宝');
            return false;
        }
        var baby_name = $("#baby_name").val();
        var baby_sex = $("ul#baby_sex").children(".selected").children("span").last().text().substring(0,1);
        var baby_birthday = $(".baby_birthday").val();
        var date_arr = baby_birthday.split('-');
        var dyear = (new Date()).getFullYear();
        var dmonth = (new Date().getMonth())+1;
        var dday = (new Date().getDate());
        var nyear = parseInt(date_arr[0]);
        var nmonth = parseInt(date_arr[1]);
        var nday = parseInt(date_arr[2]);
        if(baby_name==''){
            ale("请填写宝宝姓名");
        }
        else if(baby_birthday==''){
            ale("请选择宝宝生日");
        }else if(nyear > dyear){
            ale('请选择正确的生日');
            return false;
        }else if(nyear == dyear && nmonth > dmonth){
            ale('请选择正确的生日');
            return false;
        }else if(nyear == dyear && nmonth == dmonth && nday>dday){
            ale('请选择正确的生日');
            return false;
        }
        else{
            var baby_name = $("#baby_name").val();
            var baby_sex = $("ul#baby_sex").children(".selected").children("span").last().text().substring(0,1);
            var baby_birthday = $(".baby_birthday").val();
            var date_arr = baby_birthday.split('-');
            var dyear = (new Date()).getFullYear();
            var dmonth = (new Date().getMonth())+1;
            var dday = (new Date().getDate());
            var nyear = parseInt(date_arr[0]);
            var nmonth = parseInt(date_arr[1]);
            var nday = parseInt(date_arr[2]);
            var parentId = window.location.search.split('&')[1];
            var orderId = window.location.search.split('&')[2];
            if(baby_name==''){
                ale("请填写宝宝姓名");
            }
            else if(baby_birthday==''){
                ale("请选择宝宝生日");
            }
            else if(nyear > dyear){
                ale('请选择正确的生日');
                return false;
            }else if(nyear == dyear && nmonth > dmonth){
                ale('请选择正确的生日');
                return false;
            }else if(nyear == dyear && nmonth == dmonth && nday>dday){
                ale('请选择正确的生日');
                return false;
            }
            else{
                $.ajax({
                    type: 'post',
                    url: consult + 'consultBabyManager.action',
                    cache: false,
                    async: true,
                    data: {action: "addBaby","babyName":baby_name,"sex":baby_sex,"birthday":baby_birthday,"parentId":parentId,"imagedata":imagedata},
                    success: function (result) {
                        if(result.message=='请登录'){
                            ale('请登录', '24px');
                            window.location.href = "login.html";
                        }
                        else if(result.message=='成功'){
                            $('#addBabyCover').hide();
                            packageConfirm.getBabyRecord();

                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {

                    }
                });
            }
        }
    }
}
/*****************     日期插件BEGIN     *****************/
function time_plug(){
    var currYear = (new Date()).getFullYear();
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
        startYear: currYear - 50, //开始年份
        endYear: currYear//结束年份
    };

    $("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
    var optDateTime = $.extend(opt['datetime'], opt['default']);
    var optTime = $.extend(opt['time'], opt['default']);
//		$("#appDate").mobiscroll(optDateTime).datetime(optDateTime);
//		 $("#appTime").mobiscroll(optTime).time(optTime);
}
/*****************     日期插件END     *****************/

/*****************     上传图片BEGIN     *****************/
//测试。。。。。。。。。。。。。。。。。。。。
var MAX_HEIGHT=70,imagedata='';
function render(src){
    var image = new Image();
    image.onload = function(){
        var canvas = document.getElementById("myCanvas");
        var x = image.width;
        var y = image.height;

        if(image.height > MAX_HEIGHT) {
            // 宽度等比例缩放 *=
            image.width *= MAX_HEIGHT / image.height;
            image.height = MAX_HEIGHT;
        }
        var ctx = canvas.getContext("2d");  // 获取 canvas的 2d 环境对象,可以理解Context是管理员，canvas是房子
        ctx.clearRect(0, 0, canvas.width, canvas.height);// canvas清屏
//      canvas.width = image.width;  // 重置canvas宽高
//      canvas.height = image.height;
        canvas.width = image.width;  // 重置canvas宽高
        canvas.height = image.height;
        var top=-image.height;
        $('#updateImg>input').css({height:image.height,width:image.width,top:top});
        ctx.drawImage(image, 0, 0,x,y,0,0,image.width,image.height);  // 将图像绘制到canvas上
    };
    setTimeout(sendImage,1000);
    image.src = src;  // 记住必须先绑定事件，才能设置src属性，否则会出同步问题。
}
// 加载 图像文件(url路径)
function loadImage(obj){
    var src = $(obj).get(0).files[0];
    var imgType = src.name.split('.');
    imgType = imgType[imgType.length - 1];//返回后缀名，以兼容部分移动端浏览器
    if (imgType == 'jpg') {
        imgType = 'jpeg';
    }
    if (!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')) {
        ale('请选择图片文件');
        return false;
    }

    // 创建 FileReader 对象 并调用 render 函数来完成渲染.
    var reader = new FileReader();
    // 绑定load事件自动回调函数
    var imgData = '';
    reader.onload = function (e) {
        if (e.target.result.substring(5, 10) != 'image') {
            var imgDataArr = e.target.result.split('base64');
            imgData = imgDataArr[0] + "image/" + imgType + ";base64" + imgDataArr[1];
            render(imgData);
        } else {
            render(e.target.result);
        }
    };
    // 读取文件内容
    reader.readAsDataURL(src);
    $('#updateImg>img').hide();
    $('#myCanvas').show();
};
//---------------------------------------------
// 上传图片，jQuery版
function sendImage(){
    var canvas = document.getElementById("myCanvas");
    // 获取Base64编码后的图像数据，格式是字符串
    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。
    var dataurl = canvas.toDataURL("image/png");
    // 为安全 对URI进行编码
    // data%3Aimage%2Fpng%3Bbase64%2C 开头
    imagedata = encodeURIComponent(dataurl);
};
