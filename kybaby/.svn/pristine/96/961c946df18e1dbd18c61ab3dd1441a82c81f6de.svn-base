package com.kybaby.newbussiness.asqtest.domain;

import java.sql.Timestamp;

/**
 * AsqQuestionRecord entity. @author MyEclipse Persistence Tools
 */

public class AsqQuestionRecord implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private AsqTestUserInfo asqTestUserInfo;
	private Long asqQuestionsId;
	private AsqBeenOptions asqBeenOptions;
	private String answerRemark;
	private Double score;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Integer isdelete;
	private Integer isRight;

	// Constructors

	/** default constructor */
	public AsqQuestionRecord() {
	}

	/** full constructor */
	public AsqQuestionRecord(Long asqTestUserId, Integer asqQuestionsId,
			String answerOption, String answerRemark, Double score,
			Timestamp createTime, Timestamp modifyTime, Integer isdelete,
			Integer isRight) {
		this.answerRemark = answerRemark;
		this.score = score;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.isdelete = isdelete;
		this.isRight = isRight;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AsqTestUserInfo getAsqTestUserInfo() {
		return asqTestUserInfo;
	}

	public void setAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		this.asqTestUserInfo = asqTestUserInfo;
	}

	public Long getAsqQuestionsId() {
		return this.asqQuestionsId;
	}

	public void setAsqQuestionsId(Long asqQuestionsId) {
		this.asqQuestionsId = asqQuestionsId;
	}

	public AsqBeenOptions getAsqBeenOptions() {
		return asqBeenOptions;
	}

	public void setAsqBeenOptions(AsqBeenOptions asqBeenOptions) {
		this.asqBeenOptions = asqBeenOptions;
	}

	public String getAnswerRemark() {
		return this.answerRemark;
	}

	public void setAnswerRemark(String answerRemark) {
		this.answerRemark = answerRemark;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getIsRight() {
		return this.isRight;
	}

	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}

}