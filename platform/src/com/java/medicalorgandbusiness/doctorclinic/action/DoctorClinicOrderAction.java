package com.java.medicalorgandbusiness.doctorclinic.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.doctorclinic.service.DoctorClinicOrderService;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.core.Action;

public class DoctorClinicOrderAction extends Action{
	private static final long serialVersionUID = 1L;
	private DoctorClinicOrderService doctorClinicOrderService;
	private OrderInfoClinic orderInfoClinic;
	public DoctorClinicOrderService getDoctorClinicOrderService() {
		return doctorClinicOrderService;
	}
	public void setDoctorClinicOrderService(
			DoctorClinicOrderService doctorClinicOrderService) {
		this.doctorClinicOrderService = doctorClinicOrderService;
	}
	public OrderInfoClinic getOrderInfoClinic() {
		return orderInfoClinic;
	}
	public void setOrderInfoClinic(OrderInfoClinic orderInfoClinic) {
		this.orderInfoClinic = orderInfoClinic;
	}
	/**
	 * 门诊订单信息列表
	 * @return
	 */
	public String getClinicOrderList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(orderInfoClinic == null){
			orderInfoClinic = new OrderInfoClinic();
		}
		List<OrderInfoClinic> list = doctorClinicOrderService.getOrderInfoClinicList(psm, orderInfoClinic);
		this.putToRequest("list", list);
		return SUCCESS;
	}
}
