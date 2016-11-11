package com.java.b2cGoods.action;

import java.util.List;

import com.java.b2cGoods.service.GoodsPropAndValService;
import com.java.b2cGoods.vo.B2cGoodsProperty;
import com.java.b2cGoods.vo.B2cGoodsPropertyValue;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;

public class GoodsPropAndValAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	private GoodsPropAndValService goodsPropAndValService;
	private B2cGoodsProperty b2cGoodsProperty;
	private B2cGoodsPropertyValue b2cGoodsPropertyValue;
	/**
	 * 属性列表
	 * @return
	 */
	public String getGoodsPropList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoodsProperty == null){
			b2cGoodsProperty = new B2cGoodsProperty();
		}
		List<B2cGoodsProperty> list = this.goodsPropAndValService.getB2cGoodsPropertyByPage(psm, b2cGoodsProperty);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 属性值列表
	 * @return
	 */
	public String getGoodsPropValList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoodsPropertyValue == null){
			b2cGoodsPropertyValue = new B2cGoodsPropertyValue();
		}
		List<B2cGoodsPropertyValue> list = this.goodsPropAndValService.getB2cGoodsPropertyValueByPage(psm, b2cGoodsPropertyValue);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpGoodsProp(){
		if(b2cGoodsProperty != null && b2cGoodsProperty.getId() != null){
			this.b2cGoodsProperty = this.goodsPropAndValService.getB2cGoodsPropertyById(b2cGoodsProperty.getId());
		}
		return SUCCESS;
	}
	/**
	 * 保存或更新
	 * @return
	 */
	public String saveOrUpdateb2cGoodsProp(){
		this.goodsPropAndValService.saveOrUpdateB2cGoodsProperty(b2cGoodsProperty);
		addActionMessage("操作成功");
		return redirectActionResult("goodsPropList");
	}
	public GoodsPropAndValService getGoodsPropAndValService() {
		return goodsPropAndValService;
	}
	public void setGoodsPropAndValService(
			GoodsPropAndValService goodsPropAndValService) {
		this.goodsPropAndValService = goodsPropAndValService;
	}
	public B2cGoodsProperty getB2cGoodsProperty() {
		return b2cGoodsProperty;
	}
	public void setB2cGoodsProperty(B2cGoodsProperty b2cGoodsProperty) {
		this.b2cGoodsProperty = b2cGoodsProperty;
	}
	public B2cGoodsPropertyValue getB2cGoodsPropertyValue() {
		return b2cGoodsPropertyValue;
	}
	public void setB2cGoodsPropertyValue(B2cGoodsPropertyValue b2cGoodsPropertyValue) {
		this.b2cGoodsPropertyValue = b2cGoodsPropertyValue;
	}

}
