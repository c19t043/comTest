var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:5%'>序列</th>"+
			"<th style='width:10%'>所属分类</th>"+
			"<th style='width:10%'>项目标题</th>"+
			"<th style='width:10%'>最小月龄</th>"+
			"<th style='width:10%'>最大月龄</th>"+
			"<th style='width:10%'>项目内容</th>"+
			"<th style='width:10%'>项目费用</th>"+
			"<th style='width:10%'>"+
				"<div onclick='add_info()' class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	var data = {action:'getChildcareProjectList'};
	getAllList(data);
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:false, 
		data:{
			action:"getChildcareProjectTypeList"
		},
		success:function(result){
			for(var i=0;i < result.childcareProjectTypeList.length;i++){
				$('#childcareProjectType').append(
						"<option value='"+(result.childcareProjectTypeList[i]).id+"'>"+
						(result.childcareProjectTypeList[i]).typeName+"</option>"
				);
			}
		}
	});
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'orgBusiness.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.childcareProjectList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.childcareProjectList.length;i ++){
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:5%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectList[i]).childcareProjectType.typeName+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectList[i]).projectTitle+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectList[i]).minMonthAge+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectList[i]).maxMonthAge+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectList[i]).projectContent+"</td>"+
					"<td style='width:10%'>"+(result.childcareProjectList[i]).itemMoney+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.childcareProjectList[i]).id+"' "+
							" projectTitle='"+(result.childcareProjectList[i]).projectTitle+"' "+
							" typeId='"+(result.childcareProjectList[i]).childcareProjectType.id+"' "+
							" minMonthAge='"+(result.childcareProjectList[i]).minMonthAge+"' "+
							" maxMonthAge='"+(result.childcareProjectList[i]).maxMonthAge+"' "+
							" projectContent='"+(result.childcareProjectList[i]).projectContent+"' "+
							" remark='"+(result.childcareProjectList[i]).remark+"' "+
							" sort='"+(result.childcareProjectList[i]).sort+"' "+
							" itemMoney='"+(result.childcareProjectList[i]).itemMoney+"' "+
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
    
	$('#projectTitle').val($(obj).attr('projectTitle'));
	$('#minMonthAge').val($(obj).attr('minMonthAge'));
	$('#maxMonthAge').val($(obj).attr('maxMonthAge'));
	$('#sort').val($(obj).attr('sort'));
	$('#projectContent').html($(obj).attr('projectContent'));
	$('#remark').html($(obj).attr('remark'));
	$('#itemMoney').val($(obj).attr('itemMoney'));
	$('#childcareProjectType option').each(function(){
		if($(this).val() == $(obj).attr('typeId')){
			$(this).attr("selected","selected");
		}
	});
}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'orgBusiness.action?action=saveOrUpdateChildcareProject');
	
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
	var projectTitle = $("#ruleName_con").val();
	//var workDate_con = $("#workDate_con").val();
	//var search_status = $("#search_status").find("option:selected").text();
	var data = {action:'getChildcareProjectList',
				'childcareProject.projectTitle':projectTitle
				};
	getAllList(data);
}

