package com.java.doctorinfo.vo;

/**
 * DoctorServiceType entity. @author MyEclipse Persistence Tools
 */

public class DoctorServiceType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String serviceTypeName;
	private String isAvailable;
	private DoctorServiceType parentDoctorServiceType;


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceTypeName() {
		return this.serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public DoctorServiceType getParentDoctorServiceType() {
		return parentDoctorServiceType;
	}

	public void setParentDoctorServiceType(DoctorServiceType parentDoctorServiceType) {
		this.parentDoctorServiceType = parentDoctorServiceType;
	}


}