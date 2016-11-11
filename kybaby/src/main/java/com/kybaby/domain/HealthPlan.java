package com.kybaby.domain;

/**
 * HealthPlan entity. @author MyEclipse Persistence Tools
 */

public class HealthPlan implements java.io.Serializable {

	// Fields

	private Long id;
	private String healthPlan;
	private String comments;
	private String healthPathIds;
	private String healthPlanStatus;

	// Constructors

	/** default constructor */
	public HealthPlan() {
	}

	/** full constructor */
	public HealthPlan(String healthPlan, String comments, String healthPathIds,
			String healthPlanStatus) {
		this.healthPlan = healthPlan;
		this.comments = comments;
		this.healthPathIds = healthPathIds;
		this.healthPlanStatus = healthPlanStatus;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHealthPlan() {
		return this.healthPlan;
	}

	public void setHealthPlan(String healthPlan) {
		this.healthPlan = healthPlan;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHealthPathIds() {
		return this.healthPathIds;
	}

	public void setHealthPathIds(String healthPathIds) {
		this.healthPathIds = healthPathIds;
	}

	public String getHealthPlanStatus() {
		return this.healthPlanStatus;
	}

	public void setHealthPlanStatus(String healthPlanStatus) {
		this.healthPlanStatus = healthPlanStatus;
	}

}