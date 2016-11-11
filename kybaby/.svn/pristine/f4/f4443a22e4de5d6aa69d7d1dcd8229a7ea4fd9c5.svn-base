
//$(function(){
//	doctorShow();
//});
$(function () {
    //sessionStorage.clear();
    $.ajax({
        type:'post',
        url:clinicHost+'clinicBooking.action',
        cache:false,
        async:false,
        data:{action:"getClinicDoctorInfo"
            //"doctorInfo.id":38//得到单个医生信息传此参数，医生列表不需要传参
        },
        success:function(result){
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list=result.doctorInfoFoList;
                var html='';
                for(var i= 0,len=list.length;i<len;i++){
                    var clinicBookingDateList=list[i].clinicBookingDateList;
                    var clinicBookingDateListHtml='';
                    if(clinicBookingDateList==null){
                        clinicBookingDateListHtml='<li>暂无可约时间</li>';
                    }else{
                        for(var k=0,l=clinicBookingDateList.length;k<l;k++){
                            clinicBookingDateListHtml+='<li style="color: #FF813d">'+clinicBookingDateList[k]+'</li>'
                        }
                    }
                    var doctor=list[i].doctorInfo;
                    var professionalDirection=list[i].majorNameList;
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
                    var hospitalLeval='<span class="iconbtn">'+list[i].hospitalLeval+'</span>';
                    if(list[i].hospitalLeval==''){
                        hospitalLeval='';
                    }
                    html+='<div class="det"><div class="detailinfo" data-doctor="'+doctor.doctorName+' '+doctor.doctorEmployer+'" onclick="goTo('+doctor.id+')"> ' +
                   '<div class="faceicon"> <img src="'+hostBG+'images/doctorFaceIcon/'+doctor.doctorImage+'" /> ' +
                   '<span style="font-size: 14px">'+doctor.doctorName+'</span> </div> <div class="doctordesc"> <p class="a">' +
                   doctor.doctorEmployer+hospitalLeval+'</p>' +
                   ' <p class="b"><span class="tag">'+doctor.department+'</span>&nbsp;|&nbsp;' +
                   '<span class="tag">'+doctor.doctorTitle+'</span>' +
                    '&nbsp;|&nbsp;<span>'+doctor.clinicalExperience+'年临床经验</span></p>' +
                   // ' <div class="c"> <span>专业方向：</span> <ul>' +
                   //majorNameListHtml+'</ul></div>' +
                    ' <div class="d"> ' +
                   '<ul> <li>'+majorNameListHtml+'</li> ' +
                   '</ul> </div> <div class="e"> <ul>'+clinicBookingDateListHtml+'</ul></div></div></div><p class="gray_s"></p></div>';
                }
                $('#doctor_box_f').html(html);
            }
            },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
            layer();
        }
    });

});

function goTo(id){
    window.location.href='pointPatient.html?'+id;
}
function searchMessage(){
    var val=$('#searchbox2 input').val();
    if($.trim(val)==''){
        $('.det').css({display:'block'});
    }else{
        $('.det').css({display:'none'});
        $('.detailinfo[data-doctor*="'+val+'"]').parent().css({display:'block'});
    }
}

