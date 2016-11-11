package com.kybaby.newbussiness.doctorclinic.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * DoctorMorePractice entity. @author MyEclipse Persistence Tools
 */

public class DoctorMorePractice implements java.io.Serializable {

	// Fields
	/**
	 * 门诊机构类型（0：本院）
	 */
	public static final String CLINIC_ORG_TYPE_0="0";
	/**
	 * 门诊机构类型（1：多点机构）
	 */
	public static final String CLINIC_ORG_TYPE_1="1";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorInfo doctorInfo;
	private DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo;
	private String clinicDate;
	private String clinicEndTime;
	private String clinicBeganTime;
	private Long canClinicNum;
	private String clinicOrg;
	private String clinicAddress;
	private String isRepeat;
	private String clinicOrgType;
	private String isAddClinic;
	private String operationTime;
	//门诊时间分段（08:00,08:15,08:30）
	private String clinicTimeSegment;
	/**
	 * 外单位门诊时间
	 */
	private String  orgClinicTimeIds;
	/**
	 * 外单位门诊时间显示所用（上午、下午）
	 */
	private String orgClinicTimeShow;
	/**
	 * 外单位要求门诊人数显示所用
	 */
	private String requireClinicNumShow;
	
	//外单位门诊状态
	private String orgClinicStatus;
	//外单位门诊开始记录时间
	private String orgClinicStartTime;
	//外单位门诊开始记录地址
	private String orgClinicStartAddress;
	//外单位门诊结束记录时间
	private String orgClinicEndTime;
	//外单位门诊结束记录地址
	private String orgClinicEndAddress;
	/**
	 * 医生多点执业上下班控制状态
	 */
	private String startEndFlag;
	/**
	 * 医生面向用户类型ids
	 */
	private String userTypeIds;
	/**
	 * 医生面向用户类型names
	 */
	private String userTypeNames;
	
	public static final String START_WORK="我要上班";
	
	public static final String END_WORK="我要下班";

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public String getClinicDate() {
		return this.clinicDate;
	}

	public void setClinicDate(String clinicDate) {
		this.clinicDate = clinicDate;
	}

	public String getClinicEndTime() {
		return this.clinicEndTime;
	}

	public void setClinicEndTime(String clinicEndTime) {
		this.clinicEndTime = clinicEndTime;
	}

	public String getClinicBeganTime() {
		return this.clinicBeganTime;
	}

	public void setClinicBeganTime(String clinicBeganTime) {
		this.clinicBeganTime = clinicBeganTime;
	}

	public Long getCanClinicNum() {
		return this.canClinicNum;
	}

	public void setCanClinicNum(Long canClinicNum) {
		this.canClinicNum = canClinicNum;
	}

	public String getClinicOrg() {
		return this.clinicOrg;
	}

	public void setClinicOrg(String clinicOrg) {
		this.clinicOrg = clinicOrg;
	}

	public String getClinicAddress() {
		return this.clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getClinicOrgType() {
		return this.clinicOrgType;
	}

	public void setClinicOrgType(String clinicOrgType) {
		this.clinicOrgType = clinicOrgType;
	}

	public String getIsAddClinic() {
		return this.isAddClinic;
	}

	public void setIsAddClinic(String isAddClinic) {
		this.isAddClinic = isAddClinic;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfo() {
		return doctorMorePracticeOrgInfo;
	}

	public String getOrgClinicStatus() {
		return orgClinicStatus;
	}

	public void setOrgClinicStatus(String orgClinicStatus) {
		this.orgClinicStatus = orgClinicStatus;
	}

	public String getOrgClinicStartTime() {
		return orgClinicStartTime;
	}

	public void setOrgClinicStartTime(String orgClinicStartTime) {
		this.orgClinicStartTime = orgClinicStartTime;
	}

	public String getOrgClinicStartAddress() {
		return orgClinicStartAddress;
	}

	public void setOrgClinicStartAddress(String orgClinicStartAddress) {
		this.orgClinicStartAddress = orgClinicStartAddress;
	}

	public String getOrgClinicEndTime() {
		return orgClinicEndTime;
	}

	public void setOrgClinicEndTime(String orgClinicEndTime) {
		this.orgClinicEndTime = orgClinicEndTime;
	}

	public String getOrgClinicEndAddress() {
		return orgClinicEndAddress;
	}

	public void setOrgClinicEndAddress(String orgClinicEndAddress) {
		this.orgClinicEndAddress = orgClinicEndAddress;
	}

	public void setDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		this.doctorMorePracticeOrgInfo = doctorMorePracticeOrgInfo;
	}

	public String getOrgClinicTimeIds() {
		return orgClinicTimeIds;
	}

	public void setOrgClinicTimeIds(String orgClinicTimeIds) {
		this.orgClinicTimeIds = orgClinicTimeIds;
	}

	public String getClinicTimeSegment() {
		return clinicTimeSegment;
	}

	public void setClinicTimeSegment(String clinicTimeSegment) {
		this.clinicTimeSegment = clinicTimeSegment;
	}

	public String getOrgClinicTimeShow() {
		return orgClinicTimeShow;
	}

	public void setOrgClinicTimeShow(String orgClinicTimeShow) {
		this.orgClinicTimeShow = orgClinicTimeShow;
	}

	public String getRequireClinicNumShow() {
		return requireClinicNumShow;
	}

	public void setRequireClinicNumShow(String requireClinicNumShow) {
		this.requireClinicNumShow = requireClinicNumShow;
	}
	public String getStartEndFlag() {
		return startEndFlag;
	}
	public void setStartEndFlag(String startEndFlag) {
		this.startEndFlag = startEndFlag;
	}

	public String getUserTypeIds() {
		return userTypeIds;
	}

	public void setUserTypeIds(String userTypeIds) {
		this.userTypeIds = userTypeIds;
	}

	public String getUserTypeNames() {
		return userTypeNames;
	}

	public void setUserTypeNames(String userTypeNames) {
		this.userTypeNames = userTypeNames;
	}
	
}