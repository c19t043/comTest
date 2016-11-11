package com.java.medicalorgandbusiness.childcare.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.childcare.service.ChildCareOrderService;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.platform.core.Action;

public class ChildCareOrderAction extends Action{
	private static final long serialVersionUID = 1L;
	private ChildCareOrderService childCareOrderService;
	private UserChildcareAppointmentInfo userChildcareAppointmentInfo;
	public ChildCareOrderService getChildCareOrderService() {
		return childCareOrderService;
	}
	public void setChildCareOrderService(ChildCareOrderService childCareOrderService) {
		this.childCareOrderService = childCareOrderService;
	}
	public UserChildcareAppointmentInfo getUserChildcareAppointmentInfo() {
		return userChildcareAppointmentInfo;
	}
	public void setUserChildcareAppointmentInfo(
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		this.userChildcareAppointmentInfo = userChildcareAppointmentInfo;
	}
	
	/**
	 * 儿保订单信息列表
	 * @return
	 */
	public String getChildcareOrderList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(userChildcareAppointmentInfo == null){
			userChildcareAppointmentInfo = new UserChildcareAppointmentInfo();
		}
		List<UserChildcareAppointmentInfo> list = childCareOrderService.getUserChildcareAppointmentInfoList(psm, userChildcareAppointmentInfo);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
