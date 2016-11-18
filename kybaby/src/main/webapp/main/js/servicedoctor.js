
$(function () {
    doctorInfoFoList.getDoctorInfoFoList('');
});
var doctorInfoFoList= function () {
    var doctorInfoArrTemporary=[];
    return {
        number:1,//加载第几个10条数据
        isEndLoad:false,//这次请求的数据是否加载完全
        searchValue:'',//搜索查询值
       // doctorInfoArr:[],//临时存储后台数据
        //assignment: function () {//給body赋值
        //    $('body').height($(window).height());
        //    $('body').width($(window).width());
        //},
        getDoctorInfoFoList: function (value) {//得到医生数据
            doctorInfoFoList.isEndLoad=false;
            $.ajax({
                type:'post',
                url:clinicHost+'clinicBooking.action',
                cache:false,
                async:true,
                data:{
                    action:"getClinicDoctorInfo",
                    "pageBean.curPage":doctorInfoFoList.number,
                    "pageBean.pageSize":10,
                    "doctorInfo.doctorEmployer":value,
                    "doctorInfo.doctorName":value
                },
                success:function(result){
                    if (result.mes == '请登录') {
                        ale('请登录', '24px');
                        window.location.href = "login.html";
                    }
                    else if (result.mes == "成功") {
                        doctorInfoFoList.number++;
                        var html='';
                        var doctorInfoArr=result.doctorInfoFoList;
                        if(doctorInfoArr == null){
                            return;
                        }
                        for(var i= 0;i<doctorInfoArr.length;i++){
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
                        doctorInfoFoList.isEndLoad=true;
                        doctorInfoFoList.scroll();
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
                    if(doctorInfoFoList.isEndLoad){
                        //console.log('您已经滚动到底端');
                        doctorInfoFoList.getDoctorInfoFoList(doctorInfoFoList.searchValue);
                    }
                }
            });
        },
        goTo: function (id) {//到医生主页
            window.location.href='pointPatient.html?'+id;
        },
        searchMessage: function () {//搜索
            $('#doctor_box_f').html('');
            var val=$('#searchbox2 input').val().trim();
            doctorInfoFoList.searchValue=val;
            doctorInfoFoList.number=1;
            doctorInfoFoList.getDoctorInfoFoList(val);
        }
    }
}();