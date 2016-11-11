<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
	<title>在线支付</title>
 	<script src="../js/jquery-2.1.3.js"></script>
 	<script src="../js/MD5.js"></script>
 	<script>
 	//alert("${openId}");
 	if("${openId}"==""){
 	//取openId
 	    $.ajax({
 	         url:'configManage.action',
 	          data:{action:"getProperty",propertyName:"authUrl"}, 
		        cache:false,
		        async:false,           
		        success:function(result) {    
                   //s userOpenId=result.code;
                    //alert(JSON.stringify(result));
                    aHref=result.propertyValue;
                    //alert(aHref);
                    
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        } 
 	         
 	    });
 	    window.location.href=aHref;
 	    //window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fxinmeijiadev.yishangkeji.cn%2fxinmeijia%2fwx%2fuserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect";
 	    
 	}
 	</script>
</head>
<body>
<p>商品/订单 简要描述(不超过32位): <input type="text" id="orderDesc" value="123">
<p>订单号(不超过32位):<input type="text" id="orderNum" value="123">
<p>订单总金额:<input type="text" id="orderAmount" value="1">
<br>
<input type="button" value="微信支付" onclick="paycall($('#orderDesc').val(),$('#orderNum').val(),$('#orderAmount').val())">

<script>

  function getUserId() {
         //userOpenId="${userOpenId}";
         //userOpenId="onEEEsyJnQlSuhF8PyCNoe1KBSh4";
         //alert("openId:"+userOpenId);

      var userOpenId="";
      userOpenId="${openId}";
      
     /*
  		$.ajax({    
		        url:'userAuth.action', // 跳转到 action   
		        data:{action:"getCode"}, 
		        cache:false,
		        async:false,           
		        success:function(result) {    
                   //s userOpenId=result.code;
                    alert(JSON.stringify(result));
                    
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        }    
	        });
	        //alert(userOpenId);*/
	      /* 
	        alert("get user openId begin...");
	        $.ajax({
	            url:"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http%3a%2f%2fxinmeijiadev.yishangkeji.cn%2fxinmeijia%2fwx%2fuserAuth.action&response_type=code&scope=snsapi_base&state=gc#wechat_redirect",
	            //url:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx039ad537c0a70c1b&redirect_uri=http://xinmeijiadev.yishangkeji.cn/xinmeijia/wx/userAuth.action&response_type=code&scope=snsapi_userinfo&state=gc#wechat_redirect',
	            cache:false,
		        async:false,           
		        success:function(result) {    
                    alert(JSON.stringify(result));
                    
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        }    
	        }); */
	        
	        
	   return userOpenId; 
  }
     
  function getTimeStamp()
  	{
     	var timestamp=new Date().getTime();
        var timestampstring = timestamp.toString();//一定要转换字符串
        return timestampstring;
    }
     
  function getNonceStr()
    {
        var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        var maxPos = $chars.length;
        var noceStr = "";
        for (i = 0; i < 32; i++) {
        	noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return noceStr;
     }
     
  function getRemoteIp() {
        var remoteIp="";
   			$.ajax({    
		        url:'configManage.action', // 跳转到 action   
		        data:{action:"getRemoteIp"}, 
		        cache:false,
		        async:false,           
		        success:function(result) {    
                    remoteIp=result.ip;
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        }    
	        });
	    return remoteIp; 
   }
   
   function getAppId() {
        var appId="";
   			$.ajax({    
		        url:'configManage.action', // 跳转到 action   
		        data:{action:"getProperty", propertyName:"corpId"}, 
		        cache:false,
		        async:false,           
		        success:function(result) {   
                    appId=result.propertyValue;
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        }    
	        });
	    return appId; 
   }
   
   function getMchId() {
        var mchId="";
   			$.ajax({    
		        url:'configManage.action', // 跳转到 action   
		        data:{action:"getProperty", propertyName:"mchId"}, 
		        cache:false,
		        async:false,           
		        success:function(result) {    
                    mchId=result.propertyValue;
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        }    
	        });
	    return mchId; 
   }
   
   function getAPISecret() {
        var APISecret="";
   			$.ajax({    
		        url:'configManage.action', // 跳转到 action   
		        data:{action:"getProperty", propertyName:"APISecret"}, 
		        cache:false,
		        async:false,           
		        success:function(result) {    
                    APISecret=result.propertyValue;
		        },    
		        error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		        }    
	        });
	    return APISecret; 
   }
   
   var ip=getRemoteIp();
   var nonceStr=getNonceStr();
   var userId = getUserId();
   var appId = getAppId();
   var mchId = getMchId();
   var APISecret = getAPISecret();
     
  function paycall(orderDesc, orderNum, orderAmount) {
        if (typeof WeixinJSBridge == "undefined"){
               if( document.addEventListener ){
                   document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
               }else if (document.attachEvent){
                   document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
                   document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
               }
             }else{
                   onBridgeReady(orderDesc, orderNum, orderAmount);
             } 
    }
    
  function onBridgeReady(orderDesc, orderNum, orderAmount){
        var prepId = getPrePayId(orderDesc,orderNum,orderAmount);
        var timeStr = getTimeStamp(); 
        var nonStr = getNonceStr();      
        var signStringforPay="appId="+appId+"&nonceStr="+nonStr+"&package=prepay_id="+prepId+"&signType=MD5&timeStamp="+timeStr+"&key="+APISecret;    
        signStringforPay=MD5(signStringforPay); 
        signStringforPay=signStringforPay.toUpperCase();

        WeixinJSBridge.invoke(
           'getBrandWCPayRequest', {
               "appId":appId,     //公众号名称，由商户传入     
               "timeStamp":timeStr,         //时间戳，自1970年以来的秒数    
               "nonceStr":nonStr, //随机串     
               "package":"prepay_id=" + prepId,   
               "signType":"MD5",         //微信签名方式:     
               "paySign":signStringforPay  //微信签名 
           },
       function(res){     
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {          
               //alert("ok");
               window.location.href="notifyUrl.jsp?orderNum="+orderNum;
           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
       }
   ); }
   
   function getPrePayId(orderDesc,orderNum,orderAmount) {
   		var prepayId="";
   		var orderId=orderNum;
        
        var signString="appid="+ appId +"&body="+orderDesc+"&mch_id=" + mchId + "&nonce_str="+nonceStr+"&notify_url=http://xinmeijiadev.yishangkeji.cn/xinmeijia/wx/notifyUrl.jsp&openid="+userId+"&out_trade_no="+orderId+"&spbill_create_ip="+ip+"&total_fee="+orderAmount+"&trade_type=JSAPI&key="+APISecret;    
        signString=MD5(signString); 
        signString=signString.toUpperCase();
   		   		
   		$.ajax({    
		    url:'getPrepayId.action', // 跳转到 action 
		    data:{action:"getPrepayId",mchId:mchId,tradeNo:orderNum,remoteIp:ip,nonceStr:nonceStr,signStr:signString,userOpenId:userId,body:orderDesc,totalFee:orderAmount},   
		    cache:false, 
		    async:false,          
		    success:function(result) {   
		         resultXml=result.result;
                 prepayId= resultXml.substring(resultXml.indexOf("<prepay_id>")+20,resultXml.indexOf("</prepay_id>")-3);
		    },    
		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
		    }    
		}); 
        return prepayId;         
   }
</script>

</body>
</html>