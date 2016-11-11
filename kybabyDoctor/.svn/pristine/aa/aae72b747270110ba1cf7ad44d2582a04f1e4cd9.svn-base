package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.TimeInit;

/**
 * @author sujiantang
 *
 */
public interface SetServiceTimeDao {

	//获取服务时间段
	List<TimeInit> getServiceTimeList();
	//获取医生的地址
	List<DoctorAddress> getDoctorAddressByDoctorId(Long id);
	//获取已经使用的时间段
	List<DoctorProduct> getInUseTimeByAddressIdAndDoctorId(Long addressId, Long doctorId);
	//获取某个地址已经使用的时间段
	List<DoctorProduct> getDoctorProduct(Long id, Long addressId);
	List<DoctorProduct> getDoctorServiceTime(Long doctorId);
	//通过时间段ID获取时间段
	TimeInit getSomeTimeInitById(Long timeId);
	//保存设置的服务时间
	void saveDoctorProduct(DoctorProduct doctorProduct);
	//保存服务时间前先删除如果存在
	void deleteSomeTime(Long addressId);
}
