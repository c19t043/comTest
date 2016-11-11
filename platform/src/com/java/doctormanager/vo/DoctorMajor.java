package com.java.doctormanager.vo;

/**
 * Major entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DoctorMajor implements java.io.Serializable {

	// Fields

	private Long id;
	private String major;
	private String majorStatus;
	private DoctorMajor parent;
	private String doctorType;
	
	// Constructors

	/** default constructor */
	public DoctorMajor() {
	}

	/** full constructor */
	public DoctorMajor(String major, String majorStatus) {
		this.major = major;
		this.majorStatus = majorStatus;
	}
	// Property accessors

	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}

	public Long getId() {
		return this.id;
	}

	public DoctorMajor getParent() {
		return parent;
	}

	public void setParent(DoctorMajor parent) {
		this.parent = parent;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajorStatus() {
		return this.majorStatus;
	}

	public void setMajorStatus(String majorStatus) {
		this.majorStatus = majorStatus;
	}

}