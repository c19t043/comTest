package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyTheTypeInfoBo;
import com.kybaby.newbussiness.mommyring.dao.MommyTheTypeInfoDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;
import com.kybaby.newbussiness.mommyring.domain.MommyTheTypeInfo;

/**
 * 
 * @ClassName:MommyTheTypeInfoBoImpl
 * @Description:医生圈分类事物接口实现
 * @author Hoolee
 * @date 2015年12月10日下午4:40:47
 */
public class MommyTheTypeInfoBoImpl implements MommyTheTypeInfoBo {
	
	MommyTheTypeInfoDao theTypeInfoDao;
	
	public List<Object[]> getAllRingCategory() {
		return theTypeInfoDao.getAllRingCategory();
	}
	
	public Object[] getRemarkRingCategory() {
		return theTypeInfoDao.getRemarkRingCategory();
	}

	public MommyTheTypeInfo getSomeCategoryInstanceById(long categoryId) {
		return theTypeInfoDao.getSomeCategoryInstanceById(categoryId);
	}

	public MommyTheTypeInfoDao getMommyTheTypeInfoDao() {
		return theTypeInfoDao;
	}

	public void setMommyTheTypeInfoDao(MommyTheTypeInfoDao theTypeInfoDao) {
		this.theTypeInfoDao = theTypeInfoDao;
	}

	@Override
	public List<MommyTheTypeInfo> getMommyTheTypeInfoList(MommyTheTypeInfo theTypeInfo) {
		return this.theTypeInfoDao.getMommyTheTypeInfoList(theTypeInfo);
	}

	@Override
	public Long saveOrUpdateMommyTheTypeInfo(MommyTheTypeInfo theTypeInfo) {
		if(theTypeInfo != null){
			MommyTheTypeInfo newObj = new MommyTheTypeInfo();
			if(theTypeInfo.getId() != null){
				MommyTheTypeInfo old = this.theTypeInfoDao.getSomeCategoryInstanceById(theTypeInfo.getId());
				if(!theTypeInfo.getTypeName().equals(old.getTypeName())){//改了名称就判断是否重复
					newObj.setTypeName(theTypeInfo.getTypeName());
					List list = this.getMommyTheTypeInfoList(newObj);
					if(list != null){
						return 0L;
					}
				}
			}else{
				newObj.setTypeName(theTypeInfo.getTypeName());
				List list = this.getMommyTheTypeInfoList(newObj);
				if(list != null){
					return 0L;
				}
			}
		}
		return this.theTypeInfoDao.saveOrUpdateMommyTheTypeInfo(theTypeInfo);
	}

	@Override
	public List<MommyRingType> getMommyRingTypeListByMommyTheTypeInfoId(
			MommyTheTypeInfo theTypeInfo) {
		return this.theTypeInfoDao.getMommyRingTypeListByMommyTheTypeInfoId(theTypeInfo);
	}

	@Override
	public Long saveOrUpdateMommyRingType(MommyRingType userRingType) {
		return this.theTypeInfoDao.saveOrUpdateMommyRingType(userRingType);
	}

}
