package com.java.medicalorgandbusiness.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.vo.OrderInfoClinic;
import com.java.platform.user.service.Service;

public interface PeadiatricsService extends Service {

	/**
	 * 获取儿科订单信息
	 * @param model
	 * @param orderInfoClinic
	 * @return
	 */
	List<OrderInfoClinic> getOrderInfoClinicList(PageSortModel model,
			OrderInfoClinic orderInfoClinic);

	
}
