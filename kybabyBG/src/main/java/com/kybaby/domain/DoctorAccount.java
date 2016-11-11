package com.kybaby.domain;

/**
 * DoctorAccount entity. @author MyEclipse Persistence Tools
 */

public class DoctorAccount implements java.io.Serializable {

	// Fields

	private Long id;
	private Long doctorId;
	private Double amount;
	private String type;
	private String accountDesc;
	private String updateTime;
	
	//页面用
	private DoctorInfo doctorInfo;

	// Constructors

	/** default constructor */
	public DoctorAccount() {
	}

	/** full constructor */
	public DoctorAccount(Long doctorId, Double amount, String type,
			String accountDesc, String updateTime) {
		this.doctorId = doctorId;
		this.amount = amount;
		this.type = type;
		this.accountDesc = accountDesc;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountDesc() {
		return this.accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

}