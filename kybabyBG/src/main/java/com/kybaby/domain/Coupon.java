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
	private String couponType;
	private Double minConsumption;
	private Double maxConsumption;
	private Double maxAmount;
	private Double discountRate;
	private Long couponProduct;
	private Long couponProductNum;
	private Long couponPackage;
	private Long couponPackageNum;
	private String getWay;
	private String remark;


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

	public Double getMinConsumption() {
		return minConsumption;
	}

	public void setMinConsumption(Double minConsumption) {
		this.minConsumption = minConsumption;
	}

	public Double getMaxConsumption() {
		return maxConsumption;
	}

	public void setMaxConsumption(Double maxConsumption) {
		this.maxConsumption = maxConsumption;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public Long getCouponProduct() {
		return couponProduct;
	}

	public void setCouponProduct(Long couponProduct) {
		this.couponProduct = couponProduct;
	}

	public Long getCouponProductNum() {
		return couponProductNum;
	}

	public void setCouponProductNum(Long couponProductNum) {
		this.couponProductNum = couponProductNum;
	}

	public Long getCouponPackage() {
		return couponPackage;
	}

	public void setCouponPackage(Long couponPackage) {
		this.couponPackage = couponPackage;
	}

	public Long getCouponPackageNum() {
		return couponPackageNum;
	}

	public void setCouponPackageNum(Long couponPackageNum) {
		this.couponPackageNum = couponPackageNum;
	}

	public String getGetWay() {
		return getWay;
	}

	public void setGetWay(String getWay) {
		this.getWay = getWay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

}