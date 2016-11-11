package com.kybaby.newbussiness.ordermanager.domain;

import java.util.Date;

/**
 * HealthPlanRemindIssued entity. @author MyEclipse Persistence Tools
 */

public class HealthPlanRemindIssued implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String orderNum;
	private Date doTime;
	private Date createTime;
	private Long doNumber;
	private Long healthPlanId;
	/**
	 * 健康路径id
	 */
	private Long healthPathId;
	// Constructors

	/** default constructor */
	public HealthPlanRemindIssued() {
	}

	/** full constructor */
	public HealthPlanRemindIssued(Long userId, String orderNum,
			Date doTime, Date createTime, Long doNumber,
			Long healthPlanId) {
		this.userId = userId;
		this.orderNum = orderNum;
		this.doTime = doTime;
		this.createTime = createTime;
		this.doNumber = doNumber;
		this.healthPlanId = healthPlanId;
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

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getDoTime() {
		return doTime;
	}

	public void setDoTime(Date doTime) {
		this.doTime = doTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getDoNumber() {
		return this.doNumber;
	}

	public void setDoNumber(Long doNumber) {
		this.doNumber = doNumber;
	}

	public Long getHealthPlanId() {
		return this.healthPlanId;
	}

	public void setHealthPlanId(Long healthPlanId) {
		this.healthPlanId = healthPlanId;
	}

	public Long getHealthPathId() {
		return healthPathId;
	}

	public void setHealthPathId(Long healthPathId) {
		this.healthPathId = healthPathId;
	}

}