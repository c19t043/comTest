package com.java.b2cGoods.action;

import java.util.ArrayList;
import java.util.List;

import com.java.b2cGoods.service.GoodsOrderService;
import com.java.b2cGoods.vo.B2cGoods;
import com.java.b2cGoods.vo.B2cGoodsOrder;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.util.DateManage;

public class GoodsOrderAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	/**
	 * 商品订单业务处理类
	 */
	private GoodsOrderService goodsOrderService;
	/**
	 * 商品信息
	 */
	private B2cGoods b2cGoods;
	/**
	 * 订单信息
	 */
	private B2cGoodsOrder b2cGoodsOrder;
	/**
	 * 商品订单列表
	 */
	private List<B2cGoodsOrder> b2cGoodsOrderList = new ArrayList<>();
	
	
	/**
	 * 商品信息列表方法
	 * @return
	 */
	public String getGoodsOrderList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoodsOrder == null){
			b2cGoodsOrder = new B2cGoodsOrder();
		}
		List<B2cGoodsOrder> list = this.goodsOrderService.getB2cGoodsOrderListByPage(psm, b2cGoodsOrder);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpGoodsOrder(){
		if(b2cGoodsOrder != null && b2cGoodsOrder.getId() != null){
			this.b2cGoodsOrder = this.goodsOrderService.getB2cGoodsOrderById(b2cGoodsOrder);
		}
		return SUCCESS;
	}
	/**
	 * 保存信息
	 * @return
	 */
	public String saveOrUpdateB2cGoodsOrder(){
		if(b2cGoodsOrder.getId() != null){
			B2cGoodsOrder b2cGoodsOrderOld = this.goodsOrderService.getB2cGoodsOrderById(b2cGoodsOrder);
			b2cGoodsOrderOld.setOrderStatus(b2cGoodsOrder.getOrderStatus());
			b2cGoodsOrderOld.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			this.goodsOrderService.saveOrUpdateB2cGoodsOrder(b2cGoodsOrderOld);
		}
		addActionMessage("操作成功");
		return redirectActionResult("goodsOrderInfoList");
	}
	public GoodsOrderService getGoodsOrderService() {
		return goodsOrderService;
	}
	public void setGoodsOrderService(GoodsOrderService goodsOrderService) {
		this.goodsOrderService = goodsOrderService;
	}
	public B2cGoods getB2cGoods() {
		return b2cGoods;
	}
	public void setB2cGoods(B2cGoods b2cGoods) {
		this.b2cGoods = b2cGoods;
	}
	public B2cGoodsOrder getB2cGoodsOrder() {
		return b2cGoodsOrder;
	}
	public void setB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		this.b2cGoodsOrder = b2cGoodsOrder;
	}
	public List<B2cGoodsOrder> getB2cGoodsOrderList() {
		return b2cGoodsOrderList;
	}
	public void setB2cGoodsOrderList(List<B2cGoodsOrder> b2cGoodsOrderList) {
		this.b2cGoodsOrderList = b2cGoodsOrderList;
	}
	
	
}
