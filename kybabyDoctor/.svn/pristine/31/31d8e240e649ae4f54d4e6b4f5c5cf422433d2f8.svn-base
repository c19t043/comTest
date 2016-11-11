package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.SetServiceTimeBo;
import com.kybaby.dao.SetServiceTimeDao;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.TimeInit;

/**
 * @author sujiantang
 *
 */
public class SetServiceTimeBoImpl implements SetServiceTimeBo{
	
	private SetServiceTimeDao setServiceTimeDao;

	@Override
	public List<TimeInit> getServiceTimeList() {
		return setServiceTimeDao.getServiceTimeList();
	}

	@Override
	public List<DoctorAddress> getDoctorAddressByDoctorId(Long id) {
		return setServiceTimeDao.getDoctorAddressByDoctorId(id);
	}
	
	@Override
	public List<DoctorProduct> getInUseTimeByAddressIdAndDoctorId(
			Long addressId, Long doctorId) {
		return setServiceTimeDao.getInUseTimeByAddressIdAndDoctorId(addressId, doctorId);
	}
	
	@Override
	public List<DoctorProduct> getDoctorProduct(Long id, Long addressId) {
		return setServiceTimeDao.getDoctorProduct(id, addressId);
	}
	
	@Override
	public List<DoctorProduct> getDoctorServiceTime(Long doctorId){
		return setServiceTimeDao.getDoctorServiceTime(doctorId);
	}

	public SetServiceTimeDao getSetServiceTimeDao() {
		return setServiceTimeDao;
	}

	public void setSetServiceTimeDao(SetServiceTimeDao setServiceTimeDao) {
		this.setServiceTimeDao = setServiceTimeDao;
	}

	@Override
	public TimeInit getSomeTimeInitById(Long timeId) {
		return setServiceTimeDao.getSomeTimeInitById(timeId);
	}

	@Override
	public void saveDoctorProduct(DoctorProduct doctorProduct) {
		setServiceTimeDao.saveDoctorProduct(doctorProduct);
	}

	@Override
	public void deleteSomeTime(Long addressId) {
		setServiceTimeDao.deleteSomeTime(addressId);
	}
}
