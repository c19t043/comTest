package com.java.b2cGoods.vo;

/**
 * 商品销售属性
 * B2cGoodsSku entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsSku implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private B2cGoods b2cGoods;
	private String properties;
	private Double price;
	private String num;
	private String skuName;
	private String status;
	private String createTime;
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public B2cGoods getB2cGoods() {
		return b2cGoods;
	}
	public void setB2cGoods(B2cGoods b2cGoods) {
		this.b2cGoods = b2cGoods;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}