package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.DoctorAddressDao;
import com.kybaby.domain.DoctorAddress;

public class DoctorAddressDaoImpl extends HibernateDaoSupport implements DoctorAddressDao {

	@Override
	public List<DoctorAddress> getDoctorAddressById(long doctorId) {
		String hql="FROM DoctorAddress WHERE doctorId='"+doctorId+"'";
		List list=getHibernateTemplate().find(hql);
		if(list.isEmpty()==true)
		return null;
		else return list;
	}

}
