var ringHost="../admin/";

$(function(){
	$('#table_title').append(
		"<tr>"+
			"<th style='width:10%'>序列</th>"+
			"<th style='width:10%'>消息类别</th>"+
			"<th style='width:10%'>发送用户</th>"+
			"<th style='width:10%'>发送手机</th>"+
			"<th style='width:10%'>消息标题</th>"+
			"<th style='width:10%'>发送时间</th>"+
			"<th style='width:10%'>是否已读</th>"+
			"<th style='width:10%'>是否删除</th>"+
			"<th style='width:10%'>用户do时间</th>"+
			"<th style='width:10%'>"+
				"<div onclick=add_info() class='th_button'>添加</div>"+
			"</th>"+
		"</tr>"
	);
	hf_ueditor('edit_btn','edit_content');
	var data = {action:'messageList'};
	getAllList(data);
	
}); 

//获取机构列表信息
function getAllList(data){
	$.ajax({
		type:'post',
		url:ringHost+'messageCenter.action',
		cache:false,
		async:true, 
		data:data,
		success:function(result){
			if(result.mes != '操作成功'){
				alert('数据返回错误！');
				return false;
			}else if(result.messageCenterList == null){
				alert('没有数据！');
				return false;			
			}
			$('#table_content').empty();
			for(var i=0;i < result.messageCenterList.length;i ++){
				var userInfo = (result.messageCenterList[i]).userInfo;
				var phone = userInfo==null?"":userInfo.phone;
				var babyName = userInfo==null?"":userInfo.babyName;
				var userId = userInfo.id;
				$('#table_content').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>"+
					"<td style='width:10%'>"+(result.messageCenterList[i]).messageType+"</td>"+
					"<td style='width:10%'>"+babyName+"</td>"+
					"<td style='width:10%'>"+phone+"</td>"+
					"<td style='width:10%'>"+(result.messageCenterList[i]).messageTitle+"</td>"+
					"<td style='width:10%'>"+(result.messageCenterList[i]).messageSendTime+"</td>"+
					"<td style='width:10%'>"+(result.messageCenterList[i]).isRead+"</td>"+
					"<td style='width:10%'>"+(result.messageCenterList[i]).isDel+"</td>"+
					"<td style='width:10%'>"+(result.messageCenterList[i]).optTime+"</td>"+
					"<td style='width:10%'>"+
						"<div class='td_change_button' "+
							" keyId='"+(result.messageCenterList[i]).id+"' "+
							" userId='"+userId+"' "+
							" messageTitle='"+(result.messageCenterList[i]).messageTitle+"' "+
							" messageContent='"+(result.messageCenterList[i]).messageContent+"' "+
							" messageSendTime='"+(result.messageCenterList[i]).messageSendTime+"' "+
							" isRead='"+(result.messageCenterList[i]).isRead+"' "+
							" isDel='"+(result.messageCenterList[i]).isDel+"' "+
							" optTime='"+(result.messageCenterList[i]).optTime+"' "+
							" messageType='"+(result.messageCenterList[i]).messageType+"' "+
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
    $('#userId').val($(obj).attr('userId'));
    
	$('#messageTitle').val($(obj).attr('messageTitle'));
	$('#edit_content').html($(obj).attr('messageContent'));//消息内容
	$('#messageSendTime').val($(obj).attr('messageSendTime'));
	$('#optTime').val($(obj).attr('optTime'));
	$('#isRead').each(function(){
		if($(this).val() == $(obj).attr('isRead')){
			$(this).attr("selected","selected");
		}
	});
	$('#isDel').each(function(){
		if($(this).val() == $(obj).attr('isDel')){
			$(this).attr("selected","selected");
		}
	});
	$('#messageType').each(function(){
		if($(this).val() == $(obj).attr('messageType')){
			$(this).attr("selected","selected");
		}
	});

}

//保存或更新
function saveOrUpdate(){
	$('#data_form').attr('action',ringHost+'messageCenter.action?action=saveOrUpdateMessage');
	
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
	$('#button_add_click').show();
	$('#bottom_div').slideDown(500);
}
function search_click(){
	var baby_name = $("#baby_name").val();
	var doctor_name = $("#doctor_name").val();
	var search_status = $("#search_status").find("option:selected").text();
	var clinicDate = $("#clinicDate").val();
	var orderNum = $("#order_Num").val();
	var data = {action:'messageList',
				'orderInfoClinic.userInfo.babyName':baby_name ,
				'orderInfoClinic.orderStatus':search_status ,
				'orderInfoClinic.appointmentDate':clinicDate ,
				'orderInfoClinic.orderNum':orderNum ,
				'orderInfoClinic.doctorInfo.doctorName':doctor_name
				};
	getAllList(data);
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
				"<th style='width:10%'><input type='checkbox' onclick='selectAll(this)'/><div onclick=add_node('openService') class='th_button'>选择</div></th>"+
				"<th style='width:10%'>用户名称</th>"+
				"<th style='width:10%'>关联机构</th>"+
				"<th style='width:10%'>宝宝月龄</th>"+
				"<th style='width:10%'>用户类别</th>"+
			"</tr>"
		);
  	$.ajax({
  		'url':'messageCenter.action',
  		'type':'POST',
  		'data':{
  			'action':'getUserList',
  		},
  		success:function(result){
  			if(result.mes != '操作成功'){
  				alert('数据返回错误！');
  				return false;
  			}
  			//加载开放服务选择
  			loadOpenService(result.archivesInfoList);
  		}
  	});
  }
}
//加载开放服务
function loadOpenService(list){
	$('#selectContent').empty();
	var goodIds = $('#open_business_id').val().split("::");
	if(list == null){
		alert('没有数据！');
		return false;			
	}
	for(var i=0;i < list.length;i ++){
		var selected = "";
		var id = list[i].userInfo.id+"";
		if($.inArray(id,goodIds) > -1){
			selected = "checked";
		}
		$('#selectContent').append(
				"<tr onclick='click_light(this)'>"+
					"<td style='width:10%'>"+(i+1)+"</td>" +
					"<td style='width:10%'><input type='checkbox' name='all' "+selected+" data_name='"+list[i].childrenName+"' value='"+list[i].userInfo.id+"'/></td>"+
					"<td style='width:10%'>"+(list[i].childrenName)+"</td>"+
					"<td style='width:10%'>"+(list[i].hospitalBasicInfo.hospitalLname)+"</td>"+
					"<td style='width:10%'>"+(list[i].monthAge)+"个月</td>"+
					"<td style='width:10%'>"+(list[i].userType)+"</td>"+
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
		$("#open_business_id").val(nodeId);
		$("#userNames").text(nodeName);
	}
	hideMask();
}
//全选、反选
function selectAll(obj){
	if($(obj).prop("checked")){
	$("input:checkbox[name=all]").each(function(){
			$(this).attr("checked",true);
		});
	}else{
		$("input:checkbox[name=all]").each(function(){
			$(this).attr("checked",false);
		});
	}
}
