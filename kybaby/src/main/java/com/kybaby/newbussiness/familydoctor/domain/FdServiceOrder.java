package com.kybaby.newbussiness.familydoctor.domain;

import com.kybaby.domain.UserInfo;

/**
 * 家庭医生服务订单信息
 * @author lihao
 *
 */

public class FdServiceOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private FdServicePackage fdServicePackage;
	private FdServiceTimes fdServiceTimes;
	private FdServiceTeams fdServiceTeams;
	private String orderNum;
	private UserInfo userInfo;
	private String submitTime;
	private Double totalPrice;
	private Double realPrice;
	private String updateTime;
	private String orderStatus;
	private String comments;
	private String payMethod;
	private Double useRemainBalance;
	private String remindInfo;
	private Long usePoints;
	private String serviceEndTime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}

	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}

	public FdServiceTimes getFdServiceTimes() {
		return fdServiceTimes;
	}

	public void setFdServiceTimes(FdServiceTimes fdServiceTimes) {
		this.fdServiceTimes = fdServiceTimes;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Double getUseRemainBalance() {
		return useRemainBalance;
	}

	public void setUseRemainBalance(Double useRemainBalance) {
		this.useRemainBalance = useRemainBalance;
	}

	public String getRemindInfo() {
		return remindInfo;
	}

	public void setRemindInfo(String remindInfo) {
		this.remindInfo = remindInfo;
	}

	public Long getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(Long usePoints) {
		this.usePoints = usePoints;
	}

	public String getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	public FdServiceTeams getFdServiceTeams() {
		return fdServiceTeams;
	}

	public void setFdServiceTeams(FdServiceTeams fdServiceTeams) {
		this.fdServiceTeams = fdServiceTeams;
	}
}