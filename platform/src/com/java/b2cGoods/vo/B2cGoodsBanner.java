package com.java.b2cGoods.vo;

/**
 * 商品banner图信息
 * B2cGoodsBanner entity. @author MyEclipse Persistence Tools
 */

public class B2cGoodsBanner implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private B2cGoods b2cGoods;
	private String imgPath;
	private String imgDiscrete;
	private String isEnable;
	//页面使用
	private String imgBase64;
		
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgDiscrete() {
		return imgDiscrete;
	}
	public void setImgDiscrete(String imgDiscrete) {
		this.imgDiscrete = imgDiscrete;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
}