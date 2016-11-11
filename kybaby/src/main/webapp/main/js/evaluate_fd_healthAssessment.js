/**
 * Created by lijingwei on 2016/10/26.
 */
$(function(){
    var package_id = window.location.search.substring(1).split("&")[0];
    var url_id = 'package='+package_id;
    $(".rightmeu").click(function(){
        link_to('evaluate_fd_evaluateResults.html?'+"package="+package_id);
    })
    $.ajax({
        type: 'post',
        url: testManager + 'asqAction.action',
        cache: false,
        async: true,
        data:  {
            action: "getParentTaotiList",
        },
        success: function (result) {
            if(result.mes=='请登录'){
                ale('请登录', '24px');
                link_to("login.html");
            }
            else if(result.mes=='成功'){
                if(result.taotiList!=null){
                    var html = '';
                    for(var i = 0;i<result.taotiList.length;i++){
                        var taotiList = result.taotiList[i];
                        html += '<li data-id="'+taotiList.id+'"><div><p class="p_img"><img src="'+photo+taotiList.imgPath+'" alt=""/></p>' +
                            '<p class="p_txt">'+taotiList.titalName+'</p></div></li>';
                    }
                    $('.evaluation_project>ul').html(html);
                    $(".evaluation_project li").click(function(){
                        var type = $(this).data("id");
                        link_to('evaluate_fd_assessment_information.html?'+url_id+"&typeId="+type);
                    })
                }
            }
            else{
                ale(result.mes);
            }
        },
        error: function () {
            layer();
        }
    });
})