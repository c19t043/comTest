package com.kybaby.domain;

/**
 * Major entity. @author MyEclipse Persistence Tools
 */

public class Major implements java.io.Serializable {

	// Fields

	private Long id;
	private String major;
	private String majorStatus;

	// Constructors

	/** default constructor */
	public Major() {
	}

	/** full constructor */
	public Major(String major, String majorStatus) {
		this.major = major;
		this.majorStatus = majorStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
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