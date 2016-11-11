/**
 * Created by windows on 2016/3/26.
 */
$(function () {
    var ser=decodeURIComponent(window.location.search.substring(1).split('&')[1]);
    $.ajax({
        type: 'post',
        url: urlWay.orgBusinessHost + 'orgBusinessManage.action',
        cache: false,
        async: false,
        data: {
            action: "getBusinessNameList"
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "org_login.html";
            }
            else if (result.mes == "成功") {
                var orglist = result.businessNameList;
                if (orglist != null &&orglist.length !=0) {
                    var html = '';
                    var firsturl = '';
                    var loginName=result.organOperator.loginName;
                    $('#loginName').html(loginName);
                    for (var i = 0, len = orglist.length; i < len; i++) {
                        var serviceurl = '';
                        if (orglist[i] == '计划免疫') {
                            var pagePath=result.organModuleInfoList[0].pagePath;
                            serviceurl = pagePath+'?'+ result.organOperator.hospitalBasicInfo.id+'&'+orglist[i];
                        } else if (orglist[i] == '儿保预约') {
                            serviceurl = 'org_babyprotect.html?' + result.organOperator.hospitalBasicInfo.id+'&'+orglist[i];
                        } else if (orglist[i] == '门诊预约') {
                            serviceurl = 'org_outpatient.html?' + result.organOperator.hospitalBasicInfo.id+'&'+orglist[i];
                        }

                        if (len == 1) {
                            html += '<li data-ser='+orglist[i]+' style="width:100%" onclick="serviceLoad(\'' + serviceurl + '\')">' + orglist[i] + '</li>'
                        } else if (len == 2) {
                            html += '<li data-ser='+orglist[i]+' style="width:50%" onclick="serviceLoad(\'' + serviceurl + '\')">' + orglist[i] + '</li>'
                        } else {
                            html += '<li data-ser='+orglist[i]+' style="width:33.3%" onclick="serviceLoad(\'' + serviceurl + '\')">' + orglist[i] + '</li>'
                        }
                    }

                    $('#header ul').html(html);
                    $('#header ul>li[data-ser='+ser+']').addClass('speblue').removeAttr('onclick');
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
});
function serviceLoad(url) {
    window.location.href=url;
}
function orgOutLogin(){
    var answer=confirm('您确定要退出系统吗？');
    if(answer==false){
        return false;
    }
    $.ajax({
        type:'post',
        url:urlWay.orgBusinessHost+'orgLogin.action',
        cache:false,
        async:false,
        data:{action : "orgOutLogin"},
        success:function(result){
            if(result.mes=='成功'){
                window.location.href='org_login.html';
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}
