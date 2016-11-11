/**
 * Created by lijingwei on 2016/9/13.
 */
function hf_loading(status){/*------加载动画,传值布尔flase，取消动画------*/
    if(status == false){
        document.getElementsByTagName('body')[0].removeChild(document.getElementById('hf_loading'));
        return false;
    }
    $('body').append(
        "<div id='hf_loading' style='z-index:1000;position:absolute;top:0px;left:0px;'>"+
        "<div id='hf_loading_box' style='width:120px;height:120px;text-align:center;position:fixed;'>"+
        "<img id='hf_loading_img' src='images/hf_autoplay.png' style='margin-bottom:10px;width:40px;height:40px;' />"+
        "<span id='hf_loading_word' style='display:block;font-size:11px;text-indent:22px;text-align:left;color:#ff813d;font-weight:bold;'>加载中。</span>"+
        "</div>"+
        "</div>");

    var divobj = document.getElementById('hf_loading');
    var boxobj = document.getElementById('hf_loading_box');
    var imgobj = document.getElementById('hf_loading_img');
    var spanobj = document.getElementById('hf_loading_word');
    var du = 0;

    //divobj.style.width = document.documentElement.scrollWidth + "px";
    //divobj.style.height = document.documentElement.scrollHeight + "px";
    divobj.style.width = $(document).width() + "px";
    divobj.style.height = $(document).height() + "px";

    boxobj.style.top = (document.documentElement.clientHeight/2 - boxobj.offsetHeight/2) + "px";
    boxobj.style.left = (document.documentElement.clientWidth/2 - boxobj.offsetWidth/2) + "px";

    function trans(){
        if(du == 360){
            du = 0;
        }else{
            du = du + 10;
        }
        imgobj.style.transform = "rotate("+du+"deg)";
        imgobj.style.WebkitTransform = "rotate("+du+"deg)";
    }
    function transword(){
        if(spanobj.innerHTML == '加载中。'){
            spanobj.innerHTML = '加载中。。';
        }else if(spanobj.innerHTML == '加载中。。'){
            spanobj.innerHTML = '加载中。。。';
        }else if(spanobj.innerHTML == '加载中。。。'){
            spanobj.innerHTML = '加载中。';
        }
    }
    setInterval(function(){
        transword();
    },300);
    setInterval(function(){
        trans();
    },20);
}