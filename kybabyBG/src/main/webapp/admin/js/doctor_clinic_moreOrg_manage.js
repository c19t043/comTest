var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:20%'>机构名称</th>"+
			"<th style='width:40%'>机构地址</th>"+
			"<th style='width:10%'>状态</th>"+
			"<th style='width:20%'>"+
				"<div class='th_button' onclick='add_info()'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getAllMoreOrgList'};
	getAllList(data);
	//加载机构列表
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:true, 
		data:{
			action:"getHospitalBasicInfoList"
		},
		success:function(result){
			for(var i=0;i < result.hospitalBasicInfoList.length;i++){
				var addressList = JSON.stringify((result.hospitalBasicInfoList[i]).hospitalAddressInfoSet);
				$('#hospitalId').append(
						"<option data_address="+(result.hospitalBasicInfoList[i]).hospitalLname+
						"("+(result.hospitalBasicInfoList[i]).address+")"+
						" data_address_list='"+addressList+
						"' value='"+(result.hospitalBasicInfoList[i]).id+"'>"+
						(result.hospitalBasicInfoList[i]).hospitalLname+"</option>"
				);
			}
		}
	});
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'doctorClinic.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.morePracticeOrgList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.morePracticeOrgList.length;i ++){
				var hospitalBasicInfo = (result.morePracticeOrgList[i]).hospitalBasicInfo;
				var hospitalId = hospitalBasicInfo==null?'':hospitalBasicInfo.id;
				var addressList = JSON.stringify(hospitalBasicInfo==null?'':hospitalBasicInfo.hospitalAddressInfoSet);
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:20%'>"+(result.morePracticeOrgList[i]).orgName+"</td>"+
					"<td style='width:40%'>"+(result.morePracticeOrgList[i]).orgAddress+"</td>"+
					"<td style='width:10%'>"+(result.morePracticeOrgList[i]).isAvailable+"</td>"+
					"<td style='width:20%'>"+
						"<div class='td_change_button' "+
							" orgId='"+(result.morePracticeOrgList[i]).id+"' "+
							" orgName='"+(result.morePracticeOrgList[i]).orgName+"' "+
							" orgAddress='"+(result.morePracticeOrgList[i]).orgAddress+"' "+
							" addressList='"+addressList+"' "+
							" isAvailable='"+(result.morePracticeOrgList[i]).isAvailable+"' "+
							" hospitalId='"+hospitalId+"' "+
						" onclick='read_info(this)'>修改</div>"+
					"</td>"+
				"</tr>"
				);
			}
			reset_con_page();	
		}
	});
}

//修改读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
   
    $('#bottom_div_con input').val('');
    
    $('#orgName').val($(obj).attr('orgName'));
	$('#orgId').val($(obj).attr('orgId'));
	$('#isAvailable option').filter(function(){
		return $(this).text() == $(obj).attr('isAvailable');
	}).get(0).selected = true;
	$('#hospitalId option').filter(function(){
		if( $(this).val() == $(obj).attr('hospitalId')){
			$(this).attr("selected",true);
		}
	});
	var addressList = $(obj).attr('addressList');
	addressList = JSON.parse(addressList);
	$("#orgAddress").empty();
	$("#orgAddress").append("<option>-请选择-");
	if(addressList.length == 0){
		var orgAddress = $(obj).attr('orgAddress');
		$("#orgAddress").append("<option value="+orgAddress+">"+orgAddress);
	}else{
		for(var i=0;i<addressList.length;i++){
			var item = addressList[i];
			var orgAddress = item.showName+"("+item.address+")";
			$("#orgAddress").append("<option value="+orgAddress+">"+orgAddress);
		};
	}
	$('#orgAddress option').filter(function(){
		if( $(this).val() == $(obj).attr('orgAddress')){
			$(this).attr("selected",true);
		}
	});
	
	//加载开放日期列表。时间段列表
	$('#node_Tbody').empty();
	$('#ampm_Tbody').empty();
	$.ajax({
		url:ringHost+'doctorClinic.action',
		cache: false,
		async: false,
		'type':'POST',
		'data':{
			'action':'getMoreOrgOpenInfo',
			'doctorMorePracticeOrgInfo.id':$(obj).attr('orgId')
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}
			//加载开放日期列表
			if(result.moreOrgClinicdateList != null){
				for(var i=0;i < result.moreOrgClinicdateList.length;i ++){
					$('#node_Tbody').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:10%'>"+(i+1)+"</td>" +
						"<td style='width:10%'>" +
							"<input type='hidden' name='moreOrgClinicdateList["+i+"].id' value='"+result.moreOrgClinicdateList[i].id+"' >"+
						"<input type='checkbox' /></td>"+
						"<td style='width:30%'><input class='query' type='text' name='moreOrgClinicdateList["+i+"].canClinicDate' value='"+result.moreOrgClinicdateList[i].canClinicDate+"'  onfocus='WdatePicker()'></td>"+
					"</tr>"
					);
				}
			}
			//加载开放时间段列表
			if(result.morePracticeOrgTimeList != null){
				for(var i=0;i < result.morePracticeOrgTimeList.length;i ++){
					$('#ampm_Tbody').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:10%'>"+(i+1)+"</td>" +
						"<td style='width:10%'>" +
							"<input type='hidden' name='morePracticeOrgTimeList["+i+"].id' value='"+result.morePracticeOrgTimeList[i].id+"' >"+
						"<input type='checkbox' /></td>"+
						"<td style='width:20%'>" +
							"<input type='hidden' value='"+result.morePracticeOrgTimeList[i].timeName+"' >" +
							"<select name='morePracticeOrgTimeList["+i+"].timeName'>" +
								"<option value='上午'>上午</option>" +
								"<option value='下午'>下午</option>" +
							"</select>" +
						"</td>"+
						"<td style='width:20%'>" +
							"<input type='hidden' value='"+result.morePracticeOrgTimeList[i].startTime+"' >"+
							"<select name='morePracticeOrgTimeList["+i+"].startTime'>" +
								"<option value='08:00'>08:00</option>" +
								"<option value='08:30'>08:30</option>" +
								"<option value='09:00'>09:00</option>" +
								"<option value='09:30'>09:30</option>" +
								"<option value='10:00'>10:00</option>" +
								"<option value='13:00'>13:00</option>" +
								"<option value='13:30'>13:30</option>" +
								"<option value='14:00'>14:00</option>" +
								"<option value='14:30'>14:30</option>" +
							"</select>" +
						"</td>"+
						"<td style='width:20%'>" +
							"<input type='hidden' value='"+result.morePracticeOrgTimeList[i].endTime+"' >"+
							"<select name='morePracticeOrgTimeList["+i+"].endTime'>" +
								"<option value='12:00'>12:00</option>" +
								"<option value='13:00'>13:00</option>" +
								"<option value='13:30'>13:30</option>" +
								"<option value='14:00'>14:00</option>" +
								"<option value='14:30'>14:30</option>" +
								"<option value='16:00'>16:00</option>" +
								"<option value='17:00'>17:00</option>" +
								"<option value='17:30'>17:30</option>" +
							"</select>" +
						"</td>"+
						"<td style='width:20%'><input class='query' type='text' name='morePracticeOrgTimeList["+i+"].requireClinicNum' value='"+result.morePracticeOrgTimeList[i].requireClinicNum+"' ></td>"+
					"</tr>"
					);
				}
			}
		}
	});
	//设置select默认选中
	$('#ampm_Tbody tr').each(function(){
		var oldVal_time = $(this).find('td:eq(2)').children('input').val();
		var oldVal_am = $(this).find('td:eq(3)').children('input').val();
		var oldVal_pm = $(this).find('td:eq(4)').children('input').val();
		$(this).find('td:eq(2)').children('select').find("option").each(function(){
			if(oldVal_time == $(this).val()){
				$(this).attr("selected",true);
			}
		});
		$(this).find('td:eq(3)').children('select').find("option").each(function(){
			if(oldVal_am == $(this).val()){
				$(this).attr("selected",true);
			}
		});
		$(this).find('td:eq(4)').children('select').find("option").each(function(){
			if(oldVal_pm == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	});
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'doctorClinic.action?action=saveOrUpdateMoreOrg');
	
	function checknum_int(input){
		if(input == '' || parseInt(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	function checknum_float(input){
		if(input == '' || parseFloat(input) != input || input<1 || isNaN(input)){
			return true;
		}else{
			return false;
		}
	}
	if($.trim($('#orgName').val()) == ''){
		alert('请填写机构名称！');
		return false;
	}else if($('#orgAddress').val() == ''){
		alert('请填写机构地址');
		return false;	
	}
	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
	$('#node_Tbody').empty();
	$('#ampm_Tbody').empty();
}
//查询
function search_click(){
	var clinicOrg = $("#clinicOrg").val();
	var data = {
			action:'getAllMoreOrgList',
			'doctorMorePracticeOrgInfo.orgName':clinicOrg
			};
	getAllList(data);
}
//添加节点信息
function add_node_click(tempId,targetTbody){
	//var goodSeedBaseInfoId = $("#goodSeedBaseInfoId").val();

	var j = parseInt(document.getElementById(targetTbody).rows.length);
	var kv={"id":""};//默认值设置
	newTRbyCopyTemplate(tempId,targetTbody,j,kv);
	updateProductTable(targetTbody);
}
function newTRbyCopyTemplate(tempid,tbodyid,rowj,kv){
	var otr=document.getElementById(tempid);
	var ntr=otr.cloneNode(true);
	ntr.removeAttribute("id");
	var nntr=document.getElementById(tbodyid).appendChild(ntr);
	for(var i=0;i<nntr.cells.length;i++){
		var childs=nntr.cells[i].childNodes;
		for(var j=0;j<childs.length;j++){
			var ttt=childs[j];
			//if(!ttt.tagName=="INPUT"){continue;}
			if(ttt.name){
				if(ttt.name.indexOf("[")>=0){
					var str=ttt.name.replace('[]','['+rowj+']');
					var opName=ttt.name.substring(ttt.name.indexOf(".")+1);
				
					for(var key in kv){
						if(key==opName){
							ttt.value=kv[key];
							break;
						}
					}
					ttt.name=str;
				}
			}
			if(ttt.id){
				var str=ttt.id.replace(/Index/g,rowj);
				ttt.id=str;
			}
			if(ttt.src){
				var str=ttt.src.replace(/Index/g,rowj);
				ttt.src=str;
			}
		}
	}
	return nntr;
}
function updateProductTable(targetTbody){
	var productTypeList = document.getElementById(targetTbody);
	var size = productTypeList.rows.length;
	for(var i=0;i<=size-1;i++){
		try{
			productTypeList.rows[i].cells[0].innerText=i+1;		
			productTypeList.rows[i].cells[0].align="center";
		}
		catch(e){}
	}	
}
//移除选中行
function del_node_click(tbodyId){
	var ids = 0;
	$("#"+tbodyId+" tr").each(function(index){
		var selectObj = $(this).find("td:eq(1)").children("input[type=checkbox]");
		if(selectObj.prop("checked")){
			var sectionId = $(this).find("td:eq(1)").children("input:eq(0)").val();
			if(sectionId == ""){
				ids++;
			}else{
				alert("已有数据不能删除!");
				selectObj.attr("checked",false);
				return true;
			}
		}
	});
		if(ids == 0 ){
			alert("请选择要删除的行!");
			return false;
		}
		if(confirm("确定删除选中的行？")){
			$("#"+tbodyId+" tr").each(function(index){
				var selectObj = $(this).find("td:eq(1)").children("input[type=checkbox]");
				var sectionId = $(this).find("td:eq(1)").children("input:eq(0)").val();
				if(sectionId == "" && selectObj.prop("checked")){
					$(this).remove();
				}
	 		});
		}
}
//选择多点机构
function chooseHospital(obj){
	$("#orgName").val($(obj).find("option:selected").text());
	var addressList = $(obj).find("option:selected").attr('data_address_list');
	addressList = JSON.parse(addressList);
	$("#orgAddress").empty();
	$("#orgAddress").append("<option>-请选择-");
	if(addressList.length == 0){
		var orgAddress = $(obj).find("option:selected").attr('data_address');
		$("#orgAddress").append("<option value="+orgAddress+">"+orgAddress);
	}else{
		for(var i=0;i<addressList.length;i++){
			var item = addressList[i];
			var orgAddress = item.showName+"("+item.address+")";
			$("#orgAddress").append("<option value="+orgAddress+">"+orgAddress);
		};
	}
}
