/**
 * Created by jijingwei on 2016/10/26.
 */
$(function(){
    var package_id = window.location.search.substring(1).split("=")[1];//包id
    $(".leftmeu").click(function(){
        link_to('evaluate_fd_healthAssessment.html?'+package_id+"&");
    })
    $.ajax({
        type: 'post',
        url: testManager + 'asqAction.action',
        cache: false,
        async: true,
        data: {
            action: "getAsqResultScoreExList",
            "fdServicePackage.id" : package_id,
        },
        success: function (result) {
            if (result.mes == '请登录') {
                ale('请登录', '24px');
                link_to("login.html");
            }
            else if (result.mes == '成功') {
                if(result.asqResultScoreExList!=null){
                    var html = '';
                    for(var i=0;i<result.asqResultScoreExList.length;i++){
                        var asqResult = result.asqResultScoreExList[i];
                        var img = '';
                        if(asqResult.asqTaotiAge.taoti!=null){
                            img = '<div class="s_img"><img src="'+photo+asqResult.asqTaotiAge.taoti.imgPath+'" alt=""/></div>';
                        }
                        html += '<div class="results_item" data-id="'+asqResult.id+'"><div class="results_information flex_cont flex_simple">' +
                            img+'<div class="flex_item">' +
                            '<p class="admin_name">'+asqResult.asqTestUserInfo.asqUserName+'</p>' +
                            '<p class="s_tit">'+asqResult.asqTaoti.parent.titalName+asqResult.asqTaotiAge.showName+'</p></div>' +
                            '<div class="s_btn">查看结果</div></div><p class="gray_s"></p><p class="create_time">创建时间：'+asqResult.modifyTime.replace('T',' ')+'</p>' +
                            '</div><p class="gray_1"></p>';
                        $(".results_div").html(html);
                    }
                    $(".s_btn").click(function(){
                        var txt = $(this).parents(".results_information").find(".s_tit").text().substring(0,5);
                        var id = $(this).parents(".results_item").data("id");
                        if(txt == "ASQ-3"){
                            link_to("evaluate_fd_qResult_allInfomation.html?"+id);
                        }else{
                            link_to("evaluate_fd_qResult_seInfomation.html?"+id);
                        }
                    })
                }else{
                    no_data("暂无评测结果");
                }
            } else {
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
})