package com.kybaby.kyinterface.domain;

import java.sql.Timestamp;

import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

/**
 * 在计免订单的基础上，增加用户对象字段
 */
/**
 * 快医计免订单id与我方计免订单id对应关系表
 CREATE TABLE kyInoculationOrder2MyOrder(
	id BIGINT(11) COMMENT '订单id',
	kyid VARCHAR(64) COMMENT '快医计免订单Id',
	PRIMARY KEY(id,kyid)
)  
 */
public class InoculationOrderCustom extends UserInoculationAppointmentInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1227147956086992956L;

	//----------------------必传
	private String kyOrderId;// 快医，计免订单id、
	private String kyUserId;//用户id
	
	private String userName;//: 用户姓名
	private String userPhone;//: 用户电话
	private String userAddress;//: 用户地址
	//----------------------
	
	
	
	private String userSource;// 用户来源
	// 快医传递用户对象，用于,我方创建用户记录
	private String userSourceId;// 用户来源ID
	// -------------------------------------------------------
	
	
	
	private Long open_resources_id;// 开放资源外键
	private Long open_resources_detail_id;// 开放资源明细外键
	private Long ascription_organ;// 归属机构
	private String appointment_code;// 预约编码
	private String opt_time;// 操作时间
	// ---------------------------------------
	// 用户字段
	// private Long id;//没有传入用户Id
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Long getAccountPoints() {
		return accountPoints;
	}

	public void setAccountPoints(Long accountPoints) {
		this.accountPoints = accountPoints;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Timestamp getLastestPayTime() {
		return lastestPayTime;
	}

	public void setLastestPayTime(Timestamp lastestPayTime) {
		this.lastestPayTime = lastestPayTime;
	}

	public Long getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(Long recommendNum) {
		this.recommendNum = recommendNum;
	}

	public Double getTotalConsume() {
		return totalConsume;
	}

	public void setTotalConsume(Double totalConsume) {
		this.totalConsume = totalConsume;
	}

	public String getUserLng() {
		return userLng;
	}

	public void setUserLng(String userLng) {
		this.userLng = userLng;
	}

	public String getUserLat() {
		return userLat;
	}

	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public String getUserStreet() {
		return userStreet;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getBabyName() {
		return babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public Long getUseAppTimes() {
		return useAppTimes;
	}

	public void setUseAppTimes(Long useAppTimes) {
		this.useAppTimes = useAppTimes;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	public String getUserSourceId() {
		return userSourceId;
	}

	public void setUserSourceId(String userSourceId) {
		this.userSourceId = userSourceId;
	}

	public Long getOpen_resources_id() {
		return open_resources_id;
	}

	public void setOpen_resources_id(Long open_resources_id) {
		this.open_resources_id = open_resources_id;
	}

	public Long getOpen_resources_detail_id() {
		return open_resources_detail_id;
	}

	public void setOpen_resources_detail_id(Long open_resources_detail_id) {
		this.open_resources_detail_id = open_resources_detail_id;
	}

	public Long getAscription_organ() {
		return ascription_organ;
	}

	public void setAscription_organ(Long ascription_organ) {
		this.ascription_organ = ascription_organ;
	}

	public String getAppointment_code() {
		return appointment_code;
	}

	public void setAppointment_code(String appointment_code) {
		this.appointment_code = appointment_code;
	}

	public String getOpt_time() {
		return opt_time;
	}

	public void setOpt_time(String opt_time) {
		this.opt_time = opt_time;
	}

	public String getKyOrderId() {
		return kyOrderId;
	}

	public void setKyOrderId(String kyOrderId) {
		this.kyOrderId = kyOrderId;
	}

	public String getKyUserId() {
		return kyUserId;
	}

	public void setKyUserId(String kyUserId) {
		this.kyUserId = kyUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}
