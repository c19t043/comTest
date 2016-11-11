package com.kybaby.newbussiness.asqtest.bo.impl;

import com.kybaby.newbussiness.asqtest.bo.AsqTestUserInfoService;
import com.kybaby.newbussiness.asqtest.dao.AsqTestUserInfoDao;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;

public class AsqTestUserInfoServiceImpl implements AsqTestUserInfoService {
	private AsqTestUserInfoDao asqTestUserInfoDao;
	public AsqTestUserInfoDao getAsqTestUserInfoDao() {
		return asqTestUserInfoDao;
	}

	public void setAsqTestUserInfoDao(AsqTestUserInfoDao asqTestUserInfoDao) {
		this.asqTestUserInfoDao = asqTestUserInfoDao;
	}

	@Override
	public Long saveOrUpdateAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		return asqTestUserInfoDao.saveOrUpdateAsqTestUserInfo(asqTestUserInfo);
	}

	@Override
	public AsqTestUserInfo getAsqTestUserInfoById(Long asqUserId) {
		return asqTestUserInfoDao.getAsqTestUserInfoById(asqUserId);
	}

}
