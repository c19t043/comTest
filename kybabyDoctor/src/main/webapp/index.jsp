<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My WebSocket</title>
</head>

<body>
	Welcome<br/>
	NickName:<input type='text' name='nickName' value='admin' id='nickName'>
	<br />
	<input id="text" type="text" />
	<button onclick="send();">Send</button>
	<button id='close' onclick="closeWebSocket();">Close</button>
	
	<div id="message" style="height: 250px;width: 280px;border: 1px solid; overflow: auto;"></div>
</body>


<script type="text/javascript">
      var websocket = null;
      var nickName = document.getElementById('nickName').value;
      
      //判断当前浏览器是否支持WebSocket
      if('WebSocket' in window){
          websocket = new WebSocket("ws://192.168.1.118:8088/kybabyDoctor/websocket");
      }
      else{
          alert('Not support websocket')
      }
     
      websocket.onerror = WSonError;
       
      websocket.onopen = WSonOpen;
       
      websocket.onmessage = WSonMessage;
       
      websocket.onclose = WSonClose;  
      
      window.onbeforeunload = WSonBeforeUnload;
      
      function setMessageInnerHTML(innerHTML){
          document.getElementById('message').innerHTML += innerHTML + '<br/>';
      }
      function WSonOpen(){
    	  setMessageInnerHTML("成功进入聊天室");
      }
      function WSonError(){
    	  setMessageInnerHTML("发生错误");
      }
      function WSonMessage(event){
    	  setMessageInnerHTML(event.data);
      }
      function WSonClose(){
    	  setMessageInnerHTML("退出聊天室");
      }
      
      function closeWebSocket(){
    	  websocket.close();
      }
      function WSonBeforeUnload(){
    	  websocket.close();
      }
      function send(){
    	  var NowNickName = document.getElementById('nickName').value;
          var message = document.getElementById('text').value;
          websocket.send(NowNickName + ' 说:' +message);
      }
      
  </script>
</html>