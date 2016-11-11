package com.kybaby.newbussiness.mommyring.domain;

/**
 * RingLabel entity. @author MyEclipse Persistence Tools
 */

public class MommyRingLabel implements java.io.Serializable {

	// Fields

	private Long id;
	private String ringCategory;
	private String ringLabelName;
	private String ringLabelStatus;
	private Long ringLabelClickNum;
	private String ringLabelCreatTime;
	private String ringLabelUpdateTime;

	// Constructors

	/** default constructor */
	public MommyRingLabel() {
	}

	/** full constructor */
	public MommyRingLabel(String ringCategory, String ringLabelName,
			String ringLabelStatus, Long ringLabelClickNum,
			String ringLabelCreatTime, String ringLabelUpdateTime) {
		this.ringCategory = ringCategory;
		this.ringLabelName = ringLabelName;
		this.ringLabelStatus = ringLabelStatus;
		this.ringLabelClickNum = ringLabelClickNum;
		this.ringLabelCreatTime = ringLabelCreatTime;
		this.ringLabelUpdateTime = ringLabelUpdateTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRingCategory() {
		return this.ringCategory;
	}

	public void setRingCategory(String ringCategory) {
		this.ringCategory = ringCategory;
	}

	public String getRingLabelName() {
		return this.ringLabelName;
	}

	public void setRingLabelName(String ringLabelName) {
		this.ringLabelName = ringLabelName;
	}

	public String getRingLabelStatus() {
		return this.ringLabelStatus;
	}

	public void setRingLabelStatus(String ringLabelStatus) {
		this.ringLabelStatus = ringLabelStatus;
	}

	public Long getRingLabelClickNum() {
		return this.ringLabelClickNum;
	}

	public void setRingLabelClickNum(Long ringLabelClickNum) {
		this.ringLabelClickNum = ringLabelClickNum;
	}

	public String getRingLabelCreatTime() {
		return this.ringLabelCreatTime;
	}

	public void setRingLabelCreatTime(String ringLabelCreatTime) {
		this.ringLabelCreatTime = ringLabelCreatTime;
	}

	public String getRingLabelUpdateTime() {
		return this.ringLabelUpdateTime;
	}

	public void setRingLabelUpdateTime(String ringLabelUpdateTime) {
		this.ringLabelUpdateTime = ringLabelUpdateTime;
	}

}