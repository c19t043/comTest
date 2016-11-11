package com.kybaby.bo.impl;

import com.kybaby.bo.VersionManageBo;
import com.kybaby.dao.VersionManageDao;
import com.kybaby.domain.VersionManage;

/**
 * 版本管理BO接口实现类
 * @author xiongchao
 */
public class VersionManageBoImpl implements VersionManageBo {
	
	VersionManageDao versionManageDao;
	
	@Override
	public VersionManage getNewVersionCode(String versionType) {
		return versionManageDao.getNewVersionCode(versionType);
	}

	public VersionManageDao getVersionManageDao() {
		return versionManageDao;
	}

	public void setVersionManageDao(VersionManageDao versionManageDao) {
		this.versionManageDao = versionManageDao;
	}

}
