<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<html>
	<head>
		<title>家庭开户信息添加页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css/js -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>
		
		<!-- ecTable列表 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>
		
		<!-- environment弹出窗口样式 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>
		
		<!-- formcheck表单验证 -->
		<link href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>
		
		<!-- alert消息提示 -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>
		
		<!-- attachment组件  -->
		<link href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/css/attachment.css" rel="stylesheet"/>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/js/attachment.js"></script>
		
		<!-- util 工具 js -->
		<!--<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils2.js"></script>-->
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
  		
  		<script type="text/javascript">
  			var $j = jQuery.noConflict(); 
			function doHandle() {
			
				var pass = validateData();
				
				if(pass){
					$('save').click();
				}
			}
			
			function doSubmit(){
				
				$j("#isSubmit").val("true");
				
				doHandle();
			}
			
			function doPackageBabyInfo(){
				var babyNames = $j("#babyName_1").val();
				var babySexs = $j("#babySex_1").val();
				var babyBirthdays = $j("#babyBirthday_1").val();
				
				var count = 2;
				while($j(".baby_"+count).length>0){
					babyNames += ","+$j("#babyName_"+count).val();
					babySexs += ","+$j("#babySex_"+count).val();
					babyBirthdays += ","+$j("#babyBirthday_"+count).val();
					count++;
				}
				
				$j("#babyNames").val(babyNames);
				$j("#babySexs").val(babySexs);
				$j("#babyBirthdays").val(babyBirthdays);
			}
			
			function validateData(){
				
				var exitBabyInfo = $j(".baby_info").length;
				if(exitBabyInfo){
					var isEmpty = false;
					$j(".baby_info").each(function(){
						var val = $j(this).val();
						if(val&&/^[ ]+$/.test(val)){
							alert("添加的宝宝信息不能为空");
							isEmpty = true;
							return false;
						}
					});
					if(isEmpty){
						return false;
					}
				}
				
				/*
				*	提交表单前，验证注册信息是否完整
				*/
				var fatherName =  $j(":input[name='familyAccountInfo.fatherName']").val();
				var motherName =  $j(":input[name='familyAccountInfo.motherName']").val();
				if(!fatherName&&!motherName){
					alert("双亲姓名不能都为空");
					return false;
				}
				var fatherPhone =  $j(":input[name='familyAccountInfo.fatherPhone']").val();
				var motherPhone =  $j(":input[name='familyAccountInfo.motherPhone']").val();
				if(!fatherName&&!motherName){
					alert("双亲的电话号码不能都为空");
					return false;
				}
				var registPhone = $j("[name='familyAccountInfo.registPhone']:checked").val();
				if(!registPhone){
					alert("请选择注册账号");
					return false;
				}else{
					if("1"==registPhone){
						if(!fatherPhone) {
							alert("选择父亲电话为默认账号,父亲电话不能为空");
							return;
						}
						$j("[name='familyAccountInfo.registPhone']:checked").val(fatherPhone);
						$j("#parentName").val(fatherName);
					}
					if("2"==registPhone){
						if(!motherPhone) {
							alert("选择母亲电话为默认账号,母亲电话不能为空");
							return;
						}
						$j("[name='familyAccountInfo.registPhone']:checked").val(motherPhone);
						$j("#parentName").val(motherName);
					}
				}
				return true;
			}
			//返回
			function doBack(){
				window.location.href = "${pageContext.request.contextPath}/publichealth/familyaccount/toList.action";
			}
			/* jQuery(function(){
				var babyNames = $j("#babyNames").val();
				var babySexs = $j("#babySexs").val();
				var babyBirthdays = $j("#babyBirthdays").val();
				
				if(babyNames){
					if(babyNames.indexOf(",")==-1){
						$j("#babyName_1").val(babyNames);
						$j("#babySex_1").val(babySexs);
						$j("#babyBirthday_1").val(babyBirthdays);
					}else{
						var babyNamesArr = babyNames.split(",");
						var babySexsArr = babySexs.split(",");
						var babyBirthdaysArr = babyBirthdays.split(",");
						$j("#babyName_1").val(babyNamesArr[0]);
						$j("#babySex_1").val(babySexsArr[0]);
						$j("#babyBirthday_1").val(babyBirthdaysArr[0]);
						var len = babyNames.split(",").length;
						for(var i=2,y=1,len=babyNames.split(",").length+1;i<len;i++,y++){
							var table_content = getBabyInfoHTML(i,babyNamesArr[y],babySexsArr[y],babyBirthdaysArr[y]);
				  			$j("#tableForm").append(table_content);
				  			delBabyInfo(i);
						}
					}
				}
				
				$j("#addBabyInfo").click(function(){
					var count = 1;
					while($j(".baby_"+count).length>0){
						count++;
					}
					var table_content = getBabyInfoHTML(count,"","","");
		  			$j("#tableForm").append(table_content);
		  			delBabyInfo(count);
				});
			})
			function delBabyInfo(i){
				var _this = $j("#baby_"+i);
				_this.click(function(){
	  				var id = $j(this).attr("id");
	  				$j("."+id).remove();
	  			});
			}
			function getBabyInfoHTML(i,babyName,babySex,babyBirthday){
				var table_content=
					"<tr class='baby_"+i+"'>"
						+"<td class='inputLabel'>宝宝姓名"+i+":<span class='text4'>*</span></td>"
						+"<td width='30%' >"
							+"<input type='text' id='babyName_"+i+"' value='"+babyName+"'  class='validate['length[20]'] text3' width='70%'>"
						+"</td>"
						+"<td class='inputLabel'>宝宝性别"+i+":<span class='text4'>*</span></td>"
						+"<td width='30%' >"
							+"<input type='text' id='babySex_"+i+"' value='"+babySex+"' class='validate['length[20]'] text3' width='70%'>"
						+"</td>"
			  		+"</tr>"
				  	+"<tr class='baby_"+i+"'>"
				  		+"<td class='inputLabel'>宝宝生日"+i+":<span class='text4'>*</span></td>"
				  		+"<td width='30%' >"
				  			+"<input type='text' id='babyBirthday_"+i+"' value='"+babyBirthday+"' class='validate['length[20]'] text3' width='70%'>"
				  		+"</td>"
				  		+"<td class='inputLabel'>删除宝宝信息"+i+":</td>"
						+"<td width='30%' >"
							+"<input type='button' id='baby_"+i+"' value='删除宝宝信息' class='text3' width='70%' >"
						+"</td>"
		  			+"</tr>";
		  		return table_content;
			} */
  		</script>
  		<style type="text/css">
  			#baby_table_list{
  				border:1px solid #d1def1;
  				width:100%;
  			}
  			#baby_table_list th,td{
  				border:1px solid #abcdef;
  				font-size: 14px;
  				width:155px;height:35px;text-align: center;
  			}
  			#baby_table_list .input_complete,#baby_table_list input{
  				border:0px;font-size: 14px;text-align: center;width:150px;
  			}
  			#baby_table_list input[type='button']{
  				border: 1px solid #055DC1;background: white;width:130px;margin-right: 15px;
  			}
  			#baby_table_list .input_edit{
  				border:1px solid #055DC1;font-size: 14px;text-align: center;width:150px;
  			}
  		</style>
  	</head>
  	<body>
  		<s:actionmessage theme="popwind"/>
  		<div id="wz">
			<ap:step></ap:step>
			<div class="wz_right">
				<div class="but01">
					<div class="pop_button_bar">
						<span><a href="javascript:doHandle();" class="pop_button_blue">保存</a></span>
						<c:if test="${familyAccountInfo.id != null}">
							<span><a href="javascript:doSubmit();" class="pop_button_red">提交</a></span>
						</c:if>
						<span><a href="javascript:doBack();" class="pop_button_green">返回</a></span>
					</div>
				</div>
			</div>
		</div>
		<form id="form_handle" name="form_handle" action="<s:url namespace="/publichealth/familyaccount" action="saveOrUpdate" includeParams="true"/>" method="post" >
			<input name="save" id="save" type="submit" value="save" style="display: none;">
			<input type="hidden" name="familyAccountInfo.parentName" id="parentName"/> 
			<input type="hidden" name="familyAccountInfo.isSubmit" id="isSubmit"/> 
			<input type="hidden" name="familyAccountInfo.userInfo.id"  value="${familyAccountInfo.userInfo.id}">
			<input type="hidden" name="familyAccountInfo.id" type="hidden" value="${familyAccountInfo.id}">
			<input type="hidden" name="familyAccountInfo.babyNames"  id="babyNames" value="${familyAccountInfo.babyNames}">
			<input type="hidden" name="familyAccountInfo.babySexs"  id="babySexs" value="${familyAccountInfo.babySexs }">
			<input type="hidden" name="familyAccountInfo.babyBirthdays" id="babyBirthdays" value="${familyAccountInfo.babyBirthdays }">
			
			<div class="eXtremeTable">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left" valign="top">
							<img src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif" width="50" height="5">
						</td>
					</tr>
				</table>
					<table width="100%" class="tableRegion2" id="tableForm">
						<tr>
							<td class="inputLabel">父亲姓名:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.fatherName" value="${familyAccountInfo.fatherName }" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">母亲姓名：<span class="text4">*</span></td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.motherName" value="${familyAccountInfo.motherName }" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">所在省:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.province" value="${familyAccountInfo.province }" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">所在市：</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.city" value="${familyAccountInfo.city }" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  		  	<tr>
							<td class="inputLabel">所在区:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.area" value="${familyAccountInfo.area }" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">所在街道：</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.street" value="${familyAccountInfo.street }" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  		  	<tr>
							<td class="inputLabel">详细地址:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.address" value="${familyAccountInfo.address }" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">父亲联系电话：</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.fatherPhone" value="${familyAccountInfo.fatherPhone }" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
					  		<td class="inputLabel">建档单位：</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.establishUnit" value="${familyAccountInfo.establishUnit }" class="validate['length[20]'] text3" width="70%">
							</td>
					  		  	
							<td class="inputLabel">母亲联系电话:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.motherPhone" value="${familyAccountInfo.motherPhone }" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  		  	<tr>
							<td class="inputLabel">村(居)委会名称:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.committeeName" value="${familyAccountInfo.committeeName }" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">建档人：</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.establishHuman" value="${familyAccountInfo.establishHuman }" class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr>
							<td class="inputLabel">责任医生:</td>
							<td width="30%" >
								<input type="text" name="familyAccountInfo.responsibleDoctor" value="${familyAccountInfo.responsibleDoctor }" class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">是否使用 Y：是 N：否<span class="text4">*</span></td>
							<td width="30%">
								<s:select cssStyle="width:100px;" cssClass="text3" list="#{'Y':'Y','N':'N'}" listKey="key" listValue="value" 
									theme="simple" name="familyAccountInfo.isEnable"/>
							</td> 
					  	</tr>
					  	<tr>
							<td class="inputLabel">康优宝贝默认登陆账号选择:<span class="text4">*</span></td>
							<td width="30%">
								
								<input type="radio" 
								<c:if test="${familyAccountInfo.fatherPhone !=null && familyAccountInfo.fatherPhone == familyAccountInfo.userInfo.phone }">checked="checked"</c:if>
								name="familyAccountInfo.registPhone" value="1">父亲电话
								<input type="radio" 
								<c:if test="${familyAccountInfo.motherPhone !=null && familyAccountInfo.motherPhone == familyAccountInfo.userInfo.phone }">checked="checked"</c:if>
								name="familyAccountInfo.registPhone" value="2">母亲电话
							</td>
					  	</tr>
					  	<%-- <tr class="baby_1">
					  		<td class="inputLabel">宝宝姓名:<span class="text4">*</span></td>
							<td width="30%" >
								<input type="text" id="babyName_1"  class="validate['length[20]'] text3" width="70%">
							</td>
							
							<td class="inputLabel">宝宝性别:<span class="text4">*</span></td>
							<td width="30%" >
								<input type="text" id="babySex_1"  class="validate['length[20]'] text3" width="70%">
							</td>
					  	</tr>
					  	<tr class="baby_1">
					  		<td class="inputLabel">宝宝生日:<span class="text4">*</span></td>
							<td width="30%" >
								<input type="text" id="babyBirthday_1" class="validate['length[20]'] text3" width="70%">
							</td>

							<td class="inputLabel">多个宝宝（添加）:</td>
							<td width="30%" >
								<input type="button" id="addBabyInfo" value="增加宝宝" class="text3" width="70%">
							</td>
					  	</tr> --%>
					</table >
					<c:if test="${familyAccountInfo.id != null}">
						<table id="baby_table_list" width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr><th colspan="5">添加宝宝信息：</th></tr>
				    		<tr>
				    			<th>宝宝姓名</th>
				    			<th>宝宝性别</th>
				    			<th>宝宝生日</th>
				    			<th id="baby_op">操作</th>
				    			<th><input id="baby_add" type="button" value="增加宝宝" /></th>
				    		</tr>
			    		</table>
					</c:if>
				<br>
			</div>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
		<script type="text/javascript">
		
			/**  宝宝操作table,初始化  */
			jQuery(document).ready(function(){
				if($j("#baby_table_list").length>0){
					$j("#baby_op").attr("data_count",0);
					$j("#baby_add").bind("click",baby_table_add);
					baby_table_init();
				}
			});
			
			function baby_table_init(){
				<c:forEach items="${familyAccountInfo.consultBabyInfos }" var="v">
					baby_table_add('${v.id}','${v.babyName}','${v.sex}','${v.birthday}');
				</c:forEach>
			}
			
			/**  添加操作  */
			function baby_table_add(id,name,sex,birthday){
				var count = $j("#baby_op").attr("data_count");
				if(count==3) {
					alert("只能添加3个宝宝");
					return;
				}
				var tr_content = 
					"<tr>"
					+"<td><input type='text' class='baby_info' name='familyAccountInfo.consultBabyInfos["+count+"].babyName' value='"+(sex?name:"")+"' "
						+(name==""?"":"readonly='readonly'")
						+" /></td>"
					+"<td><input type='text' class='baby_info' name='familyAccountInfo.consultBabyInfos["+count+"].sex' value='"+(sex?sex:"")+"' "
						+(sex==""?"":"readonly='readonly'")
						+"/></td>"
					+"<td><input type='text' class='baby_birthday_input baby_info' name='familyAccountInfo.consultBabyInfos["+count+"].birthday' value='"+(sex?birthday:"")+"' "
						+(birthday==""?"":"readonly='readonly'")
						+"/></td>"
					+"<td colspan='2'>"
						+"<input type='hidden' name='familyAccountInfo.consultBabyInfos["+count+"].id' value='"+(sex?id:"")+"'/>"
						+"<input type='button' onclick='baby_table_edit_or_complete(this)' value='编辑'/>"
						+"<input type='button' onclick='baby_table_delete(this)' value='删除'/>"
					+"</tr>";
				$j("#baby_table_list").append(tr_content);
				/* $j(".baby_birthday_input").bind({
					focus:function(){
						WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});
					}
				}); */
				$j("#baby_op").attr("data_count",++count);
				
			}
			
			/**  删除  */
			function baby_table_delete(obj){
				var id = $j(obj).siblings("input[type='hidden']").val();
				if(id){
					alert("原有宝宝信息不能删除");
					return;
				}
				var is_ok_del = window.confirm("确认删除吗？");
				if(is_ok_del){
					$j(obj).parent().parent().remove();
				}
			}
			
			/**  编辑or完成  */
			function baby_table_edit_or_complete(obj){
				var a_text_content = $j(obj).val();
				if(a_text_content == '编辑'){
					baby_table_edit(obj);
				}else{
					baby_table_complete(obj);
				}
			}
			
			/**  编辑  */
			function baby_table_edit(obj){
				var tr = $j(obj).parent().parent();
				var input = tr.find("input:[type='text']");
				input.prop("readonly",false);
				input.removeClass("input_complete");
				input.addClass("input_edit");
				//input.attr("class","input_edit");
				var birthday = tr.find(".baby_birthday_input");
				birthday.bind({
					focus:function(){
						WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});
					}
				});
				$j(obj).val('完成');
			}
			/**  完成  */
			function baby_table_complete(obj){
				var tr = $j(obj).parent().parent();
				var input = tr.find("input:[type='text']");
				tr.find(".baby_birthday_input").unbind("focus");
				input.prop("readonly",true);
				input.removeClass("input_edit");
				input.addClass("input_complete");
				//input.attr("class","input_complete");
				$j(obj).val('编辑');
			}
		</script>
  	</body>
</html>
