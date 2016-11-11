package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * OrganChildcareOpenResources entity. @author MyEclipse Persistence Tools
 */

public class OrganChildcareOpenResources implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private String openDate;
	private String openStartTime;
	private String openEndTime;
	private String restStartTime;
	private String restEndTime;
	private String timeDivisionValue;
	private String isAvailable;
	private String timeDivisionType;
	
	
	private String timeDivisionNeed;
	private String generalNum;
	private String greenChannelNum;
	private String generalSurplusNum;
	private String greenChannelSurplusNum;
	/**
	 * 是否收费
	 */
	private String isMoney;
	/**
	 * 坐诊医生id串（，隔开）
	 */
	private String doctorIds;
	/**
	 * 预约截止时间
	 */
	private String deadline;
	/**
	 * 预约截止时间开关（Y/N）
	 */
	private String isUseDeadline;
	//页面展示用
	//坐诊医生姓名串
	private String doctorNames;

	//医院职称id
	private Long hospitalPositionId;
	
	
	public Long getHospitalPositionId() {
		return hospitalPositionId;
	}

	public void setHospitalPositionId(Long hospitalPositionId) {
		this.hospitalPositionId = hospitalPositionId;
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

	public String getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenStartTime() {
		return this.openStartTime;
	}

	public void setOpenStartTime(String openStartTime) {
		this.openStartTime = openStartTime;
	}

	public String getOpenEndTime() {
		return this.openEndTime;
	}

	public void setOpenEndTime(String openEndTime) {
		this.openEndTime = openEndTime;
	}

	public String getRestStartTime() {
		return this.restStartTime;
	}

	public void setRestStartTime(String restStartTime) {
		this.restStartTime = restStartTime;
	}

	public String getRestEndTime() {
		return this.restEndTime;
	}

	public void setRestEndTime(String restEndTime) {
		this.restEndTime = restEndTime;
	}

	public String getTimeDivisionValue() {
		return timeDivisionValue;
	}

	public void setTimeDivisionValue(String timeDivisionValue) {
		this.timeDivisionValue = timeDivisionValue;
	}

	public String getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getTimeDivisionNeed() {
		return timeDivisionNeed;
	}

	public void setTimeDivisionNeed(String timeDivisionNeed) {
		this.timeDivisionNeed = timeDivisionNeed;
	}

	public String getGeneralNum() {
		return generalNum;
	}

	public void setGeneralNum(String generalNum) {
		this.generalNum = generalNum;
	}

	public String getGreenChannelNum() {
		return greenChannelNum;
	}

	public void setGreenChannelNum(String greenChannelNum) {
		this.greenChannelNum = greenChannelNum;
	}

	public String getGeneralSurplusNum() {
		return generalSurplusNum;
	}

	public void setGeneralSurplusNum(String generalSurplusNum) {
		this.generalSurplusNum = generalSurplusNum;
	}

	public String getGreenChannelSurplusNum() {
		return greenChannelSurplusNum;
	}

	public void setGreenChannelSurplusNum(String greenChannelSurplusNum) {
		this.greenChannelSurplusNum = greenChannelSurplusNum;
	}

	public String getTimeDivisionType() {
		return timeDivisionType;
	}

	public void setTimeDivisionType(String timeDivisionType) {
		this.timeDivisionType = timeDivisionType;
	}

	public String getIsMoney() {
		return isMoney;
	}

	public void setIsMoney(String isMoney) {
		this.isMoney = isMoney;
	}

	public String getDoctorIds() {
		return doctorIds;
	}

	public void setDoctorIds(String doctorIds) {
		this.doctorIds = doctorIds;
	}

	public String getDoctorNames() {
		return doctorNames;
	}

	public void setDoctorNames(String doctorNames) {
		this.doctorNames = doctorNames;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getIsUseDeadline() {
		return isUseDeadline;
	}

	public void setIsUseDeadline(String isUseDeadline) {
		this.isUseDeadline = isUseDeadline;
	}

}