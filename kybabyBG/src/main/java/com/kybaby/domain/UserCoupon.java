package com.kybaby.domain;

/**
 * UserCoupon entity. @author MyEclipse Persistence Tools
 */

public class UserCoupon implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long couponId;
	private String couponStatus;
	private String comments;
	private Long activityId;

	// Constructors

	/** default constructor */
	public UserCoupon() {
	}

	/** full constructor */
	public UserCoupon(Long userId, Long couponId, String couponStatus,
			String comments, Long activityId) {
		this.userId = userId;
		this.couponId = couponId;
		this.couponStatus = couponStatus;
		this.comments = comments;
		this.activityId = activityId;
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

	public Long getCouponId() {
		return this.couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponStatus() {
		return this.couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

}