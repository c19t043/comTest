$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:20%'>流程名称</th>"+
			"<th style='width:30%'>流程说明</th>"+
			"<th style='width:10%'>是否启用</th>"+
			"<th style='width:15%'>修改时间</th>"+
			"<th style='width:15%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	$.ajax({
		'url':'operationFlow.action',
		'type':'POST',
		cache: false,
		async: false,
		'data':{
			'action':'allOperationFlowBasicInfo'
		},
		success:function(result){
			show_tableinfo(result);
		},
		error:function(xhr){
			alert('动态页出错\n\n'+xhr.responseText);
		}///////////增加错误回调，看什么问题
	
	});
});

//加载内容
function show_tableinfo(result){
	if(result.mes != '操作成功'){
		alert('数据返回错误！');
		return false;
	}else if(result.operationFlowBasicInfoList == null){
		alert('没有数据！');
		return false;			
	}
	$('#table_content').empty();
	for(var i=0;i < result.operationFlowBasicInfoList.length;i ++){
		$('#table_content').append(
		"<tr onclick='click_light(this)'>"+
			"<td style='width:10%'>"+(i+1)+"</td>"+
			"<td style='width:20%'>"+(result.operationFlowBasicInfoList[i]).flowName+"</td>"+
			"<td style='width:30%'>"+(result.operationFlowBasicInfoList[i]).flowRemark+"</td>"+
			"<td style='width:10%'>"+(result.operationFlowBasicInfoList[i]).isStart+"</td>"+
			"<td style='width:15%'>"+(result.operationFlowBasicInfoList[i]).modifyTime+"</td>"+
			"<td style='width:15%'>"+
				"<div class='td_change_button' "+
					" flowBasicId='"+(result.operationFlowBasicInfoList[i]).flowBasicId+"' "+
					" flowName='"+(result.operationFlowBasicInfoList[i]).flowName+"' "+
					" flowRemark='"+(result.operationFlowBasicInfoList[i]).flowRemark+"' "+
					" isStart='"+(result.operationFlowBasicInfoList[i]).isStart+"' "+
					" modifyTime='"+(result.operationFlowBasicInfoList[i]).modifyTime+"' "+
				" onclick='read_info(this)'>修改</div>"+
			"</td>"+
		"</tr>"
		);
	}
	reset_con_page();	
}

//修改读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
   
    $('#bottom_div_con input').val('');
    
    $("#flowName").val($(obj).attr('flowName'));//名称
    $('#flowBasicId').val($(obj).attr('flowBasicId'));//ID
    $('#flowRemark').val($(obj).attr('flowRemark'));//说明
   
    $('#isStart option').filter(function(){//是否启动
    	return $(this).text() == $(obj).attr('isStart');
    }).get(0).selected = true;
	$('#node_table_title').empty();
	$('#node_Tbody').empty();
	$('#node_table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>选择</th>"+
			"<th style='width:30%'>节点名称</th>"+
			"<th style='width:10%'>排序号</th>"+
			"<th style='width:10%'>是否使用</th>"+
			"<th style='width:30%'>说明</th>"+
		"</tr>"	
	);
  //加载节点列表
	$.ajax({
		'url':'operationFlow.action',
		'type':'POST',
		'data':{
			'action':'allOperationFlowNodeInfo',
			'operationFlowBasicInfo.flowBasicId':$(obj).attr('flowBasicId')
		},
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.operationFlowNodeList == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.operationFlowNodeList.length;i ++){
				$('#node_Tbody').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'>" +
						"<input type='hidden' name='operationFlowNodeList["+i+"].flowNodeId' value='"+result.operationFlowNodeList[i].flowNodeId+"' >"+
					"<input type='checkbox' /></td>"+
					"<td style='width:30%'><input class='query' type='text' name='operationFlowNodeList["+i+"].flowNodeName' value='"+result.operationFlowNodeList[i].flowNodeName+"' placeholder='节点名'></td>"+
					"<td style='width:10%'><input class='query' type='text' name='operationFlowNodeList["+i+"].sort' value='"+result.operationFlowNodeList[i].sort+"' placeholder='输入排序号'></td>"+
					"<td style='width:10%'><select name='operationFlowNodeList["+i+"].isStart' value='"+result.operationFlowNodeList[i].isStart+"'><option value='Y'>Y</option><option value='N'>N</option></select></td>"+
					"<td style='width:30%'><textarea name='operationFlowNodeList["+i+"].flowNodeRemark' style='width: 100%;height: 28;'>"+result.operationFlowNodeList[i].flowNodeRemark+"</textarea></td>"+
				"</tr>"
				);
			}
		}
	});
}

//保存或更新
function saveFlowInfo(){
	$('#operationFlowInfo_form').attr('action','operationFlow.action?action=saveOrUpdateOperationFlow')
	
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
	if($.trim($('#flowName').val()) == ''){
		alert('请填写流程名称！');
		return false;
	}else if($('#flowRemark').val() == ''){
		alert('请填写流程说明');
		return false;	
	}
	if(confirm("确认？")){
		$('#operationFlowInfo_form').submit();
	}
}
//添加
function add_info(){
  $('#bottom_div_con input').val('');
  $('#bottom_div_con textarea').val('');
  
  $('#bottom_div').slideDown(500);
  $('#button_add_click').show();
  $('#button_update_click').hide();	
  load_flowNode();
}
//加载节点字段
function load_flowNode(){
	$('#node_table_title').empty();
	$('#node_Tbody').empty();
	$('#node_table_title').append(
			"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'>选择</th>"+
				"<th style='width:30%'>节点名称</th>"+
				"<th style='width:10%'>排序号</th>"+
				"<th style='width:10%'>是否使用</th>"+
				"<th style='width:30%'>说明</th>"+
			"</tr>"	
		);
}
//查询
function search_click(){
	var rule_name = $("#rule_name").val();
	$.ajax({
		'url':'sendDoctor.action',
		'type':'POST',
		cache: false,
		async: false,
		'data':{
			'action':'getoperationFlowBasicInfoList',
			'ruleBasic.ruleName':rule_name,
			'ruleBasic.ruleBasicId':0
		},
		success:function(result){
			if(result == ''){
				alert('没有数据！');
				return false;		
			}
			$('#table_content').empty();
			$('#table_content').append(result);
		}
	});
}
//添加节点信息
function add_node_click(tempId,targetTbody){
		var goodSeedBaseInfoId = $("#goodSeedBaseInfoId").val();
	
		var j = parseInt(document.getElementById(targetTbody).rows.length);
		var kv={"flowNodeId":""};//默认值设置
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
