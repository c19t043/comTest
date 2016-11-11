package com.kybaby.newbussiness.doctorclinic.domain;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Position;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class HospitalPosition implements java.io.Serializable {

	private static final long serialVersionUID = 7033553017944206925L;
	private Long id;
	private Position position;
	/**
	 * 分成比例
	 */
	private String  commission;
	/**
	 * 保底薪酬
	 */
	private String  baseSalary;
	/**
	 * 每例提成比例
	 */
	private String  commissionPerCase;
	/**
	 * 半天保底薪酬
	 */
	private String  halfDayMoney;
	/**
	 * 医院信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 多点执业机构
	 */
	private DoctorMorePracticeOrgInfo  doctorMorePracticeOrgInfo;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	
	/**
	 * 本单位加号门诊费
	 */
	private String  clinicMoney;
	
	/**
	 * 外单位加号门诊费
	 */
	private String clinicMoneyOut;
	/**
	 * 业务分类（0：门诊；1：儿保）
	 */
	private String businessType;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
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

	public String getHalfDayMoney() {
		return halfDayMoney;
	}

	public void setHalfDayMoney(String halfDayMoney) {
		this.halfDayMoney = halfDayMoney;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getClinicMoney() {
		return clinicMoney;
	}

	public void setClinicMoney(String clinicMoney) {
		this.clinicMoney = clinicMoney;
	}

	public String getClinicMoneyOut() {
		return clinicMoneyOut;
	}

	public void setClinicMoneyOut(String clinicMoneyOut) {
		this.clinicMoneyOut = clinicMoneyOut;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}