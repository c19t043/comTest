package com.java.medicalorgandbusiness.orgsetmeal.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.service.MealOrderService;
import com.java.medicalorgandbusiness.orgsetmeal.vo.OrganSetMeatOrder;
import com.java.platform.core.Action;

public class MealOrderAction extends Action  {

	private static final long serialVersionUID = 5899687471267645092L;

	private MealOrderService mealOrderService;

	private OrganSetMeatOrder organSetMeatOrder;
	
	public String updateOrderStatus(){
		
		if(organSetMeatOrder!=null){
			
			mealOrderService.updateOrderStatus(organSetMeatOrder);
		}
		return redirectActionResult("toList");
	}
	
	public String toList(){

		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		
		List<OrganSetMeatOrder> organSetMeatOrderList = mealOrderService.getOrganSetMeatOrderList(model,organSetMeatOrder);
		
		putToRequest("list", organSetMeatOrderList);
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public OrganSetMeatOrder getOrganSetMeatOrder() {
		return organSetMeatOrder;
	}

	public void setOrganSetMeatOrder(OrganSetMeatOrder organSetMeatOrder) {
		this.organSetMeatOrder = organSetMeatOrder;
	}

	public MealOrderService getMealOrderService() {
		return mealOrderService;
	}

	public void setMealOrderService(MealOrderService mealOrderService) {
		this.mealOrderService = mealOrderService;
	}
	
	
}
