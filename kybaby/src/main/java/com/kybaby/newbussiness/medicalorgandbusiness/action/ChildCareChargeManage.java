package com.kybaby.newbussiness.medicalorgandbusiness.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProjectType;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.DoctorMoneyRecord;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetChildCareRecode;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeatOrder;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

/**
 * 儿保收费管理
 * @author lihao
 *
 */
public class ChildCareChargeManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 儿保预约信息
	 */
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 福利优惠信息
	 */
	private ClinicDiscountInfo clinicDiscountInfo;
	/**
	 * 儿保收费项目列表
	 */
	private List<ChildcareProject> childcareProjectList = new ArrayList<>();
	/**
	 * 儿保收费项目类别
	 */
	private ChildcareProjectType childcareProjectType;
	/**
	 * 机构套餐订单id
	 */
	private Long organSetMeatOrderId;
	
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId!=null){
			this.userInfo = this.userInfoBo.getUserById(userId);
		}
		if(userId==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 儿保付费订单管理
		 */
		if(action.equals("handleUserChildcareAppointmentInfo")){
			System.out.println("进入儿保付费订单管理。。。。。。");
			try {
				String rightNow = CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
				String monthAge = String.valueOf(CalculationMethod.getMonthSpace(userInfo.getBirthday(), rightNow));
				//得到宝宝月龄
				if(this.organSetMeatOrderId != null){//机构套餐，根据关联宝宝信息算
					OrganSetMeatOrder organSetMeatOrder= this.organSetMealService.getOrganSetMeatOrderById(organSetMeatOrderId);
					if(organSetMeatOrder != null){
						ConsultBabyInfo babyInfo = organSetMeatOrder.getBabyInfo();
						monthAge = String.valueOf(CalculationMethod.getMonthSpace(babyInfo.getBirthday(), rightNow));
					}
				}else{//普通订单，通过身份算
					ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
					if(archivesInfo != null){
						monthAge = String.valueOf(CalculationMethod.getMonthSpace(archivesInfo.getChildrenBirthday(), rightNow));
					}
				}
				
				if(userChildcareAppointmentInfo.getId() == null){//生成订单
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
							this.mes = "所选时间已被预约";
							return "fail";
						}
					}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
						if("0".equals(oldDetail.getGeneralSurplusNum())){
							this.mes = "没有号源可约";
							return "fail";
						}
					}
					//检查当前用户的未付款订单中是否已存在相同条件的订单
					Boolean flag = this.childCareChargeService.checkChildCareOrderIsExist
							(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getId(), 
									userChildcareAppointmentInfo.getOrganChildcareOpenResourcesDatail().getId(), 
									userChildcareAppointmentInfo.getHospitalBasicInfo().getId(), doctorInfo.getId(), userId);
					if(flag){
						mes = "订单已存在，请查看‘我的预约’";
						return "fail";
					}
					String orderNum = String.valueOf(System.currentTimeMillis());
					userChildcareAppointmentInfo.setOrderNum(orderNum);
					userChildcareAppointmentInfo.setDoctorInfo(doctorInfo);
					userChildcareAppointmentInfo.setUserInfo(userInfo);
					userChildcareAppointmentInfo.setStatus(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
					userChildcareAppointmentInfo.setTotalPrice(dmr.getMoneyPer());
					userChildcareAppointmentInfo.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					//优惠金额
//					ClinicDiscountInfo cd= this.clinicOrderService.getClinicDiscountInfo();
//					userChildcareAppointmentInfo.setDiscountMoney(cd==null?"0":cd.getDiscountMoney());
					userChildcareAppointmentInfo.setDiscountMoney("0");
//					//先得到用户身份,根据用户身份来源决定订单类型
//					ArchivesInfo archivesInfo = this.vaccineManageService.getCurrentUserIdentity(userId,null);
//					if(ArchivesInfo.USER_FROM_ONLINE.equals(archivesInfo.getUserFrom())){
//						userChildcareAppointmentInfo.setOrderType("机构套餐");
//					}else{
//						userChildcareAppointmentInfo.setOrderType("普通订单");
//					}
					userChildcareAppointmentInfo.setCurrentMonthAge(monthAge);
					Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(userChildcareAppointmentInfo);
					userChildcareAppointmentInfo.setId(id);
				}else{//更新订单
					UserChildcareAppointmentInfo oldOrder = this.openBusinessManagerService.
							getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
					OrganChildcareOpenResourcesDatail oldDetail = oldOrder.getOrganChildcareOpenResourcesDatail();
					OrganChildcareOpenResources oldOpenResources = oldOrder.getOrganChildcareOpenResources();
					oldOrder.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					oldOrder.setCurrentMonthAge(monthAge);
					
					if("下一步".equals(userChildcareAppointmentInfo.getStatus())){
						//儿保医生薪酬记录
						DoctorMoneyRecord dmr = this.openBusinessManagerService.getDoctorMoneyRecordBySomething
								(null, doctorInfo, oldOpenResources.getOpenDate());
						oldOrder.setTotalPrice(dmr.getMoneyPer());
						oldOrder.setDoctorInfo(doctorInfo);
						this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(oldOrder);
					}else if("付款成功".equals(userChildcareAppointmentInfo.getStatus())){
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
						//发送短信通知
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
						//购买成功后添加应做儿保项目记录信息
						this.addOrganSetChildCareRecode(oldOrder);
					}else if("用户取消".equals(userChildcareAppointmentInfo.getStatus())){
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
							mes = "时间过期,无法取消";
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
						//释放资源
						if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
							oldDetail.setIsCanUse("Y");
						}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
							//统一按普通号源算（2016-10-13改）
//							if(ConstantManage.FD_PAY.equals(oldOrder.getOrderType())){
//								Long greenSurplusNum = Long.valueOf(oldDetail.getGreenChannelSurplusNum());
//								greenSurplusNum = greenSurplusNum.longValue() + 1;
//								oldDetail.setGreenChannelSurplusNum(String.valueOf(greenSurplusNum<=0L?0L:greenSurplusNum));
//							}else{
								Long generalSurplusNum = Long.valueOf(oldDetail.getGeneralSurplusNum());
								generalSurplusNum = generalSurplusNum.longValue() + 1;
								oldDetail.setGeneralSurplusNum(String.valueOf(generalSurplusNum<=0L?0L:generalSurplusNum));
//							}
						}
						this.openBusinessManagerService.saveOrUpdateOrganChildcareOpenResourcesDatail(oldDetail);
					}else if("取消".equals(userChildcareAppointmentInfo.getStatus())){//未付款订单取消
						oldOrder.setStatus(ConstantManage.USER_CANCLE_CLINIC_ORDER);
						Long id = this.childCareChargeService.saveOrUpdateUserChildcareAppointmentInfo(oldOrder);
						userChildcareAppointmentInfo.setId(id);
					}
				}
			} catch (Exception e) {
				System.out.println("儿保预约出错了。。。。。。。。。。。。。。。");
				e.printStackTrace();
			}
		}
		/**
		 * 得到儿保预约信息
		 */
		else if(action.equals("getUserChildcareAppointmentInfo")){
			this.userChildcareAppointmentInfo = 
					this.openBusinessManagerService.getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
			//福利优惠信息
			this.clinicDiscountInfo = this.clinicOrderService.getClinicDiscountInfo();
			if(clinicDiscountInfo==null){
				clinicDiscountInfo = new ClinicDiscountInfo();
			}
			clinicDiscountInfo.setDiscountMoney("0");
		}
		/**
		 * 检查时间是否被约
		 */
		else if(action.equals("checkTimeIsUsed")){
			UserChildcareAppointmentInfo oldOrder = this.openBusinessManagerService.
					getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
			OrganChildcareOpenResourcesDatail oldDetail = oldOrder.getOrganChildcareOpenResourcesDatail();
			//判断订单门诊时间是否大于当前时间 （是否过期），如是，则不能进行退款操作
			String appointmentDateTime = oldOrder.getOrganChildcareOpenResources().getOpenDate() + " ";
			if("时间点".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if("N".equals(oldDetail.getIsCanUse())){
					this.mes = "所选时间已被预约";
					return "fail";
				}
				appointmentDateTime += oldOrder.getOrganChildcareOpenResourcesDatail().getSegment() + ":00";
			}else if("时间段".equals(oldDetail.getOrganChildcareOpenResources().getTimeDivisionNeed())){
				if("0".equals(oldDetail.getGeneralSurplusNum())){
					this.mes = "没有号源可约";
					return "fail";
				}
				appointmentDateTime += oldOrder.getOrganChildcareOpenResourcesDatail().getOpenEndTime() + ":00";
			}
			if (!DateManage.isCompareDates(DateManage.getStrToDateTime(appointmentDateTime), new Date())) {
				mes = "时间过期";
				return "fail";
			} 
		}
		/**
		 * 得到儿保项目类别下的项目列表
		 */
		else if(action.equals("getChildcareProjectList")){
			this.childcareProjectList = this.childCareChargeService.getChildcareProjectListByType(childcareProjectType);
		}
		return "success";
	}
	/**
	 * 添加当前购买儿保的适合做项目
	 * @param oldOrder
	 */
	private void addOrganSetChildCareRecode(UserChildcareAppointmentInfo oldOrder){
		OrganSetChildCareRecode organSetChildCareRecode = new OrganSetChildCareRecode();
		organSetChildCareRecode.setChildcareProject(this.getChildcareProjectByAge(oldOrder.getOrganChildcareOpenResources().getHospitalBasicInfo()));
		organSetChildCareRecode.setUserChildcareAppointmentInfo(oldOrder);
		this.organSetMealService.saveOrUpdateOrganSetChildCareRecode(organSetChildCareRecode);
	}
	/**
	 * 得到当前用户适合的儿保项目内容
	 * @param hospitalBasicInfo
	 */
	private ChildcareProject getChildcareProjectByAge(HospitalBasicInfo hospitalBasicInfo){
		//得到用户月龄
		String babyBirthday=userInfo.getBirthday();
		String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
		String monthAge="";
		try {
			monthAge = String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ChildcareProject childcareProject = this.childCareChargeService.
				getChildcareProjectBySomething(null, hospitalBasicInfo, monthAge);
		return childcareProject;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
	public ClinicDiscountInfo getClinicDiscountInfo() {
		return clinicDiscountInfo;
	}
	public void setClinicDiscountInfo(ClinicDiscountInfo clinicDiscountInfo) {
		this.clinicDiscountInfo = clinicDiscountInfo;
	}
	public List<ChildcareProject> getChildcareProjectList() {
		return childcareProjectList;
	}
	public void setChildcareProjectList(List<ChildcareProject> childcareProjectList) {
		this.childcareProjectList = childcareProjectList;
	}
	public ChildcareProjectType getChildcareProjectType() {
		return childcareProjectType;
	}
	public void setChildcareProjectType(ChildcareProjectType childcareProjectType) {
		this.childcareProjectType = childcareProjectType;
	}
	public void setOrganSetMeatOrderId(Long organSetMeatOrderId) {
		this.organSetMeatOrderId = organSetMeatOrderId;
	}
}
