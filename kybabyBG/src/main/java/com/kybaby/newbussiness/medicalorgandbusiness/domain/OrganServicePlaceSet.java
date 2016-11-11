package com.kybaby.newbussiness.medicalorgandbusiness.domain;

import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;

/**
 * OrganServicePlaceSet entity. @author MyEclipse Persistence Tools
 */

public class OrganServicePlaceSet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private HospitalBasicInfo hospitalBasicInfo;
	private String serviceType;
	private String windowName;
	private String windowType;
	private String ascriptionUserUser;


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HospitalBasicInfo getHospitalBasicInfo() {
		return hospitalBasicInfo;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getWindowName() {
		return this.windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public String getWindowType() {
		return this.windowType;
	}

	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}

	public String getAscriptionUserUser() {
		return this.ascriptionUserUser;
	}

	public void setAscriptionUserUser(String ascriptionUserUser) {
		this.ascriptionUserUser = ascriptionUserUser;
	}

}