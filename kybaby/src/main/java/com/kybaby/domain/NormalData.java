package com.kybaby.domain;

/**
 * NormalData entity. @author MyEclipse Persistence Tools
 */

public class NormalData implements java.io.Serializable {

	// Fields

	private Long id;
	private String sex;
	private Long monthYear;
	private String normalHeight;
	private String heightTips;
	private String normalWeight;
	private String weightTips;
	private String normalHead;
	private String headTips;

	// Constructors

	/** default constructor */
	public NormalData() {
	}

	/** full constructor */
	public NormalData(String sex, Long monthYear, String normalHeight,
			String heightTips, String normalWeight, String weightTips,
			String normalHead, String headTips) {
		this.sex = sex;
		this.monthYear = monthYear;
		this.normalHeight = normalHeight;
		this.heightTips = heightTips;
		this.normalWeight = normalWeight;
		this.weightTips = weightTips;
		this.normalHead = normalHead;
		this.headTips = headTips;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getMonthYear() {
		return this.monthYear;
	}

	public void setMonthYear(Long monthYear) {
		this.monthYear = monthYear;
	}

	public String getNormalHeight() {
		return this.normalHeight;
	}

	public void setNormalHeight(String normalHeight) {
		this.normalHeight = normalHeight;
	}

	public String getHeightTips() {
		return this.heightTips;
	}

	public void setHeightTips(String heightTips) {
		this.heightTips = heightTips;
	}

	public String getNormalWeight() {
		return this.normalWeight;
	}

	public void setNormalWeight(String normalWeight) {
		this.normalWeight = normalWeight;
	}

	public String getWeightTips() {
		return this.weightTips;
	}

	public void setWeightTips(String weightTips) {
		this.weightTips = weightTips;
	}

	public String getNormalHead() {
		return this.normalHead;
	}

	public void setNormalHead(String normalHead) {
		this.normalHead = normalHead;
	}

	public String getHeadTips() {
		return this.headTips;
	}

	public void setHeadTips(String headTips) {
		this.headTips = headTips;
	}

}