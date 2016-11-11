package com.kybaby.newbussiness.spservice.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.EvaluateClinic;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;
import com.kybaby.newbussiness.spservice.common.BooleanMsg;
import com.kybaby.newbussiness.spservice.common.SpServiceConstant;
import com.kybaby.newbussiness.spservice.domain.SpCheckRecord;
import com.kybaby.newbussiness.spservice.domain.SpCheckReport;
import com.kybaby.newbussiness.spservice.domain.SpCostInfo;
import com.kybaby.newbussiness.spservice.domain.SpDoctorAdviceInfo;
import com.kybaby.newbussiness.spservice.domain.SpHealthcardManager;
import com.kybaby.newbussiness.spservice.domain.SpInspectInfo;
import com.kybaby.newbussiness.spservice.domain.SpInspectRecord;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.newbussiness.spservice.domain.SpVisitRecord;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MsgUtil;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SpInterfaceAction extends NewBaseAction{
	private HospitalBasicInfo hospitalBasicInfo;
	private SpHealthcardManager spHealthcardManager;
	private Long orderId;
	private String deptFlag;//部门标识：1为儿保2为儿科
	private String mess;
	private Long spQueryMessageID;
	private String startTime;
	private String endTime;
	private String documentNo;
	private String timeFlag;
	private Integer pageNo;
	private Long visitCardID;
	private String costType;
	private String spOrgID;
	/**
	 * 门诊订单信息
	 */
	private SpRegisterOrderDetail spRegisterOrderDetail;
	
	private OrderInfoClinic orderInfoClinic;
	private List<SpDoctorAdviceInfo> doctorAdviseInfos;
	private SpDoctorAdviceInfo doctorAdviseInfo;
	
	private List<SpInspectRecord> spInspectRecords;
	private SpInspectRecord spInspectRecord;
	private SpInspectInfo spInspectInfo;
	
	private List<SpCostInfo> spCostInfos;
	private SpCostInfo spCostInfo;
	
	private List<SpCheckRecord> spCheckRecords;
	private SpCheckRecord spCheckRecord;
	private SpCheckReport saveSpCheckReport;
	
	private List<SpVisitRecord> saveSpVisitRecords;
	private SpVisitRecord spVisitRecord;
	
	private List<SpHealthcardManager> healthCardOfUserOwneds;
	/**
	 * 家医儿保预约记录
	 */
	private	UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 其他联系人信息json串
	 */
	private String othersCollectorJson;
	/**
	 * 订单评价信息
	 */
	private EvaluateClinic evaluateClinic;
	/**
	 * 健康卡号ID
	 */
	private Long healthCardID;
	/**
	 * 得到用户一天的门诊预约次数
	 * @param userInfo
	 * @return
	 */
	private long getClinicLimited(UserInfo userInfo){
		String flagLimt3 = ConstantManage.HASE_BOOKED_CLINIC_ORDER+","+
				ConstantManage.HASE_FINISHED_CLINIC_ORDER+","+
				ConstantManage.HASE_MEET_CLINIC_ORDER+","+
				ConstantManage.HASE_EVALUATED_CLINIC_ORDER;
		long count=0;
		OrderInfoClinic oic = new OrderInfoClinic();
		oic.setAppointmentDate(orderInfoClinic.getAppointmentDate());
		oic.setUserInfo(userInfo);
		List<OrderInfoClinic> orderList = this.clinicOrderService.getOrderInfoClinicList(oic);
		if(orderList != null){
			for(OrderInfoClinic order : orderList){
				if(flagLimt3.indexOf(order.getOrderStatus()) > -1){
					count++;
				}
			}
		}
		return count;
	}
	/**
	 * 保存或更新其他联系人
	 */
	@SuppressWarnings("unused")
	private UserInfo saveOrUpdateOthers(String othersCollectorJson,OrderInfoClinic newOrder,UserInfo userInfo){
		JSONArray array = JSONArray.fromObject(othersCollectorJson); 
		System.out.println(array);
		for(int i = 0; i < array.size(); i++){ 
			JSONObject jo = array.getJSONObject(i);
			ClinicOtherContactsInfo other = new ClinicOtherContactsInfo();
			String id = jo.get("id")==null?null:jo.get("id").toString().trim();
			if(id == null || "".equals(id)){
				other.setId(null);
			}else{
				other.setId(Long.valueOf(id));
			}
			other.setOtherPhone(jo.get("otherPhone")==null?"":jo.get("otherPhone").toString().trim());
			other.setOtherName(jo.get("otherName")==null?"":jo.get("otherName").toString().trim());
			other.setIsChoose(jo.get("isChoose")==null?"":jo.get("isChoose").toString().trim());
			if("Y".equals(jo.get("isChoose").toString())){
				userInfo.setPhone(jo.get("otherPhone")==null?"":jo.get("otherPhone").toString().trim());
				userInfo.setParentName(jo.get("otherName")==null?"":jo.get("otherName").toString().trim());
			}
			other.setOrderInfoClinic(orderInfoClinic);
			other.setUserInfo(userInfo);
			this.clinicOrderService.saveOrUpdateClinicOtherContactsInfo(other);
		}
		return userInfo;
	}
	/**
	 * 得到用户的会员特权
	 * @param userInfo
	 * @return
	 */
	private List<String> getMemRight(UserInfo userInfo){
		List<String> list = new ArrayList<String>();
		List<MemberManage> memberList = this.memberService.getMemberManageList(userInfo);
		if(memberList != null){
			for(MemberManage mem : memberList){
				String effectEndDate = mem.getEffectEndDate()+" 00:00:00";
				if (DateManage.isCompareDates(DateManage.getStrToDateTime(effectEndDate), new Date())) {
					List<MemberTypeRule> typeRuleList = this.memberService.getMemberTypeRuleList(mem.getMemberType(),null);
					if(typeRuleList != null){
						for(MemberTypeRule mtr : typeRuleList){
							MemberRule rule = mtr.getMemberRule();
							MemberType card = mtr.getMemberType();
							if("CLINICFREE".equals(card.getMemberCardCode().trim().toUpperCase()) && 
									"FREE".equals(rule.getRuleCode().trim().toUpperCase())){
								list.add("免费");
							}
						}
					}
				} 
			}
		}
		return list;
	}
	public String saveOrUpdatePidiatricsClinicOrder(){
		
		spOrgID = SpServiceConstant.NINE_HOSPITAL_CODE;
		
		Long userId = queryUserID();
		if(userId==null){
			if(userInfo != null && userInfo.getId() != null){
				userId = userInfo.getId();
				System.out.println("====本地存储 ClinicOrderAction userInfo.getId();====="+userId);
			}else{
				mess="请登录";
				return "fail";
			}
		}
		try{
			System.out.println("门诊预约操作状态==="+orderInfoClinic.getOrderStatus());
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			//为了显示其他联系人用
			UserInfo showUserInfo = userInfo;
			this.doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorInfo.getId());
			if(orderInfoClinic.getId() == null) { //保存添加
				
				//----------------------------------------------新增加代码,生成订单前,判断中联是否有号源
				boolean hasSourseWithRegister = 
						spInterfaceService.hasSourseWithRegister(spOrgID,
								SpServiceConstant.DEPT_NAME_PEADIATRICS, doctorInfo.getDoctorName());
				if(!hasSourseWithRegister) {
					this.mess = "创建订单失败,没有号源";
					return "fail";
				}
				//----------------------------------------------新增加代码
				
				
				//检查当前用户的未付款订单中是否已存在相同条件的订单
				List<OrderInfoClinic> orderList = this.clinicOrderService.checkOrderIsExist
						(orderInfoClinic.getAppointmentDate(), orderInfoClinic.getAppointmentBeganTime(),
								orderInfoClinic.getClinicAddress(), doctorInfo, userInfo);
				if(orderList != null){
					mess = "订单已存在，请查看‘我的订单’";
					orderInfoClinic.setId(orderList.get(0).getId());
					return "fail";
				}
				//一天只能有三个有效订单
				long count = this.getClinicLimited(userInfo);
				if(count == 3L){
					mess = "您今天的门诊预约量已超过规定次数";
					return "fail";
				}
				String orderNum = String.valueOf(System.currentTimeMillis());
				orderInfoClinic.setOrderNum(orderNum);
				orderInfoClinic.setDoctorInfo(doctorInfo);
				orderInfoClinic.setUserInfo(userInfo);
				orderInfoClinic.setOrderType("普通订单");
				//得到加号门诊费用
				OrderInfoClinic order = new OrderInfoClinic();
				DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
				
				//doctorMorePractice.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
				doctorMorePractice.setClinicAddress(orderInfoClinic.getClinicAddress());
				doctorMorePractice.setClinicDate(orderInfoClinic.getAppointmentDate());
				DoctorMorePractice old = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo).get(0);
				
				order.setClinicOrgType(orderInfoClinic.getClinicOrgType());
				if(ConstantManage.CLINIC_ORG_TYPE_1.equals(orderInfoClinic.getClinicOrgType())){
					//判断坐诊医生是否下班，下班后不能再约
					if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(old.getOrgClinicStatus())){
						mess = "医生已下班请选择其他时间预约";
						return "fail";
					}
					order.setClinicAddress(old.getDoctorMorePracticeOrgInfo().getId().toString());
				}
				HospitalPosition position = this.clinicOrderService.getHospitalPositionByDoctor(doctorInfo,order);
				
				String totalPrice = "";
				String commission = "";
				//0为本单位门诊，1为外单位门诊
				if (ConstantManage.CLINIC_ORG_TYPE_0.equals(orderInfoClinic.getClinicOrgType())) {
					totalPrice = (position == null?"0":position.getClinicMoney());
					commission = (position == null?"0":position.getCommission());
				} else {
					totalPrice = (position == null?"0":position.getClinicMoneyOut());
					commission = (position == null?"0":position.getCommissionPerCase());
				}
				orderInfoClinic.setHistoryPrice(totalPrice);
				//判断是否是会员免费
				List<?> rightList = this.getMemRight(userInfo);
				if(!rightList.isEmpty()){
					if(rightList.contains("免费")){
						totalPrice = "0";
						orderInfoClinic.setDiscountMoney("0");
					}
				}
				orderInfoClinic.setTotalPrice(totalPrice);
				orderInfoClinic.setCommission(commission);
				
				//保存或更新订单
				orderInfoClinic.setOrderStatus(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
				Long orderId = this.clinicOrderService.saveClinicOrder(orderInfoClinic);
				this.orderInfoClinic = this.clinicOrderService.getOrderInfoClinicById(orderId);
				//保存其他联系人信息
				//showUserInfo = this.saveOrUpdateOthers(othersCollectorJson,orderInfoClinic,userInfo);
				this.orderInfoClinic.setUserInfo(showUserInfo);
				
				
				
				//-----------------------------------------创建挂号订单明细
				BooleanMsg booleanMsg = spInterfaceService.saveRegisterOrderData(orderId, "2",spOrgID);
				if(booleanMsg.isTrue()){
					mess="成功";
				}else{
					mess = booleanMsg.getMsg();
				}
				//-----------------------------------------创建挂号订单明细
				
			} else {//更新订单
				System.out.println("门诊进到更新订单操作。。。。。。。。。。。。。。。。。");
				OrderInfoClinic oldOrder = this.clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
				Long orderId = oldOrder.getId();
				String orderStatus = "";
				if("下一步".equals(orderInfoClinic.getOrderStatus())){
					//得到加号门诊费用
					OrderInfoClinic order = new OrderInfoClinic();
					DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
					
					//doctorMorePractice.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
					doctorMorePractice.setClinicAddress(orderInfoClinic.getClinicAddress());
					doctorMorePractice.setClinicDate(orderInfoClinic.getAppointmentDate());
					DoctorMorePractice old = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo).get(0);
					
					order.setClinicOrgType(orderInfoClinic.getClinicOrgType());
					if(ConstantManage.CLINIC_ORG_TYPE_1.equals(orderInfoClinic.getClinicOrgType())){
						//判断坐诊医生是否下班，下班后不能再约
						if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(old.getOrgClinicStatus())){
							mess = "医生已下班请选择其他时间预约";
							return "fail";
						}
						order.setClinicAddress(old.getDoctorMorePracticeOrgInfo().getId().toString());
					}
					
					HospitalPosition position = this.clinicOrderService.getHospitalPositionByDoctor(doctorInfo,order);
					
					String totalPrice = "";
					String commission = "";
					//0为本单位门诊，1为外单位门诊
					if (ConstantManage.CLINIC_ORG_TYPE_0.equals(orderInfoClinic.getClinicOrgType())) {
						totalPrice = (position == null?"0":position.getClinicMoney());
						commission = (position == null?"0":position.getCommission());
					} else {
						totalPrice = (position == null?"0":position.getClinicMoneyOut());
						commission = (position == null?"0":position.getCommissionPerCase());
					}
					
					BeanUtils.copyProperties(orderInfoClinic, oldOrder, new String[]{"id","userInfo","orderNum"});
					oldOrder.setTotalPrice(totalPrice);
					oldOrder.setCommission(commission);;
					oldOrder.setOrderStatus(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
					//更新订单
					this.clinicOrderService.updateClinicOrder(oldOrder);
					//保存其他联系人信息
					//showUserInfo = this.saveOrUpdateOthers(othersCollectorJson,orderInfoClinic,userInfo);
				}
				else if("用户取消".equals(orderInfoClinic.getOrderStatus())){
					try{
						/*
						 * 取消苏坡挂号
						 */
						boolean cancelRegister_isOK =  spInterfaceService.cancelRegister(oldOrder.getId(), "2");
						if(!cancelRegister_isOK){
							mess = (String) MsgUtil.get();
							return "fail";
						}
						mess = "成功";
					}catch(Exception e){
						mess = (String) MsgUtil.get();
						return "fail";
					}
					//判断订单门诊时间是否大于当前时间 （是否过期），如是，则不能进行退款操作
					String appointmentDateTime = oldOrder.getAppointmentDate() + " " + oldOrder.getAppointmentBeganTime() + ":00";
					if (!DateManage.isCompareDates(DateManage.getStrToDateTime(appointmentDateTime), new Date())) {
						mess = "时间过期";
					} else {
						orderStatus = ConstantManage.USER_CANCLE_CLINIC_ORDER;
						//将已预约时间设置为可用
						this.clinicOrderService.updateDoctorClinicTimeSegmentStatus(doctorInfo, "Y", oldOrder.getAppointmentDate(), 
								oldOrder.getAppointmentBeganTime());
						oldOrder.setOrderStatus(orderStatus);
						this.clinicOrderService.updateClinicOrder(oldOrder);
						//更新用户余额
						Double realPrice = 
								oldOrder.getRealPrice()==null?0d:Double.valueOf(oldOrder.getRealPrice());
						//添加用户用钱记录
						if(realPrice.doubleValue() != 0D){
							Double accountBalance = userInfo.getAccountBalance();
							accountBalance = accountBalance.doubleValue()+realPrice.doubleValue();
							accountBalance = MyMath.round(accountBalance, 2);
							userInfo.setAccountBalance(accountBalance);
							this.userInfoBo.updateUser(userInfo);
							this.userAccountBo.addNewUserAccount(userId, realPrice, "+", "门诊订单退款", oldOrder.getOrderNum());
						}
						String toDoctorSms="亲爱的"+doctorInfo.getDoctorName()+"医生，" + oldOrder.getAppointmentDate() 
								+ " " + oldOrder.getAppointmentBeganTime() +"的门诊订单，用户已取消！";
						System.out.print("cancle toDoctor=="+toDoctorSms);
						SendSms sendSms = new SendSms();
						sendSms.sendInfo(doctorInfo.getDoctorPhone(), toDoctorSms);
					}
					
				}else if("付款成功".equals(orderInfoClinic.getOrderStatus())){
					orderStatus = ConstantManage.HASE_BOOKED_CLINIC_ORDER;
					//将已预约时间设置为不可用
					this.clinicOrderService.updateDoctorClinicTimeSegmentStatus(oldOrder.getDoctorInfo(), "N", oldOrder.getAppointmentDate(), oldOrder.getAppointmentBeganTime());
					
					//BeanUtils.copyProperties(orderInfoClinic, oldOrder, new String[]{"id","userInfo","orderNum"});
					//得到加号门诊费用
					//Position position = this.clinicOrderService.getPositionByDoctor(oldOrder.getDoctorInfo());
					//String totalPrice = position == null?"0":position.getClinicMoney();
					//oldOrder.setTotalPrice(totalPrice);
					//oldOrder.setDoctorInfo(doctorInfo);
					
					//用户的订单金额
					double totalPriceCalc = Double.valueOf(oldOrder.getTotalPrice());
					//用户使用的余额
					double useRemainBalanceCalc = orderInfoClinic.getUseRemainBalance()==null?0d:Double.valueOf(orderInfoClinic.getUseRemainBalance());
					//用户使用的福利金额
					double discountMoneyCalc = orderInfoClinic.getDiscountMoney()==null?0d:Double.valueOf(orderInfoClinic.getDiscountMoney());
					//最终需要支付的金额
					double chargeBalance = totalPriceCalc - useRemainBalanceCalc - discountMoneyCalc;
					chargeBalance = MyMath.round(chargeBalance, 2);
					//用户使用账户里的余额完全支付订单金额，不需要在线支付了
					if (chargeBalance <= 0D) {
						UserInfo currentUserInfo = this.userInfoBo.getUserById(oldOrder.getUserInfo().getId());
						//用户账户的余额
						double accountBalance = currentUserInfo.getAccountBalance();
						double balance = accountBalance - useRemainBalanceCalc;
						balance = MyMath.round(balance, 2);
						currentUserInfo.setAccountBalance(balance<0d?0d:balance);
						//更新用户余额
						this.userInfoBo.updateUser(currentUserInfo);
						//添加用户余额支付明细
						this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付门诊订单", oldOrder.getOrderNum());
						/**
						 * 更新订单的信息
						 */
						oldOrder.setOrderStatus(orderStatus);
						oldOrder.setPayMethod("余额支付");//用户使用的支付方式
						oldOrder.setUseRemainBalance(String.valueOf(useRemainBalanceCalc));//用户使用的余额数
						oldOrder.setRealPrice(String.valueOf(useRemainBalanceCalc));//实付金额
						oldOrder.setDiscountMoney(String.valueOf(discountMoneyCalc));//福利优惠抵扣数
						if(orderInfoClinic.getPayMethod() != null){//判断是否金控对接
							oldOrder.setPayMethod(orderInfoClinic.getPayMethod());
						}
						this.clinicOrderService.updateClinicOrder(oldOrder);	
					
					} 
					//用户还需要在线支付
					else {
						UserInfo currentUserInfo = this.userInfoBo.getUserById(oldOrder.getUserInfo().getId());
						//用户使用了账户中部分的余额
						if (useRemainBalanceCalc !=  0D) {
							//用户账户的余额
							double accountBalance = currentUserInfo.getAccountBalance();
							double balance = accountBalance - useRemainBalanceCalc;
							balance = MyMath.round(balance, 2);
							currentUserInfo.setAccountBalance(balance<0d?0d:balance);
							//更新用户余额
							this.userInfoBo.updateUser(currentUserInfo);
							//添加用户用钱明细
							this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付门诊订单", oldOrder.getOrderNum());
						}
						//在线支付明细记录
						this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), chargeBalance, "-", "在线支付门诊订单", oldOrder.getOrderNum());
						/**
						 * 更新订单的信息
						 */
						oldOrder.setOrderStatus(orderStatus);
						oldOrder.setPayMethod("在线支付");
						oldOrder.setUseRemainBalance(String.valueOf(useRemainBalanceCalc));//用户使用的余额数
						double realPrice = totalPriceCalc - discountMoneyCalc;
						realPrice = MyMath.round(realPrice, 2);
						oldOrder.setRealPrice(String.valueOf(realPrice));//实付金额
						oldOrder.setDiscountMoney(String.valueOf(discountMoneyCalc));//福利优惠抵扣数
						if(orderInfoClinic.getPayMethod() != null){//判断是否金控对接
							oldOrder.setPayMethod(orderInfoClinic.getPayMethod());
						}
						this.clinicOrderService.updateClinicOrder(oldOrder);	
					}
					
					//-----------------------------------------调用中联接口，创建挂号记录
					BooleanMsg booleanMsg = null;
					try{
						booleanMsg = spInterfaceService.saveSpAppointmentRecord(orderId,"2",healthCardID,spHealthcardManager);
						if(booleanMsg.isTrue()){
							mess="成功";
						}
						else{
							spInterfaceService.cancelPeadiatricsOrder(userId, userInfo,doctorInfo,oldOrder,ConstantManage.REGISTER_FAIL);	
							mess="挂号失败,支付金额已退还至余额中";
						}
					}catch(Exception e){
						spInterfaceService.cancelPeadiatricsOrder(userId, userInfo,doctorInfo,oldOrder,ConstantManage.REGISTER_FAIL);
						mess="挂号失败,支付金额已退还至余额中";
					}
					//-----------------------------------------调用中联接口，创建挂号记录
					if(booleanMsg!=null&&booleanMsg.isTrue()){
						sendMessage(oldOrder);							
					}
					
				}else if("评价医生".equals(orderInfoClinic.getOrderStatus())){
					orderStatus = ConstantManage.HASE_EVALUATED_CLINIC_ORDER;
					oldOrder.setOrderStatus(orderStatus);
					this.clinicOrderService.updateClinicOrder(oldOrder);
					//保存评价相关信息
					evaluateClinic.setOrderInfoClinic(oldOrder);
					evaluateClinic.setEvaluateStatus("Y");
					this.clinicOrderService.saveEvaluateClinic(evaluateClinic);
				}
				this.orderInfoClinic =  this.clinicOrderService.getOrderInfoClinicById(orderId);
				orderInfoClinic.setUserInfo(showUserInfo);
			}
		}catch(Exception e){
			mess = "失败";
			System.out.println("===门诊订单保存更新 err==="+DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String handleUserChildcareAppointmentInfo() throws ParseException{
		
		spOrgID = SpServiceConstant.SP_HOSPITAL_CODE;
		
		Long userId = queryUserID();
		if(userId==null){
			mess="请登录";
			return "fail";
		}
		System.out.println("进入儿保付费订单管理。。。。。。");
		try {
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			//当前时间
			String rightNow = CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
			//婴儿出生到现在的月份
			String monthAge = String.valueOf(CalculationMethod.getMonthSpace(userInfo.getBirthday(), rightNow));
			
			if(userChildcareAppointmentInfo.getId() == null){//生成订单
				//----------------------------------------------新增加代码,生成订单前,判断中联是否有号源
				doctorInfo = spInterfaceService.getObject(doctorInfo.getId(), DoctorInfo.class);
				boolean hasSourseWithRegister = 
						spInterfaceService.hasSourseWithRegister(spOrgID,
								SpServiceConstant.DEPT_NAME_CHILD_CARE, doctorInfo.getDoctorName());
				if(!hasSourseWithRegister) {
					this.mess = "创建订单失败,没有号源";
					return "fail";
				}
				
				//----------------------------------------------新增加代码
				
				Long detailId =	userChildcareAppointmentInfo.getOrganChildcareOpenResourcesDatail().getId();
				OrganChildcareOpenResourcesDatail oldDetail = 
						this.openBusinessManagerService.getOrganChildcareOpenResourcesDatailById(detailId);
				OrganChildcareOpenResources oldOpenResources = 
						this.openBusinessManagerService.getOrganChildcareOpenResourcesById
						(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getId());
				//儿保医生薪酬记录
				DoctorMoneyRecord dmr = this.openBusinessManagerService.getDoctorMoneyRecordBySomething
						(null, doctorInfo, oldOpenResources.getOpenDate());
				if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
					if("N".equals(oldDetail.getIsCanUse())){
						this.mess = "所选时间已被预约";
						return "fail";
					}
				}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
					if("0".equals(oldDetail.getGeneralSurplusNum())){
						this.mess = "没有号源可约";
						return "fail";
					}
				}
				//检查当前用户的未付款订单中是否已存在相同条件的订单
				Boolean flag = this.childCareChargeService.checkChildCareOrderIsExist
						(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getId(), 
								userChildcareAppointmentInfo.getOrganChildcareOpenResourcesDatail().getId(), 
								userChildcareAppointmentInfo.getHospitalBasicInfo().getId(), doctorInfo.getId(), userId);
				if(flag){
					mess = "订单已存在，请查看‘我的预约’";
					return "fail";
				}
				String orderNum = String.valueOf(System.currentTimeMillis());
				userChildcareAppointmentInfo.setOrderNum(orderNum);
				userChildcareAppointmentInfo.setDoctorInfo(doctorInfo);
				userChildcareAppointmentInfo.setUserInfo(userInfo);
				userChildcareAppointmentInfo.setStatus(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
				userChildcareAppointmentInfo.setTotalPrice(dmr.getMoneyPer());
				userChildcareAppointmentInfo.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				userChildcareAppointmentInfo.setDiscountMoney("0");
				userChildcareAppointmentInfo.setCurrentMonthAge(monthAge);
				Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
				userChildcareAppointmentInfo.setId(id);
				
				//-----------------------------------------创建挂号订单明细
				BooleanMsg booleanMsg = spInterfaceService.saveRegisterOrderData(id, "1",spOrgID);
				if(booleanMsg.isTrue()){
					mess = "成功";
				}else{
					mess = booleanMsg.getMsg();
				}
				//-----------------------------------------创建挂号订单明细
			}else{//更新订单
				UserChildcareAppointmentInfo oldOrder = this.openBusinessManagerService.
						getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
				OrganChildcareOpenResourcesDatail oldDetail = oldOrder.getOrganChildcareOpenResourcesDatail();
				//OrganChildcareOpenResources oldOpenResources = oldOrder.getOrganChildcareOpenResources();
				oldOrder.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				oldOrder.setCurrentMonthAge(monthAge);
				
				if("付款成功".equals(userChildcareAppointmentInfo.getStatus())){
					System.out.println("儿保付费进到付款成功，更新订单。。。。。");
					//用户的订单金额
					double totalPriceCalc = Double.valueOf(oldOrder.getTotalPrice());
					//用户使用的余额
					double useRemainBalanceCalc = userChildcareAppointmentInfo.getUseRemainBalance()==null?0d:Double.valueOf(userChildcareAppointmentInfo.getUseRemainBalance());
					//用户使用的福利金额
					double discountMoneyCalc = userChildcareAppointmentInfo.getDiscountMoney()==null?0d:Double.valueOf(userChildcareAppointmentInfo.getDiscountMoney());
					//最终需要支付的金额
					double chargeBalance = totalPriceCalc - useRemainBalanceCalc - discountMoneyCalc;
					chargeBalance = MyMath.round(chargeBalance, 2);
					//用户使用账户里的余额完全支付订单金额，不需要在线支付了
					if (chargeBalance <= 0D) {
						//用户账户的余额
						double accountBalance = userInfo.getAccountBalance();
						double balance = accountBalance - useRemainBalanceCalc;
						balance = MyMath.round(balance, 2);
						userInfo.setAccountBalance(balance<0d?0d:balance);
						//更新用户余额
						this.userInfoBo.updateUser(userInfo);
						//添加用户余额支付明细
						this.userAccountBo.addNewUserAccount(userInfo.getId(), useRemainBalanceCalc, "-", "余额支付儿保订单", oldOrder.getOrderNum());
						/**
						 * 更新订单的信息
						 */
						oldOrder.setPayMethod("余额支付");//用户使用的支付方式
					}
					//用户还需要在线支付
					else {
						//用户使用了账户中部分的余额
						if (useRemainBalanceCalc !=  0D) {
							//用户账户的余额
							double accountBalance = userInfo.getAccountBalance();
							double balance = accountBalance - useRemainBalanceCalc;
							balance = MyMath.round(balance, 2);
							userInfo.setAccountBalance(balance<0d?0d:balance);
							//更新用户余额
							this.userInfoBo.updateUser(userInfo);
							//添加用户用钱明细
							this.userAccountBo.addNewUserAccount(userInfo.getId(), useRemainBalanceCalc, "-", "余额支付儿保订单", oldOrder.getOrderNum());
						}
						//在线支付明细记录
						this.userAccountBo.addNewUserAccount(userInfo.getId(), chargeBalance, "-", "在线支付儿保订单", oldOrder.getOrderNum());
						oldOrder.setPayMethod("在线支付");
					}
					/**
					 * 更新订单的信息
					 */
					oldOrder.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
					oldOrder.setUseRemainBalance(String.valueOf(useRemainBalanceCalc));//用户使用的余额数
					double realPrice =  totalPriceCalc - discountMoneyCalc;//实付金额为用户在线支付的及余额支付的
					if(realPrice <= 0d) realPrice=0d;
					realPrice = MyMath.round(realPrice, 2);
					oldOrder.setRealPrice(String.valueOf(realPrice));//实付金额
					oldOrder.setDiscountMoney(String.valueOf(discountMoneyCalc));//福利优惠抵扣数
					Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(oldOrder);
					userChildcareAppointmentInfo.setId(id);
					//将儿保资源设置为已用,或将剩余号源减去1
					if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						oldDetail.setIsCanUse("N");
					}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						Long generalSurplusNum = Long.valueOf(oldDetail.getGeneralSurplusNum());
						generalSurplusNum = generalSurplusNum.longValue() - 1;
						oldDetail.setGeneralSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
					}
					this.openBusinessManagerService.saveOrUpdateOrganChildcareOpenResourcesDatail(oldDetail);
					//zl
					
					//-----------------------------------------调用中联接口，创建挂号记录
					BooleanMsg booleanMsg = null;
					try{
						booleanMsg = spInterfaceService.saveSpAppointmentRecord(id,"1",healthCardID,spHealthcardManager);
						if(booleanMsg.isTrue()){
							mess="成功";
						}
						else{
							Long childOrderID = spInterfaceService.cancelChildCareOrder(userId, userInfo, oldOrder,ConstantManage.REGISTER_FAIL);
							userChildcareAppointmentInfo.setId(childOrderID);
							releaseChildCardResource(oldOrder, oldDetail);
							mess="挂号失败,支付金额已退还至余额中";
						}
					}catch(Exception e){
						Long childOrderID = spInterfaceService.cancelChildCareOrder(userId, userInfo, oldOrder,ConstantManage.REGISTER_FAIL);
						userChildcareAppointmentInfo.setId(childOrderID);
						//释放资源
						releaseChildCardResource(oldOrder, oldDetail);
						mess="挂号失败,支付金额已退还至余额中";
					}
					//-----------------------------------------调用中联接口，创建挂号记录
					//发送短信通知
					if(booleanMsg!=null&&booleanMsg.isTrue()){
						sendMessage(userInfo, oldOrder, oldDetail);
					}
					//购买成功后添加应做儿保项目记录信息
//					this.addOrganSetChildCareRecode(oldOrder);
				}else if("用户取消".equals(userChildcareAppointmentInfo.getStatus())){
					try{
						/*
						 * 取消苏坡挂号
						 */
						boolean cancelRegister_isOK = spInterfaceService.cancelRegister(oldOrder.getId(), "1");
						if(!cancelRegister_isOK){
							mess = (String) MsgUtil.get();
							return "fail";
						}
						mess = "成功";
					}catch(Exception e){
						mess = (String) MsgUtil.get();
						return "fail";
					}
					
					
					//判断订单门诊时间是否大于当前时间 （是否过期），如是，则不能进行退款操作
					String appointmentDateTime = oldOrder.getOrganChildcareOpenResources().getOpenDate() + " ";
					String smsTime = "";//短信提示时间
					if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						smsTime += appointmentDateTime + oldOrder.getOrganChildcareOpenResourcesDatail().getSegment() ;
						appointmentDateTime += oldOrder.getOrganChildcareOpenResourcesDatail().getSegment() + ":00";
					}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						smsTime += appointmentDateTime + oldOrder.getOrganChildcareOpenResourcesDatail().getOpenStartTime() + "-"+
								oldOrder.getOrganChildcareOpenResourcesDatail().getOpenEndTime() + "之间";
						appointmentDateTime += oldOrder.getOrganChildcareOpenResourcesDatail().getOpenStartTime() + ":00";
					}
					if (!DateManage.isCompareDates(DateManage.getStrToDateTime(appointmentDateTime), new Date())) {
						mess = "时间过期,无法取消";
						return "fail";
					} 
					oldOrder.setStatus(ConstantManage.USER_CANCLE_CLINIC_ORDER);
					Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(oldOrder);
					userChildcareAppointmentInfo.setId(id);
					//更新用户余额
					Double realPrice = oldOrder.getRealPrice()==null?0d:Double.valueOf(oldOrder.getRealPrice());
					//Double useRemainBalance = oldOrder.getUseRemainBalance()==null?0d:Double.valueOf(oldOrder.getUseRemainBalance());
					//realPrice += useRemainBalance;
					//添加用户用钱记录
					if(realPrice.doubleValue() != 0D){
						Double accountBalance = userInfo.getAccountBalance();
						accountBalance = accountBalance.doubleValue()+realPrice.doubleValue();
						accountBalance = MyMath.round(accountBalance, 2);
						userInfo.setAccountBalance(accountBalance);
						this.userInfoBo.updateUser(userInfo);
						this.userAccountBo.addNewUserAccount(userId, realPrice, "+", "儿保订单退款", oldOrder.getOrderNum());
					}
					String toDoctorSms="亲爱的"+oldOrder.getDoctorInfo().getDoctorName()+"医生，" + smsTime +"的儿保订单，用户已取消！";
					System.out.print("cancle toDoctor=="+toDoctorSms);
					SendSms sendSms = new SendSms();
					sendSms.sendInfo(oldOrder.getDoctorInfo().getDoctorPhone(), toDoctorSms);
					//释放儿保资源
					releaseChildCardResource(oldOrder, oldDetail);
				}else if("取消".equals(userChildcareAppointmentInfo.getStatus())){//未付款订单取消
					oldOrder.setStatus(ConstantManage.USER_CANCLE_CLINIC_ORDER);
					Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(oldOrder);
					userChildcareAppointmentInfo.setId(id);
				}
			}
		} catch (Exception e) {
			mess = "失败";
			System.out.println("儿保预约出错了。。。。。。。。。。。。。。。");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 释放儿保资源
	 * @param oldOrder 订单信息
	 * @param oldDetail 资源明细信息
	 */
	private void releaseChildCardResource(
			UserChildcareAppointmentInfo oldOrder,
			OrganChildcareOpenResourcesDatail oldDetail) {
		if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
			oldDetail.setIsCanUse("Y");
		}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
			Long generalSurplusNum = Long.valueOf(oldDetail.getGeneralSurplusNum());
			generalSurplusNum = generalSurplusNum.longValue() + 1;
			oldDetail.setGeneralSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
		}
		this.openBusinessManagerService.saveOrUpdateOrganChildcareOpenResourcesDatail(oldDetail);
	}

	/**
	 * 儿保发送信息
	 * @param userInfo
	 * @param oldOrder
	 * @param oldDetail
	 */
	private void sendMessage(UserInfo userInfo,
			UserChildcareAppointmentInfo oldOrder,
			OrganChildcareOpenResourcesDatail oldDetail) {
		SendSms ss = new SendSms();
		String smsTime = oldOrder.getOrganChildcareOpenResources().getOpenDate() + " ";
		String contecnt = "亲爱的用户，您已预约"+oldOrder.getDoctorInfo().getDoctorName()+"医生的儿保服务，请于";
		if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
			smsTime += oldOrder.getOrganChildcareOpenResourcesDatail().getSegment();
		}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
			smsTime += oldOrder.getOrganChildcareOpenResourcesDatail().getOpenStartTime() + "-" +
					oldOrder.getOrganChildcareOpenResourcesDatail().getOpenEndTime() + "之间";
		}
		contecnt += smsTime + "前往，地址：" + oldOrder.getHospitalBasicInfo().getAddress()+"。详情请查‘我的预约’";	
		ss.sendInfo(userInfo.getPhone(), contecnt.toString());
		//给医生发短信通知
		String toDoctorContecnt="亲爱的" + oldOrder.getDoctorInfo().getDoctorName() + "医生，" +
				userInfo.getBabyName()+ "预约您"+ smsTime	+ "的儿保服务。详情请查'我的订单'";
		ss.sendInfo(oldOrder.getDoctorInfo().getDoctorPhone(), toDoctorContecnt.toString());
	}
	
	/**
	 * 儿科门诊发送信息
	 * @param orderInfoClinic
	 */
	private void sendMessage(OrderInfoClinic orderInfoClinic) {
		SendSms ss = new SendSms();
		//给用户发短信
		OrderInfoClinic getOrderInfoClinic = clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
		
		String serviceDate = getOrderInfoClinic.getAppointmentDate();
		String serviceTime = getOrderInfoClinic.getAppointmentBeganTime();
		String doctorName = getOrderInfoClinic.getDoctorInfo().getDoctorName();
		
//		ClinicOtherContactsInfo clinicOtherContactsInfo = clinicOrderService.getClinicOtherContactsInfoByOrderId(orderInfoClinic.getId());
		String userPhone = getOrderInfoClinic.getUserInfo().getPhone();
		
		String contecnt="亲爱的用户，您预约" + doctorName + "医生的门诊服务，时间：" + serviceDate 
				+ " " + serviceTime + "，地址：" 
				+ getOrderInfoClinic.getClinicAddress()
			    + "。详情请查'我的订单'";
		ss.sendInfo(userPhone, contecnt);
		
		//给医生发短信
		String userName = getOrderInfoClinic.getUserInfo().getBabyName();
		
		contecnt = "亲爱的" + doctorName + "医生，" + userName+ "预约您"
				+ serviceDate + " " + serviceTime
				+ "的门诊服务。详情请查'我的订单'";
		ss.sendInfo(getOrderInfoClinic.getDoctorInfo().getDoctorPhone(), contecnt);
		mess="操作成功";
	}
	/**  绑定健康卡号  
	 * @throws Exception */
	public String bindHealthCard(){
		Long userID = queryUserID();
		if(userID==null){
			mess = "请先登陆";return SUCCESS;
		}
		BooleanMsg booleanMsg = spInterfaceService.saveHealthCard(userID,spHealthcardManager);
		if(booleanMsg.isTrue())
			mess = "成功";
		else
			mess = booleanMsg.getMsg();
		return SUCCESS;
	}
	/**  查询用户拥护的健康卡号  
	 * @throws Exception */
	public String queryHealthCardOfUserOwned(){
		Long userID = queryUserID();
		if(userID==null){
			mess="请先登陆";return SUCCESS;
		}
		healthCardOfUserOwneds = spInterfaceService.getHealthCardOfUserOwneds(userID);
		if(healthCardOfUserOwneds.isEmpty()){
			mess="用户没有绑定就诊卡";
		}else{
			mess="成功";
		}
		return SUCCESS;
	}
	/**  修改健康卡   */
	public String setHealthCardOfUserUsed(){
		Long userID = queryUserID();
		if(userID==null){
			mess="请先登陆";return SUCCESS;
		}
		boolean updateOK = spInterfaceService.updateHealthCardOfUserUsed(userID,spHealthcardManager);
		if(updateOK)
			mess="成功";
		else
			mess="失败";
		return SUCCESS;
	}
	private Long queryUserID(){
		//return 382L;
		return (Long) ActionContext.getContext().getSession().get("userId");
	}
	/**  获取医嘱信息列表  */
	public String queryDoctorAdvisesInfoOfUserOwned() throws Exception{
		Long userID = queryUserID();
		if(userID==null){
			mess="请先登陆";return SUCCESS;
		}
		String [] orgIDs = new String[]{SpServiceConstant.NINE_HOSPITAL_CODE,SpServiceConstant.SP_HOSPITAL_CODE};
		doctorAdviseInfos = new ArrayList<SpDoctorAdviceInfo>();
		for (String orgID : orgIDs) {
			doctorAdviseInfos.addAll(spInterfaceService.saveSpDoctorAdviceInfos(visitCardID,pageNo, userID, timeFlag,orgID));
		}
		if(doctorAdviseInfos.isEmpty()){
			mess="没有医嘱信息";
		}else{
			mess="成功";
		}
		return SUCCESS;
	}
	/**  获取医嘱详情  */
	public String queryDoctorAdvisesInfo() throws Exception{
		doctorAdviseInfo = spInterfaceService.getDoctorAdviseInfo(spQueryMessageID);
		mess="成功";
		return SUCCESS;
	}
	
	/**  获取检验记录 
	 * @throws Exception */
	public String querySpInspectRecords() throws Exception{
		Long userID = queryUserID();
		if(userID==null){
			mess="请先登陆";return SUCCESS;
		}
		String [] orgIDs = new String[]{SpServiceConstant.NINE_HOSPITAL_CODE,SpServiceConstant.SP_HOSPITAL_CODE};
		spInspectRecords = new ArrayList<SpInspectRecord>();
		for (String orgID : orgIDs) {
			this.spInspectRecords.addAll(spInterfaceService.saveSpInspectRecords(visitCardID,pageNo, userID, timeFlag,orgID));
		}
		if(spInspectRecords.isEmpty()){
			mess="没有检验记录";
		}else{
			mess="成功";
		}
		return SUCCESS;
	}
	/** 获取检验记录和检验结果 
	 * @throws Exception */
	public String querySpInspectResultInfo() throws Exception{
		
		spInspectRecord = spInterfaceService.saveSpInspectInfo(spQueryMessageID);
		
		mess="成功";
		return SUCCESS;
	}
	/** 根据费用ID,执行门诊收费 
	 * @throws Exception */
	public String executeClinicCharge(){
		boolean saveSpClinicChargeInfo = spInterfaceService.saveSpClinicChargeInfo(spQueryMessageID);
		if(saveSpClinicChargeInfo){
			mess = "收费成功";
		}else{
			mess = "收费失败";
		}
		return SUCCESS;
	}
	/** 获取费用信息列表
	 * @throws Exception */
	public String querySpCostInfos(){
		Long userID = this.queryUserID();
		if(userID==null){
			mess="请登录";return SUCCESS;
		}
		String [] orgIDs = new String[]{SpServiceConstant.NINE_HOSPITAL_CODE,SpServiceConstant.SP_HOSPITAL_CODE};
		spCostInfos = new ArrayList<SpCostInfo>();
		for (String orgID : orgIDs) {
			spCostInfos.addAll(spInterfaceService.saveSpCostInfos(visitCardID, pageNo, userID, timeFlag, costType,orgID));
		}
		if(spCostInfos.isEmpty())
			mess="没有费用信息";
		else
			mess="成功";
		return SUCCESS;
	}
	/** 获取费用清单
	 * @throws Exception */
	public String querySpCostInfo(){
		spCostInfo = spInterfaceService.getSpCostInfo(spQueryMessageID);
		mess="成功";
		return SUCCESS;
	}
	/** 获取检查记录 列表
	 * @throws Exception */
	public String querySpCheckRecords(){
		Long userID = this.queryUserID();
		if(userID==null){
			mess="请登录";return SUCCESS;
		}
		String [] orgIDs = new String[]{SpServiceConstant.NINE_HOSPITAL_CODE,SpServiceConstant.SP_HOSPITAL_CODE};
		spCheckRecords = new ArrayList<SpCheckRecord>();
		for (String orgID : orgIDs) {
			spCheckRecords.addAll(spInterfaceService.saveSpCheckRecords(visitCardID,pageNo, userID, timeFlag,orgID));
		}
		if(spCheckRecords.isEmpty())
			mess="没有检查记录";
		else
			mess="成功";
		return SUCCESS;
	}
	/** 获取检查报告  
	 * @throws Exception */
	public String querySpCheckReport(){
	    spCheckRecord = spInterfaceService.saveSpCheckInfo(spQueryMessageID);
		mess="成功";
		return SUCCESS;
	}
	/** 获取用户就诊记录列表    */
	public String querySpVisitRecords(){
		Long userID = this.queryUserID();
		if(userID==null){
			mess="请登录";return SUCCESS;
		}
		String spOrgIDs [] = new String[]{SpServiceConstant.NINE_HOSPITAL_CODE,SpServiceConstant.SP_HOSPITAL_CODE}; 
		saveSpVisitRecords = new ArrayList<SpVisitRecord>();
		for (String spOrgID : spOrgIDs) {
			saveSpVisitRecords.addAll(spInterfaceService.saveSpVisitRecords(visitCardID,pageNo,userID, timeFlag,spOrgID));
		}
		if(saveSpVisitRecords.isEmpty()) mess="没有就诊记录";
		else mess="成功";
		return SUCCESS;
	}
	/**  查看就诊记录详细内容  
	 * @throws Exception */
	public String querySpVisitRecord(){
		this.spVisitRecord = spInterfaceService.getSpVisitRecord(spQueryMessageID);
		mess="成功";
		return SUCCESS;
	}
	/**  取消挂号订单  */
	public String cancelRegister(){
		boolean cancelRegister_isOK = spInterfaceService.cancelRegister(orderId, deptFlag);
		if(cancelRegister_isOK){
			mess = "成功";
		}else{
			mess = (String) MsgUtil.get();;
		}
		return SUCCESS;
	}
	/**  查看缴费记录是否需要缴费 */
	public String costTypeChargeIsOK(){
		boolean SpCostInfo_IsPaid = spInterfaceService.querySpCostInfo_IsPaid(spQueryMessageID);
		if(SpCostInfo_IsPaid){
			mess=SpServiceConstant.COST_TYPE_ALREADY_PAID;
		}else{
			mess=SpServiceConstant.COST_TYPE_NO_PAID;
		}
		return SUCCESS;
	}
	/**  查询挂号明细信息  */
	public String querySpRegisterOrderDetail(){
		spRegisterOrderDetail = spInterfaceService.getSpRegisterOrderDetail(orderId, deptFlag);
		mess= "成功";
		return SUCCESS;
	}
	/**  无卡挂号   */
	public String registerWithNoCard(){
		BooleanMsg booleanMsg = spInterfaceService.registerWithNoCard(spHealthcardManager);
		if(booleanMsg.isTrue()){
			spHealthcardManager = (SpHealthcardManager) booleanMsg.getObject();
			mess = "成功";
		}else{
			mess = booleanMsg.getMsg();
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public Long getVisitCardID() {
		return visitCardID;
	}
	public void setVisitCardID(Long visitCardID) {
		this.visitCardID = visitCardID;
	}
	public String getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public SpRegisterOrderDetail getSpRegisterOrderDetail() {
		return spRegisterOrderDetail;
	}
	public void setSpRegisterOrderDetail(SpRegisterOrderDetail spRegisterOrderDetail) {
		this.spRegisterOrderDetail = spRegisterOrderDetail;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public List<SpHealthcardManager> getHealthCardOfUserOwneds() {
		return healthCardOfUserOwneds;
	}
	public void setHealthCardOfUserOwneds(
			List<SpHealthcardManager> healthCardOfUserOwneds) {
		this.healthCardOfUserOwneds = healthCardOfUserOwneds;
	}
	public SpVisitRecord getSpVisitRecord() {
		return spVisitRecord;
	}
	public void setSpVisitRecord(SpVisitRecord spVisitRecord) {
		this.spVisitRecord = spVisitRecord;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<SpVisitRecord> getSaveSpVisitRecords() {
		return saveSpVisitRecords;
	}
	public void setSaveSpVisitRecords(List<SpVisitRecord> saveSpVisitRecords) {
		this.saveSpVisitRecords = saveSpVisitRecords;
	}
	public SpCheckReport getSaveSpCheckReport() {
		return saveSpCheckReport;
	}
	public void setSaveSpCheckReport(SpCheckReport saveSpCheckReport) {
		this.saveSpCheckReport = saveSpCheckReport;
	}
	public SpCheckRecord getSpCheckRecord() {
		return spCheckRecord;
	}
	public void setSpCheckRecord(SpCheckRecord spCheckRecord) {
		this.spCheckRecord = spCheckRecord;
	}
	
	public List<SpCheckRecord> getSpCheckRecords() {
		return spCheckRecords;
	}
	public void setSpCheckRecords(List<SpCheckRecord> spCheckRecords) {
		this.spCheckRecords = spCheckRecords;
	}
	public SpInspectRecord getSpInspectRecord() {
		return spInspectRecord;
	}
	public SpInspectInfo getSpInspectInfo() {
		return spInspectInfo;
	}
	public SpCostInfo getSpCostInfo() {
		return spCostInfo;
	}
	public void setSpInspectRecord(SpInspectRecord spInspectRecord) {
		this.spInspectRecord = spInspectRecord;
	}
	public void setSpInspectRecords(List<SpInspectRecord> spInspectRecords) {
		this.spInspectRecords = spInspectRecords;
	}
	public SpDoctorAdviceInfo getDoctorAdviseInfo() {
		return doctorAdviseInfo;
	}
	public void setDoctorAdviseInfo(SpDoctorAdviceInfo doctorAdviseInfo) {
		this.doctorAdviseInfo = doctorAdviseInfo;
	}
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}
	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}
	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}
	public SpHealthcardManager getSpHealthcardManager() {
		return spHealthcardManager;
	}
	public void setSpHealthcardManager(SpHealthcardManager spHealthcardManager) {
		this.spHealthcardManager = spHealthcardManager;
	}
	public String getDeptFlag() {
		return deptFlag;
	}
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}
	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getOthersCollectorJson() {
		return othersCollectorJson;
	}
	public void setOthersCollectorJson(String othersCollectorJson) {
		this.othersCollectorJson = othersCollectorJson;
	}
	public EvaluateClinic getEvaluateClinic() {
		return evaluateClinic;
	}
	public void setEvaluateClinic(EvaluateClinic evaluateClinic) {
		this.evaluateClinic = evaluateClinic;
	}
	public Long getHealthCardID() {
		return healthCardID;
	}
	public void setHealthCardID(Long healthCardID) {
		this.healthCardID = healthCardID;
	}
	public List<SpDoctorAdviceInfo> getDoctorAdviseInfos() {
		return doctorAdviseInfos;
	}
	public void setDoctorAdviseInfos(List<SpDoctorAdviceInfo> doctorAdviseInfos) {
		this.doctorAdviseInfos = doctorAdviseInfos;
	}
	public Long getSpQueryMessageID() {
		return spQueryMessageID;
	}
	public void setSpQueryMessageID(Long spQueryMessageID) {
		this.spQueryMessageID = spQueryMessageID;
	}
	public List<SpCostInfo> getSpCostInfos() {
		return spCostInfos;
	}
	public void setSpCostInfos(List<SpCostInfo> spCostInfos) {
		this.spCostInfos = spCostInfos;
	}
	public List<SpInspectRecord> getSpInspectRecords() {
		return spInspectRecords;
	}
	public void setSpInspectInfo(SpInspectInfo spInspectInfo) {
		this.spInspectInfo = spInspectInfo;
	}
	public void setSpCostInfo(SpCostInfo spCostInfo) {
		this.spCostInfo = spCostInfo;
	}
	public String getSpOrgID() {
		return spOrgID;
	}
	public void setSpOrgID(String spOrgID) {
		this.spOrgID = spOrgID;
	}
}
