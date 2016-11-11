package com.java.tohomebusiness.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.tohomebusiness.service.OrderInfoService;
import com.java.tohomebusiness.vo.OrderInfo;

public class OrderInfoAction extends Action{

	private static final long serialVersionUID = 1L;
	private OrderInfoService orderInfoService;
	private OrderInfo orderInfo;
	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	/**
	 * 上门服务订单信息列表
	 * @return
	 */
	public String getOrderInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(orderInfo == null){
			orderInfo = new OrderInfo();
		}
		List<OrderInfo> list = orderInfoService.getOrderInfoListByPage(psm, orderInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
