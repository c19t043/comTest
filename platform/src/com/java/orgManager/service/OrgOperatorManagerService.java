package com.java.orgManager.service;

import java.util.List;

import com.java.doctorinfo.vo.OrganOperator;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;

public interface OrgOperatorManagerService extends Service {
	/**
	 * 获取机构操作人员分页列表
	 * @param model
	 * @param operaDoctorSchedule
	 * @return
	 */
	public List<OrganOperator> getOrganOperatorByPage(PageSortModel model,OrganOperator organOperator);
	/**
	 * 添加or保存机构操作人员
	 * @param organOperator
	 * @return
	 */
	OrganOperator saveOrUpdateOrganOperator(OrganOperator organOperator);
}
