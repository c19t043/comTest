package com.java.b2cGoods.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import com.java.publichealth.residentsfile.vo.KyUserInfo;


/**
 * 商品订单主信息
 * B2cGoodsOrder entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsOrder implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String orderNum;
	private KyUserInfo userInfo;
	private String submitTime;
	private Double totalPrice;
	private Double realPrice;
	private String updateTime;
	private String orderStatus;
	private String payMethod;
	private Double useRemainBalance;
	private String bconsignee;
	private String baddress;
	private String bphone;
	private Double postage;
	private String orderType;
	private String comments;
	private String isDel;
	/**
	 * 订单明细列表
	 */
	private Set<B2cGoodsOrderDetail> b2cGoodsOrderDetailSet = new LinkedHashSet<>();
	/**
	 * 订单对应预售信息
	 */
	private Set<B2cGoodsOrderPromotion> b2cGoodsOrderPromotionSet = new LinkedHashSet<>();
	/**
	 * 订单送货方式
	 */
	private B2cGoodsDeliver b2cGoodsDeliver;

	// Constructors

	/** default constructor */
	public B2cGoodsOrder() {
	}

	/** full constructor */
	public B2cGoodsOrder(String orderNum, Long userId, String submitTime,
			Double totalPrice, Double realPrice, String updateTime,
			String orderStatus, String payMethod, Double useRemainBalance,
			String bconsignee, String baddress, String bphone, Long bdid,
			Double postage, String orderType, String comments) {
		this.orderNum = orderNum;
		this.submitTime = submitTime;
		this.totalPrice = totalPrice;
		this.realPrice = realPrice;
		this.updateTime = updateTime;
		this.orderStatus = orderStatus;
		this.payMethod = payMethod;
		this.useRemainBalance = useRemainBalance;
		this.bconsignee = bconsignee;
		this.baddress = baddress;
		this.bphone = bphone;
		this.postage = postage;
		this.orderType = orderType;
		this.comments = comments;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public KyUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(KyUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getRealPrice() {
		return this.realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Double getUseRemainBalance() {
		return this.useRemainBalance;
	}

	public void setUseRemainBalance(Double useRemainBalance) {
		this.useRemainBalance = useRemainBalance;
	}

	public String getBconsignee() {
		return this.bconsignee;
	}

	public void setBconsignee(String bconsignee) {
		this.bconsignee = bconsignee;
	}

	public String getBaddress() {
		return this.baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}

	public String getBphone() {
		return this.bphone;
	}

	public void setBphone(String bphone) {
		this.bphone = bphone;
	}

	public Double getPostage() {
		return this.postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Set<B2cGoodsOrderDetail> getB2cGoodsOrderDetailSet() {
		return b2cGoodsOrderDetailSet;
	}

	public void setB2cGoodsOrderDetailSet(
			Set<B2cGoodsOrderDetail> b2cGoodsOrderDetailSet) {
		this.b2cGoodsOrderDetailSet = b2cGoodsOrderDetailSet;
	}

	public Set<B2cGoodsOrderPromotion> getB2cGoodsOrderPromotionSet() {
		return b2cGoodsOrderPromotionSet;
	}

	public void setB2cGoodsOrderPromotionSet(
			Set<B2cGoodsOrderPromotion> b2cGoodsOrderPromotionSet) {
		this.b2cGoodsOrderPromotionSet = b2cGoodsOrderPromotionSet;
	}

	public B2cGoodsDeliver getB2cGoodsDeliver() {
		return b2cGoodsDeliver;
	}

	public void setB2cGoodsDeliver(B2cGoodsDeliver b2cGoodsDeliver) {
		this.b2cGoodsDeliver = b2cGoodsDeliver;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}


}