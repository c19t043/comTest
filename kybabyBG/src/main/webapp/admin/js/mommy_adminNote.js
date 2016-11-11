/**
 * Created by lijingwei on 2016/5/10.
 */
var ringHost="../ring/";
$(function(){
    $('#table_title').append(
        "<tr>"+
        "<th style='width:10%'>序列</th>"+
        "<th style='width:15%'>标题</th>"+
        "<th style='width:12.5%'>所属分类</th>"+
        "<th style='width:12.5%'>发布者</th>"+
        "<th style='width:12.5%'>发布时间</th>"+
        "<th style='width:12.5%'>允许回复</th>"+
        "<th style='width:12.5%'>状态</th>"+
        "<th style='width:12.5%'>"+
        "<div class='th_button' onclick='add_info()'>添加</div>"+
        "</th>"+
        "</tr>"
    );
    getAllRingCategory();
    $('#firstLev').change(function(){
        var zjid = $(this).find('option').filter(function(){
            return $(this).get(0).selected == true;
        }).attr('zjid');
        getSomeCategory(zjid);
    });
});
function add_info(){
    $('#note_title,#noteContent').val('');
    $('input:checkbox').each(function(){
        $(this).get(0).checked = false;
    });
    $('#noteContent').html('');
    $('#button_update_click').hide();
    $('#button_add_click').show();
    $('#bottom_div').slideDown(500);

}
$(function(){
    hf_ueditor('node_edit','noteContent');//用户协议
    getAllMommyPostInfo();
    getAllDoctorRingLabel();
});
//加载圈子类别
function getAllRingCategory(){
    $.ajax({
        type:'post',
        url:ringHost+'getDoctorRringInfo.action',
        cache:false,
        async:true,
        data:{action:"allCategory"},
        success:function(result){
            if(result.mes != '操作成功' || result.allCategory == null){
                return false;
            }
            $('#firstLev').empty();

            for(var i=0;i<result.allCategory.length;i++){
                $('#firstLev').append(
                    "<option zjid='"+result.allCategory[i][0]+"'>"+result.allCategory[i][1]+"</option>"
                );
            }
            getSomeCategory(result.allCategory[0][0]);
        }
    });
}
//加载二级类别
function getSomeCategory(zjid){
    $.ajax({
        type:'post',
        url:ringHost+'getDoctorRringInfo.action',
        cache:false,
        async:false,
        data:{action:"someCategory",someCategoryId:zjid},
        success:function(result){
            $('#secondLev').empty();
            if(result.mes != '操作成功' || result.someDoctorRingType == null){
                return false;
            }
            for(var i=0;i<result.someDoctorRingType.length;i++){
                $('#secondLev').append(
                    "<option zjid='"+result.someDoctorRingType[i][0]+"'>"+result.someDoctorRingType[i][1]+"</option>"
                );
            }
        }
    });
}
//添加新的帖子
function addNewMommyPostInfo(){
    var mommyPostInfoTitle=$.trim($("#note_title").val());
    var mommyPostInfoContent=$.trim($("#noteContent").html());

    var ringTypeId=$('#secondLev').find('option').filter(function(){
        return $(this).get(0).selected == true;
    }).attr('zjid');

    var isAllowReply=$.trim($("#isalowRes").val());
    var isTop=$.trim($("#isTop").val());
    var mommyPostInfoStatus=$.trim($("#state").val());
    var	doctorRingLabelStr ='';
    $('.basic_d input:checked').each(function(i){
        doctorRingLabelStr = doctorRingLabelStr +"::"+ $(this).attr('zjid');
    });
    doctorRingLabelStr = doctorRingLabelStr.substring(2);
    if(mommyPostInfoTitle == ''){
        $("#note_title").focus();
        return false;
    }else if(mommyPostInfoContent == ''){
        alert('内容不得为空');
        return false;
    }else if(ringTypeId == undefined){
        alert('请选择分类');
        return false;
    }else if(doctorRingLabelStr == ''){
        alert('请选择标签');
        return false;
    }
    $.ajax({
        type:'post',
        url:ringHost+'doctorRringManage.action',
        cache:false,
        async:true,
        data:{
            action:"addNewMommyPostInfo",
            mommyPostInfoTitle:mommyPostInfoTitle,
            mommyPostInfoContent:mommyPostInfoContent,
            ringTypeId:ringTypeId,
            isAllowReply:isAllowReply,
            isTop:isTop,
            mommyPostInfoStatus:mommyPostInfoStatus,
            doctorRingLabelStr:doctorRingLabelStr
        },
        success:function(result){
            if(result.mes="操作成功"){
                alert('发布成功');
                getAllMommyPostInfo();
                $('#bottom_div').slideUp(500);
            }
        }
    });

}
//获取所有的帖子
function getAllMommyPostInfo(){
    $.ajax({
        type:'post',
        url:ringHost+'getDoctorRringInfo.action',
        cache:false,
        async:true,
        data:{action:"allPostInfo"},
        success:function(result){
            if(result.mes != '操作成功' || result.allPostInfo == null){
                return false;
            }
            $('#table_content').empty();
            for(var i=0;i < result.allPostInfo.length;i++){
                $('#table_content').append(
                    "<tr onclick='click_light(this)'>"+
                    "<td style='width:10%'>"+(i+1)+"</td>"+
                    "<td style='width:15%'>"+(result.allPostInfo[i])[1]+"</td>"+
                    "<td style='width:12.5%'>"+(result.allPostInfo[i])[3]+"</td>"+
                    "<td style='width:12.5%'>"+(result.allPostInfo[i])[4]+"</td>"+
                    "<td style='width:12.5%'>"+(result.allPostInfo[i])[5]+"</td>"+
                    "<td style='width:12.5%'>"+(result.allPostInfo[i])[6]+"</td>"+
                    "<td style='width:12.5%'>"+(result.allPostInfo[i])[7]+"</td>"+
                    "<td style='width:12.5%'>"+
                    "<div"+
                    " data_id='"+(result.allPostInfo[i])[0]+"' "+
                    " data_title='"+(result.allPostInfo[i])[1]+"' "+
                    " data_firstLevId='"+(result.allPostInfo[i])[10]+"' "+
                    " data_firstLevName='"+(result.allPostInfo[i])[11]+"' "+
                    " data_secondLevId='"+(result.allPostInfo[i])[2]+"' "+
                    " data_secondLevName='"+(result.allPostInfo[i])[3]+"' "+
                    " data_author='"+(result.allPostInfo[i])[4]+"' "+
                    " data_time='"+(result.allPostInfo[i])[5]+"' "+
                    " data_isResponse='"+(result.allPostInfo[i])[6]+"' "+
                    " data_state='"+(result.allPostInfo[i])[7]+"' "+
                    " data_isTop='"+(result.allPostInfo[i])[8]+"' "+
                    " data_content='"+(result.allPostInfo[i])[9]+"' "+
                    " class='td_change_button' onclick='read_info(this)'>修改</div>"+
                    "</td>"+
                    "</tr>"
                );
            }
            reset_con_page();
        }
    });
}
function read_info(obj){
    $('#note_title').val($(obj).attr('data_title')).attr('zjid',$(obj).attr('data_id'));
    $('#noteContent').html($(obj).attr('data_content'));
    $('#firstLev option').filter(function(){
        return $(this).text() == $(obj).attr('data_firstLevName');
    }).get(0).selected = true;
    var searchId = $('#firstLev option').filter(function(){
        return $(this).get(0).selected == true;
    }).attr('zjid');
    getSomeCategory(searchId);
    $('#secondLev option').filter(function(){
        return $(this).text() == $(obj).attr('data_secondLevName');
    }).get(0).selected = true;
    $('#isalowRes option').filter(function(){
        return $(this).text() == $(obj).attr('data_isResponse');
    }).get(0).selected = true;
    $('#isTop option').filter(function(){
        return $(this).text() == $(obj).attr('data_isTop');
    }).get(0).selected = true;
    $('#state option').filter(function(){
        return $(this).text() == $(obj).attr('data_state');
    }).get(0).selected = true;
    $('.basic_d input').each(function(){
        $(this).get(0).checked = false;
    });
    getSomeMommyPostInfoLabelIdList($(obj).attr('data_id'));
    $('#button_update_click').show();
    $('#button_add_click').hide();
    $('#bottom_div').slideDown(500);
}
function changeMommyPostInfoInstance(){

    var mommyPostInfoId = $("#note_title").attr('zjid');
    var mommyPostInfoTitle=$.trim($("#note_title").val());
    var mommyPostInfoContent=$.trim($("#noteContent").html());
    var ringTypeId=$('#secondLev').find('option').filter(function(){
        return $(this).get(0).selected == true;
    }).attr('zjid');
    var isAllowReply=$.trim($("#isalowRes").val());
    var isTop=$.trim($("#isTop").val());
    var mommyPostInfoStatus=$.trim($("#state").val());
    var	doctorRingLabelStr ='';
    $('.basic_d input:checked').each(function(i){
        doctorRingLabelStr = doctorRingLabelStr +"::"+ $(this).attr('zjid');
    });
    doctorRingLabelStr = doctorRingLabelStr.substring(2);
    if(mommyPostInfoTitle == ''){
        $("#note_title").focus();
        return false;
    }else if(mommyPostInfoContent == ''){
        alert('内容不得为空');
        return false;
    }else if(ringTypeId == undefined){
        alert('请选择分类');
        return false;
    }else if(doctorRingLabelStr == ''){
        alert('请选择标签');
        return false;
    }

    $.ajax({
        type:'post',
        url:ringHost+'doctorRringManage.action',
        cache:false,
        async:true,
        data:{
            action:"updateMommyPostInfo",
            mommyPostInfoId:mommyPostInfoId,
            mommyPostInfoTitle:mommyPostInfoTitle,
            mommyPostInfoContent:mommyPostInfoContent,
            ringTypeId:ringTypeId,
            isAllowReply:isAllowReply,
            isTop:isTop,
            mommyPostInfoStatus:mommyPostInfoStatus,
            doctorRingLabelStr:doctorRingLabelStr
        },
        success:function(result){
            if(result.mes == '操作成功'){
                alert('修改成功');
                getAllMommyPostInfo();
                $('#bottom_div').slideUp(500);
            }
        }
    });
}
//加载标签
function getAllDoctorRingLabel(){
    $.ajax({
        type:'post',
        url:ringHost+'getDoctorRringInfo.action',
        cache:false,
        async:true,
        data:{
            action:"allDoctorRingLabelInfo",
            ringCategory:"Y"
        },
        success:function(result){
            if(result.mes != '操作成功' || result.someCategoryRingLabelObject == null){
                return false;
            }
            $('.basic_d').empty();

            $.each(result.someCategoryRingLabelObject,function(i,content){
                if(content[2] == 'Y'){
                    $('.basic_d').append(
                        "<p>"+
                        "<label for='tag_"+(i+1)+"'>"+content[1]+"</label>"+
                        "<input zjid='"+content[0]+"' id='tag_"+(i+1)+"' type='checkbox'>"+
                        "</p>"
                    );
                }


            });
        }
    });
}
//获取帖子的标签
function getSomeMommyPostInfoLabelIdList(mommyPostInfoLabelId){

    $.ajax({
        type:'post',
        url:ringHost+'getDoctorRringInfo.action',
        cache:false,
        async:false,
        data:{action:"getSomeMommyPostInfoLabelIdList",mommyPostInfoLabelId:mommyPostInfoLabelId},
        success:function(result){
            if(result.mes != '操作成功' || result.someMommyPostInfoLabelIdList == null){
                return false;
            }
            $.each(result.someMommyPostInfoLabelIdList,function(i,content){
                $('.basic_d input').filter(function(){
                    return $(this).attr('zjid') == content;
                }).get(0).checked = true;
            });
        }
    });
}