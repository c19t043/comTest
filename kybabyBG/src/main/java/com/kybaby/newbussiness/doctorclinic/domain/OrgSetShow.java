package com.kybaby.newbussiness.doctorclinic.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * 多点执业规则显示对象
 */
public class OrgSetShow {
	
	/**
	 * 多点执业机构信息对象
	 */
	private DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo;
	/**
	 * 医生对象
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 上午要求门诊人数
	 */
	private String amCount;
	/**
	 * 下午要求门诊人数
	 */
	private String pmCount;
	/**
	 * 上午开始时间 
	 */
	private String amStartTime;
	/**
	 * 下午开始时间 
	 */
	private String pmStartTime;
	/**
	 * 上午结束时间 
	 */
	private String amEndTime;
	/**
	 * 下午结束时间 
	 */
	private String pmEndTime;
	/**
	 * 外单位门诊费
	 */
	private String clinicMoneyOut;
	/**
	 * 半天保底薪酬
	 */
	private String halfDayMoney;
	/**
	 * 保底薪酬
	 */
	private String baseSalary;
	/**
	 * 每例提成比例（达到基本数后的每例分成）
	 */
	private String commissionPerCase;
	public String getAmCount() {
		return amCount;
	}
	public void setAmCount(String amCount) {
		this.amCount = amCount;
	}
	public String getPmCount() {
		return pmCount;
	}
	public void setPmCount(String pmCount) {
		this.pmCount = pmCount;
	}
	public String getAmStartTime() {
		return amStartTime;
	}
	public void setAmStartTime(String amStartTime) {
		this.amStartTime = amStartTime;
	}
	public String getPmStartTime() {
		return pmStartTime;
	}
	public void setPmStartTime(String pmStartTime) {
		this.pmStartTime = pmStartTime;
	}
	public String getAmEndTime() {
		return amEndTime;
	}
	public void setAmEndTime(String amEndTime) {
		this.amEndTime = amEndTime;
	}
	public String getPmEndTime() {
		return pmEndTime;
	}
	public void setPmEndTime(String pmEndTime) {
		this.pmEndTime = pmEndTime;
	}
	public String getClinicMoneyOut() {
		return clinicMoneyOut;
	}
	public void setClinicMoneyOut(String clinicMoneyOut) {
		this.clinicMoneyOut = clinicMoneyOut;
	}
	public String getHalfDayMoney() {
		return halfDayMoney;
	}
	public void setHalfDayMoney(String halfDayMoney) {
		this.halfDayMoney = halfDayMoney;
	}
	public String getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}
	public String getCommissionPerCase() {
		return commissionPerCase;
	}
	public void setCommissionPerCase(String commissionPerCase) {
		this.commissionPerCase = commissionPerCase;
	}
	public DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfo() {
		return doctorMorePracticeOrgInfo;
	}
	public void setDoctorMorePracticeOrgInfo(
			DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo) {
		this.doctorMorePracticeOrgInfo = doctorMorePracticeOrgInfo;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	
}
