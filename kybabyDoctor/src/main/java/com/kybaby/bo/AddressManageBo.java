package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.DoctorAddress;

/**
 * @author sujiantang
 *
 */
public interface AddressManageBo {
	
	//通过医生ID获取所有地址
	List<DoctorAddress> getAllAddressByDoctorId(Long id);
	//保存地址
	void save(DoctorAddress doctorAddress);
	//通过地址ID获取地址
	DoctorAddress getAddressById(Long addressId);
	//更新地址
	void update(DoctorAddress doctorAddress);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过地址的ID和地址的预约状态删除相关的doctorProduct相关的
	 * @data: 2015年11月25日下午3:21:39
	 * @param addressId 地址的ID
	 * @param isProvide 是否已经被预约
	 */
	void deleteSomeDoctorAddress(long addressId,String isProvide);
}
