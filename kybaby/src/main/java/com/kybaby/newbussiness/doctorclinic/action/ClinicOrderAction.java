package com.kybaby.newbussiness.doctorclinic.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicMedicalRecords;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.DoctorMorePractice;
import com.kybaby.newbussiness.doctorclinic.domain.DrugInfo;
import com.kybaby.newbussiness.doctorclinic.domain.EvaluateClinic;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;
import com.kybaby.newbussiness.spservice.bo.SpInterfaceService;
import com.kybaby.newbussiness.spservice.common.SpServiceConstant;
import com.kybaby.newbussiness.spservice.domain.SpRegisterOrderDetail;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

public class ClinicOrderAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 门诊订单信息
	 */
	private OrderInfoClinic orderInfoClinic;
	/**
	 * 门诊订单信息
	 */
	private List<OrderInfoClinic> orderInfoClinicList = new ArrayList<OrderInfoClinic>();
	/**
	 * 其他联系人信息json串
	 */
	private String othersCollectorJson;
	/**
	 * 验证码
	 */
	private String checkWords="";
	/**
	 * 其他联系人电话
	 */
	private String otherPhon;
	/**
	 * 其他联系人信息
	 */
	private ClinicOtherContactsInfo clinicOtherContactsInfo;
	/**
	 * 福利优惠信息
	 */
	private ClinicDiscountInfo clinicDiscountInfo;
	/**
	 * 返回订单预约时间是否被预定   已被预约/未被预约
	 */
	private String checkStatus;
	/**
	 * 订单评价信息
	 */
	private EvaluateClinic evaluateClinic;
	/**
	 * 就诊记录列表
	 */
	private List<ClinicMedicalRecords> clinicMedicalRecordsList = new ArrayList<>();
	/**
	 * 就诊记录信息
	 */
	private ClinicMedicalRecords clinicMedicalRecords;
	/**
	 * 会员列表
	 */
	private List<MemberManage> memberManageList;
	public String execute() throws Exception{
		System.out.println("============userClinicAction execute==============");
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		System.out.println("============userClinicAction userId=============="+userId);
		if(userId==null){
			if(userInfo != null && userInfo.getId() != null){
				userId = userInfo.getId();
				System.out.println("====本地存储 ClinicOrderAction userInfo.getId();====="+userId);
			}else{
				mes="请登录";
				return "fail";
			}
		}
		/**
		 * 检查订单预约时间是否被预定
		 */
		if(action.equals("checkTimeIsUsed")){
				//检查是否被预定
				Boolean flag = 
						this.clinicOrderService.checkTimeIsUsed(orderInfoClinic.getAppointmentDate(), orderInfoClinic.getAppointmentBeganTime(), doctorInfo);
				if(flag){
					checkStatus = "已被预约";
				} else {
					checkStatus = "未被预约";
				}
			if("Y".equals(orderInfoClinic.getIsPlus())){//付款时检查加号是否可加
				//有加号的需要判断加号剩余数
				OrderInfoClinic orderClinic = new OrderInfoClinic();
				orderClinic.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
				orderClinic.setIsPlus("Y");
				orderClinic.setDoctorInfo(doctorInfo);
				orderClinic.setAppointmentDate(orderInfoClinic.getAppointmentDate());
				Long hadAddClinic = this.getCanPlusNum(orderClinic);
				
				DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
				doctorMorePractice.setClinicDate(orderInfoClinic.getAppointmentDate());
				doctorMorePractice.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
				List<DoctorMorePractice> list = this.doctorClinicService.getDoctorMorePracticeList(doctorMorePractice, doctorInfo);
				if(list != null){
					DoctorMorePractice dmp = list.get(0);
					Long doctorAddNum = dmp.getIsAddClinic()==null?0L:Long.valueOf(dmp.getIsAddClinic());
					Long canAdd = doctorAddNum-hadAddClinic;
					if(canAdd <= 0L){
						checkStatus = "已被预约";
					}else{
						checkStatus = "未被预约";
					}
				}else{
					checkStatus = "已被预约";
				}
			}
			
		}
		
		/**
		 * 保存或更新订单信息
		 */
		else if(action.equals("saveOrUpdateClinicOrder")){
			try{
				System.out.println("门诊预约操作状态==="+orderInfoClinic.getOrderStatus());
				UserInfo userInfo = this.userInfoBo.getUserById(userId);
				//为了显示其他联系人用
				UserInfo showUserInfo = userInfo;
				this.doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorInfo.getId());
				if(orderInfoClinic.getId() == null) { //保存添加
					//检查当前用户的未付款订单中是否已存在相同条件的订单
					List<OrderInfoClinic> orderList = this.clinicOrderService.checkOrderIsExist
							(orderInfoClinic.getAppointmentDate(), orderInfoClinic.getAppointmentBeganTime(),
									orderInfoClinic.getClinicAddress(), doctorInfo, userInfo);
					if(orderList != null){
						mes = "订单已存在，请查看‘我的订单’";
						orderInfoClinic.setId(orderList.get(0).getId());
						return "fail";
					}
					//一天只能有三个有效订单
					long count = this.getClinicLimited(userInfo);
					if(count == 3L){
						mes = "您今天的门诊预约量已超过规定次数";
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
							mes = "医生已下班请选择其他时间预约";
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
					List rightList = this.getMemRight(userInfo);
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
					showUserInfo = this.saveOrUpdateOthers(othersCollectorJson,orderInfoClinic,userInfo);
					this.orderInfoClinic.setUserInfo(showUserInfo);
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
								mes = "医生已下班请选择其他时间预约";
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
						showUserInfo = this.saveOrUpdateOthers(othersCollectorJson,orderInfoClinic,userInfo);
					}
					else if("用户取消".equals(orderInfoClinic.getOrderStatus())){
						//判断订单门诊时间是否大于当前时间 （是否过期），如是，则不能进行退款操作
						String appointmentDateTime = oldOrder.getAppointmentDate() + " " + oldOrder.getAppointmentBeganTime() + ":00";
						if(!ConstantManage.HASE_BOOKED_CLINIC_ORDER.equals(oldOrder.getOrderStatus())){
							mes = oldOrder.getOrderStatus()+"订单不能取消";
						}
						else if (!DateManage.isCompareDates(DateManage.getStrToDateTime(appointmentDateTime), new Date())) {
							mes = "时间过期";
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
				System.out.println("===门诊订单保存更新 err==="+DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				e.printStackTrace();
			}
		}
		/**
		 * 检查多点坐诊医生上班状态
		 */
		else if(action.equals("checkClinicDoctorWorkStatue")){
			OrderInfoClinic oldOrder = this.clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			DoctorMorePractice doctorMorePractice = new DoctorMorePractice();
			
			doctorMorePractice.setClinicOrgType(ConstantManage.CLINIC_ORG_TYPE_1);
			doctorMorePractice.setClinicAddress(oldOrder.getClinicAddress());
			doctorMorePractice.setClinicDate(oldOrder.getAppointmentDate());
			DoctorMorePractice old = this.doctorClinicService.
					getDoctorMorePracticeList(doctorMorePractice, oldOrder.getDoctorInfo()).get(0);
			//判断坐诊医生是否下班，下班后不能再约
			if(ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(old.getOrgClinicStatus())){
				mes = "医生已下班请选择其他时间预约";
				return "fail";
			}
		}
		/**
		 * 用户订单列表
		 */
		else if(action.equals("getClinicOrderList")){
			//获取当前用户的订单列表
			Long currentUserId = userId;
			if (currentUserId != null) {
				if (orderInfoClinic==null) {
					orderInfoClinic = new OrderInfoClinic();
				}
				orderInfoClinic.setUserInfo(userInfoBo.getUserById(currentUserId));
			}
			this.orderInfoClinicList = this.clinicOrderService.getOrderInfoClinicList(orderInfoClinic);
			if(orderInfoClinicList != null){
				for(OrderInfoClinic clinic : orderInfoClinicList){
					//判断是否中联接口订单
					SpRegisterOrderDetail spRegisterOrderDetail = 
							this.spInterfaceService.getSpRegisterOrderDetail(clinic.getId(), SpServiceConstant.PEADIATRICS_FLAG);
					if(spRegisterOrderDetail != null){
						clinic.setIs_zhonglian("Y");
					}
				}
			}
		}
		/**
		 * 获取验证码
		 */
		else if(action.equals("getCheckWords")){
			Random random = new Random();
			for(int i=0;i<6;i++){
				checkWords += random.nextInt(10);
			}
			String contecnt="亲爱的用户，您的验证码是："+checkWords+"，请尽快输入验证！";
			System.out.print("add others contecnt=="+contecnt);
			SendSms sendSms = new SendSms();
			sendSms.sendInfo(otherPhon, contecnt);
		}
		/**
		 * 得到门诊订单信息
		 */
		else if(action.equals("getClinicOrderInfo")){
			this.orderInfoClinic = this.clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			this.doctorInfo = this.orderInfoClinic.getDoctorInfo();
			ClinicOtherContactsInfo other = new ClinicOtherContactsInfo();
			other.setIsChoose("Y");
			List<ClinicOtherContactsInfo> otherList = this.clinicOrderService.getClinicOtherContactsInfoList(other, orderInfoClinic);
			//福利优惠信息
			this.clinicDiscountInfo = this.clinicOrderService.getClinicDiscountInfo();
			this.clinicOtherContactsInfo = otherList==null?null:otherList.get(0);
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.memberManageList = this.memberService.getMemberManageList(userInfo);
		}
		/**
		 *  付款成功后的操作（比如奖励发放等）
		 */
		else if(action.equals("payClinicSuccess")){
			//打印微信支付不成功后返回的信息
			System.out.println("打印微信支付不成功后返回的信息WeixinJSBridge back msg ====="+checkWords);
		}
		/**
		 *  得到订单信息
		 */
		else if(action.equals("getClinicOrder")){
			this.orderInfoClinic =  this.clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			ClinicOtherContactsInfo other = new ClinicOtherContactsInfo();
			other.setIsChoose("Y");
			List<ClinicOtherContactsInfo> otherList = this.clinicOrderService.getClinicOtherContactsInfoList(other, orderInfoClinic);
			this.clinicOtherContactsInfo = otherList==null?null:otherList.get(0);
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.memberManageList = this.memberService.getMemberManageList(userInfo);
		}
		/**
		 *  更新订单的状态，比如用户取消订单
		 */
		else if(action.equals("updateClinicOrderStatus")){
			orderInfoClinic = this.clinicOrderService.updateOrderStatusById(orderInfoClinic.getId(), orderInfoClinic.getOrderStatus());
		}
		/**
		 *  得到用户就诊记录列表
		 */
		else if(action.equals("getClinicMedicalRecordsListByUser")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.clinicMedicalRecordsList = this.clinicOrderService.getClinicMedicalRecordsList(null, userInfo);
		}
		/**
		 * 门诊就诊记录信息
		 */
		else if(action.equals("getClinicMedicalRecords")){
			this.clinicMedicalRecords = this.clinicOrderService.getClinicMedicalRecordsById(clinicMedicalRecords.getId()); 
			if(clinicMedicalRecords != null && clinicMedicalRecords.getDrugIds() != null && 
					!"".equals(clinicMedicalRecords.getDrugIds())){
				List<DrugInfo> drugInfoOldList = new ArrayList<>();
				String drugIds[] = clinicMedicalRecords.getDrugIds().split(",");
				for(int i=0;i<drugIds.length; i++){
					DrugInfo drug = this.clinicOrderService.getDrugInfoById(Long.valueOf(drugIds[i]));
					drugInfoOldList.add(drug);
				}
				clinicMedicalRecords.setDrugInfoList(drugInfoOldList);
			}
			if(clinicMedicalRecords != null && clinicMedicalRecords.getSymptomTagIds() != null && 
					!"".equals(clinicMedicalRecords.getSymptomTagIds())){
				List<SymptomTag> symptomTagList = new ArrayList<>();
				String symptomTagIds = clinicMedicalRecords.getSymptomTagIds().replace(",", "::");
				symptomTagList = this.symptomTagBo.getSymptomTagInstanceList(symptomTagIds);
				clinicMedicalRecords.setSymptomTagList(symptomTagList);
			}
			this.orderInfoClinic = this.clinicOrderService.
					getOrderInfoClinicById(clinicMedicalRecords.getOrderInfoClinic().getId());
		}
		return "success";
	}
	public static void main(String[] args) {
		String a="1,2,3";
		a = a.replace(",", "::");
		System.out.println(a);
	}
	/**
	 * 保存或更新其他联系人
	 */
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
	 * 用户预约门诊时得到有效加号订单数
	 * @param orderClinic
	 * @return
	 */
	private Long getCanPlusNum(OrderInfoClinic orderClinic){
		String flag = ConstantManage.HASE_BOOKED_CLINIC_ORDER+","+
				ConstantManage.HASE_FINISHED_CLINIC_ORDER+","+
				ConstantManage.HASE_MEET_CLINIC_ORDER+","+
				ConstantManage.HASE_EVALUATED_CLINIC_ORDER;
		long count=0;
		List<OrderInfoClinic> plusOrderList = this.clinicOrderService.getOrderInfoClinicList(orderClinic);
		if(plusOrderList == null){
			return 0L;
		}
		for(OrderInfoClinic oic : plusOrderList){
			String status = oic.getOrderStatus();
			if(flag.indexOf(status) > -1){
				count++;
			}
		}
		return count;
	}
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
	 * 得到用户的会员特权
	 * @param userInfo
	 * @return
	 */
	private List<String> getMemRight(UserInfo userInfo){
		List list = new ArrayList<>();
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
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}
	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
	public void setOthersCollectorJson(String othersCollectorJson) {
		this.othersCollectorJson = othersCollectorJson;
	}
	public List<OrderInfoClinic> getOrderInfoClinicList() {
		return orderInfoClinicList;
	}
	public void setOrderInfoClinicList(List<OrderInfoClinic> orderInfoClinicList) {
		this.orderInfoClinicList = orderInfoClinicList;
	}
	/**
	 * @return the checkWords
	 */
	public String getCheckWords() {
		return checkWords;
	}
	/**
	 * @return otherPhon
	 */
	public String getOtherPhon() {
		return otherPhon;
	}
	public ClinicDiscountInfo getClinicDiscountInfo() {
		return clinicDiscountInfo;
	}
	public ClinicOtherContactsInfo getClinicOtherContactsInfo() {
		return clinicOtherContactsInfo;
	}
	public void setClinicOtherContactsInfo(
			ClinicOtherContactsInfo clinicOtherContactsInfo) {
		this.clinicOtherContactsInfo = clinicOtherContactsInfo;
	}
	public EvaluateClinic getEvaluateClinic() {
		return evaluateClinic;
	}
	public void setEvaluateClinic(EvaluateClinic evaluateClinic) {
		this.evaluateClinic = evaluateClinic;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public void setOtherPhon(String otherPhon) {
		this.otherPhon = otherPhon;
	}
	public List<ClinicMedicalRecords> getClinicMedicalRecordsList() {
		return clinicMedicalRecordsList;
	}
	public void setClinicMedicalRecordsList(
			List<ClinicMedicalRecords> clinicMedicalRecordsList) {
		this.clinicMedicalRecordsList = clinicMedicalRecordsList;
	}
	public ClinicMedicalRecords getClinicMedicalRecords() {
		return clinicMedicalRecords;
	}
	public void setClinicMedicalRecords(ClinicMedicalRecords clinicMedicalRecords) {
		this.clinicMedicalRecords = clinicMedicalRecords;
	}
	public List<MemberManage> getMemberManageList() {
		return memberManageList;
	}
	public void setCheckWords(String checkWords) {
		this.checkWords = checkWords;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
