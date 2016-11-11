<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
	    
	<!-- safari full screen -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta id="apple-mobile-web-app-title" name="apple-mobile-web-app-title" content="管理员">
    
    <!-- chrome full screen -->
    <meta name="mobile-web-app-capable" content="yes">
    
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="js/MD5.js.js"></script>
	<script type="text/javascript" src="js/getServiceLHH.js"></script>
    <title>预约服务</title>
</head> 
<body>
	<div>
		<h2>检测用户是否登录</h2>
		<div>
			<span>医生的ID</span><input type="text" id="doctorId" /><br/>
			<span>产品的名称</span><input type="text" id="productName" /><br/>
			<input type="button" value="检测用户是否登录" onclick="checkSession()" />
		</div>
	</div>
	
	<div>
		<span>选择的商品ID：</span><input type="text" id="prodcutId" /><br/>
		<span>选择的服务日期：</span><input type="text" id="serviceDate" /><br/>
		<span>选择的服务时间段：</span><input type="text" id="serviceTime" /><br/>
		<input type="button" value="选择该服务时间" onclick="getServiceDoctorList()" />
	</div>
	
	<div>
		<span>选择的商品ID：</span><input type="text" id="choseProductId" /><br/>
		<input type="button" value="选择医生" onclick="choseSomeProdcutDoctor()" />
	</div>
	
	<div>
		<span>选择医生的ID:</span><input type="text" id="choseDoctorId" /><br/>
		<span>选择服务产品的ID：</span><input type="text" id="newChoseProductId" /><br/>
		<input type="button" value="获取服务日期和时间" onclick="getServiceDateList()" />
	</div>
	
	<div>
		<input type="button" value="服务时间" onclick="getService()" />
	</div>
	
	<div>
		<h2>选择优惠券</h2>
		<input type="button" value="选择优惠券" onclick="getCanbeUserCoupon()" />
	</div>
	
	<p>=============================================================</p>
	<div>
		<h2>订单确认之后，开始支付</h2>
		<span>选择的商品ID</span><input type="text" id="serviceProductId" /><br/>
		<span>选择的医生ID</span><input type="text" id="serviceDoctorId" /><br/>
		<span>预约的日期</span><input type="text" id="serviceDate2" /><br/>
		<span>预约的时间</span><input type="text" id="serviceTime2" /><br/>
		<span>使用优惠券的ID</span><input type="text" id="couponId" /><br/>
		<span>使用的积分数</span><input type="text" id="points" /><br/>
		<span>使用的余额数</span><input type="text" id="amount" /><br/>
		<span>使用的支付方式</span><input type="text" id="payMethod" /><br/>
		<input type="button" value="支付" onclick="payProduct()" />
	</div>
</body>
</html>