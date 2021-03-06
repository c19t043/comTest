package com.kybaby.newbussiness.familydoctor.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * DoctorWorkTime entity. @author MyEclipse Persistence Tools
 */

public class DoctorWorkTime implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorInfo doctorInfo;
	private String onWorkTime;
	private String offWorkTime;
	private String isEnable;
	private String remark;

	// Constructors

	/** default constructor */
	public DoctorWorkTime() {
	}

	/** full constructor */
	public DoctorWorkTime(Long doctorId, String onWorkTime, String offWorkTime,
			String isEnable, String remark) {
		this.onWorkTime = onWorkTime;
		this.offWorkTime = offWorkTime;
		this.isEnable = isEnable;
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

	public String getOnWorkTime() {
		return this.onWorkTime;
	}

	public void setOnWorkTime(String onWorkTime) {
		this.onWorkTime = onWorkTime;
	}

	public String getOffWorkTime() {
		return this.offWorkTime;
	}

	public void setOffWorkTime(String offWorkTime) {
		this.offWorkTime = offWorkTime;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}