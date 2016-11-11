package com.java.operationmanage.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.platform.user.service.Service;

public interface OperationModelService extends Service {
	/**
	 * 获取医生排班分页列表
	 * @param model
	 * @param operaDoctorSchedule
	 * @return
	 */
	public List<OperaDoctorSchedule> getOperaDoctorSchedulesListByPage(PageSortModel model,OperaDoctorSchedule operaDoctorSchedule);
}
