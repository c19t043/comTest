package com.java.familydoctor.archivesinfo.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.ArchivesInfo;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.platform.user.service.Service;

public interface ArchivesInfoService extends Service{
	
	
	/**
	 * 档案信息的添加和修改方法
	 * @param archivesInfo
	 * @return
	 */
	Long saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo);
	
	/**
	 * 档案信息通过id查询一条记录方法
	 * @param id
	 * @return
	 */
	ArchivesInfo getArchivesInfoById(Long id);
	
	/**
	 * 档案信息的列表方法
	 * @param psm
	 * @param archivesInfo
	 * @return
	 */
	List<ArchivesInfo> getArchivesInfoByPage(PageSortModel psm,ArchivesInfo archivesInfo);
	
	/**
	 * 获取用户类型列表
	 * @param psm
	 * @param userType
	 * @return
	 */
	List<UserType> getUserTypeList(PageSortModel psm,UserType userType);
}	
