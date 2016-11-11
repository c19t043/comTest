package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyTheTypeInfoBo;
import com.kybaby.newbussiness.mommyring.dao.MommyTheTypeInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyTheTypeInfo;

/**
 * 
 * @ClassName:TheTypeInfoBoImpl
 * @Description:医生圈分类事物接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:40:47
 */
public class MommyTheTypeInfoBoImpl implements MommyTheTypeInfoBo {
	
	MommyTheTypeInfoDao mommyTheTypeInfoDao;
	
	public List<Object[]> getAllRingCategory() {
		return mommyTheTypeInfoDao.getAllRingCategory();
	}
	
	public Object[] getRemarkRingCategory() {
		return mommyTheTypeInfoDao.getRemarkRingCategory();
	}

	public MommyTheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		return mommyTheTypeInfoDao.getSomeCategoryInstanceById(categoryId);
	}

	public MommyTheTypeInfoDao getMommyTheTypeInfoDao() {
		return mommyTheTypeInfoDao;
	}

	public void setMommyTheTypeInfoDao(MommyTheTypeInfoDao mommyTheTypeInfoDao) {
		this.mommyTheTypeInfoDao = mommyTheTypeInfoDao;
	}

}
