var ringHost="../ring/";
//获取所有的医生圈分类
function getAllRingCategory(){
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"allCategory"},
		success:function(result){
			if(result.mes=="操作成功"){
				//返回的allRingCategory是object[]数组组成的list结构,0:ID,1:分类名称
			}else if(result.mes=="无分类"){
				alert("还没有分类哦！");
			}
		}
	});
}
//点击顶部某一个医生圈分类执行的方法
function getSomeCategory(){
	var categoryId=$.trim($("#categoryId").val());
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"someCategory",categoryId:categoryId},
		success:function(result){
			
		}
	});
}

//订阅某一个医生圈
function recommendSomeCategory(){
	var categoryId=$.trim($("#recommendCategoryId").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"recommend",ringTypeId:categoryId},
		success:function(result){
			
		}
	});
}

//点击某一个医生圈，进入到医生圈内获取到医生圈内帖子的详细列表
function getSomeDoctorRingInfo(){
	var categoryId=$.trim($("#recommendCategoryId").val());
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"turnTo",ringTypeId:categoryId},
		success:function(result){
			
		}
	});
}

function somePostInfoDetail(){
	var postInfoId=$.trim($("#postInfoId").val());
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"detail",postInfoId:postInfoId},
		success:function(result){
			
		}
	});
}

//取消订阅的方法
function cancelSomeCategory(){
	var categoryId=$.trim($("#recommendCategoryId").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
    async:false, 
		data:{action:"cancle",ringTypeId:categoryId},
		success:function(result){
			
		}
	});
}

//帖子点赞
function giveStar(){
	var postInfoId=$.trim($("#postInfoId").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"giveStar",postInfoId:postInfoId},
		success:function(result){
			
		}
	});
}

//取消点赞
function cancleStar(){
	var postInfoId=$.trim($("#postInfoId").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"cancleStar",postInfoId:postInfoId},
		success:function(result){
			
		}
	});
}

//对帖子进行回复
function postReply(){
	var postInfoId=$.trim($("#postInfoId").val());
	var postContent=$.trim($("#postContent").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"postReply",postInfoId:postInfoId,postContent:postContent},
		success:function(result){
			
		}
	});
}

//发表新的帖子
function addNewPostInfo(){
	var titleContent=$.trim($("#titleContent").val());
	var textContent=$.trim($("#textContent").val());
	var imgContent=$.trim($("#imgContent").val());
	var categoryId=$.trim($("#newCategoryId").val());
	$.ajax({
		type:'post',
		url:ringHost+'doctorRringManage.action',
		cache:false,
        async:false, 
		data:{action:"addNewPostInfo",
			titleContent:titleContent,
			textContent:textContent,
			imgContent:imgContent,
			categoryId:categoryId
		},
		success:function(result){
			
		}
	});
}

//在某一个医生圈下根据选择的标签进行帖子的筛选
function labelScreen(){
	var doctorRingId=$.trim($("#doctorRingId").val());
	var doctorRingLableId=$.trim($("#doctorRingLableId").val());
	$.ajax({
		type:'post',
		url:ringHost+'getDoctorRringInfo.action',
		cache:false,
        async:false, 
		data:{action:"postInfoScreen",doctorRingId:doctorRingId,doctorRingLableId:doctorRingLableId},
		success:function(result){
			
		}
	});
}

//获取所有医生圈标签的ID和名称
function getAllDoctorRingLabels(){
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

//医生端重构
var orderHost = "../doctorOrder/";
function testDoctor(){
	/*
	$.ajax({
		type:'post',
		url:orderHost+'orderManager.action',
		cache:false,
        async:false, 
		data:{action:"doctorSign","orderInfo.id":"11"},
		success:function(result){
			
		}
	});
	
	$.ajax({
		type:'post',
		url:orderHost+'orderManager.action',
		cache:false,
        async:false, 
		data:{action:"jumpFlowNode",
			"orderInfo.orderNum":"11",
			"orderNodeTrack.flowNodeId":"2",
			"orderInfo.productId":"3"},
		success:function(result){
			
		}
	});
	
	//保存签到信息
	$.ajax({
		type:'post',
		url:orderHost+'orderManager.action',
		cache:false,
        async:false, 
		data:{action:"doctorSign",
			"doctorSignRecord.id":"2",
			"doctorSignRecord.signAddress":"成都高新区王家庄和谐家园111",
			"doctorSignRecord.orderInfo.id":"4",
			"doctorSignRecord.doctorInfo.id":"3",
			"doctorSignRecord.userInfo.id":"1"
			},
		success:function(result){
			alert(result.mes);
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
         }
	});
	
	//保存订单结果
	
	var arr = new Array();
	var obj1 = {"id":"1","resultValue":"10"};
	var obj2 = {"id":"2","resultValue":"11"};
	arr.push(obj1);
	arr.push(obj2);
	$.ajax({
		type:'post',
		traditional: true,
		url:orderHost+'orderManager.action',
		cache:false,
        async:false, 
		data:{action:"jumpFlowNode",
			"orderResultsJson":JSON.stringify(arr),"orderInfo.id":"121"
			},
		success:function(result){
			alert(result.mes);
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
         }
	});
	*/
	$.ajax({
		type:'post',
		url:orderHost+'orderManager.action',
		cache:false,
        async:false, 
		data:{action:"getFlowNode",
			"orderInfo.id":"189"
			},
		success:function(result){
			
		}
	});
	
	/*
	var arr = new Array();
	var obj1 = {"id":"","healthPathId":"1","healthPlanId":"1"};
	var obj2 = {"id":"","healthPathId":"1","healthPlanId":"2"};
	arr.push(obj1);
	arr.push(obj2);
	$.ajax({
		type:'post',
		url:orderHost+'orderManager.action',
		cache:false,
        async:false, 
		data:{action:"saveOrUpdateHealthInstruction",
			"healthPlanJson":JSON.stringify(arr),"orderInfo.id":"189"
			},
		success:function(result){
			
		}
	});
	*/
}
var clinicHost = "http://127.0.0.1:8088/kybabyDoctor/doctorClinic/";
function testClinic(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinic.action',
		cache:false,
        async:false, 
		data:{action : "getDoctorMorePracticeList",
            "doctorMorePractice.clinicOrgType":"0"

			},
		success:function(result){
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
	});
}
//机构列表
function testClinic_1(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinic.action',
		cache:false,
        async:false, 
		data:{action : "getMoreOrgList"

			},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
	});
}
//机构时间列表
function testClinic_1(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinic.action',
		cache:false,
        async:false, 
		data:{action : "getMoreOrgTimeList",
			"doctorMorePracticeOrgInfo.id":1
			},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
	});
}
//医生多点执业订单列表
function testClinicOrder(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinicOrder.action',
		cache:false,
		async:false, 
		data:{action : "getClinicOrderList"
			//,
			//"doctorMorePracticeOrgInfo.id":1
		},
		success:function(result){
			alert(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//确认会面更新状态
function testUpdateOrderStatus(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinicOrder.action',
		cache:false,
		async:false, 
		data:{action : "updateClinicOrderStatus",
			 "orderInfoClinic.id":42,
			 "orderInfoClinic.orderStatus":"已会面1",
		},
		success:function(result){
			alert(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//我要下班
function testClinic_3(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{action : "saveWorkRecord",
			 "doctorMorePractice.id":97,
			 "doctorMorePractice.startEndFlag":"我要下班"
		},
		success:function(result){
			alert(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//多点执业标签
function testClinic_5(){
	$.ajax({
		type:'post',
		url:clinicHost+'doctorClinic.action',
		cache:false,
		async:false, 
		data:{action : "getMorePractice",
			 "doctorInfo.id":38
		},
		success:function(result){
			alert(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//========================================
var familyDoctorHost="http://127.0.0.1:8088/kybabyDoctor/familyDoctor/";
//家庭医生服务设置
function familyDoctor(){
	alert(111);
	$.ajax({
		type:'post',
		url:familyDoctorHost+'familyDoctorServe.action',
		cache:false,
		async:false, 
		data:{action : "initFamilyDoctorServe",
			 "doctorInfo.id":39
		},
		success:function(result){
			alert(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//============================================================================
var orgBusinessHost="http://127.0.0.1:8088/kybabyDoctor/orgBusiness/";
function orgLogin(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'orgLogin.action',
		cache:false,
		async:false, 
		data:{action : "orgLogin",
			"organOperator.hospitalBasicInfo.id":"2",
			"organOperator.loginName":"admin",
			"organOperator.password":"123"
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//机构业务
function orgBusinessList(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'orgBusinessManage.action',
		cache:false,
		async:false, 
		data:{action : "getBusinessNameList"
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//儿保列表
function ochildList(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'orgBusinessManage.action',
		cache:false,
		async:false, 
		data:{action : "getChildCareAppointmentListForReception",
			"userChildcareAppointmentInfo.preEncoding":4
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//计免列表
function jimianList(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'orgBusinessManage.action',
		cache:false,
		async:false, 
		data:{action : "getUserInoculationAppointmentInfoListForReception",
			"userInoculationAppointmentInfo.status":"已登记"
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//疫苗列表
function yimiaoList(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'orgBusinessManage.action',
		cache:false,
		async:false, 
		data:{action : "getVaccineInfoList",
			"userInoculationAppointmentInfo.userInfo.id":"119"
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}

//机构业务（健康档案）
function saveHealth(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'orgClinicManager.action',
		cache:false,
		async:false, 
		data:{action : "saveOrUpdateBabyBasicData",
			"orderInfoClinic.id":1
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//药品
function drug(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'drugInfoManage.action',
		cache:false,
		async:false, 
		data:{action : "getDrugClassificationAndDrugList"
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
//shenfen
function shenfen(){
	$.ajax({
		type:'post',
		url:orgBusinessHost+'archivesInfoManager.action',
		cache:false,
		async:false, 
		data:{action : "getArchivesInfoList",
			"pageBean.curPage":1,
			"archivesInfo.archivesMobile":135,
			"pageBean.pageSize":12
		},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}



























