//获取所有订单列表
function getOrderList(){
	$.ajax({
		type:'post',
		url:'getOrderInfo.action',
		cache:false,
        async:false, 
		data:{action:"orderList"},
		success:function(result){
			document.writeln(JSON.stringify(result));
			for(var i=0;i<result.someOrderList.length;i++){
//				ale(result.someOrderList.toString());
				var strList = result.someOrderList[i].toString().split(",");
				ale("订单ID:"+strList[0]);
				ale("订单时间:"+strList[1]);
				ale("订单状态:"+strList[2]);
				ale("订单应付金额:"+strList[6]);
				ale("订单产品名:"+strList[5]);
				ale("订单产品图:"+strList[7]);
				ale("医生名:"+strList[8]);
				ale("医生职称:"+strList[9]);
			}
			
		},
		error: function () {
			layer();
		}
});
}


//获取订单详情
function getOrderDetail(){
	$.ajax({
		type:'post',
		url:'getOrderInfo.action',
		cache:false,
        async:false, 
		data:{action:"orderDetail", orderId:"1"},
		success:function(result){
			ale(result.orderDetail.toString());
			var strList = result.orderDetail.toString().split(",");
			ale("日期："+strList[0]);
			ale("订单号："+strList[1]);
			ale("订单状态："+strList[2]);
			ale("家长姓名："+strList[7]);
			ale("电话："+strList[8]);
			ale("省："+strList[9]);
			ale("市："+strList[10]);
			ale("区："+strList[11]);
			ale("街道："+strList[12]);
			ale("详细地址："+strList[13]);
			ale("产品名："+strList[14]);
			ale("产品图："+strList[15]);
			ale("医生名："+strList[16]);
			ale("医生职称："+strList[17]);
			ale("产品金额："+strList[3]);
			ale("优惠券金额："+strList[18]);
			ale("积分抵现："+strList[4]);
			ale("余额："+strList[5]);
			ale("支付方式："+strList[6]);
		},
		error: function () {
			layer();
		}

	});
}


function myAccount(){
	$.ajax({
		type:'post',
		url:'accountManage.action',
		cache:false,
        async:false, 
		data:{action:"total"},
		success:function(result){
			ale("余额："+result.userBalance);
			ale("积分："+result.userPoint);
		},
		error: function () {
			layer();
		}
	});
}

function balance(){
	$.ajax({
		type:'post',
		url:'accountManage.action',
		cache:false,
        async:false, 
		data:{action:"balance"},
		success:function(result){
			ale(result.toString());
		},
		error: function () {
			layer();
		}
	});
}
function point(){
	$.ajax({
		type:'post',
		url:'accountManage.action',
		cache:false,
        async:false, 
		data:{action:"point"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}
function coupon(){
	$.ajax({
		type:'post',
		url:'accountManage.action',
		cache:false,
        async:false, 
		data:{action:"coupon"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}

function available(){
	$.ajax({
		type:'post',
		url:'accountManage.action',
		cache:false,
        async:false, 
		data:{action:"available"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}

function getCoupon(){
	$.ajax({
		type:'post',
		url:'accountManage.action',
		cache:false,
        async:false, 
		data:{action:"takeCoupon",couponId:"1"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}

function consult(){
	$.ajax({
		type:'post',
		url:'consultDoctorManage.action',
		cache:false,
        async:false, 
		data:{action:"getList"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}

function details(){
	$.ajax({
		type:'post',
		url:'consultDoctorManage.action',
		cache:false,
        async:false, 
		data:{action:"detail",logId:"2"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}

function doctorList(){
	$.ajax({
		type:'post',
		url:'getDoctorInfo.action',
		cache:false,
        async:false, 
		data:{action:"doctorList"},
		success:function(result){
			ale(result.toString());
		},error: function () {
			layer();
		}
	});
}


$.ajax({
	type:'post',
	url:'orderManage.action',
	cache:false,
    async:false, 
	data:{action:"change",orderstatus:"取消/确认",orderId:"订单ID"},
	success:function(result){
		ale(result.toString());
	},error: function () {
		layer();
	}
});

var clinicHost = "http://127.0.0.1:8088/kybaby/clinic/";
//==========================
function clinic_1(){
	$.ajax({
		type:'post',
		url:clinicHost+'clinicBooking.action',
		cache:false,
	    async:false, 
		data:{action:"recommendedDoctorList"},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer();
        }
	});
}
//医生设置的门诊信息集合  doctorMorePracticeFoList
function clinic_2(){
	$.ajax({
		type:'post',
		url:clinicHost+'clinicBooking.action',
		cache:false,
	    async:false, 
		data:{action:"getDoctorMorePracticeList",
			"doctorInfo.id":38},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
			layer();
        }
	});
}
//保存门诊订单
function clinic_3(){
	var arr = new Array();
	var obj1 = {"id":"","otherPhone":"13541280876","otherName":"小明","isChoose":"Y"};
	var obj2 = {"id":"","otherPhone":"13412345654","otherName":"小红","isChoose":"N"};
	arr.push(obj1);
	arr.push(obj2);
	$.ajax({
		type:'post',
		url:clinicHost+'clinicOrder.action',
		cache:false,
	    async:false, 
		data:{action:"saveOrUpdateClinicOrder",
			"othersCollectorJson":JSON.stringify(arr),
			"doctorInfo.id":38,
			"orderInfoClinic.appointmentDate":"2016-01-29",
			"orderInfoClinic.appointmentBeganTime":"08:15",
			"orderInfoClinic.appointmentEndTime":"08:30",
			"orderInfoClinic.clinicAddress":"地址1"
			},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
			layer();
        }
	});
}
//得到订单列表
function clinic_4(){
	$.ajax({
		type:'post',
		url:clinicHost+'clinicOrder.action',
		cache:false,
	    async:false, 
		data:{action:"getClinicOrderList",
			"doctorInfo.id":38,
			"orderInfoClinic.appointmentDate":"2016-01-30",
			"orderInfoClinic.appointmentBeganTime":"08:00",
			"orderInfoClinic.clinicAddress":"地址1"
			},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
			layer();
        }
	});
}
//检查预约时间是否备战
function clinic_5(){
	$.ajax({
		type:'post',
		url:clinicHost+'clinicOrder.action',
		cache:false,
	    async:false, 
		data:{action:"checkTimeIsUsed",
			"doctorInfo.id":38,
			"orderInfoClinic.appointmentDate":"2016-02-03",
			"orderInfoClinic.appointmentBeganTime":"08:15"
			},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
			layer();
        }
	});
}
//验证用户手机号
function checkMobile(){
	var userPhone = $("#userPhone").val();
	if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(userPhone))){
		alert(userPhone+"--false");
		return false;
	}else{
		alert(userPhone+"--true");
		return true;
	}
}
//保存评价信息
function clinic_6(){
	$.ajax({
		type:'post',
		url:clinicHost+'clinicOrder.action',
		cache:false,
	    async:false, 
		data:{action:"saveOrUpdateClinicOrder",
			"orderInfoClinic.orderStatus":"评价医生",//这个固定的
			"orderInfoClinic.id":"2",
			"evaluateClinic.evaluateLevel":"0",//评价级别（0：好；1：差）
			"evaluateClinic.evaluateContent":"评价内容"
			},
		success:function(result){
			alert(result.mes);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(errorThrown);
			layer();
        }
	});
}
//门诊医生列表
function clinic_7(){
	var host="";
//	$.ajax({
//		type:'post',
//		url:clinicHost+'clinicBooking.action',
//		cache:false,
//	    async:false, 
//		data:{action:"getClinicDoctorInfo",
//			"doctorInfo.id":38//得到单个医生信息传此参数，医生列表不需要传参
//			},
//		success:function(result){
//			alert(result.mes);
//		},
//		error: function(XMLHttpRequest, textStatus, errorThrown) {
//            alert(XMLHttpRequest.status);
//            alert(errorThrown);
//        }
//	});
	alert(111);
    $.ajax({
        type:'post',
        async:false,
        url:host+'userManage.action',
        data:{
            action:"getPhone",
            "refereeUserPhone":"d=38"
        },
        success:function(result){
        	alert(result.refereeUserPhone);
            //console.log(result);
        },error: function () {
			layer();
		}
    });
}
var orgAndBusinessHost = "http://127.0.0.1:8088/kybaby/orgbusiness/";
//机构列表
function org_1(){
	$.ajax({
		type:'post',
		async:false,
		url:orgAndBusinessHost+'orgManage.action',
		data:{
			action:"getHospitalInfoList",
			"hospitalBasicInfo.hospitalType":"社区医院"
		},
		success:function(result){
			alert(result.mes);
			console.log(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
	      //alert(XMLHttpRequest.status);
	      //alert(errorThrown);
			layer();
	    }
	});
}

//儿保信息初始化
function org_init(){
	$.ajax({
		type:'post',
		async:false,
		url:orgAndBusinessHost+'orgManage.action',
		data:{
			action:"getHospitalInfo",
			"hospitalBasicInfo.id":"2"
		},
		success:function(result){
			alert(result.mes);
			console.log(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//alert(XMLHttpRequest.status);
			//alert(errorThrown);
			layer();
		}
	});
}
//儿保预约信息保存
function saveChildCare(){
	$.ajax({
		type:'post',
		async:false,
		url:orgAndBusinessHost+'orgBoManage.action',
		data:{
			action:"saveOrUpdateUserChildcareAppointmentInfo",
			"hospitalBasicInfo.id":"2",
			"userChildcareAppointmentInfo.organChildcareOpenResources.id":"1",
			"userChildcareAppointmentInfo.organChildcareOpenResourcesDatail.id":"5"
		},
		success:function(result){
			alert(result.mes);
			console.log(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//alert(XMLHttpRequest.status);
			//alert(errorThrown);
			layer();
		}
	});
}
//儿保预约列表
function list_init(){
	$.ajax({
		type:'post',
		async:false,
		url:orgAndBusinessHost+'orgBoManage.action',
		data:{
			action:"getUserChildcareAppointmentInfoList"
		},
		success:function(result){
			alert(result.mes);
			console.log(result);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//alert(XMLHttpRequest.status);
			//alert(errorThrown);
			layer();
		}
	});
}
var hostOrgbusiness="http://127.0.0.1:8088/kybaby/orgbusiness/";
function jimian(){
	  $.ajax({
          type: 'post',
          url: hostOrgbusiness + 'vaccineManage.action',
          cache: false,
          async: false,
          data: {
              action: "getUserInitInoculationInfo"
          },
          success: function (result) {
//              order_id=result.userInoculationAppointmentInfo.id;
//              window.location.href = 'orgservice_message.html';
          },
          error: function () {
			  layer();
          }
      });

}
//微信接口
function wx(){
	alert(11);
	$.ajax({
        url:'http://dev.qiyico.com/kybaby/wx/getPrepayId.action', // 跳转到 action
        data:{action:"getPrepayId",mchId:mchId,tradeNo:orderNum,remoteIp:ip,nonceStr:nonceStr,signStr:signString,userOpenId:userOpenId,body:orderDesc,totalFee:orderAmount},
        cache:false,
        async:false,
        success:function(result) {
            resultXml=result.result;
            prepayId= resultXml.substring(resultXml.indexOf("<prepay_id>")+20,resultXml.indexOf("</prepay_id>")-3);
            alert(prepayId);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            //ale(XMLHttpRequest.status);
            //ale(XMLHttpRequest.readyState);
            //ale(textStatus);
			layer();
        }
    });
}
