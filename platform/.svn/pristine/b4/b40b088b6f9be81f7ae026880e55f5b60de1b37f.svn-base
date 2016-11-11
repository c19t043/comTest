package com.java.b2cGoods.vo;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 商品分类信息
 * B2cGoodsType entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String typeName;
	private Long pid;
	private Long sort;
	private String isEnable;
	/**
	 * 商品列表
	 */
	private Set<B2cGoods> b2cGoodsSet = new LinkedHashSet<B2cGoods>(); 

	// Constructors

	/** default constructor */
	public B2cGoodsType() {
	}

	/** full constructor */
	public B2cGoodsType(String typeName, Long pid, Long sort) {
		this.typeName = typeName;
		this.pid = pid;
		this.sort = sort;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Set<B2cGoods> getB2cGoodsSet() {
		return b2cGoodsSet;
	}

	public void setB2cGoodsSet(Set<B2cGoods> b2cGoodsSet) {
		this.b2cGoodsSet = b2cGoodsSet;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

}