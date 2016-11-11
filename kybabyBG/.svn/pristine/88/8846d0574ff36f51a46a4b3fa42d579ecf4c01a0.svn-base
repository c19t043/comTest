package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.DoctorAddressBo;
import com.kybaby.dao.DoctorAddressDao;
import com.kybaby.domain.DoctorAddress;

public class DoctorAddressBoImpl implements DoctorAddressBo {
 
	DoctorAddressDao doctorAddressDao;
	@Override
	public List<DoctorAddress> getDoctorAddressById(long doctorId) {
		// TODO Auto-generated method stub
		return doctorAddressDao.getDoctorAddressById(doctorId);
	}
	public DoctorAddressDao getDoctorAddressDao() {
		return doctorAddressDao;
	}
	public void setDoctorAddressDao(DoctorAddressDao doctorAddressDao) {
		this.doctorAddressDao = doctorAddressDao;
	}

	//2.7.1 查看医生
//	List<DoctorAddress> getDoctorAddressById(long doctorId);//通过医生Id找到所有的地址
}
