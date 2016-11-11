package com.java.operationmanage.vo;

import java.util.List;

/**
 * HospitalBasicInfo entity. @author MyEclipse Persistence Tools
 */

public class HospitalBasicInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String province;
	private String city;
	private String hospitalLname;
	private String address;
	private String hospitalLevel;
	private String hospitalType;
	private String introduction;
	private String orgDynamic;
	private String tel;
	private String manager;
	private String managerPhone;
	private String hospitalLng;
	private String hospitalLat;
	/**
	 * 是否对用户显示Y/N
	 */
	private String isShowForUser;
	/**
	 * 医院性质（公立；私立）
	 */
	private String hospitalNature;
	/**
	 * 医院本地系统编码
	 */
	private String ElseOrgID;
	
	//============================页面显示用
	//与用户距离
	private Double toUserDistance;
	//机构开展业务名称集合
	private List<String> businessNameList;
	//机构展示图片
	private String showImgPath;
	//粉丝数
	private String userFollows;
	//门诊数
	private String clinics;
	//服务医生数
	private String doctors;
	//门诊时间集合
	private List<String> clinicBookingDate;

	// Constructors

	/** default constructor */
	public HospitalBasicInfo() {
	}

	/** full constructor */
	public HospitalBasicInfo(String province, String city,
			String hospitalLname, String address, String hospitalLevel) {
		this.province = province;
		this.city = city;
		this.hospitalLname = hospitalLname;
		this.address = address;
		this.hospitalLevel = hospitalLevel;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public String getElseOrgID() {
		return ElseOrgID;
	}

	public void setElseOrgID(String elseOrgID) {
		ElseOrgID = elseOrgID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHospitalLname() {
		return this.hospitalLname;
	}

	public void setHospitalLname(String hospitalLname) {
		this.hospitalLname = hospitalLname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHospitalLevel() {
		return this.hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getOrgDynamic() {
		return orgDynamic;
	}

	public void setOrgDynamic(String orgDynamic) {
		this.orgDynamic = orgDynamic;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getHospitalLng() {
		return hospitalLng;
	}

	public void setHospitalLng(String hospitalLng) {
		this.hospitalLng = hospitalLng;
	}

	public String getHospitalLat() {
		return hospitalLat;
	}

	public void setHospitalLat(String hospitalLat) {
		this.hospitalLat = hospitalLat;
	}

	public Double getToUserDistance() {
		return toUserDistance;
	}

	public void setToUserDistance(Double toUserDistance) {
		this.toUserDistance = toUserDistance;
	}

	public List<String> getBusinessNameList() {
		return businessNameList;
	}

	public void setBusinessNameList(List<String> businessNameList) {
		this.businessNameList = businessNameList;
	}

	public String getShowImgPath() {
		return showImgPath;
	}

	public void setShowImgPath(String showImgPath) {
		this.showImgPath = showImgPath;
	}

	public String getUserFollows() {
		return userFollows;
	}

	public void setUserFollows(String userFollows) {
		this.userFollows = userFollows;
	}

	public String getClinics() {
		return clinics;
	}

	public void setClinics(String clinics) {
		this.clinics = clinics;
	}

	public String getDoctors() {
		return doctors;
	}

	public void setDoctors(String doctors) {
		this.doctors = doctors;
	}

	public List<String> getClinicBookingDate() {
		return clinicBookingDate;
	}

	public void setClinicBookingDate(List<String> clinicBookingDate) {
		this.clinicBookingDate = clinicBookingDate;
	}

	public String getIsShowForUser() {
		return isShowForUser;
	}

	public void setIsShowForUser(String isShowForUser) {
		this.isShowForUser = isShowForUser;
	}

	public String getHospitalNature() {
		return hospitalNature;
	}

	public void setHospitalNature(String hospitalNature) {
		this.hospitalNature = hospitalNature;
	}

}