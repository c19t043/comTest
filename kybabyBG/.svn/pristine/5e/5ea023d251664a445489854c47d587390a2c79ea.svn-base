var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>会员卡名称</th>"+
			"<th style='width:10%'>有效时长/天</th>"+
			"<th style='width:10%'>会员卡内容</th>"+
			"<th style='width:10%'>"+
				"<div class='th_button' onclick='add_info()'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getMemberTypeList'};
	getAllList(data);
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'memberManage.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.memberTypeList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.memberTypeList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.memberTypeList[i]).memberName+"</td>"+
					"<td style='width:10%'>"+(result.memberTypeList[i]).effectiveDate+"</td>"+
					"<td style='width:10%'>"+(result.memberTypeList[i]).memberContent+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.memberTypeList[i]).id+"' "+
							" memberName='"+(result.memberTypeList[i]).memberName+"' "+
							" memberContent='"+(result.memberTypeList[i]).memberContent+"' "+
							" effectiveDate='"+(result.memberTypeList[i]).effectiveDate+"' "+
							" ruleNames='"+(result.memberTypeList[i]).ruleNames+"' "+
							" ruleIds='"+(result.memberTypeList[i]).ruleIds+"' "+
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
    
    $('#keyId').val($(obj).attr('keyId'));
    
	$('#memberName').val($(obj).attr('memberName'));
	$('#memberContent').val($(obj).attr('memberContent'));
	$('#effectiveDate').val($(obj).attr('effectiveDate'));
	$('#ruleIds').val($(obj).attr('ruleIds'));
	$('#ruleNames').html($(obj).attr('ruleNames'));
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'memberManage.action?action=saveOrUpdateMemberType');
	
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
	if($('#memberName').val().trim() == ''){
		alert('请填写名称！');
		return false;	
	}
	if($('#effectiveDate').val().trim() == ''){
		alert('请填写有效时长！');
		return false;	
	}
	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#bottom_div_con textarea').val('');
	 $('#ruleNames').html('');
	$('#bottom_div_con img').removeAttr('src');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
}
//---------------弹出遮罩-------------------
//选择修改（弹出遮罩）
function chooseObj(obj){
	//遮罩
	$("#bg").css({
        display: "block", height: $(document).height()
    });
    var $box = $('.boxZhezhao');
    $box.css({
        //设置弹出层距离左边的位置
        left: ($("body").width() - $box.width()) / 2 - 20 + "px",
        //设置弹出层距离上面的位置
        top: ($(window).height() - $box.height()) / 2 + $(window).scrollTop() - 30+ "px",
        display: "block"
    }); 
    if("openBusiness" == obj){
    	$('#titalTable').empty();
    	$('#titalTable').append(
    		"<tr>"+
				"<th style='width:10%'>序列</th>"+
				"<th style='width:10%'><div onclick=add_node('openService') class='th_button'>选择</div></th>"+
				"<th style='width:20%'>规则名称</th>"+
				"<th style='width:20%'>规则编码</th>"+
				"<th style='width:30%'>规则说明</th>"+
			"</tr>"
		);
    	$.ajax({
    		'url':'memberManage.action',
    		'type':'POST',
    		'data':{
    			'action':'getMemberRuleList',
    		},
    		success:function(result){
    			if(result.mes != '操作成功'){
    				alert('数据返回错误！');
    				return false;
    			}
    			//加载开放服务选择
    			loadOpenService(result.memberRuleList);
    		}
    	});
    }
}
//加载开放服务
function loadOpenService(list){
	$('#selectContent').empty();
	var goodIds = $('#ruleIds').val().split("::");
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].id+"";
		if($.inArray(id,goodIds) > -1){
			selected = "checked";
		}
		$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'><input type='checkbox' "+selected+" data_name='"+list[i].ruleName+"' value='"+list[i].id+"'/></td>"+
					"<td style='width:20%'>"+(list[i].ruleName)+"</td>"+
					"<td style='width:20%'>"+(list[i].ruleCode)+"</td>"+
					"<td style='width:30%'>"+(list[i].ruleRemark)+"</td>"+
				"</tr>"
				);
	}
}
//隐藏遮罩层  
function hideMask(){     
	 $("#bg,.boxZhezhao").css("display", "none");   
}
//确定选中开展业务
function add_node(obj){
	var nodeId = "";
	var nodeName = "";
	$('#selectContent input:checkbox:checked').each(function(){
		nodeId += $(this).val() + "::";
		nodeName += $(this).attr('data_name') + "::";
	});
	if(nodeId == ""){
		alert("没有选中的数据");
		return false;
	}
	nodeId = nodeId.substring(0,nodeId.lastIndexOf("::"));
	nodeName = nodeName.substring(0,nodeName.lastIndexOf("::"));
	if("openService" == obj){
		$("#ruleIds").val(nodeId);
		$("#ruleNames").text(nodeName);
	}
	hideMask();
}
//查询
function search_click(){
	var hospitalName = $("#hospitalName").val();
	var data = {action:'getMemberTypeList',
				'hospitalBasicInfo.hospitalLname':hospitalName
				};
	getAllList(data);
}

