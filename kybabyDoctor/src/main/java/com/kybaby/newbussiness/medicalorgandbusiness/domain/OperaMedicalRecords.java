package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/** 
 * 就诊记录信息
 * OperaMedicalRecords entity. @author MyEclipse Persistence Tools
 */

public class OperaMedicalRecords implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String clinicTime;
	private HospitalBasicInfo hospitalBasicInfo;
	private String boType;
	private String patientName;
	private String patientSex;
	private String patientBirthday;
	private String patientPhone;
	private String patientParentName;
	private String patientAddress;
	private String isGuahao;
	private String isRegister;
	private String isPreAppointment;
	private String isNowAppointment;
	private String isPay;
	private String isPrescription;
	private String isCheck;
	private String isTest;
	private String isCreateFile;
	private String operateTime;
	private OrganOperator organOperator;
	private String remark;

	// Constructors

	/** default constructor */
	public OperaMedicalRecords() {
	}

	/** full constructor */
	public OperaMedicalRecords(String clinicTime, Long hospitalId,
			String boType, String patientName, String patientSex,
			String patientBirthday, String patientPhone,
			String patientParentName, String patientAddress, String isGuahao,
			String isRegister, String isPreAppointment,
			String isNowAppointment, String isPay, String isPrescription,
			String isCheck, String isTest, String isCreateFile,
			String operateTime, String operatorId, String remark) {
		this.clinicTime = clinicTime;
		this.boType = boType;
		this.patientName = patientName;
		this.patientSex = patientSex;
		this.patientBirthday = patientBirthday;
		this.patientPhone = patientPhone;
		this.patientParentName = patientParentName;
		this.patientAddress = patientAddress;
		this.isGuahao = isGuahao;
		this.isRegister = isRegister;
		this.isPreAppointment = isPreAppointment;
		this.isNowAppointment = isNowAppointment;
		this.isPay = isPay;
		this.isPrescription = isPrescription;
		this.isCheck = isCheck;
		this.isTest = isTest;
		this.isCreateFile = isCreateFile;
		this.operateTime = operateTime;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClinicTime() {
		return this.clinicTime;
	}

	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getBoType() {
		return this.boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSex() {
		return this.patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientBirthday() {
		return this.patientBirthday;
	}

	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public String getPatientPhone() {
		return this.patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientParentName() {
		return this.patientParentName;
	}

	public void setPatientParentName(String patientParentName) {
		this.patientParentName = patientParentName;
	}

	public String getPatientAddress() {
		return this.patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getIsGuahao() {
		return this.isGuahao;
	}

	public void setIsGuahao(String isGuahao) {
		this.isGuahao = isGuahao;
	}

	public String getIsRegister() {
		return this.isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getIsPreAppointment() {
		return this.isPreAppointment;
	}

	public void setIsPreAppointment(String isPreAppointment) {
		this.isPreAppointment = isPreAppointment;
	}

	public String getIsNowAppointment() {
		return this.isNowAppointment;
	}

	public void setIsNowAppointment(String isNowAppointment) {
		this.isNowAppointment = isNowAppointment;
	}

	public String getIsPay() {
		return this.isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getIsPrescription() {
		return this.isPrescription;
	}

	public void setIsPrescription(String isPrescription) {
		this.isPrescription = isPrescription;
	}

	public String getIsCheck() {
		return this.isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getIsTest() {
		return this.isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}

	public String getIsCreateFile() {
		return this.isCreateFile;
	}

	public void setIsCreateFile(String isCreateFile) {
		this.isCreateFile = isCreateFile;
	}

	public String getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public OrganOperator getOrganOperator() {
		return organOperator;
	}

	public void setOrganOperator(OrganOperator organOperator) {
		this.organOperator = organOperator;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}