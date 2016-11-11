package com.java.medicalorgandbusiness.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.service.PeadiatricsService;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.platform.core.Action;

public class PeadiatricsAction extends Action  {

	private static final long serialVersionUID = 3825738564642054233L;
	private PeadiatricsService peadiatricsService;
	
	private OrderInfoClinic orderInfoClinic;
	
	private String ids;
	
	public String toList(){
		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(), "list");
		
		List<OrderInfoClinic> list = peadiatricsService.getOrderInfoClinicList(model,orderInfoClinic);
		
		putToRequest("list", list);
		
		return SUCCESS;
	}
	
	
	public String updateOrderStatus(){
		OrderInfoClinic info_query = peadiatricsService.get(orderInfoClinic.getId(), OrderInfoClinic.class);
		info_query.setOrderStatus("已完成");		
		peadiatricsService.edit(info_query);;
		return redirectActionResult("toList");
	}
	
	
	public String doBatchEdit(){
		
		String[] idArr = ids.split(",");
		
		for(int i=0;i<idArr.length;i++){
			OrderInfoClinic info_query = peadiatricsService.get(Long.parseLong(idArr[i]), OrderInfoClinic.class);
			info_query.setOrderStatus("已完成");
			peadiatricsService.edit(info_query);
		}
		
		return redirectActionResult("toList");
	}
	
	

	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public PeadiatricsService getPeadiatricsService() {
		return peadiatricsService;
	}


	public void setPeadiatricsService(PeadiatricsService peadiatricsService) {
		this.peadiatricsService = peadiatricsService;
	}


	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}


	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
}
