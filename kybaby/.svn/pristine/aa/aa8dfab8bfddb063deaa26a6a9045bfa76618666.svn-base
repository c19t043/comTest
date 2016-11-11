$(function () {
    getUserFollowHospitalList();
    $('.selbtn').click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var text=$(this).text().trim();
        if(text=='机构'){
            getUserFollowHospitalList();
            $('#showOrg').show();
            $('#show_doctor').hide();
        }else{
            doctorList();
            $('#showOrg').hide();
            $('#show_doctor').show();
        }
    });
});
function getUserFollowHospitalList(){
    $.ajax({
        type: 'post',
        url: hostOrgbusiness + 'orgManage.action',
        cache: false,
        async: false,
        data: {action: "getUserFollowHospitalList"},
        success: function (result) {
            var list1 = result.userFollowHospitalList;
            if(list1==null || list1.length==0){
                //$("#showOrg").html("<div class='prompt'><i class='iconfont'>&#xe631;</i><p>暂无相关数据</p></div>");
                return false;
            }
            $('#showOrg').html('');
            for (var i = 0, len = list1.length; i < len; i++) {
                var list=list1[i].hospitalBasicInfo;
                if(list1[i].isFollow=='N'){
                    continue;
                }
                var biaoqian = '';
                $('#showOrg').append('' +
                '<div class="org" data-hospital="'+list.hospitalLname+'"><div class="orglist" onclick="window.location.href=\'orgservice_page.html?' + list.id + '\'"> ' +
                '<div class="orgimg"><img src="/kybabyBG/' + list.showImgPath + '" alt=""/></div> ' +
                '<div class="orgcon"> ' +
                '<p>' + list.hospitalLname + '</p> ' +
                '<p class="biaoqian"> ' + biaoqian +
                '</p> ' +
                '<div class="orgaddress"> ' +
                //'<div>地址:</div> ' +
                '<div style="width: 98%">' + list.address + '</div> ' +
                '</div> ' +
                '</div> ' +
                '<div class="orgdistance" style="font-size: 12px">已关注</div> ' +
                '</div> ' +
                '<p class="gray_s"></p></div>');
            }

        },
        error: function () {
            layer();
        }
    });

}
function doctorList() {
    $.ajax({
        type: 'post',
        url: host + 'getDoctorInfo.action',
        cache: false,
        async: false,
        data: {action: "doctorList"},
        success: function (result) {
            if (result.someDoctorMsgList == null || result.someDoctorMsgList.length == 0) {
                //$("#show_doctor").html("<div class='prompt'><i class='iconfont'>&#xe631;</i><p>暂无相关数据</p></div>");
                return false;
            }
            $('#show_doctor').html('');
            for (var i = 0; i < result.someDoctorMsgList.length; i++) {
                var arr = result.someDoctorMsgList[i].toString().split('&');
                var arr_info = arr[0].split('::');
                var status = 'none';
                var imgurl = '';
                var zxstatus = '';
                if (arr_info[0] == '是') {
                    status = 'block';
                }

                if (arr[1] == '' || arr[1] == null || arr[1] == undefined) {
                    imgurl = 'images/doctor_default.png';
                } else {
                    imgurl = hostBG + 'images/doctorFaceIcon/' + arr[1];

                }

                if (arr[7] == 'Y') {
                    zxstatus = 'on';
                } else if (arr[7] == 'N') {
                    zxstatus = '';
                }


                var arr_length = arr_info.length;
                var doctor_name = arr_info[arr_length - 1];
                var major_str = "";
                for (var k = 1; k < arr_length - 1; k++) {
                    major_str += arr_info[k] + "、";
                }
                major_str = major_str.substring(0, major_str.length - 1);

                $('#show_doctor').append(
                    "<div class='doctor_box'>" +
                    "<div class='doctor_left'>" +
                    "<div class='doctor_photo'>" +
                    "<img src=" + imgurl + " />" +
                    "</div>" +
                    "<p style='display:" + status + "' class='servicing'>服务中···</p>" +
                    "</div>" +
                    "<div class='doctor_center'>" +
                    "<p class='doctor_name'>" + doctor_name + "</p>" +
                    "<p>医师认证(" + arr[2] + "）</p>" +
                    "<p>专业方向：<span>" + major_str + "<span></p>" +
                    "<div class='mymark'>" +
                    "<div class='mark_list'>" +
                    "<div class='mark_list_left'>服务态度</div>" +
                    "<div id='service_" + i + "' class='mark_list_right'>" +

                    "</div>" +
                    "</div>" +
                    "<div class='mark_list'>" +
                    "<div class='mark_list_left'>责任感</div>" +
                    "<div id='bility_" + i + "' class='mark_list_right'>" +

                    "</div>" +
                    "</div>" +
                    "<div class='mark_list'>" +
                    "<div class='mark_list_left'>准时度</div>" +
                    "<div id='timefor_" + i + "' class='mark_list_right'>" +

                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div id='zx_for_" + i + "' class='consultation_icon " + zxstatus + " ' " +
                        //
                    " onclick=\"check('" + arr[6] + "','" + arr_info[arr_length - 1] + "','" + zxstatus + "')\" " +
                    " >" +
                    "</div>" +
                    "</div>" +
                    "<p class='gray_2'></p>"
                );
//				ale("zeren"+arr[4]);
//				ale("fuwu"+arr[3]);
//				ale("xingji"+arr[5]);
                for (var a = 0; a < arr[4]; a++) {
                    $("#service_" + i).append(
                        "<img src='images/icon_star.png'>"
                    );
                }
                for (var a = 0; a < arr[3]; a++) {
                    $("#bility_" + i).append(
                        "<img src='images/icon_star.png'>"
                    );
                }
                for (var a = 0; a < arr[5]; a++) {
                    $("#timefor_" + i).append(
                        "<img src='images/icon_star.png'>"
                    );
                }
//				$("#zx_for_"+i).click(function(){
//					if($(this).hasClass('on')){
//						ale('');
//						window.location.href="fillconsultation.html?doctorId="+arr[6]+"&doctorName="+arr_info[arr_length-1];
//					}
//				});
            }
        },
        error: function () {
            layer();
        }
    });
}


function check(a, b, c) {

    /*
     * update by HooLee
     * 2015年11月30日14:35:51
     * 所有的医生都可以咨询，不在线的咨询就是留言的形式
     * */
    //if (c =='on'){
    consulationSomeDoctor(a, b);
    //}
}


function consulationSomeDoctor(doctorId, doctorName) {
    $.ajax({
        type: 'post',
        url: host + 'getUserConsultDoctorInfo.action',
        cache: false,
        async: false,
        data: {action: "getLastAfterConsultation", doctorId: doctorId},
        success: function (result) {
            if (result.mes == "操作成功") {
                window.location.replace("consultation1.html?doctorId=" + doctorId + "&doctorName=" + encodeURIComponent(doctorName) + "&Page=mydoctor");
            } else if (result.mes == "已结束") {
                window.location.replace('fillconsultation.html?doctorId=' + doctorId + "&doctorName=" + encodeURIComponent(doctorName) + "&Page=mydoctor");
            } else if (result.mes == "未登录") {
                ale("您还没有登录，请您登录后再来咨询");
                setTimeout(function () {
                    window.location.href = "login.html";
                }, 2500);
            }
        },
        error: function () {
            layer();
        }
    });
}
