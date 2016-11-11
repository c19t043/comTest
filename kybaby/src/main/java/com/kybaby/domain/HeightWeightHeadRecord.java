package com.kybaby.domain;

/**
 * HeightWeightHeadRecord entity. @author MyEclipse Persistence Tools
 */

public class HeightWeightHeadRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String height;
	private String weight;
	private String headLength;
	private String recordTime;
	private String babyMonthYear;
	private String recordStatus;

	// Constructors

	/** default constructor */
	public HeightWeightHeadRecord() {
	}

	/** full constructor */
	public HeightWeightHeadRecord(Long userId, String height, String weight,
			String headLength, String recordTime, String babyMonthYear,
			String recordStatus) {
		this.userId = userId;
		this.height = height;
		this.weight = weight;
		this.headLength = headLength;
		this.recordTime = recordTime;
		this.babyMonthYear = babyMonthYear;
		this.recordStatus = recordStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeadLength() {
		return this.headLength;
	}

	public void setHeadLength(String headLength) {
		this.headLength = headLength;
	}

	public String getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getBabyMonthYear() {
		return this.babyMonthYear;
	}

	public void setBabyMonthYear(String babyMonthYear) {
		this.babyMonthYear = babyMonthYear;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

}