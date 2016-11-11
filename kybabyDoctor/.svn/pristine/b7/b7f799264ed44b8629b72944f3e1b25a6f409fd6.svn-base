/**
 * Created by lijingwei on 2016/8/17.
 */
var message_id='';
$(function () {
    //$('#msgText').textareaAutoHeight({ minHeight:20, maxHeight:80});
});
$.fn.extend({
    textareaAutoHeight: function (options) {
        this._options = {
            minHeight: 0,
            maxHeight: 1000
        }

        this.init = function () {
            for (var p in options) {
                this._options[p] = options[p];
            }
            if (this._options.minHeight == 0) {
                this._options.minHeight=parseFloat($(this).height());
            }
            for (var p in this._options) {
                if ($(this).attr(p) == null) {
                    $(this).attr(p, this._options[p]);
                }
            }
            $(this).keyup(this.resetHeight).change(this.resetHeight)
                .focus(this.resetHeight);
        }
        this.resetHeight = function () {
            var _minHeight = parseFloat($(this).attr("minHeight"));
            var _maxHeight = parseFloat($(this).attr("maxHeight"));

            if (!$.support.msie) {
                $(this).height(0);
            }
            var h = parseFloat(this.scrollHeight);
            h = h < _minHeight ? _minHeight :
                h > _maxHeight ? _maxHeight : h;
            $(this).height(h).scrollTop(10000);
            if (h >= _maxHeight) {
                $(this).css("overflow-y", "scroll");
            }
            else {
                $(this).css("overflow-y", "hidden");
            }
        }
        this.init();
    }
});
//点击取消，取消修改/保存快捷回复
function cancel(){
    $("#reply_mask").hide();
    $("#mask_layer_content>div").html('');
}

//点击确定，保存新的快捷回复
function determine(){
    var txt = $("#mask_layer_content>div").html();
    if(txt.trim() == ''){
        ale("快捷回复内容不能为空！");
    }else if(txt.length>500){
        ale("快捷回复内容不能超过500个字符！");
    }
    else{
        console.log(txt);
        $.ajax({
            type: 'post',
            async: true,
            url: host + 'consultManage.action',
            data: {
                action: "saveOrUpdateConsultFastReplay",
                "consultFastReplay.id":message_id,
                "consultFastReplay.fastContent":txt,
                "consultFastReplay.isEffect":"Y"
            },
            success: function (result) {
                $("#reply_mask").hide();
                ale("保存成功");
                get_list();
                setTimeout(function(){
                    $("#mask_layer_content>div").html('');
                },100);
            }
        });
    }
}

//从会话页点击快捷回复按钮
function layer_according(){
    $("#reply_layer").show();
    get_list();
    other();
    $('#container').scrollTop(1000000);
    $(window).scrollTop($(document).height());
    $('#more').prop('class','focus');
    $('#msgText').blur();
    $('footer').css({bottom:'0'});
    $('#showBusiness').hide();
}

function other(){
    //从快捷回复列表页面返回对话页面
    $("#reply_layer .header-lefts").click(function(){
        $("#reply_layer").hide();
        $(".message_operation").hide();
        $('.message_content>p').css({"width":"100%"});
    });
    //点击添加，添加新的快捷回复
    $("#bottom .button_01").click(function(){
        $("#mask_layer_content>div").html('');
        $("#reply_mask").show();
        message_id = '';
    })
}
//发送
function bui(org){
    $("#reply_layer").hide();
    $('#msgText').html($(org).find("#fastContent").html());
}
//点击编辑，修改某条快捷回复快捷
function editor(obj,e){
    e.stopPropagation();
    $("#reply_mask").show();
    var txt = $(obj).parents(".message_operation").prev(".message_content").find("span").html();
    $("#mask_layer_content>div").html(txt);
    message_id  = $(obj).parents(".message_li").first(".message_content").data("id");
}

//删除某条快捷回复
function del(obj,e){
    e.stopPropagation();
    var answer=confirm('你确定要删除这条信息吗？');
    if(answer==false){
        return false;
    }
    //var txt = $("#mask_layer_content textarea").val();
    message_id  = $(obj).parents(".message_li").first(".message_content").data("id");
    $.ajax({
        type: 'post',
        async: true,
        url: host + 'consultManage.action',
        data: {
            action: "saveOrUpdateConsultFastReplay",
            "consultFastReplay.id":message_id,
            //"consultFastReplay.fastContent":txt,
            "consultFastReplay.isEffect":"N"
        },
        success: function (result) {
            $("#reply_mask").hide();
            ale("删除成功");
            $(obj).parents(".message_li").remove();
            get_list();
        }
    });
}

//加载快捷回复列表
function get_list(){
    $.ajax({
        type: 'post',
        async: true,
        url: host + 'consultManage.action',
        data: {
            action: "getConsultFastReplayList"
        },
        success: function (result) {
            var consultFastReplayList = result.consultFastReplayList;
            if(consultFastReplayList != null){
                $("#message_list").html('');
                var j = 0;
                var html = '';
                for(var i=0;i<consultFastReplayList.length;i++){
                    var replayList = consultFastReplayList[i];
                    j++;
                    html+=('<li class="message_li" data-id='+replayList.id+'>' +
                    '<div class="message_content" ontouchstart="gtouchstart(this)" ontouchmove="gtouchmove(this)" ontouchend="gtouchend(this)"><p>'+j+'' +
                    '.<span id="fastContent">'+replayList.fastContent+'</span>' +
                    '</p></div>' +
                    '<p class="message_operation">' +
                    '<span class="editor" onclick="editor(this,event)">编辑</span>' +
                    '<span>|</span>' +
                    '<span class="del" onclick="del(this,event)">删除</span>' +
                    '</p>' +
                    '</li>');
                }
                $("#message_list").html(html);
            }
        }
    });
}
var timeOutEvent=0;//定时器
//开始按
//alert(timeOutEvent);
function gtouchstart(ind){
    timeOutEvent = setTimeout("longPress()",500);//这里设置定时器，定义长按500毫秒触发长按事件，时间可以自己改，个人感觉500毫秒非常合适
    return false;
};
//手释放，如果在500毫秒内就释放，则取消长按事件，此时可以执行onclick应该执行的事件
function gtouchend(div){
    clearTimeout(timeOutEvent);//清除定时器
    return false;
};
//如果手指有移动，则取消所有事件，此时说明用户只是要移动而不是长按
function gtouchmove(){
    clearTimeout(timeOutEvent);//清除定时器
    timeOutEvent = 0;
};

//真正长按后应该执行的内容
function longPress() {
    timeOutEvent = 0;
//执行长按要执行的内容，如弹出菜单
    $(".message_operation").show();
    $('.message_content>p').css({"width":"70%"});
    //$(".message_li").removeAttr("onclick");
    //$(".message_li .message_content").click(function(){
    //    bui(this);
    //})
}