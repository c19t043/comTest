package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorAddress;

public interface DoctorAddressDao {

	//2.7.1 查看医生
	List<DoctorAddress> getDoctorAddressById(long doctorId);//通过医生Id找到所有的地址
}
