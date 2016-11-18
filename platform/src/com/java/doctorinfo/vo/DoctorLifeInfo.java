package com.java.doctorinfo.vo;

import com.java.operationmanage.vo.DoctorInfo;

/**医生生活信息
 * DoctorLifeInfo entity. @author MyEclipse Persistence Tools
 */

public class DoctorLifeInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorInfo doctorInfo;
	private String graduateSchool;
	private String degree;
	private String hospitalMonthlIncome;
	private String isDivorce;
	private String specialInterests;
	private String remark;

	// Constructors

	/** default constructor */
	public DoctorLifeInfo() {
	}

	/** full constructor */
	public DoctorLifeInfo(Long doctorId, String graduateSchool, String degree,
			String hospitalMonthlIncome, String isDivorce,
			String specialInterests, String remark) {
		this.graduateSchool = graduateSchool;
		this.degree = degree;
		this.hospitalMonthlIncome = hospitalMonthlIncome;
		this.isDivorce = isDivorce;
		this.specialInterests = specialInterests;
		this.remark = remark;
	}

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

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getHospitalMonthlIncome() {
		return this.hospitalMonthlIncome;
	}

	public void setHospitalMonthlIncome(String hospitalMonthlIncome) {
		this.hospitalMonthlIncome = hospitalMonthlIncome;
	}

	public String getIsDivorce() {
		return this.isDivorce;
	}

	public void setIsDivorce(String isDivorce) {
		this.isDivorce = isDivorce;
	}

	public String getSpecialInterests() {
		return this.specialInterests;
	}

	public void setSpecialInterests(String specialInterests) {
		this.specialInterests = specialInterests;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}