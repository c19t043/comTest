package com.kybaby.newbussiness.b2cgoods.domain;

/**
 * 商品送货方式信息
 * B2cGoodsDeliver entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsDeliver implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String dname;
	private Double dmoney;
	private String remark;
	private String isMain;
	private String isEnable;

	// Constructors

	/** default constructor */
	public B2cGoodsDeliver() {
	}

	/** full constructor */
	public B2cGoodsDeliver(String dname, Double dmoney, String remark) {
		this.dname = dname;
		this.dmoney = dmoney;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Double getDmoney() {
		return this.dmoney;
	}

	public void setDmoney(Double dmoney) {
		this.dmoney = dmoney;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

}