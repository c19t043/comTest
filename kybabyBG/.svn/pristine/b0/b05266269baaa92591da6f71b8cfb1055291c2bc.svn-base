package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorRingType;
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
	
	public List<Object[]> getAllRingCategory() {
		return theTypeInfoDao.getAllRingCategory();
	}
	
	public Object[] getRemarkRingCategory() {
		return theTypeInfoDao.getRemarkRingCategory();
	}

	public TheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		return theTypeInfoDao.getSomeCategoryInstanceById(categoryId);
	}

	public TheTypeInfoDao getTheTypeInfoDao() {
		return theTypeInfoDao;
	}

	public void setTheTypeInfoDao(TheTypeInfoDao theTypeInfoDao) {
		this.theTypeInfoDao = theTypeInfoDao;
	}

	@Override
	public List<TheTypeInfo> getTheTypeInfoList(TheTypeInfo theTypeInfo) {
		return this.theTypeInfoDao.getTheTypeInfoList(theTypeInfo);
	}

	@Override
	public Long saveOrUpdateTheTypeInfo(TheTypeInfo theTypeInfo) {
		if(theTypeInfo != null){
			TheTypeInfo newObj = new TheTypeInfo();
			if(theTypeInfo.getId() != null){
				TheTypeInfo old = this.theTypeInfoDao.getSomeCategoryInstanceById(theTypeInfo.getId());
				if(!theTypeInfo.getTypeName().equals(old.getTypeName())){//改了名称就判断是否重复
					newObj.setTypeName(theTypeInfo.getTypeName());
					List list = this.getTheTypeInfoList(newObj);
					if(list != null){
						return 0L;
					}
				}
			}else{
				newObj.setTypeName(theTypeInfo.getTypeName());
				List list = this.getTheTypeInfoList(newObj);
				if(list != null){
					return 0L;
				}
			}
		}
		return this.theTypeInfoDao.saveOrUpdateTheTypeInfo(theTypeInfo);
	}

	@Override
	public List<DoctorRingType> getDoctorRingTypeListByTheTypeInfoId(
			TheTypeInfo theTypeInfo) {
		return this.theTypeInfoDao.getDoctorRingTypeListByTheTypeInfoId(theTypeInfo);
	}

	@Override
	public Long saveOrUpdateDoctorRingType(DoctorRingType doctorRingType) {
		return this.theTypeInfoDao.saveOrUpdateDoctorRingType(doctorRingType);
	}

}
