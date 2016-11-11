package com.java.b2cGoods.vo;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 属性信息
 * B2cGoodsProperty entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsProperty implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long typeId;
	private String propName;
	private String isAlias;
	private String isColor;
	private String isEnum;
	private String isInput;
	private String isMain;
	private String isSale;
	private String isQuery;
	private String isMust;
	private String isCheckbox;
	private String status;
	private String sort;
	private String createTime;
	private String remark;
	/**
	 * 属性值列表
	 */
	private Set<B2cGoodsPropertyValue> b2cGoodsPropertyValueSet = new LinkedHashSet<B2cGoodsPropertyValue>();

	// Constructors

	/** default constructor */
	public B2cGoodsProperty() {
	}

	/** full constructor */
	public B2cGoodsProperty(Long typeId, String propName, String isAlias,
			String isColor, String isEnum, String isInput, String isMain,
			String isSale, String isQuery, String isMust, String isCheckbox,
			String status, String sort, String createTime, String remark) {
		this.typeId = typeId;
		this.propName = propName;
		this.isAlias = isAlias;
		this.isColor = isColor;
		this.isEnum = isEnum;
		this.isInput = isInput;
		this.isMain = isMain;
		this.isSale = isSale;
		this.isQuery = isQuery;
		this.isMust = isMust;
		this.isCheckbox = isCheckbox;
		this.status = status;
		this.sort = sort;
		this.createTime = createTime;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getIsAlias() {
		return this.isAlias;
	}

	public void setIsAlias(String isAlias) {
		this.isAlias = isAlias;
	}

	public String getIsColor() {
		return this.isColor;
	}

	public void setIsColor(String isColor) {
		this.isColor = isColor;
	}

	public String getIsEnum() {
		return this.isEnum;
	}

	public void setIsEnum(String isEnum) {
		this.isEnum = isEnum;
	}

	public String getIsInput() {
		return this.isInput;
	}

	public void setIsInput(String isInput) {
		this.isInput = isInput;
	}

	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getIsSale() {
		return this.isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getIsQuery() {
		return this.isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getIsMust() {
		return this.isMust;
	}

	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}

	public String getIsCheckbox() {
		return this.isCheckbox;
	}

	public void setIsCheckbox(String isCheckbox) {
		this.isCheckbox = isCheckbox;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<B2cGoodsPropertyValue> getB2cGoodsPropertyValueSet() {
		return b2cGoodsPropertyValueSet;
	}

	public void setB2cGoodsPropertyValueSet(
			Set<B2cGoodsPropertyValue> b2cGoodsPropertyValueSet) {
		this.b2cGoodsPropertyValueSet = b2cGoodsPropertyValueSet;
	}

}