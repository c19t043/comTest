package com.kybaby.newbussiness.doctorclinic.domain;

/**
 * DoctorClinicTimeSegment entity. @author MyEclipse Persistence Tools
 */

public class DoctorClinicTimeSegment implements java.io.Serializable {

	// Fields

	private Long id;
	private DoctorMorePractice doctorMorePractice;
	private String segment;
	private String isCanUse;


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getIsCanUse() {
		return this.isCanUse;
	}

	public void setIsCanUse(String isCanUse) {
		this.isCanUse = isCanUse;
	}

	public DoctorMorePractice getDoctorMorePractice() {
		return doctorMorePractice;
	}

	public void setDoctorMorePractice(DoctorMorePractice doctorMorePractice) {
		this.doctorMorePractice = doctorMorePractice;
	}

}