package com.kybaby.newbussiness.medicalorgandbusiness.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.medicalorgandbusiness.bo.ArchivesInfoService;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.ArchivesInfoDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganOperator;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.PageBean;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserType;

public class ArchivesInfoServiceImpl implements ArchivesInfoService{
	private ArchivesInfoDao archivesInfoDao;

	public ArchivesInfoDao getArchivesInfoDao() {
		return archivesInfoDao;
	}

	public void setArchivesInfoDao(ArchivesInfoDao archivesInfoDao) {
		this.archivesInfoDao = archivesInfoDao;
	}

	@Override
	public List<ArchivesInfo> getArchivesInfoList(ArchivesInfo archivesInfo,PageBean pageBean,OrganOperator organOperator) {
		return this.archivesInfoDao.getArchivesInfoList(archivesInfo,pageBean,organOperator);
	}

	@Override
	public Long saveOrUpateArchivesInfo(ArchivesInfo archivesInfo) {
		return this.archivesInfoDao.saveOrUpateArchivesInfo(archivesInfo);
	}

	@Override
	public ArchivesInfo getArchivesInfoById(Long id) {
		return archivesInfoDao.getArchivesInfoById(id);
	}

	@Override
	public List<UserType> getUserTypeList(UserType userType) {
		return archivesInfoDao.getUserTypeList(userType);
	}
}
