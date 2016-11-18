package com.java.orgManager.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.orgManager.vo.OrganModuleInfo;
import com.java.platform.user.service.Service;

public interface OrganModuleInfoService extends Service  {
	/**
	 * 获取机构模块分页列表
	 * @param model
	 * @param organModuleInfo
	 * @return
	 */
	List<OrganModuleInfo> getOrganModuleInfoByPage(PageSortModel model,OrganModuleInfo organModuleInfo);
	/**
	 * 添加or保存机构模块
	 * @param organModuleInfo
	 * @return
	 */
	OrganModuleInfo saveOrUpdateOrganModuleInfo(OrganModuleInfo organModuleInfo);
}
