package com.java.medicalorgandbusiness.orgsetpro.vo;

import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeal;
import com.java.operationmanage.vo.HospitalBasicInfo;

/**
 * 机构套餐产品实体信息
 * OrganSetPro entity. @author MyEclipse Persistence Tools
 */

public class OrganSetPro implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganSetPro parentOrganSetPro;
	private HospitalBasicInfo hospitalBasicInfo;
	private OrganSetMeal organSetMeal;
	private String proName;
	private String proPrice;
	private String proCode;
	private String proType;
	private String proImg;
	private String serviceTimeLength;
	private String serviceTimes;
	private String serviceTimeUnit;
	private String minMonthAge;
	private String maxMonthAge;
	private String description;
	private String isEnable;
	private String isOfflinePay;
	private String offLineTime;
	private UserType userType;
	/**
	 * 定价类型（0:实时计算；1：统一定价）
	 */
	private String priceType;
	
	//页面使用
	//是否适合当前用户（月龄超过最大限制就不适合）
	private String isCanChoose;
	//套餐机构中间表id
	private Long middleID;

	//===========================上传图片使用
	private String imgBase64;
	//============================
	
	
	public String getImgBase64() {
		return imgBase64;
	}

	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProPrice() {
		return this.proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}

	public String getProCode() {
		return this.proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProType() {
		return this.proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getProImg() {
		return this.proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	public String getServiceTimeLength() {
		return this.serviceTimeLength;
	}

	public void setServiceTimeLength(String serviceTimeLength) {
		this.serviceTimeLength = serviceTimeLength;
	}

	public String getServiceTimes() {
		return this.serviceTimes;
	}

	public void setServiceTimes(String serviceTimes) {
		this.serviceTimes = serviceTimes;
	}

	public String getServiceTimeUnit() {
		return this.serviceTimeUnit;
	}

	public void setServiceTimeUnit(String serviceTimeUnit) {
		this.serviceTimeUnit = serviceTimeUnit;
	}

	public String getMinMonthAge() {
		return this.minMonthAge;
	}

	public void setMinMonthAge(String minMonthAge) {
		this.minMonthAge = minMonthAge;
	}

	public String getMaxMonthAge() {
		return this.maxMonthAge;
	}

	public void setMaxMonthAge(String maxMonthAge) {
		this.maxMonthAge = maxMonthAge;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsOfflinePay() {
		return this.isOfflinePay;
	}

	public void setIsOfflinePay(String isOfflinePay) {
		this.isOfflinePay = isOfflinePay;
	}

	public String getOffLineTime() {
		return this.offLineTime;
	}

	public void setOffLineTime(String offLineTime) {
		this.offLineTime = offLineTime;
	}

	public OrganSetPro getParentOrganSetPro() {
		return parentOrganSetPro;
	}

	public void setParentOrganSetPro(OrganSetPro parentOrganSetPro) {
		this.parentOrganSetPro = parentOrganSetPro;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getIsCanChoose() {
		return isCanChoose;
	}

	public void setIsCanChoose(String isCanChoose) {
		this.isCanChoose = isCanChoose;
	}

	public OrganSetMeal getOrganSetMeal() {
		return organSetMeal;
	}

	public void setOrganSetMeal(OrganSetMeal organSetMeal) {
		this.organSetMeal = organSetMeal;
	}

	public Long getMiddleID() {
		return middleID;
	}

	public void setMiddleID(Long middleID) {
		this.middleID = middleID;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}


}