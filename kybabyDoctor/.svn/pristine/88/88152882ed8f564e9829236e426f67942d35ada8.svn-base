//数组去重方法
var dateId=decodeURIComponent(window.location.search.substring(1).split('&&')[0]);
$(function () {
    Array.prototype.removeArrMore = function () {
        var n = {}, r = [];
        for (var i = 0; i < this.length; i++) {
            if (!n[this[i]]) {
                n[this[i]] = true;
                r.push(this[i]);
            }
        }
        return r;
    }
});
//完成页面跳转
function achieve() {
    window.location.href = 'editOutpatientTime.html?' + $('#showAddress').val()+'&&'+dateId;
}
function goBack(){
    window.location.href = 'editOutpatientTime.html?' + 'allNull'+'&&'+dateId;
}
//获取数据
$(function () {
    $.ajax({
        type: 'post',
        async: false,
        url: urlWay.clinicHost + 'doctorClinic.action',
        data: {
            action: "getClinicAddressList",
            "doctorMorePractice.clinicOrgType":0
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                window.location.href = "login.html";
            }
            else if (result.mes == "成功") {
                var list = result.addressList;
                var dataList = list.removeArrMore();
                for (var i = 0, len = dataList.length; i < len; i++) {
                    $('.historyAddress').append('<div>' + dataList[i] + '</div><p class="gray_3"></p>');
                }
            }
        },
        error: function () {
            alert('you are false');
        }
    });
});
//选择历史地址
$(function () {
    $('.historyAddress>div').click(function () {
        $('#showAddress').val($(this).text());
    });
});
