$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>编号</th>"+
			"<th style='width:9%'>项目名称</th>"+
			"<th style='width:9%'>显示名称</th>"+
			"<th style='width:9%'>适用宝宝年龄</th>"+
			"<th style='width:9%'>适用宝宝性别</th>"+
			"<th style='width:9%'>服务时长</th>"+
			"<th style='width:9%'>职称要求</th>"+
      "<th style='width:9%'>项目结果</th>"+
      "<th style='width:9%'>健康计划</th>"+
			"<th style='width:23%'><div onclick='add_info()' class='th_button'>添加</div></th>"+
		"</tr>"	
	);
	show_tableinfo();
	hf_ueditor('child_edit','text_edit');
	$.ajax({
		type:'post',
		async:false,
		url:'positionHandle.action',
		data:{action:"all"},
	    success:function(result){
	    	if(result.allPosition!=null){
	    		   for(var i=0;i<result.allPosition.length;i++){
	    			      $("#show_level").append(
									"<option zjid='"+result.allPosition[i].id+"' value='"+result.allPosition[i].id+"'>"+result.allPosition[i].name+"</option>"
	    			     );
	    			   
	    			 }
	    	}

	    }
	});
	$('#button_update_click').click(function(){
		
		var input_a = $('#show_name').val().trim();//名字
		var input_b = $('#show_time').val().trim();//时间
		var input_c = $('#show_sex').val();//性别
		var input_d = $('#show_age').val().trim();//年龄
		var show_age_max = $('#show_age_max').val().trim();//最大年龄
		var input_e = $('#text_edit').html();//说明
		var input_f = $('#show_name').attr('zjid');//id
		var flowNodeId = $('#flowNodeId').val();//所属流程节点
		var input_g = $('#show_level option').filter(function(){
			return $(this).get(0).selected;
	 	}).attr('zjid');
	 	var input_h = $('#show_status').val();
	 	var myInput_i=$('#show_Newname').val();
		
		if(input_a == '' || input_b == '' || input_d == ''||myInput_i==''){
			alert('请填写完整！');
			return false;
		}else if(isNaN(input_b) || parseInt(input_b) != input_b || input_b < 1){
			alert('时长填写格式不正确');
			return false;
		}else if(isNaN(show_age_max) || isNaN(input_d) || parseInt(input_d) != input_d || input_d < 1 || parseInt(show_age_max) != show_age_max || show_age_max<1){
			alert('年龄填写格式不正确');
			return false;	
		}else if(parseInt(input_d) > parseInt(show_age_max)){
			alert('最小年龄不能大于最大年龄');
			return false;	
		}
		
		$.ajax({
			'url':'productItemHandle.action',
			'type':'POST',
			'data':{
				'action':'update',
				'updateId':input_f,
				'name':input_a,
				'whatFitForMonth':input_d,
				'whatFitForSex':input_c,
				'serviceTime':input_b,
				'positionId':input_g,
				'handleUrl':input_e,
				'status':input_h,
				'whatFitForMonth_max':show_age_max,
				'flowNodeId':flowNodeId,
				'showName':myInput_i
			},
			success:function(result){
				if(result.mes == '成功'){
					alert('修改成功！');
					$('#bottom_div').slideUp(500);
					show_tableinfo();
				}else{
					alert('参数错误');
				}
			}
		});
		
		
	});
	$('#button_add_click').click(function(){
		var input_a = $('#show_name').val().trim();//名字
		var input_b = $('#show_time').val().trim();//时间
		var input_c = $('#show_sex').val();//性别
		var input_d = $('#show_age').val().trim();//年龄
		var show_age_max = $('#show_age_max').val().trim();//最大年龄
		var input_e = $('#text_edit').html();//说明
		var input_f = $('#show_name').attr('zjid');//id
		var flowNodeId = $('#flowNodeId').val();//所属流程节点
		var input_g = $('#show_level option').filter(function(){
			return $(this).get(0).selected;
	 	}).attr('zjid');
	 	var input_h = $('#show_status').val();
	 	//add by fkn
		var myInput_i=$('#show_Newname').val();
		if(input_a == '' || input_b == '' || input_d == ''||myInput_i=='' || show_age_max==''){
			alert('请填写完整！');
			return false;
		}else if(isNaN(input_b) || parseInt(input_b) != input_b || input_b < 1){
			alert('时长填写格式不正确');
			return false;
		}else if(isNaN(show_age_max) || isNaN(input_d) || parseInt(input_d) != input_d || input_d < 1 || parseInt(show_age_max) != show_age_max || show_age_max<1){
			alert('年龄填写格式不正确');
			return false;	
		}else if(parseInt(input_d) > parseInt(show_age_max)){
			alert('最小年龄不能大于最大年龄');
			return false;	
		}
		
		$.ajax({
			'url':'productItemHandle.action',
			'type':'POST',
			'data':{
				'action':'add',
				'updateId':input_f,
				'name':input_a,
				'whatFitForMonth':input_d,
				'whatFitForSex':input_c,
				'serviceTime':input_b,
				'positionId':input_g,
				'handleUrl':input_e,
				'status':input_h,
				'whatFitForMonth_max':show_age_max,
				'flowNodeId':flowNodeId,
				'showName':myInput_i
			},
			success:function(result){
				if(result.mes == '成功'){
					alert('添加成功！');
					$('#bottom_div').slideUp(500);
					show_tableinfo();
				}else{
					alert('参数错误');
				}
			}
		});	
	});
});
function add_info(){
	$('#bottom_div input').val('');
	$('#text_edit').text('');
    $('#bottom_div').slideDown(500);
    $('#button_add_click').show();
    $('#button_update_click').hide();
}
//读取信息
function read_info(obj){
    $('#bottom_div').slideDown(500);
    $('#button_add_click').hide();
    $('#button_update_click').show();
    $('#show_Newname').val($(obj).attr('data_showName'));
    $('#show_name').val($(obj).attr('data_name')).attr('zjid',$(obj).attr('data_id'));
    $('#show_time').val($(obj).attr('data_time'));
    $('#show_sex option').filter(function(){
    	return $(this).text() == $(obj).attr('data_sex');
    }).get(0).selected = true;
    $('#show_level option').filter(function(){
    	return $(this).text() == $(obj).attr('data_level');
    }).get(0).selected = true;
    $('#show_age').val($(obj).attr('data_age'));
    $('#text_edit').html($(obj).attr('data_info'));//产品介绍
    $('#show_status option').filter(function(){
    	return $(this).text() == $(obj).attr('data_status');
    }).get(0).selected = true;
    
    $('#flowNodeName').val($(obj).attr('flow_node_name'));
    $('#flowNodeId').val($(obj).attr('flow_node_id'));
    $('#show_age_max').val($(obj).attr('whatFitForMonth_max'));
}
function show_tableinfo(){
	$('#table_content').empty();
	$.ajax({
		'url':'productItemHandle.action',
		'type':'POST',
		'data':{
			'action':'all'
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.allProductItem == null){
				alert('没有数据！');
				return false;			
			}
			for(var i=0;i < result.allProductItem.length;i ++){

				if((result.allProductItem[i])[6] == null){
					(result.allProductItem[i])[6] = '';
				}
				if((result.allProductItem[i])[7] == null){
					(result.allProductItem[i])[7] = '';
				}
				$('#table_content').append(
					"<tr onclick='click_light(this)'>"+
						"<td style='width:5%'>"+(i+1)+"</td>"+
						"<td style='width:9%'>"+(result.allProductItem[i])[1]+"</td>"+
						"<td style='width:9%'>"+(result.allProductItem[i])[10]+"</td>"+
						"<td style='width:9%'>"+(result.allProductItem[i])[2]+"个月</td>"+
						"<td style='width:9%'>"+(result.allProductItem[i])[3]+"</td>"+
						"<td style='width:9%'>"+(result.allProductItem[i])[4]+"小时</td>"+
						"<td style='width:9%'>"+(result.allProductItem[i])[5]+"</td>"+
	          "<td style='width:9%'>"+(result.allProductItem[i])[6]+"</td>"+
	          "<td style='width:9%'>"+(result.allProductItem[i])[7]+"</td>"+
						"<td style='width:23%'>"+
							"<div"+
								" data_id='"+(result.allProductItem[i])[0]+"' "+
								" data_name='"+(result.allProductItem[i])[1]+"' "+
								" data_age='"+(result.allProductItem[i])[2]+"' "+
								" data_sex='"+(result.allProductItem[i])[3]+"' "+
								" data_time='"+(result.allProductItem[i])[4]+"' "+
								" data_level='"+(result.allProductItem[i])[5]+"' "+
								" data_result='"+(result.allProductItem[i])[6]+"' "+
								" data_plan='"+(result.allProductItem[i])[7]+"' "+
								" data_info='"+(result.allProductItem[i])[8]+"' "+
								" data_status='"+(result.allProductItem[i])[9]+"' "+
								" data_showName='"+(result.allProductItem[i])[10]+"' "+
								" flow_node_id='"+(result.allProductItem[i])[11]+"' "+
								" flow_node_name='"+(result.allProductItem[i])[12]+"' "+
								" whatFitForMonth_max='"+(result.allProductItem[i])[13]+"' "+
							" class='td_change_button' onclick='read_info(this)'>修改</div>"+
						"</td>"+
					"</tr>"	
				);
			}
			reset_con_page();	
		}
	});
}
//选择流程节点
//显示遮罩层    
function selectFlowNode(){
	$('#selectFlowName').empty();
	$('#selectContent').empty();
	//加载选择内容
	loadFlowName();
	//遮罩
	$("#bg").css({
        display: "block", height: $(document).height()
    });
    var $box = $('.box');
    $box.css({
        //设置弹出层距离左边的位置
        left: ($("body").width() - $box.width()) / 2 - 20 + "px",
        //设置弹出层距离上面的位置
        top: ($(window).height() - $box.height()) / 2 + $(window).scrollTop() - 30+ "px",
        display: "block"
    }); 
} 
//隐藏遮罩层  
function hideMask(){     
	 $("#bg,.box").css("display", "none");   
}
//加载流程名下拉
function loadFlowName(){
	$.ajax({
		'url':'operationFlow.action',
		'type':'POST',
		'data':{
			'action':'allOperationFlowBasicInfo',
			'operationFlowBasicInfo.isStart':'Y'
		},
		success:function(result){
			if(result.operationFlowBasicInfoList == null){
				alert('没有数据！');
				return false;			
			}
			$('#selectFlowName').empty();
			$('#selectFlowName').prepend("<option value=''>-请选择-</option>");
			for(var i=0;i < result.operationFlowBasicInfoList.length;i ++){
				$('#selectFlowName').append(
					"<option value='"+(result.operationFlowBasicInfoList[i]).flowBasicId+"'>"+(result.operationFlowBasicInfoList[i]).flowName+"</option>"	
				);
			}
		}
	});
}
//加载节点选择内容
function loadSelectContent(obj){
	$('#selectContent').empty();
	if($(obj).val() == ""){
		return false;
	}
	//加载节点列表
	$.ajax({
		'url':'operationFlow.action',
		'type':'POST',
		'data':{
			'action':'allOperationFlowNodeInfo',
			'operationFlowBasicInfo.flowBasicId':$(obj).val(),
			'operationFlowNode.isStart':'Y'
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
				$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'><input type='radio' name='aa' value='"+result.operationFlowNodeList[i].flowNodeId+"'/></td>"+
					"<td style='width:30%'>"+(result.operationFlowNodeList[i].flowNodeName)+"</td>"+
					"<td style='width:50%'>"+(result.operationFlowNodeList[i].flowNodeRemark)+"</td>"+
				"</tr>"
				);
			}
		}
	});
}
//确定选中流程节点
function add_node(){
	var nodeId = $('#selectContent input:radio:checked').val();
	if(nodeId == null){
		alert("请选择流程节点");
		return false;
	}
	var nodeName = $('#selectContent input:radio:checked').parent().next().text();
	$("#flowNodeId").val(nodeId);
	$("#flowNodeName").val(nodeName);
	hideMask();
}