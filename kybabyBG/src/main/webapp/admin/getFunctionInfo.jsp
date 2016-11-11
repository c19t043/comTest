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
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function getAllFunctionListAndParent(){
			$.ajax({
				type:'post',
				url:'getFunctionInfo.action',
				data:{action:"getAllFunctionList"},
				success:function(result){
					if(result.mes=="操作成功"){
						for(var i=0;i<result.allFunctionParentList.length;i++){
							alert("第"+(i+1)+"个父类功能名字是："+result.allFunctionParentList[i].name);
							var functionList=result.allFunctionList[i];
							if(functionList!=null){
								for(var j=0;j<functionList.length;j++){
									alert("第"+(j+1)+"个子类功能名字是："+functionList[j].name);
								}
							}
						}
					}else if(result.mes=="无父类"){
						alert("还没有定义父类功能列表");
					}else if(result.mes=="无子类"){
						alert("还没有定义子类功能列表");
					}
				}
				
			});
		}
		
	</script>
	
    <title>功能列表</title>
</head> 
<body>
	<div>
		<h2>功能列表</h2>
		<input type="button" value="功能列表" onclick="getAllFunctionListAndParent()" />
	</div>
</body>
</html>