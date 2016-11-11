package com.kybaby.newbussiness.doctorclinic.domain;

import com.kybaby.domain.DoctorInfo;

/**
 * DoctorServiceContent entity. @author MyEclipse Persistence Tools
 */

public class DoctorServiceContent implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private DoctorServiceType doctorServiceType;
	private DoctorInfo doctorInfo; 



	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public DoctorServiceType getDoctorServiceType() {
		return doctorServiceType;
	}

	public void setDoctorServiceType(DoctorServiceType doctorServiceType) {
		this.doctorServiceType = doctorServiceType;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

}