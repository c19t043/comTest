package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.DoctorRingType;
import com.kybaby.newbussiness.doctorring.bo.DoctorRingTypeBo;
import com.kybaby.newbussiness.doctorring.dao.DoctorRingTypeDao;
import com.kybaby.newbussiness.doctorring.dao.SubscribeUserDao;
import com.kybaby.newbussiness.doctorring.util.ListManage;

/**
 * 
 * @ClassName:DoctorRingTypeBoImpl
 * @Description:医生圈类型事物管理实现
 * @author Hoolee
 * @date 2015年12月10日下午3:22:18
 */
public class DoctorRingTypeBoImpl implements DoctorRingTypeBo {

	DoctorRingTypeDao doctorRingTypeDao;
	SubscribeUserDao subscribeUserDao;
	
	@Override
	public List<DoctorRingType> getSomeCategoryDoctorRing(long someCategoryId) {
		return doctorRingTypeDao.getSomeCategoryDoctorRing(someCategoryId);
	}
	
	@Override
	public List<DoctorRingType> getSomeUserDoctoring(long userId) {
		List<Long> doctorRingIdList=subscribeUserDao.getSomeUserSubscribeUserId(userId);
		if(doctorRingIdList!=null){
			String doctorRingIdStr=ListManage.listToStr(doctorRingIdList);
			return doctorRingTypeDao.getSomeUserDoctoring(doctorRingIdStr);
		}
		return null;
	}

	@Override
	public DoctorRingType getSomeDoctorRingTypeInstance(long ringTypeId) {
		return doctorRingTypeDao.getSomeDoctorRingTypeInstance(ringTypeId);
	}

	@Override
	public List<DoctorRingType> getSomeDoctorRingType(long doctorId) {
		List<Long> doctorRingIdList=subscribeUserDao.getSomeUserSubscribeUserId(doctorId);
		String doctorRingIdStr=ListManage.listToStr(doctorRingIdList);
		if(doctorRingIdStr!=null){
			return doctorRingTypeDao.getSomeDoctorRingType(doctorRingIdStr);
		}else{
			return doctorRingTypeDao.getOsRecommendRingType();
		}
	}
	
	@Override
	public List<DoctorRingType> getOsRecommendRingType() {
		return doctorRingTypeDao.getOsRecommendRingType();
	}
	
	@Override
	public void updateSomeDoctorRingType(DoctorRingType doctorRingType) {
		doctorRingTypeDao.updateSomeDoctorRingType(doctorRingType);
	}

	public DoctorRingTypeDao getDoctorRingTypeDao() {
		return doctorRingTypeDao;
	}

	public void setDoctorRingTypeDao(DoctorRingTypeDao doctorRingTypeDao) {
		this.doctorRingTypeDao = doctorRingTypeDao;
	}

	public SubscribeUserDao getSubscribeUserDao() {
		return subscribeUserDao;
	}

	public void setSubscribeUserDao(SubscribeUserDao subscribeUserDao) {
		this.subscribeUserDao = subscribeUserDao;
	}

}
