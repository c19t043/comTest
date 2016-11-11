package com.kybaby.domain;

/**
 * Evaluate entity. @author MyEclipse Persistence Tools
 */

public class Evaluate implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderNum;
	private Long serviceStarLevel;
	private String tagIds;
	private String content;
	private String evaluateStatus;
	private String comments;
	private Long doctorId;
	private Long userId;
	private Long dutyStarLevel;
	private Long onTimeStarLevel;

	// Constructors

	/** default constructor */
	public Evaluate() {
	}

	/** full constructor */
	public Evaluate(String orderNum, Long serviceStarLevel, String tagIds,
			String content, String evaluateStatus, String comments,
			Long doctorId, Long userId, Long dutyStarLevel, Long onTimeStarLevel) {
		this.orderNum = orderNum;
		this.serviceStarLevel = serviceStarLevel;
		this.tagIds = tagIds;
		this.content = content;
		this.evaluateStatus = evaluateStatus;
		this.comments = comments;
		this.doctorId = doctorId;
		this.userId = userId;
		this.dutyStarLevel = dutyStarLevel;
		this.onTimeStarLevel = onTimeStarLevel;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getServiceStarLevel() {
		return this.serviceStarLevel;
	}

	public void setServiceStarLevel(Long serviceStarLevel) {
		this.serviceStarLevel = serviceStarLevel;
	}

	public String getTagIds() {
		return this.tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvaluateStatus() {
		return this.evaluateStatus;
	}

	public void setEvaluateStatus(String evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDutyStarLevel() {
		return this.dutyStarLevel;
	}

	public void setDutyStarLevel(Long dutyStarLevel) {
		this.dutyStarLevel = dutyStarLevel;
	}

	public Long getOnTimeStarLevel() {
		return this.onTimeStarLevel;
	}

	public void setOnTimeStarLevel(Long onTimeStarLevel) {
		this.onTimeStarLevel = onTimeStarLevel;
	}

}