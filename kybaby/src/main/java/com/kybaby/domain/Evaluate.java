package com.kybaby.domain;

/**
 * Evaluate entity. @author MyEclipse Persistence Tools
 */

public class Evaluate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
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
	private String  submitTime;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getServiceStarLevel() {
		return serviceStarLevel;
	}

	public void setServiceStarLevel(Long serviceStarLevel) {
		this.serviceStarLevel = serviceStarLevel;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(String evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDutyStarLevel() {
		return dutyStarLevel;
	}

	public void setDutyStarLevel(Long dutyStarLevel) {
		this.dutyStarLevel = dutyStarLevel;
	}

	public Long getOnTimeStarLevel() {
		return onTimeStarLevel;
	}

	public void setOnTimeStarLevel(Long onTimeStarLevel) {
		this.onTimeStarLevel = onTimeStarLevel;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}


}