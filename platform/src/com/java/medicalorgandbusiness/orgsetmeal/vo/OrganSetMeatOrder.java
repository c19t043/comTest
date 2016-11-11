package com.java.medicalorgandbusiness.orgsetmeal.vo;

import com.java.medicalorgandbusiness.orgsetpro.vo.OrganSetPro;
import com.java.publichealth.residentsfile.vo.KyUserInfo;

/**
 * 机构套餐订单实体信息
 * OrganSetMeatOrder entity. @author MyEclipse Persistence Tools
 */

public class OrganSetMeatOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private OrganSetMeal organSetMeal;
	private OrganSetPro organSetPro;
	private ConsultBabyInfo babyInfo;
	private String orderNum;
	private KyUserInfo userInfo;
	private String submitTime;
	private Double totalPrice;
	private Double realPrice;
	private Double useRemainBalance;
	private String updateTime;
	private String orderStatus;
	private String comments;
	private String payMethod;
	private String remindInfo;
	private Long usePoints;
	private String serviceEndTime;
	private String serviceTimes;
	private String serviceSurplusTimes;


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

	public KyUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(KyUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getRealPrice() {
		return this.realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public Double getUseRemainBalance() {
		return this.useRemainBalance;
	}

	public void setUseRemainBalance(Double useRemainBalance) {
		this.useRemainBalance = useRemainBalance;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getRemindInfo() {
		return this.remindInfo;
	}

	public void setRemindInfo(String remindInfo) {
		this.remindInfo = remindInfo;
	}

	public Long getUsePoints() {
		return this.usePoints;
	}

	public void setUsePoints(Long usePoints) {
		this.usePoints = usePoints;
	}

	public String getServiceEndTime() {
		return this.serviceEndTime;
	}

	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	public String getServiceTimes() {
		return this.serviceTimes;
	}

	public void setServiceTimes(String serviceTimes) {
		this.serviceTimes = serviceTimes;
	}

	public String getServiceSurplusTimes() {
		return this.serviceSurplusTimes;
	}

	public void setServiceSurplusTimes(String serviceSurplusTimes) {
		this.serviceSurplusTimes = serviceSurplusTimes;
	}

	public OrganSetMeal getOrganSetMeal() {
		return organSetMeal;
	}

	public void setOrganSetMeal(OrganSetMeal organSetMeal) {
		this.organSetMeal = organSetMeal;
	}

	public OrganSetPro getOrganSetPro() {
		return organSetPro;
	}

	public void setOrganSetPro(OrganSetPro organSetPro) {
		this.organSetPro = organSetPro;
	}

	public ConsultBabyInfo getBabyInfo() {
		return babyInfo;
	}

	public void setBabyInfo(ConsultBabyInfo babyInfo) {
		this.babyInfo = babyInfo;
	}

}