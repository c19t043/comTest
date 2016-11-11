package com.kybaby.bo.impl;

import com.kybaby.bo.ModifyBo;
import com.kybaby.dao.ModifyDao;
import com.kybaby.domain.DoctorInfo;

/**
 * @author sujiantang
 *
 */
public class ModifyBoImpl implements ModifyBo{
	
	private ModifyDao modifyDao;

//	public DoctorInfo getDoctorInfoByDoctorId() {
//		return null;
//	}
//
	@Override
	public DoctorInfo getDoctorInfoByPhone(String phone) {
		return modifyDao.getDoctorInfoByPhone(phone);
	}

	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		modifyDao.updateDoctorInfo(doctorInfo);
	}

	@Override
	public void updateDoctorAddress() {
		
	}
	
	public ModifyDao getModifyDao() {
		return modifyDao;
	}

	public void setModifyDao(ModifyDao modifyDao) {
		this.modifyDao = modifyDao;
	}

}
