package com.kybaby.domain;

/**
 * RecommendRule entity. @author MyEclipse Persistence Tools
 */

public class RecommendRule implements java.io.Serializable {

	// Fields

	private Long id;
	private Double amount;
	private Long points;
	private Long coupon;
	private String rewardTime;
	private String ruleName;
	private String ruleStatus;
	private String recommendDirection;

	// Constructors

	/** default constructor */
	public RecommendRule() {
	}

	/** full constructor */
	public RecommendRule(Double amount, Long points, Long coupon,
			String rewardTime, String ruleName, String ruleStatus,
			String recommendDirection) {
		this.amount = amount;
		this.points = points;
		this.coupon = coupon;
		this.rewardTime = rewardTime;
		this.ruleName = ruleName;
		this.ruleStatus = ruleStatus;
		this.recommendDirection = recommendDirection;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Long getCoupon() {
		return this.coupon;
	}

	public void setCoupon(Long coupon) {
		this.coupon = coupon;
	}

	public String getRewardTime() {
		return this.rewardTime;
	}

	public void setRewardTime(String rewardTime) {
		this.rewardTime = rewardTime;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleStatus() {
		return this.ruleStatus;
	}

	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public String getRecommendDirection() {
		return this.recommendDirection;
	}

	public void setRecommendDirection(String recommendDirection) {
		this.recommendDirection = recommendDirection;
	}

}