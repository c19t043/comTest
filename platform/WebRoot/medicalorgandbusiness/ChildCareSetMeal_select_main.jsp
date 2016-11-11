<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>

		<!-- environment弹出窗口样式 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>

		<!-- ecTable列表 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>

		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/miftree/mif.tree-v1.1.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/miftree/mif_css/mif-tree.css" />
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		
		<script type="text/javascript">
		var $j = jQuery.noConflict(); 
			window.addEvent('domready', function(){
				new FormCheck('form_add',{
					display:{
						showErrors:1
					}
				});
			});
			
  		</script>
  		<style type="text/css">
  			td,input{
  				height:30px;
  				border:1
  			}
  		</style>
	</head>
	<base target="_self">
	<body id="tree_main">
		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="top"><img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5"></td>
			</tr>
		</table>
		<table width="80%" border="0" cellpadding="0" cellspacing="0" style="margin-left: 100px;font-size: 14;border:1px solid black">
			<tr><td align="center" colspan="3">订单详情</td></tr>
			<tr><td colspan="3">订单信息</td></tr>
			<tr>
				<td >订单编号：${userChildcareAppointmentInfo.orderNum }</td>
				<td >宝宝姓名：${userChildcareAppointmentInfo.userInfo.babyName }</td>
				<td >下单时间：${userChildcareAppointmentInfo.operationTime }</td>
			</tr>
			<tr>
				<td>门诊时间：${userChildcareAppointmentInfo.organChildcareOpenResources.openDate}</td>
				<td>预约医生：${userChildcareAppointmentInfo.doctorInfo.doctorName}</td>
				<td>金额：${userChildcareAppointmentInfo.totalPrice }</td>
			</tr>
			<tr><td colspan="3">宝宝月龄：${userChildcareAppointmentInfo.currentMonthAge }</td></tr>
			<tr>
				<td style="width:100%;height:50px" colspan="3"></td>
			</tr>
			<tr><td colspan="3">项目完成信息</td></tr>
			<tr>
				<td colspan="3">本次所做项目：
					<select style="width:100px" id="projectItems">
						<c:forEach items="${childcareProjectList}" var="p">
							<option value='${p.id }' data="${p.projectContent}">${p.projectTitle }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr >
				<td colspan="2" id="projectContent" style="width:250px">项目所做内容：${organSetChildCareRecode.childcareProject.projectContent }</td>
				<td>项目完成情况：<span class="text4">*</span>
					<input type="radio" name="orderStatus" value="Y" style="vertical-align: middle;">已完成
					<input type="radio" name="orderStatus" value="N" style="vertical-align: middle;">未完成
				</td>
			</tr>
			<tr>
				<td style="width:100%;height:50px;color: red;" colspan="3">如果项目未完成,请在备注中，添加说明:</td>
			</tr>
			<tr>
				<td colspan="3">
					<span>备注：</span>
					<input type="text" id="remark" style="width:90%;">
				</td>
			</tr>
			<tr>
				<td style="width:100%;height:50px" colspan="3"></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="button" id="ok" value="确认" style="width:50px;">
				</td>
			</tr>
		</table>
		<input type="hidden" id="detailId" value="${organSetChildCareRecode.id }"/>
	</body>
</html>
<script type="text/javascript">


	$j(function(){
		
		//项目下拉框改变事件
		$j("#projectItems").change(function(){
			$j("#projectContent").text("项目所做内容："+$j("#projectItems option:selected").attr("data"));
		});
		
		$j("#ok").click(function(){
			//1.获取下拉框选择的option,value值
			var projectId = $j("#projectItems option:selected").val();
			//2.获取订单明细id
			var detailId = $j("#detailId").val();
			//3.获取订单明细完成情况
			var isDoneAll = $j("[name=orderStatus]:checked").val();
			//4.获取备注内容
			var remark = $j("#remark").val();
			
			
			//验证
			if(!isDoneAll){
				alert("项目完成情况必须选择");
				return;
			}
			
			//5.修改订单明细数据
			$j.ajax({
				url:"${pageContext.request.contextPath}/medicalorgandbusiness/updateChildCareDetail.action",
				type:"POST",
				async:true,
				data:{
					"organSetChildCareRecode.id":detailId,
					"organSetChildCareRecode.remark":remark,
					"organSetChildCareRecode.isDoneAll":isDoneAll,
					"organSetChildCareRecode.childcareProject.id":projectId
				},
				success:function(result){
					//alert(result.message);
				}
			});
			
			environment.dialog.close();
		});
		
		//本次所做项目
		$j("#projectItems option").each(function(){
			if($j(this).val()==${organSetChildCareRecode.childcareProject.id}){
				$j(this).attr("selected","selected");
			}
		});
	});
	
	var object = function(ids,names) {
		this.id = ids;
		this.name = names;
	};

	var text = '';
	var hidden = '';
	function getTextName() { 
		return text;
	}
	function getHeddinId() {
		return hidden;
	}
	var objectList = new Array();
	window.addEvent('domready', function(){
		var flag = 0;
		var url = window.location.search;
		if(url.indexOf("?") != -1) {   
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(i=0; i<strs.length; i++) {   
				if([strs[i].split("=")[0]]=='text') {
					text = unescape(strs[i].split("=")[1]);
					flag++;
				}
				if([strs[i].split("=")[0]]=='hidden') {
					hidden = unescape(strs[i].split("=")[1]);
					flag++;
				}
				if(flag == 2) {
					break;
				}
			}   
		}
		var serviceItemIdsName = "";
		var serviceItemIds = "";
		if(serviceItemIds!=null&&serviceItemIds.length>0){
			$('cheakobject').innerHTML=serviceItemIdsName;
			var ids = serviceItemIds.split(",");
			var names = serviceItemIdsName.split(",");
			for(var i=0;i<ids.length;i++){
				if(ids[i]!=null&&ids[i].length>0){
					objectList.push(new object(ids[i],names[i]));
				}
			}
		}
		$('addObjectButton').addEvent("click", select);
		//$('objectIframe').src = "${pageContext.request.contextPath}/consultmanager/consultdoctormanager/getDoctorInfoList.action?positionName="+positionName;
	});
	
	function select(){
		var id="";
		for(var i=0;i<objectList.length;i++){
			id+=objectList[i].id+",";
		}
		id=id.substring(0, id.length-1);
		//保存分配人员
		confirmMsg("确定吗？",function(tp){
 			if(tp=='ok'){
 				$j.ajax({
 				   type: "POST",
 				   url: "${pageContext.request.contextPath}/consultmanager/consultdoctormanager/addDoctorInfos.action",
 				   data: {
 					  "doctorids":id,
 					  "commissionId":commissionId
 				   },
 				   success: function(msg){
 				     if(msg.message=="success"){
 				    	 environment.dialog.close();
 				     }else{
 				    	 alert("失败");
 				     }
 				   }
 				});
			}
		});
	}
	
	//从div把选中的移除
	function removeObject(name,id) {
		for(var i=0;i<objectList.length;i++){
			if(objectList[i].id==id){
			    objectList.splice(i,1); 
				break;
			}
		}
		var content = $j('#cheakobject').html().replace(name+",",'');
		$j('#cheakobject').html(content);
	}
	
	//向div里面插入数据
	function insertObject(id,name){
		objects = new object(id,name);
		for(var i=0;i<objectList.length;i++){
			if(objectList[i].id==id){
				removeObject(name,id);
				return;
			}
		}
		objectList.push(objects);
		var inner = $j('#cheakobject').html();
		$j('#cheakobject').html(inner+name+",");
	}
</script>