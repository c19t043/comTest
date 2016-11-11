<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">

<!-- safari full screen -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta id="apple-mobile-web-app-title" name="apple-mobile-web-app-title"
	content="管理员">

<!-- chrome full screen -->
<meta name="mobile-web-app-capable" content="yes">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="../js/jquery-2.1.3.js"></script>
<title>支付宝中转页面</title>
</head>
<body
	style="width:100%;margin:0px;padding:0px;background-color:#fafafa;">
	<div  >
		<p>跳转到支付宝的提示页面</p>
	</div>

	<form name="alipayment" action="../alipay/alipayapi.jsp" method="post"
		id="alipayment">
		<input type="hidden" id="WIDseller_email" name="WIDseller_email">
		<input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no">
		<input type="hidden" id="WIDsubject" name="WIDsubject">
		<input type="hidden" id="WIDtotal_fee" name="WIDtotal_fee">
	</form>

	<script>
		//提交到支付宝异步通知页面
		var ua = window.navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) != 'micromessenger') {
			arrHref = decodeURIComponent(window.location.search.substring(1)).split("&");
			WIDseller_email = arrHref[0].split("=")[1];
			WIDout_trade_no = arrHref[1].split("=")[1];
			WIDsubject = arrHref[2].split("=")[1];
			WIDtotal_fee = arrHref[3].split("=")[1];

			$("#WIDseller_email").val(WIDseller_email);
			$("#WIDout_trade_no").val(WIDout_trade_no);
			$("#WIDsubject").val(WIDsubject);
			$("#WIDtotal_fee").val(WIDtotal_fee);

			$("#alipayment").submit();
		}
	</script>

</body>
</html>