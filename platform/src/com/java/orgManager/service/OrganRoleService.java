package com.java.orgManager.service;

import java.util.List;

import com.java.doctorinfo.vo.OrganOperator;
import com.java.ec.common.PageSortModel;
import com.java.orgManager.vo.OrganModuleInfo;
import com.java.orgManager.vo.OrganRole;
import com.java.platform.user.service.Service;

public interface OrganRoleService extends Service  {
	/**
	 * 获取机构角色分页列表
	 * @param model
	 * @param organRole
	 * @return
	 */
	List<OrganRole> getOrganRoleByPage(PageSortModel model,OrganRole organRole);
	/**
	 * 添加or保存机构角色
	 * @param organRole
	 * @return
	 */
	OrganRole saveOrUpdateOrganRole(OrganRole organRole);
	/**
	 * 查询角色分配的用户
	 * @param roleID 角色ID
	 */
	List<OrganOperator> queryRoleOwnedUsers(Long roleID);
	/**
	 * 保存角色分配的用户
	 * @param roleID 角色ID
	 * @param operatorIDs 用户id串
	 */
	void saveRoleOwnedUsers(Long roleID,String operatorIDs);
	/**
	 * 查询角色分配的模块
	 * @param roleID 角色ID
	 */
	List<OrganModuleInfo> queryRoleOwnedModules(Long roleID);
	/**
	 * 保存角色分配的模块
	 * @param roleID 角色ID
	 * @param moduleIDs 用户id串
	 */
	void saveRoleOwnedModules(Long roleID,String moduleIDs);
}
