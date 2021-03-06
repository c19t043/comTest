package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.ModifyDao;
import com.kybaby.domain.DoctorInfo;

/**
 * @author sujiantang
 *
 */
public class ModifyDaoImpl extends HibernateDaoSupport implements ModifyDao{

//	public DoctorInfo getDoctorInfoByDoctorId() {
//		return null;
//	}
//
	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		getHibernateTemplate().update(doctorInfo);
	}

	@Override
	public void updateDoctorAddress() {
		
	}

	@Override
	public DoctorInfo getDoctorInfoByPhone(String phone) {
		List list=getHibernateTemplate().find("from DoctorInfo where doctorPhone=? and doctorStatus='Y'",phone);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}

}
