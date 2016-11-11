package com.kybaby.newbussiness.medicalorgandbusiness.dao;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.PageBean;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public interface ArchivesInfoDao {
	/**
	 * 得到用户身份列表信息
	 * @param archivesInfo
	 * @return
	 */
	List<ArchivesInfo> getArchivesInfoList(ArchivesInfo archivesInfo,PageBean pageBean,OrganOperator organOperator);
	/**
	 * 保存或更新用户身份信息
	 * @param archivesInfo
	 * @return
	 */
	Long saveOrUpateArchivesInfo(ArchivesInfo archivesInfo);
	/**
	 * 得到身份信息
	 * @param Long id
	 * @return
	 */
	ArchivesInfo getArchivesInfoById(Long id);
	/**
	 * 得到用户类型列表
	 * @param userType
	 * @return
	 */
	List<UserType> getUserTypeList(UserType userType);
}
