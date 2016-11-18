package com.kybaby.newbussiness.doctorsign.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * DoctorOrderSummary entity. @author MyEclipse Persistence Tools
 */

public class DoctorOrderSummary implements java.io.Serializable {

	// Fields

	private Long id;
	private DoctorInfo doctorInfo;
	private String visitDate;
	private String bussinessType;
	private Long bussinessId;

	//次数
	private String count;
	
	// Constructors

	/** default constructor */
	public DoctorOrderSummary() {
	}

	// Property accessors

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getVisitDate() {
		return this.visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getBussinessType() {
		return this.bussinessType;
	}

	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public Long getBussinessId() {
		return this.bussinessId;
	}

	public void setBussinessId(Long bussinessId) {
		this.bussinessId = bussinessId;
	}

}