package com.java.b2cGoods.action;

import java.util.List;

import com.java.b2cGoods.service.GoodsTypeService;
import com.java.b2cGoods.vo.B2cGoodsType;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;

public class GoodsTypeAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	private GoodsTypeService goodsTypeService;
	private B2cGoodsType b2cGoodsType;
	/**
	 * 列表
	 * @return
	 */
	public String getGoodsTypeList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoodsType == null){
			b2cGoodsType = new B2cGoodsType();
		}
		List<B2cGoodsType> list = this.goodsTypeService.getB2cGoodsTypeByPage(psm, b2cGoodsType);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpGoodsType(){
		if(b2cGoodsType != null && b2cGoodsType.getId() != null){
			this.b2cGoodsType = this.goodsTypeService.getB2cGoodsTypeById(b2cGoodsType.getId());
		}
		return SUCCESS;
	}
	/**
	 * 保存或更新
	 * @return
	 */
	public String saveOrUpdateb2cGoodsType(){
		this.goodsTypeService.saveOrUpdateB2cGoodsType(b2cGoodsType);
		addActionMessage("操作成功");
		return redirectActionResult("goodsTypeList");
	}
	public GoodsTypeService getGoodsTypeService() {
		return goodsTypeService;
	}
	public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
		this.goodsTypeService = goodsTypeService;
	}
	public B2cGoodsType getB2cGoodsType() {
		return b2cGoodsType;
	}
	public void setB2cGoodsType(B2cGoodsType b2cGoodsType) {
		this.b2cGoodsType = b2cGoodsType;
	}

}
