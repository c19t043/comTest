package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.AddressManageDao;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorProduct;

/**
 * @author sujiantang
 *
 */
public class AddressManageDaoImpl extends HibernateDaoSupport implements AddressManageDao{

	@Override
	public List<DoctorAddress> getAllAddressByDoctorId(Long id) {
		List list=getHibernateTemplate().find("from DoctorAddress where doctorId=? and addressStatus!='0'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public void save(DoctorAddress doctorAddress) {
		getHibernateTemplate().save(doctorAddress);
	}

	@Override
	public DoctorAddress getAddressById(Long addressId) {
		List list=getHibernateTemplate().find("from DoctorAddress where id=?",addressId);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorAddress)list.get(0);
	}

	@Override
	public void update(DoctorAddress doctorAddress) {
		getHibernateTemplate().update(doctorAddress);
	}

	@Override
	public List<DoctorProduct> getSomeAddressDoctorProduct(long addressId,String isProvide) {
		List<DoctorProduct> list=getHibernateTemplate().find("from DoctorProduct where addressId=? and isProvide=? ", new Object[]{addressId,isProvide});
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public void deleteSomeAddress(DoctorProduct doctorProduct) {
		getHibernateTemplate().delete(doctorProduct);
	}

}
