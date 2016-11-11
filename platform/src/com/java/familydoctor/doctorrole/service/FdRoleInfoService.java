package com.java.familydoctor.doctorrole.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.doctorrole.vo.FdRoleInfo;
import com.java.platform.user.service.Service;

public interface FdRoleInfoService extends Service{
	
	/**
	 * 家庭医生角色添加方法
	 */
	Long saveOrUpdateFdRoleInfo(FdRoleInfo fdRoleInfo);
	
	/**
	 * 家庭医生角色通过id查询一条数据方法
	 */
	FdRoleInfo getFdRoleInfoById(Long id);
	
	/**
	 * 家庭医生角色显示列表方法
	 */
	List<FdRoleInfo> getFdRoleInfoListByPage(PageSortModel psm,FdRoleInfo fdRoleInfo);
}
