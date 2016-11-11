package com.kybaby.newbussiness.b2cgoods.domain;

/**
 * 属性值信息
 * B2cGoodsPropertyValue entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsPropertyValue implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private B2cGoodsProperty b2cGoodsProperty;
	private String valName;
	private String status;
	private String sort;
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public B2cGoodsProperty getB2cGoodsProperty() {
		return b2cGoodsProperty;
	}
	public void setB2cGoodsProperty(B2cGoodsProperty b2cGoodsProperty) {
		this.b2cGoodsProperty = b2cGoodsProperty;
	}
	public String getValName() {
		return valName;
	}
	public void setValName(String valName) {
		this.valName = valName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}