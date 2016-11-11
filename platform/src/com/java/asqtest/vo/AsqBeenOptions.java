package com.java.asqtest.vo;

/**
 * AsqBeenOptions entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class AsqBeenOptions implements java.io.Serializable {

	// Fields

	private Long id;
	private AsqQuestions asqQuestions;
	private String optionContent;
	private String optionCode;
	private String isDelete;
	private String isNeedRemark;
	private String isRightOption;
	private Double optionScore;
	
	//-------------页面显示
	private String qtIDs;//问题的id串
	private String isOptionRecord;
	private AsqQuestions pageShow_asqQuestions;
	// Constructors

	/** default constructor */
	public AsqBeenOptions() {
	}
	public AsqBeenOptions(AsqQuestions asqQuestions) {
		this.asqQuestions = new AsqQuestions(asqQuestions.getId());
	}
	public AsqBeenOptions(String qtIDs) {
		this.qtIDs = qtIDs;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}
	public AsqQuestions getAsqQuestions() {
		return asqQuestions;
	}
	public void setAsqQuestions(AsqQuestions asqQuestions) {
		this.asqQuestions = asqQuestions;
	}
	public String getIsOptionRecord() {
		return isOptionRecord;
	}
	public void setIsOptionRecord(String isOptionRecord) {
		this.isOptionRecord = isOptionRecord;
	}
	public String getQtIDs() {
		return qtIDs;
	}
	public void setQtIDs(String qtIDs) {
		this.qtIDs = qtIDs;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AsqQuestions getPageShow_asqQuestions() {
		return pageShow_asqQuestions;
	}
	public void setPageShow_asqQuestions(AsqQuestions pageShow_asqQuestions) {
		this.pageShow_asqQuestions = pageShow_asqQuestions;
	}
	public String getOptionContent() {
		return this.optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public String getOptionCode() {
		return this.optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsNeedRemark() {
		return this.isNeedRemark;
	}

	public void setIsNeedRemark(String isNeedRemark) {
		this.isNeedRemark = isNeedRemark;
	}

	public String getIsRightOption() {
		return this.isRightOption;
	}

	public void setIsRightOption(String isRightOption) {
		this.isRightOption = isRightOption;
	}

	public Double getOptionScore() {
		return this.optionScore;
	}

	public void setOptionScore(Double optionScore) {
		this.optionScore = optionScore;
	}

}