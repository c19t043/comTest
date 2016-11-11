package com.kybaby.domain;

import java.sql.Timestamp;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String openId;
	private String nickName;
	private String password;
	private String birthday;
	private String sex;
	private String phone;
	private Double accountBalance;
	private Long accountPoints;
	private Timestamp registerTime;
	private String userStatus;
	private Timestamp lastestPayTime;
	private Long recommendNum;
	private Double totalConsume;
	private String userLng;
	private String userLat;
	private String userProvince;
	private String userCity;
	private String userArea;
	private String userStreet;
	private String detailAddress;
	private String comments;
	private String parentName;
	private String babyName;
	private Long useAppTimes;
	private String isLogin;
	private String userImage;
	private String moonSage;  //月龄  只是页面传值
	/**
	 * 家庭医生服务是否过期
	 */
	private String fdServicePast;  
	
	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(String openId, String nickName, String password,
			String birthday, String sex, String phone, Double accountBalance,
			Long accountPoints, Timestamp registerTime, String userStatus,
			Timestamp lastestPayTime, Long recommendNum, Double totalConsume,
			String userLng, String userLat, String userProvince,
			String userCity, String userArea, String userStreet,
			String detailAddress, String comments, String parentName,
			String babyName, Long useAppTimes, String isLogin, String userImage) {
		this.openId = openId;
		this.nickName = nickName;
		this.password = password;
		this.birthday = birthday;
		this.sex = sex;
		this.phone = phone;
		this.accountBalance = accountBalance;
		this.accountPoints = accountPoints;
		this.registerTime = registerTime;
		this.userStatus = userStatus;
		this.lastestPayTime = lastestPayTime;
		this.recommendNum = recommendNum;
		this.totalConsume = totalConsume;
		this.userLng = userLng;
		this.userLat = userLat;
		this.userProvince = userProvince;
		this.userCity = userCity;
		this.userArea = userArea;
		this.userStreet = userStreet;
		this.detailAddress = detailAddress;
		this.comments = comments;
		this.parentName = parentName;
		this.babyName = babyName;
		this.useAppTimes = useAppTimes;
		this.isLogin = isLogin;
		this.userImage = userImage;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Long getAccountPoints() {
		return this.accountPoints;
	}

	public void setAccountPoints(Long accountPoints) {
		this.accountPoints = accountPoints;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Timestamp getLastestPayTime() {
		return this.lastestPayTime;
	}

	public void setLastestPayTime(Timestamp lastestPayTime) {
		this.lastestPayTime = lastestPayTime;
	}

	public Long getRecommendNum() {
		return this.recommendNum;
	}

	public void setRecommendNum(Long recommendNum) {
		this.recommendNum = recommendNum;
	}

	public Double getTotalConsume() {
		return this.totalConsume;
	}

	public void setTotalConsume(Double totalConsume) {
		this.totalConsume = totalConsume;
	}

	public String getUserLng() {
		return this.userLng;
	}

	public void setUserLng(String userLng) {
		this.userLng = userLng;
	}

	public String getUserLat() {
		return this.userLat;
	}

	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}

	public String getUserProvince() {
		return this.userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserArea() {
		return this.userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public String getUserStreet() {
		return this.userStreet;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getBabyName() {
		return this.babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public Long getUseAppTimes() {
		return this.useAppTimes;
	}

	public void setUseAppTimes(Long useAppTimes) {
		this.useAppTimes = useAppTimes;
	}

	public String getIsLogin() {
		return this.isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getUserImage() {
		return this.userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getMoonSage() {
		return moonSage;
	}

	public void setMoonSage(String moonSage) {
		this.moonSage = moonSage;
	}

	public String getFdServicePast() {
		return fdServicePast;
	}

	public void setFdServicePast(String fdServicePast) {
		this.fdServicePast = fdServicePast;
	}

}