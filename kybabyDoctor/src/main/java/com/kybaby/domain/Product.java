package com.kybaby.domain;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Long id;
	private String productNum;
	private String name;
	private String itemIds;
	private Long positionId;
	private Long serviceTime;
	private Long afterServiceTime;
	private Double totalPrice;
	private String whatFitForSex;
	private String whatFitForMonth;
	private String productProperty;
	private String introduction;
	private String smallPicture;
	private String bigPictureOne;
	private String isFeatures;
	private String productStatus;
	private String comments;
	private String productCategory;
	private Long productUseTime;
	private String bigPictureTwo;
	private String bigPictureThree;
	/**
	 * 流程id
	 */
	private Long flowBasicId;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(String productNum, String name, String itemIds,
			Long positionId, Long serviceTime, Long afterServiceTime,
			Double totalPrice, String whatFitForSex, String whatFitForMonth,
			String productProperty, String introduction, String smallPicture,
			String bigPictureOne, String isFeatures, String productStatus,
			String comments, String productCategory, Long productUseTime,
			String bigPictureTwo, String bigPictureThree) {
		this.productNum = productNum;
		this.name = name;
		this.itemIds = itemIds;
		this.positionId = positionId;
		this.serviceTime = serviceTime;
		this.afterServiceTime = afterServiceTime;
		this.totalPrice = totalPrice;
		this.whatFitForSex = whatFitForSex;
		this.whatFitForMonth = whatFitForMonth;
		this.productProperty = productProperty;
		this.introduction = introduction;
		this.smallPicture = smallPicture;
		this.bigPictureOne = bigPictureOne;
		this.isFeatures = isFeatures;
		this.productStatus = productStatus;
		this.comments = comments;
		this.productCategory = productCategory;
		this.productUseTime = productUseTime;
		this.bigPictureTwo = bigPictureTwo;
		this.bigPictureThree = bigPictureThree;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductNum() {
		return this.productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemIds() {
		return this.itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public Long getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(Long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Long getAfterServiceTime() {
		return this.afterServiceTime;
	}

	public void setAfterServiceTime(Long afterServiceTime) {
		this.afterServiceTime = afterServiceTime;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getWhatFitForSex() {
		return this.whatFitForSex;
	}

	public void setWhatFitForSex(String whatFitForSex) {
		this.whatFitForSex = whatFitForSex;
	}

	public String getWhatFitForMonth() {
		return this.whatFitForMonth;
	}

	public void setWhatFitForMonth(String whatFitForMonth) {
		this.whatFitForMonth = whatFitForMonth;
	}

	public String getProductProperty() {
		return this.productProperty;
	}

	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSmallPicture() {
		return this.smallPicture;
	}

	public void setSmallPicture(String smallPicture) {
		this.smallPicture = smallPicture;
	}

	public String getBigPictureOne() {
		return this.bigPictureOne;
	}

	public void setBigPictureOne(String bigPictureOne) {
		this.bigPictureOne = bigPictureOne;
	}

	public String getIsFeatures() {
		return this.isFeatures;
	}

	public void setIsFeatures(String isFeatures) {
		this.isFeatures = isFeatures;
	}

	public String getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Long getProductUseTime() {
		return this.productUseTime;
	}

	public void setProductUseTime(Long productUseTime) {
		this.productUseTime = productUseTime;
	}

	public String getBigPictureTwo() {
		return this.bigPictureTwo;
	}

	public void setBigPictureTwo(String bigPictureTwo) {
		this.bigPictureTwo = bigPictureTwo;
	}

	public String getBigPictureThree() {
		return this.bigPictureThree;
	}

	public void setBigPictureThree(String bigPictureThree) {
		this.bigPictureThree = bigPictureThree;
	}

	public Long getFlowBasicId() {
		return flowBasicId;
	}

	public void setFlowBasicId(Long flowBasicId) {
		this.flowBasicId = flowBasicId;
	}

}