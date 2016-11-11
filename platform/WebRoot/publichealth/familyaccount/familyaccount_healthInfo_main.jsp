<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>家庭开户信息</title>
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
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
		<script type="text/javascript">
			var $j = jQuery.noConflict(); 
			// 显示详情 
			function doDetail(id) {
				var action = '<s:url namespace="/familydoctor/fdRoleInfo" action="getFdRoleInfList" includeParams="true"/>';
				window.location.href=action + "?archivesInfo.id="+id;
			}
			// 新增信息 
			function doAdd(action) {
				window.location.href=action;
			}
			// 编辑信息 
			function doEdit(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					showMsg("至少选择一项！");
					return;
				}else if(items.length > 1){
					showMsg("只能选择一项进行编辑！");
					return;
				}
				var id = "";
				items.each(function(item){
					id = item.value;
				});
				window.location.href=action + "?familyAccountInfo.id="+id;
			}
			// 返回列表页面 
			function doBack(action) {
				
				action = "${pageContext.request.contextPath}/publichealth/familyaccount/toList.action";
				window.location.href=action;
			}
  		</script>
  		<style type="text/css">
  			#family_table{border:0px;width: 99%;height:550px;}
  			#family_module_tr,#family_fn_tr,#family_info{height:30px;}
  			#family_module_tr input,#family_fn_tr input{
  				height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#055DC1;color: white;
  			}
  			.family_tr_input_back{
  				height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#055DC1;color: white;
  			}
  			.family_tr_input_click{
  				height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#DB7E05;color: white;
  			}
  			#family_info span{
  				display:inline-block;border: 1px solid #abcdef;width:180px;height:25px;
  				text-align:center;line-height: 25px;font-size: 18px;font-weight: bold;
  			}
  		</style>
	</head>

	<body>
	 <div id="wz">
		<ap:step></ap:step>
		<div class="wz_right">
			<div class="but01">
				<span id="familyInfo"><span>
				<div class="pop_button_bar">
					<span><a href="javascript:doBack();" class="pop_button_green bt_re">返回</a></span>
				</div>
			</div>
		</div>
	</div>
<%-- 	<s:actionmessage theme="popwind"/>
	<ap:stepAndOperation></ap:stepAndOperation> --%>
	<table id="family_table" cellpadding="0" cellspacing="0">
		<tr id="family_info">
			<td>
				
			</td>
		</tr>
		<tr>
			<td style="border-bottom:1px solid black;width:100%;height:1px;"></td>
		</tr>
		<tr id="family_module_tr">
			<td>
				<input id="childDo" type="button" class="family_tr_input_back" value="儿童健康管理">
				<input id="motherDo" type="button" class="family_tr_input_back" value="母亲健康管理">
			</td>
		</tr>
		<tr id="family_fn_tr">
			<td>
				<!-- 儿童 -->
				<input type="button" id="bt_child_health" name="child" class="family_tr_input_back" value="健康档案">
				<input type="button" id="bt_child_physical" name="child" class="family_tr_input_back" value="健康体检">
				<input type="button" id="bt_child_visit" name="child" class="family_tr_input_back" value="新生儿随访">
				<!-- 母亲 -->
				<input type="button" id="bt_monther_health" name="mother" class="family_tr_input_back" value="健康档案">
				<input type="button" id="bt_monther_firstVisit" name="mother" class="family_tr_input_back" value="产前第一次随访">
				<input type="button" id="bt_monther_29PerVisit" name="mother" class="family_tr_input_back" value="产前2-9次随访">
				<input type="button" id="bt_monther_6DayVisit" name="mother" class="family_tr_input_back" value="产后6天随访">
				<input type="button" id="bt_monther_40DayVisit" name="mother" class="family_tr_input_back" value="产后40天随访">
			</td>
		</tr>
		<tr>
			<td><iframe id="iframe" style="height:100%;width:100%;"></iframe></td>
		</tr>
	</table>
	<input type="hidden" id="accountId" value="${accountId }"/>
	<input type="hidden" id="motherName" >
	<input type="hidden" id="motherPhone" >
	<input type="hidden" id="babyIds" >
	<input type="hidden" id="babyId" >
	<input type="hidden" id="babyName" >
	<input type="hidden" id="babySex" >
	<input type="hidden" id="babyBirthday" >
	<input type="hidden" id="page_id">
	<script type="text/javascript">
		$j(function(){
			
			$j("#family_module_tr input,#family_fn_tr input").click(function(){
				
				var id = $j(this).attr("id");
				$j("#page_id").val(id);
				
				id = bt_style_toggle(id,this);
				
				var childOrMun_flag = "mum";
				var src = "${pageContext.request.contextPath}";
				switch(id){
					//case "childDo":
					case "bt_child_health"://儿童健康体检
						childOrMun_flag = "child";
						src+="/residentsFile/child/phPeopleBasicInfoList.action";break; 
					//case "motherDo":
					case "bt_monther_health"://母亲健康体检
						src+="/residentsFile/child/phMumPeopleBasicInfoList.action";break; 
					case "bt_child_physical"://健康体检
						childOrMun_flag = "child";
						src+="/publichealth/childhealth/toPhChildHealthExaminationRecordList.action";break; 
            			break; 
					case "bt_child_visit"://新生儿随访
						childOrMun_flag = "child";
						src+="/publichealth/neonatalvisit/toList.action";break; 
					case "bt_monther_firstVisit"://产前第一次随访
						src+="/productionvisit/preFirst/toList.action";break; 
					case "bt_monther_29PerVisit"://产前2-9次随访
						src+="/productionvisit/moreFollow/toPhPrenatalFollowRecordAfterList.action";break;  
					case "bt_monther_6DayVisit"://产后6天随访
						src+="/productionvisit/postpartumVisit/toList.action";break; 
					case "bt_monther_40DayVisit"://产后40天随访
						src+="/productionvisit/postpartumVisit/toList.action";break; 
				}
				
				/* if(childOrMun_flag == "child"){
					var isOK = true;
					if(!$j("#babyId").val()){
						isOK = isUseBabyFunction();
					}
					if(!isOK) return;
				} */
				
				var accountId = $j("#accountId").val();
				
				src +=  "?accountId="+accountId+"&babyId="+babyId;
				if(id == "bt_monther_6DayVisit") src += "&phPostpartumFollowRecord.followUpOpportunity=0";
				if(id == "bt_monther_40DayVisit") src += "&phPostpartumFollowRecord.followUpOpportunity=1";
				if(src) $j("#iframe").attr("src",src);
			});
			
			$j("#childDo").click(function(){
				$j("[name='child']").show();
				$j("[name='mother']").hide();
			});
			$j("#motherDo").click(function(){
				$j("[name='mother']").show();
				$j("[name='child']").hide();
			});
			
			getFamilyInfo();
		});
		
		function bt_style_toggle(id,obj){
			if(id=="childDo"){
				$j("#family_module_tr input,#family_fn_tr input").attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#055DC1;color: white;");
				$j(obj).attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#DB7E05;color: white;");
				id="bt_child_health";
				$j("#bt_child_health").attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#DB7E05;color: white;");
			}
			if(id=="motherDo"){
				$j("#family_module_tr input,#family_fn_tr input").attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#055DC1;color: white;");
				$j(obj).attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#DB7E05;color: white;");
				id="bt_monther_health";
				$j("#bt_monther_health").attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#DB7E05;color: white;");
			}
			var style_name = $j(obj).attr("class");
			var style_index = style_name.indexOf('family_tr_input_back');
			if(id!="childDo"||id!="motherDo"){
				var name = $j(obj).attr("name");
				if(obj)
				$j("[name='"+name+"']").attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#055DC1;color: white;");
				$j(obj).toggleClass("family_tr_input_click");
				$j(obj).attr("style","height:25px;border:1px solid #abcdef;width:150px;text-align:center;background-color:#DB7E05;color: white;");
			}
			
			return id;
		}
		
		function isUseBabyFunction(){ 	
			
			var babyIds = $j("#babyIds").val();
			if(babyIds) {
				getBabyInfo();return true;
			}else{
				alert("请添加宝宝后,在添加记录");return false;
			}
			
		}
		
		function getFamilyInfo(){
			$j.ajax({
				url:"${pageContext.request.contextPath}/publichealth/familyaccount/getFamilyInfo.action",
				type:"POST",
				async:false,
				data:{
					"accountId":$j("#accountId").val()
				},
				success:function(result){
					if(!result.username){
						$j("#familyInfo").text("家庭信息获取失败");
					}else{
						//data = "<span style='margin-right:15px;'>父亲or母亲姓名:"+result.username+"</span><span style='margin-right:15px;'>父亲or母亲电话:"+result.phone+"</span>";
						$j("#babyIds").val(result.babyIds);
						$j("#motherName").val(result.motherName);
						$j("#motherPhone").val(result.motherPhone);
						//$j("#familyInfo").append(data);
						
						var showFamilyData = "";
						if(result.fatherName){
							showFamilyData+="<span>父亲姓名：</span><span>"+result.fatherName+"</span>";
							if(result.fatherPhone)
								showFamilyData+="<span>父亲电话：</span><span>"+result.fatherPhone+"</span>";
						}
						showFamilyData+="<span>母亲姓名：</span><span>"+result.motherName+"</span>";
						showFamilyData+="<span>母亲电话：</span><span>"+result.motherPhone+"</span>";
						$j("#family_info td").html(showFamilyData);
						
						
						$j("#childDo").click();
					}
				}
			});
		}
		
		function getBabyInfo(){
			
			popBabyChooseFrame();
			
			/*
	 		*根据babyIds判断是家庭开户中管理了宝宝的个数
	 		1.如果只有1个，获取宝宝信息，不用弹出框
	 		2.有多个宝宝，弹出选择框，选择宝宝
	 		*/
	 		/* if(babyIds.indexOf(",")==-1){
	 			getUniqueBabyInfo();
	 		}else{
	 			popBabyChooseFrame();
	 		} */
		}
		
	 	function getUniqueBabyInfo(){
	 		$j.ajax({
	 			url:"${pageContext.request.contextPath}/publichealth/familyaccount/getBabyInfo.action",
	 			type:"post",
	 			async:false,
	 			data:{
	 				"accountId":$j("#accountId").val()
	 			},
	 			success:function(result){
	 				$j("#babyId").val(result.id);
	 				$j("#babyName").val(result.babyName);
	 				$j("#babySex").val(result.sex);
	 				$j("#babyBirthday").val(result.birthday);
	 				
	 				$j("#toAdd",window.frames[0].document).click();
	 				//window.frames[0].toPage();
	 				
/* 	 				alert($j("#babyId").val());
	 				alert($j("#babyName").val());
	 				alert($j("#babySex").val());
	 				alert($j("#babyBirthday").val()); */
	 			}
	 		});
	 	}
	 	function popBabyChooseFrame(){
			//var page2page = "text=relName&hidden=mealID";  //把这个page2page传到另外一个页面
	 		environment.dialog.open({
				url : '${pageContext.request.contextPath}/publichealth/familyaccount/familyaccount_select_main.jsp?',
						//+ page2page, // 然后根据url地址去找action，在执行方法，跳转到第二个页面
				width : window.getCoordinates().width-300,
			    height : window.getCoordinates().height-50,
				icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
				title : '选择宝宝信息'
			});
	 	}
	</script>
	</body>
</html>
