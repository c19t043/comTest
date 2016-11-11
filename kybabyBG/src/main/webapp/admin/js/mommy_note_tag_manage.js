/**
 * Created by lijingwei on 2016/5/10.
 */
var ringHost="../ring/";
$(function(){
    $('#table_title').append(
        "<tr>"+
        "<th style='width:20%'>序列</th>"+
        "<th style='width:20%'>名字</th>"+
        "<th style='width:20%'>点击数</th>"+
        "<th style='width:20%'>状态</th>"+
        "<th style='width:20%'>"+
        "<div class='th_button' onclick='add_info()'>添加</div>"+
        "</th>"+
        "</tr>"
    );

});
function add_info(){
    $('#bottom_div_con input').val('');
    $('#button_update_click,.con_b').hide();
    $('#button_add_click').show();
    $('#bottom_div').slideDown(500);

}
$(function(){
    getAllMommyDoctorRingLabel();
});

function read_info(obj){
    $('#tagName').val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));
    $('#tagState option').filter(function(){
        return $(this).text() == $(obj).attr('data_state');
    }).get(0).selected = true;
    $('#button_update_click,.con_b').show();
    $('#button_add_click').hide();
    $('#bottom_div').slideDown(500);
}
function addNewLabel(){
    var ringLabelName=$.trim($("#tagName").val());
    var ringLabelStatus=$.trim($("#tagState").val());
    if(ringLabelName == ''){
        $("#tagName").focus();
        return false;
    }
    $.ajax({
        type:'post',
        url:ringHost+'mommyDoctorRringManage.action',
        cache:false,
        async:true,
        data:{
            action:"addNewLabel",
            'ringCategory':"Y",
            'ringLabelName':ringLabelName,
            'ringLabelStatus':ringLabelStatus
        },
        success:function(result){
            if(result.mes == "操作成功"){
                alert('添加成功');
                $('#bottom_div').slideUp(500);
                getAllMommyDoctorRingLabel();
            }
        }
    });
}
function updateSomeLabel(){
    var ringLabelId=$.trim($("#tagName").attr('zjid'));
    var ringLabelName=$.trim($("#tagName").val());
    var ringLabelStatus=$.trim($("#tagState").val());
    if(ringLabelName == ''){
        $("#tagName").focus();
        return false;
    }
    $.ajax({
        type:'post',
        url:ringHost+'mommyDoctorRringManage.action',
        cache:false,
        async:true,
        data:{
            action:"updateSomeLabel",
            ringCategory:"Y",
            ringLabelId:ringLabelId,
            ringLabelName:ringLabelName,
            ringLabelStatus:ringLabelStatus
        },
        success:function(result){
            if(result.mes == '操作成功'){
                alert('修改成功');
                $('#bottom_div').slideUp(500);
                getAllMommyDoctorRingLabel();
            }
        }
    });
}
//获取所有妈妈圈的标签
//ringCategory:Y代表请求的妈妈圈
function getAllMommyDoctorRingLabel(){
    $.ajax({
        type:'post',
        url:ringHost+'getMommyDoctorRringInfo.action',
        cache:false,
        async:true,
        data:{
            action:"allMommyDoctorRingLabelInfo",
            ringCategory:"Y"
        },
        success:function(result){
            if(result.mes != '操作成功' || result.someCategoryRingLabelObject == null){
                return false;
            }
            $('#table_content').empty();

            $.each(result.someCategoryRingLabelObject,function(i,content){
                $('#table_content').append(
                    "<tr onclick='click_light(this)'>"+
                    "<td style='width:20%'>"+(i+1)+"</td>"+
                    "<td style='width:20%'>"+content[1]+"</td>"+
                    "<td style='width:20%'>"+content[3]+"</td>"+
                    "<td style='width:20%'>"+content[2]+"</td>"+
                    "<td style='width:20%'>"+
                    "<div class='td_change_button' "+
                    " data_id='"+content[0]+"' "+
                    " data_name='"+content[1]+"' "+
                    " data_state='"+content[2]+"' "+
                    " onclick='read_info(this)'>修改</div>"+
                    "</td>"+
                    "</tr>"
                );
            });
            reset_con_page();
        }
    });
}