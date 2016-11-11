package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyRingTypeBo;
import com.kybaby.newbussiness.mommyring.dao.MommyRingTypeDao;
import com.kybaby.newbussiness.mommyring.dao.MommySubscribeUserDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;
import com.kybaby.newbussiness.mommyring.util.ListManage;

/**
 * 
 * @ClassName:MommyRingTypeBoImpl
 * @Description:医生圈类型事物管理实现
 * @author Hoolee
 * @date 2015年12月10日下午3:22:18
 */
public class MommyRingTypeBoImpl implements MommyRingTypeBo {

	MommyRingTypeDao userRingTypeDao;
	MommySubscribeUserDao subscribeUserDao;
	
	public List<MommyRingType> getSomeCategoryDoctorRing(long someCategoryId) {
		return userRingTypeDao.getSomeCategoryDoctorRing(someCategoryId);
	}
	
	public List<MommyRingType> getSomeUserDoctoring(long userId) {
		List<Long> userRingIdList=subscribeUserDao.getSomeUserMommySubscribeUserId(userId);
		if(userRingIdList!=null){
			String userRingIdStr=ListManage.listToStr(userRingIdList);
			return userRingTypeDao.getSomeUserDoctoring(userRingIdStr);
		}
		return null;
	}

	public MommyRingType getSomeMommyRingTypeInstance(long ringTypeId) {
		return userRingTypeDao.getSomeMommyRingTypeInstance(ringTypeId);
	}

	public List<MommyRingType> getSomeMommyRingType(long userId) {
		List<Long> userRingIdList=subscribeUserDao.getSomeUserMommySubscribeUserId(userId);
		String userRingIdStr=ListManage.listToStr(userRingIdList);
		if(userRingIdStr!=null){
			return userRingTypeDao.getSomeMommyRingType(userRingIdStr);
		}else{
			return userRingTypeDao.getOsRecommendRingType();
		}
	}
	
	public List<MommyRingType> getOsRecommendRingType() {
		return userRingTypeDao.getOsRecommendRingType();
	}
	
	public void updateSomeMommyRingType(MommyRingType userRingType) {
		userRingTypeDao.updateSomeMommyRingType(userRingType);
	}

	public MommyRingTypeDao getMommyRingTypeDao() {
		return userRingTypeDao;
	}

	public void setMommyRingTypeDao(MommyRingTypeDao userRingTypeDao) {
		this.userRingTypeDao = userRingTypeDao;
	}

	public MommySubscribeUserDao getMommySubscribeUserDao() {
		return subscribeUserDao;
	}

	public void setMommySubscribeUserDao(MommySubscribeUserDao subscribeUserDao) {
		this.subscribeUserDao = subscribeUserDao;
	}

}
