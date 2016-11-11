package com.kybaby.newbussiness.doctorclinic.domain;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;

/**
 * OrderInfoClinic entity. @author MyEclipse Persistence Tools
 */

public class OrderInfoClinic implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long id;
	private String orderNum;
	private DoctorInfo doctorInfo;
	private UserInfo userInfo;
	private String appointmentDate;
	private String appointmentBeganTime;
	private String appointmentEndTime;
	private String orderTime;//下单时间
	private String meetTime;//会面时间
	private String endTime;//结束门诊时间
	private String orderStatus;
	private String totalPrice;
	private String clinicAddress;
	/**
	 * '实付金额';
	 */
	private String realPrice;
	/**
	 * 支付方式
	 */
	private String  payMethod;
	/**
	 * 使用的余额数
	 */
	private String useRemainBalance;
	/**
	 *  '福利优惠抵扣数';
	 */
	private String discountMoney;
	/**
	 * 门诊机构类型
	 */
	private String clinicOrgType;
	/**
	 * 分成比例
	 */
	private String  commission;
	/**
	 * 是否为加号(外部单位才有)
	 */
	private String  isPlus;
	
	
	/**
	 * 门诊机构类型（0：本院）
	 */
	public static final String CLINIC_ORG_TYPE_0="0";
	/**
	 * 门诊机构类型（1：多点机构）
	 */
	public static final String CLINIC_ORG_TYPE_1="1";
	
	/**
	 * 未付款
	 */
	public static final String NO_PAYMENT_CLINIC_ORDER="未付款";
	/**
	 * 已预约
	 */
	public static final String HASE_BOOKED_CLINIC_ORDER="已预约";
	/**
	 * 用户取消
	 */
	public static final String USER_CANCLE_CLINIC_ORDER="用户取消";
	/**
	 * 已会面
	 */
	public static final String HASE_MEET_CLINIC_ORDER="已会面";
	/**
	 * 已完成
	 */
	public static final String HASE_FINISHED_CLINIC_ORDER="已完成";
	/**
	 * 已评价
	 */
	public static final String HASE_EVALUATED_CLINIC_ORDER="已评价";

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentBeganTime() {
		return this.appointmentBeganTime;
	}

	public void setAppointmentBeganTime(String appointmentBeganTime) {
		this.appointmentBeganTime = appointmentBeganTime;
	}

	public String getAppointmentEndTime() {
		return this.appointmentEndTime;
	}

	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public String getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getUseRemainBalance() {
		return useRemainBalance;
	}

	public void setUseRemainBalance(String useRemainBalance) {
		this.useRemainBalance = useRemainBalance;
	}

	public String getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}

	public String getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getClinicOrgType() {
		return clinicOrgType;
	}

	public void setClinicOrgType(String clinicOrgType) {
		this.clinicOrgType = clinicOrgType;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getIsPlus() {
		return isPlus;
	}

	public void setIsPlus(String isPlus) {
		this.isPlus = isPlus;
	}
	
}