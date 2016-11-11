package com.java.medicalorgandbusiness.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.service.PeadiatricsService;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.medicalorgandbusiness.vo.OrganSetChildCareRecode;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.platform.user.service.ServiceImpl;

public class PeadiatricsServiceImpl extends ServiceImpl implements
PeadiatricsService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}
	
	/**
	 * 获取儿科订单信息
	 * @param model
	 * @param orderInfoClinic
	 * @return
	 */
	public List<OrderInfoClinic> getOrderInfoClinicList(PageSortModel model,
			OrderInfoClinic orderInfoClinic){
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		
		sb.append("from OrderInfoClinic a where 1=1 ");
/*		sb.append(" and a.orderNum is not null");
		sb.append(" and a.orderType is not null");*/
		sb.append(" and a.orderStatus not in('用户取消','未付款')");
		if (orderInfoClinic != null) {
			if (StringUtils.isNotBlank(orderInfoClinic.getAppointmentDate())) {
				sb.append(" and a.appointmentDate = :openDate");
				params.put("openDate",
						orderInfoClinic.getAppointmentDate());
			}
			
			if (StringUtils.isNotBlank(orderInfoClinic.getDoctorInfo().getDoctorName())) {
				sb.append(" and a.doctorInfo.doctorName like :doctorName");
				params.put("doctorName",
						"%"+orderInfoClinic.getDoctorInfo().getDoctorName()+"%");
			}
			
			if (StringUtils.isNotBlank(orderInfoClinic
					.getOrderType())) {
				sb.append(" and a.orderType = :orderType");
				params.put("orderType",
						orderInfoClinic.getOrderType());
			}
			
			if (StringUtils.isNotBlank(orderInfoClinic
					.getOrderStatus())) {
				sb.append(" and a.orderStatus = :status");
				params.put("status",
						orderInfoClinic.getOrderStatus());
			}
		}
		
		
		List<OrderInfoClinic> listForEc = (List<OrderInfoClinic>) super.listForEc(sb.toString(), model, params);
		if(listForEc.isEmpty())return null;

		return listForEc;
	}
}
