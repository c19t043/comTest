var ringHost="../ring/";
function getAllRingCategory(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"allCategory"},
		success:function(result){
			
		}
	});
}

function getSomeCategory(){
	var someCategoryId=$.trim($("#someCategory").val());
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"someCategory",someCategoryId:someCategoryId},
		success:function(result){
			
		}
	});
}

function addNewPostInfo(){
	var postInfoTitle=$.trim($("#postInfoTitle").val());
	var postInfoContent=$.trim($("#postInfoContent").val());
	var ringTypeId=$.trim($("#ringTypeId").val());
	var isAllowReply=$.trim($("#isAllowReply").val());
	var isTop=$.trim($("#isTop").val());
	var postInfoStatus=$.trim($("#postInfoStatus").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{
			action:"addNewPostInfo",
			postInfoTitle:postInfoTitle,
			postInfoContent:postInfoContent,
			ringTypeId:ringTypeId,
			isAllowReply:isAllowReply,
			isTop:isTop,
			postInfoStatus:postInfoStatus
		},
		success:function(result){
			
		}
	});
}

function getAllPostInfo(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"allPostInfo"},
		success:function(result){
			
		}
	});
}

function changePostInfoInstance(){
	var postInfoId=$.trim($("#postInfoId").val());
	var postInfoTitle=$.trim($("#new_postInfoTitle").val());
	var postInfoContent=$.trim($("#new_postInfoContent").val());
	var ringTypeId=$.trim($("#new_ringTypeId").val());
	var isAllowReply=$.trim($("#new_isAllowReply").val());
	var isTop=$.trim($("#new_isTop").val());
	var postInfoStatus=$.trim($("#new_postInfoStatus").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{
			action:"updatePostInfo",
			postInfoId:postInfoId,
			postInfoTitle:postInfoTitle,
			postInfoContent:postInfoContent,
			ringTypeId:ringTypeId,
			isAllowReply:isAllowReply,
			isTop:isTop,
			postInfoStatus:postInfoStatus
		},
		success:function(result){
			
		}
	});
}

function getAllTypeInfo(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"allTypeInfo"},
		success:function(result){
			
		}
	});
}

//获取所有医生圈的标签
//ringCategory:Y代表请求的医生圈
function getAllDoctorRingLabel(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"allDoctorRingLabelInfo",ringCategory:"Y"},
		success:function(result){
			
		}
	});
}

function addNewLabel(){
	var ringLabelName=$.trim($("#ringLabelName").val());
	var ringLabelStatus=$.trim($("#ringLabelStatus").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"addNewLabel",ringCategory:"Y",ringLabelName：ringLabelName,ringLabelStatus:ringLabelStatus},
		success:function(result){
			
		}
	});
}

function updateSomeLabel(){
	var ringLabelId=$.trim($("#labelId").val());
	var ringLabelName=$.trim($("#labelName").val());
	var ringLabelStatus=$.trim($("#labelStatus").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"updateSomeLabel",ringCategory:"Y",ringLabelId:ringLabelId,ringLabelName：ringLabelName,ringLabelStatus:ringLabelStatus},
		success:function(result){
			
		}
	});
}

//获取某篇帖子选中的标签ID列表
function getSomePostInfoLabelIdList(){
	var postInfoLabelId=$.trim($("#chosedPostInfoId").val());
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"getSomePostInfoLabelIdList",postInfoLabelId:postInfoLabelId},
		success:function(result){
			
		}
	});
}
