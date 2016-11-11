/**
 * Created by lijingwei on 2016/5/4.
 */
/**
 * Created by windows on 2016/1/12.
 */
var categoryId=window.location.search.substring(1).split('&&')[0];
var typename = decodeURIComponent(window.location.search.substring(1).split('&&')[1]);
var backWay = decodeURIComponent(window.location.search.substring(1).split('&&')[2]);
var categoryType='';
var sendHtml='';
var replyHtml='';
categoryType=decodeURIComponent(window.location.search.substring(1).split('&&')[0].trim());
$(function () {
    if(categoryType=='回'){
        tieziList('回','m',true);
    }
    else if(categoryType=='发'){
        tieziList('发','n',true);
    }
    else{
        tieziList('所有帖子','hh',true);
    }
});
function tieziList(name,div,isStack) {
    $('#myTieziList>div').removeClass('activeColor');
    $(div).addClass('activeColor');
    if(div=='hh'){
        $('#myTieziList>div').eq(0).addClass('activeColor');
    }else if(div=='n'){
        $('#myTieziList>div').eq(1).addClass('activeColor');
    }else if(div=='m'){
        $('#myTieziList>div').eq(2).addClass('activeColor');
    }
    var arrHistoryList = [];
    $("body").load(name,function(){
        if(isStack){
            arrHistoryList.push(window.location.href);
        }
        window.history.pushState({},"","myPost.html?"+name);
        arrHistoryList.pop();
    });
    if(name=='所有帖子'){
        replyHtml='';
        sendHtml='';
        getSendTiezi();
        getReplyTiezi();
        $('#article_list').html(sendHtml+replyHtml);
    }
    else if(name=='发'){
        sendHtml='';
        getSendTiezi();
        $('#article_list').html(sendHtml);
    }else if(name=='回'){
        replyHtml='';
        getReplyTiezi();
        $('#article_list').html(replyHtml);
    }
}
function getSendTiezi(){
    $.ajax({
        type : 'post',
        async: false ,
        cache:false,
        url : ringHost+'getDoctorRringInfo.action',
        data:{action:'mySendPostInfo'},
        success:function(result){
            var list = result.mySendSomePostInfo;
            $(list).each(function (index) {
                var thisMessage=$(list)[index];
                sendHtml +="<div class='article_con' onclick=\"goChatSignal('"+thisMessage[0]+"')\" zjid='"+thisMessage[0]+"'>"+
                    "<div class='con_row1'>"+
                    "<h3><span class='yellow'>发</span>"+thisMessage[1]+"</h3>"+
                    "</div>"+
                    "<div class='con_row2'>"+
                    "<span class='row2_chat'>"+thisMessage[5]+"</span>"+
                    "<span class='row2_view'>"+thisMessage[4]+"</span>"+
                    "<span class='row2_user'>"+thisMessage[2]+"</span>"+
                    "<span class='row2_time'>"+thisMessage[3]+"</span>"+
                    "</div>"+
                    "</div>"+
                    "<p class='gra'></p>";
            });
        },
        error:function(){
            alert('you are false');
        }
    });
}
function getReplyTiezi(){
    $.ajax({
        type : 'post',
        async: false ,
        cache:false,
        url : ringHost+'getDoctorRringInfo.action',
        data:{action:'myReplyPostInfo'},
        success:function(result){
            var list = result.myReplySomePostInfo;
            $(list).each(function (index) {
                var thisMessage=$(list)[index];
                replyHtml +="<div class='article_con' onclick=\"goChatSignal('"+thisMessage[0]+"')\" zjid='"+thisMessage[0]+"'>"+
                    "<div class='con_row1'>"+
                    "<h3><span class='gray'>回</span>"+thisMessage[1]+"</h3>"+
                    "</div>"+
                    "<div class='con_row2'>"+
                    "<span class='row2_chat'>"+thisMessage[5]+"</span>"+
                    "<span class='row2_view'>"+thisMessage[4]+"</span>"+
                    "<span class='row2_user'>"+thisMessage[2]+"</span>"+
                    "<span class='row2_time'>"+thisMessage[3]+"</span>"+
                    "</div>"+
                    "</div>"+
                    "<p class='gra'></p>";
            });
        },
        error:function(){
            alert('you are false');
        }
    });
}
function goChatSignal(zjid){
    window.location.href = "chatSignal.html?id="+zjid;
}
$(function(){
    $('#header').find('.header-left').click(function(){
        window.location.href='chatCircle.html';
    });
});