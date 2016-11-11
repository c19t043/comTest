package com.java.consultmanager.consultdoctormanager.action;

import java.util.List;

import com.java.consultmanager.consultdoctormanager.service.ConsultOrderInfoService;
import com.java.consultmanager.consultdoctormanager.vo.ConsultOrderInfo;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;

public class ConsultOrderInfoAction extends Action{
	private static final long serialVersionUID = 1L;
	
	private ConsultOrderInfo consultOrderInfo;
	
	private ConsultOrderInfoService consultOrderInfoService;
	
	public String toListPage(){
		
		PageSortModel model = new PageSortModel(this.getHttpServletRequest(),"list");
		
		List<ConsultOrderInfo> order_collection = 
			consultOrderInfoService.getObjectListOfConsultOrderInfoWithPage(model, consultOrderInfo);
		
		putToRequest("list", order_collection);
		
		return SUCCESS;
	}

	
	
	
	//--------------------------------------------------------------
	public ConsultOrderInfo getConsultOrderInfo() {
		return consultOrderInfo;
	}

	public void setConsultOrderInfo(ConsultOrderInfo consultOrderInfo) {
		this.consultOrderInfo = consultOrderInfo;
	}

	public ConsultOrderInfoService getConsultOrderInfoService() {
		return consultOrderInfoService;
	}

	public void setConsultOrderInfoService(
			ConsultOrderInfoService consultOrderInfoService) {
		this.consultOrderInfoService = consultOrderInfoService;
	}
	
	
}
