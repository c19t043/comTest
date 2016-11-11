package com.kybaby.domain;

/**
 * ItemResult entity. @author MyEclipse Persistence Tools
 */

public class ItemResult implements java.io.Serializable {

	// Fields

	private Long id;
	private String itemResultNum;
	private String itemResultName;
	private String resultUnit;
	private String resultType;
	private String options;
	private String comments;
	private String isNeedRemind;
	private String maxValue;
	private String maxValueRemind;
	private String minValue;
	private String minValueRemind;
	/**
	 * 是否需要备注标示
	 */
	private String resultRemarkFlag;

	// Constructors

	/** default constructor */
	public ItemResult() {
	}

	/** full constructor */
	public ItemResult(String itemResultNum, String itemResultName,
			String resultUnit, String resultType, String options,
			String comments, String isNeedRemind, String maxValue,
			String maxValueRemind, String minValue, String minValueRemind) {
		this.itemResultNum = itemResultNum;
		this.itemResultName = itemResultName;
		this.resultUnit = resultUnit;
		this.resultType = resultType;
		this.options = options;
		this.comments = comments;
		this.isNeedRemind = isNeedRemind;
		this.maxValue = maxValue;
		this.maxValueRemind = maxValueRemind;
		this.minValue = minValue;
		this.minValueRemind = minValueRemind;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemResultNum() {
		return this.itemResultNum;
	}

	public void setItemResultNum(String itemResultNum) {
		this.itemResultNum = itemResultNum;
	}

	public String getItemResultName() {
		return this.itemResultName;
	}

	public void setItemResultName(String itemResultName) {
		this.itemResultName = itemResultName;
	}

	public String getResultUnit() {
		return this.resultUnit;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public String getResultType() {
		return this.resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIsNeedRemind() {
		return this.isNeedRemind;
	}

	public void setIsNeedRemind(String isNeedRemind) {
		this.isNeedRemind = isNeedRemind;
	}

	public String getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMaxValueRemind() {
		return this.maxValueRemind;
	}

	public void setMaxValueRemind(String maxValueRemind) {
		this.maxValueRemind = maxValueRemind;
	}

	public String getMinValue() {
		return this.minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMinValueRemind() {
		return this.minValueRemind;
	}

	public void setMinValueRemind(String minValueRemind) {
		this.minValueRemind = minValueRemind;
	}

	public String getResultRemarkFlag() {
		return resultRemarkFlag;
	}

	public void setResultRemarkFlag(String resultRemarkFlag) {
		this.resultRemarkFlag = resultRemarkFlag;
	}

}