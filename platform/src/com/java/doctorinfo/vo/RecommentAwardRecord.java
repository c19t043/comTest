package com.java.doctorinfo.vo;

/**
 * RecommentAwardRecord entity. @author MyEclipse Persistence Tools
 */

public class RecommentAwardRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private String recommendType;
	private Long recommendUserId;
	private Long beenRecommendUserId;
	private Long recommendDoctorId;
	private Long beenRecommendDoctorId;
	private String awardTime;
	private Long pointsAmount;
	private Double amount;
	private Long couponId;
	private String comments;
	private String isGrant;
	private String whenToGrant;

	// Constructors

	/** default constructor */
	public RecommentAwardRecord() {
	}

	/** full constructor */
	public RecommentAwardRecord(String recommendType, Long recommendUserId,
			Long beenRecommendUserId, Long recommendDoctorId,
			Long beenRecommendDoctorId, String awardTime, Long pointsAmount,
			Double amount, Long couponId, String comments, String isGrant,
			String whenToGrant) {
		this.recommendType = recommendType;
		this.recommendUserId = recommendUserId;
		this.beenRecommendUserId = beenRecommendUserId;
		this.recommendDoctorId = recommendDoctorId;
		this.beenRecommendDoctorId = beenRecommendDoctorId;
		this.awardTime = awardTime;
		this.pointsAmount = pointsAmount;
		this.amount = amount;
		this.couponId = couponId;
		this.comments = comments;
		this.isGrant = isGrant;
		this.whenToGrant = whenToGrant;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecommendType() {
		return this.recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	public Long getRecommendUserId() {
		return this.recommendUserId;
	}

	public void setRecommendUserId(Long recommendUserId) {
		this.recommendUserId = recommendUserId;
	}

	public Long getBeenRecommendUserId() {
		return this.beenRecommendUserId;
	}

	public void setBeenRecommendUserId(Long beenRecommendUserId) {
		this.beenRecommendUserId = beenRecommendUserId;
	}

	public Long getRecommendDoctorId() {
		return this.recommendDoctorId;
	}

	public void setRecommendDoctorId(Long recommendDoctorId) {
		this.recommendDoctorId = recommendDoctorId;
	}

	public Long getBeenRecommendDoctorId() {
		return this.beenRecommendDoctorId;
	}

	public void setBeenRecommendDoctorId(Long beenRecommendDoctorId) {
		this.beenRecommendDoctorId = beenRecommendDoctorId;
	}

	public String getAwardTime() {
		return this.awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	public Long getPointsAmount() {
		return this.pointsAmount;
	}

	public void setPointsAmount(Long pointsAmount) {
		this.pointsAmount = pointsAmount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getCouponId() {
		return this.couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIsGrant() {
		return this.isGrant;
	}

	public void setIsGrant(String isGrant) {
		this.isGrant = isGrant;
	}

	public String getWhenToGrant() {
		return this.whenToGrant;
	}

	public void setWhenToGrant(String whenToGrant) {
		this.whenToGrant = whenToGrant;
	}

}