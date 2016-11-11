package com.kybaby.domain;

/**
 * Coupon entity. @author MyEclipse Persistence Tools
 */

public class Coupon implements java.io.Serializable {

	// Fields

	private Long id;
	private String couponName;
	private String startTime;
	private String endTime;
	private Double couponAmount;

	// Constructors

	/** default constructor */
	public Coupon() {
	}

	/** full constructor */
	public Coupon(String couponName, String startTime, String endTime,
			Double couponAmount) {
		this.couponName = couponName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.couponAmount = couponAmount;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCouponName() {
		return this.couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getCouponAmount() {
		return this.couponAmount;
	}

	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

}