package com.kybaby.bo.impl;

import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.dao.DoctorInfoDao;
import com.kybaby.domain.DoctorInfo;

/**
 * @author sujiantang
 *
 */
public class DoctorInfoBoImpl implements DoctorInfoBo{
	
	private DoctorInfoDao doctorInfoDao;

	@Override
	public DoctorInfo getDoctorInfoByPhone(String phone) {
		return doctorInfoDao.getDoctorInfoByPhone(phone);
	}
	
	@Override
	public void save(DoctorInfo doctorInfo) {
		doctorInfoDao.save(doctorInfo);
	}
	@Override
	public void update(DoctorInfo doctorInfo) {
		doctorInfoDao.update(doctorInfo);
	}
	
	@Override
	public DoctorInfo getDoctorInfoByOpenId(String openId) {
		return doctorInfoDao.getDoctorInfoByOpenId(openId);
	}
	
	//注册专用
	@Override
	public DoctorInfo getDoctorInfoByPhoneNum(String phone) {
		return doctorInfoDao.getDoctorInfoByPhoneNum(phone);
	}

	@Override
	public DoctorInfo getDoctorInfoBoById(long id) {
		return doctorInfoDao.getDoctorInfoBoById(id);
	}
	
	public DoctorInfoDao getDoctorInfoDao() {
		return doctorInfoDao;
	}

	public void setDoctorInfoDao(DoctorInfoDao doctorInfoDao) {
		this.doctorInfoDao = doctorInfoDao;
	}


}
