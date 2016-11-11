package com.kybaby.newbussiness.doctorclinic.domain;

/**
 * EvaluateClinic entity. @author MyEclipse Persistence Tools
 */

public class EvaluateClinic implements java.io.Serializable {

	// Fields

	private Long id;
	private String evaluateLevel;
	private String evaluateContent;
	private OrderInfoClinic orderInfoClinic;
	private String evaluateStatus;
	private String remark;

	// Constructors

	/** default constructor */
	public EvaluateClinic() {
	}

	/** full constructor */
	public EvaluateClinic(String evaluateLevel, String evaluateContent,
			Long orderClinicId, String evaluateStatus, String remark) {
		this.evaluateLevel = evaluateLevel;
		this.evaluateContent = evaluateContent;
		this.evaluateStatus = evaluateStatus;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvaluateLevel() {
		return this.evaluateLevel;
	}

	public void setEvaluateLevel(String evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}

	public String getEvaluateContent() {
		return this.evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}

	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}

	public String getEvaluateStatus() {
		return this.evaluateStatus;
	}

	public void setEvaluateStatus(String evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}