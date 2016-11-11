package com.kybaby.newbussiness.doctorclinic.action;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicDiscountInfo;
import com.kybaby.newbussiness.doctorclinic.domain.ClinicOtherContactsInfo;
import com.kybaby.newbussiness.doctorclinic.domain.EvaluateClinic;
import com.kybaby.newbussiness.doctorclinic.domain.OrderInfoClinic;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.MyMath;
/**
 * 订单支付回调方法
 * @author xiongchao
 *
 */
public class ClinicOrderPayAction extends NewBaseAction{
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
	 * 门诊订单信息
	 */
	private OrderInfoClinic orderInfoClinic;
	/**
	 * 门诊订单信息
	 */
	private List<OrderInfoClinic> orderInfoClinicList = new ArrayList<OrderInfoClinic>();
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
	public String execute() {
		System.out.println("============ClinicOrderPayAction execute==============");
		
		if(action.equals("paySuccess")) {
			this.doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorInfo.getId());
			
			OrderInfoClinic oldOrder = this.clinicOrderService.getOrderInfoClinicById(orderInfoClinic.getId());
			Long orderId = oldOrder.getId();
			String orderStatus = "";
			if("付款成功".equals(orderInfoClinic.getOrderStatus())) {
				orderStatus = ConstantManage.HASE_BOOKED_CLINIC_ORDER;
				//将已预约时间设置为不可用
				this.clinicOrderService.updateDoctorClinicTimeSegmentStatus(oldOrder.getDoctorInfo(), "N", oldOrder.getAppointmentDate(), oldOrder.getAppointmentBeganTime());
				
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
					this.clinicOrderService.updateClinicOrder(oldOrder);	
				}
			}
		} else if (action.equals("getDoctorInfo")) {
			if (doctorInfo!=null && doctorInfo.getId()!=null) {
				this.doctorInfo = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorInfo.getId());
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
	
}
