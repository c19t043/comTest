package com.java.familydoctor.archivesinfo.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.platform.user.service.Service;

public interface UserTypeService extends Service{
	
	/**
	 * 用户类型的添加和修改方法
	 * @param userType
	 * @return
	 */
	Long saveOrUpdateUserType(UserType userType);
	
	/**
	 * 通过id查询到用户类型的一条数据
	 * @param id
	 * @return
	 */
	UserType getUserTypeById(Long id);
	
	/**
	 * 用户类型列表方法
	 * @param psm
	 * @param userType
	 * @return
	 */
	List<UserType> getUserTypePage(PageSortModel psm,UserType userType);
}
