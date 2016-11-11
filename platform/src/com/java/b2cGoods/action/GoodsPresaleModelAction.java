package com.java.b2cGoods.action;

import java.util.List;

import com.java.b2cGoods.service.GoodsPresaleModelService;
import com.java.b2cGoods.vo.B2cGoodsPresaleModel;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;

public class GoodsPresaleModelAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	private GoodsPresaleModelService goodsPresaleModelService;
	private B2cGoodsPresaleModel b2cGoodsPresaleModel;
	
	/**
	 * 送货方式列表方法
	 * @return
	 */
	public String getPresaleModelList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoodsPresaleModel == null){
			b2cGoodsPresaleModel = new B2cGoodsPresaleModel();
		}
		List<B2cGoodsPresaleModel> list = this.goodsPresaleModelService.getb2cGoodsPresaleModelByPage(psm, b2cGoodsPresaleModel);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpPresaleModel(){
		if(b2cGoodsPresaleModel != null && b2cGoodsPresaleModel.getId() != null){
			this.b2cGoodsPresaleModel = this.goodsPresaleModelService.getb2cGoodsPresaleModelById(b2cGoodsPresaleModel.getId());
		}
		return SUCCESS;
	}
	/**
	 * 保存或更新
	 * @return
	 */
	public String saveOrUpdatePresaleModel(){
		this.goodsPresaleModelService.saveOrUpdateb2cGoodsPresaleModel(b2cGoodsPresaleModel);
		addActionMessage("操作成功");
		return redirectActionResult("goodsPresaleModelList");
	}
	public GoodsPresaleModelService getGoodsPresaleModelService() {
		return goodsPresaleModelService;
	}
	public void setGoodsPresaleModelService(
			GoodsPresaleModelService goodsPresaleModelService) {
		this.goodsPresaleModelService = goodsPresaleModelService;
	}
	public B2cGoodsPresaleModel getB2cGoodsPresaleModel() {
		return b2cGoodsPresaleModel;
	}
	public void setB2cGoodsPresaleModel(B2cGoodsPresaleModel b2cGoodsPresaleModel) {
		this.b2cGoodsPresaleModel = b2cGoodsPresaleModel;
	}

}
