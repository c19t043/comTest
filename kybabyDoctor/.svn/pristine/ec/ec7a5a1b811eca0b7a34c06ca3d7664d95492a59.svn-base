package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorProduct;

/**
 * @author sujiantang
 *
 */
public interface AddressManageDao {

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
	 * @Discription:获取某一个地址的某一个状态的DoctorProduct实例列表
	 * @data: 2015年11月25日下午3:25:17
	 * @param addressId 地址的ID
	 * @param isProvide 是否已被预约
	 * @return DoctorProduct实例列表
	 */
	List<DoctorProduct> getSomeAddressDoctorProduct(long addressId,String isProvide);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:删除某一个DoctorProduct实例
	 * @data: 2015年11月25日下午3:28:35
	 * @param doctorProduct 实例
	 */
	void deleteSomeAddress(DoctorProduct doctorProduct);
}
