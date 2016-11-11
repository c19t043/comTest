package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;

/**
 * UserChildcareAppointmentInfo entity. @author MyEclipse Persistence Tools
 */

public class UserChildcareAppointmentInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganChildcareOpenResources organChildcareOpenResources;
	private OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail;
	private OrganServicePlaceSet organServicePlaceSet;
	private HospitalBasicInfo hospitalBasicInfo;
	private UserInfo userInfo;
	private String status;
	private String operationTime;
	private String preEncoding;
	private String totalPrice;//'订单金额';
	private String realPrice;//'实付金额';
	private String payMethod;// '支付方式';
	private String useRemainBalance;//'使用余额数';
	private String discountMoney;//'优惠金额';
	private DoctorInfo doctorInfo;
	private String orderNum;
	/**
	 * 订单类型（家庭医生套餐;普通订单；机构套餐）
	 */
	private String orderType;
	/**
	 * 当前月龄(为了匹配可做儿保项目内容)
	 */
	private String currentMonthAge;
	/**
	 * 家庭医生服务包
	 */
	private FdServicePackage fdServicePackage;
	
	//查询条件用
	private String openBeginDate;
	private String openEndDate;
	private String isMoney;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrganChildcareOpenResources getOrganChildcareOpenResources() {
		return organChildcareOpenResources;
	}

	public void setOrganChildcareOpenResources(
			OrganChildcareOpenResources organChildcareOpenResources) {
		this.organChildcareOpenResources = organChildcareOpenResources;
	}

	public OrganChildcareOpenResourcesDatail getOrganChildcareOpenResourcesDatail() {
		return organChildcareOpenResourcesDatail;
	}

	public void setOrganChildcareOpenResourcesDatail(
			OrganChildcareOpenResourcesDatail organChildcareOpenResourcesDatail) {
		this.organChildcareOpenResourcesDatail = organChildcareOpenResourcesDatail;
	}

	public OrganServicePlaceSet getOrganServicePlaceSet() {
		return organServicePlaceSet;
	}

	public void setOrganServicePlaceSet(OrganServicePlaceSet organServicePlaceSet) {
		this.organServicePlaceSet = organServicePlaceSet;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getPreEncoding() {
		return preEncoding;
	}

	public void setPreEncoding(String preEncoding) {
		this.preEncoding = preEncoding;
	}

	public String getOpenBeginDate() {
		return openBeginDate;
	}

	public void setOpenBeginDate(String openBeginDate) {
		this.openBeginDate = openBeginDate;
	}

	public String getOpenEndDate() {
		return openEndDate;
	}

	public void setOpenEndDate(String openEndDate) {
		this.openEndDate = openEndDate;
	}

	public String getIsMoney() {
		return isMoney;
	}

	public void setIsMoney(String isMoney) {
		this.isMoney = isMoney;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCurrentMonthAge() {
		return currentMonthAge;
	}

	public void setCurrentMonthAge(String currentMonthAge) {
		this.currentMonthAge = currentMonthAge;
	}

	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}

}