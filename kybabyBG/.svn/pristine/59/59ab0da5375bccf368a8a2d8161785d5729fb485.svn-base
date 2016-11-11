var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>规则名称</th>"+
			"<th style='width:10%'>规则编码</th>"+
			"<th style='width:10%'>规则说明</th>"+
			"<th style='width:10%'>"+
				"<div onclick='add_info()' class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getMemberRuleList'};
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
			}else if(result.memberRuleList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.memberRuleList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.memberRuleList[i]).ruleName+"</td>"+
					"<td style='width:10%'>"+(result.memberRuleList[i]).ruleCode+"</td>"+
					"<td style='width:10%'>"+(result.memberRuleList[i]).ruleRemark+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.memberRuleList[i]).id+"' "+
							" ruleName='"+(result.memberRuleList[i]).ruleName+"' "+
							" ruleCode='"+(result.memberRuleList[i]).ruleCode+"' "+
							" ruleRemark='"+(result.memberRuleList[i]).ruleRemark+"' "+
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
    
	$('#ruleName').val($(obj).attr('ruleName'));
	$('#ruleCode').val($(obj).attr('ruleCode'));
	$('#ruleRemark').html($(obj).attr('ruleRemark'));

}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'memberManage.action?action=saveOrUpdateMemberRule');
	
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

	if(confirm("确认？")){
		$('#data_form').submit();
	}
}

function add_info(){
	$('#bottom_div_con input').val('');
	$('#bottom_div_con textarea').html('');
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
}
function search_click(){
	var ruleName = $("#ruleName_con").val();
	//var workDate_con = $("#workDate_con").val();
	//var search_status = $("#search_status").find("option:selected").text();
	var data = {action:'getMemberRuleList',
				'memberRule.ruleName':ruleName
				};
	getAllList(data);
}

