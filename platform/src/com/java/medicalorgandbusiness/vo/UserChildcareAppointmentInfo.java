package com.java.medicalorgandbusiness.vo;

import com.java.familydoctor.archivesinfo.vo.ArchivesInfo;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.publichealth.residentsfile.vo.KyUserInfo;

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
	private KyUserInfo userInfo;
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
	
	//------------
	private OrganSetChildCareRecode organSetChildCareRecode;
	private ArchivesInfo archivesInfo;
	//------------
	
	/**
	 * 订单类型（家庭医生套餐;普通订单；机构套餐）
	 */
	private String orderType;
	/**
	 * 当前月龄(为了匹配可做儿保项目内容)
	 */
	private String currentMonthAge;
	
	
	
	public ArchivesInfo getArchivesInfo() {
		return archivesInfo;
	}

	public void setArchivesInfo(ArchivesInfo archivesInfo) {
		this.archivesInfo = archivesInfo;
	}

	public OrganSetChildCareRecode getOrganSetChildCareRecode() {
		return organSetChildCareRecode;
	}

	public void setOrganSetChildCareRecode(
			OrganSetChildCareRecode organSetChildCareRecode) {
		this.organSetChildCareRecode = organSetChildCareRecode;
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

	public String getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}

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

	public KyUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(KyUserInfo userInfo) {
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

}