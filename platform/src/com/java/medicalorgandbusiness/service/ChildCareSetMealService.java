package com.java.medicalorgandbusiness.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.vo.OrganSetChildCareRecode;
import com.java.medicalorgandbusiness.vo.UserChildcareAppointmentInfo;
import com.java.platform.user.service.Service;

public interface ChildCareSetMealService extends Service {
	
	/**
	 * 根据订单类型获取儿保订单
	 * @param orderType 订单类型
	 * @return
	 */
	public List<UserChildcareAppointmentInfo> getUserChildcareAppointmentInfoList(PageSortModel model,UserChildcareAppointmentInfo userChildcareAppointmentInfo);

	/**
	 * 修改儿保订单明细
	 * @param organSetChildCareRecode
	 */
	public void updateChildCareDetail(
			OrganSetChildCareRecode organSetChildCareRecode);

	/**
	 * 根据儿保订单id，获取儿保订单明细
	 * @param id
	 * @return
	 */
	public OrganSetChildCareRecode getOrganSetChildCareRecode(Long id);
}
