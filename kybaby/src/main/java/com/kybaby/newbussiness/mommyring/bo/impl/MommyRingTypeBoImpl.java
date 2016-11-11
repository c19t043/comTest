package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyRingTypeBo;
import com.kybaby.newbussiness.mommyring.dao.MommyRingTypeDao;
import com.kybaby.newbussiness.mommyring.dao.MommySubscribeUserDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;
import com.kybaby.newbussiness.mommyring.util.ListManage;

/**
 * 
 * @ClassName:DoctorRingTypeBoImpl
 * @Description:医生圈类型事物管理实现
 * @author Hoolee
 * @date 2015年12月10日下午3:22:18
 */
public class MommyRingTypeBoImpl implements MommyRingTypeBo {

	MommyRingTypeDao mommyRingTypeDao;
	MommySubscribeUserDao mommySubscribeUserDao;
	
	public List<MommyRingType> getSomeCategoryMommyRing(long someCategoryId) {
		return mommyRingTypeDao.getSomeCategoryMommyRing(someCategoryId);
	}
	
	public List<MommyRingType> getSomeUserMommyring(long userId) {
		List<Long> doctorRingIdList=mommySubscribeUserDao.getSomeUserSubscribeUserId(userId);
		if(doctorRingIdList!=null){
			String doctorRingIdStr=ListManage.listToStr(doctorRingIdList);
			return mommyRingTypeDao.getSomeUserMommyring(doctorRingIdStr);
		}
		return null;
	}

	public MommyRingType getSomeMommyRingTypeInstance(long ringTypeId) {
		return mommyRingTypeDao.getSomeMommyRingTypeInstance(ringTypeId);
	}

	public List<MommyRingType> getSomeMommyRingType(long userId) {
		List<Long> doctorRingIdList=mommySubscribeUserDao.getSomeUserSubscribeUserId(userId);
		String doctorRingIdStr=ListManage.listToStr(doctorRingIdList);
		if(doctorRingIdStr!=null){
			return mommyRingTypeDao.getSomeMommyRingType(doctorRingIdStr);
		}else{
			return mommyRingTypeDao.getOsRecommendRingType();
		}
	}
	
	public List<MommyRingType> getOsRecommendRingType() {
		return mommyRingTypeDao.getOsRecommendRingType();
	}
	
	public void updateSomeMommyRingType(MommyRingType mommyRingType) {
		mommyRingTypeDao.updateSomeMommyRingType(mommyRingType);
	}

	public MommyRingTypeDao getMommyRingTypeDao() {
		return mommyRingTypeDao;
	}

	public void setMommyRingTypeDao(MommyRingTypeDao mommyRingTypeDao) {
		this.mommyRingTypeDao = mommyRingTypeDao;
	}

	public MommySubscribeUserDao getMommySubscribeUser() {
		return mommySubscribeUserDao;
	}

	public void setMommySubscribeUserDao(MommySubscribeUserDao mommySubscribeUserDao) {
		this.mommySubscribeUserDao = mommySubscribeUserDao;
	}

}
