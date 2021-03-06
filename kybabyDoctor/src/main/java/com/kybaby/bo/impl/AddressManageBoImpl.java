package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.AddressManageBo;
import com.kybaby.dao.AddressManageDao;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorProduct;

/**
 * @author sujiantang
 *
 */
public class AddressManageBoImpl implements AddressManageBo{
	
	private AddressManageDao addressManageDao;

	@Override
	public List<DoctorAddress> getAllAddressByDoctorId(Long id) {
		return addressManageDao.getAllAddressByDoctorId(id);
	}

	@Override
	public void save(DoctorAddress doctorAddress) {
		addressManageDao.save(doctorAddress);
	}

	@Override
	public DoctorAddress getAddressById(Long addressId) {
		return addressManageDao.getAddressById(addressId);
	}

	@Override
	public void update(DoctorAddress doctorAddress) {
		addressManageDao.update(doctorAddress);
	}

	@Override
	public void deleteSomeDoctorAddress(long addressId, String isProvide) {
		List<DoctorProduct> doctorProduct=addressManageDao.getSomeAddressDoctorProduct(addressId, isProvide);
		if(doctorProduct!=null){
			for(int i =0;i<doctorProduct.size();i++){
				addressManageDao.deleteSomeAddress(doctorProduct.get(i));
			}
		}
	}

	public AddressManageDao getAddressManageDao() {
		return addressManageDao;
	}

	public void setAddressManageDao(AddressManageDao addressManageDao) {
		this.addressManageDao = addressManageDao;
	}

}
