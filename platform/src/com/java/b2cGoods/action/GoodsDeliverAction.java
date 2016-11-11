package com.java.b2cGoods.action;

import java.util.List;
import com.java.b2cGoods.service.GoodsDeliverService;
import com.java.b2cGoods.vo.B2cGoodsDeliver;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;

public class GoodsDeliverAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	private GoodsDeliverService goodsDeliverService;
	private B2cGoodsDeliver b2cGoodsDeliver;
	/**
	 * 送货方式列表方法
	 * @return
	 */
	public String getDeliverInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoodsDeliver == null){
			b2cGoodsDeliver = new B2cGoodsDeliver();
		}
		List<B2cGoodsDeliver> list = this.goodsDeliverService.getB2cGoodsDeliverByPage(psm, b2cGoodsDeliver);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpDeliver(){
		if(b2cGoodsDeliver != null && b2cGoodsDeliver.getId() != null){
			this.b2cGoodsDeliver = this.goodsDeliverService.getB2cGoodsDeliverById(b2cGoodsDeliver.getId());
		}
		return SUCCESS;
	}
	/**
	 * 保存或更新
	 * @return
	 */
	public String saveOrUpdateB2cGoodsDeliver(){
		this.goodsDeliverService.saveOrUpdateB2cGoodsDeliver(b2cGoodsDeliver);
		addActionMessage("操作成功");
		return redirectActionResult("goodsDeliverInfoList");
	}
	public GoodsDeliverService getGoodsDeliverService() {
		return goodsDeliverService;
	}
	public void setGoodsDeliverService(GoodsDeliverService goodsDeliverService) {
		this.goodsDeliverService = goodsDeliverService;
	}
	public B2cGoodsDeliver getB2cGoodsDeliver() {
		return b2cGoodsDeliver;
	}
	public void setB2cGoodsDeliver(B2cGoodsDeliver b2cGoodsDeliver) {
		this.b2cGoodsDeliver = b2cGoodsDeliver;
	}

}
