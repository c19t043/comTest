package com.java.doctormanager.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.consultmanager.consultdoctormanager.vo.ConsultOrderInfo;
import com.java.doctormanager.service.DoctorOrderGathrerService;
import com.java.doctormanager.vo.DoctorOrderSummary;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.tohomebusiness.vo.OrderInfo;
import com.java.util.DateManage;

public class DoctorOrderGathrerServiceImpl extends ServiceImpl implements DoctorOrderGathrerService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}


	@Override
	public void saveGatherDoctorOrderData() {
		String currentDate = DateManage.formatDateStr_yyyy_MM_dd(new Date());
		String startDate = currentDate+" 00:00:00";
		String endDate = currentDate+" 23:59:59";
		/* 
		 * 汇总当天医生的订单数据
		 */
		//儿保订单
		List<UserChildcareAppointmentInfo> currentDayChildCareOrders = getCurrentDayChildCareOrders(startDate,endDate);
		if(!currentDayChildCareOrders.isEmpty()){
			//保存儿保订单汇总信息
			for (UserChildcareAppointmentInfo userChildcareAppointmentInfo : currentDayChildCareOrders) {
				saveDoctorOrderDataInfo(userChildcareAppointmentInfo.getDoctorInfo(),
						"儿保", userChildcareAppointmentInfo.getId(), currentDate);
			}
		}
		//儿科订单
		List<OrderInfoClinic> currentDayPediatricsOrders = getCurrentDayPediatricsOrders(startDate,endDate);
		if(!currentDayPediatricsOrders.isEmpty()){
			//保存儿科订单汇总信息
			for (OrderInfoClinic orderInfoClinic : currentDayPediatricsOrders) {
				saveDoctorOrderDataInfo(orderInfoClinic.getDoctorInfo(),
						"儿科", orderInfoClinic.getId(), currentDate);
			}
		}
		//上门服务订单
		List<OrderInfo> currentDaySMFWOrders = getCurrentDaySMFWOrders(startDate,endDate);
		if(!currentDaySMFWOrders.isEmpty()){
			//保存上门服务订单汇总信息
			for (OrderInfo orderInfo : currentDaySMFWOrders) {
				saveDoctorOrderDataInfo(orderInfo.getDoctorInfo(),
						"上门服务", orderInfo.getId(), currentDate);
			}
		}
		//咨询订单
		List<ConsultOrderInfo> currentDayConsultOrderInfos = getCurrentDayConsultOrderInfos(startDate,endDate);
		if(!currentDayConsultOrderInfos.isEmpty()){
			//保存上门服务订单汇总信息
			for (ConsultOrderInfo consultOrderInfo : currentDayConsultOrderInfos) {
				saveDoctorOrderDataInfo(consultOrderInfo.getConsultDoctorInfo().getDoctorInfo(),
						"咨询", consultOrderInfo.getId(), currentDate);
			}
		}
	}
	/**
	 * 保存汇总信息
	 * @param doctorInfo 医生数据
	 * @param bussinessType 业务类型(儿科,儿保,上门服务,咨询)
	 * @param bussinessId 业务记录主键ID
	 * @param visitDate 服务日期
	 */
	private void saveDoctorOrderDataInfo(DoctorInfo doctorInfo,String bussinessType,Long bussinessId,String visitDate){
		DoctorOrderSummary doctorOrderSummary = new DoctorOrderSummary();
		doctorOrderSummary.setBussinessId(bussinessId);
		doctorOrderSummary.setBussinessType(bussinessType);
		doctorOrderSummary.setDoctorInfo(doctorInfo);
		doctorOrderSummary.setVisitDate(visitDate);
		super.add(doctorOrderSummary);
	}
	/**
	 * 获取当天获取咨询订单
	 * @return
	 */
	private List<ConsultOrderInfo> getCurrentDayConsultOrderInfos(String startDate, String endDate){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from ConsultOrderInfo c where 1=1 and c.orderStatus <> '未付款'");
		sb.append(" and c.orderTime >= :startDate");
		sb.append(" and c.orderTime <= :endDate");
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return super.list(sb.toString(), -1, -1, params);
	}
	/**
	 * 获取当天获取儿保订单
	 * @return
	 */
	private List<UserChildcareAppointmentInfo> getCurrentDayChildCareOrders(String startDate, String endDate){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from UserChildcareAppointmentInfo c where 1=1 and c.status not in ('未付款','用户取消')");
		sb.append(" and c.organChildcareOpenResources.openDate >= :startDate");
		sb.append(" and c.organChildcareOpenResources.openDate <= :endDate");
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return super.list(sb.toString(), -1, -1, params);
	}
	/**
	 * 获取当天获取儿科订单
	 * @param endDate 
	 * @param startDate 
	 * @return
	 */
	private List<OrderInfoClinic> getCurrentDayPediatricsOrders(String startDate, String endDate){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from OrderInfoClinic c where 1=1 and c.orderStatus not in ('未付款','用户取消')");
		sb.append(" and c.appointmentDate >= :startDate");
		sb.append(" and c.appointmentDate <= :endDate");
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return super.list(sb.toString(), -1, -1, params);
	}
	/**
	 * 获取当天获取上门服务订单
	 * @return
	 */
	private List<OrderInfo> getCurrentDaySMFWOrders(String startDate, String endDate){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sb.append("from OrderInfo c where 1=1 and c.orderStatus not in ('未付款','用户取消')");
		sb.append(" and c.bespokeDate >= :startDate");
		sb.append(" and c.bespokeDate <= :endDate");
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return super.list(sb.toString(), -1, -1, params);
	}
	
}
