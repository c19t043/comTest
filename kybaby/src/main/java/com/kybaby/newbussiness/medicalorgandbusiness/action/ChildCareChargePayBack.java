package com.kybaby.newbussiness.medicalorgandbusiness.action;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganChildcareOpenResourcesDatail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;

/**
 * 儿保收费管理
 * @author lihao
 *
 */
public class ChildCareChargePayBack extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 儿保预约信息
	 */
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	/**
	 * 福利优惠信息
	 */
	private ClinicDiscountInfo clinicDiscountInfo;
	
	public String execute(){
		System.out.println("ChildCareChargePayBack execute=====");
		/**
		 * 儿保付费订单管理
		 */
		if(action.equals("paySucessChildcareAppointmentInfo")){
			System.out.println("ChildCareChargePayBack paySucessChildcareAppointmentInfo=====");
			try {
				UserChildcareAppointmentInfo oldOrder = this.openBusinessManagerService.
						getUserChildcareAppointmentInfoById(userChildcareAppointmentInfo.getId());
				UserInfo userInfo = oldOrder.getUserInfo();
				OrganChildcareOpenResourcesDatail oldDetail = oldOrder.getOrganChildcareOpenResourcesDatail();
				oldOrder.setOperationTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				if("付款成功".equals(userChildcareAppointmentInfo.getStatus())){
					//用户的订单金额
					double totalPriceCalc = Double.valueOf(oldOrder.getTotalPrice());
					//用户使用的余额
					double useRemainBalanceCalc = userChildcareAppointmentInfo.getUseRemainBalance()==null?0d:Double.valueOf(userChildcareAppointmentInfo.getUseRemainBalance());
					//用户使用的福利金额
					double discountMoneyCalc = userChildcareAppointmentInfo.getDiscountMoney()==null?0d:Double.valueOf(userChildcareAppointmentInfo.getDiscountMoney());
					//最终需要支付的金额
					double chargeBalance = totalPriceCalc - useRemainBalanceCalc - discountMoneyCalc;
					chargeBalance = MyMath.round(chargeBalance, 2);
					System.out.println("最终需要支付的金额=="+chargeBalance);
					//用户使用账户里的余额完全支付订单金额，不需要在线支付了
					if (chargeBalance <= 0D) {
						//用户账户的余额
						double accountBalance = userInfo.getAccountBalance();
						double balance = accountBalance-useRemainBalanceCalc;
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
							double balance = accountBalance-useRemainBalanceCalc;
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
				}
			} catch (Exception e) {
				System.out.println("=====儿保支付error==========");
				e.printStackTrace();
			}
		}
		return "success";
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}
	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}
	public ClinicDiscountInfo getClinicDiscountInfo() {
		return clinicDiscountInfo;
	}
	public void setClinicDiscountInfo(ClinicDiscountInfo clinicDiscountInfo) {
		this.clinicDiscountInfo = clinicDiscountInfo;
	}
}
