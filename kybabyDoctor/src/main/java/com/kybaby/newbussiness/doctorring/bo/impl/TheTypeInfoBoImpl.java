package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.TheTypeInfo;
import com.kybaby.newbussiness.doctorring.bo.TheTypeInfoBo;
import com.kybaby.newbussiness.doctorring.dao.TheTypeInfoDao;

/**
 * 
 * @ClassName:TheTypeInfoBoImpl
 * @Description:医生圈分类事物接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:40:47
 */
public class TheTypeInfoBoImpl implements TheTypeInfoBo {
	
	TheTypeInfoDao theTypeInfoDao;
	
	@Override
	public List<Object[]> getAllRingCategory() {
		return theTypeInfoDao.getAllRingCategory();
	}
	
	@Override
	public Object[] getRemarkRingCategory() {
		return theTypeInfoDao.getRemarkRingCategory();
	}

	@Override
	public TheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		return theTypeInfoDao.getSomeCategoryInstanceById(categoryId);
	}

	public TheTypeInfoDao getTheTypeInfoDao() {
		return theTypeInfoDao;
	}

	public void setTheTypeInfoDao(TheTypeInfoDao theTypeInfoDao) {
		this.theTypeInfoDao = theTypeInfoDao;
	}

}
