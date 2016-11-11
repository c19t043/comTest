
//$(function(){
//	doctorShow();
//});
$(function () {
    doctorInfoFoList.getDoctorInfoFoList();
});
var doctorInfoFoList= function () {
    var doctorInfoArrTemporary=[];
    return {
        number:0,//加载第几个10条数据
        doctorInfoArr:[],//临时存储后台数据
        //assignment: function () {//給body赋值
        //    $('body').height($(window).height());
        //    $('body').width($(window).width());
        //},
        getDoctorInfoFoList: function () {//得到医生数据
            $.ajax({
                type:'post',
                url:clinicHost+'clinicBooking.action',
                cache:false,
                async:true,
                data:{action:"getClinicDoctorInfo"
                    //"doctorInfo.id":38//得到单个医生信息传此参数，医生列表不需要传参
                },
                success:function(result){
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "login.html";
                    }
                    else if (result.mes == "成功") {
                        doctorInfoFoList.doctorInfoArr=result.doctorInfoFoList;
                        doctorInfoArrTemporary=doctorInfoFoList.doctorInfoArr;
                        doctorInfoFoList.showTenItems(doctorInfoFoList.number,doctorInfoFoList.doctorInfoArr);
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    //alert(XMLHttpRequest.status);
                    //alert(errorThrown);
                    layer();
                }
            });
        },
        scroll: function () {//向下滚动
            $(window).scroll(function () {
                if($(window).scrollTop()>=$(document).height()-$(window).height()){//滚动到底端
                    //console.log('您已经滚动到底端');
                    doctorInfoFoList.showTenItems(doctorInfoFoList.number,doctorInfoFoList.doctorInfoArr);
                }
            });
        },
        showTenItems: function (number,doctorInfoArr) {//下拉刷新显示10条数据
            var itemStart=number*10;
            var itemEnd=number*10+10;
            var length=doctorInfoArr.length;
            if(itemStart>=length){
                return false;
            }
            if(itemEnd>length){
                itemEnd=length;
            }
            doctorInfoFoList.number++;
            var html='';
            for(var i= itemStart;i<itemEnd;i++){
                var clinicBookingDateList=doctorInfoArr[i].clinicBookingDateList;
                var clinicBookingDateListHtml='';
                if(clinicBookingDateList==null){
                    clinicBookingDateListHtml='<li>暂无可约门诊时间</li>';
                }else{
                    for(var k=0,l=clinicBookingDateList.length;k<l;k++){
                        clinicBookingDateListHtml+='<li style="color: #FF813d">'+clinicBookingDateList[k]+'</li>'
                    }
                }
                var doctor=doctorInfoArr[i].doctorInfo;
                var professionalDirection=doctorInfoArr[i].majorNameList;
                var majorNameListHtml='';
                if(professionalDirection.length==1){
                    majorNameListHtml+=professionalDirection[0];
                }else if(professionalDirection.length>1){
                    for(var j= 0;j<2;j++){
                        majorNameListHtml+=professionalDirection[j]+' ';
                    }
                }
                //var m='list'+i;
                //sessionStorage.setItem(m,professionalDirection);
                var hospitalLeval='<span class="iconbtn">'+doctorInfoArr[i].hospitalLeval+'</span>';
                if(doctorInfoArr[i].hospitalLeval==''){
                    hospitalLeval='';
                }
                html+='<div class="det"><div class="detailinfo" data-doctor="'+doctor.doctorName+' '+doctor.doctorEmployer+'" onclick="doctorInfoFoList.goTo('+doctor.id+')"> ' +
                '<div class="faceicon"> <img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'" /><br/> ' +
                '<span style="font-size: 14px">'+doctor.doctorName+'</span> </div> <div class="doctordesc"> <p class="a">' +
                doctor.doctorEmployer+hospitalLeval+'</p>' +
                ' <p class="b"><span class="tag">'+doctor.department+'</span>&nbsp;|&nbsp;' +
                '<span class="tag">'+doctor.doctorTitle+'</span>' +
                '&nbsp;|&nbsp;<span>'+doctor.clinicalExperience+'年临床经验</span></p>' +
                    // ' <div class="c"> <span>专业方向：</span> <ul>' +
                    //majorNameListHtml+'</ul></div>' +
                ' <div class="d"> ' +
                '<ul> <li>'+majorNameListHtml+'</li> ' +
                '</ul> </div> <div class="e"><ul>'+clinicBookingDateListHtml+'</ul></div></div></div><p class="gray_s"></p></div>';
            }
            $('#doctor_box_f').append(html);
            doctorInfoFoList.scroll();
        },
        goTo: function (id) {//到医生主页
            window.location.href='pointPatient.html?'+id;
        },
        searchMessage: function () {//搜索
            var val=$('#searchbox2 input').val();
            $('#doctor_box_f').html('');
            doctorInfoFoList.number=0;
            doctorInfoFoList.doctorInfoArr=doctorInfoArrTemporary;//搜索初始化
            if($.trim(val)==''){
                doctorInfoFoList.showTenItems(doctorInfoFoList.number,doctorInfoFoList.doctorInfoArr);
                //$('.det').css({display:'block'});
            }else{
                var arr=[];
                for(var i= 0,len=doctorInfoFoList.doctorInfoArr.length;i<len;i++){
                    var data_doctor=doctorInfoFoList.doctorInfoArr[i].doctorInfo.doctorName+''+doctorInfoFoList.doctorInfoArr[i].doctorInfo.doctorEmployer;
                    if(data_doctor.indexOf(val)>-1){
                        arr.push(doctorInfoFoList.doctorInfoArr[i]);
                    }
                }
                doctorInfoFoList.doctorInfoArr=arr;
                doctorInfoFoList.showTenItems(doctorInfoFoList.number,doctorInfoFoList.doctorInfoArr);
                //$('.det').css({display:'none'});
                //$('.detailinfo[data-doctor*="'+val+'"]').parent().css({display:'block'});
            }
        }
    }
}();

