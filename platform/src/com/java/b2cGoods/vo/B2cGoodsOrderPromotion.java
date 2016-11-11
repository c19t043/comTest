package com.java.b2cGoods.vo;

/**
 * 商品订单对应促销信息表
 * B2cGoodsOrderPromotion entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsOrderPromotion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long orderId;
	private Long activityId;
	private Double discountMoney;

	// Constructors

	/** default constructor */
	public B2cGoodsOrderPromotion() {
	}

	/** full constructor */
	public B2cGoodsOrderPromotion(Long orderId, Long activityId,
			Double discountMoney) {
		this.orderId = orderId;
		this.activityId = activityId;
		this.discountMoney = discountMoney;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Double getDiscountMoney() {
		return this.discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

}