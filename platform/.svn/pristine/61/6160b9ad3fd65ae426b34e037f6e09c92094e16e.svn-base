package com.java.medicalorgandbusiness.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.service.ChildCareSetMealService;
import com.java.medicalorgandbusiness.vo.OrganSetChildCareRecode;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.platform.user.service.ServiceImpl;

public class ChildCareSetMealServiceImpl extends ServiceImpl implements
		ChildCareSetMealService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	/**
	 * 根据儿保订单id，获取儿保订单明细
	 * @param id
	 * @return
	 */
	public OrganSetChildCareRecode getOrganSetChildCareRecode(Long id){
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append("from OrganSetChildCareRecode a where 1=1 ");
		sb.append(" and a.userChildcareAppointmentInfo.id = "+id);
		// params.put("id", id);
		
		List<Object> list = super.list(sb.toString(), -1, -1, params);
		if(list.isEmpty()) return null;
		
		return (OrganSetChildCareRecode) list.get(0);
	}
	/**
	 * 修改儿保订单明细
	 * @param organSetChildCareRecode
	 */
	public void updateChildCareDetail(
			OrganSetChildCareRecode organSetChildCareRecode){
		OrganSetChildCareRecode recode_query = super.get(organSetChildCareRecode.getId(), OrganSetChildCareRecode.class);
		BeanUtils.copyProperties(organSetChildCareRecode, recode_query, new String[]{"id","userChildcareAppointmentInfo"});
		super.edit(recode_query);
	}
	
	
	/**
	 * 根据订单类型获取儿保订单
	 * 
	 * @param orderType
	 *            订单类型
	 * @return
	 */
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(
			PageSortModel model,
			UserChildcareAppointmentInfo userChildcareAppointmentInfo) {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append("from UserChildcareAppointmentInfo a where 1=1 ");
/*		sb.append(" and a.orderNum is not null");
		sb.append(" and a.orderType is not null");*/
		sb.append(" and a.status not in ('用户取消','未付款')");
		if (userChildcareAppointmentInfo != null) {
			if (StringUtils.isNotBlank(userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate())) {
				sb.append(" and a.organChildcareOpenResources.openDate = :openDate");
				params.put("openDate",
						userChildcareAppointmentInfo.getOrganChildcareOpenResources().getOpenDate());
			}
			
			if (StringUtils.isNotBlank(userChildcareAppointmentInfo.getDoctorInfo().getDoctorName())) {
				sb.append(" and a.doctorInfo.doctorName like :doctorName");
				params.put("doctorName",
						"%"+userChildcareAppointmentInfo.getDoctorInfo().getDoctorName()+"%");
			}
			
			if (StringUtils.isNotBlank(userChildcareAppointmentInfo
					.getOrderType())) {
				sb.append(" and a.orderType = :orderType");
				params.put("orderType",
						userChildcareAppointmentInfo.getOrderType());
			}
			
			if (StringUtils.isNotBlank(userChildcareAppointmentInfo
					.getStatus())) {
				sb.append(" and a.status = :status");
				params.put("status",
						userChildcareAppointmentInfo.getStatus());
			}
		}
		
		List<UserChildcareAppointmentInfo> list = (List<UserChildcareAppointmentInfo>) super
				.listForEc(sb.toString(), model, params);

		return list;
	}
}
